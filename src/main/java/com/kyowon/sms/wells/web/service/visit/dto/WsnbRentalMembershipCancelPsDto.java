package com.kyowon.sms.wells.web.service.visit.dto;

import com.sds.sflex.common.utils.DbEncUtil;

import io.swagger.annotations.ApiModel;

public class WsnbRentalMembershipCancelPsDto {

    @ApiModel("WsnbRentalMembershipCancelPsDto-SearchReq")
    public record SearchReq(

        String baseYm,

        String sellTpCd,

        String mngrDvCd,

        String ogId,

        String prtnrNo,

        String dgr1LevlOgId,

        String dgr2LevlOgId,

        String dgr3LevlOgId
    ) {}

    @ApiModel("WsnbRentalMembershipCancelPsDto-SearchRes")
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
