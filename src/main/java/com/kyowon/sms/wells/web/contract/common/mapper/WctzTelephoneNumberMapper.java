package com.kyowon.sms.wells.web.contract.common.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.contract.common.dto.WctzTelephoneNumberDto.SearchMpnoRes;
import com.kyowon.sms.wells.web.contract.common.dto.WctzTelephoneNumberDto.SearchTnoRes;

@Mapper
public interface WctzTelephoneNumberMapper {

    SearchTnoRes selectContractorTnoByCntr(String cntrNo);

    SearchMpnoRes selectContractorMpnoByCntr(String cntrNo);

    SearchTnoRes selectInstallerTnoByCntr(String dtlCntrNo, int dtlCntrSn);

    SearchMpnoRes selectInstallerMpnoByCntr(String dtlCntrNo, int dtlCntrSn);

    SearchMpnoRes selectPartnerMpnoByCntr(String cntrNo);
}
