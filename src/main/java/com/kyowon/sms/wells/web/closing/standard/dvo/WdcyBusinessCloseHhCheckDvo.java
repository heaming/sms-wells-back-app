package com.kyowon.sms.wells.web.closing.standard.dvo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * <pre>
 * 영업마감통제 서비스 DVO
 * </pre>
 *
 * @author WOO SEUNGMIN
 * @since 2023-02-06
 */
@Getter
@Setter
@ToString
public class WdcyBusinessCloseHhCheckDvo {
    private String strtdt; /*시작일자*/
    private String strtHh; /*시작시간*/
    private String enddt; /*종료일자*/
    private String endHh; /*종료시간*/
    private String perfDt; /* 실적 일자 */
    private String procsPsbYn; /* 처리가능 여부 */
}
