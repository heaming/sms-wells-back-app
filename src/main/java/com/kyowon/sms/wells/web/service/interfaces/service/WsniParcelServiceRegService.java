package com.kyowon.sms.wells.web.service.interfaces.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.service.interfaces.dto.WsniParcelServiceRegDto;
import com.kyowon.sms.wells.web.service.interfaces.dvo.WsniParcelServiceRegDvo;
import com.kyowon.sms.wells.web.service.interfaces.ivo.EAI_OCJM1_WSVO1001.request.CertKeyCreateRequestData;
import com.kyowon.sms.wells.web.service.interfaces.ivo.EAI_OCJM1_WSVO1001.request.PcsvCertKeyCreateReqIvo;
import com.kyowon.sms.wells.web.service.interfaces.ivo.EAI_OCJM1_WSVO1001.response.PcsvCertKeyCreateResIvo;
import com.kyowon.sms.wells.web.service.interfaces.ivo.EAI_OCJM1_WSVO1002.request.PcsvRegistrationReqIvo;
import com.kyowon.sms.wells.web.service.interfaces.ivo.EAI_OCJM1_WSVO1002.request.RegistrationRequestArrayProductData;
import com.kyowon.sms.wells.web.service.interfaces.ivo.EAI_OCJM1_WSVO1002.request.RegistrationRequestData;
import com.kyowon.sms.wells.web.service.interfaces.ivo.EAI_OCJM1_WSVO1003.request.AddressCleansingRequestData;
import com.kyowon.sms.wells.web.service.interfaces.ivo.EAI_OCJM1_WSVO1003.request.PcsvAddressCleansingReqIvo;
import com.kyowon.sms.wells.web.service.interfaces.ivo.EAI_OCJM1_WSVO1003.response.AddressCleansingResultData;
import com.kyowon.sms.wells.web.service.interfaces.ivo.EAI_OCJM1_WSVO1003.response.PcsvAddressCleansingResIvo;
import com.kyowon.sms.wells.web.service.interfaces.mapper.WsniParcelServiceMapper;
import com.sds.sflex.common.common.service.CruzLinkInterfaceService;
import com.sds.sflex.system.config.exception.BizException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * <pre>
 * CJ택배 집하지시 Service
 * </pre>
 *
 * @author 홍세기
 * @since 2023-06-16
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class WsniParcelServiceRegService {

    private final WsniParcelServiceMapper parcelServiceMapper;

    public static final String TMP_PRC_SRV_REG_IFID_CERT_KEY = "OCJM1_CSVO1001";

    public static final String TMP_PRC_SRV_REG_IFID_RGST_PRCL = "OCJM1_CSVO1002";
    public static final String TMP_PRC_SRV_REG_IFID_ADDR_CLNS = "OCJM1_CSVO1003";

    public static final String TMP_PRC_SRV_REG_CUST_ID = "30473216";
    public static final String TMP_PRC_SRV_REG_BIZ_REG_NUM = "2148809030";

    // Auto Wired
    private final CruzLinkInterfaceService interfaceService;

    public List<WsniParcelServiceRegDto.SearchOriginInvoiceRes> getOriginInvoiceNum(
        WsniParcelServiceRegDto.RegistParcelServiceReq req
    ) {

        return parcelServiceMapper.selectOriginInvoiceNum(req);
    }

    public String getParcelServiceCertKey() {
        String CertKey = "";

        CertKey = parcelServiceMapper.selectParcelServiceCertKey();

        if (CertKey == null || CertKey.isEmpty()) {
            PcsvCertKeyCreateReqIvo req = new PcsvCertKeyCreateReqIvo();
            CertKeyCreateRequestData data1 = new CertKeyCreateRequestData();
            data1.setCUST_ID(TMP_PRC_SRV_REG_CUST_ID);
            data1.setBIZ_REG_NUM(TMP_PRC_SRV_REG_BIZ_REG_NUM);
            req.setDATA(data1);

            PcsvCertKeyCreateResIvo res = interfaceService
                .post(TMP_PRC_SRV_REG_IFID_CERT_KEY, req, PcsvCertKeyCreateResIvo.class);

            if (res.getRESULTCD().equals("S")) {
                CertKey = res.getDATA().getTOKENNUM();
            } else {
                throw new BizException("MSG_ALT_NO_INFO_SRCH");
            }

            parcelServiceMapper.insertParcelServiceCertKey(CertKey);
        }

        return CertKey;
    }

    public AddressCleansingResultData selectAddressCleansing(
        WsniParcelServiceRegDto.SearchAddressCleansingReq dto
    ) {

        PcsvAddressCleansingReqIvo req = new PcsvAddressCleansingReqIvo();
        AddressCleansingRequestData data2 = new AddressCleansingRequestData();

        data2.setTOKENNUM(dto.tokenNum());
        data2.setCLNTNUM(TMP_PRC_SRV_REG_CUST_ID);
        data2.setADDRESS(dto.address());
        req.setDATA(data2);

        PcsvAddressCleansingResIvo res = interfaceService
            .post(TMP_PRC_SRV_REG_IFID_ADDR_CLNS, req, PcsvAddressCleansingResIvo.class);

        if (res.getRESULTCD().equals("S")) {
            return res.getDATA();
        } else {
            return null;
        }
    }

    /** CJ택배 집하지시 API 저장
     *
     * @author 홍세기
     * @since 2023-06-16
     */
    @Transactional
    public int saveParcelServiceData(WsniParcelServiceRegDvo dvo)
        throws Exception {
        return parcelServiceMapper.insertParcelServiceReg(dvo);
    }

    public PcsvAddressCleansingResIvo saveParcelServiceInterface(
        WsniParcelServiceRegDvo dvo
    ) {

        PcsvRegistrationReqIvo req = new PcsvRegistrationReqIvo();
        RegistrationRequestData data3 = new RegistrationRequestData();

        data3.setTOKENNUM(dvo.getTokkenNum());
        data3.setCUSTID(TMP_PRC_SRV_REG_CUST_ID);
        data3.setRCPTYMD(dvo.getRcptYmd());
        data3.setCUSTUSENO(dvo.getCustNo());
        data3.setRCPTDV(dvo.getRcptDv()); // 접수구분 -> 01: 일반, 2: 반품, ★중요★ 반품의 경우에는 02로 넣어야 함.
        data3.setWORKDVCD("01"); // 작업구분코드(01: 일반, 02: 교환, 03: A/S)
        data3.setREQDVCD(dvo.getReqdvCd());
        data3.setMPCKKEY(dvo.getMpckKey()); // 합포장 키(YYYYMMDD_신용번호_회수요청번호), ★중요★ 반품의 경우에는 기존송장번호로 무결성이 보장되지 않으므로 회수요청번호를 조합해서 사용한다.
        data3.setCALDVCD("01"); // 정산구분코드(01: 계약 운임)
        data3.setFRTDVCD("03"); // FRT_DV_CD 운임구분코드 01: 선불, 02: 착불, 03: 신용
        data3.setCNTRITEMCD("01"); // 계약품목코드(01: 일반 품목)

        data3.setBOXTYPECD("03"); // 극소: 01, 소: 02, 중: 03, 대1: 04, 대2: 07, 이형: 05, 취급제한: 06
        data3.setBOXQTY("1"); // 박스 수량
        data3.setCUSTMGMTDLCMCD("30480322"); // 고객관리거래처코드(KFS신용코드 - 발송고객코드)

        data3.setSENDRNM(dvo.getSendrNm());
        data3.setSENDRTELNO1(dvo.getSendrtelNo1());
        data3.setSENDRTELNO2(dvo.getSendrtelNo2());
        data3.setSENDRTELNO3(dvo.getSendrtelNo3());
        data3.setSENDRCELLNO1(dvo.getSendrcellNo1());
        data3.setSENDRCELLNO2(dvo.getSendrcellNo2());
        data3.setSENDRCELLNO3(dvo.getSendrcellNo3());
        data3.setSENDRZIPNO(dvo.getSendrZipNo());
        data3.setSENDRADDR(dvo.getSendrAddr());
        data3.setSENDRDETAILADDR(dvo.getSendrAddrDtl());

        data3.setRCVRNM("교원파주물류센터"); // 받는분 명
        data3.setRCVRTELNO1("031"); // 받는분 전화번호1
        data3.setRCVRTELNO2("947"); // 받는분 전화번호2
        data3.setRCVRTELNO3("2480"); // 받는분 전화번호3
        data3.setRCVRCELLNO1("010"); // 받는분 휴대폰번호1
        data3.setRCVRCELLNO2("5416"); // 받는분 휴대폰번호2
        data3.setRCVRCELLNO3("0766"); // 받는분 휴대폰번호3
        data3.setRCVRZIPNO("413724"); // 받는분 우편번호
        data3.setRCVRADDR("경기 파주시 검산로"); // 받는분 주소
        data3.setRCVRDETAILADDR(" 301-22 교원 파주물류센터"); // 받는분 상세주소, NOT NULL 항목이므로 비어있을 경우 임의문자 1Byte 넣도록 한다.

        data3.setINVCNO(""); // 운송장 번호, ★중요★ RCPT_DV(접수구분) = '02' (반품, 회수) 반품의 경우에는 사용 안함(빈칸으로 전송)
        data3.setORIINVCNO(dvo.getOriinvcNo()); // 원운송장번호, ★중요★ RCPT_DV(접수구분) = '02' (반품, 회수)시 대상이 되는 원 출고 운송장번호
        data3.setORIORDNO(""); // 원주문 번호
        data3.setPRTST("01"); // ★중요★ 운송장 출력상태(01: 미출력, 02: 선출력, 03: 선발번(반품은 선발번이 없음) 반품 (RCPT_DV = '02') 진행 시, PRT_ST = '01' 기재
        data3.setREMARK1(""); // 비고1
        data3.setCODYN("N"); // COD여부
        data3.setDLVDV("01"); // 택배구분(01: 택배)

        RegistrationRequestArrayProductData arrayData = new RegistrationRequestArrayProductData();
        List<RegistrationRequestArrayProductData> productArrlist = new ArrayList<RegistrationRequestArrayProductData>();

        arrayData.setMPCKSEQ("1");
        arrayData.setGDSCD(dvo.getGdsCd());
        arrayData.setGDSNM(dvo.getGdsNm());
        arrayData.setGDSQTY(dvo.getGdsQty());
        arrayData.setUNITCD("");
        arrayData.setUNITNM("");
        arrayData.setGDSAMT("");

        productArrlist.add(arrayData);
        data3.setARRAY(productArrlist);
        req.setDATA(data3);

        PcsvAddressCleansingResIvo res = interfaceService
            .post(TMP_PRC_SRV_REG_IFID_RGST_PRCL, req, PcsvAddressCleansingResIvo.class);

        return res;
    }

}
