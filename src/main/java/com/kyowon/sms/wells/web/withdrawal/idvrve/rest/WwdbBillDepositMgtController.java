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

    /**
     * 어음입금 등록 메인페이지 조회 / 페이징
     * @param dto 어음입금 등록 메인 DTO
     * @param pageInfo 페이징
     * @return PagingResult<SearchRes>
     */
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

    /**
     * 어음입금 등록 메인페이지 엑셀 다운로드
     * @param dto 어음입금 등록 메인 DTO
     * @return List<SearchRes>
     */
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

    /**
     * 전자어음 입금대상 조회 / 페이징 ( 현재 사용 X - 페이징 없어짐 )
     * @param dto 전자어음 입금 대상 DTO
     * @param pageInfo 페이징
     * @return PagingResult<SearchDetailRes>
     */
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

    /**
     * 전자어음 입금대상 조회 / 엑셀 다운로드
     * @param dto 전자어음 입금 대상 DTO
     * @return List<SearchDetailRes>
     */
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

    /**
     * 통합입금번호 PK 채번
     * @return WwdbBillDepositMgtDto.SearchItgNoRes
     */
    @GetMapping("/electronic")
    public WwdbBillDepositMgtDto.SearchItgNoRes getRegistrationPk() {
        log.info("============");
        WwdbBillDepositMgtDto.SearchItgNoRes pk = service.getRegistrationPk();
        log.info("============");
        return pk;
    }

    /**
     * 전자어음 신규 등록 저장
     * @param dtos 전자어음 등록 DTO
     * @return SaveResponse
     * @throws Exception
     */
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

    /**
     * 전자어음 상세 조회 / 페이징 ( 페이징 제거로 현재 사용 x )
     * @param dto 전자어음 상세 조회 DTO
     * @return List<SearchElectronicRes>
     */
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "itgDpNo", value = "통합입금번호", paramType = "query", required = false),
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query", required = false),
    })
    @GetMapping("/electronic-detail")
    public List<SearchElectronicRes> getRegistrationElectronicDetailPages(
        SearchElectronicReq dto
    ) {
        log.info("===========");
        log.info(dto.toString());
        log.info("===========");

        return service.getRegistrationElectronicDetailPages(dto);
    }

    /**
     * 전자어음 상세 조회 / 엑셀 다운로드
     * @param dto 전자어음 상세 조회 DTO
     * @return List<SearchElectronicRes>
     */
    @ApiImplicitParams(value = {
    })
    @GetMapping("/electronic-detail/excel-download")
    public List<SearchElectronicRes> getRegistrationElectronicDetailExcels(
        SearchElectronicReq dto
    ) {

        return service.getRegistrationElectronicDetailExcels(dto);
    }

    /**
     * 입금전표, 대체전표 생성
     * @param dto 입금전표, 대체전표 생성 DTO
     * @return SaveResponse
     * @throws Exception
     */
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
