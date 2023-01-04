package com.kyowon.sms.wells.web.service.visit.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.visit.dto.WsnbIstLctDtlMngtDto.FindReq;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbIstLctDtlMngtDto.SearchReq;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbIstLctDtlMngtDto.SearchRes;
import com.kyowon.sms.wells.web.service.visit.dvo.WsnbIstLctDtlDvo;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WsnbIstLctDtlMngtMapper {

    PagingResult<SearchRes> selectIstLocationDetailPages(
        SearchReq dto, PageInfo pageInfo
    );

    List<SearchRes> selectIstLocationDetailPages(
        SearchReq dto
    );

    int insertIstLocationDetail(
        WsnbIstLctDtlDvo dvo
    );

    int insertInitializeIstLocationDetail(
        WsnbIstLctDtlDvo dvo
    );

    String findDtlSn(
        FindReq dto
    );

    int findIstLctDtlSnLength(
        Map<String, Object> map
    );
}
