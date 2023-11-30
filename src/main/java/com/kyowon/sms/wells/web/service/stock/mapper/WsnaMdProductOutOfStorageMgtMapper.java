package com.kyowon.sms.wells.web.service.stock.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.stock.dvo.WsnaMdProdcutOutOfStorageSearchDvo;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaMdProductOutStorageDvo;

@Mapper
public interface WsnaMdProductOutOfStorageMgtMapper {

    String selectLoginPrtnrBzs();

    String selectBusinessDays(WsnaMdProdcutOutOfStorageSearchDvo dvo);

    List<WsnaMdProductOutStorageDvo> selectMdProductOutOfStorages(WsnaMdProdcutOutOfStorageSearchDvo dvo);
}
