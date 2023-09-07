package com.kyowon.sms.wells.web.service.stock.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.service.common.dvo.WsnzWellsCodeWareHouseDvo;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaAsMaterialItemGradeDto;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaMaterialsHavePresentStateDto.SearchReq;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaMaterialsHavePresentStateDto.SearchRes;
import com.kyowon.sms.wells.web.service.stock.mapper.WsnaMaterialsHavePresentStateMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import com.sds.sflex.system.config.validation.ValidAssert;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * <pre>
 * K-W-SV-U-0112M01 자재보유현황
 * </pre>
 *
 * @author segi 홍세기
 * @since 2023.07.27
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class WsnaMaterialsHavePresentStateService {
    private final WsnaMaterialsHavePresentStateMapper mapper;

    public PagingResult<SearchRes> getMaterialsHavePresentListPages(SearchReq dto, PageInfo pageInfo) {
        return mapper.selectMaterialsHavePresentState(dto, pageInfo);
    }

    public List<WsnzWellsCodeWareHouseDvo> getWareHouses(WsnaAsMaterialItemGradeDto.SearchWareReq dto) {
        ValidAssert.notNull(dto);
        ValidAssert.hasText(dto.baseYm());
        ValidAssert.hasText(dto.wareDvCd());

        return this.mapper.selectWareHouses(dto);
    }

}
