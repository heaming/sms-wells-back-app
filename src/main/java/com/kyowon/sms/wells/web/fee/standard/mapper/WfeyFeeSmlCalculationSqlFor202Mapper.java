package com.kyowon.sms.wells.web.fee.standard.mapper;

import com.kyowon.sms.common.web.fee.standard.annotation.SqlMapperInfo;
import com.kyowon.sms.common.web.fee.standard.annotation.SqlMethodInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
@SqlMapperInfo(organizationTypeCode = "W02")
public interface WfeyFeeSmlCalculationSqlFor202Mapper {

    /**
     * M조직 지국장 조직배출수수료계산 SQL Mapper (W020016, 4)
     *
     * @param baseYm      기준년월
     * @param ogTpCd      조직유형코드
     * @param coCd 회사코드
     * @param feeCd       수수료코드
     * @param mmAcuPerfAgrgCrtDvCd 월누적실적집계생성구분코드
     * @param smlCrtTpCd 시뮬레이션생성유형코드
     * @param prtnrNo 파트너번호
     * @return 처리개수
     */
    @SqlMethodInfo(methodTypeCode = "04", methodName = "M조직 지국장 조직배출수수료", methodExplanation = "M조직 지국장 조직배출수수료 계산 SQL 매퍼")
    Integer insertOrganizationEjectFee(
        String baseYm, String ogTpCd, String coCd, String feeCd, String dtaCrtFeeCd, String fnlFeeYn,
        String mmAcuPerfAgrgCrtDvCd, String smlCrtTpCd, String prtnrNo
    );

    /**
     * M조직 지국장 조직배출수수료계산 SQL Mapper (W020015, 7)
     *
     * @param baseYm      기준년월
     * @param ogTpCd      조직유형코드
     * @param coCd 회사코드
     * @param feeCd       수수료코드
     * @param mmAcuPerfAgrgCrtDvCd 월누적실적집계생성구분코드
     * @param smlCrtTpCd 시뮬레이션생성유형코드
     * @param prtnrNo 파트너번호
    * @return 처리개수
    */
    @SqlMethodInfo(methodTypeCode = "04", methodName = "M조직 지국장 조직배출1 수수료(202308)", methodExplanation = "M조직 지국장 조직배출1 수수료 계산 SQL 매퍼")
    Integer insertOrganizationEjectFee1(
        String baseYm, String ogTpCd, String coCd, String feeCd, String dtaCrtFeeCd, String fnlFeeYn,
        String mmAcuPerfAgrgCrtDvCd, String smlCrtTpCd, String prtnrNo
    );

    /**
    * M조직 지국장 조직배출1(202308) SQL Mapper (W020016, 5)
    *
    * @param baseYm      기준년월
    * @param ogTpCd      조직유형코드
     * @param coCd 회사코드
    * @param feeCd       수수료코드
     * @param mmAcuPerfAgrgCrtDvCd 월누적실적집계생성구분코드
     * @param smlCrtTpCd 시뮬레이션생성유형코드
     * @param prtnrNo 파트너번호
    * @return 처리개수
    */
    @SqlMethodInfo(methodTypeCode = "04", methodName = "M조직 지국장 조직배출수수료(202308)", methodExplanation = "M조직 지국장 조직배출수수료 계산 SQL 매퍼")
    Integer insertOrganizationEjectFeeNewBase(
        String baseYm, String ogTpCd, String coCd, String feeCd, String dtaCrtFeeCd, String fnlFeeYn,
        String mmAcuPerfAgrgCrtDvCd, String smlCrtTpCd, String prtnrNo
    );

    /**
     * M조직 지국장 신설지점수수료 SQL Mapper (W020025, 2)
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
    @SqlMethodInfo(methodTypeCode = "04", methodName = "M조직 지국장 신설지점수수료", methodExplanation = "M조직 지국장 신설지점수수료 계산 SQL 매퍼")
    Integer insertNewEstablishmentBranchFee(
        String baseYm, String ogTpCd, String coCd, String feeCd, String dtaCrtFeeCd, String fnlFeeYn,
        String mmAcuPerfAgrgCrtDvCd, String smlCrtTpCd, String prtnrNo
    );

    /**
     * M조직 지국장 순증관리수수료 SQL Mapper (W020025, 2)
     * @param baseYm 기준년월
     * @param ogTpCd 조직유형코드
     * @param coCd 회사코드
     * @param feeCd 수수료코드
     * @param dtaCrtFeeCd 데이터생성수수료코드
     * @param fnlFeeYn 최종수수료여부
     * @param mmAcuPerfAgrgCrtDvCd 월누적실적집계생성구분코드
     * @param smlCrtTpCd 시뮬레이션생성유형코드
     * @param prtnrNo 파트너번호
     * @return
     */
    @SqlMethodInfo(methodTypeCode = "04", methodName = "M조직 지국장 순증관리수수료", methodExplanation = "M조직 지국장 순증관리수수료 계산 SQL 매퍼")
    Integer insertNetIncreseManagementFee(
        String baseYm, String ogTpCd, String coCd, String feeCd, String dtaCrtFeeCd, String fnlFeeYn,
        String mmAcuPerfAgrgCrtDvCd, String smlCrtTpCd, String prtnrNo
    );

    /**
     * M조직 지점장 BS장려수수료계산 SQL Mapper (W020123, 1)
     * @param baseYm 기준년월
     * @param ogTpCd 조직유형코드
     * @param coCd 회사코드
     * @param feeCd 수수료코드
     * @param dtaCrtFeeCd 데이터생성수수료코드
     * @param fnlFeeYn 최종수수료여부
     * @param mmAcuPerfAgrgCrtDvCd 월누적실적집계생성구분코드
     * @param smlCrtTpCd 시뮬레이션생성유형코드
     * @param prtnrNo 파트너번호
     * @return
     */
    @SqlMethodInfo(methodTypeCode = "04", methodName = "M조직 지점장 BS장려수수료", methodExplanation = "M조직 지점장 BS장려수수료 계산 SQL 매퍼")
    Integer insertBsEncouragementFeeForBrmgr(
        String baseYm, String ogTpCd, String coCd, String feeCd, String dtaCrtFeeCd, String fnlFeeYn,
        String mmAcuPerfAgrgCrtDvCd, String smlCrtTpCd, String prtnrNo
    );

    /**
     * M조직 지점장 유니폼수수료 SQL Mapper (W020099, 1)
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
    @SqlMethodInfo(methodTypeCode = "04", methodName = "M조직 지점장 유니폼수수료계산", methodExplanation = "M조직 지점장 유니폼수수료 계산 SQL 매퍼")
    Integer insertUniformFeesForBrmgr(
        String baseYm, String ogTpCd, String coCd, String feeCd, String dtaCrtFeeCd, String fnlFeeYn,
        String mmAcuPerfAgrgCrtDvCd, String smlCrtTpCd, String prtnrNo
    );
}
