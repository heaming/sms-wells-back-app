package com.kyowon.sms.wells.web.contract.common.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.contract.common.dto.WctzAddressDto.SearchAdrRes;

@Mapper
public interface WctzAddressMapper {

    SearchAdrRes selectInstallerAddressByCntr(String dtlCntrNo, int dtlCntrSn);

    SearchAdrRes selectContractorAddressByCntr(String cntrNo);
}
