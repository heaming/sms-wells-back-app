package com.kyowon.sms.wells.web.service.stock.dto;

import javax.validation.constraints.NotBlank;

import com.sds.sflex.common.utils.StringUtil;

import io.swagger.annotations.ApiModel;

/**
 * <pre>
 * W-SV-U-0300M01 자가필터 배송관리
 * W-SV-U-0301M01 건식상품 배송관리
 * </pre>
 *
 * @author yeonghwa.cheon
 * @since 2023.08.01
 */
public class WsnaBsRegularShippingMgtDto {

    @ApiModel("WsnaAsConsumablesStoreDto-SearchProductReq")
    public record SearchProductReq(
        @NotBlank
        String asnYm, /* 배정년월 */
        @NotBlank
        String sppDvCd /* 프로그램ID (A1:자가필터, A2:건식상품) */
    ) {}

    @ApiModel("WsnaAsConsumablesStoreDto-SearchProductRes")
    public record SearchProductRes(
        String pdName, /* 상품명 */
        String pgGb, /* 그룹핑코드 (G:배송상품그룹매핑, P:미매핑상품) */
        String lgstWkMthdCd, /* 물류작업방식코드 */
        int cntLgstWkMthdCd /* 물류작업방식코드 건수 */
    ) {}

    @ApiModel("WsnaAsConsumablesStoreDto-SearchReq")
    public record SearchReq(
        @NotBlank
        String asnYm, /* 배정년월 */
        @NotBlank
        String sppDvCd, /* 프로그램ID (A1:자가필터, A2:건식상품) */
        @NotBlank
        String lgstWkMthdCd, /* 물류요청작업방식코드 */
        String pgGb, /* 그룹핑코드 (G:배송상품그룹매핑, P:미매핑상품) */
        String procsDvCd, /* 처리구분 */
        String rownum /* 조회제한건수 */
    ) {}

