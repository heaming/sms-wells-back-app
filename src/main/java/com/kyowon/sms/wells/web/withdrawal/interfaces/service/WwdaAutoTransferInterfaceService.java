package com.kyowon.sms.wells.web.withdrawal.interfaces.service;

import java.util.*;
import java.util.stream.Collectors;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.kyowon.sflex.common.message.service.KakaoMessageService;
import com.kyowon.sms.common.web.withdrawal.bilfnt.dvo.*;
import com.kyowon.sms.common.web.withdrawal.bilfnt.mapper.ZwdaBundleWithdrawalMgtMapper;
import com.kyowon.sms.common.web.withdrawal.bilfnt.service.ZwdaAutoTransferRealTimeAccountService;
import com.kyowon.sms.common.web.withdrawal.bilfnt.service.ZwdaKiccReceiveProcessService;
import com.kyowon.sms.common.web.withdrawal.common.dto.ZwwdbFinanceCodesDto;
import com.kyowon.sms.common.web.withdrawal.common.mapper.ZwwdbFinanceCodesMapper;
import com.kyowon.sms.common.web.withdrawal.idvrve.dto.ZwdbCreditcardDto;
import com.kyowon.sms.common.web.withdrawal.idvrve.mapper.ZwdbCreditcardMapper;
import com.kyowon.sms.common.web.withdrawal.interfaces.dvo.ZwdaBillingDelegateSubordinationContractInfoInterfaceDvo;
import com.kyowon.sms.wells.web.withdrawal.interfaces.converter.WwdaAutoTransferConverter;
import com.kyowon.sms.wells.web.withdrawal.interfaces.dto.WwdaAutoTransferInterfaceDto;
import com.kyowon.sms.wells.web.withdrawal.interfaces.dvo.*;
import com.kyowon.sms.wells.web.withdrawal.interfaces.mapper.WwdaAutoTransferInterfaceMapper;
import com.sds.sflex.system.config.core.service.MessageResourceService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WwdaAutoTransferInterfaceService {

    private final WwdaAutoTransferInterfaceMapper mapper;
    private final ZwdaBundleWithdrawalMgtMapper zwdaBundleMapper;
    private final ZwdbCreditcardMapper zwdbCreditcardMapper;
    private final ZwwdbFinanceCodesMapper financeCodesMapper;
    private final MessageResourceService messageResourceService;
    private final KakaoMessageService kakaoMessageService;
    private final WwdaAutoTransferConverter converter;
    private final ZwdaAutoTransferRealTimeAccountService realTimeAccountService;
    private final ZwdaKiccReceiveProcessService KiccReceiveService;

    private static final String MSG_ALT_CHK_NCSR = "MSG_ALT_CHK_NCSR";
    private static final String MSG_ALT_CHK_CONFIRM = "MSG_ALT_CHK_CONFIRM";

    /**
     * 자동이체 출금내역 조회
     * @param dto
     * @return
     */
    public List<WwdaAutoTransferInterfaceDto.SearchPaymentAndWithdrawalRes> getPaymentAndWithdrawalItemizations(
        WwdaAutoTransferInterfaceDto.SearchReq dto
    ) {
        return mapper.selectPaymentAndWithdrawalItemizations(dto);
    }

    /**
    * 자동이체 변경내역 조회
    * @param dto
    * @return
    */
    public List<WwdaAutoTransferInterfaceDto.SearchChangeRes> getChangeItemizations(
        WwdaAutoTransferInterfaceDto.SearchReq dto
    ) {
        return mapper.selectChangeItemizations(dto);
    }

    /**
    * 자동이체 대상목록 조회
    * @param dto
    * @return
    */
    public List<WwdaAutoTransferInterfaceDto.SearchObjectRes> getObjectItemizations(
        WwdaAutoTransferInterfaceDto.SearchObjectReq dto
    ) {
        WwdaAutoTransferObjectItemizationInterfaceSearchDvo searchDvo = converter
            .mapAutoTransferObjectItemizationInterfaceSearch(dto);
        searchDvo.setSellTps(
            dto.sellTps().stream()
                .filter((item) -> !StringUtils.isEmpty(item) && !item.equals("SELL_TP_CD"))
                .collect(Collectors.toList())
        );
        return converter
            .mapWwdaAutoTransferDvoToSearchObjectRes(mapper.selectObjectItemizations(searchDvo));
    }

    /**
    * 자동이체 정보 조회
    * @param dto
    * @return
    */
    public List<WwdaAutoTransferInterfaceDto.SearchRes> getInfos(
        WwdaAutoTransferInterfaceDto.SearchReq dto
    ) {
        List<WwdaAutoTransferInfoInterfaceDvo> selectResults = mapper.selectInfos(dto);
        for (WwdaAutoTransferInfoInterfaceDvo selectResult : selectResults) {
            selectResult
                .setMpno(selectResult.getCralLocaraTno() + selectResult.getMexnoEncr() + selectResult.getCralIdvTno());
        }
        List<WwdaAutoTransferInterfaceDto.SearchRes> results = converter
            .mapWwdaAutoTransferDvoToSearchRes(selectResults);

        return results;
    }

    /**
     * 자동이체 구분(개인/법인) 조회
     * @param dto
     * @return
     */
    public List<WwdaAutoTransferInterfaceDto.SearchCorporatePersonalityDivisionRes> getCorporatePersonalityDivisions(
        WwdaAutoTransferInterfaceDto.SearchReq dto
    ) {
        return mapper.selectCorporatePersonalityDivisions(dto);
    }

    /**
     * 자동이체 은행 목록 조회
     * @param dto
     * @return
     */
    public List<WwdaAutoTransferInterfaceDto.SearchFinancialInstitutionCodeRes> getFinancialInstitutionCodes(
        WwdaAutoTransferInterfaceDto.SearchReq dto
    ) {
        List<WwdaAutoTransferInterfaceDto.SearchFinancialInstitutionCodeRes> returnInfos = new ArrayList<>();
        ZwwdbFinanceCodesDto.SearchReq searchReq = new ZwwdbFinanceCodesDto.SearchReq("4", null);
        List<ZwwdbFinanceCodesDto.SearchRes> searchRes = financeCodesMapper.selectBankCodes(searchReq);
        for (ZwwdbFinanceCodesDto.SearchRes res : searchRes) {
            WwdaAutoTransferInterfaceDto.SearchFinancialInstitutionCodeRes returnInfo = new WwdaAutoTransferInterfaceDto.SearchFinancialInstitutionCodeRes(
                res.codeId(),
                res.codeName()
            );
            returnInfos.add(returnInfo);
        }

        return returnInfos;
    }

    /**
     * 자동이체 묶음 등록 정보 조회
     * @param dto
     * @return
     */
    public List<WwdaAutoTransferInterfaceDto.SearchBundleInfoRes> getBundleInfos(
        WwdaAutoTransferInterfaceDto.SearchReq dto
    ) {
        return mapper.selectBundleInfos(dto);
    }

    /**
     * 자동이체 증빙 정보 목록 조회
     * @param dto
     * @return
     */
    public List<WwdaAutoTransferInterfaceDto.SearchEvidenceInfoRes> getEvidenceInfos(
        WwdaAutoTransferInterfaceDto.SearchReq dto
    ) {
        List<WwdaAutoTransferInfoEvidenceInfoInterfaceDvo> selectResults = mapper.selectEvidenceInfos(dto);
        for (WwdaAutoTransferInfoEvidenceInfoInterfaceDvo selectResult : selectResults) {
            selectResult
                .setMpno(selectResult.getCralLocaraTno() + selectResult.getMexnoEncr() + selectResult.getCralIdvTno());
        }
        List<WwdaAutoTransferInterfaceDto.SearchEvidenceInfoRes> results = converter
            .mapWwdaAutoTransferDvoToSearcEvidenceInfohRes(selectResults);

        return results;
    }

    /**
     * 자동이체 일괄 묶음 등록/해제
     * @param dto
     * @return
     */
    public List<WwdaAutoTransferInterfaceDto.SaveBundleRegistrationReleaseRes> saveBundleRegistrationReleases(
        WwdaAutoTransferInterfaceDto.SaveReq dto
    ) {
        List<WwdaAutoTransferInfoBundleRegistrationReleasesInterfaceDvo> results = new ArrayList<>();

        WwdaAutoTransferInfoBundleRegistrationReleasesInterfaceDvo result = new WwdaAutoTransferInfoBundleRegistrationReleasesInterfaceDvo();

        List<WwdaAutoTransferInfoBundleRegistrationReleasesInterfaceDvo> reqs = converter
            .mapWwdaAutoTransferDvoToSaveBundleRegistrationReleasesReq(dto.bundles());

        // 대표계약번호로 중복제거한 계약번호 및 계약일련번호에 해당하는 묶음 데이터 삭제 처리
        List<WwdaAutoTransferInfoBundleRegistrationReleasesInterfaceDvo> streamDatas = reqs.stream()
            .distinct()
            .collect(Collectors.toList());

        for (WwdaAutoTransferInfoBundleRegistrationReleasesInterfaceDvo distinctData : streamDatas) {
            // 기 등록된 묶음 정보 삭제를 위한 공통 호출
            ZwdaIntegrationBillingIzDvo zwdaDvo = new ZwdaIntegrationBillingIzDvo();
            zwdaDvo.setDgCntrNo(distinctData.getDgCntrNo());
            zwdaDvo.setDgCntrSn(distinctData.getDgCntrSn());
            // 통합청구대상기본 데이터 삭제 처리(DAT_DL_YN = 'Y' 처리)
            zwdaBundleMapper.deleteItgBilOjAllUseDelegate(zwdaDvo);
            // 통합청구대상기본이력 생성(대표계약번호, 대표계약일련번호)
            zwdaBundleMapper.deleteItgBilOjUseDelegate(zwdaDvo);
        }
        List<WwdaAutoTransferInfoBundleRegistrationReleasesInterfaceDvo> streamSortDatas = reqs.stream()
            .filter(data -> !StringUtils.isEmpty(data.getCntrNo()))
            .sorted(
                Comparator.comparing(WwdaAutoTransferInfoBundleRegistrationReleasesInterfaceDvo::getDgCntrNo)
                    .thenComparing(WwdaAutoTransferInfoBundleRegistrationReleasesInterfaceDvo::getDgCntrNo)
                    .thenComparing(WwdaAutoTransferInfoBundleRegistrationReleasesInterfaceDvo::getDgYn).reversed()
            )
            .collect(Collectors.toList());

        String reslCd = "S";
        String reslCntn = "";

        for (WwdaAutoTransferInfoBundleRegistrationReleasesInterfaceDvo insertData : streamSortDatas) {
            results = new ArrayList<>();
            // 대표계약번호로 데이터가 1개만 들어온 경우 에러 체크
            long dataCount = streamSortDatas.stream()
                .filter(
                    data -> insertData.getDgCntrNo().equals(data.getDgCntrNo())
                        && insertData.getDgCntrSn().equals(data.getDgCntrSn())
                )
                .count();
            if (dataCount < 2) {
                reslCd = "E";
                reslCntn = "대표계약번호 : " + insertData.getDgCntrNo()
                    + " 대표계약일련번호 : " + insertData.getDgCntrSn()
                    + " 묶음 등록 할 수 있는 데이터가 없습니다.";
                result.setReslCd(reslCd);
                result.setPcsRsltCn(reslCntn);
                results.add(result);
                break;
            }

            ZwdaIntegrationBillingIzDvo zwdaDvo = new ZwdaIntegrationBillingIzDvo();
            zwdaDvo.setDgCntrNo(insertData.getDgCntrNo());
            zwdaDvo.setDgCntrSn(insertData.getDgCntrSn());
            zwdaDvo.setCntrNo(insertData.getCntrNo());
            zwdaDvo.setCntrSn(insertData.getCntrSn());
            // 대표인데 대표계약번호와 계약번호가 동일하지 않은 경우
            if ("Y".equals(insertData.getDgYn()) &&
                (!insertData.getDgCntrNo().equals(insertData.getCntrNo())
                    || !insertData.getDgCntrSn().equals(insertData.getCntrSn()))) {
                reslCd = "E";
                reslCntn = "계약번호 : " + insertData.getDgCntrNo()
                    + " 계약일련번호 : " + insertData.getDgCntrSn()
                    + " 대표계약은 대표계약번호와 계약번호가 동일해야 합니다.";
                result.setReslCd(reslCd);
                result.setPcsRsltCn(reslCntn);
                results.add(result);
                break;
            }
            if ("Y".equals(insertData.getDgYn())) {
                // 대표계약인데 다른 계약에 서브로 묶여 있는지 확인
                zwdaDvo.setDgYn("N");
                List<String> subordinationContracts = zwdaBundleMapper
                    .selectFundTransferBundleSubordinationContracts(zwdaDvo);
                if (!ObjectUtils.isEmpty(subordinationContracts)) {
                    reslCd = "E";
                    reslCntn = "대표계약번호 : " + insertData.getDgCntrNo()
                        + " 대표계약일련번호 : " + insertData.getDgCntrSn()
                        + " 이미 다른 계약에 묶여 있습니다.";
                    result.setReslCd(reslCd);
                    result.setPcsRsltCn(reslCntn);
                    results.add(result);
                    break;
                }
            }
            if (!"Y".equals(insertData.getDgYn())) {
                // 종속계약이 다른 계약 종속계약 혹은 대표계약에 존재하는지 확인
                ZwdaIntegrationBillingIzDvo cntrDvo = new ZwdaIntegrationBillingIzDvo();
                cntrDvo.setDgCntrNo(insertData.getCntrNo());
                cntrDvo.setDgCntrSn(insertData.getCntrSn());
                List<String> subordinationContracts = zwdaBundleMapper
                    .selectFundTransferBundleSubordinationContracts(cntrDvo);
                if (!ObjectUtils.isEmpty(subordinationContracts)) {
                    reslCd = "E";
                    reslCntn = "계약번호 : " + insertData.getCntrNo()
                        + " 계약일련번호 : " + insertData.getCntrSn()
                        + " 이미 다른 계약에 묶여 있습니다.";
                    result.setReslCd(reslCd);
                    result.setPcsRsltCn(reslCntn);
                    results.add(result);
                    break;
                }
            }

            ZwdaBillingDelegateSubordinationContractInfoInterfaceDvo dgSubCntrInfo = zwdaBundleMapper
                .selectDelegateSubordinationContractInfo(zwdaDvo);
            if (ObjectUtils.isEmpty(dgSubCntrInfo)) {
                reslCd = "E";
                reslCntn = "계약정보 확인 불가 합니다.";
                result.setReslCd(reslCd);
                result.setPcsRsltCn(reslCntn);
                results.add(result);
                break;
            } else {
                // 대표계약번호와 계약번호의 자동이체 일자가 동일한지 체크
                if (!"Y".equals(dgSubCntrInfo.getMpyBsdtYn())) {
                    reslCd = "E";
                    reslCntn = "납부일자가 달라 묶음 등록이 불가 합니다.";
                    result.setReslCd(reslCd);
                    result.setPcsRsltCn(reslCntn);
                    results.add(result);
                    break;
                }

                // 대표계약번호와 계약번호의 청구방식(카드, 계좌)이 동일한지 체크
                if (!"Y".equals(dgSubCntrInfo.getDpTpCdYn())) {
                    reslCd = "E";
                    reslCntn = "입금방식이 달라 묶음 등록이 불가 합니다.";
                    result.setReslCd(reslCd);
                    result.setPcsRsltCn(reslCntn);
                    results.add(result);
                    break;
                }

                // 대표계약번호와 계약번호의 은행/카드사가 동일한지 체크
                if (!"Y".equals(dgSubCntrInfo.getBnkCdYn()) || !"Y".equals(dgSubCntrInfo.getCdcoCdYn())) {
                    reslCd = "E";
                    reslCntn = "은행 또는 카드사가 달라 묶음 등록이 불가 합니다.";
                    result.setReslCd(reslCd);
                    result.setPcsRsltCn(reslCntn);
                    results.add(result);
                    break;
                }

                // 대표계약번호와 계약번호의 계좌번호/카드번호가 동일한지 체크
                if (!"Y".equals(dgSubCntrInfo.getCrcdnoEncrYn()) || !"Y".equals(dgSubCntrInfo.getAcnoEncrYn())) {
                    reslCd = "E";
                    reslCntn = "계좌번호 또는 카드번호가 달라 묶음 등록이 불가 합니다.";
                    result.setReslCd(reslCd);
                    result.setPcsRsltCn(reslCntn);
                    results.add(result);
                    break;
                }
            }

            ZwdaBundleWithdrawalMgtDvo saveDvo = new ZwdaBundleWithdrawalMgtDvo();

            String ItgBilOjPk = zwdaBundleMapper.selectItgBilPk(); // 통합청구대상 SEQ SQ_RVCL_ITG_BIL_OJ_BAS$ITG_BIL_OJ_ID
            saveDvo.setItgBilOjId(ItgBilOjPk);
            saveDvo.setDgCntrNo(insertData.getDgCntrNo());
            saveDvo.setDgCntrSn(insertData.getDgCntrSn());
            saveDvo.setCntrNo(insertData.getCntrNo());
            saveDvo.setCntrSn(insertData.getCntrSn());
            saveDvo.setFntDvCd(insertData.getFntDvCd());
            saveDvo.setDgYn(insertData.getDgYn());

            zwdaBundleMapper.insertItgBilOj(saveDvo); // 통찹청구대상 저장
            zwdaBundleMapper.insertItgBilOjHist(saveDvo.getItgBilOjId()); // 통합청구대상이력 저장
            result.setReslCd(reslCd);
            result.setPcsRsltCn(reslCntn);
            results.add(result);
        }
        return converter.mapSaveBundleRegistrationReleasesResToWwdaAutoTransferDvo(results);
    }

    /**
     * 자동이체 일괄 등록/해제
     * @param dto
     * @return
    public List<WwdaAutoTransferInterfaceDto.SaveBundleRegistrationReleaseRes> saveBulkRegistrationReleases(
        WwdaAutoTransferInterfaceDto.SaveReq dto
    ) throws Exception {
        List<WwdaAutoTransferInfoBulkRegistrationReleasesInterfaceDvo> results = new ArrayList<WwdaAutoTransferInfoBulkRegistrationReleasesInterfaceDvo>();

        List<WwdaAutoTransferInfoBulkRegistrationReleasesInterfaceDvo> reqs = converter
            .mapWwdaAutoTransferDvoToSaveBulkRegistrationReleasesReq(dto.bulks());

        List<WwdaAutoTransferInfoBulkRegistrationReleasesInterfaceDvo> bulks = reqs.stream()
            .sorted(
                Comparator.comparing(WwdaAutoTransferInfoBulkRegistrationReleasesInterfaceDvo::getCntrNo)
                    .thenComparing(WwdaAutoTransferInfoBulkRegistrationReleasesInterfaceDvo::getAcnoCdno)
                    .thenComparing(WwdaAutoTransferInfoBulkRegistrationReleasesInterfaceDvo::getMpyBsdt)
            ).toList();

        for (WwdaAutoTransferInfoBulkRegistrationReleasesInterfaceDvo bulk : bulks) {
            WwdaAutoTransferInfoBulkRegistrationReleasesInterfaceDvo result = new WwdaAutoTransferInfoBulkRegistrationReleasesInterfaceDvo();

            String reslCd = "S";
            String reslCntn = "";

            // 1.1 입력 계약번호, 계약일련번호 미입력 여부 체크
            if (StringUtils.isEmpty(bulk.getCntrNo())) {
                reslCd = "E";
                reslCntn = messageResourceService
                    .getMessage(MSG_ALT_CHK_NCSR, messageResourceService.getMessage("MSG_TXT_CNTR_NO")); // 계약번호을(를) 입력해주세요.

                result.setReslCd(reslCd);
                result.setPcsRsltCn(reslCntn);
                results.add(result);
                continue;
            }
            if (StringUtils.isEmpty(bulk.getCntrSn())) {
                reslCd = "E";
                reslCntn = messageResourceService
                    .getMessage(MSG_ALT_CHK_NCSR, messageResourceService.getMessage("MSG_TXT_CNTR_SN")); // 계약일련번호을(를) 입력해주세요.

                result.setReslCd(reslCd);
                result.setPcsRsltCn(reslCntn);
                results.add(result);
                continue;
            }

            // 1.2 FNT_DV_CD(이체구분코드) = "01"(계좌) 인 경우
            if ("01".equals(bulk.getFntDvCd())) {
                // 1.2.1 입력은행코드 체크
                List<String> fnits = mapper.selectBankCode(bulk.getFnitCd());
                if (fnits.isEmpty()) {
                    reslCd = "E";
                    reslCntn = messageResourceService
                        .getMessage(MSG_ALT_CHK_CONFIRM, messageResourceService.getMessage("MSG_TXT_BNK_CD")); // 은행코드 을(를) 확인하세요.

                    result.setReslCd(reslCd);
                    result.setPcsRsltCn(reslCntn);
                    results.add(result);
                    continue;
                }
                // 1.2.3 입력계좌번호 체크
                if (StringUtils.isEmpty(bulk.getAcnoCdno())) {
                    reslCd = "E";
                    reslCntn = messageResourceService
                        .getMessage(MSG_ALT_CHK_NCSR, messageResourceService.getMessage("MSG_TXT_AC_NO")); // 계좌번호 을(를) 입력해주세요.

                    result.setReslCd(reslCd);
                    result.setPcsRsltCn(reslCntn);
                    results.add(result);
                    continue;
                } else {
                    // 1.2.4 공백 제거한 ACNO_CDNO(계좌카드번호) 숫자여부 확인(문자가 포함되어 있는 경우 오류)
                    if (!StringUtils.isNumeric(bulk.getAcnoCdno().trim())) {
                        reslCd = "E";
                        reslCntn = messageResourceService
                            .getMessage(MSG_ALT_CHK_CONFIRM, messageResourceService.getMessage("MSG_TXT_AC_NO")); // 계좌번호 을(를) 확인하세요.

                        result.setReslCd(reslCd);
                        result.setPcsRsltCn(reslCntn);
                        results.add(result);
                        continue;
                    }
                }
            }

            // 1.3 FNT_DV_CD(이체구분코드) = "02"(카드) 인 경우
            if ("02".equals(bulk.getFntDvCd())) {
                // 1.3.1 입력카드번호 체크
                if (StringUtils.isEmpty(bulk.getAcnoCdno())) {
                    reslCd = "E";
                    reslCntn = messageResourceService
                        .getMessage(MSG_ALT_CHK_NCSR, messageResourceService.getMessage("MSG_TXT_CARD_NO")); // 카드번호 을(를) 입력해주세요.

                    result.setReslCd(reslCd);
                    result.setPcsRsltCn(reslCntn);
                    results.add(result);
                    continue;
                } else {
                    // 1.3.2 BIN 번호 체크(Z-WD-S-0065)
                    ZwdbCreditcardDto.SearchInfoReq searchReq = ZwdbCreditcardDto.SearchInfoReq.builder()
                        .crdcdBinNo(bulk.getAcnoCdno()).build();
                    List<ZwdbCreditcardDto.SearchInfoRes> binInfos = zwdbCreditcardMapper
                        .selectCreditcardBinInfos(searchReq);

                    if (binInfos.isEmpty()) {
                        reslCd = "E";
                        reslCntn = messageResourceService
                            .getMessage(MSG_ALT_CHK_CONFIRM, messageResourceService.getMessage("MSG_TXT_CARD_NO")); // 카드번호 을(를) 확인하세요.

                        result.setReslCd(reslCd);
                        result.setPcsRsltCn(reslCntn);
                        results.add(result);
                        continue;
                    } else {
                        if (!"01".equals(binInfos.get(0).cardKndCd())) {
                            reslCd = "E";
                            reslCntn = messageResourceService
                                .getMessage("MSG_ALT_AFTN_CANT_CRDCD"); // 자동이체 불가 카드입니다.

                            result.setReslCd(reslCd);
                            result.setPcsRsltCn(reslCntn);
                            results.add(result);
                            continue;
                        }
                    }

                    // 1.3.4 신용카드번호 앞자리 6 체크
                    String crditeCard = StringUtils.substring(bulk.getAcnoCdno(), 6);

                    if ("945034".equals(crditeCard) || "448550".equals(crditeCard) || "440447".equals(crditeCard)
                        || "588644".equals(crditeCard)) {
                        reslCd = "E";
                        reslCntn = messageResourceService
                            .getMessage("MSG_ALT_AFTN_RGST_IMP_CRDCD"); // 등록 불가 카드 입니다.

                        result.setReslCd(reslCd);
                        result.setPcsRsltCn(reslCntn);
                        results.add(result);
                        continue;
                    }
                }
                // 1.3.5 입력 카드유효기간년월 체크
                if (StringUtils.isEmpty(bulk.getCardExpdtYm())) {
                    reslCd = "E";
                    reslCntn = messageResourceService
                        .getMessage(MSG_ALT_CHK_NCSR, messageResourceService.getMessage("MSG_TXT_CARD_EXPDT")); // 카드유효기간 을(를) 입력하세요.

                    result.setReslCd(reslCd);
                    result.setPcsRsltCn(reslCntn);
                    results.add(result);
                    continue;
                } else {
                    // 1.3.6 카드유효기간이 현재월 이전 체크
                    String nowDate = DateUtil.getNowDayString();
                    Integer currentYymm = Integer.parseInt(nowDate.substring(2, 6));
                    Integer currentDd = Integer.parseInt(nowDate.substring(6, 8));
                    Integer cardExpdtYm = Integer.parseInt(bulk.getCardExpdtYm());

                    if (cardExpdtYm < currentYymm) {
                        reslCd = "E";
                        reslCntn = messageResourceService
                            .getMessage("MSG_ALT_AF_CRTL_MM", messageResourceService.getMessage("MSG_TXT_CARD_EXPDT")); // 카드유효기간은 현재월 이전이 불가합니다.

                        result.setReslCd(reslCd);
                        result.setPcsRsltCn(reslCntn);
                        results.add(result);
                        continue;
                    }

                    // 1.3.7 입력 카드유효기간년월 = 당월(현재일자, 'YYMM') AND 당일(현재일자, 'DD') >= '22')
                    if (cardExpdtYm.equals(currentYymm) && currentDd >= 22) {
                        reslCd = "E";
                        reslCntn = messageResourceService
                            .getMessage(MSG_ALT_CHK_CONFIRM, messageResourceService.getMessage("MSG_TXT_CARD_EXPDT")); // 카드유효기간 을(를) 확인하세요.

                        result.setReslCd(reslCd);
                        result.setPcsRsltCn(reslCntn);
                        results.add(result);
                        continue;
                    }
                }
            }

            // 1.4 입력 법인격구분코드 != ("1" OR "2")
            if (StringUtils.isEmpty(bulk.getCopnDvCd())) {
                reslCd = "E";
                reslCntn = messageResourceService
                    .getMessage(MSG_ALT_CHK_NCSR, messageResourceService.getMessage("MSG_TXT_COPN_DV_CD")); // 법인격구분코드 을(를) 입력하세요.

                result.setReslCd(reslCd);
                result.setPcsRsltCn(reslCntn);
                results.add(result);
                continue;
            }
            if (!"1".equals(bulk.getCopnDvCd()) && !"2".equals(bulk.getCopnDvCd())) {
                reslCd = "E";
                reslCntn = messageResourceService
                    .getMessage(MSG_ALT_CHK_CONFIRM, messageResourceService.getMessage("MSG_TXT_COPN_DV_CD")); // 법인격구분코드 을(를) 확인하세요.

                result.setReslCd(reslCd);
                result.setPcsRsltCn(reslCntn);
                results.add(result);
                continue;
            }

            // 1.5 입력 MPY_BSDT(납부기준일자) 체크
            List<String> billingSchedules = mapper.selectBillingSchedule(bulk.getMpyBsdt());
            if (billingSchedules.isEmpty()) {
                reslCd = "E";
                reslCntn = messageResourceService
                    .getMessage(MSG_ALT_CHK_CONFIRM, messageResourceService.getMessage("MSG_TXT_FNT_DT")); // 이체일자 을(를) 확인하세요.

                result.setReslCd(reslCd);
                result.setPcsRsltCn(reslCntn);
                results.add(result);
                continue;
            }


            // 1.6 현재 청구중인지 확인
            List<WwdaBillingScheduleReceiveInterfaceDvo> billingScheduleReceives = mapper
                .selectBillingScheduleReceive(bulk.getCntrNo(), bulk.getCntrSn());
            if (ObjectUtils.isEmpty(billingScheduleReceives)
                || "Y".equals(billingScheduleReceives.get(0).getRveDt())) {
                reslCd = "E";
                reslCntn = messageResourceService
                    .getMessage("MSG_ALT_BIL_ERR_CH_FNT_D"); // 현재 출금요청중으로 이체일을 뒤로 변경이 불가합니다.

                result.setReslCd(reslCd);
                result.setPcsRsltCn(reslCntn);
                results.add(result);
                continue;
            }

            // 1.7 입력 소유자한글명 미입력 에러
            if (StringUtils.isEmpty(bulk.getOwrKnm())) {
                reslCd = "E";
                reslCntn = messageResourceService
                    .getMessage(MSG_ALT_CHK_NCSR, messageResourceService.getMessage("MSG_TXT_OWK_KNM")); // 소유자한글명 을(를) 입력하세요.

                result.setReslCd(reslCd);
                result.setPcsRsltCn(reslCntn);
                results.add(result);
                continue;
            }

            // 1.8 법인격구분식별값 미입력시 에러
            if (StringUtils.isEmpty(bulk.getCrpSpmtDrmNm())) {
                reslCd = "E";
                reslCntn = messageResourceService
                    .getMessage(MSG_ALT_CHK_NCSR, messageResourceService.getMessage("MSG_TXT_BRYY_MMDD_ENTRP_NO")); // 생년월일/사업자번호 을(를) 입력하세요.

                result.setReslCd(reslCd);
                result.setPcsRsltCn(reslCntn);
                results.add(result);
                continue;
            }

            // 1.8 입력 계약자관계코드
            if (StringUtils.isEmpty(bulk.getCntrtRelCd())) {
                reslCd = "E";
                reslCntn = messageResourceService
                    .getMessage(MSG_ALT_CHK_NCSR, messageResourceService.getMessage("MSG_TXT_CNTRT_RLT_CD")); // 계약자관계코드 을(를) 입력하세요.

                result.setReslCd(reslCd);
                result.setPcsRsltCn(reslCntn);
                results.add(result);
                continue;
            }

            // 1.9 입력 변경접수사용자ID
            if (StringUtils.isEmpty(bulk.getChRcpUsrId())) {
                reslCd = "E";
                reslCntn = messageResourceService
                    .getMessage(MSG_ALT_CHK_NCSR, messageResourceService.getMessage("MSG_TXT_CH_RCP_USR_ID")); // 변경접수사용자ID 을(를) 입력하세요.

                result.setReslCd(reslCd);
                result.setPcsRsltCn(reslCntn);
                results.add(result);
                continue;
            }

            List<WwdaBillingScheduleReceiveInterfaceDvo> contracts = mapper
                .selectContractInfo(bulk.getCntrNo());

            String prtnrNo = ObjectUtils.isEmpty(contracts) ? "" : contracts.get(0).getSellPrtnrNo();

            String urlParams = "vstYn=N&akChdt=" + DateUtil.getNowString()
                + "&chRqrDvCd=2&aftnThpChYn=Y&clctamMngtYn=N&cntrChPrtnrNo="
                + prtnrNo;
            String url = "#/ns/ztwda-auto-transfer-payment-change?" + urlParams;

            Map<String, Object> paramMap = new HashMap<>();
            paramMap.put("cstNm", bulk.getOwrKnm());
            paramMap.put("url", url);

            // 카카오 발송 파라미터 set
            KakaoSendReqDvo req = KakaoSendReqDvo.withTemplateCode()
                .templateCode("Z_WDA_00003")
                .templateParamMap(paramMap)
                .destInfo(bulk.getOwrKnm() + "^" + bulk.getMpno())
                .callback("15776688")
                .reserved7("KSSE")
                .build();

            // 카카오톡 발송
            int sendResult = kakaoMessageService.sendMessage(req);
            if (sendResult > 0) {
                result.setReslCd(reslCd);
                results.add(result);
            }
        }

        return converter.mapSaveBulkRegistrationReleasesResToWwdaAutoTransferDvo(results);
    }
     */

    /**
     * 자동이체 계좌 실명인증
     * @param dto
     * @return
     */
    public List<WwdaAutoTransferInterfaceDto.SearchRealNameCertificationRes> getRealNameCertification(
        WwdaAutoTransferInterfaceDto.SearchRealNameCertificationReq dto
    ) {
        List<WwdaAutoTransferRealNameCertificationInterfaceDvo> resultDtos = new ArrayList<WwdaAutoTransferRealNameCertificationInterfaceDvo>();
        WwdaAutoTransferRealNameCertificationInterfaceDvo result = new WwdaAutoTransferRealNameCertificationInterfaceDvo();

        // 1. 계좌 유효성 검사 호출을 위한 파라미터 설정
        String cntrNo = "W99999999999"; /*임시계약번호*/
        String cntrSn = "1"; /*임시계약일련번호*/
        String bnkCd = dto.bnkCd(); /*은행코드*/
        String acNo = dto.acno(); /*계좌번호*/
        String copnDvCd = "1"; /*법인격구분코드*/
        String copnDvDrmVal = dto.bryyMmdd(); /*법인격구분코드식별값*/
        String achldrNm = dto.cntrtNm(); /*예금주명*/
        String systemDvCd = "2"; /*시스템구분코드 1 : EDU, 2: WELLS*/
        String psicId = "9999999999"; /*담장자ID*/
        String deptId = ""; /*부서ID*/

        Map<String, Object> reqParam = new HashMap<String, Object>();
        reqParam.put("cntrNo", cntrNo);
        reqParam.put("cntrSn", cntrSn);
        reqParam.put("bnkCd", bnkCd);
        reqParam.put("acNo", acNo);
        reqParam.put("copnDvCd", copnDvCd);
        reqParam.put("copnDvDrmVal", copnDvDrmVal);
        reqParam.put("achldrNm", achldrNm);
        reqParam.put("systemDvCd", systemDvCd);
        reqParam.put("sysDvCd", "W");
        reqParam.put("psicId", psicId);
        reqParam.put("deptId", deptId);

        // 2. 은행계좌 유효성검사 서비스 호출(Z-WD-S-0027)
        ZwdaAutoTransferRealTimeAccountCheckDvo resultDvo = realTimeAccountService.saveAftnAcEftnChecks(reqParam);

        // 청구생성코드가 1이 아닐때 에러 발생
        // (1 : 정상처리, 2 : 오류, 3 : 자료없음)
        // BizAssert.isTrue("1".equals(resultDvo.getBilCrtStatCd()), resultDvo.getErrCn());

        // 3. 수신결과 및 리턴 설정
        // 3.1.1 리턴 받은 값이 없거나 Null 인 경우 "0000" 셋팅
        String acFntRsCd = resultDvo.getAcFntRsCd();
        String acFntRsNm = "";

        // 3.1 리턴받은 계좌이체불능코드 셋팅
        result.setAcFntRsCd(acFntRsCd);

        // 3.2 리턴받은 계좌이체불능코드에 해당하는 계좌이체결과코드 조회
        if (!ObjectUtils.isEmpty(resultDvo.getBilCrtStatCd())) {
            if ("1".equals(resultDvo.getBilCrtStatCd())) {
                acFntRsNm = mapper.selectAutomaticTransferResultCodeName("VAC", acFntRsCd);
            }
            if ("2".equals(resultDvo.getBilCrtStatCd())) {
                acFntRsNm = resultDvo.getErrCn();
            }
        }
        result.setAcFntRsCdNm(acFntRsNm);

        // 3.1 리턴받은 계좌주명
        result.setOwrKnm(resultDvo.getAchldrNm());

        resultDtos.add(result);

        return converter.mapRealNameCertificationDvoToWwdaAutoTransferRealNameCertificationRes(resultDtos);
    }

    /**
     * 자동이체 카드 유효성 체크
     * @param dto
     * @return
     */
    public List<WwdaAutoTransferInterfaceDto.SearchCardEffectivenessCheckRes> getCardEffectivenessCheck(
        WwdaAutoTransferInterfaceDto.SearchCardEffectivenessCheckReq dto
    ) {
        List<WwdaAutoTransferCardEffectivenessCheckInterfaceDvo> resultDtos = new ArrayList<WwdaAutoTransferCardEffectivenessCheckInterfaceDvo>();
        WwdaAutoTransferCardEffectivenessCheckInterfaceDvo result = new WwdaAutoTransferCardEffectivenessCheckInterfaceDvo();

        // 1. 계좌 유효성 검사 호출을 위한 파라미터 설정
        String crdcdNo = dto.crdcdNo(); /*신용카드번호*/
        String cardExpdtYm = dto.cardExpdtYm(); /*카드유효기간년월*/
        String vacCopnDvCd = "1"; /*법인격구분코드*/
        String bryyMm = dto.bryyMmdd(); /*법인격구분식별값 = BRYY_MMDD(생년월일)*/
        String tmlNo = ""; /*단말기번호*/
        String systemDvCd = "E"; /*시스템구분코드*/

        // 2. 카드번호 유효성 검사 서비스 호출(Z-WD-S-0060)
        // TODO : 카드번호유효성검사 서비스 개발 이후 호출 필요

        ZwdaCardNumberEffectivenessCheckReqDvo param = new ZwdaCardNumberEffectivenessCheckReqDvo();
        param.setCrcdnoEncr(crdcdNo);
        param.setCardExpdtYm(cardExpdtYm);
        param.setCopnDvCd(vacCopnDvCd);
        param.setCopnDvDrmVal(bryyMm);
        param.setTmlNo(tmlNo);
        param.setSysDvCd(systemDvCd);

        ZwdaCardNumberEffectivenessCheckResDvo resDvo = KiccReceiveService.saveCardNumberEffectivenessCheck(param);

        if (!ObjectUtils.isEmpty(resDvo)) {
            if ("1".equals(resDvo.getStateCode())) {
                // 3. 수신결과 및 리턴 설정
                String cardFntRsCd = resDvo.getRspCd();
                // 3.1 리턴받은 카드이체결과코드 셋팅
                // 3.1.1 리턴 받은 값이 없거나 Null 인 경우 "0000" 셋팅
                result.setCardFntRsCd(cardFntRsCd);

                // 3.2 리턴받은 계좌이체불능코드에 해당하는 계좌이체결과코드 조회
                // 3.2.1 리턴 받은 값이 없거나 Null 인 경우 "0000" 셋팅
                result.setCardFntRsCdNm(mapper.selectAutomaticTransferResultCodeName("CARD", cardFntRsCd));

                // 3.3 카드사코드 조회
                // 3.3.1 신용카드BIN번호 조회
                ZwdbCreditcardDto.SearchInfoReq searchReq = ZwdbCreditcardDto.SearchInfoReq.builder()
                    .crdcdBinNo(crdcdNo)
                    .build();
                List<ZwdbCreditcardDto.SearchInfoRes> binInfos = zwdbCreditcardMapper
                    .selectCreditcardBinInfos(searchReq);

                if (!ObjectUtils.isEmpty(binInfos) && "0000".equals(cardFntRsCd)) {
                    result.setCdcoCd(binInfos.get(0).fnitCd());
                    result.setCdcoNm(binInfos.get(0).fnitNm());
                }
            } else {
                result.setCardFntRsCdNm(resDvo.getErrCn());
            }
        }

        resultDtos.add(result);

        return converter.mapCardEffectivenessCheckDvoToWwdaAutoTransferRealNameCertificationRes(resultDtos);
    }

    /**
     * 은행계좌유효성체크_SB
     * @param dto
     * @return
     */
    public WwdaAutoTransferInterfaceDto.SearchBankEffectivenessCheckRes getBankEffectivenessCheck(
        WwdaAutoTransferInterfaceDto.SearchBankEffectivenessCheckReq dto
    ) {

        String systemDvCd = "2"; /*시스템구분코드 1 : EDU, 2: WELLS*/
        Map<String, Object> reqParam = new HashMap<String, Object>();
        reqParam.put("cntrNo", dto.cntrNo());
        reqParam.put("cntrSn", dto.cntrSn());
        reqParam.put("bnkCd", dto.bnkCd());
        reqParam.put("acNo", dto.acno());
        reqParam.put("copnDvCd", dto.copnDvCd());
        reqParam.put("copnDvDrmVal", dto.copnDvDrmVal());
        reqParam.put("achldrNm", dto.achldrNm());
        reqParam.put("systemDvCd", systemDvCd);
        reqParam.put("sysDvCd", dto.sysDvCd());
        reqParam.put("psicId", dto.psicId());
        reqParam.put("deptId", dto.deptId());

        // 2. 은행계좌 유효성검사 서비스 호출(Z-WD-S-0027)
        ZwdaAutoTransferRealTimeAccountCheckDvo resultDvo = realTimeAccountService.saveAftnAcEftnChecks(reqParam);

        // 3. 수신결과 및 리턴 설정
        // 3.1.1 리턴 받은 값이 없거나 Null 인 경우 "0000" 셋팅
        String acFntRsCd = resultDvo.getAcFntRsCd();
        String acFntRsNm = "";

        // 3.2 리턴받은 계좌이체불능코드에 해당하는 계좌이체결과코드 조회
        if (!ObjectUtils.isEmpty(resultDvo.getBilCrtStatCd())) {
            if ("1".equals(resultDvo.getBilCrtStatCd())) {
                acFntRsNm = mapper.selectAutomaticTransferResultCodeName("VAC", acFntRsCd);
            }
            if ("2".equals(resultDvo.getBilCrtStatCd())) {
                acFntRsNm = resultDvo.getErrCn();
            }
        }

        return WwdaAutoTransferInterfaceDto.SearchBankEffectivenessCheckRes.builder()
            .achldrNm(resultDvo.getAchldrNm())
            .acFntImpsCd(acFntRsCd)
            .acFntImpsCdNm(acFntRsNm)
            .errCn(resultDvo.getErrCn())
            .bilCrtStatCd(resultDvo.getBilCrtStatCd())
            .build();
    }

}
