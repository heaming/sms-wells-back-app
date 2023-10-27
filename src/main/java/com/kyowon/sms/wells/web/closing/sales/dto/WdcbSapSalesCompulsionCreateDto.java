package com.kyowon.sms.wells.web.closing.sales.dto;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;

public class WdcbSapSalesCompulsionCreateDto {
    // *********************************************************
    // Result Dto
    // *********************************************************
    // SAP자재명 조회 Search Result Dto
    @ApiModel("WdcbSapSalesCompulsionCreateDto-SearchSapMapNmRes")
    public record SearchSapMapNmRes(
        String sapMatCd, /*SAP자재코드*/
        String sapMatNm /*SAP자재명*/
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // SAP매출강제생성 Search Result Dto
    @ApiModel("WdcbSapSalesCompulsionCreateDto-SearchRes")
    public record SearchRes(
        String zsslrq, /*매출전표요청번호*/
        String zsslsq, /*일련번호*/
        String zsiodt, /*입출고일자*/
        String zsslty, /*매출유형*/
        String zsorty, /*주문유형*/
        String zsmtrl, /*SAP자재코드*/
        String zsmtrlNm, /*자재명*/
        String zsqntt, /*수량*/
        String zssamt, /*공급가*/
        String zsvamt, /*VAT*/
        String zsrtrn, /*반품여부*/
        String zsfree, /*무상여부*/
        String zsprft, /*사은품여부*/
        String zstaxg, /* 과/면세구분*/
        String zsblty, /*세금계산서발행기준*/
        String zsmngr, /*담당자코드*/
        String zsreal, /*수불여부*/
        String zssasl, /*저장위치*/
        String lggubn, /*구분*/
        String lgmgub, /*매출구분*/
        String lgjdte, /*입력일*/
        String transYn, /*전송여부*/
        String zsifdt, /*IF전송일*/
        String zssadt, /*SAP반영일*/
        String zsslhq, /*사업부서*/
        String zsdept, /*조직정보*/
        String zscust, /*고객정보ID*/
        String updActDt, /*수정일시*/
        String updActId, /*수정자*/
        String regActDt, /*등록일시*/
        String regActId /*등록자*/
    ) {}

    // *********************************************************
    // Save Dto
    // *********************************************************
    // SAP매출강제생성 Save Dto
    @ApiModel(value = "WdcbSapSalesCompulsionCreateDto-SaveReq")
    public record SaveReq(
        @NotBlank
        String rowState,
        String baseYm, /*매출월*/
        String zsslrq, /*매출전표요청번호*/
        String zsslsq, /*일련번호*/
        String zsiodt, /*입출고일자*/
        String zsslty, /*매출유형*/
        String zsorty, /*주문유형*/
        String zsmtrl, /*SAP자재코드*/
        String zsmtrlNm, /*자재명*/
        Integer zsqntt, /*수량*/
        Integer zssamt, /*공급가*/
        Integer zsvamt, /*VAT*/
        String zsrtrn, /*반품여부*/
        String zsfree, /*무상여부*/
        String zsprft, /*사은품여부*/
        String zstaxg, /* 과/면세구분*/
        String zsblty, /*세금계산서발행기준*/
        String zsmngr, /*담당자코드*/
        String zsreal, /*수불여부*/
        String zssasl, /*저장위치*/
        String lggubn, /*구분*/
        String lgmgub, /*매출구분*/
        String lgjdte, /*입력일*/
        String transYn, /*전송여부*/
        String zsifdt, /*IF전송일*/
        String zssadt, /*SAP반영일*/
        String zsslhq, /*사업부서*/
        String zsdept, /*조직정보*/
        String zscust, /*고객정보ID*/
        String updActDt, /*수정일시*/
        String updActId, /*수정자*/
        String regActDt, /*등록일시*/
        String regActId, /*등록자*/
        String fnlMdfcDtm /*최종수정시간*/
    ) {}

    @ApiModel("WdcbSapSalesCompulsionCreateDto-RemoveReq")
    public record RemoveReq(
        @NotBlank
        String zsslrq, /*매출전표요청번호*/
        @NotBlank
        String zsslsq, /*일련번호*/
        String fnlMdfcDtm /*최종수정시간*/
    ) {}
}
