package com.kyowon.sms.wells.web.service.allocate.dto;

import io.swagger.annotations.ApiModel;

/**
 *
 * <pre>
 * K-W-SV-U-0021M01 엔지니어 배정현황
 * </pre>
 *
 * @author 37758 이재훈
 * @since 2023.09.06
 */
public class WsncAsEngineerAllocateSearchDto {

    @ApiModel(value = "WsncAsEngineerAllocateSearchDto-SearchReq")
    public record SearchReq(
        String ogId,
        String engId,
        String rgsnYn,
        String svDvCd,
        String prgsDvCd,
        String baseDateFrom,
        String baseDateTo,
        //        String vstCfrmDt,
        //        String cfrmOnlyYn,
        String sellTpCd,
        String istConfCd,
        String inquiryBase
    ) {}

    @ApiModel(value = "WsncAsEngineerAllocateSearchDto-SearchRes")
    public record SearchRes(
        String cntrNo,
        String cntrSn,
        String sellTp,
        String istllKnm,
        String cstGd,
        String svTp,
        String locaraTno,
        String exnoEncr,
        String idvTno,
        String cralLocaraTno,
        String mexnoEncr,
        String cralIdvTno,
        String cntrDt,
        String cntrCnfmAprDtm,
        String sapMatCd,
        String pdctPdCd,
        String pdNm,
        String fgptNm,
        String alncmpCd,
        String pdUswyCd,
        String newAdrZip,
        String adrDtl,
        String cnslMoCn,
        String svBizDclsf,
        String asRefriDvCd,
        String bsRefriDvCd,
        String istDt,
        String expireDt,
        String egerAsnDt,
        String vstDt,
        String dtChange,
        String cfrmDt,
        String dtmChRsonCd,
        String dtmChRsonDtlCn,
        String prtnrNm,
        String wkPrgsStat,

        String cstSvAsnNo,
        String prtnrNo,
        String svHshdNo,
        String svHshdNoCnt,
        String svBizHclsfCd,
        String svBizDclsfCd,
        String wkPrgsStatCd
    ) {}
}
