package com.kyowon.sms.wells.web.service.allocate.dto;

import java.util.Base64;

import javax.validation.constraints.NotBlank;

import org.apache.ibatis.annotations.AutomapConstructor;

import io.swagger.annotations.ApiModel;

public class WsncQuickResponseRpblDto {
    // *********************************************************
    // Request Dto
    // *********************************************************

    @ApiModel(value = "WsncQuickResponseRpblDto-SearchReq")
    public record SearchReq(
        // Case1
        @NotBlank
        String baseYm, // 기준년월
        String mngrDvCd, // 관리구분
        String pdPrpVal20, // 상품그룹
        String cntrNo, // 계약번호
        String prtnrNo, // 사번
        // Case2
        String mngtDptmtCd, // 총괄단
        String rgnlGrpCd, // 지역단
        String branchCd, // 지점
        String mngrCd, // 매니저
        // Case3
        String svcCntrCd, // 서비스센터
        String engineerCd // 엔지니어
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    @ApiModel(value = "WsncQuickResponseRpblDto-SearchRes")
    public record SearchRes(
        String mngrDvCd,
        String ogNm,
        String prtnrNo,
        String prtnrKnm,
        String pdctPdCd,
        String pdNm,
        String cntrNo,
        String cntrSn,
        String rcgvpKnm,
        String newAdrZip,
        String rnadr,
        String rdadr,
        String vstYm,
        String bcNo,
        String fnlMdfcDtm,
        String vstFshDt,
        String vstFshHh,
        String bcInMthdCd,
        String useMpno,
        String cstSignCn,
        String dnldPrtnrKnm,
        String publishInfo,
        String rnk
    ) {
        @AutomapConstructor
        public SearchRes(
            String mngrDvCd,
            String ogNm,
            String prtnrNo,
            String prtnrKnm,
            String pdctPdCd,
            String pdNm,
            String cntrNo,
            String cntrSn,
            String rcgvpKnm,
            String newAdrZip,
            String rnadr,
            String rdadr,
            String vstYm,
            String bcNo,
            String fnlMdfcDtm,
            String vstFshDt,
            String vstFshHh,
            String bcInMthdCd,
            String useMpno,
            byte[] cstSignCn,
            String dnldPrtnrKnm,
            String publishInfo,
            String rnk
        ) {
            this(
                mngrDvCd,
                ogNm,
                prtnrNo,
                prtnrKnm,
                pdctPdCd,
                pdNm,
                cntrNo,
                cntrSn,
                rcgvpKnm,
                newAdrZip,
                rnadr,
                rdadr,
                vstYm,
                bcNo,
                fnlMdfcDtm,
                vstFshDt,
                vstFshHh,
                bcInMthdCd,
                useMpno,
                cstSignCn==null?null:Base64.getEncoder().encodeToString(cstSignCn),
                dnldPrtnrKnm,
                publishInfo,
                rnk
            );
        }
    }
}
