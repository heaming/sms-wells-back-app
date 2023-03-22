package com.kyowon.sms.wells.web.service.stock.service;

import java.util.List;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaNormalOutOfStorageDto;
import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaNormalOutOfStorageDto.SearchReq;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaNormalOutOfStorageDto.SearchRes;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaNormalOutOfStorageDto.SearchWarehouse;
import com.kyowon.sms.wells.web.service.stock.mapper.WsnaNormalOutOfStorageMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import lombok.RequiredArgsConstructor;

/**
 * <pre>
 * W-SV-U-0142M01 정상출고 관리
 * </pre>
 *
 * @author inho.choi
 * @since 2023-03-14
 */
@Service
@RequiredArgsConstructor
public class WsnaNormalOutOfStorageService {
    private final WsnaNormalOutOfStorageMapper mapper;

    public PagingResult<SearchRes> getNormalOutOfStorage(SearchReq dto, PageInfo pageInfo) {
        return mapper.selectNormalOutOfStorage(dto, pageInfo);
    }

    public List<SearchRes> getNormalOutOfStorage(SearchReq dto) {
        return mapper.selectNormalOutOfStorage(dto);
    }

    public List<SearchWarehouse> getWarehouses(SearchReq dto) {
        return mapper.selectWarehouses(dto);
    }

    public PagingResult<WsnaNormalOutOfStorageDto.AskRes> getAskMaterialsHavePss(
        WsnaNormalOutOfStorageDto.AskReq dto, PageInfo pageInfo
    ) {
        return mapper.selectAskMaterialsHavePss(dto, pageInfo);
    }

    public PagingResult<WsnaNormalOutOfStorageDto.CenterRes> getAskMaterialsCenterPresentState(
        WsnaNormalOutOfStorageDto.AskReq dto, PageInfo pageInfo
    ) {
        return mapper.selectAskMaterialsCenterPresentState(dto, pageInfo);
    }
}
