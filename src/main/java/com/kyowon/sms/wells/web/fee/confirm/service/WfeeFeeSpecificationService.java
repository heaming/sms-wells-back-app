package com.kyowon.sms.wells.web.fee.confirm.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.sds.sflex.system.config.constant.CommConst;
import com.sds.sflex.system.config.exception.BizException;
import com.sds.sflex.system.config.validation.BizAssert;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.fee.confirm.mapper.WfeeFeeSpecificationMapper;

import static com.kyowon.sms.wells.web.fee.confirm.dto.WfeeFeeSpecificationDto.*;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WfeeFeeSpecificationService {

    private final WfeeFeeSpecificationMapper mapper;

    public List<SearchFeeCdRes> getFeeCodes(SearchReq dto) {
        return mapper.selectFeeCodes(dto);
    }

    //M추진단 / 플래너
    public List<?> getFeeSpecifications(SearchReq dto) {
        List<?> resList = new ArrayList<>();

        //수수료 항목들 가져옴
        Map<String, Object> feeCdMap = getFeeCdLists(dto);
        if (StringUtils.isNotEmpty(String.valueOf(feeCdMap.get("feeCdCase")))) {
            switch (dto.rsbDvCd()) {
                case "W0105": // P 플래너
                    resList = mapper.selectPPlannerFeeSpecifications(
                        dto, String.valueOf(feeCdMap.get("feeCdCase")),
                        String.valueOf(feeCdMap.get("feeCdFields")),
                        String.valueOf(feeCdMap.get("feeSumField"))
                    );
                    break;
                case "W0104": // P 지점장
                    resList = mapper.selectPManagerFeeSpecifications(
                        dto, String.valueOf(feeCdMap.get("feeCdCase")),
                        String.valueOf(feeCdMap.get("feeCdFields")),
                        String.valueOf(feeCdMap.get("feeSumField"))
                    );
                    break;
                case "W0205": // M 플래너
                    resList = mapper.selectMPlannerFeeSpecifications(
                        dto, String.valueOf(feeCdMap.get("feeCdCase")),
                        String.valueOf(feeCdMap.get("feeCdFields")),
                        String.valueOf(feeCdMap.get("feeSumField"))
                    );
                    break;
                case "W0204": // M 지점장
                    resList = mapper.selectMManagerFeeSpecifications(
                        dto, String.valueOf(feeCdMap.get("feeCdCase")),
                        String.valueOf(feeCdMap.get("feeCdFields")),
                        String.valueOf(feeCdMap.get("feeSumField"))
                    );
                    break;
                case "W0302": // 홈마스터 - 홈마스터
                    resList = mapper.selectHPlannerFeeSpecifications(
                        dto, String.valueOf(feeCdMap.get("feeCdCase")),
                        String.valueOf(feeCdMap.get("feeCdFields")),
                        String.valueOf(feeCdMap.get("feeSumField"))
                    );
                    break;
                case "W0301": // 홈마스터 - 지점장
                    resList = mapper.selectHManagerFeeSpecifications(
                        dto, String.valueOf(feeCdMap.get("feeCdCase")),
                        String.valueOf(feeCdMap.get("feeCdFields")),
                        String.valueOf(feeCdMap.get("feeSumField"))
                    );
                    break;

            }
        }
        return resList;
    }

    public Map<String, Object> getFeeCdLists(SearchReq dto) {

        List<SearchFeeCdRes> feeLists = mapper.selectFeeCodes(dto);

        String feeCdCase = feeLists.stream()
            .map(
                item -> " SUM(CASE WHEN DI.FEE_CD = '" + item.feeCd() + "' THEN DI.FEE_ATC_VAL ELSE 0 END ) AS "
                    + item.feeCd()
            )
            .collect(Collectors.joining(","));
        String feeCdFields = feeLists.stream().map(item -> " NVL(" + item.feeCd() + ",0) AS " + item.feeCd())
            .collect(Collectors.joining(","));
        String feeSumField = feeLists.stream().map(item -> " NVL(" + item.feeCd() + ",0) ")
            .collect(Collectors.joining("+")) + " AS FEE_SUM ";
        Map<String, Object> returnMap = new HashMap<String, Object>();
        returnMap.put("feeCdCase", feeCdCase);
        returnMap.put("feeCdFields", feeCdFields);
        returnMap.put("feeSumField", feeSumField);
        returnMap.put("feeCdList", feeLists);

        return returnMap;
    }

}
