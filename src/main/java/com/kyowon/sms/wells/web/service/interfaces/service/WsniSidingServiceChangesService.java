package com.kyowon.sms.wells.web.service.interfaces.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.contract.changeorder.dto.WctbSeedingPackageChangeDto;
import com.kyowon.sms.wells.web.contract.changeorder.service.WctbSeedingPackageChangeService;
import com.kyowon.sms.wells.web.service.allocate.dto.WsncRegularBfsvcAsnDto;
import com.kyowon.sms.wells.web.service.allocate.service.WsncRegularBfsvcAsnService;
import com.kyowon.sms.wells.web.service.interfaces.dto.WsniSidingServiceChangesDto.SaveReq;
import com.kyowon.sms.wells.web.service.interfaces.dto.WsniSidingServiceChangesDto.SaveRes;
import com.kyowon.sms.wells.web.service.interfaces.dvo.WsniSidingServiceChangesDvo;
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
* W-SV-I-0025 정기배송 변경(모종)
*
* @author gs.piit122 김동엽
* @since 2023-04-13
* */
@Service
@RequiredArgsConstructor
@Slf4j
public class WsniSidingServiceChangesService {

    private final WsniSidingServiceChangesMapper mapper;

    private final WsnbIndividualVisitPrdService individualVisitPrdService; // 개인별방문주기 서비스

    private final WsnbCustomerRglrBfsvcDlService customerRglrBfsvcDlService; // 고객정기BS삭제 서비스

    private final WsncRegularBfsvcAsnService regularBfsvcAsnService; // 정기BS배정 서비스

    private final WctbSeedingPackageChangeService seedingPackageChangeService; // [계약] 모종패키지변경 서비스

    /**
     * <pre>
     * W-SV-S-0010 PR_KIWI_FARM_CHANGE 모종 패키지/서비스 변경 처리
     * LC_ASREGN_API_I02_T
     * </pre>
     *
     * @author gs.piit122
     * @since 2023.07.18
     */
    public void saveSidingChanges(SaveReq req) throws Exception {

        // 요청일련번호 채번
        String akSn = mapper.selectAkSnMax(req.cntrNo(), req.cntrSn());

        // 취소일 경우 모종AS요청내역 삭제
        if ("3".equals(req.mtrProcsStatCd())) {
            mapper.deleteSdingAskAk(req.cntrNo(), req.cntrSn(), req.asAkDvCd(), req.akChdt());

        } else {

            if (mapper.selectSidingAkCount(req.cntrNo(), req.cntrSn(), req.asAkDvCd(), req.akChdt()) > 0) {
                // 모종AS요청내역 UPDATE
                mapper.updateSidingAk(
                    req.akChdt(), req.bfchPdCd(), req.afchPdCd(), req.mtrProcsStatCd(), req.cntrNo(), req.cntrSn(),
                    req.asAkDvCd(), req.choCapslCn()
                );

            } else {
                // 모종AS요청내역 INSERT
                mapper.insertSidingAk(
                    req.cntrNo(),
                    req.cntrSn(),
                    akSn,
                    req.asAkDvCd(),
                    req.bfchPdCd(),
                    req.afchPdCd(),
                    req.mtrProcsStatCd(),
                    req.akChdt(),
                    req.choCapslCn(),
                    req.rcpIchrPrtnrNo(),
                    req.rcpOgTpCd()
                );
            }

        }

        // 모종AS요청이력 INSERT
        mapper.insertSdingAsAkHist(
            req.cntrNo(),
            req.cntrSn(),
            akSn,
            RandomStringUtils.randomNumeric(6),
            req.asAkDvCd(),
            req.akChdt(),
            req.bfchPdCd(),
            req.afchPdCd(),
            req.mtrProcsStatCd(),
            req.choCapslCn(),
            "",
            req.rcpIchrPrtnrNo(),
            req.rcpOgTpCd()
        );

        // 요청 구분에 따라 처리 - 1: 패키지변경, 4: 다음회차 방문 중지
        if ("1".equals(req.asAkDvCd()) && !"3".equals(req.mtrProcsStatCd())) {
            // 개인별 방문주기 재생성 서비스 호출 (SP_LC_SERVICEVISIT_482_LST_I06)
            individualVisitPrdService.processVisitPeriodRegen(
                new WsnbIndividualVisitPrdDto.SearchProcessReq(
                    req.cntrNo(),
                    req.cntrSn(),
                    req.akChdt(),
                    null,
                    req.akChdt(),
                    req.akChdt().substring(0, 6), // 배정년월 설정
                    null,
                    null
                )
            );

            // 정기배송 변경 대상 정보 조회
            WsniSidingServiceChangesDvo bsTargetDvo = mapper.selectBsTarget(
                req.cntrNo(),
                req.cntrSn(),
                req.akChdt().substring(0, 6),
                req.afchPdCd()
            );

            if (bsTargetDvo != null) {
                log.debug("고객 정기BS 삭제");

                // 고객 정기BS 삭제 (SP_LC_SERVICEVISIT_482_LST_I07)
                customerRglrBfsvcDlService.removeRglrBfsvcDl(
                    new WsnbCustomerRglrBfsvcDlDto.SaveReq(
                        bsTargetDvo.getCstSvAsnNo(),
                        bsTargetDvo.getAsnOjYm()
                    )
                );

                log.debug("고객 정기BS 배정");

                // 고객 정기BS 배정 (SP_LC_SERVICEVISIT_482_LST_I03)
                regularBfsvcAsnService.processRegularBfsvcAsn(
                    new WsncRegularBfsvcAsnDto.SaveProcessReq(
                        req.akChdt().substring(0, 6),
                        SFLEXContextHolder.getContext().getUserSession().getUserId(),
                        req.cntrNo(),
                        req.cntrSn()
                    )
                );
            }
        }

        // AS요청구분코드 "4"인 경우 다음회차 방문 중지 처리
        if ("4".equals(req.asAkDvCd())) {
            log.debug("4: 다음회차 방문 중지");
            mapper.updateStopNextSiding(req.cntrNo(), req.cntrSn(), req.akChdt());
        }

    }

