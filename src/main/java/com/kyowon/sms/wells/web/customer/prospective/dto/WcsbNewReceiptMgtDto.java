package com.kyowon.sms.wells.web.customer.prospective.dto;

import org.apache.commons.lang.StringUtils;

import com.sds.sflex.common.utils.DbEncUtil;
import com.sds.sflex.common.utils.StringUtil;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

public class WcsbNewReceiptMgtDto {

    @ApiModel(value = "WcsbNewReceiptMgtDto-SearchReq")
    public record SearchReq(
        String assignDtFrom,
        String assignDtTo,
        String sellInflwchnlDvCd,
        String[] prdtType,
        Boolean isAllPrdt
    ) {}

    @ApiModel(value = "WcsbNewReceiptMgtDto-SearchRes")
    public record SearchRes(
        String pspcCstInflwDt,
        String aplcSn,
        String pspcCstCnslId,
        String ichrAsnFshDt,
        String ichrOgTpCd,
        String ichrPrtnrNo,
        String ichrPrtnrNm,
        String sellInflwChnlDvCd,
        String recvTpNm,
        String inrtPdDvNm,
        String inrtPdDvCd,
        String contactDate,
        String contactTime,
        String pspcCstRcpCn,
        String pspcCstKnm,
        String otsdLkDrmVal,
        String cralLocaraTno,
        String mexnoEncr,
        String cralIdvTno,
        String locaraTno,
        String exnoEncr,
        String idvTno,
        String newAdrZip,
        String custAdr,
        String fstRgstDt,
        String sppDuedt,
        String cntrPdStrtdt,
        String cntrNo,
        String pdNm,

        String pspcCstCnslRsNm,
        String cnslMoCn,

        // 가공 column 
        String phNo, /* 고객 휴대전화 */
        String wireTelNo, /* 고객 유선전화 */

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

    @ApiModel("WcsbNewReceiptMgtDto-AssignReq")
    public record AssignReq(
        String pspcCstCnslId,
        String ogTpCd,
        String prtnrNo
    ) {}

    @ApiModel(value = "WcsbNewReceiptMgtDto-SearchDtlRes")
    public record SearchDtlRes(
        String pspcCstInflwDt,
        String aplcSn,
        String pspcCstCnslId,
        String ichrAsnFshDt,
        String ichrOgTpCd,
        String ichrPrtnrNo,
        String ichrPrtnrNm,
        String pspcCstCnslRsCd,
        String pspcCstCnslRsNm,
        String cnslMoCn,
        String sellInflwChnlDvCd,
        String recvTpNm,
        String inrtPdDvNm,
        String inrtPdDvCd,
        String contactDate,
        String contactTime,
        String pspcCstRcpCn,
        String pspcCstKnm,
        String otsdLkDrmVal,
        String cralLocaraTno,
        String mexnoEncr,
        String cralIdvTno,
        String locaraTno,
        String exnoEncr,
        String idvTno,
        String newAdrZip,
        String custAdr,
        String fstRgstDt,
        String sppDuedt,
        String cntrPdStrtdt,
        String cntrNo,
        String pdNm,

        // 가공 column 
        String phNo, /* 고객 휴대전화 */
        String wireTelNo, /* 고객 유선전화 */

        String fstRgstDtm,
        String fstRgstUsrNm,
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
        }
    }

    @ApiModel("WcsbNewReceiptMgtDto-ContactReq")
    public record ContactReq(
        String[] pspcCstCnslIds,
        String pspcCstCnslId,
        String pspcCstCnslRsCd,
        String cnslMoCn
    ) {}

    /*
     * 배정조회 (TAB)   - Assign 
     */
    @ApiModel(value = "WcsbNewReceiptMgtDto-SearchAssignReq")
    public record SearchAssignReq(
        String assignDtFrom,
        String assignDtTo,
        String[] prdtType,
        Boolean isAllPrdt,
        String ichrPrtnrNo
    ) {}

    /*
     * ---------------------------------------
     *       집계 (TAB) - Summaries
     * ---------------------------------------       
     */
    @ApiModel(value = "EcsbSmartExperienceDeviceMgtDto-SearchSummariesReq")
    public record SearchSummariesReq(
        String recvDtFrom,
        String recvDtTo,
        String[] prdtType,
        Boolean isAllPrdt
    ) {}

    @ApiModel(value = "EcsbSmartExperienceDeviceMgtDto-SearchSummariesRes")
    public record SearchSummariesRes(
        String inrtPdDvNm,
        String inrtPdDvCd,
        int recvCount,
        int cntrCount
    ) {}

}
