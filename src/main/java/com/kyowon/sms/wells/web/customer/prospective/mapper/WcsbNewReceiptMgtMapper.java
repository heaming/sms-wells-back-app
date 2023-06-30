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
@Mapper
public interface WcsbNewReceiptMgtMapper {

    /**
     * 접수 목록 페이징 조회
     * @param dto
     * @param pageInfo
     * @return
     */
    PagingResult<WcsbNewReceiptMgtDto.SearchRes> selectReceiptPages(
        WcsbNewReceiptMgtDto.SearchReq dto, PageInfo pageInfo
    );

    /**
     * 접수 목록 엑셀다운로드
     * @param dto
     * @return
     */
    List<WcsbNewReceiptMgtDto.SearchRes> selectReceiptPages(WcsbNewReceiptMgtDto.SearchReq dto);

    /**
     * 접수 (단건)상세 조회
     * @param pspcCstCnslId
     * @param cntrNo
     * @return
     */
    WcsbNewReceiptMgtDto.SearchDtlRes selectPspcCstCnslAssign(String pspcCstCnslId, String cntrNo);

    /**
     * 담당자 수동배정 조회
     * @param prtnrNo
     * @return
     */
    PartnerRes selectPartnerInfoByPrtnrNo(String prtnrNo);

    /**
     * 담당자 수동배정 저장
     * @param dto
     * @return
     */
    int updatePspcCstCnslAssign(AssignReq dto);

    /**
     * 배정정보 수정
     * @param vo
     * @return
     */
    int updatePspcCstCnslContact(WcsbPspcCstCnslBasDvo vo);

    /*
     * 배정조회 (TAB)   - Assign
     */

    /**
     * 접수 목록 조회 - 그리드 페이징용
     * @param dto
     * @param pageInfo
     * @return
     */
    PagingResult<WcsbNewReceiptMgtDto.SearchDtlRes> selectAssignPages(
        WcsbNewReceiptMgtDto.SearchAssignReq dto, PageInfo pageInfo
    );

    /**
     * 접수 목록 조회- 엑셀다운로드용
     * @param dto
     * @return
     */
    List<WcsbNewReceiptMgtDto.SearchDtlRes> selectAssignPages(WcsbNewReceiptMgtDto.SearchAssignReq dto);

    /**
     * Summaries Tab
     */
    /**
     * 집계정보 조회
     * @param dto
     * @return
     */
    public List<WcsbNewReceiptMgtDto.SearchSummariesRes> selectPspcCstCnslRecvSummaries(
        WcsbNewReceiptMgtDto.SearchSummariesReq dto
    );

}
