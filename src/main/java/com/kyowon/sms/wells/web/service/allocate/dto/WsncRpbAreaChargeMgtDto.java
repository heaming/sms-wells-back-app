package com.kyowon.sms.wells.web.service.allocate.dto;

import javax.validation.constraints.NotBlank;

import com.sds.sflex.common.utils.StringUtil;

import io.swagger.annotations.ApiModel;

/**
 * <pre>
 * W-SV-U-0017M01 책임지역 담당자 관리
 * </pre>
 *
 * @author hyewon.kim
 * @since 2022.12.22
 */
public class WsncRpbAreaChargeMgtDto {

    @ApiModel(value = "WsncRpbLocaraPsicMngtDto-SearchReq")
    public record SearchReq(
        String zipFrom, // 우편번호 From
        String zipTo, // 우편번호 To
        String ctpvNm, // 시도명
        String ctctyNm, // 시군구명
        String ogId, // 서비스센터(조직ID)
        @NotBlank
        String wkGrpCd, // 작업그룹코드
        @NotBlank
        String applyDate, // 적용일자
        String rpbLocaraCdFrom, // 지역코드 From
        String rpbLocaraCdTo // 지역코드 To
    ) {}

    @ApiModel(value = "WsncRpbLocaraPsicMngtDto-SearchRes")
    public record SearchRes(
        String zipList, // 우편번호 리스트
        String hemdList, // 행정동 리스트
        String mgtCnt, // 지역별 서비스 계정 수
        String wrkCnt, // 월별 수임 건수 (조회월 이전 3개월 평균)
        String rpbLocaraCd, // 책임지역코드
        String wkGrpCd, // 작업그룹코드
        Integer izSn, // 내역일련번호
        String apyStrtdt, // 적용시작일자
        String apyEnddt, // 적용종료일자
        String ichrPrtnrNo, // 담당파트너번호
        String prtnrKnm, // 파트너한글명
        String pprnIchrPrtnrNo1, // 예비담당파트너번호1
        String pprnIchrPrtnrKnm1, // 예비담당파트너번호1
        String pprnIchrPrtnrNo2, // 예비담당파트너번호2
        String pprnIchrPrtnrKnm2, // 예비담당파트너번호2
        String pprnIchrPrtnrNo3, // 예비담당파트너번호3
        String pprnIchrPrtnrKnm3, // 예비담당파트너번호3
        String pprnIchrPrtnrNo4, // 예비담당파트너번호4
        String pprnIchrPrtnrKnm4, // 예비담당파트너번호4
        String pprnIchrPrtnrNo5, // 예비담당파트너번호5
        String pprnIchrPrtnrKnm5, // 예비담당파트너번호5
        String rstrCndtUseYn, // 제약조건사용여부
        String udsnUseYn, // 미지정사용여부
        String rpbLocaraGrpCd, // 책임지역그룹코드
        String vstDowVal, // 방문요일값
        Long mmtAvLdtm, // 이동평균소요시간
        String locaraCenStruAdr, // 지역중심건물주소
        String w1W3SatWrkYn, // 1주3주토요일근무여부
        String ogNm // 조직명
    ) {}

    @ApiModel(value = "WsncRpbLocaraPsicMngtDto-SearchZipsReq")
    public record SearchZipsReq(
        @NotBlank
        String applyDate, // 적용일자
        @NotBlank
        String rpbLocaraCd // 지역코드
    ) {}

    @ApiModel(value = "WsncRpbLocaraPsicMngtDto-CreateReq")
    public record CreateReq(
        @NotBlank
        String wkGrpCd, // 작업그룹코드
        @NotBlank
        String rpbLocaraCd, // 책임지역코드
        Integer izSn, // 내역일련번호
        @NotBlank
        String ichrPrtnrNo, // 담당파트너번호
        String pprnIchrPrtnrNo1, // 예비담당파트너번호1
        String pprnIchrPrtnrNo2, // 예비담당파트너번호2
        String pprnIchrPrtnrNo3, // 예비담당파트너번호3
        String pprnIchrPrtnrNo4, // 예비담당파트너번호4
        String pprnIchrPrtnrNo5, // 예비담당파트너번호5
        String vstDowVal, // 방문요일값
        Long mmtAvLdtm, // 이동평균소요시간
        String rstrCndtUseYn, // 제약조건사용여부
        String udsnUseYn, // 미지정사용여부
        String locaraCenStruAdr, // 지역중심건물주소
        String w1W3SatWrkYn, // 1주3주토요일근무여부 0
        String rpbLocaraGrpCd, // 책임지역그룹코드
        @NotBlank
        String apyStrtdt, // 적용시작일자
        @NotBlank
        String apyEnddt // 적용종료일자
    ) {
        public CreateReq {
            rstrCndtUseYn = StringUtil.nvl(rstrCndtUseYn, "N");
            udsnUseYn = StringUtil.nvl(udsnUseYn, "N");
            w1W3SatWrkYn = StringUtil.nvl(w1W3SatWrkYn, "N");
        }
    }

}
