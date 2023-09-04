package com.kyowon.sms.wells.web.withdrawal.idvrve.dto;

import com.sds.sflex.common.utils.DbEncUtil;
import com.sds.sflex.common.utils.StringUtil;
import io.swagger.annotations.ApiModel;

public class WwdbRentalExpirationExcessiveAmountDto {

    @ApiModel(value = "WwdbRentalExpirationExcessiveAmountDto-SearchReq")
    public record SearchReq(
        String dpDt, /* 선수년월 */
        String copnDvCd, /* 계약구분코드 */
        String cntrPdEnddt, /* 만료취소년월 */
        String cntrDtlStatCd /* 종료구분코드 */
    ) {
    }

    @ApiModel(value = "WwdbRentalExpirationExcessiveAmountDto-SearchRes")
    public record SearchRes(
        String cntrNo, /* 주문번호 */
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


}
