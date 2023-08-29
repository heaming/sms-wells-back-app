package com.kyowon.sms.wells.web.closing.performance.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.closing.performance.dto.WdccPrepaymentExpirationDto.SearchCharacterFwIzReq;
import com.kyowon.sms.wells.web.closing.performance.dto.WdccPrepaymentExpirationDto.SearchCharacterFwIzRes;
import com.kyowon.sms.wells.web.closing.performance.dto.WdccPrepaymentExpirationDto.SearchCharacterFwUldReq;
import com.kyowon.sms.wells.web.closing.performance.dto.WdccPrepaymentExpirationDto.SearchCharacterFwUldRes;
import com.kyowon.sms.wells.web.closing.performance.dto.WdccPrepaymentExpirationDto.SearchReq;
import com.kyowon.sms.wells.web.closing.performance.dto.WdccPrepaymentExpirationDto.SearchRes;

@Mapper
public interface WdccPrepaymentExpirationMapper {

    List<SearchRes> selectObjectPresentState(SearchReq req);

    List<SearchCharacterFwUldRes> selectCharacterFwUld(SearchCharacterFwUldReq req);

    List<SearchCharacterFwIzRes> selectCharacterFwIz(SearchCharacterFwIzReq req);

}
