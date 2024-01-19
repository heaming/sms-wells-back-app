package com.kyowon.sms.wells.web.fee.standard.mapper;

import com.kyowon.sms.common.web.fee.standard.annotation.SqlMapperInfo;
import com.kyowon.sms.common.web.fee.standard.annotation.SqlMethodInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
@SqlMapperInfo(organizationTypeCode = "W02")
public interface WfeyFeeSmlCalculationSqlFor201Mapper {

    /**
     * M조직 판매자 정착수수료 SQL Mapper (W020081, 1)
     *
     * @param baseYm      기준년월
     * @param ogTpCd      조직유형코드
     * @param coCd 회사코드
     * @param feeCd       수수료코드
     * @param dtaCrtFeeCd 데이터생성수수료코드
     * @param fnlFeeYn 최종수수료여부
     * @param mmAcuPerfAgrgCrtDvCd 월누적실적집계생성구분코드
     * @param smlCrtTpCd 시뮬레이션생성유형코드
     * @param prtnrNo 파트너번호
     * @return 처리개수
     */
    @SqlMethodInfo(methodTypeCode = "04", methodName = "M조직 판매자 정착수수료계산", methodExplanation = "M조직 판매자 정착수수료 계산 SQL 매퍼")
    Integer insertSettlementFeesForSeller(String baseYm, String ogTpCd, String coCd, String feeCd, String dtaCrtFeeCd,
                                          String fnlFeeYn, String mmAcuPerfAgrgCrtDvCd, String smlCrtTpCd, String prtnrNo);


    /**
     * M조직 판매자 유니폼수수료 SQL Mapper (W020098, 1)
     *
     * @param baseYm      기준년월
     * @param ogTpCd      조직유형코드
     * @param coCd 회사코드
     * @param feeCd       수수료코드
     * @param dtaCrtFeeCd 데이터생성수수료코드
     * @param fnlFeeYn 최종수수료여부
     * @param mmAcuPerfAgrgCrtDvCd 월누적실적집계생성구분코드
     * @param smlCrtTpCd 시뮬레이션생성유형코드
     * @param prtnrNo 파트너번호
     * @return 처리개수
     */
    @SqlMethodInfo(methodTypeCode = "04", methodName = "M조직 판매자 유니폼수수료계산", methodExplanation = "M조직 판매자 유니폼수수료 계산 SQL 매퍼")
    Integer insertUniformFeesForSeller(String baseYm, String ogTpCd, String coCd, String feeCd, String dtaCrtFeeCd,
                                       String fnlFeeYn, String mmAcuPerfAgrgCrtDvCd, String smlCrtTpCd, String prtnrNo);

    /**
     * M조직 판매자 BS장려수수료계산 SQL Mapper (W020122, 1)
     *
     * @param baseYm      기준년월
     * @param ogTpCd      조직유형코드
     * @param coCd 회사코드
     * @param feeCd       수수료코드
     * @param dtaCrtFeeCd 데이터생성수수료코드
     * @param fnlFeeYn 최종수수료여부
     * @param mmAcuPerfAgrgCrtDvCd 월누적실적집계생성구분코드
     * @param smlCrtTpCd 시뮬레이션생성유형코드
     * @param prtnrNo 파트너번호
     * @return 처리개수
     */
    @SqlMethodInfo(methodTypeCode = "04", methodName = "M조직 판매자 BS장려수수료계산", methodExplanation = "M조직 판매자 BS장려수수료 계산 SQL 매퍼")
    Integer insertBsEncouragementFeeForSeller(String baseYm, String ogTpCd, String coCd, String feeCd, String dtaCrtFeeCd,
                                              String fnlFeeYn, String mmAcuPerfAgrgCrtDvCd, String smlCrtTpCd, String prtnrNo);

    /**
     * M조직 플래너 추가장려수수료계산 SQL Mapper (W020124, 1)
     *
     * @param baseYm      기준년월
     * @param coCd      회사코드
     * @param ogTpCd      조직유형코드
     * @param feeCd       수수료코드
     * @param dtaCrtFeeCd 데이터생성수수료코드
     * @param fnlFeeYn 최종수수료여부
     * @param mmAcuPerfAgrgCrtDvCd 월누적실적집계생성구분코드
     * @param smlCrtTpCd 시뮬레이션생성유형코드
     * @param prtnrNo 파트너번호
     * @return 처리개수
     */
    @SqlMethodInfo(methodTypeCode = "04", methodName = "M조직 플래너 추가장려수수료계산", methodExplanation = "M조직 플래너 추가장려수수료 계산 SQL 매퍼")
    Integer insertSupplementaryEncrgFeeForPlanner(String baseYm, String ogTpCd, String coCd, String feeCd, String dtaCrtFeeCd,
                                                  String fnlFeeYn, String mmAcuPerfAgrgCrtDvCd, String smlCrtTpCd, String prtnrNo);
}
