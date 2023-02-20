package com.kyowon.sms.wells.web.service.stock.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.service.stock.mapper.WsnaMatAutoAplcCrdovrMapper;

import lombok.RequiredArgsConstructor;

/**
 *
 * <pre>
 * W-SV-U-0125M01 물량배정 입고창고관리
 * </pre>
 *
 * @author inho.choi
 * @since 2023.02.17
 */
@Service
@RequiredArgsConstructor
public class WsnaMatAutoAplcCrdovrService {
    private final WsnaMatAutoAplcCrdovrMapper mapper;

    @Transactional
    public int saveMatAutoAplcCrdovrs() {
        int cnt = 0;
        cnt += mapper.insertMatCarried();
        cnt += mapper.updateItmOstrAkIz();
        return cnt;
    }
}
