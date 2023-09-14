package com.kyowon.sms.wells.web.service.interfaces.service;

import com.kyowon.sms.wells.web.service.interfaces.dto.WsnbRegularShippingChangeDto.*;
import com.kyowon.sms.wells.web.service.interfaces.mapper.WsnbRegularShippingChangeMapper;
import com.sds.sflex.common.utils.StringUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

    /**
     * 정기배송 변경처리
     *
     * @programId W-SV-I-0016
     * @see 홈카페 캡슐 상품/서비스 변경 처리
     *      environmentController.java > LC_ASREGN_API_005(HttpServletRequest request, HttpServletResponse response)
     */
    public SaveRes saveRegularShippingChange(SaveReq req) {

        boolean step1 = false;
        boolean step2 = false;
        String msg = "";

        //@TODO 이진성 프로 작업 예정
        //홈카페 캡슐 패키지/서비스 변경 오라클
        //String result = LC_ASREGN_API_I04_T;
        String result = "S001";

        if (result.length() > 0 && result.equals("S001"))
            step1 = true;

        if (step1) {

            /* 2. 5250 인서트 로직 */
            String cntrNo = req.cntrNo();
            String cntrSn = req.cntrSn();
            String reqGb = req.reqGb();
            String reqDt = req.reqDt();
            String reqSaleCd = req.reqBasePdCd(); // LCPKAG 변경판매코드
            String partList = req.partList(); /*자유패키지 캡슐 구성 정보 > 판매코드,수량 | 판매코드, 수량 |~~~ */
            String mtrProcsStatCd = req.mtrProcsStatCd();

            SaveRegularShippingChangeHistReq historyReq = new SaveRegularShippingChangeHistReq(
                cntrNo, cntrSn, mtrProcsStatCd, reqGb, reqDt
            );
            SaveRegularShippingChangeBaseReq baseReq = new SaveRegularShippingChangeBaseReq(
                cntrNo, cntrSn, reqGb, reqDt
            );


            /*1.먼저 LCLIB.LD3200P 에 미처리 된 같은 요청이 존재하는지 체크*/
            //int LD3200_CNT = LC_ASREGN_API_S09(request, response);
            if (StringUtil.nvl2(mtrProcsStatCd,"").equals("3")) {

                //Database.getInstanceDB2().insert("environment.LC_ASREGN_API_I05", params);
                mapper.insertRegularShippingChangeHist(historyReq);

                //Database.getInstanceDB2().delete("environment.LC_ASREGN_API_D02", params);
                mapper.deleteRegularShippingChangeDtl(new SaveRegularShippingChangeDtlReq(cntrNo, cntrSn, reqGb, reqDt, 0));

                //Database.getInstanceDB2().delete("environment.LC_ASREGN_API_D01", params);
                mapper.deleteRegularShippingChangeBase(baseReq);

                step2 = true;

            } else if (mapper.selectRegularShippingChangeCount(new SearchRegularShippingChangeBaseReq(cntrNo, cntrSn, reqGb, reqDt)) == 0) {

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
                        mapper.insertRegularShippingChangeDtl(new SaveRegularShippingChangeDtlReq(cntrNo, cntrSn, reqGb, reqDt, seq));
                    }

                    //LC3300H 인서트
                    //Database.getInstanceDB2().insert("environment.LC_ASREGN_API_I05", params);
                    mapper.insertRegularShippingChangeHist(historyReq);

                }

                step2 = true;
            } else {
                //LC3220P 삭제 처리
                //Database.getInstanceDB2().delete("environment.LC_ASREGN_API_D02", params);
                mapper.deleteRegularShippingChangeDtl(new SaveRegularShippingChangeDtlReq(cntrNo, cntrSn, reqGb, reqDt, 0));

                //LC3200P 업데이트
                //Database.getInstanceDB2().insert("environment.LC_ASREGN_API_U05", params);
                mapper.updateRegularShippingChangeBase(new SaveRegularShippingChangeBaseReq(cntrNo, cntrSn, reqGb, reqDt));

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
                        mapper.insertRegularShippingChangeDtl(new SaveRegularShippingChangeDtlReq(cntrNo, cntrSn, reqGb, reqDt, 0));
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
