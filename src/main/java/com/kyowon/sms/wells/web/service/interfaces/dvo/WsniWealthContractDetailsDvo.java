package com.kyowon.sms.wells.web.service.interfaces.dvo;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class WsniWealthContractDetailsDvo {

    private String rsltCd = ""; /* 결과코드        */
    private String rsltMsg = ""; /* 결과메세지      */

    private String cntrNo = ""; /*계약번호*/
    private String cntrSn = ""; /*계약일련번호*/
    private String basePdCd = ""; /*상품코드*/
    private String pdNm = ""; /* 상품명  */
    private String sellTpCd = ""; /* 구매유형 1:일시불 = ""; 2:렌탈 = ""; 3:멤버십 = ""; 6:정기배송 */
    private String sellTpDtlCd = ""; /* 구매유형상세 일시불(1: 일반 = ""; 2:홈케어) = ""; 렌탈(1:일반 = ""; 2:금융리스 = ""; 3:장기할부) = ""; 멤버십(1:일반 = ""; 2:홈케어) = ""; 모종(1:일반) */
    private String cntrTam = ""; /* 구매가(총계약금액) */
    private String cntrPdStrtdt = ""; /* 매출일자 */
    private String stplPtrm = ""; /*약정개월수*/
    private String pymtDiv = ""; /*납부구분 1:완납 = "";2:할부 = "";3:월납*/
    private String mnthPymnAmt = ""; /*월납부금액*/
    private String rcgvpKnm = ""; /*설치자명*/
    private String instrCralLocaraTno = ""; /*설치자연락처1*/
    private String instrMexnoEncr = ""; /*설치자연락처2*/
    private String instrCralIdvTno = ""; /*설치자연락처3*/
    private String instrLocaraTno = ""; /*설치자전화1*/
    private String instrExnoEncr = ""; /*설치자전화2*/
    private String instrIdvTno = ""; /*설치자전화3*/
    private String instrNewAdrZip = ""; /*설치/배송우편번호*/
    private String instrRnadr = ""; /*설치배송주소1*/
    private String instrRdadr = ""; /*설치배송주소2*/
    private String instrWad3 = ""; /*설치배송주소3*/
    private String cstKnm = ""; /*계약자명*/
    private String cntrtCralLocaraTno = ""; /*계약자연락처1*/
    private String cntrtMexnoEncr = ""; /*계약자연락처2*/
    private String cntrtCralIdvTno = ""; /*계약자연락처3*/
    private String cntrtNewAdrZip = ""; /*계약자우편번호*/
    private String cntrtRnadr = ""; /*계약자주소1*/
    private String cntrtRdadr = ""; /*계약자주소2*/
    private String cntrtCad3 = ""; /*계약자주소3*/
    private String emadr = ""; /*계약자이메일*/
    private String stat = ""; /*계약상태 1:정상 = "";2:보류 = "";3:환불 = "";4:예보 = "";5:일시정지 = "";6:연체정지 = "";7:해지완료 = "";8:기간만료 = "";9:계약취소 = "";10:연체해약 = "";11:미출해약*/
    private String istDt = ""; /*설치일자*/
    private String useMonth = ""; /*사용개월*/
    private String cntrPdEnddt = ""; /*만기일자*/
    private String prtnrKnm = ""; /*웰스매니저명*/
    private String managerCralLocaraTno = ""; /*웰스매니저연락처1*/
    private String managerMexnoEncr = ""; /*웰스매니저연락처2*/
    private String managerCralIdvTno = ""; /*웰스매니저연락처3*/
    private String damt = ""; /*연체금액*/

}
