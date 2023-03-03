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

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 수수료 개인 상세 Search Result Dto
    @ApiModel(value = "WfeeFeeIndividualDetailDto-SearchRes")
    public record SearchRes(
        String col1, /*번호*/
        String col2, /*성명*/
        String col3, /*접수일자*/
        String col4, /*매출일자*/
        String col5, /*계약번호*/
        String col6, /*상품내역*/
        String col7, /*고객명*/
        String col8, /*판매구분*/
        int col9, /*인정건수*/
        int col10, /*BS인정건수*/
        int col11, /*환경가전렌탈*/
        int col12, /*환경가전일시불*/
        int col13 /*환경가전정액*/
    ) {}

    @ApiModel(value = "WfeeFeeIndividualDetailDto-FindHmstRes")
    public record FindHmstRes(
        String col1, /*실적년월*/
        String col2, /*번호*/
        int col3, /*수수료계*/
        String col4, /*소속*/
        String col5, /*직책*/
        int col6, /*공제계*/
        String col7, /*성명*/
        String col8, /*지급은행*/
        int col9, /*지급계좌*/
        int col10 /*실지급*/
    ) {}

    @ApiModel(value = "WfeeFeeIndividualDetailDto-SearchHmstEtcRes")
    public record SearchHmstEtcRes(
        String col1, /*서비스처리율*/
        int col2, /*가전인정건수*/
        int col3, /*서비스건수*/
        String col4 /*서비스처리율*/
    ) {}

    @ApiModel(value = "WfeeFeeIndividualDetailDto-SearchHmstFeeRes")
    public record SearchHmstFeeRes(
        String col1,
        int col2,
        String col3,
        int col4,
        String col5,
        int col6
    ) {}

    @ApiModel(value = "WfeeFeeIndividualDetailDto-FindHmstDeductionRes")
    public record FindHmstDeductionRes(
        int col1,
        int col2,
        int col3,
        int col4,
        int col5,
        int col6,
        int col7,
        int col8
    ) {}

    @ApiModel(value = "WfeeFeeIndividualDetailDto-SearchHmstPnpyamRes")
    public record SearchHmstPnpyamRes(
        String col1,
        int col2,
        int col3,
        int col4,
        int col5,
        int col6
    ) {}

    @ApiModel(value = "WfeeFeeIndividualDetailDto-FindMngerRes")
    public record FindMngerRes(
        String col1, /*실적년월*/
        String col2, /*번호*/
        int col3, /*수수료계*/
        String col4, /*소속*/
        String col5, /*직책*/
        int col6, /*공제계*/
        String col7, /*성명*/
        String col8, /*지급은행*/
        String col9, /*지급계좌*/
        int col10 /*실지급*/
    ) {}

    @ApiModel(value = "WfeeFeeIndividualDetailDto-SearchMngerEtcRes")
    public record SearchMngerEtcRes(
        String col1,
        int col2,
        int col3,
        int col4,
        int col5,
        int col6
    ) {}

    @ApiModel(value = "WfeeFeeIndividualDetailDto-SearchMngerFeeRes")
    public record SearchMngerFeeRes(
        String col1,
        int col2,
        String col3,
        int col4,
        String col5,
        int col6
    ) {}

    @ApiModel(value = "WfeeFeeIndividualDetailDto-FindMngerDeductionRes")
    public record FindMngerDeductionRes(
        int col1,
        int col2,
        int col3,
        int col4,
        int col5,
        int col6,
        int col7,
        int col8
    ) {}

    @ApiModel(value = "WfeeFeeIndividualDetailDto-SearchMngerPnpyamRes")
    public record SearchMngerPnpyamRes(
        String col1,
        int col2,
        int col3,
        int col4,
        int col5,
        int col6
    ) {}

    @ApiModel(value = "WfeeFeeIndividualDetailDto-FindPlarRes")
    public record FindPlarRes(
        String col1, /*실적년월*/
        String col2, /*번호*/
        int col3, /*수수료계*/
        String col4, /*소속*/
        String col5, /*직책*/
        int col6, /*공제계*/
        String col7, /*성명*/
        String col8, /*지급은행*/
        String col9, /*지급계좌*/
        String col10 /*실지급*/
    ) {}

    @ApiModel(value = "WfeeFeeIndividualDetailDto-SearchPlarEtcRes")
    public record SearchPlarEtcRes(
        String col1, /*서비스처리율*/
        int col2, /*가전인정건수*/
        int col3, /*서비스건수*/
        String col4 /*서비스처리율*/
    ) {}

    @ApiModel(value = "WfeeFeeIndividualDetailDto-SearchPlarFeeRes")
    public record SearchPlarFeeRes(
        String col1,
        int col2,
        String col3,
        int col4,
        String col5,
        int col6
    ) {}

    @ApiModel(value = "WfeeFeeIndividualDetailDto-FindPlarDeductionRes")
    public record FindPlarDeductionRes(
        int col1,
        int col2,
        int col3,
        int col4,
        int col5,
        int col6,
        int col7,
        int col8
    ) {}

    @ApiModel(value = "WfeeFeeIndividualDetailDto-SearchPlarPnpyamRes")
    public record SearchPlarPnpyamRes(
        String col1,
        int col2,
        int col3,
        int col4,
        int col5,
        int col6
    ) {}
}
