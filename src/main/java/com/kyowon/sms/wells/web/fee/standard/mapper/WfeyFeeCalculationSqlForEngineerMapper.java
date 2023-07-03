package com.kyowon.sms.wells.web.fee.standard.mapper;

import com.kyowon.sms.common.web.fee.standard.annotation.SqlMethodInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WfeyFeeCalculationSqlForEngineerMapper {

    /**
     * 엔지니어 수당계산 SQL Mapper (W060001, W060002, W060003, W060004, W060005, W060006, W060007, W060008, W060010, W060011, W060012)
     *
     * @param baseYm
     * @param ogTpCd
     * @param coCd
     * @param fnlFeeYn
     * @param feeTcntDvCd
     * @return
     */
    @SqlMethodInfo(methodName = "엔지니어 수당 계산 SQL", methodExplanation = "엔지니어 수당(W060001, W060002, W060003, W060004, W060005, W060006, W060007, W060008, W060010, W060011, W060012) 계산 SQL 매퍼")
    Integer insertOrganizationEjectFee(String baseYm, String ogTpCd, String coCd, String fnlFeeYn, String feeTcntDvCd);

}
