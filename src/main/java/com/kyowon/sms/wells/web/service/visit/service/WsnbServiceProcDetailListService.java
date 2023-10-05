package com.kyowon.sms.wells.web.service.visit.service;

import java.util.Base64;
import java.util.List;

import org.apache.commons.lang3.ObjectUtils;
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

        WsnbServiceProcDetailListDvo dvo = mapper.selectServiceProcDetailList(dto);
        if (ObjectUtils.isNotEmpty(dvo.getCstSignCnByte())) {
            dvo.setCstSignCn(Base64.getEncoder().encodeToString(dvo.getCstSignCnByte()));
        }
        return dvo;
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
