package com.kyowon.sms.wells.web.withdrawal.common.dvo;

import com.sds.sflex.system.config.annotation.DBEncField;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WwdaAutoTransferRealTimeAccountCheckDvo {

    private String acFntImpsCd; /* 계좌이체불능코드 */
    private String acFntImpsCdNm; /* 계좌이체불능코드명 */
    private String errCn; /* 오류내용 */
    private String bilCrtStatCd; /* 청구생성상태코드 */
    private String acFntRsCd; /* 계좌이체결과코드 */

    //TB_RVCL_AC_FNT_RTM_AK_APR_IZ-계좌이체실시간요청승인내역
    private Integer akSn; /* 요청일련번호 */
    private String copnDvCd; /* 법인격구분코드 */
    private String copnDvDrmVal; /* 법인격구분식별값 */
    private String acFntMtrDvCd; /* 계좌이체자료구분코드 */
    private String bnkCd; /* 은행코드 */
    @DBEncField
    private String acnoEncr; /* 계좌번호암호화 */
    private String achldrNm; /* 예금주명 */
}
