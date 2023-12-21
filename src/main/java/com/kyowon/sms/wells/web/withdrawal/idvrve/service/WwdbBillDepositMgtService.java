package com.kyowon.sms.wells.web.withdrawal.idvrve.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.kyowon.sms.common.web.closing.payment.service.ZdcaNumberingSlpnoService;
import com.kyowon.sms.common.web.withdrawal.idvrve.dvo.ZwdbEtcDepositProcessingDvo;
import com.kyowon.sms.common.web.withdrawal.idvrve.dvo.ZwdbIntegrationDepositDvo;
import com.kyowon.sms.common.web.withdrawal.idvrve.mapper.ZwdbCorporationDepositMapper;
import com.kyowon.sms.common.web.withdrawal.idvrve.mapper.ZwdbEtcDepositMapper;
import com.kyowon.sms.common.web.withdrawal.idvrve.mapper.ZwdbIntegrationDepositMapper;
import com.kyowon.sms.common.web.withdrawal.idvrve.service.ZwdbDepositComparisonComfirmationService;
import com.kyowon.sms.common.web.withdrawal.zcommon.dvo.ZwdzWithdrawalReceiveAskDvo;
import com.kyowon.sms.common.web.withdrawal.zcommon.service.ZwdzWithdrawalService;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbBillDepositMgtDto;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dvo.WwdbBillDepositMgtDvo;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dvo.WwdbBillDepositMgtSubDvo;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dvo.WwdbBillDepositContractDvo;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dvo.WwdbBillDepositSlipProcessingDvo;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dvo.WwdbBillDepositExcelUploadDvo;
import com.sds.sflex.common.common.dto.ExcelUploadDto;
import com.sds.sflex.common.common.dvo.ExcelMetaDvo;
import com.sds.sflex.common.common.dvo.ExcelUploadErrorDvo;
import com.sds.sflex.common.common.service.ExcelReadService;
import com.sds.sflex.common.utils.DateUtil;
import com.sds.sflex.common.utils.StringUtil;
import com.sds.sflex.system.config.context.SFLEXContextHolder;
import com.sds.sflex.system.config.core.dvo.UserSessionDvo;
import com.sds.sflex.system.config.core.service.MessageResourceService;
import com.sds.sflex.system.config.validation.BizAssert;
import org.apache.velocity.runtime.directive.Foreach;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.withdrawal.idvrve.converter.WwdbBillDepositMgtConvert;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbBillDepositMgtDto.SaveMainDtlReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbBillDepositMgtDto.SaveMainReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbBillDepositMgtDto.SaveReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbBillDepositMgtDto.SearchDetailReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbBillDepositMgtDto.SearchDetailRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbBillDepositMgtDto.SearchElectronicReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbBillDepositMgtDto.SearchElectronicRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbBillDepositMgtDto.SearchReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbBillDepositMgtDto.SearchRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.mapper.WwdbBillDepositMgtMapper;
import com.sds.sflex.system.config.constant.CommConst;
import com.sds.sflex.common.common.dto.ExcelUploadDto.UploadRes;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import com.sds.sflex.system.config.exception.BizException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;

/**
 * <pre>
 * 어음입금 등록 서비스 ( 메인 + 신규등록 팝업 )
 * </pre>
 *
 * @author heungjun.lee
 * @since 2023-10-24
 */

@Service
@RequiredArgsConstructor
@Slf4j
public class WwdbBillDepositMgtService {

    private final WwdbBillDepositMgtMapper mapper;

    private final WwdbBillDepositMgtConvert convert;

    private final ZwdbIntegrationDepositMapper zwdbIntegrationDepositMapper;

    private final ZwdzWithdrawalService zwdzWithdrawalService;

    private final ZwdbCorporationDepositMapper zwdbCorporationDepositMapper;

    private final ZwdbEtcDepositMapper etcDepositMapper;

    private final ZdcaNumberingSlpnoService slpnoService;

    private final ZwdbDepositComparisonComfirmationService depositComparisonComfirmationService;

    private final MessageResourceService messageResourceService;

