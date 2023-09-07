package com.kyowon.sms.wells.web.service.stock.dvo;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class WsnaOutOfStorageAskMngtSearchDvo {
    String strOjWareNo; // 출고요청창고 = 입고대상창고번호
    String ostrAkTpCd; // 출고요청유형코드
    @NotBlank
    String startStrHopDt; // 입고희망일자 시작일
    @NotBlank
    String endStrHopDt; // 입고희망일자 종료일
    @NotBlank
    String wareDvCd; // 출고요청 접수창고
    String ostrOjWareNo; /*출고대상창고*/
    String ostrAkNo; /*출고요청번호*/
    String ostrWareDvCd; //출고대상창고구분코드

}
