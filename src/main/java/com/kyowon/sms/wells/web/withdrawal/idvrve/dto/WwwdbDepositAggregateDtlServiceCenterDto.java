package com.kyowon.sms.wells.web.withdrawal.idvrve.dto;

import com.sds.sflex.common.utils.DbEncUtil;
import io.swagger.annotations.ApiModel;

public class WwwdbDepositAggregateDtlServiceCenterDto {

    @ApiModel(value = "WwwdbDepositAggregateDtlServiceCenterDto-SearchReq")
    public record SearchReq(
        String ogId, //센터코드
        String startDt, //시작일
        String endDt, //종료일
        String useYn, //대사처리
        String stlmDvCd //결제유형

    ) {

    }

    @ApiModel(value = "WwwdbDepositAggregateDtlServiceCenterDto-SearchRes")
    public record SearchRes(
        String vstFshDt, /*설치일자*/
        String dpDtm, /*입금일자*/
        String ogId, /* 센터 id */
        String ogNm, /* 센터명 */
        String ichrPrtnrNo, /*담당파트너번호*/
        String prtnrKnm, /*담당파트너명*/
        String cntrNo,
        String cntrSn,
        String cntrCstNo, /*고객번호*/
        String cstKnm, /*고객명*/
        String cralLocaraTno, /*휴대폰 앞자리*/
        String mexnoEncr, /*휴대폰 중간자리*/
        String cralIdvTno, /*휴대폰 끝자리*/
        String pdCd, /*상품코드*/
        String pdNm, /*제품명*/
        String sellTpCd, /*고객구분*/
        String svBizDclsfCd, /*고객서비스as설치배정내역*/ /*서비스유형*/
        String bilAmt, /*청구금액*/
        String dpSumAmt, /*대사금액*/
        String stlmDvNo, /*입금번호*/

        String stlmDvCd, /*결제구분코드*/
        String iscmpCd, /*결제처*/
        String cardAprno, /*승인번호*/
        String taxBll /*세금계산서*/
    ) {
        public SearchRes {
            mexnoEncr = DbEncUtil.dec(mexnoEncr);
        }
    }

    @ApiModel(value = "WwwdbDepositAggregateDtlServiceCenterDto-SearchCodeRes")
    public record SearchCodeRes(
        String codeId,
        String codeName
    ) {

    }

    @ApiModel(value = "WwwdbDepositAggregateDtlServiceCenterDto-SearchSumRes")
    public record SearchSumRes(
        String bilAmtTot,
        String dpSumAmtTot
    ) {

    }
}
