package com.kyowon.sms.wells.web.service.stock.dto;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

public class WsnaChangeOfRentalStatusDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    // 렌탈 상태변경 현황 Search Request Dto
    @Builder
    @ApiModel("WsnaChangeOfRentalStatusDto-SearchReq")
    public record SearchReq(
        String itmGdCd, // 품목등급코드
        String itmPdCd, // 품목상품코드
        String inqrYm, //
        String rentalRtngdProcsTp, // 렌탈상태구분
        String svnCd //창고번호

    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 렌탈 상태변경 현황 Search Result Dto
    @ApiModel("WsnaChangeOfRentalStatusDto-SearchRes")
    public record SearchRes(
        String svpdSapCd, //sap코드
        String svpdPdCd, //상품코드
        String svpdNmAbbr1, //상품약어명
        String istDt, //설치일자
        String fnlVstFshDt, //작업일자
        String reqdDt, //철거일자
        String fnlItmGdCd, //최종상품등급코드
        String deptNm, //부서명
        String useQty, //사용수량
        String cntrDtlNo, //계약번호
        String rcgvpKnm, //고객명
        String ostrConfDt, //확인일자
        String ostrDt, //전산반품일자
        String rtngdProcsTpCd, //반품처리유형코드
        String rmkCn, //비고
        String rentalAssetStat, //랜탈관련상태값
        String cntrNo, //고객번호
        String cntrSn, // 고객순번
        String rtngdRvpyProcsYn, //반품수불처리여부
        String hgrWareNo, //상위창고번호
        String factoryDisposalGb, // 물류폐기, 공장폐기 임시구분
        String svpdItemGr // 상품구분
    ) {}
    @ApiModel("WsnaChangeOfRentalStatusDto-SearchItmPdCdRes")
    public record SearchItmPdCdRes(
        String codeId, //코드ID
        String codeName //코드NAME
    ) {}

    @ApiModel("WsnaChangeOfRentalStatusDto-SearchWarehouseReq")
    public record SearchWarehouseReq(
        String inqrYm //조회년월
    ) {}

    @ApiModel("WsnaChangeOfRentalStatusDto-SearchWarehouseRes")
    public record SearchWarehouseRes(
        String codeId, //코드ID
        String codeName //코드NAME
    ) {}
}
