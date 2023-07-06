package com.kyowon.sms.wells.web.service.stock.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.stock.dvo.WsnaPcsvReturningGoodsSaveDvo;

@Mapper
public interface WsnaPcsvReturningGoodsSaveMapper {

    int selectExistSvpdCstSvWkRsIz(WsnaPcsvReturningGoodsSaveDvo vo);

    WsnaPcsvReturningGoodsSaveDvo selectEngineerOgbsMmPrtnrIz(WsnaPcsvReturningGoodsSaveDvo vo);

    void updateSvpdCstSvasAsIstAsnIz(WsnaPcsvReturningGoodsSaveDvo vo);

    void updateSvpdCstSvasIstAsnIz(WsnaPcsvReturningGoodsSaveDvo vo);

    void insertSvpdCstSvWkRsIz(WsnaPcsvReturningGoodsSaveDvo vo);

    void updateSvpdCstSvExcnIz(WsnaPcsvReturningGoodsSaveDvo vo);

    void updateSvpdCstSvBfsvcAsnIz(WsnaPcsvReturningGoodsSaveDvo vo);

    void insertSvstSvWkOstrIz(WsnaPcsvReturningGoodsSaveDvo vo);
}
