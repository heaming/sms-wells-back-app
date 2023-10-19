package com.kyowon.sms.wells.web.fee.confirm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.fee.confirm.dto.WfeeIndividualFeeHomeMasterDto.*;

@Mapper
public interface WfeeIndividualFeeHomeMasterMapper {
    List<SearchHmstRes> selectIndividualPerformanceHmstDetails(SearchHmstReq dto);

    FindHmstRes selectHmst(SearchHmstReq dto);

    List<SearchHmstEtcRes> selectHmstEtcs(SearchHmstReq dto);

    List<SearchHmstFeeRes> selectHmstFees(SearchHmstReq dto);

    List<FindHmstDeductionRes> selectHmstDeductions(SearchHmstReq dto);

    List<SearchHmstPnpyamRes> selectHmstPnpyams(SearchHmstReq dto);
}
