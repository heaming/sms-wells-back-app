package com.kyowon.sms.wells.web.closing.performance.dto;

import io.swagger.annotations.ApiModel;

public class WdccSalesBondDto {

    /**
     * 목록 조회 요청
     * @param slClYm 기준월
     * @param agrgDv 집계구분(1차 작업 시 설정 정보)
     * @param sellTpCd 판매유형
     * @param sellTpDtlCd 판매유형상세
     * @param sellChnlDtl 판매채널상세코드(1차 작업 시 설정 정보)
     * @param sapPdDvCd SAP상품구분코드
     * @param cntrNo 계약번호
     * @param cntrSn 계약상세번호
     */
    @ApiModel(value = "WdccDelinquentDto-SearchReq")
    public record SearchReq(
        String slClYm,
        String agrgDv,
        String sellTpCd,
        String sellTpDtlCd,
        String sellChnlDtl,
        String sapPdDvCd,
        String cntrNo,
        String cntrSn
    ) {}
    @ApiModel(value = "WdccDelinquentDto-SearchRes")
    public record SearchRes(
        String slClYm,
        String slClDt,
        String sellTpCd,
        String sellTpCdNm,
        String sellTpDtlCd,
        String sellTpDtlCdNm,
        String sapPdDvCd,
        String sapPdAtcNm,
        String cntrNo,
        String cstNo,
        String cstKnm,
        String pdCd,
        String pdNm,
        String slRcogDt,
        String preTotUcAmt,
        String preUcAmt1,
        String preUcAmt2,
        String nomSlAmt,
        String nomSlAmt1,
        String nomSlAmt2,
        String nomSlAmt3,
        String nomSlAmt4,
        String nomSlAmt5,
        String nomSlAmt6,
        String slCanAmt,
        String totSlAmt,
        String totDpAmt,
        String slBndAlrpyAmt,
        String totUcBlam,
        String ucBlam1,
        String ucBlam2,
        String borRemAmt,
        String borRemAmt1,
        String borRemAmt2,
        String borAdjAmt,
        String borAdjAmt1,
        String borAdjAmt2,
        String borAdjAmt3,
        String dpCngAmt,
        String dpCngAmt1,
        String dfaProcsAmt,
        String slClYm2,
        String btdDlqAddAmt,
        String thmOcDlqAddAmt,
        String thmCtrDlqAddAmt,
        String thmDlqAddDpSumAmt,
        String thmDlqAddRfndSumAmt,
        String eotDlqAddAmt,
        String w1Am011,
        String w1Am012,
        String w1Am013,
        String oriSlAmt,
        String itrSlAmt,
        String svcSlAmt,
        String slAdjAmt,
        String w1Am081,
        String w1Am082,
        String w1Am09,
        String w1Am10,
        String w1Am50,
        String w1Am121,
        String w1Am122,
        String w1Am123,
        String canSlAmt,
        String w1Am05,
        String w1Am07,
        String w1Am08,
        String feeSlAmt,
        String boutNorSlAmt,
        String boutCanSlAmt,
        String cntrDpAmt,
        String instDpAmt,
        String eotUcAmt,
        String crpUcAmt,
        String boutDpAmt,
        String etcDpAmt,
        String interContNomSlAmt,
        String interContCanSlAmt,
        String interContDpAmt,
        String interContDpAmt1,
        String interContDpAmt2,
        String bfThmUcBlam,
        String bfEotDlqAddAmt,
        String bfEotBorAmt,
        String w1Am191,
        String w1Am192,
        String w1Am193,
        String w1Am194,
        String w1Am195,
        String w1Am196,
        String thmUcBlam,
        String eotBorAmt
    ) {}
}