    private final ExcelReadService excelReadService;

    private final static String MSG_ALT_INVALID_UPLOAD_DATA = "MSG_ALT_INVALID_UPLOAD_DATA";

    /**
     * 어음입금 등록 메인페이지 조회 / 페이징
     * @param dto 어음입금 등록 메인 DTO
     * @param pageInfo 페이징
     * @return PagingResult<SearchRes>
     */
    @Transactional
    public PagingResult<SearchRes> getRegistrationPages(SearchReq dto, PageInfo pageInfo) {
        return mapper.selectRegistrationPages(dto, pageInfo);
    }

    /**
     * 어음입금 등록 메인페이지 엑셀 다운로드
     * @param dto 어음입금 등록 메인 DTO
     * @return List<SearchRes>
     */
    @Transactional
    public List<SearchRes> getRegistrationExcels(SearchReq dto) {
        return mapper.selectRegistrationPages(dto);
    }

    /**
     * 전자어음 입금대상 조회 / 페이징 ( 현재 사용 X - 페이징 없어짐 )
     * @param dto 전자어음 입금 대상 DTO
     * @param pageInfo 페이징
     * @return PagingResult<SearchDetailRes>
     */
    @Transactional
    public PagingResult<SearchDetailRes> getRegistrationElectronicPages(SearchDetailReq dto, PageInfo pageInfo) {
        return mapper.selectRegistrationElectronics(dto, pageInfo);
    }

    /**
     * 전자어음 입금대상 조회 / 엑셀 다운로드
     * @param dto 전자어음 입금 대상 DTO
     * @return
     */
    @Transactional
    public List<SearchDetailRes> getRegistrationElectronicExcels(SearchDetailReq dto) {
        return mapper.selectRegistrationElectronics(dto);
    }

    /**
     * 통합입금번호 PK 채번
     * @return WwdbBillDepositMgtDto.SearchItgNoRes
     */
    @Transactional
    public WwdbBillDepositMgtDto.SearchItgNoRes getRegistrationPk() {
        return mapper.selectRegistrationPk();
    }

    /**
     * 전자어음 신규 등록 저장 ( 팝업 )
     * @param dtos 전자어음 신규 등록 DTO
     * @return SaveResponse
     * @throws Exception
     */
    @Transactional
    public int saveRegistrationElectronics(SaveReq dtos) throws Exception {

        int processCount = 0;

        SaveMainReq mainDto = dtos.saveMainReq();
        List<SaveMainDtlReq> subDto = dtos.SaveMainDtlReq();

        log.info("==============service");
        log.info(mainDto.toString());
        log.info("==============service");

        //오늘 날짜
        String sysDate = DateUtil.getNowString();
        String sysDateYmd = DateUtil.getNowDayString();

        WwdbBillDepositMgtDvo dvo = convert.mapSaveWwdbRegistrationListDvo(mainDto);

        switch (mainDto.state()) {
            case CommConst.ROW_STATE_CREATED -> {
                processCount += mapper.insertRegistrationMainElectronics(dvo);
            }
            case CommConst.ROW_STATE_UPDATED -> {
                processCount += mapper.updateRegistrationMainElectronics(dvo);
            }
            default -> throw new BizException("MSG_ALT_UNHANDLE_ROWSTATE");

        }

        int sumAmt = 0;

        if (subDto.size() > 0) {
            if (CommConst.ROW_STATE_CREATED.equals(subDto.get(0).rowState()))
                processCount += mapper.deleteRegistrationSubElectronics(dvo);

            for (SaveMainDtlReq dto : subDto) {
                WwdbBillDepositMgtSubDvo subDvo = convert.mapSaveWwdbRegistrationSubListDvo(dto);
                sumAmt += Integer.parseInt(dto.billDpAmt());
                switch (dto.rowState()) {
                    case CommConst.ROW_STATE_CREATED -> {
                        processCount += mapper.insertRegistrationSubElectronics(subDvo);
                    }
                    case CommConst.ROW_STATE_UPDATED -> {
                        processCount += mapper.updateRegistrationSubElectronics(subDvo);
                    }
                    default -> throw new BizException("MSG_ALT_UNHANDLE_ROWSTATE");
                }

            }
        }

        switch (mainDto.state()) {
            case CommConst.ROW_STATE_CREATED -> {
                //통합입금기본 데이터 생성
                ZwdbIntegrationDepositDvo depoDvo = new ZwdbIntegrationDepositDvo();
                depoDvo.setItgDpNo(dvo.getItgDpNo()); /*통합입금번호*/
                depoDvo.setKwGrpCoCd("2000"); /*교원그룹회사코드*/
                depoDvo.setRveCoCd("2000"); /*수납회사코드*/

                //        depoDvo.setCstNo(dvo.);    /*고객번호*/
                depoDvo.setDpDvCd("1"); /*입금구분코드*/
                depoDvo.setDpMesCd("03"); /*입금수단코드*/
                depoDvo.setDpTpCd("0301"); /*입금유형코드*/
                depoDvo.setDpDtm(sysDateYmd); /*입금일시*/
                depoDvo.setPerfDt(sysDate); /*실적일자*/
                //        depoDvo.setDprNm();    /*입금자명*/
                depoDvo.setDpAmt(Integer.toString(sumAmt)); /*입금금액*/

                depoDvo.setDpBlam(Integer.toString(sumAmt)); /*입금잔액*/

                depoDvo.setIncmdcYn("N"); /*소득공제여부*/

                zwdbIntegrationDepositMapper.insertIntegrationDeposit(depoDvo);
                zwdbIntegrationDepositMapper.insertIntegrationDepositHistory(depoDvo);
            }
        }

        return processCount;
    }

