package com.kyowon.sms.wells.web.competence.evaluate.service;

import com.kyowon.sms.wells.web.competence.evaluate.dto.WpsdExclDivAwdErnWhtxRgstDto.SearchReq;
import com.kyowon.sms.wells.web.competence.evaluate.dto.WpsdExclDivAwdErnWhtxRgstDto.SearchRes;
import com.kyowon.sms.wells.web.competence.evaluate.dvo.WpsdExclDivAwdErnWhtxRgstDvo;
import com.kyowon.sms.wells.web.competence.evaluate.mapper.WpsdExclDivAwdErnWhtxRgstMapper;
import com.sds.sflex.common.common.dto.ExcelUploadDto;
import com.sds.sflex.common.common.dvo.ExcelMetaDvo;
import com.sds.sflex.common.common.dvo.ExcelUploadErrorDvo;
import com.sds.sflex.common.common.service.ExcelReadService;
import com.sds.sflex.system.config.core.service.MessageResourceService;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class WpsdExclDivAwdErnWhtxRgstService {

    private final WpsdExclDivAwdErnWhtxRgstMapper mapper;
    private final MessageResourceService messageService;
    private final ExcelReadService excelReadService;

    public PagingResult<SearchRes> getExclDivAwdErnWhtxRgstPages(SearchReq dto, PageInfo pageInfo) {
        return mapper.selectExclDivAwdErnWhtxRgstPages(dto, pageInfo);
    }

    public List<SearchRes> getExclDivAwdErnWhtxRgstExcelDownload(SearchReq dto) {
        return mapper.selectExclDivAwdErnWhtxRgstPages(dto);
    }

    @Transactional
    public ExcelUploadDto.UploadRes exclDivAwdErnWhtxRgstExcelUpload(MultipartFile file) throws Exception {
        Map<String, String> headerTitle = new LinkedHashMap<>();
        headerTitle.put("perfYm", messageService.getMessage("MSG_TXT_PERF_YM"));
        headerTitle.put("ogTpCd", messageService.getMessage("MSG_TXT_OG_TP_CD"));
        headerTitle.put("prtnrNo", messageService.getMessage("MSG_TXT_PRTNR_NUMBER"));
        headerTitle.put("awdDvCd", messageService.getMessage("MSG_TXT_DV_CD"));
        headerTitle.put("awdNm", messageService.getMessage("MSG_TXT_AWD_NM"));
        headerTitle.put("awdIntbsAmt", messageService.getMessage("MSG_TXT_AWD_INTBS_AMT"));
        headerTitle.put("awdErnWhtx", messageService.getMessage("AWD_ERN_WHTX"));

        ExcelMetaDvo meta = new ExcelMetaDvo(1, headerTitle);

        List<WpsdExclDivAwdErnWhtxRgstDvo> dvos = excelReadService.readExcel(file, meta, WpsdExclDivAwdErnWhtxRgstDvo.class);
        List<ExcelUploadErrorDvo> excelUploadErrorDvos = new ArrayList<>();

        for (WpsdExclDivAwdErnWhtxRgstDvo dvo : dvos) {
            mapper.insertExclDivAwdErnWhtxRgst(dvo);
        }
        return ExcelUploadDto.UploadRes.builder()
            .status(excelUploadErrorDvos.isEmpty() ? "S" : "E")
            .errorInfo(excelUploadErrorDvos)
            .excelData(dvos).build();

    }
}
