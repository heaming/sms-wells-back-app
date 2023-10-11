package com.kyowon.sms.wells.web.service.stock.rest;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaItemBaseInformationDto.*;

import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.service.stock.dvo.WsnaItemBaseInformationDvo;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaItemBaseInformationReturnDvo;
import com.kyowon.sms.wells.web.service.stock.service.WsnaItemBaseInformationService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

/**
 * <pre>
 * W-SV-U-0173P01 품목기본정보 팝업 Controller
 * </pre>
 *
 * @author songTaeSung
 * @since 2023.02.20
 */
@Api(tags = "[WSNA] 품목기본정보")
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(SnServiceConst.REST_URL_V1 + "/item-base-informations")
public class WsnaItemBaseInformationController {

    private final WsnaItemBaseInformationService service;

    @ApiOperation(value = "품목기본정보 조회", notes = "")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "", value = "", paramType = "query", required = true),
    })
    @GetMapping
    public List<WsnaItemBaseInformationReturnDvo> getItemBaseInformations(
        @Valid
        SearchReq dto
    ) {
        return service.getItemBaseInformations(dto);
    }

    @GetMapping("/out-of")
    public List<WsnaItemBaseInformationDvo> getItemBaseInformationsOutOf(
        SearchReq dto
    ) {
        return service.getItemBaseInformationsOutOf(dto);
    }

    @GetMapping("/aplclists")
    public List<SearchAplcRes> getItemBaseInformationAplcLists(
        SearchAplcReq dto
    ) {
        return service.getItemBaseInformationAplcLists(dto);
    }

    @ApiOperation(value = "품목기본정보 창고구분, 창고상세구분 조회", notes = "부모창에서 넘어온 파라미터로 창고구분, 창고상세구분 조회")
    @GetMapping("/checked-code")
    public List<SearchWareRes> getItemBaseInformationWareDvCds(
        SearchReq dto
    ) {
        return service.getItemBaseInformationWareDvCds(dto);
    }

}
