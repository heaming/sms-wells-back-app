package com.kyowon.sms.wells.web.customer.contact.service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sflex.common.common.service.SujiewonService;
import com.kyowon.sms.common.web.customer.common.dvo.ZcsaCustomerInfoByEccDvo;
import com.kyowon.sms.common.web.customer.common.dvo.ZcsaCustomerInfoDvo;
import com.kyowon.sms.common.web.customer.common.dvo.ZcsaCustomerInfoReqDvo;
import com.kyowon.sms.common.web.customer.common.dvo.ZcsaCustomerInfoResDvo;
import com.kyowon.sms.common.web.customer.common.mapper.ZcsaCustomersMapper;
import com.kyowon.sms.common.web.customer.common.mapper.ZcscTermsMapper;
import com.kyowon.sms.common.web.customer.common.service.ZcsaCustomerInfoService;
import com.kyowon.sms.common.web.customer.common.service.ZcsaCustomersService;
import com.kyowon.sms.common.web.customer.contact.dvo.ZcsaCstCtplcBasDvo;
import com.kyowon.sms.common.web.customer.contact.mapper.ZcsaCustomerMapper;
import com.kyowon.sms.wells.web.customer.contact.converter.WcsaCustomerInterfaceConverter;
import com.kyowon.sms.wells.web.customer.contact.dto.WcsaCustomerInterfaceDto;
import com.kyowon.sms.wells.web.customer.contact.dto.WcsaCustomerInterfaceDto.SaveCustomerAgreementReq;
import com.kyowon.sms.wells.web.customer.contact.dto.WcsaCustomerInterfaceDto.SaveCustomerAgreementRes;
import com.kyowon.sms.wells.web.customer.contact.dto.WcsaCustomerInterfaceDto.SearchCustomerInfoReq;
import com.kyowon.sms.wells.web.customer.contact.dto.WcsaCustomerInterfaceDto.SearchCustomerRes;
import com.kyowon.sms.wells.web.customer.contact.dvo.WcsaCustomerAgreementDvo;
import com.kyowon.sms.wells.web.customer.contact.dvo.WcsaCustomerAgreementResultDvo;
import com.kyowon.sms.wells.web.customer.contact.dvo.WcsaInterfaceResultDvo;
import com.kyowon.sms.wells.web.customer.contact.mapper.WcsaCustomerMapper;
import com.kyowon.sms.wells.web.customer.zcommon.constants.CsCustomerConst;
import com.sds.sflex.common.utils.DateUtil;
import com.sds.sflex.common.utils.StringUtil;
import com.sds.sflex.system.config.context.SFLEXContextHolder;
import com.sds.sflex.system.config.core.dvo.UserSessionDvo;
import com.sds.sflex.system.config.core.service.MessageResourceService;
import com.sds.sflex.system.config.validation.BizAssert;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * <pre>
 * 고객 인터페이스 관리 - WELLS Service
 * </pre>
 *
 * @author jeongeon.kim
 * @since 2023-02-01
 */
