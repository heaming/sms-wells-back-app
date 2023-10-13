package com.kyowon.sms.wells.web.product.manage.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.common.web.product.manage.dto.ZpdcMaterialMgtDto.ValidationReq;
import com.kyowon.sms.wells.web.product.manage.dto.WpdcAsPartMgtDto;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

/* wpdc-as-part-mgt.xml */
/**
 * <pre>
 * 상품 >> AS부품 관리 Mapper
 * </pre>
 *
 * @author junho.bae
 * @since 2023-07-01
 */
@Mapper
public interface WpdcAsPartMgtMapper {

    /**
     * AS부품 목록 페이징 조회
     * @param dto 검색조건
     * @param pageInfo 페이징정ㅂ오
     * @return AS 부품관리 목록
     */
    PagingResult<WpdcAsPartMgtDto.SearchRes> selectAsPartPages(
        WpdcAsPartMgtDto.SearchReq dto, PageInfo pageInfo
    );

    /**
     * AS부품 목록 엑셀다운로드
     * @param dto 검색조건
     * @return AS 부품관리 목록
     */
    List<WpdcAsPartMgtDto.SearchRes> selectAsPartPages(WpdcAsPartMgtDto.SearchReq dto);

    /**
     * 유효성 체크 조회
     * @param dto AS 부품관리 정보
     * @return AS 부품관리 유효성 체크 결과
     */
    String selectValidation(ValidationReq dto);

}
