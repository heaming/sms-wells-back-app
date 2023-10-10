package com.kyowon.sms.wells.web.service.stock.service;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaItemLocationDto.*;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.service.stock.converter.WsnaItemLocationConverter;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaItemLocationDvo;
import com.kyowon.sms.wells.web.service.stock.mapper.WsnaItemLocationMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import lombok.RequiredArgsConstructor;

/**
 * <pre>
 * W-SV-U-0210P01 품목위치관리 팝업, W-SV-U-0137M01 품목위치 관리 서비스
 * </pre>
 *
 * @author SaeRomI.Kim
 * @since 2023-10-10
 */

@Service
@RequiredArgsConstructor
public class WsnaItemLocationService {
    private final WsnaItemLocationMapper mapper;
    private final WsnaItemLocationConverter converter;

    /**
     * 품목위치관리 팝업 페이징 조회
     *
     * @param dto
     * @param pageInfo
     * @return
     */
    public PagingResult<SearchRes> getItemLocations(SearchReq dto, PageInfo pageInfo) {
        return mapper.selectItemLocations(dto, pageInfo);
    }

    /**
     * 품목위치관리 팝업 엑셀 다운로드
     * @param dto
     * @return
     */
    public List<SearchRes> getItemLocations(SearchReq dto) {
        return mapper.selectItemLocations(dto);
    }

    /**
     * 품목위치관리 팝업 저장
     * @param list
     * @return
     */
    @Transactional
    public int saveItemLocations(List<CreateReq> list) {
        List<WsnaItemLocationDvo> voList = converter.mapAllWsnaItemLocationDvos(list);
        return mapper.saveItemLocations(voList);
    }

    /**
     * 품목위치 관리 상단 창고 조회
     * @return
     */
    public List<SearchStockCdRes> getItemLocationsStockCd() {
        return mapper.selectItemLocationsStockCd();
    }

    /**
     * 품목위치 관리 페이징 조회
     * @param dto
     * @param pageInfo
     * @return
     */
    public PagingResult<SearchLocationRes> getStockItemLocations(SearchLocationReq dto, PageInfo pageInfo) {
        return mapper.selectStockItemLocations(dto, pageInfo);
    }

    /**
     * 품목위치 관리 엑셀 다운로드
     * @param dto
     * @return
     */
    public List<SearchLocationRes> getStockItemLocationsExcelDownload(SearchLocationReq dto) {
        return mapper.selectStockItemLocations(dto);
    }

    /**
     * 품목위치 관리 저장
     * @param list
     * @return
     */
    @Transactional
    public int saveStockItemLocations(List<CreateLocationReq> list) {
        List<WsnaItemLocationDvo> voList = converter.mapAllStockWsnaItemLocationDvos(list);
        return mapper.saveStockItemLocations(voList);
    }

    /**
     * 품목위치 관리 표준 미적용 체크
     * @param dto
     * @return
     */
    @Transactional
    public int saveStandardWarehouse(CreateWareLocationReq dto) {
        WsnaItemLocationDvo dvo = this.converter.mapToStckStdGbWsnaItemLocataionDvo(dto);
        return mapper.updateStandarWarehouse(dvo);
    }
}
