package com.kyowon.sms.wells.web.service.allocate.converter;

import static com.kyowon.sms.wells.web.service.allocate.dto.WsncAsEngineerAllocateSearchDto.SearchRes;

import java.util.List;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.service.allocate.dvo.WsncAsEngineerAllocateSearchDvo;
import com.sds.sflex.system.config.datasource.PagingResult;

/**
 *
 * <pre>
 * K-W-SV-U-0021M01 엔지니어 배정현황
 * </pre>
 *
 * @author 37758 이재훈
 * @since 2023.09.06
 */
@Mapper(componentModel = "spring")
public interface WsncAsEngineerAllocateSearchConverter {

    PagingResult<SearchRes> mapAllAsEngineerAllocateSearchSearchRes(
        List<WsncAsEngineerAllocateSearchDvo> dvos
    );

}
