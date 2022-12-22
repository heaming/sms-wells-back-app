package com.kyowon.sms.wells.web.service.orgcode.dvo;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * W-SV-U-0145M01 급지 우편번호 관리
 * </pre>
 *
 * @author gs.piit130 김혜원
 * @since 2022.12.06
 */
@Getter
@Setter
public class WsndRegionLevelZipNoDvo {

    private String newAdrZip; // 신주소우편번호
    private String mgtCnt; // 지역별 서비스 계정 수
    private String wrkCnt; // 월별 수임 건수 (조회월 이전 3개월 평균)
    private String ctpvNm; // 시도명
    private String ctctyNm; // 시군구명
    private String lawcEmdNm; // 법정읍면동명
    private String amtdNm; // 행정동명
    private String rpbLocaraCd; // 책임지역코드
    private String rpbLocaraGrpCd; // 책임지역그룹코드
    private String ogNm; // 조직명
    private String ichrPrtnrNo; // 담당파트너번호
    private String prtnrKnm; // 파트너한글명
    private String vstDowVal; // 방문요일값
    private String pdlvNo; // 출고지번호
    private String pdlvNm; // 행정동주민센터 (출고지명)
    private String pdlvAdr; // 주민센터주소 (출고지주소)
    private String ildYn; // 섬여부
    private String applyDate; // 적용일자
    private List<WsndPlaceOfDeliveryDvo> placeOfDeliveries;

}
