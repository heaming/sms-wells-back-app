
package com.kyowon.sms.wells.web.fee.confirm.dto;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;

/**
 * <pre>
 * 수수료 개인 상세
 * </pre>
 *
 * @author gs.piit56
 * @since 2023.02.01
 */
public class WfeeIndividualFeeDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    // 수수료 개인 상세 Search Request Dto
    @ApiModel(value = "WfeeFeeIndividualDetailDto-SearchReq")
    public record SearchReq(
        @NotBlank
        String perfYm,
        @NotBlank
        String no
    ) {}
    @ApiModel(value = "WfeeFeeIndividualDetailDto-SearchHmstReq")
    public record SearchHmstReq(
        @NotBlank
        String perfYm,
        @NotBlank
        String no
    ) {}
    @ApiModel(value = "WfeeFeeIndividualDetailDto-SearchPlarReq")
    public record SearchPlarReq(
        @NotBlank
        String perfYm,
        @NotBlank
        String no
    ) {}
    @ApiModel(value = "WfeeFeeIndividualDetailDto-SearchMngerReq")
    public record SearchMngerReq(
        @NotBlank
        String perfYm,
        @NotBlank
        String no
    ) {}
    @ApiModel(value = "WfeeFeeIndividualDetailDto-SearchFeeReq")
    public record SearchFeeReq(
        @NotBlank
        String perfYm, /*실적년월*/
        @NotBlank
        String ogTp, /*조직유형*/
        @NotBlank
        String rsbTp, /*직책유형*/
        String ogLevl1, /*조직레벨1*/
        String ogLevl2, /*조직레벨2*/
        String ogLevl3, /*조직레벨3*/
        String prtnrNo, /*파트너번호*/
        String feeDsbYn, /*수수료지급여부*/
        String rsbDvCd, /*직책구분코드*/
        String hirFomCd /*고용형태코드*/
    ) {}

    @ApiModel(value = "WfeeFeeIndividualDetailDto-SearchFeeHmstReq")
    public record SearchFeeHmstReq(
        @NotBlank
        String perfYm, /*실적년월*/
        @NotBlank
        String ogTp, /*조직유형*/
        @NotBlank
        String rsbTp, /*직책유형*/
        String ogLevl1, /*조직레벨1*/
        String ogLevl2, /*조직레벨2*/
        String ogLevl3, /*조직레벨3*/
        String prtnrNo, /*파트너번호*/
        String feeDsbYn, /*수수료지급여부*/
        String rsbDvCd, /*직책구분코드*/
        String hirFomCd /*고용형태코드*/
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 수수료 개인 상세 Search Result Dto
    @ApiModel(value = "WfeeFeeIndividualDetailDto-SearchPlarRes")
    public record SearchPlarRes(
        String prtnrNo, /*파트너번호*/
        String prtnrKnm, /*성명*/
        String perfAtcNm, /*실적구분*/
        String cntrwTpNm, /*상품구분*/
        String rcpdt, /*접수일자*/
        String slDt, /*매출일자*/
        String cntrNo, /*계약번호*/
        String pdNm, /*상품명*/
        String cstKnm, /*고객명*/
        String saleDiv, /*판매구분*/
        int perfElhm, /*가전*/
        int perfChng, /*기변*/
        int perfElhmExcd /*가전외*/
    ) {}

    @ApiModel(value = "WfeeFeeIndividualDetailDto-SearchMngerRes")
    public record SearchMngerRes(
        String prtnrNo, /*파트너번호*/
        String prtnrKnm, /*성명*/
        String rcpdt, /*접수일자*/
        String slDt, /*매출일자*/
        String cntrNo, /*계약번호*/
        String pdNm, /*상품명*/
        String cstKnm, /*고객명*/
        String saleDiv, /*판매구분*/
        String pdAccCnt, /*인정건수*/
        int perfRental, /*환경가전렌탈*/
        int perfSnglPmnt, /*환경가전일시불*/
        int perfFxam, /*환경가전정액*/
        int perfBsPdAccCnt /*BS인정건수*/
    ) {}

    @ApiModel(value = "WfeeFeeIndividualDetailDto-SearchHmstRes")
    public record SearchHmstRes(
        String prtnrNo, /*파트너번호*/
        String prtnrKnm, /*성명*/
        String rcpdt, /*접수일자*/
        String slDt, /*매출일자*/
        String cntrNo, /*계약번호*/
        String pdNm, /*상품명*/
        String cstKnm, /*고객명*/
        String saleDiv, /*판매구분*/
        int pdAccCnt, /*인정건수*/
        int perfRental, /*렌탈*/
        int perfSnglPmnt /*일시불*/
    ) {}

    @ApiModel(value = "WfeeFeeIndividualDetailDto-FindHmstRes")
    public record FindHmstRes(
        String perfYm, /*실적년월*/
        String blg, /*소속*/
        String prtnrNo, /*번호*/
        String rsb, /*직책*/
        String emplNm, /*성명*/
        String frrSum, /*수수료계*/
        String ddtnSum, /*공제계*/
        String aclDsb, /*실지급*/
        String dsbBnk, /*지급은행*/
        String dsbAc /*지급계좌*/
    ) {}

    @ApiModel(value = "WfeeFeeIndividualDetailDto-SearchHmstEtcRes")
    public record SearchHmstEtcRes(
        String div,
        String elhmAckmtCt,
        String svCnt,
        String svRat
    ) {}

    @ApiModel(value = "WfeeFeeIndividualDetailDto-SearchHmstFeeRes")
    public record SearchHmstFeeRes(
        String item1,
        String amt1,
        String item2,
        String amt2,
        String item3,
        String amt3
    ) {}

    @ApiModel(value = "WfeeFeeIndividualDetailDto-FindHmstDeductionRes")
    public record FindHmstDeductionRes(
        String item1,
        String amt1,
        String item2,
        String amt2
    ) {}

    @ApiModel(value = "WfeeFeeIndividualDetailDto-SearchHmstPnpyamRes")
    public record SearchHmstPnpyamRes(
        String item,
        String lstmm,
        String thmOc,
        String tmh,
        String thmDdtn,
        String thmBlam
    ) {}

    @ApiModel(value = "WfeeFeeIndividualDetailDto-FindMngerRes")
    public record FindMngerRes(
        String perfYm, /*실적년월*/
        String prtnrNo, /*번호*/
        String frrSum, /*수수료계*/
        String blg, /*소속*/
        String rsb, /*직책*/
        String ddtnSum, /*공제계*/
        String emplNm, /*성명*/
        String dsbBnk, /*지급은행*/
        String dsbAc, /*지급계좌*/
        String aclDsb, /*실지급*/
        String mgtCnt,
        String vstCnt,
        String procsRt,
        String rsbYn
    ) {}

    @ApiModel(value = "WfeeFeeIndividualDetailDto-SearchMngerEtcRes")
    public record SearchMngerEtcRes(
        String item1,
        String fval1,
        String item2,
        String fval2,
        String item3,
        String fval3,
        String item4,
        String fval4
    ) {}

    @ApiModel(value = "WfeeFeeIndividualDetailDto-SearchMngerBeforeServiceRes")
    public record SearchMngerBeforeServiceRes(
        String cdNm,
        String cnt1,
        String cnt2,
        String amt1,
        String cnt3,
        String cnt4,
        String amt2,
        String sumAmt
    ) {}

    @ApiModel(value = "WfeeFeeIndividualDetailDto-SearchMngerFeeRes")
    public record SearchMngerFeeRes(
        String item1,
        String fval1,
        String item2,
        String fval2,
        String item3,
        String fval3,
        String item4,
        String fval4
    ) {}

    @ApiModel(value = "WfeeFeeIndividualDetailDto-FindMngerDeductionRes")
    public record FindMngerDeductionRes(
        String rds,
        String erntx,
        String rsdntx,
        String hirInsr,
        String buDdtn,
        String pnpyam
    ) {}

    @ApiModel(value = "WfeeFeeIndividualDetailDto-SearchMngerPnpyamRes")
    public record SearchMngerPnpyamRes(
        String item,
        String lstmm,
        String thmOc,
        String tmh,
        String thmDdtn,
        String thmBlam
    ) {}

    @ApiModel(value = "WfeeFeeIndividualDetailDto-FindPlarRes")
    public record FindPlarRes(
        String perfYm, /*실적년월*/
        String blg, /*소속*/
        String prtnrNo, /*번호*/
        String rsb, /*직책*/
        String emplNm, /*성명*/
        String frrSum, /*수수료계*/
        String ddtnSum, /*공제계*/
        String aclDsb, /*실지급*/
        String dsbBnk, /*지급은행*/
        String dsbAc/*지급계좌*/
    ) {}

    @ApiModel(value = "WfeeFeeIndividualDetailDto-SearchPlarEtcRes")
    public record SearchPlarEtcRes(
        String div,
        String elhmAckmtCt,
        String bfsvcAckmtCt,
        String chng,
        String fhsCt,
        String metgPrscD
    ) {}

    @ApiModel(value = "WfeeFeeIndividualDetailDto-SearchPlarFeeRes")
    public record SearchPlarFeeRes(
        String item1,
        String amt1,
        String item2,
        String amt2,
        String item3,
        String amt3
    ) {}

    @ApiModel(value = "WfeeFeeIndividualDetailDto-FindPlarDeductionRes")
    public record FindPlarDeductionRes(
        String rds,
        String erntx,
        String rsdntx,
        String pnpyam,
        String hirInsr,
        String inddInsr,
        String buDdtn,
        String ddtnSum
    ) {}

    @ApiModel(value = "WfeeFeeIndividualDetailDto-SearchPlarPnpyamRes")
    public record SearchPlarPnpyamRes(
        String item,
        String lstmm,
        String thmOc,
        String tmh,
        String thmDdtn,
        String thmBlam
    ) {}

    @ApiModel(value = "WfeeFeeIndividualDetailDto-SearchFeeRes")
    public record SearchFeeRes(
        String mngtDiv,
        String renlGrp,
        String branch,
        String emplNm,
        String prtnrNo,
        String rsb,
        String qlf,
        String bnk,
        String acNo,
        int intbsSum,
        int ddtnSum,
        int aclDsbAmt,
        int awbIntbsSum,
        int awbDdtnSum,
        int awbAclDsbAmt
    ) {}

    @ApiModel(value = "WfeeFeeIndividualDetailDto-SearchFeeHmstRes")
    public record SearchFeeHmstRes(
        String renlGrp,
        String branch,
        String emplNm,
        String prtnrNo,
        String rsb,
        String qlf,
        String bnk,
        String acNo,
        int intbsSum,
        int ddtnSum,
        int aclDsbAmt,
        int awbIntbsSum,
        int awbDdtnSum,
        int awbAclDsbAmt
    ) {}
}
