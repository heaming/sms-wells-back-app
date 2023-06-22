package com.kyowon.sms.wells.web.product.manage.service;

import static com.kyowon.sms.wells.web.product.manage.dto.WpdcAsPartCommonUseMgtDto.*;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.product.manage.mapper.WpdcAsPartCommonUseMgtMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WpdcAsPartCommonUseMgtService {

    private final WpdcAsPartCommonUseMgtMapper mapper;

    public List<SearchPartRes> getAsParts(SearchPartReq dto) {
        return mapper.selectAsParts(dto);
    }

    public List<SearchProductRes> getProductsByPart(String partPdCd) {
        return mapper.selectProductsByPart(partPdCd);
    }
}
