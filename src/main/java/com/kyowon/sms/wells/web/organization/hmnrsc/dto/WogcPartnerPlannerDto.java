package com.kyowon.sms.wells.web.organization.hmnrsc.dto;

import java.util.List;

import javax.validation.constraints.NotBlank;

import com.sds.sflex.system.config.annotation.DBEncField;
import org.apache.commons.lang.StringUtils;

import com.sds.sflex.common.utils.DbEncUtil;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

public class WogcPartnerPlannerDto {

    @ApiModel(value = "WogcPartnerPlannerDto-SearchLicenseReq")
    @Builder
    public record SearchLicenseReq(
        String prtnrKnm, // 파트너한글명
        String prtnrNo, // 파트너번호
        List<String> qlfDvCd // 자격구분코드
    ) {
    }

    @ApiModel(value = "WogcPartnerPlannerDto-SearchLicenseRes")
    public record SearchLicenseRes(
        String dgr1LevlOgNm, // 총괄단
        String dgr2LevlOgNm, // 지역단
        String ogCd, // 조직코드
        String bldNm, // 빌딩명
        String ogId, // 조직ID
        String ogTpCd, // 조직유형코드
        String prtnrNo, // 파트너번호
        String prtnrKnm, // 파트너한글명
        String usrId, // 사용자ID
        String rsbDvCd, // 직책코드
        String rsbDvNm, // 직책명
        String cralLocaraTno, // 휴대지역전화번호
        String mexnoEncr, // 휴대전화국번호암호화
        String cralIdvTno, // 휴대개별전화번호
        String bizUseIdvTno, // 업무사용개별전화번호
        String bizUseExnoEncr, // 업무사용전화국번호암호화
        String bizUseLocaraTno, // 업무사용지역전화번호
        String bryyMmdd, // 생년월일
        String cntrDt, // 계약일자(리쿠르팅 일자)
        String fnlCltnDt, // 최종해약일자
        String edu143, // Pre스타트업
        String edu96, // 스타트업
        String qlfDvCd, // 자격구분코드
        String qlfDvNm // 자격구분코드명
    ) {
        public SearchLicenseRes {
            // 업무사용전화국번호암호화 복호화처리
            mexnoEncr = StringUtils.isNotEmpty(mexnoEncr) ? DbEncUtil.dec(mexnoEncr) : mexnoEncr;
            // 업무사용전화국번호암호화 복호화처리
            bizUseExnoEncr = StringUtils.isNotEmpty(bizUseExnoEncr) ? DbEncUtil.dec(bizUseExnoEncr) : bizUseExnoEncr;
        }
    }

    @ApiModel(value = "WogcPartnerPlannerDto-SearchLicenseDetailRes")
    public record SearchLicenseDetailRes(
        String ogTpCd,
        String prtnrNo,
        String ogId,
        String qlfDvCd,
        String qlfDvNm,
        String qlfAplcDvCd,
        String qlfAplcDvNm,
        String strtdt,
        String cvDt,
        String enddt,
        String pymdt,
        String dsbAmt,
        String cntrSn,
        String cntrDt,
        String prtnrCntrTpCd,
        String pcpOgTpCd,
        String pcpPrtnrNo,
        String pcpPrtnrKnm,
        String prcsdt
    ) {
    }

    @ApiModel(value = "WogcPartnerPlannerDto-SearchReq")
    @Builder
    public record SearchReq(
        String ogTpCd,
        String prtnrKnm,
        String prtnrNo,
        String mOgYn,
        String baseYm,
        String mngtYm,
        String ogId,
        String ogLevlDvCd1,
        String ogLevlDvCd2,
        String ogLevlDvCd3,
        String ogLevlDvCd4,
        String olfDvCd
    ) {
        public SearchReq {
            if (StringUtils.isNotEmpty(mngtYm)) {
                baseYm = mngtYm;
            }
        }
    }

    @ApiModel(value = "WogcPartnerPlannerDto-SearchCheckReq")
    @Builder
    public record SearchCheckReq(
        String mngtYm

    ) {
    }

    @ApiModel(value = "WogcPartnerPlannerDto-SearchRes")
    public record SearchRes(
        String dgr1LevlOgNm,
        String dgr2LevlOgNm,
        String ogCd,
        String ogTpCd,
        String prtnrKnm,
        String prtnrNo,
        String qlfDvCd,
        String qlfDvNm,
        String rcmdrPrtnrNm,
        String rcmdrPrtnrNo,
        String cntrDt,
        String fnlCltnDt,
        String rcntrDt,
        String edu14,
        String twoSum,
        String curSum,
        String mTotSum,
        String curBs,
        String totSum,
        String prfmtDt,
        String dmtnDt,
        String dmtnCurBs,
        String dmtnTotSum,
        String strtdt,
        String fnlEnddt,
        String cvDt,
        String enddt,
        String mQlfDvCd,
        String mQlfDvNm,
        String btnYn,
        String mTotCnt
    ) {
    }

    @ApiModel(value = "WogcPartnerPlannerDto-DeleteReq")
    @Builder
    public record DeleteReq(
        String ogTpCd,
        String prtnrKnm,
        String prtnrNo,
        String aplcSn
    ) {
    }

    @ApiModel(value = "WogcPartnerPlannerDto-SaveReq")
    @Builder
    public record SaveReq(
        String mngtYm,
        String ogTpCd,
        String prtnrKnm,
        String prtnrNo,

        String qlfDvCd
    ) {
    }

