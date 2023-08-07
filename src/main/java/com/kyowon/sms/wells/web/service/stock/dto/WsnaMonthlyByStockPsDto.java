package com.kyowon.sms.wells.web.service.stock.dto;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

/**
 * <pre>
 * W-SV-U-0136M01 월별 재고현황 dto
 * </pre>
 *
 * @author SaeRomI.Kim
 * @since 2023-08-07
 */

public class WsnaMonthlyByStockPsDto {

    @Builder
    @ApiModel("WsnaMonthlyByStockPsDto-SearchWareReq")
    public record SearchWareReq(
        @NotBlank
        String baseYm,
        @NotBlank
        String wareDvCd,
        String hgrWareNo
    ) {}

    @Builder
    @ApiModel("WsnaMonthlyByStockPsDto-SearchWareRes")
    public record SearchWareRes(
        String wareNo,
        String wareNm
    ) {}

    @Builder
    @ApiModel("WsnaMonthlyByStockPsDto-SearchReq")
    public record SearchReq(

        @NotBlank
        String baseYm,
        @NotBlank
        String wareDvCd,
        String hgrWareNo,
        String wareNo,
        String wareDtlDvCd,
        String itmGdCd,
        String useYn,
        String itmKndCd,
        List<String> itmPdCds,
        String itmPdCd,
        String strtSapCd,
        String endSapCd,
        String matUtlzDvCd

    ) {}

    @Builder
    @ApiModel("WsnaMonthlyByStockPsDto-SearchRes")
    public record SearchRes(

        String sapMatCd,
        String itmPdCd,
        String pdNm,
        // 구매입고
        BigDecimal prchsQty,
        // 안전재고
        BigDecimal sftStocQty,
        // 기초재고
        BigDecimal btdStocQty,
        // 시점재고
        BigDecimal pitmStocQty,
        // 정상입고
        BigDecimal nomStrQty,
        // 물량배정입고
        BigDecimal qomAsnStrQty,
        // 물량이동입고
        BigDecimal qomMmtStrQty,
        // 반품입고내부
        BigDecimal rtngdStrInsiQty,
        // 반품입고외부
        BigDecimal rtngdStrOtsdQty,
        // 기타입고
        BigDecimal etcStrQty,
        // 기타입고1
        BigDecimal etcStrQty1,
        // 재고실사입고
        BigDecimal stocAcinspStrQty,
        // 정상출고
        BigDecimal nomOstrQty,
        // 물량배정출고
        BigDecimal qomAsnOstrQty,
        // 물량이동출고
        BigDecimal qomMmtOstrQty,
        // 반품출고내부
        BigDecimal rtngdOstrInsiQty,
        // 반품출고외부
        BigDecimal rtngdOstrOtsdQty,
        // 판매출고
        BigDecimal sellOstrQty,
        // 폐기출고
        BigDecimal dsuOstrQty,
        // 작업출고
        BigDecimal wkOstrQty,
        // 기타출고
        BigDecimal etcOstrQty,
        // 기타출고1
        BigDecimal etcOstrQty1,
        // 재고실사출고
        BigDecimal stocAcinspOstrQty,
        // 재고상태조정
        BigDecimal stocStatCtrQty,
        // 재고실사
        BigDecimal stocAcinspQty,
        // 이동재고
        BigDecimal mmtStocQty,
        // 무료체험재고
        BigDecimal freExpnStocQty,
        // 리퍼출고
        BigDecimal refrOstrQty,
        // 입고등급조정
        BigDecimal strCtrQty,
        // 출고등급조정
        BigDecimal ostrCtrQty

    ) {}

}
