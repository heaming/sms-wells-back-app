package com.kyowon.sms.wells.web.service.stock.dto;

import java.util.List;

import javax.validation.constraints.NotBlank;

import com.sds.sflex.common.utils.StringUtil;

import io.swagger.annotations.ApiModel;

public class WsnaManagerBsConsumableDto {

    @ApiModel(value = "WsnaManagerBsConsumableDto-SearchReq")
    public record SearchReq(
        String mngtYm, /* 관리년월 */
        List<String> bldCds /* 빌딩코드 리스트 */
    ) {}

    @ApiModel(value = "WsnaManagerBsConsumableDto-SearchRes")
    public record SearchRes(
        String bldCd, /* 빌딩코드 */
        String bldNm, /* 빌딩명 */
        String prtnrKnm, /* 파트너한글명 */
        String prtnrNo, /* 파트너번호 */
        String reqYn, /* 신청여부 */
        String ogCd, /* 조직코드 */
        String vstCstN, /* 방문계정수 */
        String bfsvcCsmbDdlvStatCd, /* BS소모품배부상태코드 */
        String wrfr, /* 정수기 */
        String bdtIndv, /* 비데(개인) */
        String bdtCrp, /* 비데(법인) */
        String arcleIndv, /* 공기청정기(개인) */
        String arcleCrp, /* 공기청정기(법인) */
        String wtrSftnr, /* 연수기 */
        String cffMchn, /* 커피머신 */
        String msgcr, /* 안마의자 */
        String dryr, /* 건조기 */
        String wash, /* 세탁기 */
        String ardrssr, /* 에어드레서 */
        String sscling, /* 삼성청소기 */
        List<String> fxnQtys, /* 고정품목갯수 리스트 */
        List<String> aplcQtys /* 신청품목갯수 리스트 */
    ) {}

    @ApiModel(value = "WsnaManagerBsConsumableDto-SearchItmRes")
    public record SearchItmRes(
        String bfsvcCsmbDdlvTpCd, /* BS소모품배부상태코드 */
        String fxnPdCd, /* 고정품목코드 */
        String fxnPckngUnit, /* 고정포장단위 */
        String fxnPdNm, /* 고정품목명 */
        String fxnSapMatCd, /* 고정SAP코드 */
        String aplcPdCd, /* 신청품목코드 */
        String aplcPckngUnit, /* 신청포장단위 */
        String aplcPdNm, /* 신청품목명 */
        String aplcSapMatCd, /* 신청SAP코드 */
        String qty /* 수량 */
    ) {}

    @ApiModel(value = "WsnaManagerBsConsumableDto-FindTmlmRes")
    public record FindTmlmRes(
        String bizStrtdt, /* 업무시작일자 */
        String bizStrtHh, /* 업무시작시간 */
        String bizEnddt, /* 업무종료일자 */
        String bizEndHh /* 업무종료시간 */
    ) {}

    @ApiModel(value = "WsnaManagerBsConsumableDto-FindTmlmRes")
    public record CreateTmlmReq(
        @NotBlank
        String mngtYm, /* 관리년월 */
        @NotBlank
        String bizStrtdt, /* 업무시작일자 */
        @NotBlank
        String bizStrtHh, /* 업무시작시간 */
        @NotBlank
        String bizEnddt, /* 업무종료일자 */
        @NotBlank
        String bizEndHh /* 업무종료시간 */
    ) {}

    @ApiModel(value = "WsnaManagerBsConsumableDto-CreateReq")
    public record CreateReq(
        @NotBlank
        String mngtYm, /* 관리년월 */
        @NotBlank
        String bfsvcCsmbDdlvOjCd, /* BS소모품배부대상코드 */
        @NotBlank
        String strWareNo, /* 입고창고번호(파트너번호 or 빌딩코드) */
        @NotBlank
        String csmbPdCd, /* 소모품상품코드 */
        @NotBlank
        String sapMatCd, /* SAP자재코드 */
        // @NotBlank
        String bfsvcCsmbDdlvQty, /* BS소모품배부수량 */
        @NotBlank
        String bfsvcCsmbDdlvStatCd /* BS소모품배부상태코드 */
    ) {
        public CreateReq {
            bfsvcCsmbDdlvQty = StringUtil.isEmpty(bfsvcCsmbDdlvQty) ? "0" : bfsvcCsmbDdlvQty;
        }
    }

    @ApiModel(value = "WsnaManagerBsConsumableDto-CreateOstrReq")
    public record CreateOstrReq(
        @NotBlank
        String mngtYm, /* 관리년월 */
        @NotBlank
        String strWareNo /* 입고창고번호(파트너번호 or 빌딩코드) */
    ) {}

    public record SearchLmQtyRes(
        String sapMatCd, /* SAP자재코드 */
        String bfsvcCsmbAplcLmQty /* BS소모품신청제한수량 */
    ) {}
}
