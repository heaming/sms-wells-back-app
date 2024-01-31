package com.kyowon.sms.wells.web.deduction.redf.converter;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.deduction.redf.dto.WdeaAllowanceRedfMgtDto.SearchRedfBizdRes;
import com.kyowon.sms.wells.web.deduction.redf.dvo.WdeaAllowanceRedfMgtDvo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper(componentModel = "spring", imports = {StringUtils.class})
public interface WdeaAllowanceRedfMgtConverter {
    /*P조직 컨버터*/
    PagingResult<SearchRedfBizdRes> mapRedfBizdW01Res(PagingResult<WdeaAllowanceRedfMgtDvo> dtos);

    List<SearchRedfBizdRes> mapRedfBizdW01Res(List<WdeaAllowanceRedfMgtDvo> dtos);

    /*M조직 컨버터*/
    PagingResult<SearchRedfBizdRes> mapRedfBizdW02CancelRes(PagingResult<WdeaAllowanceRedfMgtDvo> dtos);

    List<SearchRedfBizdRes> mapRedfBizdW02CancelRes(List<WdeaAllowanceRedfMgtDvo> dtos);

    /*M조직 연체 컨버터*/
    //    redfBizdW02DelinquentRes mapRedfBizdW02DelinquentRes(WdeaAllowanceRedfMgtDvo dto);
}
