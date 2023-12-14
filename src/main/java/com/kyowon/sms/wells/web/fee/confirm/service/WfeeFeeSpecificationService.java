package com.kyowon.sms.wells.web.fee.confirm.service;

import static com.kyowon.sms.wells.web.fee.confirm.dto.WfeeFeeSpecificationDto.SearchFeeCdRes;
import static com.kyowon.sms.wells.web.fee.confirm.dto.WfeeFeeSpecificationDto.SearchReq;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.kyowon.sms.wells.web.fee.confirm.mapper.WfeeFeeSpecificationMapper;
import com.sds.sflex.common.utils.StringUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WfeeFeeSpecificationService {

    private final WfeeFeeSpecificationMapper mapper;

    public List<SearchFeeCdRes> getFeeCodes(SearchReq dto) {
        return mapper.selectFeeCodes(dto);
    }

    //수수료지급명세서 리스트
    public List<HashMap<String, Object>> getFeeSpecifications(SearchReq dto) {
        List<HashMap<String, Object>> resList = new ArrayList<>();

        //수수료 항목들 가져옴
        Map<String, Object> feeCdMap = getFeeCdLists(dto);
        if (StringUtils.isNotEmpty(String.valueOf(feeCdMap.get("feeCdCase")))) {
            switch (dto.rsbDvCd()) {
                case "W0105": // P 플래너
                    resList = mapper.selectPPlannerFeeSpecifications(
                        dto, String.valueOf(feeCdMap.get("feeCdCase")),
                        String.valueOf(feeCdMap.get("feeCdFields")),
                        String.valueOf(feeCdMap.get("feeSumField")),
                        String.valueOf(feeCdMap.get("feeEachSumField"))
                    );
                    break;
                case "W0104": // P 지점장
                    resList = mapper.selectPManagerFeeSpecifications(
                        dto, String.valueOf(feeCdMap.get("feeCdCase")),
                        String.valueOf(feeCdMap.get("feeCdFields")),
                        String.valueOf(feeCdMap.get("feeSumField")),
                        String.valueOf(feeCdMap.get("feeEachSumField"))
                    );
                    break;
                case "W0205": // M 플래너
                    resList = mapper.selectMPlannerFeeSpecifications(
                        dto, String.valueOf(feeCdMap.get("feeCdCase")),
                        String.valueOf(feeCdMap.get("feeCdFields")),
                        String.valueOf(feeCdMap.get("feeSumField")),
                        String.valueOf(feeCdMap.get("feeEachSumField"))
                    );
                    break;
                case "W0204": // M 지점장
                    resList = mapper.selectMManagerFeeSpecifications(
                        dto, String.valueOf(feeCdMap.get("feeCdCase")),
                        String.valueOf(feeCdMap.get("feeCdFields")),
                        String.valueOf(feeCdMap.get("feeSumField")),
                        String.valueOf(feeCdMap.get("feeEachSumField"))
                    );
                    break;
                case "W0302": // 홈마스터 - 홈마스터
                    resList = mapper.selectHPlannerFeeSpecifications(
                        dto, String.valueOf(feeCdMap.get("feeCdCase")),
                        String.valueOf(feeCdMap.get("feeCdFields")),
                        String.valueOf(feeCdMap.get("feeSumField")),
                        String.valueOf(feeCdMap.get("feeEachSumField"))
                    );
                    break;
                case "W0301": // 홈마스터 - 지점장
                    resList = mapper.selectHManagerFeeSpecifications(
                        dto, String.valueOf(feeCdMap.get("feeCdCase")),
                        String.valueOf(feeCdMap.get("feeCdFields")),
                        String.valueOf(feeCdMap.get("feeSumField")),
                        String.valueOf(feeCdMap.get("feeEachSumField"))
                    );
                    break;
            }
        }
        return resList;
    }

    // 리포트 생성
    public Map<String, Object> getFeeSpecificationsReport(SearchReq dto) {

        // #####   메인 데이터 가져옴
        List<HashMap<String, Object>> resList = getFeeSpecifications(dto);

        // 공제 , 실적 부분 header 명칭설정
        HashMap<String, String> ddtnPerfMap = returnDdtnPerfMap(dto.rsbDvCd());

        // 수수료 코드 리스트 가져옴
        List<SearchFeeCdRes> feeLists = mapper.selectFeeCodes(dto);
        HashMap<String, String> feeCodeMap = new HashMap<>();

        for (SearchFeeCdRes _obj : feeLists) {
            feeCodeMap.put(_obj.feeCd(), _obj.feeNm());
        }

        HashMap<String, String> newHMap = new HashMap<>(); //헤더
        List<Map<String, String>> newDMapList = new ArrayList<>(); //데이터

        for (HashMap<String, Object> dataObj : resList) {
            // body 부분 data key-명칭 매핑
            newDMapList.add(replaceKeyName(dataObj, null));
            if (ObjectUtils.isEmpty(newHMap)) {
                // header 부분 data key-명칭 매핑
                newHMap = replaceKeyName(dataObj, feeCodeMap);
                newHMap.putAll(ddtnPerfMap);
            }
        }

        Map<String, Object> returnMap = new HashMap();
        returnMap.put("AKSDYM", dto.perfDt());
        returnMap.put("AKSDTY", dto.perfDt().substring(0, 4));
        returnMap.put("AKSDTM", dto.perfDt().substring(4, 6));
        returnMap.put("jsonheader", newHMap);
        returnMap.put("jsondata", newDMapList);

        return returnMap;
    }

    public Map<String, Object> getFeeCdLists(SearchReq dto) {

        List<SearchFeeCdRes> feeLists = mapper.selectFeeCodes(dto);

        String feeCdCase = feeLists.stream()
            .map(
                item -> " SUM(CASE WHEN DI.FEE_CD = '" + item.feeCd() + "' THEN DI.FEE_ATC_VAL ELSE 0 END ) AS "
                    + item.feeCd()
            )
            .collect(Collectors.joining(","));
        String feeCdFields = feeLists.stream().map(item -> " NVL(" + item.feeCd() + ",0) AS FEE_" + item.feeCd())
            .collect(Collectors.joining(","));
        String feeSumField = feeLists.stream().map(item -> " NVL(" + item.feeCd() + ",0) ")
            .collect(Collectors.joining("+")) + " AS FEE_VAL0 ";

        String feeEachSumField = "";
        //합계일때 (리포트) - 각 수수료 항목별 전체 합계
        if ("Y".equals(dto.isSum())) {
            feeEachSumField = feeLists.stream().map(item -> " SUM(FEE_" + item.feeCd() + ") AS FEE_" + item.feeCd())
                .collect(Collectors.joining(","));
        }

        Map<String, Object> returnMap = new HashMap<String, Object>();
        returnMap.put("feeCdCase", feeCdCase);
        returnMap.put("feeCdFields", feeCdFields);
        returnMap.put("feeSumField", feeSumField);
        returnMap.put("feeCdList", feeLists);
        returnMap.put("feeEachSumField", feeEachSumField);

        return returnMap;
    }

    // Map 의 key 이름을 리포트 항목의 key 값으로 변환
    public HashMap<String, String> replaceKeyName(
        HashMap<String, Object> paramMap, HashMap<String, String> feeCodeMap
    ) {
        HashMap<String, String> newHMap = new HashMap<>();
        //oz 헤더의 항목에 맞게 변환
        AtomicInteger f = new AtomicInteger(1);

        paramMap.forEach((key, value) -> {
            String val = String.valueOf(value);

            // 컬럼명이 fee 로 시작하거나 합계( ~Val0 ) 가 아닐경우
            if (key.startsWith("fee") && !key.endsWith("Val0")) {
                if (!ObjectUtils.isEmpty(feeCodeMap)) {
                    String str = key;
                    str = key.replaceAll("^(?:fee)(\\w+)$", "$1");
                    val = feeCodeMap.get(str);
                }
                newHMap.put("feeVal" + f, val);
                f.getAndIncrement();
            } else
                newHMap.put(key, val);
        });
        // Map 의 key 이름을  49개까지 채움 (REPORT 에서 고정으로 생성되어있음)
        for (int j = 1; j < 50; j++) {
            if (StringUtil.isEmpty(newHMap.get("ddtnVal" + j)))
                newHMap.put("ddtnVal" + j, "");
            if (StringUtil.isEmpty(newHMap.get("feeVal" + j)))
                newHMap.put("feeVal" + j, "");
            if (StringUtil.isEmpty(newHMap.get("perfVal" + j)))
                newHMap.put("perfVal" + j, "");
        }
        return newHMap;
    }

    // 실적 , 공제 부분 헤더변수/명칭 가져옴
    public HashMap<String, String> returnDdtnPerfMap(String rsbDvCd) {
        HashMap<String, String> ddtnPerfMap = new HashMap<>();
        if ("W0205".equals(rsbDvCd)) { // M 플래너
            ddtnPerfMap.put("perfVal1", "가전인정건수");
            ddtnPerfMap.put("perfVal2", "가전기변건수");

            ddtnPerfMap.put("ddtnVal1", "소득세");
            ddtnPerfMap.put("ddtnVal2", "주민세");
            ddtnPerfMap.put("ddtnVal3", "보증예치금");
            ddtnPerfMap.put("ddtnVal4", "가지급금");
            ddtnPerfMap.put("ddtnVal5", "부담공제");
            ddtnPerfMap.put("ddtnVal6", "고용보험");
            ddtnPerfMap.put("ddtnVal7", "산재보험");
        } else if ("W0105".equals(rsbDvCd)) { //P 플래너
            ddtnPerfMap.put("perfVal1", "가전실적");
            ddtnPerfMap.put("perfVal2", "가전외실적");
            ddtnPerfMap.put("perfVal3", "가전 합산");
            ddtnPerfMap.put("perfVal4", "기변실적");

            ddtnPerfMap.put("ddtnVal1", "소득세");
            ddtnPerfMap.put("ddtnVal2", "주민세");
            ddtnPerfMap.put("ddtnVal3", "보증예치금");
            ddtnPerfMap.put("ddtnVal4", "가지급금");
            ddtnPerfMap.put("ddtnVal5", "부담공제");
            ddtnPerfMap.put("ddtnVal6", "고용보험");
            ddtnPerfMap.put("ddtnVal7", "산재보험");
        } else if ("W0204".equals(rsbDvCd)) { // M지점장
            ddtnPerfMap.put("perfVal1", "조직가전인정건수");
            ddtnPerfMap.put("perfVal2", "가전인정건수");
            ddtnPerfMap.put("perfVal3", "기변실적");

            ddtnPerfMap.put("ddtnVal1", "소득세");
            ddtnPerfMap.put("ddtnVal2", "주민세");
            ddtnPerfMap.put("ddtnVal3", "보증예치금");
            ddtnPerfMap.put("ddtnVal4", "가지급금");
            ddtnPerfMap.put("ddtnVal5", "부담공제");
            ddtnPerfMap.put("ddtnVal6", "할인판매");
            ddtnPerfMap.put("ddtnVal7", "고용보험");
            ddtnPerfMap.put("ddtnVal8", "산재보험");
        } else if ("W0204".equals(rsbDvCd)) { // P지점장
            ddtnPerfMap.put("perfVal1", "조직가전인정건수");
            ddtnPerfMap.put("perfVal2", "가전가전외인정건수");
            ddtnPerfMap.put("perfVal3", "조직합산");

            ddtnPerfMap.put("perfVal4", "개인가전실적");
            ddtnPerfMap.put("perfVal5", "개인가전외실적");
            ddtnPerfMap.put("perfVal6", "개인합산");
            ddtnPerfMap.put("perfVal7", "개인기변실적");

            ddtnPerfMap.put("ddtnVal1", "소득세");
            ddtnPerfMap.put("ddtnVal2", "주민세");
            ddtnPerfMap.put("ddtnVal3", "보증예치금");
            ddtnPerfMap.put("ddtnVal4", "가지급금");
            ddtnPerfMap.put("ddtnVal5", "부담공제");
            ddtnPerfMap.put("ddtnVal7", "고용보험");
            ddtnPerfMap.put("ddtnVal8", "산재보험");
        } else if ("W0302".equals(rsbDvCd)) { // 홈마스터 플래너
            ddtnPerfMap.put("perfVal1", "가전인정건수");
            ddtnPerfMap.put("perfVal2", "서비스건수");

            ddtnPerfMap.put("ddtnVal1", "보증예치금");
            ddtnPerfMap.put("ddtnVal2", "소득세");
            ddtnPerfMap.put("ddtnVal3", "주민세");
            ddtnPerfMap.put("ddtnVal4", "고용보험");
            ddtnPerfMap.put("ddtnVal5", "가지급금");
            ddtnPerfMap.put("ddtnVal6", "부담공제");
            ddtnPerfMap.put("ddtnVal7", "기타공제");
            ddtnPerfMap.put("ddtnVal8", "산재보험");
        } else if ("W0301".equals(rsbDvCd)) { // 홈마스터 지점장
            ddtnPerfMap.put("perfVal1", "가전인정건수");
            ddtnPerfMap.put("perfVal2", "서비스건수");
            ddtnPerfMap.put("perfVal3", "조직인정건수");
            ddtnPerfMap.put("perfVal4", "조직서비스건수");

            ddtnPerfMap.put("ddtnVal1", "보증예치금");
            ddtnPerfMap.put("ddtnVal2", "소득세");
            ddtnPerfMap.put("ddtnVal3", "주민세");
            ddtnPerfMap.put("ddtnVal4", "고용보험");
            ddtnPerfMap.put("ddtnVal5", "가지급금");
            ddtnPerfMap.put("ddtnVal6", "부담공제");
            ddtnPerfMap.put("ddtnVal7", "기타공제");
            ddtnPerfMap.put("ddtnVal8", "산재보험");
        }
        return ddtnPerfMap;
    }

}
