package com.kyowon.sms.wells.web.service.stock.service;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaItemOrderQuantityDto.SearchReq;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.service.stock.converter.WsnaItemOrderQuantityConverter;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaItemOrderQuantityDvo;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaItemOrderQuantitySearchDvo;
import com.kyowon.sms.wells.web.service.stock.mapper.WsnaItemOrderQuantityMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import lombok.RequiredArgsConstructor;

/**
 * <pre>
 * W-SV-U-0296M01 BS자재 발주수량 산출 서비스
 * </pre>
 *
 * @author SaeRomI.Kim
 * @since 2023-06-28
 */

@Service
@RequiredArgsConstructor
public class WsnaItemOrderQuantityService {

    private final WsnaItemOrderQuantityMapper mapper;

    private final WsnaItemOrderQuantityConverter converter;

    /**
     * BS자재 발주수량 산출 페이징 조회
     * @param dto
     * @param pageInfo
     * @return
     */
    public PagingResult<WsnaItemOrderQuantityDvo> getItemOrderQuantityPaging(SearchReq dto, PageInfo pageInfo) {

        WsnaItemOrderQuantitySearchDvo searchDvo = this.converter.mapSearchReqToWsnaItemOrderQuantitySearchDvo(dto);

        boolean needTotalCount = pageInfo.isNeedTotalCount();
        // 총 건수를 조회해야 할 경우
        if (needTotalCount) {
            // 건수 조회
            long totalCount = this.mapper.selectItemOrderQuantityCount(searchDvo);
            pageInfo.setTotalCount(totalCount);
            pageInfo.setNeedTotalCount(Boolean.FALSE);
        }

        int pageIndex = pageInfo.getPageIndex();
        int pageSize = pageInfo.getPageSize();

        int startRow = (pageIndex - 1) * pageSize;
        searchDvo.setOffSet(startRow);
        searchDvo.setSize(pageSize);

        List<WsnaItemOrderQuantityDvo> itmOrdQtys = this.mapper.selectItemOrderQuantitiy(searchDvo);

        return new PagingResult<>(itmOrdQtys, pageInfo);
    }

    /**
     * BS자재 발주수량 산출 엑셀 다운로드
     * @param dto
     * @return
     */
    public List<WsnaItemOrderQuantityDvo> getItemOrderQuantityExcelDownload(SearchReq dto) {
        WsnaItemOrderQuantitySearchDvo searchDvo = this.converter.mapSearchReqToWsnaItemOrderQuantitySearchDvo(dto);

        return this.mapper.selectItemOrderQuantitiy(searchDvo);
    }

}
