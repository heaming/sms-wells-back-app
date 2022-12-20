package com.kyowon.sms.wells.web.service.allocate.dvo;

import lombok.Getter;
import lombok.Setter;

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
public class WsncTotalMngtCstAgrgDvo {

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("filtSellTpCd(필터판매유형코드): ").append(this.filtSellTpCd).append("\n");
        sb.append("mngtYm(관리년월): ").append(this.mngtYm).append("\n");
        sb.append("yyyy(년도): ").append(this.yyyy).append("\n");
        sb.append("typ(구분): ").append(this.typ).append("\n");
        sb.append("typNm(구분명): ").append(this.typNm).append("\n");
        sb.append("acol1(ACOL1): ").append(this.acol1).append("\n");
        sb.append("acol2(ACOL2): ").append(this.acol2).append("\n");
        sb.append("acol3(ACOL3): ").append(this.acol3).append("\n");
        sb.append("acol4(ACOL4): ").append(this.acol4).append("\n");
        sb.append("acol5(ACOL5): ").append(this.acol5).append("\n");
        sb.append("acol6(ACOL6): ").append(this.acol6).append("\n");
        sb.append("acol7(ACOL7): ").append(this.acol7).append("\n");
        sb.append("acol8(ACOL8): ").append(this.acol8).append("\n");
        sb.append("acol9(ACOL9): ").append(this.acol9).append("\n");
        sb.append("acol10(ACOL10): ").append(this.acol10).append("\n");
        sb.append("acol11(ACOL11): ").append(this.acol11).append("\n");
        sb.append("acol12(ACOL12): ").append(this.acol12).append("\n");
        sb.append("tcnt(계): ").append(this.tcnt).append("\n");
        return sb.toString();
    }

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
