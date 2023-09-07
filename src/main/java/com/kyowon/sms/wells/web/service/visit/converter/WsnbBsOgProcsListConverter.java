package com.kyowon.sms.wells.web.service.visit.converter;

import static com.kyowon.sms.wells.web.service.visit.dto.WsnbBsOgProcsListDto.SearchCrdOvrRes;
import static com.kyowon.sms.wells.web.service.visit.dto.WsnbBsOgProcsListDto.SearchRes;

import java.util.List;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.service.visit.dvo.WsnbBsOgProcsListDvo;

/**
 *
 * <pre>
 * K-W-SV-U-0238M01 B/S 처리 현황(조직)
 * </pre>
 *
 * @author 37758 이재훈
 * @since 2023.08.02
 */
@Mapper(componentModel = "spring")
public interface WsnbBsOgProcsListConverter {
    List<SearchRes> mapSearchResDvo(List<WsnbBsOgProcsListDvo> dvos);

    List<SearchCrdOvrRes> mapSearchCrdOvrResDvo(List<WsnbBsOgProcsListDvo> dvos);
}
