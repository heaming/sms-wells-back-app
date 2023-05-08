package com.kyowon.sms.wells.web.customer.contact.service;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.common.web.customer.common.dvo.ZcsaCustomerInfoByEccDvo;
import com.kyowon.sms.common.web.customer.common.dvo.ZcsaCustomerInfoDvo;
import com.kyowon.sms.common.web.customer.common.dvo.ZcsaCustomerInfoReqDvo;
import com.kyowon.sms.common.web.customer.common.dvo.ZcsaCustomerInfoResDvo;
import com.kyowon.sms.common.web.customer.common.mapper.ZcsaCustomersMapper;
import com.kyowon.sms.common.web.customer.common.mapper.ZcscTermsMapper;
import com.kyowon.sms.common.web.customer.common.service.ZcsaCustomerInfoService;
import com.kyowon.sms.common.web.customer.contact.dvo.ZcsaCstCtplcBasDvo;
import com.kyowon.sms.common.web.customer.contact.mapper.ZcsaCustomerMapper;
import com.kyowon.sms.wells.web.customer.contact.converter.WcsaCustomerInterfaceConverter;
import com.kyowon.sms.wells.web.customer.contact.dto.WcsaCustomerInterfaceDto;
import com.kyowon.sms.wells.web.customer.contact.dto.WcsaCustomerInterfaceDto.SearchCustomerInfoReq;
import com.kyowon.sms.wells.web.customer.contact.dto.WcsaCustomerInterfaceDto.SearchCustomerRes;
import com.kyowon.sms.wells.web.customer.contact.dvo.WcsaInterfaceResultDvo;
import com.kyowon.sms.wells.web.customer.contact.mapper.WcsaCustomerMapper;
import com.sds.sflex.common.utils.DateUtil;
import com.sds.sflex.common.utils.StringUtil;
import com.sds.sflex.system.config.validation.BizAssert;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class WcsaCustomerInterfaceService {
    private final ZcsaCustomerInfoService zcsaCustomerInfoService;
    private final WcsaCustomerInterfaceConverter converter;

    private final WcsaCustomerMapper mapper;
    private final ZcscTermsMapper zcscTermsMapper;
    private final ZcsaCustomerMapper zcsaCustomerMapper;
    private final ZcsaCustomersMapper zcsaCustomersMapper;

    /**
    * 고객번호 기준으로 고객정보를 조회 - 고객번호에 해당하는 고객 기본/상세 정보 조회
    * @param dto
    * @return
    */
    public SearchCustomerRes getCustomerByCstNo(
        SearchCustomerInfoReq dto
    ) {
        // 응답코드
        WcsaInterfaceResultDvo ifResDvo = new WcsaInterfaceResultDvo();
        ifResDvo.setRsCd("S");//정상 처리되었습니다.
        ifResDvo.setRsMsg(null); //정상의 경우 결과코드(rsCd)만 송신한다.

        //  1. 필수값 체크(고객번호 → 필수값 체크)
        if (dto.CST_NO().isEmpty()) {
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

    public WcsaCustomerInterfaceDto.SearchCustomerInfoEditRes editCustomerByCc(
        WcsaCustomerInterfaceDto.SearchCustomerInfoEditReq dto
    ) {
        log.info("dto.CALNG_DV_CD" + dto.CALNG_DV_CD());
        log.info("dto.COPN_DV_CD" + dto.COPN_DV_CD());
        log.info("dto.cstno" + dto.CST_NO());
        log.info("dto.LOCARA_TNO" + dto.LOCARA_TNO());
        log.info("dto.EXNO" + dto.EXNO());
        log.info("dto.IDV_TNO" + dto.IDV_TNO());
        log.info("dto.CRAL_LOCARA_TNO" + dto.CRAL_LOCARA_TNO());
        log.info("dto.MEXNO" + dto.MEXNO());
        log.info("dto.CRAL_IDV_TNO" + dto.CRAL_IDV_TNO());
        log.info("dto.ADR_ID" + dto.ADR_ID());
        log.info("dto.ADR_DV_CD" + dto.ADR_DV_CD());
        log.info("dto.CH_LTRQ_CONF_YN" + dto.CH_LTRQ_CONF_YN());
        log.info("dto.CH_LTRQ_CONF_DT" + dto.CH_LTRQ_CONF_DT());
        log.info("dto.UNUITM_CN" + dto.UNUITM_CN());
        log.info("dto.RGST_MDFC_USR_ID" + dto.RGST_MDFC_USR_ID());

        WcsaInterfaceResultDvo ifResDvo = new WcsaInterfaceResultDvo();
        ifResDvo.setCstNo("");
        ifResDvo.setRsCd("");
        ifResDvo.setRsMsg(null);
        String cstNo = dto.CST_NO();
        String copnDvCd = dto.COPN_DV_CD();
        String calngDvCd = dto.CALNG_DV_CD();

        //  1. 필수값 체크(호출구분코드 → 필수값 체크)
        if (cstNo.isEmpty()) {
            ifResDvo.setRsCd("F");
            Arrays.stream(dto.getClass().getDeclaredFields()).forEach(data -> {
                ifResDvo.setRsMsg(data.getName() + "가(이) 없습니다 !");
            });
            log.info("필수값 고객번호가 없습니다.");
            return converter.mapCustomerInfoEditToInterfaceResultRes(ifResDvo);
        }
        //  2. 필수값 체크(법인격구분코드 → 필수값 체크)
        if (copnDvCd.isEmpty()) {
            ifResDvo.setRsCd("F");
            Arrays.stream(dto.getClass().getDeclaredFields()).forEach(data -> {
                ifResDvo.setRsMsg(data.getName() + "가(이) 없습니다 !");
            });
            log.info("필수값 법인격구분코드가 없습니다.");
            return converter.mapCustomerInfoEditToInterfaceResultRes(ifResDvo);
        }
        //  3. 필수값 체크(고객번호 → 필수값 체크)
        if (calngDvCd.isEmpty()) {
            ifResDvo.setRsCd("F");
            Arrays.stream(dto.getClass().getDeclaredFields()).forEach(data -> {
                ifResDvo.setRsMsg(data.getName() + "가(이) 없습니다 !");
            });
            log.info("호출구분코드가 없거나 업데이트 코드값이 아닙니다.");
            return converter.mapCustomerInfoEditToInterfaceResultRes(ifResDvo);
        }

        // 4. 고객등록여부 조회
        ZcsaCustomerInfoReqDvo indvDvo = new ZcsaCustomerInfoReqDvo();
        indvDvo.setSearchType("C01");
        indvDvo.setCstNo(dto.CST_NO());

        List<ZcsaCustomerInfoResDvo> pextCustomer = zcsaCustomerInfoService.getCustomers(indvDvo);

        if (pextCustomer.isEmpty()) {
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

    public int editCustomerInfoByEcc(ZcsaCustomerInfoByEccDvo dvo) {
        //휴대폰 번호, 주소, 변경요청확인여부, 변경요청서 확인일자, 특이사항내용, 등록수정사용자ID
        log.info("dto.CALNG_DV_CD" + dvo.getCalngDvCd());
        log.info("dto.COPN_DV_CD" + dvo.getCopnDvCd());
        log.info("dto.cstno" + dvo.getCstNo());
        log.info("dto.LOCARA_TNO" + dvo.getLocaraTno());
        log.info("dto.EXNO" + dvo.getExno());
        log.info("dto.IDV_TNO" + dvo.getIdvTno());
        log.info("dto.CRAL_LOCARA_TNO" + dvo.getCralLocaraTno());
        log.info("dto.MEXNO" + dvo.getMexno());
        log.info("dto.CRAL_IDV_TNO" + dvo.getCralIdvTno());
        log.info("dto.ADR_ID" + dvo.getAdrId());
        log.info("dto.ADR_DV_CD" + dvo.getAdrDvCd());
        log.info("dto.CH_LTRQ_CONF_YN" + dvo.getChLtrqConfYn());
        log.info("dto.CH_LTRQ_CONF_DT" + dvo.getChLtrqConfDt());
        log.info("dto.UNUITM_CN" + dvo.getUnuitmCn());
        log.info("dto.RGST_MDFC_USR_ID" + dvo.getRgstMdfcUsrId());

        String strDate = DateUtil.getDate(DateUtil.getNow());
        String endDate = DateUtil.getDate(DateUtil.getNow() - 1000);
        String cstNo = dvo.getCstNo();
        String dtaDlYn = "Y";
        String itgCstNo;
        int result;
        // 고객정보변경
        result = mapper.updateIndvCstBasEai(dvo);
        BizAssert.isTrue(result > 0, "MSG_ALT_SVE_ERR");
        // 고객기본이력 업데이트
        result = zcsaCustomerMapper.updateLastIndvCstBasInfoHistory(cstNo, endDate); // 고객기본이력 업데이트
        BizAssert.isTrue(result > 0, "MSG_ALT_SVE_ERR");
        result = zcsaCustomerMapper.insertIndvCstBasInfoHistory(cstNo, strDate); // 고객정보변경이력 생성
        BizAssert.isTrue(result == 1, "MSG_ALT_SVE_ERR");
        // 연락처-주소
        if (StringUtils.isNotEmpty(dvo.getAdrId())) {
            zcsaCustomerMapper.updateLastIndvCstAdrInfo(cstNo, endDate, dtaDlYn);
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
            BizAssert.isTrue(result == 1, "MSG_ALT_SVE_ERR");
        }
        // 연락처-휴대폰
        if (StringUtils.isNotEmpty(dvo.getCralLocaraTno())
            && StringUtils.isNotEmpty(dvo.getMexno())
            && StringUtils.isNotEmpty(dvo.getCralIdvTno())) {
            zcsaCustomerMapper.updateLastIndvCstMpnoInfo(cstNo, endDate, dtaDlYn);
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
            BizAssert.isTrue(result == 1, "MSG_ALT_SVE_ERR");
        }
        // 연락처-휴대폰-법인
        if (StringUtils.isNotEmpty(dvo.getLocaraTno())
            && StringUtils.isNotEmpty(dvo.getExno())
            && StringUtils.isNotEmpty(dvo.getIdvTno())
            && "2".equals(dvo.getCopnDvCd())) {
            ZcsaCstCtplcBasDvo indvCrpHpnoDvo = converter.mapCstCtplcBasToCustomerInfoByEcc(dvo);
            mapper.updateLastCrpCstMpnoInfo(cstNo, endDate, dtaDlYn);
            indvCrpHpnoDvo.setLocaraTno(dvo.getLocaraTno());
            indvCrpHpnoDvo.setExnoEncr(dvo.getExno());
            indvCrpHpnoDvo.setIdvTno(dvo.getIdvTno());
            indvCrpHpnoDvo.setHistStrtDtm(strDate);
            indvCrpHpnoDvo.setCstCtplcOjRefkVal(cstNo);
            indvCrpHpnoDvo.setDtaDlYn("N");

            result = mapper.insertCrpCstMpnoInfo(indvCrpHpnoDvo);
            BizAssert.isTrue(result == 1, "MSG_ALT_SVE_ERR");
        }
        if ("1".equals(dvo.getCopnDvCd())) {
            itgCstNo = zcsaCustomersMapper.selectItgCstNo(cstNo);
            // 통합고객 존재시
            dvo.setItgCstNo(itgCstNo);
            if (StringUtil.isNotEmpty(itgCstNo)) {
                int resultItg = mapper.updateItgCstBasEai(dvo);
                BizAssert.isTrue(resultItg > 0, "MSG_ALT_SVE_ERR");
                int resultItgHis = zcscTermsMapper.updateIntegratedCustomerInfoHistory(itgCstNo, endDate);
                BizAssert.isTrue(resultItgHis > 0, "MSG_ALT_SVE_ERR");
                int resultInsItgHis = zcscTermsMapper.insertIntegratedCustomerInfoHistory(itgCstNo, strDate);
                BizAssert.isTrue(resultInsItgHis > 0, "MSG_ALT_SVE_ERR");
            }
        } else {
            int resultCrp = mapper.updateCrpCstBasEai(dvo);
            BizAssert.isTrue(resultCrp > 0, "MSG_ALT_SVE_ERR");
        }
        return 1;
    }
}
