package com.kyowon.sms.wells.web.withdrawal.idvrve.dto;

import com.sds.sflex.common.utils.DbEncUtil;
import com.sds.sflex.common.utils.StringUtil;
import io.swagger.annotations.ApiModel;

/**
 * <pre>
 * 렌탈만료초과금현황 DTO
 * </pre>
 *
 * @author kimoon.lim
 * @since 2023-05-19
 */
public class WwdbRentalExpirationExcessiveAmountDto {

    // *********************************************************
    // Request Dto
    // *********************************************************
    // 렌탈만료초과금현황 Search Request Dto
    @ApiModel(value = "WwdbRentalExpirationExcessiveAmountDto-SearchReq")
    public record SearchReq(
        String dpDt, /* 선수년월 */
        String copnDvCd, /* 계약구분코드 */
        String cntrPdEnddt, /* 만료취소년월 */
        String cntrDtlStatCd /* 종료구분코드 */
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 렌탈만료초과금현황 Search Result Dto
    @ApiModel(value = "WwdbRentalExpirationExcessiveAmountDto-SearchRes")
    public record SearchRes(
        String cntrNo, /* 주문번호 */
        String cntrSn,
        String cntr,
        String cstKnm, /* 고객명-고객번호 */
        String pdNm, /* 제품명 */
        String dpAmt, /* 선수금 */
        String fnitCd, /* 은행명 */
        String acnoEncr, /* 계좌번호 */
        String dprNm, /* 예금주 */
        String copnDvCd, /* 구분 */
        String nmn, /* 차월 */
        String cntrPdEnddt, /* 만료취소년월 */
        String cntrDtlStatCd /* 종료구분 */
    ) {
        public SearchRes {
            if (!StringUtil.isEmpty(acnoEncr)) {
                acnoEncr = DbEncUtil.dec(acnoEncr);
            }
        }
    }

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 렌탈만료초과금현황 합계 조회 Search Result Dto
    public record SearchTotalSumRes(
        String dpAmt /* 선수금 */
    ) {}
}
