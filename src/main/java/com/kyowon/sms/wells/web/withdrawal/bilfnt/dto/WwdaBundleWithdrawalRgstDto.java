package com.kyowon.sms.wells.web.withdrawal.bilfnt.dto;

import org.apache.commons.lang.StringUtils;

import com.sds.sflex.common.utils.DbEncUtil;

import io.swagger.annotations.ApiModel;

public class WwdaBundleWithdrawalRgstDto {

    @ApiModel("WwdaBundleWithdrawalRgstDto-SearchReq")
    public record SearchReq(
        String unrgRs, // 대상구분, 처리결과
        String cntrNo, // 계약번호
        String cntrSn, // 계약 일련번호
        String cntrNoA, // 계약번호
        String cntrSnA, // 계약 일련번호
        String cntrPdStrtdt, // 접수시작일
        String cntrPdEnddt // 접수종료일

    ) {}

    @ApiModel("WwdaBundleWithdrawalRgstDto-SaveReq")
    public record SaveReq(
        String cntrNo, // 계약번호
        String cntrSn, // 계약 일련번호
        String cntrPdStrtdt, // 접수시작일
        String cntrPdEnddt // 접수종료일

    ) {}

    @ApiModel("WwdaBundleWithdrawalRgstDto-SearchUnrgPsRes")
    public record SearchUnrgPsRes(
        String cstKnm, // 고객명
        String pdNm, // 상품명
        String cntrPdStrtdt, // 접수일자
        String unrgRsonCd, // 묶음출금 미등록 코드
        String unrgRson, // 묶음출금 미등록 사유

        String cntrStlmId, // 계약결제ID
        String dgCntr, // 묶음대표번호
        String dgCntrNo, // 기기 주문번호
        String dgCntrSn, // 기기 주문번호 일련번호
        String dpTpCd, // 이체구분
        String fnitAprRsCd, // 상태
        String bnkNm, // 이체기관명
        String acnoEncr, // 이체번호
        String crcdnoEncr, // 카드번호
        String owrKnm, // 이체 소유주명
        String mpyBsdt, // 이체일자
        String bryyMmdd, //이체 인증번호

        String sdingCntrStlmId, // 모종 계약결제ID
        String sdingCntrStlmRelId, // 모종 계약결제관계ID
        String sdingCntrNo, // 모종 주문번호
        String sdingCntrSn, // 모종 상세번호
        String sdingDpTpCd, // 모종 이체구분
        String sdingFnitAprRsCd, // 모종 상태
        String sdingDgCntrNo, // 모종 묶음대표번호
        String sdingBnkNm, // 모종 이체기관명
        String sdingAcnoEncr, // 모종이체번호
        String sdingCrcdnoEncr, // 모종카드번호
        String sdingOwrKnm, // 모종 이체 소유주명
        String sdingMpyBsdt, // 모종 이체일자
        String sdingBryyMmdd //모종 이체 인증번호
    ) {
        public SearchUnrgPsRes {
            acnoEncr = StringUtils.isNotEmpty(acnoEncr) ? DbEncUtil.dec(acnoEncr) : acnoEncr;
            sdingAcnoEncr = StringUtils.isNotEmpty(sdingAcnoEncr) ? DbEncUtil.dec(sdingAcnoEncr) : sdingAcnoEncr;
            crcdnoEncr = StringUtils.isNotEmpty(crcdnoEncr) ? DbEncUtil.dec(crcdnoEncr) : crcdnoEncr;
            sdingCrcdnoEncr = StringUtils.isNotEmpty(sdingCrcdnoEncr) ? DbEncUtil.dec(sdingCrcdnoEncr)
                : sdingCrcdnoEncr;
        }

    }

    @ApiModel("WwdaBundleWithdrawalRgstDto-SearchRgstHistRes")
    public record SearchRgstHistRes(
        String itgBilPrtcDtm, //등록일시
        String fnlMdfcUsrId, //등록사번
        String aftnItgUnrgRsCd, //결과
        String errCn, //오류내용
        String cstKnm, //고객명
        String pdNm, //상품명
        String cntrPdStrtdt, //접수일자
        String aftnItgUnrgRsonCd, //묶음출금 미등록
        String cntrSn, //기기주문번호
        String dpTpCd, //이체구분
        String dgCntrSn, //묶음대표번호
        String bnkCd, //이체기관명
        String acnoEncr, //이체번호
        String crcdnoEncr, // 카드번호
        String owrKnm, //이체 소유주명
        String mpyBsdt, //이체일자
        String bryyMmdd, //이체 인증번호

        String sdingCntrNo, //모종주문번호
        String sdingCntrSn, //모종주문번호
        String sdingDpTpCd, //이체구분
        String sdingDgCntrSn, //묶음대표번호
        String sdingBnkCd, //이체기관명
        String sdingAcnoEncr, //이체번호
        String sdingCrcdnoEncr, // 모종카드번호
        String sdingOwrKnm, //이체 소유주명
        String sdingMpyBsdt, //이체일자
        String sdingBryyMmdd //이체 인증번호

    ) {
        public SearchRgstHistRes {
            acnoEncr = StringUtils.isNotEmpty(acnoEncr) ? DbEncUtil.dec(acnoEncr) : acnoEncr;
            sdingAcnoEncr = StringUtils.isNotEmpty(sdingAcnoEncr) ? DbEncUtil.dec(sdingAcnoEncr) : sdingAcnoEncr;
            crcdnoEncr = StringUtils.isNotEmpty(crcdnoEncr) ? DbEncUtil.dec(crcdnoEncr) : crcdnoEncr;
            sdingCrcdnoEncr = StringUtils.isNotEmpty(sdingCrcdnoEncr) ? DbEncUtil.dec(sdingCrcdnoEncr)
                : sdingCrcdnoEncr;
        }

    }

}
