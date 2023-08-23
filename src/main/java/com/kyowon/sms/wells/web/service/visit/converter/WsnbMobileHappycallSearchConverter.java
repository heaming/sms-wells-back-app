package com.kyowon.sms.wells.web.service.visit.converter;

import static com.kyowon.sms.wells.web.service.visit.dto.WsnbMobileHappycallSearchDto.SearchRes;

import java.util.List;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.service.visit.dvo.WsnbMobileHappycallSearchDvo;
import com.sds.sflex.system.config.datasource.PagingResult;

/**
 *
 * <pre>
 * K-W-SV-U-0267M01 모바일 해피콜 현황
 * </pre>
 *
 * @author 37758 이재훈
 * @since 2023.08.17
 */
@Mapper(componentModel = "spring")
public interface WsnbMobileHappycallSearchConverter {

    PagingResult<SearchRes> mapWsnbMobileHappycallSearchSearchRes(
        List<WsnbMobileHappycallSearchDvo> dvos
    );

}
