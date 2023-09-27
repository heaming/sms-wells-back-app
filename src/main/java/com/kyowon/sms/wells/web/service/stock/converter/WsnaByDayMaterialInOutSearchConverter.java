package com.kyowon.sms.wells.web.service.stock.converter;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaByDayMaterialInOutSearchDto.DeleteReq;
import static com.kyowon.sms.wells.web.service.stock.dto.WsnaByDayMaterialInOutSearchDto.SearchRes;

import java.util.List;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.service.stock.dvo.WsnaByDayMaterialInOutSearchDvo;
import com.sds.sflex.system.config.datasource.PagingResult;

/**
 *
 * <pre>
 * K-W-SV-U-0258M01 일자별 자재 입출고 관리
 * </pre>
 *
 * @author 37758 이재훈
 * @since 2023.09.19
 */
@Mapper(componentModel = "spring")
public interface WsnaByDayMaterialInOutSearchConverter {

    PagingResult<SearchRes> mapAllByDayMaterialInOutSearchRes(
        List<WsnaByDayMaterialInOutSearchDvo> dvos
    );

    WsnaByDayMaterialInOutSearchDvo mapByDayMaterialInOutDeleteReq(DeleteReq dto);
}
