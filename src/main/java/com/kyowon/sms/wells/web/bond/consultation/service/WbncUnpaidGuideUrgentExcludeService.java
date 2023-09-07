package com.kyowon.sms.wells.web.bond.consultation.service;

import static com.kyowon.sms.wells.web.bond.consultation.dto.WbncUnpaidGuideUrgentExcludeDto.SearchReq;
import static com.kyowon.sms.wells.web.bond.consultation.dto.WbncUnpaidGuideUrgentExcludeDto.SearchRes;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.jetty.util.StringUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.kyowon.sms.common.web.bond.zcommon.utils.BnBondUtils;
import com.kyowon.sms.wells.web.bond.consultation.converter.WbncUnpaidGuideUrgentExcludeConverter;
import com.kyowon.sms.wells.web.bond.consultation.dto.WbncUnpaidGuideUrgentExcludeDto.SaveReq;
import com.kyowon.sms.wells.web.bond.consultation.dvo.WbncBondContactExcludeObjectIzDvo;
import com.kyowon.sms.wells.web.bond.consultation.mapper.WbncUnpaidGuideUrgentExcludeMapper;
import com.sds.sflex.common.common.dto.ExcelUploadDto.UploadRes;
import com.sds.sflex.common.common.dvo.ExcelMetaDvo;
import com.sds.sflex.common.common.dvo.ExcelUploadErrorDvo;
import com.sds.sflex.common.common.service.ExcelReadService;
import com.sds.sflex.system.config.core.service.MessageResourceService;
import com.sds.sflex.system.config.validation.BizAssert;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WbncUnpaidGuideUrgentExcludeService {

    private final WbncUnpaidGuideUrgentExcludeMapper mapper;
    private final WbncUnpaidGuideUrgentExcludeConverter converter;
    private final MessageResourceService messageResourceService;
    private final ExcelReadService excelReadService;

    public List<SearchRes> getUnpaidGuideUrgentExcludes(SearchReq dto) {
        return mapper.selectUnpaidGuideUrgentExcludes(dto);
    }

    @Transactional
    public int saveUnpaidGuideUrgentExcludes(List<SaveReq> dtos) {
        int processCount = 0;

        for (SaveReq dto : dtos) {
            WbncBondContactExcludeObjectIzDvo dvo = this.converter.mapSaveReqToExcludeObjectIzDvo(dto);
            int result = this.mapper.updateUnpaidGuideUrgentExclude(dvo);
            BizAssert.isTrue(result == 1, "MSG_ALT_SVE_ERR");
            processCount += result;
        }
        return processCount;
    }

    @Transactional
    public UploadRes saveUnpaidGuideUrgentExcludesExcelUpload(MultipartFile file) throws Exception {
        // 업로드 엑셀 헤더 설정
        Map<String, String> headerTitle = new LinkedHashMap<>();
        headerTitle.put("fwDvNm", messageResourceService.getMessage("MSG_TXT_FW_DV"));
        headerTitle.put("cstNo", messageResourceService.getMessage("MSG_TXT_CST_NO"));
        headerTitle.put("apyStrtdt", messageResourceService.getMessage("MSG_TXT_APY_STRT_YM"));
        headerTitle.put("apyEnddt", messageResourceService.getMessage("MSG_TXT_APY_END_YM"));
        // file
        List<WbncBondContactExcludeObjectIzDvo> list = excelReadService
            .readExcel(file, new ExcelMetaDvo(1, headerTitle), WbncBondContactExcludeObjectIzDvo.class, true);
        List<ExcelUploadErrorDvo> excelUploadErrorDvos = new ArrayList<>();

        int row = 2;
        // 엑셀 데이터 검증 and 정규식
        for (WbncBondContactExcludeObjectIzDvo dvo : list) {
            // null check
            String[] nullColumnName = new String[4];
            if (StringUtil.isBlank(dvo.getFwDvNm())) {
                nullColumnName[0] = "fwDvNm";
            }
            if (StringUtil.isBlank(dvo.getCstNo())) {
                nullColumnName[1] = "cstNo";
            }
            if (StringUtil.isBlank(dvo.getApyStrtdt())) {
                nullColumnName[2] = "apyStrtdt";
            }
            if (StringUtil.isBlank(dvo.getApyEnddt())) {
                nullColumnName[3] = "apyEnddt";
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
            // 발송구분명 검증
            if (StringUtil.isNotBlank(dvo.getFwDvNm())) {
                switch (dvo.getFwDvNm()) {
                    case "안내서" -> dvo.setCtntExcdBndBizCd("04");
                    case "촉구서" -> dvo.setCtntExcdBndBizCd("05");
                    default -> {
                        ExcelUploadErrorDvo errorDvo = new ExcelUploadErrorDvo();
                        errorDvo.setErrorRow(row);
                        errorDvo.setHeaderName(headerTitle.get("fwDvNm"));
                        errorDvo.setErrorData(
                            messageResourceService.getMessage(
                                "MSG_ALT_INVALID_FW_DV_NM" // 잘못된 발송업무명입니다.
                            )
                        );
                        excelUploadErrorDvos.add(errorDvo);
                    }
                }

            }
            // 고객번호 검증
            if (StringUtil.isNotBlank(dvo.getCstNo())) {
                if (!dvo.getCstNo().matches("^\\d{1,10}$")) {
                    ExcelUploadErrorDvo errorDvo = new ExcelUploadErrorDvo();
                    errorDvo.setErrorRow(row);
                    errorDvo.setHeaderName(headerTitle.get("cstNo"));
                    errorDvo.setErrorData(
                        messageResourceService.getMessage(
                            "MSG_ALT_CST_NO_MRTN" // 잘못된 고객번호입니다.
                        )
                    );
                    excelUploadErrorDvos.add(errorDvo);
                }
            }

            // 적용시작년월 검증
            if (StringUtil.isNotBlank(dvo.getApyStrtdt())) {
                if (BnBondUtils.checkDate(dvo.getApyStrtdt())) {
                    String apyStrtdt = dvo.getApyStrtdt().replaceAll("[^0-9]", "");
                    dvo.setApyStrtdt(apyStrtdt);
                } else {
                    ExcelUploadErrorDvo errorDvo = new ExcelUploadErrorDvo();
                    errorDvo.setErrorRow(row);
                    errorDvo.setHeaderName(headerTitle.get("apyStrtdt"));
                    errorDvo.setErrorData(
                        messageResourceService.getMessage(
                            "MSG_ALT_EXIST_INVALID_ITEM" // 유효하지 않은 항목이 있습니다.
                        )
                    );
                    excelUploadErrorDvos.add(errorDvo);
                }
            }
            // 적용종료년월 검증
            if (StringUtil.isNotBlank(dvo.getApyEnddt())) {
                if (BnBondUtils.checkDate(dvo.getApyEnddt())) {
                    String apyEnddt = dvo.getApyEnddt().replaceAll("[^0-9]", "");
                    dvo.setApyEnddt(apyEnddt);
                } else {
                    ExcelUploadErrorDvo errorDvo = new ExcelUploadErrorDvo();
                    errorDvo.setErrorRow(row);
                    errorDvo.setHeaderName(headerTitle.get("apyEnddt"));
                    errorDvo.setErrorData(
                        messageResourceService.getMessage(
                            "MSG_ALT_EXIST_INVALID_ITEM" // 유효하지 않은 항목이 있습니다.
                        )
                    );
                    excelUploadErrorDvos.add(errorDvo);
                }
            }
            // 적용시작년월 > 적용종료년월
            if (StringUtil.isNotBlank(dvo.getApyStrtdt()) && StringUtil.isNotBlank(dvo.getApyEnddt())) {
                if (BnBondUtils.checkDate(dvo.getApyStrtdt()) && BnBondUtils.checkDate(dvo.getApyEnddt())) {
                    int apyStrtdt = Integer.parseInt(dvo.getApyStrtdt().replaceAll("[^0-9]", ""));
                    int apyEnddt = Integer.parseInt(dvo.getApyEnddt().replaceAll("[^0-9]", ""));
                    if (apyStrtdt > apyEnddt) {
                        ExcelUploadErrorDvo errorDvo = new ExcelUploadErrorDvo();
                        errorDvo.setErrorRow(row);
                        errorDvo.setHeaderName(headerTitle.get("apyStrtdt") + ", " + headerTitle.get("apyEnddt"));
                        errorDvo.setErrorData(
                            messageResourceService.getMessage(
                                "MSG_ALT_STRT_YM_END_YM_CONF" // 시작년월이 종료년월보다 클 수 없습니다.
                            )
                        );
                        excelUploadErrorDvos.add(errorDvo);
                    }
                }
            }
        }
        // insert
        if (excelUploadErrorDvos.size() == 0) {
            for (WbncBondContactExcludeObjectIzDvo insertData : list) {
                int result = this.mapper.updateUnpaidGuideUrgentExcludeExcelUpload(insertData);
                BizAssert.isTrue(result == 1, "MSG_ALT_SVE_ERR");
            }
        }
        return UploadRes.builder()
            .status(excelUploadErrorDvos.isEmpty() ? "S" : "E").errorInfo(excelUploadErrorDvos).excelData(list).build();
    }
}
