package com.kyowon.sms.wells.web.service.interfaces.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.interfaces.dto.WsniParcelServiceRegDto;
import com.kyowon.sms.wells.web.service.interfaces.dvo.WsniParcelServiceRegDvo;

@Mapper
public interface WsniParcelServiceMapper {

    List<WsniParcelServiceRegDto.SearchOriginInvoiceRes> selectOriginInvoiceNum(
        WsniParcelServiceRegDto.RegistParcelServiceReq dto
    );

    String selectParcelServiceCertKey();

    int insertParcelServiceCertKey(String tokkenKey);

    int insertParcelServiceReg(WsniParcelServiceRegDvo dvo);

}
