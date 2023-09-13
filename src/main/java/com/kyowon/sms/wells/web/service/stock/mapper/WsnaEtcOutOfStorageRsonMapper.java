package com.kyowon.sms.wells.web.service.stock.mapper;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaEtcOutOfStorageRsonDto.*;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WsnaEtcOutOfStorageRsonMapper {

    PagingResult<SearchRes> selectEtcOutOfStorageRsons(SearchReq dto, PageInfo pageInfo);

    List<SearchRes> selectEtcOutOfStorageRsons(SearchReq dto);

    List<CenterRes> selectServiceCenters(SearchReq dto);

    List<BusinessRes> selectBusinessCenter(SearchReq dto);

    List<SearchRes> selectEtcOutOfStorageRsonBusiness(SearchReq dto);

}
