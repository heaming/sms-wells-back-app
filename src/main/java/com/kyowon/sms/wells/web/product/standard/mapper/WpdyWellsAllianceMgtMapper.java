package com.kyowon.sms.wells.web.product.standard.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.product.standard.dto.WpdyWellsAllianceMgtDto;
import com.kyowon.sms.wells.web.product.standard.dvo.WpdyAllianceBaseDvo;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

/**
 * <pre>
 * 상품(Wells) - 상품운영관리 - 서비스 - B/S 투입 필터/부품 연결
 * zpdc-price-mngt.xml
 * </pre>
 *
 * @author jintae.choi
 * @since 2023-06-30
 */
@Mapper
public interface WpdyWellsAllianceMgtMapper {

    List<WpdyWellsAllianceMgtDto.SearchRes> selectWellsAlliances(WpdyWellsAllianceMgtDto.SearchReq dto);

    PagingResult<WpdyWellsAllianceMgtDto.SearchRes> selectWellsAlliancePages(
        WpdyWellsAllianceMgtDto.SearchReq dto, PageInfo pageInfo
    );

    String selectWellsAllianceId();

    int mergeWellsAllianceBase(WpdyAllianceBaseDvo info);

    int deleteWellsAllianceBase(WpdyAllianceBaseDvo info);

    String selectWellsAllianceDuplication(WpdyAllianceBaseDvo info);
}