    /**
     * 전자어음 상세 조회 / 페이징 ( 페이징 제거로 현재 사용 x )
     * @param dto 전자어음 상세 조회 DTO
     * @return List<SearchElectronicRes>
     */
    @Transactional
    public List<SearchElectronicRes> getRegistrationElectronicDetailPages(
        SearchElectronicReq dto
    ) {
        return mapper.selectRegistrationElectronicDetails(dto);
    }

    /**
     * 전자어음 상세 조회 / 엑셀 다운로드
     * @param dto 전자어음 상세 조회 DTO
     * @return List<SearchElectronicRes>
     */
    @Transactional
    public List<SearchElectronicRes> getRegistrationElectronicDetailExcels(
        SearchElectronicReq dto
    ) {
        return mapper.selectRegistrationElectronicDetails(dto);
    }

    /**
     * 입금전표 생성
     * @param dto 입금 / 대체전표 생성 DTO
     * @return int processCount
     */
    @Transactional
    public int saveRegistrationElectronicDepositSlip(List<WwdbBillDepositMgtDto.SaveDepositSlip> dto) throws Exception {
        int processCount = 0;

        //오늘 날짜
        String sysDateYmd = DateUtil.getNowDayString();

        UserSessionDvo session = SFLEXContextHolder.getContext().getUserSession(); //세션정보

        int sumResult = 0;

        String year = sysDateYmd.substring(0, 4);
        String month = sysDateYmd.substring(4, 6);

        //전표 PK
        String zzsnum = slpnoService.getNumberingSlpno("FI", Integer.parseInt(year), Integer.parseInt(month));

        /*수납요청기본*/
        ZwdzWithdrawalReceiveAskDvo zwdzWithdrawalReceiveAskDvo = new ZwdzWithdrawalReceiveAskDvo();
        zwdzWithdrawalReceiveAskDvo.setKyowonGroupCompanyCd(session.getCompanyCode()); //KW_GRP_CO_CD	교원그룹회사코드
        zwdzWithdrawalReceiveAskDvo.setRveAkMthdCd("01"); //RVE_AK_MTHD_CD	수납요청방식코드 대면(01)
        zwdzWithdrawalReceiveAskDvo.setRveAkPhCd("20"); //RVE_AK_PH_CD	수납요청경로코드
        zwdzWithdrawalReceiveAskDvo.setRvePrtnrOgTpCd(session.getOgTpCd()); //RVE_AK_PRTNR_OG_TP_CD	수납요청파트너조직유형코드
        zwdzWithdrawalReceiveAskDvo.setRvePrtnrNo(session.getEmployeeIDNumber()); //RVE_AK_PRTNR_NO	수납요청파트너번호
        zwdzWithdrawalReceiveAskDvo.setReceiveAskAmount(dto.get(0).resultSum()); //RVE_AK_AMT	수납요청금액
        zwdzWithdrawalReceiveAskDvo.setReceiveAskDate(sysDateYmd); //RVE_RQDT	수납요청일자
        zwdzWithdrawalReceiveAskDvo.setReceiveAskStatusCode("02"); //RVE_AK_STAT_CD	수납요청상태코드
        zwdzWithdrawalReceiveAskDvo.setReceiveCompanyCode(session.getCompanyCode()); //RVE_CO_CD	수납회사코드

        /*수납요청기본 데이터 생성*/
        String receiveAskNumber = zwdzWithdrawalService.createReceiveAskBase(zwdzWithdrawalReceiveAskDvo);
        zwdzWithdrawalReceiveAskDvo.setReceiveAskNumber(receiveAskNumber);
        for (WwdbBillDepositMgtDto.SaveDepositSlip list : dto) {

            //통합입금 업데이트
            ZwdbEtcDepositProcessingDvo itgDvo = new ZwdbEtcDepositProcessingDvo();

            itgDvo.setItgDpNo(dto.get(0).itgDpNo());//통합입금번호
            itgDvo.setRveAkNo(receiveAskNumber); //수납요청번호
            itgDvo.setRveCd(session.getRveCd());
            processCount += etcDepositMapper.updateIntegrationDeposit(itgDvo);

            ZwdbIntegrationDepositDvo depoDvo = new ZwdbIntegrationDepositDvo();
            depoDvo.setItgDpNo(dto.get(0).itgDpNo());
            zwdbIntegrationDepositMapper.insertIntegrationDepositHistory(depoDvo);

            /*수납요청상세*/
            WwdbBillDepositContractDvo contractDvo = new WwdbBillDepositContractDvo();
            contractDvo.setItgDpNo(dto.get(0).itgDpNo()); //통합입금번호
            contractDvo.setRveAkNo(receiveAskNumber); //요청번호1
            contractDvo.setCntrNo(list.cntrNo()); //계약번호
            contractDvo.setCntrSn(list.cntrSn()); //일련번호

            processCount += mapper.insertBillDepositContracts(contractDvo);

            sumResult += Integer.parseInt(list.billDpAmt());

        }

        if (!StringUtils.isEmpty(dto.get(0).itgDpNo())) {

            //입금대사 서비스 호출
            depositComparisonComfirmationService.createDepositComparisonComfirmation(dto.get(0).itgDpNo(), null, "Y");

        }

        //전표발행

        //        String dpCprcnfDtm = zwdzWithdrawalReceiveAskDvo.getReceiveAskDate();

        WwdbBillDepositMgtDvo wwdbBillDepositMgtDvo = convert.mapSaveDepositSlipDvo(dto.get(0));
        wwdbBillDepositMgtDvo.setBillDpSapSlpno(zzsnum);

        processCount += mapper.updateSlipRegistration(wwdbBillDepositMgtDvo);

        WwdbBillDepositMgtDto.SearchSlipReq slipReq = new WwdbBillDepositMgtDto.SearchSlipReq(
            zzsnum, Integer.toString(sumResult), dto.get(0).itgDpNo()
        );

        List<WwdbBillDepositSlipProcessingDvo> slipProcessingDvo = mapper.selectSlipProcessings(slipReq);
        log.info("service==========================");
        log.info(slipProcessingDvo.toString());
        log.info("service==========================");
        for (WwdbBillDepositSlipProcessingDvo list : slipProcessingDvo) {
            processCount += mapper.insertSlipProcessings(list);
        }

        return processCount;

    }

