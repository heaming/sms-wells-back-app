package com.kyowon.sms.wells.web.service.stock.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaInstallationStockPsByDayDto.SearchReq;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaInstallationStockPsByDayDto.SearchResPsWareStr;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaInstallationStockPsByDayCenterDvo;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaInstallationStockPsByDayPdDvo;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

/**
 * <pre>
 * K-W-SV-U-0116M01 일자별 설치재고 현황
 * </pre>
 *
 * @author heymi.cho 조혜미
 * @since 2023.07.11
 */
@Mapper
public interface WsnaInstallationStockPsByDayMapper {

    PagingResult<WsnaInstallationStockPsByDayCenterDvo> selectInstallationStockPsByDayCenter(
        SearchReq dto, PageInfo pageInfo
    );

    List<WsnaInstallationStockPsByDayCenterDvo> selectInstallationStockPsByDayCenter(SearchReq dto);

    PagingResult<WsnaInstallationStockPsByDayPdDvo> selectInstallationStockPsByDayPd(SearchReq dto, PageInfo pageInfo);

    List<WsnaInstallationStockPsByDayPdDvo> selectInstallationStockPsByDayPd(SearchReq dto);

    List<SearchResPsWareStr> selectPsWareStr(SearchReq dto);

}
