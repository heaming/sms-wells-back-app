package com.kyowon.sms.wells.web.service.stock.rest;

import javax.validation.Valid;

import com.kyowon.sms.wells.web.service.stock.service.WsnaOutOfStorageAskRgstService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaOutOfStorageAskRgstDto;
import com.kyowon.sms.wells.web.zcommon.constants.SnServiceConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(SnServiceConst.REST_URL_WELLS_SERVICE + "/out-of-storage-ask-rgst")
@Api(tags = "[WSNA] 출고요청 등록 REST API")
@RequiredArgsConstructor
@Validated
public class WsnaOutOfStorageAskRgstController {

    private final WsnaOutOfStorageAskRgstService outOfStorageAskRgstService;

    /**
     * 출고요청 등록 - 조회 (페이징)
     * @param dto : {}
     * @param pageInfo
     * @return 조회결과
     */
    @GetMapping("/paging")
    public PagingResult<WsnaOutOfStorageAskRgstDto.SearchRes> getOutOfStorageAskRgst(
        WsnaOutOfStorageAskRgstDto.SearchReq dto,
        @Valid
        PageInfo pageInfo
    ) {
        return this.outOfStorageAskRgstService.getOutOfStorageAsk(dto, pageInfo);
    }

}
