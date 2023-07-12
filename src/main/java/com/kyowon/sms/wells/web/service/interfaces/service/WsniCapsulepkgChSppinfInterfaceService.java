package com.kyowon.sms.wells.web.service.interfaces.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.service.interfaces.dto.WsniCapsulepkgChSppinfDto.SearchReq;
import com.kyowon.sms.wells.web.service.interfaces.dto.WsniCapsulepkgChSppinfDto.SearchRes;
import com.kyowon.sms.wells.web.service.interfaces.mapper.WsniCapsulepkgChSppinfMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * <pre>
 * W-SV-I-0020 Wells홈페이지 홈카페 캡슐 패키지 변경을 위한 배송 정보 리스트
 * </pre>
 *
 * @author yeonghwa.cheon
 * @since 2023.03.20
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class WsniCapsulepkgChSppinfInterfaceService {

    private final WsniCapsulepkgChSppinfMapper mapper;

    public List<SearchRes> getCapsulepkgChSppinfs(SearchReq dto) {
        /* db2 테이블 확인 된 후 로직 전체적으로 수정 예정 */
        return mapper.selectCapsulepkgChSppinfs(dto);
    }

}
