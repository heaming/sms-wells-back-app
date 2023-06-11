package com.kyowon.sms.wells.web.product.standard.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.product.standard.dto.WpdyRentalLeasePenaltyMgtDto;
import com.kyowon.sms.wells.web.product.standard.dvo.WpdyCancelChargeBaseDvo;
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
public interface WpdyRentalLeasePenaltyMgtMapper {

    List<WpdyRentalLeasePenaltyMgtDto.SearchRes> selectRentalLeasePenalties(WpdyRentalLeasePenaltyMgtDto.SearchReq dto);

    PagingResult<WpdyRentalLeasePenaltyMgtDto.SearchRes> selectRentalLeasePenaltyPages(
        WpdyRentalLeasePenaltyMgtDto.SearchReq dto, PageInfo pageInfo
    );

    String selectRentalLeasePenaltyId();

    int mergeRentalLeasePenaltyBase(WpdyCancelChargeBaseDvo info);

    int deleteRentalLeasePenaltyBase(WpdyCancelChargeBaseDvo info);

    String selectRentalLeasePenaltyDuplication(WpdyCancelChargeBaseDvo info);
}
