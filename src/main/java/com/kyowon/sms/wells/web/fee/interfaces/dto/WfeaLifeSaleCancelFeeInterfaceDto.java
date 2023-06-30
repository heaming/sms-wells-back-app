package com.kyowon.sms.wells.web.fee.interfaces.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
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
        int akdseq, /* 회차 */
        @JsonProperty("AKSGUB")
        @JsonSetter(nulls = Nulls.SKIP)
        @JsonDeserialize(using = EmptyStringToNullDeserializer.class)
        String aksgub, /* 구분 */
        @JsonProperty("AKSALE")
        @JsonSetter(nulls = Nulls.SKIP)
        @JsonDeserialize(using = EmptyStringToNullDeserializer.class)
        String aksale, /* 판매구분 */
        @JsonProperty("AKDCDE")
        @JsonSetter(nulls = Nulls.SKIP)
        @JsonDeserialize(using = EmptyStringToNullDeserializer.class)
        String akdcde, /* 사번 */
        @JsonProperty("AKDBON")
        @JsonSetter(nulls = Nulls.SKIP)
        @JsonDeserialize(using = EmptyStringToNullDeserializer.class)
        String akdbon, /* 지점장 */
        @JsonProperty("AKCODE")
        @JsonSetter(nulls = Nulls.SKIP)
        @JsonDeserialize(using = EmptyStringToNullDeserializer.class)
        String akcode, /* 상조상품코드 */
        @JsonProperty("ITM03")
        @JsonSetter(nulls = Nulls.SKIP)
        @JsonDeserialize(using = EmptyStringToNullDeserializer.class)
        String itm03, /* 상조상품명 */
        @JsonProperty("AKCRTE")
        @JsonSetter(nulls = Nulls.SKIP)
        @JsonDeserialize(using = EmptyStringToNullDeserializer.class)
        String akcrte, /* 접수일 */
        @JsonProperty("AKSLTE")
        @JsonSetter(nulls = Nulls.SKIP)
        @JsonDeserialize(using = EmptyStringToNullDeserializer.class)
        String akslte, /* 계약일 */
        @JsonProperty("AKCDTE")
        @JsonSetter(nulls = Nulls.SKIP)
        @JsonDeserialize(using = EmptyStringToNullDeserializer.class)
        String akcdte, /* 취소일 */
        @JsonProperty("AKTCNT")
        @JsonSetter(nulls = Nulls.SKIP)
        @JsonDeserialize(using = EmptyStringToNullDeserializer.class)
        String aktcnt, /* 총지급대상 */
        @JsonProperty("AKMAMT")
        @JsonSetter(nulls = Nulls.SKIP)
        @JsonDeserialize(using = EmptyStringToNullDeserializer.class)
        int akmamt, /* 누적발생매출 */
        @JsonProperty("AKIAMT")
        @JsonSetter(nulls = Nulls.SKIP)
        @JsonDeserialize(using = EmptyStringToNullDeserializer.class)
        int akiamt, /* 누적입금 */
        @JsonProperty("AKISEQ")
        @JsonSetter(nulls = Nulls.SKIP)
        @JsonDeserialize(using = EmptyStringToNullDeserializer.class)
        int akiseq, /* 완납회차 */
        @JsonProperty("ITM04")
        @JsonSetter(nulls = Nulls.SKIP)
        @JsonDeserialize(using = EmptyStringToNullDeserializer.class)
        String itm04, /* WELLS 고객코드 */
        @JsonProperty("ITM05")
        @JsonSetter(nulls = Nulls.SKIP)
        @JsonDeserialize(using = EmptyStringToNullDeserializer.class)
        String itm05, /* WELLS 계약일련번호 */
        @JsonProperty("AKGDYM")
        @JsonSetter(nulls = Nulls.SKIP)
        @JsonDeserialize(using = EmptyStringToNullDeserializer.class)
        String akgdym, /* 수수료지급월 */
        @JsonProperty("AKHDYM")
        @JsonSetter(nulls = Nulls.SKIP)
        @JsonDeserialize(using = EmptyStringToNullDeserializer.class)
        String akhdym, /* 수수료되물림 */
        @JsonProperty("AKLOCK")
        @JsonSetter(nulls = Nulls.SKIP)
        @JsonDeserialize(using = EmptyStringToNullDeserializer.class)
        String aklock, /* 마감여부 */
        @JsonProperty("ITM06")
        @JsonSetter(nulls = Nulls.SKIP)
        @JsonDeserialize(using = EmptyStringToNullDeserializer.class)
        String itm06, /* 데이터삭제여부 */
        @JsonProperty("AKWDAY")
        @JsonSetter(nulls = Nulls.SKIP)
        @JsonDeserialize(using = EmptyStringToNullDeserializer.class)
        String akwday, /* 생성일자 */
        @JsonProperty("AKWDSP")
        @JsonSetter(nulls = Nulls.SKIP)
        @JsonDeserialize(using = EmptyStringToNullDeserializer.class)
        String akwdsp, /* 생성자 */
        @JsonProperty("AKWPGM")
        @JsonSetter(nulls = Nulls.SKIP)
        @JsonDeserialize(using = EmptyStringToNullDeserializer.class)
        String akwpgm, /* 생성PGM */
        @JsonProperty("ITM07")
        @JsonSetter(nulls = Nulls.SKIP)
        @JsonDeserialize(using = EmptyStringToNullDeserializer.class)
        String itm07, /* 최초등록부서ID */
        @JsonProperty("AKUDAY")
        @JsonSetter(nulls = Nulls.SKIP)
        @JsonDeserialize(using = EmptyStringToNullDeserializer.class)
        String akuday, /* 수정일자 */
        @JsonProperty("AKUDSP")
        @JsonSetter(nulls = Nulls.SKIP)
        @JsonDeserialize(using = EmptyStringToNullDeserializer.class)
        String akudsp, /* 수정자 */
        @JsonProperty("AKUPGM")
        @JsonSetter(nulls = Nulls.SKIP)
        @JsonDeserialize(using = EmptyStringToNullDeserializer.class)
        String akupgm, /* 생성PGM */
        @JsonProperty("ITM08")
        @JsonSetter(nulls = Nulls.SKIP)
        @JsonDeserialize(using = EmptyStringToNullDeserializer.class)
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
