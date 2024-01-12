package com.kyowon.sms.wells.web.service.allocate.rest;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.*;

import com.kyowon.sms.wells.web.service.allocate.dto.WsncRegularBfsvcOjDto;
import com.kyowon.sms.wells.web.service.allocate.dvo.WsncRegularBfsvcOjDvo;
import com.kyowon.sms.wells.web.service.allocate.service.WsncRegularBfsvcOjService;
import com.sds.sflex.system.config.constant.CommConst;
import com.sds.sflex.system.config.response.SaveResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * <pre>
 * [WSNC] 정기 B/S 대상 선정
 * </pre>
 *
 * @author  juno.cha
 * @since 2022-12-29
 */
@Api(tags = "[WSNC] 정기 B/S 대상 선정")
@RequiredArgsConstructor
@RestController
@RequestMapping(CommConst.REST_URL_V1 + "/sms/wells/service/regular-bs-object")
@Slf4j
public class WsncRegularBfsvcOjController {

    private final WsncRegularBfsvcOjService service;

    @ApiOperation(value = "정기 B/S 대상 선정 - 생성", notes = "정기 B/S 대상 선정")
    @PostMapping
    public SaveResponse createRegularBfsvcOj(
        @Valid
        @RequestBody
        WsncRegularBfsvcOjDto.CreateReq dto
    ) throws Exception {
        log.info(
            "[WsncRegularBfsvcOjController.createRegularBfsvcOj] WsncRegularBfsvcOjJob-Create batch Job execute."
                + dto.asnOjYm() + " / " + dto.createTarget()
        );

        //TODO Job Execution
        return SaveResponse.builder()
            .processCount(service.createRegularBfsvcOj(dto))
            .build();
    }

    @ApiOperation(value = "정기 B/S 대상 선정 - 조회", notes = "조회조건에 따른 정기 B/S 대상 선정 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "asnOjYm", value = "배정년월", paramType = "query", required = true),
    })
    @GetMapping
    public WsncRegularBfsvcOjDvo getRegularBfsvcOjs(WsncRegularBfsvcOjDto.SearchReq dto) throws Exception {
        return service.getRegularBfsvcOjs(dto);
    }
}
