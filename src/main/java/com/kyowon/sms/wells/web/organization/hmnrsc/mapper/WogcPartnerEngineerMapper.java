package com.kyowon.sms.wells.web.organization.hmnrsc.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.organization.hmnrsc.dto.WogcPartnerEngineerDto;
import com.kyowon.sms.wells.web.organization.hmnrsc.dto.WogcPartnerEngineerDto.*;
import com.kyowon.sms.wells.web.organization.hmnrsc.dvo.WogcPartnerEngineerDvo;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

/**
 * <pre>
 *
 * </pre>
 *
 * @author 김동석
 * @since 2023-05-24
 */
@Mapper
public interface WogcPartnerEngineerMapper {

    PagingResult<SearchEngineerRes> selectEngineerAttends(SearchEngineerReq dto, PageInfo pageInfo);

    List<SearchEngineerRes> selectEngineerAttends(SearchEngineerReq dto);

    int updateEngineer(WogcPartnerEngineerDvo engineer);

    PagingResult<WogcPartnerEngineerDvo> selectJoeManagements(FindJoeManagementReq dto, PageInfo pageInfo);

    List<WogcPartnerEngineerDvo> selectJoeManagementForExcelDownload(FindJoeManagementReq dto);

    int insertWkGrpBlgDtl(WogcPartnerEngineerDvo dvo);

    void updatePrtnrGrpCd(WogcPartnerEngineerDvo dvo);

    PagingResult<FindEngineerGradeRes> selectEngineerGrades(
        WogcPartnerEngineerDto.FindEngineerGradeReq dto, PageInfo pageInfo
    );

    List<FindEngineerGradeRes> selectEngineerGrades(WogcPartnerEngineerDto.FindEngineerGradeReq dto);

    int insertEgerGdRgst(WogcPartnerEngineerDvo dvo);

    void insertEgerGdRgsts(WogcPartnerEngineerDvo dvo);

    String selectEngineerPartner(WogcPartnerEngineerDvo list);

    void updateMonthPrtnrRolDvCd(WogcPartnerEngineerDvo dvo);

    void updatePrtnrRolDvCd(WogcPartnerEngineerDvo dvo);

    void insertPrtnrHist(WogcPartnerEngineerDvo dvo);

    void updatePrtnrBusiness(WogcPartnerEngineerDvo dvo);
}
