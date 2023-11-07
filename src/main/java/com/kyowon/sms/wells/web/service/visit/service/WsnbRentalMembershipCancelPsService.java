package com.kyowon.sms.wells.web.service.visit.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.service.visit.converter.WsnbRentalMembershipCancelPsConverter;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbRentalMembershipCancelPsDto.SearchReq;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbRentalMembershipCancelPsDto.SearchRes;
import com.kyowon.sms.wells.web.service.visit.mapper.WsnbRentalMembershipCancelPsMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class WsnbRentalMembershipCancelPsService {

    private final WsnbRentalMembershipCancelPsMapper mapper;

    private final WsnbRentalMembershipCancelPsConverter converter;

    public PagingResult<SearchRes> getRentalMembershipCancelPsPages(SearchReq dto, PageInfo pageInfo) {
        PagingResult<SearchRes> pagingResult = converter
            .mapDvoToSearchResPages(mapper.selectRentalMembershipCancelPss(dto, pageInfo));
        pagingResult.setPageInfo(pageInfo);
        return pagingResult;
    }

    public List<SearchRes> getRentalMembershipCancelPss(SearchReq dto) {
        return converter.mapDvoToSearchRes(mapper.selectRentalMembershipCancelPss(dto));
    }
}
