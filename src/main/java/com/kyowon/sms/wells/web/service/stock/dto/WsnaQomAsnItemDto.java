package com.kyowon.sms.wells.web.service.stock.dto;

import java.math.BigDecimal;

import io.swagger.annotations.ApiModel;

/**
 * <pre>
 * 물량배정 품목 조회 Dto
 * </pre>
 *
 * @author SaeRomI.Kim
 * @since 2023-11-09
 */

public class WsnaQomAsnItemDto {

    @ApiModel("WsnaQomAsnItemDto-FindRes")
    public record FindRes(

        // 출고유형
        String ostrTpNm,
        // 출고창고
        String ostrWareNm,
        // 배정정보 (배정년월/회차)
        String asnInfo,
        // 물류출고요청번호
        String lgstOstrAkNo,
        // 출고일자
        String ostrDt,
        // 입고창고
        String strWareNm

    ) {}

    @ApiModel("WsnaQomAsnItemDto-FindDetailRes")
    public record FindDetailRes(

        // SAP코드
        String sapMatCd,
        // 품목코드
        String itmPdCd,
        // 품목명
        String pdAbbrNm,
        // 확정수량
        BigDecimal ostrQty,
        // 재고수량(개인)
        BigDecimal stocQty,
        // 입고확인일자
        String strConfDt,
        // 비고
        String rmkCn
    ) {}

}
