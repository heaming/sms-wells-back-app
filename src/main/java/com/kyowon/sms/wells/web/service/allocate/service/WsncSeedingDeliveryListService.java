package com.kyowon.sms.wells.web.service.allocate.service;

import static com.kyowon.sms.wells.web.service.allocate.dto.WsncSeedingDeliveryListDto.SearchReq;
import static com.kyowon.sms.wells.web.service.allocate.dto.WsncSeedingDeliveryListDto.SearchRes;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.service.allocate.mapper.WsncSeedingDeliveryListMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class WsncSeedingDeliveryListService {

    private final WsncSeedingDeliveryListMapper mapper;

    /**
     * 모종배송내역 - 조회
     *
     * @programId : K-W-SV-U-0213P01
     * @param dto : { cntrNo: 계약번호, cntrSn: 일련번호 }
     * @return 조회결과
     */
    public PagingResult<SearchRes> getSeedingDeliveryList(
        SearchReq dto,
        PageInfo pageInfo
    ) {
        return mapper.selectSeedingDeliveryList(dto, pageInfo);
    }
}
