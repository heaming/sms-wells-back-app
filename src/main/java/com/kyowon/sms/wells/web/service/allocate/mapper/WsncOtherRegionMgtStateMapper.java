package com.kyowon.sms.wells.web.service.allocate.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.allocate.dto.WsncOtherRegionMgtStateDto;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

/**
 *
 * <pre>
 * 타지역단 관리 현황
 * </pre>
 *
 * @author heymi.cho 조혜미
 * @since 2023.06.01
 */
@Mapper
public interface WsncOtherRegionMgtStateMapper {

    /**
     * 조회
     *
     * @param dto : SearchReq mgtYnm(배정년월)
     * @return 조회결과
     */
    PagingResult<WsncOtherRegionMgtStateDto.SearchRes> selectOtherRegionMgtState(
        WsncOtherRegionMgtStateDto.SearchReq dto, PageInfo pageInfo
    );

    List<WsncOtherRegionMgtStateDto.SearchRes> selectOtherRegionMgtState(
        WsncOtherRegionMgtStateDto.SearchReq dto
    );

}
