package com.kyowon.sms.wells.web.contract.ordermgmt.dto;

import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.*;
import com.sds.sflex.system.config.validation.validator.ValidDate;
import io.swagger.annotations.ApiModel;
import lombok.Builder;

import javax.validation.constraints.NotBlank;
import java.util.List;

public class WctaContractSettelmentDto {

    @Builder
    @ApiModel(
        value = "EctaContractSettelmentDto-SearchBasicInfoReq",
        description = "고객 확인을 위한 기본 계약 정보를 제공하기 위한 검색 객체"
    )
    public record SearchBasicInfoReq(
        @NotBlank
        String cntrNo
    ) {
//        public SearchBasicInfoReq {
//            cntrNo = DbEncUtil.dec(cntrNo);
//        }
    }

    @Builder
    @ApiModel(
        value = "EctaContractSettelmentDto-FindBasicInfoRes",
        description = "고객 확인을 위한 기본 계약 정보 응답 객체 "
    )
    public record FindBasicInfoRes(
        String cntrCstKnm,
        String copnDvCd
    ) {
        /*public FindBasicInfoRes {
            cntrCstKnm = DbEncUtil.enc(cntrCstKnm);
            lrnnCstKnm = DbEncUtil.enc(lrnnCstKnm);
            copnDvCd = DbEncUtil.enc(copnDvCd);
        }*/
    }

    @Builder
    @ApiModel(
        value = "EctaContractSettelmentDto-AuthenticationReq",
        description = "고객 인증 요청 객체"
    )
    public record AuthenticationReq(
        @NotBlank
        String cntrNo,
        @ValidDate
        String cntrCstBryyMmdd,
        String bzrno
    ) {
       /* public AuthenticationReq {
            bryyMmdd = DbEncUtil.dec(cntrNo);
            cntrNo = DbEncUtil.dec(cntrNo);
        }*/
    }

    @Builder
    @ApiModel(
        value = "EctaContractSettelmentDto-Authorization",
        description = "고객 인가 객체"
    )
    public record Authorization(
        boolean valid,
        String key
    ) {
    }

    @Builder
    @ApiModel(
        value="EctaContractSettelmentDto-FindContractForStlmRes",
        description="Find Contract For Stlm Req Dto"
    )
    public record FindContractForStlmRes(
        WctaContractBasDvo base,
        List<WctaAgreeItemDtlDvo> agIzs,
        WctaContractCstRelDvo cntrCstInfo,
        List<WctaContractDtlDvo> dtls,
        List<WctaContractStlmBasDvo> stlms,
        WctaContractPrtnrRelDvo prtnr
    ) {}

    @Builder
    @ApiModel(
        value = "EctaContractSettelmentDto-SaveReq",
        description = "고객 결제 확정 객체"
    )
    public record SaveReq(
        @NotBlank
        String cntrNo,
        List<WctaContractStlmBasDvo> stlmBases, /* 여력이 된다면 DTO 로 바꾸고 validation 도 돌립시다.*/
        List<WctaAgreeItemDtlDvo> agIzs,
        List<WctaContractAdrpcBasDvo> adrpcs
    ) {
       /* public AuthenticationReq {
            bryyMmdd = DbEncUtil.dec(cntrNo);
            cntrNo = DbEncUtil.dec(cntrNo);
        }*/
    }

    @Builder
    @ApiModel(
        value = "EctaContractSettelmentDto-SaveReq",
        description = "고객 결제 확정 결과"
    )
    public record SaveRes(
        boolean result
    ) {
       /* public AuthenticationReq {
            bryyMmdd = DbEncUtil.dec(cntrNo);
            cntrNo = DbEncUtil.dec(cntrNo);
        }*/
    }
}
