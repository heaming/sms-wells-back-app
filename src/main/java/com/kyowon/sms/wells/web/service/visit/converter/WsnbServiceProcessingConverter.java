package com.kyowon.sms.wells.web.service.visit.converter;

import java.util.List;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.service.visit.dto.WsnbServiceProcessingDto.SearchRes;
import com.kyowon.sms.wells.web.service.visit.dvo.WsnbServiceProcessingDvo;

/**
 * <pre>
 * W-SV-U-0054M01 서비스처리 내역
 * </pre>
 *
 * @author hyewon.kim
 * @since 2023.06.27
 */
@Mapper(componentModel = "spring")
public interface WsnbServiceProcessingConverter {

    List<SearchRes> mapAllDvoToSearchRes(List<WsnbServiceProcessingDvo> dvos);

}
