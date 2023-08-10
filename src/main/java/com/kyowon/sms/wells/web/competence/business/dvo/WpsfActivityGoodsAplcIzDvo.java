package com.kyowon.sms.wells.web.competence.business.dvo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WpsfActivityGoodsAplcIzDvo {
    private String actiGdsAplcId; /* 활동물품신청ID */
    private String ogTpCd; /* 조직유형코드 */
    private String actiGdsCd; /* 활동물품코드 */
    private Integer actiGdsSn; /* 활동물품일련번호 */
    private Long aplcQty; /* 신청수량 */
    private String actiGdsStddCd; /* 활동물품규격구분코드*/
    private String prtnrNo; /* 파트너번호 */
    private String dtaDlYn; /* 데이터삭제여부 */

    private Integer newActiGdsSn; /* 활동물품일련번호 */

}
