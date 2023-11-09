package com.kyowon.sms.wells.web.competence.affiars.mapper;

import java.util.List;

import com.kyowon.sms.wells.web.competence.affiars.dto.WwpseGenenalAffairsElcBizAkDto;
import com.kyowon.sms.wells.web.competence.affiars.dto.WwpseGenenalAffairsElcBizAkPsDto;
import com.kyowon.sms.wells.web.competence.affiars.dvo.WwpseGenenalAffairsElcBizAkDvo;
import org.apache.ibatis.annotations.Mapper;

import static com.kyowon.sms.wells.web.competence.affiars.dto.WwpseGenenalAffairsElcBizAkDto.*;

import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface WwpseGenenalAffairsElcBizAkMapper {

    PagingResult<WwpseGenenalAffairsElcBizAkDvo> selectGenenalAffairsElcBizAkPages(
        SearchReq dto,
        PageInfo pageInfo
    );

    List<WwpseGenenalAffairsElcBizAkDvo> selectPsgaRpotBizPsicBas(
        WwpseGenenalAffairsElcBizAkDto.BaseSearchReq dto
    );

    List<WwpseGenenalAffairsElcBizAkDto.businessTypeRes> selectPsgaRpotBizTpBas(
        WwpseGenenalAffairsElcBizAkDto.BaseSearchReq dto
    );

    List<WwpseGenenalAffairsElcBizAkDto.OgbsBldBasRes> selectOgbsMmOgIz(
        WwpseGenenalAffairsElcBizAkDto.BaseSearchReq dto
    );

    List<WwpseGenenalAffairsElcBizAkDvo> selectOgbsPrtnrBas(
        WwpseGenenalAffairsElcBizAkDto.BaseSearchReq dto
    );

    WwpseGenenalAffairsElcBizAkDvo selectPsgaRpotbizProcsIz(
        WwpseGenenalAffairsElcBizAkDto.SearchReq dto
    );

    List<WwpseGenenalAffairsElcBizAkDto.SearchRes> selectGenenalAffairsElcBizAkPages(
        WwpseGenenalAffairsElcBizAkDto.SearchReq dto
    );

    int insertPsgaRpotBizAplcIz(
        @Param("dvo") WwpseGenenalAffairsElcBizAkDvo dvo
    );
    int insertPsgaRpotBizProcsIz(
        WwpseGenenalAffairsElcBizAkDvo dvo
    );
    int insertPsgaRntAplcIz(
        WwpseGenenalAffairsElcBizAkDvo dvo
    );

    int updatePsgaRpotBizProcsIz(
        WwpseGenenalAffairsElcBizAkDto.SaveReq req
    );
}
