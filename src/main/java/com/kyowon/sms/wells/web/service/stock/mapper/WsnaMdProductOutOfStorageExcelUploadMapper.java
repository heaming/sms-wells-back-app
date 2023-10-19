package com.kyowon.sms.wells.web.service.stock.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.stock.dvo.WsnaMdProductOutOfStorageExcelUploadDvo;

@Mapper
public interface WsnaMdProductOutOfStorageExcelUploadMapper {

    int selectExistMdProductOutOfStorage(WsnaMdProductOutOfStorageExcelUploadDvo dvo);

    int selectExistSppIvcNo(WsnaMdProductOutOfStorageExcelUploadDvo dvo);

    int insertSppBzsInvoiceProcessIz(WsnaMdProductOutOfStorageExcelUploadDvo dvo);

    int updateSvpdCstSvWkRsIz(WsnaMdProductOutOfStorageExcelUploadDvo dvo);

    int updateSvpdCstSvasIstAsnIz(WsnaMdProductOutOfStorageExcelUploadDvo dvo);
}
