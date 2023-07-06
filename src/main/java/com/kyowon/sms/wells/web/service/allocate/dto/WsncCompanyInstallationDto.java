package com.kyowon.sms.wells.web.service.allocate.dto;

import java.util.List;

import javax.validation.constraints.NotBlank;

import org.apache.commons.lang.StringUtils;

import com.sds.sflex.common.utils.DbEncUtil;

import io.swagger.annotations.ApiModel;

/**
 *
 * <pre>
 * 회사설치 현황
 * </pre>
 *
 * @author heymi.cho 조혜미
 * @since 2023-05-24
 */
public class WsncCompanyInstallationDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    @ApiModel(value = "WsncCompanyIstStateDto-SearchMainReq")
    public record SearchMainReq(
        @NotBlank
        String mgtYnm,
        List<String> mgtTyps,
        String istDtFrom,
        String istDtTo,
        List<String> itmKndCd
    ) {}

    @ApiModel(value = "WsncCompanyIstStateDto-SearchPsReq")
    public record SearchPsReq(
        List<String> mgtTyps,
        String istDtFrom,
        String istDtTo
    ) {}

    // *********************************************************
    // Response Dto
    // *********************************************************
    @ApiModel(value = "WsncCompanyIstStateDto-SearchAllRes")
    public record SearchAllRes(
        String cntrNo,
        String cntrSn,
        String ogId,
        String ogNm,
        String prtnrNo,
        String cscnCd,
        String deptNm1,
        String rcgvpKnm,
        String svcTpNm,
        String fnlPdCd, // 상품코드
        String basePdNm, // 상품명
        String svBizHclsfCd, // 작업그룹
        String svBizDclsfCd, // 작업유형상세
        String sapMatCd, // SAP코드
        String pdctPdCd,
        String basePdCd,
        String itmPdCd, // 품목코드
        String itmPdNm, // 부품명
        Long useQty, // 사용수량
        Long pdctUprc, // 실제원가
        Long pdctUprcSum, // 원가합계금액
        Long csmrUprcAmt, // 소비자가
        Long csmrUprcAmtSum, // 소비자가합계급액
        //        String refriDvCd, // 유무상
        String prtnrClsfCd, // 작업자 구분
        String ichrPrtnrNo, // 작업자 사번
        String prtnrKnm, // 작업자 성명
        String prtnrOgNm, // 작업자 소속
        String cstAdr// 고객주소 상세
    ) {}

    @ApiModel(value = "WsncCompanyIstStateDto-SearchPsRes")
    public record SearchPsRes(
        String cntrNo,
        String cntrSn,
        String sapMatCd,
        String basePdCd,
        String basePdNm,
        String svcTpNm,
        String istDt,
        String useMcn,
        String frisuBfsvcPtrmN,
        String newAdrZip,
        String cralLocaraTno,
        String mexnoEncr,
        String cralIdvTno,
        String locaraTno,
        String exnoEncr,
        String idvTno,
        String rnadr,
        String rdadr,
        String ogId,
        String ogNm,
        String hgrOgId,
        String hgrOgNm
    ) {
        public SearchPsRes {
            mexnoEncr = StringUtils.isNotEmpty(mexnoEncr) ? DbEncUtil.dec(mexnoEncr) : mexnoEncr;
            exnoEncr = StringUtils.isNotEmpty(exnoEncr) ? DbEncUtil.dec(exnoEncr) : exnoEncr;
        }
    }

    @ApiModel(value = "WsncCompanyIstStateDto-SearchFltrSubMatRes")
    public record SearchFltrSubMatRes(
        String itmKndCd,
        String sapMatCd,
        String itmPdCd,
        String itmPdNm,
        Long useQtySum,
        Long pdctUprc,
        Long pdctUprcSum,
        Long csmrUprcAmt,
        Long csmrUprcAmtSum
    ) {}
}
