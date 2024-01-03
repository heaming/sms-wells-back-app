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
        // 기준년월
        @NotBlank
        String baseYm,
        // 창고구분
        @NotBlank
        String wareDvCd,
        // 상위창고번호
        String hgrWareNo
    ) {}

    @Builder
    @ApiModel("WsnaMonthlyByStockPsDto-SearchWareRes")
    public record SearchWareRes(
        // 창고번호
        String wareNo,
        // 창고명
        String wareNm
    ) {}

    @Builder
    @ApiModel("WsnaMonthlyByStockPsDto-SearchReq")
    public record SearchReq(
        // 기준년월
        @NotBlank
        String baseYm,
        // 창고구분
        @NotBlank
        String wareDvCd,
        // 상위창고번호
        String hgrWareNo,
        // 창고번호
        String wareNo,
        // 창고상세구분
        String wareDtlDvCd,
        // 등급
        String itmGdCd,
        // 사용여부
        String useYn,
        // 품목종류
        String itmKndCd,
        // 품목그룹
        String itmGrpCd,
        // 품목코드 리스트
        List<String> itmPdCds,
        // 품목코드
        String itmPdCd,
        // 시작SAP코드
        String strtSapCd,
        // 종료SAP코드
        String endSapCd,
        // 자재그룹
        String svMatGrpCd,
        // 중수리자재여부
        String commGb,
        // 기초자재여부
        String baseGb,
        // 회전율대상여부
        String turnoverGb

    ) {}

    @Builder
    @ApiModel("WsnaMonthlyByStockPsDto-SearchRes")
    public record SearchRes(
        // SAP코드
        String sapMatCd,
        // 품목코드
        String itmPdCd,
        // 품목명
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
