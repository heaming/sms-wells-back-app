package com.kyowon.sms.wells.web.competence.report.mapper;

import java.util.List;

import com.kyowon.sms.wells.web.competence.report.dvo.WwpscBusinessManagerReportMgtDvo;
import org.apache.ibatis.annotations.Mapper;

import static com.kyowon.sms.wells.web.competence.report.dto.WwpsgBusinessManagerReportMgtDto.*;

import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WwpsgBusinessManagerReportMgtMapper {

    PagingResult<WwpscBusinessManagerReportMgtDvo> selectBusinessManagerReportMgtPages(
        SearchReq dto,
        PageInfo pageInfo
    );

    List<businessTypeRes> selectPsgaRpotBizTpBas(
        SearchReq dto
    );

    List<OptionListRes> selectOgbsMmOgIz(
        SearchReq dto
    );

    List<WwpscBusinessManagerReportMgtDvo> selectOgbsPrtnrBas(
        SearchReq dto
    );

    int updatePsgaRpotBizPsicBas(
        SaveReq dto
    );

    int updatePsgaRpotBizTpBas(
        SaveReq dto
    );

    List<SearchRes> selectBusinessManagerReportMgtPages(
        SearchReq dto
    );

    List<SearchRes> selectBusinessManagerReportMgtPages(
        CheckSearchReq dto
    );

    int deletePsgaRpotBizPsicBas(
        SaveReq dto
    );

}
