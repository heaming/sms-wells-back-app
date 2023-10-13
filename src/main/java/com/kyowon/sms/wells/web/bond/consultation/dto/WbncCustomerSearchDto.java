package com.kyowon.sms.wells.web.bond.consultation.dto;

import org.apache.commons.lang.StringUtils;

import com.sds.sflex.common.utils.DbEncUtil;

import io.swagger.annotations.ApiModel;

public class WbncCustomerSearchDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    // 채권상담 고객찾기 Search Request Dto
    @ApiModel(value = "WbncCustomerSearchDto-SearchReq")
    public record SearchReq(
        String bndClctnPrpDvCd, /* 채권속성코드 */
        String bndClctnPrpRsonCd, /* 속성사유코드 */
        String clctamPrtnrNo, /* 집급담당파트너번호 */
        String cstKnm, /* 고객명 */
        String cstNo, /* 고객번호 */
        String cntrCralLocaraTno, /* 계약처휴대지역전화번호 */
        String cntrMexnoEncr, /* 계약처휴대전화국번호암호화 */
        String cntrCralIdvTno, /* 계약처휴대개별전화번호 */
        String istCralLocaraTno, /* 설치처휴대지역전화번호 */
        String istMexnoEncr, /* 설치처휴대전화국번호암호화 */
        String istCralIdvTno, /* 설치처휴대개별전화번호 */
        String cntrLocaraTno, /* 계약처지역전화번호 */
        String cntrExnoEncr, /* 계약처전화국번호암호화 */
        String cntrIdvTno, /* 계약처개별전화번호 */
        String istLocaraTno, /* 설치처지역전화번호 */
        String istExnoEncr, /* 설치처전화국번호암호화 */
        String istIdvTno, /* 설치처개별전화번호 */
        String adr /* 주소 */
    ) {
        public SearchReq {
            cntrExnoEncr = StringUtils.isNotEmpty(cntrExnoEncr) ? DbEncUtil.enc(cntrExnoEncr) : cntrExnoEncr;
            cntrMexnoEncr = StringUtils.isNotEmpty(cntrMexnoEncr) ? DbEncUtil.enc(cntrMexnoEncr) : cntrMexnoEncr;
            istExnoEncr = StringUtils.isNotEmpty(istExnoEncr) ? DbEncUtil.enc(istExnoEncr) : istExnoEncr;
            istMexnoEncr = StringUtils.isNotEmpty(istMexnoEncr) ? DbEncUtil.enc(istMexnoEncr) : istMexnoEncr;
        }
    }

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 채권상담 고객찾기 Search Result Dto
    @ApiModel(value = "WbncCustomerSearchDto-SearchRes")
    public record SearchRes(
        String bndBizDvCd, /* 채권업무구분코드 */
        String bndBizDvNm, /* 채권업무구분코드명 */
        String cntrNo, /* 계약번호 */
        int cntrSn, /* 계약일련번호 */
        String cstKnm, /* 고객명 */
        String clctamPrtnrNo, /* 집급담당파트너번호 */
        String prtnrKnm, /* 집급담당파트너명 */
        String cstNo, /* 고객번호 */
        String sppZip, /* 계약처우편번호 */
        String sppAdr, /* 계약처기본주소 */
        String sppDtlAdr, /* 계약처상세주소 */
        String adr, /* 주소 */
        String cntrLocaraTno, /* 계약처지역전화번호 */
        String cntrExnoEncr, /* 계약처전화국번호암호화 */
        String cntrIdvTno, /* 계약처개별전화번호 */
        String cntrCralLocaraTno, /* 계약처휴대지역전화번호 */
        String cntrMexnoEncr, /* 계약처휴대전화국번호암호화 */
        String cntrCralIdvTno, /* 계약처휴대개별전화번호 */
        String istLocaraTno, /* 설치처지역전화번호 */
        String istExnoEncr, /* 설치처전화국번호암호화 */
        String istIdvTno, /* 설치처개별전화번호 */
        String istCralLocaraTno, /* 설치처휴대지역전화번호 */
        String istMexnoEncr, /* 설치처휴대전화국번호암호화 */
        String istCralIdvTno, /* 설치처휴대개별전화번호 */
        String sfkVal, /* 세이프키 */
        String bndClctnPrpDvCd, /* 채권속성코드 */
        String bndClctnPrpDvNm, /* 채권속성명 */
        String bndClctnPrpRsonCd, /* 속성사유코드 */
        String bndClctnPrpRsonNm /* 속성사유명 */
    ) {
        public SearchRes {
            cntrExnoEncr = StringUtils.isNotEmpty(cntrExnoEncr) ? DbEncUtil.dec(cntrExnoEncr) : cntrExnoEncr;
            cntrMexnoEncr = StringUtils.isNotEmpty(cntrMexnoEncr) ? DbEncUtil.dec(cntrMexnoEncr) : cntrMexnoEncr;
            istExnoEncr = StringUtils.isNotEmpty(istExnoEncr) ? DbEncUtil.dec(istExnoEncr) : istExnoEncr;
            istMexnoEncr = StringUtils.isNotEmpty(istMexnoEncr) ? DbEncUtil.dec(istMexnoEncr) : istMexnoEncr;
        }

    }
}
