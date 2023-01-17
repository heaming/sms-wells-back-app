package com.kyowon.sms.wells.web.service.stock.service;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaEtcOutOfStorageRsonDto;
import com.kyowon.sms.wells.web.service.stock.mapper.WsnaEtcOutOfStorageRsonMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaEtcOutOfStorageRsonDto.*;

/**
 *
 * <pre>
 * W-SV-U-0274M01 기타출고 사유내역
 * </pre>
 *
 * @author songtaesung
 * @since 2023.01.13
 */
@Service
@RequiredArgsConstructor
public class WsnaEtcOutOfStorageRsonService {

    private final WsnaEtcOutOfStorageRsonMapper mapper;

    public List<SearchRes> getEtcOutOfStorageRsons(SearchReq dto) {
        return this.mapper.selectEtcOutOfStorageRsons(dto);
    }
}
