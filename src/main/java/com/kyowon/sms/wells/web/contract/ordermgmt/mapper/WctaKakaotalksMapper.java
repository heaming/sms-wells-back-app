package com.kyowon.sms.wells.web.contract.ordermgmt.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaKakaotalksDto.SearchKakaotalkFwIzsRes;

@Mapper
public interface WctaKakaotalksMapper {

    public List<SearchKakaotalkFwIzsRes> SelectKakaotalkFwIzs(String mtPr, List<String> tempCodeList);

}
