package com.kyowon.sms.wells.web.bond.consultation.service;

import static com.kyowon.sms.wells.web.bond.consultation.dto.WbncRentalResignExpectedMgtDto.*;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.kyowon.sms.common.web.bond.zcommon.utils.BnBondUtils;
import com.kyowon.sms.wells.web.bond.consultation.converter.WbncRentalResignExpectedMgtConverter;
import com.kyowon.sms.wells.web.bond.consultation.dvo.WbncAuthorityResignIzDvo;
import com.kyowon.sms.wells.web.bond.consultation.mapper.WbncRentalResignExpectedMgtMapper;
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

@Service
@RequiredArgsConstructor
public class WbncRentalResignExpectedMgtService {

    private final WbncRentalResignExpectedMgtMapper mapper;
    private final WbncRentalResignExpectedMgtConverter converter;
    private final MessageResourceService messageResourceService;
    private final ExcelReadService excelReadService;

    private static final String MSG_ALT_SVE_ERR_STR = "MSG_ALT_SVE_ERR";

    public List<SearchRes> getRentalResignExpecteds(SearchReq dto) {
        String baseDt = dto.baseDt();
        BizAssert.isTrue(DateUtil.getLastDateOfMonth(baseDt).equals(baseDt), "MSG_ALT_CHO_MM_TLST_D"); // 직권해지일자가 월마지막날이 아닙니다. 월마지막날을 선택하기 바랍니다.
        return this.converter.listAuthorityResignIzToSearchRes(mapper.selectRentalResignExpecteds(dto));
    }

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
        return processCount;
    }

    private int checkRentalResignExpectedBaseYm(CheckReq dto) {
        return this.mapper.selectcheckRentalResignExpectedByReq(dto);
    }

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

        return processCount;
    }

    @Transactional
    public int saveRentalResignExpectedCancels(SaveCancelReq dto) throws Exception {
        int processCount = this.mapper.insertRentalResignExpectedCancel(dto);
        BizAssert.isFalse(processCount == 0, "MSG_ALT_NO_DATA_RGST_CANCEL_DATA"); // 취소자료 등록할 자료가 없습니다.
        BizAssert.isTrue(processCount > 0, MSG_ALT_SVE_ERR_STR); // 저장에 실패 하였습니다.
        return processCount;
    }

    @Transactional
    public UploadRes saveRentalResignExpectedExcelUpload(MultipartFile file) throws Exception {
        // 업로드 엑셀 헤더 설정
        Map<String, String> headerTitle = new LinkedHashMap<>();
        headerTitle.put("baseYm", messageResourceService.getMessage("MSG_TXT_AUTH_AUTH_RSG_YM")); // 직권해지년월
        headerTitle.put("cntrNoSn", messageResourceService.getMessage("MSG_TXT_CST_CD")); // 계약상세번호
        headerTitle.put("excdYn", messageResourceService.getMessage("MSG_TXT_EXCD_YN")); // 제외여부
        headerTitle.put("authRsgExcdRsonCd", messageResourceService.getMessage("MSG_TXT_EXCD_RSON")); // 제외 사유
        // file
        List<WbncAuthorityResignIzDvo> list = excelReadService
            .readExcel(file, new ExcelMetaDvo(2, headerTitle), WbncAuthorityResignIzDvo.class, true);
        List<ExcelUploadErrorDvo> excelUploadErrorDvos = new ArrayList<>();
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
        if (excelUploadErrorDvos.size() == 0) {
            row = 3;
            for (WbncAuthorityResignIzDvo dvo : list) {
                String baseYm = dvo.getBaseYm();
                Matcher matcher = BnBondUtils.cntrDtlNoMatch(dvo.getCntrNoSn());
                String cntrNo = matcher.group(1);
                int cntrSn = Integer.parseInt(matcher.group(2));
                String excdYn = dvo.getExcdYn();

                if (!BnBondUtils.checkDate(baseYm)) {
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
                } else {
                    /* 업로드 월 확정데이터 여부 확인 */
                    int checkCount = this.mapper.selectcheckRentalResignExpectedByReq(
                        CheckReq.builder().baseDt(baseYm).cntrNo(cntrNo).cntrSn(cntrSn).build()
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

                if (!List.of("0", "1").contains(excdYn)) {
                    ExcelUploadErrorDvo errorDvo = new ExcelUploadErrorDvo();
                    errorDvo.setErrorRow(row);
                    errorDvo.setHeaderName(headerTitle.get("excdYn"));
                    // 잘못된 제외여부 입니다.
                    errorDvo.setErrorData(
                        messageResourceService.getMessage("MSG_ALT_INVLD_PARAM", headerTitle.get("excdYn"))
                    );
                    excelUploadErrorDvos.add(errorDvo);
                } else if (excdYn.equals("1") && StringUtil.isBlank(dvo.getAuthRsgExcdRsonCd())) {
                    ExcelUploadErrorDvo errorDvo = new ExcelUploadErrorDvo();
                    errorDvo.setErrorRow(row);
                    errorDvo.setHeaderName(headerTitle.get("authRsgExcdRsonCd"));
                    errorDvo.setErrorData(
                        messageResourceService.getMessage(
                            "MSG_ALT_EXIST_INVALID_ITEM" // 유효하지 않은 항목이 있습니다.
                        )
                    );
                    excelUploadErrorDvos.add(errorDvo);
                } else if (excdYn.equals("0") && StringUtil.isNotBlank(dvo.getAuthRsgExcdRsonCd())) {
                    ExcelUploadErrorDvo errorDvo = new ExcelUploadErrorDvo();
                    errorDvo.setErrorRow(row);
                    errorDvo.setHeaderName(headerTitle.get("authRsgExcdRsonCd"));
                    errorDvo.setErrorData(
                        messageResourceService.getMessage(
                            "MSG_ALT_EXIST_INVALID_ITEM" // 유효하지 않은 항목이 있습니다.
                        )
                    );
                    excelUploadErrorDvos.add(errorDvo);
                } else if (excdYn.equals("1") && StringUtil.isNotBlank(dvo.getAuthRsgExcdRsonCd())) {
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
        if (excelUploadErrorDvos.size() == 0) {
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

                int result = this.mapper.updateRentalResignExpected(param);
                BizAssert.isTrue(result == 1, MSG_ALT_SVE_ERR_STR);
            }
        }
        return UploadRes.builder()
            .status(excelUploadErrorDvos.isEmpty() ? "S" : "E").errorInfo(excelUploadErrorDvos).excelData(list).build();
    }
}
