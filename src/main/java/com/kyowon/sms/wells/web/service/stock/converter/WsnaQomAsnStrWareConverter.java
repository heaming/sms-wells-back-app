package com.kyowon.sms.wells.web.service.stock.converter;

import java.util.List;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaQomAsnStrWareDto.CreateReq;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaQomAsnStrWareDvo;

/**
 * <pre>
 * W-SV-U-0125M01 물량배정 입고창고관리
 * </pre>
 *
 * @author inho.choi
 * @since 2023-02-10
 */
@Mapper(componentModel = "spring")
public interface WsnaQomAsnStrWareConverter {
    List<WsnaQomAsnStrWareDvo> mapCreateReqToWsnaQomAsnStrWareDvo(List<CreateReq> list);
}
