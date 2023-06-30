package com.kyowon.sms.wells.web.competence.business.dvo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WpsfRuleBaseInquiryDvo {
    private String mnalRghId; /* 매뉴얼권한ID */
    private String bznsSpptMnalId; /* 영업지원매뉴얼ID */
    private String vlStrtDtm; /* 유효시작일시 */
    private String vlEndDtm; /* 유효종료일시 */
    private String ogTpCd; /* 조직유형코드 */
    private String rsbDvCd; /* 직책유형코드 */
    private String bznsSpptMnalRghCd; /* 영업지원매뉴얼권한코드 */
    private String mnalRghRelId; /* 매뉴얼권한관계ID */
    private String dtaDlYn; /* 데이터삭제여부 */
}
