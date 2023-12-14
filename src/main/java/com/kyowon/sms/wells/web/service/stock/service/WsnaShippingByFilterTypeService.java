package com.kyowon.sms.wells.web.service.stock.service;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaShippingByFilterTypeDto.*;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.service.stock.converter.WsnaShippingByFilterTypeConverter;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaShippingByFilterTypeDvo;
import com.kyowon.sms.wells.web.service.stock.mapper.WsnaShippingByFilterTypeMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import lombok.RequiredArgsConstructor;

/**
 * <pre>
 * W-SV-U-0261M01 필터 종류별 출고내역 서비스
 * </pre>
 *
 * @author SaeRomI.Kim
 * @since 2023-08-09
 */

@Service
@RequiredArgsConstructor
public class WsnaShippingByFilterTypeService {

    private final WsnaShippingByFilterTypeMapper mapper;

    private final WsnaShippingByFilterTypeConverter converter;

    /**
     * 품목 조회
     * @return
     */
    public List<SearchPdRes> getShippingByFilterProducts() {
        return this.mapper.selectShippingByFilterProducts();
    }

    /**
     * 필터 종류별 출고내역 페이징 조회
     * @param dto
     * @param pageInfo
     * @return
     */
    public PagingResult<SearchRes> getShippingByFilterTypesPaging(SearchReq dto, PageInfo pageInfo) {
        return this.mapper.selectShippingByFilterTypes(dto, pageInfo);
    }

    /**
     * 필터 종류별 출고내역 엑셀 다운로드
     * @param dto
     * @return
     */
    public List<SearchRes> getShippingByFilterTypesExcelDownload(SearchReq dto) {
        return this.mapper.selectShippingByFilterTypes(dto);
    }

    /**
     * 필터 종류별 출고내역 저장
     * @param dtos
     * @return
     */
    @Transactional
    public int editShippingByFilterTypes(List<EditReq> dtos) {

        int count = 0;

        List<WsnaShippingByFilterTypeDvo> dvos = this.converter.mapAllEditRegToWsnaShippingByFilterTypeDvo(dtos);

        for (WsnaShippingByFilterTypeDvo dvo : dvos) {
            count += this.mapper.updateSvWkOstrIz(dvo);
        }

        return count;
    }

}
