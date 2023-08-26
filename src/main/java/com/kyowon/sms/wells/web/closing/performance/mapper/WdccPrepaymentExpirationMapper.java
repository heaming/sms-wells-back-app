package com.kyowon.sms.wells.web.closing.performance.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.closing.performance.dto.WdccPrepaymentExpirationDto.SearchCharacterFwIzReq;
import com.kyowon.sms.wells.web.closing.performance.dto.WdccPrepaymentExpirationDto.SearchCharacterFwIzRes;
import com.kyowon.sms.wells.web.closing.performance.dto.WdccPrepaymentExpirationDto.SearchCharacterFwUldReq;
import com.kyowon.sms.wells.web.closing.performance.dto.WdccPrepaymentExpirationDto.SearchCharacterFwUldRes;
import com.kyowon.sms.wells.web.closing.performance.dto.WdccPrepaymentExpirationDto.SearchReq;
import com.kyowon.sms.wells.web.closing.performance.dto.WdccPrepaymentExpirationDto.SearchRes;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WdccPrepaymentExpirationMapper {

    PagingResult<SearchRes> selectObjectPresentState(SearchReq dto, PageInfo pageInfo);

    List<SearchRes> selectObjectPresentState(SearchReq req);

    PagingResult<SearchCharacterFwUldRes> selectCharacterFwUld(SearchCharacterFwUldReq dto, PageInfo pageInfo);

    List<SearchCharacterFwUldRes> selectCharacterFwUld(SearchCharacterFwUldReq req);

    PagingResult<SearchCharacterFwIzRes> selectCharacterFwIz(SearchCharacterFwIzReq dto, PageInfo pageInfo);

    List<SearchCharacterFwIzRes> selectCharacterFwIz(SearchCharacterFwIzReq req);

}
