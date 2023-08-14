package com.kyowon.sms.wells.web.withdrawal.idvrve.rest;

import java.util.List;

import javax.validation.Valid;

import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbBillDepositMgtDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbBillDepositMgtDto.SaveReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbBillDepositMgtDto.SearchDetailReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbBillDepositMgtDto.SearchDetailRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbBillDepositMgtDto.SearchElectronicReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbBillDepositMgtDto.SearchElectronicRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbBillDepositMgtDto.SearchReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbBillDepositMgtDto.SearchRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbBillDepositMgtDto.SaveDepositSlip;
import com.kyowon.sms.wells.web.withdrawal.idvrve.service.WwdbBillDepositMgtService;
import com.kyowon.sms.wells.web.withdrawal.zcommon.constants.WdWithdrawalConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import com.sds.sflex.system.config.response.SaveResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Api(tags = "[수납입출금 - 개별수납] 어음입금 등록")
@RestController
@RequiredArgsConstructor
@RequestMapping(value = WdWithdrawalConst.REST_URL_IDVRVE + "/bill-deposits")
@Slf4j
public class WwdbBillDepositMgtController {

    private final WwdbBillDepositMgtService service;

    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "rcpStartDt", value = "시작일자", paramType = "query", required = false),
        @ApiImplicitParam(name = "rcpEndDt", value = "종료일자", paramType = "query", required = false),
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query", required = false),
        @ApiImplicitParam(name = "billExprDt", value = "만기일자", paramType = "query", required = false),
    })
    @GetMapping("/paging")
    public PagingResult<SearchRes> getRegistrationPages(SearchReq dto, PageInfo pageInfo) {
        return service.getRegistrationPages(dto, pageInfo);
    }

    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "rcpStartDt", value = "시작일자", paramType = "query", required = false),
        @ApiImplicitParam(name = "rcpEndDt", value = "종료일자", paramType = "query", required = false),
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query", required = false),
        @ApiImplicitParam(name = "billExprDt", value = "만기일자", paramType = "query", required = false),
    })
    @GetMapping("/excel-download")
    public List<SearchRes> getRegistrationExcels(SearchReq dto) {
        return service.getRegistrationExcels(dto);
    }

    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "bzrno", value = "사업자등록번호", paramType = "query", required = false),
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query", required = false),
        @ApiImplicitParam(name = "cntrSn", value = "일련번호", paramType = "query", required = false),
    })
    @GetMapping("/electronic/paging")
    public PagingResult<SearchDetailRes> getRegistrationElectronicPages(
        SearchDetailReq dto, PageInfo pageInfo
    ) {
        return service.getRegistrationElectronicPages(dto, pageInfo);
    }

    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "bzrno", value = "사업자등록번호", paramType = "query", required = false),
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query", required = false),
        @ApiImplicitParam(name = "cntrSn", value = "일련번호", paramType = "query", required = false),
    })
    @GetMapping("/electronic/execl-download")
    public List<SearchDetailRes> getRegistrationElectronicExcels(
        SearchDetailReq dto
    ) {
        return service.getRegistrationElectronicExcels(dto);
    }

    @GetMapping("/electronic")
    public WwdbBillDepositMgtDto.SearchItgNoRes getRegistrationPk() {
        log.info("============");
        WwdbBillDepositMgtDto.SearchItgNoRes pk = service.getRegistrationPk();
        log.info("============");
        return pk;
    }

    @PostMapping("/electronic")
    public SaveResponse saveRegistrationElectronics(
        @RequestBody
        @Valid
        SaveReq dtos
    ) throws Exception {
        log.info("===========");
        log.info(dtos.toString());
        log.info("===========");

        return SaveResponse.builder()
            .processCount(service.saveRegistrationElectronics(dtos))
            .build();
    }

    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "itgDpNo", value = "통합입금번호", paramType = "query", required = false),
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query", required = false),
    })
    @GetMapping("/electronic-detail/paging")
    public PagingResult<SearchElectronicRes> getRegistrationElectronicDetailPages(
        SearchElectronicReq dto, PageInfo pageInfo
    ) {
        log.info("===========");
        log.info(dto.toString());
        log.info("===========");

        return service.getRegistrationElectronicDetailPages(dto, pageInfo);
    }

    @ApiImplicitParams(value = {
    })
    @GetMapping("/electronic-detail/excel-download")
    public List<SearchElectronicRes> getRegistrationElectronicDetailExcels(
        SearchElectronicReq dto
    ) {

        return service.getRegistrationElectronicDetailExcels(dto);
    }

    @PostMapping("/deposit-processing")
    public SaveResponse saveRegistrationElectronicDepositSlip(
        @RequestBody
        @Valid
        List<SaveDepositSlip> dto
    ) throws Exception {

        //입금전표
        if ("deposit".equals(dto.get(0).sort())) {
            return SaveResponse.builder()
                .processCount(service.saveRegistrationElectronicDepositSlip(dto))
                .build();
        }
        //대체전표
        return SaveResponse.builder()
            .processCount(service.saveReplacementSlipProcessing(dto))
            .build();

    }

}
