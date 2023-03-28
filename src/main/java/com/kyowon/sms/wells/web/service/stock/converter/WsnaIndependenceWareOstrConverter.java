package com.kyowon.sms.wells.web.service.stock.converter;

import java.util.List;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaIndependenceWareOstrDto.CreateReq;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaIndependenceWareOstrDvo;

/**
 * <pre>
 * W-SV-U-0193M01 독립창고출고관리
 * </pre>
 *
 * @author inho.choi
 * @since 2023-03-28
 */
@Mapper(componentModel = "spring")
public interface WsnaIndependenceWareOstrConverter {
    List<WsnaIndependenceWareOstrDvo> mapAllCreateReqToWsnaIndependenceWareOstrDvo(List<CreateReq> list);
}