    /**
     * 대체전표 생성
     * @param dto 입금 / 대체전표 생성 DTO
     * @return int processCount
     */
    @Transactional
    public int saveReplacementSlipProcessing(List<WwdbBillDepositMgtDto.SaveDepositSlip> dto) throws Exception {
        int processCount = 0;

        //오늘 날짜
        String sysDateYmd = DateUtil.getNowDayString();

        int sumResult = 0;

        for (WwdbBillDepositMgtDto.SaveDepositSlip list : dto) {
            sumResult += Integer.parseInt(list.billDpAmt());
        }

        //대체 전표발행
        String year = sysDateYmd.substring(0, 4);
        String month = sysDateYmd.substring(4, 6);

        String zzsnum = slpnoService.getNumberingSlpno("FE", Integer.parseInt(year), Integer.parseInt(month));
        WwdbBillDepositMgtDto.SearchSlipReq slipReq = new WwdbBillDepositMgtDto.SearchSlipReq(
            zzsnum, Integer.toString(sumResult), dto.get(0).itgDpNo()
        );

        List<WwdbBillDepositSlipProcessingDvo> slipProcessingDvo = mapper.selectReplacementSlipProcessing(slipReq);

        for (WwdbBillDepositSlipProcessingDvo list : slipProcessingDvo) {
            processCount += mapper.insertSlipProcessings(list);
        }

        WwdbBillDepositMgtDvo wwdbBillDepositMgtDvo = convert.mapSaveDepositSlipDvo(dto.get(0));
        wwdbBillDepositMgtDvo.setBillRplcSapSlpno(zzsnum);

        processCount += mapper.updateSlipRegistration(wwdbBillDepositMgtDvo);

        return processCount;

    }

