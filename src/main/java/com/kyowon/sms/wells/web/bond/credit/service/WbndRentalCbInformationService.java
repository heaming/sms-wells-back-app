package com.kyowon.sms.wells.web.bond.credit.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kyowon.sms.wells.web.bond.credit.dto.WbndRentalCbInformationDto.SearchContractPresentStateReq;
import com.kyowon.sms.wells.web.bond.credit.dvo.WbndRentalCbInformationDvo;
import com.kyowon.sms.wells.web.bond.credit.mapper.WbndRentalCbInformationMapper;

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

    /**
     * 렌탈CB 정보 - 계약현황(미해지 계약) 조회
     * @param dto 검색 조건
     * @return 렌탈CB 정보 - 계약현황(미해지 계약) 조회 결과
     */
    public List<WbndRentalCbInformationDvo> getContractPresentStates(SearchContractPresentStateReq dto)
        throws Exception {

        Map<String, Object> resultMap = new HashMap<String, Object>(); //UI 전송 return Map

        Map<String, Object> ROWDATA_5 = new HashMap<String, Object>(); //렌탈CB연체(일반)세그먼트
        Map<String, Object> SUMMARYITEM_6 = new HashMap<String, Object>(); //일반 요약항목 세그먼트
        Map<String, Object> SUMMARYITEM_7 = new HashMap<String, Object>(); //기타 요약항목 세그먼트

        List<Map<String, Object>> ROWDATA_5_REPEAT = new ArrayList<Map<String, Object>>();//렌탈CB연체(렌터카) 세그먼트 반복부
        Map<String, Object> SUMMARYITEM_6_REPEAT = new HashMap<String, Object>(); //일반 요약항목 세그먼트 반복부 
        Map<String, Object> SUMMARYITEM_7_REPEAT = new HashMap<String, Object>(); //기타 요약항목 세그먼트 반복부

        Map<String, Object> paramMap = new HashMap<String, Object>();

        List<WbndRentalCbInformationDvo> res = new ArrayList<>();

        try {
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

            String TransSeq = mapper.getTransSeq();
            paramMap.put("TransSeq", TransSeq);

            String rtnJson = Trans(paramMap);
            Map<String, Object> NcCbResultMap = new ObjectMapper().readValue(rtnJson, Map.class);
            Map<String, Object> cruzjson = (Map<String, Object>)NcCbResultMap.get("cruzjson"); // 최상위  KEY
            Map<String, Object> header = (Map<String, Object>)cruzjson.get("header");
            Map<String, Object> body = (Map<String, Object>)cruzjson.get("body");
            Map<String, Object> COMM = (Map<String, Object>)body.get("COMM");

            String rplyCd = COMM.get("rplyCd").toString();
            //regCbSearchInfoMap.put("KSRCDE", rplyCd);
            log.debug("응답코드 :  " + rplyCd);
            paramMap.put("rplyCd", rplyCd);

            /**
             *  P000        정상
             */

            if (rplyCd.equals("P000") || rplyCd.equals("5857")) {

                ROWDATA_5 = (Map<String, Object>)body.get("ROWDATA_5");
                SUMMARYITEM_6 = (Map<String, Object>)body.get("SUMMARYITEM_6");
                SUMMARYITEM_7 = (Map<String, Object>)body.get("SUMMARYITEM_7");

                // 렌탈CB연체(일반)세그먼트>>>  0 or 1건 : {} ,  2건이상 : [{},{}] 처리
                if (this.S2I(ROWDATA_5.get("respCnt5").toString()) == 1) {
                    Map<String, Object> ROWDATA_5_REPEAT_tmp = new HashMap<String, Object>();
                    ROWDATA_5_REPEAT_tmp = (Map<String, Object>)body.get("ROWDATA_5_REPEAT");
                    ROWDATA_5_REPEAT.add(ROWDATA_5_REPEAT_tmp);
                } else if (this.S2I(ROWDATA_5.get("respCnt5").toString()) > 1) {
                    ROWDATA_5_REPEAT = (List<Map<String, Object>>)body.get("ROWDATA_5_REPEAT");
                }

                // 렌탈CB연체(일반)세그먼트 코드 매핑
                if (this.S2I(ROWDATA_5.get("respCnt5").toString()) > 0) {
                    Map<String, Object> params;
                    Map<String, Object> rst;
                    for (int i = 0; i < ROWDATA_5_REPEAT.size(); i++) {
                        //렌탈 상품코드(소분류)
                        params = new HashMap<String, Object>();
                        rst = new HashMap<String, Object>();
                        //params.put("LCGROP", "1000000212");
                        params.put("dangArbitCd", ROWDATA_5_REPEAT.get(i).get("rntlPrdtCdS5"));

                        rst = mapper.getTransCdMsg(params);
                        if (rst != null) {
                            ROWDATA_5_REPEAT.get(i).put("rntlPrdtNmS5", rst.get("LCTEXT"));
                        }

                        //연체구분코드
                        params = new HashMap<String, Object>();
                        rst = new HashMap<String, Object>();
                        //params.put("LCGROP", "1000000211");
                        params.put("dangArbitCd", ROWDATA_5_REPEAT.get(i).get("delyDivCd5"));

                        rst = mapper.getTransCdMsg(params);
                        if (rst != null) {
                            ROWDATA_5_REPEAT.get(i).put("delyDivNm5", rst.get("LCTEXT"));
                        }

                    }
                }

                // 일반 요약항목 세그먼트>>>  0 : {Nodata} , 1건 : {} ,  2건이상 : [{},{}] 처리  
                if (this.S2I(SUMMARYITEM_6.get("respCnt6").toString()) == 1) {
                    Map<String, Object> SUMMARYITEM_6_REPEAT_OBJ = new HashMap<String, Object>();
                    SUMMARYITEM_6_REPEAT_OBJ = (Map<String, Object>)body.get("SUMMARYITEM_6_REPEAT");
                    SUMMARYITEM_6_REPEAT.put(
                        SUMMARYITEM_6_REPEAT_OBJ.get("siCd6").toString(),
                        SUMMARYITEM_6_REPEAT_OBJ.get("siVal6").toString()
                    );
                } else if (this.S2I(SUMMARYITEM_6.get("respCnt6").toString()) > 1) {
                    List<Map<String, Object>> SUMMARYITEM_6_REPEAT_LIST = (List<Map<String, Object>>)body
                        .get("SUMMARYITEM_6_REPEAT");
                    for (int i = 0; i < SUMMARYITEM_6_REPEAT_LIST.size(); i++) {
                        SUMMARYITEM_6_REPEAT.put(
                            SUMMARYITEM_6_REPEAT_LIST.get(i).get("siCd6").toString(),
                            SUMMARYITEM_6_REPEAT_LIST.get(i).get("siVal6").toString()
                        );
                    }
                }

                //기타  요약항목 세그먼트>>> 0 : {Nodata} , 1건 : {} ,  2건이상 : [{},{}] 처리
                if (this.S2I(SUMMARYITEM_7.get("respCnt7").toString()) == 1) {
                    Map<String, Object> SUMMARYITEM_7_OBJ = new HashMap<String, Object>();
                    SUMMARYITEM_7_OBJ = (Map<String, Object>)body.get("SUMMARYITEM_7_REPEAT");
                    SUMMARYITEM_7_REPEAT
                        .put(
                            SUMMARYITEM_7_OBJ.get("etcItmCd7").toString(),
                            SUMMARYITEM_7_OBJ.get("etcItmVal7").toString()
                        );
                } else if (this.S2I(SUMMARYITEM_7.get("respCnt7").toString()) > 1) {
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
                if (SUMMARYITEM_6_REPEAT != null) {
                    //납입율 계산  >>> 약정납입액 && 실제납입액(현재 )
                    if (SUMMARYITEM_6_REPEAT.get("RT0100201") != null
                        && SUMMARYITEM_6_REPEAT.get("RT0100202") != null) {
                        try {
                            int RT0100201 = this.S2I(SUMMARYITEM_6_REPEAT.get("RT0100201").toString());
                            int RT0100202 = this.S2I(SUMMARYITEM_6_REPEAT.get("RT0100202").toString());
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
                            int RT0300203 = this.S2I(SUMMARYITEM_6_REPEAT.get("RT0300203").toString());
                            int RT0300204 = this.S2I(SUMMARYITEM_6_REPEAT.get("RT0300204").toString());

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
                            int RT0600203 = this.S2I(SUMMARYITEM_6_REPEAT.get("RT0600203").toString());
                            int RT0600204 = this.S2I(SUMMARYITEM_6_REPEAT.get("RT0600204").toString());

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
                            int RT1200203 = this.S2I(SUMMARYITEM_6_REPEAT.get("RT1200203").toString());
                            int RT1200204 = this.S2I(SUMMARYITEM_6_REPEAT.get("RT1200204").toString());

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
                            int RT2400103 = this.S2I(SUMMARYITEM_6_REPEAT.get("RT2400103").toString());
                            int RT2400104 = this.S2I(SUMMARYITEM_6_REPEAT.get("RT2400104").toString());
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
                            int RT3600103 = this.S2I(SUMMARYITEM_6_REPEAT.get("RT3600103").toString());
                            int RT3600104 = this.S2I(SUMMARYITEM_6_REPEAT.get("RT3600104").toString());
                            double CAL_PER_36 = 0;
                            if (RT3600103 > 0) {
                                CAL_PER_36 = (double)RT3600104 / RT3600103 * 100;
                            }
                            SUMMARYITEM_6_REPEAT.put("CAL_PER_36", String.format("%,.0f", CAL_PER_36));

                        } catch (Exception e) {
                            log.debug("납입율 계산 오류(36) :  " + e);
                        }

                    }
                } else {
                    log.debug("SUMMARYITEM_6_REPEAT IS NULL ");
                }

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
                for (int i = 0; i < 8; i++) {
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
                }

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
                    cbVo.setCrtlTot2(String.valueOf(cbMap.get("ocuBmcNm5")));
                    cbVo.setCrtlTot3(String.valueOf(cbMap.get("fistOcuDay5")));
                    cbVo.setCrtlTot4(String.valueOf(cbMap.get("ocuRegtDay5")));
                    cbVo.setCrtlTot5(String.valueOf(cbMap.get("delyAmt5")));
                    cbVo.setCrtlTot6(String.valueOf(cbMap.get("fistDelyAmt5")));
                    res.add(cbVo);
                }
            }
        } catch (Exception e) {
            throw new Exception("내부 서버 오류.[" + e + "]");
        } finally {

            mapper.insertCBSearchTrans(paramMap);
        }

        return res;

    }

    private JsonParser SUMMARYITEM_6_REPEAT() {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * 전문 처리 처버 URL  
     * @return
     */
    private String getSpczServerURL() {

        //String profile = kcComnService.getProfile();
        String strUrl = null;
        /*  if (StringUtil.isEquals("product", profile)) {
            // 운영 URL
            strUrl = "http://10.1.25.23:23101/";
        } else {
            //strUrl = "http://10.1.25.23:23101/";
            // 개발 URL
            strUrl = "http://10.1.25.21:23101/";
        }*/

        strUrl = "http://10.1.25.23:23101/";
        return strUrl;
    }

    public String Trans(Map<String, Object> params) throws Exception {
        String body = "";
        OutputStreamWriter wr = null;
        String spczServerURL = "";
        URL url;
        HttpURLConnection connection;
        try {
            spczServerURL = this.getSpczServerURL();
            log.debug("spczServerURL :  " + spczServerURL);
            url = new URL(spczServerURL); //CLOUD DEV
            connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);

            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept", "*/*");
            connection.setRequestProperty("X-Requested-With", "XMLHttpRequest");

            wr = new OutputStreamWriter(connection.getOutputStream(), "UTF-8");

            wr.write(this.jsonGen(params));
            wr.flush();
            connection.connect();
            InputStream is = connection.getInputStream();
            //apache util사용을 할경우 아래와 같이 출력 가능           
            body = IOUtils.toString(is);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (wr != null)
                try {
                    wr.close();
                } catch (IOException e) {}
        }

        return body;

    }

    /**
     * @param params TransSeq 기관전문 관리번호
     * @param params inqwtcnRsnCd 조회동의사유코드
     * @param params keyDiv 주민/사업자/법인/회원번호 조회키 구분
     * @param params rsdnNo 주민번호/사업자/법인번호/회원번호
     * @param params insHpNo 설치지 휴대폰번호
     * @param params insHomNo 설치지 자택 전화번호
     * @param params InsAdr 설치지 주소
     * @param params inqRsnCd 조회사유코드
     * @return 
     */

    private String jsonGen(Map<String, Object> params) {

        String data = "";
        data += "   {\"cruzjson\":{     ";
        data += "       \"header\":{    ";
        data += "            \"bkndPgmId\":\"NC02\", ";
        //data +="              \"globId\":\""+getGID("FO1", "NC02")+"\",   "; 
        data += "            \"globId\":\"" + getWellsGID("wells") + "\",    ";
        data += "            \"orgChanId\":\"ONC2\",";
        data += "            \"exsIntfId\":\"ONC2_1R000\",";
        data += "            \"resultCode\":\"0000\",";
        data += "            \"resultMsg\":\"\"";
        data += "  }";
        //대외계와 통신 기준정보 마감 입니다.

        data += " ,\"body\":{";

        //1.요청 데이터 레이아웃 공통부 
        data += "\"COMM\":{";
        data += "\"spczGropCd\"    :\"NICEIF   \"";
        data += ",\"dealKindClfyCd\"  :\"0200\"";
        data += ",\"dealDivCd\"  :\"1R000\"";
        data += ",\"snrcFlag\"   :\"B\"";
        data += ",\"trunDiv\"     :\"503\"";
        data += ",\"rplyCd\"    :\"\"";
        data += ",\"etrcInttId\"  :\"KYOWON12 \"";
        data += ",\"inttSpczAdmnNo\"     :\"" + params.get("TransSeq") + "\"";
        data += ",\"inttSpczTrnmHr\"       :\"" + getT() + "\"";
        data += ",\"niceSpczAdmnNo\"       :\"0000000000\"";
        data += ",\"niceSpczTrnmHr\"       :\"00000000000000\"";
        data += ",\"blnkC\"       :\"\"";
        data += ",\"inqwtcnRsnCd\"       :\"" + params.get("inqwtcnRsnCd") + "\""; //조회 동의사유코드

        data += " },";

        //2.요청 데이터 레이아웃 개별요청        
        data += "\"DATA\":{";
        data += "\"keyDiv\":\"" + params.get("keyDiv") + "\","; //주민/사업자/법인/회원번호 조회키 구분
        data += "\"rsdnNo\":\"" + params.get("rsdnNo") + "\","; //주민번호/사업자/법인번호/회원번호
        data += "\"insHpNo\":\"" + params.get("insHpNo") + "\","; //설치지 휴대폰번호
        data += "\"insHomNo\":\"" + params.get("insHomNo") + "\","; //설치지 자택 전화번호
        data += "\"InsAdr\":\"" + params.get("InsAdr") + "\","; //설치지 주소
        data += "\"inqRsnCd\":\"" + params.get("inqRsnCd") + "\","; //조회사유코드
        data += "\"rtryReqNum\":\"00\","; //재요청횟수
        data += "\"infoCntiYn\":\"N\","; //정보연속여부
        data += "\"repotCnfimNo\":\"0000000000000\","; //보고서 인증번호
        data += "\"rwDtaSgmtReqCnt\":\"0003\","; //raw data SEGMENT 요청건수 (반복건수)
        data += "\"shtItmSgmtReqCnt\":\"02\","; //요약항목 SEGMENT 요청건수 (반복건수)
        data += "\"blnkD\":\"\""; //공란
        data += " },";

        //3.요청 데이터 레이아웃 raw data 세그먼트       
        data += "\"ROWDATA_1\":{"; // 렌탈CB식별정보
        data += "\"rdSgmtId1\":\"CBR01\","; //
        data += "\"rdRecvCnt1\":\"0\","; //
        data += "\"rdReqCnt1\":\"999\""; //
        data += " },";
        //      data +="\"REPEAT2\":{";
        //      data += "\"sgmtId2\":\"CBR02\",";  //
        //      data += "\"rntlCbRecvCnt2\":\"0\",";  //
        //      data += "\"rntlCbReqCnt2\":\"0\"";  //
        //      data +=" },";
        //      data +="\"REPEAT3\":{";
        //      data += "\"sgmtId3\":\"CBR03\",";  //
        //      data += "\"rntlCbRecvCnt3\":\"0\",";  //
        //      data += "\"rntlCbReqCnt3\":\"0\"";  //
        //      data +=" },";
        data += "\"ROWDATA_4\":{"; //렌탈CB연체(렌터카)정보
        data += "\"rdSgmtId4\":\"CBR04\","; //
        data += "\"rdRecvCnt4\":\"0\","; //
        data += "\"rdReqCnt4\":\"0\""; //
        data += " },";
        data += "\"ROWDATA_5\":{"; //렌탈CB연체(일반)정보
        data += "\"rdSgmtId5\":\"CBR05\","; //
        data += "\"rdRecvCnt5\":\"0\","; //
        data += "\"rdReqCnt5\":\"999\""; //
        data += " },";

        //4.요청 데이터 레이아웃 요약항목 세그먼트
        //
        data += "\"SUMMARYITEM_6\":{"; //렌탈CB 일반 요약항목 반복부
        data += "\"siSgmtId6\":\"CBR06\","; //
        data += "\"siRecvCnt6\":\"0\","; //
        data += "\"siReqCnt6\":\"48\""; //
        data += " },";
        data += " \"SUMMARYITEM_6_REPAET\":[";
        data += "{\"siCd6\":\"RT0000001\"}, {\"siCd6\":\"RT0300001\"}, {\"siCd6\":\"RT0600001\"}, {\"siCd6\":\"RT1200001\"}, {\"siCd6\":\"RT2400001\"}, {\"siCd6\":\"RT3600001\"},"; //미해지 일반렌탈 총건수
        data += "{\"siCd6\":\"RT0000101\"}, {\"siCd6\":\"RT0300101\"}, {\"siCd6\":\"RT0600101\"}, {\"siCd6\":\"RT1200101\"}, {\"siCd6\":\"RT2400101\"}, {\"siCd6\":\"RT3600101\"},"; //미해지 일반렌탈 총기관수
        data += "{\"siCd6\":\"RT0000201\"}, {\"siCd6\":\"RT0300201\"}, {\"siCd6\":\"RT0600201\"}, {\"siCd6\":\"RT1200201\"}, {\"siCd6\":\"RT2400201\"}, {\"siCd6\":\"RT3600201\"},"; //미해지 일반렌탈 총계약금액

        data += "{\"siCd6\":\"RT0100201\"}, {\"siCd6\":\"RT0300203\"}, {\"siCd6\":\"RT0600203\"}, {\"siCd6\":\"RT1200203\"}, {\"siCd6\":\"RT2400103\"}, {\"siCd6\":\"RT3600103\"},"; //미해지 일반렌탈 총 약정납입액
        data += "{\"siCd6\":\"RT0100202\"}, {\"siCd6\":\"RT0300204\"}, {\"siCd6\":\"RT0600204\"}, {\"siCd6\":\"RT1200204\"}, {\"siCd6\":\"RT2400104\"}, {\"siCd6\":\"RT3600104\"},"; //미해지 일반렌탈 총 실제납입액

        data += "{\"siCd6\":\"RT8000001\"}, {\"siCd6\":\"RT0100001\"}, {\"siCd6\":\"RT0200001\"}, {\"siCd6\":\"RT0300003\"}, {\"siCd6\":\"RT0600003\"}, {\"siCd6\":\"RT1200003\"},"; //(최근)미해지 일반렌탈 총건수
        data += "{\"siCd6\":\"RT8000201\"}, {\"siCd6\":\"RT0100205\"}, {\"siCd6\":\"RT0200201\"}, {\"siCd6\":\"RT0300207\"}, {\"siCd6\":\"RT0600207\"}, {\"siCd6\":\"RT1200207\"},"; //(최근)미해지 일반렌탈 총계약금액
        data += "{\"siCd6\":\"RT8000101\"}, {\"siCd6\":\"RT0100101\"}, {\"siCd6\":\"RT0200101\"}, {\"siCd6\":\"RT0300103\"}, {\"siCd6\":\"RT0600103\"}, {\"siCd6\":\"RT1200103\"}"; //(최근)미해지 일반렌탈 총기관수

        data += " ],";

        data += "\"SUMMARYITEM_7\":{"; //렌탈CB 기타 요약항목
        data += "\"siSgmtId7\":\"CBR07\","; //
        data += "\"siRecvCnt7\":\"0\","; //
        data += "\"siReqCnt7\":\"42\""; //
        data += " },";
        data += " \"SUMMARYITEM_7_REPAET\":[";
        data += "{\"siCd7\":\"RENTB0001\"}, {\"siCd7\":\"RENTB0008\"}, {\"siCd7\":\"RENTB0015\"}, {\"siCd7\":\"RENTB0022\"}, {\"siCd7\":\"RENTB0029\"}, {\"siCd7\":\"RENTB0036\"},"; //휴대폰번호 기준 렌탈(일반) 계약 건수
        data += "{\"siCd7\":\"RENTB0002\"}, {\"siCd7\":\"RENTB0009\"}, {\"siCd7\":\"RENTB0016\"}, {\"siCd7\":\"RENTB0023\"}, {\"siCd7\":\"RENTB0030\"}, {\"siCd7\":\"RENTB0037\"},"; //휴대폰번호 기준 렌탈(일반) 약정 납입액
        data += "{\"siCd7\":\"RENTB0003\"}, {\"siCd7\":\"RENTB0010\"}, {\"siCd7\":\"RENTB0017\"}, {\"siCd7\":\"RENTB0024\"}, {\"siCd7\":\"RENTB0031\"}, {\"siCd7\":\"RENTB0038\"},"; //휴대폰번호 기준 렌탈(일반) 실제 납입액

        /*data += "{\"siCd7\":\"RENTA0001\"}, {\"siCd7\":\"RENTA0008\"}, {\"siCd7\":\"RENTA0015\"}, {\"siCd7\":\"RENTA0022\"}, {\"siCd7\":\"RENTA0029\"}, {\"siCd7\":\"RENTA0036\"},"; //휴대폰번호 기준 렌탈(렌터카) 계약 건수
        data += "{\"siCd7\":\"RENTA0002\"}, {\"siCd7\":\"RENTA0009\"}, {\"siCd7\":\"RENTA0016\"}, {\"siCd7\":\"RENTA0023\"}, {\"siCd7\":\"RENTA0030\"}, {\"siCd7\":\"RENTA0037\"},"; //휴대폰번호 기준 렌탈(렌터카) 약정 납입액
        data += "{\"siCd7\":\"RENTA0003\"}, {\"siCd7\":\"RENTA0010\"}, {\"siCd7\":\"RENTA0017\"}, {\"siCd7\":\"RENTA0024\"}, {\"siCd7\":\"RENTA0031\"}, {\"siCd7\":\"RENTA0038\"},"; //휴대폰번호 기준 렌탈(렌터카) 실제 납입액
        */
        data += "{\"siCd7\":\"RENTB0004\"}, {\"siCd7\":\"RENTB0011\"}, {\"siCd7\":\"RENTB0018\"}, {\"siCd7\":\"RENTB0025\"}, {\"siCd7\":\"RENTB0032\"}, {\"siCd7\":\"RENTB0039\"},"; //설치지주소 기준 렌탈(일반) 계약 건수
        data += "{\"siCd7\":\"RENTB0005\"}, {\"siCd7\":\"RENTB0012\"}, {\"siCd7\":\"RENTB0019\"}, {\"siCd7\":\"RENTB0026\"}, {\"siCd7\":\"RENTB0033\"}, {\"siCd7\":\"RENTB0040\"},"; //설치지주소 기준 렌탈(일반) 약정 납입액
        data += "{\"siCd7\":\"RENTB0006\"}, {\"siCd7\":\"RENTB0013\"}, {\"siCd7\":\"RENTB0020\"}, {\"siCd7\":\"RENTB0027\"}, {\"siCd7\":\"RENTB0034\"}, {\"siCd7\":\"RENTB0041\"},"; //설치지주소 기준 렌탈(일반) 실제 납입액

        data += "{\"siCd7\":\"RENTB0007\"}, {\"siCd7\":\"RENTB0014\"}, {\"siCd7\":\"RENTB0021\"}, {\"siCd7\":\"RENTB0028\"}, {\"siCd7\":\"RENTB0035\"}, {\"siCd7\":\"RENTB0042\"}"; //자택번호 기준 렌탈(일반) 계약 건수
        //data += "{\"siCd7\":\"RENTA0007\"}, {\"siCd7\":\"RENTA0014\"}, {\"siCd7\":\"RENTA0021\"}, {\"siCd7\":\"RENTA0028\"}, {\"siCd7\":\"RENTA0035\"}, {\"siCd7\":\"RENTA0042\"}"; //자택번호 기준 렌탈(렌터카) 계약 건수

        data += " ]";

        /*data +="\"REPEAT6\":{";
        data += "\"sgmtId6\":\"CBR06\",";  //
        data += "\"rntlCbRecvCnt6\":\"0\",";  //
        data += "\"rntlCbReqCnt6\":\"2\"";  //
        data +=" },";
        data +=" \"REPEAT6_D\":[";
        data += "   {\"shtItmCd6\":\"RT0300001\"}, {\"shtItmCd6\":\"RT0000201\"}"; //{"members":["a","b"]}  
        data +=" ],";
        
        data +="\"REPEAT7\":{";
        data += "\"sgmtId7\":\"CBR07\",";  //
        data += "\"rntlCbRecvCnt7\":\"0\",";  //
        data += "\"rntlCbReqCnt7\":\"2\"";  //
        data +=" },";
        data +=" \"REPEAT7_D\":[";
        data += "   {\"shtItmCd7\":\"RENTA0029\"}, {\"shtItmCd7\":\"RENTA0038\"}"; //{"members":["a","b"]}  
        data +=" ]";
        */
        //요청 데이터 레이아웃  종료
        data += "}}}";

        log.debug("data :  " + data);
        return data;
    }

    private String getWellsGID(String prgramNumber) {
        String GID = "";
        Calendar calendar = Calendar.getInstance();
        String date = calendar.get(Calendar.YEAR) + ""
            + get2Ciper(calendar.get(Calendar.MONTH) + 1) + ""
            + get2Ciper(calendar.get(Calendar.DAY_OF_MONTH));
        ;
        String time = get2Ciper(calendar.get(Calendar.HOUR_OF_DAY)) + ""
            + get2Ciper(calendar.get(Calendar.MINUTE)) + ""
            + get2Ciper(calendar.get(Calendar.SECOND)) + ""
            + get3Ciper(calendar.get(Calendar.MILLISECOND));
        long nanoTime = System.nanoTime();
        GID = prgramNumber + "_" + date + time + "_" + nanoTime;
        return GID;
    }

    private String getGID(String SystemCode, String prgramNumber) {
        String GID = "";
        Calendar calendar = Calendar.getInstance();
        String date = calendar.get(Calendar.YEAR) + ""
            + get2Ciper(calendar.get(Calendar.MONTH) + 1) + ""
            + get2Ciper(calendar.get(Calendar.DAY_OF_MONTH));
        ;
        String time = get2Ciper(calendar.get(Calendar.HOUR_OF_DAY)) + ""
            + get2Ciper(calendar.get(Calendar.MINUTE)) + ""
            + get2Ciper(calendar.get(Calendar.SECOND)) + ""
            + get3Ciper(calendar.get(Calendar.MILLISECOND));
        long nanoTime = System.nanoTime();
        GID = date + time + "_" + nanoTime + "_" + SystemCode + "_" + prgramNumber;
        return GID;
    }

    private String getT() {
        Calendar calendar = Calendar.getInstance();
        String date = calendar.get(Calendar.YEAR) + ""
            + get2Ciper(calendar.get(Calendar.MONTH) + 1) + ""
            + get2Ciper(calendar.get(Calendar.DAY_OF_MONTH));
        ;
        String time = get2Ciper(calendar.get(Calendar.HOUR_OF_DAY)) + ""
            + get2Ciper(calendar.get(Calendar.MINUTE)) + ""
            + get2Ciper(calendar.get(Calendar.SECOND));
        return date + time;
    }

    private String get2Ciper(int i) {
        if (i < 10) {
            return String.valueOf("0" + i);
        } else {
            return String.valueOf(i);
        }
    }

    /**
     * @param i
     * @return
     */
    private String get3Ciper(int i) {
        int length = String.valueOf(i).length();
        switch (length) {
            case 1:
                return String.valueOf("00" + i);
            case 2:
                return String.valueOf("0" + i);
            case 3:
                return String.valueOf("" + i);
        }
        if (length > 3) {
            return String.valueOf(i).substring(0, 3);
        }
        return String.valueOf(i);
    }

    /**
     * Convert a String to an int
     * 
     * @param data the thing to convert
     * @return the converted data
     */
    public static int S2I(String data) {
        try {
            return Integer.parseInt(data);
        } catch (NumberFormatException ex) {
            return 0;
        }
    }

    /**
     * str 가 null or empty 이면 true 리턴
     * @param str
     * @return
     */
    public static boolean isEmpty(String str) {
        if (str == null || str.trim().length() == 0) {
            return true;
        }
        return false;
    }

}
