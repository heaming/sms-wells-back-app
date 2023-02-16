package com.kyowon.sms.wells.web.withdrawal.bilfnt.rest;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.withdrawal.bilfnt.dto.WwdaAutoFntDsnWdrwMgtDto.SaveReq;
import com.kyowon.sms.wells.web.withdrawal.bilfnt.dto.WwdaAutoFntDsnWdrwMgtDto.SearchAutoFntDsnWdrwCstReq;
import com.kyowon.sms.wells.web.withdrawal.bilfnt.dto.WwdaAutoFntDsnWdrwMgtDto.SearchAutoFntDsnWdrwCstRes;
import com.kyowon.sms.wells.web.withdrawal.bilfnt.service.WwdaAutoFntDsnWdrwMgtService;
import com.kyowon.sms.wells.web.withdrawal.zcommon.constants.WdWithdrawalConst;
import com.sds.sflex.system.config.datasource.PagingResult;
import com.sds.sflex.system.config.response.SaveResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(WdWithdrawalConst.REST_URL_V1)
@Api(tags = "[WWDA] 자동이체 지정 출금 고객 관리")
public class WwdaAutoFntDsnWdrwMgtController {

    private final WwdaAutoFntDsnWdrwMgtService service;

    @ApiOperation(value = "자동이체 지정 출금 고객 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query", required = false, example = "W20220086004"),
        @ApiImplicitParam(name = "cntrSn", value = "계약일련번호", paramType = "query", required = false, example = "1"),
        @ApiImplicitParam(name = "sellTpCd", value = "판매유형코드", paramType = "query", required = false, example = "20230215"),
    })
    @GetMapping("/w-aftn-dsn-wdrw-cst-inqr")
    public PagingResult<SearchAutoFntDsnWdrwCstRes> getAftnDsnWdrwCstInqrPages(
        @ApiParam
        @Valid
        SearchAutoFntDsnWdrwCstReq req
    ) {
        return service.getAftnDsnWdrwCstInqrPages(req);
    }

    @ApiOperation(value = "자동이체 지정 출금 고객 저장")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "rowState", value = "행 상태", paramType = "query", required = false, example = "created"),
        @ApiImplicitParam(name = "dataRow", value = "행 번호", paramType = "query", required = false, example = "0"),
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query", required = false, example = "W20220086004"),
        @ApiImplicitParam(name = "cntrSn", value = "계약일련번호", paramType = "query", required = false, example = "1"),
        @ApiImplicitParam(name = "dsnWdrwAmt", value = "지정금액", paramType = "query", required = false, example = "99999"),
        @ApiImplicitParam(name = "dpAmt", value = "입금금액", paramType = "query", required = false, example = "900"),
        @ApiImplicitParam(name = "fntYn", value = "이체구분", paramType = "query", required = false, example = "Y"),
        @ApiImplicitParam(name = "dsnWdrwFntD", value = "이체일자", paramType = "query", required = false, example = "10"),
        @ApiImplicitParam(name = "dsnWdrwFntPrdCd", value = "이체주기", paramType = "query", required = false, example = "2"),
    })
    @PostMapping("/w-aftn-dsn-wdrw-cst-rgst")
    public SaveResponse saveAutoFntDsnWdrwCst(
        @RequestBody
        @Valid
        @NotEmpty
        List<SaveReq> req
    ) throws Exception {
        return SaveResponse.builder()
            .processCount(service.saveAutoFntDsnWdrwCst(req))
            .build();
    }
}
