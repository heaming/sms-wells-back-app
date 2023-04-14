package com.kyowon.sms.wells.web.closing.payment.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.closing.payment.dvo.WdcaEtcAnticipationAmtDvo;

// TODO: 테이블 미정의로 추후 재작업 필요
@Mapper
public interface WdcaEtcAnticipationAmtMapper {
    /**
    * 기타선수금 번호 채번
    * @param itgDpNo 통합입금번호
    * @return 기타선수금 번호 MAX + 1
    */
    String selectMaxEtcAnticipationNo(
        String itgDpNo
    );

    /**
    * 기타선수금 일련번호 채번
    * @param etcAtamNo 기타선수금번호
    * @return 기타선수금 일련번호 MAX + 1
    */
    int selectMaxEtcAnticipationSn(
        String etcAtamNo
    );

    /**
    * 기타선수금 기본 테이블 저장
    * @param dvo 기타선수금 DVO
    */
    int insertEtcBasic(
        WdcaEtcAnticipationAmtDvo dvo
    );

    /**
    * 기타선수금 처리내역 테이블 저장
    * @param dvo 기타선수금 DVO
    */
    int insertEtcProcess(
        WdcaEtcAnticipationAmtDvo dvo
    );

    /**
    * 기타선수금 잔액 수정
    * @param etcAtamNo 기타선수금번호
    */
    int updateEtcBasic(
        String etcAtamNo
    );
}
