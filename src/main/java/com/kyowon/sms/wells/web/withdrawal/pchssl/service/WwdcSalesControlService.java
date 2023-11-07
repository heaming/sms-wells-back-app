package com.kyowon.sms.wells.web.withdrawal.pchssl.service;

import java.util.*;
import java.util.regex.Pattern;

import com.kyowon.sms.common.web.closing.sales.dvo.ZdcbSalesDiscountCancelDvo;
import com.kyowon.sms.common.web.closing.sales.service.ZdcbSalesDiscountCancelService;
import com.kyowon.sms.common.web.withdrawal.common.dto.ZwwdbContractDetailInquiryDto;
import com.kyowon.sms.common.web.withdrawal.common.mapper.ZwwdbContractDetailInquiryMapper;
import com.sds.sflex.common.utils.DateUtil;
import com.sds.sflex.common.utils.StringUtil;
import com.sds.sflex.system.config.context.SFLEXContextHolder;
import com.sds.sflex.system.config.core.dvo.UserSessionDvo;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.kyowon.sms.wells.web.withdrawal.pchssl.converter.WwdcSalesControlConvert;
import com.kyowon.sms.wells.web.withdrawal.pchssl.dto.WwdcSalesControlDto.RemoveSalesControlReq;
import com.kyowon.sms.wells.web.withdrawal.pchssl.dto.WwdcSalesControlDto.SaveSalesControlReq;
import com.kyowon.sms.wells.web.withdrawal.pchssl.dto.WwdcSalesControlDto.SearchSalesControlReq;
import com.kyowon.sms.wells.web.withdrawal.pchssl.dto.WwdcSalesControlDto.SearchSalesControlRes;
import com.kyowon.sms.wells.web.withdrawal.pchssl.dvo.WwdcSalesControlDvo;
import com.kyowon.sms.wells.web.withdrawal.pchssl.mapper.WwdcSalesControlMapper;
import com.sds.sflex.common.common.dto.ExcelUploadDto;
import com.sds.sflex.common.common.dvo.ExcelMetaDvo;
import com.sds.sflex.common.common.dvo.ExcelUploadErrorDvo;
import com.sds.sflex.common.common.service.ExcelReadService;
import com.sds.sflex.system.config.constant.CommConst;
import com.sds.sflex.system.config.core.service.MessageResourceService;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import com.sds.sflex.system.config.exception.BizException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * <pre>
 * 매출 조정 관리 서비스
 * </pre>
 *
 * @author kimoon.lim
 * @since 2023-05-25
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class WwdcSalesControlService {

    private final WwdcSalesControlMapper mapper;

    private final WwdcSalesControlConvert convert;

    private final MessageResourceService messageService;
    private final ExcelReadService excelReadService;
    private final ZdcbSalesDiscountCancelService zdcbSalesDiscountCancelService;

    private final ZwwdbContractDetailInquiryMapper zwwdbContractDetailInquiryMapper;

    /**
     * 매출조정관리
     *
     * @param req
     * @return
     */
    public PagingResult<SearchSalesControlRes> getSalesControl(
        SearchSalesControlReq req, PageInfo pageInfo
    ) {
        return mapper.selectSalesControl(req, pageInfo);
    }

    /**
     * 매출조정 엑셀다운로드
     *
     * @param req
     * @return
     */
    public List<SearchSalesControlRes> getSalesControlForExcelDownload(
        SearchSalesControlReq req
    ) {
        return mapper.selectSalesControl(req);
    }

    /**
     * 매출조정 데이터 수정
     *
     * @param req
     * @return
     */
    @Transactional
    public int saveSalesControl(
        List<SaveSalesControlReq> req
    ) throws Exception {
        int processCount = 0;

        ZdcbSalesDiscountCancelDvo zdcbSalesDiscountCancelDvo;
        String sysDateYmd = DateUtil.getNowDayString();
        UserSessionDvo session = SFLEXContextHolder.getContext().getUserSession();

        for (SaveSalesControlReq dto : req) {
            WwdcSalesControlDvo dvo = convert.mapSaveWwdcSalesControlDvo(dto);
            switch (dto.rowState()) {
                case CommConst.ROW_STATE_CREATED -> {
                    processCount += mapper.insertSalesControl(dvo); // 매출조정T 삽입
                    processCount += mapper.insertSalesControlHistory(dvo); // 매출조정이력T 삽입

                    zdcbSalesDiscountCancelDvo = new ZdcbSalesDiscountCancelDvo();
                    zdcbSalesDiscountCancelDvo.setCntrNo(dvo.getCntrNo()); //계약번호
                    zdcbSalesDiscountCancelDvo.setCntrSn(Integer.parseInt(dvo.getCntrSn())); //계약일련번호
                    zdcbSalesDiscountCancelDvo.setSlRcogDt(sysDateYmd); //매출인식일자
                    zdcbSalesDiscountCancelDvo.setKwGrpCoCd(session.getCompanyCode()); //교원코드
                    zdcbSalesDiscountCancelDvo.setSlRcogDvCd("04"); //매출인식구분코드

                    if ("2".equals(dvo.getSlCtrMtrDvCd())) {
                        zdcbSalesDiscountCancelDvo.setBorCtrYn("A");
                    }else if("3".equals(dvo.getSlCtrMtrDvCd())){
                        zdcbSalesDiscountCancelDvo.setBorCtrYn("Y");
                    }

                    if (StringUtil.isNotEmpty(dvo.getSlCtrAmt())) {
                       zdcbSalesDiscountCancelDvo.setSlCtrAmt(Long.parseLong(dvo.getSlCtrAmt())); //조정금액
                   }

                   zdcbSalesDiscountCancelService.createSalesDiscountCancelData(zdcbSalesDiscountCancelDvo);
                }
                case CommConst.ROW_STATE_UPDATED -> {
                    processCount += mapper.updateSalesControl(dvo); // 매출조정T 수정
                    processCount += mapper.insertSalesControlHistory(dvo); // 매출조정이력T 삽입
                }
                default -> throw new BizException("MSG_ALT_UNHANDLE_ROWSTATE");
            }
        }
        return processCount;
    }

    /**
     * 매출조정 데이터 삭제
     *
     * @param req
     * @return
     */
    @Transactional
    public int removeSalesControl(
        List<RemoveSalesControlReq> req
    ) throws Exception {
        int processCount = 0;

        for (RemoveSalesControlReq dto : req) {
            WwdcSalesControlDvo dvo = convert.mapRemoveWwdcSalesControlDvo(dto);
            processCount += mapper.deleteSalesControl(dvo);
        }
        return processCount;
    }

//    // 데이터 확인시, 엑셀 속성값에 Validation Check
//    public void validateExcelAttribute(
//        String column, String columnName, ExcelUploadErrorDvo errorDvo, Map<String, String> headerTitle, int length
//    ) {
//        //        String pattern = "^[0-9]*$"; //숫자 정규식
//        if (StringUtils.isEmpty(column)) { // 유효성 : 시작년월
//            errorDvo.setErrorRow(2);
//            errorDvo.setHeaderName(headerTitle.get(columnName));
//            errorDvo.setErrorData(messageService.getMessage("MSG_ALT_EMPTY_REQUIRED_VAL")); //필수값이 누락되어 있습니다.
//        }
//        //        else {
//        //            if (!Pattern.matches(pattern, column)) {
//        //                errorDvo.setErrorRow(2);
//        //                errorDvo.setHeaderName(headerTitle.get(columnName));
//        //                errorDvo.setErrorData(messageService.getMessage("MSG_ALT_ONLY_NUMBER")); // 숫자만 입력 가능합니다
//        //            } else {
//        //                if (column.length() != length) {
//        //                    errorDvo.setErrorRow(2);
//        //                    errorDvo.setHeaderName(headerTitle.get(columnName));
//        //                    errorDvo.setErrorData(messageService.getMessage("MSG_ALT_IS_NOT_VAL")); // 올바르지 않습니다.
//        //                }
//        //            }
//        //        }
//    }

    /**
     * 매출 조정 엑셀 업로드 유효성 검사
     * @param lists
     * @param headerTitle
     * @return List<ExcelUploadErrorDvo>
     */
  public List<ExcelUploadErrorDvo> validateExcelAttribute(List<WwdcSalesControlDvo> lists, Map<String, String> headerTitle) {

      int row = 2;

      String pattern = "^[0-9]*$";

      List<ExcelUploadErrorDvo> errorDvos = new ArrayList<ExcelUploadErrorDvo>();

      for (WwdcSalesControlDvo dvo :lists) {
          ExcelUploadErrorDvo errorDvo;

          //시작년월 비어있을경우
          if(StringUtil.isEmpty(dvo.getSlCtrStrtYm())){
              errorDvo = new ExcelUploadErrorDvo();
              errorDvo.setErrorRow(row);
              errorDvo.setHeaderName(headerTitle.get("slCtrStrtYm"));
              errorDvo.setErrorData(messageService.getMessage("MSG_ALT_EMPTY_REQUIRED_VAL")); //필수값이 누락되어 있습니다.
              errorDvos.add(errorDvo);
          }else{
              //시작년월
              String slCtrStrtYm = dvo.getSlCtrStrtYm().replace("-", ""); //시작년월

              if(slCtrStrtYm.matches(pattern)){
                  //날짜 길이가 4자리가 아닌 경우 오류 체크
                  if(slCtrStrtYm.length() != 6){
                      errorDvo = new ExcelUploadErrorDvo();
                      errorDvo.setErrorRow(row);
                      errorDvo.setHeaderName(headerTitle.get("slCtrStrtYm"));
                      errorDvo.setErrorData(messageService.getMessage("MSG_TXT_NOT_APY_Y_MTP")); // 적용년도의 형식이 아닙니다.
                      errorDvos.add(errorDvo);
                  }
              }else{ //숫자 형식이 아닐 경우 오류 체크
                errorDvo = new ExcelUploadErrorDvo();
                errorDvo.setErrorRow(row);
                errorDvo.setHeaderName(headerTitle.get("slCtrStrtYm"));
                errorDvo.setErrorData(messageService.getMessage("MSG_TXT_NOT_DATE_FORMAT")); // 올바른 날짜 형식이 아닙니다.
                errorDvos.add(errorDvo);
              }
          }

          //종료년월 비어있을경우
          if(StringUtil.isEmpty(dvo.getSlCtrEndYm())){
              errorDvo = new ExcelUploadErrorDvo();
              errorDvo.setErrorRow(row);
              errorDvo.setHeaderName(headerTitle.get("slCtrEndYm"));
              errorDvo.setErrorData(messageService.getMessage("MSG_ALT_EMPTY_REQUIRED_VAL")); //필수값이 누락되어 있습니다.
              errorDvos.add(errorDvo);
          }else{
              //종료년월
             String slCtrEndYm = dvo.getSlCtrEndYm().replace("-", ""); //종료년월

              if(slCtrEndYm.matches(pattern)){
                   //날짜 길이가 2자리가 아닌 경우 오류 체크
                   if(slCtrEndYm.length() != 6){
                       errorDvo = new ExcelUploadErrorDvo();
                       errorDvo.setErrorRow(row);
                       errorDvo.setHeaderName(headerTitle.get("slCtrEndYm"));
                       errorDvo.setErrorData(messageService.getMessage("MSG_TXT_NOT_CTR_MM_MTP")); // 조정월의 형식이 아닙니다.
                       errorDvos.add(errorDvo);
                   }
               }else{ //숫자 형식이 아닐 경우 오류 체크
                 errorDvo = new ExcelUploadErrorDvo();
                 errorDvo.setErrorRow(row);
                 errorDvo.setHeaderName(headerTitle.get("slCtrEndYm"));
                 errorDvo.setErrorData(messageService.getMessage("MSG_TXT_NOT_DATE_FORMAT")); // 올바른 날짜 형식이 아닙니다.
                 errorDvos.add(errorDvo);
               }
          }

          //계약상세번호가 비어있을경우
          if(StringUtil.isEmpty(dvo.getCntrDtlNo())){
              errorDvo = new ExcelUploadErrorDvo();
              errorDvo.setErrorRow(row);
              errorDvo.setHeaderName(headerTitle.get("cntrDtlNo"));
              errorDvo.setErrorData(messageService.getMessage("MSG_ALT_EMPTY_REQUIRED_VAL")); //필수값이 누락되어 있습니다.
              errorDvos.add(errorDvo);
          }else{
              //계약상세번호
                String cntrDtlNo = dvo.getCntrDtlNo().replace("-", "");//계약상세번호
                String cntrNo = cntrDtlNo.substring(0, 12);
                String cntrSn = cntrDtlNo.substring(12);
                dvo.setCntrNo(cntrNo);
                dvo.setCntrSn(cntrSn);

              //계약상세번호가 13보다 작거나 17자리보다 클 경우 오류 체크
              if(cntrDtlNo.length() < 13 && cntrDtlNo.length() < 17){
                  errorDvo = new ExcelUploadErrorDvo();
                  errorDvo.setErrorRow(row);
                  errorDvo.setHeaderName(headerTitle.get("cntrDtlNo"));
                  errorDvo.setErrorData(messageService.getMessage("MSG_ALT_CNTR_DTL_NO_CONF")); // 올바른 계약상세번호로 조회 하세요.
                  errorDvos.add(errorDvo);
              }else{
                  ZwwdbContractDetailInquiryDto.SearchReq cntrDto = new ZwwdbContractDetailInquiryDto.SearchReq(null, null,cntrNo , cntrSn ,null , "2000");

                  //계약조회
                  ZwwdbContractDetailInquiryDto.SearchRes cntrRes = zwwdbContractDetailInquiryMapper.selectContractDetailInquiry(cntrDto).get(0);

                  dvo.setSlCtrSellTpCd(cntrRes.sellTpCd());

                  if(Objects.isNull(cntrRes)){
                    errorDvo = new ExcelUploadErrorDvo();
                    errorDvo.setErrorRow(row);
                    errorDvo.setHeaderName(headerTitle.get("cntrDtlNo"));
                    errorDvo.setErrorData(messageService.getMessage("MSG_TXT_NEX_CNTR_NO")); //존재하지 않는 계약번호입니다.
                    errorDvos.add(errorDvo);
                  }else{
                      if(mapper.selectSalesControlCount(dvo) > 0){
                          errorDvo = new ExcelUploadErrorDvo();
                          errorDvo.setErrorRow(row);
                          errorDvo.setHeaderName(headerTitle.get("cntrDtlNo"));
                          errorDvo.setErrorData(messageService.getMessage("MSG_TXT_SMD_INF_EXST")); //동일한 정보가 존재합니다.
                          errorDvos.add(errorDvo);
                      }
                  }
              }
          }

          //자료구분이 비어있을경우
          if(StringUtil.isEmpty(dvo.getSlCtrMtrDvCd())){
              errorDvo = new ExcelUploadErrorDvo();
              errorDvo.setErrorRow(row);
              errorDvo.setHeaderName(headerTitle.get("slCtrMtrDvCd"));
              errorDvo.setErrorData(messageService.getMessage("MSG_ALT_EMPTY_REQUIRED_VAL")); //필수값이 누락되어 있습니다.
              errorDvos.add(errorDvo);
          }else{
              if(dvo.getSlCtrMtrDvCd().matches(pattern)){
                  if(dvo.getSlCtrMtrDvCd().length() != 1){
                      errorDvo = new ExcelUploadErrorDvo();
                      errorDvo.setErrorRow(row);
                      errorDvo.setHeaderName(headerTitle.get("slCtrMtrDvCd"));
                      errorDvo.setErrorData(messageService.getMessage("자료구분의 경우 한자리만 입력 가능합니다.")); // 자료구분의 경우 한자리만 입력 가능합니다.
                      errorDvos.add(errorDvo);
                  }
              }else{
                  errorDvo = new ExcelUploadErrorDvo();
                  errorDvo.setErrorRow(row);
                  errorDvo.setHeaderName(headerTitle.get("slCtrMtrDvCd"));
                  errorDvo.setErrorData(messageService.getMessage("자료구분은 숫자로 입력해주세요.")); // 자료구분은 숫자로 입력해주세요.
                  errorDvos.add(errorDvo);
              }
          }

          //조정구분이 비어있을경우
          if(StringUtil.isEmpty(dvo.getSlCtrDvCd())){
              errorDvo = new ExcelUploadErrorDvo();
              errorDvo.setErrorRow(row);
              errorDvo.setHeaderName(headerTitle.get("slCtrDvCd"));
              errorDvo.setErrorData(messageService.getMessage("MSG_ALT_EMPTY_REQUIRED_VAL")); //필수값이 누락되어 있습니다.
              errorDvos.add(errorDvo);
          }else{
              if(dvo.getSlCtrDvCd().matches(pattern)){
                  if(dvo.getSlCtrDvCd().length() != 1){
                      errorDvo = new ExcelUploadErrorDvo();
                      errorDvo.setErrorRow(row);
                      errorDvo.setHeaderName(headerTitle.get("slCtrDvCd"));
                      errorDvo.setErrorData(messageService.getMessage("조정구분의 경우 한자리만 입력 가능합니다.")); // 조정구분의 경우 한자리만 입력 가능합니다.
                      errorDvos.add(errorDvo);
                  }
              }else{
                  errorDvo = new ExcelUploadErrorDvo();
                  errorDvo.setErrorRow(row);
                  errorDvo.setHeaderName(headerTitle.get("slCtrDvCd"));
                  errorDvo.setErrorData(messageService.getMessage("조정구분은 숫자로 입력해주세요.")); // 조정구분은 숫자로 입력해주세요.
                  errorDvos.add(errorDvo);
              }
          }

          //자료유형이 비어있을경우
          if(StringUtil.isEmpty(dvo.getSlCtrMtrTpCd())){
              errorDvo = new ExcelUploadErrorDvo();
              errorDvo.setErrorRow(row);
              errorDvo.setHeaderName(headerTitle.get("slCtrMtrTpCd"));
              errorDvo.setErrorData(messageService.getMessage("MSG_ALT_EMPTY_REQUIRED_VAL")); //필수값이 누락되어 있습니다.
              errorDvos.add(errorDvo);
          }else{
              if(dvo.getSlCtrMtrTpCd().matches(pattern)){
                  if(dvo.getSlCtrMtrTpCd().length() != 1){
                      errorDvo = new ExcelUploadErrorDvo();
                      errorDvo.setErrorRow(row);
                      errorDvo.setHeaderName(headerTitle.get("slCtrMtrTpCd"));
                      errorDvo.setErrorData(messageService.getMessage("자료유형의 경우 한자리만 입력 가능합니다.")); // 조정구분의 경우 한자리만 입력 가능합니다.
                      errorDvos.add(errorDvo);
                  }
              }else{
                  errorDvo = new ExcelUploadErrorDvo();
                  errorDvo.setErrorRow(row);
                  errorDvo.setHeaderName(headerTitle.get("slCtrMtrTpCd"));
                  errorDvo.setErrorData(messageService.getMessage("자료유형은 숫자로 입력해주세요.")); // 조정구분은 숫자로 입력해주세요.
                  errorDvos.add(errorDvo);
              }
          }

          //조정유형 비어있을경우
          if(StringUtil.isEmpty(dvo.getSlCtrTpCd())){
              errorDvo = new ExcelUploadErrorDvo();
              errorDvo.setErrorRow(row);
              errorDvo.setHeaderName(headerTitle.get("slCtrTpCd"));
              errorDvo.setErrorData(messageService.getMessage("MSG_ALT_EMPTY_REQUIRED_VAL")); //필수값이 누락되어 있습니다.
              errorDvos.add(errorDvo);
          }else{
              if(dvo.getSlCtrTpCd().matches(pattern)){
                  if(dvo.getSlCtrTpCd().length() != 1){
                      errorDvo = new ExcelUploadErrorDvo();
                      errorDvo.setErrorRow(row);
                      errorDvo.setHeaderName(headerTitle.get("slCtrTpCd"));
                      errorDvo.setErrorData(messageService.getMessage("조정유형의 경우 한자리만 입력 가능합니다.")); // 조정구분의 경우 한자리만 입력 가능합니다.
                      errorDvos.add(errorDvo);
                  }
              }else{
                  errorDvo = new ExcelUploadErrorDvo();
                  errorDvo.setErrorRow(row);
                  errorDvo.setHeaderName(headerTitle.get("slCtrTpCd"));
                  errorDvo.setErrorData(messageService.getMessage("조정유형은 숫자로 입력해주세요.")); // 조정구분은 숫자로 입력해주세요.
                  errorDvos.add(errorDvo);
              }
          }
          //할인 비어있을경우
          if(StringUtil.isEmpty(dvo.getSlCtrDscTpCd())){
              errorDvo = new ExcelUploadErrorDvo();
              errorDvo.setErrorRow(row);
              errorDvo.setHeaderName(headerTitle.get("slCtrDscTpCd"));
              errorDvo.setErrorData(messageService.getMessage("MSG_ALT_EMPTY_REQUIRED_VAL")); //필수값이 누락되어 있습니다.
              errorDvos.add(errorDvo);
          }else{
              if(dvo.getSlCtrDscTpCd().matches(pattern)){
                  if(dvo.getSlCtrDscTpCd().length() != 2){
                      errorDvo = new ExcelUploadErrorDvo();
                      errorDvo.setErrorRow(row);
                      errorDvo.setHeaderName(headerTitle.get("slCtrDscTpCd"));
                      errorDvo.setErrorData(messageService.getMessage("할인의 경우 한자리만 입력 가능합니다.")); // 조정구분의 경우 한자리만 입력 가능합니다.
                      errorDvos.add(errorDvo);
                  }
              }else{
                  errorDvo = new ExcelUploadErrorDvo();
                  errorDvo.setErrorRow(row);
                  errorDvo.setHeaderName(headerTitle.get("slCtrDscTpCd"));
                  errorDvo.setErrorData(messageService.getMessage("할인은 숫자로 입력해주세요.")); // 조정구분은 숫자로 입력해주세요.
                  errorDvos.add(errorDvo);
              }
          }

          //조정금액이 비어있을경우
          if(StringUtil.isEmpty(dvo.getSlCtrAmt())){
              errorDvo = new ExcelUploadErrorDvo();
              errorDvo.setErrorRow(row);
              errorDvo.setHeaderName(headerTitle.get("slCtrAmt"));
              errorDvo.setErrorData(messageService.getMessage("MSG_ALT_EMPTY_REQUIRED_VAL")); //필수값이 누락되어 있습니다.
              errorDvos.add(errorDvo);
          }else{
              if(dvo.getSlCtrAmt().matches(pattern)){
                  int amt = Integer.parseInt(dvo.getSlCtrAmt());

                  if(amt < 1000){
                      errorDvo = new ExcelUploadErrorDvo();
                      errorDvo.setErrorRow(row);
                      errorDvo.setHeaderName(headerTitle.get("slCtrAmt"));
                      errorDvo.setErrorData(messageService.getMessage("MSG_TXT_CTR_AMT_MIN_THW_IN")); // 조정금액의 경우 최소 천원 이상부터 입력 가능합니다.
                      errorDvos.add(errorDvo);
                  }

              }else{
                errorDvo = new ExcelUploadErrorDvo();
                errorDvo.setErrorRow(row);
                errorDvo.setHeaderName(headerTitle.get("slCtrAmt"));
                errorDvo.setErrorData(messageService.getMessage("MSG_TXT_CTR_AMT_NUM_IN")); // 조정금액의 경우 숫자로 입력해주세요.
                errorDvos.add(errorDvo);
              }
          }


          //조정사유가 비어있을경우
          if(StringUtil.isEmpty(dvo.getSlCtrRmkCn())){
              errorDvo = new ExcelUploadErrorDvo();
              errorDvo.setErrorRow(row);
              errorDvo.setHeaderName(headerTitle.get("slCtrRmkCn"));
              errorDvo.setErrorData(messageService.getMessage("MSG_ALT_EMPTY_REQUIRED_VAL")); //필수값이 누락되어 있습니다.
              errorDvos.add(errorDvo);
          }else{
              if(dvo.getSlCtrRmkCn().length() < 1 && dvo.getSlCtrRmkCn().length() < 333){
                errorDvo = new ExcelUploadErrorDvo();
                errorDvo.setErrorRow(row);
                errorDvo.setHeaderName(headerTitle.get("slCtrRmkCn"));
                errorDvo.setErrorData(messageService.getMessage("MSG_TXT_CTR_RSON_LNTH_RDCT"));
                // 조정사유의 글이 너무 길어 등록할 수 없습니다. 글자 수를 줄여주세요. (최대 333자리)
                errorDvos.add(errorDvo);
              }
          }

          row ++;
      }
      return errorDvos;


  }

    /**
     * 매출조정 엑셀 업로드
     * @param exmpYn
     * @param file
     * @return ExcelUploadDto.UploadRes
     * @throws Exception
     */
    @Transactional
    public ExcelUploadDto.UploadRes saveSalesControlExcelUpload(String exmpYn, MultipartFile file) throws Exception {
            Map<String, String> headerTitle = new LinkedHashMap<>();

            String sysDateYmd = DateUtil.getNowDayString();
            UserSessionDvo session = SFLEXContextHolder.getContext().getUserSession();


            headerTitle.put("cntrDtlNo", messageService.getMessage("MSG_TXT_CNTR_DTL_NO")); // 계약상세번호
            headerTitle.put("slCtrStrtYm", messageService.getMessage("MSG_TXT_STRT_YM")); // 시작년월
            headerTitle.put("slCtrEndYm", messageService.getMessage("MSG_TXT_END_YM")); // 종료년월
            headerTitle.put("slCtrMtrDvCd", messageService.getMessage("MSG_TXT_MTR_DV")); // 자료구분
            headerTitle.put("slCtrDvCd", messageService.getMessage("MSG_TXT_CTR_DV")); // 조정구분
            headerTitle.put("slCtrMtrTpCd", messageService.getMessage("MSG_TXT_DATA_TP")); // 자료유형
            headerTitle.put("slCtrTpCd", messageService.getMessage("MSG_TXT_CTR_TP")); // 조정유형
            headerTitle.put("slCtrDscTpCd", messageService.getMessage("MSG_TXT_DSC")); // 할인
            headerTitle.put("canAfOjYn", messageService.getMessage("MSG_TXT_CAN_AFT_APY")); // 취소 후 적용
            headerTitle.put("slCtrAmt", messageService.getMessage("MSG_TXT_CTR_AMT")); // 조정금액
            headerTitle.put("slCtrWoExmpAmt", messageService.getMessage("MSG_TXT_FULL_EXMP_AMT")); // 전액면제금액
            headerTitle.put("slCtrPtrmExmpAmt", messageService.getMessage("MSG_TXT_INQR_PTRM_EXMP_AMT")); // 조회 기간 면제 금액
            headerTitle.put("slCtrRmkCn", messageService.getMessage("MSG_TXT_RSN_FR_ADJ")); // 조정사유

            String status = "S";

            ExcelMetaDvo meta = new ExcelMetaDvo(1, headerTitle);

            List<WwdcSalesControlDvo> lists = excelReadService.readExcel(file, meta, WwdcSalesControlDvo.class);

            //엑셀 파일 유효성 체크
            List<ExcelUploadErrorDvo> errorDvos = validateExcelAttribute(lists, headerTitle);
//            List<ExcelUploadErrorDvo> errorDvos = new ArrayList<ExcelUploadErrorDvo>();

            if(errorDvos.isEmpty()){

                ZdcbSalesDiscountCancelDvo zdcbSalesDiscountCancelDvo;

                for (WwdcSalesControlDvo dvo : lists) {
                String cntrNo = dvo.getCntrDtlNo().substring(0, 12);
                String cntrSn = dvo.getCntrDtlNo().substring(12);

                ZwwdbContractDetailInquiryDto.SearchReq cntrDto = new ZwwdbContractDetailInquiryDto.SearchReq(null, null,cntrNo , cntrSn ,null , "2000");

                //계약조회
                ZwwdbContractDetailInquiryDto.SearchRes cntrRes = zwwdbContractDetailInquiryMapper.selectContractDetailInquiry(cntrDto).get(0);

                dvo.setSlCtrSellTpCd(cntrRes.sellTpCd());
                dvo.setCntrNo(cntrNo);
                dvo.setCntrSn(cntrSn);

                mapper.insertSalesControl(dvo); // 매출조정T 삽입
                mapper.insertSalesControlHistory(dvo); // 매출조정이력T 삽입

                zdcbSalesDiscountCancelDvo = new ZdcbSalesDiscountCancelDvo();
                zdcbSalesDiscountCancelDvo.setCntrNo(dvo.getCntrNo()); //계약번호
                zdcbSalesDiscountCancelDvo.setCntrSn(Integer.parseInt(dvo.getCntrSn())); //계약일련번호
                zdcbSalesDiscountCancelDvo.setSlRcogDt(sysDateYmd); //매출인식일자
                zdcbSalesDiscountCancelDvo.setKwGrpCoCd(session.getCompanyCode()); //교원코드
                zdcbSalesDiscountCancelDvo.setSlRcogDvCd("04"); //매출인식구분코드

                if ("2".equals(dvo.getSlCtrMtrDvCd())) {
                    zdcbSalesDiscountCancelDvo.setBorCtrYn("A");
                }else if("3".equals(dvo.getSlCtrMtrDvCd())){
                    zdcbSalesDiscountCancelDvo.setBorCtrYn("Y");
                }

                if (StringUtil.isNotEmpty(dvo.getSlCtrAmt())) {
                   zdcbSalesDiscountCancelDvo.setSlCtrAmt(Long.parseLong(dvo.getSlCtrAmt())); //조정금액
                }

                zdcbSalesDiscountCancelService.createSalesDiscountCancelData(zdcbSalesDiscountCancelDvo);
//
                }
            }else{
                status = "E";
            }
            ExcelUploadDto.UploadRes result = ExcelUploadDto.UploadRes.builder()
                .status(status)
                .excelData(lists)
                .errorInfo(errorDvos)
                .build();
            return result;

        }

}
