package com.kyowon.sms.wells.web.service.orgcode.dto;

import java.util.List;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;

/**
 * <pre>
 * W-SV-U-0145M01 급지 우편번호 관리
 * </pre>
 *
 * @author hyewon.kim
 * @since 2022.12.06
 */
public class WsndRegionLevelZipMgtDto {

    @ApiModel(value = "WsndRegionLevelZipMngtDto-SearchReq")
    public record SearchReq(
        String zipFrom, // 우편번호 From
        String zipTo, // 우편번호 To
        String ctpvNm, // 시도명
        String ctctyNm, // 시군구명
        @NotBlank
        String wkGrpCd, // 작업그룹코드
        String ogId, // 서비스센터
        String unrgCenLocaraYn // 미등록 중심지
    ) {}

    @ApiModel(value = "WsndRegionLevelZipMngtDto-SearchRes")
    public record SearchRes(
        String newAdrZip, // 신주소우편번호
        String emdSn, // 읍면동일련번호
        String mgtCnt, // 지역별 서비스 계정 수
        String wrkCnt, // 월별 수임 건수 (조회월 이전 3개월 평균)
        String ctpvNm, // 시도명
        String ctctyNm, // 시군구명
        String lawcEmdNm, // 법정읍면동명
        String amtdNm, // 행정동명
        String rpbLocaraCd, // 책임지역코드
        String rpbLocaraGrpCd, // 책임지역그룹코드
        String ogNm, // 조직명
        String prtnrNo, // 담당파트너번호
        String prtnrKnm, // 파트너한글명
        String vstDowVal, // 방문요일값
        String pdlvNo, // 출고지번호
        String pdlvNm, // 행정동주민센터 (출고지명)
        String pdlvAdr, // 주민센터주소 (출고지주소)
        String ildYn, // 섬여부
        List<PlaceOfDelivery> placeOfDeliveries // 출고지
    ) {}

    @ApiModel(value = "WsndRegionLevelZipMngtDto-SearchExcelRes")
    public record SearchExcelRes(
        String newAdrZip, // 신주소우편번호
        String emdSn, // 읍면동일련번호
        String mgtCnt, // 지역별 서비스 계정 수
        String wrkCnt, // 월별 수임 건수 (조회월 이전 3개월 평균)
        String ctpvNm, // 시도명
        String ctctyNm, // 시군구명
        String lawcEmdNm, // 법정읍면동명
        String amtdNm, // 행정동명
        String rpbLocaraCd, // 책임지역코드
        String rpbLocaraGrpCd, // 책임지역그룹코드
        String ogNm, // 조직명
        String prtnrNo, // 담당파트너번호
        String prtnrKnm, // 파트너한글명
        String vstDowVal, // 방문요일값
        String pdlvNo, // 출고지번호
        String pdlvNm, // 행정동주민센터 (출고지명)
        String pdlvAdr, // 주민센터주소 (출고지주소)
        String ildYn // 섬여부
    ) {}

    @ApiModel(value = "WsndRegionLevelZipMngtDto-PlaceOfDelivery")
    public record PlaceOfDelivery(
        String pdlvDvCd, // 출고지구분코드
        String pdlvNo, // 출고지번호
        String pdlvNm, // 출고지명
        String zip, // 우편번호
        String pdlvAdr // 출고지주소
    ) {}

    @ApiModel(value = "WsndRegionLevelZipMngtDto-EditReq")
    public record EditReq(
        @NotBlank
        String newAdrZip, // 우편번호
        @NotBlank
        String emdSn, // 읍면동일련번호
        @NotBlank
        String pdlvNo // 출고지번호
    ) {}

}
