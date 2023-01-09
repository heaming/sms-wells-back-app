package com.kyowon.sms.wells.web.service.allocate.converter;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.service.allocate.dto.WsncRpbAreaCodeMgtDto.SaveReq;
import com.kyowon.sms.wells.web.service.allocate.dvo.WsncRpbAreaCodeDvo;

/**
 * <pre>
 * W-SV-U-0035M01 책임지역 지역코드 관리
 * </pre>
 *
 * @author gs.piit129 천영화
 * @since 2022.11.22
 */
@Mapper(componentModel = "spring")
public interface WsncRpbAreaCodeMgtConverter {

    WsncRpbAreaCodeDvo mapSaveReqToWsncRpbAreaCodeDvo(SaveReq dto);
}
