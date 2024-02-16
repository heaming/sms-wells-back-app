package com.kyowon.sms.wells.web.service.visit.dto;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

import javax.validation.constraints.NotBlank;

/**======================================
 *
 * <pre>
 * K-W-SV-U-0245M01    실적_신제품 M+3 누적 A/S율
 * </pre>
 *
 * @author gs.piit231
 * @since 2023.09.07
**======================================*/
public class WsnbNewPdctMThreeAcuAfSvRtDto {
    @ApiModel(value = "WsnbNewPdctMThreeAcuAfSvRtDto-SearchReq")
    public record SearchReq(
        @NotBlank
        String baseY,
        String svType,
        String badDivide,
        String pdGrp,
        String pdCd
    ) {}

    @ApiModel(value = "WsnbNewPdctMThreeAcuAfSvRtDto-SearchRes")
    public record SearchRes(
        String nm,
        Integer acol1,
        Integer acol2,
        Integer acol3,
        Integer acol4,
        Integer acol5,
        Integer acol6,
        Integer acol7,
        Integer acol8,
        Integer acol9,
        Integer acol10,
        Integer acol11,
        Integer acol12,
        Integer maxval,
        Integer minval,
        Integer tcnt,
        Integer avg
    ) {}

    @ApiModel(value = "WsnbNewPdctMThreeAcuAfSvRtDto-PdListReq")
    public record PdListReq(
        String pdGrp,
        String pdCd
    ){}

    @ApiModel(value = "WsnbNewPdctMThreeAcuAfSvRtDto-PdListRes")
    public record PdListRes(
        String chk,
        String pdCd,        /* 상품코드 */
        String code,        /* 상품코드 */
        String codeName,    /* 상품코드 + 상품명 */
        String nmKor,       /* 상품명 */
        String itmKndCd,    /* 품목종류코드 */
        String pdGrpCd,     /* 상품그룹코드 */
        String pdHclsfId,   /* 상품대분류 */
        String pdTpCd,      /* 상품유형코드 */
        String otscDvCd,    /* 아웃소싱구분코드 */
        String lncStrtdt,   /* 출시시작일자 */
        String lncExdt      /* 출시만기일자 */
    ) {}

    @Builder
    @ApiModel(value = "WsnbNewPdctMThreeAcuAfSvRtDto-SaveReq")
    public record SaveReq(
        @NotBlank
        String rowState, /* 상태 */
        @NotBlank
        String pdCd, /* 상품코드 */
        String otscDvCd, /* 아웃소싱구분코드 */
        String nmKor,
        String pdGrpCd,
        @NotBlank
        String lncStrtdt,
        @NotBlank
        String lncExdt,
        String pdHclsfId,
        String pdTpCd
    ) {}

    @ApiModel(value = "WsnbNewPdctMThreeAcuAfSvRtDto-PdDtlListRes")
    public record PdDtlListRes(
        String cd,
        String cdNm,
        String svpdItemGr,
        String pdHclsfId
    ){}
}
