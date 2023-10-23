package com.kyowon.sms.wells.web.service.stock.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.stock.dvo.WsnaMdProductReturningGoodsSaveDvo;

@Mapper
public interface WsnaMdProductReturningGoodsSaveMapper {

    int selectExistSvpdCstSvWkRsIz(WsnaMdProductReturningGoodsSaveDvo vo);

    void updateSvpdCstSvasAsIstAsnIz(WsnaMdProductReturningGoodsSaveDvo vo);

    void updateSvpdCstSvasIstAsnIz(WsnaMdProductReturningGoodsSaveDvo vo);

    void insertSvpdCstSvWkRsIz(WsnaMdProductReturningGoodsSaveDvo vo);

    void updateSvpdCstSvExcnIz(WsnaMdProductReturningGoodsSaveDvo vo);

    void updateSvpdCstSvBfsvcAsnIz(WsnaMdProductReturningGoodsSaveDvo vo);

    void insertSvstSvWkOstrIz(WsnaMdProductReturningGoodsSaveDvo vo);

}
