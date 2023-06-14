package com.kyowon.sms.wells.web.contract.changeorder.dto;

import io.swagger.annotations.ApiModel;

public class WctbSinglePaymentBulkChangeDto {
    @ApiModel(value = "WctbSinglePaymentBulkChangeDto-SearchRes")
    public record SearchRes(
        String cntrNo, /* [계약상세번호] 계약번호 */
        String cntrSn, /* [계약상세번호] 계약일련번호 */
        String cstKnm, /* [계약자명] 고객한글명 */
        String rveCd, /* [수납코드] */
        String pyerNo, /* 납부자번호 */
        String basePdCd, /* [상품코드] */
        String pdNm, /* [상품명] */
        String cntrRcpFshDtm, /* [접수일] 계약접수완료일시 */
        String istDt, /* [설치일] 설치일자 */
        String cntrCanDt, /* [취소일] 계약취소일자 */
        String cpsDt, /* [보상일] 보상일자 */
        String sppDuedt, /* [예정일] 배송예정일자 */
        String canPerfDt, /* [실적취소일] */
        String reqdDt, /* [kiwi철거일] 철거일자 */
        String cttRsCd, /* 컨택결과코드 */
        String cttRsNm, /* [컨택명] 컨택결과명 */
        String booSellYn, /* [예약여부] */
        String booSellTpCd, /* 예약판매유형코드 */
        String booSellTpNm, /* 예약판매유형명 */
        String feeAckmtCt, /* [인정건수] 수수료인정건수 */
        String ackmtPerfAmt, /* [인정금액] 인정실적금액 */
        String ackmtPerfRt, /* [인정율(%)] 인정실적율 */
        String feeAckmtBaseAmt, /* [기준수수료] 수수료인정기준금액 */
        String feeFxamYn, /* [정액여부] 수수료정액여부 */
        String dpTpCd, /* [할부이체] 입금유형코드 */
        String bfsvcBzsDvCd, /* [할부실적] BFSVC_BZS_DV_CD */
        String mmbsDpTpCd, /* [멤버십이체] 입금유형코드 */
        String copnDvYn, /* [멤버십원장] */
        String frisuBfsvcPtrmN, /* [무상멤버십] 무상BS기간수 */
        String frisuAsPtrmN, /* [무상A/S] 무상AS기간수 */
        String sellEvCd, /* [행사코드] 판매행사코드 */
        String rgstDtm, /* [등록일] */
        String rgstUsrId, /* 등록자ID */
        String rgstUsrNm, /* [등록자] */
        String mdfcDtm, /* [최종수정일] */
        String mdfcUsrId, /* 수정자ID */
        String mdfcUsrNm /* [최종수정자] */
    ) {}

}
