package com.kyowon.sms.wells.web.contract.interfaces.dto;

import javax.validation.constraints.NotBlank;

import org.apache.commons.lang.StringUtils;

import com.sds.sflex.common.utils.DbEncUtil;

import io.swagger.annotations.ApiModel;

/**
 * EAI INTERFACE 통신용 DTO
 * request/response의 body에 정의된 key 형식(snake case) 사용
 */
public class WctiContractInstallDto {

    // *********************************************************
    // Request Dto
    // *********************************************************
    // 계약처, 설치처 정보 변경용 Save Request Dto
    @ApiModel("WctiContractDeliversDto-SaveReq")
    public record SaveReq(
        @NotBlank
        String CNTR_NO, //계약번호(필수)
        @NotBlank
        String CNTR_SN, //계약일련번호(필수)
        String ADR_ID, //변경될 주소ID
        String CRAL_LOCARA_TNO, //휴대지역전화번호
        String MEXNO, //휴대전화국번호
        String CRAL_IDV_TNO, //휴대개별전화번호
        String LOCARA_TNO, //지역전화번호
        String EXNO, //전화국번호
        String IDV_TNO //개별전화번호
    ) {
        public SaveReq {
            /* TODO:암호화모듈 확인중 */
            MEXNO = StringUtils.isNotEmpty(MEXNO) ? DbEncUtil.enc(MEXNO) : MEXNO; //암호화
            EXNO = StringUtils.isNotEmpty(EXNO) ? DbEncUtil.enc(EXNO) : EXNO; //암호화
        }
    }

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 계약처, 배송지 정보 변경용 Save Search Response Dto
    @ApiModel("WctiContractDeliversDto-SaveRes")
    public record SaveRes(
        String RS_PROCS_YN //처리결과(Y/N)
    ) {}
}