    @ApiModel("WsnaAsConsumablesStoreDto-SearchRes")
    public record SearchRes(
        String asnOjYm, /* 배정년월 */
        String cntrNo, /* 계약번호 */
        String cntrSn, /* 계약일련번호 */
        String cstSvAsnNo, /* 고객서비스배정번호 */
        String cstKnm, /* 고객명 */
        String zip, /* 배송우편번호 */
        String sppAkSpfDt, /* 배송요청특정일자 */
        //자재정보
        String partCd01, /* 투입부품코드01 */
        String partNm01, /* 투입부품명01 */
        int partQty01, /* 투입부품갯수01 */
        String partCd02, /* 투입부품코드02 */
        String partNm02, /* 투입부품명02 */
        int partQty02, /* 투입부품갯수02 */
        String partCd03, /* 투입부품코드03 */
        String partNm03, /* 투입부품명03 */
        int partQty03, /* 투입부품갯수03 */
        String partCd04, /* 투입부품코드04 */
        String partNm04, /* 투입부품명04 */
        int partQty04, /* 투입부품갯수04 */
        String partCd05, /* 투입부품코드05 */
        String partNm05, /* 투입부품명05 */
        int partQty05, /* 투입부품갯수05 */
        String partCd06, /* 투입부품코드06 */
        String partNm06, /* 투입부품명06 */
        int partQty06, /* 투입부품갯수06 */
        String partCd07, /* 투입부품코드07 */
        String partNm07, /* 투입부품명07 */
        int partQty07, /* 투입부품갯수07 */
        String partCd08, /* 투입부품코드08 */
        String partNm08, /* 투입부품명08 */
        int partQty08, /* 투입부품갯수08 */
        String partCd09, /* 투입부품코드09 */
        String partNm09, /* 투입부품명09 */
        int partQty09, /* 투입부품갯수09 */
        String partCd10, /* 투입부품코드10 */
        String partNm10, /* 투입부품명10 */
        int partQty10, /* 투입부품갯수10 */
        String istDt, /* 설치일자 */
        String reqdDt, /* 철거일자 */
        String cpsDt, /* 보상일자 (취소일자?) */
        String thReqdDt, /* 철거일자2 */
        String sellTpCd, /* 판매유형코드 */
        String sellTpNm, /* 판매유형명 */
        String istNmnN, /* 설치차월수 */
        String vstNmnN, /* 방문차월수 */
        String pdctPdCd, /* 제품상품코드 */
        String basePdCd, /* 기준상품코드 */
        String pdNm, /* 상품명 */
        String svBizDclsfCd, /* 서비스업무세분류코드 */
        String svBizDclsfNm, /* 서비스업무세분류명 */
        String vstDuedt, /* 방문예정일자 */
        String ppVstPrgsStatCd, /* 방문진행상태코드 */
        String ppVstPrgsStatNm, /* 방문진행상태명 */
        String vstPrgsStatCd, /* 방문진행상태코드 */
        String vstPrgsStatNm, /* 방문진행상태명 */
        String wkExcnDt, /* 작업수행일자(작업완료일자) */
        String asLctCd, /* AS위치코드 */
        String asLctNm, /* AS위치명 */
        String asPhnCd, /* AS현상코드 */
        String asPhnNm, /* AS현상명 */
        String asCausCd, /* AS원인코드 */
        String asCausNm, /* AS원인명 */
        String wareNo, /* 배송창고(창고번호) */
        String wareIchrNo, /* 배송창고(번호) */
        String wareMngtPrtnrno, /* 배송창고(파트너번호) */
        String vstOjLocaraCd, /* 방문대상지역코드 */
        String vstOjLocaraNm, /* 방문대상지역명 */
        String locaraTno, /* 지역전화번호 */
        String exnoEncr, /* 전화국번호암호화 */
        String idvTno, /* 개별전화번호 */
        String tno, /* 전화번호tot */
        String cralLocaraTno, /* 휴대지역전화번호 */
        String mexnoEncr, /* 휴대전화국번호암호화 */
        String cralIdvTno, /* 휴대개별전화번호 */
        String mpno, /* 휴대전화번호tot */
        String basAdr, /* 배송기본주소 */
        String dtlAdr, /* 상세주소 */
        String cnfmPsicPrtnrNo, /* 확정담당자파트너번호 */
        String siteAwPdGrpCd, /* 현장수당상품그룹코드 */
        int partCntTotal, /* 투입부품모델수 */
        int partSumTotal, /* 투입부품수량합계 */
        String frisuRcvryTpCd, /* 무상복구유형코드 */
        String bzrno, /* 사업자등록번호 */
        String bfsvcBzsDvCd, /* BS업체구분코드 */
        String sppDvCd, /* 배송구분코드 */
        String sppPdCd, /* 배송상품코드 */
        String procsDvCd, /* 처리구분코드 */
        //        String sppPdNm,
        String partNmQty01, /* 투입부품정보(코드+이름+갯수)01 */
        String partNmQty02, /* 투입부품정보(코드+이름+갯수)02 */
        String partNmQty03, /* 투입부품정보(코드+이름+갯수)03 */
        String partNmQty04, /* 투입부품정보(코드+이름+갯수)04 */
        String partNmQty05, /* 투입부품정보(코드+이름+갯수)05 */
        String partNmQty06, /* 투입부품정보(코드+이름+갯수)06 */
        String partNmQty07, /* 투입부품정보(코드+이름+갯수)07 */
        String partNmQty08, /* 투입부품정보(코드+이름+갯수)08 */
        String partNmQty09, /* 투입부품정보(코드+이름+갯수)09 */
        String partNmQty10, /* 투입부품정보(코드+이름+갯수)10 */
        String cstNo, /* 고객번호 */
        String pdGroupCd, /* 상품그룹코드 */
        String mpacSn /* 합포장번호 */
    ) {
        public SearchRes {
            if (StringUtil.isNotBlank(locaraTno) && StringUtil.isNotBlank(exnoEncr)
                && StringUtil.isNotBlank(idvTno)) {
                tno = locaraTno + exnoEncr + idvTno;
            } else if (StringUtil.isNotBlank(locaraTno) && StringUtil.isNotBlank(idvTno)) {
                tno = locaraTno + idvTno;
            }
            mpno = cralLocaraTno + mexnoEncr + cralIdvTno;
        }
    }

