package com.kyowon.sms.wells.web.fee.standard.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.common.web.fee.standard.annotation.SqlMapperInfo;
import com.kyowon.sms.common.web.fee.standard.annotation.SqlMethodInfo;

@Mapper
@SqlMapperInfo(organizationTypeCode = "W02")
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
    @SqlMethodInfo(methodTypeCode = "01", methodName = "M조직 지국장 조직배출수수료", methodExplanation = "M조직 지국장 조직배출수수료 계산 SQL 매퍼")
    Integer insertOrganizationEjectFee(
        String baseYm, String ogTpCd, String coCd, String feeCd, String dtaCrtFeeCd, String feeTcntDvCd, String fnlFeeYn
    );

    /**
    * M조직 지국장 조직배출수수료계산 SQL Mapper (W020015, 7)
    *
    * @param baseYm      기준년월
    * @param ogTpCd      조직유형코드
    * @param feeCd       수수료코드
    * @param feeTcntDvCd 수수료차수구분코드
    * @return 처리개수
    */
    @SqlMethodInfo(methodTypeCode = "01", methodName = "M조직 지국장 조직배출1 수수료(202308)", methodExplanation = "M조직 지국장 조직배출1 수수료 계산 SQL 매퍼")
    Integer insertOrganizationEjectFee1(
        String baseYm, String ogTpCd, String coCd, String feeCd, String dtaCrtFeeCd, String feeTcntDvCd, String fnlFeeYn
    );

    /**
    * M조직 지국장 조직상조수수료계산 SQL Mapper (W020126, 7)
    *
    * @param baseYm      기준년월
    * @param ogTpCd      조직유형코드
    * @param feeCd       수수료코드
    * @param feeTcntDvCd 수수료차수구분코드
    * @return 처리개수
    */
    @SqlMethodInfo(methodTypeCode = "01", methodName = "M조직 지국장 조직상조 수수료(202308)", methodExplanation = "M조직 지국장 조직상조 수수료 계산 SQL 매퍼")
    Integer insertManagerLifeAlncFee2(
        String baseYm, String ogTpCd, String coCd, String feeCd, String dtaCrtFeeCd, String feeTcntDvCd, String fnlFeeYn
    );

    /**
    * M조직 지국장 조직배출1(202308) SQL Mapper (W020016, 5)
    *
    * @param baseYm      기준년월
    * @param ogTpCd      조직유형코드
    * @param feeCd       수수료코드
    * @param feeTcntDvCd 수수료차수구분코드
    * @return 처리개수
    */
    @SqlMethodInfo(methodTypeCode = "01", methodName = "M조직 지국장 조직배출수수료(202308)", methodExplanation = "M조직 지국장 조직배출수수료 계산 SQL 매퍼")
    Integer insertOrganizationEjectFeeNewBase(
        String baseYm, String ogTpCd, String coCd, String feeCd, String dtaCrtFeeCd, String feeTcntDvCd, String fnlFeeYn
    );

    /**
     * M조직 지국장 신설지점수수료 SQL Mapper (W020025, 2)
     *
     * @param baseYm 기준년월
     * @param ogTpCd 조직유형코드
     * @param feeCd 수수료코드
     * @param feeTcntDvCd 수수료차수구분코드
     * @return 처리개수
     */
    @SqlMethodInfo(methodTypeCode = "01", methodName = "M조직 지국장 신설지점수수료", methodExplanation = "M조직 지국장 신설지점수수료 계산 SQL 매퍼")
    Integer insertNewEstablishmentBranchFee(
        String baseYm, String ogTpCd, String coCd, String feeCd, String dtaCrtFeeCd, String feeTcntDvCd, String fnlFeeYn
    );

    /**
     * M조직 지국장 순증관리수수료 SQL Mapper (W020025, 2)
     * @param baseYm 기준년월
     * @param ogTpCd 조직유형코드
     * @param coCd 회사코드
     * @param feeCd 수수료코드
     * @param dtaCrtFeeCd 데이터생
     * @param feeTcntDvCd
     * @param fnlFeeYn
     * @return
     */
    @SqlMethodInfo(methodTypeCode = "01", methodName = "M조직 지국장 순증관리수수료", methodExplanation = "M조직 지국장 순증관리수수료 계산 SQL 매퍼")
    Integer insertNetIncreseManagementFee(
        String baseYm, String ogTpCd, String coCd, String feeCd, String dtaCrtFeeCd, String feeTcntDvCd, String fnlFeeYn
    );

    /**
     * M조직 지점장 BS장려수수료계산 SQL Mapper (W020123, 1)
     * @param baseYm 기준년월
     * @param ogTpCd 조직유형코드
     * @param coCd 회사코드
     * @param feeCd 수수료코드
     * @param dtaCrtFeeCd 데이터생
     * @param feeTcntDvCd
     * @param fnlFeeYn
     * @return
     */
    @SqlMethodInfo(methodTypeCode = "01", methodName = "M조직 지점장 BS장려수수료", methodExplanation = "M조직 지점장 BS장려수수료 계산 SQL 매퍼")
    Integer insertBsEncouragementFeeForBrmgr(
        String baseYm, String ogTpCd, String coCd, String feeCd, String dtaCrtFeeCd, String feeTcntDvCd, String fnlFeeYn
    );

    /**
     * M조직 지점장 유니폼수수료 SQL Mapper (W020099, 1)
     *
     * @param baseYm      기준년월
     * @param ogTpCd      조직유형코드
     * @param feeCd       수수료코드
     * @param feeTcntDvCd 수수료차수구분코드
     * @return 처리개수
     */
    @SqlMethodInfo(methodTypeCode = "01", methodName = "M조직 지점장 유니폼수수료계산", methodExplanation = "M조직 지점장 유니폼수수료 계산 SQL 매퍼")
    Integer insertUniformFeesForBrmgr(
        String baseYm, String ogTpCd, String coCd, String feeCd, String dtaCrtFeeCd, String feeTcntDvCd, String fnlFeeYn
    );

    /******************************************* 되물림 *************************************************/

    /**
     * M조직 플래너지점장 정착수수료 되물림 계산 SQL Mapper (W020080)
     *
     * @param baseYm      기준년월
     * @param ogTpCd      조직유형코드
     * @param feeCd       수수료코드
     * @param perfAgrgCrtDvCd 실적집계생성구분코드
     * @param apyStrtYm 적용시작일자
     * @param apyEndYm 적용종료년월
     * @return 처리개수
     */
    @SqlMethodInfo(methodTypeCode = "02", methodName = "M조직 플래너지점장 정착수수료 되물림 계산(W020080)", methodExplanation = "M조직 플래너지점장 정착수수료 되물림 계산(W020080) SQL 매퍼")
    Integer insertPlarBrmgrSettleFeeRedf(
        String baseYm, String ogTpCd, String coCd, String feeCd, String dtaCrtFeeCd, String perfAgrgCrtDvCd,
        String apyStrtYm, String apyEndYm
    );

    /**
     * M조직 플래너 추가장려수수료 되물림 계산 SQL Mapper (W020124)
     *
     * @param baseYm      기준년월
     * @param ogTpCd      조직유형코드
     * @param feeCd       수수료코드
     * @param perfAgrgCrtDvCd 실적집계생성구분코드
     * @param apyStrtYm 적용시작일자
     * @param apyEndYm 적용종료년월
     * @return 처리개수
     */
    @SqlMethodInfo(methodTypeCode = "02", methodName = "M조직 플래너 추가장려수수료 되물림 계산(W020124)", methodExplanation = "M조직 플래너 추가장려수수료 되물림 계산(W020124) SQL 매퍼")
    Integer insertPlarSpmtEncrgRedf(
        String baseYm, String ogTpCd, String coCd, String feeCd, String dtaCrtFeeCd, String perfAgrgCrtDvCd,
        String apyStrtYm, String apyEndYm
    );

    /**
     * M조직 지점장 조직관리수수료 되물림 계산 SQL Mapper (W020018, 3)
     *
     * @param baseYm      기준년월
     * @param ogTpCd      조직유형코드
     * @param feeCd       수수료코드
     * @param apyStrtYm 적용시작일자
     * @param apyEndYm 적용종료년월
     * @return 처리개수
     */
    @SqlMethodInfo(methodTypeCode = "02", methodName = "M조직 지점장 조직관리수수료 되물림 계산(W020018, 3)", methodExplanation = "M조직 지점장 조직관리수수료 되물림 계산(W020018, 3) SQL 매퍼")
    Integer insertBrmgrOgMngtFeeRedfForApyTn3(
        String baseYm, String ogTpCd, String coCd, String feeCd, String dtaCrtFeeCd, String apyStrtYm, String apyEndYm
    );

    /**
     * M조직 지점장 조직관리수수료 되물림 계산 SQL Mapper (W020018, 4)
     *
     * @param baseYm      기준년월
     * @param ogTpCd      조직유형코드
     * @param feeCd       수수료코드
     * @param apyStrtYm 적용시작일자
     * @param apyEndYm 적용종료년월
     * @return 처리개수
     */
    @SqlMethodInfo(methodTypeCode = "02", methodName = "M조직 지점장 조직관리수수료 되물림 계산(W020018, 4)", methodExplanation = "M조직 지점장 조직관리수수료 되물림 계산(W020018, 4) SQL 매퍼")
    Integer insertBrmgrOgMngtFeeRedfForApyTn4(
        String baseYm, String ogTpCd, String coCd, String feeCd, String dtaCrtFeeCd, String apyStrtYm, String apyEndYm
    );
}
