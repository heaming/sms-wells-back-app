package com.kyowon.sms.wells.web.fee.standard.mapper;

import com.kyowon.sms.common.web.fee.standard.annotation.SqlMapperInfo;
import com.kyowon.sms.common.web.fee.standard.annotation.SqlMethodInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
@SqlMapperInfo(organizationTypeCode = "W01")
public interface WfeyFeeSmlCalculationSqlFor102Mapper {

    /**
     * P조직 지점장 정착수수료 정착수수료 SQL Mapper (W010105, 2)
     *
     * @param baseYm      기준년월
     * @param ogTpCd      조직유형코드
     * @param coCd 회사코드
     * @param feeCd       수수료코드
     * @param dtaCrtFeeCd 데이터생성수수료코드
     * @param fnlFeeYn 최종수수료여부
     * @param mmAcuPerfAgrgCrtDvCd 월누적실적집계생성구분코드
     * @param prtnrNo 파트너번호
     * @return 처리개수
     */
    @SqlMethodInfo(methodTypeCode = "04", methodName = "P조직 지점장 정착수수료 정착수수료계산", methodExplanation = "P조직 지점장 정착수수료 정착수수료 계산 SQL 매퍼")
    Integer insertSettlementFeesForBrmgr(String baseYm, String ogTpCd, String coCd, String feeCd, String dtaCrtFeeCd, String fnlFeeYn, String mmAcuPerfAgrgCrtDvCd , String prtnrNo);
}
