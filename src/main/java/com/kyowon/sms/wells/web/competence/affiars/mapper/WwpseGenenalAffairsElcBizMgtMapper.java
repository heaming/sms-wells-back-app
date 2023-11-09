package com.kyowon.sms.wells.web.competence.affiars.mapper;

import java.util.List;

import com.kyowon.sms.wells.web.competence.affiars.dto.WwpseGenenalAffairsElcBizMgtDto;
import com.kyowon.sms.wells.web.competence.affiars.dvo.WwpseGenenalAffairsElcBizMgtDvo;
import org.apache.ibatis.annotations.Mapper;

import static com.kyowon.sms.wells.web.competence.affiars.dto.WwpseGenenalAffairsElcBizMgtDto.*;

import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WwpseGenenalAffairsElcBizMgtMapper {

    PagingResult<WwpseGenenalAffairsElcBizMgtDvo> selectGenenalAffairsElcBizMgtPages(
        SearchReq dto,
        PageInfo pageInfo
    );

    List<businessTypeRes> selectPsgaRpotBizTpBas(
        SearchReq dto
    );

    List<WwpseGenenalAffairsElcBizMgtDto.OptionListRes> selectOgbsMmOgIz(
        WwpseGenenalAffairsElcBizMgtDto.SearchReq dto
    );

    List<WwpseGenenalAffairsElcBizMgtDvo> selectOgbsPrtnrBas(
        WwpseGenenalAffairsElcBizMgtDto.SearchReq dto
    );

    int updatePsgaRpotBizPsicBas(
        WwpseGenenalAffairsElcBizMgtDto.SaveReq dto
    );

    int updatePsgaRpotBizTpBas(
        WwpseGenenalAffairsElcBizMgtDto.SaveReq dto
    );

    List<WwpseGenenalAffairsElcBizMgtDto.SearchRes> selectGenenalAffairsElcBizMgtPages(
        WwpseGenenalAffairsElcBizMgtDto.CheckSearchReq dto
    );

    int deletePsgaRpotBizPsicBas(
        WwpseGenenalAffairsElcBizMgtDto.SaveReq dto
    );

    List<SearchRes> selectGenenalAffairsElcBizMgtPages(
        SearchReq dto
    );
}
