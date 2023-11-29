package com.kyowon.sms.wells.web.fee.confirm.dto;

import javax.validation.constraints.NotBlank;

import org.apache.commons.lang.StringUtils;

import com.sds.sflex.common.utils.DbEncUtil;

import io.swagger.annotations.ApiModel;

/**
 * <pre>
 * 수수료 개인 상세 (홈마스터)
 * </pre>
 *
 * @author LEE SUNHO
 * @since 2023.10.17
 */
public class WfeeIndividualFeeHomeMasterDto {
    @ApiModel(value = "WfeeIndividualFeeHomeMasterDto-SearchHmstReq")
    public record SearchHmstReq(
        @NotBlank
        String perfYm,
        @NotBlank
        String prtnrNo,
        String feeSellPerfDvCd /* 수수료판매실적구분코드 */
    ) {}

    @ApiModel(value = "WfeeIndividualFeeHomeMasterDto-SearchHmstRes")
    public record SearchHmstRes(
        String dgr3LevlDgPrtnrNo, /*지점장파트너번호*/
        String dgr3LevlDgPrtnrNm, /*지점장파트너명*/
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

    @ApiModel(value = "WfeeIndividualFeeHomeMasterDto-FindHmstRes")
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
        String dsbAc, /*지급계좌*/
        String pstnDvCd
    ) {
        public FindHmstRes {
            dsbAc = StringUtils.isNotEmpty(dsbAc) ? DbEncUtil.dec(dsbAc) : dsbAc;
        }
    }

    @ApiModel(value = "WfeeIndividualFeeHomeMasterDto-SearchHmstEtcRes")
    public record SearchHmstEtcRes(
        String div,
        String elhmAckmtCt,
        String svCnt,
        String svRat
    ) {}

    @ApiModel(value = "WfeeIndividualFeeHomeMasterDto-SearchHmstFeeRes")
    public record SearchHmstFeeRes(
        String item1,
        String fval1,
        String item2,
        String fval2,
        String item3,
        String fval3,
        String item4,
        String fval4
    ) {}

    @ApiModel(value = "WfeeIndividualFeeHomeMasterDto-FindHmstDeductionRes")
    public record FindHmstDeductionRes(
        String item,
        String amt
    ) {}

    @ApiModel(value = "WfeeIndividualFeeHomeMasterDto-SearchHmstPnpyamRes")
    public record SearchHmstPnpyamRes(
        String item,
        String lstmm,
        String thmOc,
        String tmh,
        String thmDdtn,
        String thmBlam
    ) {}
}
