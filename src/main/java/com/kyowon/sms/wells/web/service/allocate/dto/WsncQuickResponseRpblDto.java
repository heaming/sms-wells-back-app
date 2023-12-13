package com.kyowon.sms.wells.web.service.allocate.dto;

import java.util.Base64;

import javax.validation.constraints.NotBlank;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.annotations.AutomapConstructor;

import com.sds.sflex.common.utils.DbEncUtil;

import io.swagger.annotations.ApiModel;

public class WsncQuickResponseRpblDto {
    // *********************************************************
    // Request Dto
    // *********************************************************

    @ApiModel(value = "WsncQuickResponseRpblDto-SearchReq")
    public record SearchReq(
        // Case1
        @NotBlank
        String baseYm, // 기준년월
        String mngrDvCd, // 관리구분
        String pdPrpVal20, // 상품그룹
        String cntrNo, // 계약번호
        String cntrSn, // 계약순번
        String prtnrNo, // 사번
        // Case2
        String mngtDptmtCd, // 총괄단
        String rgnlGrpCd, // 지역단
        String branchCd, // 지점
        String mngrCd, // 매니저
        // Case3
        String svcCntrCd, // 서비스센터
        String engineerCd, // 엔지니어

        String rpblYn // 재발행 여부
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    @ApiModel(value = "WsncQuickResponseRpblDto-SearchRes")
    public record SearchRes(
        String mngrDvCd, /* 구분 */
        String ogNm, /* 소속 */
        String prtnrNo, /* 파트너번호 */
        String prtnrKnm, /* 파트너명 */
        String ogId, /* 조직ID */
        String ogTpCd, /* 조직유형코드 */
        String pdctPdCd, /* 제품상품코드 */
        String pdNm, /* 제품명 */
        String cntrNo, /* 계약번호 */
        String cntrSn, /* 계약일련번호 */
        String rcgvpKnm, /* 설치자명 */
        String newAdrZip, /* 우편번호 */
        String rnadr, /* 주소1 */
        String rdadr, /* 주소2 */
        String vstYm, /* 발행월 */
        String bcNo, /* 발행바코드 */
        String dldDt, /* 다운로드일자 */
        String vstFshDt, /* 처리일자 */
        String vstFshHh, /* 처리시간 */
        String bcInMthdCd, /* 스캔 */
        String cstSignCn, /* 고객서명 */
        String dnldPrtnrKnm, /* 다운로드담당자 */
        String publishInfo, /* 발행정보 */
        String svpdQrType, /* QR유형 */
        String qrCd, /* QR코드 */
        String cralLocaraTno, /* 작업파트너 휴대전화번호1 */
        String mexnoEncr, /* 작업파트너 휴대전화번호2 */
        String cralIdvTno, /* 작업파트너 휴대전화번호3 */
        String bcRpblId
    ) {
        @AutomapConstructor
        public SearchRes(
            String mngrDvCd,
            String ogNm,
            String prtnrNo,
            String prtnrKnm,
            String ogId,
            String ogTpCd,
            String pdctPdCd,
            String pdNm,
            String cntrNo,
            String cntrSn,
            String rcgvpKnm,
            String newAdrZip,
            String rnadr,
            String rdadr,
            String vstYm,
            String bcNo,
            String dldDt,
            String vstFshDt,
            String vstFshHh,
            String bcInMthdCd,
            byte[] cstSignCn,
            String dnldPrtnrKnm,
            String publishInfo,
            String svpdQrType,
            String qrCd,
            String cralLocaraTno,
            String mexnoEncr,
            String cralIdvTno,
            String bcRpblId
        ) {
            this(
                mngrDvCd,
                ogNm,
                prtnrNo,
                prtnrKnm,
                ogId,
                ogTpCd,
                pdctPdCd,
                pdNm,
                cntrNo,
                cntrSn,
                rcgvpKnm,
                newAdrZip,
                rnadr,
                rdadr,
                vstYm,
                bcNo,
                dldDt,
                vstFshDt,
                vstFshHh,
                bcInMthdCd,
                cstSignCn==null?null:Base64.getEncoder().encodeToString(cstSignCn),
                dnldPrtnrKnm,
                publishInfo,
                svpdQrType,
                qrCd,
                cralLocaraTno,
                StringUtils.isNotEmpty(mexnoEncr) ? DbEncUtil.dec(mexnoEncr) : mexnoEncr,
                cralIdvTno,
                bcRpblId
            );
        }
    }
}
