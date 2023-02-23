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
}
