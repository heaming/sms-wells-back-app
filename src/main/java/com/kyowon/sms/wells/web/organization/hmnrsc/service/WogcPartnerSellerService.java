package com.kyowon.sms.wells.web.organization.hmnrsc.service;

import com.kyowon.sms.wells.web.organization.hmnrsc.dto.WogcPartnerSellerDto.SearchInformationConfirmReq;
import com.kyowon.sms.wells.web.organization.hmnrsc.dto.WogcPartnerSellerDto.SearchInformationConfirmRes;
import com.kyowon.sms.wells.web.organization.hmnrsc.mapper.WogcPartnerSellerMapper;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

/**
 * <pre>
 *
 * </pre>
 *
 * @author hyh
 * @since 2023-05-24
 */
@Service
@RequiredArgsConstructor
public class WogcPartnerSellerService {

    private final WogcPartnerSellerMapper mapper;

    public SearchInformationConfirmRes getInformationConfirm(
        SearchInformationConfirmReq dto
    ) {
        SearchInformationConfirmRes res = null;
        if (StringUtils.isEmpty(dto.ogTpCd()) || StringUtils.isEmpty(dto.prtnrKnm())
            || StringUtils.isEmpty(dto.bryyMmdd()) || StringUtils.isEmpty(dto.sexDvCd())
            || (StringUtils.isEmpty(dto.ymd()))) {
            res = SearchInformationConfirmRes.builder().chk("X").build();
            return res;
        }

        if(dto.ymd().length() != 8 || dto.bryyMmdd().length() != 8) {
            res = SearchInformationConfirmRes.builder().chk("X").build();
            return res;
        }

        res = mapper.selectInformationConfirm(dto);

        if (res == null) {
            res = SearchInformationConfirmRes.builder().chk("E").build();
        }

        return res;
    }
}
