package com.kyowon.sms.wells.web.competence.affiars.mapper;

import java.util.List;

import com.kyowon.sms.wells.web.competence.affiars.dto.WwpseGenenalAffairsElcBizAkDto;
import com.kyowon.sms.wells.web.competence.affiars.dto.WwpseGenenalAffairsElcBizAkPsDto;
import org.apache.ibatis.annotations.Mapper;

import static com.kyowon.sms.wells.web.competence.affiars.dto.WwpseGenenalAffairsElcBizAkPsDto.*;

import com.kyowon.sms.wells.web.competence.affiars.dvo.WwpseGenenalAffairsElcBizAkPsDvo;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WwpseGenenalAffairsElcBizAkPsMapper {

    PagingResult<WwpseGenenalAffairsElcBizAkPsDvo> selectGenenalAffairsElcBizAkPsPages(
        SearchReq dto,
        PageInfo pageInfo
    );

    List<WwpseGenenalAffairsElcBizAkPsDto.businessTypeRes> selectPsgaRpotBizTpBas(
        WwpseGenenalAffairsElcBizAkPsDto.BaseSearchReq dto
    );

    List<SearchRes> selectGenenalAffairsElcBizAkPsPages(
        SearchReq dto
    );
}
