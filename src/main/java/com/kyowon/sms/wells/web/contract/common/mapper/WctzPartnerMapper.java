package com.kyowon.sms.wells.web.contract.common.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.contract.common.dto.WctzPartnerDto.SearchEntrepreneurBaseRes;

@Mapper
public interface WctzPartnerMapper {

    List<SearchEntrepreneurBaseRes> selectEntrepreneurBases(String dlpnrCd);
}
