package com.kyowon.sms.wells.web.service.stock.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaStoreNaprvStateDto.SearchReq;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaStoreNaprvStateDto.SearchRes;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

/**
 *
 * <pre>
 * 입고 미승인현황
 * </pre>
 *
 * @author heymi.cho 조혜미
 * @since 2023.06.20
 */
@Mapper
public interface WsnaStoreNaprvStateMapper {
    /**
     * 조회 (페이징)
     *
     * @param dto : SearchReq(strWareDvCd-입고창고구분코드, strWareNoM-상위창고코드, strWareNoD-창고코드)
     * @return 조회결과
     */
    PagingResult<SearchRes> selectStoreNaprvState(
        SearchReq dto, PageInfo pageInfo
    );

    /**
     * 조회
     *
     * @param dto : SearchReq(strWareDvCd-입고창고구분코드, strWareNoM-상위창고코드, strWareNoD-창고코드)
     * @return 조회결과
     */
    List<SearchRes> selectStoreNaprvState(
        SearchReq dto
    );
}
