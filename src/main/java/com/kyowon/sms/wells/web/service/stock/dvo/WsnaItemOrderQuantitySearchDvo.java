package com.kyowon.sms.wells.web.service.stock.dvo;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * W-SV-U-0296M01 BS자재 발주수량 산출 조회 dvo
 * </pre>
 *
 * @author SaeRomI.Kim
 * @since 2023-06-28
 */

@Getter
@Setter
public class WsnaItemOrderQuantitySearchDvo {

    // 관리년월
    private String inqrYm;
    // 품목종류코드
    private String itmKndCd;
    // 품목상품코드 리스트
    private List<String> itmPdCds;

    // 품목코드
    private String itmPdCd;

    // 시작 SAP코드
    private String strtSapCd;

    // 종료 SAP코드
    private String endSapCd;

    // OFFSET
    private Integer offSet;
    // FETCH SIZE
    private Integer size;

}
