package com.kyowon.sms.wells.web.service.stock.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.stock.dvo.WsnaPcsvOutOfStorageSaveDvo;

@Mapper
public interface WsnaPcsvOutOfStorageSaveMapper {

    int selectExistSvpdCstSvWkRsIz(WsnaPcsvOutOfStorageSaveDvo vo);

    WsnaPcsvOutOfStorageSaveDvo selectEngineerOgbsMmPrtnrIz(WsnaPcsvOutOfStorageSaveDvo vo);

    WsnaPcsvOutOfStorageSaveDvo selectQtySvstCstSvItmStocIz(WsnaPcsvOutOfStorageSaveDvo vo);

    WsnaPcsvOutOfStorageSaveDvo selectAsCodeSvpdCstSvasIstOjIz(WsnaPcsvOutOfStorageSaveDvo vo);

    WsnaPcsvOutOfStorageSaveDvo selectReturningSvpdCstSvasIstOjIz(WsnaPcsvOutOfStorageSaveDvo vo);

    void updateSvpdCstSvasIstAsnIz(WsnaPcsvOutOfStorageSaveDvo vo);

    void insertSvpdCstSvWkRsIz(WsnaPcsvOutOfStorageSaveDvo vo);

    void insertSvstSvWkOstrIz(WsnaPcsvOutOfStorageSaveDvo vo);
}
