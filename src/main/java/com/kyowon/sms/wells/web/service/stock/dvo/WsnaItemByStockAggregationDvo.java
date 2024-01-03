package com.kyowon.sms.wells.web.service.stock.dvo;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * W-SV-U-0140M01 품목별 재고 집계 dvo
 * </pre>
 *
 * @author SaeRomI.Kim
 * @since 2023-08-08
 */

@Getter
@Setter
public class WsnaItemByStockAggregationDvo {

    // 기준일자
    private String baseDt;

    // 창고구분
    private String wareDvCd;
    // 재고유형
    private String mgtTypCd;
    // 품목종류
    private String itmKndCd;
    // 품목그룹
    private String itmGrpCd;
    // 품목코드 리스트
    private List<String> itmPdCds;
    // 품목그룹
    private String itmGdCd;
    // 사용여부
    private String useYn;
    // 자재그룹
    private String svMatGrpCd;
    // 창고유형
    private String wareTpCd;
    // 품목코드
    private String itmPdCd;
    // 시작SAP코드
    private String strtSapCd;
    // 종료SAP코드
    private String endSapCd;

    // 중수리자재여부
    private String commGb;
    // 기초자재여부
    private String baseGb;
    // 회전율대상여부
    private String turnoverGb;

    // PIVOT 창고번호 조건
    private String wareNoInStr;
    // PIVOT 창고번호 필드
    private String wareNoFields;

    // OFFSET
    private Integer offSet;
    // FETCH SIZE
    private Integer size;
}
