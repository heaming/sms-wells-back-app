package com.kyowon.sms.wells.web.withdrawal.interfaces.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.kyowon.sflex.common.message.dvo.KakaoSendReqDvo;
import com.kyowon.sflex.common.message.service.KakaoMessageService;
import com.kyowon.sms.common.web.withdrawal.bilfnt.dvo.ZwdaBundleWithdrawalMgtDvo;
import com.kyowon.sms.common.web.withdrawal.bilfnt.dvo.ZwdaIntegrationBillingIzDvo;
import com.kyowon.sms.common.web.withdrawal.bilfnt.mapper.ZwdaBundleWithdrawalMgtMapper;
import com.kyowon.sms.common.web.withdrawal.idvrve.dto.ZwdbCreditcardDto;
import com.kyowon.sms.common.web.withdrawal.idvrve.mapper.ZwdbCreditcardMapper;
import com.kyowon.sms.wells.web.withdrawal.common.dvo.WwdaAutoTransferRealTimeAccountCheckDvo;
import com.kyowon.sms.wells.web.withdrawal.common.service.WwdaAutoTransferRealTimeAccountService;
import com.kyowon.sms.wells.web.withdrawal.interfaces.converter.WwdaAutoTransferConverter;
import com.kyowon.sms.wells.web.withdrawal.interfaces.dto.WwdaAutoTransferInterfaceDto;
import com.kyowon.sms.wells.web.withdrawal.interfaces.dvo.WwdaAutoTransferCardEffectivenessCheckInterfaceDvo;
import com.kyowon.sms.wells.web.withdrawal.interfaces.dvo.WwdaAutoTransferInfoBulkRegistrationReleasesInterfaceDvo;
import com.kyowon.sms.wells.web.withdrawal.interfaces.dvo.WwdaAutoTransferInfoBundleRegistrationReleasesInterfaceDvo;
import com.kyowon.sms.wells.web.withdrawal.interfaces.dvo.WwdaAutoTransferInfoEvidenceInfoInterfaceDvo;
import com.kyowon.sms.wells.web.withdrawal.interfaces.dvo.WwdaAutoTransferInfoInterfaceDvo;
import com.kyowon.sms.wells.web.withdrawal.interfaces.dvo.WwdaAutoTransferObjectItemizationInterfaceDvo;
import com.kyowon.sms.wells.web.withdrawal.interfaces.dvo.WwdaAutoTransferRealNameCertificationInterfaceDvo;
import com.kyowon.sms.wells.web.withdrawal.interfaces.dvo.WwdaBillingScheduleReceiveInterfaceDvo;
import com.kyowon.sms.wells.web.withdrawal.interfaces.mapper.WwdaAutoTransferInterfaceMapper;
import com.sds.sflex.common.utils.DateUtil;
import com.sds.sflex.common.utils.StringUtil;
import com.sds.sflex.system.config.core.service.MessageResourceService;
import com.sds.sflex.system.config.validation.BizAssert;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WwdaAutoTransferInterfaceService {

    private final WwdaAutoTransferInterfaceMapper mapper;
    private final ZwdaBundleWithdrawalMgtMapper zwdaBundleMapper;
    private final ZwdbCreditcardMapper zwdbCreditcardMapper;
    private final MessageResourceService messageResourceService;
    private final KakaoMessageService kakaoMessageService;
    private final WwdaAutoTransferConverter converter;
    private final WwdaAutoTransferRealTimeAccountService realTimeAccountService;

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
        List<WwdaAutoTransferObjectItemizationInterfaceDvo> selectResults = mapper.selectObjectItemizations(dto);
        for (WwdaAutoTransferObjectItemizationInterfaceDvo selectResult : selectResults) {
            selectResult
                .setMpno(selectResult.getCralLocaraTno() + selectResult.getMexnoEncr() + selectResult.getCralIdvTno());
        }
        List<WwdaAutoTransferInterfaceDto.SearchObjectRes> results = converter
            .mapWwdaAutoTransferDvoToSearchObjectRes(selectResults);

        return results;
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
        return mapper.selectFinancialInstitutionCodes(dto);
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

        List<WwdaAutoTransferInfoBundleRegistrationReleasesInterfaceDvo> results = new ArrayList<WwdaAutoTransferInfoBundleRegistrationReleasesInterfaceDvo>();

        List<WwdaAutoTransferInfoBundleRegistrationReleasesInterfaceDvo> reqs = converter
            .mapWwdaAutoTransferDvoToSaveBundleRegistrationReleasesReq(dto.bundles());

        String dgcntrNo = "";
        String dgcntrSn = "";

        for (WwdaAutoTransferInfoBundleRegistrationReleasesInterfaceDvo req : reqs) {
            WwdaAutoTransferInfoBundleRegistrationReleasesInterfaceDvo result = new WwdaAutoTransferInfoBundleRegistrationReleasesInterfaceDvo();

            // 기 등록된 묶음 정보 삭제를 위한 공통 호출
            ZwdaIntegrationBillingIzDvo zwdaDvo = new ZwdaIntegrationBillingIzDvo();

            String reslCd = "S";
            String reslCntn = "";

            zwdaDvo.setDgCntrNo(req.getDgCntrNo());
            zwdaDvo.setDgCntrSn(req.getDgCntrSn());
            // 대표계약번호, 대표계약일련번호 존재하고 계약번호와 계약일련번호가 존재하지 않는 경우 삭제만 처리
            if (!StringUtils.isEmpty(req.getDgCntrNo()) && !StringUtils.isEmpty(req.getDgCntrSn())
                && StringUtils.isEmpty(req.getCntrNo()) && StringUtils.isEmpty(req.getCntrSn())) {
                try {
                    // 통합청구대상기본 데이터 삭제 처리(DAT_DL_YN = 'Y' 처리)
                    zwdaBundleMapper.deleteItgBilOjAllUseDelegate(zwdaDvo);
                    // 통합청구대상기본이력 생성(대표계약번호, 대표계약일련번호)
                    zwdaBundleMapper.deleteItgBilOjUseDelegate(zwdaDvo);
                } catch (Exception e) {
                    reslCd = "E";
                    reslCntn = messageResourceService.getMessage("MSG_ALT_ERR_BNDL_IF");
                }
            } else {
                // 대표계약번호, 대표계약일련번호 존재하고 계약번호와 계약일련번호도 존재하는 경우
                // 1. 중복되는 대표계약번호, 대표계약일련번호가 동일한 경우 삭제 하지 않음.
                if (!StringUtils.equals(dgcntrNo, req.getDgCntrNo())
                    || !StringUtils.equals(dgcntrSn, req.getDgCntrSn())) {
                    try {
                        // 통합청구대상기본 데이터 삭제 처리(DAT_DL_YN = 'Y' 처리)
                        zwdaBundleMapper.deleteItgBilOjAllUseDelegate(zwdaDvo);
                        // 통합청구대상기본이력 생성(대표계약번호, 대표계약일련번호)
                        zwdaBundleMapper.deleteItgBilOjUseDelegate(zwdaDvo);
                    } catch (Exception e) {
                        reslCd = "E";
                        reslCntn = messageResourceService.getMessage("MSG_ALT_ERR_BNDL_IF");
                    }
                    dgcntrNo = req.getDgCntrNo();
                    dgcntrSn = req.getDgCntrSn();
                }
                if ("S".equals(reslCd)) {
                    ZwdaBundleWithdrawalMgtDvo saveDvo = new ZwdaBundleWithdrawalMgtDvo();

                    String ItgBilOjPk = zwdaBundleMapper.selectItgBilPk(); // 통합청구대상 SEQ SQ_RVCL_ITG_BIL_OJ_BAS$ITG_BIL_OJ_ID
                    saveDvo.setItgBilOjId(ItgBilOjPk);
                    saveDvo.setDgCntrNo(req.getDgCntrNo());
                    saveDvo.setDgCntrSn(req.getDgCntrSn());
                    saveDvo.setCntrNo(req.getCntrNo());
                    saveDvo.setCntrSn(req.getCntrSn());
                    saveDvo.setFntDvCd(req.getFntDvCd());
                    saveDvo.setDgYn(req.getDgYn());
                    try {

                        zwdaBundleMapper.insertItgBilOj(saveDvo); // 통찹청구대상 저장
                        zwdaBundleMapper.insertItgBilOjHist(saveDvo.getItgBilOjId()); // 통합청구대상이력 저장
                    } catch (Exception e) {
                        reslCd = "E";
                        reslCntn = messageResourceService.getMessage("MSG_ALT_ERR_BNDL_IF");
                    }
                }
            }
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
     */
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
                    .getMessage("MSG_ALT_CHK_NCSR", messageResourceService.getMessage("MSG_TXT_CNTR_NO")); // 계약번호을(를) 입력해주세요.

                result.setReslCd(reslCd);
                result.setPcsRsltCn(reslCntn);
                results.add(result);
                continue;
            }
            if (StringUtils.isEmpty(bulk.getCntrSn())) {
                reslCd = "E";
                reslCntn = messageResourceService
                    .getMessage("MSG_ALT_CHK_NCSR", messageResourceService.getMessage("MSG_TXT_CNTR_SN")); // 계약일련번호을(를) 입력해주세요.

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
                        .getMessage("MSG_ALT_CHK_CONFIRM", messageResourceService.getMessage("MSG_TXT_BNK_CD")); // 은행코드 을(를) 확인하세요.

                    result.setReslCd(reslCd);
                    result.setPcsRsltCn(reslCntn);
                    results.add(result);
                    continue;
                }
                // 1.2.3 입력계좌번호 체크
                if (StringUtils.isEmpty(bulk.getAcnoCdno())) {
                    reslCd = "E";
                    reslCntn = messageResourceService
                        .getMessage("MSG_ALT_CHK_NCSR", messageResourceService.getMessage("MSG_TXT_AC_NO")); // 계좌번호 을(를) 입력해주세요.

                    result.setReslCd(reslCd);
                    result.setPcsRsltCn(reslCntn);
                    results.add(result);
                    continue;
                } else {
                    // 1.2.4 공백 제거한 ACNO_CDNO(계좌카드번호) 숫자여부 확인(문자가 포함되어 있는 경우 오류)
                    if (!StringUtils.isNumeric(bulk.getAcnoCdno().trim())) {
                        reslCd = "E";
                        reslCntn = messageResourceService
                            .getMessage("MSG_ALT_CHK_CONFIRM", messageResourceService.getMessage("MSG_TXT_AC_NO")); // 계좌번호 을(를) 확인하세요.

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
                        .getMessage("MSG_ALT_CHK_NCSR", messageResourceService.getMessage("MSG_TXT_CARD_NO")); // 카드번호 을(를) 입력해주세요.

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
                            .getMessage("MSG_ALT_CHK_CONFIRM", messageResourceService.getMessage("MSG_TXT_CARD_NO")); // 카드번호 을(를) 확인하세요.

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
                        .getMessage("MSG_ALT_CHK_NCSR", messageResourceService.getMessage("MSG_TXT_CARD_EXPDT")); // 카드유효기간 을(를) 입력하세요.

                    result.setReslCd(reslCd);
                    result.setPcsRsltCn(reslCntn);
                    results.add(result);
                    continue;
                } else {
                    // 1.3.6 카드유효기간이 현재월 이전 체크
                    SimpleDateFormat sdf = new SimpleDateFormat("yyMM", Locale.KOREAN);
                    SimpleDateFormat sdf2 = new SimpleDateFormat("DD", Locale.KOREAN);
                    Calendar cal = Calendar.getInstance();

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
                    if (cardExpdtYm == currentYymm && currentDd >= 22) {
                        reslCd = "E";
                        reslCntn = messageResourceService
                            .getMessage("MSG_ALT_CHK_CONFIRM", messageResourceService.getMessage("MSG_TXT_CARD_EXPDT")); // 카드유효기간 을(를) 확인하세요.

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
                    .getMessage("MSG_ALT_CHK_NCSR", messageResourceService.getMessage("MSG_TXT_COPN_DV_CD")); // 법인격구분코드 을(를) 입력하세요.

                result.setReslCd(reslCd);
                result.setPcsRsltCn(reslCntn);
                results.add(result);
                continue;
            }
            if (!"1".equals(bulk.getCopnDvCd()) && !"2".equals(bulk.getCopnDvCd())) {
                reslCd = "E";
                reslCntn = messageResourceService
                    .getMessage("MSG_ALT_CHK_CONFIRM", messageResourceService.getMessage("MSG_TXT_COPN_DV_CD")); // 법인격구분코드 을(를) 확인하세요.

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
                    .getMessage("MSG_ALT_CHK_CONFIRM", messageResourceService.getMessage("MSG_TXT_FNT_DT")); // 이체일자 을(를) 확인하세요.

                result.setReslCd(reslCd);
                result.setPcsRsltCn(reslCntn);
                results.add(result);
                continue;
            }

            // 1.6 현재 청구중인지 확인
            List<WwdaBillingScheduleReceiveInterfaceDvo> billingScheduleReceives = mapper
                .selectBillingScheduleReceive(bulk.getCntrNo(), bulk.getCntrSn());
            if (!ObjectUtils.isEmpty(billingScheduleReceives)
                && !ObjectUtils.isEmpty(billingScheduleReceives.get(0).getRveDt())) {
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
                    .getMessage("MSG_ALT_CHK_NCSR", messageResourceService.getMessage("MSG_TXT_OWK_KNM")); // 소유자한글명 을(를) 입력하세요.

                result.setReslCd(reslCd);
                result.setPcsRsltCn(reslCntn);
                results.add(result);
                continue;
            }

            // 1.8 법인격구분식별값 미입력시 에러
            if (StringUtils.isEmpty(bulk.getCrpSpmtDrmNm())) {
                reslCd = "E";
                reslCntn = messageResourceService
                    .getMessage("MSG_ALT_CHK_NCSR", messageResourceService.getMessage("MSG_TXT_BRYY_MMDD_ENTRP_NO")); // 생년월일/사업자번호 을(를) 입력하세요.

                result.setReslCd(reslCd);
                result.setPcsRsltCn(reslCntn);
                results.add(result);
                continue;
            }

            List<WwdaBillingScheduleReceiveInterfaceDvo> contracts = mapper
                .selectContractInfo(bulk.getCntrNo());

            String prtnrNo = contracts.isEmpty() ? "" : contracts.get(0).getSellPrtnrNo();

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
                result.setReslCd("S");
                results.add(result);
            }
        }

        return converter.mapSaveBulkRegistrationReleasesResToWwdaAutoTransferDvo(results);
    }

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
        String cstNo = "9999999999"; /*임시고객번호*/
        String bnkCd = dto.bnkCd(); /*은행코드*/
        String acNo = dto.acno(); /*계좌번호*/
        String copnDvCd = "1"; /*법인격구분코드*/
        String copnDvDrmVal = dto.bryyMmdd(); /*법인격구분코드식별값*/
        String achldrNm = dto.cntrtNm(); /*예금주명*/
        String systemDvCd = "1"; /*시스템구분코드 1 : EDU, 2: WELLS*/
        String picId = "9999999999"; /*담장자ID*/
        String deptId = ""; /*부서ID*/

        Map<String, Object> reqParam = new HashMap<String, Object>();
        reqParam.put("cstNo", cstNo);
        reqParam.put("bnkCd", bnkCd);
        reqParam.put("acNo", acNo);
        reqParam.put("copnDvCd", copnDvCd);
        reqParam.put("copnDvDrmVal", copnDvDrmVal);
        reqParam.put("achldrNm", achldrNm);
        reqParam.put("systemDvCd", systemDvCd);
        reqParam.put("picId", picId);
        reqParam.put("deptId", deptId);

        // 2. 은행계좌 유효성검사 서비스 호출(Z-WD-S-0027)
        WwdaAutoTransferRealTimeAccountCheckDvo resultDvo = realTimeAccountService.saveAftnAcEftnChecks(reqParam);

        // 청구생성코드가 1이 아닐때 에러 발생
        // (1 : 정상처리, 2 : 오류, 3 : 자료없음)
        BizAssert.isTrue(resultDvo.getBilCrtStatCd() == "1", resultDvo.getErrCn());

        // 3. 수신결과 및 리턴 설정
        // 3.1.1 리턴 받은 값이 없거나 Null 인 경우 "0000" 셋팅
        String acFntRsCd = StringUtil.isNotEmpty(resultDvo.getAcFntRsCd()) ? resultDvo.getAcFntRsCd() : "0000";

        // 3.1 리턴받은 계좌이체불능코드 셋팅
        result.setAcFntRsCd(acFntRsCd);

        // 3.2 리턴받은 계좌이체불능코드에 해당하는 계좌이체결과코드 조회
        result.setAcFntRsCdNm(mapper.selectAutomaticTransferResultCodeName("VAC", acFntRsCd));

        resultDtos.add(result);

        return converter.mapRealNameCertificationDvoToWwdaAutoTransferRealNameCertificationRes(resultDtos);
    }

    /**
     * 자동이체 계좌 실명인증
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
        String bryyMm = dto.bryyMmdd(); /*생년월일*/
        String tmlNo = ""; /*단말기번호*/
        String trdAmt = "0"; /*거래금액*/
        String istmMcnt = "0"; /*할부개월*/
        String aprpsicNo = ""; /*승인담당자번호*/
        String systemDvCd = "E"; /*시스템구분코드*/

        // 2. 카드번호 유효성 검사 서비스 호출(Z-WD-S-0060)
        // TODO : 카드번호유효성검사 서비스 개발 이후 호출 필요

        // 3. 수신결과 및 리턴 설정
        String cardFntRsCd = "0000";

        // 3.1 리턴받은 카드이체결과코드 셋팅
        // 3.1.1 리턴 받은 값이 없거나 Null 인 경우 "0000" 셋팅
        result.setCardFntRsCd(cardFntRsCd);

        // 3.2 리턴받은 계좌이체불능코드에 해당하는 계좌이체결과코드 조회
        // 3.2.1 리턴 받은 값이 없거나 Null 인 경우 "0000" 셋팅
        result.setCardFntRsCdNm(mapper.selectAutomaticTransferResultCodeName("CARD", cardFntRsCd));

        // 3.3 카드사코드 조회
        // 3.3.1 신용카드BIN번호 조회
        ZwdbCreditcardDto.SearchInfoReq searchReq = ZwdbCreditcardDto.SearchInfoReq.builder().crdcdBinNo(crdcdNo)
            .build();
        List<ZwdbCreditcardDto.SearchInfoRes> binInfos = zwdbCreditcardMapper
            .selectCreditcardBinInfos(searchReq);

        if (!ObjectUtils.isEmpty(binInfos)) {
            result.setCdcoCd(binInfos.get(0).fnitCd());
            result.setCdcoNm(binInfos.get(0).fnitNm());
        }

        resultDtos.add(result);

        return converter.mapCardEffectivenessCheckDvoToWwdaAutoTransferRealNameCertificationRes(resultDtos);
    }

}
