package com.kyowon.sms.wells.web.service.visit.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.service.visit.dto.WsnbRentalMembershipCancelPsDto.SearchReq;
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

    public PagingResult getRentalMembershipCancelPsPages(SearchReq dto, PageInfo pageInfo) {
        return this.mapper.selectRentalMembershipCancelPss(dto, pageInfo);
    }

    public List getRentalMembershipCancelPss(SearchReq dto) {
        return this.mapper.selectRentalMembershipCancelPss(dto);
    }
}
