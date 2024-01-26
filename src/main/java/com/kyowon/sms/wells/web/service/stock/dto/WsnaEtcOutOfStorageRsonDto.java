package com.kyowon.sms.wells.web.service.stock.dto;

import java.math.BigDecimal;

import io.swagger.annotations.ApiModel;

/**
 * <pre>
 * W-SV-U-0274M01 기타출고 사유내역
 * </pre>
 *
 * @author songTaeSung
 * @since 2023.01.13
 */
public class WsnaEtcOutOfStorageRsonDto {
    @ApiModel(value = "WsnaEtcOutOfStorageRsonDto-SearchReq")
    public record SearchReq(
        // 시작출고일자
        String stOstrDt,
        // 종료출고일자
        String edOstrDt,
        // 청구사유코드
        String bilRsonCd,
        // 창고구분코드
        String wareDvCd,
        // 상위창고번호
        String wareNoM,
        // 창고번호
        String wareNoD,
        // 상품그룹코드
        String pdGdCd,
        // 품목구분코드
        String itmKndCd,
        // 시작품목코드
        String startItemCd,
        // 종료품목코드
        String endItemCd

    ) {}
    @ApiModel(value = "WsnaEtcOutOfStorageRsonDto-SearchRes")
    public record SearchRes(
        String sapMatCd, /*SAP코드*/
        String itemNm, /*품목명*/
        String ostrWareNo, /**/
        String itmPdCd, /*품목상품코드*/
        String wareNm, /*창고명*/
        String itmGdCd, /*등큽코드*/
        String ostrDt, /*출고일자*/
        String ostrRsonCd, /*청구사유*/
        int ostrQty, /*수량*/
        BigDecimal csmrUprcAmt, /*소비자가*/
        BigDecimal totalAmt, /*총금액*/
        String sortDvVal, /* 정렬구분 */
        String deptNm, /* 부서명 */
        String rmkCn /*비고*/
    ) {}
}
