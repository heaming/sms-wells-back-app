package com.kyowon.sms.wells.web.service.stock.dvo;

import com.sds.sflex.system.config.annotation.DBDecField;
import com.sds.sflex.system.config.annotation.DBEncField;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WsnaShippingMaterialDvo {
    String ostrAkNo; // 출고요청번호
    int ostrAkSn; // 출고요청일련번호
    String sppDvCd;
    String asnOjYm; // 배정년월
    String cntrNo; // 계약번호
    String cntrSn; // 계약일련번호
    String cstSvAsnNo; // 고객서비스배정번호
    String cstNo; // 계약고객번호
    String cstKnm; // 고객명
    String zip; // 배송우편번호
    String basAdr; // 배송기본주소
    String dtlAdr; // 상세주소
    String sppAkSpfDt; // 특정일자배송
    @DBEncField
    @DBDecField
    String tno;
    @DBEncField
    @DBDecField
    String mpno;
    //자재정보
    String itmPdCd;
    String partNm;
    int ostrAkQty;
    String pdCn;
    String svBizDclsfCd; // 작업구분코드
    String pdctPdCd; //기준상품코드
    String sellTpCd; //판매코드
    String istDt; //설치일자
    String ogCd; // 배송창고(번호)

    // 물류센터 요청용 세팅해주는 값
    String ostrAkTpCd; // 출고요청유형코드
    String ostrAkRgstDt; //출고요청일자
    String iostAkDvCd; // 입출고요청구분코드
    int mpacSn; // 합포장일련번호
    String lgstSppMthdCd; // 물류배송방식코드
    String lgstWkMthdCd; // 물류작업방식코드
    @DBEncField
    @DBDecField
    String adrsTnoVal; // 수취인전화번호
    @DBEncField
    @DBDecField
    String adrsCphonNoVal; // 수취인휴대폰번호값
    String wareMngtPrtnrNo;
    String wareMngtPrtnrOgTpCd;
    String ostrOjWareNo;
}
