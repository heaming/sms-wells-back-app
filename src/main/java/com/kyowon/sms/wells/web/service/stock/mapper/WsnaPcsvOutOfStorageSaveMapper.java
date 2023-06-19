package com.kyowon.sms.wells.web.service.stock.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.stock.dvo.WsnaPcsvOutOfStorageSaveDvo;

@Mapper
public interface WsnaPcsvOutOfStorageSaveMapper {
    void insertPcsvOutOfStorage(WsnaPcsvOutOfStorageSaveDvo vo);

    void insertPcsvOutOfStorageRvPy(WsnaPcsvOutOfStorageSaveDvo vo);

    void insertPcsvOutOfStorageRshp(WsnaPcsvOutOfStorageSaveDvo vo);
}
