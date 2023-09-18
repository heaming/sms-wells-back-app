package com.kyowon.sms.wells.web.service.interfaces.service;

import com.kyowon.sms.wells.web.service.allocate.dto.WsncRegularBfsvcAsnDto;
import com.kyowon.sms.wells.web.service.allocate.service.WsncRegularBfsvcAsnService;
import com.kyowon.sms.wells.web.service.interfaces.dto.WsnbRegularShippingChangeDto.*;
import com.kyowon.sms.wells.web.service.interfaces.dvo.WsniSidingServiceChangesDvo;
import com.kyowon.sms.wells.web.service.interfaces.mapper.WsnbRegularShippingChangeMapper;
import com.kyowon.sms.wells.web.service.interfaces.mapper.WsniSidingServiceChangesMapper;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbCustomerRglrBfsvcDlDto;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbIndividualVisitPrdDto;
import com.kyowon.sms.wells.web.service.visit.service.WsnbCustomerRglrBfsvcDlService;
import com.kyowon.sms.wells.web.service.visit.service.WsnbIndividualVisitPrdService;
import com.sds.sflex.common.utils.StringUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 *
 *
 * W-SV-I-0016 정기배송 변경
 *
 *
 * @author gs.piit122 김동엽
 * @since 2023-04-13
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class WsnbRegularShippingChangeService {

    private final WsnbRegularShippingChangeMapper mapper;
    private final WsniSidingServiceChangesMapper mapper2;
    private final WsnbIndividualVisitPrdService service1;
    private final WsnbCustomerRglrBfsvcDlService service2;
    private final WsncRegularBfsvcAsnService service3;

    public void AsReceiption(SaveReq req) throws Exception {

        mapper2.insertSdingAsAkHist(
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
            mapper2.deleteSdingAskAk(
                req.cntrNo(),
                req.cntrSn(),
                req.akSn(),
                req.asAkDvCd(),
                req.akChdt()
            );
        else {

            if (mapper2.selectSidingAkCount(req.cntrNo(), req.cntrSn(), req.akSn(), req.asAkDvCd(), req.akChdt())
                .intValue() > 0)
                mapper2.updateSidingAk(
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
                mapper2.insertSidingAk(
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
        WsniSidingServiceChangesDvo dvo = mapper2.selectCustomer(req.cntrNo(), req.cntrSn());

        /*요청 구분에 따라 처리 - 1: 패키지변경, 4:다음회차 방문 중지*/
        //IF(P_REQ_GB = '1' AND mtrProcsStatCd != '3') THEN
        if ("1".equals(req.asAkDvCd()) && !"3".equals(req.mtrProcsStatCd())) {

            /*방문주기 재생성(SP_LC_SERVICEVISIT_482_LST_I06)*/
            service1.processVisitPeriodRegen(
                new WsnbIndividualVisitPrdDto.SearchProcessReq(
                    req.cntrNo(),
                    req.cntrSn(),
                    req.akChdt(),
                    "",
                    "",
                    "",
                    "",
                    ""
                )
            );

            if (mapper2.selectBsTarget(req.cntrNo(), req.cntrSn(), "") > 0) {
                /*고객 정기BS 삭제(SP_LC_SERVICEVISIT_482_LST_I07)*/
                service2.removeRglrBfsvcDl(
                    new WsnbCustomerRglrBfsvcDlDto.SaveReq(
                        "", //row.getCstSvAsnNo(),
                        //""//row.getAsnOjYm() 배정년월
                        req.akChdt()
                    )
                );

                /*고객 정기BS 배정(SP_LC_SERVICEVISIT_482_LST_I03)*/
                service3.processRegularBfsvcAsn(
                    new WsncRegularBfsvcAsnDto.SaveProcessReq(
                        "", //row.getAsnOjYm(),
                        "", //SFLEXContextHolder.getContext().getUserSession().getUserId(),
                        req.cntrNo(),
                        req.cntrSn()
                    )
                );
            }
        }

        /*요청 구분에 따라 처리 - 1: 패키지변경, 4:다음회차 방문 중지*/
        if ("4".equals(req.asAkDvCd())) {
            mapper2.updateStopNextSiding(req.cntrNo(), req.cntrSn(), req.akChdt());
        }

    }

    /**
     * 정기배송 변경처리
     *
     * @programId W-SV-I-0016
     * @see 홈카페 캡슐 상품/서비스 변경 처리
     *      environmentController.java > LC_ASREGN_API_005(HttpServletRequest request, HttpServletResponse response)
     */
    public SaveRes saveRegularShippingChange(SaveReq req) throws Exception {

        boolean step1 = false;
        boolean step2 = false;
        String msg = "";

        //@TODO 이진성 프로 작업 예정
        //홈카페 캡슐 패키지/서비스 변경 오라클
        //String result = LC_ASREGN_API_I04_T; -> PR_KIWI_CAPSULE_CHANGE
        AsReceiption(req);
        String result = "S001";

        if (result.length() > 0 && result.equals("S001"))
            step1 = true;

        if (step1) {

            /* 2. 5250 인서트 로직 */
            String cntrNo = req.cntrNo();
            String cntrSn = req.cntrSn();
            String asAkDvCd = req.asAkDvCd();
            String akChdt = req.akChdt();
            String afchPdCd = req.afchPdCd(); // LCPKAG 변경판매코드
            String partList = req.partList(); /*자유패키지 캡슐 구성 정보 > 판매코드,수량 | 판매코드, 수량 |~~~ */
            String mtrProcsStatCd = req.mtrProcsStatCd();

            SaveRegularShippingChangeHistReq historyReq = new SaveRegularShippingChangeHistReq(
                cntrNo, cntrSn, mtrProcsStatCd, asAkDvCd, akChdt
            );
            SaveRegularShippingChangeBaseReq baseReq = new SaveRegularShippingChangeBaseReq(
                cntrNo, cntrSn, asAkDvCd, akChdt
            );

            /*1.먼저 LCLIB.LD3200P 에 미처리 된 같은 요청이 존재하는지 체크*/
            //int LD3200_CNT = LC_ASREGN_API_S09(request, response);
            if (StringUtil.nvl2(mtrProcsStatCd, "").equals("3")) {

                //Database.getInstanceDB2().insert("environment.LC_ASREGN_API_I05", params);
                mapper.insertRegularShippingChangeHist(historyReq);

                //Database.getInstanceDB2().delete("environment.LC_ASREGN_API_D02", params);
                mapper.deleteRegularShippingChangeDtl(
                    new SaveRegularShippingChangeDtlReq(cntrNo, cntrSn, asAkDvCd, akChdt, 0)
                );

                //Database.getInstanceDB2().delete("environment.LC_ASREGN_API_D01", params);
                mapper.deleteRegularShippingChangeBase(baseReq);

                step2 = true;

            } else if (mapper.selectRegularShippingChangeCount(
                new SearchRegularShippingChangeBaseReq(cntrNo, cntrSn, asAkDvCd, akChdt)
            ) == 0) {

                /*요청일련번호 - LC0200P에서 채번 (별도 시트 참고)*/
                //ArrayList chk1 = (ArrayList)Database.getInstanceDB2().queryForList("environment.LC_ASREGN_API_S08", params01);
                int seq = mapper.selectRegularShippingChangeMaxSn(cntrNo);

                // Database.getInstanceDB2().insert("environment.LC_ASREGN_API_I04", params);
                mapper.insertRegularShippingChangeBase(baseReq);

                if (partList.length() > 0) {

                    //자유선택 패키지이고 선택 캡슐이 있다면,
                    String[] A_P_PART_LIST = partList.split("\\|");

                    for (int i = 0; i < A_P_PART_LIST.length; i++) {
                        String[] AA_P_PART_LIST = A_P_PART_LIST[i].split(",");
                        //---params.put("LCICDE", AA_P_PART_LIST[0].trim());
                        //---params.put("LCIQTY", AA_P_PART_LIST[1].trim());
                        //---params.put("LCISEQ", String.valueOf(i + 1));

                        //LC3220P 인서트
                        //Database.getInstanceDB2().insert("environment.LC_ASREGN_API_I06", params);
                        //배송변경접수상세
                        mapper.insertRegularShippingChangeDtl(
                            new SaveRegularShippingChangeDtlReq(cntrNo, cntrSn, asAkDvCd, akChdt, seq)
                        );
                    }

                    //LC3300H 인서트
                    //Database.getInstanceDB2().insert("environment.LC_ASREGN_API_I05", params);
                    mapper.insertRegularShippingChangeHist(historyReq);

                }

                step2 = true;
            } else {
                //LC3220P 삭제 처리
                //Database.getInstanceDB2().delete("environment.LC_ASREGN_API_D02", params);
                mapper.deleteRegularShippingChangeDtl(
                    new SaveRegularShippingChangeDtlReq(cntrNo, cntrSn, asAkDvCd, akChdt, 0)
                );

                //LC3200P 업데이트
                //Database.getInstanceDB2().insert("environment.LC_ASREGN_API_U05", params);
                mapper.updateRegularShippingChangeBase(
                    new SaveRegularShippingChangeBaseReq(cntrNo, cntrSn, asAkDvCd, akChdt)
                );

                //LC3220P 인서트
                if (partList.length() > 0) {

                    //자유선택 패키지이고 선택 캡슐이 있다면,
                    String[] A_P_PART_LIST = partList.split("\\|");

                    for (int i = 0; i < A_P_PART_LIST.length; i++) {
                        String[] AA_P_PART_LIST = A_P_PART_LIST[i].split(",");
                        //---params.put("LCICDE", AA_P_PART_LIST[0].trim()); //변경적용예정년월일
                        //---params.put("LCIQTY", AA_P_PART_LIST[1].trim()); //작업자
                        //---params.put("LCISEQ", String.valueOf(i + 1)); //작업자

                        //LC3220P 인서트
                        //Database.getInstanceDB2().insert("environment.LC_ASREGN_API_I06", params);
                        mapper.insertRegularShippingChangeDtl(
                            new SaveRegularShippingChangeDtlReq(cntrNo, cntrSn, asAkDvCd, akChdt, 0)
                        );
                    }
                }

                //LC3300H 인서트
                //Database.getInstanceDB2().insert("environment.LC_ASREGN_API_I05", params);
                mapper.insertRegularShippingChangeHist(historyReq);

                step2 = true;
            }

        }

        if (step1 && !step2) {
            result = "E998";
            msg = "DB2 처리 오류";
            return new SaveRes(result, msg);
        }

        return new SaveRes(result, msg);
    }
}
