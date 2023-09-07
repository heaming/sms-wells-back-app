package com.kyowon.sms.wells.web.competence.business.dvo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WpsfActivityGoodsDeductionItemizationDvo {
    private String actiGdsDdtnId; /* 활동물품공제ID */
    private String actiGdsAplcId; /* 활동물품신청ID */
    private Long feeDdtnDstAmt; /* 수수료공제배분금액 */
    private String feeDdtnOcDt; /* 수수료공제발생일자 */
    private String procsYn; /* 처리여부 */
    private String procsCn; /* 처리내용 */
    private String dtaDlYn; /* 데이터삭제여부 */

}
