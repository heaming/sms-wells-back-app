package com.kyowon.sms.wells.web.closing.expense.dvo;

import com.sds.sflex.common.docs.dto.AttachFileDto.AttachFile;
import com.sds.sflex.system.config.annotation.DBDecField;
import com.sds.sflex.system.config.annotation.DBEncField;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class WdcdCleanerReqeustDvo {

    private String clinrRgno; // 청소원등록번호
    private String rcpYm;/*접수년월*/
    private String bldCd;/*빌딩코드*/
    private String aplcDt;    // 신청일자;
    private String aplcnsNm; // 신청자 명
    private String clinrFxnAmt;  // 청소원고정금액
    private String taxDdctam;  // 세금공제금액
    private String clinrNm; // 청소원 명
    private String wrkStrtdt; // 근무시작일자
    private String wrkEnddt; // 근무종료일자
    private String frontRrnoEncr; // 주민등록번호 앞자리
    @DBEncField
    @DBDecField
    private String backRrnoEncr; // 주민등록번호 뒷자리
    private String rrnoEncr; // 주민등록번호 암호화
    private String locaraTno; // 지역번호
    @DBEncField
    @DBDecField
    private String exnoEncr; // 전화국별
    private String idvTno; // 개별전화번호
    private String zip; // 우편번호
    private String basAdr; // 기본주소
    private String dtlAdr; // 상세주소
    private String bnkCd; // 은행코드
    @DBEncField
    @DBDecField
    private String acnoEncr; // 계좌번호
    private String idfApnFileId; // 신분층 첨부파일
    private String bnkbApnFileId; // 통장사본 첨부파일
    private String cntrwApnFileId; // 계약서 첨부파일
    private String cntrLroreApnFileId; // 계약해지 첨부파일
    List<AttachFile> attachFiles1; // 신분층 첨부파일
    List<AttachFile> attachFiles2; // 통장사본 첨부파일
    List<AttachFile> attachFiles3; // 계약서 첨부파일
    List<AttachFile> attachFiles4; // 계약해지 첨부파일
    private String dtaDlYn; // 삭제여부
}
