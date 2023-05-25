package com.kyowon.sms.wells.web.organization.hmnrsc.service;

import com.kyowon.sms.wells.web.organization.hmnrsc.converter.WogcPartnerSellerConverter;
import com.kyowon.sms.wells.web.organization.hmnrsc.dto.WogcPartnerSellerDto.SearchInformationConfirmReq;
import com.kyowon.sms.wells.web.organization.hmnrsc.dto.WogcPartnerSellerDto.SearchInformationConfirmRes;
import com.kyowon.sms.wells.web.organization.hmnrsc.dvo.WogcPartnerSellerDvo;
import com.kyowon.sms.wells.web.organization.hmnrsc.mapper.WogcPartnerSellerMapper;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
    private final WogcPartnerSellerConverter converter;

    public List<SearchInformationConfirmRes> getInformationConfirms(
        SearchInformationConfirmReq dto
    ) {
        List<SearchInformationConfirmRes> res = null;
        List<WogcPartnerSellerDvo> dvos = new ArrayList<WogcPartnerSellerDvo>();
        WogcPartnerSellerDvo dvo = new WogcPartnerSellerDvo();
        if (StringUtils.isEmpty(dto.ogTpCd()) || StringUtils.isEmpty(dto.prtnrKnm())
            || StringUtils.isEmpty(dto.bryyMmdd()) || StringUtils.isEmpty(dto.sexDvCd())
            || (StringUtils.isEmpty(dto.ymd()))) {
            dvo.setChk("X");
            dvos.add(dvo);
            res = converter.mapAllWogcPartnerSellerDvoToSearchInformationConfirmRes(dvos);
            return res;
        }

        if(dto.ymd().length() != 8 || dto.bryyMmdd().length() != 8) {
            dvo.setChk("X");
            dvos.add(dvo);
            res = converter.mapAllWogcPartnerSellerDvoToSearchInformationConfirmRes(dvos);
            return res;
        }

        dvos = mapper.selectInformationConfirms(dto);

        if (CollectionUtils.isEmpty(dvos)) {
            dvo.setChk("E");
            dvos.add(dvo);
        }
        res = converter.mapAllWogcPartnerSellerDvoToSearchInformationConfirmRes(dvos);

        return res;
    }
}
