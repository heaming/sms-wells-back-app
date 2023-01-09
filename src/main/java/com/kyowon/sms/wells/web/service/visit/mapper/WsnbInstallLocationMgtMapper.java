package com.kyowon.sms.wells.web.service.visit.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.visit.dto.WsnbInstallLocationMgtDto.SearchReq;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbInstallLocationMgtDto.SearchRes;
import com.kyowon.sms.wells.web.service.visit.dvo.WsnbInstallLocationDvo;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WsnbInstallLocationMgtMapper {

    PagingResult<SearchRes> selectInstallLocationPages(
        SearchReq dto, PageInfo pageInfo
    );

    List<SearchRes> selectInstallLocationPages(
        SearchReq dto
    );

    int insertInstallLocation(
        WsnbInstallLocationDvo dvo
    );

    int insertInitializeInstallLocation(
        WsnbInstallLocationDvo dvo
    );

    String selectSerialNumberByPk(
        String cntrNo
    );

    int selectInstallLocationContentLength(
        WsnbInstallLocationDvo dvo
    );
}
