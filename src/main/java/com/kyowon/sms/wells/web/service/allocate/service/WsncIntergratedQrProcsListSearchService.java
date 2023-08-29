package com.kyowon.sms.wells.web.service.allocate.service;

import static com.kyowon.sms.wells.web.service.allocate.dto.WsncIntergratedQrProcsListSearchDto.*;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.service.allocate.converter.WsncIntergratedQrProcsListSearchConverter;
import com.kyowon.sms.wells.web.service.allocate.mapper.WsncIntergratedQrProcsListSearchMapper;

import lombok.RequiredArgsConstructor;

/**
 *
 * <pre>
 * K-W-SV-U-0239M01 통합QR 처리현황
 * </pre>
 *
 * @author 37758 이재훈
 * @since 2023.08.25
 */
@Service
@RequiredArgsConstructor
public class WsncIntergratedQrProcsListSearchService {

    private final WsncIntergratedQrProcsListSearchMapper mapper;
    private final WsncIntergratedQrProcsListSearchConverter converter;

    public List<SearchByOgRes> getByOgProcsList(SearchReq dto) {
        return converter.mapSearchByOgResDvo(mapper.selectByOgList(dto));
    }

    public List<SearchOgDetailRes> getOgDetailProcsList(SearchReq dto) {
        return converter.mapSearchOgDetailResDvo(mapper.selectOgDetailList(dto));
    }
}
