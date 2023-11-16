package com.kyowon.sms.wells.web.fee.simulation.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.fee.simulation.dto.WfefSellFeeEtPerfDto.SearchCanNincRes;
import com.kyowon.sms.wells.web.fee.simulation.dto.WfefSellFeeEtPerfDto.SearchDetailRes;
import com.kyowon.sms.wells.web.fee.simulation.dto.WfefSellFeeEtPerfDto.SearchReq;
import com.kyowon.sms.wells.web.fee.simulation.dto.WfefSellFeeEtPerfDto.SearchRes;

@Mapper
public interface WfefSellFeeEtPerfMapper {

    List<SearchRes> selectSellFeeEtPerfs(SearchReq dto);

    List<SearchDetailRes> selectSellFeeEtPerfDetails(SearchReq dto);

    List<SearchCanNincRes> selectSellFeeEtPerfCans(SearchReq dto);

    List<SearchCanNincRes> selectSellFeeEtPerfNincs(SearchReq dto);
}
