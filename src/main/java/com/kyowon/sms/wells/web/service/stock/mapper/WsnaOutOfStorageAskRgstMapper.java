package com.kyowon.sms.wells.web.service.stock.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaOutOfStorageAskRgstDto;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaOutOfStorageAskRgstDvo;
import com.sds.sflex.system.config.datasource.PageInfo;

/**
 *
 * <pre>
 * W-SV-U-0172P01 출고요청 등록
 * </pre>
 *
 * @author gs.piit130 김혜원
 * @since 2022.12.02
 */
@Mapper
public interface WsnaOutOfStorageAskRgstMapper {

    List<WsnaOutOfStorageAskRgstDvo> selectOutOfStorageAsks(
        WsnaOutOfStorageAskRgstDto.SearchReq dto, PageInfo pageInfo
    );

}
