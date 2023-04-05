package com.kyowon.sms.wells.web.service.stock.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaBsCsmbDeliveryBaseDto.CreateReq;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaBsCsmbDeliveryBaseDto.SearchItemsRes;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaBsCsmbDeliveryBaseDto.SearchReq;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaBsCsmbDeliveryBaseDto.SearchRes;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaBsCsmbDeliveryBaseDvo;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WsnaBsCsmbDeliveryBaseMapper {
    List<SearchRes> selectDeliveryBases(SearchReq dto);

    PagingResult<SearchRes> selectDeliveryBases(SearchReq dto, PageInfo pageInfo);

    int insertDeliveryBasesNextMonth();

    int insertDeliveryBaseDtlsNextMonth();

    List<SearchItemsRes> selectAllItemInformation();

    String selectDeliveryBaseDuplicateYn(CreateReq dto);

    int insertDeliveryBase(WsnaBsCsmbDeliveryBaseDvo dvo);
}
