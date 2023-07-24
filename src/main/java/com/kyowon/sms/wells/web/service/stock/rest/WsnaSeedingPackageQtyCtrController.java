package com.kyowon.sms.wells.web.service.stock.rest;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaSeedingPackageQtyCtrDto.SearchReq;
import static com.kyowon.sms.wells.web.service.stock.dto.WsnaSeedingPackageQtyCtrDto.SearchRes;

import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.service.stock.service.WsnaSeedingPackageQtyCtrService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

/**
 * <pre>
 * W-SV-U-0298M01 모종 패키지 수량 조정 관리 Controller
 * </pre>
 *
 * @author SaeRomI.Kim
 * @since 2023-07-24
 */

@Api(tags = "[WSNA] 모종패키지 수량 조정 관리")
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(SnServiceConst.REST_URL_V1 + "/seeding-package-qty-ctrs")
public class WsnaSeedingPackageQtyCtrController {

    private final WsnaSeedingPackageQtyCtrService service;

    @GetMapping("/exclusion-qtys")
    @ApiOperation(value = "모종패키지 제외수량 조회", notes = "모종패키지 제외수량을 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "ostrDt", value = "출고일자", paramType = "query", example = "20230627", required = true)
    })
    public List<SearchRes> getSeedingPackageQtyCtrExcdQtys(@Valid
    SearchReq dto) {
        return this.service.getSeedingPackageQtyCtrExcdQtys(dto);
    }

    @GetMapping("/addition-qtys")
    @ApiOperation(value = "모종패키지 추가수량 조회", notes = "모종패키지 추가수량을 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "ostrDt", value = "출고일자", paramType = "query", example = "20230627", required = true)
    })
    public List<SearchRes> getSeedingPackageQtyCtrSpmtQtys(@Valid
    SearchReq dto) {
        return this.service.getSeedingPackageQtyCtrSpmtQtys(dto);
    }
}
