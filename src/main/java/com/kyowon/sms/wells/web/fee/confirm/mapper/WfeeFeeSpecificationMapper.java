package com.kyowon.sms.wells.web.fee.confirm.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import static com.kyowon.sms.wells.web.fee.confirm.dto.WfeeFeeSpecificationDto.*;

@Mapper
public interface WfeeFeeSpecificationMapper {
    List<SearchFeeCdRes> selectFeeCodes(SearchReq dto);

    //M추진단 /플래너
    List<HashMap<String, Object>> selectMPlannerFeeSpecifications(
        SearchReq dto, String feeCdCase // 수수료 계산단위 유형코드
        , String feeCdFields, String feeSumField, String feeEachSumField
    );

    //P추진단 /플래너
    List<HashMap<String, Object>> selectPPlannerFeeSpecifications(
        SearchReq dto, String feeCdCase // 수수료 계산단위 유형코드
        , String feeCdFields, String feeSumField, String feeEachSumField
    );

    //M추진단  / 지점장
    List<HashMap<String, Object>> selectMManagerFeeSpecifications(
        SearchReq dto, String feeCdCase // 수수료 계산단위 유형코드
        , String feeCdFields, String feeSumField, String feeEachSumField
    );

    //P추진단 / 지점장
    List<HashMap<String, Object>> selectPManagerFeeSpecifications(
        SearchReq dto, String feeCdCase // 수수료 계산단위 유형코드
        , String feeCdFields, String feeSumField, String feeEachSumField
    );

    //홈마스터 / 플래너
    List<HashMap<String, Object>> selectHPlannerFeeSpecifications(
        SearchReq dto, String feeCdCase // 수수료 계산단위 유형코드
        , String feeCdFields, String feeSumField, String feeEachSumField
    );

    //홈마스터 / 지점장
    List<HashMap<String, Object>> selectHManagerFeeSpecifications(
        SearchReq dto, String feeCdCase // 수수료 계산단위 유형코드
        , String feeCdFields, String feeSumField, String feeEachSumField
    );
}
