package com.kyowon.sms.wells.web.product.standard.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.product.standard.dto.WpdySeedlingPriceMgtDto;
import com.kyowon.sms.wells.web.product.standard.dvo.WpdySeedlingPriceBaseDvo;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

/**
 * <pre>
 * 상품(Wells) - 기준정보관리 - 모종제품가격 관리
 * wpdy-seedling-price-mgt.xml
 * </pre>
 *
 * @author jintae.choi
 * @since 2023-06-30
 */
@Mapper
public interface WpdySeedlingPriceMgtMapper {

    List<WpdySeedlingPriceMgtDto.SearchRes> selectSeedlingPrices(WpdySeedlingPriceMgtDto.SearchReq dto);

    PagingResult<WpdySeedlingPriceMgtDto.SearchRes> selectSeedlingPricePages(
        WpdySeedlingPriceMgtDto.SearchReq dto, PageInfo pageInfo
    );

    int insertSeedlingPriceBase(WpdySeedlingPriceBaseDvo info);

    int updateSeedlingPriceBase(WpdySeedlingPriceBaseDvo info);

    int deleteSeedlingPriceBase(WpdySeedlingPriceBaseDvo info);
}
