package com.kyowon.sms.wells.web.service.visit.converter;

import static com.kyowon.sms.wells.web.service.visit.dto.WsnbEngineerAsRevisitListSearchDto.SearchRes;

import java.util.List;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.service.visit.dvo.WsnbEngineerAsRevisitListSearchDvo;
import com.sds.sflex.system.config.datasource.PagingResult;

/**
 *
 * <pre>
 * K-W-SV-U-0233M01 A/S 재방문현황(엔지니어)
 * </pre>
 *
 * @author 37758 이재훈
 * @since 2023.10.11
 */
@Mapper(componentModel = "spring")
public interface WsnbEngineerAsRevisitListSearchConverter {

    PagingResult<SearchRes> mapWsnbEngineerAsRevisitListSearchRes(
        List<WsnbEngineerAsRevisitListSearchDvo> dvos
    );

}
