package com.kyowon.sms.wells.web.closing.interfaces.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.closing.interfaces.dto.WdccHomeCareBreachPromiseAmtInterfaceDto.FindReq;
import com.kyowon.sms.wells.web.closing.interfaces.dto.WdccHomeCareBreachPromiseAmtInterfaceDto.FindRes;

@Mapper
public interface WdccHomeCareBreachPromiseAmtInterfaceMapper {
    FindRes selectHomeCareBreachPromiseAmt(FindReq req);
}
