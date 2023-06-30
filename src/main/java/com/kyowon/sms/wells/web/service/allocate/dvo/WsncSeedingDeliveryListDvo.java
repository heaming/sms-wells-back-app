package com.kyowon.sms.wells.web.service.allocate.dvo;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * <pre>
 * 모종 배송내역
 * </pre>
 *
 * @author heymi.cho 조혜미
 * @since 2023-06-26
 */

@Getter
@Setter
public class WsncSeedingDeliveryListDvo {
    String cntrNo;
    String cntrSn;
    String sdingInfo;
    String adr;
    String vstDt;
    String dlvyStatus;
    Integer sellAmt;
}
