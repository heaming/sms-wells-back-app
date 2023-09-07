package com.kyowon.sms.wells.web.service.stock.rest;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaSeedingPackageCtrQtyRegDto.*;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.kyowon.sms.wells.web.service.stock.service.WsnaSeedingPackageCtrQtyRegService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;
import com.sds.sflex.system.config.response.SaveResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

/**
 * <pre>
 * W-SV-U-0298P01 모종패키지 조정 수량 등록 Controller
 * </pre>
 *
 * @author SaeRomI.Kim
 * @since 2023-07-25
 */

@Api(tags = "[WSNA] 모종패키지 조정 수량 등록")
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(SnServiceConst.REST_URL_V1 + "/seeding-package-ctr-qtys-reg")
public class WsnaSeedingPackageCtrQtyRegController {

    private final WsnaSeedingPackageCtrQtyRegService service;

    @GetMapping
    @ApiOperation(value = "모종패키지 조정 수량 조회", notes = "모종패키지 조정 수량 데이터를 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "dgGgLctCd", value = "서비스센터", paramType = "query", example = "03", required = true),
        @ApiImplicitParam(name = "ostrDt", value = "출고일자", paramType = "query", example = "20230627", required = true)
    })
    public List<SearchRes> getSeedingPackageCtrQtys(@Valid
    SearchReq dto) {
        return this.service.getSeedingPackageCtrQtys(dto);
    }

    @PostMapping
    @ApiOperation(value = "모종패키지 조정 수량 저장", notes = "모종패키지 조정 수량 데이터를 저장한다.")
    public SaveResponse saveSeedingPackageCtrQtys(
        @RequestBody
        @Valid
        @NotEmpty
        List<SaveReq> dtos
    ) {
        return SaveResponse.builder().processCount(this.service.saveSeedingPackageCtrQtys(dtos)).build();
    }

}
