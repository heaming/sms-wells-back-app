package com.kyowon.sms.wells.web.closing.performance.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.closing.performance.dto.WdccMembershipCnfmGenDto.SearchReq;
import com.kyowon.sms.wells.web.closing.performance.dto.WdccMembershipCnfmGenDto.SearchRes;
import com.kyowon.sms.wells.web.closing.performance.dvo.MembershipCnfmGenDvo;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WdccMembershipCnfmGenMapper {

    PagingResult<SearchRes> selectMembershipConfirmGens(SearchReq dto, PageInfo pageInfo);

    List<SearchRes> selectMembershipConfirmGens(SearchReq req);

    int updateMembershipCnfmGen(MembershipCnfmGenDvo dvo);

}
