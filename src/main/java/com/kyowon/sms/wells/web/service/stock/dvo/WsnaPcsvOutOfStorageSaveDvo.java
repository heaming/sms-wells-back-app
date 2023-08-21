package com.kyowon.sms.wells.web.service.stock.dvo;

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
    String ivcPrntSn;
    String ogId;
    String ogTpCd;
    String pdGrpCd;
    String svProcsCn; /*서비스처리내용*/
    String wkCanMoCn; /*작업취소메모내용*/

    /* 사용제품 내역등록 ,수불처리 */
    String pdGdCd;
    String useQty;
    String wkWareNo;
    String pdCd;
    String svBizHclsfCd;
    String svBizDclsfCd;
    String asRefriDvCd;
    String bfsvcRefriDvCd;
    String filtSellTpCd;
    String pdSellTpCd;
    String pdUswyCd;
    String istDt;
    String wareMngtPrtnrNo; /*창고관리파트너번호*/
    String itmOstrNo; /*품목출고번호*/

    /* 엔지니어 정보 조회 */
    String mngrDvCd;
    String dgr1LevlOgId;
    String dgr3LevlOgId;
    String brchOgId;

    /*수불 재고 수량 조회 */
    int aQty;
    int bQty;
    int eQty;
    int rQty;

    /* 물류 인터페이스 필수 */
    String rcgvpKnm;

    String cralIdvTno;

    String idvTno;

    String newAdrZip; // 우편번호

    String rnadr; // 기본 주소

    String rdadr; // 상세 주소

    String cntrCstNo; // 계약자 고객번호

    String pdNm; // 상품 명

    String wareMngtPrtnrOgTpCd;

}
