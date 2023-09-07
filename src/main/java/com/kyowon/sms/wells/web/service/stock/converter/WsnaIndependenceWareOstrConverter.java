package com.kyowon.sms.wells.web.service.stock.converter;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaIndependenceWareOstrDto.CreateReq;
import static com.kyowon.sms.wells.web.service.stock.dto.WsnaIndependenceWareOstrDto.SaveReq;

import java.util.List;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.service.stock.dvo.WsnaIndependenceWareOstrDvo;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaIndependenceWareOstrLgstDvo;

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
    List<WsnaIndependenceWareOstrDvo> mapAllSaveReqToWsnaIndependenceWareOstrDvo(List<SaveReq> dtos);

    WsnaIndependenceWareOstrLgstDvo mapCreateReqToWsnaIndependenceWareOstrLgstDvo(CreateReq dto);

}
