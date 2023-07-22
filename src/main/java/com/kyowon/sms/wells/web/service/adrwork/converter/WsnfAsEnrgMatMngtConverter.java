package com.kyowon.sms.wells.web.service.adrwork.converter;

import com.kyowon.sms.wells.web.service.adrwork.dvo.WsnfAsEnrgMatMngtDvo;
import com.kyowon.sms.wells.web.service.adrwork.dto.WsnfAsEnrgMatMngtDto.*;
import com.sds.sflex.system.config.datasource.PagingResult;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface WsnfAsEnrgMatMngtConverter {

    PagingResult<SearchRes> mapWsnfAsEnrgMatMngtDvoToSearchRes(
        List<WsnfAsEnrgMatMngtDvo> dvos
    );
}
