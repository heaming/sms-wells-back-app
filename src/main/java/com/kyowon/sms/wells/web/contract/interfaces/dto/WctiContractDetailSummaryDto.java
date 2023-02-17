package com.kyowon.sms.wells.web.contract.interfaces.dto;

import javax.validation.constraints.NotBlank;

import org.apache.commons.lang.StringUtils;

import com.sds.sflex.common.utils.DbEncUtil;

import io.swagger.annotations.ApiModel;

public class WctiContractDetailSummaryDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    // 계약상세 요약 정보 Search Request Dto
    @ApiModel("WctiContractDetailSummaryDto-FindReq")
    public record FindReq(
            @NotBlank
            String CNTR_NO,
            @NotBlank
            String CNTR_SN
    ) {
    }

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 계약상세 요약 정보 Search Result Dto
    @ApiModel("WctiContractDetailSummaryDto-FindRes")
    public record FindRes(
            String CNTRT_NM,
            String CNTRT_CRAL_LOCARA_TNO,
            String CNTRT_MEXNO,
            String CNTRT_CRAL_IDV_TNO,
            String IST_CST_NM,
            String IST_CRAL_LOCARA_TNO,
            String IST_MEXNO,
            String IST_CRAL_IDV_TNO,
            String IST_LOCARA_TNO,
            String IST_EXNO,
            String IST_IDV_TNO,
            String BASE_PD_CD,
            String BASE_PD_NM
    ) {
        public FindRes {
            /* TODO:암호화모듈 확인중 */
            IST_MEXNO = StringUtils.isNotEmpty(IST_MEXNO) ? DbEncUtil.dec(IST_MEXNO) : IST_MEXNO;
            IST_EXNO = StringUtils.isNotEmpty(IST_EXNO) ? DbEncUtil.dec(IST_EXNO) : IST_EXNO;
        }
    }
}
