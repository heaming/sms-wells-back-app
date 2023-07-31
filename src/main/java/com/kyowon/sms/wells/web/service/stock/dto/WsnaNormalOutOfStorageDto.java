package com.kyowon.sms.wells.web.service.stock.dto;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

import com.sds.sflex.system.config.validation.validator.ValidDate;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

/**
 * <pre>
 * W-SV-U-0142M01 정상출고 관리
 * </pre>
 *
 * @author inho.choi
 * @since 2023-03-14
 */
public class WsnaNormalOutOfStorageDto {
    @Builder
    @ApiModel(value = "WsnaNormalOutOfStorageDto-SearchReq")
    public record SearchReq(
        @NotBlank
        @ValidDate
        String strHopDtStr,
        @NotBlank
        @ValidDate
        String strHopDtEnd,
        String ostrCnfm,
        String ostrAkTpCd,
        @NotBlank
        String ostrOjWareNo,
        String itmKndCd,
        @NotBlank
        String wareDvCd
    ) {}

    @Builder
    @ApiModel(value = "WsnaNormalOutOfStorageDto-SearchRes")
    public record SearchRes(
        String ostrAkNo,
        String ostrAkTpCd,
        String ostrOjWareNo,
        String strOjWareNo,
        String strOjWareNm,

        String strHopDt,
        String ovivTpCd,
        String rectOstrDt,
        String itmPdCd,
        String ostrAkRgstDt,
        String ostrDtrnYn,
        String pdNm,
        int ostrAkSn,
        String rmkCn

    ) {}

    @Builder
    @ApiModel(value = "WsnaNormalOutOfStorageDto-SearchWarehouse")
    public record SearchWarehouse(
        String wareNo,
        String wareNm
    ) {}

    @Builder
    @ApiModel(value = "WsnaNormalOutOfStorageDto-AskReq")
    public record AskReq(
        @NotBlank
        String itmPdCd,
        @NotBlank
        String strOjWareNo,
        String wareDvCd,
        String wareDtlDvCd
    ) {}

    @Builder
    @ApiModel(value = "WsnaNormalOutOfStorageDto-AskRes")
    public record AskRes(
        String wareMngtPrtnrNo, /*창고파트너번호*/
        String wareNm, /*창고명*/
        String itmPdCd, /*품목상품코드*/
        String wareNo, /*창고번호*/
        String qty /*현재재고수량*/
    ) {}

    @Builder
    @ApiModel(value = "WsnaNormalOutOfStorageDto-CenterRes")
    public record CenterRes(
        String wareNo, /*창고번호*/
        String qty, /*현재재고수량*/
        String wareNm
    ) {}

    @Builder
    @ApiModel(value = "WsnaNormalOutOfStorageDto-DetailReq")
    public record DetailReq(
        @NotBlank
        String ostrAkTpCd,
        @ValidDate
        String ostrAkRgstDt,
        @NotBlank
        String ostrAkNo,
        String itmPdCd,
        @NotBlank
        String strOjWareNo,
        @NotBlank
        String ostrOjWareNo,
        String ostrOjWareNm,
        String strOjWareNm,
        String stckStdGb,
        @ValidDate
        String rgstDt,
        String itmOstrNo,
        @Positive
        Integer ostrSn,
        String ostrTpCd
    ) {}
    @Builder
    @ApiModel(value = "WsnaNormalOutOfStorageDto-DetailRes")
    public record DetailRes(
        String flag,
        String chk,
        String dummyQty,
        String ostrAkTpCd,
        String ostrTpCd,
        String strWareNo,
        String ostrAkRgstDt,
        String ostrAkSn,
        String ostrAkNo,
        String strHopDt,
        String svpdItemKnd,
        String itmPdCd,
        String svpdNmKor,
        String svpdSapCd,
        String itemLoc,
        String pajuLoc,
        String ostrAkWareDvCd,
        String wareMngtPrtnrNo,
        String itmGdCd,
        String reqStckQty,
        String ostrWareNo,
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
        String svpdBaseGb,
        String svpdBaseColorGb,
        String svpdMgtTyp,
        String cfrmCnt,
        String avgOut,
        String ostrWareDvCd,
        String strWareDvCd,
        String ostrWareDtlDvCd,
        String strWareDtlDvCd,
        String strWareNm,
        String ostrWareNm
    ) {}

    @Builder
    @ApiModel(value = "WsnaNormalOutOfStorageDto-CreqteReq")
    public record CreateReq(
        String todayStr,
        String itmOstrNo,
        String itmStrNo,
        String ostrAkTpCd,
        String ostrTpCd,
        String strTpCd,
        String strWareNo,
        @ValidDate
        String ostrAkRgstDt,
        String qty,
        String ostrAkSn,
        String ostrAkNo,
        @ValidDate
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
        String avgOu,
        String ostrWareDvCd,
        String strWareDvCd
    ) {}

    @Builder
    @ApiModel(value = "WsnaNormalOutOfStorageDto-CheckedReq")
    public record CheckedReq(
        String ostrAkNo,
        List ostrAkSns
    ) {}

    @Builder
    @ApiModel(value = "WsnaNormalOutOfStorageDto-StrNoAndOstrNoRes")
    public record StrNoAndOstrNoRes(
        String itmOstrNo,
        String itmStrNo,
        String ostrTpCd,
        String strTpCd,
        String todayStr
    ) {}

    @Builder
    @ApiModel(value = "WsnaNormalOutOfStorageDto-StandardWareReq")
    public record StandardWareReq(
        @NotBlank
        String apyYm,
        @NotBlank
        String wareNo
    ) {}

    @Builder
    @ApiModel(value = "WsnaNormalOutOfStorageDto-StandardWareRes")
    public record StandardWareRes(
        String apyYm,
        String wareNo,
        String stckStdGb
    ) {}

    @Builder
    @ApiModel(value = "WsnaNormalOutOfStorageDto-SearchItmOstrAkReq")
    public record SearchItmOstrAkReq(
        @NotBlank
        String ostrAkNo,
        int ostrAkSn
    ) {}

    @Builder
    @ApiModel(value = "WsnaNormalOutOfStorageDto-SearchItmOstrAkRes")
    public record SearchItmOstrAkRes(
        String ostrAkNo,
        String ostrAkTpCd,
        String strHopDt,
        String ostrAkRgstDt,
        String ostrOjWareNo,
        String strOjWareNo,
        String ostrOjWareNm,
        String strOjWareNm,
        String ostrAkTpNm,
        String itmPdCd,
        String itmOstrNo,
        String ostrTpCd
    ) {}
}
