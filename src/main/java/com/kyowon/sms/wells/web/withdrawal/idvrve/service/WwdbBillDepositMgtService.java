package com.kyowon.sms.wells.web.withdrawal.idvrve.service;

import java.util.List;

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
import com.kyowon.sms.wells.web.withdrawal.idvrve.dvo.WwdbBillDepositContractDvo;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dvo.WwdbBillDepositSlipProcessingDvo;
import com.sds.sflex.common.utils.DateUtil;
import com.sds.sflex.system.config.context.SFLEXContextHolder;
import com.sds.sflex.system.config.core.dvo.UserSessionDvo;
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
import com.kyowon.sms.wells.web.withdrawal.idvrve.dvo.WwdbBillDepositMgtDvo;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dvo.WwdbBillDepositMgtSubDvo;
import com.kyowon.sms.wells.web.withdrawal.idvrve.mapper.WwdbBillDepositMgtMapper;
import com.sds.sflex.system.config.constant.CommConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import com.sds.sflex.system.config.exception.BizException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.thymeleaf.util.StringUtils;

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

    @Transactional
    public PagingResult<SearchRes> getRegistrationPages(SearchReq dto, PageInfo pageInfo) {
        return mapper.selectRegistrationPages(dto, pageInfo);
    }

    @Transactional
    public List<SearchRes> getRegistrationExcels(SearchReq dto) {
        return mapper.selectRegistrationPages(dto);
    }

    @Transactional
    public PagingResult<SearchDetailRes> getRegistrationElectronicPages(SearchDetailReq dto, PageInfo pageInfo) {
        return mapper.selectRegistrationElectronics(dto, pageInfo);
    }

    @Transactional
    public List<SearchDetailRes> getRegistrationElectronicExcels(SearchDetailReq dto) {
        return mapper.selectRegistrationElectronics(dto);
    }

    @Transactional
    public WwdbBillDepositMgtDto.SearchItgNoRes getRegistrationPk() {
        return mapper.selectRegistrationPk();
    }

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
                depoDvo.setItgDpNo(dvo.getItgDpNo());    /*통합입금번호*/
                depoDvo.setKwGrpCoCd("2000");    /*교원그룹회사코드*/
                depoDvo.setRveCoCd("2000");    /*수납회사코드*/

                //        depoDvo.setCstNo(dvo.);    /*고객번호*/
                depoDvo.setDpDvCd("1");    /*입금구분코드*/
                depoDvo.setDpMesCd("03");    /*입금수단코드*/
                depoDvo.setDpTpCd("0301");    /*입금유형코드*/
                depoDvo.setDpDtm(sysDateYmd);    /*입금일시*/
                depoDvo.setPerfDt(sysDate);    /*실적일자*/
                //        depoDvo.setDprNm();    /*입금자명*/
                depoDvo.setDpAmt(Integer.toString(sumAmt));    /*입금금액*/

                depoDvo.setDpBlam(Integer.toString(sumAmt));    /*입금잔액*/

                depoDvo.setIncmdcYn("N");    /*소득공제여부*/


                zwdbIntegrationDepositMapper.insertIntegrationDeposit(depoDvo);
                zwdbIntegrationDepositMapper.insertIntegrationDepositHistory(depoDvo);
            }
        }

        return processCount;
    }

    @Transactional
    public List<SearchElectronicRes> getRegistrationElectronicDetailPages(
        SearchElectronicReq dto
    ) {
        return mapper.selectRegistrationElectronicDetails(dto);
    }

    @Transactional
    public List<SearchElectronicRes> getRegistrationElectronicDetailExcels(
        SearchElectronicReq dto
    ) {
        return mapper.selectRegistrationElectronicDetails(dto);
    }

    /**
     * 입금전표 생성
     *
     * @param dto
     * @return
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
            depositComparisonComfirmationService.createDepositComparisonComfirmation(dto.get(0).itgDpNo(), null);

        }


        //전표발행

//        String dpCprcnfDtm = zwdzWithdrawalReceiveAskDvo.getReceiveAskDate();

        WwdbBillDepositMgtDvo wwdbBillDepositMgtDvo = convert.mapSaveDepositSlipDvo(dto.get(0));
        wwdbBillDepositMgtDvo.setBillDpSapSlpno(zzsnum);

        processCount += mapper.updateSlipRegistration(wwdbBillDepositMgtDvo);

        WwdbBillDepositMgtDto.SearchSlipReq slipReq = new WwdbBillDepositMgtDto.SearchSlipReq(zzsnum, Integer.toString(sumResult), dto.get(0).itgDpNo());

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
     *
     * @param dto
     * @return
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
        WwdbBillDepositMgtDto.SearchSlipReq slipReq = new WwdbBillDepositMgtDto.SearchSlipReq(zzsnum, Integer.toString(sumResult), dto.get(0).itgDpNo());

        List<WwdbBillDepositSlipProcessingDvo> slipProcessingDvo = mapper.selectReplacementSlipProcessing(slipReq);

        for (WwdbBillDepositSlipProcessingDvo list : slipProcessingDvo) {
            processCount += mapper.insertSlipProcessings(list);
        }

        WwdbBillDepositMgtDvo wwdbBillDepositMgtDvo = convert.mapSaveDepositSlipDvo(dto.get(0));
        wwdbBillDepositMgtDvo.setBillRplcSapSlpno(zzsnum);

        processCount += mapper.updateSlipRegistration(wwdbBillDepositMgtDvo);


        return processCount;

    }

}