    /**
     * 어음신규 등록 전자어음 입금대상 엑셀 업로드
     * @param file 업로드 파일
     * @return 업로드 결과정보
     * @throws Exception 업로드된 파일 정보 Read, 파싱시 발생할 수 있는  Exception
     */
    @Transactional
    public UploadRes saveRegistrationElectronicExcelUpload(MultipartFile file) throws Exception {
        // 업로드 엑셀 헤더 설정
        Map<String, String> headerTitle = new LinkedHashMap<>();
        headerTitle.put("cntr", messageResourceService.getMessage("MSG_TXT_CNTR_DTL_NO")); // 계약상세번호
        headerTitle.put("sellAmt", messageResourceService.getMessage("MSG_TXT_AMT_WON")); // 계약상세번호

        // File 데이터 Read
        List<WwdbBillDepositExcelUploadDvo> readExcel = excelReadService
            .readExcel(file, new ExcelMetaDvo(1, headerTitle), WwdbBillDepositExcelUploadDvo.class);

        // Validation Check
        List<ExcelUploadErrorDvo> errorDvos = new ArrayList<>();

        List<WwdbBillDepositExcelUploadDvo> returnDvos = new ArrayList<WwdbBillDepositExcelUploadDvo>();

        //        int row = 1;
        for (WwdbBillDepositExcelUploadDvo dvo : readExcel) {

            if (StringUtil.isEmpty(dvo.getCntr())) { //계약상세번호 칸이 비어있을경우
                dvo.setErrorCode("2");
                continue;
            } else {
                String cntr = dvo.getCntr().replace("-", "");
                if (cntr.length() > 17) { // 계약일련번호 초과 시 오류
                    dvo.setErrorCode("2");
                    continue;
                } else if (cntr.length() < 13) { // 계약일련번호까지 안들어오면 오류
                    dvo.setErrorCode("2");
                    continue;
                }

                //                2. 계약상세번호가 DB에 존재하는지 체크

                String cntrNo = cntr.substring(0, 12);
                String cntrSn = cntr.substring(12);
                dvo.setCntr(cntr);
                dvo.setCntrNo(cntrNo);
                dvo.setCntrSn(cntrSn);

                int humanChk = mapper.selectValidationCntr(dvo); // 계약 상세 번호 존재여부

                if (humanChk < 1) { // 계약 번호가 없을 경우
                    dvo.setErrorCode("2");
                    continue;
                } else {
                    dvo.setErrorCode("1");
                }
            }

            if (StringUtil.isEmpty(dvo.getSellAmt())) { // 금액 비어있을 경우
                dvo.setSellAmt("0"); // 0 설정
                //                BizAssert.hasText(
                //                    dvo.getSellAmt().toString(), MSG_ALT_INVALID_UPLOAD_DATA,
                //                    new String[] {String.valueOf(row), headerTitle.get("sellAmt"), dvo.getSellAmt().toString()}
                //                );
                continue;
            }
            returnDvos.add(dvo);
            //            row++;
        }
        // Upload 결과 리턴
        return UploadRes.builder()
            .status(returnDvos.isEmpty() ? "S" : "E")
            //            .errorInfo(returnDvos)
            .excelData(readExcel)
            .build();
    }

