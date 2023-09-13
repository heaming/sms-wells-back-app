package com.kyowon.sms.wells.web.service.visit.mapper;

import com.kyowon.sms.wells.web.service.visit.dto.WsnbInstallationYOneAcuAfSvRtDto.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WsnbInstallationYOneAcuAfSvRtMapper {
    List<SearchRes> selectInstallationYOneAcuAfSvRtInfos(SearchReq searchReq);
}
