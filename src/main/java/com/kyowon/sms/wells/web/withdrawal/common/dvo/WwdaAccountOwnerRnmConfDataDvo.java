package com.kyowon.sms.wells.web.withdrawal.common.dvo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WwdaAccountOwnerRnmConfDataDvo {

    private String acno; // (계좌번호) = 복호화(ACNO_ENCR(계좌번호암호화))
    private String depsPrsnNm; // (예금주명) = ""
    private String hndlBank; // (취급은행) = substring(TB_RVDW_FNIT_CD.금융기관대표코드, 2, 2) // 뒤 2자리
    private String hndlBankBrnc; // (취급은행지점) = "0000" 
    private String dealAmt; // (거래금액) = "100"
    private String dpstRqstPrsnNm; // (입금의뢰인명) = "교원"
    private String chckamt; // (수표금액) = ""
    private String cmsCd; // (CMS_CD) = ""
    private String blan1; // (Filler) = ""
    private String nwHndlBank; // (신 취급은행) = TB_RVCL_AC_FNT_RTM_AK_APR_IZ.BNK_CD(은행코드)
    private String blan2; // (Filler) = ""
}
