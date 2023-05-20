package com.kyowon.sms.wells.web.contract.ordermgmt.dvo;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WctaContractDtlDvo {
    private String cntrNo; /* 계약번호 */
    private Integer cntrSn; /* 계약일련번호 */
    private String basePdCd; /* 기준상품코드 */
    private String hgrPdCd; /* 상위상품코드 */
    private Long pdQty; /* 상품수량 */
    private String sellTpCd; /* 판매유형코드 */
    private String sellTpDtlCd; /* 판매유형상세코드 */
    private String cntrDtlStatCd; /* 계약상세상태코드 */
    private String cntrPtrmUnitCd; /* 계약기간단위코드 */
    private Long cntrPtrm; /* 계약기간 */
    private String cntrPdStrtdt; /* 계약상품시작일자 */
    private String cntrPdEnddt; /* 계약상품종료일자 */
    private String stplPtrmUnitCd; /* 약정기간단위코드 */
    private Long stplPtrm; /* 약정기간 */
    private String svPtrmUnitCd; /* 서비스기간단위코드 */
    private Long svPrd; /* 서비스주기 */
    private String cntrwTpCd; /* 계약서유형코드 */
    private String blgCrpCd; /* 소속법인코드 */
    private String rveCrpCd; /* 수납법인코드 */
    private String coCd; /* 회사코드 */
    private String booSellTpCd; /* 예약판매유형코드 */
    private String pdGdCd; /* 상품등급코드 */
    private String pdHclsfId; /* 상품대분류ID */
    private String pdMclsfId; /* 상품중분류ID */
    private String pdLclsfId; /* 상품소분류ID */
    private String pdDclsfId; /* 상품세분류ID */
    private String sellDscDvCd; /* 판매할인구분코드 */
    private String sellDscrCd; /* 판매할인율코드 */
    private Long sellDscCtrAmt; /* 판매할인조정금액 */
    private String sellDscTpCd; /* 판매할인유형코드 */
    private String stlmTpCd; /* 결제유형코드 */
    private String crncyDvCd; /* 통화구분코드 */
    private BigDecimal apyExcr; /* 적용환율 */
    private Long pdBaseAmt; /* 상품기준금액 */
    private Long sellAmt; /* 판매금액 */
    private Long dscAmt; /* 할인금액 */
    private Long fnlAmt; /* 최종금액 */
    private Long vat; /* 부가가치세 */
    private Long cntrAmt; /* 계약금액 */
    private Long cntramDscAmt; /* 계약금할인금액 */
    private Long istmMcn; /* 할부개월수 */
    private Long istmPcamAmt; /* 할부원금금액 */
    private Long istmIntAmt; /* 할부이자금액 */
    private Long mmIstmAmt; /* 월할부금액 */
    private Long crpUcAmt; /* 법인미수금액 */
    private Long sellFee; /* 판매수수료 */
    private Long cntrTam; /* 계약총액 */
    private BigDecimal ackmtPerfRt; /* 인정실적율 */
    private Long ackmtPerfAmt; /* 인정실적금액 */
    private Long cvtPerfAmt; /* 환산실적금액 */
    private BigDecimal feeAckmtCt; /* 수수료인정건수 */
    private Long feeAckmtBaseAmt; /* 수수료인정기준금액 */
    private String feeFxamYn; /* 수수료정액여부 */
    private String sppDuedt; /* 배송예정일자 */
    private String resubYn; /* 재구독여부 */
    private String rstlYn; /* 재약정여부 */
    private String frisuYn; /* 무상여부 */
    private String frisuDsbTpCd; /* 무상지급유형코드 */
    private String txinvPblOjYn; /* 세금계산서발행대상여부 */
    private String alncmpCd; /* 제휴사코드 */
    private String alncmpCntrDrmVal; /* 제휴사계약식별값 */
    private String smtplId; /* 스마트플랜ID */
    private Integer smtplSn; /* 스마트플랜일련번호 */
    private String cttRsCd; /* 컨택결과코드 */
    private String cttPsicId; /* 컨택담당자ID */
    private String bfOrdNo; /* 이전주문번호 */
    private String cntrChRcpId; /* 계약변경접수ID */
    private Integer cntrChSn; /* 계약변경일련번호 */
    private String cntrChDtlAkCn; /* 계약변경상세요청내용 */
    private String dtaDlYn; /* 데이터삭제여부 */

    private List<WctaContractPrtnrRelDvo> prtnrRels;
    private List<WctaContractCstRelDvo> cstRels;
    private List<WctaContractHsmtrlDtlDvo> hsmtrlDtls;
    private List<WctaContractPdRelDvo> pdRels;
    private List<WctaContractPmotIzDvo> pmotIzs;
    private List<WctaFgptRcpIzDvo> fgptRcpIzs;
    private List<WctaContractPrcCmptIzDvo> prcCmptIzs;
    private List<WctaContractStlmRelDvo> stlmRels;
    private WctaContractAdrRelDvo adrRels;

    /*STEP2*/
    private String mclsfVal;
    private String lclsfVal;
    private String pdCd;
    private String pdClsfNm;
    private String pdNm;
    private String pdChip1;
    private String pdChip2;
    private String lrnnLvGrpDvCd;
    @Setter(AccessLevel.NONE)
    private String lrnnStrtRqdt;
    private Long suscMm;
    private String lrnnStrtLvCd;
    private String lrnnEnddt;
    private Long ackmtAmt;
    private BigDecimal ackmtRt;
    private String pdPrcFnlDtlId;
    private String fxamFxrtDvCd;
    private Integer verSn;
    private Long ctrVal;
    private String pdPrcId;
    List<WctaContractRegStep2Dvo.PdDetailDvo> suscMms;
    List<WctaContractRegStep2Dvo.PdDetailDvo> lrnnLvs;
    List<WctaContractRegStep2Dvo.PdDetailDvo> strtLvs;

    /*STEP3*/
    private String mlgYn; /*마일리지 사용여부*/
    private Long cntram; /*계약금결제금액*/

    public void setLrnnStrtRqdt(String lrnnStrtRqdt) {
        if (StringUtils.isNotEmpty(lrnnStrtRqdt)) {
            this.lrnnStrtRqdt = 6 == lrnnStrtRqdt.length() ? lrnnStrtRqdt.concat("01") : lrnnStrtRqdt;
        }
    }
}
