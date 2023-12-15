package com.kyowon.sms.wells.web.service.stock.converter;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaStockAcinspRgstMngtDto.*;

import java.util.List;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.service.stock.dvo.WsnaStockAcinspRgstMngtDvo;

@Mapper(componentModel = "spring")
public interface WsnaStockAcinspRgstMngtConverter {

    List<WsnaStockAcinspRgstMngtDvo> mapAllSaveReqToWsnaStockAcinspRgstMngtDvo(List<SaveReq> dtos);

    List<WsnaStockAcinspRgstMngtDvo> mapAllSaveAcinspReqToWsnaStockAcinspRgstMngtDvo(List<SaveAcinspReq> dtos);

    List<WsnaStockAcinspRgstMngtDvo> mapAllSaveAcinspCnfmReqToWsnaStockAcinspRgstMngtDvo(List<SaveAcinspCnfmReq> dtos);

    List<WsnaStockAcinspRgstMngtDvo> mapAllDeleteAcinspReqToWsnaStockAcinspRgstMngtDvo(List<SaveCancelReq> dtos);

    List<WsnaStockAcinspRgstMngtDvo> mapAllDeleteApplAcinspReqToWsnaStockAcinspRgstMngtDvo(List<RemoveReq> dtos);

}
