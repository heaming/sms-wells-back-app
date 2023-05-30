package com.kyowon.sms.wells.web.organization.hmnrsc.service;

import com.kyowon.sms.wells.web.organization.hmnrsc.converter.WogcPartnerSellerConverter;
import com.kyowon.sms.wells.web.organization.hmnrsc.dto.WogcPartnerSellerDto;
import com.kyowon.sms.wells.web.organization.hmnrsc.dto.WogcPartnerSellerDto.SearchInformationConfirmReq;
import com.kyowon.sms.wells.web.organization.hmnrsc.dto.WogcPartnerSellerDto.SearchInformationConfirmRes;

import com.kyowon.sms.wells.web.organization.hmnrsc.dto.WogcPartnerSellerInterfaceDto.SearchWMReq;
import com.kyowon.sms.wells.web.organization.hmnrsc.dto.WogcPartnerSellerInterfaceDto.SearchWMRes;
import com.kyowon.sms.wells.web.organization.hmnrsc.dvo.WogcPartnerSellerDvo;
import com.kyowon.sms.wells.web.organization.hmnrsc.dvo.WogcPartnerSellerInterfaceDvo;
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
        List<SearchWMRes> list = null;
        List<WogcPartnerSellerInterfaceDvo> dvos = mapper.selectWMs(dto);
        WogcPartnerSellerInterfaceDvo emptyDvo = new WogcPartnerSellerInterfaceDvo();
        if (CollectionUtils.isEmpty(dvos)) {
            emptyDvo.setErrCd("0011");
            emptyDvo.setErrNm("계약정보가 없습니다");
            dvos.add(emptyDvo);
        } else {
            for (WogcPartnerSellerInterfaceDvo dvo : dvos) {
                dvo.setCralTno(dvo.getCralLocaraTno()+"-"+dvo.getMexnoEncr()+"-"+dvo.getCralTno());
            }
        }
        list = converter.mapAllWogcPartnerSellerInterfaceDvoToSearchWMRes(dvos);
        return list;
    }
}
