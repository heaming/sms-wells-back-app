package com.kyowon.sms.wells.web.service.interfaces.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.service.interfaces.dto.WsniPointmallEgerAcptestatDto.SearchReq;
import com.kyowon.sms.wells.web.service.interfaces.dto.WsniPointmallEgerAcptestatDto.SearchRes;
import com.kyowon.sms.wells.web.service.interfaces.mapper.WsniPointmallEgerAcptestatMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * <pre>
 * W-SV-I-0018 포인트몰 금융리스 안마의자,전기레인지 엔지니어 수락상태값 조회
 * </pre>
 *
 * @author yeonghwa.cheon
 * @since 2023.04.20
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class WsniPointmallEgerAcptestatService {

    private final WsniPointmallEgerAcptestatMapper mapper;

    public List<SearchRes> getPointmallEgerAcptestats(SearchReq dto) {
        /* db2 테이블 확인 된 후 로직 전체적으로 수정 예정 */
        return mapper.selectPointmallEgerAcptestats(dto);
    }

}
