package com.kyowon.sms.wells.web.service.stock.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.service.stock.converter.WsnaNormalOutOfStorageConverter;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaNormalOutOfStorageDto.*;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaNormalOutOfStorageDvo;
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

    private final WsnaNormalOutOfStorageConverter converter;

    public PagingResult<SearchRes> getNormalOutOfStorage(SearchReq dto, PageInfo pageInfo) {
        return mapper.selectNormalOutOfStorage(dto, pageInfo);
    }

    public List<SearchRes> getNormalOutOfStorage(SearchReq dto) {
        return mapper.selectNormalOutOfStorage(dto);
    }

    public List<SearchWarehouse> getWarehouses(SearchReq dto) {
        return mapper.selectWarehouses(dto);
    }

    public PagingResult<AskRes> getAskMaterialsHavePss(
        AskReq dto, PageInfo pageInfo
    ) {
        return mapper.selectAskMaterialsHavePss(dto, pageInfo);
    }

    public PagingResult<CenterRes> getAskMaterialsCenterPresentState(
        AskReq dto, PageInfo pageInfo
    ) {
        return mapper.selectAskMaterialsCenterPresentState(dto, pageInfo);
    }

    public PagingResult<DetailRes> getNormalOutOfStoragesDetails(DetailReq dto, PageInfo pageInfo) {
        return mapper.selectNormalOutOfStoragesDetails(dto, pageInfo);
    }

    @Transactional
    public int saveNormalOstrRgsts(List<CreateReq> list) {
        int cnt = 0;
        List<WsnaNormalOutOfStorageDvo> voList = converter.mapCreateReqToWsnaNormalOutOfStorageDvo(list);
        for (WsnaNormalOutOfStorageDvo vo : voList) {
            HashMap<String, String> resultMap = mapper.selectStrNoAndOstrNo(vo);
            cnt += mapper.insertNormalOstrRgst(vo);
            cnt += mapper.insertNormalStrRgst(vo);
        }
        return cnt;
    }

    public int getNormalOstrRgstChecked(CheckedReq dto) {
        return mapper.selectNormalOstrRgstChecked(dto);

    }
}
