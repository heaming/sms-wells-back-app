package com.kyowon.sms.wells.web.service.allocate.dto;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;

public class WsncBsMngtSchdInqrDto {
    // *********************************************************
    // Request Dto
    // 2023.06.06  BS관리일정조회 홍세기 생성
    // *********************************************************
    @ApiModel(value = "WsncBsMngtSchdInqrDto-SearchReq")
    public record SearchReq(
        @NotBlank
        String fxnPrtnrNo,
        @NotBlank
        String baseDateFrom,

        @NotBlank
        String baseDateTo
    ) {}

    @ApiModel(value = "WsncBsMngtSchdInqrDto-SearchRes")
    public record SearchRes(
        String vst_Dt, // 방문일자

        String vst_Tm, // 방문시간

        String cst_Nm, // 고객명

        String cntr_No, // 계약번호

        String pdct_CD, // 제품명

        String goods_Nm, // 제품명

        String mp_No, // 휴대전화번호

        String pu_Part1, // 투입부품1

        String pu_Part2, // 투입부품2

        String pu_Part3, // 투입부품3

        String pu_Part4, // 투입부품4

        String pu_Part5, // 투입부품5

        String pu_Part6, // 투입부품6

        String curnt_Dt, // 최초계약일

        String cntr_Dt, // 계약일자

        String og_Id, // 조직ID

        String prtnr_No, // 파트너사번

        String recntr_Dt, // 재계약일자

        String mngt_Acc, // 관리계정

        String vst_Acc, // 방문계정

        String fsh_Acc, // 완료계정

        String svc_Proc // 서비스처리율

    ) {}
}
