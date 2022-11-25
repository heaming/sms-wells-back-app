package com.kyowon.sms.wells.web.service.allocate.dto;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

import javax.validation.constraints.NotBlank;

/**
 *
 * <pre>
 * W-SV-U-0036M01 책임지역 우편번호 관리
 * </pre>
 *
 * @author gs.piit130 김혜원
 * @since 2022.11.17
 */
public class WsncRpbLocaraZipMngtDto {

    @ApiModel(value = "WsncRpbLocaraZipMngtDto-SearchReq")
    public record SearchReq(
        String zipFrom, /* 우편번호 From */
        String zipTo, /* 우편번호 To */
        String ctpvNm, /* 시도명 */
        String ctctyNm, /* 시군구명 */
        String wkGrpCd, /* 작업그룹코드 */
        String applyDate /* 적용일자 */
    ) {}

    @ApiModel(value = "WsncRpbLocaraZipMngtDto-SearchRes")
    public record SearchRes(
        String newAdrZip, /* 신주소우편번호 */
        String mgtCnt, /* 지역별 서비스 계정 수 */
        String wrkCnt, /* 월별 수임 건수 (조회월 이전 3개월 평균) */
        String ctpvNm, /* 시도명 */
        String ctctyNm, /* 시군구명 */
        String lawcEmdNm, /* 법정읍면동명 */
        String amtdNm, /* 행정동명 */
        String mngtAmtd, /* 관리행정동 */
        String rpbLocaraCd, /* 책임지역코드 */
        String rpbLocaraGrpCd, /* 책임지역그룹코드 */
        String ogNm, /* 조직명 */
        String ichrPrtnrNo, /* 담당파트너번호 */
        String prtnrKnm, /* 파트너한글명 */
        String vstDowVal, /* 방문요일값 */
        String apyStrtdt, /* 적용시작일자 */
        String apyEnddt, /* 적용종료일자 */
        String emdSn, /* 읍면동일련번호 */
        String fr2pLgldCd, /* 앞2자리법정동코드 */
        String kynorLocaraYn, /* 경북지역여부 */
        String ildYn, /* 섬여부 */
        String pdlvNo, /* 출고지번호 */
        String dtaDlYn, /* 데이터삭제여부 */
        String applyDate
    ) {}

    @ApiModel("WsncRpbLocaraZipMngtDto-CreateReq")
    @Builder
    public record CreateReq(
        @NotBlank
        String newAdrZip, /* 신주소우편번호 */
        @NotBlank
        String emdSn, /* 읍면동일련번호 */
        String fr2pLgldCd, /* 앞2자리법정동코드 */
        String ctpvNm, /* 시도명 */
        String ctctyNm, /* 시군구명 */
        String lawcEmdNm, /* 법정읍면동명 */
        String amtdNm, /* 행정동명 */
        @NotBlank
        String kynorLocaraYn, /* 경북지역여부 */
        String ildYn, /* 섬여부 */
        String pdlvNo, /* 출고지번호 */
        String dtaDlYn /* 데이터삭제여부 */
    ) {}

}
