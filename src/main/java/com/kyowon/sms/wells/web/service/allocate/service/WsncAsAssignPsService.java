package com.kyowon.sms.wells.web.service.allocate.service;

import com.kyowon.sms.wells.web.service.allocate.converter.WsncAsAssignPsConverter;
import com.kyowon.sms.wells.web.service.allocate.dto.WsncAsAssignPsDto;
import com.kyowon.sms.wells.web.service.allocate.mapper.WsncAsAssignPsMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 *
 * <pre>
 * 배정관리 집계
 * </pre>
 *
 * @author gs.piit122 김동엽
 * @since 2022-12-06
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class WsncAsAssignPsService {

    private final WsncAsAssignPsMapper mapper;
    private final WsncAsAssignPsConverter converter;

    /**
     * 총 관리고객 집계
     *
     * @param dto : 조회파라메터
     * @return 조회결과
     */
    public List<WsncAsAssignPsDto.SearchTotalCustomerRes> getTotalCustomers(
        WsncAsAssignPsDto.SearchTotalCustomerReq dto
    ) {
        return converter.mapAllTotalCustomersDvoToRes(mapper.selectTotalCustomers(dto));
    }

    /**
    * 제품 서비스 현황
    *
    * @param req : 조회파라메터
    * @return 조회결과
    */
    public List<WsncAsAssignPsDto.SearchProductServicesRes> getProductServices(
        WsncAsAssignPsDto.SearchProductServicesReq req
    ) {
        return converter.mapAllDvoToRes(mapper.selectProductServices(req));
    }
}
