package com.kyowon.sms.wells.web.competence.report.mapper;

import java.util.List;

import com.kyowon.sms.wells.web.competence.report.dto.WwpsgBusinessCellPhoneChMgtDto;
import com.kyowon.sms.wells.web.competence.report.dto.WwpsgRentManagementDto;
import com.kyowon.sms.wells.web.competence.report.dvo.WwpsgBusinessCellPhoneChMgtDvo;
import org.apache.ibatis.annotations.Mapper;

import static com.kyowon.sms.wells.web.competence.report.dto.WwpsgBusinessCellPhoneChMgtDto.*;

import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface WwpsgBusinessCellPhoneChMgtMapper {

    PagingResult<WwpsgBusinessCellPhoneChMgtDvo> selectBusinessCellPhoneChMgtPages(
        SearchReq dto,
        PageInfo pageInfo
    );


    List<WwpsgBusinessCellPhoneChMgtDvo> selectPsgaRpotBizPsicBas(
        WwpsgBusinessCellPhoneChMgtDto.BaseSearchReq dto
    );

    WwpsgBusinessCellPhoneChMgtDvo selectOgbsMmPrtnrIz(
        WwpsgBusinessCellPhoneChMgtDto.BaseSearchReq dto
    );

    List<WwpsgBusinessCellPhoneChMgtDto.businessTypeRes> selectPsgaRpotBizTpBas(
        WwpsgBusinessCellPhoneChMgtDto.BaseSearchReq dto
    );

    List<WwpsgBusinessCellPhoneChMgtDvo> selectRentManagementPages(
        WwpsgBusinessCellPhoneChMgtDto.SearchReq dto
    );

    WwpsgBusinessCellPhoneChMgtDvo selectPsgaRpotbizProcsIz(
        WwpsgBusinessCellPhoneChMgtDto.SearchReq dto
    );


    int insertPsgaRpotBizAplcIz(
        @Param("dvo") WwpsgBusinessCellPhoneChMgtDvo dvo
    );
    int insertPsgaRpotBizProcsIz(
        WwpsgBusinessCellPhoneChMgtDvo dvo
    );
    int insertPsgaCralTelChAplcIz(
        WwpsgBusinessCellPhoneChMgtDvo dvo
    );

    int updatePsgaRpotBizProcsIz(
        WwpsgBusinessCellPhoneChMgtDto.SaveReq req
    );
}
