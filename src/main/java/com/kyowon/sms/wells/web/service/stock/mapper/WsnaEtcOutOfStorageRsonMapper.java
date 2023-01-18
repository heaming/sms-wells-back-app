package com.kyowon.sms.wells.web.service.stock.mapper;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaEtcOutOfStorageRsonDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaEtcOutOfStorageRsonDto.*;

@Mapper
public interface WsnaEtcOutOfStorageRsonMapper {

    List<SearchRes> selectEtcOutOfStorageRsons(SearchReq dto);

    List<CenterRes> selectServiceCenters(SearchReq dto);

    List<BusinessRes> selectBusinessCenter(SearchReq dto);

    List<SearchRes> selectEtcOutOfStorageRsonBusiness(SearchReq dto);
}
