package com.kyowon.sms.wells.web.withdrawal.idvrve.dto;

import java.util.List;

import javax.validation.constraints.NotBlank;

public class WwwdbBillingDocumentMgtDto {
    public record SearchReq(
        String cstFnm,
        String fstRgstDtm,
        String bildcPblNo
    ) {}

    public record SearchRes(
        String bildcPblNo,
        String bildcPblSn,
        String cstFnm, //--고객성명      
        //--     --작성일자 필요
        String fwDt, //      --발송일자
        String pdNm, //--상품명
        String pdQty,
        String pdQtySum, //--총수량
        String pdSellAmt, //--판매금액
        String pdSellAmtSum, //--총금액
        String rmkCn, //--비고 이건 수정 가능성이 잇음
        String fstRgstDtm, //--작성일자
        String dummyText //발송
    ) {}

    public record RemoveReq(
        @NotBlank
        String bildcPblNo,
        String bildcPblSn
    ) {

    }
    public record SaveReq(
        List<SaveDtlsReq> saveDtlsReq,
        @NotBlank
        SaveMainReq saveMainReq
    ) {

    }

    public record SaveMainReq(
        String state,
        @NotBlank
        String bildcPblNo,
        @NotBlank
        String sellPrtnrNo,
        @NotBlank
        String sellPrtnrNm,
        String cstFnm
    ) {

    }
    public record SaveDtlsReq(
        String rowState,
        @NotBlank
        String bildcPblNo,
        @NotBlank
        String bildcPblSn,
        @NotBlank
        String pdNm,
        String pdQty,
        String pdUprc,
        String pdSellAmt,
        String pdSplAmt,
        String pdVat,
        String rmkCn
    ) {

    }
    public record SearchDtlsReq(
        String bildcPblNo,
        String bildcPblSn
    ) {

    }
    public record SearchDtlsRes(
        String bildcPblNo,
        String bildcPblSn,
        String pdNm,
        String pdQty,
        String pdUprc,
        String pdSellAmt,
        String pdSplAmt,
        String pdVat,
        String rmkCn
    ) {

    }

    public record SearchFwReq(
        String bildcPblNo,
        String bildcFwTpCd
    ) {

    }

    public record SearchFwRes(
        String bildcFwTpCd, //--발송유형
        String fwDt, //--발송일자 
        String callback, //--발신자 번호
        String recipientNum //--수신자 전화 번호 
    ) {

    }

    public record SaveFwReq(
        @NotBlank
        String bildcPblNo,
        String bildcFwTpCd,
        String cstFnm,
        String pdNm,
        String pdQtySum,
        String pdSellAmtSum,
        String destInfo, //받는사람
        String callback, //보내는사람
        String fromMail //받는사람 메일

    ) {

    }
}
