package com.kyowon.sms.wells.web.fee.standard.service;

import com.kyowon.sms.wells.web.fee.standard.converter.WfeyContractBsFeeExConverter;
import com.kyowon.sms.wells.web.fee.standard.dto.WfeyContractBsFeeExDto.SaveContractBsFeeExReq;
import com.kyowon.sms.wells.web.fee.standard.dto.WfeyContractBsFeeExDto.SearchContractBsFeeExReq;
import com.kyowon.sms.wells.web.fee.standard.dto.WfeyContractBsFeeExDto.SearchContractBsFeeExRes;
import com.kyowon.sms.wells.web.fee.standard.dvo.WfeyContractBsFeeExDvo;
import com.kyowon.sms.wells.web.fee.standard.mapper.WfeyContractBsFeeExMapper;
import com.sds.sflex.common.common.dto.ExcelUploadDto;
import com.sds.sflex.common.common.dvo.ExcelMetaDvo;
import com.sds.sflex.common.common.dvo.ExcelUploadErrorDvo;
import com.sds.sflex.common.common.service.ExcelReadService;
import com.sds.sflex.system.config.constant.CommConst;
import com.sds.sflex.system.config.core.service.MessageResourceService;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import com.sds.sflex.system.config.exception.BizException;
import com.sds.sflex.system.config.validation.BizAssert;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

/**
 * <pre>
 * 계약별 BS 수수료 예외 기준정보
 * </pre>
 *
 * @author mj
 * @since 2023.07.13
 */
@Service
@RequiredArgsConstructor
public class WfeyContractBsFeeExService {

    private final WfeyContractBsFeeExMapper mapper;
    private final WfeyContractBsFeeExConverter converter;
    private final MessageResourceService messageService;
    private final ExcelReadService excelReadService;


    /**
     * 조회
     * @param req
     * @return
     */
    public List<SearchContractBsFeeExRes> getContractBsFeeExList(SearchContractBsFeeExReq req) {
        return mapper.selectContractBsFeeExList(req);
    }
    public PagingResult<SearchContractBsFeeExRes> getContractBsFeeExList(SearchContractBsFeeExReq req, PageInfo pageInfo) {
        return mapper.selectContractBsFeeExList(req, pageInfo);
    }

    public int getMaxBaseChTcnt(String cntrNo, int cntrSn) {
         return mapper.selectMaxBaseChTcnt(cntrNo, cntrSn);
    }

    /**
     * 저장
     * @param req
     * @return
     * @throws Exception
     */
    @Transactional
    public int saveContractBsFeeEx(List<SaveContractBsFeeExReq> req) throws Exception {
        int processCount = 0;
        for (SaveContractBsFeeExReq dto : req) {
            WfeyContractBsFeeExDvo dvo = converter.mapSaveReqWfeyContractBsFeeExDvo(dto);
            switch (dto.rowState()) {
                case CommConst.ROW_STATE_CREATED -> {
                   int validCount = mapper.selecDuplicateContractBsFeeEx(dvo);
                    BizAssert.isTrue(validCount == 0, "MSG_ALT_DUPLICATE_EXISTS");
                    processCount += mapper.insertContractBsFeeEx(dvo);
                }
                case CommConst.ROW_STATE_UPDATED -> {
                    processCount += mapper.updateContractBsFeeEx(dvo);
                }
                case CommConst.ROW_STATE_DELETED -> {
                    processCount += mapper.deleteContractBsFeeEx(dvo);
                }
                default -> throw new BizException("MSG_ALT_UNHANDLE_ROWSTATE");
            }
        }
        return processCount;
    }


