package com.kyowon.sms.wells.web.service.allocate.converter;

import java.util.List;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.service.allocate.dto.WsncRpbLocaraCdMngtDto;
import com.kyowon.sms.wells.web.service.allocate.dvo.WsncRpbLocaraCdMngtDvo;

/**
 * <pre>
 * W-SV-U-0035M01 책임지역 지역코드 관리
 * </pre>
 *
 * @author gs.piit129 천영화
 * @since 2022.11.22
 */
@Mapper(componentModel = "spring")
public interface WsncRpbLocaraCdMngtConverter {

    List<WsncRpbLocaraCdMngtDto.SearchRes> mapCreateResToListDvo(List<WsncRpbLocaraCdMngtDvo> dvoList);
}