    /**
     * 엑셀 업로드 유효성 검사 - 현재 사용 X
     * @param header 헤더
     * @param dvos
     * @return
     */
    public List<ExcelUploadErrorDvo> validateExcelDatas(
        Map<String, String> header, List<WwdbBillDepositExcelUploadDvo> dvos
    ) {
        List<ExcelUploadErrorDvo> excelUploadErrorDvos = new ArrayList<>();
        int row = 1;
        for (int i = 0; i < dvos.size(); i++) {
            WwdbBillDepositExcelUploadDvo dvo = dvos.get(i);

            // 데이터 검증
            if (StringUtil.isBlank(dvo.getCntr())) { // 계약상세번호
                ExcelUploadErrorDvo errorDvo = new ExcelUploadErrorDvo();
                errorDvo.setErrorRow(row);
                errorDvo.setHeaderName(header.get("cntr"));
                BizAssert.hasText(
                    dvo.getCntr(), MSG_ALT_INVALID_UPLOAD_DATA,
                    new String[] {String.valueOf(row), header.get("cntr"), dvo.getCntr()}
                );
                excelUploadErrorDvos.add(errorDvo);
            }

            if (StringUtil.isBlank(dvo.getSellAmt().toString())) { // 금액(원)
                BizAssert.hasText(
                    dvo.getSellAmt().toString(), MSG_ALT_INVALID_UPLOAD_DATA,
                    new String[] {String.valueOf(row), header.get("sellAmt"), dvo.getSellAmt().toString()}
                );
            }
        }

        // 1.필수값 체크(계약상세번호, 금액 null check)
        //        int row = 1;
        //        for (WwdbBillDepositExcelUploadDvo dvo : readExcel) {
        //            Map<String, String> headerTitleValidation = Map.of(
        //                "cntr", dvo.getCntr(),
        //                "sellAmt", dvo.getSellAmt()
        //            );
        //
        //            for (String key : headerTitleValidation.keySet()) {
        //                if(StringUtil.isBlank(headerTitleValidation.get(key))) {
        //                    ExcelUploadErrorDvo errorDvo = new ExcelUploadErrorDvo();
        //                    errorDvo.setErrorRow(row);
        //                    errorDvo.setHeaderName(headerTitle.get(key));
        //                    errorDvo.setErrorData(
        //                        messageResourceService.getMessage(
        //                        "MSG_ALT_NO_EXPORT_DATA" // 엑셀 파일로 내보낼 데이터가 없습니다.
        //                        )
        //                    );
        //                    excelUploadErrorDvos.add(errorDvo);
        //                }
        //            }
        //            row++;
        //        }
        //
        //        for(WwdbBillDepositExcelUploadDvo uploadDvo : readExcel) {
        //            String cntrNo = uploadDvo.getCntr().
        //
        //            // 2. 계약상세번호가 DB에 존재하는지 체크
        //            int row2 = 1;
        //
        //            for (WwdbBillDepositExcelUploadDvo dvo : readExcel) {
        //                int existCntr = mapper.selectValidationCntr(dvo.getCntr());
        //
        //            int(existCntr == 0) {
        //
        //                }
        //            }
        //        }
        return excelUploadErrorDvos;
    }

}
