package com.kyowon.sms.wells.web.service.visit.mapper;

import com.kyowon.sms.wells.web.service.visit.dvo.WsnbCallingLogDvo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WsnbCallingLogSaveMapper {

    int insertCallingLog(WsnbCallingLogDvo dvo);
}
