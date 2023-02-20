package com.kyowon.sms.wells.web.service.stock.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.service.stock.converter.WsnaMatAutoAplcCrdovrConverter;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaMatAutoAplcCrdovrDto.CreateReq;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaMatAutoAplcCrdovrDvo;
import com.kyowon.sms.wells.web.service.stock.mapper.WsnaMatAutoAplcCrdovrMapper;

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
public class WsnaMatAutoAplcCrdovrService {
    private final WsnaMatAutoAplcCrdovrMapper mapper;
    private final WsnaMatAutoAplcCrdovrConverter converter;

    @Transactional
    public int saveMatAutoAplcCrdovrs(CreateReq dto) {
        int cnt = 0;

        WsnaMatAutoAplcCrdovrDvo dvo = converter.mapCreateReqToWsnaMatAutoAplcCrdovrDvo(dto);

        cnt += mapper.insertMatAutoAplcCrdovr(dvo);
        cnt += mapper.updateMatAutoAplcCrdovr(dvo);

        return cnt;
    }
}
