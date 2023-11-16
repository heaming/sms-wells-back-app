package com.kyowon.sms.wells.web.service.stock.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaInstallationStockPsByDayDto.SearchReq;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaInstallationStockPsByDayDto.SearchResCenter;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaInstallationStockPsByDayDto.SearchResPd;
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

    PagingResult<SearchResCenter> selectInstallationStockPsByDayCenter(SearchReq dto, PageInfo pageInfo);

    List<SearchResCenter> selectInstallationStockPsByDayCenter(SearchReq dto);

    PagingResult<SearchResPd> selectInstallationStockPsByDayPd(SearchReq dto, PageInfo pageInfo);

    List<SearchResPd> selectInstallationStockPsByDayPd(SearchReq dto);

}
