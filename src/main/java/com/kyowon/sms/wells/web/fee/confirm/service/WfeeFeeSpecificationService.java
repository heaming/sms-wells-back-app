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
        return mapper.selectFeeCodes(dto.perfDt(), "");
    }

    //M추진단 / 플래너
    public List<?> getFeeSpecifications(SearchReq dto) {
        List<?> resList = new ArrayList<>();
        String rsbDvCd = dto.rsbDvCd();

        Map<String, Object> feeCdMap = new HashMap<>();

        // feeCalcUnitTpCd, // 수수료 계산단위 유형코드
        // 수수료계산단위유형코드 (101 : P추진단 플래너, 102 : P추진단 지국장, 201 : M추진단-일반(15등급), 202 : M추진단- 지점장(7등급)
        switch (dto.ogTpCd()) {
            case "W01" -> { //P 추진단
                if ("1".equals(rsbDvCd)) { // 플래너
                    feeCdMap = getFeeCdLists(dto.perfDt(), "101");
                    if (StringUtils.isNotEmpty(String.valueOf(feeCdMap.get("feeCdInStr"))))
                        resList = mapper.selectPPlannerFeeSpecifications(
                            dto, String.valueOf(feeCdMap.get("feeCdInStr")),
                            String.valueOf(feeCdMap.get("feeCdFields")),
                            String.valueOf(feeCdMap.get("feeCdInStr"))
                        );

                } else if ("2".equals(rsbDvCd)) { //지점장
                    feeCdMap = getFeeCdLists(dto.perfDt(), "102");
                    if (StringUtils.isNotEmpty(String.valueOf(feeCdMap.get("feeCdInStr"))))
                        resList = mapper.selectPManagerFeeSpecifications(
                            dto, String.valueOf(feeCdMap.get("feeCdInStr")),
                            String.valueOf(feeCdMap.get("feeCdFields")),
                            String.valueOf(feeCdMap.get("feeCdInStr"))
                        );
                }
            }
            case "W02" -> { //M 추진단
                if ("1".equals(rsbDvCd)) { //플래너
                    feeCdMap = getFeeCdLists(dto.perfDt(), "201");
                    if (StringUtils.isNotEmpty(String.valueOf(feeCdMap.get("feeCdInStr"))))
                        resList = mapper.selectMPlannerFeeSpecifications(
                            dto, String.valueOf(feeCdMap.get("feeCdInStr")),
                            String.valueOf(feeCdMap.get("feeCdFields")),
                            String.valueOf(feeCdMap.get("feeCdInStr"))
                        );
                } else if ("2".equals(rsbDvCd)) { //지점장
                    feeCdMap = getFeeCdLists(dto.perfDt(), "202");
                    if (StringUtils.isNotEmpty(String.valueOf(feeCdMap.get("feeCdInStr"))))
                        resList = mapper.selectMManagerFeeSpecifications(
                            dto, String.valueOf(feeCdMap.get("feeCdInStr")),
                            String.valueOf(feeCdMap.get("feeCdFields")),
                            String.valueOf(feeCdMap.get("feeCdInStr"))
                        );
                }
            }
            case "W03" -> { //홈마스터
                //TODO : 홈마스터 일 경우 ( 아직 TB_FEAM_FEE_CD에 홈마스터 코드가 없음)
            }
        }

        return resList;
    }

    public Map<String, Object> getFeeCdLists(String perfDt, String feeCalcUnitTpCd) {

        List<SearchFeeCdRes> feeLists = mapper.selectFeeCodes(perfDt, feeCalcUnitTpCd);
        String feeCdInStr = feeLists.stream()
            .map(item -> "'" + item.feeCd() + "' as " + item.feeCd())
            .collect(Collectors.joining(","));
        String feeCdFields = feeLists.stream().map(item -> " NVL(" + item.feeCd() + ",0) AS " + item.feeCd())
            .collect(Collectors.joining(","));
        Map<String, Object> returnMap = new HashMap<String, Object>();
        returnMap.put("feeCdInStr", feeCdInStr);
        returnMap.put("feeCdFields", feeCdFields);
        returnMap.put("feeCdList", feeLists);

        return returnMap;
    }

}
