package com.kyowon.sms.wells.web.closing.payment.mapper;

import com.kyowon.sms.wells.web.closing.payment.dvo.WdcaEtcAnticipationAmtDvo;
import org.apache.ibatis.annotations.Mapper;

// TODO: 테이블 미정의로 추후 재작업 필요
@Mapper
public interface WdcaEtcAnticipationAmtMapper {
    /**
    * 기타선수금 번호 채번
    * @return 기타선수금번호 MAX + 1
    */
    WdcaEtcAnticipationAmtDvo selectMaxEtcAnticipationAmtNo();

    /**
    * 기타선수금 테이블 저장
    * @param etcAtamNo 기타선수금번호
    * @param etcAtamOcDt 기타선수금발생일자
    * @param itgDpNo 통합입금번호
    * @param etcAtamAmt 기타선수금금액
    * @param etcAtamTpCd 기타선수금유형코드
    * @param etcAtamBlam 기타선수금잔액
    * @param cntrNo 계약번호
    * @param cntrSn 계약일련번호
    * @param coCd 회사코드
    * @param rveCd 수납코드
    * @param ichrPrtnrNo 담당파트너번호
    * @param custNo 고객번호
    * @return count
    */
    int insertEtcAnticipationAmt(
        String etcAtamNo,
        String etcAtamOcDt,
        String itgDpNo,
        String etcAtamAmt,
        String etcAtamTpCd,
        String etcAtamBlam,
        String cntrNo,
        String cntrSn,
        String coCd,
        String rveCd,
        String ichrPrtnrNo,
        String custNo
    );
}
