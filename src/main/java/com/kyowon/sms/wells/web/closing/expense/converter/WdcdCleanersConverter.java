package com.kyowon.sms.wells.web.closing.expense.converter;

import com.kyowon.sms.wells.web.closing.expense.dto.WdcdCleanersMgtDto.SearchRes;
import com.kyowon.sms.wells.web.closing.expense.dvo.WdcdCleanersDvo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WdcdCleanersConverter {

    SearchRes mapSearchResToWdcdCleanersDvo(WdcdCleanersDvo dvo);
}
