package com.kyowon.sms.wells.web.service.stock.dvo;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WsnaManagerBsConsumableDvo {
    String reqYn; /* 신청여부 */
    String bldCd; /* 빌딩코드 */
    String bldNm; /* 빌딩명 */
    String prtnrNo; /* 파트너번호 */
    String prtnrNmNo; /* 파트너명번호 */
    String vstCstN; /* 방문계정수 */
    String strWareNo; /* 입고창고번호(파트너번호 or 빌딩코드) */
    String csmbPdCd; /* 소모품상품코드 */
    String sapMatCd; /* SAP자재코드 */
    String bfsvcCsmbDdlvTpCd; /* BS소모품배부유형코드 */
    String bfsvcCsmbDdlvQty; /* BS소모품배부수량 */
    String fxnDdlvUnitQty; /* 고정배부단위수량 */
    String aplcDdlvUnitQty; /* 신청배부단위수량 */
    String prtnrKnm; /* 파트너한글명 */
    String ogCd; /* 조직코드 */
    String wrfr; /* 정수기  */
    String bdtIndv; /* 비데(개인)  */
    String bdtCrp; /* 비데(법인)  */
    String arcleIndv; /* 공기청정기(개인)  */
    String arcleCrp; /* 공기청정기(법인)  */
    String wtrSftnr; /* 연수기  */
    String cffMchn; /* 커피머신  */
    String msgcr; /* 안마의자  */
    String dryr; /* 건조기  */
    String wash; /* 세탁기  */
    String ardrssr; /* 에어드레서  */
    String sscling; /* 삼성청소기  */
    List<String> fxnQtys; /* 고정수량 리스트 */
    List<String> aplcQtys; /* 신청수량 리스트 */
    String mngtYm; /* 관리년월 */
    String bizStrtdt; /* 업무시작일자 */
    String bizStrtHh; /* 업무시작시간 */
    String bizEnddt; /* 업무종료일자 */
    String bizEndHh; /* 업무종료시간 */
    String bfsvcCsmbDdlvOjCd; /* BS소모품배부대상코드 */
    String bfsvcCsmbDdlvStatCd; /* BS소모품배부상태코드 */
    String ostrAkNo; /* 출고요청번호 */
    int ostrAkSn; /* 출고요청일련번호 */
    String ogTpCd; /* 조직유형코드 */
    String fxnPdCd; /* 고정품목코드 */
    String aplcPdCd; /* 신청품목코드 */

    String fxnPckngUnit; /* 고정포장단위 */
    String fxnPdNm; /* 고정품목명 */
    String fxnSapMatCd; /* 고정SAP코드 */
    String aplcPckngUnit; /* 신청포장단위 */
    String aplcPdNm; /* 신청품목명 */
    String aplcSapMatCd; /* 신청SAP코드 */
    String qty; /* 수량 */

    List<String> bldCds;
    // PIVOT 조건
    private String pivotInStr;
    // PIVOT 컬럼
    private String pivotColumns;
}
