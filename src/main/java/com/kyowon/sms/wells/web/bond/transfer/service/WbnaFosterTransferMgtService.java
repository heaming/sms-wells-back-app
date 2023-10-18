package com.kyowon.sms.wells.web.bond.transfer.service;

import java.util.*;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.kyowon.sflex.common.common.dvo.BatchCallReqDvo;
import com.kyowon.sflex.common.common.service.BatchCallService;
import com.kyowon.sms.common.web.bond.transfer.dvo.ZbnaExcelHistoryMgtDvo;
import com.kyowon.sms.common.web.bond.transfer.service.ZbnaExcelHistoryMgtService;
import com.kyowon.sms.wells.web.bond.transfer.converter.WbnaFosterTransferMgtConverter;
import com.kyowon.sms.wells.web.bond.transfer.dto.WbnaFosterTransferMgtDto.*;
import com.kyowon.sms.wells.web.bond.transfer.dvo.WbnaBondContractBaseDvo;
import com.kyowon.sms.wells.web.bond.transfer.mapper.WbnaFosterTransferMgtMapper;
import com.sds.sflex.common.common.dto.ExcelUploadDto.UploadRes;
import com.sds.sflex.common.common.dvo.ExcelMetaDvo;
import com.sds.sflex.common.common.dvo.ExcelUploadErrorDvo;
import com.sds.sflex.common.common.service.ExcelReadService;
import com.sds.sflex.common.utils.StringUtil;
import com.sds.sflex.system.config.core.service.MessageResourceService;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import com.sds.sflex.system.config.response.SaveResponse;
import com.sds.sflex.system.config.validation.BizAssert;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WbnaFosterTransferMgtService {
    private final WbnaFosterTransferMgtMapper mapper;
    private final WbnaFosterTransferMgtConverter converter;
    private final BatchCallService batchCallService;
    private final MessageResourceService messageResourceService;
    private final ExcelReadService excelReadService;
    private final ZbnaExcelHistoryMgtService zbnaExcelHistoryMgtService;
    private static final String MSG_ALT_SVE_ERR_STR = "MSG_ALT_SVE_ERR";

    public List<SearchRes> getFosterTransfers(SearchReq dto) {
        return mapper.selectFosterTransfers(this.converter.mapSearchReqToBondContractBaseDvo(dto));
    }

    public PagingResult<SearchDetailRes> getFosterTransferDetails(SearchReq dto, PageInfo pageInfo) {
        return mapper.selectFosterTransferDetails(dto, pageInfo);
    }

    public SearchDetailSummaryRes getPartTransferDetailsSummary(
        SearchReq dto
    ) {
        return mapper.selectFosterTransferDetailsSummary(dto);
    }

    @Transactional
    public SaveResponse confirmFosterDataTransfers(SearchReq dto) throws Exception {
        int processCount = 0;
        String data = null;

        switch (dto.clctamDvCd()) {
            // 집금구분코드 = 위탁
            case "09" -> {
                //배치 dvo 생성
                BatchCallReqDvo batchDvo = new BatchCallReqDvo();

                Map<String, String> params = new HashMap<>();

                batchDvo.setJobKey("WSM_BN_OA0001");
                params.put("baseYm", dto.baseYm()); //기준년월
                params.put("bzHdqDvCd", dto.bzHdqDvCd()); //사업부 구분
                params.put("clctamDvCd", dto.clctamDvCd()); //집금구분코드
                batchDvo.setParams(params); // Job 실행시 필요한 파라미터

                String runId = batchCallService.runJob(batchDvo);
                BizAssert.isTrue(StringUtils.isNotEmpty(runId), MSG_ALT_SVE_ERR_STR);

                data = StringUtil.isBlank(runId) ? "S" : "E";
            }
            // 집금구분코드 = TF
            case "11" -> {
                WbnaBondContractBaseDvo params = this.converter.mapSearchReqToBondContractBaseDvo(dto);
                // 채권상담기본내역 전월 데이터 삭제
                this.mapper.deleteBondCounselingBasics(params);

                // 채권계약기본 Table update
                int updateContractCount = this.mapper.updateBondContractBases(params);
                BizAssert.isTrue(updateContractCount >= 1, MSG_ALT_SVE_ERR_STR);
                processCount += updateContractCount;

                // 채권상담기본내역 계약별 MERGE
                int mergeCounselingCount = this.mapper.insertBondCounselingBasics(params);
                BizAssert.isTrue(mergeCounselingCount >= 1, MSG_ALT_SVE_ERR_STR);

                // 채권이관배정수행내역 Table insert
                int insertCount = this.mapper.insertBondTransferAssignExecutionIz(params);
                BizAssert.isTrue(insertCount == 1, MSG_ALT_SVE_ERR_STR);
            }
        }

        return SaveResponse.builder().data(data).processCount(processCount).build();
    }

    @Transactional
    public int editFosterDataTransfers(List<SaveReq> dtos) {
        int processCount = 0;

        for (SaveReq dto : dtos) {
            WbnaBondContractBaseDvo dvo = this.converter.mapSaveReqToBondContractBaseDvo(dto);
            int insertResult = this.mapper.insertBondContractHistories(dvo);
            int updateResult = this.mapper.updateFosterTransfer(dvo);

            BizAssert.isTrue(updateResult == 1 && insertResult == 1, MSG_ALT_SVE_ERR_STR);
            processCount += updateResult;
        }
        return processCount;
    }

    @Transactional
    public List<SearchDetailRes> getFosterTransferDetailsExcelDownload(
        SearchReq dto, String downFileName, String pageId
    ) {
        List<SearchDetailRes> response = mapper.selectFosterTransferDetails(dto);
        if (response.size() != 0) {
            ZbnaExcelHistoryMgtDvo dvo = ZbnaExcelHistoryMgtDvo
                .builder()
                .baseYm(dto.baseYm())
                .bzHdqDvCd(dto.bzHdqDvCd())
                .excelDldFileNm(downFileName)
                .excelDldSrnId(pageId)
                .excelDldCt(response.size())
                .excelUldPsbYn('N')
                .build();

            int result = zbnaExcelHistoryMgtService.createBondDownloadIz(dvo);
            BizAssert.isTrue(result == 1, MSG_ALT_SVE_ERR_STR);
        }
        return response;
    }

    @Transactional
    public UploadRes editFosterTransferDetailsExcelUpload(
        MultipartFile file, String baseYm, String bzHdqDvCd, String clctamDvCd, String pageId
    ) throws Exception {
        // 업로드 엑셀 헤더 설정
        Map<String, String> headerTitle = new LinkedHashMap<>();
        headerTitle.put("cntrDtlNo", messageResourceService.getMessage("MSG_TXT_CNTR_DTL_NO")); // 계약상세번호
        headerTitle.put("clctamPrtnrNo", messageResourceService.getMessage("MSG_TXT_CLCTAM_PSIC_NO_SUB")); // 집금담당자번호
        // file
        List<WbnaBondContractBaseDvo> list = excelReadService
            .readExcel(file, new ExcelMetaDvo(2, headerTitle), WbnaBondContractBaseDvo.class, false);
        List<ExcelUploadErrorDvo> excelUploadErrorDvos = new ArrayList<>();

        // 집금담당자 번호가 db에 있는지 check
        List<String> prtnrList = this.mapper.selectAllCollector(baseYm, bzHdqDvCd, clctamDvCd);
        List<String> cntrDtlNoList = this.mapper.selectAllBondContract(baseYm, bzHdqDvCd, clctamDvCd);
        int row = 1;

        // 데이터 검증
        for (WbnaBondContractBaseDvo dvo : list) {
            dvo.setBaseYm(baseYm);
            dvo.setBzHdqDvCd(bzHdqDvCd);
            dvo.setClctamDvCd(clctamDvCd);

            // empty pk check
            if (StringUtil.isBlank(dvo.getCntrDtlNo())) {
                ExcelUploadErrorDvo errorDvo = new ExcelUploadErrorDvo();
                errorDvo.setErrorRow(row);
                errorDvo.setHeaderName(headerTitle.get("cntrDtlNo"));
                errorDvo.setErrorData(
                    messageResourceService.getMessage(
                        "MSG_ALT_NO_EXPORT_DATA" // 엑셀 파일로 내보낼 데이터가 없습니다.
                    )
                );
                excelUploadErrorDvos.add(errorDvo);
            } else if (cntrDtlNoList.contains(dvo.getCntrDtlNo())) {
                ExcelUploadErrorDvo errorDvo = new ExcelUploadErrorDvo();
                errorDvo.setErrorRow(row);
                errorDvo.setHeaderName(headerTitle.get("cntrDtlNo"));
                errorDvo.setErrorData(
                    messageResourceService.getMessage(
                        "MSG_ALT_NO_CNTR_DTL_NO_FOUND" // 계약상세번호를 찾을 수 없습니다.
                    )
                );
                excelUploadErrorDvos.add(errorDvo);
            }

            if (StringUtil.isBlank(dvo.getClctamPrtnrNo())) {
                ExcelUploadErrorDvo errorDvo = new ExcelUploadErrorDvo();
                errorDvo.setErrorRow(row);
                errorDvo.setHeaderName(headerTitle.get("clctamPrtnrNo"));
                errorDvo.setErrorData(
                    messageResourceService.getMessage(
                        "MSG_ALT_NO_EXPORT_DATA" // 엑셀 파일로 내보낼 데이터가 없습니다.
                    )
                );
                excelUploadErrorDvos.add(errorDvo);
            } else if (!prtnrList.contains(dvo.getClctamPrtnrNo())) {
                ExcelUploadErrorDvo errorDvo = new ExcelUploadErrorDvo();
                errorDvo.setErrorRow(row);
                errorDvo.setHeaderName(headerTitle.get("clctamPrtnrNo"));
                errorDvo.setErrorData(
                    messageResourceService.getMessage(
                        "MSG_ALT_NO_CLCTAM_PRTNR_NO_FOUND" // 집금자번호를 찾을 수 없습니다.
                    )
                );
                excelUploadErrorDvos.add(errorDvo);
            }
            row++;
        }

        if (excelUploadErrorDvos.size() == 0) {
            for (WbnaBondContractBaseDvo data : list) {
                String[] cntrList = data.getCntrDtlNo().split("-");
                data.setCntrNo(cntrList[0]);
                data.setCntrSn(Integer.parseInt(cntrList[1]));
                data.setBaseYm(baseYm);

                int insertResult = this.mapper.insertBondContractHistories(data);
                int updateResult = this.mapper.updateFosterTransfer(data);

                BizAssert.isTrue(insertResult == 1 && updateResult == 1, MSG_ALT_SVE_ERR_STR);
            }

            // 채권 업로드 이력 insert
            ZbnaExcelHistoryMgtDvo uploadIz = ZbnaExcelHistoryMgtDvo
                .builder()
                .baseYm(baseYm)
                .bzHdqDvCd(bzHdqDvCd)
                .excelUldCt(list.size())
                .excelDldFileNm(Objects.requireNonNull(file.getOriginalFilename()).replaceAll("[^ㄱ-ㅎㅏ-ㅣ가-힣0-9]", ""))
                .excelDldSrnId(pageId)
                .excelUldErrTpCd(excelUploadErrorDvos.size() > 0 ? "06" : "")
                .build();
            zbnaExcelHistoryMgtService.createBondUploadIz(uploadIz);
        }

        return UploadRes.builder()
            .status(excelUploadErrorDvos.isEmpty() ? "S" : "E").errorInfo(excelUploadErrorDvos).excelData(list).build();
    }

}
