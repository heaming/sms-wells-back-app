package com.kyowon.sms.wells.web.service.interfaces.dto;

import javax.validation.constraints.NotBlank;

import org.apache.commons.lang.StringUtils;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sds.sflex.common.utils.DbEncUtil;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

public class WsniWealthContractDetailsDto {
    @Builder
    @ApiModel(value = "WsniWealthContractDetailsDto-FindReq")
    public record FindReq(
        @NotBlank
        @JsonProperty("CNTR_NO") /*계약번호*/
        String cntrNo,

        @JsonProperty("CNTR_SN") /*계약일련번호*/
        int cntrSn
    ) {}

    @Builder
    @ApiModel(value = "WsniWealthContractDetailsDto-FindRes")
    public record FindRes(

        String cntrNo, /*계약번호*/
        String cntrSn, /*계약일련번호*/
        String basePdCd, /*상품코드*/
        String pdNm, /* 상품명  */
        String sellTpCd, /* 구매유형 1:일시불, 2:렌탈, 3:멤버십, 6:정기배송 */
        String sellTpDtlCd, /* 구매유형상세 일시불(1: 일반, 2:홈케어), 렌탈(1:일반, 2:금융리스, 3:장기할부), 멤버십(1:일반, 2:홈케어), 모종(1:일반) */
        String cntrTam, /* 구매가(총계약금액) */
        String cntrPdStrtdt, /* 매출일자 */
        String stplPtrm, /*약정개월수*/
        String pymtDiv, /*납부구분 1:완납,2:할부,3:월납*/
        String mnthPymnAmt, /*월납부금액*/
        String rcgvpKnm, /*설치자명*/
        String instrCralLocaraTno, /*설치자연락처1*/
        String instrMexnoEncr, /*설치자연락처2*/
        String instrCralIdvTno, /*설치자연락처3*/
        String instrLocaraTno, /*설치자전화1*/
        String instrExnoEncr, /*설치자전화2*/
        String instrIdvTno, /*설치자전화3*/
        String instrNewAdrZip, /*설치/배송우편번호*/
        String instrRnadr, /*설치배송주소1*/
        String instrRdadr, /*설치배송주소2*/
        String instrWad3, /*설치배송주소3*/
        String cstKnm, /*계약자명*/
        String cntrtCralLocaraTno, /*계약자연락처1*/
        String cntrtMexnoEncr, /*계약자연락처2*/
        String cntrtCralIdvTno, /*계약자연락처3*/
        String cntrtNewAdrZip, /*계약자우편번호*/
        String cntrtRnadr, /*계약자주소1*/
        String cntrtRdadr, /*계약자주소2*/
        String cntrtCad3, /*계약자주소3*/
        String emadr, /*계약자이메일*/
        String stat, /*계약상태 1:정상,2:보류,3:환불,4:예보,5:일시정지,6:연체정지,7:해지완료,8:기간만료,9:계약취소,10:연체해약,11:미출해약*/
        String istDt, /*설치일자*/
        String useMonth, /*사용개월*/
        String cntrPdEnddt, /*만기일자*/
        String prtnrKnm, /*웰스매니저명*/
        String managerCralLocaraTno, /*웰스매니저연락처1*/
        String managerMexnoEncr, /*웰스매니저연락처2*/
        String managerCralIdvTno, /*웰스매니저연락처3*/
        String damt /*연체금액*/

    ) {
        public FindRes {
            instrMexnoEncr = StringUtils.isNotEmpty(instrMexnoEncr) ? DbEncUtil.dec(instrMexnoEncr) : instrMexnoEncr;
            instrExnoEncr = StringUtils.isNotEmpty(instrExnoEncr) ? DbEncUtil.dec(instrExnoEncr) : instrExnoEncr;

            cntrtMexnoEncr = StringUtils.isNotEmpty(cntrtMexnoEncr) ? DbEncUtil.dec(cntrtMexnoEncr) : cntrtMexnoEncr;
            managerMexnoEncr = StringUtils.isNotEmpty(managerMexnoEncr) ? DbEncUtil.dec(managerMexnoEncr)
                : managerMexnoEncr;

        }

    }
}
