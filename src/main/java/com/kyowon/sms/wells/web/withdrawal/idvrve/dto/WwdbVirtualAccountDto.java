package com.kyowon.sms.wells.web.withdrawal.idvrve.dto;

import javax.validation.constraints.NotBlank;

import com.sds.sflex.common.utils.DbEncUtil;
import com.sds.sflex.common.utils.StringUtil;

import io.swagger.annotations.ApiModel;

public class WwdbVirtualAccountDto {

    @ApiModel(value = "WwdbVirtualAccountDto-SearchReq")
    public record SearchReq(
        String prtnrNo, // 판매자번호
        String bnkCd, // 은행코드
        String strtdt, // 시작일
        String enddt // 종료일
    ) {

    }

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
