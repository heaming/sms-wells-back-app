package com.kyowon.sms.wells.web.deduction.redf.dto;

import java.math.BigDecimal;
import java.util.List;

import com.sds.sflex.system.config.datasource.PagingResult;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

public class WwdeaMutualAidFeeMgtDto {

    @Builder
    @ApiModel("WwdeaMutualAidFeeMgtDto-SearchMutualAidFeeReq")
    public record SearchMutualAidFeeReq(
        String prtnrNo,
        String baseYm,
        String dvCd,
        String redfAdsbTpCd
    ) {}

    @ApiModel("WwdeaMutualAidFeeMgtDto-SearchMutualAidFeeRes")
    public record SearchMutualAidFeeRes(
        String dvCd, // 개인,조직 구분
        String sellDvCd, /*구분(판매구분코드) TODO: 컬럼 확인 필요*/
        String ogCd, /*소속(조직코드)*/
        String prtnrNo, /*파트너번호*/
        String prtnrKnm, /*판매자명(파트너한글명)*/
        String rsbDvCd, /*직급(직책구분코드)*/
        String brmgrPrtnrNo, /*지점장(지점장파트너번호)*/
        String cntrNoSn, /*계약번호(계약번호+계약일련번호)*/
        String pdNm, /*상품명*/
        String istDt, /*설치일자*/
        String lifCntrNo, /*상조계약번호(라이프계약번호)*/
        String lifPdNm, /*상조상품명(라이프상품명)*/
        String rcpdt, /*접수일(접수일자)*/
        String cntrDt, /*계약일(계약일자)*/
        String canDt, /*취소일(취소일자)*/
        BigDecimal sellFee, /*판매자수수료(판매수수료) TODO: 컬럼 확인 필요*/
        String totDsbOjDvCd, /*총지급대상(총지급대상구분코드)*/
        String lifCntrOcTn, /*회차(라이프계약발생회차)*/
        BigDecimal slOcAcuAmt, /*누적발생매출(매출발생누적금액)*/
        BigDecimal dpAcuAmt, /*누적입금(입금누적금액)*/
        String flpymTn, /*완납회차*/
        BigDecimal prdsb, /*기지급*/
        String cnfmYn, /*라이프확정(확정여부)*/
        String feeDsbYm, /*수수료지급월(수수료지급년월)*/
        String feeRedfYm, /*수수료되물림월(수수료되물림년월)*/
        String perfYm /*실적년월*/
    ) {}

    @ApiModel("WwdeaMutualAidFeeMgtDto-SearchMutualAidFeeSubRes")
    public record SearchMutualAidFeeSubRes(
        String dvCd, // 개인,조직 구분
        String sellDvCd, /*구분(판매구분코드) TODO: 컬럼 확인 필요*/
        String baseYm, /*발생월(기준년월)*/
        String ackmtPerfCt, /*건수(인정실적건수)*/
        BigDecimal ackmtPerfAmt, /*금액(인정실적금액)*/
        String ogCd, /*소속(조직코드)*/
        String prtnrNo, /*파트너번호*/
        String prtnrKnm, /*판매자명(파트너한글명)*/
        String rsbDvCd /*직급(직책구분코드)*/
    ) {}

    @Builder
    public record SearchTotalMutualAidFeeRes(
        PagingResult<SearchMutualAidFeeRes> mutualAidFee,
        PagingResult<SearchMutualAidFeeSubRes> mutualAidFeeSub
    ) {}

    @Builder
    public record SearchTotalMutualAidFeeForExcelDownloadRes(
        List<SearchMutualAidFeeRes> mutualAidFeeForExcelDownload,
        List<SearchMutualAidFeeSubRes> mutualAidFeeSubForExcelDownload
    ) {}

}
