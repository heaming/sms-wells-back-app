package com.kyowon.sms.wells.web.service.stock.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaOutOfStorageAskMngtDto.SearchReq;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaOutOfStorageAskMngtDto.SearchRes;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaOutOfStorageAskMngtDto.Warehouse;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaOutOfStorageAskMngtDto.WarehouseReq;

/**
 * <pre>
 * W-SV-U-0117M01 출고요청 관리
 * </pre>
 *
 * @author gs.piit130 김혜원
 * @since 2022.11.25
 */
@Mapper
public interface WsnaOutOfStorageAskMngtMapper {

    List<SearchRes> selectOutOfStorageAsks(SearchReq dto);

    List<Warehouse> selectWarehouses(WarehouseReq dto);

}
