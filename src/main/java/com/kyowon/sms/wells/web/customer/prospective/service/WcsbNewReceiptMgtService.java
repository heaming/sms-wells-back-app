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

@Service
@RequiredArgsConstructor
public class WcsbNewReceiptMgtService {

    private final WcsbNewReceiptMgtMapper mapper;
    private final WcsbNewReceiptMgtConverter converter;

    /**
     * 접수 목록 조회 - 페이징용
     * @param dto
     * @param pageInfo
     * @return
     */
    public PagingResult<WcsbNewReceiptMgtDto.SearchRes> getReceiptPages(
        WcsbNewReceiptMgtDto.SearchReq dto, PageInfo pageInfo
    ) {
        return mapper.selectReceiptPages(dto, pageInfo);
    }

    /**
     * 접수 목록 조회 - 엑셀다운로드용
     * @param dto
     * @return
     */
    public List<WcsbNewReceiptMgtDto.SearchRes> getReceiptsForExcelDownload(
        WcsbNewReceiptMgtDto.SearchReq dto
    ) {
        return this.mapper.selectReceiptPages(dto);
    }

    /**
     * 접수 (단건)상세 조회
     * @param pspcCstCnslId
     * @return
     */
    public WcsbNewReceiptMgtDto.SearchDtlRes getPspcCstCnslAssign(
        String pspcCstCnslId
    ) {
        return mapper.selectPspcCstCnslAssign(pspcCstCnslId);
    }

    /**
     * 담당자 수동배정 조회
     * @param prtnrNo
     * @return
     */
    public PartnerRes getPartnerInfoByPrtnrNo(String prtnrNo) {
        return mapper.selectPartnerInfoByPrtnrNo(prtnrNo);
    }

    /**
     * 담당자 수동배정 저장
     * @param dto
     * @return
     */
    @Transactional
    public int editPspcCstCnslAssign(AssignReq dto) {
        int processCount = 0;
        processCount = mapper.updatePspcCstCnslAssign(dto);
        BizAssert.isTrue(processCount == 1, "MSG_ALT_SVE_ERR");
        return processCount;
    }

    /**
     * 배정정보 수정
     * @param dto
     * @return
     */
    @Transactional
    public int editPspcCstCnslContact(ContactReq dto) {
        int processCount = 0;

        for (String pspcCstCnslId : dto.pspcCstCnslIds()) {

            WcsbPspcCstCnslBasDvo vo = converter.mapContactReqToWcsbPspcCstCnslBasDvo(dto);
            vo.setPspcCstCnslId(pspcCstCnslId);

            processCount = mapper.updatePspcCstCnslContact(vo);
            BizAssert.isTrue(processCount == 1, "MSG_ALT_SVE_ERR");

        }
        return processCount;
    }

    /*
     * 배정조회 (TAB)   - Assign 
     */

    /**
     * 접수 목록 조회 - 그리드 페이징용
     * @param dto
     * @param pageInfo
     * @return
     */
    public PagingResult<WcsbNewReceiptMgtDto.SearchDtlRes> getAssignPages(
        WcsbNewReceiptMgtDto.SearchAssignReq dto, PageInfo pageInfo
    ) {
        return mapper.selectAssignPages(dto, pageInfo);
    }

    /**
     * 접수 목록 조회- 엑셀다운로드용
     * @param dto
     * @return
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
     * @param dto
     * @return
     */
    public List<WcsbNewReceiptMgtDto.SearchSummariesRes> getPspcCstCnslRecvSummaries(
        WcsbNewReceiptMgtDto.SearchSummariesReq dto
    ) {
        return this.mapper.selectPspcCstCnslRecvSummaries(dto);
    }

    //    @Transactional
    //    public int saveReceipts(List<SaveReq> dtos, String bsdt, String ogTpCd) {
    //        int processCount = 0;
    //        for (SaveReq dto : dtos) {
    //            WcsbNewReceiptDvo vo = converter.mapSaveReqToEpdyReceiptDvo(dto);
    //
    //            if (StringUtils.isEmpty(vo.getBsdt())) {
    //                vo.setBsdt(bsdt + vo.getDays());
    //                vo.setOgTpCd(ogTpCd);
    //                processCount += mapper.insertReceipt(vo);
    //            } else {
    //                int result = mapper.updateReceipt(vo);
    //                BizAssert.isTrue(result == 1, "MSG_ALT_SVE_ERR");
    //                processCount += result;
    //            }
    //
    //            //            switch (dto.rowState()) {
    //            //                case CommConst.ROW_STATE_CREATED -> {
    //            //                    processCount += mapper.insertReceipt(vo);
    //            //                }
    //            //                case CommConst.ROW_STATE_UPDATED -> {
    //            //                    int result = mapper.updateReceipt(vo);
    //            //                    BizAssert.isTrue(result == 1, "MSG_ALT_SVE_ERR");
    //            //                    processCount += result;
    //            //                }
    //            //                default -> throw new BizException("MSG_ALT_UNHANDLE_ROWSTATE");
    //            //            }
    //        }
    //        return processCount;
    //    }

    //    @Transactional
    //    public int removeReceipts(List<String> keys) {
    //        int processCount = 0;
    //        for (String compositePk : keys) {
    //            String compositePkArr[] = compositePk.split("\\|");
    //            WcsbNewReceiptDvo vo = new WcsbNewReceiptDvo();
    //            vo.setBsdt(compositePkArr[0]);
    //            vo.setOgTpCd(compositePkArr[1]);
    //
    //            int result = mapper.deleteReceipt(vo);
    //            BizAssert.isTrue(result == 1, "MSG_ALT_SVE_ERR");
    //            processCount += result;
    //        }
    //        return processCount;
    //    }

}
