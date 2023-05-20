package com.kyowon.sms.wells.web.contract.ordermgmt.dvo;

import java.util.List;

import com.kyowon.sms.common.web.closing.mileage.dvo.ZdceMileageRestAmountDvo;
import com.sds.sflex.system.config.annotation.DBDecField;
import com.sds.sflex.system.config.annotation.DBEncField;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WctaContractRegStep3Dvo {
    private String cntrNo;
    private WctaContractBasDvo bas; /*계약기본*/
    private List<WctaContractDtlDvo> dtls; /*계약상세*/
    private String rcgvpKnm;
    private String cralLocaraTno;
    @DBEncField
    @DBDecField
    private String mexnoEncr;
    private String cralIdvTno;
    private String zip;
    private String adr;
    private String adrDtl;
    private String adrDvCd;
    private String adrId;
    private String sppDuedt;
    private boolean isIncludeCocn; /*전집포함여부*/
    private String sppMthdTpCd;
    private String cntrtRelCd; /*계약자와의관계*/
    private String copnDvCd; /*법인격구분코드*/
    private String stlmTpCd; /*결제유형*/
    private String cntramDpTpCd; /*계약금(일시금)결제방법*/
    private String intamDpTpCd; /*할부금결제방법*/
    private String idvIstmYn; /*개별할부여부(세부설정체크)*/
    private Long istmStlmAmt; /*할부인 경우 할부금액*/
    private Long istmMcn; /*할부개월*/
    private List<ZdceMileageRestAmountDvo> mlgs; /*마일리지*/
}
