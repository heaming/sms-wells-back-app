package com.kyowon.sms.wells.web.bond.credit.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.bond.credit.dto.WbndRentalCbMgtDto.SearchTalkReq;
import com.kyowon.sms.wells.web.bond.credit.dto.WbndRentalCbMgtDto.SearchTalkRes;
import com.kyowon.sms.wells.web.bond.credit.dvo.WbndBondContactExcludeIzDvo;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WbndRentalCbMgtMapper {

    PagingResult<SearchTalkRes> selectNotificationTalks(
        SearchTalkReq dto,
        PageInfo pageInfo
    );

    List<SearchTalkRes> selectNotificationTalks(
        SearchTalkReq dto
    );

    int insertNotificationTalk(
        WbndBondContactExcludeIzDvo dvo
    );

    int updateNotificationTalk(
        WbndBondContactExcludeIzDvo dvo
    );

    int deleteNotificationTalk(
        String bndCntcExcdOjId
    );
}
