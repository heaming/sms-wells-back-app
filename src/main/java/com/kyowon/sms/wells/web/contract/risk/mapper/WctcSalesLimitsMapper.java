package com.kyowon.sms.wells.web.contract.risk.mapper;

import static com.kyowon.sms.wells.web.contract.risk.dto.WctcSalesLimitsDto.SearchBlacklistReq;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.kyowon.sms.wells.web.contract.risk.dto.WctcSalesLimitsDto.SearchBlacklistRes;
import com.kyowon.sms.wells.web.contract.risk.dvo.WctcSellLimitOjIzDvo;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WctcSalesLimitsMapper {

    PagingResult<SearchBlacklistRes> selectBlacklistPages(SearchBlacklistReq dto, PageInfo pageInfo);

    List<SearchBlacklistRes> selectBlacklistPages(SearchBlacklistReq dto);

    int insertBlacklist(@Param("item")
    WctcSellLimitOjIzDvo dvo);

    int updateBlacklist(WctcSellLimitOjIzDvo dvo);

    int deleteBlacklist(String sellLmId);
}
