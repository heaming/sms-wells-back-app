package com.kyowon.sms.wells.web.closing.expense.rest;

import com.kyowon.sms.wells.web.closing.expense.dto.WdcdCleanerReqeustMgtDto.FindCodeReq;
import com.kyowon.sms.wells.web.closing.expense.dto.WdcdCleanerReqeustMgtDto.FindCodeRes;
import com.kyowon.sms.wells.web.closing.expense.dto.WdcdCleanerReqeustMgtDto.FindRes;
import com.kyowon.sms.wells.web.closing.expense.dto.WdcdCleanerReqeustMgtDto.SaveReq;
import com.kyowon.sms.wells.web.closing.expense.service.WdcdCleanerReqeustMgtService;
import com.kyowon.sms.wells.web.closing.zcommon.constants.DcClosingConst;
import com.sds.sflex.system.config.response.SaveResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(tags = "[WDCD] 청소원 등록(신규변경)")
@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping(DcClosingConst.COMMON_URL_V1 + "/expense/cleaners/cleaners-reqeust-change")
public class WdcdCleanerReqeustMgtController {

    private final WdcdCleanerReqeustMgtService service;

    @ApiOperation(value = "청소원 등록(신규변경)", notes = "빌딩 코드 조회")
    @GetMapping("/code")
    public List<FindCodeRes> getBuilDingCd(FindCodeReq req) {
        return service.getBuilDingCd(req);
    }

    @ApiOperation(value = "청소원 등록(신규변경)", notes = "청소원 등록 및 수정")
    @PostMapping
    public SaveResponse saveCleanerReqeust(@Valid @RequestBody SaveReq req) throws Exception {
        return SaveResponse.builder().processCount(service.saveCleanerReqeust(req)).build();
    }

    @ApiOperation(value = "청소원 등록(신규변경)", notes = "청소원 등록 조회")
    @GetMapping("/{clinrRgno}")
    public FindRes getCleanerReqeust(@PathVariable String clinrRgno) {
        return service.getCleanerReqeust(clinrRgno);
    }
}
