package com.kyowon.sms.wells.web.withdrawal.idvrve.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbCntramRfndListDto.SearchCntramRfndListAgrgReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbCntramRfndListDto.SearchCntramRfndListAgrgRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbCntramRfndListDto.SearchCntramRfndListReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbCntramRfndListDto.SearchCntramRfndListRes;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WwdbCntramRfndListMapper {

    PagingResult<SearchCntramRfndListRes> selectCntramRfndList(
        SearchCntramRfndListReq req,
        PageInfo pageInfo
    );

    List<SearchCntramRfndListRes> selectCntramRfndList(
        SearchCntramRfndListReq req
    );

    SearchCntramRfndListAgrgRes selectCntramRfndListAgrg(
        SearchCntramRfndListAgrgReq req
    );

}
