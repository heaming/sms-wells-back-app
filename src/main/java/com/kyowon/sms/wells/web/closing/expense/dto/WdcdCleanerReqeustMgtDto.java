package com.kyowon.sms.wells.web.closing.expense.dto;

import com.sds.sflex.common.docs.dto.AttachFileDto.AttachFile;
import com.sds.sflex.common.utils.DbEncUtil;
import com.sds.sflex.system.config.masking.MaskRequired;
import com.sds.sflex.system.config.masking.MaskingType;
import io.swagger.annotations.ApiModel;
import lombok.Builder;
import org.apache.commons.lang.StringUtils;

import java.util.List;

public class WdcdCleanerReqeustMgtDto {

    // *********************************************************
    // Request Dto
    // *********************************************************
    // 청소원 관리 - 청소원 등록(신규변경)
    @Builder
    @ApiModel(value = "WdcdCleanerReqeustMgtDto-CodeRes")
    public record CodeRes(
        String bldCd,
        String bldNm
    ) {
    }

    // *********************************************************
    // Request Dto
    // *********************************************************
    // 청소원 관리 - 청소원 등록(신규변경)
    @Builder
    @ApiModel(value = "WdcdCleanerReqeustMgtDto-SaveReq")
    public record SaveReq(
        String clinrRgno, // 청소원등록번호
        String rcpYm,/*접수년월*/
        String bldCd,/*빌딩코드*/
        String aplcDt,    // 신청일자,
        String aplcnsNm, // 신청자 명
        String clinrFxnAmt,  // 청소원고정금액
        String taxDdctam,  // 세금공제금액
        String clinrNm, // 청소원 명
        String wrkStrtdt, // 근무시작일자
        String wrkEnddt, // 근무종료일자
        String frontRrnoEncr, // 주민등록번호 앞자리
        String backRrnoEncr, // 주민등록번호 뒷자리
        String locaraTno, // 지역번호
        String exnoEncr, // 전화국별
        String idvTno, // 개별전화번호
        String zip, // 우편번호
        String basAdr, // 기본주소
        String dtlAdr, // 상세주소
        String bnkCd, // 은행코드
        String acnoEncr, // 계좌번호
        String idfApnFileId, // 신분층 첨부파일
        String bnkbApnFileId, // 통장사본 첨부파일
        String cntrwApnFileId, // 계약서 첨부파일
        String cntrLroreApnFileId, // 계약해지 첨부파일
        List<AttachFile> attachFiles1, // 신분층 첨부파일
        List<AttachFile> attachFiles2, // 통장사본 첨부파일
        List<AttachFile> attachFiles3, // 계약서 첨부파일
        List<AttachFile> attachFiles4 // 계약해지 첨부파일
    ) {
        public SaveReq {

            backRrnoEncr = StringUtils.isNotEmpty(backRrnoEncr) ? DbEncUtil.dec(backRrnoEncr) : backRrnoEncr; // 계좌번호 암호화
        }
    }

    // *********************************************************
    // Request Dto
    // *********************************************************
    // 청소원 관리 - 청소원 등록(신규변경)
    @Builder
    @ApiModel(value = "WdcdCleanerReqeustMgtDto-FindRes")
    public record FindRes(
        String clinrRgno,
        String rcpYm,
        String bldCd,
        String aplcDt,
        @MaskRequired(type = MaskingType.NAME)
        String aplcnsNm,/*신청자*/
        String clinrFxnAmt,
        String taxDdctam,
        String dsbAmt,
        @MaskRequired(type = MaskingType.NAME)
        String clinrNm, /*청소원*/
        String wrkStrtdt,
        String wrkEnddt,
        String bryyMmdd,
        String frontRrnoEncr, // 주민번호 앞자리
        String backRrnoEncr, // 주민번호 뒷자리
        String locaraTno,
        @MaskRequired(type = MaskingType.PHONE)
        String exnoEncr,
        String idvTno,
        String zip,
        String basAdr,
        String dtlAdr,
        String bnkCd,
        String acnoEncr,
        String idfApnFileId,
        String bnkbApnFileId,
        String cntrwApnFileId,
        String cntrLroreApnFileId
    ) {
        public FindRes {
            exnoEncr = StringUtils.isNotEmpty(exnoEncr) ? DbEncUtil.dec(exnoEncr) : exnoEncr; // 전화국번호 복호화
        }
    }
}
