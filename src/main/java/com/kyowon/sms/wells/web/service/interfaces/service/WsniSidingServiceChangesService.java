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
*
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
    //private final WsnbRegularShippingChangeMapper mapper2;

    private final WsnbIndividualVisitPrdService service1;
    private final WsnbCustomerRglrBfsvcDlService service2;
    private final WsncRegularBfsvcAsnService service3;
    private final WctbSeedingPackageChangeService service4;

    /**
     * <pre>
     * W-SV-S-0010 PR_KIWI_FARM_CHANGE    모종 패키지/서비스 변경 처리
     * LC_ASREGN_API_I02_T
     * </pre>
     *
     * @author gs.piit122
     * @since 2023.07.18
     */
    public void AsReceiption(SaveReq req) throws Exception {

        String akSn = req.akSn();

        //취소일 경우 삭제
        if ("3".equals(req.mtrProcsStatCd())) {
            mapper.deleteSdingAskAk(req.cntrNo(), req.cntrSn(), akSn, req.asAkDvCd(), req.akChdt());
        } else {
            if (mapper.selectSidingAkCount(req.cntrNo(), req.cntrSn(), akSn, req.asAkDvCd(), req.akChdt()) > 0) {
                mapper.updateSidingAk(
                    req.akChdt(), req.bfchPdCd(), req.afchPdCd(), req.mtrProcsStatCd(), req.cntrNo(), req.cntrSn(),
                    akSn, req.asAkDvCd(), req.choCapslCn()
                );
            } else {
                akSn = mapper.selectAkSnMax(req.cntrNo(), req.cntrSn());
                mapper.insertSidingAk(
                    req.cntrNo(),
                    req.cntrSn(),
                    akSn,
                    req.asAkDvCd(),
                    req.bfchPdCd(),
                    req.afchPdCd(),
                    req.mtrProcsStatCd(),
                    req.akChdt(),
                    req.choCapslCn()
                );
            }
        }

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
            StringUtil.isEmpty(req.choCapslCn()) ? mapper.selectPdctPdCds(req.cntrNo(), req.cntrSn(), akSn)
                : req.choCapslCn(),
            ""
        );

        /*요청 구분에 따라 처리 - 1: 패키지변경, 4:다음회차 방문 중지*/
        //IF(P_REQ_GB = '1' AND P_DATA_STUS != '3') THEN
        if ("1".equals(req.asAkDvCd()) && !"3".equals(req.mtrProcsStatCd())) {
            /*방문주기 재생성(SP_LC_SERVICEVISIT_482_LST_I06)*/
            service1.processVisitPeriodRegen(
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

            WsniSidingServiceChangesDvo bsTargetDvo = mapper.selectBsTarget(
                req.cntrNo(),
                req.cntrSn(),
                req.akChdt().substring(0, 6),
                req.afchPdCd()
            );
            if (bsTargetDvo != null) {
                /*고객 정기BS 삭제(SP_LC_SERVICEVISIT_482_LST_I07)*/
                service2.removeRglrBfsvcDl(
                    new WsnbCustomerRglrBfsvcDlDto.SaveReq(
                        bsTargetDvo.getCstSvAsnNo(), //row.getCstSvAsnNo(),
                        bsTargetDvo.getAsnOjYm()
                    )
                );
                log.debug("고객 정기BS 배정(SP_LC_SERVICEVISIT_482_LST_I03)");
                /*고객 정기BS 배정(SP_LC_SERVICEVISIT_482_LST_I03)*/
                service3.processRegularBfsvcAsn(
                    new WsncRegularBfsvcAsnDto.SaveProcessReq(
                        req.akChdt().substring(0, 6),
                        SFLEXContextHolder.getContext().getUserSession().getUserId(),
                        req.cntrNo(),
                        req.cntrSn()
                    )
                );
            }
        }

        /*요청 구분에 따라 처리 - 1: 패키지변경, 4:다음회차 방문 중지*/
        if ("4".equals(req.asAkDvCd())) {
            log.debug("요청 구분에 따라 처리 - 1: 패키지변경, 4:다음회차 방문 중지");
            mapper.updateStopNextSiding(req.cntrNo(), req.cntrSn(), req.akChdt());
        }

    }

    @Transactional
    public SaveRes saveSidingProductChange(SaveReq req) throws Exception {

        log.debug("cntrNo : " + req.cntrNo());
        log.debug("cntrSn : " + req.cntrSn());
        log.debug("akSn : " + req.akSn());
        log.debug("asAkDvCd : " + req.asAkDvCd());
        log.debug("akChdt : " + req.akChdt());
        log.debug("bfchPdCd : " + req.bfchPdCd());
        log.debug("afchPdCd : " + req.afchPdCd());
        log.debug("choCapslCn : " + req.choCapslCn());
        log.debug("mtrProcsStatCd : " + req.mtrProcsStatCd());

        AsReceiption(req); // LC_ASREGN_API_I02_T

        List<WctbSeedingPackageChangeDto.ConsPdct> consPdList = new ArrayList<>();
        String strPdctPdcds = StringUtil.isEmpty(req.choCapslCn()) ? mapper.selectPdctPdCds(
            req.cntrNo(), req.cntrSn(),
            req.akSn()
        ) : req.choCapslCn();
        if (StringUtil.isNotEmpty(strPdctPdcds)) {
            String[] arrayPdctPdCds = strPdctPdcds.split("\\|");
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
        log.debug("saveGbn: 1");
        log.debug("cntrNo: " + req.cntrNo());
        log.debug("cntrSn: " + req.cntrSn());
        log.debug("chPdCd: " + req.afchPdCd());
        log.debug("akChdt: " + req.akChdt());
        log.debug("consPdList: " + consPdList);

        service4.saveSeedingPackageChanges(
            new WctbSeedingPackageChangeDto.SaveReq(
                "1", req.cntrNo(), req.cntrSn(), req.afchPdCd(), consPdList, req.akChdt()
            )
        );

        return new SaveRes("S", "");
    }
}
