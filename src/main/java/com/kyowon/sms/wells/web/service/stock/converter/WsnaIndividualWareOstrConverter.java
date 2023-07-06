package com.kyowon.sms.wells.web.service.stock.converter;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaIndividualWareOstrDto.CreateReq;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaIndividualWareOstrDvo;
import org.mapstruct.Mapper;

import java.util.List;

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
    List<WsnaIndividualWareOstrDvo> mapAllCreateReqToIndividualDvo(List<CreateReq> list);
}
