package com.kyowon.sms.wells.web.promotion.manage.dto;

import io.swagger.annotations.ApiModel;
import org.apache.commons.lang.StringUtils;

import javax.validation.constraints.NotBlank;
import java.util.Objects;

public class WpmbPromotionObjectCustomerMgtDto {

    /**
     * SearchReq - 조회조건 DTO
     * @param cntrNo
     * @param cntrSn
     * @param vlStrtDtm
     * @param vlEndDtm
     * @param pmotOjSpcDscDvCd
     */
    @ApiModel("ZpmbPromotionObjectCustomerMgtDto-SearchReq")
    public record SearchReq(
        String cntrNo,                          /* 계약번호 */
        String cntrSn,                          /* 계약일련번호 */
        String vlStrtDtm,                       /* 유효시작일시 */
        String vlEndDtm,                        /* 이력종료일시 */
        String pmotOjSpcDscDvCd                 /* 유효종료일시 */
    ) {
        public SearchReq {
            if (StringUtils.isEmpty(Objects.toString(vlStrtDtm, "")) && !StringUtils.isEmpty(Objects.toString(vlEndDtm, ""))) {
                vlStrtDtm = vlEndDtm;
            } else if (!StringUtils.isEmpty(Objects.toString(vlStrtDtm, "")) && StringUtils.isEmpty(Objects.toString(vlEndDtm, ""))) {
                vlEndDtm = vlStrtDtm;
            }
        }
    }

    /**
     * SearchRes - 조회 결과 DTO
     * @param pmotOjRelId
     * @param histStrtDtm
     * @param histEndDtm
     * @param cntrNo
     * @param cntrSn
     * @param vlStrtDtm
     * @param vlEndDtm
     * @param pmotOjSpcDscDvCd
     * @param pdCd
     * @param sellTpCd
     * @param dtaDlYn
     * @param fstRgstDtm
     * @param fstRgstUsrId
     * @param fstRgstUsrNm
     * @param fnlMdfcDtm
     * @param fnlMdfcUsrId
     * @param fnlMdfcUsrNm
     */
    @ApiModel("ZpmbPromotionObjectCustomerMgtDto-SearchRes")
    public record SearchRes(
        String pmotOjRelId,                     /* 프로모션대상관계ID */
        String histStrtDtm,                     /* 이력시작일시 */
        String histEndDtm,                      /* 이력종료일시 */
        String cntrNo,                          /* 계약번호 */
        String cntrSn,                          /* 계약일련번호 */
        String vlStrtDtm,                       /* 유효시작일시 */
        String vlEndDtm,                        /* 유효종료일시 */
        String pmotOjSpcDscDvCd,                /* 특별할인코드 */
        String pdCd,                            /* 상품코드 */
        String sellTpCd,                        /* 판매유형코드 */
        String dtaDlYn,                         /* 데이터삭제여부 */
        String fstRgstDtm,                      /* 최초등록일 */
        String fstRgstUsrId,                    /* 최초등록 사용자ID */
        String fstRgstUsrNm,                    /* 최초등록 사용자 이름 */
        String fnlMdfcDtm,                      /* 최종수정일 */
        String fnlMdfcUsrId,                    /* 최종수정 사용자ID */
        String fnlMdfcUsrNm                     /* 최종수정 사용자이름 */
    ) {
    }

    /**
     * ContractRes - 조회 결과 DTO
     * @param pmotOjRelId
     * @param cntrNo
     * @param cntrSn
     * @param sellTpCd
     */
    @ApiModel("ZpmbPromotionObjectCustomerMgtDto-ContractRes")
    public record ContractRes(
        String pmotOjRelId,                     /* 프로모션대상관계ID */
        String cntrNo,                          /* 계약번호 */
        String cntrSn,                          /* 계약일련번호 */
        String sellTpCd                         /* 판매유형코드 */
    ) {
    }

    /**
     * SaveReq - 저장 DTO
     * @param pmotOjRelId
     * @param histStrtDtm
     * @param histEndDtm
     * @param cntrNo
     * @param cntrSn
     * @param vlStrtDtm
     * @param vlEndDtm
     * @param pmotOjSpcDscDvCd
     * @param pdCd
     * @param sellTpCd
     * @param dtaDlYn
     * @param fstRgstDtm
     * @param fstRgstUsrId
     * @param fstRgstUsrNm
     * @param fnlMdfcDtm
     * @param fnlMdfcUsrId
     * @param fnlMdfcUsrNm
     */
    @ApiModel("ZpmbPromotionObjectCustomerMgtDto-SaveReq")
    public record SaveReq(
        String pmotOjRelId,                     /* 프로모션대상관계ID */
        String histStrtDtm,                     /* 이력시작일시 */
        String histEndDtm,                      /* 이력종료일시 */
        @NotBlank
        String cntrNo,                          /* 계약번호 */
        @NotBlank
        String cntrSn,                          /* 계약일련번호 */
        @NotBlank
        String vlStrtDtm,                       /* 유효시작일시 */
        @NotBlank
        String vlEndDtm,                        /* 유효종료일시 */
        @NotBlank
        String pmotOjSpcDscDvCd,                /* 특별할인코드 */
        String pdCd,                            /* 상품코드 */
        @NotBlank
        String sellTpCd,                        /* 판매유형코드 */
        String dtaDlYn,                         /* 데이터삭제여부 */
        String fstRgstDtm,                      /* 최초등록일 */
        String fstRgstUsrId,                    /* 최초등록 사용자ID */
        String fstRgstUsrNm,                    /* 최초등록 사용자 이름 */
        String fnlMdfcDtm,                      /* 최종수정일 */
        String fnlMdfcUsrId,                    /* 최종수정 사용자ID */
        String fnlMdfcUsrNm                     /* 최종수정 사용자이름 */
    ) {
    }

    /**
     * RemoveReq - 데이터 삭제 DTO
     * @param pmotOjRelId
     * @param histStrtDtm
     */
    @ApiModel(value = "ZpmbPromotionObjectCustomerMgtDto-RemoveReq")
    public record RemoveReq(
        @NotBlank
        String pmotOjRelId,                 /* 프로모션대상관계ID */
        @NotBlank
        String histStrtDtm                  /* 이력시작일시 */
    ) {}

}
