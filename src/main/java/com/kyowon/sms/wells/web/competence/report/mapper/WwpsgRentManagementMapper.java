package com.kyowon.sms.wells.web.competence.report.mapper;

import java.util.List;

import com.kyowon.sms.wells.web.competence.report.dvo.WwpsgRentManagementDvo;
import org.apache.ibatis.annotations.Mapper;

import static com.kyowon.sms.wells.web.competence.report.dto.WwpsgRentManagementDto.*;

import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface WwpsgRentManagementMapper {

    PagingResult<WwpsgRentManagementDvo> selectRentManagementPages(
        SearchReq dto,
        PageInfo pageInfo
    );

    List<WwpsgRentManagementDvo> selectPsgaRpotBizPsicBas(
        BaseSearchReq dto
    );

    List<businessTypeRes> selectPsgaRpotBizTpBas(
        BaseSearchReq dto
    );

    List<OgbsBldBasRes> selectOgbsBldBas(
        BaseSearchReq dto
    );

    List<WwpsgRentManagementDvo> selectRentManagementPages(
        SearchReq dto
    );

    WwpsgRentManagementDvo selectPsgaRpotbizProcsIz(
        SearchReq dto
    );


    int insertPsgaRpotBizAplcIz(
        @Param("dvo") WwpsgRentManagementDvo dvo
    );
    int insertPsgaRpotBizProcsIz(
        WwpsgRentManagementDvo dvo
    );
    int insertPsgaRntAplcIz(
        WwpsgRentManagementDvo dvo
    );

    int updatePsgaRpotBizProcsIz(
        SaveReq req
    );
}
