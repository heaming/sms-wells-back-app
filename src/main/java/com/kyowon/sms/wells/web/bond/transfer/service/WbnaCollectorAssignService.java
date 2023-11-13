package com.kyowon.sms.wells.web.bond.transfer.service;

import com.kyowon.sflex.common.common.dvo.BatchCallReqDvo;
import com.kyowon.sflex.common.common.service.BatchCallService;
import com.kyowon.sms.common.web.bond.standard.dto.ZbnyBondAssignRuleMgtDto;
import com.kyowon.sms.common.web.bond.standard.service.ZbnyBondAssignRuleMgtService;
import com.kyowon.sms.common.web.bond.transfer.dvo.ZbnaBondTransferAssignDvo;
import com.kyowon.sms.common.web.bond.zcommon.constants.BnBondConst;
import com.kyowon.sms.wells.web.bond.transfer.converter.WbnaCollectorAssingConverter;
import com.kyowon.sms.wells.web.bond.transfer.dto.WbnaCollectorAssignDto.*;
import com.kyowon.sms.wells.web.bond.transfer.dvo.WbnaCollectorAssignDvo;
import com.kyowon.sms.common.web.bond.transfer.service.ZbnaBondContractBasicHistService;
import com.kyowon.sms.common.web.bond.transfer.service.ZbnaBondTransferAssignMgtService;
import com.sds.sflex.common.common.dto.ExcelUploadDto;
import com.sds.sflex.common.common.dvo.ExcelMetaDvo;
import com.sds.sflex.common.common.dvo.ExcelUploadErrorDvo;
import com.sds.sflex.common.common.service.ExcelReadService;
import com.sds.sflex.system.config.core.service.MessageResourceService;
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
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

