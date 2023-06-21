package com.kyowon.sms.wells.web.bond.credit.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.kyowon.sms.wells.web.bond.credit.converter.WbndRentalCbMgtMessageExcludeConverter;
import com.kyowon.sms.wells.web.bond.credit.dto.WbndRentalCbMgtMessageExcludeDto.SaveReq;
import com.kyowon.sms.wells.web.bond.credit.dto.WbndRentalCbMgtMessageExcludeDto.SearchReq;
import com.kyowon.sms.wells.web.bond.credit.dto.WbndRentalCbMgtMessageExcludeDto.SearchRes;
import com.kyowon.sms.wells.web.bond.credit.dvo.WbndBondContactExcludeIzDvo;
import com.kyowon.sms.wells.web.bond.credit.mapper.WbndRentalCbMgtMessageExcludeMapper;
import com.sds.sflex.common.common.dto.ExcelUploadDto.UploadRes;
import com.sds.sflex.common.common.dvo.ExcelMetaDvo;
import com.sds.sflex.common.common.dvo.ExcelUploadErrorDvo;
import com.sds.sflex.common.common.service.ExcelReadService;
import com.sds.sflex.common.utils.DateUtil;
import com.sds.sflex.common.utils.StringUtil;
import com.sds.sflex.system.config.constant.CommConst;
import com.sds.sflex.system.config.core.service.MessageResourceService;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import com.sds.sflex.system.config.exception.BizException;
import com.sds.sflex.system.config.validation.BizAssert;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class WbndRentalCbMgtMessageExcludeService {

    private final WbndRentalCbMgtMessageExcludeMapper mapper;
    private final WbndRentalCbMgtMessageExcludeConverter converter;
    private final MessageResourceService messageResourceService;
    private final ExcelReadService excelReadService;

    public PagingResult<SearchRes> getRentalCbMessageExcludePages(
        SearchReq dto, PageInfo pageInfo
    ) {
        return mapper.selectRentalCbMessageExcludePages(dto, pageInfo);
    }

    public List<SearchRes> getRentalCbMessageExcludesForExcelDownload(SearchReq dto) {
        return mapper.selectRentalCbMessageExcludePages(dto);
    }

    @Transactional
    public int saveRentalCbMessageExcludes(List<SaveReq> dtos) {
        int processCount = 0;
        for (SaveReq dto : dtos) {
            WbndBondContactExcludeIzDvo dvo = this.converter.mapSaveReqToContactDvo(dto);
            switch (dto.rowState()) {
                case CommConst.ROW_STATE_CREATED -> {
                    int result = this.mapper.insertRentalCbMessageExclude(dvo);
                    BizAssert.isTrue(result == 1, "MSG_ALT_SVE_ERR");
                    processCount += result;
                }
                case CommConst.ROW_STATE_UPDATED -> {
                    int result = this.mapper.updateRentalCbMessageExclude(dvo);
                    BizAssert.isTrue(result == 1, "MSG_ALT_SVE_ERR");
                    processCount += result;
                }
                default -> throw new BizException("MSG_ALT_UNHANDLE_ROWSTATE");
            }
        }
        return processCount;
    }

    @Transactional
    public int removeRentalCbMessageExcludes(List<String> bndCntcExcdOjIds) {
        int processCount = 0;

        for (String bndCntcExcdOjId : bndCntcExcdOjIds) {
            int result = this.mapper.deleteRentalCbMessageExclude(bndCntcExcdOjId);
            BizAssert.isTrue(result == 1, "MSG_ALT_SVE_ERR");
            processCount += result;
        }
        return processCount;
    }

    @Transactional
    public UploadRes saveRentalCbMessageExcludesExcelUpload(MultipartFile file) throws Exception {
        // 업로드 엑셀 헤더 설정
        Map<String, String> headerTitle = new LinkedHashMap<>();
        headerTitle.put("cstNo", messageResourceService.getMessage("MSG_TXT_CST_NO"));
        headerTitle.put("apyStrtdt", messageResourceService.getMessage("MSG_TXT_APY_STRT_YM"));
        headerTitle.put("apyEnddt", messageResourceService.getMessage("MSG_TXT_APY_END_YM"));

        // file
        List<WbndBondContactExcludeIzDvo> list = excelReadService
            .readExcel(file, new ExcelMetaDvo(1, headerTitle), WbndBondContactExcludeIzDvo.class);
        List<ExcelUploadErrorDvo> excelUploadErrorDvos = new ArrayList<>();

        // 엑셀데이터 유효성체크
        int row = 1;
        int processCount = 0;
        for (WbndBondContactExcludeIzDvo dvo : list) {
            Map<String, String> headerTitleValidation = Map.of(
                "cstNo", StringUtil.isBlank(dvo.getCstNo()) ? "" : dvo.getCstNo(),
                "apyStrtdt", StringUtil.isBlank(dvo.getApyStrtdt()) ? "" : dvo.getApyStrtdt(),
                "apyEnddt", StringUtil.isBlank(dvo.getApyEnddt()) ? "" : dvo.getApyEnddt()
            );

            for (String key : headerTitleValidation.keySet()) {
                // empty check
                if (StringUtil.isBlank(headerTitleValidation.get(key))) {
                    ExcelUploadErrorDvo errorDvo = new ExcelUploadErrorDvo();
                    errorDvo.setErrorRow(row);
                    errorDvo.setHeaderName(headerTitle.get(key));
                    errorDvo.setErrorData(
                        messageResourceService.getMessage(
                            "MSG_ALT_NO_EXPORT_DATA" // 엑셀 파일로 내보낼 데이터가 없습니다.
                        )
                    );
                    excelUploadErrorDvos.add(errorDvo);
                }
                // 적용시작년월 적용종료년월 check
                if (key.equals("apyEnddt") && StringUtil.isBlank(headerTitleValidation.get(key))) {
                    String date = headerTitleValidation.get(key).replaceAll("[^0-9]", "");
                    if (!DateUtil.isValid(date, "yyyyMM")) {
                        ExcelUploadErrorDvo errorDvo = new ExcelUploadErrorDvo();
                        errorDvo.setErrorRow(row);
                        errorDvo.setHeaderName(headerTitle.get(key));
                        errorDvo.setErrorData(
                            messageResourceService.getMessage(
                                "MSG_ALT_CHK_DT" // 날짜를 확인해 주세요.
                            )
                        );
                        excelUploadErrorDvos.add(errorDvo);
                    }
                }
                if (key.equals("apyStrtdt") && StringUtil.isBlank(headerTitleValidation.get(key))) {
                    String date = headerTitleValidation.get(key).replaceAll("[^0-9]", "");
                    if (!DateUtil.isValid(date, "yyyyMM")) {
                        ExcelUploadErrorDvo errorDvo = new ExcelUploadErrorDvo();
                        errorDvo.setErrorRow(row);
                        errorDvo.setHeaderName(headerTitle.get(key));
                        errorDvo.setErrorData(
                            messageResourceService.getMessage(
                                "MSG_ALT_CHK_DT" // 날짜를 확인해 주세요.
                            )
                        );
                        excelUploadErrorDvos.add(errorDvo);
                    }
                }
            }
            row++;
        }
        if (excelUploadErrorDvos.size() == 0) {
            for (WbndBondContactExcludeIzDvo dvo : list) {
                dvo.setCtntExcdBndBizCd("02"); // 렌탈CB
                dvo.setCtntExcdOjTpCd("01"); // 고객번호
                dvo.setCtntExcdMediTpCd("03"); // 알림톡

                int result = this.mapper.insertRentalCbMessageExclude(dvo);
                BizAssert.isTrue(result == 1, "MSG_ALT_SVE_ERR");
                processCount += result;
            }
        }
        return UploadRes.builder()
            .status(excelUploadErrorDvos.isEmpty() ? "S" : "E").errorInfo(excelUploadErrorDvos).excelData(list).build();

    }
}
