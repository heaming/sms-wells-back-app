package com.kyowon.sms.wells.web.service.stock.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.stock.dvo.WsnaMdProdcutOutOfStorageSaveDvo;

@Mapper
public interface WsnaMdProductOutOfStorageSaveMapper {

    int selectExistSvpdCstSvWkRsIz(WsnaMdProdcutOutOfStorageSaveDvo vo);

    void updateSvpdCstSvBfsvcAsnIz(WsnaMdProdcutOutOfStorageSaveDvo vo);

    void updateSvpdCstSvasIstAsnIz(WsnaMdProdcutOutOfStorageSaveDvo vo);

    void updateSvpdCstSvExcnIz(WsnaMdProdcutOutOfStorageSaveDvo vo);

    void insertSvpdCstSvWkRsIz(WsnaMdProdcutOutOfStorageSaveDvo vo);

    void insertSvstSvWkOstrIz(WsnaMdProdcutOutOfStorageSaveDvo vo);

    void updateSvpdCstSvExcnIzCancel(WsnaMdProdcutOutOfStorageSaveDvo vo);

    void updateSvstSvWkOstrIzCancel(WsnaMdProdcutOutOfStorageSaveDvo vo);

    void updateSvpdCstSvasIstAsnIzCancel(WsnaMdProdcutOutOfStorageSaveDvo vo);

    void updateSvpdCstSvBfsvcAsnIzCancel(WsnaMdProdcutOutOfStorageSaveDvo vo);

    void deleteSvpdCstSvWkRsIzCancel(WsnaMdProdcutOutOfStorageSaveDvo vo);
}
