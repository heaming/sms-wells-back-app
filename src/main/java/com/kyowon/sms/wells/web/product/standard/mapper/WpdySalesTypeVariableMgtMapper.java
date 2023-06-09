package com.kyowon.sms.wells.web.product.standard.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.product.standard.dto.WpdySalesTypeVariableMgtDto;
import com.kyowon.sms.wells.web.product.standard.dvo.WpdyTypeVariableBaseDvo;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

/**
 * <pre>
 * 상품(Wells) - 기준정보관리 - B/S 투입 필터/부품 연결
 * zpdc-price-mngt.xml
 * </pre>
 *
 * @author jintae.choi
 * @since 2023-06-30
 */
@Mapper
public interface WpdySalesTypeVariableMgtMapper {

    List<WpdySalesTypeVariableMgtDto.SearchRes> selectSalesTypeVariables(WpdySalesTypeVariableMgtDto.SearchReq dto);

    PagingResult<WpdySalesTypeVariableMgtDto.SearchRes> selectSalesTypeVariablePages(
        WpdySalesTypeVariableMgtDto.SearchReq dto, PageInfo pageInfo
    );

    int mergeSalesTypeVariableBase(WpdyTypeVariableBaseDvo info);

    int deleteSalesTypeVariableBase(WpdyTypeVariableBaseDvo info);
}
