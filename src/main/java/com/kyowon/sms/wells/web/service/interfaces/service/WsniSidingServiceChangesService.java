package com.kyowon.sms.wells.web.service.interfaces.service;

import com.kyowon.sms.wells.web.contract.changeorder.dto.WctbSeedingPackageChangeDto;
import com.kyowon.sms.wells.web.contract.changeorder.service.WctbSeedingPackageChangeService;
import com.kyowon.sms.wells.web.service.allocate.dto.WsncRegularBfsvcAsnDto;
import com.kyowon.sms.wells.web.service.allocate.service.WsncRegularBfsvcAsnService;
import com.kyowon.sms.wells.web.service.interfaces.dto.WsniSidingServiceChangesDto.SaveReq;
import com.kyowon.sms.wells.web.service.interfaces.dto.WsniSidingServiceChangesDto.SaveRes;
import com.kyowon.sms.wells.web.service.interfaces.dvo.WsniSidingServiceChangesDvo;
import com.kyowon.sms.wells.web.service.interfaces.mapper.WsnbRegularShippingChangeMapper;
import com.kyowon.sms.wells.web.service.interfaces.mapper.WsniSidingServiceChangesMapper;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbCustomerRglrBfsvcDlDto;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbIndividualVisitPrdDto;
import com.kyowon.sms.wells.web.service.visit.service.WsnbCustomerRglrBfsvcDlService;
import com.kyowon.sms.wells.web.service.visit.service.WsnbIndividualVisitPrdService;
import com.sds.sflex.common.utils.StringUtil;
import com.sds.sflex.system.config.context.SFLEXContextHolder;

import java.util.ArrayList;
import java.util.List;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
*
* EAI_WSVI1002 정기배송 변경(모종)
*
* environmentController
*   LC_ASREGN_API_004 (모종 상품/서비스 변경 처리)
*       LC_ASREGN_API_I02_T (모종 패키지/서비스 변경 오라클)
*       LC_ASREGN_API_I03_T (모종 패키지/서비스 변경 DB2)
* */
@Service
@RequiredArgsConstructor
@Slf4j
public class WsniSidingServiceChangesService {

    private final WsniSidingServiceChangesMapper mapper1;
    private final WsnbRegularShippingChangeMapper mapper2;

    private final WsnbIndividualVisitPrdService service1;
    private final WsnbCustomerRglrBfsvcDlService service2;
    private final WsncRegularBfsvcAsnService service3;
    private final WctbSeedingPackageChangeService service4;

    @Transactional
    public SaveRes saveSidingProductChange(SaveReq req) throws Exception {
        AsReceiption(req); // LC_ASREGN_API_I02_T
        sidingChange(req); // LC_ASREGN_API_I03_T
        return new SaveRes("S", "");
    }

