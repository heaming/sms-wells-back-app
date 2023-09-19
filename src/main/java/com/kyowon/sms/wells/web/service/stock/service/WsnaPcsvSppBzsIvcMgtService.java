package com.kyowon.sms.wells.web.service.stock.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.kyowon.sms.wells.web.service.stock.dvo.WsnaPcsvSppBzsIvcExcelUploadDvo;
import com.sds.sflex.common.common.dto.ExcelUploadDto;
import com.sds.sflex.common.common.dto.ExcelUploadDto.UploadRes;
import com.sds.sflex.common.common.dvo.ExcelMetaDvo;
import com.sds.sflex.common.common.dvo.ExcelUploadErrorDvo;
import com.sds.sflex.common.common.service.ExcelReadService;
import com.sds.sflex.system.config.core.service.MessageResourceService;
import com.sds.sflex.system.config.validation.BizAssert;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class WsnaPcsvSppBzsIvcMgtService {

    private final MessageResourceService messageResourceService;
    private final ExcelReadService excelReadService;

    @Transactional
    public UploadRes savePcsvSppBzsOutOfStorageExcelUpload(MultipartFile file) throws Exception {

        // TODO FIX 된 양식 기준으로 헤더 정의 필요 (현재는 ASIS 기준)

        // 1. Excel Header 정보 설정
        Map<String, String> headerTitle = getHeaderTitle();

        // 2. File 데이터 Read
        List<WsnaPcsvSppBzsIvcExcelUploadDvo> list = excelReadService.readExcel(
            file, new ExcelMetaDvo(1, headerTitle), WsnaPcsvSppBzsIvcExcelUploadDvo.class, true
        );

        // 3. 유효성 체크
        List<ExcelUploadErrorDvo> excelUploadErrorDvos = validateExcelDatas(headerTitle, list);

        // 4. Data 저장 (에러메세지 없는경우)
        if (CollectionUtils.isEmpty(excelUploadErrorDvos)) {
            for (WsnaPcsvSppBzsIvcExcelUploadDvo insertData : list) {
                log.info("insertData: {}", insertData);
                // TODO 저장할 테이블 지정
                int result = 1;
                BizAssert.isTrue(result == 1, "MSG_ALT_SVE_ERR");
            }
        }

        // 5. Upload 결과 리턴
        return ExcelUploadDto.UploadRes.builder()
            .status(CollectionUtils.isNotEmpty(list) && CollectionUtils.isEmpty(excelUploadErrorDvos) ? "S" : "E")
            .errorInfo(excelUploadErrorDvos)
            .excelData(list).build();
    }

    /**
     * 엑셀업로드 헤더 설정
     */
    private Map<String, String> getHeaderTitle() {

        Map<String, String> headerTitle = new LinkedHashMap<>();
        headerTitle.put("reqDv", "구분");
        headerTitle.put("cntrNo", "계약번호");
        headerTitle.put("cntrSn", "계약일련번호");
        headerTitle.put("pcsvCompDv", "택배사구분");
        headerTitle.put("sppIvcNo", "송장번호");
        headerTitle.put("sppBzsPdId", "SR번호");

        return headerTitle;
    }

    /**
     * 엑셀업로드 유효성 체크 설정
     */
    private List<ExcelUploadErrorDvo> validateExcelDatas(
        Map<String, String> headerTitle, List<WsnaPcsvSppBzsIvcExcelUploadDvo> list
    ) {
        List<ExcelUploadErrorDvo> excelUploadErrorDvos = new ArrayList<>();

        if (CollectionUtils.isNotEmpty(list)) {
            int row = 1;

            for (WsnaPcsvSppBzsIvcExcelUploadDvo dvo : list) {
                row++;

                List<String> emptyColumnNames = new ArrayList();

                // TODO FIX 된 양식 유효성 정의 필요 (현재는 ASIS 기준)
                String reqDv = dvo.getReqDv();
                String cntrNo = dvo.getCntrNo();
                String cntrSn = dvo.getCntrSn();
                String sppIvcNo = dvo.getSppIvcNo();
                String sppBzsPdId = dvo.getSppBzsPdId();
                String pcsvCompDv = dvo.getPcsvCompDv();
                // 구분
                if (StringUtils.isEmpty(reqDv)) {
                    emptyColumnNames.add("reqDv");
                }
                // 계약번호
                if (StringUtils.isEmpty(cntrNo)) {
                    emptyColumnNames.add("cntrNo");
                }
                // 계약일련번호
                if (StringUtils.isEmpty(cntrSn)) {
                    emptyColumnNames.add("cntrSn");
                }
                // 송장번호
                if (StringUtils.isEmpty(sppIvcNo)) {
                    emptyColumnNames.add("sppIvcNo");
                }
                // SR번호
                if (StringUtils.isEmpty(sppBzsPdId)) {
                    emptyColumnNames.add("sppBzsPdId");
                }
                // 택배사 구분
                if (StringUtils.isEmpty(pcsvCompDv)) {
                    emptyColumnNames.add("pcsvCompDv");
                }

                if (CollectionUtils.isNotEmpty(emptyColumnNames)) {
                    //  데이터 존재 체크 [유효하지 않은 항목이 있습니다.]
                    for (String column : emptyColumnNames) {
                        excelUploadErrorDvos.add(
                            getErrorDvo(
                                row, headerTitle.get(column),
                                messageResourceService.getMessage("MSG_ALT_EXIST_INVALID_ITEM")
                            )
                        );
                    }
                    continue;
                }

                // 데이터 존재하는경우에만 유효성 체크

                // 송장번호 숫자 체크 [숫자만 입력 가능합니다.]
                if (StringUtils.isNotEmpty(sppIvcNo) && !isNumeric(sppIvcNo)) {
                    String header = headerTitle.get("sppIvcNo");
                    excelUploadErrorDvos.add(
                        getErrorDvo(
                            row, header,
                            messageResourceService.getMessage("MSG_ALT_ONLY_NUMBER", header)
                        )
                    );
                }
                // 계약일련번호 숫자 체크 [숫자만 입력 가능합니다.]
                if (StringUtils.isNotEmpty(cntrSn) && !isNumeric(cntrSn)) {
                    String header = headerTitle.get("cntrSn");
                    excelUploadErrorDvos.add(
                        getErrorDvo(
                            row, header,
                            messageResourceService.getMessage("MSG_ALT_ONLY_NUMBER", header)
                        )
                    );
                }

            }
        }
        return excelUploadErrorDvos;
    }

    /**
     * 숫자 체크 (정규식): 소수점까지 체크
     */
    private boolean isNumeric(String s) {
        return s.matches("[0-9]+(.[0-9])");
    }

    /**
     * 에러 정보 Dvo
     */
    private ExcelUploadErrorDvo getErrorDvo(int row, String header, String errorData) {
        ExcelUploadErrorDvo errorDvo = new ExcelUploadErrorDvo();
        errorDvo.setErrorRow(row);
        errorDvo.setHeaderName(header);
        errorDvo.setErrorData(errorData);
        return errorDvo;
    }
}
