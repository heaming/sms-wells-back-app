package com.kyowon.sms.wells.web.service.interfaces.dto;

import javax.validation.constraints.NotBlank;

import com.sds.sflex.common.utils.DbEncUtil;

import io.swagger.annotations.ApiModel;

public class WsniCustomerCenterInterfaceDto {
    @ApiModel(value = "WsniCustomerCenterInterfaceDto-SearchReq")
    public record SearchReq(
        String asAkId,
        String cstSvAsnNo,
        String wkPrtnrNo,
        String wkDt,
        String cntrNo,
        String cntrSn
    ) {}

    @ApiModel(value = "WsniCustomerCenterInterfaceDto-SearchContactRes")
    public record SearchContactRes(
        String asAkId,
        String sysDvCd,
        String cstSvAsnNo,
        String cntcDt,
        String cntcHh,
        String absncRsonCd,
        String ogTpCd,
        String ogTpCdNm,
        String prtnrNo,
        String prtnrKnm
    ) {}

    @ApiModel(value = "WsniCustomerCenterInterfaceDto-SearchPromChRes")
    public record SearchPromChRes(
        String asAkId,
        String sysDvCd,
        String svBizHclsfCd,
        String svBizHclsfCdNm,
        String svBizDclsfCd,
        String svBizDclsfCdNm,
        String asIstOjNo,
        String cntrNo,
        String cntrSn,
        String sellTpCd,
        String sellTpCdNm,
        String fstRgstUsrId,
        String fstRgstUsrNm,
        String mtrStatCd,
        String mtrStatCdNm,
        String wkExcnDt,
        String wkExcnHh,
        String wkPrgsStatCd,
        String wkPrgsStatCdNm,
        String wkCanRsonCd,
        String wkCanMoCn,
        String urgtYn,
        String vstRqdt,
        String vstAkHh,
        String cnslTpHclsfCd,
        String cnslTpHclsfCdNm,
        String cnslTpMclsfCd,
        String cnslTpMclsfCdNm,
        String cnslTpLclsfCd,
        String cnslTpLclsfCdNm,
        String cnslDtlpTpCd,
        String smsFwYn,
        String dpDvCd,
        String dpDvCdNm,
        String svEtAmt,
        String svCnrOgId,
        String svCnrOgNm,
        String wkPrtnrNo,
        String wkPrtnrKnm
    ) {}

    @ApiModel(value = "WsniCustomerCenterInterfaceDto-SearchCancelRes")
    public record SearchCancelRes(
        String asAkId,
        String sysDvCd,
        String svBizHclsfCd,
        String svBizHclsfCdNm,
        String svBizDclsfCd,
        String svBizDclsfCdNm,
        String asIstOjNo,
        String cntrNo,
        String cntrSn,
        String sellTpCd,
        String sellTpCdNm,
        String fstRgstUsrId,
        String fstRgstUsrNm,
        String mtrStatCd,
        String mtrStatCdNm,
        String wkExcnDt,
        String wkExcnHh,
        String wkPrgsStatCd,
        String wkPrgsStatCdNm,
        String wkCanRsonCd,
        String wkCanMoCn,
        String urgtYn,
        String vstRqdt,
        String vstAkHh,
        String cnslTpHclsfCd,
        String cnslTpHclsfCdNm,
        String cnslTpMclsfCd,
        String cnslTpMclsfCdNm,
        String cnslTpLclsfCd,
        String cnslTpLclsfCdNm,
        String cnslDtlpTpCd,
        String smsFwYn,
        String dpDvCd,
        String dpDvCdNm,
        String svEtAmt,
        String svCnrOgId,
        String svCnrOgNm,
        String wkPrtnrNo,
        String wkPrtnrKnm
    ) {}

    @ApiModel(value = "WsniCustomerCenterInterfaceDto-SearchSppPdctRes")
    public record SearchSppPdctRes(
        String cntrNo,
        String cntrSn,
        String svBizHclsfCd,
        String svBizHclsfNm,
        String sppOrdNo,
        String sppSn,
        String sdingPdCd1,
        String sdingPdNm1,
        String sdingQty1,
        String sdingPdCd2,
        String sdingPdNm2,
        String sdingQty2,
        String sdingPdCd3,
        String sdingPdNm3,
        String sdingQty3,
        String sdingPdCd4,
        String sdingPdNm4,
        String sdingQty4,
        String sdingPdCd5,
        String sdingPdNm5,
        String sdingQty5,
        String vstDt
    ) {}

    @ApiModel(value = "WsniCustomerCenterInterfaceDto-SearchSppVstRes")
    public record SearchSppVstRes(
        String cntrNo,
        String cntrSn,
        String vstDuedt,
        String chVstClDt,
        String stpDuedt,
        String stpClDt,
        String lastStpDt,
        String gdcCd,
        String gdcNm,
        String saleCd
    ) {}

    @ApiModel(value = "WsniCustomerCenterInterfaceDto-SearchAsRes")
    public record SearchAsRes(
        String cntrNo,
        String wkGrpCd1,
        String wkGrpNm1,
        String wkGrpCd2,
        String wkGrpNm2,
        String wkGrpCd3,
        String wkGrpNm3
    ) {}

    @ApiModel(value = "WsniCustomerCenterInterfaceDto-CreateShpadrReq")
    public record CreateShpadrReq(
        @NotBlank
        String cntrNo,
        @NotBlank
        String cntrSn,
        String sppTcnt,
        String cralLocaraTno,
        String mexnoEncr,
        String cralIdvTno,
        String locaraTno,
        String exnoEncr,
        String idvTno,
        String sppZip,
        String sppBasAdr,
        String sppDtlAdr,
        String refAdr,
        String adrDvCd,
        String sppDptuDt,
        String sppFshDt,
        String useYn
    ) {
        public CreateShpadrReq {
            mexnoEncr = DbEncUtil.enc(mexnoEncr);
            exnoEncr = DbEncUtil.enc(exnoEncr);
        }
    }

    @ApiModel(value = "WsniCustomerCenterInterfaceDto-CreateShpadrRes")
    public record CreateShpadrRes(
        String msg,
        String result
    ) {}

    @ApiModel(value = "WsniCustomerCenterInterfaceDto-EditShpadrReq")
    public record EditShpadrReq(
        @NotBlank
        String cntrNo,
        @NotBlank
        String cntrSn,
        String sppFshDt
    ) {}

    @ApiModel(value = "WsniCustomerCenterInterfaceDto-EditShpadrRes")
    public record EditShpadrRes(
        String msg,
        String result
    ) {}
}
