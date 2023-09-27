package com.kyowon.sms.wells.web.service.stock.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.stock.dvo.WsnaMdProductOutOfStorageExcelUploadDvo;

@Mapper
public interface WsnaMdProductOutOfStorageExcelUploadMapper {

    int selectExistSppIvcNo(WsnaMdProductOutOfStorageExcelUploadDvo dvo);
}
