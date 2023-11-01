package com.kyowon.sms.wells.web.competence.report.mapper;

import java.util.List;

import com.kyowon.sms.wells.web.competence.report.dto.WwpsgBusinessSpptMngerRpotMgtDto;
import com.kyowon.sms.wells.web.competence.report.dvo.WwpsgBusinessSpptMngerRpotMgtDvo;
import org.apache.ibatis.annotations.Mapper;

import static com.kyowon.sms.wells.web.competence.report.dto.WwpsgBusinessSpptMngerRpotMgtDto.*;

import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WwpsgBusinessSpptMngerRpotMgtMapper {

    PagingResult<WwpsgBusinessSpptMngerRpotMgtDvo> selectBusinessSpptMngerRpotMgtPages(
        SearchReq dto,
        PageInfo pageInfo
    );

    List<WwpsgBusinessSpptMngerRpotMgtDto.businessTypeRes> selectPsgaRpotBizTpBas(
        WwpsgBusinessSpptMngerRpotMgtDto.SearchReq dto
    );

    List<WwpsgBusinessSpptMngerRpotMgtDto.OptionListRes> selectOgbsMmOgIz(
        WwpsgBusinessSpptMngerRpotMgtDto.SearchReq dto
    );

    List<WwpsgBusinessSpptMngerRpotMgtDvo> selectOgbsPrtnrBas(
        WwpsgBusinessSpptMngerRpotMgtDto.SearchReq dto
    );

    int updatePsgaRpotBizPsicBas(
        WwpsgBusinessSpptMngerRpotMgtDto.SaveReq dto
    );

    int updatePsgaRpotBizTpBas(
        WwpsgBusinessSpptMngerRpotMgtDto.SaveReq dto
    );

    List<WwpsgBusinessSpptMngerRpotMgtDto.SearchRes> selectBusinessManagerReportMgtPages(
        WwpsgBusinessSpptMngerRpotMgtDto.SearchReq dto
    );

    List<WwpsgBusinessSpptMngerRpotMgtDto.SearchRes> selectBusinessManagerReportMgtPages(
        WwpsgBusinessSpptMngerRpotMgtDto.CheckSearchReq dto
    );

    int deletePsgaRpotBizPsicBas(
        WwpsgBusinessSpptMngerRpotMgtDto.SaveReq dto
    );
}
