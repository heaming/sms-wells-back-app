package com.kyowon.sms.wells.web.service.visit.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.visit.dto.WsnbRentalMembershipCancelPsDto.SearchReq;
import com.kyowon.sms.wells.web.service.visit.dvo.WsnbRentalMembershipCancelPsDvo;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WsnbRentalMembershipCancelPsMapper {

    PagingResult<WsnbRentalMembershipCancelPsDvo> selectRentalMembershipCancelPss(SearchReq dto, PageInfo pageInfo);

    List<WsnbRentalMembershipCancelPsDvo> selectRentalMembershipCancelPss(SearchReq dto);
}
