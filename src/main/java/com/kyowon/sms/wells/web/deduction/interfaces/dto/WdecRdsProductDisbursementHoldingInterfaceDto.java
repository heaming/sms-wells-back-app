package com.kyowon.sms.wells.web.deduction.interfaces.dto;

import java.util.List;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

public class WdecRdsProductDisbursementHoldingInterfaceDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    // RDS 상품 지급 보류 상세 조회,  Search Request Dto
    @ApiModel("WdecRdsProductDisbursementHoldingInterfaceDto-FindReq")
    public record FindReq(
        @NotBlank
        String ogTpCd,
        @NotBlank
        String prtnrNo,
        @NotBlank
        String hdPrcsdt

    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // RDS 상품 지급 보류 상세 조회, Result Dto
    // 조회 결과는 인터페이스 응답양식(key-value 형태)에 맞추기 위해 DTO 형식으로 선언
    @ApiModel("WdecRdsProductDisbursementHoldingInterfaceDto-FindRes")
    public record FindRes(
        String rdsPdDsbHdId, /*RDS상품지급보류ID*/
        String coCd, /*회사코드*/
        String ogTpCd, /*조직유형코드*/
        String prtnrNo, /*파트너번호*/
        String cntrNo, /*계약번호*/
        Integer cntrSn, /*계약일련번호*/
        String ojOgTpCd, /*대상조직유형코드*/
        String ojPrtnrNo, /*대상파트너번호*/
        String pdgrpCd, /*상품군코드*/
        String rdsHdOjDvCd, /*RDS보류대상구분코드*/
        String rdsPdHdProcsDvCd, /*RDS상품보류처리구분코드*/
        String apyStrtdt, /*적용시작일자*/
        String apyEnddt, /*적용종료일자*/
        String cltnDt, /*해약일자*/
        String pstnDvCd, /*직급구분코드*/
        String hdPrcsdt, /*보류처리일자*/
        String slDt, /*매출일자*/
        Integer cntrTotAmt, /*계약총금액*/
        Integer pyTotAmt, /*납입총금액*/
        Integer ucTotAmt, /*미수총금액*/
        Integer hdCalcTotAmt, /*보류계산총금액*/
        Integer hdCalcTotIndvAmt, /*보류계산총개인금액*/
        Integer hdCalcTotOgAmt, /*보류계산총조직금액*/
        Integer hdDecAmt, /*보류결정금액*/
        Integer suscMcn, /*구독개월수*/
        String smryCn /*적요내용*/

    ) {}

    @Builder
    @ApiModel("WdecRdsProductDisbursementHoldingInterfaceDto-OgTp")
    public record OrganizationTypes(
        @NotBlank
        @JsonProperty("OG_TP_CD_ARY_VAL")
        String ogTpCdAryVal, /*조직유형코드값*/

        @NotBlank
        @JsonProperty("PRTNRNO")
        String prtnrNo /*파트너번호*/
    ) {}

    @ApiModel("WdecRdsProductDisbursementHoldingInterfaceDto-SaveReq")
    public record SaveReq(
        @NotBlank
        @JsonProperty("RDS_DSB_DUEDT")
        String rdsDsbDuedt, /*RDS지급예정일자*/

        @NotBlank
        @JsonProperty("ITEM")
        List<OrganizationTypes> data /*조직정보*/

    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // RDS지급보류대상내역, Save Result Dto
    @ApiModel("WdecRdsProductDisbursementHoldingInterfaceDto-SaveRes")
    @Builder
    public record SaveRes(

        @JsonProperty("RDS_DSB_DUEDT")
        String rdsDsbDuedt, /*RDS지급예정일자*/

        @JsonProperty("RS_CD")
        String rsCd, /*결과코드*/

        @JsonProperty("RS_MSG")
        String rsMsg /*결과메세지*/
    ) {}

}
