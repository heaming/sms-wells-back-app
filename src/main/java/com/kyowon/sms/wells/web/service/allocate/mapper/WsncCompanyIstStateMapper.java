package com.kyowon.sms.wells.web.service.allocate.mapper;

import static com.kyowon.sms.wells.web.service.allocate.dto.WsncCompanyIstStateDto.*;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

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
    PagingResult<SearchAllRes> selectCompanyIstStateAll(
        SearchMainReq dto, PageInfo pageInfo
    );

    /**
     * 전체 탭 엑셀 다운로드
     *
     * @param dto : SearchReq mgtYnm(배정년월)
     * @return 조회결과
     */
    List<SearchAllRes> selectCompanyIstStateAll(
        SearchMainReq dto
    );

    /**
     * 현황 탭 조회
     *
     * @param dto : SearchReq mgtYnm(배정년월)
     * @return 조회결과
     */
    PagingResult<SearchPsRes> selectCompanyIstStatePs(
        SearchPsReq dto, PageInfo pageInfo
    );

    /**
     * 현황 탭 엑셀 다운로드
     *
     * @param dto : SearchReq mgtYnm(배정년월)
     * @return 조회결과
     */
    List<SearchPsRes> selectCompanyIstStatePs(
        SearchPsReq dto
    );
}
