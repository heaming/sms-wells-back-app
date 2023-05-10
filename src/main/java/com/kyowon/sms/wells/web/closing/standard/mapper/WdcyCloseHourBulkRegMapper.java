package com.kyowon.sms.wells.web.closing.standard.mapper;

import com.kyowon.sms.wells.web.closing.standard.dto.WdcyCloseHourBulkRegDto.CreateReq;
import com.kyowon.sms.wells.web.closing.standard.dvo.WdcyCloseHourBulkRegDvo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WdcyCloseHourBulkRegMapper {

    int insertCloseHour(WdcyCloseHourBulkRegDvo dvo);

    int deleteHourBulk(CreateReq dto);
}
