package com.kyowon.sms.wells.web.service.stock.dvo;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WsnaPcsvOutOfStorageSaveDvo {

    /* 작업결과 */
    String cstSvAsnNo;

    String prtnrNo;

    String siteAwSvTpCd;

    String siteAwAtcCd;

    String cntrNo;

    String cntrSn;

    String urgtDvCd;

    String rpbLocaraCd;

    String asLctCd;

    String asPhnCd;

    String asCausCd;

    String ogId;

    String ogTpCd;

    String pdGrpCd; /* 상품그룹코드 */

    String pdctPdCd; /* 제품코드 */

    String svProcsCn; /*서비스처리내용*/

    String wkCanMoCn; /*작업취소메모내용*/

    /* 사용제품 내역등록 ,수불처리 */
    String pdGdCd;

    int useQty;

    String wkWareNo;

    String pdCd;

    String svBizHclsfCd;

    String svBizDclsfCd;

    String asRefriDvCd;

    String bfsvcRefriDvCd;

    String sellTpCd;

    String pdUswyCd;

    String istDt;

    String wareMngtPrtnrNo; /*창고관리파트너번호*/

    String itmOstrNo; /*품목출고번호*/

    /* 엔지니어 정보 조회 */
    String mngrDvCd;

    String dgr1LevlOgId;

    String dgr3LevlOgId;

    String brchOgId;

    /* 물류 인터페이스 필수 */
    String rcgvpKnm;

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

    List<WsnaPcsvOutOfStorageSaveProductDvo> products;
}
