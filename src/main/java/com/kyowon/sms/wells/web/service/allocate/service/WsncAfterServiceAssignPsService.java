package com.kyowon.sms.wells.web.service.allocate.service;

import com.kyowon.sms.wells.web.service.allocate.converter.WsncAfterServiceAssignPsConverter;
import com.kyowon.sms.wells.web.service.allocate.dto.WsncAfterServiceAssignPsDto;
import com.kyowon.sms.wells.web.service.allocate.mapper.WsncAfterServiceAssignPsMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 *
 * <pre>
 * W-SV-U-0229M01 제품 서비스 현황
 * </pre>
 *
 * @author gs.piit122 김동엽
 * @since 2022-12-29
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class WsncAfterServiceAssignPsService {

    private final WsncAfterServiceAssignPsMapper mapper;
    private final WsncAfterServiceAssignPsConverter converter;

    /**
     * 상품각사속성상세(ST101TB), 고객서비스수행배정내역(AC221TB, AC261TB), 고객서비스AS설치대상내역(AC211TB), 고객서비스BS대상내역(AC251TB) 테이블을 JOIN하여 가져온다
     *
     * @param req : 조회파라메터
     * @return 조회결과
     */
    public List<WsncAfterServiceAssignPsDto.SearchRes> getProductServiceStates(
        WsncAfterServiceAssignPsDto.SearchReq req
    ) {
        return converter.mapAllDvoToRes(mapper.selectProductServiceStates(req));
    }

}
