package com.kyowon.sms.wells.web.customer.prospective.dto;

import org.apache.commons.lang.StringUtils;

import com.sds.sflex.common.utils.DbEncUtil;
import com.sds.sflex.common.utils.StringUtil;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

/**
 * <pre>
 * 고객 >>  신규접수 배정관리 Dto
 * </pre>
 *
 * @author junho.bae
 * @since 2023-07-01
 */
public class WcsbNewReceiptMgtDto {

    @ApiModel(value = "WcsbNewReceiptMgtDto-SearchReq")
    public record SearchReq(
        String assignDtFrom, /* 배정시작일 */
        String assignDtTo, /* 배정종료일 */
        String sellInflwchnlDvCd, /* 접수구분 */
        String[] prdtType, /* 상품유형 */
        Boolean isAllPrdt /* 상품유형 검색조건 쿼리 수행할지 판단 변수 */
    ) {}

    @ApiModel(value = "WcsbNewReceiptMgtDto-SearchRes")
    public record SearchRes(
        String pspcCstInflwDt, /* 가망고객유입일자 */
        String aplcSn, /* 신청일련번호 */
        String pspcCstCnslId, /* 가망고객상담ID */
        String ichrAsnFshDt, /* 담당배정완료일시 */
        String ichrOgTpCd, /* 조직담당유형코드 */
        String ichrPrtnrNo, /* 파트너번호 */
        String ichrPrtnrNm, /* 파트너명 */
        String sellInflwChnlDvCd, /* 판매유입채널구분코드 */
        String recvTpNm, /* 접수구분 */
        String inrtPdDvNm, /* 상품구분 */
        String inrtPdDvCd, /* 상품구분코드 */
        String contactDate, /* 컨택요청일 */
        String contactTime, /* 요청시간 */
        String pspcCstRcpCn, /* 요청내용 */
        String pspcCstKnm, /* 고객명 */
        String otsdLkDrmVal, /* 고객코드 */
        String cralLocaraTno,
        String mexnoEncr,
        String cralIdvTno,
        String locaraTno,
        String exnoEncr,
        String idvTno, /* 휴대개별전화번호 */
        String newAdrZip, /* 우편번호 */
        String custAdr, /* 주소 */
        String fstRgstDt,
        String sppDuedt, /* 배송예정일자 */
        String cntrPdStrtdt, /* 매출일 */
        String cntrNo, /* 계약번호 */
        String pdNm, /* 상품명 */

        String pspcCstCnslRsNm, /* 배정컨택 */
        String cnslMoCn, /* 비고 */

        // 가공 column
        String phNo, /* 고객 휴대전화 */
        String wireTelNo, /* 고객 유선전화 */

        String fstRgstDtmCp, /* 생성일 */
        String fstRgstDeptNm,
        String fnlMdfcDeptNm,
        String fstRgstDtm,
        String fstRgstUsrNm,
        String fnlMdfcDtm,
        String fnlMdfcUsrNm,
        String fstRgstUsrId,
        String fnlMdfcUsrId
    ) {
        public SearchRes {

            mexnoEncr = StringUtils.isNotEmpty(mexnoEncr) ? DbEncUtil.dec(mexnoEncr) : mexnoEncr;
            if (StringUtils.isNotEmpty(cralLocaraTno) || StringUtils.isNotEmpty(mexnoEncr)
                || StringUtils.isNotEmpty(cralIdvTno)) {
                phNo = StringUtil.nvl2(cralLocaraTno, " ") + "-" + StringUtil.nvl2(mexnoEncr, " ") + "-"
                    + StringUtil.nvl2(cralIdvTno, " ");
            }

            exnoEncr = StringUtils.isNotEmpty(exnoEncr) ? DbEncUtil.dec(exnoEncr) : exnoEncr;
            if (StringUtils.isNotEmpty(locaraTno) || StringUtils.isNotEmpty(exnoEncr)
                || StringUtils.isNotEmpty(idvTno)) {
                wireTelNo = StringUtil.nvl2(locaraTno, " ") + "-" + StringUtil.nvl2(exnoEncr, " ") + "-"
                    + StringUtil.nvl2(idvTno, " ");
            }

            if (StringUtils.isNotEmpty(pspcCstInflwDt)) {
                pspcCstInflwDt = pspcCstInflwDt.substring(0, 4) + "-" + pspcCstInflwDt.substring(4, 6) + "-"
                    + pspcCstInflwDt.substring(6, 8);
            }
            if (StringUtils.isNotEmpty(ichrAsnFshDt)) {
                ichrAsnFshDt = ichrAsnFshDt.substring(0, 4) + "-" + ichrAsnFshDt.substring(4, 6) + "-"
                    + ichrAsnFshDt.substring(6, 8);
            }
            if (StringUtils.isNotEmpty(contactDate)) {
                contactDate = contactDate.substring(0, 4) + "-" + contactDate.substring(4, 6) + "-"
                    + contactDate.substring(6, 8);
            }
            if (StringUtils.isNotEmpty(cntrPdStrtdt)) {
                cntrPdStrtdt = cntrPdStrtdt.substring(0, 4) + "-" + cntrPdStrtdt.substring(4, 6) + "-"
                    + cntrPdStrtdt.substring(6, 8);
            }
            if (StringUtils.isNotEmpty(sppDuedt)) {
                sppDuedt = sppDuedt.substring(0, 4) + "-" + sppDuedt.substring(4, 6) + "-"
                    + sppDuedt.substring(6, 8);
            }

        }
    }

