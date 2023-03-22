package com.kyowon.sms.wells.web.service.stock.dto;

import io.swagger.annotations.ApiModel;

/**
 * <pre>
 * W-SV-U-0142M01 정상출고 관리
 * </pre>
 *
 * @author inho.choi
 * @since 2023-03-14
 */
public class WsnaNormalOutOfStorageDto {
    @ApiModel(value = "WsnaNormalOutOfStorageDto-SearchReq")
    public record SearchReq(
        String strHopDtStr,
        String strHopDtEnd,
        String ostrCnfm,
        String ostrAkTpCd,
        String ostrOjWareNo,
        String itmKndCd,
        String wareDvCd,
        String wareLocaraCd
    ) {}

    @ApiModel(value = "WsnaNormalOutOfStorageDto-SearchRes")
    public record SearchRes(
        String ostrAkNo,
        String ostrAkTpCd,
        String ostrOjWareNo,
        String ostrOjWareNm,
        String strHopDt,
        String ovivTpCd,
        String rectOstrDt,
        String ostrDtrnYn,
        String itmPdCd,
        String pdNm,
        String rmkCn

    ) {}

    @ApiModel(value = "WsnaNormalOutOfStorageDto-SearchWarehouse")
    public record SearchWarehouse(
        String wareNo,
        String wareNm
    ) {}

    @ApiModel(value = "WsnaNormalOutOfStorageDto-AskReq")
    public record AskReq(
        String itmPdCd,
        String strOjWareNo
    ) {}

    @ApiModel(value = "WsnaNormalOutOfStorageDto-AskRes")
    public record AskRes(
        String wareMngtPrtrnNo, /*창고파트너번호*/
        String wareNm, /*창고명*/
        String itmPdCd, /*품목상품코드*/
        String wareNo, /*창고번호*/
        String qty /*현재재고수량*/
    ) {}

    @ApiModel(value = "WsnaNormalOutOfStorageDto-CenterRes")
    public record CenterRes(
        String wareNo, /*창고번호*/
        String qty, /*현재재고수량*/
        String ogNm /*조직명*/
    ) {}
}
