package com.kyowon.sms.wells.web.fee.standard.mapper;

import com.kyowon.sms.common.web.fee.standard.annotation.SqlMethodInfo;
import org.apache.ibatis.annotations.Mapper;

/**
 * @name : EfeyAdsbFeeCalculationSqlMapper
 * @description : EfeyAdsbFeeCalculationSql Mapper Class
 * @datasource LE_DATA_SRC Copyright 2022 SAMSUNG. SDS All right reserved.
 */

@Mapper
public interface WfeyAdsbFeeCalculationSqlMapper {

    /**
     * WELLS 재지급수수료 계산
     *
     * @param baseYm 기준년월
     * @param perfYm 실적년월
     * @param feeCd 수수료코드
     * @param dtaCrtFeeCd 데이터생성수수료코드
     * @param coCd 회사코드
     * @param feeOjExrCndtCn 수수료대상추출조건내용
     * @param feeTcntDvCd 수수료차수구분코드
     * @return
     */

    @SqlMethodInfo(methodName = "Wells 재지급수수료 계산", methodExplanation = "WELLS사업부의 재지급수수료 계산")
    int insertAgainDisbursementFees(String baseYm, String perfYm, String feeCd, String dtaCrtFeeCd, String coCd, String feeOjExrCndtCn, String feeTcntDvCd);

}
