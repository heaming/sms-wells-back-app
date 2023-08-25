package com.kyowon.sms.wells.web.closing.performance.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.closing.performance.dto.WdccPrepaymentExpirationDto.SearchCharacterFwIzReq;
import com.kyowon.sms.wells.web.closing.performance.dto.WdccPrepaymentExpirationDto.SearchCharacterFwIzRes;
import com.kyowon.sms.wells.web.closing.performance.dto.WdccPrepaymentExpirationDto.SearchCharacterFwUldReq;
import com.kyowon.sms.wells.web.closing.performance.dto.WdccPrepaymentExpirationDto.SearchCharacterFwUldRes;
import com.kyowon.sms.wells.web.closing.performance.dto.WdccPrepaymentExpirationDto.SearchReq;
import com.kyowon.sms.wells.web.closing.performance.dto.WdccPrepaymentExpirationDto.SearchRes;
import com.kyowon.sms.wells.web.closing.performance.mapper.WdccPrepaymentExpirationMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WdccPrepaymentExpirationService {

    private final WdccPrepaymentExpirationMapper mapper;

    public PagingResult<SearchRes> getObjectPresentState(SearchReq dto, PageInfo pageInfo) {
        return this.mapper.selectObjectPresentState(dto, pageInfo);
    }

    public List<SearchRes> getObjectPresentState(SearchReq dto) {
        return this.mapper.selectObjectPresentState(dto);
    }

    public PagingResult<SearchCharacterFwUldRes> getCharacterFwUld(SearchCharacterFwUldReq dto, PageInfo pageInfo) {
        return this.mapper.selectCharacterFwUld(dto, pageInfo);
    }

    public List<SearchCharacterFwUldRes> getCharacterFwUld(SearchCharacterFwUldReq dto) {
        return this.mapper.selectCharacterFwUld(dto);
    }

    public PagingResult<SearchCharacterFwIzRes> getCharacterFwIz(SearchCharacterFwIzReq dto, PageInfo pageInfo) {
        return this.mapper.selectCharacterFwIz(dto, pageInfo);
    }

    public List<SearchCharacterFwIzRes> getCharacterFwIz(SearchCharacterFwIzReq dto) {
        return this.mapper.selectCharacterFwIz(dto);
    }

}
