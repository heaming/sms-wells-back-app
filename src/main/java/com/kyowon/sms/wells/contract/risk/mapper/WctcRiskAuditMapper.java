package com.kyowon.sms.wells.contract.risk.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.contract.risk.dto.WctcRiskAuditDto.SearchReq;
import com.kyowon.sms.wells.contract.risk.dto.WctcRiskAuditDto.SearchRes;
import com.kyowon.sms.wells.contract.risk.dvo.WctcRiskAuditDvo;

@Mapper
public interface WctcRiskAuditMapper {
    List<SearchRes> selectIrgBznsArbitArtc(SearchReq dto);

    int updateDangerCheckIz(String dangChkId);

    int updateDangerCheckChHist(String dangChkId);

    int insertDangerCheckChHist(WctcRiskAuditDvo dangerArbit);
}
