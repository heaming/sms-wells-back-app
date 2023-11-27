
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
public class WfeeIndividualFeePlannerDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    // 수수료 개인 상세 Search Request Dto

    @ApiModel(value = "WfeeFeeIndividualDetailDto-SearchPlarReq")
    public record SearchPlarReq(
        @NotBlank
        String perfYm,
        @NotBlank
        String no
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 수수료 개인 상세 Search Result Dto
    @ApiModel(value = "WfeeFeeIndividualDetailDto-SearchPlarRes")
    public record SearchPlarRes(
        String dgr3LevlDgPrtnrNo, /*지점장파트너번호*/
        String dgr3LevlDgPrtnrNm, /*지점장파트너명*/
        String prtnrNo, /*파트너번호*/
        String prtnrKnm, /*성명*/
        String cntrwTpNm, /*상품구분*/
        String rcpdt, /*접수일자*/
        String slDt, /*매출일자*/
        String canDt, /*취소일자*/
        String cntrNo, /*계약번호*/
        String pdNm, /*상품명*/
        String cstKnm, /*고객명*/
        String saleDiv, /*판매구분*/
        int perfElhm, /*가전*/
        int perfChng, /*기변*/
        int perfElhmExcd /*가전외*/
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
        String dsbAc, /*지급계좌*/
        String pstnDvCd
    ) {
        public FindPlarRes {
            dsbAc = StringUtils.isNotEmpty(dsbAc) ? DbEncUtil.dec(dsbAc) : dsbAc;
        }
    }

    @ApiModel(value = "WfeeFeeIndividualDetailDto-SearchPlarEtcRes")
    public record SearchPlarEtcRes(
        String prtnrNo,
        String elhm,
        String elhmExcp,
        String elhmChdvc,
        String mutu,
        String metgN,
        String div
    ) {}

    @ApiModel(value = "WfeeFeeIndividualDetailDto-SearchPlarFeeRes")
    public record SearchPlarFeeRes(
        String item1,
        String fval1,
        String item2,
        String fval2,
        String item3,
        String fval3
    ) {}

    @ApiModel(value = "WfeeFeeIndividualDetailDto-FindPlarDeductionRes")
    public record FindPlarDeductionRes(
        String item,
        String fval
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
}
