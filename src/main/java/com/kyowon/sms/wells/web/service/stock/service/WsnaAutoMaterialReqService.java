package com.kyowon.sms.wells.web.service.stock.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.service.stock.converter.WsnaAutoMaterialReqConverter;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaAutoMaterialReqDto.CreateReq;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaAutoMaterialReqDvo;
import com.kyowon.sms.wells.web.service.stock.mapper.WsnaAutoMaterialReqMapper;

import lombok.RequiredArgsConstructor;

/**
 *
 * <pre>
 * W-SV-S-0065 서비스운영팀 자재자동신청 관련 데이터 이월
 * </pre>
 *
 * @author inho.choi
 * @since 2023.02.17
 */
@Service
@RequiredArgsConstructor
public class WsnaAutoMaterialReqService {
    private final WsnaAutoMaterialReqMapper mapper;
    private final WsnaAutoMaterialReqConverter converter;

    @Transactional
    public int carryOver(CreateReq dto) {
        int cnt = 0;
        WsnaAutoMaterialReqDvo dvo = converter.mapCreateReqToWsnaAutoMaterialReqDvo(dto);
        cnt += mapper.insertOteamMatAutoAplcIz(dvo);
        cnt += mapper.updateItmOstrAkIz(dvo);
        return cnt;
    }
}
