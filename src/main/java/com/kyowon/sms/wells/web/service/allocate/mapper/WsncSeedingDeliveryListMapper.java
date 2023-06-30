package com.kyowon.sms.wells.web.service.allocate.mapper;

import static com.kyowon.sms.wells.web.service.allocate.dto.WsncSeedingDeliveryListDto.SearchReq;
import static com.kyowon.sms.wells.web.service.allocate.dto.WsncSeedingDeliveryListDto.SearchRes;

import org.apache.ibatis.annotations.Mapper;

import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

/**
 *
 * <pre>
 * 모종 배송내역
 * </pre>
 *
 * @author heymi.cho 조혜미
 * @since 2023.06.23
 */
@Mapper
public interface WsncSeedingDeliveryListMapper {

    /**
     * 조회
     *
     * @param dto : SearchReq csnrNo(계약번호), csnrSn(계약일련번호)
     * @return 조회결과
     */
    PagingResult<SearchRes> selectSeedingDeliveryList(
        SearchReq dto, PageInfo pageInfo
    );
}