    @Builder
    @ApiModel("WcsbNewReceiptMgtDto-SaveReq")
    public record SaveReq(
        String rowState, /* 그리드 Row 상태 */
        String days, /* 날짜 */
        String bsdt, /* 기준일자 */
        String ogTpCd, /* 조직유형코드 */
        Long sellBaseQty, /* 판매기준수량 */
        Long rngBaseQty, /* 범위기준수량 */
        String aplcBaseMoCn, /* 신청기준메모내용 */
        String fnlMdfcDtm
    ) {}

    @ApiModel(value = "WcsbNewReceiptMgtDto-PartnerRes")
    public record PartnerRes(
        String ogId, /* 조직ID */
        String ogTpCd, /* 조직유형 코드 */
        String ogCd, /* 조직코드 */
        String ogNm, /* 조직명 */
        String pstnDvCd, /* 직급코드 */
        String pstnNm, /* 직급명 */
        String prtnrNo, /* 파트너사번 */
        String prtnrNm, /* 파트너이름 */
        String prtnrMpNo1, /* 파트너휴대폰번호1 */
        String prtnrMpNo2, /* 파트너휴대폰번호2 */
        String prtnrMpNo3, /* 파트너휴대폰번호3 */
        String prtnrHpNo /* 파트너휴대폰번호1+2+3 가공컬럼 */

    ) {
        public PartnerRes {
            //            prtnrMpNo2 = StringUtils.isNotEmpty(prtnrMpNo2) ? DbEncUtil.dec(prtnrMpNo2) : prtnrMpNo2;
            //            prtnrHpNo = prtnrHpNo + "-" + prtnrMpNo2 + "-" + prtnrMpNo3;
            prtnrMpNo2 = StringUtils.isNotEmpty(prtnrMpNo2) ? DbEncUtil.dec(prtnrMpNo2) : prtnrMpNo2;
            if (StringUtils.isNotEmpty(prtnrMpNo1) || StringUtils.isNotEmpty(prtnrMpNo2)
                || StringUtils.isNotEmpty(prtnrMpNo3)) {
                prtnrHpNo = StringUtil.nvl2(prtnrMpNo1, " ") + "-" + StringUtil.nvl2(prtnrMpNo2, " ") + "-"
                    + StringUtil.nvl2(prtnrMpNo3, " ");
            }
        }
    }

    @Builder
    @ApiModel("WcsbNewReceiptMgtDto-AssignReq")
    public record AssignReq(
        String[] pspcCstCnslIds, /* 가망고개상담ID 배열 */
        String ogTpCd, /* 조직유형코드 */
        String prtnrNo, /* 파트너번호 */
        String pspcCstCnslId /* 가망고개상담ID */

    ) {}

