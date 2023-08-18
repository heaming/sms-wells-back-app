package com.kyowon.sms.wells.web.service.visit.converter;

import static com.kyowon.sms.wells.web.service.visit.dto.WsnbTaskTypeItemSearchDto.SearchRes;

import java.util.List;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.service.visit.dvo.WsnbTaskTypeItemSearchDvo;

/**
 *
 * <pre>
 * K-W-SV-U-0075M01 업무유형별 품목 현황
 * </pre>
 *
 * @author 37758 이재훈
 * @since 2023.08.10
 */
@Mapper(componentModel = "spring")
public interface WsnbTaskTypeItemSearchConverter {
    List<SearchRes> mapSearchResDvo(List<WsnbTaskTypeItemSearchDvo> dvos);
}
