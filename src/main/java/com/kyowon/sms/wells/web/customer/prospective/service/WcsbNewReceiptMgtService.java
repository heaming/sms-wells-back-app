package com.kyowon.sms.wells.web.customer.prospective.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.customer.prospective.converter.WcsbNewReceiptMgtConverter;
import com.kyowon.sms.wells.web.customer.prospective.dto.WcsbNewReceiptMgtDto;
import com.kyowon.sms.wells.web.customer.prospective.dto.WcsbNewReceiptMgtDto.AssignReq;
import com.kyowon.sms.wells.web.customer.prospective.dto.WcsbNewReceiptMgtDto.ContactReq;
import com.kyowon.sms.wells.web.customer.prospective.dto.WcsbNewReceiptMgtDto.PartnerRes;
import com.kyowon.sms.wells.web.customer.prospective.dvo.WcsbPspcCstCnslBasDvo;
import com.kyowon.sms.wells.web.customer.prospective.mapper.WcsbNewReceiptMgtMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import com.sds.sflex.system.config.validation.BizAssert;

import lombok.RequiredArgsConstructor;

/**
 * <pre>
 * 고객 >>  신규접수 배정관리 Service
 * </pre>
 *
 * @author junho.bae
 * @since 2023-07-01
 */
@Service
@RequiredArgsConstructor
public class WcsbNewReceiptMgtService {

    private final WcsbNewReceiptMgtMapper mapper;
    private final WcsbNewReceiptMgtConverter converter;

    /**
     * 접수 목록 조회 - 페이징용
     * @param dto 검색조건
     * @param pageInfo 페이징정보
     * @return 접수 목록
     */
    public PagingResult<WcsbNewReceiptMgtDto.SearchRes> getReceiptPages(
        WcsbNewReceiptMgtDto.SearchReq dto, PageInfo pageInfo
    ) {
        return mapper.selectReceiptPages(dto, pageInfo);
    }

    /**
     * 접수 목록 조회 - 엑셀다운로드용
     * @param dto 검색조건
     * @return 접수 목록
     */
    public List<WcsbNewReceiptMgtDto.SearchRes> getReceiptsForExcelDownload(
        WcsbNewReceiptMgtDto.SearchReq dto
    ) {
        return this.mapper.selectReceiptPages(dto);
    }

    /**
     * 접수 (단건)상세 조회
     * @param pspcCstCnslId 가망고객상담ID
     * @param cntrNo 계약번호
     * @return 접수정보
     */
    public WcsbNewReceiptMgtDto.SearchDtlRes getPspcCstCnslAssign(
        String pspcCstCnslId, String cntrNo
    ) {
        return mapper.selectPspcCstCnslAssign(pspcCstCnslId, cntrNo);
    }

    /**
     * 담당자 수동배정 조회
     * @param prtnrNo 파트너번호
     * @return 파트너 정보
     */
    public PartnerRes getPartnerInfoByPrtnrNo(String prtnrNo) {
        return mapper.selectPartnerInfoByPrtnrNo(prtnrNo);
    }

    /**
     * 담당자 수동배정 저장
     * @param dto 담당자 정보
     * @return 수행결과
     */
    @Transactional
    public int editPspcCstCnslAssign(AssignReq dto) {
        int processCount = 0;

        for (String pspcCstCnslId : dto.pspcCstCnslIds()) {
            processCount += mapper.updatePspcCstCnslAssign(
                AssignReq.builder()
                    .pspcCstCnslId(pspcCstCnslId)
                    .ogTpCd(dto.ogTpCd())
                    .prtnrNo(dto.prtnrNo())
                    .build()
            );
            mapper.updatePspcCstCnslChHistForEnd(pspcCstCnslId);
            mapper.insertPspcCstCnslChHist(pspcCstCnslId);

        }
        BizAssert.isTrue(processCount == dto.pspcCstCnslIds().length, "MSG_ALT_SVE_ERR");
        return processCount;
    }

    /**
     * 배정정보 수정
     * @param dto 배정정보
     * @return 수행결과
     */
    @Transactional
    public int editPspcCstCnslContact(ContactReq dto) {
        int processCount = 0;

        for (String pspcCstCnslId : dto.pspcCstCnslIds()) {

            WcsbPspcCstCnslBasDvo vo = converter.mapContactReqToWcsbPspcCstCnslBasDvo(dto);
            vo.setPspcCstCnslId(pspcCstCnslId);

            processCount = mapper.updatePspcCstCnslContact(vo);
            BizAssert.isTrue(processCount == 1, "MSG_ALT_SVE_ERR");
            mapper.updatePspcCstCnslChHistForEnd(pspcCstCnslId);
            mapper.insertPspcCstCnslChHist(pspcCstCnslId);

        }
        return processCount;
    }

    /*
     * 배정조회 (TAB)   - Assign
     */

    /**
     * 접수 목록 조회 - 그리드 페이징용
     * @param dto 검색조건
     * @param pageInfo 페이징 정보
     * @return 접수정보 데이터 목록
     */
    public PagingResult<WcsbNewReceiptMgtDto.SearchDtlRes> getAssignPages(
        WcsbNewReceiptMgtDto.SearchAssignReq dto, PageInfo pageInfo
    ) {
        return mapper.selectAssignPages(dto, pageInfo);
    }

    /**
     * 접수 목록 조회- 엑셀다운로드용
     * @param dto 검색조건
     * @return 접수정보 데이터 목록
     */
    public List<WcsbNewReceiptMgtDto.SearchDtlRes> getAssignsForExcelDownload(
        WcsbNewReceiptMgtDto.SearchAssignReq dto
    ) {
        return this.mapper.selectAssignPages(dto);
    }

    /**
     * Summaries Tab
     */

    /**
     * 집계정보 조회
     * @param dto 검색조건
     * @return 집계정보 데이터 목록
     */
    public List<WcsbNewReceiptMgtDto.SearchSummariesRes> getPspcCstCnslRecvSummaries(
        WcsbNewReceiptMgtDto.SearchSummariesReq dto
    ) {
        return this.mapper.selectPspcCstCnslRecvSummaries(dto);
    }

}
