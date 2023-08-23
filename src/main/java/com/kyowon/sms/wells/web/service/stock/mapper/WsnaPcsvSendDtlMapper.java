package com.kyowon.sms.wells.web.service.stock.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.stock.dvo.WsnaPcsvSendDtlDvo;

@Mapper
public interface WsnaPcsvSendDtlMapper {

    String selectOstAkNo();

    int insertPcsvSendDtl(WsnaPcsvSendDtlDvo vo);
}
