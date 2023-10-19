package com.kyowon.sms.wells.web.service.stock.dvo;

import com.sds.sflex.system.config.annotation.DBDecField;
import com.sds.sflex.system.config.annotation.DBEncField;

import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * W-SV-U-0300M01 자가필터 배송관리
 * W-SV-U-0301M01 건식상품 배송관리
 * </pre>
 *
 * @author yeonghwa.cheon
 * @since 2023.08.01
 */
@Getter
@Setter
public class WsnaBsRegularShippingMaterialDvo {
    String ostrAkNo; /* 출고요청번호 */
    int ostrAkSn; /* 출고요청일련번호 */
    String sppDvCd; /* 프로그램ID (A1:자가필터, A2:건식상품) */
    String asnOjYm; /* 배정년월 */
    String cntrNo; /* 계약번호 */
    String cntrSn; /* 계약일련번호 */
    String cstSvAsnNo; /* 고객서비스배정번호 */
    String cstNo; /* 계약고객번호 */
    String cstKnm; /* 고객명 */
    String zip; /* 배송우편번호 */
    String basAdr; /* 배송기본주소 */
    String dtlAdr; /* 상세주소 */
    String sppAkSpfDt; /* 배송요청특정일자 */
    @DBEncField
    @DBDecField
    String tno; /* 전화번호tot */
    @DBEncField
    @DBDecField
    String mpno; /* 휴대전화번호tot */
    //자재정보
    String itmPdCd; /* 상품코드(투입부품코드) */
    String partNm; /* 상품명(투입부품명) */
    int ostrAkQty; /* 출고요청갯수(투입부품갯수) */
    String pdCn; /* 투입부품정보(코드+이름+갯수)01 */
    String svBizDclsfCd; /* 서비스업무세분류코드 */
    String pdctPdCd; /* 제품상품코드 */
    String sellTpCd; /* 판매유형코드 */
    String istDt; /* 설치일자 */
    String ogCd; // 배송창고(번호)

    // 물류센터 요청용 세팅해주는 값
    String ostrAkTpCd; // 출고요청유형코드
    String ostrAkRgstDt; //출고요청일자
    String iostAkDvCd; // 입출고요청구분코드
    int mpacSn; // 합포장일련번호
    String lgstSppMthdCd; // 물류배송방식코드
    String lgstWkMthdCd; // 물류작업방식코드
    String lgstOstrAkNo; /* 물류요청번호 */
    @DBEncField
    @DBDecField
    String adrsTnoVal; // 수취인전화번호
    @DBEncField
    @DBDecField
    String adrsCphonNoVal; // 수취인휴대폰번호값
    String wareMngtPrtnrNo; /* 배송창고(파트너번호) */
    String wareMngtPrtnrOgTpCd; /* 배송창고(파트너조직유형코드) */
    String ostrOjWareNo; /* 출고대상창고번호 */
    String svCnrCd; /* 서비스센터코드 */
    String svCnrNm; /* 서비스센터명 */
    String svCnrIchrPrtnrNm; /* 서비스센터담당파트너명 */
    String svCnrLkTnoEncr; /* 서비스센터전화번호암호화 */
    String svCnrAdr; /* 서비스센터주소 */
    String ovivTpCd; /* 배차유형코드 */
    String strOjWareNo; /* 입고대상창고번호 */
    String wareDtlDvCd; /* 입고대상 창고의 상세구분코드 */
    String wareNm; /* 입고대상창고명 */
    String itmGdCd; /* 품목등급코드 */
    String cstNm; /* 고객명 */
    String wkWareNo; /* 작업창고번호 */
}
