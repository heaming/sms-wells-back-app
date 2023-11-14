package com.kyowon.sms.wells.web.service.visit.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.visit.dto.WsnbTotAsRtSdingPsDto.*;

import java.util.List;

@Mapper
public interface WsnbTotAsRtSdingPsMapper {
    List<SearchRes> selectTotAsRtSdingPss(SearchReq dto);
    List<SdingPackageRes> selectSdingPackages();
    List<SdingDtlRes> selectSdingDtlInfos(SdingDtlReq dtlDto);
}
