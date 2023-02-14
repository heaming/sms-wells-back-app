package com.kyowon.sms.wells.web.service.stock.dvo;

import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * W-SV-U-0125M01 물량배정 입고창고관리
 * </pre>
 *
 * @author inho.choi
 * @since 2023.02.10
 */
@Getter
@Setter
public class WsnaQomAsnStrWareDvo {
    String prtnrNo; /* 파트너번호 */
    String qomAsnApyYn; /* 물량배정적용여부 */
}
