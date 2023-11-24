package com.kyowon.sms.wells.web.withdrawal.bilfnt.dto;

import com.sds.sflex.common.utils.DbEncUtil;
import com.sds.sflex.common.utils.StringUtil;
import com.sds.sflex.system.config.masking.MaskRequired;
import com.sds.sflex.system.config.masking.MaskingType;

import io.swagger.annotations.ApiModel;

/**
 * <pre>
 * 자동이체 미수신 체크 목록 관리 DTO
 * </pre>
 *
 * @author donghyun.yoo
 * @since 2023-02-07
 */
public class WwdaNotReceivedCheckListDto {

    // *********************************************************
    // Request Dto
    // *********************************************************
    // 자동이체 미수신 체크 목록 Request Dto
    @ApiModel("WwdaNotReceivedCheckListDto-SearchAftnBilNrcvListReq")
    public record SearchAftnBilNrcvListReq(
        String bilDt, // 기준일자
        String fntDvCd // 이체구분
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 자동이체 미수신 체크 목록 Result Dto
    @ApiModel("WwdaNotReceivedCheckListDto-SearchAftnBilNrcvListRes")
    public record SearchAftnBilNrcvListRes(
        String autoFntClsf, // 자동이체 분류
        String bnkCd, // 은행/카드사 코드
        String bnkNm, // 은행/카드사 명
        String ct // 미수신 건
    ) {}

    // *********************************************************
    // Request Dto
    // *********************************************************
    // 자동이체 입금 미생성 체크 목록 Request Dto
    @ApiModel("WwdaNotReceivedCheckListDto-SearchAftnDpNcrtCheckListReq")
    public record SearchAftnDpNcrtCheckListReq(
        String bilDt, // 기준일자
        String fntDvCd // 이체구분
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 자동이체 입금 미생성 체크 목록 Result Dto
    @ApiModel("WwdaNotReceivedCheckListDto-SearchAftnDpNcrtCheckListRes")
    public record SearchAftnDpNcrtCheckListRes(
        String aftnClsf, //  자동이체 분류
        String cntrNo, // 계약번호
        String cntrSn, // 계약일련번호
        String bilDt, // 이체일자
        String sellTpCd, // 판매유형
        String rveAmt // 수신금액
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 매출실적 입금 전용 누락건 Result Dto
    @ApiModel("WwdaNotReceivedCheckListDto-SearchAftnSlPerfCheckInqrRes")
    public record SearchAftnSlPerfCheckInqrRes(
        String sellTpNm, // 자동이체 분류
        String baseYm, // 실적년월
        String cntrNo, // 계약번호
        String cntrSn, // 계약일련번호
        String cntr, // 계약상세번호
        String rentalNmn, // 렌탈차월
        String thmIntamDpAmt, // 선수입금
        String thmIstmRfndAmt, // 선수환불
        String rveAmt, // 금액
        String dpMesCd // 결제정보
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 자동이체 결과 묶음 오류 조회 Result Dto
    @ApiModel("WwdaNotReceivedCheckListDto-SearchResultBundleErrorRes")
    public record SearchResultBundleErrorRes(
        // 묶음 출금 오등록
        String mstrSellTpCd, // 대표주문 판매유형
        String dgCntrNo, // 대표계약번호
        String mstrDpTpCd, // 대표주문 이체구분
        String subSellTpCd, // 묶음주문 판매유형
        String subodCntrNo, // 묶음계약번호
        String subDpTpCd, // 묶음주문 이체구분
        String errTyp, // 오등록 내역
        @MaskRequired(type = MaskingType.ACCOUNT)
        String mAcnoEncr, // 계좌번호
        @MaskRequired(type = MaskingType.ACCOUNT)
        String sAcnoEncr, // 계좌번호
        @MaskRequired(type = MaskingType.CARD)
        String mCrcdnoEncr, // 카드번호
        @MaskRequired(type = MaskingType.CARD)
        String sCrcdnoEncr // 카드번호
    ) {
        public SearchResultBundleErrorRes {
            mAcnoEncr = StringUtil.isNotEmpty(mAcnoEncr) ? DbEncUtil.dec(mAcnoEncr) : mAcnoEncr;
            sAcnoEncr = StringUtil.isNotEmpty(sAcnoEncr) ? DbEncUtil.dec(sAcnoEncr) : sAcnoEncr;
            mCrcdnoEncr = StringUtil.isNotEmpty(mCrcdnoEncr) ? DbEncUtil.dec(mCrcdnoEncr) : mCrcdnoEncr;
            sCrcdnoEncr = StringUtil.isNotEmpty(sCrcdnoEncr) ? DbEncUtil.dec(sCrcdnoEncr) : sCrcdnoEncr;
        }
    }

}
