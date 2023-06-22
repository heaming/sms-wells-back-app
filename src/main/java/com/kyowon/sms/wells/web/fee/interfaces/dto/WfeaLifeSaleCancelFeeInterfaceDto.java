package com.kyowon.sms.wells.web.fee.interfaces.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import lombok.Builder;

import javax.validation.constraints.NotBlank;
import java.util.List;

public class WfeaLifeSaleCancelFeeInterfaceDto {
    @ApiModel("WfeaLifeSaleCancelFeeInterfaceDto-SaveReq")
    public record SaveReq(
        @NotBlank
        @JsonProperty("AKSDTE")
        String aKSDTE, /* 발생월 */
        @NotBlank
        @JsonProperty("ITM01")
        String iTM01, /* 예상확정구분코드 */
        @NotBlank
        @JsonProperty("ITM02")
        String iTM02, /* 상조고객코드 */
        @NotBlank
        @JsonProperty("AKDSEQ")
        String aKDSEQ, /* 회차 */
        @JsonProperty("AKSGUB")
        String aKSGUB, /* 구분 */
        @JsonProperty("AKSALE")
        String aKSALE, /* 판매구분 */
        @JsonProperty("AKDCDE")
        String aKDCDE, /* 사번 */
        @JsonProperty("AKDBON")
        String aKDBON, /* 지점장 */
        @JsonProperty("AKCODE")
        String aKCODE, /* 상조상품코드 */
        @JsonProperty("ITM03")
        String iTM03, /* 상조상품명 */
        @JsonProperty("AKCRTE")
        String aKCRTE, /* 접수일 */
        @JsonProperty("AKSLTE")
        String aKSLTE, /* 계약일 */
        @JsonProperty("AKCDTE")
        String aKCDTE, /* 취소일 */
        @JsonProperty("AKTCNT")
        String aKTCNT, /* 총지급대상 */
        @JsonProperty("AKMAMT")
        String aKMAMT, /* 누적발생매출 */
        @JsonProperty("AKIAMT")
        String aKIAMT, /* 누적입금 */
        @JsonProperty("AKISEQ")
        String aKISEQ, /* 완납회차 */
        @JsonProperty("ITM04")
        String iTM04, /* WELLS 고객코드 */
        @JsonProperty("ITM05")
        String iTM05, /* WELLS 계약일련번호 */
        @JsonProperty("AKGDYM")
        String aKGDYM, /* 수수료지급월 */
        @JsonProperty("AKHDYM")
        String aKHDYM, /* 수수료되물림 */
        @JsonProperty("AKLOCK")
        String aKLOCK, /* 마감여부 */
        @JsonProperty("ITM06")
        String iTM06, /* 데이터삭제여부 */
        @JsonProperty("AKWDAY")
        String aKWDAY, /* 생성일자 */
        @JsonProperty("AKWDSP")
        String aKWDSP, /* 생성자 */
        @JsonProperty("AKWPGM")
        String aKWPGM, /* 생성PGM */
        @JsonProperty("ITM07")
        String iTM07, /* 최초등록부서ID */
        @JsonProperty("AKUDAY")
        String aKUDAY, /* 수정일자 */
        @JsonProperty("AKUDSP")
        String aKUDSP, /* 수정자 */
        @JsonProperty("AKUPGM")
        String aKUPGM, /* 생성PGM */
        @JsonProperty("ITM08")
        String iTM08 /* 최종수정부서ID */

    ) {}

    @ApiModel("WfeaLifeSaleCancelFeeInterfaceDto-SaveRes")
    @Builder
    public record SaveRes(
        @JsonProperty("RS_CD")
        String rsCd, /*결과코드*/
        @JsonProperty("RS_MSG")
        String rsMsg /*결과메세지*/
    ) {}
}
