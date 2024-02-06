package com.kyowon.sms.wells.web.service.stock.dvo;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WsnaPcsvOutOfStorageSaveDvo {

    /* 작업결과 */
    String cstSvAsnNo; /* 고객서비스배정번호 */
    String prtnrNo;  /* 파트너번호 */
    String siteAwSvTpCd; /* 현장수당서비스유형코드 */
    String siteAwAtcCd;  /* 현장수당항목코드 */
    String cntrNo; /*계약번호*/
    String cntrSn;  /* 계약일련번호 */
    String urgtDvCd; // 긴급구분코드 (1: 고객센터 당일 접수 건, NULL: 그외)
    String rpbLocaraCd; // 책임지역코드
    String asLctCd; /* AS위치코드 */
    String asPhnCd; /* AS현상코드 */
    String asCausCd; /* AS원인코드 */
    String ogId; /*조직ID*/
    String ogTpCd; /* 조직유형코드 */
    String pdGrpCd; /* 상품그룹코드 */
    String pdctPdCd; /* 제품코드 */
    String svProcsCn; /*서비스처리내용*/
    String wkCanMoCn; /*작업취소메모내용*/

    /* 사용제품 내역등록 ,수불처리 */
    String pdGdCd; /*상품등급코드*/
    int useQty; /*수량*/
    String wkWareNo; /*작업창고번호*/
    String pdCd; // 상품코드
    String svBizHclsfCd; /*서비스업무대분류코드 */
    String svBizDclsfCd; /* 서비스업무세분류코드 */
    String asRefriDvCd;// AS유무상구분코드
    String bfsvcRefriDvCd; /*BS유무상구분코드*/
    String sellTpCd;/* 판매유형코드 */
    String pdUswyCd; /* 최초상품용도코드*/
    String istDt; /* 설치일 */
    String wareMngtPrtnrNo; /*창고관리파트너번호*/
    String itmOstrNo; /*품목출고번호*/

    /* 엔지니어 정보 조회 */
    String mngrDvCd; /* 관리자구분코드 */
    String dgr1LevlOgId; /* 총괄단조직ID*/
    String dgr3LevlOgId; /* 지점 */
    String brchOgId; /* 지점조직ID */

    /* 물류 인터페이스 필수 */
    String rcgvpKnm; /* 설치자명 */
    String cralLocaraTno; // 핸드폰1
    String mexnoEncr; // 핸드폰2
    String cralIdvTno; // 핸드폰3
    String locaraTno; // 전화번호1
    String exnoEncr; // 전화번호2
    String idvTno; // 전화번호3
    String newAdrZip; // 우편번호
    String rnadr; // 기본 주소
    String rdadr; // 상세 주소
    String cntrCstNo; // 계약자 고객번호
    String wareMngtPrtnrOgTpCd;
    int mpacSn; // 합포장 일련번호
    String lgstWkMthdCd; // 물류작업방식코드
    String lgstOstrAkNo; // 물류요청번호
    String wlcmBfsvcYn; //웰컴BS 생성여부
    List<WsnaPcsvOutOfStorageSaveProductDvo> products;  //상품 목록
}