    @ApiModel(value = "WcsbNewReceiptMgtDto-SearchDtlRes")
    public record SearchDtlRes(
        String pspcCstInflwDt, /* 가망고객유입일자 */
        String aplcSn, /* 신청일련번호 */
        String pspcCstCnslId, /* 가망고개상담ID */
        String ichrAsnFshDt, /* 담당배정완료일시 */
        String ichrOgTpCd, /* 담당조직유형코드 */
        String ichrPrtnrNo, /* 파트너번호 */
        String ichrPrtnrNm, /* 파트너명 */
        String pspcCstCnslRsCd, /* 가망고객상담결과코드 */
        String pspcCstCnslRsNm, /* 가망고객상담결과명 */
        String cnslMoCn, /* 비고 */
        String sellInflwChnlDvCd, /* 판매유입채널구분코드 */
        String recvTpNm, /* 접수구분 */
        String inrtPdDvNm, /* 상품구분명 */
        String inrtPdDvCd, /* 상품구분코드 */
        String contactDate, /* 계약일 */
        String contactTime, /* 계약시간 */
        String pspcCstRcpCn, /* 요청내용 */
        String pspcCstKnm, /* 고객명 */
        String otsdLkDrmVal, /* 고객코드 */
        String cralLocaraTno,
        String mexnoEncr,
        String cralIdvTno,
        String locaraTno,
        String exnoEncr,
        String idvTno, /* 휴대개별전화번호 */
        String newAdrZip, /* 우편번호 */
        String custAdr, /* 주소 */
        String fstRgstDt,
        String sppDuedt, /* 배송예정일자 */
        String cntrPdStrtdt, /* 매출일 */
        String cntrNo, /* 계약번호 */
        String pdNm, /* 상품명 */

        // 가공 column
        String phNo, /* 고객 휴대전화 */
        String wireTelNo, /* 고객 유선전화 */

        String fstRgstDtmCp, /* 생성일 */
        String fstRgstDtm,
        String fstRgstUsrNm,
        String fstRgstDeptNm,
        String fnlMdfcDeptNm,
        String fnlMdfcDtm,
        String fnlMdfcUsrNm,
        String fstRgstUsrId,
        String fnlMdfcUsrId

    ) {
        public SearchDtlRes {

            mexnoEncr = StringUtils.isNotEmpty(mexnoEncr) ? DbEncUtil.dec(mexnoEncr) : mexnoEncr;
            if (StringUtils.isNotEmpty(cralLocaraTno) || StringUtils.isNotEmpty(mexnoEncr)
                || StringUtils.isNotEmpty(cralIdvTno)) {
                phNo = StringUtil.nvl2(cralLocaraTno, " ") + "-" + StringUtil.nvl2(mexnoEncr, " ") + "-"
                    + StringUtil.nvl2(cralIdvTno, " ");
            }

            exnoEncr = StringUtils.isNotEmpty(exnoEncr) ? DbEncUtil.dec(exnoEncr) : exnoEncr;
            if (StringUtils.isNotEmpty(locaraTno) || StringUtils.isNotEmpty(exnoEncr)
                || StringUtils.isNotEmpty(idvTno)) {
                wireTelNo = StringUtil.nvl2(locaraTno, " ") + "-" + StringUtil.nvl2(exnoEncr, " ") + "-"
                    + StringUtil.nvl2(idvTno, " ");
            }

            if (StringUtils.isNotEmpty(pspcCstInflwDt)) {
                pspcCstInflwDt = pspcCstInflwDt.substring(0, 4) + "-" + pspcCstInflwDt.substring(4, 6) + "-"
                    + pspcCstInflwDt.substring(6, 8);
            }
            if (StringUtils.isNotEmpty(ichrAsnFshDt)) {
                ichrAsnFshDt = ichrAsnFshDt.substring(0, 4) + "-" + ichrAsnFshDt.substring(4, 6) + "-"
                    + ichrAsnFshDt.substring(6, 8);
            }
            if (StringUtils.isNotEmpty(contactDate)) {
                contactDate = contactDate.substring(0, 4) + "-" + contactDate.substring(4, 6) + "-"
                    + contactDate.substring(6, 8);
            }
            if (StringUtils.isNotEmpty(cntrPdStrtdt)) {
                cntrPdStrtdt = cntrPdStrtdt.substring(0, 4) + "-" + cntrPdStrtdt.substring(4, 6) + "-"
                    + cntrPdStrtdt.substring(6, 8);
            }
            if (StringUtils.isNotEmpty(sppDuedt)) {
                sppDuedt = sppDuedt.substring(0, 4) + "-" + sppDuedt.substring(4, 6) + "-"
                    + sppDuedt.substring(6, 8);
            }
        }
    }

    @ApiModel("WcsbNewReceiptMgtDto-ContactReq")
    public record ContactReq(
        String[] pspcCstCnslIds, /* 가망고개상담ID 배열 */
        String pspcCstCnslId, /* 가망고개상담ID */
        String pspcCstCnslRsCd, /* 가망고객상담결과코드 */
        String cnslMoCn /* 비고 */
    ) {}

    /*
     * 배정조회 (TAB)   - Assign
     */
    @ApiModel(value = "WcsbNewReceiptMgtDto-SearchAssignReq")
    public record SearchAssignReq(
        String assignDtFrom, /* 배정시작일 */
        String assignDtTo, /* 배정종료일 */
        String[] prdtType, /* 상품유형 */
        Boolean isAllPrdt, /* 상품유형 검색조건 쿼리 수행할지 판단 변수 */
        String ichrPrtnrNo /* 파트너번호 */
    ) {}

    /*
     * ---------------------------------------
     *       집계 (TAB) - Summaries
     * ---------------------------------------
     */
    @ApiModel(value = "EcsbSmartExperienceDeviceMgtDto-SearchSummariesReq")
    public record SearchSummariesReq(
        String recvDtFrom, /* 집계시작일자 */
        String recvDtTo, /* 집계종료일자 */
        String[] prdtType, /* 상품유형 */
        Boolean isAllPrdt /* 상품유형 검색조건 쿼리 수행할지 판단 변수 */
    ) {}

    @ApiModel(value = "EcsbSmartExperienceDeviceMgtDto-SearchSummariesRes")
    public record SearchSummariesRes(
        String inrtPdDvNm, /* 상품구분 */
        String inrtPdDvCd, /* 상품구분 코드 */
        int recvCount, /* 접수카운트 */
        int cntrCount /* 계약카운트 */
    ) {}

}
