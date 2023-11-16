package com.kyowon.sms.wells.web.competence.affiars.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.kyowon.sms.wells.web.competence.affiars.converter.WwpseGenenalAffairsElcBizAkPsConverter;
import com.kyowon.sms.wells.web.competence.affiars.dto.WwpseGenenalAffairsElcBizAkDto;
import com.kyowon.sms.wells.web.competence.affiars.dto.WwpseGenenalAffairsElcBizAkPsDto;
import com.kyowon.sms.wells.web.competence.affiars.dvo.WwpseGenenalAffairsElcBizAkDvo;
import com.kyowon.sms.wells.web.competence.affiars.mapper.WwpseGenenalAffairsElcBizAkPsMapper;
import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.competence.affiars.dvo.WwpseGenenalAffairsElcBizAkPsDvo;
import com.kyowon.sms.wells.web.competence.affiars.converter.WwpseGenenalAffairsElcBizAkPsConverter;
import com.kyowon.sms.wells.web.competence.affiars.mapper.WwpseGenenalAffairsElcBizAkPsMapper;

import static com.kyowon.sms.wells.web.competence.affiars.dto.WwpseGenenalAffairsElcBizAkPsDto.*;

import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WwpseGenenalAffairsElcBizAkPsService {

    private final WwpseGenenalAffairsElcBizAkPsMapper mapper;
    private final WwpseGenenalAffairsElcBizAkPsConverter converter;

    public PagingResult<SearchRes> getGenenalAffairsElcBizAkPsPages(SearchReq dto, PageInfo pageInfo) {
        PagingResult<WwpseGenenalAffairsElcBizAkPsDvo> result = mapper.selectGenenalAffairsElcBizAkPsPages(dto, pageInfo);
        PagingResult<WwpseGenenalAffairsElcBizAkPsDto.SearchRes> converterResult = this.converter.dvoToSearchRes(result);

        converterResult.setPageInfo(result.getPageInfo());

        return converterResult;

    }

    public List<WwpseGenenalAffairsElcBizAkPsDto.businessTypeRes> getBusinessType(WwpseGenenalAffairsElcBizAkPsDto.BaseSearchReq dto) {
        return mapper.selectPsgaRpotBizTpBas(dto);
    }

    public List<SearchRes> getGenenalAffairsElcBizAkPssForExcelDownload(SearchReq dto) {
        return mapper.selectGenenalAffairsElcBizAkPsPages(dto);
    }
}
