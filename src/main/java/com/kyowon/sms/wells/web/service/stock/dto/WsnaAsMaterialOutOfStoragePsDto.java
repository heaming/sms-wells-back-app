package com.kyowon.sms.wells.web.service.stock.dto;

import com.sds.sflex.common.utils.DbEncUtil;

import io.swagger.annotations.ApiModel;

public class WsnaAsMaterialOutOfStoragePsDto {

    @ApiModel(value = "WsnaAsMaterialOutOfStoragePsDto-SearchReq")
    public record SearchReq(
        String startDt,
        String endDt,

        String serviceType,

        String prtnrNo,

        String refriType,

        String pdGrpCd,

        String ogCd,

        String installBase,

        String itmKndCd,

        String svBizDclsfCd
    ) {}

    @ApiModel(value = "WsnaAsMaterialOutOfStoragePsDto-SearchRes")
    public record SearchRes(
        String sapMatCd,

        String cntrNo,

        String cntrSn,

        String rnadr, //주소

        String rdadr, //주소 상세

        String locaraTno, //지역전화번호 (전화번호)

        String exnoEncr, //전화국번호암호화(전화번호)

        String idvTno //개별전화번호(전화번호)
    ) {
        public SearchRes {
            exnoEncr = DbEncUtil.dec(exnoEncr);
        }
    }
}
