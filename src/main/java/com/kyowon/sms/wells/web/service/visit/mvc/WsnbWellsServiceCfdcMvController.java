package com.kyowon.sms.wells.web.service.visit.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.kyowon.sflex.common.report.dto.ReportDto;
import com.kyowon.sms.wells.web.service.visit.service.WsnbWellsServiceCfdcService;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/anonymous/sms/wells/service/wells-service-cfdc")
@Api(tags = "[WSNB] 웰스 서비스 확인서 MVC")
@RequiredArgsConstructor
@Validated
public class WsnbWellsServiceCfdcMvController {
    private final WsnbWellsServiceCfdcService service;

    @GetMapping("/report/{cstSvAsnNo}")
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
        ReportDto.FindEntryReq dto
    ) {
        return service.openReport(cstSvAsnNo, dto.custBday());
    }
}
