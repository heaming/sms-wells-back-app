package com.kyowon.sms.wells.web.service.allocate.mapper;

import com.kyowon.sms.wells.web.service.allocate.dvo.WsncCapsuleSubscriptionCustomerDvo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 *
 *
 * W-SV-S-0049 홈카페 캡슐 정기구매 고객 스케쥴 인서트/업데이트
 *
 *
 * @author gs.piit122 김동엽
 * @since 2023-04-13
 */
@Mapper
public interface WsncCapsuleSubscriptionCustomerMapper {
    List<WsncCapsuleSubscriptionCustomerDvo> selectCapsuleRglrPrchsCsts(String baseYm);

    int deleteBfsvcPrd(String cntrNo, String cntrSn);

    int updateCancelDate(String cntrNo, String cntrSn, String canDt);

    int updateIstDt(String cntrNo, String cntrSn);

    int deleteSchd(String cntrNo);
}
