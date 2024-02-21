package com.kyowon.sms.wells.web.deduction.redf.converter;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.deduction.redf.dto.WdeaAllowanceRedfMgtDto.SearchRedfBizdTestRes;
import com.kyowon.sms.wells.web.deduction.redf.dvo.WdeaAllowanceRedfMgtDvo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper(componentModel = "spring", imports = {StringUtils.class})
public interface WdeaAllowanceRedfMgtConverter {
    /*P조직 컨버터*/
    PagingResult<SearchRedfBizdTestRes> mapRedfBizdW01Res(PagingResult<WdeaAllowanceRedfMgtDvo> dtos);

    List<SearchRedfBizdTestRes> mapRedfBizdW01Res(List<WdeaAllowanceRedfMgtDvo> dtos);

    /*M조직 컨버터*/
    PagingResult<SearchRedfBizdTestRes> mapRedfBizdW02Res(PagingResult<WdeaAllowanceRedfMgtDvo> dtos);

    List<SearchRedfBizdTestRes> mapRedfBizdW02Res(List<WdeaAllowanceRedfMgtDvo> dtos);

    /*M조직 연체 컨버터*/
    //    redfBizdW02DelinquentRes mapRedfBizdW02DelinquentRes(WdeaAllowanceRedfMgtDvo dto);
}
