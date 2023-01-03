package com.kyowon.sms.wells.web.service.allocate.converter;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.service.allocate.dto.WsncRpbLocaraPsicMngtDto.CreateReq;
import com.kyowon.sms.wells.web.service.allocate.dto.WsncRpbLocaraPsicMngtDto.SearchRes;
import com.kyowon.sms.wells.web.service.allocate.dvo.WsncRpbLocaraPsicDvo;
import com.sds.sflex.system.config.datasource.PagingResult;

/**
 * <pre>
 * W-SV-U-0017M01 책임지역 담당자 관리
 * </pre>
 *
 * @author hyewon.kim
 * @since 2022.12.22
 */
@Mapper(componentModel = "spring")
public interface WsncRpbLocaraPsicMngtConverter {

    WsncRpbLocaraPsicDvo mapCreateReqToWsncRpbLocaraPsicDvo(CreateReq dto);

    PagingResult<SearchRes> mapAllWsncRpbLocaraPsicDvoToSearchRes(PagingResult<WsncRpbLocaraPsicDvo> dvos);

}
