package com.kyowon.sms.wells.web.service.interfaces.ivo.EAI_OCJM1_WSVO1002.request;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RegistrationRequestData {

    @JsonProperty("PRT_ST")
    private String pRTST;

    @JsonProperty("CUST_ID")
    private String cUSTID;

    @JsonProperty("SENDR_DETAIL_ADDR")
    private String sENDRDETAILADDR;

    @JsonProperty("DLV_DV")
    private String dLVDV;

    @JsonProperty("ARRAY")
    private List<RegistrationRequestArrayProductData> aRRAY;

    @JsonProperty("SENDR_CELL_NO1")
    private String sENDRCELLNO1;

    @JsonProperty("SENDR_CELL_NO2")
    private String sENDRCELLNO2;

    @JsonProperty("MPCK_KEY")
    private String mPCKKEY;

    @JsonProperty("SENDR_CELL_NO3")
    private String sENDRCELLNO3;

    @JsonProperty("SENDR_ZIP_NO")
    private String sENDRZIPNO;

    @JsonProperty("SENDR_ADDR")
    private String sENDRADDR;

    @JsonProperty("FRT_DV_CD")
    private String fRTDVCD;

    @JsonProperty("COD_YN")
    private String cODYN;

    @JsonProperty("INVC_NO")
    private String iNVCNO;

    @JsonProperty("REQ_DV_CD")
    private String rEQDVCD;

    @JsonProperty("RCPT_YMD")
    private String rCPTYMD;

    @JsonProperty("RCVR_ZIP_NO")
    private String rCVRZIPNO;

    @JsonProperty("CNTR_ITEM_CD")
    private String cNTRITEMCD;

    @JsonProperty("SENDR_NM")
    private Object sENDRNM;

    @JsonProperty("BOX_TYPE_CD")
    private String bOXTYPECD;

    @JsonProperty("TOKEN_NUM")
    private String tOKENNUM;

    @JsonProperty("ORI_INVC_NO")
    private String oRIINVCNO;

    @JsonProperty("RCVR_DETAIL_ADDR")
    private String rCVRDETAILADDR;

    @JsonProperty("RCVR_NM")
    private String rCVRNM;

    @JsonProperty("WORK_DV_CD")
    private String wORKDVCD;

    @JsonProperty("CUST_MGMT_DLCM_CD")
    private String cUSTMGMTDLCMCD;

    @JsonProperty("BOX_QTY")
    private String bOXQTY;

    @JsonProperty("CAL_DV_CD")
    private String cALDVCD;

    @JsonProperty("RCVR_TEL_NO3")
    private String rCVRTELNO3;

    @JsonProperty("SENDR_TEL_NO3")
    private String sENDRTELNO3;

    @JsonProperty("RCVR_TEL_NO1")
    private String rCVRTELNO1;

    @JsonProperty("ORI_ORD_NO")
    private String oRIORDNO;

    @JsonProperty("SENDR_TEL_NO2")
    private String sENDRTELNO2;

    @JsonProperty("RCVR_TEL_NO2")
    private String rCVRTELNO2;

    @JsonProperty("RCVR_CELL_NO2")
    private String rCVRCELLNO2;

    @JsonProperty("RCVR_CELL_NO3")
    private String rCVRCELLNO3;

    @JsonProperty("SENDR_TEL_NO1")
    private String sENDRTELNO1;

    @JsonProperty("RCVR_CELL_NO1")
    private String rCVRCELLNO1;

    @JsonProperty("CUST_USE_NO")
    private String cUSTUSENO;

    @JsonProperty("RCVR_ADDR")
    private String rCVRADDR;

    @JsonProperty("RCPT_DV")
    private String rCPTDV;

    @JsonProperty("REMARK_1")
    private String rEMARK1;
}
