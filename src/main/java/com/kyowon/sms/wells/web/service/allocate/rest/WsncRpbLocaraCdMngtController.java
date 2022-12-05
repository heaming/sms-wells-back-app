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
import com.kyowon.sms.wells.web.service.zcommon.constants.ServiceConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(ServiceConst.REST_URL_WELLS_SERVICE + "/responsible-area-codes")
@Api(tags = "[WSNC] AS 책임지역 지역코드 관리 REST API")
@RequiredArgsConstructor
@Validated
@Slf4j
public class WsncRpbLocaraCdMngtController {

    private final WsncRpbLocaraCdMngtService service;

    /**
     * 책임지역 지역코드 관리
     * @param dto : { zipForm : 우편번호 From, zipTo : 우편번호 To, fr2pLgldCd : 법정동코드 앞 2자리, ctpvNm : 시도명, ctctyNm : 시군구명,
     *           ogId :서비스센터 ID, wrkGrpCd : 작업그룹코드, applyDate : 적용일자, locaraCdFrom : 지역코드 From, locaraCdTo : 지역코드 To }
     * @param pageInfo : 페이징정보
     * @return 조회결과
     */
    @GetMapping("/paging")
    public PagingResult<SearchRes> getRpbLocaraCdMngtPages(
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
