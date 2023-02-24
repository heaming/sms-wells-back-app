package com.kyowon.sms.wells.web.contract.risk.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.contract.risk.dto.WctcRiskAuditDto.SearchReq;
import com.kyowon.sms.wells.web.contract.risk.dto.WctcRiskAuditDto.SearchRes;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WctcRiskAuditMapper {
    PagingResult<SearchRes> selectIrregularBznsInqr(SearchReq dto, PageInfo pageInfo);

    List<SearchRes> selectIrregularBznsInqr(SearchReq dto);

    int updateDangerCheckIz(String dangChkId);

    int updateDangerCheckChHist(String dangChkId);

    int insertDangerCheckChHist(String dangChkId);
}
