package com.kyowon.sms.wells.web.product.manage.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.kyowon.sms.wells.web.product.manage.dto.WpdcRoutineBsWorkMgtDto;
import com.kyowon.sms.wells.web.product.manage.dvo.WpdcLifeCustomFilterBaseDvo;
import com.kyowon.sms.wells.web.product.manage.dvo.WpdcRoutineBsWorkBaseDvo;
import com.kyowon.sms.wells.web.product.manage.dvo.WpdcRoutineBsWorkDetailDvo;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

/**
 * <pre>
 * 상품(공통) - 상품운영관리 - 서비스 - B/S 투입 필터/부품 연결
 * zpdc-price-mngt.xml
 * </pre>
 *
 * @author jintae.choi
 * @since 2023-06-30
 */
@Mapper
public interface WpdcRoutineBsWorkMgtMapper {

    List<WpdcRoutineBsWorkMgtDto.SearchRoutineBsWorkBaseRes> selectRoutineBsWorkBases(
        WpdcRoutineBsWorkMgtDto.SearchReq dto
    );

    PagingResult<WpdcRoutineBsWorkMgtDto.SearchRoutineBsWorkBaseRes> selectRoutineBsWorkBasePages(
        WpdcRoutineBsWorkMgtDto.SearchStdBaseReq dto, PageInfo pageInfo
    );

    List<WpdcRoutineBsWorkMgtDto.SearchRoutineBsWorkDetailRes> selectRoutineBsWorkDetails(WpdcRoutineBsWorkMgtDto.SearchReq dto);

    List<WpdcRoutineBsWorkMgtDto.SearchLifeCustomFiltersRes> selectLifeCustomFilters(WpdcRoutineBsWorkMgtDto.SearchReq dto);

    int isnertRoutineBsWorkBase(@Param("info")
    WpdcRoutineBsWorkBaseDvo info);

    int isnertRoutineBsWorkDetail(@Param("info")
    WpdcRoutineBsWorkDetailDvo info);

    int updateRoutineBsWorkDetail(@Param("info")
    WpdcRoutineBsWorkDetailDvo info);

    int mergeLifeCustomFilterBase(WpdcLifeCustomFilterBaseDvo info);

    int deleteRoutineBsWorkBase(String svPdCd, String pdctPdCd);

    int deleteRoutineBsWorkDetail(String svPdCd, String pdctPdCd);

    int deleteRoutineBsWorkDetail(WpdcRoutineBsWorkDetailDvo info);

    int deleteLifeCustomFilterStd(WpdcLifeCustomFilterBaseDvo info);

    int deleteLifeCustomFilterStdByPdCd(String svPdCd);
}
