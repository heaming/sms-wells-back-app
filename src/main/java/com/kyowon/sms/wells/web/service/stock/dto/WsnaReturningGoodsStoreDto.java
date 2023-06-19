package com.kyowon.sms.wells.web.service.stock.dto;

import javax.validation.constraints.NotBlank;

import com.sds.sflex.system.config.validation.validator.ValidDate;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

public class WsnaReturningGoodsStoreDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    // 반품입고 관리 Search Request Dto
    @Builder
    @ApiModel("WsnaReturningGoodsStoreDto-SearchReq")
    public record SearchReq(
        String strWareDvCd,
        String strWareNoM,
        String strWareNoD,
        String stFnlVstFshDtFrom, /*처리일자From*/
        String edFnlVstFshDtTo, /*처리일자To*/
        String fnlItmGdCd, /*최종품목*/
        String itmPdCd, /*품목상품코드*/
        String itmKndCd, /*품목종류코드*/
        String svBizDclsfCd, /*서비스업무세분류코드*/
        String strConfYnCd, /*입고확인여부코드*/
        String stRtngdProcsTpCd, /*반품처리유형코드*/
        String stOstrConfDt /*확인일자*/

    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 반품입고 관리 Search Result Dto
    @ApiModel("WsnaReturningGoodsStoreDto-SearchRes")
    public record SearchRes(
        String cstSvAsnNo, /*고객서비스배정내역*/
        String sapCd, /*SAP코드*/
        String itmPdCd, /*품목상품코드*/
        String itmPdNm, /*품목상품명*/
        String istDt, /*설치일자*/
        String reqdDt, /*철거일자*/
        String vstFshDt, /*작업일자*/
        String rtngdConfYn, /*반품확인여부*/
        //        String col8, /*사용일자 TODO*/
        String useMths, /*사용개월*/
        String refurbishYn, /*리퍼여부*/
        String fnlItmGdCd, /*최종상품등급코드*/
        String useQty, /*사용수량*/
        String refrAsRcpYn, /*리퍼접수*/
        String cntrDtlNo, /*고객번호*/
        String cntrNo,
        String cntrSn,
        String itemGr,
        String rcgvpKnm, /*고객명*/
        String sellTpCd, /*판매유형코드*/
        String sellTpNm, /*판매유형명*/
        String referArtc, /*참고사항*/
        String stkrPrntYn, /*스티커출력여부*/
        String stkrRPrntYn, /*스티커 R출력여부*/
        String svBizDclsfCd,
        String svBizDclsfNm,
        String ostrConfDt1,
        String ostrConfDt,
        String ostrDt,
        String rtngdProcsTpCd,
        String rtngdProcsTpCd1,
        String rtngdRvpyProcsYn,
        String wkWareNo,
        String wkOstrSn,

        String rmkCn,
        String cntrNoNew,
        String barCd,
        String asLctNm,
        String asphnNm,
        String asCausNm,
        String svProcsCn,
        String ichrPrtnrNo,
        String empNm,
        String fstRgstUsrId,
        String fstRgstUserNm,
        String cnslMoCn,
        //        String col23,
        String prtnrKnm,
        String itemKnd,
        String badDvNm,
        String mgtUnt

    ) {}

    @ApiModel(value = "WsnaReturningGoodsStoreDto-FindItmOstrNoReq")
    public record FindItmOstrNoReq(
        String ostrTpCd, // 출고유형코드
        String ostrDt // 출고일자
    ) {}

    @ApiModel(value = "WsnaReturningGoodsStoreDto-FindItmStrNoReq")
    public record FindItmStrNoReq(
        String ostrTpCd, // 출고유형코드
        String ostrDt // 출고일자
    ) {}

    @ApiModel(value = "WsnaReturningGoodsStoreDto-FindOstrAkNoReq")
    public record FindOstrAkNoReq(
        String ostrAkTpCd, //출고요청유형코드
        String ostrAkDt //출고요청일자
    ) {}

    @ApiModel("WsnaReturningGoodsStoreDto-SaveReq")
    public record SaveReq(
        @NotBlank
        String rowState,
        String cstSvAsnNo,
        String itmPdCd,
        String itmKndCd,
        String stFnlVstFshDtFrom,
        String edFnlVstFshDtTo,
        String strWareDvCd,
        String strWareNoM,
        String strWareNoD,

        String useQty, /*수량*/
        String ostrConfDt, /*확인일자*/
        String rtngdProcsTpCd, /*반품처리유형코드*/
        String mgtUnt, /*관리단위*/
        String itemKnd, /*품목종류*/
        String WareNm, /*창고명*/
        String wkWareNo, /*작업창고번호*/
        String rtngdConfYn, /*반품확인여부*/
        String fnlItmGdCd, /*최종품목등급코드*/
        String itemGrNm,
        String itemGr,

        String itmOstrNo,
        String ostrTpCd,
        String ostrDt,

        String strTpCd,
        String strRgstDt,
        String itmStrNo,
        String rtngdRvpyProcsYn,
        String stkrPrntYn,
        String StkrRPrntYn,
        String cntrNo,
        String cntrSn,
        String wkOstrSn,
        String rmkCn

    ) {}

    @ApiModel("WsnaReturningGoodsStoreDto-SaveConfirmationReq")
    public record SaveConfirmationReq(
        @NotBlank
        String rowState,
        String cstSvAsnNo,
        String itmPdCd,
        String itmKndCd,
        String stFnlVstFshDtFrom,
        String edFnlVstFshDtTo,
        String strWareDvCd,
        String strWareNoM,
        String strWareNoD,

        String useQty, /*수량*/
        String ostrConfDt, /*확인일자*/
        String rtngdProcsTpCd, /*반품처리유형코드*/
        String mgtUnt, /*관리단위*/
        String itemKnd, /*품목종류*/
        String WareNm, /*창고명*/
        String wkWareNo, /*작업창고번호*/
        String rtngdConfYn, /*반품확인여부*/
        String fnlItmGdCd, /*최종품목등급코드*/
        String itemGrNm,
        String itemGr,

        String itmOstrNo,
        String ostrTpCd,
        String ostrDt,

        String strTpCd,
        String strRgstDt,
        String itmStrNo,
        String rtngdRvpyProcsYn,
        String stkrPrntYn,
        String StkrRPrntYn,
        String cntrNo,
        String cntrSn,
        String wkOstrSn,
        String rmkCn

    ) {}
}
