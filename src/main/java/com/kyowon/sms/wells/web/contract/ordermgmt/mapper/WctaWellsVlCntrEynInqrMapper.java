package com.kyowon.sms.wells.web.contract.ordermgmt.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaWellsVlCntrEynInqrDto.SearchReq;
import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.WctaWellsVlCntrEynInqrDvo;

@Mapper
public interface WctaWellsVlCntrEynInqrMapper {
    List<WctaWellsVlCntrEynInqrDvo> selectRentalCstInfo(SearchReq dto);

    List<WctaWellsVlCntrEynInqrDvo> selectMshCstInfo(SearchReq dto);

}
