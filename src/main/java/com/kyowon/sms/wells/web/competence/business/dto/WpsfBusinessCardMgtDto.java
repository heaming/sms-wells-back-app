package com.kyowon.sms.wells.web.competence.business.dto;

import java.util.List;

import javax.validation.constraints.NotBlank;

import org.apache.commons.lang.StringUtils;

import com.sds.sflex.common.docs.dto.AttachFileDto;
import com.sds.sflex.common.utils.DbEncUtil;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

public class WpsfBusinessCardMgtDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    // 명함첩 관리 -파트너고객연락처기본 Search Request Dto
    @Builder
    @ApiModel("WpsfBusinessCardMgtDto-SearchReq")
    public record SearchReq(
        String fnm, /* 성명 */
        String ogTpCd, /* 조직유형코드 */
        String prtnrNo /* 파트너번호 */

    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 명함첩 관리 -파트너고객연락처기본 Search Result Dto
    @ApiModel("WpsfBusinessCardMgtDto-SearchRes")
    public record SearchRes(
        String ogTpCd, /* 조직유형코드 */
        String prtnrNo, /* 파트너번호 */
        Integer ctplcSn, /* 연락처일련번호 */
        String fnm, /* 성명 */
        String cralLocaraTno, /* 휴대지역전화번호 */
        String mexnoEncr, /* 휴대전화국번호암호화 */
        String cralIdvTno, /* 휴대개별전화번호 */
        String zip, /* 우편번호 */
        String basAdr, /* 기본주소 */
        String dtlAdr, /* 상세주소 */
        String ctplcImgFileId, /* 연락처이미지파일ID */
        String fileNm,
        String realFpath,
        String fileUid
    ) {
        public SearchRes {
            mexnoEncr = StringUtils.isNotEmpty(mexnoEncr) ? DbEncUtil.dec(mexnoEncr) : mexnoEncr;
        }
    }

    @ApiModel(value = "WpsfBusinessCardMgtDto-EditReq")
    public record EditReq(
        @NotBlank
        String fnm, /* 성명 */
        Integer ctplcSn, /* 연락처일련번호 */
        String cralLocaraTno, /* 휴대지역전화번호 */
        String mexnoEncr, /* 휴대전화국번호암호화 */
        String cralIdvTno, /* 휴대개별전화번호 */
        String zip, /* 우편번호 */
        String basAdr, /* 기본주소 */
        String dtlAdr, /* 상세주소 */
        String ctplcImgFileId, /* 연락처이미지파일ID */
        List<AttachFileDto.AttachFile> attachFiles1 /* 명함이미지파일 */
    ) {
        public EditReq {
            mexnoEncr = StringUtils.isNotEmpty(mexnoEncr) ? DbEncUtil.enc(mexnoEncr) : mexnoEncr;
        }

    }

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 명함첩 관리 -파트너고객연락처기본 Search Result Dto
    @ApiModel("WpsfBusinessCardMgtDto-SearchPartnerRes")
    public record SearchPartnerRes(
        String ogTpCd, /* 조직유형코드 */
        String prtnrNo, /* 파트너번호 */
        String blgNm, /* 소속명 */
        String fnm, /* 성명 */
        String rsbNm, /* 직책명 */
        String cralLocaraTno, /* 휴대지역전화번호 */
        String mexnoEncr, /* 휴대전화국번호암호화 */
        String cralIdvTno, /* 휴대개별전화번호 */
        String locaraTno, /* 지역전화번호 */
        String exnoEncr, /* 전화국번호암호화 */
        String idvTno, /* 개별전화번호 */
        String zip, /* 우편번호 */
        String basAdr, /* 기본주소 */
        String dtlAdr, /* 상세주소 */
        String faxLocaraTno, /* 팩스지역전화번호 */
        String faxExno, /* 팩스전화국번호 */
        String faxIdvTno, /* 팩스개별전화번호 */
        String emadr, /* 이메일주소 */
        String ctplcApnImgFileId, /* 연락처첨부이미지파일ID */
        String ctplcImgFileId, /* 연락처이미지파일ID */
        String fileNm,
        String realFpath,
        String inMode
    ) {
        public SearchPartnerRes {
            mexnoEncr = StringUtils.isNotEmpty(mexnoEncr) ? DbEncUtil.dec(mexnoEncr) : mexnoEncr;
            exnoEncr = StringUtils.isNotEmpty(exnoEncr) ? DbEncUtil.dec(exnoEncr) : exnoEncr;
        }
    }

    @ApiModel(value = "WpsfBusinessCardMgtDto-EditPartnerReq")
    public record EditPartnerReq(
        @NotBlank
        String blgNm, /* 소속명 */
        @NotBlank
        String fnm, /* 성명 */
        @NotBlank
        String rsbNm, /* 직책명 */
        @NotBlank
        String cralLocaraTno, /* 휴대지역전화번호 */
        String mexnoEncr, /* 휴대전화국번호암호화 */
        String cralIdvTno, /* 휴대개별전화번호 */
        String locaraTno, /* 지역전화번호 */
        String exnoEncr, /* 전화국번호암호화 */
        String idvTno, /* 개별전화번호 */
        String zip, /* 우편번호 */
        String basAdr, /* 기본주소 */
        String dtlAdr, /* 상세주소 */
        String faxLocaraTno, /* 팩스지역전화번호 */
        String faxExno, /* 팩스전화국번호 */
        String faxIdvTno, /* 팩스개별전화번호 */
        String emadr, /* 이메일주소 */
        String ctplcApnImgFileId, /* 연락처첨부이미지파일ID */
        String ctplcImgFileId, /* 연락처이미지파일ID */
        AttachFileDto.AttachFile attachFiles /* 명함이미지파일 */
    ) {
        public EditPartnerReq {
            mexnoEncr = StringUtils.isNotEmpty(mexnoEncr) ? DbEncUtil.enc(mexnoEncr) : mexnoEncr;
            exnoEncr = StringUtils.isNotEmpty(exnoEncr) ? DbEncUtil.enc(exnoEncr) : exnoEncr;
        }

    }

    @ApiModel("WpsfBusinessCardMgtDto-SendReq")
    public record SendReq(
        String fnm,
        String phone,
        String cellphone

    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 명함첩 관리 -파트너고객연락처기본 Search Result Dto
    @ApiModel("WpsfBusinessCardMgtDto-SearchPreviewRes")
    public record SearchPreviewRes(
        String fnm,
        String realFpath

    ) {}

}
