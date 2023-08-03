package com.kyowon.sms.wells.web.service.stock.mapper;

import java.util.List;

import com.kyowon.sms.wells.web.service.common.dvo.WsnzWellsCodeWareHouseDvo;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaStockAcinspRgstMngtDvo;
import org.apache.ibatis.annotations.Mapper;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaStockAcinspRgstMngtDto.*;

import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WsnaStockAcinspRgstMngtMapper {

    PagingResult<SearchRes> selectStockAcinspRgstMngtPages(
        SearchReq dto,
        PageInfo pageInfo
    );

    List<SearchRes> selectStockAcinspRgstMngtPages(
        SearchReq dto
    );

    List<WsnzWellsCodeWareHouseDvo> selectWarehouse(SearchWareReq dto);

    List<WsnaStockAcinspRgstMngtDvo> selectStocAcinspIz(WsnaStockAcinspRgstMngtDvo dvo);

    int selectChkCountAcinsp(WsnaStockAcinspRgstMngtDvo dvo);

    int insertStockAcinspIz(List<WsnaStockAcinspRgstMngtDvo> stocApyDvoList);

    int insertStockAcinsp(WsnaStockAcinspRgstMngtDvo dvo);

    int insertStockAcinspCstSvItmStocIz(List<WsnaStockAcinspRgstMngtDvo> stocApyDvoList);

    int updateStockAcinspIostRfdt(List<WsnaStockAcinspRgstMngtDvo> stocApyDvoList);

    List<WsnaStockAcinspRgstMngtDvo> selectAcinspRgstCnfm(WsnaStockAcinspRgstMngtDvo dvo);

    int selectChkCountCnfm(WsnaStockAcinspRgstMngtDvo dvo);

    int updateStockAcinspIzCnfm(List<WsnaStockAcinspRgstMngtDvo> reSearchDvo);

    List<WsnaStockAcinspRgstMngtDvo> selectDeleteAcinspRgstCancel(WsnaStockAcinspRgstMngtDvo dvo);

    int selectChkAcinspRgstCancel(WsnaStockAcinspRgstMngtDvo dvo);

    int updateStockAcinspIzCancel(List<WsnaStockAcinspRgstMngtDvo> reDeleteDvo);

}
