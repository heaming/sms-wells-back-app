package com.kyowon.sms.wells.web.closing.payment.mapper;

import com.kyowon.sms.wells.web.closing.payment.dvo.WdcaBusinessAnticipationAmtDvo;
import org.apache.ibatis.annotations.Mapper;

// TODO: 테이블 미정의로 추후 재작업 필요
@Mapper
public interface WdcaBusinessAnticipationAmtMapper {
    /**
    * SAP입금유형코드기준 테이블에서 SAP입금유형코드 조회
    * @param coCd 회사코드
    * @param apyStrtdt 입금마감일자
    * @param apyEnddt 입금마감일자
    * @param rveTpCd 수납유형코드
    * @param dpKndCd 입금종류코드
    * @param dpTpCd 입금유형코드
    */
    WdcaBusinessAnticipationAmtDvo selectSapDepositTypeCode(
        String coCd,
        String apyStrtdt,
        String apyEnddt,
        String rveTpCd,
        String dpKndCd,
        String dpTpCd
    );

    /**
    * SAP상품구분코드기준 테이블에서 SAP상품구분코드, SAP상품항목코드 조회
    * @param coCd 회사코드
    * @param apyStrtdt 입금마감일자
    * @param apyEnddt 입금마감일자
    * @param rveTpCd 수납유형코드
    * @param dpKndCd 입금종류코드
    * @param dpTpCd 입금유형코드
    * @param pdHcsfId 상품대분류ID
    * @param pdMclsfId 상품중분류ID
    * @param pdLclsfId 상품분류ID
    * @param sellTpCd 판매유형코드
    */
    WdcaBusinessAnticipationAmtDvo selectSapProductClassificationCode(
        String coCd,
        String apyStrtdt,
        String apyEnddt,
        String rveTpCd,
        String dpKndCd,
        String dpTpCd,
        String pdHcsfId,
        String pdMclsfId,
        String pdLclsfId,
        String sellTpCd
    );

    /**
    * 영업선수금 테이블 저장
    * @param rveNo 수납번호
    * @param rveSn 수납일련번호
    * @param dpClDt 입금마감일자
    * @param cntrNo 계약번호
    * @param cntrSn 계약일련번호
    * @param coCd 회사코드
    * @param rveCd 수납코드
    * @param ichrPrtnrNo 담당파트너번호
    * @param rveAmt 수납금액
    * @param sapDpTpCd SAP입금유형코드
    * @param sapPdDvCd SAP상품구분코드
    * @param sapPdAtcCd SAP상품항목코드
    * @param bnkCd 은행코드
    * @param cdcoCd 카드사코드
    * @param etcAtamNo 기타선수금번호
    * @param etcAtamSn 기타선수금일련번호
    * @param rveTpCd 수납유형코드
    * @param dpKndCd 입금종류코드
    * @param dpTpCd 입금유형코드
    * @return count
    */
    int insertBusinessAnticipationAmt(
        String rveNo,
        //@NotBlank
        String rveSn,
        //@NotBlank
        String dpClDt,
        //@NotBlank
        String cntrNo,
        //@NotBlank
        String cntrSn,
        //@NotBlank
        String coCd,
        //@NotBlank
        String rveCd,
        //@NotBlank
        String ichrPrtnrNo,
        //@NotBlank
        Long rveAmt,
        String sapDpTpCd,
        String sapPdDvCd,
        String sapPdAtcCd,
        //@NotBlank
        String bnkCd,
        //@NotBlank
        String cdcoCd,
        //@NotBlank
        String etcAtamNo,
        //@NotBlank
        String etcAtamSn,
        //@NotBlank
        String rveTpCd,
        //@NotBlank
        String dpKndCd,
        //@NotBlank
        String dpTpCd
    );
}
