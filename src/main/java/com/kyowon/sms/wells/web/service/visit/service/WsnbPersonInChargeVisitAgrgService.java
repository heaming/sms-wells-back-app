package com.kyowon.sms.wells.web.service.visit.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.service.visit.dto.WsnbPersonInChargeVisitAgrgDto.FindBldRes;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbPersonInChargeVisitAgrgDto.SearchReq;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbPersonInChargeVisitAgrgDto.SearchRes;
import com.kyowon.sms.wells.web.service.visit.mapper.WsnbPersonInChargeVisitAgrgMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WsnbPersonInChargeVisitAgrgService {

    private final WsnbPersonInChargeVisitAgrgMapper mapper;

    public List<SearchRes> getPersonInChargeVisitAgrgs(SearchReq dto) {
        return mapper.selectPersonInChargeVisitAgrgs(dto);
    }

    public List<SearchRes> getPersonInChargeVisitAgrgExcelDownload(SearchReq dto) {
        return mapper.selectPersonInChargeVisitAgrgs(dto);
    }

    public List<FindBldRes> getBuildings() {
        return mapper.selectBuildings();
    }
}
