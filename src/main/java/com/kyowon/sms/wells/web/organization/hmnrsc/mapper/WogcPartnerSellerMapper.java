package com.kyowon.sms.wells.web.organization.hmnrsc.mapper;

import com.kyowon.sms.wells.web.organization.hmnrsc.dto.WogcPartnerSellerDto.SearchInformationConfirmReq;
import com.kyowon.sms.wells.web.organization.hmnrsc.dto.WogcPartnerSellerDto.SearchInformationConfirmRes;
import com.kyowon.sms.wells.web.organization.hmnrsc.dto.WogcPartnerSellerInterfaceDto.SearchWMReq;
import com.kyowon.sms.wells.web.organization.hmnrsc.dto.WogcPartnerSellerInterfaceDto.SearchRecentContractReq;
import com.kyowon.sms.wells.web.organization.hmnrsc.dvo.WogcPartnerSellerDvo;
import com.kyowon.sms.wells.web.organization.hmnrsc.dvo.WogcPartnerSellerInterfaceDvo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <pre>
 *
 * </pre>
 *
 * @author hyh
 * @since 2023-05-24
 */
@Mapper
public interface WogcPartnerSellerMapper {

    List<WogcPartnerSellerDvo> selectInformationConfirms(SearchInformationConfirmReq dto);

    List<WogcPartnerSellerInterfaceDvo> selectWMs(SearchWMReq dto);

    List<WogcPartnerSellerInterfaceDvo> selectRecentContracts(SearchRecentContractReq dto);
}
