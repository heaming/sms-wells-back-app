package com.kyowon.sms.wells.web.product.standard.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.product.standard.dto.WpdyHealthAllianceMgtDto;
import com.kyowon.sms.wells.web.product.standard.dvo.WpdyAllianceBaseDvo;
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
public interface WpdyHealthAllianceMgtMapper {

    List<WpdyHealthAllianceMgtDto.SearchRes> selectHealthAlliances(WpdyHealthAllianceMgtDto.SearchReq dto);

    PagingResult<WpdyHealthAllianceMgtDto.SearchRes> selectHealthAlliancePages(
        WpdyHealthAllianceMgtDto.SearchReq dto, PageInfo pageInfo
    );

    String selectHealthAllianceId();

    int mergeHealthAllianceBase(WpdyAllianceBaseDvo info);

    int deleteHealthAllianceBase(WpdyAllianceBaseDvo info);
}
