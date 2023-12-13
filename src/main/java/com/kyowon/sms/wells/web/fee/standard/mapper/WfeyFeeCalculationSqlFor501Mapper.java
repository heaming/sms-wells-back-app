package com.kyowon.sms.wells.web.fee.standard.mapper;

import com.kyowon.sms.common.web.fee.standard.annotation.SqlMapperInfo;
import com.kyowon.sms.common.web.fee.standard.annotation.SqlMethodInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
@SqlMapperInfo(organizationTypeCode = "W05")
public interface WfeyFeeCalculationSqlFor501Mapper {

    /**
     * 총판 기본수수료 되물림 SQL Mapper (W050001, 1)
     *
     * 되물림데이터는 지점장, 개인 모두 생성
     * @param baseYm
     * @param ogTpCd
     * @param coCd
     * @param feeCd
     * @param dtaCrtFeeCd
     * @param redfDtaTpCd
     * @param apyStrtYm
     * @param apyEndYm
     * @param perfAgrgCrtDvCd
     * @return
     */
    @SqlMethodInfo(methodTypeCode = "02", methodName = "총판 기본수수료 되물림", methodExplanation = "총판 기본수수료 되물림 계산 SQL 매퍼")
    Integer insertOnlBasFeeRedf(String baseYm, String ogTpCd, String coCd, String feeCd, String dtaCrtFeeCd, String redfDtaTpCd, String apyStrtYm, String apyEndYm, String perfAgrgCrtDvCd);

}
