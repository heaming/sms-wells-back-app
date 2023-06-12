package com.kyowon.sms.wells.web.service.allocate.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.allocate.dto.WsncCompanyIstStateDto;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

/**
 *
 * <pre>
 * 회사설치 (8888코드) 현황
 * </pre>
 *
 * @author heymi.cho 조혜미
 * @since 2023.05.24
 */
@Mapper
public interface WsncCompanyIstStateMapper {

    /**
     * 전체 탭 조회
     *
     * @param dto : SearchReq mgtYnm(배정년월)
     * @return 조회결과
     */
    PagingResult<WsncCompanyIstStateDto.SearchAllRes> selectCompanyIstStateAll(
        WsncCompanyIstStateDto.SearchReq dto, PageInfo pageInfo
    );

}