/**
 * <pre>
 * 집금자배정 조회,생성,저장 관련 서비스
 * </pre>
 *
 * @author gugyeongu
 * @since 2023-02-22
 */
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
    private final MessageResourceService messageResourceService;
    private final ExcelReadService excelReadService;

    /**
     * 집금자배정 목록 조회
     * @param dto 검색조건
     * @return 집금자배정 목록
     */
    public List<SearchRes> getCollectorAssigns(
        SearchReq dto
    ) {
        return mapper.selectCollectorAssigns(dto);
    }

    /**
     * 집금자배정 상세 목록 조회
     * @param dto 검색조건
     * @param pageInfo 페이지정보
     * @return 상세목록
     */
    public PagingResult<SearchDetailRes> getCollectorAssignDetails(
        SearchReq dto, PageInfo pageInfo
    ) {
        return mapper.selectCollectorAssignDetailPages(dto, pageInfo);
    }

    /**
     * 집금자배정 상세 목록 조회(엑셀다운로드용)-테스트
     * @param dto 검색조건
     * @return 상세목록
     */
    public List<SearchDetailRes> getExcelDownload(
        SearchReq dto
    ) {
        return mapper.selectCollectorAssignDetailPages(dto);
    }

    /**
     * 집금자배정 상세 목록 합계 조회
     * @param dto dto 검색조건
     * @return 합계정보
     */
    public SearchSummaryRes getCollectorAssignDetailsSummary(
        SearchReq dto
    ) {
        return mapper.selectCollectorAssignDetailsSummary(dto);
    }

    /**
     * 집금자 배정 생성
     * @param dto 작업정보
     * @return 호출된 배치 정보
     * @throws Exception 배치호출 관련 에러
     */
    @Transactional
    public String createCollectorAssigns(
        CreateReq dto
    ) throws Exception {
        // 배정 가능 여부 확인
        BizAssert.isTrue(mapper.selectCanCollectorAssign(dto) == 1, "MSG_ALT_ASN_DTA_EXST");

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
        BizAssert.isTrue(CollectionUtils.isNotEmpty(dtos), BnBondConst.MSG_ALT_SVE_ERR); // TODO 메시지 변경 필요(설계 혹은 공통 메시지 나오면 수정)
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

        // TB_CBBO_BND_CNTR_BAS > CLCTAM_PRTNR_NO 정보 초기화
        mapper.updateClctamPrtnrNoForCollectorAssing(dto.baseYm(), dto.bzHdqDvCd(), dto.clctamDvCd());
        // TB_CBBO_BND_ASN_IZ > CLCTAM_PRTNR_NO 정보 초기화
        mapper.updateClctamPrtnrNoForBondAssignItemization(dto.baseYm(), dto.bzHdqDvCd(), dto.clctamDvCd());

        //배치호출(try-catch대신, throw사용)
        String oldBondBatchJobRunId = batchCallService.runJob(dvo); //결과값으로 Control-M 에서 run-id를 받는다.
        BizAssert.isTrue(StringUtils.isNotEmpty(oldBondBatchJobRunId), BnBondConst.MSG_ALT_SVE_ERR); // TODO 메시지 변경 필요(설계 혹은 공통 메시지 나오면 수정)

        return oldBondBatchJobRunId;
    }

    /**
     * 집금자배정 수정
     * @param dtos 수정목록
     * @return 갱신개수
     */
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
            BizAssert.isTrue(result == 1, BnBondConst.MSG_ALT_SVE_ERR); // TODO 메시지 변경 필요(설계 혹은 공통 메시지 나오면 수정)

            result = mapper.updateCollectorAssing(dvo);
            BizAssert.isTrue(result == 1, BnBondConst.MSG_ALT_SVE_ERR); // TODO 메시지 변경 필요(설계 혹은 공통 메시지 나오면 수정)

            result = bondContractBasicHistService.createBondContractHistory(dvo.getBndCntrId());
            BizAssert.isTrue(result == 1, BnBondConst.MSG_ALT_SVE_ERR); // TODO 메시지 변경 필요(설계 혹은 공통 메시지 나오면 수정)

            processCount += result;
        }
        if (processCount > 0) {
            // 집금자배정 개별 데이터 변경 시에도 TB_CBBO_BND_TF_ASN_EXCN_IZ 갱신 필요 해당 정보를 기준으로 파트이관,배정,배정확정 가능 여부를 판단
            ZbnaBondTransferAssignDvo bondTransferAssignDvo = ZbnaBondTransferAssignDvo.builder()
                .baseYm(dtos.get(0).baseYm())
                .tfBizDvCd(BnBondConst.TfBizDvCd.COLLECTOR_ASSIGNMENT.getValue())
                .bzHdqDvCd(dtos.get(0).bzHdqDvCd()).clctamDvCd(dtos.get(0).clctamDvCd()).build();
            bondTransferAssignDvo.setExcnSn(bondTransferAssignMgtService.getExcnSn(bondTransferAssignDvo));

            int result = bondTransferAssignMgtService.createBondTransferAssign(bondTransferAssignDvo);
            BizAssert.isTrue(result == 1, BnBondConst.MSG_ALT_SVE_ERR); // TODO 메시지 변경 필요(설계 혹은 공통 메시지 나오면 수정)
        }
        return processCount;
    }

    /**
     * 집금자배정 확정
     * @param dto 확정할 정보
     * @return 갱신개수
     */
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
        BizAssert.isTrue(result != 0, BnBondConst.MSG_ALT_SVE_ERR); // TODO 메시지 변경 필요(설계 혹은 공통 메시지 나오면 수정)

        ZbnaBondTransferAssignDvo bondTransferAssignDvo = converter.mapSearchReqToZbnaBondTransferAssignDvo(dto);
        bondTransferAssignDvo.setTfBizDvCd("03"); // 집금자배정확정
        bondTransferAssignDvo.setExcnSn(bondTransferAssignMgtService.getExcnSn(bondTransferAssignDvo));
        result = bondTransferAssignMgtService.createBondTransferAssign(bondTransferAssignDvo);
        BizAssert.isTrue(result == 1, BnBondConst.MSG_ALT_SVE_ERR); // TODO 메시지 변경 필요(설계 혹은 공통 메시지 나오면 수정)

        processCount += result;

        return processCount;
    }

    /**
     * 집금자배정 엑셀업로드
     * @param file 엑셀파일
     * @param baseYm 기준년월
     * @return 업로드결과
     * @throws Exception 엑셀업로드 에러
     */
    @Transactional
    public ExcelUploadDto.UploadRes createCollectorAssignsDetailsExcelUpload(
        MultipartFile file, String baseYm
    )
        throws Exception {
        Map<String, String> headerTitle = new LinkedHashMap<>();
        headerTitle.put("cntrDtlNo", messageResourceService.getMessage("MSG_TXT_CNTR_DTL_NO")); //계약상세번호
        headerTitle.put("oldClctamPrtnrNo", messageResourceService.getMessage("MSG_TXT_BFCH_CLCTAM_PSIC_NO")); //변경전 집금담당자사번
        headerTitle.put("clctamPrtnrNo", messageResourceService.getMessage("MSG_TXT_AFCH_CLCTAM_PSIC_NO")); //변경후 집금담당자사번
        // file dvo로 변경
        List<WbnaCollectorAssignDvo> collectorAssignDvos = excelReadService
            .readExcel(file, new ExcelMetaDvo(1, headerTitle), WbnaCollectorAssignDvo.class);
        List<ExcelUploadErrorDvo> excelUploadErrorDvos = new ArrayList<>();

        // 데이터 갱신 작업 진행
        int result;
        int loopNumber = 1;
        for (WbnaCollectorAssignDvo dvo : collectorAssignDvos) {
            loopNumber += 1;
            if (StringUtils.isNotEmpty(dvo.getCntrDtlNo())) {
                String cntrNo = dvo.getCntrDtlNo().split("-")[0];
                String cntrSn = dvo.getCntrDtlNo().split("-")[1];
                dvo.setCntrNo(cntrNo);
                dvo.setCntrSn(cntrSn);
                dvo.setBaseYm(baseYm);

                result = bondContractBasicHistService
                    .createBondContractHistoryWithCntrNo(dvo.getBaseYm(), cntrNo, cntrSn, "Y");
                if (result == 0) { // 히스토리 만들지 못한 경우 추가 작업 없이 다음으로(히스토리 생성시 집금구분 같이 확인(단기~집행))
                    ExcelUploadErrorDvo excelUploadErrorDvo = new ExcelUploadErrorDvo();
                    excelUploadErrorDvo.setErrorRow(loopNumber);
                    excelUploadErrorDvo
                        .setHeaderName(messageResourceService.getMessage("MSG_ALT_NO_CNTR_DTL_NO_FOUND"));
                    excelUploadErrorDvo.setErrorData(
                        messageResourceService.getMessage(
                            "MSG_ALT_INVALID_UPLOAD_DATA", String.valueOf(loopNumber), "계약상세번호", dvo.getCntrDtlNo()
                        )
                    );
                    excelUploadErrorDvos.add(excelUploadErrorDvo);
                    continue;
                }
                mapper.updateCollectorAssingByCntr(dvo);
                mapper.updateCollectorAssingForAnsIzByCntr(dvo);

            }
        }
        return ExcelUploadDto.UploadRes.builder()
            .status(excelUploadErrorDvos.isEmpty() ? "S" : "E").errorInfo(excelUploadErrorDvos)
            .excelData(collectorAssignDvos).build();
    }
}
