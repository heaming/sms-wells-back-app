package com.kyowon.sms.wells.web.service.allocate.mapper;

import com.kyowon.sms.wells.web.service.allocate.dto.WsncWellsAsInterfaceDto;
import com.kyowon.sms.wells.web.service.allocate.dvo.WsncWellsAsInterfaceCustInfoDvo;
import org.apache.ibatis.annotations.Mapper;
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
@Mapper
public interface WsncWellsAsInterfaceMapper {

    /**
     * 고객서비스수행내역, 계약주소지기본 테이블을 기준으로 고객정보 조회
     *
     * @param req : 조회파라메터
     * @return 조회결과
     */
    List<WsncWellsAsInterfaceCustInfoDvo> selectCustomerInformations(WsncWellsAsInterfaceDto.SearchCustInfoReq req);

}
