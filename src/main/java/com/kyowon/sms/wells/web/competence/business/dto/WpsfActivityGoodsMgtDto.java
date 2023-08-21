package com.kyowon.sms.wells.web.competence.business.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang.StringUtils;

import com.sds.sflex.common.utils.DbEncUtil;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

public class WpsfActivityGoodsMgtDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    // 활동물품 관리 Search Request Dto
    @Builder
    @ApiModel("WpsfActivityGoodsMgtDto-SearchReq")
    public record SearchReq(

        String ogTpCd,
        String actiGdsCd, /* 활동물품코드 */
        String aplcDt,
        String prtnrNo,
        String actiGdsStddDvId, /* 활동물품규격구분ID */
        String actiGdsSn, /* 활동물품일련번호 */
        String actiGdsStddCd, /* 활동물품규격구분코드 */
        String aplcQty, /* 신청수량 */
        String bzStatCd /* 사업상태코드 */

    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 활동물품 관리 Search Result Dto
    @ApiModel("WpsfActivityGoodsMgtDto-SearchRes")
    public record SearchRes(
        String ogTpCd, /* 조직유형코드 */
        String aplcPsbStrtD, /* 신청가능시작일 */
        String aplcPsbEndD, /* 신청가능종료일 */
        String rtngdPsbStrtD, /* 반품가능시작일 */
        String rtngdPsbEndD, /* 반품가능종료일 */
        String rtngdShrnEmadr, /* 반품공유이메일주소 */
        String confArtcCn /* 확인사항내용 */

    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 활동물품신청내역 조회 Search Result Dto
    @ApiModel("WpsfActivityGoodsMgtDto-SearchStatRes")
    public record SearchStatRes(
        String actiGdsAplcId, /* 활동물품신청ID */
        String ogTpCd, /* 조직유형코드 */
        String actiGdsCd, /* 활동물품코드 */
        String actiGdsSn, /* 활동물품일련번호 */
        Long aplcQty, /* 신청수량 */
        String actiGdsStddCd, /* 활동물품규격구분코드 */
        String prtnrNo, /* 파트너번호 */
        String actiGdsAplcSn, /* 활동물품신청일련번호 */
        String actiGdsAplcStatCd, /* 활동물품신청상태내역 */
        String aplcDt, /* 신청일자 */
        String aplcRsonCn, /* 신청사유내용 */
        String sppDt, /* 배송일자 */
        String sppBzsCd, /* 배송업체코드 */
        String sppBzsNm,
        String sppIvcNo, /* 배송송장번호 */
        String prtnrKnm, /* 성명 */
        String bldNm,
        String cralLocaraTno,
        String mexnoEncr,
        String cralIdvTno,
        String baseDvNm,
        String startYrmn,
        String actiGdsAplcStatNm,
        String actiGdsNm,
        Long actiGdsAmt,
        Integer patDdtnMcn,
        Long pnpyamOcAmt,
        String maxFeeDdtnOcDt,
        String sumFeeDdtnDstAmt,
        Integer countMcn,
        String actiGdsDdtnId,

        String aplcDtS,
        String actiGdsStddNm,
        String cltnDt, /* 해약일자 */
        String cntrDt /* 계약일자 */

    ) {
        public SearchStatRes {
            mexnoEncr = StringUtils.isNotEmpty(mexnoEncr) ? DbEncUtil.dec(mexnoEncr) : mexnoEncr;
        }

    }

    @Builder
    @ApiModel("WpsfActivityGoodsMgtDto-SaveReq")
    public record SaveReq(
        @NotBlank
        String ogTpCd, /* 조직유형코드 */
        String aplcPsbStrtD, /* 신청가능시작일 */
        String aplcPsbEndD, /* 신청가능종료일 */
        String rtngdPsbStrtD, /* 반품가능시작일 */
        String rtngdPsbEndD, /* 반품가능종료일 */
        String rtngdShrnEmadr, /* 반품공유이메일주소 */
        String confArtcCn /* 확인사항내용 */

    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 활동물품기본 Search Result Dto
    @ApiModel("WpsfActivityGoodsMgtDto-SearchAcriGdsBasRes")
    public record SearchAcriGdsBasRes(
        String ogTpCd, /* 조직유형코드 */
        String actiGdsCd, /* 활동물품코드 */
        Integer actiGdsSn, /* 활동물품일련번호 */
        String actiGdsNm, /* 활동물품명 */
        Long actiGdsAmt, /* 활동물품금액 */
        Integer patDdtnMcn, /* 분할공제개월수 */
        String vlStrtdt, /* 유효시작일자 */
        String vlEnddt, /* 유효종료일자 */
        String actiGdsStddDvId, /* 활동물품규격구분ID */
        String useYn, /* 사용여부 */
        String fstRgstDtm,
        String fstRgstUsrNm

    ) {}

    @ApiModel(value = "WpsfActivityGoodsMgtDto-EditReq")
    public record EditReq(
        @NotBlank
        String ogTpCd, /* 조직유형코드 */
        String actiGdsCd, /* 활동물품코드 */
        Integer actiGdsSn, /* 활동물품일련번호 */
        String actiGdsNm, /* 활동물품명 */
        Long actiGdsAmt, /* 활동물품금액 */
        Integer patDdtnMcn, /* 분할공제개월수 */
        String vlStrtdt, /* 유효시작일자 */
        String vlEnddt, /* 유효종료일자 */
        String actiGdsStddDvId, /* 활동물품규격구분ID */
        String useYn /* 사용여부 */
    ) {}

    @ApiModel(value = "WpsfActivityGoodsMgtDto-RemoveReq")
    public record RemoveReq(
        @NotBlank
        String ogTpCd, /* 조직유형코드 */
        String actiGdsCd, /* 활동물품코드 */
        @NotNull
        Integer actiGdsSn /* 활동물품일련번호 */

    ) {}

    @ApiModel("WpsfActivityGoodsMgtDto-SearchCodeRes")
    public record SearchCodeRes(
        String codeId,
        String codeName,
        String prtsCodeId

    ) {}

    @ApiModel("WpsfActivityGoodsMgtDto-SearchBaseCodeRes")
    public record SearchBaseCodeRes(
        String codeId,
        String codeName,
        String prtsCodeId,
        String actiGdsStddDvId

    ) {}

    @ApiModel(value = "WpsfActivityGoodsMgtDto-SaveApplicationReq")
    public record SaveApplicationReq(
        @NotBlank
        String ogTpCd, /* 조직유형코드 */
        String actiGdsCd, /* 활동물품코드 */
        Integer actiGdsSn, /* 활동물품일련번호 */
        String actiGdsStddCd, /* 활동물품규격구분코드 */
        @NotNull
        Long aplcQty,
        String prtnrNo,
        String actiGdsAplcId

    ) {}

    @ApiModel(value = "WpsfActivityGoodsMgtDto-RemovepplicationReq")
    public record RemovepplicationReq(
        @NotBlank
        String ogTpCd, /* 조직유형코드 */
        @NotBlank
        String actiGdsAplcId, /* 활동물품신청ID */
        String actiGdsAplcSn, /* 활동물품신청일련번호 */
        String aplcDt, /* 신청일자 */
        @NotBlank
        String actiGdsAplcStatCd, /* 활동물품신청상태내역 */
        String aplcRsonCn, /* 신청사유내용 */
        String sppDt, /* 배송일자 */
        String sppBzsCd, /* 배송업체코드 */
        String sppIvcNo, /* 배송송장번호 */
        String oldActiGdsAplcStatCd /* 변경전-활동물품신청상태내역 */

    ) {}

    @ApiModel(value = "WpsfActivityGoodsMgtDto-EditDeductionReq")
    public record EditDeductionReq(
        @NotBlank
        String ogTpCd, /* 조직유형코드 */
        @NotBlank
        String prtnrNo,
        String actiGdsDdtnId, /* 활동물품공제ID */
        @NotBlank
        String actiGdsAplcId, /* 활동물품신청ID */
        @NotBlank
        String feeDdtnOcDt, /* 수수료공제발생일자 */
        String procsYn, /* 처리여부 */
        String procsCn, /* 처리내용 */
        Long pnpyamOcAmt,
        Integer patDdtnMcn,
        Integer countMcn

    ) {}

    @ApiModel(value = "WpsfActivityGoodsMgtDto-RemoveDeductionReq")
    public record RemoveDeductionReq(
        @NotBlank
        String ogTpCd, /* 조직유형코드 */
        @NotBlank
        String actiGdsDdtnId, /* 활동물품공제ID */
        @NotBlank
        String maxFeeDdtnOcDt /* 수수료최종공제발생일자 */
    ) {}

    @ApiModel("WpsfActivityGoodsMgtDto-SearchDeductionItemizationRes")
    public record SearchDeductionItemizationRes(
        String feeDdtnDstAmt, /* 수수료공제배분금액 */
        String feeDdtnOcDt, /* 수수료공제발생일자 */
        String procsCn, /* 처리내용 */
        String aplcDt,
        String actiGdsNm, /* 활동물품명 */
        String prtnrNo

    ) {}

    @ApiModel("WpsfActivityGoodsMgtDto-SearchSizeRes")
    public record SearchSizeRes(
        String actiGdsStddDvId, /* 활동물품규격구분ID */
        String actiGdsStddDvNm, /* 활동물품규격구분명 */
        Long sortOdr, /* 정렬순서 */
        String useYn /* 사용여부 */

    ) {}

    @ApiModel(value = "WpsfActivityGoodsMgtDto-EditSizeReq")
    public record EditSizeReq(
        String actiGdsStddDvId, /* 활동물품규격구분ID */
        @NotBlank
        String actiGdsStddDvNm, /* 활동물품규격구분명 */
        Long sortOdr, /* 정렬순서 */
        String useYn /* 사용여부 */
    ) {}

    @ApiModel(value = "WpsfActivityGoodsMgtDto-RemoveSizenReq")
    public record RemoveSizenReq(
        @NotBlank
        String actiGdsStddDvId /* 활동물품규격구분ID */
    ) {}

    @ApiModel("WpsfActivityGoodsMgtDto-SearchSizeDetailRes")
    public record SearchSizeDetailRes(
        String actiGdsStddDvId, /* 활동물품규격구분ID */
        String actiGdsStddCd, /* 활동물품규격코드 */
        String actiGdsStddNm, /* 활동물품규격명 */
        Long sortOdr, /* 정렬순서 */
        String useYn /* 사용여부 */
    ) {}

    @ApiModel(value = "WpsfActivityGoodsMgtDto-EditSizeDetailReq")
    public record EditSizeDetailReq(
        @NotBlank
        String actiGdsStddDvId, /* 활동물품규격구분ID */
        @NotBlank
        String actiGdsStddCd, /* 활동물품규격코드 */
        @NotBlank
        String actiGdsStddNm, /* 활동물품규격명 */
        String useYn, /* 사용여부 */
        Long sortOdr /* 정렬순서 */
    ) {}

    @ApiModel(value = "WpsfActivityGoodsMgtDto-RemoveSizenDetailReq")
    public record RemoveSizenDetailReq(
        @NotBlank
        String actiGdsStddDvId, /* 활동물품규격구분ID */
        @NotBlank
        String actiGdsStddCd /* 활동물품규격코드 */
    ) {}

}
