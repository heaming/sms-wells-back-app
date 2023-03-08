package com.kyowon.sms.wells.web.contract.risk.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.kyowon.sms.wells.web.contract.risk.dto.WctcDangerArbitDto.SearchOrganizationRes;
import com.kyowon.sms.wells.web.contract.risk.dto.WctcDangerArbitDto.SearchReq;
import com.kyowon.sms.wells.web.contract.risk.dto.WctcDangerArbitDto.SearchRes;
import com.kyowon.sms.wells.web.contract.risk.dvo.WctcDangerArbitDvo;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WctcDangerArbitMapper {
    PagingResult<SearchRes> selectDangerArbitManagerial(SearchReq dto, PageInfo pageInfo);

    List<SearchRes> selectDangerArbitManagerial(SearchReq dto);

    String selectDangChkId(String dangMngtPrtnrNo, String dangOcStrtdt, String dangMngtPstnDvCd, String dangOjPrtnrNo);

    List<SearchOrganizationRes> selectOrganizationInfInqr(String baseYm, String pntnrNo, String ogTpCd);

    int updateDangerCheckIzDlYn(String dangChkId);

    int updateDangerCheckChHist(String dangChkId);

    int insertDangerCheckChHistY(String dangChkId);

    int insertDangerCheckChHistN(String dangChkId);

    int insertDangerCheckIz(@Param("item")
    WctcDangerArbitDvo dvo);

    int updateDangerCheckIz(WctcDangerArbitDvo dvo);
}
