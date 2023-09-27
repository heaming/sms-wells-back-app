package com.kyowon.sms.wells.web.service.interfaces.service;

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

import java.util.ArrayList;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class WsniSidingServiceChangesService {

    private final WsniSidingServiceChangesMapper mapper;

    private final WsnbIndividualVisitPrdService service1;
    private final WsnbCustomerRglrBfsvcDlService service2;
    private final WsncRegularBfsvcAsnService service3;

    /**
     * <pre>
     * W-SV-S-0010 PR_KIWI_FARM_CHANGE
     * </pre>
     *
     * @author gs.piit122
     * @since 2023.07.18
     */
    public void AsReceiption(SaveReq req) throws Exception {

        mapper.insertSdingAsAkHist(
            req.cntrNo(),
            req.cntrSn(),
            req.akSn(),
            "",
            req.asAkDvCd(),
            req.akChdt(),
            req.bfchPdCd(),
            req.afchPdCd(),
            req.mtrProcsStatCd(),
            "",
            ""
        );

        /*취소일 경우 삭제 */
        if ("3".equals(req.mtrProcsStatCd()))
            mapper.deleteSdingAskAk(
                req.cntrNo(),
                req.cntrSn(),
                req.akSn(),
                req.asAkDvCd(),
                req.akChdt()
            );
        else {

            if (mapper.selectSidingAkCount(req.cntrNo(), req.cntrSn(), req.akSn(), req.asAkDvCd(), req.akChdt())
                .intValue() > 0)
                mapper.updateSidingAk(
                    req.akChdt(),
                    req.bfchPdCd(),
                    req.afchPdCd(),
                    req.mtrProcsStatCd(),
                    req.cntrNo(),
                    req.cntrSn(),
                    req.akSn(),
                    req.asAkDvCd()
                );
            else
                mapper.insertSidingAk(
                    req.cntrNo(),
                    req.cntrSn(),
                    req.asAkDvCd(),
                    req.bfchPdCd(),
                    req.afchPdCd(),
                    req.mtrProcsStatCd()
                );
        }

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
        //IF(P_REQ_GB = '1' AND mtrProcsStatCd != '3') THEN
        if ("1".equals(req.asAkDvCd()) && !"3".equals(req.mtrProcsStatCd())) {

            /*방문주기 재생성(SP_LC_SERVICEVISIT_482_LST_I06)*/
            service1.processVisitPeriodRegen(
                new WsnbIndividualVisitPrdDto.SearchProcessReq(
                    req.cntrNo(),
                    req.cntrSn(),
                    null,
                    null,
                    null,
                    null,
                    null,
                    null
                )
            );

            WsniSidingServiceChangesDvo dvo = mapper.selectBsTarget(req.cntrNo(), req.cntrSn(),
             req.akChdt().substring(0, 6));
            if (StringUtil.isNotEmpty(dvo.getCstSvAsnNo())) {
                /*고객 정기BS 삭제(SP_LC_SERVICEVISIT_482_LST_I07)*/
                service2.removeRglrBfsvcDl(
                    new WsnbCustomerRglrBfsvcDlDto.SaveReq(
                        dvo.getCstSvAsnNo(), //row.getCstSvAsnNo(),
                        //""//row.getAsnOjYm() 배정년월
                        req.akChdt().substring(0, 6)
                    )
                );

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
            mapper.updateStopNextSiding(req.cntrNo(), req.cntrSn(), req.akChdt());
        }

    }

    /**
     * <pre>
     * W-SV-I-0025 모종 상품/서비스 변경 처리
     * </pre>
     *
     * @author gs.piit122
     * @since 2023.07.18
     */
    public SaveRes saveSidingProductChange(SaveReq req) throws Exception {
        AsReceiption(req);
        sidingChange(req);
        return null;
    }

    public void sidingChange(SaveReq req) throws Exception {

        // req.cntrNo
        // req.cntrSn
        // req.akSn
        // req.asAkDvCd
        // req.akChdt
        // req.bfchPdCd
        // req.afchPdCd
        // req.mtrProcsStatCd
        // req.userId

        int LD3200_CNT = 0;
        ArrayList chk1 = null;

        //TB_SVPD_SDING_AS_AK_IZ
        //TB_SVPD_SDING_AS_AK_HIST

        if (req.mtrProcsStatCd().equals("3")) {
            //
            //           LC_ASREGN_API_I05
            //           -- INSERT LD3300H(정기배송 주문변경 관리History)
            //
            //           LC_ASREGN_API_D01
            //           -- DELETE LD3200P(정기배송 주문변경 관리)
            //
            //           return true;
            //
        } else {
            //
            //            int LD3200_CNT = LC_ASREGN_API_S09
            //            -- COUNT(*) LD3200P(정기배송　주문변경　관리)
            //
            if (LD3200_CNT == 0) {
                //
                //                ArrayList chk1 = LC_ASREGN_API_S08
                //                -- SELECT LC0200P(일련번호　채번（고객코드，일자，단순）)
                //
                if (chk1 != null) {
                    //
                    //	               		    if (SEQ.equals("1")) {
                    //	               		    	LC_ASREGN_API_I03
                    //	               		    	-- INSERT LC0200P(일련번호　채번（고객코드，일자，단순）)
                    //	               		    } else {
                    //	               		    	LC_ASREGN_API_U04
                    //	               		    	-- UPDATE LC0200P(일련번호　채번（고객코드，일자，단순）)
                    //	               		    }
                    //
                    //	               		    LC_ASREGN_API_I04
                    //	               		    -- INSERT LD3200P(정기배송　주문변경　관리)
                    //
                    //	               		    LC_ASREGN_API_I05
                    //	               		    -- INSERT LD3300H(정기배송　주문변경　관리History)
                    //
                    //
                    //	               		    return true;
                    //
                } else {
                    throw new Exception(">> LC_ASREGN_API_I03_T : data insert failed <<");
                }
                //
            } else {
                //
                //	               		LC_ASREGN_API_U05
                //	               		-- UPDATE LD3200P(정기배송　주문변경　관리)
                //
                //	               		LC_ASREGN_API_I05
                //	               		-- INSERT LD3300H(정기배송　주문변경　관리History)
                //
                //	               		return true;
            }
            //
        }
    }
}
