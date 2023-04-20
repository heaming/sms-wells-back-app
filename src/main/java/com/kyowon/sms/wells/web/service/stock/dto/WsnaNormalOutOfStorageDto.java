package com.kyowon.sms.wells.web.service.stock.dto;

import java.util.List;

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
        String wareLocaraCd,
        String apyYm
    ) {}

    @ApiModel(value = "WsnaNormalOutOfStorageDto-SearchRes")
    public record SearchRes(
        String ostrAkNo,
        String ostrAkTpCd,
        String ostrOjWareNm,
        String ostrOjWareNo,
        String strOjWareNm,
        String strOjWareNo,
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

    @ApiModel(value = "WsnaNormalOutOfStorageDto-DetailReq")
    public record DetailReq(
        String ostrAkTpCd,
        String ostrAkRgstDt,
        String ostrAkNo,
        String itmPdCd,
        String strWareNo,
        String ostrWareNo,
        String ostrOjWareNm,
        String strOjWareNm,
        String stckStdGb
    ) {}
    @ApiModel(value = "WsnaNormalOutOfStorageDto-DetailRes")
    public record DetailRes(
        String flag,
        String chk,
        String ostrAkTpCd,
        String ostrTpCd,
        String strWareNo,
        String ostrAkRgstDt,
        String ostrAkSn,
        String ostrAkNo,
        String strHopDt,
        String pdPrpVal19,
        String itmPdCd,
        String itmCd,
        String pdNm,
        String sapMatCd,
        String itemLoc,
        String pajuLoc,
        String ostrAkWareDvCd,
        String wareMngtPrtnrNo,
        String itmGdCd,
        String reqStckQty,
        String ostrWareNo,
        String ostrWareMngtPrtnrNo,
        String mngtUnitCd,
        String mgtUntNm,
        String boxUnitQty,
        String ostrAkQty,
        String qty,
        String ostrCnfmQty,
        String rmkCn,
        String ostrCnfmCd,
        String rectOstrDt,
        String ostrAggQty,
        String outQty,
        String outQtyOrg,
        String strConfDt,
        String pdPrpVal15,
        String pdPrpVal16,
        String pdPrpVal02,
        String cfrmCnt,
        String avgOut
    ) {}

    @ApiModel(value = "WsnaNormalOutOfStorageDto-CreqteReq")
    public record CreateReq(
        String todayStr,
        String itmOstrNo,
        String itmStrNo,
        String ostrAkTpCd,
        String ostrTpCd,
        String strTpCd,
        String strWareNo,
        String ostrAkRgstDt,
        String qty,
        String ostrAkSn,
        String ostrAkNo,
        String strHopDt,
        String pdPrpVal19,
        String itmPdCd,
        String itmCd,
        String pdNm,
        String sapMatCd,
        String itemLoc,
        String pajuLoc,
        String ostrAkWareDvCd,
        String wareMngtPrtnrNo,
        String itmGdCd,
        String reqStckQty,
        String ostrWareNo,
        String ostrWareMngtPrtnrNo,
        String mngtUnitCd,
        String mgtUntNm,
        String boxUnitQty,
        String ostrAkQty,
        String ostrCnfmQty,
        String rmkCn,
        String ostrCnfmCd,
        String rectOstrDt,
        String ostrAggQty,
        String outQty,
        String outQtyOrg,
        String strConfDt,
        String pdPrpVal15,
        String pdPrpVal16,
        String pdPrpVal02,
        String cfrmCnt,
        String avgOu
    ) {}

    @ApiModel(value = "WsnaNormalOutOfStorageDto-CheckedReq")
    public record CheckedReq(
        String ostrAkNo,
        List ostrAkSns
    ) {}

    @ApiModel(value = "WsnaNormalOutOfStorageDto-StrNoAndOstrNoRes")
    public record StrNoAndOstrNoRes(
        String itmOstrNo,
        String itmStrNo,
        String ostrTpCd,
        String strTpCd,
        String todayStr
    ) {}
    public record NomalOutOfStorageReq() {

    }
}
