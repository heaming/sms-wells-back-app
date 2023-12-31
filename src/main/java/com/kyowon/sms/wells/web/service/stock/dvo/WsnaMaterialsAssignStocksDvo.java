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
public class WsnaMaterialsAssignStocksDvo {
    private String prtnrNo; /* 파트너번호 */
    private String qomAsnApyYn; /* 물량배정적용여부 */
    private String ogTpCd; /* 조직유형코드 */
}
