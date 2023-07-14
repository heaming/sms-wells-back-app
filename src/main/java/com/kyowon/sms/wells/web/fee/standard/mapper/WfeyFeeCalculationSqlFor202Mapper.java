package com.kyowon.sms.wells.web.fee.standard.mapper;

import com.kyowon.sms.common.web.fee.standard.annotation.SqlMethodInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WfeyFeeCalculationSqlFor202Mapper {

    /**
     * M조직 지국장 조직배출수수료계산 SQL Mapper (W020016, 4)
     *
     * @param baseYm      기준년월
     * @param ogTpCd      조직유형코드
     * @param feeCd       수수료코드
     * @param feeTcntDvCd 수수료차수구분코드
     * @return 처리개수
     */
    @SqlMethodInfo(methodName = "M조직 지국장 조직배출수수료", methodExplanation = "M조직 지국장 조직배출수수료 계산 SQL 매퍼")
    Integer insertOrganizationEjectFee(String baseYm, String ogTpCd, String coCd, String feeCd, String dtaCrtFeeCd, String feeTcntDvCd);

    /**
     * M조직 지국장 신설지점수수료 SQL Mapper (W020025, 2)
     *
     * @param baseYm 기준년월
     * @param ogTpCd 조직유형코드
     * @param feeCd 수수료코드
     * @param feeTcntDvCd 수수료차수구분코드
     * @return 처리개수
     */
    @SqlMethodInfo(methodName = "M조직 지국장 신설지점수수료", methodExplanation = "M조직 지국장 신설지점수수료 계산 SQL 매퍼")
    Integer insertNewEstablishmentBranchFee(String baseYm, String ogTpCd, String coCd, String feeCd, String dtaCrtFeeCd, String feeTcntDvCd, String fnlFeeYn);

}
