package com.kyowon.sms.wells.web.service.stock.dvo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WsnaItemBaseInformationSearchDvo {
    private String itmKndCd; /* 품목구분코드 */
    private String itmPdCd; /* 품목상품코드 */
    private String itmPdNm; /* 품목상품명 */
    private String wareNo; /* 창고번호 */
    private String ostrWareNo; /* 출고창고번호 */
    private String ostrWareDvCd; /* 출고창고구분코드 */
    private String wareDvCd; /* 창고구분코드 */
    private String wareDtlDvCd; /* 창고상세구분코드 */
    private String strtSapCd; /* 시작SAP코드 */
    private String endSapCd; /* 종료SAP코드 */
    private String itmGrpCd; /* 품목그룹코드 */
    private String svMatGrpCd; /* 자재그룹코드 */
    // OFFSET
    private Integer offSet; /* OFFSET */
    // FETCH SIZE
    private Integer size; /* SIZE */
}
