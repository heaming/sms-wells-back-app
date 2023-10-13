package com.kyowon.sms.wells.web.customer.prospective.dvo;

import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * 고객 >> 신규배정관리 Dvo
 * </pre>
 *
 * @author  junho.bae
 * @since 2023-07-01
 */
@Setter
@Getter
public class WcsbPspcCstCnslBasDvo {

    private String pspcCstCnslRsCd; /* 가망고객상담결과코드 */
    private String cnslMoCn; /* 상담메모내용 */
    private String pspcCstCnslId; /* 가망고객상담ID */

}
