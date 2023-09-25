package com.kyowon.sms.wells.web.service.stock.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.stock.dvo.WsnaMdProdcutOutOfStorageSaveDvo;

@Mapper
public interface WsnaMdProductOutOfStorageExcelUploadMapper {
    void updateSvpdCstSvExcnIz(WsnaMdProdcutOutOfStorageSaveDvo vo);
}
