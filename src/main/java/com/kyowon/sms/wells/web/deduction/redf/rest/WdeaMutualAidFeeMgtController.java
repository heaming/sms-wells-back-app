package com.kyowon.sms.wells.web.deduction.redf.rest;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.common.web.deduction.zcommon.constant.DeDeductionConst;
import com.kyowon.sms.wells.web.deduction.redf.dto.WwdeaMutualAidFeeMgtDto.SearchMutualAidFeeReq;
import com.kyowon.sms.wells.web.deduction.redf.dto.WwdeaMutualAidFeeMgtDto.SearchTotalMutualAidFeeForExcelDownloadRes;
import com.kyowon.sms.wells.web.deduction.redf.dto.WwdeaMutualAidFeeMgtDto.SearchTotalMutualAidFeeRes;
import com.kyowon.sms.wells.web.deduction.redf.service.WwdeaMutualAidFeeMgtService;
import com.sds.sflex.system.config.datasource.PageInfo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = "[WDEA] 상조 되물림생성")
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(DeDeductionConst.REST_URL_V1 + "/redf/mutual-aid")
public class WdeaMutualAidFeeMgtController {
    private final WwdeaMutualAidFeeMgtService service;

    @ApiOperation(value = "상조 되물림생성 조회", notes = "상조 되물림생성을 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "prtnrNo", value = "파트너번호", paramType = "query", required = true),
        @ApiImplicitParam(name = "baseYm", value = "발생년월", paramType = "query", required = true),
        @ApiImplicitParam(name = "dvCd", value = "구분코드", paramType = "query", required = true),
        @ApiImplicitParam(name = "redfAdsbTpCd", value = "처리유형", paramType = "query", required = true),
    })
    @GetMapping("/paging")
    public SearchTotalMutualAidFeeRes getMutualAidFeePages(
        SearchMutualAidFeeReq dto,
        @Valid
        PageInfo pageInfo
    ) {
        return service.getMutualAidFees(dto, pageInfo);
    }

    @ApiOperation(value = "상조 되물림생성 조회 엑셀다운로드", notes = "상조 되물림생성을 조회하고 엑셀다운로드 한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "prtnrNo", value = "파트너번호", paramType = "query", required = true),
        @ApiImplicitParam(name = "baseYm", value = "발생년월", paramType = "query", required = true),
        @ApiImplicitParam(name = "dvCd", value = "구분코드", paramType = "query", required = true),
        @ApiImplicitParam(name = "redfAdsbTpCd", value = "처리유형", paramType = "query", required = true),
    })
    @GetMapping("/excel-download")
    public SearchTotalMutualAidFeeForExcelDownloadRes getMutualAidFeeForExcelDownload(SearchMutualAidFeeReq dto)
        throws Exception {

        return service.getMutualAidFeeForExcelDownload(dto);

    }

}
