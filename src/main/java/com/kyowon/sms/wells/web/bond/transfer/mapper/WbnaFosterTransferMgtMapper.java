package com.kyowon.sms.wells.web.bond.transfer.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.bond.transfer.dto.WbnaFosterTransferMgtDto.SearchDetailRes;
import com.kyowon.sms.wells.web.bond.transfer.dto.WbnaFosterTransferMgtDto.SearchDetailSummaryRes;
import com.kyowon.sms.wells.web.bond.transfer.dto.WbnaFosterTransferMgtDto.SearchReq;
import com.kyowon.sms.wells.web.bond.transfer.dto.WbnaFosterTransferMgtDto.SearchRes;
import com.kyowon.sms.wells.web.bond.transfer.dvo.WbnaBondContractBaseDvo;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

/**
 * <pre>
 * W-BN-U-0130M01	위탁 이관 관리
 * </pre>
 *
 * @author gs.piit128 gilyong.han
 * @since 2023-04-18
 */
@Mapper
public interface WbnaFosterTransferMgtMapper {

    /**
     * 위탁 이관 관리 집계 결과 조회
     * @param dvo
     * @return List<SearchRes>
     */
    List<SearchRes> selectFosterTransfers(WbnaBondContractBaseDvo dvo);

    /**
     * 위탁 이관 관리 집계 결과 상세 페이징 조회
     * @param dto, pageInfo
     * @return PagingResult<SearchDetailRes>
     */
    PagingResult<SearchDetailRes> selectFosterTransferDetails(SearchReq dto, PageInfo pageInfo);

    /**
     * 위탁 이관 관리 집계 결과 상세 페이징 - summary 조회
     * @param dto
     * @return SearchDetailSummaryRes
     */
    SearchDetailSummaryRes selectFosterTransferDetailsSummary(SearchReq dto);

    /**
     * 위탁 이관 관리 집계 결과 상세 엑셀 다운로드
     * @param dto, downFileName, pageId
     * @return List<SearchDetailRes>
     */
    List<SearchDetailRes> selectFosterTransferDetails(SearchReq dto);

    /**
     * 위탁 이관 관리 채권상담기본내역 전월 데이터 삭제
     * @param dvo
     * @return int
     */
    int deleteBondCounselingBasics(WbnaBondContractBaseDvo dvo);

    /**
     * 위탁 이관 관리 채권계약기본 Table update
     * @param dvo
     * @return int
     */
    int updateBondContractBases(WbnaBondContractBaseDvo dvo);

    /**
     * 위탁 이관 관리 채권상담기본내역 계약별 MERGE
     * @param dvo
     * @return int
     */
    int insertBondCounselingBasics(WbnaBondContractBaseDvo dvo);

    /**
     * 위탁 이관 관리 채권이관배정수행내역 Table insert
     * @param dvo
     * @return int
     */
    int insertBondTransferAssignExecutionIz(WbnaBondContractBaseDvo dvo);

    /**
     * 위탁 이관 관리 채권계약이력 Table insert
     * @param dvo
     * @return int
     */
    int insertBondContractHistories(WbnaBondContractBaseDvo dvo);

    /**
     * 위탁 이관 관리 저장
     * @param dvo
     * @return int
     */
    int updateFosterTransfer(WbnaBondContractBaseDvo dvo);

    /**
     * 미배정 위탁 이관 관리 저장
     * @param dvo
     * @return int
     */
    int updateNoAssignFosterTransfer(WbnaBondContractBaseDvo dvo);

    /**
     * 위탁 이관 관리 집금담당자 전체 조회
     * @param baseYm, bzHdqDvCd, clctamDvCd
     * @return List<String>
     */
    List<String> selectAllCollector(String baseYm, String bzHdqDvCd, String clctamDvCd);

    /**
     * 위탁 이관 관리 채권계약기본 전체 조회
     * @param baseYm, bzHdqDvCd, clctamDvCd
     * @return List<String>
     */
    List<String> selectAllBondContract(String baseYm, String bzHdqDvCd, String clctamDvCd);

}
