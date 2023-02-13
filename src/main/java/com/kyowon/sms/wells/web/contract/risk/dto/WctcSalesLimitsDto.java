package com.kyowon.sms.wells.web.contract.risk.dto;

import javax.validation.constraints.NotBlank;

import com.kyowon.sms.wells.web.contract.zcommon.constants.CtContractConst;
import com.sds.sflex.common.utils.DbEncUtil;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

public class WctcSalesLimitsDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    // 접수제한 관리-블랙리스트 Search Request Dto
    @Builder
    @ApiModel("WctcSalesLimitsDto-SearchBlacklistReq")
    public record SearchBlacklistReq(
        String cntrCstNo,
        String cntrNo,
        String cstKnm,
        String adr,
        String tno,
        String selrInf
    ) {}

    // 접수제한 관리-블랙리스트 Save Request Dto
    @Builder
    @ApiModel("WctcSalesLimitsDto-SaveBlacklistReq")
    public record SaveBlacklistReq(
        @NotBlank
        String rowState,
        String sellLmId,
        String sellLmCntrNo,
        int sellLmCntrSn,
        String sellLmRsonCn,
        String dtaDlYn,
        String orglDtaDlYn,
        String fnlMdfcDtm
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 접수제한 관리-블랙리스트 Search Result Dto
    @ApiModel("WctcSalesLimitsDto-SearchBlacklistRes")
    public record SearchBlacklistRes(
        String sellLmId,
        String dtaDlYn,
        String orglDtaDlYn,
        String sellTpCd,
        String cntrCstNo,
        String sellLmRsonCn,
        String sellLmCntrNo,
        int sellLmCntrSn,
        String cstKnm,
        String copnDvCd,
        String bryyMmdd,
        String bzrno,
        String cntrCralLocaraTno,
        String cntrMexnoEncr,
        String cntrCralIdvTno,
        String cntrMpno,
        String cntrLocaraTno,
        String cntrExnoEncr,
        String cntrIdvTno,
        String cntrTno,
        String cntrZip,
        String cntrAdr,
        String istllKnm,
        String istllCralLocaraTno,
        String istllMexnoEncr,
        String istllCralIdvTno,
        String istllMpno,
        String istllLocaraTno,
        String istllExnoEncr,
        String istllIdvTno,
        String istllTno,
        String istllZip,
        String istllAdr,
        String ogNm,
        String prtnrKnm,
        String prtnrNo,
        String prtnrCralLocaraTno,
        String prtnrMexnoEncr,
        String prtnrCralIdvTno,
        String prtnrMpno,
        String fstRgstDtm,
        String fstRgstUsrId,
        String fnlMdfcDtm,
        String fnlMdfcUsrId
    ) {
        public SearchBlacklistRes {
            cntrMpno = cntrCralLocaraTno + CtContractConst.TNO_DELIM + DbEncUtil.dec(cntrMexnoEncr)
                + CtContractConst.TNO_DELIM + cntrCralIdvTno;
            cntrTno = cntrLocaraTno + CtContractConst.TNO_DELIM + DbEncUtil.dec(cntrExnoEncr)
                + CtContractConst.TNO_DELIM + cntrIdvTno;
            istllMpno = istllCralLocaraTno + CtContractConst.TNO_DELIM + DbEncUtil.dec(istllMexnoEncr)
                + CtContractConst.TNO_DELIM + istllCralIdvTno;
            istllTno = istllLocaraTno + CtContractConst.TNO_DELIM + DbEncUtil.dec(istllExnoEncr)
                + CtContractConst.TNO_DELIM + istllIdvTno;
            prtnrMpno = prtnrCralLocaraTno + CtContractConst.TNO_DELIM + DbEncUtil.dec(prtnrMexnoEncr)
                + CtContractConst.TNO_DELIM + prtnrCralIdvTno;
        }
    }
}
