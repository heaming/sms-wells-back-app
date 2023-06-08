package com.kyowon.sms.wells.web.promotion.manage.service;

import static com.kyowon.sms.wells.web.promotion.manage.dto.WpmbPromotionObjectCustomerMgtDto.SearchReq;
import static com.kyowon.sms.wells.web.promotion.manage.dto.WpmbPromotionObjectCustomerMgtDto.SearchRes;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import com.kyowon.sms.wells.web.promotion.manage.dvo.WpmbPromotionObjectCustomerDvo;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.kyowon.sms.wells.web.promotion.manage.converter.WpmbPromotionObjectCustomerMgtConverter;
import com.kyowon.sms.wells.web.promotion.manage.dto.WpmbPromotionObjectCustomerMgtDto.ContractRes;
import com.kyowon.sms.wells.web.promotion.manage.dto.WpmbPromotionObjectCustomerMgtDto.RemoveReq;
import com.kyowon.sms.wells.web.promotion.manage.dto.WpmbPromotionObjectCustomerMgtDto.SaveReq;
import com.kyowon.sms.wells.web.promotion.manage.mapper.WpmbPromotionObjectCustomerMgtMapper;
import com.sds.sflex.common.common.dto.ExcelUploadDto.UploadRes;
import com.sds.sflex.common.common.dvo.CodeDetailDvo;
import com.sds.sflex.common.common.dvo.ExcelMetaDvo;
import com.sds.sflex.common.common.dvo.ExcelUploadErrorDvo;
import com.sds.sflex.common.common.mapper.CodeMapper;
import com.sds.sflex.common.common.service.ExcelReadService;
import com.sds.sflex.common.utils.DateUtil;
import com.sds.sflex.system.config.core.service.MessageResourceService;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import com.sds.sflex.system.config.validation.BizAssert;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WpmbPromotionObjectCustomerMgtService {

    private static final int EXCEL_DATA_START_ROW_INDEX = 1;
    private static final String SPC_DSC_COMMON_CODE_ID = "PMOT_OJ_SPC_DSC_DV_CD";
    private static final String UPLOAD_RESULT_SUCCESS = "S";
    private static final String UPLOAD_RESULT_ERROR = "E";

    private final WpmbPromotionObjectCustomerMgtMapper mapper;
    private final CodeMapper codeMapper;
    private final WpmbPromotionObjectCustomerMgtConverter converter;
    private final ExcelReadService excelReadService;
    private final MessageResourceService messageResourceService;

    /**
     * 프로모션 대상고객 일괄등록 관리 - 페이징 조회
     * @param dto
     * @param pageInfo
     * @return
     */
    public PagingResult<SearchRes> getPromotionObjectCustomerPages(SearchReq dto, PageInfo pageInfo) {
        return mapper.selectPromotionObjectCustomerPages(dto, pageInfo);
    }

    /**
     * 프로모션 대상고객 일괄등록 관리 엑셀 다운로드
     * @param dto
     * @return
     */
    public List<SearchRes> getPromotionObjectCustomersForExcelDownload(SearchReq dto) {
        return mapper.selectPromotionObjectCustomerPages(dto);
    }

    /**
     * 프로모션 대상고객 일괄등록 관리 다건 수정 (그리드 내에서 수정)
     * @param dtos
     * @return
     */
    @Transactional
    public int savePromotionObjectCustomers(@Valid @NotEmpty List<SaveReq> dtos) {
        int processCount = 0;
        for (SaveReq dto : dtos) {
            WpmbPromotionObjectCustomerDvo dvo = converter.mapSaveReqToWpmbPromotionObjectCustomerDvo(dto);
            int result = 0;
            if (StringUtils.isBlank(dvo.getPmotOjRelId())) {
                result = mapper.insertPromotionObjectCustomer(dvo);
            } else {
                result = mapper.updatePromotionObjectCustomer(dvo);
            }
            BizAssert.isTrue(result == 1, "MSG_ALT_SVE_ERR");
            processCount += result;
        }
        return processCount;
    }

    @Transactional
    public UploadRes savePromotionObjectCustomersExcelUpload(MultipartFile file) throws Exception {

        // 1. Header 정보 설정
        Map<String, String> headerTitle = getHeaderTitle();

        // 2. File 데이터 Read
        List<WpmbPromotionObjectCustomerDvo> readExcel = excelReadService.readExcel(file, new ExcelMetaDvo(EXCEL_DATA_START_ROW_INDEX, headerTitle), WpmbPromotionObjectCustomerDvo.class);

        // 3. Validation Check
        List<ExcelUploadErrorDvo> excelUploadErrorDvos = validateExcelDatas(headerTitle, readExcel);

        // 4. Data 저장
        if (excelUploadErrorDvos.isEmpty()) {
            for (WpmbPromotionObjectCustomerDvo dvo : readExcel) {
                int result = mapper.insertPromotionObjectCustomer(dvo);
                BizAssert.isTrue(result == 1, "MSG_ALT_SVE_ERR");
            }
        }

        // 5. Upload 결과 리턴
        return UploadRes.builder()
            .status(excelUploadErrorDvos.isEmpty()?UPLOAD_RESULT_SUCCESS:UPLOAD_RESULT_ERROR)
            .excelData(readExcel)
            .errorInfo(excelUploadErrorDvos)
            .build();
    }

    private Map<String, String> getHeaderTitle() {

        Map<String, String> headerTitle = new LinkedHashMap<>();
        headerTitle.put("cntrNo"            , messageResourceService.getMessage("MSG_TXT_CNTR_NO"));    // 계약번호
        headerTitle.put("cntrSn"            , messageResourceService.getMessage("MSG_TXT_CNTR_SN"));    // 계약일련번호
        headerTitle.put("vlStrtDtm"         , messageResourceService.getMessage("MSG_TXT_START_DATE")); // 시작일
        headerTitle.put("vlEndDtm"          , messageResourceService.getMessage("MSG_TXT_END_DATE"));   // 종료일
        headerTitle.put("pmotOjSpcDscDvCd"  , messageResourceService.getMessage("MSG_TXT_SPC_DSC_CD")); // 특별할인코드

        return headerTitle;
    }

    private List<ExcelUploadErrorDvo> validateExcelDatas(Map<String, String> headerTitle, List<WpmbPromotionObjectCustomerDvo> readExcel) {

        List<ExcelUploadErrorDvo> excelUploadErrorDvos = new ArrayList<>();

        if (!readExcel.isEmpty()) {

            List<CodeDetailDvo> spcDscCommonCds = codeMapper.selectDetails(SPC_DSC_COMMON_CODE_ID);

            for (int i = 0; i < readExcel.size(); i++) {
                WpmbPromotionObjectCustomerDvo excelRow = readExcel.get(i);

                // 1. 계약번호 체크
                if (StringUtils.isBlank(excelRow.getCntrNo())) {    // 필수값 체크
                    excelUploadErrorDvos.add(getErrorDvo(i, headerTitle.get("cntrNo"), messageResourceService.getMessage("MSG_ALT_NCELL_REQUIRED_VAL", headerTitle.get("cntrNo"))));
                }
                // 2. 계약일련번호 체크
                if (StringUtils.isBlank(excelRow.getCntrSn())) {    // 필수값 체크
                    excelUploadErrorDvos.add(getErrorDvo(i, headerTitle.get("cntrSn"), messageResourceService.getMessage("MSG_ALT_NCELL_REQUIRED_VAL", headerTitle.get("cntrSn"))));
                }
                // 3. 존재하는 계약인지 체크
                if (StringUtils.isNotBlank(excelRow.getCntrNo()) && StringUtils.isNotBlank(excelRow.getCntrSn())) {
                    WpmbPromotionObjectCustomerDvo checkDvo = mapper.selectObjectCustomerContractInfo(excelRow);
                    // 3.1. 계약이 존재하는지 체크
                    if (checkDvo == null || StringUtils.isBlank(checkDvo.getCntrNo())) {
                        excelUploadErrorDvos.add(getErrorDvo(i, headerTitle.get("cntrNo"), messageResourceService.getMessage("MSG_ALT_INVALID_ANYTHING", headerTitle.get("cntrNo"), excelRow.getCntrNo())));
                    }
                    // 3.2. 이미 대상고객으로 등록된 계약인지 체크
                    else if (StringUtils.isNotBlank(checkDvo.getPmotOjRelId())) {
                        excelUploadErrorDvos.add(getErrorDvo(i, headerTitle.get("cntrNo"), messageResourceService.getMessage("MSG_ALT_ALREADY_RGST", headerTitle.get("cntrNo"))));
                    }
                    // 3.3. 판매유형코드(적용업무) 값 설정
                    else {
                        readExcel.get(i).setSellTpCd(checkDvo.getSellTpCd());
                    }
                }
                // 4. 시작일 체크
                if (StringUtils.isNotBlank(excelRow.getVlStrtDtm()) && !DateUtil.isValid(excelRow.getVlStrtDtm(), "yyyyMMdd")) {
                    excelUploadErrorDvos.add(getErrorDvo(i, headerTitle.get("vlStrtDtm"), messageResourceService.getMessage("MSG_ALT_ERROR_DT", "'" + excelRow.getVlStrtDtm() + "'")));
                }
                // 5. 종료일 체크
                if (StringUtils.isNotBlank(excelRow.getVlEndDtm()) && !DateUtil.isValid(excelRow.getVlEndDtm(), "yyyyMMdd")) {
                    excelUploadErrorDvos.add(getErrorDvo(i, headerTitle.get("vlEndDtm"), messageResourceService.getMessage("MSG_ALT_ERROR_DT", "'" + excelRow.getVlEndDtm() + "'")));
                }
                // 6. 시작/종료일 선후관계 체크
                if (StringUtils.isNotBlank(excelRow.getVlStrtDtm()) && DateUtil.isValid(excelRow.getVlStrtDtm(), "yyyyMMdd")
                    && StringUtils.isNotBlank(excelRow.getVlEndDtm()) && DateUtil.isValid(excelRow.getVlEndDtm(), "yyyyMMdd")) {
                    if (excelRow.getVlStrtDtm().compareTo(excelRow.getVlEndDtm()) > 0) {
                        excelUploadErrorDvos.add(getErrorDvo(i, headerTitle.get("vlStrtDtm") + "," + headerTitle.get("vlEndDtm"), messageResourceService.getMessage("MSG_ALT_CHK_DT_RLT")));
                    }
                }
                // 7. 특별할인코드 체크
                // 7.1. 필수값 체크
                if (StringUtils.isBlank(excelRow.getPmotOjSpcDscDvCd())) {
                    excelUploadErrorDvos.add(getErrorDvo(i, headerTitle.get("pmotOjSpcDscDvCd"), messageResourceService.getMessage("MSG_ALT_NCELL_REQUIRED_VAL", headerTitle.get("pmotOjSpcDscDvCd"))));
                } else {
                    // 7.2. 정의되지 않는 코드값인지 체크
                    if (spcDscCommonCds.stream().filter(item -> StringUtils.equals(excelRow.getPmotOjSpcDscDvCd(), item.getCodeValidityValue())).findAny().isEmpty()) {
                        excelUploadErrorDvos.add(getErrorDvo(i, headerTitle.get("pmotOjSpcDscDvCd"), messageResourceService.getMessage("MSG_ALT_NOT_DEFINE_ON_COMMON_CODE", excelRow.getPmotOjSpcDscDvCd())));
                    }
                }
            }
        }

        return excelUploadErrorDvos;
    }

    private ExcelUploadErrorDvo getErrorDvo(int row, String header, String errorData) {
        ExcelUploadErrorDvo errorDvo = new ExcelUploadErrorDvo();
        errorDvo.setErrorRow(row + EXCEL_DATA_START_ROW_INDEX + 1);
        errorDvo.setHeaderName(header);
        errorDvo.setErrorData(errorData);
        return errorDvo;
    }

    public ContractRes getContractInfo(String cntrNo, int cntrSn) {
        WpmbPromotionObjectCustomerDvo paramDvo = new WpmbPromotionObjectCustomerDvo();
        paramDvo.setCntrNo(cntrNo);
        paramDvo.setCntrSn(String.valueOf(cntrSn));
        return converter.mapWpmbPromotionObjectCustomerDvoToContractRes(mapper.selectObjectCustomerContractInfo(paramDvo));
    }

    public int removePromotionObjectCustomers(List<RemoveReq> dtos) {
        int processCount = 0;
        for (RemoveReq dto : dtos) {
            WpmbPromotionObjectCustomerDvo dvo = converter.mapRemoveReqToWpmbPromotionObjectCustomerDvo(dto);
            int result = mapper.deletePromotionObjectCustomer(dvo);
            BizAssert.isTrue(result == 1, "MSG_ALT_SVE_ERR");
            processCount += result;
        }
        return processCount;
    }
}
