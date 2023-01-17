package com.kyowon.sms.wells.web.service.allocate.mapper;

import com.kyowon.sms.wells.web.service.allocate.dto.WsncAsAssignPsDto;
import com.kyowon.sms.wells.web.service.allocate.dvo.WsncAsAssProductServicesDvo;
import com.kyowon.sms.wells.web.service.allocate.dvo.WsncAsAssTotalCustomerDvo;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface WsncAsAssignPsMapper {

    /**
     * 총 관리고객 집계
     *
     * @param dto : 조회파라메터
     * @return 조회결과
     */
    List<WsncAsAssTotalCustomerDvo> selectTotalCustomers(WsncAsAssignPsDto.SearchTotalCustomerReq dto);

    /**
     * 상품각사속성상세(ST101TB), 고객서비스수행배정내역(AC221TB, AC261TB), 고객서비스AS설치대상내역(AC211TB), 고객서비스BS대상내역(AC251TB) 테이블을 JOIN하여 가져온다
     *
     * @param req : 조회파라메터
     * @return 조회결과
     */
    List<WsncAsAssProductServicesDvo> selectProductServices(WsncAsAssignPsDto.SearchProductServicesReq req);
}
