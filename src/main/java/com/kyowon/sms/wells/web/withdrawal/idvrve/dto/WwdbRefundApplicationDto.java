package com.kyowon.sms.wells.web.withdrawal.idvrve.dto;

import java.util.List;

import com.sds.sflex.common.docs.dto.AttachFileDto;
import com.sds.sflex.common.utils.DbEncUtil;
import com.sds.sflex.common.utils.StringUtil;

import com.sds.sflex.system.config.masking.MaskRequired;
import com.sds.sflex.system.config.masking.MaskingType;
import io.swagger.annotations.ApiModel;

/**
 * <pre>
 * 환불 신청 현황 DTO
 * </pre>
 *
 * @author kimoon.lim
 * @since 2023-10-31
 */
public class WwdbRefundApplicationDto {

    // *********************************************************
    // Request Dto
    // *********************************************************
    // 환불 신청 현황 메인 Search Request Dto
    /*TODO:환불신청메인 Req*/
    @ApiModel(value = "WwdbRefundApplicationDto-SearchRefundApplicationReq")
    public record SearchRefundApplicationReq(
        String searchDt, // 접수일자 구분 ( 01: 접수일자 , 02: 실적일자, 03: 처리일자)
        String startDay, // 접수일자 시작일
        String endDay, // 접수일자 종료일
        String rfndStatCd, // 환불상태
        //        String rfndDsbDvCdCshBltf, // 처리구분 2023-12-23 조건삭제처리
        String cntrNo, // 계약번호
        String cntrSn, // 계약일련번호
        String cstNo // 고객번호
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 환불 신청 현황 메인 Search Result Dto
    @ApiModel(value = "WwdbRefundApplicationDto-SearchRefundApplicationRes")
    public record SearchRefundApplicationRes(
        String rfndAkStatCd, // 환불상태
        String rfndAkDtlCd, // 환불상세
        String rfndAkDtm, // 접수일자
        String rfndAkNo, // 접수번호
        String rfndPsbAmt, // 환불가능금액
        String rfndAkAmt, // 환불요청금액
        String cntrNo, // 계약기본
        String cntrSn, // 계약일련번호
        String cstNo, /*고객번호*/
        String cntrDtlNo, /* 계약상세번호 */
        String rfndCshAkAmt, /* 현금환불금액 */
        String rfndCardAkAmt, /* 카드환불금액 */
        String crdcdFeeAmt, /* 카드수수료 */
        String rfndBltfAkAmt, /* 전금금액 */
        String fnlMdfcUsrNm, /* 신청자 */
        String fnlMdfcUsrId, /* 번호 */
        String rfndFshDt, /* 승인일자 */
        String rfndProcsCn, /* 처리내용 */
        String rfndRsonCd, /* 환불사유 */
        String rfndRsonCn, /* 환불내용 */
        String rfndEvidMtrFileId /* 첨부파일:환불증빙자료파일 */
    ) {}

    // *********************************************************
    // Request Dto
    // *********************************************************
    // 환불신청팝업 호출시(p01,p03) 환불요청기본정보 Search Request Dto
    @ApiModel(value = "WwdbRefundApplicationDto-SearchRefundReq")
    public record SearchRefundReq(
        String cntrNo, // 계약번호
        String cntrSn, // 계약일련번호
        String rfndAkNo, // 접수번호
        String rfndAkStatCd // 환불상태
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 환불신청팝업 호출시(p01,p03) 환불요청기본정보 Search Result Dto
    @ApiModel(value = "WwdbRefundApplicationDto-SearchRefundRes")
    public record SearchRefundRes(
        String rfndAkNo, // 접수번호
        String arfndYn, // 선환불여부
        String cshRfndFnitCd, // 지급은행
        String cshRfndAcnoEncr, // 계좌번호
        String cshRfndAcownNm, // 예금주
        String rfndRveDt, // 환불수납일자
        String rfndPerfDt, // 환불실적일자
        String rfndDsbDt, // 환불지급일자
        String rfndProcsDvCd, // 환불처리구분코드
        String rfndProcsCn // 환불처리내용
    ) {
        public SearchRefundRes {
            if (!StringUtil.isEmpty(cshRfndAcnoEncr)) {
                cshRfndAcnoEncr = DbEncUtil.dec(cshRfndAcnoEncr); // 계좌번호 복호화
            }
        }
    }

    // *********************************************************
    // Request Dto
    // *********************************************************
    // 환불신청팝업 - 환불상세 Search Request Dto
    /* TODO: 환불신청팝업 - 환불상세 */
    @ApiModel(value = "WwdbRefundApplicationDto-SearchRefundDetailReq")
    public record SearchRefundDetailReq(
        String cntrNo, // 계약번호
        String cntrSn, // 계약일련번호
        String rfndAkNo, // 접수번호
        String rfndAkStatCd, // 환불상태
        String cntrStartDay, // 승인/입금일자 - from
        String cntrEndDay // 승인/입금일자 - to

    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 환불신청팝업 - 환불상세 Search Result Dto
    @ApiModel(value = "WwdbRefundApplicationDto-SearchRefundDetailRes")
    public record SearchRefundDetailRes(
        String rveNo, // 수납상세.수납번호
        String rveSn, // 수납일련번호
        String cntrNo, // 계약번호
        String cntrSn, // 계약일련번호
        String cntrDtlNo, // 계약상세번호
        String dpDt, // 입금일자
        String dpMesCd, // 입금수단
        String rveDvCd, /* 입금유형 -> 수납유형 ( 사양서 , 화면설계서 결과에 맞게 조정 ) */
        String dpAmt, // 입금금액
        String bltfAdd, // 전금추가
        String rfndPsbAmt, // 환불가능금액
        /* default 0값 (환불요청값) */
        String rfndCshAkAmt, // 현금환불금액
        String rfndCardAkAmt, // 카드환불요청금액
        String crdcdFeeAmt, // 카드수수료
        String rfndBltfAkAmt, // 전금금액
        String rfndAkNo, // 접수번호
        String fnitCd, // 금융기관코드
        String fnitNm, // 금융기관명
        //        @MaskRequired(type = MaskingType.ACCOUNT)
        String acCrNo, // 계좌번호/카드번호
        String cstNo, // 고객번호
        String crdcdFer, // 수수료율 ( 저장 )
        String dpTpCd, // 입금유형코드
        String dpDvCd, /*입금구분*/
        String rfndRsonCd, // 환불사유
        String rfndRsonCn // 환불내용
    ) {
        public SearchRefundDetailRes {
            if (!StringUtil.isEmpty(acCrNo)) {
                acCrNo = DbEncUtil.dec(acCrNo); // 계좌/신용카드 번호 복호화
            }
        }
    }
    /* 환불신청팝업 - 환불상세 END*/

    // *********************************************************
    // Request Dto
    // *********************************************************
    // 환불 신청 팝업 - 전금 상세 Search Request Dto
    @ApiModel(value = "WwdbRefundApplicationDto-SearchRefundBalanceTransferReq")
    public record SearchRefundBalanceTransferReq(
        String cntrNo, // 계약번호
        String cntrSn, // 계약일련번호
        String rfndAkNo, // 접수번호
        String rfndAkStatCd // 환불상태
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 환불 신청 팝업 - 전금 상세 Search Result Dto
    @ApiModel(value = "WwdbRefundApplicationDto-SearchRefundBalanceTransferRes")
    public record SearchRefundBalanceTransferRes(
        String cntrNo, // 계약번호
        String cntrSn, // 계약일련번호
        String cntrDtlNo, // 계약상세번호
        String rveNo, // 수납상세.수납번호
        String rveSn, // 수납일련번호
        String dpDt, // 입금일자
        String dpMesCd, // 입금수단
        String dpAmt, // 입금금액
        String rfndBltfAkAmt, // 전금금액
        String bltfOjCntrNo, // 전금계약번호
        String bltfOjCntrSn, // 전금일련번호
        String bltfOjCntrDtlNo, // 전금계약상세번호
        String sellTpCd, // 판매유형코드
        String bltfRfndMbDvCd, // 회원구분
        String rfndEvidMtrFileId, // 증빙자료 파일첨부
        String atthDocId, // 첨부문서ID
        Integer atthDocIdNumberOfFiles, // 첨부파일건수
        String rfndEvidMtrFileNm, // 전금자료명(업로드시 버튼형식으로나오면 사용)
        String rfndAkNo, // 접수번호
        String cstNo // 고객번호
    ) {}
    /* TODO: 전금상세  END*/

    // TODO:환불신청팝업 P01 Req
    // *********************************************************
    // Request Dto
    // *********************************************************
    // 계약 상세 목록 조회 Search Request Dto
    @ApiModel(value = "WwdbRefundApplicationDto-SearchRefundContractDetailReq")
    public record SearchRefundContractDetailReq(
        String cntrStartDay, // 승인/입금일자 - from
        String cntrEndDay, // 승인/입금일자 - to
        String copnDvCd, //  고객유형
        String cntrNo, // 계약번호
        String cntrSn, //계약일련번호
        String cstNo, // 고객번호
        String crdcdNo, // 카드번호
        String acnoEncr, // 계좌번호
        String encr, /* 카드/계좌 코드 */
        String rfndAkNo // 접수번호
    ) {
        public SearchRefundContractDetailReq {
            if (!StringUtil.isEmpty(crdcdNo)) {
                crdcdNo = DbEncUtil.enc(crdcdNo); // 계좌번호 암호화
            }
            if (!StringUtil.isEmpty(acnoEncr)) {
                acnoEncr = DbEncUtil.enc(acnoEncr); // 카드번호 암호화
            }
        }
    }

    // TODO:환불신청팝업 P01 RES
    // *********************************************************
    // Result Dto
    // *********************************************************
    // 계약 상세 목록 조회 Search Result Dto
    @ApiModel(value = "WwdbRefundApplicationDto-SearchRefundContractDetailRes")
    public record SearchRefundContractDetailRes(
        String cntrNo, // 계약번호
        String cntrSn, // 계약일련번호
        String cntrDtlNo, // 계약상세번호
        String sellTpCd, // 판매유형코드
        String cstNo, // 고객번호
        String rfndPsbAmt, // 환불가능금액
        String pdCd, // 상품코드
        String pdNm, // 상품명
        String cstKnm, // 고객 한글명
        String dpAmt, // 입금금액
        String dlqAddAmt, // 연체가산금액
        String mmIstmAmt, // 월할부금액
        String borAmt, // 위약금액
        String svAmt, // 서비스금액
        String sellAmt, // 판매금액
        String cntrStartDay, // 승인/입금일자 - from
        String cntrEndDay // 승인/입금일자 - to
    ) {}

    // *********************************************************
    // Request Dto
    // *********************************************************
    // 환불 신청 팝업 저장 Save Request Dto
    @ApiModel(value = "WwdbRefundApplicationDto-SaveReq")
    public record SaveReq(
        SaveBaseReq saveBaseReq,
        List<SaveCntrReq> saveCntrReqs,
        List<SaveDtlReq> saveDtlReqs,
        List<SaveBltfReq> saveBltfReqs
    ) {

    }

    // *********************************************************
    // Request Dto
    // *********************************************************
    // 환불 요청 기본 데이터 (팝업) Save Request Dto
    @ApiModel(value = "WwdbRefundApplicationDto-SaveBaseReq")
    public record SaveBaseReq(
        String kwGrpCoCd, /* 법인 구분 */
        String rfndAkNo, /* 환불요청ID */
        String aftRfndAkNo, /* 채번용 키값 */
        String rfndAkDtm, // 접수일자
        String rfndAkPrtnrOgTpCd, // 환불요청파트너조직유형코드
        String rfndAkPrtnrNo, // 환불요청파트너번호
        String arfndYn, // 선환불여부
        String cshRfndFnitCd, // 지급은행
        String cshRfndAcnoEncr, // 계좌번호
        String cshRfndAcownNm, // 예금주
        String rfndCshAkSumAmt, // 환불현금요청합계금액
        String rfndCardAkSumAmt, // 환불카드요청합계금액
        String rfndBltfAkSumAmt, // 환불전금요청합계금액
        String crdcdFeeSumAmt, // 신용카드수수료합계금액
        String rfndAkStatCd, // 환불상태
        String rfndProcsDvCd, // 환불처리구분코드
        String rfndProcsCn, // 환불처리내용
        String rfndProcsUsrId, // 환불처리사용자ID
        String rveCoCd, // 수납회사코드
        /* 처리정보 : 추가데이터 */
        String rveDt, /* 수납일자 */
        String perfDt, /* 실적일자 */
        String dsbDt, /* 지급일자*/
        String procsDv, /* 처리구분 */
        String procsCn /* 처리내용 */
    ) {
        //cshRfndAcnoEncr
        public SaveBaseReq {
            if (!StringUtil.isEmpty(cshRfndAcnoEncr)) {
                cshRfndAcnoEncr = DbEncUtil.enc(cshRfndAcnoEncr); // 계좌번호 암호화
            }
        }
    }

    // *********************************************************
    // Request Dto
    // *********************************************************
    // 환불 요청 계약 Save Request Dto
    @ApiModel(value = "WwdbRefundApplicationDto-SaveCntrReq")
    public record SaveCntrReq(
        String cntrNo, // 계약번호
        String cntrSn, // 계약일련번호
        String cstNo, // 고객번호
        String cstKnm, // 고객 한글명
        String cntrDtlNo, // 계약상세번호
        String rveNo, // 수납상세.수납번호
        String borAmt, // 위약금액
        String dlqAddAmt, // 연체가산금액
        String dpAmt, // 입금금액
        String mmIstmAmt, // 월할부금액
        String rfndPsbAmt, // 환불가능금액
        String sellAmt, // 판매금액
        String svAmt, // 서비스금액
        String rfndAkNo, // 환불번호
        String aftRfndAkNo // 채번용 키값
    ) {}

    // *********************************************************
    // Request Dto
    // *********************************************************
    // 환불 요청 상세 데이터(팝업) Save Request Dto
    @ApiModel(value = "WwdbRefundApplicationDto-SaveDtlReq")
    public record SaveDtlReq(
        String kwGrpCoCd, /* 법인 구분 */
        String rfndAkNo, // 환불번호
        String aftRfndAkNo, /* 채번용 키값 */
        String cntrNo, /* 계약번호 */
        String cntrSn, /* 계약일련번호 */
        String rveNo, // 수납상세.수납번호
        String rveSn, // 수납일련번호
        String cstNo, // 고객번호
        String rfndDvCd, // 환불구분. 01.전체 환불, 02.부분 환불
        String rfndDsbDvCd, // 환불구분. 환불지급구분코드 01.현금환불, 02.카드환불, 03.전금
        String cshRfndDvCd, // 현금환불 구분. 현금환불구분코드 01.선환불, 02.일반환불, 03.카드현금
        String rfndCshAkAmt, // 현금환불금액
        String rfndCardAkAmt, // 환불요청금액(카드)
        String crdcdFeeAmt, // 카드수수료
        String crdcdFer, // 수수료율 ( 저장 )
        String rfndBltfAkAmt, /* 관련 전금요청금액 총 합계 */
        String rfndRsonCd, // 환불사유
        String rfndRsonCn, // 환불내용
        String dtaDlYn, // 데이터 삭제여부

        String rfndRcpNo, /*환불접수번호*/
        String rfndRcpPhCd, /*환불접수경로코드*/
        String rfndRveDt, /*환불수납일자*/
        String rfndPerfDt, /*환불실적일자*/
        String rfndDsbDt, /*환불지급일자*/
        String rfndProcsCn /*환불처리내용*/

    ) {}

    // *********************************************************
    // Request Dto
    // *********************************************************
    // 환불 요청 전금 상세 데이터 (팝업) Save Request Dto
    @ApiModel(value = "WwdbRefundApplicationDto-SaveBltfReq")
    public record SaveBltfReq(
        String rowState,
        String kwGrpCoCd, /* 법인 구분 */
        String rfndAkNo, /* 환불번호 */
        String aftRfndAkNo, /* 채번용 키값 */
        String cntrNo, // 계약번호
        String cntrSn, // 계약일련번호
        String rveNo, // 수납상세.수납번호
        String rveSn, // 수납일련번호
        String bltfOjCntrNo, // 전금계약번호
        String bltfOjCntrSn, // 전금일련번호
        String cstNo, // 고객번호
        String rfndBltfAkAmt, // 전금금액
        String bltfRfndDvCd, // 전금구분
        String bltfRfndTpCd, // 전금환불유형코드
        String bltfRfndMbDvCd, // 회원구분
        String rfndEvidMtrFileId, // 증빙자료 파일첨부
        String dtaDlYn, // 데이터삭제여부
        List<AttachFileDto.AttachFile> attachFiles
    ) {

    }

    // *********************************************************
    // Request Dto
    // *********************************************************
    // 임시 저장용 Save Request Dto
    /* TODO: 임시저장  저장용 Req*/
    @ApiModel(value = "WwdbRefundApplicationDto-SearchRefundTempSaveReq")
    public record SearchRefundTempSaveReq(
        String rowState, // 생성-수정-삭제용도
        String flag, // 환불-전금 그리드 구분
        String arfndYn, // 선환불여부
        String rfndAkNo, // 환불요청번호

        /* 환불상세 */
        String rfndCshAkAmt, //  환불요청금액 (현금)
        String rfndCardAkAmt, //  환불요청금액(카드)
        String crdcdFeeAmt, // 카드수수료(전액)
        String crdcdFer, // 수수료율 ( 저장 )
        String totRfndBltfAkAmt, // 전금요청금액
        String rfndRsonCd, // 환불사유
        String rfndRsonCn, // 환불내용

        String ogTpCd, /* 조직유형코드 */
        String prtnrNo, /* 파트너번호 */
        String fnitCd, /* 금융기관코드 */
        String acownNm, /* 계좌주명 */
        String acnoEncr, /* 계좌번호암호화 */
        /* 전금상세 */
        String rfndBltfAkAmt, //  전금금액
        String bltfOjCntrNo, // 전금계약번호
        String bltfOjCntrSn, // 전금일련번호
        String bltfOjCntrDtlNo, // 전금계약상세번호
        String bltfRfndMbDvCd, // 회원구분
        String rfndEvidMtrFileId, // 증빙자료 파일첨부

        /* 환불요청기본 */
        String totRfndCshAkAmt, // 총 현금금액
        String totRfndCardAkAmt, // 총 카드금액
        String totCrdcdFeeAmt, // 총 수수료금액
        String totRfndEtAmt // 총 합계
    ) {}

    /* TODO: 그리드에서 팝업진입시 */
    // *********************************************************
    // Request Dto
    // *********************************************************
    // 환불신청팝업 - 계약상세(메인에서 조회) Search Request Dto
    @ApiModel(value = "WwdbRefundApplicationDto-SearchRefundBaseReq")
    public record SearchRefundBaseReq(
        String rfndAkNo, /* 환불번호 */
        String rfndAkStatCd, // 환불상태
        String cntrNo, // 계약번호
        String cntrSn // 계약일련번호
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 환불신청팝업 - 계약상세(메인에서 조회) Search Result Dto
    @ApiModel(value = "WwdbRefundApplicationDto-SearchRefundBaseRes")
    public record SearchRefundBaseRes(
        String cntrNo /* ※미수정*/ // 계약번호
    ) {}
    /* TODO: 그리드에서 팝업진입시 END */

    //    @ApiModel(value = "WwdbRefundApplicationDto-SaveRefundReq")
    //    public record SaveRefundReq(
    //        String rfndRcpNo, // 환불접수번호
    //        String cntrNo, // 계약번호
    //        String cntrSn,
    //        String cstNo, // 고객번호
    //        String exRfndRsonCn, // 예외환불 사유
    //        String rfndCshAmt, // 현금 환불금액
    //        String rfndCardAmt, // 카드 환불금액
    //        String rfndBltfAmt, // 전금 금액
    //        String rfndPsbResAmt, // 환불가능 잔액
    //        String rfndEtAmt, // 총환불가능 잔액
    //        List<SaveRefundDetail> details // 추가 버튼 누르면 추가로 생성되는 부분
    //    ) {}

    @ApiModel(value = "WwdbRefundApplicationDto-RefundDetail")
    public record SaveRefundDetail(
        String rfndRcpDtlSn, /* 환불접수상세일련번호 */
        String rveNo, // 수납상세.수납번호
        String rfndDvCd, // 환불구분. 01.전체 환불, 02.부분 환불
        String rfndDsbDvCd, // 환불구분. 환불지급구분코드 01.현금환불, 02.카드환불, 03.전금
        String cshRfndDvCd, // 현금환불 구분. 현금환불구분코드 01.선환불, 02.일반환불, 03.카드현금
        String rfndAcDvCd, // 환불계좌 구분. 환불계좌구분코드 01.기입금 계좌, 02.신규 계좌
        String cshRfndFnitCd, // 지급은행
        String cshRfndAcnoEncr, // 계좌번호
        String cshRfndAcownNm, // 예금주
        String cshRfndAdrsDvCd, // 수취인
        String cshRfndDlgpsNm, // 대표자 확인
        String startDay, // 승인일 시작
        String endDay, // 승인일 종료
        String rfndAkAmt, // 환불신청금액, 전금 요청금액(원)
        String rfndDsbAmt, // 실지급액 (원)
        //        String rfndAkAmt2, // 전금 요청금액(원)
        String rfndRsonCd, // 환불사유코드

        String cardRfndFnitCd, // 카드구분
        String cardRfndCrcdnoEncr, // 카드번호
        String cardRfndFer, // 카드 공제액
        String rfndRsonCn, // 기타 환불사유 입력란

        String bltfRfndDvCd, // 전금구분
        String bltfRfndMbDvCd, // 회원구분
        String bltfOjCntrSn, // 전금일련번호
        String bltfBfVacNoEncr, // 전금전 가상계좌
        String bltfAfVacNoEncr, // 전금후 가상계좌
        String rfndEvidMtrFileId // 증빙자료 파일첨부

    ) {
        public SaveRefundDetail {
            if (!StringUtil.isEmpty(cshRfndAcnoEncr)) {
                cshRfndAcnoEncr = DbEncUtil.enc(cshRfndAcnoEncr); // 계좌번호 암호화
            }
            if (!StringUtil.isEmpty(cardRfndCrcdnoEncr)) {
                cardRfndCrcdnoEncr = DbEncUtil.enc(cardRfndCrcdnoEncr); // 카드번호 암호화
            }
        }

    }

    @ApiModel(value = "WwdbRefundApplicationDto-RefundBasic")
    public record RefundBasic(
        String rfndRcpNo, // 환불접수번호
        String cntrNo, // 계약번호
        String cntrSn, // 계약일련번호
        String cstNo, // 고객번호
        String exRfndRsonCn, // 예외환불 사유

        String rfndCshAmt, // 현금 환불금액
        String rfndCardAmt, // 카드 환불금액
        String rfndBltfAmt, // 전금 금액
        String rfndPsbResAmt, // 환불가능 잔액
        String totRfndEtAmt, // 총환불가능 잔액

        String rfndRveDt, // 수납일자
        String rfndPerfDt, // 실적일자
        String rfndDsbDt, // 지급일자
        String rfndStatCd, // 처리구분 (반려, 승인)
        String rfndProcsCn // 처리내용
    ) {}

    // *********************************************************
    // Request Dto
    // *********************************************************
    // 환불 삭제 Remove Request Dto
    @ApiModel(value = "WwdbRefundApplicationDto-removeReq")
    public record removeReq(
        String rfndAkNo /* 환불요청번호 */
    ) {}

    // TODO: SaveEnd

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 환불 신청 현황 메인 Search Result Dto
    @ApiModel(value = "WwdbRefundApplicationDto-SearchRefundApplicationInfoRes")
    public record SearchRefundApplicationInfoRes(
        RefundBasic basic,
        List<RefundDetail> details // 추가 버튼 누르면 추가로 생성되는 부분
    ) {}

    @ApiModel(value = "WwdbRefundApplicationDto-RefundDetail")
    public record RefundDetail(
        String rfndRcpNo, // 환불접수번호
        String rfndRcpDtlSn, /* 환불접수상세일련번호 */
        String rveNo, // 수납상세.수납번호
        String rfndDvCd, // 환불구분. 01.전체 환불, 02.부분 환불
        String rfndDsbDvCd, // 환불구분. 환불지급구분코드 01.현금환불, 02.카드환불, 03.전금
        String cshRfndDvCd, // 현금환불 구분. 현금환불구분코드 01.선환불, 02.일반환불, 03.카드현금
        String rfndAcDvCd, // 환불계좌 구분. 환불계좌구분코드 01.기입금 계좌, 02.신규 계좌
        String cshRfndFnitCd, // 지급은행
        String cshRfndAcnoEncr, // 계좌번호
        String cshRfndAcownNm, // 예금주
        String cshRfndAdrsDvCd, // 수취인
        String cshRfndDlgpsNm, // 대표자 확인
        String startDay, // 승인일 시작
        String endDay, // 승인일 종료
        String rfndAkAmt, // 환불신청금액, 전금 요청금액(원)
        String rfndDsbAmt, // 실지급액 (원)
        //        String rfndAkAmt2, // 전금 요청금액(원)
        String rfndRsonCd, // 환불사유코드

        String cardRfndFnitCd, // 카드구분
        String cardRfndCrcdnoEncr, // 카드번호
        String cardRfndFer, // 카드 공제액
        String rfndRsonCn, // 기타 환불사유 입력란

        String bltfRfndDvCd, // 전금구분
        String bltfRfndMbDvCd, // 회원구분
        String bltfOjCntrSn, // 전금일련번호
        String bltfBfVacNoEncr, // 전금전 가상계좌
        String bltfAfVacNoEncr, // 전금후 가상계좌
        String rfndEvidMtrFileId // 증빙자료 파일첨부

    ) {
        public RefundDetail {
            if (!StringUtil.isEmpty(cshRfndAcnoEncr)) {
                cshRfndAcnoEncr = DbEncUtil.dec(cshRfndAcnoEncr); // 계좌번호 암호화
            }
            if (!StringUtil.isEmpty(cardRfndCrcdnoEncr)) {
                cardRfndCrcdnoEncr = DbEncUtil.dec(cardRfndCrcdnoEncr); // 카드번호 암호화
            }
        }

    }

    // *********************************************************
    // Request Dto
    // *********************************************************
    // 환불 상세 Request Dto
    @ApiModel(value = "WwdbRefundApplicationDto-EditRefundReq")
    public record EditRefundReq(
        RefundBasic basic,
        List<RefundDetail> details // 추가 버튼 누르면 추가로 생성되는 부분
    ) {}

    // *********************************************************
    // Request Dto
    // *********************************************************
    // 환불 상세 Request Dto
    @ApiModel(value = "WwdbRefundApplicationDto-SearchRefundApplicationDetailReq")
    public record SearchRefundApplicationDetailReq(
        String rfndStatCd, // 환불상태코드
        String cntrNo, // 계약번호
        String cntrSn, // 계약일련번호
        String rfndRcpNo, // 환불접수번호
        String rfndRcpDtlSn // 환불접수일련번호
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 환불 신청 컨텍 이력 사항 Search Result Dto
    @ApiModel(value = "WwdbRefundApplicationDto-SearchRefundApplicationConnectHistoryRes")
    public record SearchRefundApplicationConnectHistoryRes(
        String cttRcpDtm, // 컨택일시
        String cttChnlTpCd, // 상담경로
        String prtnrKnm, // 상담자
        String cttPsicId, // 번호
        String cttMoCn // 상담내용

    ) {}

    // *********************************************************
    // Request Dto
    // *********************************************************
    // 환불 신청 팝업 임시 저장 Search Request Dto
    @ApiModel(value = "WwdbRefundApplicationDto-SaveApprovalReq")
    public record SaveApprovalReq(
        SaveBaseReq saveBaseReq,
        List<SaveDtlReq> saveDtlReqs,
        List<SaveBltfReq> saveBltfReqs
    ) {}

}
