package com.kyowon.sms.wells.web.service.visit.dto;

import javax.validation.constraints.NotBlank;

import com.sds.sflex.common.utils.DbEncUtil;

import io.swagger.annotations.ApiModel;

public class WsnbIndividualServicePsDto {
    @ApiModel(value = "WsnbIndividualServicePsDto-SearchReq")
    public record SearchReq(
        String cntrNo,
        String cntrSn,
        String bcNo,
        String sppIvcNo
    ){}

    @ApiModel(value = "WsnbIndividualServicePsDto-SearchRes")
    public record SearchRes(
        String cntrNoDtl, /*계약상세번호*/
        String cstNm, /*고객성명*/
        String cstGdNm, /*고객등급*/
        String fxnPrtnrNm, /*고정방문자명*/
        String locaraTno, /*전화번호(지역전화번호)*/
        String exnoEncr, /*전화번호(전화국번호암호화)*/
        String idvTno, /*전화번호(개별전화번호)*/
        String cralLocaraTno, /*휴대전화번호(휴대지역전화번호)*/
        String mexnoEncr, /*휴대전화번호(휴대전화국번호암호화)*/
        String cralIdvTno, /*휴대전화번호(휴대개별전화번호)*/
        String addr, /*주소*/
        String cstUnuitmCn, /*고객특이사항내용*/
        String wkPrtnrNm, /*작성자*/
        String wkPrtnrNo, /*작성자사번*/
        String wkOgTpCd, /*작성자소속*/
        String wrteDt, /*작성일시*/
        String basePdNm, /*계약상품*/
        String pdNm, /*현재상품*/
        String frisuAsPtrmN, /*무상AS개월수*/
        String frisuBfsvcPtrmN, /*무상BS개월수*/
        String pdUswy, /*용도구분*/
        String sellTpNm, /*판매유형*/
        String svTpNm, /*서비스유형*/
        String cntrDt, /*계약일자*/
        String ogCd, /*지점코드*/
        String brmgrPrtnrNo, /*지점장사번*/
        String brmgrPrtnrNm, /*지점장*/
        String asnDt, /*배정일자*/
        String sellChnl, /*판매채널*/
        String prtnrNo, /*판매자사번*/
        String prtnrKnm, /*판매자*/
        String bfCntrNo, /*전상대코드*/
        String afCntrNo, /*후상대코드*/
        String istDt, /*설치일자*/
        String chngDt, /*교체일자*/
        String canDt, /*취소일자*/
        String svStpDt, /*중지일자*/
        String reqdDt, /*철거일자*/
        String asExprDt, /*AS만기*/
        String bsExprDt, /*BS만기*/
        String cpsDt, /*기변일자*/
        String mshJDt, /*멤버십시작일*/
        String mshWdwalDt, /*멤버십종료일*/
        String bcNo, /*바코드*/
        String pblBcNo, /*발행바코드*/
        String qrsRdmNo, /*맞춤가이드*/
        String ssPdctBcNo, /*삼성제조번호*/
        String ivcNo, /*송장번호*/
        String vstPrdNm, /*방문주기1*/
        String prdNm /* 방문주기2 */
    ){
        public SearchRes{
            exnoEncr = DbEncUtil.dec(exnoEncr);
            mexnoEncr = DbEncUtil.dec(mexnoEncr);
        }
    }
    @ApiModel(value = "WsnbIndividualServicePsDto-SearchHouseholdRes")
    public record SearchHouseholdRes(
        String svHshdNo,
        String cntrDtl,
        String cstNm,
        String pdNm,
        String adrZip,
        String cralLocaraTno,
        String mexnoEncr,
        String cralIdvTno,
        String locaraTno,
        String exnoEncr,
        String idvTno
    ){}

    @ApiModel(value = "WsnbIndividualServicePsDto-SearchContactRes")
    public record SearchContactRes(
        String cntcDt,
        String absncRsonKind,
        String absncRson,
        String ogTpCd,
        String prtnrNo,
        String prtnrNm,
        String cntcCn
    ){}

    @ApiModel(value = "WsnbIndividualServicePsDto-SearchStateRes")
    public record SearchStateRes(
        String cstSvAsnNo, //
        String svTp, // 유형
        String rcpDt, // 접수(배정)일자
        String svBizDclsf, //접수(배정)내역
        String reqDt, //요청약속일자
        String vstFshDt, // 처리일자
        String wkPrgsStat, // 처리결과
        String asCaus, // 처리내역
        String rtngdProcsTp, // 반품처리정보
        String fstVstFshDt, // 폐기일자: 출고확인일자
        String zipNo, // 우편번호
        String ogTp, // 구분(담당자)
        String ogNm, // 소속(담당자)
        String prtnrNo, // 사번(담당자)
        String prtnrNm, // 성명(담당자)
        String cralLocaraTno, // 휴대지역전화번호(담당자)
        String mexnoEncr, // 휴대전화국번호암호화(담당자)
        String cralIdvTno, // 휴대개별전화번호(담당자)
        String bldNm, // 소속빌딩(담당자)
        String bcNo, // 작업시바코드
        String imgYn, // 사진
        String istEnvrPhoPhDocId, // 설치환경사진경로
        String istKitPhoPhDocId, // 설치키트사진경로
        String istCelngPhoPhDocId, // 설치천장사진경로
        String istEnvrFileUid, // 설치환경사진UID
        String istKitFileUid, //설치키트사진UID
        String istCelngFileUid, // 설치천장사진UID
        String svHshdNo, // 가구화번호
        String svHshdNoCnt, // 가구화번호CNT
        String svBizHclsfCd, // 서비스대분류코드
        String svBizDclsfCd, // 서비스소분류코드
        String procStus, // 작업진행상태코드
        String cntrNo, //계약번호
        String cntrSn


    ){
        public SearchStateRes{
            mexnoEncr = DbEncUtil.dec(mexnoEncr);
        }
    }

    @ApiModel(value = "WsnbIndividualServicePsDto-SearchFarmRes")
    public record SearchFarmRes(
        String gubun,
        String cntrDtl,
        String cstNm,
        String pdNm,
        String sdingCntrDtl,
        String adrZip,
        String locaraTno,
        String exnoEncr,
        String idvTno,
        String cralLocaraTno,
        String mexnoEncr,
        String cralIdvTno
    ){
        public SearchFarmRes{
            exnoEncr = DbEncUtil.dec(exnoEncr);
            mexnoEncr = DbEncUtil.dec(mexnoEncr);
        }
    }

    @ApiModel(value = "WsnbIndividualServicePsDto-SearchCounselRes")
    public record SearchCounselRes(
        String cselSts,
        String cnslStDt,
        String cnslEdDt,
        String cnslTpHcsfCd,
        String cnslTpMcsfCd,
        String cnslTpLcsfCd,
        String pcpNm,
        String cselRstCd,
        String custResp,
        String clntDvNm,
        String cnslCn
    ){}

    @ApiModel(value = "WsnbIndividualServicePsDto-SearchDelinquentRes")
    public record SearchDelinquentRes(
        String psuminamt,
        String csuminamt,
        String tsuminamt,
        String pdlyamt,
        String cdlyamt,
        String tdlyamt
    ){}

    @ApiModel(value = "WsnbIndividualServicePsDto-SaveReq")
    public record SaveReq(
        @NotBlank
        String cntrNo,
        @NotBlank
        String cntrSn,
        String ogTpCd,
        String wkPrtnrNo,
        String cstUnuitmCn
    ){}
}
