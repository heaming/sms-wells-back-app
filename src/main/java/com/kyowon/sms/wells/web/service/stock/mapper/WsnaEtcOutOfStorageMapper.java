package com.kyowon.sms.wells.web.service.stock.mapper;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaEtcOutOfStorageDto.*;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.stock.dvo.WsnaEtcOutOfStorageDvo;

@Mapper
public interface WsnaEtcOutOfStorageMapper {

    List<SearchRes> selectEtcOutOfStorages(SearchReq dto);

    int deleteEtcOutOfStorages(WsnaEtcOutOfStorageDvo etcOutOfStorage);

    List<SearchDeptRes> selectEtcOutOfStorageDepts();

    String selectNewItmOstrNo(FindItmOstrNoReq findItmOstrNoReq);

    int insertEtcOutOfStorageOstrIz(WsnaEtcOutOfStorageDvo dvo);

    String selectWareMngtPrtnrNo(FindWareMngtPrtnrNoReq findWareMngtPrtnrNoReq);

    List<SearchCodeRes> selectWellsCenterWarehouse(String apyYm);
}