    /**
     * 엑셀업로드
     * @param file
     * @return
     * @throws Exception
     */
    @Transactional
    public ExcelUploadDto.UploadRes saveContractBsFeeExExcelUpload(MultipartFile file) throws Exception {
        // 업로드 엑셀 헤더 설정
        Map<String, String> headerTitle = new LinkedHashMap<>();
        headerTitle.put("cntrDtlSn", messageService.getMessage("MSG_TXT_CNTR_DTL_NO"));
        headerTitle.put("vstMcn", messageService.getMessage("MSG_TXT_VISIT_MN"));
        headerTitle.put("baseChTcnt", messageService.getMessage("MSG_TXT_ORDR"));
        headerTitle.put("svFeeBaseAmt", messageService.getMessage("TXT_MSG_FEE_AMT") + "(" + messageService.getMessage("MSG_TXT_FXAM") + "/" + messageService.getMessage("MSG_TXT_HMST") + ")");
        headerTitle.put("feeFxamYn", messageService.getMessage("MSG_TXT_FXAM_YN"));
        headerTitle.put("apyStrtYm", messageService.getMessage("MSG_TXT_APY_STRT_YM"));
        headerTitle.put("apyEndYm", messageService.getMessage("MSG_TXT_APY_END_YM"));

        String status = "S";
        int row = 2;
        List<Map<String, Object>> checks = new ArrayList<>();
        ExcelMetaDvo meta = new ExcelMetaDvo(1, headerTitle);
        List<WfeyContractBsFeeExDvo> lists = excelReadService.readExcel(file, meta, WfeyContractBsFeeExDvo.class);
        List<ExcelUploadErrorDvo> errorDvos = new ArrayList<>();

        for (WfeyContractBsFeeExDvo list : lists) {
            ExcelUploadErrorDvo errorDvo = new ExcelUploadErrorDvo();
            Map<String, Object> check = new HashMap<>();
            int finalRow = row;
            int cntrExistCnt = 0;

            // 필수 유효성
            if (StringUtils.isEmpty(list.getCntrDtlSn())) {
                errorDvo.setErrorRow(finalRow);
                errorDvo.setHeaderName(headerTitle.get("cntrDtlSn"));
                errorDvo.setErrorData(messageService.getMessage("MSG_ALT_EMPTY_REQUIRED_VAL")); //필수값이 누락되어 있습니다.
            }
            if (list.getVstMcn() == null) {
                errorDvo.setErrorRow(finalRow);
                errorDvo.setHeaderName(headerTitle.get("vstMcn"));
                errorDvo.setErrorData(messageService.getMessage("MSG_ALT_EMPTY_REQUIRED_VAL")); //필수값이 누락되어 있습니다.
            }
            if (list.getBaseChTcnt() == null) {
                errorDvo.setErrorRow(finalRow);
                errorDvo.setHeaderName(headerTitle.get("baseChTcnt"));
                errorDvo.setErrorData(messageService.getMessage("MSG_ALT_EMPTY_REQUIRED_VAL")); //필수값이 누락되어 있습니다.
            }
            if (list.getSvFeeBaseAmt() == null) {
                errorDvo.setErrorRow(finalRow);
                errorDvo.setHeaderName(headerTitle.get("svFeeBaseAmt"));
                errorDvo.setErrorData(messageService.getMessage("MSG_ALT_EMPTY_REQUIRED_VAL")); //필수값이 누락되어 있습니다.
            }
            if (StringUtils.isEmpty(list.getFeeFxamYn())) {
                errorDvo.setErrorRow(finalRow);
                errorDvo.setHeaderName(headerTitle.get("feeFxamYn"));
                errorDvo.setErrorData(messageService.getMessage("MSG_ALT_EMPTY_REQUIRED_VAL")); //필수값이 누락되어 있습니다.
            }
            if (StringUtils.isEmpty(list.getApyStrtYm())) {
                errorDvo.setErrorRow(finalRow);
                errorDvo.setHeaderName(headerTitle.get("apyStrtYm"));
                errorDvo.setErrorData(messageService.getMessage("MSG_ALT_EMPTY_REQUIRED_VAL")); //필수값이 누락되어 있습니다.
            }
            if (StringUtils.isEmpty(list.getApyEndYm())) {
                errorDvo.setErrorRow(finalRow);
                errorDvo.setHeaderName(headerTitle.get("apyEndYm"));
                errorDvo.setErrorData(messageService.getMessage("MSG_ALT_EMPTY_REQUIRED_VAL")); //필수값이 누락되어 있습니다.
            }
            // 키값 필수 유효성 통과시 데이터 정합성 체크
            if (StringUtils.isNotEmpty(list.getCntrDtlSn()) && list.getVstMcn() != null && list.getBaseChTcnt() == null) {
                // 입력 중복 체크
                if (CollectionUtils.isNotEmpty(checks)) {
                    for (int i = 0; i < checks.size(); i++) {
                        if (checks.get(i).get("cntrDtlSn").equals(list.getCntrDtlSn())
                            && checks.get(i).get("vstMcn").equals(list.getVstMcn())
                            && checks.get(i).get("baseChTcnt").equals(list.getBaseChTcnt())) {
                            errorDvo.setErrorRow(finalRow);
                            errorDvo.setHeaderName(headerTitle.get("cntrDtlSn"));
                            errorDvo.setErrorData(messageService.getMessage("MSG_ALT_DUPLICATE_EXISTS")); //중복된 값이 존재합니다.
                        }
                    }
                }
                // DB중복체크
                cntrExistCnt = mapper.selecDuplicateContractBsFeeEx(list);
                if (cntrExistCnt > 0) { //기존데이터 체크
                    errorDvo.setErrorRow(finalRow);
                    errorDvo.setHeaderName(headerTitle.get("basePdCd"));
                    errorDvo.setErrorData(messageService.getMessage("MSG_TXT_SMD_INF_EXST")); // 동일한 정보가 존재합니다.
                }
                check.put("cntrDtlSn", list.getCntrDtlSn());
                check.put("vstMcn", list.getVstMcn());
                check.put("baseChTcnt", list.getBaseChTcnt());
                checks.add(check);
            }
            if (null != errorDvo && (errorDvo.getErrorRow() != 0)) {
                errorDvos.add(errorDvo);
            }
            row++;
        }
        if (CollectionUtils.isNotEmpty(errorDvos)) {
            status = "E";
        }
        // 성공시 insert gogo
        if (status.equals("S")) {
            for (WfeyContractBsFeeExDvo list : lists) {
                mapper.insertContractBsFeeEx(list);
            }
        }
        ExcelUploadDto.UploadRes result = ExcelUploadDto.UploadRes.builder()
            .status(status)
            .excelData(lists)
            .errorInfo(errorDvos)
            .build();
        return result;
    }
}
