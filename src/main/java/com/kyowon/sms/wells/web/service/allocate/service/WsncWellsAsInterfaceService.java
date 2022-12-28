package com.kyowon.sms.wells.web.service.allocate.service;

import com.kyowon.sms.wells.web.service.allocate.converter.WsncWellsAsInterfaceConverter;
import com.kyowon.sms.wells.web.service.allocate.dto.WsncWellsAsInterfaceDto;
import com.kyowon.sms.wells.web.service.allocate.mapper.WsncWellsAsInterfaceMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 *
 * <pre>
 * W-SV-I-0021 Wells 인터페이스 AS 고객 정보 조회
 * </pre>
 *
 * @author gs.piit122 김동엽
 * @since 2022-12-28
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class WsncWellsAsInterfaceService {

    private final WsncWellsAsInterfaceMapper mapper;
    private final WsncWellsAsInterfaceConverter converter;

    /**
     * 고객서비스수행내역, 계약주소지기본 테이블을 기준으로 고객정보 조회
     *
     * @param req : 조회파라메터
     * @return 조회결과
     */
    public List<WsncWellsAsInterfaceDto.SearchCustInfoRes> getCustomerInformations(
        WsncWellsAsInterfaceDto.SearchCustInfoReq req
    ) {
        return converter.mapAllListCustInfoDvoToListRes(mapper.selectCustomerInformations(req));
    }

}
