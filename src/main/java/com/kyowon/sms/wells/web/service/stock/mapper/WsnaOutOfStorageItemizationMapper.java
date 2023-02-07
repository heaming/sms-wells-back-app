package com.kyowon.sms.wells.web.service.stock.mapper;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaOutOfStorageItemizationDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaOutOfStorageItemizationDto.*;

/**
 * <pre>
 * W-SV-U-0141M01 출고관리
 * </pre>
 *
 * @author songTaeSung
 * @since 2023-01-30
 */
@Mapper
public interface WsnaOutOfStorageItemizationMapper {

    List<SearchRes> selectOutOfStorageItemizations(SearchReq dto);
}
