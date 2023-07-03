package com.kyowon.sms.wells.web.fee.standard.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WfeyEngineerCalculationMapper {

    /**
     * 수당일괄계산 수당데이터 삭제 전처리 메소드
     * W060001, W060002, W060003, W060004, W060005, W060006, W060007, W060008, W060010, W060011, W060012 수수료 삭제
     *
     * @param baseYm 기준년월
     * @param feeTcntDvCd 수수료차수구분코드
     * @return 처리개수
     */
    Integer deleteAllowances(String baseYm, String feeTcntDvCd);

}
