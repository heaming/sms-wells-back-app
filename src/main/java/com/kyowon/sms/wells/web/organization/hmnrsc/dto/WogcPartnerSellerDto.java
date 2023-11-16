package com.kyowon.sms.wells.web.organization.hmnrsc.dto;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

public class WogcPartnerSellerDto {

    /**
     * Wells 주문의 판매자 정보 확인 조회 Request Dto
     * @param ogTpCd 조직유형코드
     * @param prtnrKnm 파트너명
     * @param bryyMmdd 생년월일
     * @param sexDvCd 성별구분코드
     * @param ymd 접수일자
     */
    @ApiModel(value = "WogcPartnerSellerDto-SearchInformationConfirmReq")
    @Builder
    public record SearchInformationConfirmReq(
        String ogTpCd,
        String prtnrKnm,
        String bryyMmdd,
        String sexDvCd,
        String ymd
    ) {}

    /**
     * Wells 주문의 판매자 정보 확인 조회
     * @param prtnrNo 파트너번호
     * @param pstnDvCd 직급구분코드
     * @param chk 결과
     */
    @ApiModel(value = "WogcPartnerSellerDto-SearchInformationConfirmRes")
    @Builder
    public record SearchInformationConfirmRes(
        String prtnrNo,
        String pstnDvCd,
        String chk
    ) {}
}
