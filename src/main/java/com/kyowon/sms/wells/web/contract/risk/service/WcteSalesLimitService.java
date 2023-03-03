package com.kyowon.sms.wells.web.contract.risk.service;

import static com.kyowon.sms.wells.web.contract.risk.dto.WcteSalesLimitDto.*;

import java.util.*;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.kyowon.sms.wells.web.contract.risk.converter.WcteSalesLimitConverter;
import com.kyowon.sms.wells.web.contract.risk.dvo.WcteSellLmOjIzDvo;
import com.kyowon.sms.wells.web.contract.risk.mapper.WcteSalesLimitMapper;
import com.sds.sflex.common.common.dvo.ExcelMetaDvo;
import com.sds.sflex.common.common.service.ExcelReadService;
import com.sds.sflex.common.uifw.service.MessageResourceService;
import com.sds.sflex.system.config.constant.CommConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import com.sds.sflex.system.config.exception.BizException;
import com.sds.sflex.system.config.validation.BizAssert;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WcteSalesLimitService {

    private final WcteSalesLimitMapper mapper;
    private final WcteSalesLimitConverter converter;
    private final MessageResourceService messageResourceService;
    private final ExcelReadService excelReadService;

    public PagingResult<SearchEntrpJLmOjRes> getEntrepreneurJoinLmOjssPages(
        SearchEntrpJLmOjReq dto, PageInfo pageInfo
    ) {
        return mapper.selectEntrepreneurJoinLmOjss(dto, pageInfo);
    }

    public List<SearchEntrpJLmOjRes> getEntrepreneurJoinLmOjssExcelDownload(SearchEntrpJLmOjReq dto) {
        return mapper.selectEntrepreneurJoinLmOjss(dto);
    }

    @Transactional
    public int saveEntrepreneurJoinLmOjss(List<SaveEntrpJLmOjReq> dtos) {
        int processCount = 0;
        Iterator<SaveEntrpJLmOjReq> iterator = dtos.iterator();

        while (iterator.hasNext()) {
            SaveEntrpJLmOjReq dto = iterator.next();
            WcteSellLmOjIzDvo dvo = converter.mapSaveEntrpJLmOjReqToDvo(dto);
            String sellLmDv = dvo.getSellLmDv();
            String sellLmRlsDtm = dvo.getSellLmRlsDtm(); //해제일자
            String sellLmOcDtm = dvo.getSellLmOcDtm(); //발생일자
            String[] param = {dvo.getDataRow()};

            processCount += switch (dvo.getRowState()) {
                case CommConst.ROW_STATE_UPDATED -> {
                    if ("3".equals(sellLmDv))
                        BizAssert.isNull(sellLmRlsDtm, "MSG_ALT_BAD_RLS_ERR", param);

                    if ("4".equals(sellLmDv))
                        BizAssert.hasText(sellLmRlsDtm, "MSG_ALT_RLS_DT_ERR", param);

                    String sellLmBzrno = mapper.selectEntrepreneurJoinLmOjssCheck(dvo.getSellLmId());
                    BizAssert.isTrue(dvo.getSellLmBzrno().equals(sellLmBzrno), "MSG_ALT_LM_BZRNO_ERR");

                    int result = mapper.updateEntrepreneurJoinLmOjss(dvo);
                    BizAssert.isTrue(result == 1, "MSG_ALT_SVE_ERR");

                    yield result;
                }
                case CommConst.ROW_STATE_CREATED -> {
                    if ("3".equals(sellLmDv))
                        BizAssert.isNull(sellLmRlsDtm, "MSG_ALT_RLS_DT_ERR", param);

                    BizAssert.isTrue(sellLmOcDtm.length() == 8, "MSG_ALT_BAD_OC_DT_ERR");

                    int result = mapper.insertEntrepreneurJoinLmOjss(dvo);
                    BizAssert.isTrue(result == 1, "MSG_ALT_SVE_ERR");

                    yield result;
                }
                default -> throw new BizException("MSG_ALT_UNHANDLE_ROWSTATE");
            };
        }
        return processCount;
    }

    @Transactional
    public int removeEntrepreneurJoinLmOjss(List<String> sellLmIds) {
        int processCount = 0;

        for (String sellLmId : sellLmIds) {
            int result = mapper.deleteEntrepreneurJoinLmOjss(sellLmId);

            BizAssert.isTrue(result == 1, "MSG_ALT_SVE_ERR");
            processCount += result;
        }
        return processCount;
    }

    @Transactional
    public List<SaveEntrpJLmOjReq> saveEntrepreneurForExcelUpload(MultipartFile file) throws Exception {
        Map<String, String> headerTitle = new LinkedHashMap<>();
        headerTitle.put("sellLmDv", messageResourceService.getMessage("MSG_TXT_INF_CLS"));
        headerTitle.put("sellLmBzrno", messageResourceService.getMessage("MSG_TXT_ENTRP_NO"));
        headerTitle.put("sellLmRsonCd", messageResourceService.getMessage("MSG_TXT_DFT_CD"));
        headerTitle.put("sellLmOcDtm", messageResourceService.getMessage("MSG_TXT_OCCUR_DATE"));
        headerTitle.put("sellLmRlsDtm", messageResourceService.getMessage("MSG_TXT_CNC_DT"));
        headerTitle.put("sellLmRson", messageResourceService.getMessage("MSG_TXT_OCC_RSN"));
        headerTitle.put("sellLmPsicNm", messageResourceService.getMessage("MSG_TXT_RGST_ICHR"));
        headerTitle.put("sellLmRlsPsicNm", messageResourceService.getMessage("MSG_TXT_CNC_INCHR"));

        // 업로드 엑셀 파일 DRM 복호화
        List<WcteSellLmOjIzDvo> dvos = excelReadService.readExcel(file, new ExcelMetaDvo(1), WcteSellLmOjIzDvo.class);
        List<SaveEntrpJLmOjReq> result = new LinkedList<>();

        int processCount = 0;
        int row = 1;
        for (WcteSellLmOjIzDvo dvo : dvos) {
            result.add(converter.mapSaveEntrpJLmOjReqToDvoToSaveEntrpJLmOjReq(dvo));
        }
        System.out.println(result.toString());
        // 업로드 엑셀 파일 DRM 복호화
        Map<String, String> kvForValidation;
        for (WcteSellLmOjIzDvo req : dvos) {
            kvForValidation = Map.of(
                "sellLmDv", req.getSellLmDv(),
                "sellLmBzrno", req.getSellLmBzrno(),
                "sellLmRsonCd", req.getSellLmRsonCd(),
                "sellLmOcDtm", req.getSellLmOcDtm(),
                "sellLmRsonCd", req.getSellLmRsonCd(),
                "sellLmOcDtm", req.getSellLmOcDtm(),
                "sellLmRlsDtm", req.getSellLmRlsDtm(),
                "sellLmRson", req.getSellLmRson(),
                "sellLmPsicNm", req.getSellLmPsicNm(),
                "sellLmRlsPsicNm", req.getSellLmRlsPsicNm()
            );

            // 유효성검사
            for (String key : kvForValidation.keySet()) {
                BizAssert.hasText(
                    kvForValidation.get(key), "MSG_ALT_INVALID_UPLOAD_DATA",
                    new String[] {String.valueOf(row), headerTitle.get(key), kvForValidation.get(key)}
                );
            }
        }
        return result;
    }
}
