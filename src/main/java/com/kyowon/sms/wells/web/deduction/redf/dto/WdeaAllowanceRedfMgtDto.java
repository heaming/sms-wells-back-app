package com.kyowon.sms.wells.web.deduction.redf.dto;

import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WdeaAllowanceRedfMgtDto {
    @Builder
    @ApiModel("WdeaAllowanceRedfMgtDto-SearchReq")
    public record SearchReq(
        String redfAdsbOcYmFrom, // 발생년월(from)
        String redfAdsbOcYmTo, // 발생년월(to)
        String slYmFrom, // 매출년월(from) 
        String slYmTo, // 매출년월(to)
        String ogTpCd, // 조직유형
        String sellTpCd, // 상품구분
        String envrYn, // 환경여부
        String redfAdsbTpCd, // 처리유형
        String prtnrNoFrom, // 파트너번호(from)
        String prtnrNoTo, // 파트너번호(to)

        /*조직유형 총판, B2B의 경우 소속코드로 검색조건 변경*/
        String ogCdFrom, // 소속코드(from)
        String ogCdTo // 소속코드(to)
    ) {}

    @ApiModel("WdeaAllowanceRedfMgtDto-SearchRedfRecordRes")
    public record SearchAwRedfRes(
        String redfAdsbOcYm, /* 발생년월 */
        String slYm, /*매출년월     */
        String pdGub, /* 상품구분   */
        String envrYn, /* 환경여부  */
        String pdCd, /*상품코드*/
        String pdClsfNm, /*상품명*/
        String cntrNo, /*계약번호*/
        String cntrSn, /*계약일련번호*/
        String cntrNoSn, /*계약상세번호*/
        String cstKnm, /*고객명*/
        String prtnrKnm, /*판매자성명*/
        String prtnrNo, /*파트너번호*/
        String gnlrLedr, /*총괄단장, TODO: 조직쪽에서 정리되면 추가*/
        String localAreaManager, /*지역단장, TODO: 조직쪽에서 정리되면 추가*/
        String branchManager, /*지점장, TODO: 조직쪽에서 정리되면 추가*/
        String dstrcManager, /*지구장, TODO: 조직쪽에서 정리되면 추가*/
        String perfTpCd, /*처리유형(실적유형코드) 01: 취소, 10: 연체*/
        String thmOcDlqAmt, /*연체금액, 당월발생연체금액*/
        String cntrDtlStatChRsonNm, /*취소유형*/
        String dlqYm, /*연체되물림년월*/
        String rdsbYm, /*재지급년월*/
        String rentalAmt, /*렌탈료*/
        String mchnChTpCd, /*기변유형*/
        String feeFxamYn, /*정액여부*/
        String feeAckmtCt, /*인정건수*/
        String perfVal1, /*BS인정건수*/
        String perfVal2, /*BS신규건수*/
        String sellDscDvCd, /*할인구분*/
        String sellDscrCd, /*할인유형*/
        String sellDscTpCd, /*할인제도*/
        String pakSn, /*패키지 일련번호*/
        String feeAckmtBaseAmt, /*수수료 인정기준금액 - wells M용*/
        String ackmtPerfAmt, /*인정실적금액 - wells P 용*/
        String booYn, /*예약여부*/
        String ogId, /*지점*/
        String cntrPdStrtdt, /*설치일자*/
        String canDt, /*취소일자*/
        String cpsnRedfYn /*강제되물림여부*/
    ) {}

    @ApiModel("WdeaAllowanceRedfMgtDto-SearchRedfDetailRes")
    public record SearchRedfRes(
        String baseYm, /*발생년월*/
        String slYm, /*매출년월*/
        String ogNm, /*업체명*/
        String ogCd, /*소속코드*/
        String prtnrKnm, /*판매자명*/
        String prtnrNo, /*파트너번호*/
        String cntrNo, /*계약번호*/
        String cntrSn, /*계약일련번호*/
        String cntrNoSn, /*계약상세번호*/
        String cstKnm, /*고객명*/
        String pdCd, /*제품코드*/
        String pdClsfNm, /*제품명*/
        String modelNo, /*모델명*/
        String sellDscDvCd, /*할인구분*/
        String sellDscTpCd, /*할인유형*/
        String pmotUswyDvCd, /*업소*/
        String bfsvcPrdCd, /*주기*/
        String mchnCh1, /*기변영*/
        String mchnCh2, /*기변센*/
        String rcpDt, /*계약일자*/
        String slDt, /*매출일자*/
        String canDt, /*취소일자*/
        String redfAmt /*되물림액*/
    ) {}

}
