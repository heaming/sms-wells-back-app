package com.kyowon.sms.wells.web.bond.credit.service;

import java.util.*;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.bond.credit.dto.WbndRentalCbInformationDto.SearchContractPresentStateReq;
import com.kyowon.sms.wells.web.bond.credit.dvo.WbndRentalCbInformationDvo;
import com.kyowon.sms.wells.web.bond.credit.ivo.ONIC2_CBNO1003.request.*;
import com.kyowon.sms.wells.web.bond.credit.mapper.WbndRentalCbInformationMapper;
import com.kyowon.sms.wells.web.bond.zcommon.constants.BnBondConst;
import com.sds.sflex.common.common.service.CruzLinkInterfaceService;
import com.sds.sflex.common.utils.StringUtil;
import com.sds.sflex.system.config.exception.BizException;
import com.sds.sflex.system.config.validation.BizAssert;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * <pre>
 * 렌탈CB 정보
 * </pre>
 *
 * @author sunghun.choi
 * @since 2023-05-26
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class WbndRentalCbInformationService {

    private final WbndRentalCbInformationMapper mapper;
    private final CruzLinkInterfaceService interfaceService;
    private static final String RENTAL_CB_URL = "ONIC2_CBNO1003";

    /**
     * 렌탈CB 정보 - 계약현황(미해지 계약) 조회
     * @param dto 검색 조건
     * @return 렌탈CB 정보 - 계약현황(미해지 계약) 조회 결과
     */
    public List<WbndRentalCbInformationDvo> getContractPresentStates(SearchContractPresentStateReq dto)
        throws Exception {
        List<WbndRentalCbInformationDvo> res = new ArrayList<>();

        Map<String, Object> paramMap = new HashMap<>();
        String insHpNo = dto.insHpNo1() + "" + dto.insHpNo2() + "" + dto.insHpNo3();
        String insHomNo = dto.insHomNo1() + "" + dto.insHomNo2() + "" + dto.insHomNo3();
        String insAdr = dto.insAdrWAD1() + "" + dto.insAdrWAD2() + "" + dto.insAdrWAD3();

        paramMap.put("keyDiv", dto.keyDiv());
        paramMap.put("rsdnNo", dto.rsdnNo());
        paramMap.put("cstNo", dto.cstNo());
        paramMap.put("inqRsnCd", dto.inqRsnCd());
        paramMap.put("inqwtcnRsnCd", dto.inqwtcnRsnCd());
        paramMap.put("insHpNo", insHpNo);
        paramMap.put("insHomNo", insHomNo);
        paramMap.put("insAdr", insAdr);

        String TransSeq = mapper.selectTransSeq();
        paramMap.put("TransSeq", TransSeq);
        // 인터페이스 호출 ivo 생성
        RentalCBInformationReqIvo req = getRentalCBInformationReqIvo(paramMap);

        Map<String, Object> ROWDATA_5; //렌탈CB연체(일반)세그먼트
        Map<String, Object> SUMMARYITEM_6; //일반 요약항목 세그먼트
        Map<String, Object> SUMMARYITEM_7; //기타 요약항목 세그먼트

        List<Map<String, Object>> ROWDATA_5_REPEAT = new ArrayList<>();//렌탈CB연체(렌터카) 세그먼트 반복부
        Map<String, Object> SUMMARYITEM_6_REPEAT = new HashMap<>(); //일반 요약항목 세그먼트 반복부
        Map<String, Object> SUMMARYITEM_7_REPEAT = new HashMap<>(); //기타 요약항목 세그먼트 반복부

        try {
            // ONIC2_CBNO1003 (CB렌탈정보 조회 요청) 인터페이스 호출
            Map<String, Object> body = interfaceService.post(RENTAL_CB_URL, req, Map.class);
            log.debug("CB렌탈정보 조회 응답 데이터 >>>>>>>>>>>>>>>>>>>>>>>>>>>> {}", body);

            Map<String, Object> COMM = (Map<String, Object>)body.get("COMM");

            String rplyCd = COMM.get("rplyCd").toString();
            paramMap.put("rplyCd", rplyCd);
            log.debug("응답코드 >>>>>>>>>>>>>>>>>>>>>>>>>>>> {}", rplyCd);

            /**
             *  P000        정상
             */
            if (rplyCd.equals("P000")) {
                ROWDATA_5 = (Map<String, Object>)body.get("ROWDATA_5");
                SUMMARYITEM_6 = (Map<String, Object>)body.get("SUMMARYITEM_6");
                SUMMARYITEM_7 = (Map<String, Object>)body.get("SUMMARYITEM_7");

                log.debug("CB렌탈정보 ROWDATA_5 데이터 >>>>>>>>>>>>>>>>>>>>>>>>>>>> {}", ROWDATA_5);
                log.debug("CB렌탈정보 SUMMARYITEM_6 데이터 >>>>>>>>>>>>>>>>>>>>>>>>>>>> {}", SUMMARYITEM_6);
                log.debug("CB렌탈정보 SUMMARYITEM_7 데이터 >>>>>>>>>>>>>>>>>>>>>>>>>>>> {}", SUMMARYITEM_7);

                // 렌탈CB연체(일반)세그먼트>>>  0 or 1건 : {} ,  2건이상 : [{},{}] 처리
                if (Integer.parseInt(ROWDATA_5.get("respCnt5").toString()) == 1) {
                    ROWDATA_5_REPEAT.add((Map<String, Object>)body.get("ROWDATA_5_REPEAT"));
                    log.debug("CB렌탈정보 ROWDATA_5_REPEAT 데이터 >>>>>>>>>>>>>>>>>>>>>>>>>>>> {}", ROWDATA_5_REPEAT);
                } else if (Integer.parseInt(ROWDATA_5.get("respCnt5").toString()) > 1) {
                    ROWDATA_5_REPEAT = (List<Map<String, Object>>)body.get("ROWDATA_5_REPEAT");
                    log.debug("CB렌탈정보 ROWDATA_5_REPEAT 데이터 >>>>>>>>>>>>>>>>>>>>>>>>>>>> {}", ROWDATA_5_REPEAT);
                }

                // 렌탈CB연체(일반)세그먼트 코드 매핑
                if (Integer.parseInt(ROWDATA_5.get("respCnt5").toString()) > 0) {
                    Map<String, Object> params;
                    String rst = "";
                    for (Map<String, Object> repeat5Map : ROWDATA_5_REPEAT) {
                        //렌탈 상품코드(소분류)
                        rst = BnBondConst.RentalPdCd.getValue((String)repeat5Map.get("rntlPrdtCdS5"));
                        if (StringUtil.isNotBlank(rst)) {
                            repeat5Map.put("rntlPrdtNmS5", rst);
                        }

                        //연체구분코드
                        rst = BnBondConst.RentalPdCd.getValue((String)repeat5Map.get("delyDivCd5"));
                        if (StringUtil.isNotBlank(rst)) {
                            repeat5Map.put("delyDivNm5", rst);
                        }

                    }
                }

                // 일반 요약항목 세그먼트>>>  0 : {Nodata} , 1건 : {} ,  2건이상 : [{},{}] 처리
                if (Integer.parseInt(SUMMARYITEM_6.get("respCnt6").toString()) == 1) {
                    Map<String, Object> SUMMARYITEM_6_REPEAT_OBJ = (Map<String, Object>)body
                        .get("SUMMARYITEM_6_REPEAT");

                    log.debug(
                        "CB렌탈정보 SUMMARYITEM_6_REPEAT 데이터 >>>>>>>>>>>>>>>>>>>>>>>>>>>> {}", SUMMARYITEM_6_REPEAT_OBJ
                    );

                    SUMMARYITEM_6_REPEAT.put(
                        SUMMARYITEM_6_REPEAT_OBJ.get("siCd6").toString(),
                        SUMMARYITEM_6_REPEAT_OBJ.get("siVal6").toString()
                    );
                } else if (Integer.parseInt(SUMMARYITEM_6.get("respCnt6").toString()) > 1) {
                    List<Map<String, Object>> SUMMARYITEM_6_REPEAT_LIST = (List<Map<String, Object>>)body
                        .get("SUMMARYITEM_6_REPEAT");

                    for (Map<String, Object> repeat6Map : SUMMARYITEM_6_REPEAT_LIST) {
                        SUMMARYITEM_6_REPEAT.put(
                            repeat6Map.get("siCd6").toString(),
                            repeat6Map.get("siVal6").toString()
                        );
                    }

                    log.debug("CB렌탈정보 SUMMARYITEM_6_REPEAT 데이터 >>>>>>>>>>>>>>>>>>>>>>>>>>>> {}", SUMMARYITEM_6_REPEAT);

                }

                //기타  요약항목 세그먼트>>> 0 : {Nodata} , 1건 : {} ,  2건이상 : [{},{}] 처리
                if (Integer.parseInt(SUMMARYITEM_7.get("respCnt7").toString()) == 1) {
                    Map<String, Object> SUMMARYITEM_7_OBJ = (Map<String, Object>)body.get("SUMMARYITEM_7_REPEAT");
                    SUMMARYITEM_7_REPEAT
                        .put(
                            SUMMARYITEM_7_OBJ.get("etcItmCd7").toString(),
                            SUMMARYITEM_7_OBJ.get("etcItmVal7").toString()
                        );

                    log.debug("CB렌탈정보 SUMMARYITEM_7_REPEAT 데이터 >>>>>>>>>>>>>>>>>>>>>>>>>>>> {}", SUMMARYITEM_7_REPEAT);
                } else if (Integer.parseInt(SUMMARYITEM_7.get("respCnt7").toString()) > 1) {
                    List<Map<String, Object>> SUMMARYITEM_7_LIST = (List<Map<String, Object>>)body
                        .get("SUMMARYITEM_7_REPEAT");
                    for (int i = 0; i < SUMMARYITEM_7_LIST.size(); i++) {
                        if ("999999802".equals(SUMMARYITEM_7_LIST.get(i).get("etcItmVal7").toString())
                            || "999999801".equals(SUMMARYITEM_7_LIST.get(i).get("etcItmVal7").toString())) { // 조회 조건 불충 분시 999999802 코드 전송됨
                            SUMMARYITEM_7_REPEAT.put(SUMMARYITEM_7_LIST.get(i).get("etcItmCd7").toString(), 0);
                        } else {
                            SUMMARYITEM_7_REPEAT.put(
                                SUMMARYITEM_7_LIST.get(i).get("etcItmCd7").toString(),
                                SUMMARYITEM_7_LIST.get(i).get("etcItmVal7").toString()
                            );
                        }
                    }
                }

                //납입율 계산
                //납입율 계산  >>> 약정납입액 && 실제납입액(현재 )
                if (SUMMARYITEM_6_REPEAT.get("RT0100201") != null
                    && SUMMARYITEM_6_REPEAT.get("RT0100202") != null) {
                    try {
                        int RT0100201 = Integer.parseInt(SUMMARYITEM_6_REPEAT.get("RT0100201").toString());
                        int RT0100202 = Integer.parseInt(SUMMARYITEM_6_REPEAT.get("RT0100202").toString());
                        double CAL_PER_0 = 0;
                        if (RT0100201 > 0) {
                            CAL_PER_0 = (double)RT0100202 / RT0100201 * 100;
                        }

                        SUMMARYITEM_6_REPEAT.put("CAL_PER_0", String.format("%,.0f", CAL_PER_0));

                    } catch (Exception e) {
                        log.debug("납입율 계산 오류(0) :  " + e);
                    }

                }
                //납입율 계산  >>> 약정납입액 && 실제납입액(3)
                if (SUMMARYITEM_6_REPEAT.get("RT0300203") != null
                    && SUMMARYITEM_6_REPEAT.get("RT0300204") != null) {
                    try {
                        int RT0300203 = Integer.parseInt(SUMMARYITEM_6_REPEAT.get("RT0300203").toString());
                        int RT0300204 = Integer.parseInt(SUMMARYITEM_6_REPEAT.get("RT0300204").toString());

                        double CAL_PER_3 = 0;
                        if (RT0300203 > 0) {
                            CAL_PER_3 = (double)RT0300204 / RT0300203 * 100;
                        }
                        SUMMARYITEM_6_REPEAT.put("CAL_PER_3", String.format("%,.0f", CAL_PER_3));

                    } catch (Exception e) {
                        log.debug("납입율 계산 오류(3) :  " + e);
                    }

                }
                //납입율 계산  >>> 약정납입액 && 실제납입액(6)
                if (SUMMARYITEM_6_REPEAT.get("RT0600203") != null
                    && SUMMARYITEM_6_REPEAT.get("RT0600204") != null) {
                    try {
                        int RT0600203 = Integer.parseInt(SUMMARYITEM_6_REPEAT.get("RT0600203").toString());
                        int RT0600204 = Integer.parseInt(SUMMARYITEM_6_REPEAT.get("RT0600204").toString());

                        double CAL_PER_6 = 0;
                        if (RT0600203 > 0) {
                            CAL_PER_6 = (double)RT0600204 / RT0600203 * 100;
                        }
                        SUMMARYITEM_6_REPEAT.put("CAL_PER_6", String.format("%,.0f", CAL_PER_6));

                    } catch (Exception e) {
                        log.debug("납입율 계산 오류(6) :  " + e);
                    }

                }
                //납입율 계산  >>> 약정납입액 && 실제납입액(12)
                if (SUMMARYITEM_6_REPEAT.get("RT1200203") != null
                    && SUMMARYITEM_6_REPEAT.get("RT1200204") != null) {
                    try {
                        int RT1200203 = Integer.parseInt(SUMMARYITEM_6_REPEAT.get("RT1200203").toString());
                        int RT1200204 = Integer.parseInt(SUMMARYITEM_6_REPEAT.get("RT1200204").toString());

                        double CAL_PER_12 = 0;
                        if (RT1200203 > 0) {
                            CAL_PER_12 = (double)RT1200204 / RT1200203 * 100;
                        }
                        SUMMARYITEM_6_REPEAT.put("CAL_PER_12", String.format("%,.0f", CAL_PER_12));

                    } catch (Exception e) {
                        log.debug("납입율 계산 오류(12) :  " + e);
                    }

                }
                //납입율 계산  >>> 약정납입액 && 실제납입액(24)
                if (SUMMARYITEM_6_REPEAT.get("RT2400103") != null
                    && SUMMARYITEM_6_REPEAT.get("RT2400104") != null) {
                    try {
                        int RT2400103 = Integer.parseInt(SUMMARYITEM_6_REPEAT.get("RT2400103").toString());
                        int RT2400104 = Integer.parseInt(SUMMARYITEM_6_REPEAT.get("RT2400104").toString());
                        double CAL_PER_24 = 0;
                        if (RT2400103 > 0) {
                            CAL_PER_24 = (double)RT2400104 / RT2400103 * 100;
                        }
                        SUMMARYITEM_6_REPEAT.put("CAL_PER_24", String.format("%,.0f", CAL_PER_24));

                    } catch (Exception e) {
                        log.debug("납입율 계산 오류(24) :  " + e);
                    }

                }
                //납입율 계산  >>> 약정납입액 && 실제납입액(36)
                if (SUMMARYITEM_6_REPEAT.get("RT3600103") != null
                    && SUMMARYITEM_6_REPEAT.get("RT3600104") != null) {
                    try {
                        int RT3600103 = Integer.parseInt(SUMMARYITEM_6_REPEAT.get("RT3600103").toString());
                        int RT3600104 = Integer.parseInt(SUMMARYITEM_6_REPEAT.get("RT3600104").toString());
                        double CAL_PER_36 = 0;
                        if (RT3600103 > 0) {
                            CAL_PER_36 = (double)RT3600104 / RT3600103 * 100;
                        }
                        SUMMARYITEM_6_REPEAT.put("CAL_PER_36", String.format("%,.0f", CAL_PER_36));

                    } catch (Exception e) {
                        log.debug("납입율 계산 오류(36) :  " + e);
                    }

                }
                if (SUMMARYITEM_6_REPEAT.size() != 0) {
                    String[] type = {"A", "A", "A", "B", "B", "B", "C", "C", "C"};
                    String[] baseNm = {"총건수", "기관수", "총약정액", "약정납입액", "실제납입액", "납입률", "계약건수", "계약총금액", "계약기관수"};
                    String[] crtlTot1 = {"RT0000001", "RT0000101", "RT0000201", "RT0100201", "RT0100202", "CAL_PER_0",
                        "RT8000001",
                        "RT8000201", "RT8000101"};
                    String[] crtlTot2 = {"RT0300001", "RT0300101", "RT0300201", "RT0300203", "RT0300204", "CAL_PER_3",
                        "RT0100001",
                        "RT0100205", "RT0100101"};
                    String[] crtlTot3 = {"RT0600001", "RT0600101", "RT0600201", "RT0600203", "RT0600204", "CAL_PER_6",
                        "RT0200001",
                        "RT0200201", "RT0200101"};
                    String[] crtlTot4 = {"RT1200001", "RT1200101", "RT1200201", "RT1200203", "RT1200204", "CAL_PER_12",
                        "RT0300003",
                        "RT0300207", "RT0300103"};
                    String[] crtlTot5 = {"RT2400101", "RT2400101", "RT2400201", "RT2400103", "RT2400104", "CAL_PER_24",
                        "RT0600003",
                        "RT0600207", "RT0600103"};
                    String[] crtlTot6 = {"RT3600001", "RT3600101", "RT3600201", "RT3600103", "RT3600104", "CAL_PER_36",
                        "RT1200003",
                        "RT1200207", "RT1200103"};
                    for (int i = 0; i < 9; i++) {
                        try {
                            WbndRentalCbInformationDvo cbVo = new WbndRentalCbInformationDvo();
                            cbVo.setType(type[i]);
                            cbVo.setBaseNm(baseNm[i]);
                            cbVo.setCrtlTot1(SUMMARYITEM_6_REPEAT.get(crtlTot1[i]).toString());
                            cbVo.setCrtlTot2(SUMMARYITEM_6_REPEAT.get(crtlTot2[i]).toString());
                            cbVo.setCrtlTot3(SUMMARYITEM_6_REPEAT.get(crtlTot3[i]).toString());
                            cbVo.setCrtlTot4(SUMMARYITEM_6_REPEAT.get(crtlTot4[i]).toString());
                            cbVo.setCrtlTot5(SUMMARYITEM_6_REPEAT.get(crtlTot5[i]).toString());
                            cbVo.setCrtlTot6(SUMMARYITEM_6_REPEAT.get(crtlTot6[i]).toString());
                            res.add(cbVo);
                        } catch (NullPointerException e) {
                            log.debug("errMsg : {}", e.getMessage());
                        }
                    }
                }
                if (SUMMARYITEM_7_REPEAT.size() != 0) {
                    String[] type_D = {"D", "D", "D", "D", "D", "D", "D"};
                    String[] baseNm_D = {"휴대전화전화", "휴대전화전화", "휴대전화전화", "설치지주소", "설치지주소", "설치지주소", "자택번호"};
                    String[] ptrmPs_D = {"계약건수", "약정납입액", "실제납입액", "계약건수", "약정납입액", "실제납입액", "계약건수"};
                    String[] crtlTot_D1 = {"RENTB0001", "RENTB0002", "RENTB0003", "RENTB0004", "RENTB0005", "RENTB0006",
                        "RENTB0007"};
                    String[] crtlTot_D2 = {"RENTB0008", "RENTB0009", "RENTB0010", "RENTB0011", "RENTB0012", "RENTB0013",
                        "RENTB0014"};
                    String[] crtlTot_D3 = {"RENTB0015", "RENTB0016", "RENTB0017", "RENTB0018", "RENTB0019", "RENTB0020",
                        "RENTB0021"};
                    String[] crtlTot_D4 = {"RENTB0022", "RENTB0023", "RENTB0024", "RENTB0025", "RENTB0026", "RENTB0027",
                        "RENTB0028"};
                    String[] crtlTot_D5 = {"RENTB0029", "RENTB0030", "RENTB0031", "RENTB0032", "RENTB0033", "RENTB0034",
                        "RENTB0035"};
                    String[] crtlTot_D6 = {"RENTB0036", "RENTB0037", "RENTB0038", "RENTB0039", "RENTB0040", "RENTB0041",
                        "RENTB0042"};
                    for (int i = 0; i < 7; i++) {
                        try {
                            WbndRentalCbInformationDvo cbVo = new WbndRentalCbInformationDvo();
                            cbVo.setType(type_D[i]);
                            cbVo.setBaseNm(baseNm_D[i]);
                            cbVo.setPtrmPs(ptrmPs_D[i]);
                            cbVo.setCrtlTot1(SUMMARYITEM_7_REPEAT.get(crtlTot_D1[i]).toString());
                            cbVo.setCrtlTot2(SUMMARYITEM_7_REPEAT.get(crtlTot_D2[i]).toString());
                            cbVo.setCrtlTot3(SUMMARYITEM_7_REPEAT.get(crtlTot_D3[i]).toString());
                            cbVo.setCrtlTot4(SUMMARYITEM_7_REPEAT.get(crtlTot_D4[i]).toString());
                            cbVo.setCrtlTot5(SUMMARYITEM_7_REPEAT.get(crtlTot_D5[i]).toString());
                            cbVo.setCrtlTot6(SUMMARYITEM_7_REPEAT.get(crtlTot_D6[i]).toString());
                            res.add(cbVo);
                        } catch (NullPointerException e) {
                            log.debug("errMsg : {}", e.getMessage());
                        }
                    }
                }
                for (Map<String, Object> cbMap : ROWDATA_5_REPEAT) {
                    WbndRentalCbInformationDvo cbVo = new WbndRentalCbInformationDvo();
                    cbVo.setType("F");

                    if (cbMap.get("rntlPrdtNmS5") == null) {
                        cbVo.setBaseNm("");
                    } else {
                        cbVo.setBaseNm(String.valueOf(cbMap.get("rntlPrdtNmS5")));
                    }
                    if (cbMap.get("delyDivNm5") == null) {
                        cbVo.setCrtlTot1("");
                    } else {
                        cbVo.setCrtlTot1(String.valueOf(cbMap.get("delyDivNm5")));
                    }
                    cbVo.setCrtlTot2(String.valueOf(cbMap.get("ocuinstNm5")));
                    cbVo.setCrtlTot3(String.valueOf(cbMap.get("fistOcuDay5")));
                    cbVo.setCrtlTot4(String.valueOf(cbMap.get("ocuRegtDay5")));
                    cbVo.setCrtlTot5(String.valueOf(cbMap.get("delyAmt5")));
                    cbVo.setCrtlTot6(String.valueOf(cbMap.get("fistDelyAmt5")));
                    res.add(cbVo);
                }
            } else {
                // 에러 메세지 송출
                String rst;
                log.debug("응답코드 :  {}", rplyCd);

                rst = this.mapper.selectTransErrorCdMsg(rplyCd);
                if (StringUtil.isBlank(rst)) {
                    throw new BizException("[" + rplyCd + "]" + "등록되지 않은 응답 코드 오류.");
                }
                throw new BizException("[" + rplyCd + "]" + rst);
            }
        } catch (Exception e) {
            BizAssert.isFalse(true, "내부 서버 오류.[" + e.getMessage() + "]");
            //            throw new Exception("내부 서버 오류.[" + e + "]");
        } finally {
            // 렌탈CB 정보 조회 정보 Insert
            int result = mapper.insertCBSearchTrans(paramMap);
            BizAssert.isFalse(result == 0, "MSG_ALT_SVE_ERR");
        }
        return res;

    }

    /**
     * 렌탈CB 정보 - 호출 인터페이스 ivo 생성 로직
     * @param paramMap 검색 조건
     * @return RentalCBInformationReqIvo 호출 인터페이스 ivo
     */
    private RentalCBInformationReqIvo getRentalCBInformationReqIvo(Map<String, Object> paramMap) {
        RentalCBInformationReqIvo req = new RentalCBInformationReqIvo();

        // 1. 공통부
        CommReq commReq = new CommReq();
        commReq.setSpczGropCd("NICEIF");
        commReq.setDealKindClfyCd("0200");
        commReq.setDealDivCd("1R000");
        commReq.setSnrcFlag("B");
        commReq.setTrunDiv("503");
        commReq.setRplyCd("");
        commReq.setEtrcInttId("KYOWON12");
        commReq.setInttSpczAdmnNo(String.valueOf(paramMap.get("TransSeq")));
        commReq.setInttSpczTrnmHr(getT());
        commReq.setEtrcInttId("KYOWON12");
        commReq.setNiceSpczAdmnNo("0000000000");
        commReq.setNiceSpczTrnmHr("00000000000000");
        commReq.setBlnkC("");
        commReq.setInqwtcnRsnCd(String.valueOf(paramMap.get("inqwtcnRsnCd")));

        log.debug("공통부 >>>>>>>>>>>>>>>>>>>>>>>>>>>> {}", commReq);
        req.setCommReq(commReq);

        // 2. 개별요청부
        DataReq dataReq = new DataReq();
        dataReq.setKeyDiv(String.valueOf(paramMap.get("keyDiv")));
        dataReq.setRsdnNo(String.valueOf(paramMap.get("rsdnNo")));
        dataReq.setInsHpNo(String.valueOf(paramMap.get("insHpNo")));
        dataReq.setInsHomNo(String.valueOf(paramMap.get("insHomNo")));
        dataReq.setInsAdr(String.valueOf(paramMap.get("insAdr")));
        dataReq.setInqRsnCd(String.valueOf(paramMap.get("inqRsnCd")));
        dataReq.setRtryReqNum("00");
        dataReq.setInfoCntiYn("N");
        dataReq.setRepotCnfimNo("0000000000000");
        dataReq.setRwDtaSgmtReqCnt("0003");
        dataReq.setShtItmSgmtReqCnt("02");
        dataReq.setBlnkD("");

        log.debug("개별요청부 >>>>>>>>>>>>>>>>>>>>>>>>>>>> {}", dataReq);
        req.setDataReq(dataReq);

        // 3.렌탈CB식별정보
        RowData1Req rowData1Req = new RowData1Req();
        rowData1Req.setRdSgmtId1("CBR01");
        rowData1Req.setRdRecvCnt1("0");
        rowData1Req.setRdReqCnt1("999");

        log.debug("렌탈CB식별정보 >>>>>>>>>>>>>>>>>>>>>>>>>>>> {}", rowData1Req);
        req.setRowData1Req(rowData1Req);

        // 4.렌탈CB연체(렌터카)정보
        RowData4Req rowData4Req = new RowData4Req();
        rowData4Req.setRdSgmtId4("CBR04");
        rowData4Req.setRdRecvCnt4("0");
        rowData4Req.setRdReqCnt4("0");

        log.debug("렌탈CB연체(렌터카)정보 >>>>>>>>>>>>>>>>>>>>>>>>>>>> {}", rowData4Req);
        req.setRowData4Req(rowData4Req);

        // 5.렌탈CB연체(렌터카)정보
        RowData5Req rowData5Req = new RowData5Req();
        rowData5Req.setRdSgmtId5("CBR05");
        rowData5Req.setRdRecvCnt5("0");
        rowData5Req.setRdReqCnt5("999");

        log.debug("렌탈CB연체(렌터카)정보 >>>>>>>>>>>>>>>>>>>>>>>>>>>> {}", rowData5Req);
        req.setRowData5Req(rowData5Req);

        // 6.렌탈CB 일반 요약항목
        SummaryItem6Req summaryItem6Req = new SummaryItem6Req();
        summaryItem6Req.setSiSgmtId6("CBR06");
        summaryItem6Req.setSiRecvCnt6("0");
        summaryItem6Req.setSiReqCnt6("48");

        log.debug("렌탈CB 일반 요약항목 >>>>>>>>>>>>>>>>>>>>>>>>>>>> {}", summaryItem6Req);
        req.setSummaryItem6Req(summaryItem6Req);

        // 7.렌탈CB 일반 요약항목 반복부
        List<SummaryItem6RepaetItemReq> summaryItem6RepaetItemReqs = new ArrayList<>();
        //미해지 일반렌탈 총건수
        List<String> item6Repaet1 = Arrays
            .asList("RT0000001", "RT0300001", "RT0600001", "RT1200001", "RT2400001", "RT3600001");
        //미해지 일반렌탈 총기관수
        List<String> item6Repaet2 = Arrays
            .asList("RT0000101", "RT0300101", "RT0600101", "RT1200101", "RT2400101", "RT3600101");
        //미해지 일반렌탈 총계약금액
        List<String> item6Repaet3 = Arrays
            .asList("RT0000201", "RT0300201", "RT0600201", "RT1200201", "RT2400201", "RT3600201");

        //미해지 일반렌탈 총 약정납입액
        List<String> item6Repaet4 = Arrays
            .asList("RT0100201", "RT0300203", "RT0600203", "RT1200203", "RT2400103", "RT3600103");
        //미해지 일반렌탈 총 실제납입액
        List<String> item6Repaet5 = Arrays
            .asList("RT0100202", "RT0300204", "RT0600204", "RT1200204", "RT2400104", "RT3600104");

        //(최근)미해지 일반렌탈 총건수
        List<String> item6Repaet6 = Arrays
            .asList("RT8000001", "RT0100001", "RT0200001", "RT0300003", "RT0600003", "RT1200003");
        //(최근)미해지 일반렌탈 총계약금액
        List<String> item6Repaet7 = Arrays
            .asList("RT8000201", "RT0100205", "RT0200201", "RT0300207", "RT0600207", "RT1200207");
        //(최근)미해지 일반렌탈 총기관수
        List<String> item6Repaet8 = Arrays
            .asList("RT8000101", "RT0100101", "RT0200101", "RT0300103", "RT0600103", "RT1200103");

        List<String> item6Repaet = new ArrayList<>();
        item6Repaet.addAll(item6Repaet1);
        item6Repaet.addAll(item6Repaet2);
        item6Repaet.addAll(item6Repaet3);
        item6Repaet.addAll(item6Repaet4);
        item6Repaet.addAll(item6Repaet5);
        item6Repaet.addAll(item6Repaet6);
        item6Repaet.addAll(item6Repaet7);
        item6Repaet.addAll(item6Repaet8);

        for (String s : item6Repaet) {
            SummaryItem6RepaetItemReq summaryItem6RepaetItemReq = new SummaryItem6RepaetItemReq();
            summaryItem6RepaetItemReq.setSiCd6(s);
            summaryItem6RepaetItemReqs.add(summaryItem6RepaetItemReq);
        }

        log.debug("렌탈CB 일반 요약항목 반복부 >>>>>>>>>>>>>>>>>>>>>>>>>>>> {}", item6Repaet);
        req.setSummaryItem6RepaetItemReqs(summaryItem6RepaetItemReqs);

        // 7.렌탈CB 기타 요약항목
        SummaryItem7Req summaryItem7Req = new SummaryItem7Req();
        summaryItem7Req.setSiSgmtId7("CBR07");
        summaryItem7Req.setSiRecvCnt7("0");
        summaryItem7Req.setSiReqCnt7("42");

        log.debug("렌탈CB 기타 요약항목 >>>>>>>>>>>>>>>>>>>>>>>>>>>> {}", summaryItem7Req);
        req.setSummaryItem7Req(summaryItem7Req);

        // 8.렌탈CB 기타 요약항목 반복부
        List<SummaryItem7RepaetItemReq> summaryItem7RepaetItemReqs = new ArrayList<>();
        //휴대폰번호 기준 렌탈(일반) 계약 건수
        List<String> Item7Repaet1 = Arrays
            .asList("RENTB0001", "RENTB0008", "RENTB0015", "RENTB0022", "RENTB0029", "RENTB0036");
        //휴대폰번호 기준 렌탈(일반) 약정 납입액
        List<String> Item7Repaet2 = Arrays
            .asList("RENTB0002", "RENTB0009", "RENTB0016", "RENTB0023", "RENTB0030", "RENTB0037");
        //휴대폰번호 기준 렌탈(일반) 실제 납입액
        List<String> Item7Repaet3 = Arrays
            .asList("RENTB0003", "RENTB0010", "RENTB0017", "RENTB0024", "RENTB0031", "RENTB0038");

        //        //휴대폰번호 기준 렌탈(렌터카) 계약 건수
        //        List<String> Item7Repaet4 = Arrays
        //            .asList("RENTA0001", "RENTA0008", "RENTA0015", "RENTA0022", "RENTA0029", "RENTA0036");
        //        //휴대폰번호 기준 렌탈(렌터카) 약정 납입액
        //        List<String> Item7Repaet5 = Arrays
        //            .asList("RENTA0002", "RENTA0009", "RENTA0016", "RENTA0023", "RENTA0030", "RENTA0037");
        //        //휴대폰번호 기준 렌탈(렌터카) 실제 납입액
        //        List<String> Item7Repaet6 = Arrays
        //            .asList("RENTA0003", "RENTA0010", "RENTA0017", "RENTA0024", "RENTA0031", "RENTA0038");

        //설치지주소 기준 렌탈(일반) 계약 건수
        List<String> Item7Repaet7 = Arrays
            .asList("RENTB0004", "RENTB0011", "RENTB0018", "RENTB0025", "RENTB0032", "RENTB0039");
        //설치지주소 기준 렌탈(일반) 약정 납입액
        List<String> Item7Repaet8 = Arrays
            .asList("RENTB0005", "RENTB0012", "RENTB0019", "RENTB0026", "RENTB0033", "RENTB0040");
        ////설치지주소 기준 렌탈(일반) 실제 납입액
        List<String> Item7Repaet9 = Arrays
            .asList("RENTB0006", "RENTB0013", "RENTB0020", "RENTB0027", "RENTB0034", "RENTB0041");

        //자택번호 기준 렌탈(일반) 계약 건수
        List<String> Item7Repaet10 = Arrays
            .asList("RENTB0007", "RENTB0014", "RENTB0021", "RENTB0028", "RENTB0035", "RENTB0042");
        //자택번호 기준 렌탈(렌터카) 계약 건수
        //        List<String> Item7Repaet11 = Arrays
        //            .asList("RENTA0007", "RENTA0014", "RENTA0021", "RENTA0028", "RENTA0035", "RENTA0042");

        List<String> item7Repaet = new ArrayList<>();
        item7Repaet.addAll(Item7Repaet1);
        item7Repaet.addAll(Item7Repaet2);
        item7Repaet.addAll(Item7Repaet3);
        //        item7Repaet.addAll(Item7Repaet4);
        //        item7Repaet.addAll(Item7Repaet5);
        //        item7Repaet.addAll(Item7Repaet6);
        item7Repaet.addAll(Item7Repaet7);
        item7Repaet.addAll(Item7Repaet8);
        item7Repaet.addAll(Item7Repaet9);
        item7Repaet.addAll(Item7Repaet10);
        //        item7Repaet.addAll(Item7Repaet11);

        for (String s : item7Repaet) {
            SummaryItem7RepaetItemReq summaryItem7RepaetItemReq = new SummaryItem7RepaetItemReq();
            summaryItem7RepaetItemReq.setSiCd7(s);
            summaryItem7RepaetItemReqs.add(summaryItem7RepaetItemReq);
        }

        log.debug("렌탈CB 기타 요약항목 반복부 >>>>>>>>>>>>>>>>>>>>>>>>>>>> {}", item7Repaet);
        req.setSummaryItem7RepaetItemReqs(summaryItem7RepaetItemReqs);

        return req;
    }

    private String getT() {
        Calendar calendar = Calendar.getInstance();
        String date = calendar.get(Calendar.YEAR) + ""
            + get2Ciper(calendar.get(Calendar.MONTH) + 1) + ""
            + get2Ciper(calendar.get(Calendar.DAY_OF_MONTH));

        String time = get2Ciper(calendar.get(Calendar.HOUR_OF_DAY)) + ""
            + get2Ciper(calendar.get(Calendar.MINUTE)) + ""
            + get2Ciper(calendar.get(Calendar.SECOND));
        return date + time;
    }

    private String get2Ciper(int i) {
        if (i < 10) {
            return "0" + i;
        } else {
            return String.valueOf(i);
        }
    }
}
