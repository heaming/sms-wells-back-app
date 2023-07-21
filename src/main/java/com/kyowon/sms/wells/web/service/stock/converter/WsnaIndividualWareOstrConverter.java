package com.kyowon.sms.wells.web.service.stock.converter;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaIndividualWareOstrDto.SaveReq;

import java.util.List;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.service.stock.dvo.WsnaIndividualWareOstrDvo;

/**
 * <pre>
 * W-SV-U-0192M01 개인창고출고관리
 * </pre>
 *
 * @author songTaeSung
 * @since 2023.02.16
 */
@Mapper(componentModel = "spring")
public interface WsnaIndividualWareOstrConverter {
    List<WsnaIndividualWareOstrDvo> mapAllSaveReqToWsnaIndividualWareOstrDvo(List<SaveReq> list);
}
