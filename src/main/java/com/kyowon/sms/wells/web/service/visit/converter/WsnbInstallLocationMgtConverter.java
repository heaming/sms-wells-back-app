package com.kyowon.sms.wells.web.service.visit.converter;

import java.util.List;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.service.visit.dto.WsnbInstallLocationMgtDto.CreateReq;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbInstallLocationMgtDto.SearchRes;
import com.kyowon.sms.wells.web.service.visit.dvo.WsnbInstallLocationDvo;
import com.sds.sflex.system.config.datasource.PagingResult;

/**
 * <pre>
 * W-SV-U-0059M01 - 설치 위치 상세 관리
 * </pre>
 *
 * @author yeonghwa.cheon 천영화
 * @since 2023.01.02
 */
@Mapper(componentModel = "spring")
public interface WsnbInstallLocationMgtConverter {

    WsnbInstallLocationDvo mapCreateReqToWsnbInstallLocationDvo(CreateReq dto);

    PagingResult<SearchRes> mapWsnbInstallLocationDvoToSearchRes(
        List<WsnbInstallLocationDvo> dvos
    );

    SearchRes mapWsnbInstallLocationDvoToSearchRes(WsnbInstallLocationDvo dvo);

}
