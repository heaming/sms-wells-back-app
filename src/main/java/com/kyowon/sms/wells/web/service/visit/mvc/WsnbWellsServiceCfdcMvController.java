package com.kyowon.sms.wells.web.service.visit.mvc;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.kyowon.sflex.common.report.dto.ReportDto;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbWellsServiceCfdcDto;
import com.kyowon.sms.wells.web.service.visit.service.WsnbWellsServiceCfdcService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/anonymous/sms/wells/service/wells-service-cfdc")
@Api(tags = "[WSNB] 웰스 서비스 확인서 MVC")
@RequiredArgsConstructor
@Validated
public class WsnbWellsServiceCfdcMvController {
    private final WsnbWellsServiceCfdcService service;

    @GetMapping("/report/{cstSvAsnNo}/auth")
    public ModelAndView openReportAuthEntry(
        @PathVariable
        String cstSvAsnNo
    ) {
        return service.openReportAuthEntry(cstSvAsnNo);
    }

    @PostMapping("/report/{cstSvAsnNo}")
    public ModelAndView openReport(
        @PathVariable
        String cstSvAsnNo,
        @RequestBody
        ReportDto.FindEntryReq dto
    ) {
        return service.openReport(cstSvAsnNo, dto.custBday());
    }

    @ApiOperation(value = "OZ리포트 조회", notes = "OZ리포트 조회")
    @GetMapping("/oz")
    public Map<String, Object> getOzReport(WsnbWellsServiceCfdcDto.FindOzReq dto) {
        return service.getOzReport(dto);
    }
}
