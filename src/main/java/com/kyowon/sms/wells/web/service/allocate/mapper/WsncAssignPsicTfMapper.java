package com.kyowon.sms.wells.web.service.allocate.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.allocate.dvo.WsncAssignPsicTfDvo;

@Mapper
public interface WsncAssignPsicTfMapper {
    List<WsncAssignPsicTfDvo> selectAssignPsicTf(WsncAssignPsicTfDvo dvo);

    int insertAssignPsicTf(WsncAssignPsicTfDvo dvo);

    int updateAssignPsicTfAs(WsncAssignPsicTfDvo dvo);

    int updateAssignPsicTfBs(WsncAssignPsicTfDvo dvo);
}
