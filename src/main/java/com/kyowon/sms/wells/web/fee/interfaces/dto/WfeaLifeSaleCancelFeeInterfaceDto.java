package com.kyowon.sms.wells.web.fee.interfaces.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import lombok.Builder;

import javax.validation.constraints.NotBlank;
import java.util.List;

public class WfeaLifeSaleCancelFeeInterfaceDto {


    @Builder
    public record IfRequest(
        @NotBlank
        @JsonProperty("AKSDTE")
        String aksdte, /* 발생월 */
        @NotBlank
        @JsonProperty("ITM01")
        String itm01, /* 예상확정구분코드 */
        @NotBlank
        @JsonProperty("ITM02")
        String itm02, /* 상조고객코드 */
        @NotBlank
        @JsonProperty("AKDSEQ")
        Integer akdseq, /* 회차 */
        @JsonProperty("AKSGUB")
        String aksgub, /* 구분 */
        @JsonProperty("AKSALE")
        String aksale, /* 판매구분 */
        @JsonProperty("AKDCDE")
        String akdcde, /* 사번 */
        @JsonProperty("AKDBON")
        String akdbon, /* 지점장 */
        @JsonProperty("AKCODE")
        String akcode, /* 상조상품코드 */
        @JsonProperty("ITM03")
        String itm03, /* 상조상품명 */
        @JsonProperty("AKCRTE")
        String akcrte, /* 접수일 */
        @JsonProperty("AKSLTE")
        String akslte, /* 계약일 */
        @JsonProperty("AKCDTE")
        String akcdte, /* 취소일 */
        @JsonProperty("AKTCNT")
        String aktcnt, /* 총지급대상 */
        @JsonProperty("AKMAMT")
        Long  akmamt, /* 누적발생매출 */
        @JsonProperty("AKIAMT")
        Long  akiamt, /* 누적입금 */
        @JsonProperty("AKISEQ")
        Integer akiseq, /* 완납회차 */
        @JsonProperty("ITM04")
        String itm04, /* WELLS 고객코드 */
        @JsonProperty("ITM05")
        String itm05, /* WELLS 계약일련번호 */
        @JsonProperty("AKGDYM")
        String akgdym, /* 수수료지급월 */
        @JsonProperty("AKHDYM")
        String akhdym, /* 수수료되물림 */
        @JsonProperty("AKLOCK")
        String aklock, /* 마감여부 */
        @JsonProperty("ITM06")
        String itm06, /* 데이터삭제여부 */
        @JsonProperty("AKWDAY")
        String akwday, /* 생성일자 */
        @JsonProperty("AKWDSP")
        String akwdsp, /* 생성자 */
        @JsonProperty("AKWPGM")
        String akwpgm, /* 생성PGM */
        @JsonProperty("ITM07")
        String itm07, /* 최초등록부서ID */
        @JsonProperty("AKUDAY")
        String akuday, /* 수정일자 */
        @JsonProperty("AKUDSP")
        String akudsp, /* 수정자 */
        @JsonProperty("AKUPGM")
        String akupgm, /* 생성PGM */
        @JsonProperty("ITM08")
        String itm08 /* 최종수정부서ID */



    ) {}
    @Builder
    @ApiModel("WfeaLifeSaleCancelFeeInterfaceDto-SaveReq")
    public record SaveReq(
        @JsonProperty("LIST")
        List<IfRequest> list
    ) {}

    @Builder
    @ApiModel("WfeaLifeSaleCancelFeeInterfaceDto-SaveRes")
    public record SaveRes(
        @JsonProperty("RS_CD")
        String rsCd, /*결과코드*/
        @JsonProperty("RS_MSG")
        String rsMsg /*결과메세지*/
    ) {}

}
