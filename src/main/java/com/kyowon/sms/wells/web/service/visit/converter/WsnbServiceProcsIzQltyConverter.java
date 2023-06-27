package com.kyowon.sms.wells.web.service.visit.converter;

import java.util.List;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.service.visit.dto.WsnbServiceProcsIzQltyDto.SearchRes;
import com.kyowon.sms.wells.web.service.visit.dvo.WsnbServiceProcsIzQltyDvo;

/**
 * <pre>
 * W-SV-U-0099M01 서비스처리 내역(품질)
 * </pre>
 *
 * @author hyewon.kim
 * @since 2023.06.22
 */
@Mapper(componentModel = "spring")
public interface WsnbServiceProcsIzQltyConverter {

    List<SearchRes> mapAllDvoToSearchRes(List<WsnbServiceProcsIzQltyDvo> dvos);

}
