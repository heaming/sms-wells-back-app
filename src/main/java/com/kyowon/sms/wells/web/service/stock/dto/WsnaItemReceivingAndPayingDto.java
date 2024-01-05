package com.kyowon.sms.wells.web.service.stock.dto;

import java.math.BigDecimal;
import java.util.List;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

public class WsnaItemReceivingAndPayingDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    // 품목별수불현황 조회 Search Request Dto
    @Builder
    @ApiModel("WsnaReceiptsAndPaymentsDto-SearchReq")
    public record SearchReq(
        // 입고창고구분
        String strWareDvCd,
        // 입고상위창고
        String strWareNoM,
        // 입고창고
        String strWareNoD,
        // 창고상세구분
        String wareDtlDvCd,
        // 시작일자
        String stFromYmd,
        // 종료일자
        String edToYmd,
        // 품목구분
        String itmKnd,
        // 품목그룹
        String itmGrp,
        // 자재그룹
        String svMatGrpCd,
        // 품목리스트
        List<String> itmPdCds,
        // 등급
        String itmGdCd,
        // 사용여부
        String useYn,
        // 품목코드
        String itmPdCdFrom,
        // 시작 SAP코드
        String sapMatCdFrom,
        // 종료 SAP코드
        String sapMatCdTo,
        // SAP코드 리스트
        List<String> sapMatDpcts

    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 품목별수불현황 조회 Search Result Dto
    @ApiModel("WsnaReceiptsAndPaymentsDto-SearchRes")
    public record SearchRes(
        // 품목코드
        String itmPdCd,
        // SAP코드
        String sapMatCd,
        // 품목명
        String itmPdNm,
        // 기초재고수량
        BigDecimal basStocQty,
        // 구매입고수량
        BigDecimal prchsStrQty,
        // 정상입고수량
        BigDecimal nomStrQty,
        // 물량배정입고수량
        BigDecimal qomAsnStrQty,
        // 물량이동입고수량
        BigDecimal qomMmtStrQty,
        // 반품입고내부수량
        BigDecimal rtngdStrInsdQty,
        // 반품입고외부수량
        BigDecimal rtngdStrOtsdQty,
        // 기타입고수량
        BigDecimal etcStrQty,
        // 등급조정입고수량
        BigDecimal strCtrQty,
        // 재고실사입고수량
        BigDecimal cnfmPitmStrGapQty,
        // 입고수량 합계
        BigDecimal strQty,
        // 정상출고수량
        BigDecimal nomOstrQty,
        // 서비스센터 정상출고수량
        BigDecimal svcNomOstrQty,
        // 영업센터 정상출고수량
        BigDecimal sellNomOstrQty,
        // 물량배정출고수량
        BigDecimal qomAsnOstrQty,
        // 물량이동출고수량
        BigDecimal qomMmtOstrQty,
        // 반품출고내부수량
        BigDecimal rtngdOstrInsdQty,
        // 반품출고외부수량
        BigDecimal rtngdOstrOtsdQty,
        // 작업출고수량
        BigDecimal useQty,
        // 1년내 작업출고수량
        BigDecimal yearInUseQty,
        // 1년후 작업출고수량
        BigDecimal yearOutUseQty,
        // 리퍼출고수량
        BigDecimal refrOstrQty,
        // 판매출고수량
        BigDecimal sellOstrQty,
        // 폐기출고수량
        BigDecimal dsuOstrQty,
        // 기타출고수량
        BigDecimal etcOstrQty,
        // 등급조정출고수량
        BigDecimal ostrCtrQty,
        // 재고실사출고수량
        BigDecimal cnfmPitmOstrGapQty,
        // 출고수량 합계
        BigDecimal ostrQty,
        // 기말재고수량
        BigDecimal eotStocQty,
        // 이동재고수량
        BigDecimal mmtStocQty

    ) {}

    @ApiModel("WsnaReceiptsAndPaymentsDto-SearchDateReq")
    public record SearchDateReq(
        // 품목코드
        String itmPdCd,
        // 창고번호
        String wareNo,
        // 시작일자
        String strRgstFrom,
        // 종료일자
        String strRgstTo
    ) {}

    @ApiModel("WsnaReceiptsAndPaymentsDto-SearchDateRes")
    public record SearchDateRes(
        // 일자
        String ymd,
        // 기초재고수량
        BigDecimal basStocQty,
        // 구매입고수량
        BigDecimal prchsStrQty,
        // 정상입고수량
        BigDecimal nomStrQty,
        // 물량배정입고수량
        BigDecimal qomAsnStrQty,
        // 물량이동입고수량
        BigDecimal qomMmtStrQty,
        // 반품입고내부수량
        BigDecimal rtngdStrInsdQty,
        // 반품입고외부수량
        BigDecimal rtngdStrOtsdQty,
        // 기타입고수량
        BigDecimal etcStrQty,
        // 등급조정입고수량
        BigDecimal strCtrQty,
        // 재고실사입고수량
        BigDecimal cnfmPitmStrGapQty,
        // 정상출고수량
        BigDecimal nomOstrQty,
        // 서비스센터 정상출고수량
        BigDecimal svcNomOstrQty,
        // 영업센터 정상출고수량
        BigDecimal sellNomOstrQty,
        // 물량배정출고수량
        BigDecimal qomAsnOstrQty,
        // 물량이동출고수량
        BigDecimal qomMmtOstrQty,
        // 반품출고내부수량
        BigDecimal rtngdOstrInsdQty,
        // 반품출고외부수량
        BigDecimal rtngdOstrOtsdQty,
        // 작업출고수량
        BigDecimal useQty,
        // 리퍼출고수량
        BigDecimal refrOstrQty,
        // 판매출고수량
        BigDecimal sellOstrQty,
        // 폐기출고수량
        BigDecimal dsuOstrQty,
        // 기타출고수량
        BigDecimal etcOstrQty,
        // 등급조정수량
        BigDecimal ostrCtrQty,
        // 재고실사출고수량
        BigDecimal cnfmPitmOstrGapQty,
        // 기말재고수량
        BigDecimal eotStocQty,
        // 이동재고수량
        BigDecimal mmtStocQty

    ) {}
}
