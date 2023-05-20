package com.kyowon.sms.wells.web.bond.transfer.service;

import com.kyowon.sms.common.web.bond.transfer.dvo.ZbnaBondTransferAssignDvo;
import com.kyowon.sms.wells.web.bond.transfer.converter.WbnaCollectorAssingConverter;
import com.kyowon.sms.wells.web.bond.transfer.dto.WbnaCollectorAssignDto.*;
import com.kyowon.sms.wells.web.bond.transfer.dvo.WbnaCollectorAssignDvo;
import com.kyowon.sms.common.web.bond.transfer.service.ZbnaBondContractBasicHistService;
import com.kyowon.sms.common.web.bond.transfer.service.ZbnaBondTransferAssignMgtService;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import com.sds.sflex.system.config.validation.BizAssert;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.bond.transfer.mapper.WbnaCollectorAssignMapper;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class WbnaCollectorAssignService {

    private final WbnaCollectorAssignMapper mapper;
    private final WbnaCollectorAssingConverter converter;
    private final ZbnaBondContractBasicHistService bondContractBasicHistService;
    private final ZbnaBondTransferAssignMgtService bondTransferAssignMgtService;

    public List<SearchRes> getCollectorAssigns(
        SearchReq dto
    ) {
        return mapper.selectCollectorAssigns(dto);
    }

    public PagingResult<SearchDetailRes> getCollectorAssignDetails(
        SearchReq dto, PageInfo pageInfo
    ) {
        return mapper.selectCollectorAssignDetailPages(dto, pageInfo);
    }

    public List<SearchDetailRes> getExcelDownload(
        SearchReq dto
    ) {
        return mapper.selectCollectorAssignDetailPages(dto);
    }

    public SearchSummaryRes getCollectorAssignDetailsSummary(
        SearchReq dto
    ) {
        return mapper.selectCollectorAssignDetailsSummary(dto);
    }

    @Transactional
    public int editCollectorAssignsDetails(
        List<EditReq> dtos
    ) {
        int processCount = 0;
        for (EditReq dto : dtos) {
            // TODO 테이블 및 쿼리문 정의가 완료 되지 않아 mapper 호출은 하지 않은 상태 DB완료 후 작업 예정
            log.debug(dto.cntrNo());
            WbnaCollectorAssignDvo dvo = converter.mapEditReqToWbnaCollectorAssignDvo(dto);
            // 1. updateCollectorAssingForBondAssignItemization - TB_CBBO_BND_ASN_IZ 정보 수정
            // 2. updateCollectorAssing - TB_CBBO_BND_CNTR_BAS 정보 수정
            // 3. TB_CBBO_BND_CNTR_HIST 수정 이력 추가
            int result = mapper.updateCollectorAssingForBondAssignItemization(dvo);
            BizAssert.isTrue(result == 1, "MSG_ALT_SVE_ERR"); // TODO 메시지 변경 필요(설계 혹은 공통 메시지 나오면 수정)

            result = mapper.updateCollectorAssing(dvo);
            BizAssert.isTrue(result == 1, "MSG_ALT_SVE_ERR"); // TODO 메시지 변경 필요(설계 혹은 공통 메시지 나오면 수정)

            result = bondContractBasicHistService.createBondContractHistory(dvo.getBndCntrId());
            BizAssert.isTrue(result == 1, "MSG_ALT_SVE_ERR"); // TODO 메시지 변경 필요(설계 혹은 공통 메시지 나오면 수정)

            processCount += result;
        }
        return processCount;
    }

    @Transactional
    public int editCollectorAssingsConfirm(
        SearchReq dto
    ) {
        int processCount = 0;
        WbnaCollectorAssignDvo dvo = converter.mapSearchReqToWbnaCollectorAssignDvo(dto);
        log.debug("dvo display information on service page: " + dvo);
        //1. updateCollectorAssingsConfirm TB_CBBO_BND_ASN_IZ 채권배정 내용 확정
        //2. TB_CBBO_BND_TF_ASN_EXCN_IZ 테이블 이력 저장
        int result = mapper.updateCollectorAssingsConfirm(dvo);
        BizAssert.isTrue(result != 0, "MSG_ALT_SVE_ERR"); // TODO 메시지 변경 필요(설계 혹은 공통 메시지 나오면 수정)

        ZbnaBondTransferAssignDvo bondTransferAssignDvo = converter.mapSearchReqToZbnaBondTransferAssignDvo(dto);
        bondTransferAssignDvo.setTfBizDvCd("03"); // 집금자배정확정
        bondTransferAssignDvo.setExcnSn(bondTransferAssignMgtService.getExcnSn(bondTransferAssignDvo));
        result = bondTransferAssignMgtService.createBondTransferAssign(bondTransferAssignDvo);
        BizAssert.isTrue(result == 1, "MSG_ALT_SVE_ERR"); // TODO 메시지 변경 필요(설계 혹은 공통 메시지 나오면 수정)

        processCount += result;

        return processCount;
    }
}
