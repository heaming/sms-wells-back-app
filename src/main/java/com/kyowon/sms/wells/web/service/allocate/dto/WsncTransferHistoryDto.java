package com.kyowon.sms.wells.web.service.allocate.dto;

import io.swagger.annotations.ApiModel;

import javax.validation.constraints.NotBlank;

public class WsncTransferHistoryDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    @ApiModel(value = "WsncTransferHistoryDto-SearchReq")
    public record SearchReq(
        @NotBlank
        String cstSvAsnNo /* 고객서비스배정번호 */
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    @ApiModel(value = "WsncTransferHistoryDto-SearchRes")
    public record SearchRes(
        String fnlMdfcDtm, /* 최종수정일시 */
        String asnTfDvCd, /* 배정이관구분코드 */
        String bfOgNm, /* 이전조직명 */
        String bfCnfmPsicPrtnrNo, /* 이전이관확정파트너번호 */
        String bfPrtnrKnm, /* 이전이관확정파트너명 */
        String afOgNm, /* 이후조직명 */
        String afCnfmPsicPrtnrNo, /* 이후이관확정파트너번호 */
        String afPrtnrKnm, /* 이후이관확정파트너명 */
        String tfCnfmdt, /* 이관확정일자 */
        String tfAkRsonCd, /* 이관요청사유코드 */
        String tfOgNm, /* 이관요청조직명 */
        String tfPrtnrKnm, /* 이관요청파트너명 */
        String tfOgTpCd, /* 이관요청조직유형코드 */
        String tfPrtnrNo, /* 이관요청파트너번호 */
        String tfOgId, /* 이관요청조직ID */
        String cnfmOgNm, /* 이관확정조직명 */
        String cnfmPsicPrtnrNo, /* 이관확정파트너번호 */
        String cnfmdt, /* 이관확정일자 */
        String cnfmPrtnrKnm /* 이관확정파트너명 */
    ) {}

}
