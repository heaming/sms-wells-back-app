package com.kyowon.sms.wells.web.service.stock.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.kyowon.sms.wells.web.service.stock.converter.WsnaBsCsmbDeliveryAggregateConverter;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaBsCsmbDeliveryAggregateDto.SearchQtysRes;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaBsCsmbDeliveryAggregateDto.SearchReq;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaBsCsmbDeliveryAggregateDto.SearchRes;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaBsCsmbDeliveryAggregateDvo;
import com.kyowon.sms.wells.web.service.stock.mapper.WsnaBsCsmbDeliveryAggregateMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WsnaBsCsmbDeliveryAggregateService {

    private final WsnaBsCsmbDeliveryAggregateMapper mapper;

    private final WsnaBsCsmbDeliveryAggregateConverter converter;

    public List<SearchRes> getDeliveryAggregates(SearchReq dto) {
        List<WsnaBsCsmbDeliveryAggregateDvo> dvos = mapper.selectDeliveryAggregate(dto);

        if (!ObjectUtils.isEmpty(dvos)) {
            for (int i = 0; i < dvos.size(); i++) {
                if (!ObjectUtils.isEmpty(dvos.get(i).getBldCd())) {
                    String bldCd = dvos.get(i).getBldCd();

                    for (int j = i + 1; j < dvos.size(); j++) {
                        if (bldCd == dvos.get(j).getBldCd()) {
                            dvos.get(j).setBldCd(null);
                            dvos.get(j).setBldNm(null);
                            dvos.get(j).setVstAccSum(null);
                            dvos.get(j).setWrfr(null);
                            dvos.get(j).setBdtIndv(null);
                            dvos.get(j).setBdtCrp(null);
                            dvos.get(j).setArcleIndv(null);
                            dvos.get(j).setArcleCrp(null);
                            dvos.get(j).setWtrSftnr(null);
                            dvos.get(j).setCffMchn(null);
                            dvos.get(j).setMsgcr(null);
                            dvos.get(j).setDryr(null);
                            dvos.get(j).setWash(null);
                            dvos.get(j).setArdrssr(null);
                            dvos.get(j).setSscling(null);
                        }
                    }
                }
            }
        }

        return converter.mapDvoToSearchRes(dvos);
    }

    public PagingResult<SearchRes> getDeliveryAggregatePages(SearchReq dto, PageInfo pageInfo) {
        PagingResult<WsnaBsCsmbDeliveryAggregateDvo> dvos = mapper.selectDeliveryAggregate(dto, pageInfo);

        if (!ObjectUtils.isEmpty(dvos)) {
            for (int i = 0; i < dvos.size(); i++) {
                if (!ObjectUtils.isEmpty(dvos.get(i).getBldCd())) {
                    String bldCd = dvos.get(i).getBldCd();

                    for (int j = i + 1; j < dvos.size(); j++) {
                        if (bldCd.equals(dvos.get(j).getBldCd())) {
                            dvos.get(j).setBldCd("");
                            dvos.get(j).setBldNm("");
                            dvos.get(j).setVstAccSum("");
                            dvos.get(j).setWrfr("");
                            dvos.get(j).setBdtIndv("");
                            dvos.get(j).setBdtCrp("");
                            dvos.get(j).setArcleIndv("");
                            dvos.get(j).setArcleCrp("");
                            dvos.get(j).setWtrSftnr("");
                            dvos.get(j).setCffMchn("");
                            dvos.get(j).setMsgcr("");
                            dvos.get(j).setDryr("");
                            dvos.get(j).setWash("");
                            dvos.get(j).setArdrssr("");
                            dvos.get(j).setSscling("");
                        }
                    }
                }
            }
        }

        PagingResult<SearchRes> rtnDtos = converter.mapDvoToSearchResPages(dvos);
        rtnDtos.setPageInfo(dvos.getPageInfo());

        return rtnDtos;
    }

    public List<SearchQtysRes> getItemQtys(String mngtYmFrom, String mngtYmTo) {
        return mapper.selectItemQtys(mngtYmFrom, mngtYmTo);
    }
}
