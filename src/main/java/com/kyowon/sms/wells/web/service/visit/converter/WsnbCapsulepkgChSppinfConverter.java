package com.kyowon.sms.wells.web.service.visit.converter;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.service.visit.dvo.WsnbCallingLogDvo;
import com.kyowon.sms.wells.web.service.visit.dvo.WsnbCapsulepkgChSppinfDvo;

/**
 * <pre>
 * W-SV-I-0020 Wells홈페이지 홈카페 캡슐 패키지 변경을 위한 배송 정보 리스트
 * </pre>
 *
 * @author yeonghwa.cheon
 * @since 2023.03.20
 */
@Mapper(componentModel = "spring")
public interface WsnbCapsulepkgChSppinfConverter {

    WsnbCallingLogDvo mapWsnbCapsulepkgChSppinfDvoToSearchRes(WsnbCapsulepkgChSppinfDvo dvo);

}
