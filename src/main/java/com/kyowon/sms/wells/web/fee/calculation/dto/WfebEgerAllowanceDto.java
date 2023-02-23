package com.kyowon.sms.wells.web.fee.calculation.dto;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;

/**
 * <pre>
 * 엔지니어 수당 생성관리
 * </pre>
 *
 * @author gs.piit56
 * @since 2023.02.01
 */
public class WfebEgerAllowanceDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    // 엔지니어 수당 생성관리 Search Request Dto
    @ApiModel(value = "WfebEngineerAwCrtMgtDto-SearchReq")
    public record SearchReq(
        @NotBlank
        String perfYm,
        String no
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 엔지니어 수당 생성관리 Search Result Dto
    @ApiModel(value = "WfebEngineerAwCrtMgtDto-SearchEngineerRes")
    public record SearchEngineerRes(
        String col1, /*센터*/
        String col2, /*지점*/
        String col3, /*성명*/
        String col4, /*번호*/
        String col5, /*직급*/
        String col6, /*직책*/
        int col7, /*설치작업-작업실적*/
        int col8, /*설치작업-수당*/
        int col9, /*설치작업-급지수당*/
        int col10, /*정기B/S작업-작업실적*/
        int col11, /*정기B/S작업-수당*/
        int col12, /*정기B/S작업-급지수당*/
        int col13, /*일반A/S-작업실적*/
        int col14, /*일반A/S-수당*/
        int col15, /*일반A/S-급지수당*/
        int col16, /*동행처리-작업실적*/
        int col17, /*동행처리-수당*/
        int col18, /*동행처리-급지수당*/
        int col19, /*합계-작업실적*/
        int col20, /*합계-수당*/
        int col21, /*합계-급지수당*/
        int col22, /*생산성인센티브-작업실적*/
        int col23, /*생산성인센티브-지급금액*/
        int col24, /*스케일링-작업실적*/
        int col25, /*스케일링-수당*/
        int col26, /*일반수리-작업실적*/
        int col27, /*일반수리-수당*/
        int col28, /*경수리-작업실적*/
        int col29, /*경수리-수당*/
        int col30, /*중수리-작업실적*/
        int col31, /*중수리-수당*/
        int col32, /*아웃소싱-작업실적*/
        int col33, /*아웃소싱-수당*/
        int col34, /*합계-작업실적*/
        int col35, /*합계-수당*/
        int col36, /*조정전수당합계*/
        int col37, /*조정후수당합계*/
        int col38, /*지원수당*/
        int col39, /*기타수당*/
        int col40, /*철거/철회수당*/
        int col41, /*판매권유수당*/
        int col42, /*토요근무수당-출동건*/
        int col43, /*토요근무수당-수당*/
        int col44, /*휴무당직수당-출동건*/
        int col45, /*휴무당직수당-수당*/
        int col46, /*강의수당-강의시간*/
        int col47, /*강의수당-수당*/
        int col48, /*도서방문수당-방문일수*/
        int col49, /*도서방문수당-수당*/
        int col50, /*기술숙련수당*/
        int col51, /*조장수당*/
        int col52, /*부가수당합계*/
        int col53, /*지급대상수당금액*/
        String col54, /*센터확정일자*/
        String col55 /*비고*/
    ) {}

    @ApiModel(value = "WfebEngineerAwCrtMgtDto-SearchEngineerManagerRes")
    public record SearchEngineerManagerRes(
        String col1, /*센터*/
        String col2, /*번호*/
        String col3, /*성명*/
        String col4, /*직책*/
        String col5, /*직급*/
        int col6, /*업적수당*/
        int col7, /*자격수당*/
        int col8 /*수당합계*/
    ) {}

}
