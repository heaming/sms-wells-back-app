package com.kyowon.sms.wells.web.closing.payment.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.closing.payment.dvo.WdcaBusinessAnticipationAmtDvo;

// TODO: 테이블 미정의로 추후 재작업 필요

@Mapper
public interface WdcaBusinessAnticipationAmtMapper {
    /**
    * 영업선수금 기본 테이블 확인
    * @param rveNo 수납번호
    * @param rveSn 수납일련번호
    * @return count
    */
    int selectBusinessBasicCheck(
        String rveNo,
        String rveSn
    );

    /**
    * 영업선수금 기본 테이블 저장
    * @param dvo 영업선수금 DVO
    */
    int insertBusinessBasic(
        WdcaBusinessAnticipationAmtDvo dvo
    );

    /**
    * 영업선수금 처리내역 저장
    * @param dvo 영업선수금 DVO
    */
    int insertBusinessProcess(
        WdcaBusinessAnticipationAmtDvo dvo
    );

    /**
    * 영업선수금 기본 수정
    * @param rveNo 수납번호
    * @param rveSn 수납일련번호
    */
    int updateBusinessBasic(
        String rveNo,
        String rveSn
    );
}
