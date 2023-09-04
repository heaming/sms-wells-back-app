package com.kyowon.sms.wells.web.service.visit.converter;

import static com.kyowon.sms.wells.web.service.visit.dto.WsnbTotalAsRateQltySearchDto.SearchRes;

import java.util.List;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.service.visit.dvo.WsnbTotalAsRateQltySearchDvo;

/**
 * <pre>
 * W-SV-U-0248M01 총 A/S율 현황(품질)
 * </pre>
 *
 * @author 37758 이재훈
 * @since 2023.09.01
 */
@Mapper(componentModel = "spring")
public interface WsnbTotalAsRateQltySearchConverter {
    List<SearchRes> mapSearchResDvo(List<WsnbTotalAsRateQltySearchDvo> dvos);
}