    @ApiModel("WsnaAsConsumablesStoreDto-SaveReq")
    public record SaveReq(
        String asnOjYm, /* 배정년월 */
        @NotBlank
        String cntrNo, /* 계약번호 */
        @NotBlank
        String cntrSn, /* 계약일련번호 */
        @NotBlank
        String cstSvAsnNo, /* 고객서비스배정번호 */
        String cstKnm, /* 고객명 */
        String zip, /* 배송우편번호 */
        String sppAkSpfDt, /* 배송요청특정일자 */
        //자재정보
        String partCd01, /* 투입부품코드01 */
        String partNm01, /* 투입부품명01 */
        int partQty01, /* 투입부품갯수01 */
        String partCd02, /* 투입부품코드02 */
        String partNm02, /* 투입부품명02 */
        int partQty02, /* 투입부품갯수02 */
        String partCd03, /* 투입부품코드03 */
        String partNm03, /* 투입부품명03 */
        int partQty03, /* 투입부품갯수03 */
        String partCd04, /* 투입부품코드04 */
        String partNm04, /* 투입부품명04 */
        int partQty04, /* 투입부품갯수04 */
        String partCd05, /* 투입부품코드05 */
        String partNm05, /* 투입부품명05 */
        int partQty05, /* 투입부품갯수05 */
        String partCd06, /* 투입부품코드06 */
        String partNm06, /* 투입부품명06 */
        int partQty06, /* 투입부품갯수06 */
        String partCd07, /* 투입부품코드07 */
        String partNm07, /* 투입부품명07 */
        int partQty07, /* 투입부품갯수07 */
        String partCd08, /* 투입부품코드08 */
        String partNm08, /* 투입부품명08 */
        int partQty08, /* 투입부품갯수08 */
        String partCd09, /* 투입부품코드09 */
        String partNm09, /* 투입부품명09 */
        int partQty09, /* 투입부품갯수09 */
        String partCd10, /* 투입부품코드10 */
        String partNm10, /* 투입부품명10 */
        int partQty10, /* 투입부품갯수10 */
        String istDt, /* 설치일자 */
        String reqdDt, /* 철거일자 */
        String cpsDt, /* 보상일자 */
        String thReqdDt, /* 철거일자2 */
        String sellTpCd, /* 판매유형코드 */
        String sellTpNm, /* 판매유형명 */
        String istNmnN, /* 설치차월수 */
        String vstNmnN, /* 방문차월수 */
        String pdctPdCd, /* 제품상품코드 */
        String basePdCd, /* 기준상품코드 */
        String pdNm, /* 상품명 */
        String svBizDclsfCd, /* 서비스업무세분류코드 */
        String svBizDclsfNm, /* 서비스업무세분류명 */
        String vstDuedt, /* 방문예정일자 */
        String ppVstPrgsStatCd, /* 방문진행상태코드 */
        String ppVstPrgsStatNm, /* 방문진행상태명 */
        String vstPrgsStatCd, /* 방문진행상태코드 */
        String vstPrgsStatNm, /* 방문진행상태명 */
        String wkExcnDt, /* 작업수행일자(작업완료일자) */
        String asLctCd, /* AS위치코드 */
        String asLctNm, /* AS위치명 */
        String asPhnCd, /* AS현상코드 */
        String asPhnNm, /* AS현상명 */
        String asCausCd, /* AS원인코드 */
        String asCausNm, /* AS원인명 */
        String wareNo, /* 배송창고(창고번호) */
        String wareIchrNo, /* 배송창고(번호) */
        String wareMngtPrtnrno, /* 배송창고(파트너번호) */
        String vstOjLocaraCd, /* 방문대상지역코드 */
        String vstOjLocaraNm, /* 방문대상지역명 */
        String locaraTno, /* 지역전화번호 */
        String exnoEncr, /* 전화국번호암호화 */
        String idvTno, /* 개별전화번호 */
        String tno, /* 전화번호tot */
        String cralLocaraTno, /* 휴대지역전화번호 */
        String mexnoEncr, /* 휴대전화국번호암호화 */
        String cralIdvTno, /* 휴대개별전화번호 */
        String mpno, /* 휴대전화번호tot */
        String basAdr, /* 배송기본주소 */
        String dtlAdr, /* 상세주소 */
        String cnfmPsicPrtnrNo, /* 확정담당자파트너번호 */
        String siteAwPdGrpCd, /* 현장수당상품그룹코드 */
        int partCntTotal, /* 투입부품모델수 */
        int partSumTotal, /* 투입부품수량합계 */
        String frisuRcvryTpCd, /* 무상복구유형코드 */
        String bzrno, /* 사업자등록번호 */
        String bfsvcBzsDvCd, /* BS업체구분코드 */
        String sppDvCd, /* 배송구분코드 */
        String sppPdCd, /* 배송상품코드 */
        String procsDvCd, /* 처리구분코드 */
        //        String sppPdNm,
        String partNmQty01, /* 투입부품정보(코드+이름+갯수)01 */
        String partNmQty02, /* 투입부품정보(코드+이름+갯수)02 */
        String partNmQty03, /* 투입부품정보(코드+이름+갯수)03 */
        String partNmQty04, /* 투입부품정보(코드+이름+갯수)04 */
        String partNmQty05, /* 투입부품정보(코드+이름+갯수)05 */
        String partNmQty06, /* 투입부품정보(코드+이름+갯수)06 */
        String partNmQty07, /* 투입부품정보(코드+이름+갯수)07 */
        String partNmQty08, /* 투입부품정보(코드+이름+갯수)08 */
        String partNmQty09, /* 투입부품정보(코드+이름+갯수)09 */
        String partNmQty10, /* 투입부품정보(코드+이름+갯수)10 */
        String cstNo, /* 계약고객번호 */
        String pdGroupCd, /* 상품그룹코드 */
        String mpacSn, /* 합포장번호 */
        String lgstWkMthdCd /* 물류작업방식코드 */
    ) {}

    @ApiModel("WsnaAsConsumablesStoreDto-WareMngtRes")
    public record WareMngtRes(
        String wareMngtPrtnrNo, /* 배송창고(파트너번호) */
        String wareMngtPrtnrOgTpCd /* 배송창고(파트너조직유형코드) */
    ) {}
}
