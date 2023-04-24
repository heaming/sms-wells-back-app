package com.kyowon.sms.wells.web.product.standard.dvo;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class WpdyTypeVariableBaseDvo {
    private String sellTpCd; /* 판매유형코드 */
    private Integer varbSn; /* 변수일련번호 */
    private String choFxnDvCd; /* 선택고정구분코드 */
    private String rgltnVarbId; /* 규칙변수ID */
    private Long sortOdr; /* 정렬순서 */
    private String tempSaveYn; /* 임시저장여부 */
    private String dtaDlYn; /* 데이터삭제여부 */
}
