package com.kyowon.sms.wells.web.service.stock.dto;

import javax.validation.constraints.NotBlank;

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
        String strWareDvCd, /*입고창고구분코드*/
        String strWareNoM, /*입고창고상위*/
        String strWareNoD, /*입고창고번호*/
        String stFnlVstFshDtFrom, /*처리일자From*/
        String edFnlVstFshDtTo, /*처리일자To*/
        String fnlItmGdCd, /*최종품목*/
        String itmPdCd, /*품목상품코드*/
        String itmKndCd, /*품목종류코드*/
        String svBizDclsfCd, /*서비스업무세분류코드*/
        String strConfYnCd, /*입고확인여부코드*/
        String stRtngdProcsTpCd, /*반품처리유형코드*/
        String stOstrConfDt, /*확인일자*/
        String chkErrorCheck, /*등급오류체크*/
        String barCode /*바코드필터링값*/

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
        String useDay, /*사용일수*/
        String useMths, /*사용개월*/
        String refurbishYn, /*리퍼여부*/
        String fnlItmGdCd, /*최종상품등급코드*/
        String useQty, /*사용수량*/
        String refrAsRcpYn, /*리퍼접수*/
        String cntrDtlNo, /*고객번호*/
        String cntrNo, /*계약번호*/
        String cntrSn, /*계약순번*/
        String rcgvpKnm, /*고객명*/
        String mngtUnitNm, /*관리단위*/
        String sellTpCd, /*판매유형코드*/
        String sellTpNm, /*판매유형명*/
        String referArtc, /*참고사항*/
        String stkrPrntYn, /*스티커출력여부*/
        String stkrRPrntYn, /*스티커 R출력여부*/
        String svBizDclsfCd, /*서비스업무세분류코드*/
        String svBizDclsfNm, /*서비스업무세분류명*/
        String ostrConfDt1, /*출고확인일자*/
        String ostrConfDt, /*출고확인일자*/
        String ostrDt, /*전산반품*/
        String rtngdProcsTpCd, /*반품처리유형코드*/
        String rtngdProcsTpCd1, /*반품처리유형코드*/
        String rtngdRvpyProcsYn, /*반품수불처리여부*/
        String wkWareNo, /*작업창고번호*/
        String wkOstrSn, /*작업출고순번*/
        String rmkCn, /*비고*/
        String cntrNoNew, /*신규계약번호*/
        String barCd, /*바코드*/
        String asLctNm, /*고장위치*/
        String asphnNm, /*고장현상*/
        String asCausNm, /*위치상세*/
        String svProcsCn, /*서비스처리내용*/
        String ichrPrtnrNo, /*담당엔지니어사번*/
        String empNm, /*담당엔지니어명*/
        String rcpIchrPrtnrNo, /*철거요청사번*/
        String fstRgstUserNm, /*철거요청자명*/
        String cnslMoCn, /*접수내역*/
        String prtnrKnm, /*접수자명*/

        String ogNm, /*조직명*/

        String rcstDv, /*접수자구분*/
        String itemKnd, /*품목구분*/
        String badDvNm, /*불량구분*/
        String mgtUnt, /*관리단위*/
        String errorCheck, /*등급오류*/
        String rtngdConfYn1, /*기존반품확인여부*/
        String itemGr /*상품그룹*/

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

    @ApiModel("WsnaReturningGoodsStoreDto-SaveReq")
    public record SaveReq(
        @NotBlank
        String rowState, /*행상태*/
        String cstSvAsnNo, /*고객요청번호*/
        String itmPdCd, /*품목상품코드*/
        String itmKnd, /*품목구분코드*/
        String stFnlVstFshDtFrom, /*처리일자FROM*/
        String edFnlVstFshDtTo, /*처리일자TO*/
        String strWareDvCd, /*입고창고구분코드*/
        String strWareNoM, /*입고창고상위*/
        String strWareNoD, /*입고창고*/

        String useQty, /*수량*/
        String ostrConfDt, /*확인일자*/
        String rtngdProcsTpCd, /*반품처리유형코드*/
        String mgtUnt, /*관리단위*/
        String itemKnd, /*품목종류*/
        String WareNm, /*창고명*/
        String wkWareNo, /*작업창고번호*/
        String rtngdConfYn, /*반품확인여부*/
        String fnlItmGdCd, /*최종품목등급코드*/

        String itmOstrNo, /*품목출고번호*/
        String ostrTpCd, /*출고구분코드*/
        String ostrDt, /*출고일자*/

        String strTpCd, /*입고구분코드*/
        String strRgstDt, /*입고등록일자*/
        String itmStrNo, /*품목입고번호*/
        String rtngdRvpyProcsYn, /*반품수불처리여부*/
        String stkrPrntYn, /*스티커출력여부*/
        String StkrRPrntYn, /*스티커R출력여부*/
        String cntrNo, /*계약번호*/
        String cntrSn, /*계약순번*/
        String wkOstrSn, /*작업출고순번*/
        String rmkCn /*비고*/

    ) {}

    @ApiModel("WsnaReturningGoodsStoreDto-SaveConfirmationReq")
    public record SaveConfirmationReq(
        @NotBlank
        String rowState, /*행상태*/
        String cstSvAsnNo, /*고객요청번호*/
        String itmPdCd, /*품목상품코드*/
        String itmKndCd, /*품목구분코드*/
        String stFnlVstFshDtFrom, /*처리일자FROM*/
        String edFnlVstFshDtTo, /*처리일자TO*/
        String strWareDvCd, /*입고창고구분코드*/
        String strWareNoM, /*입고창고상위*/
        String strWareNoD, /*입고창고*/

        String useQty, /*수량*/
        String ostrConfDt, /*확인일자*/
        String rtngdProcsTpCd, /*반품처리유형코드*/
        String mgtUnt, /*관리단위*/
        String itemKnd, /*품목종류*/
        String WareNm, /*창고명*/
        String wkWareNo, /*작업창고번호*/
        String rtngdConfYn, /*반품확인여부*/
        String fnlItmGdCd, /*최종품목등급코드*/

        String itmOstrNo, /*품목출고번호*/
        String ostrTpCd, /*출고구분코드*/
        String ostrDt, /*출고일자*/

        String strTpCd, /*입고구분코드*/
        String strRgstDt, /*입고등록일자*/
        String itmStrNo, /*품목입고번호*/
        String rtngdRvpyProcsYn, /*반품수불처리여부*/
        String stkrPrntYn, /*스티커출력여부*/
        String StkrRPrntYn, /*스티커R출력여부*/
        String cntrNo, /*계약번호*/
        String cntrSn, /*계약순번*/
        String wkOstrSn, /*작업출고순번*/
        String rmkCn /*비고*/

    ) {}

    @ApiModel("WsnaReturningGoodsStoreDto-SearchWareRes")
    public record SearchWareRes(
        String hgrWareNo /*상위창고번호*/
    ) {}
}
