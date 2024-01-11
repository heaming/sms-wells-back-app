package com.kyowon.sms.wells.web.service.common.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.common.dto.WsnyPaidAsCostMgtDto.SearchReq;
import com.kyowon.sms.wells.web.service.common.dto.WsnyPaidAsCostMgtDto.SearchRes;
import com.kyowon.sms.wells.web.service.common.dvo.WsnyPaidAsCostMgtDvo;

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

    List<SearchRes> selectPaidAsCostMgts(SearchReq dto);

    int insertPaidAsCostMgts(WsnyPaidAsCostMgtDvo dvo);

    int updateApyStrtdt(WsnyPaidAsCostMgtDvo dvo);

    int updatePaidAsCostMgts(WsnyPaidAsCostMgtDvo dvo);
}
