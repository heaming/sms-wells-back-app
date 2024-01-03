package com.kyowon.sms.wells.web.bond.consultation.service;

import com.kyowon.sms.common.web.bond.zcommon.utils.BnBondUtils;
import com.kyowon.sms.wells.web.bond.consultation.converter.WbncRentalResignExpectedMgtConverter;
import com.kyowon.sms.wells.web.bond.consultation.dvo.WbncAuthorityResignIzDvo;
import com.kyowon.sms.wells.web.bond.consultation.mapper.WbncRentalResignExpectedMgtMapper;
import com.kyowon.sms.wells.web.contract.changeorder.dvo.WctbContractDtlStatCdChDvo;
import com.kyowon.sms.wells.web.contract.changeorder.service.WctbContractDtlStatCdChService;
import com.kyowon.sms.wells.web.contract.zcommon.constants.WctzCntrDtlStatCd;
import com.sds.sflex.common.common.dto.ExcelUploadDto.UploadRes;
import com.sds.sflex.common.common.dvo.ExcelMetaDvo;
import com.sds.sflex.common.common.dvo.ExcelUploadErrorDvo;
import com.sds.sflex.common.common.service.ExcelReadService;
import com.sds.sflex.common.utils.DateUtil;
import com.sds.sflex.common.utils.StringUtil;
import com.sds.sflex.system.config.constant.CommConst;
import com.sds.sflex.system.config.core.service.MessageResourceService;
import com.sds.sflex.system.config.exception.BizException;
import com.sds.sflex.system.config.validation.BizAssert;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;
import java.util.regex.Matcher;

import static com.kyowon.sms.wells.web.bond.consultation.dto.WbncRentalResignExpectedMgtDto.*;

@Service
@RequiredArgsConstructor
public class WbncRentalResignExpectedMgtService {

    private final WbncRentalResignExpectedMgtMapper mapper;
    private final WbncRentalResignExpectedMgtConverter converter;
    private final MessageResourceService messageResourceService;
    private final ExcelReadService excelReadService;
    private final WctbContractDtlStatCdChService wctbContractDtlStatCdChService;

    private static final String MSG_ALT_SVE_ERR_STR = "MSG_ALT_SVE_ERR";

    /**
     * <pre>
     * 직권 해지 내역 조회
     * </pre>
     *
     * @param dto baseDt 계약번호(필수)
     *            sellTpCd 상품유형코드
     *            clctamDvCd 집금구분코드
     *            clctamPrtnrNo 집금담당자
     *            cntrNo 계약번호
     *            cntrSn 계약일련번호
     *            cstNo 고객번호
     *            authRsgCd 확정구분(필수)
     * @author jeongwon.hwang
     * @since 2023-10-15
     */
    public SearchResponse getRentalResignExpecteds(SearchReq dto) {
        String baseDt = dto.baseDt();
        BizAssert.isTrue(DateUtil.getLastDateOfMonth(baseDt).equals(baseDt), "MSG_ALT_CHO_MM_TLST_D"); // 직권해지일자가 월마지막날이 아닙니다. 월마지막날을 선택하기 바랍니다.
        List<SearchRes> list = this.converter.listAuthorityResignIzToSearchRes(mapper.selectRentalResignExpecteds(dto));

        SearchResponse.SearchResponseBuilder builder = SearchResponse.builder().list(list);
        List<String> stateList = List.of("03", "02", "01"); // 01 : 예정생성 (미확정), 02: 예정확정, 03: 최정확정
        for (String state : stateList) {
            int count = this.checkRentalResignExpectedBaseYm(CheckReq.builder().baseDt(baseDt).authRsgCd(state).build());
            if (count > 0) {
                return builder.authRsgState(state).build();
            }
        }
        return builder.authRsgState("00").build();
    }

