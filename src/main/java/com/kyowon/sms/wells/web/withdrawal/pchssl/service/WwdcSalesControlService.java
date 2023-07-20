package com.kyowon.sms.wells.web.withdrawal.pchssl.service;

import java.util.*;
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

        for (SaveSalesControlReq dto : req) {
            WwdcSalesControlDvo dvo = convert.mapSaveWwdcSalesControlDvo(dto);
            switch (dto.rowState()) {
                case CommConst.ROW_STATE_CREATED -> {
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
    public ExcelUploadDto.UploadRes saveSalesControlExcelUpload(String exmpYn, MultipartFile file) throws Exception {
        if (exmpYn.equals("N")) { // 방학제외 미선택시
            Map<String, String> headerTitle = new LinkedHashMap<>();
            headerTitle.put("cntrDtlNo", messageService.getMessage("MSG_TXT_CNTR_SN"));
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
            //            String pattern = "^[0-9]*$"; //숫자 정규식
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

                if (StringUtils.isEmpty(list.getCntrDtlNo())) { // 유효성
                    errorDvo.setErrorRow(finalRow);
                    errorDvo.setHeaderName(headerTitle.get("cntrDtlNo"));
                    errorDvo.setErrorData(messageService.getMessage("MSG_ALT_EMPTY_REQUIRED_VAL")); //필수값이 누락되어 있습니다.
                } else {
                    if (!Pattern.matches(specialPattern, list.getCntrDtlNo())) {
                        errorDvo.setErrorRow(finalRow);
                        errorDvo.setHeaderName(headerTitle.get("cntrDtlNo"));
                        errorDvo.setErrorData(messageService.getMessage("MSG_ALT_CHK_SPECL_CHAR")); // 특수 문자는 허용되지 않습니다.
                    } else {
                        if (list.getCntrDtlNo().length() != 12) {
                            errorDvo.setErrorRow(finalRow);
                            errorDvo.setHeaderName(headerTitle.get("cntrDtlNo"));
                            errorDvo.setErrorData(messageService.getMessage("MSG_ALT_CNTR_DTL_NO_CONF")); // 올바른 계약상세번호로 조회 하세요.
                        }
                    }
                }

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
        } else { // 방학제외 선택시 (방학 할인)
            Map<String, String> headerTitle = new LinkedHashMap<>();
            headerTitle.put("cntrDtlNo", messageService.getMessage("MSG_TXT_CNTR_SN"));
            headerTitle.put("cstKnm", messageService.getMessage("MSG_TXT_CST_NM"));
            headerTitle.put("slCtrStrtYm", messageService.getMessage("MSG_TXT_STRT_YM"));
            headerTitle.put("slCtrEndYm", messageService.getMessage("MSG_TXT_END_YM"));
            headerTitle.put("canDt", messageService.getMessage("MSG_TXT_CANC_DT"));
            headerTitle.put("apyY", messageService.getMessage("MSG_TXT_APY_Y"));
            headerTitle.put("jan", messageService.getMessage("MSG_TXT_JAN"));
            headerTitle.put("feb", messageService.getMessage("MSG_TXT_FEB"));
            headerTitle.put("jul", messageService.getMessage("MSG_TXT_JUL"));
            headerTitle.put("aug", messageService.getMessage("MSG_TXT_AUG"));
            headerTitle.put("slCtrMtrDvCd", messageService.getMessage("MSG_TXT_MTR_DV"));
            headerTitle.put("slCtrSellTpCd", messageService.getMessage("MSG_TXT_SELL_TP_CD"));
            headerTitle.put("sellTpDtlCd", messageService.getMessage("MSG_TXT_SELL_TP_DTL"));
            headerTitle.put("slCtrWoExmpAmt", messageService.getMessage("MSG_TXT_CTR_EXMP_AMT"));
            headerTitle.put("slCtrDvCd", messageService.getMessage("MSG_TXT_CTR_DV"));
            headerTitle.put("slCtrDscTpCd", messageService.getMessage("MSG_TXT_DSC"));
            headerTitle.put("slCtrMtrTpCd", messageService.getMessage("MSG_TXT_CTR_TP"));

            String specialPattern = "^[0-9a-zA-Zㄱ-ㅎ가-힣]*$"; //특수문자 정규식
            //            String pattern = "^[0-9]*$"; //숫자 정규식
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

                if (StringUtils.isEmpty(list.getCntrDtlNo())) { // 유효성
                    errorDvo.setErrorRow(finalRow);
                    errorDvo.setHeaderName(headerTitle.get("cntrDtlNo"));
                    errorDvo.setErrorData(messageService.getMessage("MSG_ALT_EMPTY_REQUIRED_VAL")); //필수값이 누락되어 있습니다.
                } else {
                    if (!Pattern.matches(specialPattern, list.getCntrDtlNo())) {
                        errorDvo.setErrorRow(finalRow);
                        errorDvo.setHeaderName(headerTitle.get("cntrDtlNo"));
                        errorDvo.setErrorData(messageService.getMessage("MSG_ALT_CHK_SPECL_CHAR")); // 특수 문자는 허용되지 않습니다.
                    } else {
                        if (list.getCntrDtlNo().length() != 12) {
                            errorDvo.setErrorRow(finalRow);
                            errorDvo.setHeaderName(headerTitle.get("cntrDtlNo"));
                            errorDvo.setErrorData(messageService.getMessage("MSG_ALT_CNTR_DTL_NO_CONF")); // 올바른 계약상세번호로 조회 하세요.
                        }
                    }
                }

                validateExcelAttribute(list.getSlCtrStrtYm(), "slCtrStrtYm", errorDvo, headerTitle, 6);
                validateExcelAttribute(list.getSlCtrMtrDvCd(), "slCtrMtrDvCd", errorDvo, headerTitle, 1);
                validateExcelAttribute(list.getSlCtrSellTpCd(), "slCtrSellTpCd", errorDvo, headerTitle, 1);
                validateExcelAttribute(list.getSlCtrDvCd(), "slCtrDvCd", errorDvo, headerTitle, 1);
                validateExcelAttribute(list.getSlCtrMtrTpCd(), "slCtrMtrTpCd", errorDvo, headerTitle, 1);
                validateExcelAttribute(list.getSlCtrDscTpCd(), "slCtrDscTpCd", errorDvo, headerTitle, 2);

                if (StringUtils.isNotEmpty(list.getCntrDtlNo())
                    && StringUtils.isNotEmpty(list.getSlCtrStrtYm())
                    && StringUtils.isNotEmpty(list.getSlCtrMtrDvCd())
                    && StringUtils.isNotEmpty(list.getSlCtrSellTpCd())
                    && StringUtils.isNotEmpty(list.getSlCtrDvCd())
                    && StringUtils.isNotEmpty(list.getSlCtrMtrTpCd())
                    && StringUtils.isNotEmpty(list.getSlCtrDscTpCd())) {
                    if (CollectionUtils.isNotEmpty(checks)) { //중복데이터 체크
                        for (Map<String, Object> stringObjectMap : checks) {
                            if (stringObjectMap.get("cntrNo").equals(list.getCntrDtlNo())
                                && stringObjectMap.get("slCtrStrtYm").equals(list.getSlCtrStrtYm())
                                && stringObjectMap.get("slCtrMtrDvCd").equals(list.getSlCtrMtrDvCd())
                                && stringObjectMap.get("slCtrSellTpCd").equals(list.getSlCtrSellTpCd())
                                && stringObjectMap.get("slCtrDvCd").equals(list.getSlCtrDvCd())
                                && stringObjectMap.get("slCtrMtrTpCd").equals(list.getSlCtrMtrTpCd())
                                && stringObjectMap.get("slCtrDscTpCd").equals(list.getSlCtrDscTpCd())) {
                                errorDvo.setErrorRow(finalRow);
                                errorDvo.setHeaderName(headerTitle.get("prtnrNo"));
                                errorDvo.setErrorData(messageService.getMessage("MSG_ALT_DUPLICATE_EXISTS")); //중복된 값이 존재합니다.
                            }
                        }
                    }
                    cntrExistCnt = mapper.selectSalesControlCount(list);
                    if (cntrExistCnt > 0) { //기존데이터 체크
                        errorDvo.setErrorRow(finalRow);
                        errorDvo.setHeaderName(headerTitle.get("cntrDtlNo"));
                        errorDvo.setErrorData(messageService.getMessage("MSG_TXT_SMD_INF_EXST")); //동일한 정보가 존재합니다.
                    }
                    check.put("cntrDtlNo", list.getCntrDtlNo());
                    check.put("cstKnm", list.getCstKnm());
                    check.put("slCtrStrtYm", list.getSlCtrStrtYm());
                    check.put("slCtrEndYm", list.getSlCtrEndYm());
                    check.put("canDt", list.getCanDt());
                    check.put("apyY", list.getApyY());
                    check.put("jan", list.getJan());
                    check.put("feb", list.getFeb());
                    check.put("jul", list.getJul());
                    check.put("aug", list.getAug());

                    check.put("slCtrMtrDvCd", list.getSlCtrMtrDvCd());
                    check.put("slCtrSellTpCd", list.getSlCtrSellTpCd());
                    check.put("sellTpDtlCd", list.getSellTpDtlCd());
                    check.put("slCtrWoExmpAmt", list.getSlCtrWoExmpAmt());
                    check.put("slCtrDvCd", list.getSlCtrDvCd());
                    check.put("slCtrDscTpCd", list.getSlCtrDscTpCd());
                    check.put("slCtrMtrTpCd", list.getSlCtrMtrTpCd());

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
            ExcelUploadDto.UploadRes result2 = ExcelUploadDto.UploadRes.builder()
                .status(status)
                .excelData(lists)
                .errorInfo(errorDvos)
                .build();
            return result2;
        }
    }
}
