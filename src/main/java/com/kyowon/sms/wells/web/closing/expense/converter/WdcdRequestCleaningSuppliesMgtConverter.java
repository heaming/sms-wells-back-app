package com.kyowon.sms.wells.web.closing.expense.converter;

import com.kyowon.sms.wells.web.closing.expense.dto.WdcdRequestCleaningSuppliesMgtDto.SaveReq;
import com.kyowon.sms.wells.web.closing.expense.dvo.WdcdRequestCleaningSuppliesDvo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WdcdRequestCleaningSuppliesMgtConverter {

    WdcdRequestCleaningSuppliesDvo mapSaveReqToWdcdRequestCleaningSuppliesDvo(SaveReq req);
}
