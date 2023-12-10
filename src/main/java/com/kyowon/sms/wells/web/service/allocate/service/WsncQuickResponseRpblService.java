package com.kyowon.sms.wells.web.service.allocate.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.service.allocate.dto.WsncQuickResponseRpblDto;
import com.kyowon.sms.wells.web.service.allocate.mapper.WsncQuickResponseRpblMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import lombok.RequiredArgsConstructor;

/**
 * <pre>
 * [WSNC] 고객 QR코드 재발행 내역 조회
 * </pre>
 *
 * @author  juno.cha
 * @since 2023-03-13
 */
@Service
@RequiredArgsConstructor
public class WsncQuickResponseRpblService {

    private final WsncQuickResponseRpblMapper mapper;

    public PagingResult<WsncQuickResponseRpblDto.SearchRes> getQuickResponseRpbls(
        WsncQuickResponseRpblDto.SearchReq dto, PageInfo pageInfo
    ) {
        return mapper.selectQuickResponseRpbl(dto, pageInfo);
    }
    public List<WsncQuickResponseRpblDto.SearchRes> getQuickResponseRpblsExcelDownload(
        WsncQuickResponseRpblDto.SearchReq dto
    ) {
        return mapper.selectQuickResponseRpbl(dto);
    }

    public int saveQuickResponseRpbls(
        WsncQuickResponseRpblDto.SearchReq dto
    ) {
        return mapper.updateQuickResponseRpbl(dto);
    }
}
