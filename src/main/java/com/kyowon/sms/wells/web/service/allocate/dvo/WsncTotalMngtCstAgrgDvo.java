package com.kyowon.sms.wells.web.service.allocate.dvo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * <pre>
 * W-SV-U-0228M01 총 관리고객 집계
 * </pre>
 *
 * @author gs.piit122 김동엽
 * @since 2022-12-06
 */
@Setter
@Getter
@ToString
public class WsncTotalMngtCstAgrgDvo {
    String filtSellTpCd; /*필터판매유형코드*/
    String mngtYm; /*관리년월*/
    String yyyy; /*년도*/
    String typ; /*구분*/
    String typNm; /*구분명*/
    String acol1; /*ACOL1*/
    String acol2; /*ACOL2*/
    String acol3; /*ACOL3*/
    String acol4; /*ACOL4*/
    String acol5; /*ACOL5*/
    String acol6; /*ACOL6*/
    String acol7; /*ACOL7*/
    String acol8; /*ACOL8*/
    String acol9; /*ACOL9*/
    String acol10; /*ACOL10*/
    String acol11; /*ACOL11*/
    String acol12; /*ACOL12*/
    int tcnt;
    int per;
}
