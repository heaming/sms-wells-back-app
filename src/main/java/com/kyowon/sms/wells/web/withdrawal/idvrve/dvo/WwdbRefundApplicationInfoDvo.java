package com.kyowon.sms.wells.web.withdrawal.idvrve.dvo;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WwdbRefundApplicationInfoDvo {
    private WwdbRefundApplicationBasicDvo basic;

    private List<WwdbRefundApplicationDetailDvo> details; // 추가 버튼 누르면 추가로 생성되는 부분

}
