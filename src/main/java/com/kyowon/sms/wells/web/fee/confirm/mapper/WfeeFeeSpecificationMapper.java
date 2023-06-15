package com.kyowon.sms.wells.web.fee.confirm.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import static com.kyowon.sms.wells.web.fee.confirm.dto.WfeeFeeSpecificationDto.*;

@Mapper
public interface WfeeFeeSpecificationMapper {
    List<SearchFeeCdRes> selectFeeCodes(String perfDt, String feeCalcUnitTpCd);

    //M추진단 /플래너
    List<HashMap<String, Object>> selectMPlannerFeeSpecifications(
        SearchReq dto, String feeCdStr // 수수료 계산단위 유형코드
        , String feeCdFields, String feeSumField, String feeCdInStr
    );

    //P추진단 /플래너
    List<HashMap<String, Object>> selectPPlannerFeeSpecifications(
        SearchReq dto, String feeCdStr // 수수료 계산단위 유형코드
        , String feeCdFields, String feeSumField, String feeCdInStr
    );

    //M추진단  / 지점장
    List<HashMap<String, Object>> selectMManagerFeeSpecifications(
        SearchReq dto, String feeCdStr // 수수료 계산단위 유형코드
        , String feeCdFields, String feeSumField, String feeCdInStr
    );

    //P추진단 / 지점장
    List<HashMap<String, Object>> selectPManagerFeeSpecifications(
        SearchReq dto, String feeCdStr // 수수료 계산단위 유형코드
        , String feeCdFields, String feeSumField, String feeCdInStr
    );

}
