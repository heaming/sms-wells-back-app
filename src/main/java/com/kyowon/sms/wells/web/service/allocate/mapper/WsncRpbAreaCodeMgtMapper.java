package com.kyowon.sms.wells.web.service.allocate.mapper;

import static com.kyowon.sms.wells.web.service.allocate.dto.WsncRpbAreaCodeMgtDto.SearchReq;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.allocate.dvo.WsncRpbAreaCodeDvo;

@Mapper
public interface WsncRpbAreaCodeMgtMapper {

    //    PagingResult<WsncRpbAreaCodeDvo> selectAreaCodePages(
    //        SearchReq dto, PageInfo pageInfo
    //    );

    List<WsncRpbAreaCodeDvo> selectAreaCodes(
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
