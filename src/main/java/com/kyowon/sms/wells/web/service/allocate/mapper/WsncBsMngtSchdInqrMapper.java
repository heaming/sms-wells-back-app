package com.kyowon.sms.wells.web.service.allocate.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.allocate.dto.WsncBsMngtSchdInqrDto;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

/**
 *
 *
 * W-SV-U-0089M01 BS관리일정조회
 *
 *
 * @author 37615 홍세기
 * @since 2023-06-06
 */

@Mapper
public interface WsncBsMngtSchdInqrMapper {
    List<WsncBsMngtSchdInqrDto.SearchRes> selectBsMngtSchdInqrAgrg(
        WsncBsMngtSchdInqrDto.SearchReq dto
    );

    PagingResult<WsncBsMngtSchdInqrDto.SearchRes> selectBsMngtSchdInqrPages(
        WsncBsMngtSchdInqrDto.SearchReq dto, PageInfo pageInfo
    );

}
