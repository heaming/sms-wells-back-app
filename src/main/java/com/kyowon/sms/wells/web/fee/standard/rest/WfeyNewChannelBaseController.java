package com.kyowon.sms.wells.web.fee.standard.rest;

import com.kyowon.sms.wells.web.fee.standard.dto.WfeyNewChannelBaseDto.SaveNewChannelBaseReq;
import com.kyowon.sms.wells.web.fee.standard.dto.WfeyNewChannelBaseDto.SearchNewChannelBaseReq;
import com.kyowon.sms.wells.web.fee.standard.dto.WfeyNewChannelBaseDto.SearchNewChannelBaseRes;
import com.kyowon.sms.wells.web.fee.standard.service.WfeyNewChannelBaseService;
import com.kyowon.sms.wells.web.fee.zcommon.constants.CtFeeConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import com.sds.sflex.system.config.response.SaveResponse;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(tags = "[WFEY] 신채널수수료 기준관리")
@RequiredArgsConstructor
@RestController
@RequestMapping(CtFeeConst.REST_URL_V1 + "/new-chanel-base")
@Slf4j
public class WfeyNewChannelBaseController {

    private final WfeyNewChannelBaseService service;


    @ApiOperation(value = "신채널수수료기준관리 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "apyStrtdt", value = "적용시작일", paramType = "query", required = true),
        @ApiImplicitParam(name = "pdCd", value = "상품코드 ", paramType = "query"),
        @ApiImplicitParam(name = "sellTpCd", value = "판매유형 ", paramType = "query"),
        @ApiImplicitParam(name = "svPrd", value = "BS주기 ", paramType = "query"),
        @ApiImplicitParam(name = "pmotCd", value = "할인제도 ", paramType = "query"),
        @ApiImplicitParam(name = "feecDvCd", value = "채널구분 ", paramType = "query"),
    })
    @GetMapping()
    public List<SearchNewChannelBaseRes> getNewChannelBase(@ApiParam @Valid SearchNewChannelBaseReq req) {
        return service.getNewChannelBaseMgtList(req);
    }

    @ApiOperation(value = "신채널수수료기준관리 페이징")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "apyStrtdt", value = "적용시작일", paramType = "query", required = true),
        @ApiImplicitParam(name = "pdCd", value = "상품코드 ", paramType = "query"),
        @ApiImplicitParam(name = "sellTpCd", value = "판매유형 ", paramType = "query"),
        @ApiImplicitParam(name = "svPrd", value = "BS주기 ", paramType = "query"),
        @ApiImplicitParam(name = "pmotCd", value = "할인제도 ", paramType = "query"),
        @ApiImplicitParam(name = "feecDvCd", value = "채널구분 ", paramType = "query"),
    })
    @GetMapping("/pages")
    public PagingResult<SearchNewChannelBaseRes> getNewChannelBase(@ApiParam @Valid SearchNewChannelBaseReq req, @Valid PageInfo pageInfo) {
        return service.getNewChannelBaseMgtList(req, pageInfo);
    }

    @ApiOperation(value = "신채널수수료기준관리 저장", notes = "신채널수수료기준관리 데이터를 수정한다.")
    @PostMapping()
    public SaveResponse saveNewChannelBase(@RequestBody @Valid List<SaveNewChannelBaseReq> dto) throws Exception {
        return SaveResponse.builder()
            .processCount(service.saveNewChannelBaseMgt(dto))
            .build();
    }



}
