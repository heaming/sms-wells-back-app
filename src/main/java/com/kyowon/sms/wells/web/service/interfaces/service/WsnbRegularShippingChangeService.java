package com.kyowon.sms.wells.web.service.interfaces.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import com.kyowon.sflex.common.message.dvo.SmsSendReqDvo;
import com.kyowon.sflex.common.message.service.SmsMessageService;
import com.kyowon.sms.wells.web.contract.changeorder.dto.WctbSeedingPackageChangeDto;
import com.kyowon.sms.wells.web.contract.changeorder.service.WctbSeedingPackageChangeService;
import com.kyowon.sms.wells.web.service.allocate.dto.WsncRegularBfsvcAsnDto;
import com.kyowon.sms.wells.web.service.allocate.service.WsncRegularBfsvcAsnService;
import com.kyowon.sms.wells.web.service.interfaces.dto.WsnbRegularShippingChangeDto.SaveReq;
import com.kyowon.sms.wells.web.service.interfaces.dto.WsnbRegularShippingChangeDto.SaveRes;
import com.kyowon.sms.wells.web.service.interfaces.dvo.WsniSidingServiceChangesDvo;
import com.kyowon.sms.wells.web.service.interfaces.mapper.WsnbRegularShippingChangeMapper;
import com.kyowon.sms.wells.web.service.interfaces.mapper.WsniSidingServiceChangesMapper;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbCustomerRglrBfsvcDlDto;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbIndividualVisitPrdDto;
import com.kyowon.sms.wells.web.service.visit.service.WsnbCustomerRglrBfsvcDlService;
import com.kyowon.sms.wells.web.service.visit.service.WsnbIndividualVisitPrdService;
import com.sds.sflex.common.utils.StringUtil;
import com.sds.sflex.system.config.context.SFLEXContextHolder;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * W-SV-I-0016 정기배송 변경
 *
 * @author gs.piit122 김동엽
 * @since 2023-04-13
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class WsnbRegularShippingChangeService {

    private final WsnbRegularShippingChangeMapper regularShippingChangeMapper;

    private final WsniSidingServiceChangesMapper sidingServiceChangesMapper;

    private final WsnbIndividualVisitPrdService individualVisitPrdService;

    private final WsnbCustomerRglrBfsvcDlService customerRglrBfsvcDlService;

    private final WsncRegularBfsvcAsnService regularBfsvcAsnService;

    private final SmsMessageService smsMessageService;

    private final WctbSeedingPackageChangeService seedingPackageChangeService;

    /**
    * <pre>
    * PR_KIWI_CAPSULE_CHANGE 홈카페 캡슐 패키지/서비스 변경 처리
    * </pre>
    * */
    public void capsuleChange(SaveReq req) throws Exception {

        log.debug("cntrNo : " + req.cntrNo());
        log.debug("cntrSn : " + req.cntrSn());
        log.debug("asAkDvCd : " + req.asAkDvCd());
        log.debug("akChdt : " + req.akChdt());
        log.debug("bfchPdCd : " + req.bfchPdCd());
        log.debug("afchPdCd : " + req.afchPdCd());
        log.debug("choCapslCn : " + req.choCapslCn());
        log.debug("mtrProcsStatCd : " + req.mtrProcsStatCd());
        log.debug("rcpIchrPrtnrNo: " + req.rcpIchrPrtnrNo());
        log.debug("rcpOgTpCd : " + req.rcpOgTpCd());

        // 취소일 경우 삭제
        if ("3".equals(req.mtrProcsStatCd())) {
            regularShippingChangeMapper.deleteTbSvpdHcfAsAkIz(req);

        } else {
            // 해당 키로 존재하는지 체크
            if (regularShippingChangeMapper.countTbSvpdHcfAsAkIz(req) > 0) {
                regularShippingChangeMapper.updateTbSvpdHcfAsAkIz(req);
            } else {
                req = new SaveReq(
                    req.cntrNo(),
                    req.cntrSn(),
                    req.asAkDvCd(),
                    req.akChdt(),
                    req.bfchPdCd(),
                    req.afchPdCd(),
                    req.choCapslCn(),
                    req.mtrProcsStatCd(),
                    RandomStringUtils.randomNumeric(6),
                    req.rcpIchrPrtnrNo(),
                    req.rcpOgTpCd()
                );
                regularShippingChangeMapper.insertTbSvpdHcfAsAkIz(req);
            }
        }

        // 홈카페AS요청이력
        regularShippingChangeMapper.insertTbSvpdHcfAsAkHist(
            new SaveReq(
                req.cntrNo(),
                req.cntrSn(),
                req.asAkDvCd(),
                req.akChdt(),
                req.bfchPdCd(),
                req.afchPdCd(),
                req.choCapslCn(),
                req.mtrProcsStatCd(),
                RandomStringUtils.randomNumeric(6),
                req.rcpIchrPrtnrNo(),
                req.rcpOgTpCd()
            )
        );

        // 요청 구분에 따라 처리 - 1: 패키지변경, 4:다음회차 방문 중지
        if ("1".equals(req.asAkDvCd()) && !"3".equals(req.mtrProcsStatCd())) {

            // 방문주기 재생성(SP_LC_SERVICEVISIT_482_LST_I06)
            individualVisitPrdService.processVisitPeriodRegen(
                new WsnbIndividualVisitPrdDto.SearchProcessReq(
                    req.cntrNo(),
                    req.cntrSn(),
                    req.akChdt(),
                    null,
                    req.akChdt(),
                    null,
                    null,
                    null
                )
            );

            // 정기배송 변경 대상 정보 조회
            WsniSidingServiceChangesDvo bsTargetDvo = sidingServiceChangesMapper.selectBsTarget(
                req.cntrNo(),
                req.cntrSn(),
                req.akChdt().substring(0, 6),
                req.afchPdCd()
            );

            if (bsTargetDvo != null) {
                // 고객 정기BS 삭제(SP_LC_SERVICEVISIT_482_LST_I07)
                customerRglrBfsvcDlService.removeRglrBfsvcDl(
                    new WsnbCustomerRglrBfsvcDlDto.SaveReq(
                        bsTargetDvo.getCstSvAsnNo(),
                        req.akChdt().substring(0, 6)
                    )
                );

                // 고객 정기BS 배정(SP_LC_SERVICEVISIT_482_LST_I03)
                regularBfsvcAsnService.processRegularBfsvcAsn(
                    new WsncRegularBfsvcAsnDto.SaveProcessReq(
                        req.akChdt().substring(0, 6),
                        SFLEXContextHolder.getContext().getUserSession().getUserId(),
                        req.cntrNo(),
                        req.cntrSn()
                    )
                );

                // 알림톡발송
                final Map<String, Object> paramMap = new HashMap<>();
                paramMap.put("cntrNo", req.cntrNo()); // 계약번호
                paramMap.put("cntrSn", req.cntrSn()); // 계약순번
                paramMap.put("rcgvpKnm", bsTargetDvo.getRcgvpKnm()); // 고객명
                paramMap.put("afchPdNm", bsTargetDvo.getAfchPdNm()); // 변경상품명
                paramMap.put("mmMpyAmt", bsTargetDvo.getMmMpyAmt()); // 월이용료
                paramMap.put("sppDuedt", bsTargetDvo.getSppDuedt()); // 배송예정일 -> 월까지만 ex) 11월
                smsMessageService.sendMessage(
                    SmsSendReqDvo.withTemplateId()
                        .templateId("TMP_SNZ_W_SNB_B0023") // Wells18100
                        .templateParamMap(paramMap)
                        .destInfo(
                            bsTargetDvo.getRcgvpKnm() + "^" + bsTargetDvo.getCralLocaraTno()
                                + bsTargetDvo.getMexnoEncr() + bsTargetDvo.getCralIdvTno()
                        )
                        .callback("15884113")
                        .build()
                );
            }

        }

        // 요청 구분에 따라 처리 - 1: 패키지변경, 4:다음회차 방문 중지
        if ("4".equals(req.asAkDvCd())) {
            regularShippingChangeMapper.updateStopNextSiding(req);
        }

    }

    /**
     * 정기배송 변경처리
     *
     * @programId W-SV-I-0016
     * @see [홈카페 캡슐 상품/서비스 변경 처리]
     *      environmentController.java > LC_ASREGN_API_005(HttpServletRequest request, HttpServletResponse response)
     */
    public SaveRes saveRegularShippingChange(SaveReq req) throws Exception {

        capsuleChange(req);

        List<WctbSeedingPackageChangeDto.ConsPdct> consPdList = null;
        String strPdctPdCds = req.choCapslCn();
        String saveGbn = StringUtil.isNotEmpty(strPdctPdCds) && strPdctPdCds.indexOf("|") > -1 ? "2" : "1";

        if ("2".equals(saveGbn)) {
            consPdList = new ArrayList<>();
            String[] arrayPdctPdCds = strPdctPdCds.split("\\|");

            for (String s : arrayPdctPdCds) {
                log.debug(s);
                consPdList.add(
                    new WctbSeedingPackageChangeDto.ConsPdct(
                        s.split("\\,")[0], Integer.parseInt(s.split("\\,")[1])
                    )
                );
                log.debug(s.split("\\,")[0]);
                log.debug(s.split("\\,")[1]);
            }
        }

        /**
        * @param
        * 수행구분코드(1.모종 기준상품변경 2.모종패키지 구성제품 변경)
        * 계약번호
        * 계약일련번호
        * 변경기준상품코드(수행구분코드 1일때 필수)
        * 변경모종구성제품/수량리스트(List<제품, 수량>)
        * */
        log.debug("saveGbn: " + saveGbn);
        log.debug("cntrNo: " + req.cntrNo());
        log.debug("cntrSn: " + req.cntrSn());
        log.debug("chPdCd: " + req.afchPdCd());
        log.debug("akChdt: " + req.akChdt());
        log.debug("consPdList: " + consPdList);

        seedingPackageChangeService.saveSeedingPackageChanges(
            new WctbSeedingPackageChangeDto.SaveReq(
                saveGbn, req.cntrNo(), req.cntrSn(), req.afchPdCd(), consPdList, req.akChdt()
            )
        );

        return new SaveRes("S", "");
    }
}
