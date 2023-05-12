package com.kyowon.sms.wells.web.organization.hmnrsc.mapper;

import com.kyowon.sms.wells.web.organization.hmnrsc.dto.WogcPartnerEngineerDto;
import com.kyowon.sms.wells.web.organization.hmnrsc.dvo.WogcPartnerEngineerDvo;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import org.apache.ibatis.annotations.Mapper;
import com.kyowon.sms.wells.web.organization.hmnrsc.dto.WogcPartnerEngineerDto.SearchEngineerRes;
import com.kyowon.sms.wells.web.organization.hmnrsc.dto.WogcPartnerEngineerDto.SearchEngineerReq;
import com.kyowon.sms.wells.web.organization.hmnrsc.dto.WogcPartnerEngineerDto.FindJoeManagementReq;
import com.kyowon.sms.wells.web.organization.hmnrsc.dto.WogcPartnerEngineerDto.FindJoeManagementRes;
import com.kyowon.sms.wells.web.organization.hmnrsc.dto.WogcPartnerEngineerDto.FindEngineerGradeReq;
import com.kyowon.sms.wells.web.organization.hmnrsc.dto.WogcPartnerEngineerDto.FindEngineerGradeRes;
import java.util.List;

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

    PagingResult<FindJoeManagementRes> selectJoeManagements(FindJoeManagementReq dto, PageInfo pageInfo);

    List<WogcPartnerEngineerDvo> selectJoeManagementForExcelDownload(FindJoeManagementReq dto);

    int insertWkGrpBlgDtl(WogcPartnerEngineerDvo dvo);

    void updatePrtnrGrpCd(WogcPartnerEngineerDvo dvo);

    PagingResult<FindEngineerGradeRes> selectEngineerGrades(WogcPartnerEngineerDto.FindEngineerGradeReq dto, PageInfo pageInfo);

    List<FindEngineerGradeRes> selectEngineerGrades(WogcPartnerEngineerDto.FindEngineerGradeReq dto);

    int insertEgerGdRgst(WogcPartnerEngineerDvo dvo);

    void insertEgerGdRgsts(WogcPartnerEngineerDvo dvo);

    String selectEngineerPartner(WogcPartnerEngineerDvo list);

    void updateMonthPrtnrRolDvCd(WogcPartnerEngineerDvo dvo);

    void updatePrtnrRolDvCd(WogcPartnerEngineerDvo dvo);

    void insertPrtnrHist(WogcPartnerEngineerDvo dvo);
}
