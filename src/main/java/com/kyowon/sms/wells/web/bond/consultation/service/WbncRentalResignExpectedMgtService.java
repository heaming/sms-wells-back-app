package com.kyowon.sms.wells.web.bond.consultation.service;

import static com.kyowon.sms.wells.web.bond.consultation.dto.WbncRentalResignExpectedMgtDto.*;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.kyowon.sms.wells.web.bond.consultation.converter.WbncRentalResignExpectedMgtConverter;
import com.kyowon.sms.wells.web.bond.consultation.dvo.WbncAuthorityResignIzDvo;
import com.kyowon.sms.wells.web.bond.consultation.mapper.WbncRentalResignExpectedMgtMapper;
import com.sds.sflex.common.common.dto.ExcelUploadDto.UploadRes;
import com.sds.sflex.common.common.dvo.ExcelMetaDvo;
import com.sds.sflex.common.common.dvo.ExcelUploadErrorDvo;
import com.sds.sflex.common.common.service.ExcelReadService;
import com.sds.sflex.common.utils.DateUtil;
import com.sds.sflex.system.config.constant.CommConst;
import com.sds.sflex.system.config.exception.BizException;
import com.sds.sflex.system.config.validation.BizAssert;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WbncRentalResignExpectedMgtService {

    private final WbncRentalResignExpectedMgtMapper mapper;
    private final WbncRentalResignExpectedMgtConverter converter;
    //    private final MessageResourceService messageResourceService;
    private final ExcelReadService excelReadService;

    public List<SearchRes> getRentalResignExpecteds(SearchReq dto) {
        String baseDt = dto.baseDt();
        BizAssert.isTrue(DateUtil.getLastDateOfMonth(baseDt).equals(baseDt), "MSG_ALT_CHO_MM_TLST_D"); // 직권해지일자가 월마지막날이 아닙니다. 월마지막날을 선택하기 바랍니다.
        return this.converter.ListAuthorityResignIzToSearchRes(mapper.selectRentalResignExpecteds(dto));
    }

    @Transactional
    public int createRentalResignExpecteds(CreateReq dto) {
        int processCount = 0;
        String baseDt = dto.baseDt();
        BizAssert.isTrue(DateUtil.getLastDateOfMonth(baseDt).equals(baseDt), "MSG_ALT_CHO_MM_TLST_D"); // 직권해지일자가 월마지막날이 아닙니다. 월마지막날을 선택하기 바랍니다.

        int count = this.checkRentalResignExpectedBaseYm(CheckReq.builder().baseDt(baseDt).build());
        BizAssert.isTrue(count == 0, "MSG_ALT_EXIST_AUTH_AUTH_RSG_DT"); // 직권해지일자에 해당되는 데이터가 존재합니다.

        int rentalCount = this.mapper.insertAuthorityResignRentals(baseDt);
        BizAssert.isTrue(rentalCount >= 0, "MSG_ALT_SVE_ERR"); // 저장에 실패 하였습니다.
        processCount += rentalCount;

        int regularShippingCount = this.mapper.insertAuthorityResignRegularShippings(baseDt);
        BizAssert.isTrue(regularShippingCount >= 0, "MSG_ALT_SVE_ERR"); // 저장에 실패 하였습니다.
        processCount += regularShippingCount;

        BizAssert.isTrue(processCount != 0, "MSG_ALT_DTA_EXST"); // 생성된 데이터가 없습니다.
        return processCount;
    }

    public int checkRentalResignExpectedBaseYm(CheckReq dto) {
        return this.mapper.selectcheckRentalResignExpectedByReq(dto);
    }

    @Transactional
    public int editRentalResignExpecteds(List<SaveReq> dtos) {
        int processCount = 0;
        for (SaveReq dto : dtos) {
            WbncAuthorityResignIzDvo dvo = this.converter.mapSaveReqToAuthorityResignIz(dto);
            switch (dto.rowState()) {
                case CommConst.ROW_STATE_UPDATED -> {
                    int result = this.mapper.updateRentalResignExpected(dvo);
                    BizAssert.isTrue(result == 1, "MSG_ALT_SVE_ERR"); // 저장에 실패 하였습니다.
                    processCount += result;
                }
                default -> throw new BizException("MSG_ALT_UNHANDLE_ROWSTATE"); // 처리할 수 없는 rowState값입니다.
            }
        }
        return processCount;
    }

    @Transactional
    public int saveRentalResignExpectedCnfms(SaveConfirmReq dto) {
        int processCount = 0;

        // 직권해지 렌탈 [예정확정] [최종확정]
        int rentalCount = this.mapper.updateAuthorityResignRentalCnfms(dto);
        BizAssert.isTrue(rentalCount >= 0, "MSG_ALT_SVE_ERR"); // 저장에 실패 하였습니다.
        processCount += rentalCount;
        // 직권해지 정기배송 [예정확정] [최종확정]
        int regularShippingCount = this.mapper.updateAuthorityResignRegularShippingCnfms(dto);
        BizAssert.isTrue(regularShippingCount >= 0, "MSG_ALT_SVE_ERR"); // 저장에 실패 하였습니다.
        processCount += regularShippingCount;

        BizAssert.isTrue(processCount != 0, "MSG_ALT_NO_CNFM_DTA"); // 확정할 데이터가 없습니다.

        return processCount;
    }

    /** TODO: 설계 보완 후 수정 예정 */
    @Transactional
    public int saveRentalResignExpectedCancels(SaveCancelReq dto) {
        int processCount = 0;
        return processCount;
    }

    /** TODO: 설계 보완 후 수정 예정 */
    @Transactional
    public UploadRes saveRentalResignExpectedExcelUpload(MultipartFile file) throws Exception {
        // 업로드 엑셀 헤더 설정
        Map<String, String> headerTitle = new LinkedHashMap<>();
        //            headerTitle.put("fwDvNm", messageResourceService.getMessage("MSG_TXT_FW_DV"));
        //            headerTitle.put("cstNo", messageResourceService.getMessage("MSG_TXT_CST_NO"));
        //            headerTitle.put("apyStrtdt", messageResourceService.getMessage("MSG_TXT_APY_STRT_YM"));
        //            headerTitle.put("apyEnddt", messageResourceService.getMessage("MSG_TXT_APY_END_YM"));
        // file
        List<WbncAuthorityResignIzDvo> list = excelReadService
            .readExcel(file, new ExcelMetaDvo(1, headerTitle), WbncAuthorityResignIzDvo.class, true);
        List<ExcelUploadErrorDvo> excelUploadErrorDvos = new ArrayList<>();

        int row = 2;
        // 엑셀 데이터 검증 and 정규식
        for (WbncAuthorityResignIzDvo dvo : list) {
            // null check
            //                String[] nullColumnName = new String[4];
            //                if (StringUtil.isBlank(dvo.getFwDvNm())) {
            //                    nullColumnName[0] = "fwDvNm";
            //                }
            //                if (StringUtil.isBlank(dvo.getCstNo())) {
            //                    nullColumnName[1] = "cstNo";
            //                }
            //                if (StringUtil.isBlank(dvo.getApyStrtdt())) {
            //                    nullColumnName[2] = "apyStrtdt";
            //                }
            //                if (StringUtil.isBlank(dvo.getApyEnddt())) {
            //                    nullColumnName[3] = "apyEnddt";
            //                }
            //                for (String column : nullColumnName) {
            //                    if (StringUtil.isNotBlank(column)) {
            //                        ExcelUploadErrorDvo errorDvo = new ExcelUploadErrorDvo();
            //                        errorDvo.setErrorRow(row);
            //                        errorDvo.setHeaderName(headerTitle.get(column));
            //                        errorDvo.setErrorData(
            //                            messageResourceService.getMessage(
            //                                "MSG_ALT_EXIST_INVALID_ITEM" // 유효하지 않은 항목이 있습니다.
            //                            )
            //                        );
            //                        excelUploadErrorDvos.add(errorDvo);
            //                    }
        }

        // insert
        //            if (excelUploadErrorDvos.size() == 0) {
        //                for (WbncBondContactExcludeObjectIzDvo insertData : list) {
        //                    int result = this.mapper.updateRentalResignExpectedMgtExcelUpload(insertData);
        //                    BizAssert.isTrue(result == 1, "MSG_ALT_SVE_ERR");
        //                }
        //            }
        return UploadRes.builder()
            .status(excelUploadErrorDvos.isEmpty() ? "S" : "E").errorInfo(excelUploadErrorDvos).excelData(list).build();
    }
}
