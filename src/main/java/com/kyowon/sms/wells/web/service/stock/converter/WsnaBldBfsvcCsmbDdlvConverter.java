package com.kyowon.sms.wells.web.service.stock.converter;

import java.util.List;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaBldBfsvcCsmbDdlvDto.CreateReq;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaBldBfsvcCsmbDdlvDto.CreateTmlmReq;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaBldBfsvcCsmbDdlvDto.SearchRes;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaBldBfsvcCsmbDdlvDvo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper(componentModel = "spring")
public interface WsnaBldBfsvcCsmbDdlvConverter {
    PagingResult<SearchRes> mapDvoToSearchRes(List<WsnaBldBfsvcCsmbDdlvDvo> dvos);

    WsnaBldBfsvcCsmbDdlvDvo mapCreateTmlmReqToCsmbDblv(CreateTmlmReq dto);

    WsnaBldBfsvcCsmbDdlvDvo mapCreateReqToCsmbDblv(CreateReq dto);
}
