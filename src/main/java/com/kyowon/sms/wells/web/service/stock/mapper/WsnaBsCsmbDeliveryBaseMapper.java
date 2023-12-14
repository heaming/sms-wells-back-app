package com.kyowon.sms.wells.web.service.stock.mapper;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaBsCsmbDeliveryBaseDto.SearchItemsRes;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaBsCsmbDeliveryBaseDto.SearchReq;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaBsCsmbDeliveryBaseDto.SearchRes;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaBsCsmbDeliveryBaseDvo;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WsnaBsCsmbDeliveryBaseMapper {

    List<SearchItemsRes> selectAllItemInformation();

    PagingResult<SearchRes> selectDeliveryBases(SearchReq dto, PageInfo pageInfo);

    List<SearchRes> selectDeliveryBases(SearchReq dto);

    int selectExistNowMonthDeliveryBase(String mngtYm);

    int selectExistNowMonthDeliveryDtl(String mngtYm);

    int insertDeliveryBasesNowMonth(String carriedOverFrom, String carriedOverTo);

    int insertDeliveryBaseDtlsNowMonth(String carriedOverFrom, String carriedOverTo);

    String selectDeliveryBaseDuplicateYn(WsnaBsCsmbDeliveryBaseDvo dto);

    int insertDeliveryBase(WsnaBsCsmbDeliveryBaseDvo dvo);

    Optional<SearchRes> selectDeliveryBases(String mngtYm, String csmbPdCd);

    int updateDeliveryBase(WsnaBsCsmbDeliveryBaseDvo dvo);

}
