package com.kyowon.sms.wells.web.service.allocate.converter;

import com.kyowon.sms.wells.web.service.allocate.dvo.WsncRpbLocaraZipNoDvo;
import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.service.allocate.dto.WsncRpbLocaraZipMngtDto;

/**
 *
 * <pre>
 * W-SV-U-0036M01 책임지역 우편번호 관리
 * </pre>
 *
 * @author gs.piit130 김혜원
 * @since 2022.11.17
 */
@Mapper(componentModel = "spring")
public interface WsncRpbLocaraZipMngtConverter {

    WsncRpbLocaraZipNoDvo mapCreateReqToWsncRpbLocaraZipNoDvo(WsncRpbLocaraZipMngtDto.CreateReq dto);

}
