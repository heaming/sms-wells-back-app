package com.kyowon.sms.wells.contract.risk.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.contract.risk.dto.WctcDangerArbitDto.SearchReq;
import com.kyowon.sms.wells.contract.risk.dto.WctcDangerArbitDto.SearchRes;
import com.kyowon.sms.wells.contract.risk.dvo.WctcDangerArbitDvo;

@Mapper
public interface WctcDangerArbitMapper {
    List<SearchRes> selectIrgBznsArbitArtc(SearchReq dto);

    int updateDangerCheckIz(String dangChkId);

    int updateDangerCheckChHist(String dangChkId);

    int insertDangerCheckChHist(WctcDangerArbitDvo dangerArbit);
}
