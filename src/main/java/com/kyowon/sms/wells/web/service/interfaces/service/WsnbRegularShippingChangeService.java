package com.kyowon.sms.wells.web.service.interfaces.service;

import com.kyowon.sflex.common.message.dvo.SmsSendReqDvo;
import com.kyowon.sflex.common.message.service.SmsMessageService;
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
import com.sds.sflex.system.config.context.SFLEXContextHolder;

import java.util.HashMap;
import java.util.Map;

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
    private final WsniSidingServiceChangesMapper mapper3;
    private final WsnbIndividualVisitPrdService service1;
    private final WsnbCustomerRglrBfsvcDlService service2;
    private final WsncRegularBfsvcAsnService service3;
    private final SmsMessageService smsMessageService;

    /**
    * PR_KIWI_CAPSULE_CHANGE
    * */
    public void capsuleChange(SaveReq req) throws Exception {

        /**변경구분 1:패키지변경, 4:차월 방문 중지**/
        String asAkDvCd = req.asAkDvCd();

        /**작업구분 1:신규, 2:변경, 3:취소**/
        String mtrProcsStatCd = req.mtrProcsStatCd();

        /**홈카페AS요청이력**/
        mapper.insertTbSvpdHcfAsAkHist(req);

        /**취소일 경우 삭제 **/
        if ("3".equals(mtrProcsStatCd))
            mapper.deleteTbSvpdHcfAsAkIz(req);
        else {
            /**해당 키로 존재하는지 체크**/
            if (mapper.countTbSvpdHcfAsAkIz(req)
                .intValue() > 0)
                mapper.updateTbSvpdHcfAsAkIz(req);
            else
                mapper.insertTbSvpdHcfAsAkIz(req);
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
        //WsniSidingServiceChangesDvo dvo = mapper3.selectCustomer(req.cntrNo(), req.cntrSn());

        /*요청 구분에 따라 처리 - 1: 패키지변경, 4:다음회차 방문 중지*/
        if ("1".equals(asAkDvCd) && !"3".equals(mtrProcsStatCd)) {

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

            WsniSidingServiceChangesDvo dvo = mapper3.selectBsTarget(
                req.cntrNo(), req.cntrSn(),
                req.akChdt().substring(0, 6)
            );

            if (StringUtil.isNotEmpty(dvo.getCstSvAsnNo())) {

                /*고객 정기BS 삭제(SP_LC_SERVICEVISIT_482_LST_I07)*/
                service2.removeRglrBfsvcDl(
                    new WsnbCustomerRglrBfsvcDlDto.SaveReq(
                        dvo.getCstSvAsnNo(),
                        req.akChdt().substring(0, 6)
                    )
                );

                /*고객 정기BS 배정(SP_LC_SERVICEVISIT_482_LST_I03)*/
                service3.processRegularBfsvcAsn(
                    new WsncRegularBfsvcAsnDto.SaveProcessReq(
                        req.akChdt().substring(0, 6),
                        SFLEXContextHolder.getContext().getUserSession().getUserId(),
                        req.cntrNo(),
                        req.cntrSn()
                    )
                );

                /*알림톡발송*/
                final Map<String, Object> paramMap = new HashMap<>();
                paramMap.put("cntrNo", req.cntrNo());
                paramMap.put("cntrSn", req.cntrSn());
                smsMessageService.sendMessage(
                    SmsSendReqDvo.withTemplateId()
                        .templateId("Wells18100")
                        .templateParamMap(paramMap)
                        .destInfo(
                            dvo.getRcgvpKnm() + "^" + dvo.getCralLocaraTno() + dvo.getMexnoEncr() + dvo.getCralIdvTno()
                        )
                        .callback("15884113")
                        .build()
                );
            }

        }

        /*요청 구분에 따라 처리 - 1: 패키지변경, 4:다음회차 방문 중지*/
        if ("4".equals(req.asAkDvCd())) {
            mapper.updateStopNextSiding(req);
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

        //홈카페 캡슐 패키지/서비스 변경 오라클
        //String result = LC_ASREGN_API_I04_T; -> PR_KIWI_CAPSULE_CHANGE
        capsuleChange(req);
        String result = "S001";

        /* 2. 5250 인서트 로직 */
        String cntrNo = req.cntrNo();
        String cntrSn = req.cntrSn();
        String asAkDvCd = req.asAkDvCd();
        String akChdt = req.akChdt();
        //String afchPdCd = req.afchPdCd(); // LCPKAG 변경판매코드
        String partList = req.choCapslCn(); /*자유패키지 캡슐 구성 정보 > 판매코드,수량 | 판매코드, 수량 |~~~ */
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
                    //String[] AA_P_PART_LIST = A_P_PART_LIST[i].split(",");
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
                    //String[] AA_P_PART_LIST = A_P_PART_LIST[i].split(",");
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

        }

        return new SaveRes(result, "");
    }
}
