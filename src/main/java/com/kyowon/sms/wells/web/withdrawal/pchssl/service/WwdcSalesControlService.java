package com.kyowon.sms.wells.web.withdrawal.pchssl.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

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

@Slf4j
@Service
@RequiredArgsConstructor
public class WwdcSalesControlService {

    private final WwdcSalesControlMapper mapper;

    private final WwdcSalesControlConvert convert;

    private final MessageResourceService messageService;
    private final ExcelReadService excelReadService;

    /**
     * 매출조정관리
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
     * @param req
     * @return
     */
    @Transactional
    public int saveSalesControl(
        List<SaveSalesControlReq> req
    ) throws Exception {
        int processCount = 0;

        for (SaveSalesControlReq dto : req) {
            WwdcSalesControlDvo dvo = convert.mapSaveWwdcSalesControlDvo(dto);

            switch (dto.rowState()) {
                case CommConst.ROW_STATE_CREATED -> {
                    //                    dvo.setCntrNo(dto.cntrDtlNo().substring(0, dto.cntrDtlNo().length() - 1)); // 계약번호
                    //                    dvo.setCntrSn(dto.cntrDtlNo().substring(dto.cntrDtlNo().length() - 1, dto.cntrDtlNo().length())); // 계약일련번호
                    processCount += mapper.insertSalesControl(dvo); // 매출조정T 삽입
                    processCount += mapper.insertSalesControlHistory(dvo); // 매출조정이력T 삽입
                    processCount += mapper.updateSalesConfirm(dvo); // 매출확정테이블 업데이트(조정금액)
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

    // 데이터 확인시, 엑셀 속성값에 Validation Check
    public void validateExcelAttribute(
        String column, String columnName, ExcelUploadErrorDvo errorDvo, Map<String, String> headerTitle, int length
    ) {
        //        String pattern = "^[0-9]*$"; //숫자 정규식
        if (StringUtils.isEmpty(column)) { // 유효성 : 시작년월
            errorDvo.setErrorRow(2);
            errorDvo.setHeaderName(headerTitle.get(columnName));
            errorDvo.setErrorData(messageService.getMessage("MSG_ALT_EMPTY_REQUIRED_VAL")); //필수값이 누락되어 있습니다.
        }
        //        else {
        //            if (!Pattern.matches(pattern, column)) {
        //                errorDvo.setErrorRow(2);
        //                errorDvo.setHeaderName(headerTitle.get(columnName));
        //                errorDvo.setErrorData(messageService.getMessage("MSG_ALT_ONLY_NUMBER")); // 숫자만 입력 가능합니다
        //            } else {
        //                if (column.length() != length) {
        //                    errorDvo.setErrorRow(2);
        //                    errorDvo.setHeaderName(headerTitle.get(columnName));
        //                    errorDvo.setErrorData(messageService.getMessage("MSG_ALT_IS_NOT_VAL")); // 올바르지 않습니다.
        //                }
        //            }
        //        }
    }

    @Transactional
    public ExcelUploadDto.UploadRes saveSalesControlExcelUpload(MultipartFile file) throws Exception {
        Map<String, String> headerTitle = new LinkedHashMap<>();
        headerTitle.put("cntrNo", messageService.getMessage("MSG_TXT_CNTR_NO"));
        headerTitle.put("cntrSn", messageService.getMessage("MSG_TXT_CNTR_SN"));
        //        headerTitle.put("cntrDtlNo", messageService.getMessage("MSG_TXT_CNTR_SN"));
        headerTitle.put("cstKnm", messageService.getMessage("MSG_TXT_CST_NM"));
        headerTitle.put("slCtrStrtYm", messageService.getMessage("MSG_TXT_STRT_YM"));
        headerTitle.put("slCtrEndYm", messageService.getMessage("MSG_TXT_END_YM"));
        headerTitle.put("slCtrMtrDvCd", messageService.getMessage("MSG_TXT_MTR_DV"));
        headerTitle.put("slCtrSellTpCd", messageService.getMessage("MSG_TXT_SELL_TP_CD"));
        headerTitle.put("pdCd", messageService.getMessage("MSG_TXT_GOODS_NM"));
        headerTitle.put("pdNm", messageService.getMessage("MSG_TXT_PROD_CD"));
        headerTitle.put("slCtrDvCd", messageService.getMessage("MSG_TXT_CTR_DV"));
        headerTitle.put("slCtrMtrTpCd", messageService.getMessage("MSG_TXT_CTR_TP"));
        headerTitle.put("slCtrDscTpCd", messageService.getMessage("MSG_TXT_DSC"));
        headerTitle.put("canAfOjYn", messageService.getMessage("MSG_TXT_PD_AF_CAN"));
        headerTitle.put("slCtrAmt", messageService.getMessage("MSG_TXT_CTR_AMT"));
        headerTitle.put("slCtrWoExmpAmt", messageService.getMessage("MSG_TXT_CTR_EXMP_AMT"));
        headerTitle.put("slCtrPtrmExmpAmt", messageService.getMessage("MSG_TXT_INQR_PTRM_EXMP_AMT"));
        headerTitle.put("slCtrRmkCn", messageService.getMessage("MSG_TXT_RSN_FR_ADJ"));

        String specialPattern = "^[0-9a-zA-Zㄱ-ㅎ가-힣]*$"; //특수문자 정규식
        String pattern = "^[0-9]*$"; //숫자 정규식
        String status = "S";
        int row = 2;
        List<Map<String, Object>> checks = new ArrayList<>();
        ExcelMetaDvo meta = new ExcelMetaDvo(1, headerTitle);
        List<WwdcSalesControlDvo> lists = excelReadService.readExcel(file, meta, WwdcSalesControlDvo.class);
        List<ExcelUploadErrorDvo> errorDvos = new ArrayList<>();

        for (WwdcSalesControlDvo list : lists) {
            ExcelUploadErrorDvo errorDvo = new ExcelUploadErrorDvo();
            Map<String, Object> check = new HashMap<>();
            int finalRow = row;
            int cntrExistCnt = 0;
            //            int partnerCnt = 0;

            if (StringUtils.isEmpty(list.getCntrNo())) { // 유효성
                errorDvo.setErrorRow(finalRow);
                errorDvo.setHeaderName(headerTitle.get("cntrNo"));
                errorDvo.setErrorData(messageService.getMessage("MSG_ALT_EMPTY_REQUIRED_VAL")); //필수값이 누락되어 있습니다.
            } else {
                if (!Pattern.matches(specialPattern, list.getCntrNo())) {
                    errorDvo.setErrorRow(finalRow);
                    errorDvo.setHeaderName(headerTitle.get("cntrNo"));
                    errorDvo.setErrorData(messageService.getMessage("MSG_ALT_CHK_SPECL_CHAR")); // 특수 문자는 허용되지 않습니다.
                } else {
                    if (StringUtils.isEmpty(list.getCntrSn())) { // 유효성
                        errorDvo.setErrorRow(finalRow);
                        errorDvo.setHeaderName(headerTitle.get("cntrSn"));
                        errorDvo.setErrorData(messageService.getMessage("MSG_ALT_EMPTY_REQUIRED_VAL")); //필수값이 누락되어 있습니다.
                    } else {
                        if (!Pattern.matches(pattern, list.getCntrSn())) {
                            errorDvo.setErrorRow(finalRow);
                            errorDvo.setHeaderName(headerTitle.get("cntrSn"));
                            errorDvo.setErrorData(messageService.getMessage("MSG_ALT_ONLY_NUMBER")); // 숫자만 입력 가능합니다
                        }
                    }
                }
            }

            //            // 시작년월 
            //            if (StringUtils.isEmpty(list.getSlCtrStrtYm())) { // 유효성 : 시작년월
            //                errorDvo.setErrorRow(finalRow);
            //                errorDvo.setHeaderName(headerTitle.get("slCtrStrtYm"));
            //                errorDvo.setErrorData(messageService.getMessage("MSG_ALT_EMPTY_REQUIRED_VAL")); //필수값이 누락되어 있습니다.
            //            } else {
            //                if (!Pattern.matches(pattern, list.getSlCtrStrtYm())) {
            //                    errorDvo.setErrorRow(finalRow);
            //                    errorDvo.setHeaderName(headerTitle.get("slCtrStrtYm"));
            //                    errorDvo.setErrorData(messageService.getMessage("MSG_ALT_ONLY_NUMBER")); // 숫자만 입력 가능합니다
            //                } else {
            //                    if (list.getSlCtrStrtYm().length() != 6) {
            //                        errorDvo.setErrorRow(finalRow);
            //                        errorDvo.setHeaderName(headerTitle.get("slCtrStrtYm"));
            //                        errorDvo.setErrorData(messageService.getMessage("MSG_ALT_IS_NOT_VAL")); // 올바르지 않습니다.
            //                    }
            //                }
            //            }
            //            validateExcelAttribute(컬럼명, 이름,ExcelUploadErrorDvo, headerTitle, 문자길이 )

            //            if (StringUtils.isEmpty(list.getSlCtrMtrDvCd())) { // 유효성2 : 자료구분
            //                errorDvo.setErrorRow(finalRow);
            //                errorDvo.setHeaderName(headerTitle.get("prtnrCntrTpCd"));
            //                errorDvo.setErrorData(messageService.getMessage("MSG_ALT_EMPTY_REQUIRED_VAL")); //필수값이 누락되어 있습니다.
            //            } else {
            //                if (!Pattern.matches(pattern, list.getSlCtrMtrDvCd())) {
            //                    errorDvo.setErrorRow(finalRow);
            //                    errorDvo.setHeaderName(headerTitle.get("prtnrCntrTpCd"));
            //                    errorDvo.setErrorData(messageService.getMessage("MSG_ALT_ONLY_NUMBER"));// 숫자만 입력 가능합니다
            //                } else {
            //                    if (list.getSlCtrMtrDvCd().length() != 1) {
            //                        errorDvo.setErrorRow(finalRow);
            //                        errorDvo.setHeaderName(headerTitle.get("prtnrCntrTpCd"));
            //                        errorDvo.setErrorData(messageService.getMessage("MSG_ALT_IS_NOT_VAL"));// 숫자만 입력 가능합니다
            //                    }
            //                }
            //            }

            //            if (StringUtils.isEmpty(list.getSlCtrSellTpCd())) { // 유효성3 : 판매유형
            //                errorDvo.setErrorRow(finalRow);
            //                errorDvo.setHeaderName(headerTitle.get("slCtrSellTpCd"));
            //                errorDvo.setErrorData(messageService.getMessage("MSG_ALT_EMPTY_REQUIRED_VAL")); //필수값이 누락되어 있습니다.
            //            } else {
            //                if (!Pattern.matches(pattern, list.getSlCtrSellTpCd())) {
            //                    errorDvo.setErrorRow(finalRow);
            //                    errorDvo.setHeaderName(headerTitle.get("slCtrSellTpCd"));
            //                    errorDvo.setErrorData(messageService.getMessage("MSG_ALT_ONLY_NUMBER")); // 숫자만 입력 가능합니다
            //                } else {
            //                    if (list.getSlCtrSellTpCd().length() != 1) {
            //                        errorDvo.setErrorRow(finalRow);
            //                        errorDvo.setHeaderName(headerTitle.get("slCtrSellTpCd"));
            //                        errorDvo.setErrorData(messageService.getMessage("MSG_ALT_IS_NOT_VAL")); // 숫자만 입력 가능합니다
            //                    }
            //                }
            //            }

            //            if (StringUtils.isEmpty(list.getSlCtrDvCd())) { // 유효성4 : 조정구분
            //                errorDvo.setErrorRow(finalRow);
            //                errorDvo.setHeaderName(headerTitle.get("slCtrDvCd"));
            //                errorDvo.setErrorData(messageService.getMessage("MSG_ALT_EMPTY_REQUIRED_VAL")); //필수값이 누락되어 있습니다.
            //            } else {
            //                if (!Pattern.matches(pattern, list.getSlCtrDvCd())) {
            //                    errorDvo.setErrorRow(finalRow);
            //                    errorDvo.setHeaderName(headerTitle.get("slCtrDvCd"));
            //                    errorDvo.setErrorData(messageService.getMessage("MSG_ALT_ONLY_NUMBER")); // 숫자만 입력 가능합니다
            //                } else {
            //                    if (list.getSlCtrDvCd().length() != 1) {
            //                        errorDvo.setErrorRow(finalRow);
            //                        errorDvo.setHeaderName(headerTitle.get("slCtrDvCd"));
            //                        errorDvo.setErrorData(messageService.getMessage("MSG_ALT_IS_NOT_VAL")); // 숫자만 입력 가능합니다
            //                    }
            //                }
            //            }

            //            if (StringUtils.isEmpty(list.getSlCtrMtrTpCd())) { // 유효성5 : 조정유형
            //                errorDvo.setErrorRow(finalRow);
            //                errorDvo.setHeaderName(headerTitle.get("slCtrMtrTpCd"));
            //                errorDvo.setErrorData(messageService.getMessage("MSG_ALT_EMPTY_REQUIRED_VAL")); //필수값이 누락되어 있습니다.
            //            } else {
            //                if (!Pattern.matches(pattern, list.getSlCtrMtrTpCd())) {
            //                    errorDvo.setErrorRow(finalRow);
            //                    errorDvo.setHeaderName(headerTitle.get("slCtrMtrTpCd"));
            //                    errorDvo.setErrorData(messageService.getMessage("MSG_ALT_ONLY_NUMBER")); // 숫자만 입력 가능합니다
            //                } else {
            //                    if (list.getSlCtrMtrTpCd().length() != 1) {
            //                        errorDvo.setErrorRow(finalRow);
            //                        errorDvo.setHeaderName(headerTitle.get("slCtrMtrTpCd"));
            //                        errorDvo.setErrorData(messageService.getMessage("MSG_ALT_IS_NOT_VAL")); // 숫자만 입력 가능합니다
            //                    }
            //                }
            //            }

            //            if (StringUtils.isEmpty(list.getSlCtrDscTpCd())) { // 유효성6 : 할인
            //                errorDvo.setErrorRow(finalRow);
            //                errorDvo.setHeaderName(headerTitle.get("slCtrDscTpCd"));
            //                errorDvo.setErrorData(messageService.getMessage("MSG_ALT_EMPTY_REQUIRED_VAL")); //필수값이 누락되어 있습니다.
            //            } else {
            //                if (!Pattern.matches(pattern, list.getSlCtrDscTpCd())) {
            //                    errorDvo.setErrorRow(finalRow);
            //                    errorDvo.setHeaderName(headerTitle.get("slCtrDscTpCd"));
            //                    errorDvo.setErrorData(messageService.getMessage("MSG_ALT_ONLY_NUMBER")); // 숫자만 입력 가능합니다
            //                } else {
            //                    if (list.getSlCtrDscTpCd().length() != 2) {
            //                        errorDvo.setErrorRow(finalRow);
            //                        errorDvo.setHeaderName(headerTitle.get("slCtrDscTpCd"));
            //                        errorDvo.setErrorData(messageService.getMessage("MSG_ALT_IS_NOT_VAL")); // 숫자만 입력 가능합니다
            //                    }
            //                }
            //            }

            validateExcelAttribute(list.getSlCtrStrtYm(), "slCtrStrtYm", errorDvo, headerTitle, 6);
            validateExcelAttribute(list.getSlCtrMtrDvCd(), "slCtrMtrDvCd", errorDvo, headerTitle, 1);
            validateExcelAttribute(list.getSlCtrSellTpCd(), "slCtrSellTpCd", errorDvo, headerTitle, 1);
            validateExcelAttribute(list.getSlCtrDvCd(), "slCtrDvCd", errorDvo, headerTitle, 1);
            validateExcelAttribute(list.getSlCtrMtrTpCd(), "slCtrMtrTpCd", errorDvo, headerTitle, 1);
            validateExcelAttribute(list.getSlCtrDscTpCd(), "slCtrDscTpCd", errorDvo, headerTitle, 2);

            if (StringUtils.isEmpty(list.getSlCtrRmkCn())) { // 유효성7 : 조정사유
                errorDvo.setErrorRow(finalRow);
                errorDvo.setHeaderName(headerTitle.get("slCtrRmkCn"));
                errorDvo.setErrorData(messageService.getMessage("MSG_ALT_EMPTY_REQUIRED_VAL")); //필수값이 누락되어 있습니다.
            }

            if (StringUtils.isNotEmpty(list.getCntrNo())
                && StringUtils.isNotEmpty(list.getCntrSn())
                && StringUtils.isNotEmpty(list.getSlCtrStrtYm())
                && StringUtils.isNotEmpty(list.getSlCtrMtrDvCd())
                && StringUtils.isNotEmpty(list.getSlCtrSellTpCd())
                && StringUtils.isNotEmpty(list.getSlCtrDvCd())
                && StringUtils.isNotEmpty(list.getSlCtrMtrTpCd())
                && StringUtils.isNotEmpty(list.getSlCtrDscTpCd())
                && StringUtils.isNotEmpty(list.getSlCtrRmkCn())) {
                if (CollectionUtils.isNotEmpty(checks)) { //중복데이터 체크
                    for (int i = 0; i < checks.size(); i++) {
                        if (checks.get(i).get("cntrNo").equals(list.getCntrNo())
                            && checks.get(i).get("cntrSn").equals(list.getCntrSn())
                            && checks.get(i).get("slCtrStrtYm").equals(list.getSlCtrStrtYm())
                            && checks.get(i).get("slCtrMtrDvCd").equals(list.getSlCtrMtrDvCd())
                            && checks.get(i).get("slCtrSellTpCd").equals(list.getSlCtrSellTpCd())
                            && checks.get(i).get("slCtrDvCd").equals(list.getSlCtrDvCd())
                            && checks.get(i).get("slCtrMtrTpCd").equals(list.getSlCtrMtrTpCd())
                            && checks.get(i).get("slCtrDscTpCd").equals(list.getSlCtrDscTpCd())
                            && checks.get(i).get("slCtrRmkCn").equals(list.getSlCtrRmkCn())) {
                            errorDvo.setErrorRow(finalRow);
                            errorDvo.setHeaderName(headerTitle.get("prtnrNo"));
                            errorDvo.setErrorData(messageService.getMessage("MSG_ALT_DUPLICATE_EXISTS")); //중복된 값이 존재합니다.
                        }
                    }
                }
                cntrExistCnt = mapper.selectSalesControlCount(list);
                if (cntrExistCnt > 0) { //기존데이터 체크
                    errorDvo.setErrorRow(finalRow);
                    errorDvo.setHeaderName(headerTitle.get("prtnrNo"));
                    errorDvo.setErrorData(messageService.getMessage("MSG_TXT_SMD_INF_EXST")); //동일한 정보가 존재합니다.
                }
                check.put("cntrNo", list.getCntrNo());
                check.put("cntrSn", list.getCntrSn());
                //                check.put("cntrDtlNo", list.getCntrDtlNo());
                check.put("cstKnm", list.getCstKnm());
                check.put("slCtrStrtYm", list.getSlCtrStrtYm());
                check.put("slCtrEndYm", list.getSlCtrEndYm());
                check.put("slCtrMtrDvCd", list.getSlCtrMtrDvCd());
                check.put("slCtrSellTpCd", list.getSlCtrSellTpCd());
                check.put("pdCd", list.getPdCd());
                check.put("pdNm", list.getPdNm());
                check.put("slCtrDvCd", list.getSlCtrDvCd());
                check.put("slCtrMtrTpCd", list.getSlCtrMtrTpCd());
                check.put("slCtrDscTpCd", list.getSlCtrDscTpCd());
                check.put("canAfOjYn", list.getCanAfOjYn());
                check.put("slCtrAmt", list.getSlCtrAmt());
                check.put("slCtrWoExmpAmt", list.getSlCtrWoExmpAmt());
                check.put("slCtrPtrmExmpAmt", list.getSlCtrPtrmExmpAmt());
                check.put("slCtrRmkCn", list.getSlCtrRmkCn());
                checks.add(check);

            }

            if (null != errorDvo && (errorDvo.getErrorRow() != 0)) {
                errorDvos.add(errorDvo);
            }
            row++;
        }

        if (CollectionUtils.isNotEmpty(errorDvos)) {
            status = "E";
        }
        if (status.equals("S")) {
            for (WwdcSalesControlDvo list : lists) {
                mapper.insertSalesControl(list);
            }

        }
        ExcelUploadDto.UploadRes result = ExcelUploadDto.UploadRes.builder()
            .status(status)
            .excelData(lists)
            .errorInfo(errorDvos)
            .build();
        return result;
    }

}
