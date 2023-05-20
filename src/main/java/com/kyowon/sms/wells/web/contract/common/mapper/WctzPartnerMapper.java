package com.kyowon.sms.wells.web.contract.common.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.contract.common.dto.WctzPartnerDto.SearchBranchDivisionsRes;
import com.kyowon.sms.wells.web.contract.common.dto.WctzPartnerDto.SearchEntrepreneurBaseRes;
import com.kyowon.sms.wells.web.contract.common.dto.WctzPartnerDto.SearchGeneralDivisionsRes;
import com.kyowon.sms.wells.web.contract.common.dto.WctzPartnerDto.SearchRegionalDivisionsRes;

@Mapper
public interface WctzPartnerMapper {

    List<SearchEntrepreneurBaseRes> selectEntrepreneurBases(String bzrno);

    List<SearchGeneralDivisionsRes> selectGeneralDivisions(String baseYm);

    List<SearchRegionalDivisionsRes> selectRegionalDivisions(String baseYm);

    List<SearchBranchDivisionsRes> selectBranchDivisions(String baseYm);

}
