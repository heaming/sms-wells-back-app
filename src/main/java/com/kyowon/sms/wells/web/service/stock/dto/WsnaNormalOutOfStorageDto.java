package com.kyowon.sms.wells.web.service.stock.dto;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;

import com.sds.sflex.system.config.validation.validator.ValidDate;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

/**
 * <pre>
 * W-SV-U-0142M01 정상출고 관리
 * </pre>
 *
 * @author inho.choi
 * @since 2023-03-14
 */
public class WsnaNormalOutOfStorageDto {
    @Builder
    @ApiModel(value = "WsnaNormalOutOfStorageDto-SearchReq")
    public record SearchReq(
        // 입고희망시작일자
        @NotBlank
        @ValidDate
        String strHopDtStr,
        // 입고희망종료일자
        @NotBlank
        @ValidDate
        String strHopDtEnd,
        // 출고상태 리스트
        List<String> ostrStts,
        // 출고유형
        String ostrAkTpCd,
        // 출고창고
        @NotBlank
        String ostrOjWareNo,
        // 출고품목
        String itmKndCd,
        // 출고요청창고구분
        @NotBlank
        String wareDvCd
    ) {}

    @Builder
    @ApiModel(value = "WsnaNormalOutOfStorageDto-SearchRes")
    public record SearchRes(
        // 출고요청번호
        String ostrAkNo,
        // 출고요청유형
        String ostrAkTpCd,
        // 출고대상창고번호
        String ostrOjWareNo,
        // 출고대상창고명
        String ostrOjWareNm,
        // 입고대상창고번호
        String strOjWareNo,
        // 입고대상창고명
        String strOjWareNm,
        // 입고희망일자
        String strHopDt,
        // 배차유형
        String ovivTpCd,
        // 최근출고일자
        String rectOstrDt,
        // 품목코드
        String itmPdCd,
        // 출고요청등록일자
        String ostrAkRgstDt,
        // 출고상태
        String ostrDtrnYn,
        // 출고요청품목
        String pdNm,
        // 출고요청일련번호
        int ostrAkSn,
        // 비고
        String rmkCn

    ) {}

    @Builder
    @ApiModel(value = "WsnaNormalOutOfStorageDto-SearchWarehouse")
    public record SearchWarehouse(
        // 창고번호
        String wareNo,
        // 창고명
        String wareNm
    ) {}

    @Builder
    @ApiModel(value = "WsnaNormalOutOfStorageDto-AskReq")
    public record AskReq(
        // 품목코드
        @NotBlank
        String itmPdCd,
        // 입고창고
        @NotBlank
        String strOjWareNo,
        // 창고구분
        @NotBlank
        String wareDvCd
    ) {}

    @Builder
    @ApiModel(value = "WsnaNormalOutOfStorageDto-CenterReq")
    public record CenterReq(
        // 품목코드
        @NotBlank
        String itmPdCd,
        // 창고구분
        @NotBlank
        String wareDvCd
    ) {}

    @Builder
    @ApiModel(value = "WsnaNormalOutOfStorageDto-AskRes")
    public record AskRes(
        String wareMngtPrtnrNo, /*창고파트너번호*/
        String wareNm, /*창고명*/
        String itmPdCd, /*품목상품코드*/
        String wareNo, /*창고번호*/
        String qty /*현재재고수량*/
    ) {}

    @Builder
    @ApiModel(value = "WsnaNormalOutOfStorageDto-CenterRes")
    public record CenterRes(
        String wareNo, /*창고번호*/
        String qty, /*현재재고수량*/
        String wareNm /*창고명*/
    ) {}

    @Builder
    @ApiModel(value = "WsnaNormalOutOfStorageDto-DetailReq")
    public record DetailReq(
        // 출고요청유형
        @NotBlank
        String ostrAkTpCd,
        // 출고요청번호
        @NotBlank
        String ostrAkNo,
        // 입고대상창고
        @NotBlank
        String strOjWareNo,
        // 출고대상창고
        @NotBlank
        String ostrOjWareNo
    ) {}

    @Builder
    @ApiModel(value = "WsnaNormalOutOfStorageDto-DetailRemoveReq")
    public record DetailRemoveReq(
        // 출고유형
        @NotBlank
        String ostrTpCd,
        // 품목출고번호
        @NotBlank
        String itmOstrNo,
        // 입고대상창고
        @NotBlank
        String strOjWareNo,
        // 출고대상창고
        @NotBlank
        String ostrOjWareNo
    ) {}

