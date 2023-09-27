package com.kyowon.sms.wells.web.kakaosync.common.service;

import com.kyowon.sflex.common.common.dvo.FormatAddressDvo;
import com.kyowon.sflex.common.common.service.SujiewonService;
import com.kyowon.sflex.common.zcommon.constants.CmSujiewonConst;
import com.kyowon.sms.wells.web.customer.common.dvo.WcszPartnerDvo;
import com.kyowon.sms.wells.web.customer.common.service.WcszPartnerSearchService;
import com.kyowon.sms.wells.web.customer.prospective.dto.WcsbProspecCustomerMgtDto;
import com.kyowon.sms.wells.web.customer.prospective.dvo.WcsbProspecCustomerDvo;
import com.kyowon.sms.wells.web.customer.prospective.dvo.WcsbTbSsopPspcCstDdlvHistDvo;
import com.kyowon.sms.wells.web.kakaosync.common.converter.KakaoWcsbProspecCustomerMgtConverter;
import com.kyowon.sms.wells.web.kakaosync.common.dto.KakaoWcsbProspecCustomerMgtDto;
import com.kyowon.sms.wells.web.kakaosync.common.dvo.KakaoWcsbSyncDefaultDvo;
import com.kyowon.sms.wells.web.kakaosync.common.mapper.KakaoFrdnCustMapper;
import com.kyowon.sms.wells.web.kakaosync.zcommon.kakaosyncutils.AES256Util;
import com.sds.sflex.common.portal.service.PortalSessionService;
import com.sds.sflex.common.utils.DateUtil;
import com.sds.sflex.common.utils.DbEncUtil;
import com.sds.sflex.system.config.exception.BizException;
import com.sds.sflex.system.config.validation.BizAssert;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class KakaoWcsbProspecCustomerMgtService {
    private final KakaoWcsbProspecCustomerMgtConverter converter;
    private final WcszPartnerSearchService partnerSearchService;
    private final SujiewonService addressService;
    private final KakaoFrdnCustMapper kakaoFrdnCustMapper;
    private final PortalSessionService portalSessionService;

    /**
     * 고객 DB 목록 저장(추가)
     * @param dto
     * @return
     * @throws Exception
     */
    @Transactional
    public int saveProspecCustomers(KakaoWcsbProspecCustomerMgtDto.SaveReq dto) throws Exception {

        // 미인증 세션 처리
        portalSessionService.makeAnonymousSession();

        int processCount = 0;

        WcszPartnerDvo partnerVo = partnerSearchService.getPartnerByPk(null, dto.employee_id());
        String startDtm = DateUtil.getDate(new Date());

        // 파트너 번호 정상 셋팅 됐는지 확인 (웹)
        BizAssert.hasText(dto.employee_id(), "파트너 번호를 조회할 수 없습니다.");

        // 사번 조회 후 예외 처리
        BizAssert.hasText(partnerVo.getEpNo(), "사번을 조회할 수 없습니다. 사번을 확인해 주세요.");

        //사번과 신청자 연락처 같은지 확인
        if (partnerVo.getOgId() != null) {
            String akdhpno = AES256Util
                .castString(partnerVo.getEpMpNo1() + DbEncUtil.dec(partnerVo.getEpMpNo2()) + partnerVo.getEpMpNo3());
            BizAssert.isFalse(dto.phoneNumber().equals(akdhpno), "담당EP 휴대전화 번호와  동일한 번호는 등록할 수 없습니다.");
        }

        WcsbTbSsopPspcCstDdlvHistDvo teacherVo = new WcsbTbSsopPspcCstDdlvHistDvo();

        //  카카오싱크 + default dvo
        KakaoWcsbSyncDefaultDvo defaultdvo = converter.mapSaveReqToKakaoWcsbSyncDefaultDvo(dto);

        //  가망고객기본 dvo
        WcsbProspecCustomerDvo dvo = new WcsbProspecCustomerDvo();

        /**
         * #0. 카카오싱크 기본 ()
         */

        String clphNo = dto.phoneNumber().replace("-", "");
        String clphNo1, clphNo2, clphNo3;

        log.info(clphNo);

        if (clphNo.length() == 10) {
            clphNo1 = clphNo.substring(0, 3);
            clphNo2 = DbEncUtil.enc(clphNo.substring(3, 6));
            clphNo3 = clphNo.substring(6, 10);
        } else if (clphNo.length() == 11) {
            clphNo1 = clphNo.substring(0, 3);
            clphNo2 = DbEncUtil.enc(clphNo.substring(3, 7));
            clphNo3 = clphNo.substring(7, 11);
        } else {
            throw new BizException("휴대전화 번호 오류");
        }

        // 카카오 Dvo 상수값 Setting
        defaultdvo.setInPath("J");
        defaultdvo.setAkdGub("7");
        defaultdvo.setCustWcde(dto.employee_id());
        defaultdvo.setCustUcde(dto.employee_id());
        defaultdvo.setCustHnO1(clphNo1);
        defaultdvo.setCustHnO2(clphNo2);
        defaultdvo.setCustHnO3(clphNo3);
        defaultdvo.setFrdnsrNo("000");
        defaultdvo.setPrensrNo("078");
        defaultdvo.setCustWdsp("");
        defaultdvo.setCustUdsp("");
        defaultdvo.setK_Coddan(partnerVo.getDtrcNd());
        defaultdvo.setAkdcde(dto.employee_id());

        /**
         * #1. 가망고객기본
         */

        dvo.setBasAdr(dto.baseAddress());
        dvo.setDtlAdr(dto.detailAddress());
        dvo.setIchrPrtnrNo(dto.employee_id());
        dvo.setBryyMmdd((dto.birthyear() + dto.birthday()).replace("-", ""));
        dvo.setCralLocaraTno(clphNo1);
        dvo.setMexnoEncr(clphNo2);
        dvo.setCralIdvTno(clphNo3);
        dvo.setEmadr(dto.email());
        dvo.setSexDvCd(dto.gender().equals("M") ? "1" : "2");
        dvo.setPspcCstKnm(dto.nickname());
        dvo.setPspcCstInflwPhCd(defaultdvo.getInPath());
        dvo.setFreDgnsTpCd(defaultdvo.getFrdnsrNo());
        dvo.setHgrFreDgnsTpCd(defaultdvo.getPrensrNo());
        dvo.setPspcCstDivDvCd(defaultdvo.getAkdGub());
        dvo.setOtsdChnlSpmtYn(dto.channelYn());
        dvo.setOtsdLkDrmVal(dto.auid());
        dvo.setIchrOgTpCd("W01");
        dvo.setNatCd("KR");
        dvo.setCopnDvCd("1");
        dvo.setPspcCstTpCd("20");
        dvo.setPspcCstFtfYn("N");
        dvo.setOgAsnStatCd("3");
        processCount = saveSmplJoinCust(dvo, defaultdvo, startDtm);

        /**
         * #2. 교사정보 (배부정보)
         */

        // 공통값 할당
        teacherVo.setPspcCstId(dvo.getPspcCstId());
        teacherVo.setPspcCstTpCd(dvo.getPspcCstTpCd());
        teacherVo.setAsnFshDtm(DateUtil.getDate(new Date())); // 배정완료일시
        teacherVo.setOgAsnStatCd("1");
        teacherVo.setOgTpCd("W01");

        // 센터장 (상위직급자)
        teacherVo.setOgAsnStatCd("1");
        teacherVo.setPrtnrNo(partnerVo.getCnrNo());
        savePspcCstDdlvHist(teacherVo);

        return processCount;
    }

    /**
     *
     * 가망고객기본 테이블에 insert 한다.
     * @param dvo, defaultdvo, startDtm
     * @return java.lang.String
     *
     */
    @Transactional
    public int saveSmplJoinCust(WcsbProspecCustomerDvo dvo, KakaoWcsbSyncDefaultDvo defaultdvo, String startDtm)
        throws Exception {

        int processCount = 0;

        // 사용자 입력 주소-데이터 저장.
        String address = dvo.getBasAdr() + dvo.getDtlAdr();

        FormatAddressDvo addrDvo = addressService
            .getFormattedAddress(address, CmSujiewonConst.FORMAT_TYPE_ROAD_ADDRESS);

        dvo.setAdrId(addrDvo.getAdrId());

        Map<String, String> strMap = new HashMap<String, String>();

        strMap.put("CUSTHNO1", defaultdvo.getCustHnO1());
        strMap.put("CUSTHNO2", defaultdvo.getCustHnO2());
        strMap.put("CUSTHNO3", defaultdvo.getCustHnO3());
        strMap.put("userId", defaultdvo.getAkdcde());

        // 교원키가 존재 하면 교원키 추가 저장 (To-Be Table)
        String kwKey = kakaoFrdnCustMapper.selectKwkey(dvo); //custkey
        defaultdvo.setKywonKey(kwKey);

        // 전화번호로 기존 고객여부 판단
        int CusthnoExsn = kakaoFrdnCustMapper.selectCusthnoExsn(defaultdvo);
        if (CusthnoExsn != 0) {
            defaultdvo.setCustGubn("EXS"); // 기존고객
        } else {
            defaultdvo.setCustGubn("NEW"); // 신규고객
        }

        // 가망고객 등록
        processCount += kakaoFrdnCustMapper.insertProspectCustomer(dvo);
        kakaoFrdnCustMapper.insertProspectCustomerHistory(dvo.getPspcCstId(), startDtm);

        return processCount;
    }

    /**
     * 가망고객배부이력 저장
     * 저장 부분 관련
     * @param teacherVo
     * @return
     * @throws Exception
     */
    @Transactional
    public int savePspcCstDdlvHist(WcsbTbSsopPspcCstDdlvHistDvo teacherVo) {
        int processCount = 0;
        processCount += kakaoFrdnCustMapper.insertPspcCstDdlvHist(teacherVo);
        return processCount;
    }

}
