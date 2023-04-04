package com.kyowon.sms.wells.web.contract.ordermgmt.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.contract.ordermgmt.converter.WctaOrderDetailInqrConverter;
import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaOrderDetailInqrDto;
import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.WctaOrderDetailInqrDvo;
import com.kyowon.sms.wells.web.contract.ordermgmt.mapper.WctaOrderDetailInqrMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WctaOrderDetailInqrService {
    private final WctaOrderDetailInqrMapper mapper;
    private final WctaOrderDetailInqrConverter converter;

    public PagingResult<WctaOrderDetailInqrDto.SearchRes> getOrderDetailSpayCntrtPages(
        WctaOrderDetailInqrDto.SearchReq dto, PageInfo pageInfo
    ) {
        PagingResult<WctaOrderDetailInqrDvo> dvos = mapper.selectOrderDetailSpayCntrtPages(dto, pageInfo);
        PagingResult<WctaOrderDetailInqrDto.SearchRes> reqs = new PagingResult<WctaOrderDetailInqrDto.SearchRes>();

        for (int i = 0; i < dvos.size(); i++) {
            WctaOrderDetailInqrDvo dvo = dvos.get(i);
            WctaOrderDetailInqrDto.SearchRes req = null;
            req = converter.mapSearchResToWctaOrderDetailInqrDvo(dvo);
            reqs.add(req);
        }
        System.out.println("장성 reqs= " + reqs);
        System.out.println("장성 reqs1= " + reqs.get(1));
        return reqs;
    }

    public List<WctaOrderDetailInqrDto.SearchRes> getOrderDetailSpayCntrtPagesExcelDownload(
        WctaOrderDetailInqrDto.SearchReq dto
    ) {
        List<WctaOrderDetailInqrDvo> dvos = mapper.selectOrderDetailSpayCntrtPages(dto);
        List<WctaOrderDetailInqrDto.SearchRes> reqs = new PagingResult<WctaOrderDetailInqrDto.SearchRes>();
        ;
        for (int i = 0; i < dvos.size(); i++) {
            WctaOrderDetailInqrDvo dvo = dvos.get(i);
            WctaOrderDetailInqrDto.SearchRes req = null;
            req = converter.mapSearchResToWctaOrderDetailInqrDvo(dvo);
            reqs.add(req);
        }
        return reqs;
    }
}
