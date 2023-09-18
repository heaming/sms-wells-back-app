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
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaPcsvSppBzsIvcExcelUploadValidDvo;
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

        // 업로드 엑셀 헤더 설정
        // TODO FIX 된 양식 기준으로 헤더 정의 필요 (현재는 ASIS 기준)
        Map<String, String> headerTitle = new LinkedHashMap<>();
        headerTitle.put("reqDv", "구분");
        headerTitle.put("cntrNo", "계약번호");
        headerTitle.put("pcsvCompDv", "택배사구분");
        headerTitle.put("sppIvcNo", "송장번호");
        headerTitle.put("sppBzsPdId", "SR번호");

        // file 복호화
        List<WsnaPcsvSppBzsIvcExcelUploadDvo> list = excelReadService
            .readExcel(file, new ExcelMetaDvo(1, headerTitle), WsnaPcsvSppBzsIvcExcelUploadDvo.class, true);

        // 업로드 에러
        List<ExcelUploadErrorDvo> excelUploadErrorDvos = new ArrayList<>();

        if (CollectionUtils.isNotEmpty(list)) {
            int row = 2;

            WsnaPcsvSppBzsIvcExcelUploadValidDvo validDvo = new WsnaPcsvSppBzsIvcExcelUploadValidDvo();
            validDvo.setHeaderTitle(headerTitle);
            validDvo.setErrorDvos(excelUploadErrorDvos);

            for (WsnaPcsvSppBzsIvcExcelUploadDvo dvo : list) {
                validDvo.setDvo(dvo);
                validDvo.setRow(row);
                //유효성체크
                this.checkExcelUploadValid(validDvo);
                row++;
            }

            excelUploadErrorDvos = validDvo.getErrorDvos();

            // 에러메세지 없는경우 (정상 데이터) 인경우 테이블 저장
            if (CollectionUtils.isEmpty(excelUploadErrorDvos)) {
                for (WsnaPcsvSppBzsIvcExcelUploadDvo insertData : list) {
                    log.info("insertData: {}", insertData);
                    // TODO 저장할 테이블 지정
                    int result = 1;
                    BizAssert.isTrue(result == 1, "MSG_ALT_SVE_ERR");
                }
            }

        }

        return ExcelUploadDto.UploadRes.builder()
            .status(CollectionUtils.isNotEmpty(list) && CollectionUtils.isEmpty(excelUploadErrorDvos) ? "S" : "E")
            .errorInfo(excelUploadErrorDvos)
            .excelData(list).build();
    }

    /**
     * 엑셀업로드 유효성 체크
     * @param validDvo
     */
    private void checkExcelUploadValid(WsnaPcsvSppBzsIvcExcelUploadValidDvo validDvo) {

        WsnaPcsvSppBzsIvcExcelUploadDvo dvo = validDvo.getDvo();
        Map<String, String> headerTitle = validDvo.getHeaderTitle();
        List<ExcelUploadErrorDvo> excelUploadErrorDvos = validDvo.getErrorDvos();

        List<String> emptyColumnNames = new ArrayList();
        // 유효성체크 (headerTitle 기준으로 정의해야함)

        // TODO FIX 된 양식 유효성 정의 필요 (현재는 ASIS 기준)
        // 구분
        if (StringUtil.isBlank(dvo.getReqDv())) {
            emptyColumnNames.add("reqDv");
        }
        // 계약번호
        if (StringUtil.isBlank(dvo.getCntrNo())) {
            emptyColumnNames.add("cntrNo");
        }
        // 송장번호
        if (StringUtil.isBlank(dvo.getSppIvcNo())) {
            emptyColumnNames.add("sppIvcNo");
        }
        // SR번호
        if (StringUtil.isBlank(dvo.getSppBzsPdId())) {
            emptyColumnNames.add("sppBzsPdId");
        }
        // 택배사 구분
        if (StringUtil.isBlank(dvo.getPcsvCompDv())) {
            emptyColumnNames.add("pcsvCompDv");
        }

        // 화면에 각각 에러표시를 위한 저장
        int row = validDvo.getRow();
        for (String column : emptyColumnNames) {
            ExcelUploadErrorDvo errorDvo = new ExcelUploadErrorDvo();
            errorDvo.setErrorRow(row);
            errorDvo.setHeaderName(headerTitle.get(column));
            errorDvo.setErrorData(
                messageResourceService.getMessage(
                    "MSG_ALT_EXIST_INVALID_ITEM" // 유효하지 않은 항목이 있습니다.
                )
            );
            excelUploadErrorDvos.add(errorDvo);
        }

    }
}
