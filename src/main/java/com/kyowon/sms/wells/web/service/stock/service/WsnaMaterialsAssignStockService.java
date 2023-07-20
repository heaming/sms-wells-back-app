package com.kyowon.sms.wells.web.service.stock.service;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaMaterialsAssignStocksDto.*;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.service.stock.converter.WsnaMaterialsAssignStocksConverter;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaMaterialsAssignStocksDvo;
import com.kyowon.sms.wells.web.service.stock.mapper.WsnaMaterialsAssignStocksMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import lombok.RequiredArgsConstructor;

/**
 * <pre>
 * W-SV-U-0125M01 물량배정 입고창고 관리 서비스
 * </pre>
 *
 * @author SaeRomI.Kim
 * @since 2023-07-17
 */

@Service
@RequiredArgsConstructor
public class WsnaMaterialsAssignStockService {

    private final WsnaMaterialsAssignStocksMapper mapper;

    private final WsnaMaterialsAssignStocksConverter converter;

    /**
     * 물량배정 입고창고 페이징 조회
     *
     * @param dto
     * @param pageInfo
     * @return
     */
    public PagingResult<SearchRes> getMaterialsAssignStocksPaging(SearchReq dto, PageInfo pageInfo) {
        return this.mapper.selectMaterialsAssignStocksPaging(dto, pageInfo);
    }

    /**
     * 물량배정 입고창고 엑셀 다운로드
     *
     * @param dto
     * @return
     */
    public List<SearchRes> selectMaterialsAssignStocksExcelDownload(SearchReq dto) {
        return this.mapper.selectMaterialsAssignStocksPaging(dto);
    }

    /**
     * 물량배정 입고창고 저장
     *
     * @param list
     * @return
     */
    @Transactional
    public int createMaterialsAssignStocks(List<CreateReq> list) {

        int count = 0;

        List<WsnaMaterialsAssignStocksDvo> dvos = this.converter.mapAllCreateReqToWsnaMaterialsAssignStocksDvo(list);

        for (WsnaMaterialsAssignStocksDvo dvo : dvos) {
            count += this.mapper.insertQomAsnPrtnrApyIz(dvo);
        }

        return count;
    }

}
