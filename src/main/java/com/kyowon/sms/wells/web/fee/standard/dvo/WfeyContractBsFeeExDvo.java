package com.kyowon.sms.wells.web.fee.standard.dvo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WfeyContractBsFeeExDvo {
    private String cntrNo;            /* 계약번호 */
    private String cntrSn;           /* 계약일련번호 */
    private String cntrDtlSn; /* 계약번호-계약일련번호 */
    private String cntorNm; /* 계약자명 */
    private String basePdCd; /* 상품 */
    private String basePdNm;
    private String vstMcn;           /* 방문개월수 */
    private String baseChTcnt;       /* 기준변경차수 */
    private String svFeePdDvCd;       /* 서비스수수료상품구분코드 */
    private String svFeeBaseAmt;        /* 서비스수수료기준금액 */
    private String feeFxamYn;         /* 수수료정액여부 */
    private String apyStrtYm;         /* 적용시작년월 */
    private String apyEndYm;          /* 적용종료년월 */
    private String feeCtrRsonCn;      /* 수수료조정사유내용 */
    private String dtaDlYn;        /* 데이터삭제여부 */
}
