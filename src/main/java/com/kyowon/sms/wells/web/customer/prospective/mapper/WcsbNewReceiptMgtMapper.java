package com.kyowon.sms.wells.web.customer.prospective.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.customer.prospective.dto.WcsbNewReceiptMgtDto;
import com.kyowon.sms.wells.web.customer.prospective.dto.WcsbNewReceiptMgtDto.AssignReq;
import com.kyowon.sms.wells.web.customer.prospective.dto.WcsbNewReceiptMgtDto.PartnerRes;
import com.kyowon.sms.wells.web.customer.prospective.dvo.WcsbPspcCstCnslBasDvo;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

/* wcsb-new-receipt-mgt.xml */
/**
 * <pre>
 * 고객 >>  신규접수 배정관리 Mapper
 * </pre>
 *
 * @author junho.bae
 * @since 2023-07-01
 */
@Mapper
public interface WcsbNewReceiptMgtMapper {

    /**
     * 접수 목록 페이징 조회
     * @param dto 검색조건
     * @param pageInfo 페이징 정보
     * @return 접수 데이터 목록
     */
    PagingResult<WcsbNewReceiptMgtDto.SearchRes> selectReceiptPages(
        WcsbNewReceiptMgtDto.SearchReq dto, PageInfo pageInfo
    );

    /**
     * 접수 목록 엑셀다운로드
     * @param dto 검색조건
     * @return 접수 목록
     */
    List<WcsbNewReceiptMgtDto.SearchRes> selectReceiptPages(WcsbNewReceiptMgtDto.SearchReq dto);

    /**
     * 접수 (단건)상세 조회
     * @param pspcCstCnslId 가망고객상담ID
     * @param cntrNo 계약번호
     * @return 접수 상세정보
     */
    WcsbNewReceiptMgtDto.SearchDtlRes selectPspcCstCnslAssign(String pspcCstCnslId, String cntrNo);

    /**
     * 담당자 수동배정 조회
     * @param prtnrNo 파트너번호
     * @return 파트너정보
     */
    PartnerRes selectPartnerInfoByPrtnrNo(String prtnrNo);

    /**
     * 담당자 수동배정 저장
     * @param dto 수동배정할 담당자 정보
     * @return 수행결과
     */
    int updatePspcCstCnslAssign(AssignReq dto);

    /**
     * 배정정보 수정
     * @param vo 배정정보
     * @return 수행결과
     */
    int updatePspcCstCnslContact(WcsbPspcCstCnslBasDvo vo);

    /*
     * 배정조회 (TAB)   - Assign
     */

    /**
     * 접수 목록 조회 - 그리드 페이징용
     * @param dto 검색조건
     * @param pageInfo 페이징 정보
     * @return 접수 목록
     */
    PagingResult<WcsbNewReceiptMgtDto.SearchDtlRes> selectAssignPages(
        WcsbNewReceiptMgtDto.SearchAssignReq dto, PageInfo pageInfo
    );

    /**
     * 접수 목록 조회- 엑셀다운로드용
     * @param dto 검색조건
     * @return 접수 목록
     */
    List<WcsbNewReceiptMgtDto.SearchDtlRes> selectAssignPages(WcsbNewReceiptMgtDto.SearchAssignReq dto);

    /*
     * Summaries Tab
     */
    /**
     * 집계정보 조회
     * @param dto 요약조회할 집계정보
     * @return 집계 요약정보
     */
    public List<WcsbNewReceiptMgtDto.SearchSummariesRes> selectPspcCstCnslRecvSummaries(
        WcsbNewReceiptMgtDto.SearchSummariesReq dto
    );

    /**
    * 기존 상담 이력 중지 처리
    * @param pspcCstCnslId 가망고객상담ID
    * @return 수행결과
    */
    int updatePspcCstCnslChHistForEnd(String pspcCstCnslId);

    /**
    * 신규 이력 생성
    * @param pspcCstCnslId 가망고객상담ID
    * @return 수행결과
    */
    int insertPspcCstCnslChHist(String pspcCstCnslId);
}
