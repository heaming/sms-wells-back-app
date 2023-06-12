package com.kyowon.sms.wells.web.service.interfaces.mapper;

import com.kyowon.sms.wells.web.service.interfaces.dto.WsniFalseVisitRgstInterfaceDto.SaveReq;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WsniFalseVisitRgstInterfaceMapper {
    int insertFalseVisit(SaveReq dto);
}
