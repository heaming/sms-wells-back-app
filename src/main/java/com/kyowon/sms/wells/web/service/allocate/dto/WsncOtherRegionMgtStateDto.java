package com.kyowon.sms.wells.web.service.allocate.dto;

import javax.validation.constraints.NotBlank;

import org.apache.commons.lang.StringUtils;

import com.sds.sflex.common.utils.DbEncUtil;

import io.swagger.annotations.ApiModel;

/**
 *
 * <pre>
 * 타지역단 관리 현황
 * </pre>
 *
 * @author heymi.cho 조혜미
 * @since 2023-06-01
 */
public class WsncOtherRegionMgtStateDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    @ApiModel(value = "WsncOtherRegionMgtStateDto-SearchReq")
    public record SearchReq(
        @NotBlank
        String mgtYnm,
        String mgtDept,
        String rgnlGrp,
        String branch,
        String zipFrom,
        String zipTo
    ) {}

    // *********************************************************
    // Response Dto
    // *********************************************************
    @ApiModel(value = "WsncOtherRegionMgtStateDto-SearchRes")
    public record SearchRes(
        String rgnlGrp,
        String cntrNo,
        String cstKnm,
        String newAdrZip,
        String cstAdr,
        String ltnAdr,
        String cralLocaraTno,
        String mexnoEncr,
        String cralIdvTno,
        String hgrOgNm,
        String ogNm,
        String mngtPrtnrNo,
        String prtnrKnm,
        String bldNm,
        String fxnPrtnrYn,
        String mngerRglvlDvCd
    ) {
        public SearchRes {
            mexnoEncr = StringUtils.isNotEmpty(mexnoEncr) ? DbEncUtil.dec(mexnoEncr) : mexnoEncr; // 암호화
        }
    }

}
