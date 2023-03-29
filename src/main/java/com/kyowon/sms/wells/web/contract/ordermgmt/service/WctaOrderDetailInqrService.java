package com.kyowon.sms.wells.web.contract.ordermgmt.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaOrderDetailInqrDto;
import com.kyowon.sms.wells.web.contract.ordermgmt.mapper.WctaOrderDetailInqrMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WctaOrderDetailInqrService {
    private final WctaOrderDetailInqrMapper mapper;

    public PagingResult<WctaOrderDetailInqrDto.SearchRes> getOrderDetailSpayCntrtPages(
        WctaOrderDetailInqrDto.SearchReq dto, PageInfo pageInfo
    ) {
        return mapper.selectOrderDetailSpayCntrtPages(dto, pageInfo);
    }

    public List<WctaOrderDetailInqrDto.SearchRes> getOrderDetailSpayCntrtPagesExcelDownload(
        WctaOrderDetailInqrDto.SearchReq dto
    ) {
        return mapper.selectOrderDetailSpayCntrtPages(dto);
    }
}
