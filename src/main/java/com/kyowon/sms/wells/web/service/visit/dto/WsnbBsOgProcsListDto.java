package com.kyowon.sms.wells.web.service.visit.dto;

import io.swagger.annotations.ApiModel;

/**
 *
 * <pre>
 * K-W-SV-U-0238M01 B/S 처리 현황(조직)
 * </pre>
 *
 * @author 37758 이재훈
 * @since 2023.08.02
 */
public class WsnbBsOgProcsListDto {

    @ApiModel(value = "WsnbBsOgProcsListDto-SearchReq")
    public record SearchReq(
        String baseYm,
        String mngrDvCd,
        String ogId,
        String pdGrpCd
    ) {}

    @ApiModel(value = "WsnbAsRevisitRateListDto-SearchRes")
    public record SearchRes(
        String ogNm,
        String gb,
        int asgnCrdovrYCnt,
        int asgnCrdovrNCnt,
        int asgnTotalCnt,
        int compCrdovrYCnt,
        int compCrdovrNCnt,
        int compTotalCnt,
        String compRate,
        int comp01,
        int comp02,
        int comp03,
        int comp04,
        int comp05,
        int comp06,
        int comp07,
        int comp08,
        int comp09,
        int comp10,
        int comp11,
        int comp12,
        int comp13,
        int comp14,
        int comp15,
        int comp16,
        int comp17,
        int comp18,
        int comp19,
        int comp20,
        int comp21,
        int comp22,
        int comp23,
        int comp24,
        int comp25,
        int comp26,
        int comp27,
        int comp28,
        int comp29,
        int comp30,
        int comp31
    ) {}

    @ApiModel(value = "WsnbAsRevisitRateListDto-SearchCrdOvrRes")
    public record SearchCrdOvrRes(
        String ogNm,
        String gb,
        String vstPrgsStat,
        int crdovrTotal,
        int crdovr01,
        int crdovr02,
        int crdovr03,
        int crdovr04,
        int crdovr05,
        int crdovr06,
        int crdovr07,
        int crdovr08,
        int crdovr09,
        int crdovr10,
        int crdovr11,
        int crdovr12,
        int crdovr13
    ) {}
}
