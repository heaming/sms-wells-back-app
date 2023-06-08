package com.kyowon.sms.wells.web.promotion.manage.dvo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WpmbPromotionObjectCustomerDvo {
    private String pmotOjRelId;                     /* 계약번호 */
    private String histStrtDtm;                     /* 이력시작일시 */
    private String histEndDtm;                      /* 이력종료일시 */
    private String cntrNo;                          /* 계약번호 */
    private String cntrSn;                          /* 계약일련번호 */
    private String vlStrtDtm;                       /* 유효시작일시 */
    private String vlEndDtm;                        /* 유효종료일시 */
    private String pmotOjSpcDscDvCd;                /* 특별할인코드 */
    private String pdCd;                            /* 상품코드 */
    private String sellTpCd;                        /* 판매유형코드 */
    private String dtaDlYn;                         /* 데이터삭제여부 */
    private String fstRgstDtm;                      /* 최초등록일 */
    private String fstRgstUsrId;                    /* 최초등록 사용자ID */
    private String fstRgstUsrNm;                    /* 최초등록 사용자 이름 */
    private String fnlMdfcDtm;                      /* 최종수정일 */
    private String fnlMdfcUsrId;                    /* 최종수정 사용자ID */
    private String fnlMdfcUsrNm;                    /* 최종수정 사용자이름 */
}
