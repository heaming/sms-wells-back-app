package com.kyowon.sms.wells.web.service.standard.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.standard.dvo.WsnyBusinessTypeWorkHourMgtSaveDvo;

@Mapper
public interface WsnyBusinessTypeWorkHourSaveMapper {

    int insertSvpdBpdTpWkGrpIz(WsnyBusinessTypeWorkHourMgtSaveDvo vo);

}
