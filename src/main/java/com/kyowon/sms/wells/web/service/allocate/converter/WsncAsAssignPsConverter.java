package com.kyowon.sms.wells.web.service.allocate.converter;

import com.kyowon.sms.wells.web.service.allocate.dto.WsncAsAssignPsDto;
import com.kyowon.sms.wells.web.service.allocate.dvo.WsncAsAssProductServicesDvo;
import com.kyowon.sms.wells.web.service.allocate.dvo.WsncAsAssTotalCustomerDvo;
import org.mapstruct.Mapper;
import java.util.List;

/**
 *
 * <pre>
 * W-SV-U-0228M01 총 관리고객 집계
 * </pre>
 *
 * @author gs.piit122 김동엽
 * @since 2022-12-06
 */
@Mapper(componentModel = "spring")
public interface WsncAsAssignPsConverter {
    List<WsncAsAssignPsDto.SearchTotalCustomerRes> mapAllTotalCustomersDvoToRes(List<WsncAsAssTotalCustomerDvo> dvos);

    List<WsncAsAssignPsDto.SearchProductServicesRes> mapAllDvoToRes(List<WsncAsAssProductServicesDvo> dvos);

//    WsncAsAssignPsDto.SearchProductServicesRes mapDvoToLRes(WsncProductServicesDvo dvo);
}
