package com.kyowon.sms.wells.web.withdrawal.idvrve.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbRefundApplicationPresentStateDto.SearchRefundApplicationPresentStateReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbRefundApplicationPresentStateDto.SearchRefundApplicationPresentStateRes;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WwdbRefundApplicationPresentStateMapper {

    PagingResult<SearchRefundApplicationPresentStateRes> selectRefundApplicationPresentState(
        SearchRefundApplicationPresentStateReq req,
        PageInfo pageInfo
    );

    List<SearchRefundApplicationPresentStateRes> selectRefundApplicationPresentState(
        SearchRefundApplicationPresentStateReq req
    );

}
