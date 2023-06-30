package com.kyowon.sms.wells.web.service.visit.dto;

import com.sds.sflex.common.utils.DbEncUtil;
import io.swagger.annotations.ApiModel;
import org.apache.commons.lang.StringUtils;

import javax.validation.constraints.NotBlank;

public class WsnbWellsManagerIchrExcdDto {
    @ApiModel(value = "WsnbWellsManagerIchrExcdDto-SearchReq")
    public record SearchReq(
        @NotBlank
        String mngtYm,
        String fromAdrZip,
        String toAdrZip,
        String dgr1LevlOgId,
        String dgr2LevlOgId,
        String accountDivCd,
        String exceptWellsFarmYn,
        String exceptFixYn,
        String exceptEgerWkYn
    ) {}

    @ApiModel(value = "WsnbWellsManagerIchrExcdDto-SearchRes")
    public record SearchRes(
        String baseYm,
        String dgr1LevlOgCd,
        String ogCd,
        String ogTpCd,
        String ogTp,
        String cntrNo,
        Integer cntrSn,
        String cntr,
        String rcgvpKnm,
        String cstGdCd,
        String svpdItemGrNm,
        String svpdNmAbbr1,
        String sellTpCd,
        Integer vstNmnN,
        Integer istNmnN,
        String vstDvCd,
        String vstPrgsStatCd,
        String svHshdNo,
        String svHshdNoCnt,
        String newAdrZip,
        String locaraTno,
        String exnoEncr,
        String idvTno,
        String cralLocaraTno,
        String mexnoEncr,
        String cralIdvTno,
        String adr,
        String emd,
        String dgr2LevlOgCd,
        String dgr3LevlOgCd,
        String prtnrKnm,
        String mngerRglvlDvCd,
        String clsfCdSrnPrntCn,
        String egerWk,
        String fix,
        String chRsonCn
    ) {
        public SearchRes {
            mexnoEncr = StringUtils.isNotEmpty(mexnoEncr) ? DbEncUtil.dec(mexnoEncr) : mexnoEncr;
            exnoEncr = StringUtils.isNotEmpty(exnoEncr) ? DbEncUtil.dec(exnoEncr) : exnoEncr;
        }
    }
}
