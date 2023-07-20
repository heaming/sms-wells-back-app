package com.kyowon.sms.wells.web.closing.payment.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.closing.payment.dvo.WdcaEtcAnticipationAmtDvo;

@Mapper
public interface WdcaEtcAnticipationAmtMapper {
    /**
    * 기타선수금 번호 채번
    * @return 기타선수금 번호 MAX + 1
    */
    String selectMaxEtcAnticipationNo();

    /**
    * 기타선수금 일련번호 채번
    * @param dvo 기타선수금
    * @return 기타선수금 일련번호 MAX + 1
    */
    int selectMaxEtcAnticipationSn(WdcaEtcAnticipationAmtDvo dvo);

    String selectSapPdDvCd(WdcaEtcAnticipationAmtDvo dvo);

    String selectCustomerId(WdcaEtcAnticipationAmtDvo dvo);

    /**
    * 기타선수금 기본 테이블 저장
    * @param dvo 기타선수금
    */
    int insertEtcBasic(
        WdcaEtcAnticipationAmtDvo dvo
    );

    /**
    * 기타선수금 처리내역 테이블 저장
    * @param dvo 기타선수금
    */
    int insertEtcProcess(
        WdcaEtcAnticipationAmtDvo dvo
    );

    /**
    * 기타선수금 잔액 수정
    * @param dvo 기타선수금
    */
    int updateEtcBasic(WdcaEtcAnticipationAmtDvo dvo);
}
