package com.kyowon.sms.wells.web.service.stock.converter;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaSeedingOstrQtyDto.EditReq;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.kyowon.sms.wells.web.service.stock.dvo.WsnaSeedingOstrQtyDvo;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaSeedingOstrQtyExcelDvo;

@Mapper(componentModel = "spring", imports = {java.math.BigDecimal.class})
public interface WsnaSeedingOstrQtyConverter {

    WsnaSeedingOstrQtyDvo mapEditReqToWsnaSeedingOstrQtyDvo(EditReq dto);

    @Mapping(target = "limQty", expression = "java(new BigDecimal(dvo.getLimQty()))")
    WsnaSeedingOstrQtyDvo mapWsnaSeedingOstrQtyExcelDvoToWsnaSeedingOstrQtyDvo(WsnaSeedingOstrQtyExcelDvo dvo);
}
