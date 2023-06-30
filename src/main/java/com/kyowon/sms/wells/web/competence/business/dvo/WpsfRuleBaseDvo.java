package com.kyowon.sms.wells.web.competence.business.dvo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WpsfRuleBaseDvo {
    private String bznsSpptMnalId; /* 영업지원매뉴얼ID */
    private String vlStrtDtm; /* 유효시작일시 */
    private String vlEndDtm; /* 유효종료일시 */
    private String bznsSpptMnalNm; /* 영업지원매뉴얼명 */
    private String hgrBznsSpptMnalId; /* 상위영업지원매뉴얼ID */
    private String bznsSpptMnalRgstCd; /* 영업지원매뉴얼등록코드 */
    private String bznsSpptMnalMpblDvCd; /* 영업지원매뉴얼공개구분코드 */
    private String bznsSpptMnalChCn; /* 영업지원매뉴얼변경내용 */
    private String mnalRghRelId; /* 매뉴얼권한관계ID */
    private Integer inqrLvTcnt; /* 조회단계차수 */
    private Long expsrOdr; /* 노출순서 */
    private String apnFileDocId; /* 첨부파일문서ID */
    private String dtaDlYn; /* 데이터삭제여부 */
}
