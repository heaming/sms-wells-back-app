package com.kyowon.sms.wells.web.service.stock.dvo;

import com.sds.sflex.system.config.annotation.DBDecField;
import com.sds.sflex.system.config.annotation.DBEncField;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WsnaShippingManagementDvo {
    String asnOjYm; // 배정년월
    String cntrNo; // 계약번호
    String cntrSn; // 계약일련번호
    String cstSvAsnNo; // 고객서비스배정번호
    String cstKnm; // 고객명
    String zip; // 배송우편번호
    String basAdr; // 배송기본주소
    String dtlAdr; // 상세주소
    String cralLocaraTno;
    @DBEncField
    @DBDecField
    String mexnoEncr;
    String cralIdvTno;
    String locaraTno;
    @DBEncField
    @DBDecField
    String exnoEncr;
    String idvTno;
    String sellTpCd; //판매코드
    String sellTpNm; // 판매유형
    String istDt; //설치일자
    String reqdDt; //철거일자
    String cpsDt; //보상일자 (취소일자?)
    String thReqdDt;
    String pdctPdCd; //기준상품코드
    String basePdCd;
    String pdNm; //상품명
    String svBizDclsfCd; // 작업구분코드
    String svBizDclsfNm; // 작업구분명
    String istNmnN; //설치차월
    String vstNmnN; //방문차월
    String pVstPrgsStatCd;
    String pVstPrgsStatNm;
    String vstDuedt; //방문예정일자
    String wkExcnDt; // 작업수행일자(작업완료일자)
    String vstOjLocaraCd; // 방문대상지역코드
    String vstOjLocaraNm; // 방문대상지역명
    String wareNo; // 배송창고(창고번호)
    String ogCd; // 배송창고(번호)
    String cnfmPsicPrtnrNo;
    String siteAwPdGrpCd;
    String asLctCd; // AS위치코드
    String asLctNm; // AS위치명
    String asPhnCd; // AS현상코드
    String asPhnNm; // AS현상명
    String asCausCd; // AS원인코드
    String asCausNm; // AS원인명
    int partCntTotal;
    int partSumTotal;
    //자재정보
    String partCd01;
    String partCd02;
    String partCd03;
    String partCd04;
    String partCd05;
    String partCd06;
    String partCd07;
    String partCd08;
    String partCd09;
    String partCd10;
    String partNm01;
    String partNm02;
    String partNm03;
    String partNm04;
    String partNm05;
    String partNm06;
    String partNm07;
    String partNm08;
    String partNm09;
    String partNm10;
    int partQty01;
    int partQty02;
    int partQty03;
    int partQty04;
    int partQty05;
    int partQty06;
    int partQty07;
    int partQty08;
    int partQty09;
    int partQty10;
    String frisuRcvryTpCd;
    String sppAkSpfDt; // 특정일자배송
    String bzrno;
    String bfsvcBzsDvCd;
    String sppDvCd;
    String sppPdCd;
    String sppThmYn;
    String cstNo; // 계약고객번호
    String wareIchrNo;
    String wareMngtPrtnrno;
    String vstPrgsStatCd;
    String procsDvCd;
    String sppPdNm;
    String partNmQty01;
    String partNmQty02;
    String partNmQty03;
    String partNmQty04;
    String partNmQty05;
    String partNmQty06;
    String partNmQty07;
    String partNmQty08;
    String partNmQty09;
    String partNmQty10;
    String vstPrgsStatNm;

    String ostrAkNo; // 출고요청번호
    int ostrAkSn; // 출고요청일련번호
    String tno;
    String mpno;

}
