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

                    if ("1".equals(dvo.getSlCtrMtrDvCd())) {
                        zdcbSalesDiscountCancelDvo = new ZdcbSalesDiscountCancelDvo();
                        zdcbSalesDiscountCancelDvo.setCntrNo(dvo.getCntrNo()); //계약번호
                        zdcbSalesDiscountCancelDvo.setCntrSn(Integer.parseInt(dvo.getCntrSn())); //계약일련번호
                        zdcbSalesDiscountCancelDvo.setSlRcogDt(sysDateYmd); //매출인식일자
                        zdcbSalesDiscountCancelDvo.setKwGrpCoCd(session.getCompanyCode()); //교원코드
                        zdcbSalesDiscountCancelDvo.setSlRcogDvCd("04"); //매출인식구분코드
                        if (StringUtil.isNotEmpty(dvo.getSlCtrAmt())) {
                            zdcbSalesDiscountCancelDvo.setSlCtrAmt(Long.parseLong(dvo.getSlCtrAmt())); //조정금액
                        }

                        zdcbSalesDiscountCancelService.createSalesDiscountCancelData(zdcbSalesDiscountCancelDvo);
                    }
//                    processCount += mapper.updateSalesConfirm(dvo); // 매출확정테이블 업데이트(조정금액)
                }
                case CommConst.ROW_STATE_UPDATED -> {
                    processCount += mapper.updateSalesControl(dvo); // 매출조정T 수정
                    processCount += mapper.updateSalesControlHistory(dvo); // 매출조정이력T 수정
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
          String cntrDtlNo = dvo.getCntrDtlNo().replace("-", "");

          String cntrNo = cntrDtlNo.substring(0, 12);
          String cntrSn = cntrDtlNo.substring(12);
          dvo.setCntrNo(cntrNo);
          dvo.setCntrSn(cntrSn);

          //조정년도가 비어있을경우
          if(StringUtil.isEmpty(dvo.getControlYear())){
              errorDvo = new ExcelUploadErrorDvo();
              errorDvo.setErrorRow(row);
              errorDvo.setHeaderName(headerTitle.get("controlYear"));
              errorDvo.setErrorData(messageService.getMessage("MSG_ALT_EMPTY_REQUIRED_VAL")); //필수값이 누락되어 있습니다.
              errorDvos.add(errorDvo);
          }else{
              if(dvo.getControlYear().matches(pattern)){
                  //날짜 길이가 4자리가 아닌 경우 오류 체크
                  if(dvo.getControlYear().length() != 4){
                      errorDvo = new ExcelUploadErrorDvo();
                      errorDvo.setErrorRow(row);
                      errorDvo.setHeaderName(headerTitle.get("controlYear"));
                      errorDvo.setErrorData(messageService.getMessage("MSG_TXT_NOT_APY_Y_MTP")); // 적용년도의 형식이 아닙니다.
                      errorDvos.add(errorDvo);
                  }
              }else{ //숫자 형식이 아닐 경우 오류 체크
                errorDvo = new ExcelUploadErrorDvo();
                errorDvo.setErrorRow(row);
                errorDvo.setHeaderName(headerTitle.get("controlYear"));
                errorDvo.setErrorData(messageService.getMessage("MSG_TXT_NOT_DATE_FORMAT")); // 올바른 날짜 형식이 아닙니다.
                errorDvos.add(errorDvo);
              }
          }
          //조정월이 비어있을경우
          if(StringUtil.isEmpty(dvo.getControlMonth())){
              errorDvo = new ExcelUploadErrorDvo();
              errorDvo.setErrorRow(row);
              errorDvo.setHeaderName(headerTitle.get("controlMonth"));
              errorDvo.setErrorData(messageService.getMessage("MSG_ALT_EMPTY_REQUIRED_VAL")); //필수값이 누락되어 있습니다.
              errorDvos.add(errorDvo);
          }else{
              if(dvo.getControlMonth().matches(pattern)){
                   //날짜 길이가 2자리가 아닌 경우 오류 체크
                   if(dvo.getControlMonth().length() != 2){
                       errorDvo = new ExcelUploadErrorDvo();
                       errorDvo.setErrorRow(row);
                       errorDvo.setHeaderName(headerTitle.get("controlYear"));
                       errorDvo.setErrorData(messageService.getMessage("MSG_TXT_NOT_CTR_MM_MTP")); // 조정월의 형식이 아닙니다.
                       errorDvos.add(errorDvo);
                   }
               }else{ //숫자 형식이 아닐 경우 오류 체크
                 errorDvo = new ExcelUploadErrorDvo();
                 errorDvo.setErrorRow(row);
                 errorDvo.setHeaderName(headerTitle.get("controlYear"));
                 errorDvo.setErrorData(messageService.getMessage("MSG_TXT_NOT_DATE_FORMAT")); // 올바른 날짜 형식이 아닙니다.
                 errorDvos.add(errorDvo);
               }

          }

          //계약상세번호가 비어있을경우
          if(StringUtil.isEmpty(cntrDtlNo)){
              errorDvo = new ExcelUploadErrorDvo();
              errorDvo.setErrorRow(row);
              errorDvo.setHeaderName(headerTitle.get("cntrDtlNo"));
              errorDvo.setErrorData(messageService.getMessage("MSG_ALT_EMPTY_REQUIRED_VAL")); //필수값이 누락되어 있습니다.
              errorDvos.add(errorDvo);
          }else{
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
          //주문유형이 비어있을경우
          if(StringUtil.isEmpty(dvo.getOrderType())){
              errorDvo = new ExcelUploadErrorDvo();
              errorDvo.setErrorRow(row);
              errorDvo.setHeaderName(headerTitle.get("orderType"));
              errorDvo.setErrorData(messageService.getMessage("MSG_ALT_EMPTY_REQUIRED_VAL")); //필수값이 누락되어 있습니다.
              errorDvos.add(errorDvo);
          }else{
              if(dvo.getOrderType().matches(pattern)){
                  if(dvo.getOrderType().length() != 1){
                      errorDvo = new ExcelUploadErrorDvo();
                      errorDvo.setErrorRow(row);
                      errorDvo.setHeaderName(headerTitle.get("orderType"));
                      errorDvo.setErrorData(messageService.getMessage("MSG_TXT_ORD_TP_IN_ONE")); // 주문유형의 경우 한자리만 입력 가능합니다.
                      errorDvos.add(errorDvo);
                  }
              }else{
                  errorDvo = new ExcelUploadErrorDvo();
                  errorDvo.setErrorRow(row);
                  errorDvo.setHeaderName(headerTitle.get("orderType"));
                  errorDvo.setErrorData(messageService.getMessage("MSG_TXT_ORD_TP_NUM_IN")); // 주문유형은 숫자로 입력해주세요.
                  errorDvos.add(errorDvo);
              }
          }

          //조정금액이 비어있을경우
          if(StringUtil.isEmpty(dvo.getControlAmount())){
              errorDvo = new ExcelUploadErrorDvo();
              errorDvo.setErrorRow(row);
              errorDvo.setHeaderName(headerTitle.get("controlAmount"));
              errorDvo.setErrorData(messageService.getMessage("MSG_ALT_EMPTY_REQUIRED_VAL")); //필수값이 누락되어 있습니다.
              errorDvos.add(errorDvo);
          }else{
              if(dvo.getControlAmount().matches(pattern)){
                  int amt = Integer.parseInt(dvo.getControlAmount());

                  if(amt < 1000){
                      errorDvo = new ExcelUploadErrorDvo();
                      errorDvo.setErrorRow(row);
                      errorDvo.setHeaderName(headerTitle.get("controlAmount"));
                      errorDvo.setErrorData(messageService.getMessage("MSG_TXT_CTR_AMT_MIN_THW_IN")); // 조정금액의 경우 최소 천원 이상부터 입력 가능합니다.
                      errorDvos.add(errorDvo);
                  }

              }else{
                errorDvo = new ExcelUploadErrorDvo();
                errorDvo.setErrorRow(row);
                errorDvo.setHeaderName(headerTitle.get("controlAmount"));
                errorDvo.setErrorData(messageService.getMessage("MSG_TXT_CTR_AMT_NUM_IN")); // 조정금액의 경우 숫자로 입력해주세요.
                errorDvos.add(errorDvo);
              }
          }


          //조정구분이 비어있을경우
          if(StringUtil.isEmpty(dvo.getControlDivide())){
              errorDvo = new ExcelUploadErrorDvo();
              errorDvo.setErrorRow(row);
              errorDvo.setHeaderName(headerTitle.get("controlDivide"));
              errorDvo.setErrorData(messageService.getMessage("MSG_ALT_EMPTY_REQUIRED_VAL")); //필수값이 누락되어 있습니다.
              errorDvos.add(errorDvo);
          }else{
              if(dvo.getControlDivide().matches(pattern)){
                    if(dvo.getControlDivide().length() != 1){
                        errorDvo = new ExcelUploadErrorDvo();
                        errorDvo.setErrorRow(row);
                        errorDvo.setHeaderName(headerTitle.get("controlDivide"));
                        errorDvo.setErrorData(messageService.getMessage("MSG_TXT_CTR_DV_IN_ONE")); // 조정구분의 경우 한자리만 입력 가능합니다.
                        errorDvos.add(errorDvo);
                    }
                }else{
                    errorDvo = new ExcelUploadErrorDvo();
                    errorDvo.setErrorRow(row);
                    errorDvo.setHeaderName(headerTitle.get("controlDivide"));
                    errorDvo.setErrorData(messageService.getMessage("MSG_TXT_CTR_DV_NUM_IN")); // 조정구분은 숫자로 입력해주세요.
                    errorDvos.add(errorDvo);
                }
          }

          //조정유형이 비어있을경우
          if(StringUtil.isEmpty(dvo.getControlType())){
              errorDvo = new ExcelUploadErrorDvo();
              errorDvo.setErrorRow(row);
              errorDvo.setHeaderName(headerTitle.get("controlType"));
              errorDvo.setErrorData(messageService.getMessage("MSG_ALT_EMPTY_REQUIRED_VAL")); //필수값이 누락되어 있습니다.
              errorDvos.add(errorDvo);
          }else {
            if(dvo.getControlType().matches(pattern)){
                  if(dvo.getControlType().length() != 2){
                      errorDvo = new ExcelUploadErrorDvo();
                      errorDvo.setErrorRow(row);
                      errorDvo.setHeaderName(headerTitle.get("controlType"));
                      errorDvo.setErrorData(messageService.getMessage("MSG_TXT_CTR_TP_DIGI2_IN")); // 조정유형의 경우 두 자리만 입력 가능합니다.
                      errorDvos.add(errorDvo);
                  }
              }else{
                  errorDvo = new ExcelUploadErrorDvo();
                  errorDvo.setErrorRow(row);
                  errorDvo.setHeaderName(headerTitle.get("controlType"));
                  errorDvo.setErrorData(messageService.getMessage("MSG_TXT_CTR_TP_NUM_IN")); // 조정유형은 숫자로 입력해주세요.
                  errorDvos.add(errorDvo);
              }
          }

          //조정사유가 비어있을경우
          if(StringUtil.isEmpty(dvo.getControlReason())){
              errorDvo = new ExcelUploadErrorDvo();
              errorDvo.setErrorRow(row);
              errorDvo.setHeaderName(headerTitle.get("controlReason"));
              errorDvo.setErrorData(messageService.getMessage("MSG_ALT_EMPTY_REQUIRED_VAL")); //필수값이 누락되어 있습니다.
              errorDvos.add(errorDvo);
          }else{
              if(dvo.getControlReason().length() < 1 && dvo.getControlReason().length() < 333){
                errorDvo = new ExcelUploadErrorDvo();
                errorDvo.setErrorRow(row);
                errorDvo.setHeaderName(headerTitle.get("controlReason"));
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
            headerTitle.put("controlYear", messageService.getMessage("MSG_TXT_CTR_Y")); // 조정년도
            headerTitle.put("controlMonth", messageService.getMessage("MSG_TXT_CTR_MM")); // 조정월
            headerTitle.put("cntrDtlNo", messageService.getMessage("MSG_TXT_CNTR_DTL_NO")); // 계약상세번호
            headerTitle.put("orderType", messageService.getMessage("MSG_TXT_ORD_TYP")); // 주문유형
            headerTitle.put("controlAmount", messageService.getMessage("MSG_TXT_CTR_AMT")); // 조정금액
            headerTitle.put("controlDivide", messageService.getMessage("MSG_TXT_CTR_DV")); // 조정구분
            headerTitle.put("controlType", messageService.getMessage("MSG_TXT_CTR_TP")); // 조정유형
            headerTitle.put("controlReason", messageService.getMessage("MSG_TXT_CTR_RSON")); // 조정사유

            String status = "S";

            ExcelMetaDvo meta = new ExcelMetaDvo(1, headerTitle);

            List<WwdcSalesControlDvo> lists = excelReadService.readExcel(file, meta, WwdcSalesControlDvo.class);

            //엑셀 파일 유효성 체크
            List<ExcelUploadErrorDvo> errorDvos = validateExcelAttribute(lists, headerTitle);

            if(errorDvos.isEmpty()){
                for (WwdcSalesControlDvo dvo : lists) {

                  String cntrNo = dvo.getCntrDtlNo().substring(0, 12);
                  String cntrSn = dvo.getCntrDtlNo().substring(12);
                  dvo.setCntrNo(cntrNo);
                  dvo.setCntrSn(cntrSn);
                  mapper.insertExcelUploadSalesControl(dvo);
                  mapper.insertExcelUploadSalesControlHistory(dvo);

                  String sysDateYmd = DateUtil.getNowDayString();
                  ZdcbSalesDiscountCancelDvo zdcbSalesDiscountCancelDvo = new ZdcbSalesDiscountCancelDvo();
                  zdcbSalesDiscountCancelDvo.setCntrNo(dvo.getCntrNo()); //계약번호
                  zdcbSalesDiscountCancelDvo.setCntrSn(Integer.parseInt(dvo.getCntrSn())); //계약일련번호
                  zdcbSalesDiscountCancelDvo.setSlRcogDt(sysDateYmd); //매출인식일자
                  zdcbSalesDiscountCancelDvo.setKwGrpCoCd("2000"); //교원코드
                  zdcbSalesDiscountCancelDvo.setSlRcogDvCd("04"); //매출인식구분코드
                  zdcbSalesDiscountCancelDvo.setSlCtrAmt(Long.parseLong(dvo.getControlAmount())); //조정금액
                 zdcbSalesDiscountCancelService.createSalesDiscountCancelData(zdcbSalesDiscountCancelDvo);

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