    /**
     * <pre>
     * 직권 해지 내역 생성
     * </pre>
     *
     * @param dto baseDt 계약번호(필수)
     * @author jeongwon.hwang
     * @since 2023-10-15
     */
    @Transactional
    public int createRentalResignExpecteds(CreateReq dto) throws Exception {
        int processCount = 0;
        String baseDt = dto.baseDt();
        BizAssert.isTrue(DateUtil.getLastDateOfMonth(baseDt).equals(baseDt), "MSG_ALT_CHO_MM_TLST_D"); // 직권해지일자가 월마지막날이 아닙니다. 월마지막날을 선택하기 바랍니다.

        int count = this.checkRentalResignExpectedBaseYm(CheckReq.builder().baseDt(baseDt).build());
        BizAssert.isTrue(count == 0, "MSG_ALT_EXIST_AUTH_AUTH_RSG_DT"); // 직권해지일자에 해당되는 데이터가 존재합니다.

        int rentalCount = this.mapper.insertAuthorityResignRentals(baseDt);
        BizAssert.isTrue(rentalCount >= 0, MSG_ALT_SVE_ERR_STR); // 저장에 실패 하였습니다.
        processCount += rentalCount;

        int regularShippingCount = this.mapper.insertAuthorityResignRegularShippings(baseDt);
        BizAssert.isTrue(regularShippingCount >= 0, MSG_ALT_SVE_ERR_STR); // 저장에 실패 하였습니다.
        processCount += regularShippingCount;

        BizAssert.isTrue(processCount != 0, "MSG_ALT_DTA_EXST"); // 생성된 데이터가 없습니다.

        /* 직권해지 대상관리 전월 제외대상 당월 저장 */
        this.mapper.updateOvrdAthrTrmtPreExcdAdmn(baseDt);

        return processCount;
    }

    private int checkRentalResignExpectedBaseYm(CheckReq dto) {
        return this.mapper.selectcheckRentalResignExpectedByReq(dto);
    }

    /**
     * <pre>
     * 직권 해지 제외 여부 수정
     * </pre>
     *
     * @param dtos baseDt 직권해지일(필수)
     *             excdYn 제외여부(필수)
     *             authRsgExcdRsonCd 제외사유
     *             cntrNo 계약번호(필수)
     *             cntrSn 계약일련번호(필수)
     *             rowState 상태코드(필수)
     * @author jeongwon.hwang
     * @since 2023-10-15
     */
    @Transactional
    public int editRentalResignExpecteds(List<SaveReq> dtos) throws Exception {
        int processCount = 0;
        for (SaveReq dto : dtos) {
            WbncAuthorityResignIzDvo dvo = this.converter.mapSaveReqToAuthorityResignIz(dto);
            switch (dto.rowState()) {
                case CommConst.ROW_STATE_UPDATED -> {
                    int result = this.mapper.updateRentalResignExpected(dvo);
                    BizAssert.isTrue(result == 1, MSG_ALT_SVE_ERR_STR); // 저장에 실패 하였습니다.
                    processCount += result;
                }
                default -> throw new BizException("MSG_ALT_UNHANDLE_ROWSTATE"); // 처리할 수 없는 rowState값입니다.
            }
        }
        return processCount;
    }

    /**
     * <pre>
     * 예정생성된 직권해지 데이터에 대해 예정확정, 최종확정 처리
     * </pre>
     *
     * @param dto baseDt 직권해지일(필수)
     *            confirmDvCd 예정확정(필수) : '01' , 최종확정 : '02'
     * @author jeongwon.hwang
     * @since 2023-10-15
     */
    @Transactional
    public int saveRentalResignExpectedCnfms(SaveConfirmReq dto) throws Exception {

        int processCount = 0;
        // 직권해지 렌탈 [예정확정] [최종확정]
        int rentalCount = this.mapper.updateAuthorityResignRentalCnfms(dto);
        BizAssert.isTrue(rentalCount >= 0, MSG_ALT_SVE_ERR_STR); // 저장에 실패 하였습니다.
        processCount += rentalCount;
        // 직권해지 정기배송 [예정확정] [최종확정]
        int regularShippingCount = this.mapper.updateAuthorityResignRegularShippingCnfms(dto);
        BizAssert.isTrue(regularShippingCount >= 0, MSG_ALT_SVE_ERR_STR); // 저장에 실패 하였습니다.
        processCount += regularShippingCount;

        BizAssert.isTrue(processCount != 0, "MSG_ALT_NO_CNFM_DTA"); // 확정할 데이터가 없습니다.

        // 직권해지 확정 조회, 계약상태 변경처리
        if ("02".equals(dto.confirmDvCd())) {

            SaveCancelReq cancelDto = this.converter.mapSaveReqToCancleDto(dto);
            // 직권해지 관리 취소자료 업데이트
            this.mapper.updateRentalResignExpectedCancel(cancelDto);
            // 계약해지처리내역 등록
            this.mapper.insertRentalCntrResignExpctCancel(cancelDto);
            // 직권해지 계약 조회
            List<WbncAuthorityResignIzDvo> resignConfirms = this.mapper.selectRentalResignConfirms(dto.baseDt());
            List<WctbContractDtlStatCdChDvo> resignContractList = getResignContractList(resignConfirms);
            if (!resignContractList.isEmpty()) {
                for (final var resignContract : resignContractList) {
                    wctbContractDtlStatCdChService.editContractDtlStatCdCh(resignContract);
                }
            }
        }
        return processCount;
    }

