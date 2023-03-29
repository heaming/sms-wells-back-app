package com.kyowon.sms.wells.web.service.common.mapper;

import com.kyowon.sms.wells.web.service.common.dto.WsnyPaidAsCostMgtDto.*;
import com.kyowon.sms.wells.web.service.common.dvo.WsnyPaidAsCostMgtDvo;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <pre>
 * W-SV-U-0159M01 유상A/S 서비스비용 관리
 * </pre>
 *
 * @author kyunglyn.lee
 * @since 2023.03.08
 */
@Mapper
public interface WsnyPaidAsCostMgtMapper {

    PagingResult<SearchRes> selectPaidAsCostMgts(SearchReq dto, PageInfo pageInfo);

    List<PdRes> selectHgrPdCd(PdReq dto);

    List<SearchRes> selectPaidAsCostMgts(SearchReq dto);

    int updatePaidAsCostMgts(WsnyPaidAsCostMgtDvo dvo);
}
