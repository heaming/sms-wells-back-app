package com.kyowon.sms.wells.web.service.stock.dvo;

import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * W-SV-U-0143M01 기타출고 등록
 * </pre>
 *
 * @author songTaeSung
 * @since 2023-02-06
 */
@Getter
@Setter
public class WsnaEtcOutOfStorageDvo {
    String itmOstrNo; /* 품목출고번호 */
    String ostrTpCd; /*출고유형코드 */
    String ostrWareNo; /* 출고창고번호 */
    String sapMatCd; /* SAP자재코드 */
    String itmPdCd; /* 품목상품코드 */
    String itmKndCd; /* 품목구분코드 */
    String ostrSn; /* 출고순번 */
    String pdAbbrNm; /* 품목약어명 */
    String itmGdCd; /* 품목등급코드 */
    int onQty; /* 수량 */
    String mngtUnitCd; /* 관리단위코드 */
    int ostrQty; /* 출고수량 */
    String ostrRsonCd; /* 출고사유코드 */
    String rmkCn; /* 비고 */
    String wareNm; /* 창고명 */
    String wareMngtPrtnrNo; /* 창고관리파트너번호 */
    String ostrDt; /* 출고일자 */
    String strOjWareNo; /* 입고대상창고번호 */
    String bilDept; /* 청구부서 */
}
