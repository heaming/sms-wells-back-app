package com.kyowon.sms.wells.web.service.stock.dto;

import io.swagger.annotations.ApiModel;

import javax.validation.constraints.NotBlank;

/**
 *
 * <pre>
 * W-SV-U-0117M01 출고요청 관리
 * </pre>
 *
 * @author songtaesung
 * @since 2022.12.26
 */
public class WsnaOutOfStorageAskMngtDto {

    @ApiModel(value = "WsnaOutOfStorageAskMngtDto-SearchReq")
    public record SearchReq(
        String strOjWareNo, // 출고요청창고 = 입고대상창고번호
        String ostrAkTpCd, // 출고요청유형코드
        @NotBlank
        String startStrHopDt, // 입고희망일자 시작일
        @NotBlank
        String endStrHopDt, // 입고희망일자 종료일
        @NotBlank
        String wareDvCd, // 출고요청 접수창고
        String ostrOjWareNo, /*출고대상창고*/
        String ostrAkNo /*출고요청번호*/
    ) {}

    @ApiModel(value = "WsnaOutOfStorageAskMngtDto-WarehouseReq")
    public record WarehouseReq(
        String apyYm
    ) {}

    @ApiModel(value = "WsnaOutOfStorageAskMngtDto-SearchRes")
    public record SearchRes(
        String ostrAkNo, // 출고요청번호
        String ostrAkTpCd, // 출고요청유형코드
        String ostrAkTpNm, // 출고요청유형명
        String strHopDt, // 입고희망일자
        String rectOstrDt, // 최근출고일자
        String wareNm, // 창고명
        String ostrOjWareNo, //출고대상창고번호
        String ostrAkRgstDt, //출고요청등록일자
        String strOjWareNo //입고대상창고번호
    ) {}

    @ApiModel(value = "WsnaOutOfStorageAskMngtDto-OutOfRes")
    public record OutOfRes(
        String sapCd, /*SAP코드*/
        String itmPdCd, /*품목상품코드*/
        String itmPdNm, /*품목상품명*/
        String ostrAkNo, /*출고요청번호*/
        String ostrAkSn, /*출고요청순번*/
        String ostrAkTpCd, /*출고요청유형코드*/
        String strHopDt, /*입고희망일자*/
        String mngtUnitCd, /*관리단위코드*/
        String boxUnitQty, /*박스단위수량*/
        String itmGdCd, /*품목등급코드*/
        String ostrAkQty, /*출고요청수량*/
        String ostrCnfmQty, /*출고확정수량*/
        String rmkCn, /*비고*/
        String rectOstrDt, /*최근출고일자*/
        String ostrWareMngtPrtnrNo, /*출고창고관리파트너번호*/
        String ostrOjWareNo, /*출고대상창고번호*/
        String strOjWareNo, /*입고대상창고번호*/
        String itmKnd, /*품목종류*/
        String itmKndNm, /*품목종류명*/
        String imgUrl, /*IMGURL*/
        String ostrAkWareDvCd, /*출고요청창고구분코드*/
        String wareMngtPrtnrNo, /*창고관리파트너번호*/
        int warehouseQty, /*재고*/
        int centerQty, /*센터수량*/
        int indiQty, /*개인수량*/
        int useQty, /*당월수량*/
        int baseStocQty, /*기준재고수량*/
        int sftStocQty /*안전재고수량*/

    ) {}

    @ApiModel(value = "WsnaOutOfStorageAskMngtDto-FindReq")
    public record FindReq(
        String ostrAkNo // 출고요청창고관리번호
    ) {}

    @ApiModel(value = "WsnaOutOfStorageAskMngtDto-FindOstrAkNoReq")
    public record FindOstrAkNoReq(
        String ostrAkRgstDt, //출고요청등록일자
        String ostrAkTpCd, //출고요청구분코드
        String strOjWareNo //입고대상창고번호
    ) {}
    @ApiModel(value = "WsnaOutOfStorageAskMngtDto-FindRes")
    public record FindRes(
        String ostrAkNo, //출고요청번호
        String ostrAkTpCd, // 출고요청유형
        String strOjWareNo, // 입고대상창고번호
        String strOjWareNm, // 입고대상창고명
        String strOjStckMgr, // 입고대상창고파트너
        String ostrAkRgstDt, //출고요청등록일자
        String strHopDt, //입고희망일자
        String ostrOjWareNo, //출고대상창고번호
        String ostrOjWareNm, //출고대상창고명
        String ostrOjStckMgr, //출고대상창고파트너
        String ovivTpCd, // 배차유형코드(삭제가능성O)
        String ostrItmNo // 품목코드
    ) {}

    @ApiModel(value = "WsnaOutOfStorageAskMngtDto-SearchOstrObjectWarehouseReq")
    public record SearchOstrObjectWarehouseReq(
        String apyYm, // 입고희망일
        String wareDvCd, // 창고구분코드(출고요청창고)
        String wareIchrNo // 창고담당자번호(출고요청창고)
    ) {}

    @ApiModel(value = "WsnaOutOfStorageAskMngtDto-SearchOstrObjectWarehouseRes")
    public record SearchOstrObjectWarehouseRes(
        String codeId, // 창고번호(출고대상)
        String codeName, // 창고명(출고대상)
        String wareMngtPrtnrNo // 창고관리파트너번호(출고대상)
    ) {}

    @ApiModel(value = "WsnaOutOfStorageAskMngtDto-RemoveReq")
    public record RemoveReq(
        String ostrAkNo, // 출고요청번호
        String ostrAkSn // 출고요청일련번호
    ) {}

    @ApiModel(value = "WsnaOutOfStorageAskMngtDto-SaveReq")
    public record SaveReq(
        String rowState,
        String sapCd, //SAP자재코드
        String itmPdCd, // 품목상품코드
        String ostrAkTpCd,
        String strOjWareNo,
        String ostrAkNo, // 출고요청번호
        String ostrAkSn, //출고요청일련번호
        String fstRgstDtm, // 최초등록일시
        String strHopDt, // 입고희망일자
        String ostrAkRgstDt, //출고요청등록일자
        String itmCd, // 품목코드
        String itmKnd, // 품목종류
        String itmNm, // 상품약어명
        String ostrAkWareDvCd, // 출고요청창고구분코드
        String wareMngtPrtnrNo, // 창고관리파트너번호
        String ostrOjWareDvCd, // 출고대상창고구분코드
        String ostrOjWareNo, // 출고대상창고번호
        String ostrWareMngtPrtnrNo, // 출고창고관리파트너번호
        String mngtUnitCd, // 관리단위코드
        String ovivTpCd,
        int boxUnitQty, // 박스단위수량
        String itmGdCd, // 품목등급코드
        int onQty, //
        int ostrAkQty, // 출고요청수량
        int ostrCnfmQty, // 출고확정수량
        String rmkCn, // 비고내용
        String rectOstrDt, // 최근출고일자
        int ostrAggQty, // 출고누계수량
        int warehouseQty, // 재고
        int baseStocQty, // 기준재고수량
        int sftStocQty, // 안전재고수량
        int useQty, // 당월수량
        int centerQty, // 센터수량
        int indiQty, // 개인수량
        String imgUrl, // 이미지첨부파일ID
        int cfrmCnt // 방문확정수량

    ) {}
}
