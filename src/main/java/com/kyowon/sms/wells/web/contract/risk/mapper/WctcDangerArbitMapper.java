package com.kyowon.sms.wells.web.contract.risk.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.kyowon.sms.wells.web.contract.risk.dto.WctcDangerArbitDto.SearchReq;
import com.kyowon.sms.wells.web.contract.risk.dto.WctcDangerArbitDto.SearchRes;
import com.kyowon.sms.wells.web.contract.risk.dvo.WctcDangerArbitDvo;

@Mapper
public interface WctcDangerArbitMapper {
    List<SearchRes> selectDangerArbitManagerial(SearchReq dto);

    int updateDangerCheckIzDlYn(String dangChkId);

    int updateDangerCheckChHist(String dangChkId);

    int insertDangerCheckChHistY(String dangChkId);

    int insertDangerCheckChHistN(String dangChkId);

    int insertDangerCheckIz(@Param("item")
    WctcDangerArbitDvo dvo);

    int updateDangerCheckIz(WctcDangerArbitDvo dvo);
}
