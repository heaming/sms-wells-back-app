package com.kyowon.sms.wells.web.withdrawal.idvrve.dto;

import org.thymeleaf.util.StringUtils;

import io.swagger.annotations.ApiModel;

/**
 * <pre>
 * 가상계좌 조회 DTO
 * </pre>
 *
 * @author
 * @since 2023-05-08
 */
public class WwdbVirtualAccountDto {

    // *********************************************************
    // Request Dto
    // *********************************************************
    // 가상계좌조회 (모바일) Request Dto
    @ApiModel(value = "WwdbVirtualAccountDto-SearchReq")
    public record SearchReq(
        String prtnrNo, // 판매자번호
        String bnkCd, // 은행코드
        String strtdt, // 시작일
        String enddt // 종료일
    ) {
        public SearchReq {
            if (!StringUtils.isEmpty(strtdt)) {
                strtdt = strtdt + "000000";
            }
            if (!StringUtils.isEmpty(enddt)) {
                enddt = enddt + "235959";
            }
        }
    }

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 가상계좌조회 (모바일) Result Dto
    @ApiModel(value = "WwdbVirtualAccountDto-SearchRes")
    public record SearchRes(
        String vacBnkCd, // 은행
        String vacBnkNm, // 은행명
        String vacNo, // 가상계좌번호
        String ichrPrtnrNo, // 파트너번호
        String ichrPrtnrNm, // 파트너명
        String fnlDpDtm, // 최종입금일
        String fnlDpAmt, // 최종입금액
        String totDpBlam // 총잔액
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 가상계좌입금내역조회 (모바일) Result Dto
    @ApiModel(value = "WwdbVirtualAccountDto-SearchDtlRes")
    public record SearchDtlRes(
        String dpDt, // 입금일
        String dpHm, // 입금시분
        String dpDtm, // 입금일
        String dprNm, // 입금자
        String dpAmt, // 입금금액
        String useAmt, // 총잔액
        String dpBlam, // 사용금액
        String vacNo // 가상계좌번호
    ) {}
}