    @ApiModel(value = "WogcPartnerPlannerDto-SearchCountMmPartnerReq")
    @Builder
    public record SearchCountMmPartnerReq(
        String mngtYm,
        String ogTpCd,
        String prtnrKnm,
        String prtnrNo,
        String qlfDvCd
    ) {
    }

    @ApiModel(value = "WogcPartnerPlannerDto-SearchCountPlarPartnerReq")
    @Builder
    public record SearchCountPlarPartnerReq(
        String ogTpCd,
        String prtnrKnm,
        String prtnrNo,
        String olfDvCd
    ) {
    }

    @ApiModel(value = "WogcPartnerPlannerDto-FindRes")
    public record FindRes(
        String mngtYm,
        String prtnrNo,
        String prtnrKnm,
        String upgrYm,
        String dmtnYm,
        String qlfDvCd,
        String rfdt,
        String cntrDt,
        String cltnDt,
        String upgrDmtnDvCd,
        String rcntrDt,
        String upgrMcn,
        String rgrOgTpCd,
        String rgstPrtnrKnm,
        String rgrPrtnrNo,
        String rgstDt,
        String udrOgTpCd,
        String mdfcPrtnrKnm,
        String udrPrtnrNo,
        String mdfcDt

    ) {
    }

    @ApiModel(value = "WogcPartnerPlannerDto-EditReq")
    @Builder
    public record EditReq(
        @NotBlank
        String mngtYm,
        @NotBlank
        String ogTpCd,
        @NotBlank
        String prtnrNo,
        String qlfDvCd,
        String rfdt,
        String upgrDmtnDvCd,
        String upgrYm,
        String dmtnYm,
        String cntrDt,
        String cltnDt,
        String rcntrDt,
        String upgrMcn
    ) {
    }

    @ApiModel(value = "WogcPartnerPlannerDto-SaveQulificationReq")
    @Builder
    public record SaveQulificationReq(
        String ogId,
        String ogTpCd,
        String prtnrNo,
        String qlfDvCd,
        String strtdt,
        String enddt,
        String cvDt,
        String qlfAplcDvCd,
        String pymdt,
        String dsbAmt,
        String chdt,
        String pcpOgTpCd,
        String pcpPrtnrNo,
        String prcsdt,
        String dtaDlYn,
        String fstRgstDtm,
        String fstRgstUsrId,
        String fstRgstPrgId,
        String fstRgstDeptId,
        String fnlMdfcDtm,
        String fnlMdfcUsrId,
        String fnlMdfcPrgId,
        String fnlMdfcDeptId
    ) {
    }

    @ApiModel(value = "WogcPartnerPlannerDto-CheckCancellationReq")
    @Builder
    public record CheckCancellationReq(
        @NotBlank
        String prtnrNo, // 파트너번호
        @NotBlank
        String ogTpCd // 조직유형코드
    ) {
    }

    @ApiModel(value = "WogcPartnerPlannerDto-SearchWellsPartnerRes")
    public record SearchWellsPartnerRes(
       String dgr2LevlOgCd // 지역단
     , String dgr2LevlOgNm // 지역단명
     , String ogCd // 현재소속
     , String ogNm // 현재소속명
     , String preOgCd // 전월소속
     , String preOgNm // 전월소속명
     , String bldNm // 빌딩명
     , String prtnrNo // 업무등록번호
     , String prtnrKnm // 이름
     , String rsbDvNm // 직급
     , String fullCralLocaraTno
     , String cralLocaraTno
     , String mexnoEncr
     , String cralIdvTno
     , String fullRrnoFrpsnVal // 주민등록번호전체
     , String rrnoFrpsnVal // 주민등록번호앞자리
     , String rrnoEncr // 주민등록번호뒷자리
     , String fstCntrDt // 업무등록일
     , String prfmtDt // 승진/강등일
     , String cntrDt // 재등록일
     , String cltnDt // 업무해약일
     , String fnlCltnDt // 최종해약일
     , String qlfDvCd // 매니저
     , String conDt // 계약일
     , String reconDt // 재등록일
     , String fireDt // 해약일
     , String rcmdrPrtnrNo // 업무등록번호
     , String prtnrNm // 이름
    ) {
        public SearchWellsPartnerRes {
            // 전화번호
            mexnoEncr = StringUtils.isNotEmpty(mexnoEncr) ? DbEncUtil.dec(mexnoEncr) : mexnoEncr;
            fullCralLocaraTno = StringUtils.isNotEmpty(cralLocaraTno) ? cralLocaraTno + "-" + mexnoEncr + "-" + cralIdvTno : "-";
            rrnoEncr = StringUtils.isNotEmpty(rrnoEncr) ? DbEncUtil.dec(rrnoEncr) : rrnoEncr;
            fullRrnoFrpsnVal = StringUtils.isNotEmpty(rrnoFrpsnVal) ? rrnoFrpsnVal + "-" + rrnoEncr: "-";
        }
    }

    @ApiModel(value = "WogcPartnerPlannerDto-SearchWellsPartnerReq")
    @Builder
    public record SearchWellsPartnerReq(
        String prtnrKnm, // 파트너한글명
        String prtnrNo, // 파트너번호
        String ogTpCd, // 자격구분코드
        String rsbDvCd, //직급코드
        String wmActiCd, //사용중구분코드
        String schOjBlamStrt,
        String schOjBlamEnd,
        String tno,
        String cralLocaraTno,
        String mexnoEncr,
        String cralIdvTno,
        String actiYn,
        String wmYn


    ) {
        public SearchWellsPartnerReq{
            mexnoEncr = StringUtils.isNotEmpty(mexnoEncr) ? DbEncUtil.enc(mexnoEncr) : mexnoEncr;
        }
    }
}
