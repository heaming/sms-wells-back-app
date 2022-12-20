package com.kyowon.sms.wells.web.service.allocate.dto;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

/**
 *
 * <pre>
 * W-SV-U-0228M01 총 관리고객 집계
 * </pre>
 *
 * @author gs.piit122 김동엽
 * @since 2022-12-06
 */
public class WsncTotalMngtCstAgrgDto {

    @ApiModel(value = "WsncTotalMngtCstAgrgDto-SearchReq")
    public record SearchReq(
        String year,
        String pdGdCd,
        String pdCd
    ) {
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("mngtYm(관리년월): ").append(year).append("\n");
            sb.append("pdGdCd(상품등급코드): ").append(pdGdCd).append("\n");
            sb.append("pdCd(상품코드): ").append(pdCd).append("\n");
            return sb.toString();
        }
    }
    @ApiModel(value = "WsncTotalMngtCstAgrgDto-SearchRes")
    public record SearchRes(
        String filtSellTpCd, /*필터판매유형코드*/
        String mngtYm, /*관리년월*/
        String yyyy, /*년도*/
        String typ, /*구분*/
        String typNm, /*구분명*/
        String acol1, /*ACOL1*/
        String acol2, /*ACOL2*/
        String acol3, /*ACOL3*/
        String acol4, /*ACOL4*/
        String acol5, /*ACOL5*/
        String acol6, /*ACOL6*/
        String acol7, /*ACOL7*/
        String acol8, /*ACOL8*/
        String acol9, /*ACOL9*/
        String acol10, /*ACOL10*/
        String acol11, /*ACOL11*/
        String acol12, /*ACOL12*/
        int tcnt,
        int per
    ) {
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("filtSellTpCd(필터판매유형코드): ").append(filtSellTpCd).append("\n");
            sb.append("mngtYm(관리년월): ").append(mngtYm).append("\n");
            sb.append("yyyy(년도): ").append(yyyy).append("\n");
            sb.append("typ(구분): ").append(typ).append("\n");
            sb.append("typNm(구분명): ").append(typNm).append("\n");
            sb.append("acol1(ACOL1): ").append(acol1).append("\n");
            sb.append("acol2(ACOL2): ").append(acol2).append("\n");
            sb.append("acol3(ACOL3): ").append(acol3).append("\n");
            sb.append("acol4(ACOL4): ").append(acol4).append("\n");
            sb.append("acol5(ACOL5): ").append(acol5).append("\n");
            sb.append("acol6(ACOL6): ").append(acol6).append("\n");
            sb.append("acol7(ACOL7): ").append(acol7).append("\n");
            sb.append("acol8(ACOL8): ").append(acol8).append("\n");
            sb.append("acol9(ACOL9): ").append(acol9).append("\n");
            sb.append("acol10(ACOL10): ").append(acol10).append("\n");
            sb.append("acol11(ACOL11): ").append(acol11).append("\n");
            sb.append("acol12(ACOL12): ").append(acol12).append("\n");
            sb.append("tCnt(계): ").append(tcnt).append("\n");
            sb.append("per(비율): ").append(per).append("\n");
            return sb.toString();
        }
    }
}
