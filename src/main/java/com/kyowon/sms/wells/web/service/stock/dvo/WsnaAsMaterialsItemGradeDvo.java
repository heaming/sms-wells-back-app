package com.kyowon.sms.wells.web.service.stock.dvo;

import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * W-SV-U-0115M01 AS자재 품목등급관리 데이터 생성 dvo
 * </pre>
 *
 * @author SaeRomI.Kim
 * @since 2023-06-15
 */

@Getter
@Setter
public class WsnaAsMaterialsItemGradeDvo {

    // 기준년월
    private String baseYm;
    // 품목상품코드
    private String itmPdCd;
    // 품목종류코드
    private String itmKndCd;
    // 창고번호
    private String wareNo;
    // 창고구분코드
    private String wareDvCd;
    // 조정품목관리등급코드
    private String ctrItmMngtGdCd;
    // 비고내용
    private String rmkCn;
}
