package com.kyowon.sms.wells.web.organization.hmnrsc.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.organization.hmnrsc.converter.WogcPartnerSellerConverter;
import com.kyowon.sms.wells.web.organization.hmnrsc.dto.WogcPartnerSellerDto.SearchInformationConfirmReq;
import com.kyowon.sms.wells.web.organization.hmnrsc.dto.WogcPartnerSellerDto.SearchInformationConfirmRes;
import com.kyowon.sms.wells.web.organization.hmnrsc.dto.WogcPartnerSellerInterfaceDto.SearchRecentContractReq;
import com.kyowon.sms.wells.web.organization.hmnrsc.dto.WogcPartnerSellerInterfaceDto.SearchRecentContractRes;
import com.kyowon.sms.wells.web.organization.hmnrsc.dto.WogcPartnerSellerInterfaceDto.SearchWMReq;
import com.kyowon.sms.wells.web.organization.hmnrsc.dto.WogcPartnerSellerInterfaceDto.SearchWMRes;
import com.kyowon.sms.wells.web.organization.hmnrsc.dvo.WogcPartnerSellerDvo;
import com.kyowon.sms.wells.web.organization.hmnrsc.dvo.WogcPartnerSellerInterfaceDvo;
import com.kyowon.sms.wells.web.organization.hmnrsc.mapper.WogcPartnerSellerMapper;

import lombok.RequiredArgsConstructor;

/**
 * <pre>
 *
 * </pre>
 *
 * @author gs.piit158
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

        if (dto.ymd().length() != 8 || dto.bryyMmdd().length() != 8) {
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

    /**
     * EAI 웰스 WM 정보 조회
     *
     * @param dto
     * @return
     */
    public List<SearchWMRes> getWM(SearchWMReq dto) {
        List<WogcPartnerSellerInterfaceDvo> dvos = mapper.selectWMs(dto);
        dvos = convertDefaultMapping(dvos);

        return converter.mapAllWogcPartnerSellerInterfaceDvoToSearchWMRes(dvos);
    }

    public List<SearchRecentContractRes> getSearchRecentContracts(SearchRecentContractReq dto) {
        List<WogcPartnerSellerInterfaceDvo> dvos = mapper.selectRecentContracts(dto);
        dvos = convertDefaultMapping(dvos);

        return converter.mapAllWogcPartnerSellerInterfaceDvoToSearchRecentContractRes(dvos);
    }

    private List<WogcPartnerSellerInterfaceDvo> convertDefaultMapping(
        List<WogcPartnerSellerInterfaceDvo> dvos
    ) {
        if (CollectionUtils.isEmpty(dvos)) {
            dvos = List.of(new WogcPartnerSellerInterfaceDvo("0011", "계약정보가 없습니다"));
        } else {
            dvos.forEach(
                dvo -> dvo.setCralTno(
                    String.format("%s%s%s", dvo.getCralLocaraTno(), dvo.getMexnoEncr(), dvo.getCralIdvTno())
                )
            );
        }
        return dvos;
    }
}