    /**
     * 계약 모종 패키지 변경 호출 서비스
     *
     * @param req
     * @return
     * @throws Exception
     */
    @Transactional
    public SaveRes saveSidingPackageOrProductChange(SaveReq req) throws Exception {
        log.debug("cntrNo : " + req.cntrNo());
        log.debug("cntrSn : " + req.cntrSn());
        log.debug("asAkDvCd : " + req.asAkDvCd());
        log.debug("akChdt : " + req.akChdt());
        log.debug("bfchPdCd : " + req.bfchPdCd());
        log.debug("afchPdCd : " + req.afchPdCd());
        log.debug("choCapslCn : " + req.choCapslCn());
        log.debug("mtrProcsStatCd : " + req.mtrProcsStatCd());
        log.debug("rcpIchrPrtnrNo: " + req.rcpIchrPrtnrNo());
        log.debug("rcpOgTpCd: " + req.rcpOgTpCd());

        saveSidingChanges(req); // LC_ASREGN_API_I02_T

        List<WctbSeedingPackageChangeDto.ConsPdct> consPdList = null;
        String strPdctPdCds = req.choCapslCn();
        String saveGbn = StringUtil.isNotEmpty(strPdctPdCds) && strPdctPdCds.indexOf("|") > -1 ? "2" : "1";

        // 모종패키지 구성제품변경인 경우에만 수행
        if ("2".equals(saveGbn)) {
            consPdList = new ArrayList<>();
            String[] arrayPdctPdCds = strPdctPdCds.split("\\|");

            for (String s : arrayPdctPdCds) {
                log.debug(s);
                consPdList.add(
                    new WctbSeedingPackageChangeDto.ConsPdct(s.split("\\,")[0], Integer.parseInt(s.split("\\,")[1]))
                );
                log.debug(s.split("\\,")[0]);
                log.debug(s.split("\\,")[1]);
            }
        }

        log.debug("saveGbn: " + saveGbn);
        log.debug("cntrNo: " + req.cntrNo());
        log.debug("cntrSn: " + req.cntrSn());
        log.debug("chPdCd: " + req.afchPdCd());
        log.debug("consPdList: " + consPdList);
        log.debug("akChdt: " + req.akChdt());

        /**
         * @param saveGbn 수행구분코드(1.모종 기준상품변경 2.모종패키지 구성제품 변경)
         * @param cntrNo 계약번호
         * @param cntrSn 계약일련번호
         * @param chPdCd 변경기준 상품코드. 수행구분코드 1일때 필수
         * @param consPdList 변경모종구성제품/수량리스트(List<제품, 수량>)
         * @param applyDt 적용일자 (오늘 or 다음달 이후 일자)
         */
        seedingPackageChangeService.saveSeedingPackageChanges(
            new WctbSeedingPackageChangeDto.SaveReq(
                saveGbn, req.cntrNo(), req.cntrSn(), req.afchPdCd(), consPdList, req.akChdt()
            )
        );

        return new SaveRes("S", "");
    }

}
