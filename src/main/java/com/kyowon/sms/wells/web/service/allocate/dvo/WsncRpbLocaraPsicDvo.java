package com.kyowon.sms.wells.web.service.allocate.dvo;

import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * W-SV-U-0017M01 책임지역 담당자 관리
 * </pre>
 *
 * @author hyewon.kim
 * @since 2022.12.22
 */
@Setter
@Getter
public class WsncRpbLocaraPsicDvo {

    private String zipList; // 우편번호 리스트
    private String hemdList; // 행정동 리스트
    private String mgtCnt; // 지역별 서비스 계정 수
    private String wrkCnt; // 월별 수임 건수 (조회월 이전 3개월 평균)
    private String rpbLocaraCd; // 책임지역코드
    private String wkGrpCd; // 작업그룹코드
    private Integer izSn; // 내역일련번호
    private String apyStrtdt; // 적용시작일자
    private String apyEnddt; // 적용종료일자
    private String ichrPrtnrNo; // 담당파트너번호
    private String prtnrKnm; // 파트너한글명
    private String pprnIchrPrtnrNo1; // 예비담당파트너번호1
    private String pprnIchrPrtnrKnm1; // 예비담당파트너번호1
    private String pprnIchrPrtnrNo2; // 예비담당파트너번호2
    private String pprnIchrPrtnrKnm2; // 예비담당파트너번호2
    private String pprnIchrPrtnrNo3; // 예비담당파트너번호3
    private String pprnIchrPrtnrKnm3; // 예비담당파트너번호3
    private String pprnIchrPrtnrNo4; // 예비담당파트너번호4
    private String pprnIchrPrtnrKnm4; // 예비담당파트너번호4
    private String pprnIchrPrtnrNo5; // 예비담당파트너번호5
    private String pprnIchrPrtnrKnm5; // 예비담당파트너번호5
    private String rstrCndtUseYn; // 제약조건사용여부
    private String udsnUseYn; // 미지정사용여부
    private String rpbLocaraGrpCd; // 책임지역그룹코드
    private String vstDowVal; // 방문요일값
    private Long mmtAvLdtm; // 이동평균소요시간
    private String locaraCenStruAdr; // 지역중심건물주소
    private String w1W3SatWrkYn; // 1주3주토요일근무여부
    private String ogNm; // 조직명

}