@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class WcsaCustomerInterfaceService {
    private final ZcsaCustomerInfoService zcsaCustomerInfoService;
    private final MessageResourceService messageService;
    private final WcsaCustomerInterfaceConverter converter;

    private final WcsaCustomerMapper mapper;
    private final ZcscTermsMapper zcscTermsMapper;
    private final ZcsaCustomerMapper zcsaCustomerMapper;
    private final ZcsaCustomersMapper zcsaCustomersMapper;

    public static final String ERROR_MESSAGE = "MSG_ALT_SVE_ERR";

    private final ZcsaCustomersService zcsaCustomersService;

    private final SujiewonService sujiewonService;

    /**
    * 고객번호 기준으로 고객정보를 조회 - 고객번호에 해당하는 고객 기본/상세 정보 조회
    * @param dto 고객정보 조회 조건 (주요 PARAM: 고객번호 )
    * @return 고객 정보
    */
    public SearchCustomerRes getCustomerByCstNo(
        SearchCustomerInfoReq dto
    ) {
        // 응답코드
        WcsaInterfaceResultDvo ifResDvo = new WcsaInterfaceResultDvo();
        ifResDvo.setRsCd("S");//정상 처리되었습니다.
        ifResDvo.setRsMsg(null); //정상의 경우 결과코드(rsCd)만 송신한다.

        //  1. 필수값 체크(고객번호 → 필수값 체크)
        if (dto.cstNo().isEmpty()) {
            ifResDvo.setRsCd("F");
            Arrays.stream(dto.getClass().getDeclaredFields()).forEach(data -> {
                ifResDvo.setRsMsg(data.getName() + "가(이) 없습니다 !");
            });

            return converter.copy(ifResDvo);
        }

        //  2. 고객 조회 서비스 호출
        ZcsaCustomerInfoReqDvo param = converter.copy(dto);

        List<ZcsaCustomerInfoResDvo> pextCustomer = zcsaCustomerInfoService.getCustomers(param);
        if (pextCustomer.isEmpty()) {
            ifResDvo.setRsCd("S");//계약자 정보가 없습니다!
            ifResDvo.setRsMsg(null);//정상의 경우 결과코드(rsCd)만 송신한다.
            return converter.copy(ifResDvo);
        }

        //  3. 개인고객 / 법인고객 확인 (법인격구분에 따라 다르게 요청)
        if ("1".equals(pextCustomer.get(0).getCopnDvCd())) {
            //    - 개인고객 호출
            ZcsaCustomerInfoDvo indvCustomerDtl = zcsaCustomerInfoService
                .getIndvCustomerInfo(pextCustomer.get(0).getCstNo());
            return converter.mapCreateCustomerDtlToCustomerRes(indvCustomerDtl, ifResDvo);
        } else {
            //    - 법인고객 호출
            ZcsaCustomerInfoDvo crpCustomerDtl = zcsaCustomerInfoService
                .getCrpCustomerInfo(pextCustomer.get(0).getCstNo());
            return converter.mapCreateCustomerDtlToCustomerRes(crpCustomerDtl, ifResDvo);
        }

    }

    /**
    * 고객센터 Wells 계약고객 정보 변경 처리 서비스. 연관 I/F : EAI_WCUI1017
    * @param dto 고객정보
    * @return 고객번호, 결과
    */
    public WcsaCustomerInterfaceDto.SearchCustomerInfoEditRes editCustomerByCc(
        WcsaCustomerInterfaceDto.SearchCustomerInfoEditReq dto
    ) {

        WcsaInterfaceResultDvo ifResDvo = new WcsaInterfaceResultDvo();
        ifResDvo.setCstNo("");
        ifResDvo.setRsCd("");
        ifResDvo.setRsMsg(null);
        String cstNo = dto.cstNo();
        String copnDvCd = dto.copnDvCd();
        String calngDvCd = dto.calngDvCd();

        //  1. 필수값 체크(고객번호 → 필수값 체크)
        if (cstNo.isEmpty()) {
            ifResDvo.setRsCd("F");
            ifResDvo.setRsMsg("CST_NO 가(이) 없습니다 !");
            return converter.mapCustomerInfoEditToInterfaceResultRes(ifResDvo);
        }
        //  2. 필수값 체크(법인격구분코드 → 필수값 체크)
        if (copnDvCd.isEmpty()) {
            ifResDvo.setRsCd("F");
            ifResDvo.setRsMsg("COPN_DV_CD 가(이) 없습니다 !");
            return converter.mapCustomerInfoEditToInterfaceResultRes(ifResDvo);
        }
        //  3. 필수값 체크(호출구분코드 → 필수값 체크)
        if (calngDvCd.isEmpty()) {
            ifResDvo.setRsCd("F");
            ifResDvo.setRsMsg("CALNG_DV_CD 가(이) 없습니다 !");
            return converter.mapCustomerInfoEditToInterfaceResultRes(ifResDvo);
        }

        // 4. 고객등록여부 조회
        ZcsaCustomerInfoReqDvo indvDvo = new ZcsaCustomerInfoReqDvo();
        indvDvo.setSearchType("C01");
        indvDvo.setCstNo(dto.cstNo());

        List<ZcsaCustomerInfoResDvo> pextCustomer = zcsaCustomerInfoService.getCustomers(indvDvo);
        if (pextCustomer.isEmpty()) {
            ifResDvo.setCstNo(cstNo);
            ifResDvo.setRsCd("S");
            ifResDvo.setRsMsg("계약자 정보가 없습니다!");
            return converter.mapCustomerInfoEditToInterfaceResultRes(ifResDvo);
        }

        if (!copnDvCd.equals(pextCustomer.get(0).getCopnDvCd())) {
            ifResDvo.setCstNo(cstNo);
            ifResDvo.setRsCd("S");
            ifResDvo.setRsMsg("계약자 정보가 없습니다!");
            return converter.mapCustomerInfoEditToInterfaceResultRes(ifResDvo);
        }

        ZcsaCustomerInfoByEccDvo dvo = converter.mapCustomerInfoToCustomerInfoByEcc(dto);

        int response = editCustomerInfoByEcc(dvo);

        if (response == 1) {
            ifResDvo.setCstNo(cstNo);
            ifResDvo.setRsCd("S");
            ifResDvo.setRsMsg("정상 처리되었습니다");
            return converter.mapCustomerInfoEditToInterfaceResultRes(ifResDvo);
        } else {
            ifResDvo.setCstNo(cstNo);
            ifResDvo.setRsCd("F");
            ifResDvo.setRsMsg("고객정보 변경처리에 실패했습니다.");
            return converter.mapCustomerInfoEditToInterfaceResultRes(ifResDvo);

        }
    }

    /**
    * 고객센터 Wells 계약고객 정보 변경 처리 서비스. 연관 I/F : EAI_WCUI1017
    * @param dvo 고객정보
    * @return 결과
    */
    public int editCustomerInfoByEcc(ZcsaCustomerInfoByEccDvo dvo) {
        //휴대폰 번호, 주소, 변경요청확인여부, 변경요청서 확인일자, 특이사항내용, 등록수정사용자ID

        String strDate = DateUtil.getDate(DateUtil.getNow());
        String endDate = DateUtil.getDate(DateUtil.getNow() - 1000);
        String cstNo = dvo.getCstNo();
        String dtaDlYn = "Y";
        String itgCstNo;
        String rgstMdfcUsrId = dvo.getRgstMdfcUsrId();
        int result;
        // 고객정보변경
        result = mapper.updateIndvCstBasEai(dvo);
        BizAssert.isTrue(result > 0, ERROR_MESSAGE);
        // 고객기본이력 업데이트
        result = zcsaCustomerMapper.updateLastIndvCstBasInfoHistory(cstNo, endDate, rgstMdfcUsrId); // 고객기본이력 업데이트
        BizAssert.isTrue(result > 0, ERROR_MESSAGE);
        result = zcsaCustomerMapper.insertIndvCstBasInfoHistory(cstNo, strDate); // 고객정보변경이력 생성
        BizAssert.isTrue(result == 1, ERROR_MESSAGE);
        // 연락처-주소
        if (StringUtils.isNotEmpty(dvo.getAdrId())) {
            zcsaCustomerMapper.updateLastIndvCstAdrInfo(cstNo, endDate, dtaDlYn, rgstMdfcUsrId);
            ZcsaCstCtplcBasDvo indvCstAdrDvo = converter.mapCstCtplcBasToCustomerInfoByEcc(dvo);
            indvCstAdrDvo.setHistStrtDtm(strDate);
            indvCstAdrDvo.setCstCtplcOjRefkVal(cstNo);
            indvCstAdrDvo.setDtaDlYn("N");
            if ("2".equals(dvo.getCopnDvCd())) {
                indvCstAdrDvo.setCopnDvCd("2");
                indvCstAdrDvo.setCstCtplcOjDvCd("03");
                indvCstAdrDvo.setCtplcTpCd("02");
            }
            result = zcsaCustomerMapper.insertIndvCstAdrInfo(indvCstAdrDvo);
            BizAssert.isTrue(result == 1, ERROR_MESSAGE);
        }
        // 연락처-휴대폰
        if (StringUtils.isNotEmpty(dvo.getCralLocaraTno())
            && StringUtils.isNotEmpty(dvo.getMexno())
            && StringUtils.isNotEmpty(dvo.getCralIdvTno())) {
            zcsaCustomerMapper.updateLastIndvCstMpnoInfo(cstNo, endDate, dtaDlYn, rgstMdfcUsrId);
            ZcsaCstCtplcBasDvo indvCstHpnoDvo = converter.mapCstCtplcBasToCustomerInfoByEcc(dvo);
            indvCstHpnoDvo.setHistStrtDtm(strDate);
            indvCstHpnoDvo.setCstCtplcOjRefkVal(cstNo);
            indvCstHpnoDvo.setDtaDlYn("N");
            indvCstHpnoDvo.setLocaraTno(dvo.getCralLocaraTno());
            indvCstHpnoDvo.setExnoEncr(dvo.getMexno());
            indvCstHpnoDvo.setIdvTno(dvo.getCralIdvTno());
            if ("2".equals(dvo.getCopnDvCd())) {
                indvCstHpnoDvo.setCopnDvCd("2");
                indvCstHpnoDvo.setCstCtplcOjDvCd("03");
                indvCstHpnoDvo.setCtplcTpCd("03");
            }
            result = zcsaCustomerMapper.insertIndvCstMpnoInfo(indvCstHpnoDvo);
            BizAssert.isTrue(result == 1, ERROR_MESSAGE);
        }
        // 연락처-휴대폰-법인
        if (StringUtils.isNotEmpty(dvo.getLocaraTno())
            && StringUtils.isNotEmpty(dvo.getExno())
            && StringUtils.isNotEmpty(dvo.getIdvTno())
            && "2".equals(dvo.getCopnDvCd())) {
            ZcsaCstCtplcBasDvo indvCrpHpnoDvo = converter.mapCstCtplcBasToCustomerInfoByEcc(dvo);
            mapper.updateLastCrpCstMpnoInfo(cstNo, endDate, dtaDlYn, rgstMdfcUsrId);
            indvCrpHpnoDvo.setLocaraTno(dvo.getLocaraTno());
            indvCrpHpnoDvo.setExnoEncr(dvo.getExno());
            indvCrpHpnoDvo.setIdvTno(dvo.getIdvTno());
            indvCrpHpnoDvo.setHistStrtDtm(strDate);
            indvCrpHpnoDvo.setCstCtplcOjRefkVal(cstNo);
            indvCrpHpnoDvo.setDtaDlYn("N");
            indvCrpHpnoDvo.setRgstMdfcUsrId(rgstMdfcUsrId);

            result = mapper.insertCrpCstMpnoInfo(indvCrpHpnoDvo);
            BizAssert.isTrue(result == 1, ERROR_MESSAGE);
        }
        if ("1".equals(dvo.getCopnDvCd())) {
            itgCstNo = zcsaCustomersMapper.selectItgCstNo(cstNo);
            // 통합고객 존재시
            dvo.setItgCstNo(itgCstNo);
            if (StringUtil.isNotEmpty(itgCstNo)) {
                int resultItg = mapper.updateItgCstBasEai(dvo);
                BizAssert.isTrue(resultItg > 0, ERROR_MESSAGE);
                int resultItgHis = zcscTermsMapper
                    .updateIntegratedCustomerInfoHistory(itgCstNo, endDate, rgstMdfcUsrId);
                BizAssert.isTrue(resultItgHis > 0, ERROR_MESSAGE);
                int resultInsItgHis = zcscTermsMapper.insertIntegratedCustomerInfoHistory(itgCstNo, strDate);
                BizAssert.isTrue(resultInsItgHis > 0, ERROR_MESSAGE);
            }
        } else {
            int resultCrp = mapper.updateCrpCstBasEai(dvo);
            BizAssert.isTrue(resultCrp > 0, ERROR_MESSAGE);
        }
        return 1;
    }

    /**
    * wells 사업본부 동의정보 등록/변경(IF ID:EAI_WCUI1009)
    * @param dto 고객정보
    * @return 결과
    */
    @Transactional
    public SaveCustomerAgreementRes saveCustomerAgreements(SaveCustomerAgreementReq dto) {

        // 0. Session 설정
        UserSessionDvo userSession = SFLEXContextHolder.getContext().getUserSession();
        if (userSession != null) {
            if (StringUtils.isBlank(Objects.toString(userSession.getLangId(), ""))) {
                userSession.setLangId(CsCustomerConst.IF_DEFAULT_LANG_ID);
            }
        }

        // 1. 고객동의 정보 설정
        WcsaCustomerAgreementDvo agreeDvo = converter.mapSaveCustomerAgreementReqToWcsaCustomerAgreementDvo(dto);

        // 2. 고객 유효성 체크
        String isExistCustomer = mapper.selectCustomerExistYn(agreeDvo.getCstNo());
        if (StringUtils.equals(Objects.toString(isExistCustomer, "N"), "N")) { // 고객정보가 존재하지 않는 경우, return
            WcsaCustomerAgreementResultDvo resultDvo = new WcsaCustomerAgreementResultDvo(
                false, CsCustomerConst.IF_RETURN_CODE_SUCCESS, messageService.getMessage("MSG_ALT_CST_INF_NOT_EXST")
            );
            return converter.mapWcsaCustomerAgreementResultDvoToSaveCustomerAgreementRes(resultDvo);
        }

        // 3. 기존 동의내역 조회
        String preCstAgId = mapper.selectCustomerRecentAgreement(agreeDvo.getCstNo());

        // 4. 고객동의내역 등록
        Map<String, String> agAtcDvCdMap = agreeDvo.getAgAtcDvCdMap();
        if (agAtcDvCdMap != null && !agAtcDvCdMap.isEmpty()) {
            // 4.1. 고객동의내역 Insert
            mapper.insertCustomerAgreement(agreeDvo);

            // 4.2. 고객동의내역상세 Insert
            for (String agAtcDvCd : agAtcDvCdMap.keySet()) {
                if (StringUtils.equals(agAtcDvCdMap.get(agAtcDvCd), "Y")
                    || StringUtils.equals(agAtcDvCdMap.get(agAtcDvCd), "N")) {
                    agreeDvo.setAgAtcDvCd(agAtcDvCd);
                    agreeDvo.setAgYn(agAtcDvCdMap.get(agAtcDvCd));
                    agreeDvo.setPrvDocId(agreeDvo.getPrvCn());
                    mapper.insertCustomerAgreementDetail(agreeDvo);
                }
            }
        } else {
            WcsaCustomerAgreementResultDvo resultDvo = new WcsaCustomerAgreementResultDvo(
                false, CsCustomerConst.IF_RETURN_CODE_SUCCESS, messageService.getMessage("MSG_ALT_AG_INF_NOT_EXST")
            );
            return converter.mapWcsaCustomerAgreementResultDvoToSaveCustomerAgreementRes(resultDvo);
        }

        // 5. 기존 동의내역 만료처리 - 기존 동의내역이 존재하는 경우
        if (StringUtils.isNotBlank(Objects.toString(preCstAgId, ""))) {
            // 5.1. 고객동의내역 만료처리 Update
            mapper.updateCustomerAgreementExpiration(preCstAgId);

            // 5.2. 고객동의내역상세 만료처리 Update
            mapper.updateCustomerAgreementDetailExpiration(preCstAgId);
        }

        // 6. 정상완료처리
        WcsaCustomerAgreementResultDvo resultDvo = new WcsaCustomerAgreementResultDvo(
            true, CsCustomerConst.IF_RETURN_CODE_SUCCESS, messageService.getMessage("MSG_ALT_PRGS_OK")
        );
        return converter.mapWcsaCustomerAgreementResultDvoToSaveCustomerAgreementRes(resultDvo);

    }

}
