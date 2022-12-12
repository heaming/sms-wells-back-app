package com.kyowon.sms.wells.web.service.orgcode.converter;

import com.kyowon.sms.wells.web.service.orgcode.dto.WsndWorkNoticeDto;
import com.kyowon.sms.wells.web.service.orgcode.dvo.WsndWorkNoticeDvo;
import org.apache.commons.lang.StringUtils;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", imports = {StringUtils.class})
public interface WsndWorkNoticeConverter {
    WsndWorkNoticeDvo mapCreateReqToWorkNoticeDvo(WsndWorkNoticeDto.CreateReq dto);

    WsndWorkNoticeDvo mapEditReqToWorkNoticeDvo(WsndWorkNoticeDto.EditReq dto);
}
