package com.kyowon.sms.wells.web.fee.standard.mapper;

import com.kyowon.sms.common.web.fee.standard.annotation.SqlMethodInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WfeyFeeCalculationSqlFor301Mapper {

    /**
     * 홈마스터 파트너 조기정착수수료 계산 SQL Mapper (W030016, 2)
     *
     * @param baseYm 기준년월
     * @param ogTpCd 조직유형코드
     * @param feeCd 수수료코드
     * @param feeTcntDvCd 수수료차수구분코드
     * @return 처리개수
     */
    @SqlMethodInfo(methodName = "홈마스터 파트너 조기정착수수료", methodExplanation = "홈마스터 파트너 조기정착수수료 계산 SQL 매퍼")
    Integer insertEarlSettleFee(String baseYm, String ogTpCd, String coCd, String feeCd, String dtaCrtFeeCd, String feeTcntDvCd, String fnlFeeYn);

}
