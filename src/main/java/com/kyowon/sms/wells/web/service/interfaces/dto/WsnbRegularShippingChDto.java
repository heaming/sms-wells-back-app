package com.kyowon.sms.wells.web.service.interfaces.dto;

import io.swagger.annotations.ApiModel;

public class WsnbRegularShippingChDto {

    @ApiModel(value = "WsnbRegularShippingChDto-SaveReq")
    public record SaveReq(
        String cntrNo,
        String cntrSn,
        String csmrSer,
        String reqGb,
        String reqDt,
        String saleCd,
        String reqSaleCd,
        String partList,
        String dataStus,
        String userId
    ) {}

    @ApiModel(value = "WsnbRegularShippingChDto-SaveRes")
    public record SaveRes(
        String result,
        String msg
    ) {}

    /**
    * 배송변경접수기본(TB_SSSO_SPP_CH_RCP_BAS)
    *      - 배송:Spp, 변경: Ch, 접수: Rcp
    */
    /*@ApiModel(value = "WsnbRegularShippingChDto-SaveSppChRcpRes")
    public record SaveSppChRcpRes(
        String sppChRcpId, // 배송변경접수ID
        String sppChTpCd, // 배송변경유형코드
        String chRqrDvCd, // 변경요청자구분코드
        String cntrNo, // 계약번호
        String cntrCstNo, // 계약고객번호
        String cntrCstRelTpCd, // 계약고객관계유형코드
        String chRqrNm, // 변경요청자명
        String chRcpDtm, // 변경접수일시
        String chRcstDvCd, // 변경접수자구분코드
        String chRcpUsrId, // 변경접수사용자ID
        String chRcpDtlRsonCd, // 변경접수상세사유코드
        String chRcpCn, // 변경접수내용
        String chApyStrtdt, // 변경적용시작일자
        String chApyEnddt, // 변경적용종료일자
        String sppMthdCd, // 배송방식코드
        String sppChPrgsStatCd, // 배송변경진행상태코드
        String cnrSppYn, // 센터배송여부
        String docId, // 문서ID
        String dtaDlYn //데이터삭제여부
    ) {}*/

    /**
    * 배송변경접수변경이력(TB_SSSO_SPP_CH_RCCH_HIST)
    *      - 배송:Spp, 변경: Ch, 접수: Rcp, 이력: Hist
    */
    @ApiModel(value = "WsnbRegularShippingChDto-SaveSppChRcpHistReq")
    public record SaveSppChRcpHistReq(
        String cntrNo,
        String cntrSn,
        String dataStus,
        String reqGb, // P_REQ_GB = LCGUBN
        String reqDt // P_REQ_DT = LCCHGT
    ) {}

    /**
    * 배송변경접수변경상세(TB_SSSO_SPP_CH_RCP_DTL)
    *      - 배송:Spp, 변경: Ch, 접수: Rcp, 상세: Dtl
    */
    @ApiModel(value = "WsnbRegularShippingChDto-SaveSppChRcpDtlReq")
    public record SaveSppChRcpDtlReq(
        String cntrNo,
        String cntrSn,
        String reqGb, // P_REQ_GB = LCGUBN
        String reqDt, // P_REQ_DT = LCCHGT
        int SppChSn

    ) {}

    /**
    * 배송변경접수기본(TB_SSSO_SPP_CH_RCP_BAS)
    *      - 배송:Spp, 변경: Ch, 접수: Rcp, 기본: Bas
    */
    @ApiModel(value = "WsnbRegularShippingChDto-SearchSppChRcpBasReq")
    public record SearchSppChRcpBasReq(
        String cntrNo,
        String cntrSn,
        String reqGb, // P_REQ_GB = LCGUBN
        String reqDt // P_REQ_DT = LCCHGT
    ) {}

    /**
    * 배송변경접수기본(TB_SSSO_SPP_CH_RCP_BAS)
    *      - 배송:Spp, 변경: Ch, 접수: Rcp, 기본: Bas
    */
    @ApiModel(value = "WsnbRegularShippingChDto-SaveSppChRcpBasReq")
    public record SaveSppChRcpBasReq(
        String cntrNo,
        String cntrSn,
        String reqGb, // P_REQ_GB = LCGUBN
        String reqDt // P_REQ_DT = LCCHGT
    ) {}
}
