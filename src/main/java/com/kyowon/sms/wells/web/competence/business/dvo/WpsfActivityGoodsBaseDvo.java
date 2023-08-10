package com.kyowon.sms.wells.web.competence.business.dvo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WpsfActivityGoodsBaseDvo {
    private String ogTpCd; /* 조직유형코드 */
    private String actiGdsCd; /* 활동물품코드 */
    private Integer actiGdsSn; /* 활동물품일련번호 */
    private String actiGdsNm; /* 활동물품명 */
    private Long actiGdsAmt; /* 활동물품금액 */
    private Integer patDdtnMcn; /* 분할공제개월수 */
    private String vlStrtdt; /* 유효시작일자 */
    private String vlEnddt; /* 유효종료일자 */
    private String actiGdsStddDvId; /* 활동물품규격구분id*/
    private String useYn; /* 사용여부 */
    private String dtaDlYn; /* 데이터삭제여부 */

}
