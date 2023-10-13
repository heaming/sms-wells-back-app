package com.kyowon.sms.wells.web.product.manage.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.kyowon.sms.common.web.product.manage.dto.ZpdcMaterialMgtDto.SearchSapReq;
import com.kyowon.sms.common.web.product.manage.dto.ZpdcMaterialMgtDto.SearchSapRes;
import com.kyowon.sms.common.web.product.manage.dto.ZpdcMaterialMgtDto.ValidationReq;
import com.kyowon.sms.common.web.product.manage.dto.ZpdcRelationMgtDto;
import com.kyowon.sms.common.web.product.manage.dvo.ZpdcGbcoSapMatDvo;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

/* wpdc-product-material-mgt.xml */
/**
 * <pre>
 * 상품 >> 교재/자재 WELLS Mapper
 * </pre>
 *
 * @author junho.bae
 * @since 2023-07-01
 */
@Mapper
public interface WpdcMaterialMgtMapper {

    /**
     * Sap 교재/자재 목록 조회
     * @param dto 조회조건
     * @param pageInfo 페이징 정보
     * @return Sap 교재/자재 목록
     */
    PagingResult<SearchSapRes> selectMaterialSapPages(@Param("dto")
    SearchSapReq dto, PageInfo pageInfo);

    /**
     * Sap 교재/자재 목록 조회 (엑셀다운로드 용도)
     * @param dto 조회조건
     * @return Sap 교재/자재 목록
     */
    List<SearchSapRes> selectMaterialSapPages(@Param("dto")
    SearchSapReq dto);

    /**
     * 연결상품 정보 삭제
     * @param pdCd 상품코드(PK)
     * @param tbPdbsPdRels 연결상품
     * @param delMode 삭제모드
     * @param startDtm 상품이력시작일자
     * @return 성공여부
     */
    int deleteTbPdbsPdRel(@Param("pdCd")
    String pdCd,
        @Param("info")
        List<ZpdcRelationMgtDto.ProductRelation> tbPdbsPdRels,
        @Param("delMode")
        String delMode,
        @Param("startDtm")
        String startDtm
    );

    // 연결상품
    //    int mergeEachTbPdbsPdRel(@Param("info")
    //    ZpdcEachTbPdbsPdRelDvo info);

    /**
     * 연결상품 정보 INSERT/UPDATE
     * @param dto 연결상품정보
     * @return INSERT/UPDATE 쿼리수행 수
     */
    int mergeEachTbPdbsPdRelByDto(ZpdcRelationMgtDto.ProductRelation dto);

    /**
     * 연결상품 삭제
     * @param pdCd 상품코드(PK)
     * @return 성공여부
     */
    int deleteEachPdbsPdRels(String pdCd);

    /**
     * Sap 자재코드 단건조회
     * @param sapMatCd Sap 자재코드
     * @return Sap 자재코드
     */
    ZpdcGbcoSapMatDvo selectMaterialSap(String sapMatCd);

    /**
     * Sap 자재코드 목록조회
     * @param sapMatCd Sap 자재코드
     * @param sapPlntVal Sap 플랜트코드
     * @return Sap 교재/자재
     */
    List<ZpdcGbcoSapMatDvo> selectMaterialSaps(String sapMatCd, String sapPlntVal);

    /**
     * 유효성 체크 조회
     * @param dto 유효성 체크할 항목
     * @return 유효성 체크된 항목
     */
    String selectValidation(ValidationReq dto);

}
