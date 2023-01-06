package com.kyowon.sms.wells.web.service.stock.service;

import com.kyowon.sms.wells.web.service.stock.converter.WsnaOutOfStorageAskRgstConverter;
import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaOutOfStorageAskRgstDto;
import com.kyowon.sms.wells.web.service.stock.mapper.WsnaOutOfStorageAskRgstMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import lombok.RequiredArgsConstructor;

/**
 *
 * <pre>
 * W-SV-U-0172P01 출고요청 등록
 * </pre>
 *
 * @author gs.piit130 김혜원
 * @since 2022.12.02
 */
@Service
@RequiredArgsConstructor
public class WsnaOutOfStorageAskRgstService {

    private final WsnaOutOfStorageAskRgstMapper mapper;

    private final WsnaOutOfStorageAskRgstConverter converter;

    public PagingResult<WsnaOutOfStorageAskRgstDto.SearchRes> getOutOfStorageAsk(
        WsnaOutOfStorageAskRgstDto.SearchReq dto, PageInfo pageInfo
    ) {
        return new PagingResult<>(
            this.converter.mapDvoListToSearchResDtoList(this.mapper.selectOutOfStorageAsks(dto, pageInfo)), pageInfo
        );
    }

}
