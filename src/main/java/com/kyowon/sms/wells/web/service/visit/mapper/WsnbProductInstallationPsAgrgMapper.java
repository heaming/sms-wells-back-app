package com.kyowon.sms.wells.web.service.visit.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.visit.dto.WsnbProductInstallationPsAgrgDto.SearchReq;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbProductInstallationPsAgrgDto.SearchRes;

@Mapper
public interface WsnbProductInstallationPsAgrgMapper {
    List<SearchRes> selectProductInstallationPsAgrgs(SearchReq dto);
}
