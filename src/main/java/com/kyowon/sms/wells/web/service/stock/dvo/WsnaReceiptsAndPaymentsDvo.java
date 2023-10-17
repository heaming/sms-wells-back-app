package com.kyowon.sms.wells.web.service.stock.dvo;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WsnaReceiptsAndPaymentsDvo {

    // 입고창고구분
    String strWareDvCd;
    // 입고상위창고
    String strWareNoM;
    // 입고창고
    String strWareNoD;
    // 창고상세구분
    String wareDtlDvCd;
    // 시작일자
    String stFromYmd;
    // 종료일자
    String edToYmd;
    // 품목구분
    String itmKnd;
    // 등급
    String itmGdCd;
    // 사용여부
    String useYn;
    // 품목코드
    String itmPdCdFrom;
    // 시작 SAP코드
    String sapMatCdFrom;
    // 종료 SAP코드
    String sapMatCdTo;
    // SAP코드 리스트
    List<String> sapMatDpcts;
    // OFFSET
    Integer offSet;
    // FETCH SIZE
    Integer size;
}