    @Builder
    @ApiModel(value = "WsnaNormalOutOfStorageDto-RemoveReq")
    public record RemoveReq(
        // 품목출고번호
        @NotBlank
        String itmOstrNo,
        // 출고일련번호
        @Positive
        int ostrSn,
        // 출고요청번호
        @NotBlank
        String ostrAkNo,
        // 출고요청일련번호
        @Positive
        int ostrAkSn,
        // 품목입고번호
        @NotBlank
        String itmStrNo,
        // 입고일련번호
        @Positive
        int strSn,
        // 출고유형
        @NotBlank
        String ostrTpCd,
        // 입고유형
        @NotBlank
        String strTpCd,
        // 출고대상창고
        @NotBlank
        String ostrOjWareNo,
        // 출고창고구분
        String ostrWareDvCd,
        // 출고창고파트너번호
        String ostrPrtnrNo,
        // 출고창고조직유형
        String ostrOgTpCd,
        // 품목코드
        @NotBlank
        String itmPdCd,
        // 등급
        @NotBlank
        String itmGdCd,
        // 관리단위
        String mngtUnitCd,
        // 출고수량
        @Max(999999999999L)
        BigDecimal outQty,

        // 입고창고
        @NotBlank
        String strOjWareNo,
        // 입고창고구분
        String strWareDvCd,
        // 입고창고파트너번호
        String strPrtnrNo,
        // 입고창고조직유형
        String strOgTpCd

    ) {}

    @Builder
    @ApiModel(value = "WsnaNormalOutOfStorageDto-CreqteReq")
    public record CreateReq(
        // 출고요청번호
        @NotBlank
        String ostrAkNo,
        // 출고요청일련번호
        @Positive
        int ostrAkSn,
        // 출고유형
        @NotBlank
        String ostrTpCd,
        // 입고유형
        @NotBlank
        String strTpCd,
        // 출고대상창고
        @NotBlank
        String ostrOjWareNo,
        // 출고창고구분
        String ostrWareDvCd,
        // 출고창고파트너번호
        String ostrPrtnrNo,
        // 출고창고조직유형
        String ostrOgTpCd,
        // 품목코드
        @NotBlank
        String itmPdCd,
        // 등급
        @NotBlank
        String itmGdCd,
        // 관리단위
        String mngtUnitCd,
        // 박스단위수량
        BigDecimal boxUnitQty,
        // 품목종류
        String svpdItemKnd,
        // 출고수량
        @Max(999999999999L)
        BigDecimal outQty,
        // 입고대상창고
        @NotBlank
        String strOjWareNo,
        // 입고창고구분
        String strWareDvCd,
        // 입고창고파트너번호
        String strPrtnrNo,
        // 입고창고조직유형
        String strOgTpCd,
        // 출고일자
        @NotBlank
        @ValidDate
        String ostrDt,
        // 비고
        String rmkCn
    ) {}

    @Builder
    @ApiModel(value = "WsnaNormalOutOfStorageDto-StandardWareReq")
    public record StandardWareReq(
        // 기준년월
        @NotBlank
        String apyYm,
        // 창고번호
        @NotBlank
        String wareNo,
        // 표준창고사용여부
        String stckStdGb
    ) {}

    @Builder
    @ApiModel(value = "WsnaNormalOutOfStorageDto-StandardWareRes")
    public record StandardWareRes(
        // 기준년월
        String apyYm,
        // 창고번호
        String wareNo,
        // 표준창고사용여부
        String stckStdGb
    ) {}

    @Builder
    @ApiModel(value = "WsnaNormalOutOfStorageDto-SearchItmOstrAkReq")
    public record SearchItmOstrAkReq(
        // 출고요청번호
        @NotBlank
        String ostrAkNo,
        // 출고요청일련번호
        @Positive
        int ostrAkSn,
        // 품목출고번호
        String itmOstrNo
    ) {}

    @Builder
    @ApiModel(value = "WsnaNormalOutOfStorageDto-SearchItmOstrAkRes")
    public record SearchItmOstrAkRes(
        // 출고요청번호
        String ostrAkNo,
        // 출고요청유형코드
        String ostrAkTpCd,
        // 입고희망일자
        String strHopDt,
        // 출고요청등록일자
        String ostrAkRgstDt,
        // 출고대상창고번호
        String ostrOjWareNo,
        // 입고대상창고번호
        String strOjWareNo,
        // 출고대상창고명
        String ostrOjWareNm,
        // 입고대상창고명
        String strOjWareNm,
        // 출고요청유형
        String ostrAkTpNm,
        // 품목코드
        String itmPdCd,
        // 품목출고번호
        String itmOstrNo,
        // 출고유형
        String ostrTpCd,
        // 표준창고사용여부
        String stckStdGb,
        // 출고일자
        String ostrDt,
        // 기준년월
        String baseYm
    ) {}

    @Builder
    @ApiModel(value = "WsnaNormalOutOfStorageDto-CheckReq")
    public record CheckReq(
        // 출고요청번호
        @NotBlank
        String ostrAkNo,
        // 출고요청일련번호 리스트
        @NotEmpty
        List<Integer> ostrAkSns
    ) {}
}
