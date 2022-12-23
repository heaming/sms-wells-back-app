package com.kyowon.sms.wells.web.service.allocate.rest;

import static com.kyowon.sms.wells.web.service.allocate.dto.WsncRpbLocaraCdMngtDto.SearchReq;
import static com.kyowon.sms.wells.web.service.allocate.dto.WsncRpbLocaraCdMngtDto.SearchRes;

import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.service.allocate.service.WsncRpbLocaraCdMngtService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(SnServiceConst.REST_URL_WELLS_SERVICE + "/responsible-area-codes")
@Api(tags = "[WSNC] 책임지역 지역코드 관리 REST API")
@RequiredArgsConstructor
@Validated
@Slf4j
public class WsncRpbLocaraCdMngtController {

    private final WsncRpbLocaraCdMngtService service;

    @GetMapping("/paging")
    public PagingResult<SearchRes> getResponsibleAreaCodePages(
        SearchReq dto, @Valid
        PageInfo pageInfo
    ) {
        return service.getAfterServiceCodeMngtPages(dto, pageInfo);
    }

    @GetMapping("/excel-download")
    public List<SearchRes> getAfterServiceCodeMngtExcelDownload(SearchReq dto) {
        return service.getAfterServiceCodeMngtExcelDownload(dto);
    }

}
