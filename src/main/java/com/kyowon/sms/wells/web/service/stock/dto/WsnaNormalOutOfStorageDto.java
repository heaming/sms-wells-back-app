package com.kyowon.sms.wells.web.service.stock.dto;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
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
        @NotBlank
        String wareDvCd
    ) {}

    @Builder
    @ApiModel(value = "WsnaNormalOutOfStorageDto-CenterReq")
    public record CenterReq(
        @NotBlank
        String itmPdCd,
        @NotBlank
        String wareDvCd
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
        @NotBlank
        String ostrAkNo,
        @NotBlank
        String strOjWareNo,
        @NotBlank
        String ostrOjWareNo,
        @NotBlank
        String stckStdGb,
        @NotBlank
        @ValidDate
        String rgstDt
    ) {}

    @Builder
    @ApiModel(value = "WsnaNormalOutOfStorageDto-DetailRemoveReq")
    public record DetailRemoveReq(
        @NotBlank
        String ostrTpCd,
        @NotBlank
        String itmOstrNo,
        @NotBlank
        String strOjWareNo,
        @NotBlank
        String ostrOjWareNo,
        @NotBlank
        String stckStdGb,
        @NotBlank
        @ValidDate
        String ostrDt
    ) {}

    @Builder
    @ApiModel(value = "WsnaNormalOutOfStorageDto-RemoveReq")
    public record RemoveReq(
        @NotBlank
        String itmOstrNo,
        @Positive
        int ostrSn,
        @NotBlank
        String ostrAkNo,
        @Positive
        int ostrAkSn,
        @NotBlank
        String itmStrNo,
        @Positive
        int strSn,
        @NotBlank
        String ostrTpCd,
        @NotBlank
        String strTpCd,
        @NotBlank
        String ostrOjWareNo,
        String ostrWareDvCd,
        String ostrPrtnrNo,
        String ostrOgTpCd,
        @NotBlank
        String itmPdCd,
        @NotBlank
        String itmGdCd,
        String mngtUnitCd,
        @Max(999999999999L)
        BigDecimal outQty,

        @NotBlank
        String strOjWareNo,
        String strWareDvCd,
        String strPrtnrNo,
        String strOgTpCd

    ) {}

    @Builder
    @ApiModel(value = "WsnaNormalOutOfStorageDto-CreqteReq")
    public record CreateReq(
        @NotBlank
        String ostrAkNo,
        @Positive
        int ostrAkSn,
        @NotBlank
        String ostrTpCd,
        @NotBlank
        String strTpCd,
        @NotBlank
        String ostrOjWareNo,
        String ostrWareDvCd,
        String ostrPrtnrNo,
        String ostrOgTpCd,
        @NotBlank
        String itmPdCd,
        @NotBlank
        String itmGdCd,
        String mngtUnitCd,
        BigDecimal boxUnitQty,
        String svpdItemKnd,
        @Max(999999999999L)
        BigDecimal outQty,

        @NotBlank
        String strOjWareNo,
        String strWareDvCd,
        String strPrtnrNo,
        String strOgTpCd
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
        String ostrTpCd,
        String stckStdGb,
        String ostrDt
    ) {}

    @Builder
    @ApiModel(value = "WsnaNormalOutOfStorageDto-CheckReq")
    public record CheckReq(

        @NotBlank
        String ostrAkNo,
        @NotEmpty
        List<Integer> ostrAkSns
    ) {}
}
