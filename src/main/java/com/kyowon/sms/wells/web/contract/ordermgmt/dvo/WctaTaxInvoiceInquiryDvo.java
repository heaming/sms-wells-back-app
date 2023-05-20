package com.kyowon.sms.wells.web.contract.ordermgmt.dvo;

import com.sds.sflex.system.config.annotation.DBDecField;
import com.sds.sflex.system.config.annotation.DBEncField;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WctaTaxInvoiceInquiryDvo {
    private String txinvPblOjYn; /* 세금계산서발행여부 */
    private String bzrno; /* 사업자등록번호 */
    private String dlpnrNm; /* 거래처명 */
    private String dlpnrPsicNm; /* 거래처담당자명 */
    private String cralLocaraTno; /* 휴대지역전화번호 */

    @DBDecField
    private String mexno; /* 휴대전화국번호 */
    private String cralIdvTno; /* 휴대개별전화번호 */
    private String emadr; /* 이메일 주소 */
    private String emadr1; /* @앞 메일주소 */
    private String emadr2; /* @뒤 메일주소 */
    private String txinvPblD; /* 세금계산서발행일 */
    private String cntrCnfmDtm; /* 계약확정일시 */
    private String dlpnrCd; /* 거래처 코드 */
    private String dpTpCd; /* 입금유형코드 */

    /* 세금계산서접수기준내역 */
    private String cntrNo; /* 계약번호 */
    private int cntrSn; /* 계약일련번호 */
    private String txinvPdDvCd; /* 세금계산서상품구분코드 */
    private String txinvPblDvCd; /* 세금계산서발행구분코드 */
    private String aplcPsicId; /* 신청담당자ID */
    private String rcpdt; /* 접수일자 */
    private String cntrCstNo; /* 계약고객번호 */
    private String txinvPblYn; /* 세금계산서발행여부 */
    private Integer txinvBndlSn; /* 세금계산서묶음일련번호 */
    private String locaraTno; /* 지역전화번호 */
    @DBEncField
    private String exnoEncr; /* 전화국번호암호화 */
    private String idvTno; /* 개별전화번호 */
    @DBDecField
    @DBEncField
    private String mexnoEncr; /* 휴대전화국번호암호화 */
    private String rmkCn; /* 비고내용 */
    private String mvsDstcRcvryBaseDtm; /* 소산파기복구기준일시 */
    private String mvsDstcRcvryDvCd; /* 소산파기복구구분코드 */
    private String mvsDstcRcvryDtm; /* 소산파기복구일시 */
    private String dtaDlYn; /* 데이터삭제여부 */

}
