package com.kyowon.sms.wells.web.service.stock.dto;

import io.swagger.annotations.ApiModel;

public class WsnaNormalOutOfStorageAgrgDto {
    @ApiModel(value = "WsnaNormalOutOfStorageAgrgDto-SearchReq")
    public record SearchReq(
        String startStrHopDt,     /* 입고희망일자 시작  */
        String endStrHopDt,       /* 입고희망일자 종료  */
        String ostrAkTpCd,        /* 출고요청유형       */
        String ostrOjWareNo,      /* 출고요청접수       */
        String itmKndCd,          /* 출고품목           */
        String wareDvCd,          /* 출고요청창고       */
        String ostrCnfmYn         /* 출고확정 체크      */
    ) {
    }

    @ApiModel(value = "WsnaNormalOutOfStorageAgrgDto-FindWarehouseRes")
    public record FindWarehouseRes(
        String codeId,
        String codeName
    ) {
    }
}
