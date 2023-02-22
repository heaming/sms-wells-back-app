package com.kyowon.sms.wells.closing.expense.rest;

import com.kyowon.sms.wells.closing.expense.dto.WwdcdOperatingCostMgtSecuritiesDto.SearchReq;
import com.kyowon.sms.wells.closing.expense.dto.WwdcdOperatingCostMgtSecuritiesDto.SearchRes;
import com.kyowon.sms.wells.closing.expense.service.WwdcdOperatingCostMgtSecuritiesService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Api(tags = "운영비 등록 관리 - 유가증권")
@RequiredArgsConstructor
@RestController
@Slf4j
//@RequestMapping(CtContractConst.REST_URL_V1 + "/operationgCost")
@RequestMapping("/api/v1/sms/wells/expense/operating-cost/marketable-securities")
public class WwdcdOperatingCostMgtSecuritiesController {

    private final WwdcdOperatingCostMgtSecuritiesService service;

    public List<SearchRes> getAdjustObject(@Valid
                                           SearchReq req) {

        List<SearchRes> res = new ArrayList<SearchRes>();
        return res;
    }

    @GetMapping("/excel-download")
    public List<SearchRes> getAdjustObjectExcelDownload(SearchReq dto) {
        return service.getAdjustObjectExcelDownload(dto);
    }
}
