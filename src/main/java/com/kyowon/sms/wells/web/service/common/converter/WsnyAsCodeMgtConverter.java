package com.kyowon.sms.wells.web.service.common.converter;

import com.kyowon.sms.wells.web.service.common.dto.WsnyAsCodeMgtDto;
import com.kyowon.sms.wells.web.service.common.dvo.WsnyAsCodeMgtDvo;

import java.util.List;

import org.mapstruct.Mapper;

/**
 *
 * <pre>
 * W-SV-U-0016M01 AS 코드관리
 * </pre>
 *
 * @author gs.piit122 김동엽
 * @since 2022-11-08
 */
@Mapper(componentModel = "spring")
public interface WsnyAsCodeMgtConverter {

    List<WsnyAsCodeMgtDto.SearchRes> mapAllSearchResToDvo(List<WsnyAsCodeMgtDvo> dvos);
}
