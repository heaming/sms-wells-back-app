package com.kyowon.sms.wells.web.fee.standard.mapper;

import com.kyowon.sms.common.web.fee.standard.annotation.SqlMapperInfo;
import com.kyowon.sms.common.web.fee.standard.annotation.SqlMethodInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
@SqlMapperInfo(organizationTypeCode = "W02")
public interface WfeyFeeCalculationSqlFor201Mapper {

    /**
     * M조직 판매자 정착수수료 SQL Mapper (W020081, 1)
     *
     * @param baseYm      기준년월
     * @param ogTpCd      조직유형코드
     * @param feeCd       수수료코드
     * @param feeTcntDvCd 수수료차수구분코드
     * @return 처리개수
     */
    @SqlMethodInfo(methodTypeCode = "01", methodName = "M조직 판매자 정착수수료계산", methodExplanation = "M조직 판매자 정착수수료 계산 SQL 매퍼")
    Integer insertSettlementFeesForSeller(String baseYm, String ogTpCd, String coCd, String feeCd, String dtaCrtFeeCd, String feeTcntDvCd, String fnlFeeYn);


    /**
     * M조직 판매자 유니폼수수료 SQL Mapper (W020098, 1)
     *
     * @param baseYm      기준년월
     * @param ogTpCd      조직유형코드
     * @param feeCd       수수료코드
     * @param feeTcntDvCd 수수료차수구분코드
     * @return 처리개수
     */
    @SqlMethodInfo(methodTypeCode = "01", methodName = "M조직 판매자 유니폼수수료계산", methodExplanation = "M조직 판매자 유니폼수수료 계산 SQL 매퍼")
    Integer insertUniformFeesForSeller(String baseYm, String ogTpCd, String coCd, String feeCd, String dtaCrtFeeCd, String feeTcntDvCd, String fnlFeeYn);

    /**
     * M조직 판매자 BS장려수수료계산 SQL Mapper (W020122, 1)
     *
     * @param baseYm      기준년월
     * @param ogTpCd      조직유형코드
     * @param feeCd       수수료코드
     * @param feeTcntDvCd 수수료차수구분코드
     * @return 처리개수
     */
    @SqlMethodInfo(methodTypeCode = "01", methodName = "M조직 판매자 BS장려수수료계산", methodExplanation = "M조직 판매자 BS장려수수료 계산 SQL 매퍼")
    Integer insertBsEncouragementFeeForSeller(String baseYm, String ogTpCd, String coCd, String feeCd, String dtaCrtFeeCd, String feeTcntDvCd, String fnlFeeYn);

    /**
     * M조직 플래너 추가장려수수료계산 SQL Mapper (W020124, 1)
     *
     * @param baseYm      기준년월
     * @param ogTpCd      조직유형코드
     * @param feeCd       수수료코드
     * @param feeTcntDvCd 수수료차수구분코드
     * @return 처리개수
     */
    @SqlMethodInfo(methodTypeCode = "01", methodName = "M조직 플래너 추가장려수수료계산", methodExplanation = "M조직 플래너 추가장려수수료 계산 SQL 매퍼")
    Integer insertSupplementaryEncrgFeeForPlanner(String baseYm, String ogTpCd, String coCd, String feeCd, String dtaCrtFeeCd, String feeTcntDvCd, String fnlFeeYn);

    /******************************************* 되물림 *************************************************/
    /**
     * M조직 판매자 정착수수료 되물림 계산 SQL Mapper (W020081,1)
     *
     * @param baseYm      기준년월
     * @param ogTpCd      조직유형코드
     * @param feeCd       수수료코드
     * @param dtaCrtFeeCd       데이터생성수수료코드
     * @param perfAgrgCrtDvCd 실적집계생성구분코드
     * @param apyStrtYm 적용시작일자
     * @param apyEndYm 적용종료년월
     * @return 처리개수
     */
    @SqlMethodInfo(methodTypeCode = "02", methodName = "M조직 판매자 정착수수료 되물림 계산(W020081,1)", methodExplanation = "M조직 판매자 정착수수료 되물림 계산(W020081,1) SQL 매퍼")
    Integer insertSellerSettleFeeRedfForApnTn1(String baseYm, String ogTpCd, String coCd, String feeCd, String dtaCrtFeeCd, String perfAgrgCrtDvCd, String apyStrtYm, String apyEndYm);

    /**
     * M조직 판매자 정착수수료 되물림 계산 SQL Mapper (W020081,2)
     *
     * @param baseYm      기준년월
     * @param ogTpCd      조직유형코드
     * @param feeCd       수수료코드
     * @param dtaCrtFeeCd       데이터생성수수료코드
     * @param perfAgrgCrtDvCd 실적집계생성구분코드
     * @param apyStrtYm 적용시작일자
     * @param apyEndYm 적용종료년월
     * @return 처리개수
     */
    @SqlMethodInfo(methodTypeCode = "02", methodName = "M조직 판매자 정착수수료 되물림 계산(W020081,2)", methodExplanation = "M조직 판매자 정착수수료 되물림 계산(W020081,2) SQL 매퍼")
    Integer insertSellerSettleFeeRedfForApnTn2(String baseYm, String ogTpCd, String coCd, String feeCd, String dtaCrtFeeCd, String perfAgrgCrtDvCd, String apyStrtYm, String apyEndYm);

}
