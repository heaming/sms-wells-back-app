package com.kyowon.sms.wells.web.service.visit.dto;

import io.swagger.annotations.ApiModel;

/**
 *
 * <pre>
 * K-W-SV-U-0267M01 모바일 해피콜 현황
 * </pre>
 *
 * @author 37758 이재훈
 * @since 2023.08.17
 */
public class WsnbMobileHappycallSearchDto {

    @ApiModel(value = "WsnbMobileHappycallSearchDto-SearchReq")
    public record SearchReq(
        String svcTpCd, /* 서비스유형 */
        String ogId, /*서비스센터ID */
        String engId, /* 엔지니어ID */
        String inqrBase, /* 조회기준 */
        String baseDateFrom, /* 기준일자 From */
        String baseDateTo, /* 기준일자 To */
        String rgsnYn, /* 퇴사자여부 */
        String acpnWrkInc /* 동행건포함 */
    ) {}

    @ApiModel(value = "WsnbMobileHappycallSearchDto-SearchRes")
    public record SearchRes(
        String cntrNo,
        String cntrSn,
        String cstKnm,
        String sapCd,
        String pdCd,
        String pdNm,
        String cralLocaraTno,
        String mexnoEncr,
        String cralIdvTno,
        String copnDv,
        String ogNm,
        String prtnrKnm,
        String prtnrNo,
        String acpnPrtnrNm,
        String acpnPrtnrNo,
        String svDv,
        String wkGrpCd,
        String svBizDclsfCd,
        String rplyDt,
        String vstFshDt,
        String hpcallStpcN,
        String rslt01,
        String rslt02,
        String rslt03,
        String rslt04,
        String rslt05,
        String rslt06,
        String rslt07,
        String rslt08,
        String rslt09,
        String rslt10,
        String rslt11,
        String rslt12,
        String hpcallHdwrQstCn,
        String sendYn,
        String rplyYn,
        String rdnyYn
    ) {}
}
