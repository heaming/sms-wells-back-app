package com.kyowon.sms.wells.web.service.stock.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaInstallationStockPsByDayDto.SearchReq;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaInstallationStockPsByDayDto.SearchResCenter;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaInstallationStockPsByDayDto.SearchResPd;
import com.kyowon.sms.wells.web.service.stock.mapper.WsnaInstallationStockPsByDayMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * <pre>
 * K-W-SV-U-0116M01 일자별 설치재고 현황
 * </pre>
 *
 * @author heymi.cho 조혜미
 * @since 2023.07.11
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class WsnaInstallationStockPsByDayService {
    private final WsnaInstallationStockPsByDayMapper mapper;

    public PagingResult<SearchResCenter> getInstallationStockPsByDayCenter(
        SearchReq dto, PageInfo pageInfo
    ) {
        return mapper.selectInstallationStockPsByDayCenter(dto, pageInfo);
    }

    public List<SearchResCenter> getInstallationStockPsByDayCenter(SearchReq dto) {
        return mapper.selectInstallationStockPsByDayCenter(dto);
    }

    public PagingResult<SearchResPd> getInstallationStockPsByDayPd(
        SearchReq dto, PageInfo pageInfo
    ) {
        return mapper.selectInstallationStockPsByDayPd(dto, pageInfo);
    }

    public List<SearchResPd> getInstallationStockPsByDayPd(SearchReq dto) {
        return mapper.selectInstallationStockPsByDayPd(dto);
    }
}
