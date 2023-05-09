package com.kyowon.sms.wells.web.withdrawal.interfaces.service;

import java.util.ArrayList;
import java.util.List;

import com.kyowon.sms.wells.web.withdrawal.interfaces.dvo.WwdaAutoTransferInfoBundleRegistrationReleasesInterfaceDvo;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.kyowon.sms.common.web.withdrawal.bilfnt.dvo.ZwdaBundleWithdrawalMgtDvo;
import com.kyowon.sms.common.web.withdrawal.bilfnt.dvo.ZwdaIntegrationBillingIzDvo;
import com.kyowon.sms.common.web.withdrawal.bilfnt.mapper.ZwdaBundleWithdrawalMgtMapper;
import com.kyowon.sms.wells.web.withdrawal.interfaces.converter.WwdaAutoTransferConverter;
import com.kyowon.sms.wells.web.withdrawal.interfaces.dto.WwdaAutoTransferInterfaceDto;
import com.kyowon.sms.wells.web.withdrawal.interfaces.dvo.WwdaAutoTransferInfoEvidenceInfoInterfaceDvo;
import com.kyowon.sms.wells.web.withdrawal.interfaces.dvo.WwdaAutoTransferInfoInterfaceDvo;
import com.kyowon.sms.wells.web.withdrawal.interfaces.dvo.WwdaAutoTransferObjectItemizationInterfaceDvo;
import com.kyowon.sms.wells.web.withdrawal.interfaces.mapper.WwdaAutoTransferInterfaceMapper;
import com.sds.sflex.system.config.core.service.MessageResourceService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WwdaAutoTransferInterfaceService {

    private final WwdaAutoTransferInterfaceMapper mapper;
    private final ZwdaBundleWithdrawalMgtMapper zwdaBundleMapper;
    private final MessageResourceService messageResourceService;
    private final WwdaAutoTransferConverter converter;

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
                if ("S".equals(reslCd)) {
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

}
