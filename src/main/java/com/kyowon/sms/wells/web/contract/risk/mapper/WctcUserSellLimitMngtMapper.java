package com.kyowon.sms.wells.web.contract.risk.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.kyowon.sms.wells.web.contract.risk.dto.WctcUserSellLimitMngtDto.SaveReq;
import com.kyowon.sms.wells.web.contract.risk.dto.WctcUserSellLimitMngtDto.SearchReq;
import com.kyowon.sms.wells.web.contract.risk.dto.WctcUserSellLimitMngtDto.SearchRes;
import com.kyowon.sms.wells.web.contract.risk.dvo.WctcUserSellLimitMngtDvo;

@Mapper
public interface WctcUserSellLimitMngtMapper {

    List<SearchRes> selectSellLimitList(SearchReq dto);

    String selecBaseCdCheck(SaveReq dto);

    int insertSellBaseBas(@Param("item")
    WctcUserSellLimitMngtDvo userSellLimitMngt);

    int insertSellBaseDtl(WctcUserSellLimitMngtDvo userSellLimitMngt);

    int updateSellBaseBas(WctcUserSellLimitMngtDvo userSellLimitMngt);

    int updateSellBaseDtl(WctcUserSellLimitMngtDvo userSellLimitMngt);

    int removeSellBaseBas(String sellBaseId);

    int removeSellBaseDtl(String sellBaseId);
}
