package com.kyowon.sms.wells.web.withdrawal.idvrve.dto;

import com.sds.sflex.common.utils.DbEncUtil;
import io.swagger.annotations.ApiModel;
import org.apache.commons.lang.StringUtils;

/**
 * <pre>
 * 입금집계 상세현황 DTO
 * </pre>
 *
 * @author heungjun.lee
 * @since 2023-10-31
 */
public class WwdbDepositAggregateDtlServiceCenterDto {

    // *********************************************************
    // Request Dto
    // *********************************************************
    // 입금집계 상세현황 목록 조회 Search Request Dto
    @ApiModel(value = "WwwdbDepositAggregateDtlServiceCenterDto-SearchReq")
    public record SearchReq(
        String ogId, //센터코드
        String startDt, //시작일
        String endDt, //종료일
        String useYn, //대사처리
        String stlmDvCd //결제유형

    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 입금집계 상세현황 목록 조회 Search Result Dto
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
        String totRfndEtAmt, /*총환불예상금액 - 환불금액*/
        String blam, /*잔액 = 대사금액 - 환불금액*/
        String stlmDvNo, /*입금번호*/

        String stlmDvCd, /*결제구분코드*/
        String iscmpCd, /*결제처*/
        String iscmpNm, /*결제처명*/
        String cardAprno, /*승인번호*/
        String taxBll, /*세금계산서*/

        /* 팝업전달용 */
        String crcdonrNm, /*카드주*/
        String crcdnoEncr, /*카드번호*/
        String crdcdExpdtYm, /*카드유효기간*/
        String istmMcn, /*할부기간*/
        String vacNo, /*가상계좌번호*/
        String vacBnkCd, /*은행코드*/
        String csBilNo, /*비용청구번호*/
        String cstSvAsnNo, /*고객서비스배정번호*/
        String dpSn, /*입금일련번호*/
        String itgDpNo, /*통합입금번호*/
        String adpBilOjYn /*합산청구여부*/
    ) {
        public SearchRes {
            mexnoEncr = DbEncUtil.dec(mexnoEncr);
            crcdnoEncr = StringUtils.isNotEmpty(crcdnoEncr) ? DbEncUtil.dec(crcdnoEncr) : crcdnoEncr;
        }
    }

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 입금집계 상세현황 서비스 조회 - 조직 내역 Search Result Dto
    @ApiModel(value = "WwwdbDepositAggregateDtlServiceCenterDto-SearchCodeRes")
    public record SearchCodeRes(
        String codeId,
        String codeName
    ) {}

    // *********************************************************
    // Result Dto
    // ******************************************************** *
    // 입금집계 상세현황 서비스 합계 조회 Search Result Dto
    @ApiModel(value = "WwwdbDepositAggregateDtlServiceCenterDto-SearchSumRes")
    public record SearchSumRes(
        String bilAmtTot,
        String dpSumAmtTot,
        String rfndEtAmtTot,
        String blamTot
    ) {}
}
