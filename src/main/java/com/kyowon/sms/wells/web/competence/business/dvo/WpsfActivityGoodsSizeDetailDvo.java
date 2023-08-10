package com.kyowon.sms.wells.web.competence.business.dvo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WpsfActivityGoodsSizeDetailDvo {
    private String actiGdsStddDvId; /* 활동물품규격구분ID */
    private String actiGdsStddCd; /* 활동물품규격코드 */
    private String actiGdsStddNm; /* 활동물품규격명 */
    private Long sortOdr; /* 정렬순서 */
    private String useYn; /* 사용여부 */
    private String dtaDlYn; /* 데이터삭제여부 */
}
