package com.kyowon.sms.wells.web.organization.hmnrsc.mapper;

import com.kyowon.sms.wells.web.organization.hmnrsc.dto.WogcPartnerSellerDto.SearchInformationConfirmReq;
import com.kyowon.sms.wells.web.organization.hmnrsc.dto.WogcPartnerSellerDto.SearchInformationConfirmRes;
import org.apache.ibatis.annotations.Mapper;

/**
 * <pre>
 *
 * </pre>
 *
 * @author 김동석
 * @since 2023-05-24
 */
@Mapper
public interface WogcPartnerSellerMapper {

    SearchInformationConfirmRes selectInformationConfirm(SearchInformationConfirmReq dto);
}
