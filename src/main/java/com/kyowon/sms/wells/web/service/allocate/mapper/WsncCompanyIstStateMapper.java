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
     * @param dto : SearchReq mgtTyps(관리유형), istDtFrom(설치일자 시작), istDtTo(설치일자 종료)
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

    //    <!-- TODO 개발중 - K-W-SV-U-0270M02 K-W-SV-U-0270M03 -->
    //    /**
    //     * 필터 탭 조회
    //     *
    //     * @param dto : SearchReq mgtYnm(배정년월)
    //     * @return 조회결과
    //     */
    //    PagingResult<SearchFltrSubMatRes> selectCompanyIstStateFltr(
    //        SearchMainReq dto, PageInfo pageInfo
    //    );
    //
    //    /**
    //     * 필터 탭 엑셀 다운로드
    //     *
    //     * @param dto : SearchReq mgtYnm(배정년월)
    //     * @return 조회결과
    //     */
    //    List<SearchFltrSubMatRes> selectCompanyIstStateFltr(
    //        SearchMainReq dto
    //    );
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
