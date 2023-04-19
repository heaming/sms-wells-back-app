package com.kyowon.sms.wells.web.service.common.service;

import com.kyowon.sms.common.web.closing.mileage.dvo.ZdceSmartMileageExcelDvo;
import com.kyowon.sms.common.web.closing.mileage.util.ZdceMileageUtil;
import com.kyowon.sms.wells.web.service.common.converter.WsnyAsCodeMgtConverter;
import com.kyowon.sms.wells.web.service.common.dto.WsnyAsCodeMgtDto;
import com.kyowon.sms.wells.web.service.common.dvo.WsnyAsCodeMgtDvo;
import com.kyowon.sms.wells.web.service.common.mapper.WsnyAsCodeMgtMapper;
import com.sds.sflex.common.common.dto.ExcelUploadDto;
import com.sds.sflex.common.common.dvo.ExcelMetaDvo;
import com.sds.sflex.common.common.dvo.ExcelUploadErrorDvo;
import com.sds.sflex.common.common.service.ExcelReadService;
import com.sds.sflex.system.config.core.service.MessageResourceService;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * <pre>
 * W-SV-U-0016M01 AS 코드관리
 * </pre>
 *
 * @author gs.piit122 김동엽
 * @since 2022-11-08
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class WsnyAsCodeMgtService {

    private final WsnyAsCodeMgtMapper mapper;
    private final WsnyAsCodeMgtConverter converter;
    private final Map<String, String> header;
    private final MessageResourceService messageResourceService;
    private final ExcelReadService excelReadService;

    public PagingResult<WsnyAsCodeMgtDto.SearchRes> getAsCodePages(
        WsnyAsCodeMgtDto.SearchReq dto, PageInfo pageInfo
    ) {
        return new PagingResult<>(
            converter.mapAllSearchResToDvo(mapper.selectAsCodePages(dto, pageInfo)), pageInfo
        );
    }

    public ExcelUploadDto.UploadRes uploadExcel(
        final MultipartFile file
    ) throws Exception {

        header.put("evnNm", messageResourceService.getMessage("MSG_TXT_RV_RSON"));
        header.put("mlgExnDt", messageResourceService.getMessage("MSG_TXT_VALID_PERIOD"));

        final List<WsnyAsCodeMgtDvo> dvos = excelReadService
            .readExcel(file, new ExcelMetaDvo(1, header), WsnyAsCodeMgtDvo.class);

        //처리한 뒤 오류가 있으면 뱉어주고,  없으면 status = "S" 로 내려준다.
        List<ExcelUploadErrorDvo> excelUploadErrorDvos = new ArrayList<>();

        for (WsnyAsCodeMgtDvo dvo : dvos) {
            log.debug(dvo.getAsCode());
        }

        //    ...

        return ExcelUploadDto.UploadRes.builder()
            .status(excelUploadErrorDvos.isEmpty() ? "S" : "E")
            .errorInfo(excelUploadErrorDvos)
            .build();
    }

}
