package com.kyowon.sms.wells.web.deduction.redf.dto;

import io.swagger.annotations.ApiModel;

public class WdeaSoleDistributorMgtDto {

    // 총판/B2B 되물림 생성 검색조건
    @ApiModel(value = "WdeaSoleDistributorMgtDto-SearchSoleDistributorCreateReq")
    public record SearchSoleDistributorCreateReq(
        String ogTpCd,
        String redfAdsbOcYmFrom,
        String redfAdsbOcYmTo,
        String perfYmFrom,
        String perfYmTo,
        String ogCdFrom, /*소속코드 from*/
        String ogCdTo /*소속코드 to*/
    ) {}

    // 총판/B2B 되물림 관리 - 수정
    @ApiModel(value = "WdeaSoleDistributorMgtDto-SaveReq")
    public record SaveReq(
        String ogNm, /*업체명*/
        String ogCd, /*소속코드*/
        String ogTpCd, /*조직유형, 수정용 key*/
        String redfAdsbOcYm, /*발생년월, 수정용 key*/
        String prtnrNo, /*판매자번호*/
        String redfDdtnBlamCrdovrAmt, /*되물림.전월잔액*/
        String redfOcAmt, /*되물림.당월발생*/
        String redfOcSum, /*되물림.당월합계*/
        String redfDdctam, /*되물림.당월공제*/
        String redfDdtnBlam /*되물림.당월잔액*/
    ) {}

    // 총판/B2B 되물림 생성 1번 그리드 RES
    @ApiModel(value = "WdeaSoleDistributorMgtDto-SearchSoleDistributorPrtnrRes")
    public record SearchSoleDistributorPrtnrRes(
        String perfYm, /*실적년월*/
        String ogCd, /*소속코드*/
        String ogNm, /*업체명*/
        String prtnrNo, /*판매자번호*/
        String totOcMmCnt, /*총실적.발생월*/
        String totRedfBfCnt, /*총실저.되물림전*/
        String totHcrCnt, /*총실적.홈케어*/
        String samOcMmCnt, /*삼성제품.발생월*/
        String samRedfBfCnt, /*삼성제품.되물림전*/
        String welOcMmCnt, /*자사제품.발생월*/
        String welRedfBfCnt, /*자사제품.되물림전*/
        String redfTotCnt, /*되물림.총실적*/
        String redfSamCnt, /*되물림.삼성실적*/
        String redfWelCnt, /*되물림.자사실적*/
        String totRedfAfCnt, /*되물림후.총실적*/
        String samRedfAfCnt, /*되물림후.삼성실적*/
        String welRedfAfCnt, /*되물림후.자사실적*/
        String feeBase, /*수수료(원).기본*/
        String feeEncrg, /*수수료(원).장려*/
        String feeSum /*수수료(원).합계*/
    ) {}

    // 총판/B2B 되물림 생성 2번 그리드 RES
    @ApiModel(value = "WdeaSoleDistributorMgtDto-SearchSoleDistributorContractRes")
    public record SearchSoleDistributorContractRes(
        String ogCd, /*지점코드*/
        String ogNm, /*지점명*/
        String prtnrKnm, /*판매자(파트너성명)*/
        String prtnrNo, /*판매자번호(파트너번호)*/
        String cntrNo, /*계약번호*/
        String cntrSn, /*계약일련번호*/
        String cntrNoSn, /*계약상세번호*/
        String cstKnm, /*고객성명*/
        String pdCd, /*상품코드*/
        String pdClsfNm, /*상품명*/
        String modelNo, /*모델명*/
        String sellDscDvCd, /*할인구분*/
        String sellDscTpCd, /*할인유형*/
        String pmotCd, /*할인제도*/
        String combiDv, /*결합구분*/
        String pmotUswyDvCd, /*용도구분*/
        String pmotUswyDvNm, /*관리유형*/
        String bfsvcPrdCd, /*방문주기*/
        String pdGub1, /*상품구분1*/
        String pdGub2, /*상품구분2*/
        String rcpdt, /*접수일자*/
        String slDt, /*매출일자*/
        String canDt, /*취소일자*/
        String ackmtPerfAmt, /*수수료(원)*/
        String ackmtPerfCt /*신규건수*/
    ) {}

    // 총판/B2B 되물림 생성 3번 그리드 RES
    @ApiModel(value = "WdeaSoleDistributorMgtDto-SearchBusinessToBusinessPrtnrRes")
    public record SearchBusinessToBusinessPrtnrRes(
        String perfYm, /*실적년월*/
        String ogCd, /*소속코드*/
        String ogNm, /*업체명*/
        String prtnrNo, /*판매자번호*/
        String ocPerfCn, /*발생월*/
        String redfBfCn, /*되물림전*/
        String ackmtPerfCt, /*당월 되물림*/
        String redfAfCn, /*되물림 후*/
        String feeBase /*수수료(원)*/
    ) {}

    // 총판/B2B 되물림 관리 검색조건
    @ApiModel(value = "WdeaSoleDistributorMgtDto-SearchSoleDistributorPrtnrReq")
    public record SearchSoleDistributorMgtReq(
        String ogTpCd,
        String redfAdsbOcYmFrom,
        String redfAdsbOcYmTo
    ) {}

    // 총판/B2B 되물림 관리 RES
    @ApiModel(value = "WdeaSoleDistributorMgtDto-SearchSoleDistributorMgtRes")
    public record SearchSoleDistributorMgtRes(
        String ogNm, /*업체명*/
        String ogCd, /*소속코드*/
        String ogTpCd, /*조직유형, 수정용 key*/
        String redfAdsbOcYm, /*발생년월, 수정용 key*/
        String prtnrNo, /*판매자번호*/
        String redfDdtnBlamCrdovrAmt, /*되물림.전월잔액*/
        String redfOcAmt, /*되물림.당월발생*/
        String redfOcSum, /*되물림.당월합계*/
        String redfDdctam, /*되물림.당월공제*/
        String redfDdtnBlam /*되물림.당월잔액*/
    ) {}

}
