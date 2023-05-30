package com.kyowon.sms.wells.web.closing.expense.rest;

import com.kyowon.sms.wells.web.closing.expense.dto.WdcdRequestCleaningSuppliesMgtDto.CodeReq;
import com.kyowon.sms.wells.web.closing.expense.dto.WdcdRequestCleaningSuppliesMgtDto.CodeRes;
import com.kyowon.sms.wells.web.closing.expense.dto.WdcdRequestCleaningSuppliesMgtDto.FindRes;
import com.kyowon.sms.wells.web.closing.expense.dto.WdcdRequestCleaningSuppliesMgtDto.SaveReq;
import com.kyowon.sms.wells.web.closing.expense.service.WdcdRequestCleaningSuppliesMgtService;
import com.kyowon.sms.wells.web.closing.zcommon.constants.DcClosingConst;
import com.sds.sflex.system.config.response.SaveResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(tags = "[WDCD] 청소 용품비 관리")
@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping(DcClosingConst.COMMON_URL_V1 + "/expense/cleaning-cost/교통지역총괄단장조직CD")
public class WdcdRequestCleaningSuppliesMgtController {

    private final WdcdRequestCleaningSuppliesMgtService service;

    @ApiOperation(value = "청소 용품비 관리", notes = "빌딩 코드 조회")
    @GetMapping("/code")
    public List<CodeRes> getBuilDingCd(CodeReq req) {
        return service.getBuilDingCd(req);
    }

    @ApiOperation(value = "청소 용품비 관리", notes = "청소 용품비 관리 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "clingCostAdjRcpNo", value = "청소비정산접수번호", paramType = "query"),
    })
    @GetMapping("/{clingCostAdjRcpNo}")
    public FindRes getRequestCleaningSupplies(@PathVariable String clingCostAdjRcpNo) {
        return service.getRequestCleaningSupplies(clingCostAdjRcpNo);
    }

    @ApiOperation(value = "청소 용품비 관리", notes = "용품비 등록")
    @PostMapping
    public SaveResponse saveRequestCleaningSupplies(@Valid @RequestBody SaveReq req) throws Exception {
        return SaveResponse.builder().processCount(service.saveRequestCleaningSupplies(req)).build();
    }
}

