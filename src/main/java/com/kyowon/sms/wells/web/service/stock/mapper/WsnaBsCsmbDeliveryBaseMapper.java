package com.kyowon.sms.wells.web.service.stock.mapper;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaBsCsmbDeliveryBaseDto.*;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaBsCsmbDeliveryBaseDvo;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WsnaBsCsmbDeliveryBaseMapper {
    List<SearchRes> selectDeliveryBases(SearchReq dto);

    PagingResult<SearchRes> selectDeliveryBases(SearchReq dto, PageInfo pageInfo);

    int insertDeliveryBasesNowMonth();

    int insertDeliveryBaseDtlsNowMonth();

    int insertDeliveryBasesNextMonth();

    int insertDeliveryBaseDtlsNextMonth();

    List<SearchItemsRes> selectAllItemInformation();

    String selectDeliveryBaseDuplicateYn(CreateReq dto);

    int insertDeliveryBase(WsnaBsCsmbDeliveryBaseDvo dvo);

    Optional<FindRes> selectDeliveryBase(String mngtYm, String csmbPdCd);

    int updateDeliveryBase(WsnaBsCsmbDeliveryBaseDvo dvo);

    int selectExistNowMonthDeliveryBase();

    int selectExistNowMonthDeliveryDtl();
}
