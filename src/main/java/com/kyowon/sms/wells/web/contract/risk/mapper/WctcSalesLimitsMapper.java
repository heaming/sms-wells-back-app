package com.kyowon.sms.wells.web.contract.risk.mapper;

import static com.kyowon.sms.wells.web.contract.risk.dto.WctcSalesLimitsDto.SearchBlacklistReq;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.contract.risk.dto.WctcSalesLimitsDto.SearchBlacklistInfoRes;
import com.kyowon.sms.wells.web.contract.risk.dvo.WctcSellLimitOjIzDvo;

@Mapper
public interface WctcSalesLimitsMapper {

    List<SearchBlacklistInfoRes> selectBlacklistPages(SearchBlacklistReq dto);

    int insertBlacklist(WctcSellLimitOjIzDvo dvo);

    int updateBlacklist(WctcSellLimitOjIzDvo dvo);

    int deleteBlacklist(String sellLmId);
}
