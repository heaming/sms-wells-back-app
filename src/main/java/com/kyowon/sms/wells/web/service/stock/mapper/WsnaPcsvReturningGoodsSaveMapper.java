package com.kyowon.sms.wells.web.service.stock.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.stock.dvo.WsnaPcsvReturningGoodsDvo;

@Mapper
public interface WsnaPcsvReturningGoodsSaveMapper {

    void savePcsvReturningGoods(WsnaPcsvReturningGoodsDvo vo);

    void savePcsvReturningGoodsCancel(WsnaPcsvReturningGoodsDvo vo);

    void savePcsvReturningGoodsReceivingAndPaying(WsnaPcsvReturningGoodsDvo vo);
}
