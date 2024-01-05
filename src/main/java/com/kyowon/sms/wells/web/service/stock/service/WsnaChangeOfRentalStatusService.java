package com.kyowon.sms.wells.web.service.stock.service;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaChangeOfRentalStatusDto.*;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.service.stock.mapper.WsnaChangeOfRentalStatusMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import lombok.RequiredArgsConstructor;

/**
 * <pre>
 * W-SV-U-0287M01 렌탈상태변경 현황
 * </pre>
 *
 * @author SongTaeSung
 * @since 2023.07.21
 */
@Service
@RequiredArgsConstructor
public class WsnaChangeOfRentalStatusService {

    private final WsnaChangeOfRentalStatusMapper mapper;

    /**
     * 렌탈상태변경 현황 페이징 조회
     * @param dto
     * @param pageInfo
     * @return
     */
    public PagingResult<SearchRes> getChangeOfRentalStatusPages(SearchReq dto, PageInfo pageInfo) {
        return mapper.selectChangeOfRentalStatusPages(dto, pageInfo);
    }

    /**
     * 렌탈 상태변경 현황 엑셀 다운로드
     * @param dto
     * @return
     */
    public List<SearchRes> getChangeOfRentalStatussForExcelDownload(SearchReq dto) {
        return mapper.selectChangeOfRentalStatusPages(dto);
    }

    /**
     * 렌탈 상태변경 현황 조회조건 품목코드 조회
     * @return
     */
    public List<SearchItmPdCdRes> getChangeOfRentalStatusItmPdCd() {
        return mapper.selectChangeOfRentalStatusItmPdCd();
    }

    /**
     * 렌탈 상태변경 현황 조회조건 창고를 조회
     * @param dto
     * @return
     */
    public List<SearchWarehouseRes> getChangeOfRentalStatusWarehouse(SearchWarehouseReq dto) {
        return mapper.selectChangeOfRentalStatusWarehouse(dto);
    }
}
