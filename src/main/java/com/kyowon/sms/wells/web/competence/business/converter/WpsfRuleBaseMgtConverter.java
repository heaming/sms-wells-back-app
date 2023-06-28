package com.kyowon.sms.wells.web.competence.business.converter;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.competence.business.dto.WpsfRuleBaseMgtDto;
import com.kyowon.sms.wells.web.competence.business.dvo.WpsfRuleBaseDvo;

@Mapper(componentModel = "spring")
public interface WpsfRuleBaseMgtConverter {

    WpsfRuleBaseDvo mapSaveReq(WpsfRuleBaseMgtDto.SaveReq dto);
}
