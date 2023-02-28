package com.kyowon.sms.wells.web.service.stock.mapper;

import java.util.List;

import com.kyowon.sms.wells.web.service.stock.dvo.WsnaEtcOutOfStorageDvo;
import org.apache.ibatis.annotations.Mapper;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaEtcOutOfStorageDto.*;

@Mapper
public interface WsnaEtcOutOfStorageMapper {

    List<SearchRes> selectEtcOutOfStorages(SearchReq dto);

    int deleteEtcOutOfStorages(WsnaEtcOutOfStorageDvo etcOutOfStorage);

    String selectNewItmOstrNo(WsnaEtcOutOfStorageDvo dvo);

    String selectNewOstrSn(WsnaEtcOutOfStorageDvo dvo);
}
