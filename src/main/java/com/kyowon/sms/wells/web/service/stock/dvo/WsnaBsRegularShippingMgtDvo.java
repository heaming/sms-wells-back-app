package com.kyowon.sms.wells.web.service.stock.dvo;

import com.sds.sflex.system.config.annotation.DBDecField;
import com.sds.sflex.system.config.annotation.DBEncField;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WsnaBsRegularShippingMgtDvo {
    String asnOjYm; /* 배정년월 */
    String cntrNo; /* 계약번호 */
    String cntrSn; /* 계약일련번호 */
    String cstSvAsnNo; /* 고객서비스배정번호 */
    String cstKnm; /* 고객명 */
    String zip; /* 배송우편번호 */
    String basAdr; /* 배송기본주소 */
    String dtlAdr; /* 상세주소 */
    String cralLocaraTno; /* 휴대지역전화번호 */
    @DBEncField
    @DBDecField
    String mexnoEncr; /* 휴대전화국번호암호화 */
    String cralIdvTno; /* 휴대개별전화번호 */
    String locaraTno; /* 지역전화번호 */
    @DBEncField
    @DBDecField
    String exnoEncr; /* 전화국번호암호화 */
    String idvTno; /* 개별전화번호 */
    String sellTpCd; /* 판매유형코드 */
    String sellTpNm; /* 판매유형명 */
    String istDt; /* 설치일자 */
    String reqdDt; /* 철거일자 */
    String cpsDt; /* 보상일자 */
    String thReqdDt; /* 철거일자2 */
    String pdctPdCd; /* 제품상품코드 */
    String basePdCd; /* 기준상품코드 */
    String pdNm; /* 상품명 */
    String svBizDclsfCd; /* 서비스업무세분류코드 */
    String svBizDclsfNm; /* 서비스업무세분류명 */
    String istNmnN; /* 설치차월수 */
    String vstNmnN; /* 방문차월수 */
    String ppVstPrgsStatCd; /* 방문진행상태코드 */
    String ppVstPrgsStatNm; /* 방문진행상태명 */
    String vstDuedt; /* 방문예정일자 */
    String wkExcnDt; /* 작업수행일자(작업완료일자) */
    String vstOjLocaraCd; /* 방문대상지역코드 */
    String vstOjLocaraNm; /* 방문대상지역명 */
    String wareNo; /* 배송창고(창고번호) */
    String ogCd; /* 배송창고(번호) */
    String cnfmPsicPrtnrNo; /* 확정담당자파트너번호 */
    String siteAwPdGrpCd; /* 현장수당상품그룹코드 */
    String asLctCd; /* AS위치코드 */
    String asLctNm; /* AS위치명 */
    String asPhnCd; /* AS현상코드 */
    String asPhnNm; /* AS현상명 */
    String asCausCd; /* AS원인코드 */
    String asCausNm; /* AS원인명 */
    int partCntTotal; /* 투입부품모델수 */
    int partSumTotal; /* 투입부품수량합계 */
    //자재정보
    String partCd01; /* 투입부품코드01 */
    String partCd02; /* 투입부품코드02 */
    String partCd03; /* 투입부품코드03 */
    String partCd04; /* 투입부품코드04 */
    String partCd05; /* 투입부품코드05 */
    String partCd06; /* 투입부품코드06 */
    String partCd07; /* 투입부품코드07 */
    String partCd08; /* 투입부품코드08 */
    String partCd09; /* 투입부품코드09 */
    String partCd10; /* 투입부품코드10 */
    String partNm01; /* 투입부품명01 */
    String partNm02; /* 투입부품명02 */
    String partNm03; /* 투입부품명03 */
    String partNm04; /* 투입부품명04 */
    String partNm05; /* 투입부품명05 */
    String partNm06; /* 투입부품명06 */
    String partNm07; /* 투입부품명07 */
    String partNm08; /* 투입부품명08 */
    String partNm09; /* 투입부품명09 */
    String partNm10; /* 투입부품명10 */
    int partQty01; /* 투입부품갯수01 */
    int partQty02; /* 투입부품갯수02 */
    int partQty03; /* 투입부품갯수03 */
    int partQty04; /* 투입부품갯수04 */
    int partQty05; /* 투입부품갯수05 */
    int partQty06; /* 투입부품갯수06 */
    int partQty07; /* 투입부품갯수07 */
    int partQty08; /* 투입부품갯수08 */
    int partQty09; /* 투입부품갯수09 */
    int partQty10; /* 투입부품갯수10 */
    String frisuRcvryTpCd; /* 무상복구유형코드 */
    String sppAkSpfDt; /* 배송요청특정일자 */
    String bzrno; /* 사업자등록번호 */
    String bfsvcBzsDvCd; /* BS업체구분코드 */
    String sppDvCd; /* 배송구분코드 */
    String sppPdCd; /* 배송상품코드 */
    String cstNo; /* 계약고객번호 */
    String wareIchrNo; /* 배송창고(번호) */
    String wareMngtPrtnrno; /* 배송창고(파트너번호) */
    String vstPrgsStatCd; /* 방문진행상태코드 */
    String procsDvCd; /* 처리구분코드 */
    //    String sppPdNm;
    String partNmQty01; /* 투입부품정보(코드+이름+갯수)01 */
    String partNmQty02; /* 투입부품정보(코드+이름+갯수)02 */
    String partNmQty03; /* 투입부품정보(코드+이름+갯수)03 */
    String partNmQty04; /* 투입부품정보(코드+이름+갯수)04 */
    String partNmQty05; /* 투입부품정보(코드+이름+갯수)05 */
    String partNmQty06; /* 투입부품정보(코드+이름+갯수)06 */
    String partNmQty07; /* 투입부품정보(코드+이름+갯수)07 */
    String partNmQty08; /* 투입부품정보(코드+이름+갯수)08 */
    String partNmQty09; /* 투입부품정보(코드+이름+갯수)09 */
    String partNmQty10; /* 투입부품정보(코드+이름+갯수)10 */
    String vstPrgsStatNm; /* 방문진행상태명 */

    String ostrAkNo; /* 출고요청번호 */
    int ostrAkSn; /* 출고요청일련번호 */
    String tno; /* 전화번호tot */
    String mpno; /* 휴대전화번호tot */
    String pdGroupCd; /* 상품그룹코드 */
    int mpacSn; /* 합포장번호 */
    String lgstWkMthdCd; /* 물류작업방식코드 */
    String lgstOstrAkNo; /* 물류요청번호 */
}
