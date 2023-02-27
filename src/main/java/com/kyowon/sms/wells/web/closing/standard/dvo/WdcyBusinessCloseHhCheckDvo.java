package com.kyowon.sms.wells.web.closing.standard.dvo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * <pre>
 * 영업마감통제 서비스 DVO
 * </pre>
 *
 * @author gs.piit183
 * @since 2023-02-06
 */
@Getter
@Setter
@ToString
public class WdcyBusinessCloseHhCheckDvo {

    //테이블 정의가 안된 상태로 작성된 DVO 추후 테이블 구조가 완료 되는 경우 맞게 수정
    private String strtdt;
    private String strtHh;
    private String enddt;
    private String endHh;
    private String perfDt; /* 실적 일자 */
    private String procsPsbYn; /* 처리가능 여부 */
}
