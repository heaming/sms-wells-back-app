package com.kyowon.sms.wells.web.fee.simulation.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import static com.kyowon.sms.wells.web.fee.simulation.dto.WfefFeeDeductionPresentStateDto.*;

import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WfefFeeDeductionPresentStateMapper {

    PagingResult<SearchRes> selectFeeDeductionPresentStatePages(
        SearchReq dto,
        PageInfo pageInfo
    );

    List<SearchRes> selectFeeDeductionPresentStatePages(
        SearchReq dto
    );
}
