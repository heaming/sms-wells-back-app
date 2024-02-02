package com.kyowon.sms.wells.web.service.stock.dvo;

import com.sds.sflex.system.config.annotation.DBEncField;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WsnaPcsvSendDtlDvo {
    /* 물류센터 요청용 셋팅 */
    int ostrAkSn; // 출고요청일련번호
    String ostrAkNo; // 출고요청번호
    String ostrAkTpCd; // 출고요청유형코드
    String ostrAkRgstDt; //출고요청일자
    String ostrHopDt; // 출고희망일자
    String asnOjYm; // 배정년월
    String sppDvCd; // 택배출고상태구분코드
    String iostAkDvCd; // 입출고요청구분코드
    String lgstSppMthdCd; // 물류배송방식코드
    String lgstWkMthdCd; // 물류작업방식코드
    String lgstOstrAkNo; // 물류요청번호
    String ostrOjWareNo; // 출고 창고 번호
    String mdlvPrtnrNo; // 배달 파트너번호
    String pcsvOstrAkDvCd; // 택배출고상태구분코드
    int mpacSn; // 합포장일련번호

    /* 파라미터 : 상품정보  */
    String itmPdCd; // 상품코드
    int ostrAkQty; // 출고요청 수량
    String pdCn;

    /* 파라미터 : 고객정보  */
    String cntrNo; // 계약번호
    String cntrSn; // 계약일련번호
    String cstSvAsnNo; // 고객서비스배정번호
    String cstNo; // 계약고객번호
    String cstNm; // 고객명
    String zip; // 배송우편번호
    String basAdr; // 배송기본주소
    String dtlAdr; // 상세주소

    @DBEncField
    String adrsTnoVal; // 수취인전화번호

    @DBEncField
    String adrsCphonNoVal; // 수취인휴대폰번호값
    String wareMngtPrtnrNo; /*창고관리파트너번호*/
    String wareMngtPrtnrOgTpCd; /* 배송창고(파트너조직유형코드) */
    String itmGdCd; // 상품 등급코드

    /* 물류 인터페이스 NULl 대신 X로 요청 */
    String svCnrCd; /* 서비스센터코드 */
    String svCnrNm; /* 서비스센터명 */
    String svCnrIchrPrtnrNm; /* 서비스센터담당파트너명 */
    String svCnrLkTnoEncr; /* 서비스센터전화번호암호화 */
    String svCnrAdr; /* 서비스센터주소 */
    String ovivTpCd; /* 배차유형코드 */
}
