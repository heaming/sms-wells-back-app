package com.kyowon.sms.wells.web.service.visit.service;

import static com.kyowon.sms.wells.web.service.visit.dto.WsnbCenterCustomerAgrgByProductDto.SearchReq;
import static com.kyowon.sms.wells.web.service.visit.dto.WsnbCenterCustomerAgrgByProductDto.SearchRes;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.service.visit.mapper.WsnbCenterCustomerAgrgByProductMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WsnbCenterCustomerAgrgByProductService {
    private final WsnbCenterCustomerAgrgByProductMapper mapper;

    public PagingResult<SearchRes> getCenterCustomerAgrgByProduct(SearchReq dto, PageInfo pageInfo) {
        return mapper.selectCenterCustomerAgrgByProduct(dto, pageInfo);
    }

    public List<SearchRes> getCenterCustomerAgrgByProduct(SearchReq dto) {
        return mapper.selectCenterCustomerAgrgByProduct(dto);
    }
}
