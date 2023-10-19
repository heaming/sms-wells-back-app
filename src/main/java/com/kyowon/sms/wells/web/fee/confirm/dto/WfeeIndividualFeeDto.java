
package com.kyowon.sms.wells.web.fee.confirm.dto;

import javax.validation.constraints.NotBlank;

import org.apache.commons.lang.StringUtils;

import com.sds.sflex.common.utils.DbEncUtil;

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
        String perfYm, /* 실적년월 */
        @NotBlank
        String prtnrNo /* 파트너번호 */
    ) {}
    @ApiModel(value = "WfeeFeeIndividualDetailDto-SearchHmstReq")
    public record SearchHmstReq(
        @NotBlank
        String perfYm,
        @NotBlank
        String no
    ) {}
    @ApiModel(value = "WfeeFeeIndividualDetailDto-SearchMngerReq")
    public record SearchMngerReq(
        @NotBlank
        String perfYm, /* 실적년월 */
        @NotBlank
        String prtnrNo /* 파트너번호 */
    ) {}
    @ApiModel(value = "WfeeFeeIndividualDetailDto-SearchFeeReq")
    public record SearchFeeReq(
        @NotBlank
        String perfYm, /*실적년월*/
        @NotBlank
        String ogTpCd, /*조직유형*/
        @NotBlank
        String rsbDvCd, /*직책유형*/
        String ogLevl1Id, /*조직레벨1*/
        String ogLevl2Id, /*조직레벨2*/
        String ogLevl3Id, /*조직레벨3*/
        String prtnrNo, /*파트너번호*/
        String feeDsbYn, /*수수료지급여부*/
        String userRsbCd, /*직책구분코드*/
        String userSpptRsbDvCd,
        String hirFomCd, /*고용형태코드*/
        String userEmpId /*로그인Id*/
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 수수료 개인 상세 Search Result Dto

    @ApiModel(value = "WfeeFeeIndividualDetailDto-SearchMngerRes")
    public record SearchMngerRes(
        String prtnrNo, /*파트너번호*/
        String prtnrKnm, /*성명*/
        String rcpdt, /*접수일자*/
        String slDt, /*매출일자*/
        String canDt, /*취소일자*/
        String cntrNo, /*계약번호*/
        String pdNm, /*상품명*/
        String cstKnm, /*고객명*/
        String saleDiv, /*판매구분*/
        int pdAccCnt, /*인정건수*/
        int perfRental, /*가전기준가*/
        int perfSnglPmnt, /*환경가전일시불*/
        int perfFxam, /*환경가전정액*/
        int perfBsPdAccCnt, /*가전인정건수*/
        int perfElhmExcpAckmt /*가전외인정실적*/
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

    @ApiModel(value = "WfeeFeeIndividualDetailDto-FindMngerBasicRes")
    public record FindMngerBasicRes(
        String perfYm, /* 실적년월 */
        String ogCd, /* 조직코드 */
        String prtnrNo, /* 파트너번호 */
        String prtnrKnm, /* 파트너명 */
        String rsbDvCd, /* 직책구분코드 */
        String intbsAmt, /* 소득과표금액 */
        String ddctam, /* 공제금액 */
        String dsbOjAmt, /* 지급대상금액 */
        String bnkNm, /* 은행명 */
        String acnoEncr, /* 계좌번호암호화 */
        String mngtCt, /* 관리건수 */
        String vstCt, /* 방문건수 */
        String procsRt, /* 처리율 */
        String dgr1LevlOgId, /* 1차레벨조직ID */
        String dgr2LevlOgId, /* 2차레벨조직ID */
        String dgr3LevlOgId, /* 3차레벨조직ID */
        String dgr5LevlOgId, /* 4차레벨조직ID */
        String dgr4LevlOgId /* 5차레벨조직ID */
    ) {
        public FindMngerBasicRes {
            acnoEncr = StringUtils.isNotEmpty(acnoEncr) ? DbEncUtil.dec(acnoEncr) : acnoEncr;
        }
    }

    @ApiModel(value = "WfeeFeeIndividualDetailDto-SearchMngerSellEtcsRes")
    public record SearchMngerSellEtcsRes(
        String perfItem1, /* 실적항목1 */
        String perfItem2, /* 실적항목2 */
        String perfItem3, /* 실적항목3 */
        String perfItem4, /* 실적항목4 */
        String perfVal1, /* 개인실적 */
        String perfVal2, /* 조직실적 */
        String perfVal3, /* BS실적 */
        String perfVal4 /* 기타 */
    ) {}

    @ApiModel(value = "WfeeFeeIndividualDetailDto-SearchMngerBeforeServiceRes")
    public record SearchMngerBeforeServiceRes(
        String cdNm, /* 상품명 */
        String geMngtCt, /* 일반-관리건수 */
        String geVstCt, /* 일반-방문건수 */
        String geAmt, /* 일반-금액 */
        String fxamMngtCt, /* 정액-관리건수 */
        String fxamVstCt, /* 정액-방문건수 */
        String fxamAmt, /* 정액-금액 */
        String sumAmt /* 합계 */
    ) {}

    @ApiModel(value = "WfeeFeeIndividualDetailDto-SearchMngerFeeRes")
    public record SearchMngerFeeRes(
        String srnMarkFeeNm, /* 화면표시수수료명 */
        String feeAtcVal /* 수수료항목값 */
    ) {}

    @ApiModel(value = "WfeeFeeIndividualDetailDto-SearchMngerDeductionRes")
    public record SearchMngerDeductionRes(
        String feeAtcItem, /* 공제코드명 */
        String feeAtcVal /* 수수료항목값 */
    ) {}

    @ApiModel(value = "WfeeFeeIndividualDetailDto-SearchMngerPnpyamRes")
    public record SearchMngerPnpyamRes(
        String pnpyamAtcCdNm, /* 가지급금 항목명 */
        String bfmnBlnc, /* 전월잔액 */
        String thmnOccr, /* 당월발생 */
        String thmnSum, /* 당월합계 */
        String thmnDctn, /* 당월공제 */
        String thmnBlnc /* 당월잔액 */
    ) {}

    @ApiModel(value = "WfeeFeeIndividualDetailDto-SearchFeeRes")
    public record SearchFeeRes(
        String mngtDiv,
        String renlGrp,
        String branch,
        String emplNm,
        String prtnrNo,
        String rsbDvCd,
        String qlfDvCd,
        String fnitCd,
        String acNo,
        String pstnDvCd,
        int intbsSum,
        int ddtnSum,
        int aclDsbAmt,
        int awbIntbsSum,
        int awbDdtnSum,
        int awbAclDsbAmt
    ) {
        public SearchFeeRes {
            acNo = StringUtils.isNotEmpty(acNo) ? DbEncUtil.dec(acNo) : acNo;
        }
    }

    @ApiModel(value = "WfeeFeeIndividualDetailDto-SearchUserInfoRes")
    public record SearchUserInfoRes(
        String hirFomCD,
        String bznsSpptRsbDvCd,
        String rsbDvCd,
        String pstnDvCd
    ) {}

}
