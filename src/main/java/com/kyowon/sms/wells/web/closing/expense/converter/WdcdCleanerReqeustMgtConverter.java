package com.kyowon.sms.wells.web.closing.expense.converter;

import com.kyowon.sms.wells.web.closing.expense.dto.WdcdCleanerReqeustMgtDto.FindRes;
import com.kyowon.sms.wells.web.closing.expense.dto.WdcdCleanerReqeustMgtDto.SaveReq;
import com.kyowon.sms.wells.web.closing.expense.dvo.WdcdCleanerReqeustDvo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WdcdCleanerReqeustMgtConverter {

    WdcdCleanerReqeustDvo mapSaveReqToWdcdCleanerReqeustDvo(SaveReq req);

    FindRes mapFindResToWdcdCleanerReqeustDvo(WdcdCleanerReqeustDvo req);
}
