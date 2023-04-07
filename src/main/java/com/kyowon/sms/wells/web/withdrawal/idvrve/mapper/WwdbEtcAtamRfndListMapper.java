package com.kyowon.sms.wells.web.withdrawal.idvrve.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbEtcAtamRfndListDto.SearchEtcAtamRfndListAgrgReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbEtcAtamRfndListDto.SearchEtcAtamRfndListAgrgRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbEtcAtamRfndListDto.SearchEtcAtamRfndListReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbEtcAtamRfndListDto.SearchEtcAtamRfndListRes;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WwdbEtcAtamRfndListMapper {

    PagingResult<SearchEtcAtamRfndListRes> selectEtcAtamRfndList(
        SearchEtcAtamRfndListReq req,
        PageInfo pageInfo
    );

    List<SearchEtcAtamRfndListRes> selectEtcAtamRfndList(
        SearchEtcAtamRfndListReq req
    );

    SearchEtcAtamRfndListAgrgRes selectEtcAtamRfndListAgrg(
        SearchEtcAtamRfndListAgrgReq req
    );

}
