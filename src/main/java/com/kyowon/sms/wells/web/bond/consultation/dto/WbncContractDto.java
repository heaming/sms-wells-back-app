package com.kyowon.sms.wells.web.bond.consultation.dto;

import org.apache.commons.lang.StringUtils;

import com.sds.sflex.common.utils.DbEncUtil;

import io.swagger.annotations.ApiModel;

public class WbncContractDto {

    @ApiModel(value = "WbncContractDto-SearchReq")
    public record SearchReq(
        String schClctamNo,
        String schCstNm,
        String schDlqMcntStrt,
        String schDlqMcntEnd,
        String schIstLocaraTno,
        String schIstExnoEncr,
        String schIstIdvTno, /* 설치전화번호 */
        String schCntrNo,
        String schCstNo,
        String schBizDv,
        String schIstCralLocaraTno,
        String schIstMexnoEncr,
        String schIstCralIdvTno, /* 설치휴대전화번호 */
        String schCntrLocaraTno,
        String schCntrExnoEncr,
        String schCntrIdvTno, /* 계약전화번호 */
        String schSfK,
        String schCstDv,
        String schOjBlamStrt,
        String schOjBlamEnd,
        String schCntrCralLocaraTno,
        String schCntrMexnoEncr,
        String schCntrCralIdvTno, /* 계약휴대전화번호 */
        String schTfDtStrt,
        String schTfDtEnd,
        String schFntDv,
        String schFntDtStrt,
        String schFntDtEnd,
        String schBilDv,
        String schCstThmDp,
        String schAuthRsgYn,
        String schDv
    ) {
        public SearchReq {
            schIstExnoEncr = StringUtils.isNotEmpty(schIstExnoEncr) ? DbEncUtil.enc(schIstExnoEncr) : schIstExnoEncr;
            schIstMexnoEncr = StringUtils.isNotEmpty(schIstMexnoEncr) ? DbEncUtil.enc(schIstMexnoEncr)
                : schIstMexnoEncr;
            schCntrExnoEncr = StringUtils.isNotEmpty(schCntrExnoEncr) ? DbEncUtil.enc(schCntrExnoEncr)
                : schCntrExnoEncr;
            schCntrMexnoEncr = StringUtils.isNotEmpty(schCntrMexnoEncr) ? DbEncUtil.enc(schCntrMexnoEncr)
                : schCntrMexnoEncr;
        }

    }

    @ApiModel(value = "WbncContractDto-SearchRes")
    public record SearchRes(
        String ctt, /* 컨택 */
        String bizDv, /* 업무구분 */
        String prdf, /* 제품군 */
        String pdctNm, /* 제품명 */
        String cntrNo, /* 계약번호 */
        String cntrSn, /* 계약일련번호 */
        String cstNm, /* 고객명 */
        String dlqMcnt, /* 연체개월 */
        String ojAmt, /* 대상금액 */
        String ojDp, /* 대상입금 */
        String ojBlam, /* 대상금액 - 대상입금 = 대상잔액 */
        String totDlqAmt, /* 연체금액 + 연체가산금 = 총연체금 */
        String totDlqDp, /* 연체입금금액 + 연체가산금입금금액 = 총연체입금 */
        String totDlqBlam, /* 총연체금 - 총연체입금 = 총연체잔액 */
        String dlqAmt, /* 연체금액 */
        String dlqDp, /* 연체입금 */
        String dlqBlam, /* 연체금액 - 연체입금 = 연체잔액 */
        String mmChramAmt, /* 월요금액 */
        String mmChramDp, /* 월요금입금 */
        String mmChramBlam, /* 당월요금금액 - 당월요금입금금액 = 월요금잔액 */
        String dlqAddAmt, /* 연체가산금액 */
        String dlqAddDp, /* 연체가산입금 */
        String dlqAdamtBlam, /* 연체가산금액 - 연체가산금입금금액 = 연체가산금잔액 */
        String ucAmt, /* 미수금액 */
        String ucDp, /* 미수입금 */
        String ucBlam, /* 미수금 - 총입금액 = 미수잔액 */
        String totDpAmt, /* 연체입금금액 + 연체가산입금금액 + 해지위약금입금금액 + 당월요금입금금액 = 총입금액 */
        String spmtSl, /* 추가매출 */
        String ccam, /* 위약금 */
        String lsfe, /* 분실료 */
        String rtlfe1, /* 렌탈료1 */
        String rtlfeIstm1, /* 렌탈료1할 */
        String rtlfe2, /* 렌탈료2 */
        String rtlfeIstm2, /* 렌탈료2할 */
        String promDt, /* 약속일자 */
        String dprNm, /* 입금자명 */
        String cvcpInf, /* 민원정보 */
        String clctamPrtnrNo, /* 집금번호 */
        String clctamIchr, /* 집급담당 */
        String cntrCralLocaraTno,
        String cntrMexnoEncr,
        String cntrCralIdvTno, /* 계약휴대전화번호 */
        String cntrLocaraTno,
        String cntrExnoEncr,
        String cntrIdvTno, /* 계약전화번호 */
        String istCralLocaraTno,
        String istMexnoEncr,
        String istCralIdvTno, /* 설치휴대전화번호 */
        String istLocaraTno,
        String istExnoEncr,
        String istIdvTno, /* 설치전화번호 */
        String cstNo, /* 고객번호 */
        String tfDt, /* 이관일자 */
        String buNotiDt, /* 부담통보일자 */
        String buNotiTp, /* 부담통보유형 */
        String cntrZip, /* 계약우편번호 */
        String cntrAdr, /* 계약주소 */
        String istZip, /* 설치우편번호 */
        String istAdr, /* 설치주소 */
        String vtAcBnk, /* 가상계좌은행 */
        String vtAcNo, /* 가상계좌번호 */
        String pesuDt, /* 독촉일자 */
        String hiDt, /* 최고일자 */
        String ovrdDt, /* 채불일자 */
        String cpsnEfcm, /* 강제집행*/
        String dsbCmd, /* 지급명령*/
        String prtyClrs, /* 재산명시*/
        String submCmd, /* 제출명령*/
        String crcCmd, /* 보정명령*/
        String cujOvrd, /* 법원채불*/
        String vstRs, /* 방문결과*/
        String vstDt, /* 방문일자*/
        String sfk, /* 세이프키*/
        String unuitm, /* 특이사항*/
        String bndBizDvCd, /* 업무구분 */
        String cntrTpCd, /* 고객구분*/
        String bilDt, /* 이체일자 */
        String bilTpCd /* 납부방식유형코드*/
    ) {
        public SearchRes {
            cntrMexnoEncr = StringUtils.isNotEmpty(cntrMexnoEncr) ? DbEncUtil.dec(cntrMexnoEncr) : cntrMexnoEncr;
            cntrExnoEncr = StringUtils.isNotEmpty(cntrExnoEncr) ? DbEncUtil.dec(cntrExnoEncr) : cntrExnoEncr;
            istMexnoEncr = StringUtils.isNotEmpty(istMexnoEncr) ? DbEncUtil.dec(istMexnoEncr) : istMexnoEncr;
            istExnoEncr = StringUtils.isNotEmpty(istExnoEncr) ? DbEncUtil.dec(istExnoEncr) : istExnoEncr;
        }
    }

}
