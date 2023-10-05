package com.kyowon.sms.wells.web.service.stock.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.stock.dvo.WsnaMdProdcutOutOfStorageSearchDvo;

@Mapper
public interface WsnaMdProductOutOfStorageMgtMapper {

    String selectBusinessDays(WsnaMdProdcutOutOfStorageSearchDvo dvo);

    List selectMdProductOutOfStorages(WsnaMdProdcutOutOfStorageSearchDvo dvo);
}