    /**
     * <pre>
     * 예정확정된 직권해지 대상 알림톡 발송 건수 체크
     * </pre>
     *
     * @param dto baseDt 직권해지일(필수)
     * @author jeongwon.hwang
     * @since 2023-10-15
     */
    @Transactional
    public Integer getRentalResignExpectedSmsCount(SmsCheckReq dto) throws Exception {
        return this.mapper.selectRentalResignExpectedSmsCount(dto);
    }

    /**
     * <pre>
     * 엑셀 업로드 - 직권 해지 제외 여부 수정
     * </pre>
     *
     * @param file   baseYm 직권해지월(필수)
     *               cntrNoSn 계약상세번호(필수)
     *               excdYn 제외여부(필수)
     *               authRsgExcdRsonCd 제외사유
     * @param baseDt 기준월(필수)
     * @author jeongwon.hwang
     * @since 2023-10-15
     */
    @Transactional
    public UploadRes saveRentalResignExpectedExcelUpload(String baseDt, String authRsgCd, MultipartFile file) throws Exception {

        // 업로드 엑셀 헤더 설정
        Map<String, String> headerTitle = new LinkedHashMap<>();
        headerTitle.put("baseYm", messageResourceService.getMessage("MSG_TXT_AUTH_AUTH_RSG_YM")); // 직권해지년월
        headerTitle.put("cntrNoSn", messageResourceService.getMessage("MSG_TXT_CNTR_DTL_NO")); // 계약상세번호
        headerTitle.put("excdYn", messageResourceService.getMessage("MSG_TXT_EXCD_YN")); // 제외여부
        headerTitle.put("authRsgExcdRsonCd", messageResourceService.getMessage("MSG_TXT_EXCD_RSON")); // 제외 사유

        BizAssert.isTrue(StringUtil.isNotBlank(baseDt)
            , messageResourceService.getMessage("MSG_ALT_NCELL_REQUIRED_VAL", headerTitle.get("baseYm")));
        BizAssert.isTrue(DateUtil.getLastDateOfMonth(baseDt).equals(baseDt), "MSG_ALT_CHO_MM_TLST_D"); // 직권해지일자가 월마지막날이 아닙니다. 월마지막날을 선택하기 바랍니다.

        Date baseDate = BnBondUtils.parseStrToDate(baseDt.substring(0, 6));
        BizAssert.notNull(baseDate
            , messageResourceService.getMessage("MSG_ALT_NCELL_REQUIRED_VAL", headerTitle.get("baseYm")));

        // file
        List<WbncAuthorityResignIzDvo> list = excelReadService
            .readExcel(file, new ExcelMetaDvo(2, headerTitle), WbncAuthorityResignIzDvo.class, true);
        List<ExcelUploadErrorDvo> excelUploadErrorDvos = new ArrayList<>();

        BizAssert.notEmpty(list, "MSG_ALT_SAV_NO_DATA");

        int row = 3;
        // 엑셀 데이터 검증 and 정규식
        for (WbncAuthorityResignIzDvo dvo : list) {
            //null check
            String[] nullColumnName = new String[3];
            if (StringUtil.isBlank(dvo.getBaseYm())) {
                nullColumnName[0] = "baseYm";
            }
            if (StringUtil.isBlank(dvo.getCntrNoSn())) {
                nullColumnName[1] = "cntrNoSn";
            } else {
                Matcher matcher = BnBondUtils.cntrDtlNoMatch(dvo.getCntrNoSn());
                if (matcher == null) {
                    nullColumnName[1] = "cntrNoSn";
                }
            }
            if (StringUtil.isBlank(dvo.getExcdYn())) {
                nullColumnName[3] = "excdYn";
            }
            for (String column : nullColumnName) {
                if (StringUtil.isNotBlank(column)) {
                    ExcelUploadErrorDvo errorDvo = new ExcelUploadErrorDvo();
                    errorDvo.setErrorRow(row);
                    errorDvo.setHeaderName(headerTitle.get(column));
                    errorDvo.setErrorData(
                        messageResourceService.getMessage(
                            "MSG_ALT_EXIST_INVALID_ITEM" // 유효하지 않은 항목이 있습니다.
                        )
                    );
                    excelUploadErrorDvos.add(errorDvo);
                }
            }
            row++;
        }
        if (excelUploadErrorDvos.isEmpty()) {
            row = 3;

            for (WbncAuthorityResignIzDvo dvo : list) {
                String baseYm = dvo.getBaseYm();
                String excdYn = dvo.getExcdYn();
                Date baseYmDate = BnBondUtils.parseStrToDate(baseYm);
                if (baseYmDate == null) {
                    ExcelUploadErrorDvo errorDvo = new ExcelUploadErrorDvo();
                    errorDvo.setErrorRow(row);
                    errorDvo.setHeaderName(headerTitle.get("baseYm"));
                    // {0}이/가 올바르지 않은 날짜형식입니다.
                    errorDvo.setErrorData(
                        messageResourceService.getMessage(
                            "MSG_ALT_ERROR_DT", headerTitle.get("baseYm")
                        )
                    );
                    excelUploadErrorDvos.add(errorDvo);
                } else if (baseYmDate.compareTo(baseDate) != 0) {
                    ExcelUploadErrorDvo errorDvo = new ExcelUploadErrorDvo();
                    errorDvo.setErrorRow(row);
                    errorDvo.setHeaderName(headerTitle.get("baseYm"));
                    // 값이 올바르지 않습니다.
                    errorDvo.setErrorData(
                        messageResourceService.getMessage("MSG_ALT_IS_NOT_VAL")
                    );
                    excelUploadErrorDvos.add(errorDvo);
                } else {
                    Matcher matcher = BnBondUtils.cntrDtlNoMatch(dvo.getCntrNoSn());
                    if (matcher == null) {
                        ExcelUploadErrorDvo errorDvo = new ExcelUploadErrorDvo();
                        errorDvo.setErrorRow(row);
                        errorDvo.setHeaderName(headerTitle.get("cntrNoSn"));
                        // 계약번호가 존재하지 않습니다.
                        errorDvo.setErrorData(
                            messageResourceService.getMessage("MSG_ALT_CNTR_NO_NOT_EXIST")
                        );
                        excelUploadErrorDvos.add(errorDvo);
                    } else {
                        String cntrNo = matcher.group(1);
                        int cntrSn = Integer.parseInt(matcher.group(2));
                        /* 업로드 월 확정데이터 여부 확인 */
                        int checkCount = this.mapper.selectcheckRentalResignExpectedByReq(
                            CheckReq.builder().baseDt(baseYm).cntrNo(cntrNo).cntrSn(cntrSn).authRsgCd(authRsgCd).build()
                        );
                        if (checkCount != 1) {
                            ExcelUploadErrorDvo errorDvo = new ExcelUploadErrorDvo();
                            errorDvo.setErrorRow(row);
                            errorDvo.setHeaderName(headerTitle.get("cntrNoSn"));
                            // 계약번호가 존재하지 않습니다.
                            errorDvo.setErrorData(
                                messageResourceService.getMessage("MSG_ALT_CNTR_NO_NOT_EXIST")
                            );
                            excelUploadErrorDvos.add(errorDvo);
                        }
                    }
                }

                if (!List.of("0", "1").contains(excdYn)) {
                    ExcelUploadErrorDvo errorDvo = new ExcelUploadErrorDvo();
                    errorDvo.setErrorRow(row);
                    errorDvo.setHeaderName(headerTitle.get("excdYn"));
                    // 잘못된 제외여부 입니다.
                    errorDvo.setErrorData(
                        messageResourceService.getMessage("MSG_ALT_INVLD_PARAM", headerTitle.get("excdYn"))
                    );
                    excelUploadErrorDvos.add(errorDvo);
                } else if ("1".equals(excdYn) && StringUtil.isBlank(dvo.getAuthRsgExcdRsonCd())) {
                    ExcelUploadErrorDvo errorDvo = new ExcelUploadErrorDvo();
                    errorDvo.setErrorRow(row);
                    errorDvo.setHeaderName(headerTitle.get("authRsgExcdRsonCd"));
                    errorDvo.setErrorData(
                        messageResourceService.getMessage(
                            "MSG_ALT_EXIST_INVALID_ITEM" // 유효하지 않은 항목이 있습니다.
                        )
                    );
                    excelUploadErrorDvos.add(errorDvo);
                } else if ("0".equals(excdYn) && StringUtil.isNotBlank(dvo.getAuthRsgExcdRsonCd())) {
                    ExcelUploadErrorDvo errorDvo = new ExcelUploadErrorDvo();
                    errorDvo.setErrorRow(row);
                    errorDvo.setHeaderName(headerTitle.get("authRsgExcdRsonCd"));
                    errorDvo.setErrorData(
                        messageResourceService.getMessage(
                            "MSG_ALT_EXIST_INVALID_ITEM" // 유효하지 않은 항목이 있습니다.
                        )
                    );
                    excelUploadErrorDvos.add(errorDvo);
                } else if ("1".equals(excdYn) && StringUtil.isNotBlank(dvo.getAuthRsgExcdRsonCd())) {
                    if (!List.of("1", "2", "3", "4", "5").contains(dvo.getAuthRsgExcdRsonCd())) {
                        ExcelUploadErrorDvo errorDvo = new ExcelUploadErrorDvo();
                        errorDvo.setErrorRow(row);
                        errorDvo.setHeaderName(headerTitle.get("authRsgExcdRsonCd"));
                        // 잘못된 제외사유 입니다.
                        errorDvo.setErrorData(
                            messageResourceService
                                .getMessage("MSG_ALT_INVLD_PARAM", headerTitle.get("authRsgExcdRsonCd"))
                        );
                        excelUploadErrorDvos.add(errorDvo);
                    }
                }
                row++;
            }
        }
        // insert
        if (excelUploadErrorDvos.isEmpty()) {
            List<WbncAuthorityResignIzDvo> updateList = new ArrayList<>();
            for (WbncAuthorityResignIzDvo dvo : list) {
                WbncAuthorityResignIzDvo param = new WbncAuthorityResignIzDvo();
                param.setBaseYm(dvo.getBaseYm());
                Matcher matcher = BnBondUtils.cntrDtlNoMatch(dvo.getCntrNoSn());
                param.setCntrNo(matcher.group(1));
                param.setCntrSn(Integer.parseInt(matcher.group(2)));
                param.setExcdYn(dvo.getExcdYn().equals("0") ? "N" : "Y");
                param.setAuthRsgExcdRsonCd(
                    StringUtil.isBlank(dvo.getAuthRsgExcdRsonCd()) ? "" : dvo.getAuthRsgExcdRsonCd()
                );
                updateList.add(param);
            }
            if (!updateList.isEmpty()) {
                this.mapper.updateRentalResignExpectedList(updateList);
            }
        }
        return UploadRes.builder()
            .status(excelUploadErrorDvos.isEmpty() ? "S" : "E").errorInfo(excelUploadErrorDvos).excelData(list).build();
    }

    private List<WctbContractDtlStatCdChDvo> getResignContractList(List<WbncAuthorityResignIzDvo> resignConfirms) {
        List<WctbContractDtlStatCdChDvo> resignContractList = new ArrayList<>();
        // 계약 해지 호출
        for (WbncAuthorityResignIzDvo resignConfirm : resignConfirms) {
            WctbContractDtlStatCdChDvo wctbContractDtlStatCdChDvo = new WctbContractDtlStatCdChDvo();
            wctbContractDtlStatCdChDvo.setCntrNo(resignConfirm.getCntrNo());
            wctbContractDtlStatCdChDvo.setCntrSn(String.valueOf(resignConfirm.getCntrSn()));
            wctbContractDtlStatCdChDvo.setCntrDtlStatCd(WctzCntrDtlStatCd.CLTN_DLQ.getCode());
            resignContractList.add(wctbContractDtlStatCdChDvo);
        }
        return resignContractList;
    }
}
