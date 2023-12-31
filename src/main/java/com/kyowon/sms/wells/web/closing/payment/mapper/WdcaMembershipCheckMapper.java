package com.kyowon.sms.wells.web.closing.payment.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.closing.payment.dto.WdcaMembershipCheckDto.SearchAfterRes;
import com.kyowon.sms.wells.web.closing.payment.dto.WdcaMembershipCheckDto.SearchReq;
import com.kyowon.sms.wells.web.closing.payment.dto.WdcaMembershipCheckDto.SearchRes;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

/**
 * <pre>
 * 영업마감(wells) - 멤버십확정 체크리스트
 * </pre>
 *
 * @author kicheol.choi
 * @since 2023-04-04
 */
@Mapper
public interface WdcaMembershipCheckMapper {
    PagingResult<SearchRes> selectBeforePages(
        PageInfo pageInfo
    );

    List<SearchRes> selectBeforePages();

    PagingResult<SearchAfterRes> selectAfterPages(
        SearchReq dto, PageInfo pageInfo
    );

    List<SearchAfterRes> selectAfterPages(
        SearchReq dto
    );
}
