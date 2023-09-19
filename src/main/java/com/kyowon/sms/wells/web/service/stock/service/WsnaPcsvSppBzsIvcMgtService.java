package com.kyowon.sms.wells.web.service.stock.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.eclipse.jetty.util.StringUtil;
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
            int row = 2;

            for (WsnaPcsvSppBzsIvcExcelUploadDvo dvo : list) {
                List<String> emptyColumnNames = new ArrayList();

                // TODO FIX 된 양식 유효성 정의 필요 (현재는 ASIS 기준)
                // 구분
                if (StringUtil.isEmpty(dvo.getReqDv())) {
                    emptyColumnNames.add("reqDv");
                }
                // 계약번호
                if (StringUtil.isEmpty(dvo.getCntrNo())) {
                    emptyColumnNames.add("cntrNo");
                }
                // 계약일련번호
                if (StringUtil.isEmpty(dvo.getCntrSn())) {
                    emptyColumnNames.add("cntrSn");
                }
                // 송장번호
                if (StringUtil.isEmpty(dvo.getSppIvcNo())) {
                    emptyColumnNames.add("sppIvcNo");
                }
                // SR번호
                if (StringUtil.isEmpty(dvo.getSppBzsPdId())) {
                    emptyColumnNames.add("sppBzsPdId");
                }
                // 택배사 구분
                if (StringUtil.isEmpty(dvo.getPcsvCompDv())) {
                    emptyColumnNames.add("pcsvCompDv");
                }

                //  데이터 없을시 [유효하지 않은 항목이 있습니다.]
                for (String column : emptyColumnNames) {
                    excelUploadErrorDvos.add(
                        getErrorDvo(
                            row, headerTitle.get(column),
                            messageResourceService.getMessage("MSG_ALT_EXIST_INVALID_ITEM", headerTitle.get(column))
                        )
                    );
                }

                // 추가 유효성체크

                row++;
            }
        }
        return excelUploadErrorDvos;
    }

    private ExcelUploadErrorDvo getErrorDvo(int row, String header, String errorData) {
        ExcelUploadErrorDvo errorDvo = new ExcelUploadErrorDvo();
        errorDvo.setErrorRow(row);
        errorDvo.setHeaderName(header);
        errorDvo.setErrorData(errorData);
        return errorDvo;
    }
}
