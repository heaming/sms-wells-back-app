package com.kyowon.sms.wells.web.service.allocate.mapper;

import static com.kyowon.sms.wells.web.service.allocate.dto.WsncCompanyInstallationDto.*;

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
public interface WsncCompanyInstallationMapper {

    /**
     * 전체 탭 조회
     *
     * @param dto : SearchReq mgtYnm(배정년월)
     * @return 조회결과
     */
    PagingResult<SearchAllRes> selectCompanyInstallationAll(
        SearchMainReq dto, PageInfo pageInfo
    );

    /**
     * 전체 탭 엑셀 다운로드
     *
     * @param dto : SearchReq mgtYnm(배정년월)
     * @return 조회결과
     */
    List<SearchAllRes> selectCompanyInstallationAll(
        SearchMainReq dto
    );

    /**
     * 현황 탭 조회
     *
     * @param dto : SearchReq mgtTyps(관리유형), istDtFrom(설치일자 시작), istDtTo(설치일자 종료)
     * @return 조회결과
     */
    PagingResult<SearchPsRes> selectCompanyInstallationPs(
        SearchPsReq dto, PageInfo pageInfo
    );

    /**
     * 현황 탭 엑셀 다운로드
     *
     * @param dto : SearchReq mgtYnm(배정년월)
     * @return 조회결과
     */
    List<SearchPsRes> selectCompanyInstallationPs(
        SearchPsReq dto
    );

    /**
     * 필터 / 부자재 탭 조회
     *
     * @param dto : SearchReq mgtYnm(배정년월), itmKndCd(품목종류 - 5/필터 6/부자재)
     * @return 조회결과
     */
    PagingResult<SearchFltrSubMatRes> selectCompanyInstallationFltrOrSubMat(
        SearchMainReq dto, PageInfo pageInfo
    );

    /**
     * 필터 탭 엑셀 다운로드
     *
     * @param dto : SearchReq mgtYnm(배정년월), itmKndCd(품목종류 - 5/필터 6/부자재)
     * @return 조회결과
     */
    List<SearchFltrSubMatRes> selectCompanyInstallationFltrOrSubMat(
        SearchMainReq dto
    );
    //
    //    /**
    //     * 부자재 탭 조회
    //     *
    //     * @param dto : SearchReq mgtYnm(배정년월)
    //     * @return 조회결과
    //     */
    //    PagingResult<SearchFltrSubMatRes> selectCompanyIstStateSubMat(
    //        SearchMainReq dto, PageInfo pageInfo
    //    );
    //
    //    /**
    //     * 부자재 탭 액셀 다운로드
    //     *
    //     * @param dto : SearchReq mgtYnm(배정년월)
    //     * @return 조회결과
    //     */
    //    List<SearchFltrSubMatRes> selectCompanyIstStateSubMat(
    //        SearchMainReq dto
    //    );
}
