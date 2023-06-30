package com.kyowon.sms.wells.web.service.stock.mapper;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaSeedingOstrQtyDto.SearchReq;
import static com.kyowon.sms.wells.web.service.stock.dto.WsnaSeedingOstrQtyDto.SearchRes;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.stock.dvo.WsnaSeedingOstrQtyDvo;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WsnaSeedingOstrQtyMapper {

    PagingResult<SearchRes> selectDcbySdingRcpLim(SearchReq dto, PageInfo pageInfo);

    List<SearchRes> selectDcbySdingRcpLim(SearchReq dto);

    int updateDcbySdingRcpLimQty(WsnaSeedingOstrQtyDvo dvo);

    List<String> selectCmzSvBizHclsfCds();

    List<String> selectCmzPkgDvCds();

    int insertDcbySdingRcpLimQty(WsnaSeedingOstrQtyDvo dvo);

}
