package com.kyowon.sms.wells.web.fee.standard.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.common.web.fee.standard.annotation.SqlMapperInfo;
import com.kyowon.sms.common.web.fee.standard.annotation.SqlMethodInfo;

@Mapper
@SqlMapperInfo(organizationTypeCode = "W03")
public interface WfeyFeeSmlCalculationSqlFor301Mapper {

    /**
     * 홈마스터 파트너 조기정착수수료 계산 SQL Mapper (W030016, 2)
     *
     * @param baseYm 기준년월
     * @param ogTpCd 조직유형코드
     * @param coCd 회사코드
     * @param feeCd 수수료코드
     * @param dtaCrtFeeCd 데이터생성수수료코드
     * @param fnlFeeYn 최종수수료여부
     * @param mmAcuPerfAgrgCrtDvCd 월누적실적집계생성구분코드
     * @param smlCrtTpCd 시뮬레이션생성유형코드
     * @param prtnrNo 파트너번호
     * @return 처리개수
     */
    @SqlMethodInfo(methodTypeCode = "04", methodName = "홈마스터 파트너 조기정착수수료", methodExplanation = "홈마스터 파트너 조기정착수수료 계산 SQL 매퍼")
    Integer insertEarlSettleFee(
        String baseYm, String ogTpCd, String coCd, String feeCd, String dtaCrtFeeCd, String fnlFeeYn,
        String mmAcuPerfAgrgCrtDvCd, String smlCrtTpCd, String prtnrNo
    );

    /**
     * 홈마스터 파트너 교육수수료 계산 SQL Mapper (W030011)
     *
     * @param baseYm 기준년월
     * @param ogTpCd 조직유형코드
     * @param coCd 회사코드
     * @param feeCd 수수료코드
     * @param dtaCrtFeeCd 데이터생성수수료코드
     * @param fnlFeeYn 최종수수료여부
     * @param mmAcuPerfAgrgCrtDvCd 월누적실적집계생성구분코드
     * @param smlCrtTpCd 시뮬레이션생성유형코드
     * @param prtnrNo 파트너번호
     * @return 처리개수
     */
    @SqlMethodInfo(methodTypeCode = "04", methodName = "홈마스터 파트너 교육수수료", methodExplanation = "홈마스터 파트너 교육수수료 계산 SQL 매퍼")
    Integer insertEducationFee(
        String baseYm, String ogTpCd, String coCd, String feeCd, String dtaCrtFeeCd, String fnlFeeYn,
        String mmAcuPerfAgrgCrtDvCd, String smlCrtTpCd, String prtnrNo
    );

}
