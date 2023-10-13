package com.kyowon.sms.wells.web.service.visit.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.service.visit.dto.WsnbServiceProcDetailListDto.SearchReq;
import com.kyowon.sms.wells.web.service.visit.dvo.WsnbServiceProcDetailBilItemDvo;
import com.kyowon.sms.wells.web.service.visit.dvo.WsnbServiceProcDetailListDvo;
import com.kyowon.sms.wells.web.service.visit.dvo.WsnbServiceProcDetailPuPartDvo;
import com.kyowon.sms.wells.web.service.visit.dvo.WsnbServiceProcDetailStlmIzDvo;
import com.kyowon.sms.wells.web.service.visit.mapper.WsnbServiceProcDetailListMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class WsnbServiceProcDetailListService {
    private final WsnbServiceProcDetailListMapper mapper;

    public WsnbServiceProcDetailListDvo getServiceProcDetailList(SearchReq dto) {

        return mapper.selectServiceProcDetailList(dto);
    }

    public List<WsnbServiceProcDetailStlmIzDvo> getServiceProcDetailStlmIzs(SearchReq dto) {

        return mapper.selectServiceProcDetailStlmIz(dto);
    }

    public List<WsnbServiceProcDetailBilItemDvo> getServiceProcDetailBilItems(SearchReq dto) {

        return mapper.selectServiceProcDetailBilItem(dto);
    }

    public List<WsnbServiceProcDetailPuPartDvo> getServiceProcDetailPuParts(SearchReq dto) {

        return mapper.selectServiceProcDetailPuPart(dto);
    }

}
