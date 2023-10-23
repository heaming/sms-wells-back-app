package com.kyowon.sms.wells.web.service.stock.dvo;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * W-SV-U-0001M01 반품출고 등록
 * </pre>
 *
 * @author hyewon.kim
 * @since 2023.02.14
 */
@Setter
@Getter
public class WsnaReturningGoodsDvo {

    String itmOstrNo; // 품목출고번호
    String ostrSn; // 출고일련번호
    String itmKndCd; // 품목종류코드
    String sapMatCd; // SAP자재코드
    String itmPdCd; // 품목상품코드
    String itmPdNm; // 품목명(한글)
    String itmGdCd; // 품목등급코드
    BigDecimal onQty; // 재고수량
    String mngtUnitCd; // 관리단위코드
    String ostrRsonCd; // 출고사유코드
    BigDecimal ostrQty; // 출고수량
    String rmkCn; // 비고내용
    String strConfDt; // 입고확인일자
    String ostrTpCd; // 출고유형코드
    String ostrWareNo; // 출고창고번호
    String ostrDt; // 출고일자
    String wareDvCd; // 출고창고구분코드
    String wareMngtPrtnrNo; // 창고관리파트너번호
    String acbDt; // 회계일자
    String evidDvCd; // 증빙구분코드
    String strTpCd; // 입고유형코드
    String strWareDvCd; // 입고창고구분코드
    String strWareNo; // 입고창고번호
    String itmStrNo; // 품목입고번호
    String strSn; // 일련번호
    String strWareMngtPrtnrNo; // 입고창고관리파트너번호
    String trnspnCd; //운송코드

    //물류에 전송하기 위한 데이터 dvo
    private String ostrAkNo; /* 출고요청번호 */
    private String ostrAkSn; /* 출고요청순번 */
    private String ostrAkTpCd; /* 출고요청유형코드 */
    private String ostrAkRgstDt; /* 출고요청등록일자 */
    private String strHopDt; /* 입고희망일자 */
    private String lgstStrTpCd; /* 물류입고유형코드 */
    private String iostAkDvCd; /* 입출고요청구분코드 */
    private String wareMngtPrtnrOgTpCd; /* 창고관리파트너조직유형코드 */
    private String sapIostTpCd; /* SAP입출고유형코드 */
    private String lgstSppMthdCd; /* 물류배송방식코드 */
    private String ostrAkQty; /* 출고요청수량 */
    private String ostrOjWareNo; /* 출고대상창고번호 */
    private String svCnrCd; /* 서비스센터코드 */
    private String svCnrNm; /* 서비스센터명 */
}
