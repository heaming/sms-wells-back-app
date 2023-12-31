package com.kyowon.sms.wells.web.closing.performance.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.kyowon.sms.wells.web.closing.performance.converter.WdccNormalAccountExceptConverter;
import com.kyowon.sms.wells.web.closing.performance.dto.WdccNormalAccountExceptDto.*;
import com.kyowon.sms.wells.web.closing.performance.dvo.WdccNormalAccountExceptDvo;
import com.kyowon.sms.wells.web.closing.performance.mapper.WdccNormalAccountExceptMapper;
import com.sds.sflex.common.common.dto.ExcelUploadDto.UploadRes;
import com.sds.sflex.common.common.dvo.ExcelMetaDvo;
import com.sds.sflex.common.common.dvo.ExcelUploadErrorDvo;
import com.sds.sflex.common.common.service.ExcelReadService;
import com.sds.sflex.common.docs.service.AttachFileService;
import com.sds.sflex.system.config.constant.CommConst;
import com.sds.sflex.system.config.core.service.MessageResourceService;
import com.sds.sflex.system.config.exception.BizException;
import com.sds.sflex.system.config.validation.BizAssert;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class WdccNormalAccountExceptService {
    private final WdccNormalAccountExceptMapper mapper;
    private final WdccNormalAccountExceptConverter converter;
    private final MessageResourceService messageResourceService;
    private final ExcelReadService excelReadService;
    private final AttachFileService attachFileService;

    /**
     * 정상계정 제외관리 - 상품별 - 조회
     * @param dto 검색조건
     * @return result 조회 목록
     */
    public List<SearchRes> getProductsList(
        SearchReq dto
    ) {
        return mapper.selectProduct(dto);
    }

    /**
     * 정상계정 제외관리 - 계약상세번호별 - 조회
     * @param dto 검색조건
     * @return result 조회 목록
     */
    public List<SearchCntrRes> getContractsList(
        SearchContractReq dto
    ) {
        return mapper.selectContract(dto);
    }

    /**
     * 정상계정 제외관리 - SAVE
     * @param dtos 등록 / 수정 목록
     * @return processCount 처리건수
     */
    @Transactional
    public int createNormalAccountExcept(
        List<SaveReq> dtos
    ) throws Exception {
        int processCount = 0;

        for (SaveReq dto : dtos) {
            WdccNormalAccountExceptDvo dvo = converter.mapSaveReqToWdccNormalAccountExceptDvo(dto);
            int result;
            switch (dto.rowState()) {
                case CommConst.ROW_STATE_CREATED -> {
                    result = mapper.insertExceptMng(dvo);
                    BizAssert.isTrue(result == 1, "MSG_ALT_SVE_ERR");
                    processCount += result;
                }
                case CommConst.ROW_STATE_UPDATED -> {
                    result = mapper.updateExceptMng(dvo);
                    BizAssert.isTrue(result == 1, "MSG_ALT_SVE_ERR");
                    processCount += result;
                }
                default -> throw new BizException("MSG_ALT_UNHANDLE_ROWSTATE");
            }
        }
        return processCount;
    }

    /**
     * 정상계정 제외관리 - 삭제
     * @param nomAccExcdIds 삭제 목록
     * @return processCount 처리건수
     */
    @Transactional
    public int removeNormalAccountExcept(
        List<String> nomAccExcdIds
    ) {
        int processCount = 0;
        for (String nomAccExcdId : nomAccExcdIds) {
            int result = mapper.deleteExceptMng(nomAccExcdId);
            BizAssert.isTrue(result == 1, "MSG_ALT_SVE_ERR");
            processCount += result;
        }
        return processCount;
    }

    /**
     * 정상계정 제외관리 - 엑셀 업로드
     */
    @Transactional
    public UploadRes saveNormalAccountExceptForExcelUpload(
        MultipartFile file
    ) throws Exception {
        Map<String, String> headerTitle = new LinkedHashMap<>();
        headerTitle.put("sellTpCd", messageResourceService.getMessage("MSG_TXT_SEL_TYPE"));
        headerTitle.put("sellTpDtlCd", messageResourceService.getMessage("MSG_TXT_SELL_TP_DTL"));
        headerTitle.put("pdCd", messageResourceService.getMessage("MSG_TXT_PRDT_CODE"));
        headerTitle.put("pmotCd", messageResourceService.getMessage("MSG_TXT_PMOT_CD"));
        headerTitle
            .put("cntrStrtdt", messageResourceService.getMessage("MSG_TXT_CNTRCT_DT" + "(" + "MSG_TXT_STRT" + ")"));
        headerTitle
            .put("cntrEnddt", messageResourceService.getMessage("MSG_TXT_CNTRCT_DT" + "(" + "MSG_TXT_SHUTDOWN" + ")"));
        headerTitle.put("cntrDtl", messageResourceService.getMessage("MSG_TXT_CNTR_DTL_NO"));
        headerTitle.put("cntrExcdRsonCn", messageResourceService.getMessage("MSG_TXT_CNTN"));
        headerTitle.put("nomAccExcdRsonCn", messageResourceService.getMessage("MSG_TXT_EXCD_RSON"));

        ExcelMetaDvo meta = new ExcelMetaDvo(1, headerTitle);

        // 업로드 엑셀 파일 DRM 복호화
        List<WdccNormalAccountExceptDvo> dvos = excelReadService
            .readExcel(file, meta, WdccNormalAccountExceptDvo.class);
        List<ExcelUploadErrorDvo> excelUploadErrorDvos = new ArrayList<>();

        int row = 2;
        int processCount = 0;

        for (WdccNormalAccountExceptDvo dvo : dvos) {

            if (StringUtils.isEmpty(dvo.getCntrDtl())) {} else {
                String cntrArr[] = dvo.getCntrDtl().split("-");

                dvo.setCntrNo(cntrArr[0]);
                dvo.setCntrSn(cntrArr[1]);
            }

            row++;

            if (excelUploadErrorDvos.size() == 0) {
                int result = mapper.insertExceptMng(dvo);
                BizAssert.isTrue(result == 1, "MSG_ALT_SVE_ERR");
                processCount += result;
            }
        }

        return UploadRes.builder()
            .status(excelUploadErrorDvos.isEmpty() ? "S" : "E").errorInfo(excelUploadErrorDvos).excelData(dvos).build();
    }
}
