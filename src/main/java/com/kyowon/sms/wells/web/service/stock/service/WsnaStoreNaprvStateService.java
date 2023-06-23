package com.kyowon.sms.wells.web.service.stock.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaStoreNaprvStateDto;
import com.kyowon.sms.wells.web.service.stock.mapper.WsnaStoreNaprvStateMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import lombok.RequiredArgsConstructor;

/**
 * <pre>
 *  K-W-SV-U-0126M01 입고 미승인상세현황
 * </pre>
 *
 * @author heymi.cho
 * @since 2023.06.20
 */
@Service
@RequiredArgsConstructor
public class WsnaStoreNaprvStateService {

    private final WsnaStoreNaprvStateMapper mapper;

    /**
     * 입고 미승인현황 - 조회
     *
     * @param dto : { strWareDvCd:입고창고구분코드, strWareNoM:상위창고코드, strWareNoD:창고코드 }
     * @return 조회결과
     */
    public PagingResult<WsnaStoreNaprvStateDto.SearchRes> getStoreNaprvState(
        WsnaStoreNaprvStateDto.SearchReq dto, PageInfo pageInfo
    ) {
        return mapper.selectStoreNaprvState(dto, pageInfo);
    }

    /**
     * 입고 미승인현황 - 조회 (엑셀 다운로드)
     *
     * @param dto : { strWareDvCd:입고창고구분코드, strWareNoM:상위창고코드, strWareNoD:창고코드 }
     * @return 조회결과
     */
    public List<WsnaStoreNaprvStateDto.SearchRes> getStoreNaprvState(WsnaStoreNaprvStateDto.SearchReq dto) {
        return mapper.selectStoreNaprvState(dto);
    }
}
