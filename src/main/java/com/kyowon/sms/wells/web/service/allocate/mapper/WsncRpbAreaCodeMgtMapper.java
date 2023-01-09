package com.kyowon.sms.wells.web.service.allocate.mapper;

import static com.kyowon.sms.wells.web.service.allocate.dto.WsncRpbAreaCodeMgtDto.SearchReq;
import static com.kyowon.sms.wells.web.service.allocate.dto.WsncRpbAreaCodeMgtDto.SearchRes;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.allocate.dvo.WsncRpbAreaCodeDvo;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WsncRpbAreaCodeMgtMapper {

    PagingResult<SearchRes> selectAreaCodePages(
        SearchReq dto, PageInfo pageInfo
    );

    List<SearchRes> selectAreaCodePages(
        SearchReq dto
    );

    int selectCountAreaCodePsic(
        WsncRpbAreaCodeDvo dvo
    );

    int insertAreaCode(
        WsncRpbAreaCodeDvo dvo
    );

    int insertAreaCodePsic(
        WsncRpbAreaCodeDvo dvo
    );
}
