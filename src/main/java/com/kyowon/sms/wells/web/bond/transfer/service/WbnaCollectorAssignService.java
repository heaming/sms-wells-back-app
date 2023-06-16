package com.kyowon.sms.wells.web.bond.transfer.service;

import com.kyowon.sflex.common.common.dvo.BatchCallReqDvo;
import com.kyowon.sflex.common.common.service.BatchCallService;
import com.kyowon.sms.common.web.bond.standard.dto.ZbnyBondAssignRuleMgtDto;
import com.kyowon.sms.common.web.bond.standard.service.ZbnyBondAssignRuleMgtService;
import com.kyowon.sms.common.web.bond.transfer.dvo.ZbnaBondBatchStatusDetailsDvo;
import com.kyowon.sms.common.web.bond.transfer.dvo.ZbnaBondTransferAssignDvo;
import com.kyowon.sms.common.web.bond.transfer.service.ZbnaBondBatchStatusDetailsService;
import com.kyowon.sms.common.web.bond.zcommon.constants.BnBondConst;
import com.kyowon.sms.wells.web.bond.transfer.converter.WbnaCollectorAssingConverter;
import com.kyowon.sms.wells.web.bond.transfer.dto.WbnaCollectorAssignDto.*;
import com.kyowon.sms.wells.web.bond.transfer.dvo.WbnaCollectorAssignDvo;
import com.kyowon.sms.common.web.bond.transfer.service.ZbnaBondContractBasicHistService;
import com.kyowon.sms.common.web.bond.transfer.service.ZbnaBondTransferAssignMgtService;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import com.sds.sflex.system.config.validation.BizAssert;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.bond.transfer.mapper.WbnaCollectorAssignMapper;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class WbnaCollectorAssignService {

    private final WbnaCollectorAssignMapper mapper;
    private final WbnaCollectorAssingConverter converter;
    private final ZbnaBondContractBasicHistService bondContractBasicHistService;
    private final ZbnaBondTransferAssignMgtService bondTransferAssignMgtService;
    private final ZbnyBondAssignRuleMgtService bondAssignRuleMgtService;
    private final BatchCallService batchCallService;
    private final ZbnaBondBatchStatusDetailsService bondBatchStatusDetailsService;

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
    public String createCollectorAssigns(
        CreateReq dto
    ) throws Exception {
        // 배정 가능 여부 확인
        BizAssert.isTrue(mapper.selectCanCollectorAssign(dto) == 1, "MSG_ALT_ASN_DTA_EXST");

        // 기존 배치 동작 여부 확인 (true:배치 동작 가능, false:배치 동작 불가)
        /* TODO 배치 실행 확인 문제로 임시 주석
        BizAssert.isTrue(
            bondBatchStatusDetailsService
                .checkBondBatchOperationStatus(dto.baseYm(), dto.bzHdqDvCd(), dto.clctamDvCd()),
            "MSG_ALT_RUN_ASN_BAT_EXST"
        );*/ // TODO 메시지 변경 필요한 경우 해당 id의 내용만 변경

        /*
        * 1.균등배정 -> 배정 타입1(균등배정 -> 지역우선배정) 0103
        * 2.전월담당자배정 -> 배정 타입2(균등배정 -> 기존배정고려균등배정) 0104
        * 3.지역우선배정 -> 배정 타입3(전월담당자배정 -> 지역우선배정) 0203
        * 4.기존배정고려균등배정 -> 배정 타입4(전월담당자배정 -> 기존배정고려균등배정) 0204
        * */
        List<ZbnyBondAssignRuleMgtDto.SearchRes> dtos = bondAssignRuleMgtService.getAssignRules(
            ZbnyBondAssignRuleMgtDto.SearchReq.builder().baseYm(dto.baseYm()).bzHdqDvCd(dto.bzHdqDvCd())
                .clctamDvCd(dto.clctamDvCd()).build()
        );
        BizAssert.isTrue(CollectionUtils.isNotEmpty(dtos), "MSG_ALT_SVE_ERR"); // TODO 메시지 변경 필요(설계 혹은 공통 메시지 나오면 수정)
        ZbnyBondAssignRuleMgtDto.SearchRes bondAssignRuleMgtDto = dtos.get(0);

        // bndAsnMthCd을 기준으로 배치 동작
        BatchCallReqDvo dvo = new BatchCallReqDvo();
        dvo.setJobKey(
            BnBondConst.CollectorAssignBatchJob
                .valueOf("BATCH_JOB" + bondAssignRuleMgtDto.pextBndAsnDvCd() + bondAssignRuleMgtDto.nwBndAsnDvCd())
                .getValue()
        ); // 기 등록된 잡 Key

        Map<String, String> params = new HashMap<>();
        params.put("baseYm", dto.baseYm());
        params.put("bzHdqDvCd", dto.bzHdqDvCd());
        params.put("clctamDvCd", dto.clctamDvCd());
        params.put("bndNwDvCd", BnBondConst.BndNwDvCd.OLD.getValue());
        dvo.setParams(params); // Job 실행시 필요한 파라미터

        //배치호출(try-catch대신, throw사용)
        String oldBondBatchJobRunId = batchCallService.runJob(dvo); //결과값으로 Control-M 에서 run-id를 받는다.
        BizAssert.isTrue(StringUtils.isNotEmpty(oldBondBatchJobRunId), "MSG_ALT_SVE_ERR"); // TODO 메시지 변경 필요(설계 혹은 공통 메시지 나오면 수정)

        //배치 호출 후 호출 정보 저장(여기서 부터는 배치 호출 후기 때문에 에러가 나면 안됨)
        ZbnaBondBatchStatusDetailsDvo bondBatchStatusDetailsDvo = new ZbnaBondBatchStatusDetailsDvo();
        bondBatchStatusDetailsDvo.setBaseYm(dto.baseYm());
        bondBatchStatusDetailsDvo.setBzHdqDvCd(dto.bzHdqDvCd());
        bondBatchStatusDetailsDvo.setClctamDvCd(dto.clctamDvCd());
        bondBatchStatusDetailsDvo.setBndBatWkId(oldBondBatchJobRunId);
        bondBatchStatusDetailsDvo.setTfBizDvCd(BnBondConst.TfBizDvCd.COLLECTOR_ASSIGNMENT.getValue());
        bondBatchStatusDetailsDvo.setBndNwDvCd(BnBondConst.BndNwDvCd.OLD.getValue());
        bondBatchStatusDetailsDvo.setBndBatStatCd(BnBondConst.BndBatStatCd.START.getValue());
        // TODO 항목 길이 문제로 임시 주석
        // bondBatchStatusDetailsService.createBondBatchStatusDetails(bondBatchStatusDetailsDvo);

        return oldBondBatchJobRunId;
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
