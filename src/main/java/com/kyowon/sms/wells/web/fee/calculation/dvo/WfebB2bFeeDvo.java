package com.kyowon.sms.wells.web.fee.calculation.dvo;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class WfebB2bFeeDvo {

    private String baseYm;                /* 기준년월 */
    private String perfYm;                /* 실적년월 */
    private String ojDsbYm;               /* 대상지급년월 */
    private String coCd;                  /* 회사코드 */
    private String ogTpCd;                /* 조직유형코드 */
    private String prtnrNo;               /* 파트너번호 */
    private String feeCd;                 /* 수수료코드 */
    private String dtaCrtFeeCd;           /* 데이터생성수수료코드 */
    private String feeTcntDvCd;           /* 수수료차수구분코드 */
    private String spmtDsbDvCd;           /* 추가지급구분코드 */
    private String feeCalcTpCd;           /* 수수료계산유형코드 */
    private Long feeCalcAmt;              /* 수수료계산금액 */
    private Long feeCtrCnfmAmt;           /* 수수료조정확정금액 */
    private String feeCtrRsonCn;          /* 수수료조정사유내용 */

    private String feeCtrOgTpCd;          /* 수수료조정조직유형코드 */
    private String feeCtrPrtnrNo;         /* 수수료조정파트너번호 */
    private BigDecimal feeCalcVarbVal;    /* 수수료계산변수값 */
    private String fnlFeeYn;              /* 최종수수료여부 */
    private String dtaDlYn;               /* 데이터삭제여부 */

}