    /**
     * <pre>
     * W-SV-S-0010 PR_KIWI_FARM_CHANGE    모종 패키지/서비스 변경 처리
     *             PR_KIWI_CAPSULE_CHANGE 홈카페 캡슐 패키지/서비스 변경 처리
     * LC_ASREGN_API_I02_T
     * </pre>
     *
     * @author gs.piit122
     * @since 2023.07.18
     */
    public void AsReceiption(SaveReq req) throws Exception {

        String akSn = req.akSn();

        /*취소일 경우 삭제 */
        if ("3".equals(req.mtrProcsStatCd())) {
            mapper1.deleteSdingAskAk(req.cntrNo(), req.cntrSn(), akSn, req.asAkDvCd(), req.akChdt());
        } else {
            if (mapper1.selectSidingAkCount(req.cntrNo(), req.cntrSn(), akSn, req.asAkDvCd(), req.akChdt()) > 0) {
                mapper1.updateSidingAk(
                    req.akChdt(), req.bfchPdCd(), req.afchPdCd(), req.mtrProcsStatCd(), req.cntrNo(), req.cntrSn(),
                    akSn, req.asAkDvCd()
                );
            } else {
                akSn = mapper1.selectAkSnMax(req.cntrNo(), req.cntrSn());
                mapper1.insertSidingAk(
                    req.cntrNo(),
                    req.cntrSn(),
                    akSn,
                    req.asAkDvCd(),
                    req.bfchPdCd(),
                    req.afchPdCd(),
                    req.mtrProcsStatCd()
                );
            }
        }

        mapper1.insertSdingAsAkHist(
            req.cntrNo(),
            req.cntrSn(),
            akSn,
            RandomStringUtils.randomNumeric(6),
            req.asAkDvCd(),
            req.akChdt(),
            req.bfchPdCd(),
            req.afchPdCd(),
            req.mtrProcsStatCd(),
            StringUtil.isEmpty(req.consPdList()) ? mapper1.selectPdctPdCds(req.cntrNo(), req.cntrSn(), akSn)
                : req.consPdList(),
            ""
        );

        /***********************************************************
        * 주기변경 처리를 위한 고객의 정보 확인
        *
        * SV_PRD       방문주기
        * PD_PRP_VAL01 상품용도
        * SELL_TP_CD   관리유형
        * IST_DT       설치일자
        * BS_MTHS      무상 BS 개월수
        ***********************************************************/
        //WsniSidingServiceChangesDvo dvo = mapper.selectCustomer(req.cntrNo(), req.cntrSn());

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
                    null,
                    null,
                    null
                )
            );

            WsniSidingServiceChangesDvo bsTargetDvo = mapper1.selectBsTarget(
                req.cntrNo(),
                req.cntrSn(),
                req.akChdt().substring(0, 6)
            );
            if (bsTargetDvo != null) {
                /*고객 정기BS 삭제(SP_LC_SERVICEVISIT_482_LST_I07)*/
                service2.removeRglrBfsvcDl(
                    new WsnbCustomerRglrBfsvcDlDto.SaveReq(
                        bsTargetDvo.getCstSvAsnNo(), //row.getCstSvAsnNo(),
                        //""//row.getAsnOjYm() 배정년월
                        req.akChdt().substring(0, 6)
                    )
                );
                log.debug("고객 정기BS 배정(SP_LC_SERVICEVISIT_482_LST_I03)");
                /*고객 정기BS 배정(SP_LC_SERVICEVISIT_482_LST_I03)*/
                service3.processRegularBfsvcAsn(
                    new WsncRegularBfsvcAsnDto.SaveProcessReq(
                        req.akChdt().substring(0, 6), //row.getAsnOjYm(),
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
            mapper1.updateStopNextSiding(req.cntrNo(), req.cntrSn(), req.akChdt());
        }

    }

    /**
     * <pre>
     * W-SV-I-0025 모종 상품/서비스 변경 처리
     * LC_ASREGN_API_I03_T
     * </pre>
     *
     * @author gs.piit122
     * @since 2023.07.18
     */
    public void sidingChange(SaveReq req) throws Exception {

        /*취소 요청인지 확인*/
        if (StringUtil.nvl2(req.mtrProcsStatCd(), "").equals("3")) {
            /*********************************************************
            * TB_SVPD_SDING_AS_AK_IZ (모종AS요청내역) 삭제
            *
            * ASIS:
            * LC3200P 삭제 처리
            * Database.getInstanceDB2().delete("environment.LC_ASREGN_API_D01", params); LD3200P
            * ********************************************************/
            mapper1.deleteSdingAskAk(
                req.cntrNo(),
                req.cntrSn(),
                req.akSn(),
                req.asAkDvCd(),
                req.akChdt()
            );

            /*********************************************************
            * TB_SVPD_SDING_AS_AK_HIST(모종AS요청이력) 저장
            *
            * ASIS:
            * LD3300H 히스토리 인서트
            * Database.getInstanceDB2().insert("environment.LC_ASREGN_API_I05", params);
            * ********************************************************/
            mapper1.insertSdingAsAkHist(
                req.cntrNo(),
                req.cntrSn(),
                req.akSn(),
                RandomStringUtils.randomNumeric(6),
                req.asAkDvCd(),
                req.akChdt(),
                req.bfchPdCd(),
                req.afchPdCd(),
                req.mtrProcsStatCd(), // 1:입력, 2:수정, 3:취소
                StringUtil.isEmpty(req.consPdList()) ? mapper1.selectPdctPdCds(req.cntrNo(), req.cntrSn(), req.akSn())
                : req.consPdList(), // matNm
                "" // csmrUprcAmt
            );

        } else {
            /*********************************************************
            * TB_SVPD_SDING_AS_AK_IZ (모종AS요청내역) 에서
            * 해당계약이 확정일자가 없는지 COUNT
            *
            * ASIS:
            * int LD3200_CNT = LC_ASREGN_API_S09 -- LD3200P
            * COUNT(*) LD3200P(정기배송　주문변경　관리)
            * 먼저 LCLIB.LD3200P 에 미처리 된 같은 요청이 존재하는지 체크
            * ********************************************************/
            //            int LD3200_CNT = mapper1.selectSidingAkCount(
            //                req.cntrNo(), req.cntrSn(), req.akSn(), req.asAkDvCd(),
            //                req.akChdt()
            //            );

            //            if (LD3200_CNT == 0) {

            /*********************************************************
            * TB_SSSO_SPP_CH_RCP_DTL (배송변경접수상세) 에서
            * 계약번호 기준으로 배송변경일련번호 채번
            *
            * ASIS:
            * ArrayList chk1 = LC_ASREGN_API_S08
            * 요청일련번호 - LC0200P에서 채번 (별도 시트 참고)
            * ********************************************************/
            //                int akSn = mapper2.selectRegularShippingChangeMaxSn(req.cntrNo());

            //                if (akSn > 0) { // if (chk1 != null) {

            // 채번 이후에 채번 테이블 인서트/업데이트
            //if (SEQ.equals("1")) {
            //  LC_ASREGN_API_I03
            //  -- INSERT LC0200P(일련번호　채번（고객코드，일자，단순）)
            //} else {
            //  LC_ASREGN_API_U04
            //  -- UPDATE LC0200P(일련번호　채번（고객코드，일자，단순）)
            //}
            //-------------------------------------------------------

            /********************************************************
            * TB_SSSO_SPP_CH_RCP_BAS (배송변경접수기본) 에 INSERT
            *
            *
            * ASIS:
            * LD3200P = TB_SSSO_SPP_CH_RCP_BAS
            * LC_ASREGN_API_I04
            * INSERT LD3200P(정기배송　주문변경　관리)
            *********************************************************/
            //                    WsnbRegularShippingChangeDto.SaveRegularShippingChangeBaseReq baseReq = new WsnbRegularShippingChangeDto.SaveRegularShippingChangeBaseReq(
            //                        req.cntrNo(), req.cntrSn(), req.asAkDvCd(), req.akChdt()
            //                    );
            //                    mapper2.insertRegularShippingChangeBase(baseReq);
            //-------------------------------------------------------

            //LC_ASREGN_API_I05
            //-- INSERT LD3300H(정기배송　주문변경　관리History)
            // LD3300H = TB_SSSO_SPP_CH_RCCH_HIST
            //                    WsnbRegularShippingChangeDto.SaveRegularShippingChangeHistReq historyReq = new WsnbRegularShippingChangeDto.SaveRegularShippingChangeHistReq(
            //                        req.cntrNo(), req.cntrSn(), req.mtrProcsStatCd(), req.asAkDvCd(), req.akChdt()
            //                    );
            //                    mapper2.insertRegularShippingChangeHist(historyReq);
            //-------------------------------------------------------
            //                } else {
            //                    throw new Exception(">> LC_ASREGN_API_I03_T : data insert failed <<");
            //                }
            //
            //            } else {
            //
            // LC_ASREGN_API_U05
            // LD3200P = TB_SSSO_SPP_CH_RCP_BAS
            //-- UPDATE LD3200P(정기배송　주문변경　관리)
            //
            // LC_ASREGN_API_I05
            // LD3300H = TB_SSSO_SPP_CH_RCCH_HIST
            // -- INSERT LD3300H(정기배송　주문변경　관리History)
            //
            //	               		return true;
            //            }
            //
        }

        List<WctbSeedingPackageChangeDto.ConsPdct> consPdList = new ArrayList<>();
        String strPdctPdcds = StringUtil.isEmpty(req.consPdList()) ? mapper1.selectPdctPdCds(
            req.cntrNo(), req.cntrSn(),
            req.akSn()
        ) : req.consPdList();
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
        log.debug("consPdList: " + consPdList);
        service4.saveSeedingPackageChanges(
            new WctbSeedingPackageChangeDto.SaveReq(
                "1", req.cntrNo(), req.cntrSn(), req.afchPdCd(), consPdList
            )
        );
    }
}
