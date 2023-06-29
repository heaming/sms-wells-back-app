package com.kyowon.sms.wells.web.closing.performance.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.kyowon.sms.wells.web.closing.performance.converter.WdccNormalAccountExceptConverter;
import com.kyowon.sms.wells.web.closing.performance.dto.WdccNormalAccountExceptDto.SaveReq;
import com.kyowon.sms.wells.web.closing.performance.dto.WdccNormalAccountExceptDto.SearchCntrRes;
import com.kyowon.sms.wells.web.closing.performance.dto.WdccNormalAccountExceptDto.SearchReq;
import com.kyowon.sms.wells.web.closing.performance.dto.WdccNormalAccountExceptDto.SearchRes;
import com.kyowon.sms.wells.web.closing.performance.dvo.WdccNormalAccountExceptDvo;
import com.kyowon.sms.wells.web.closing.performance.mapper.WdccNormalAccountExceptMapper;
import com.sds.sflex.common.common.dvo.ExcelMetaDvo;
import com.sds.sflex.common.common.service.ExcelReadService;
import com.sds.sflex.common.docs.service.AttachFileService;
import com.sds.sflex.common.uifw.dvo.MessageResourceDvo;
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
    public List<SearchRes> getProductList(
        SearchReq dto
    ) {
        return mapper.selectProduct(dto);
    }

    /**
     * 정상계정 제외관리 - 계약상세번호별 - 조회
     * @param dto 검색조건
     * @return result 조회 목록
     */
    public List<SearchCntrRes> getContractList(
        SearchReq dto
    ) {
        return mapper.selectContract(dto);
    }

    /**
     * 정상계정 제외관리 - SAVE
     * @param dtos 등록 / 수정 목록
     * @return processCount 처리건수
     */
    @Transactional
    public int createExceptManagement(
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
    public int removeExceptManagement(
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
    //    public int saveExceptManagementForExcelUpload(
    //        MultipartFile file
    //    ) throws Exception {
    //        Map<String, String> headerTitle = new LinkedHashMap<>();
    //        headerTitle.put("messageResourcesId", messageResourceService.getMessage("MSG_TXT_MSG_RESO_ID"));
    //        headerTitle.put("langId", messageResourceService.getMessage("MSG_TXT_LANG"));
    //        headerTitle.put("multiLanguageContent", messageResourceService.getMessage("MSG_TXT_MSG_RESO_VAL"));
    //
    //        ExcelMetaDvo meta = new ExcelMetaDvo(1, headerTitle);
    //
    //        // 업로드 엑셀 파일 DRM 복호화
    //        List<MessageResourceDvo> messageResources = excelReadService.readExcel(file, meta, MessageResourceDvo.class);
    //
    //        int processCount = 0;
    //        int row = 1;
    //
    //        for (MessageResourceDvo messageResource : messageResources) {
    //            String messageResourcesId = messageResource.getMessageResourcesId();
    //            String langId = messageResource.getLangId();
    //            String multiLanguageContent = messageResource.getMultiLanguageContent();
    //
    //            // 유효성검사
    //            BizAssert.hasText(
    //                messageResourcesId, "MSG_ALT_INVALID_UPLOAD_DATA",
    //                new String[] {String.valueOf(row), headerTitle.get("messageResourcesId"), messageResourcesId}
    //            );
    //
    //            processCount++;
    //        }
    //        return processCount;
    //    }
}
