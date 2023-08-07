package com.kyowon.sms.wells.web.service.visit.dvo;

import com.sds.sflex.system.config.annotation.DBDecField;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class WsnbIndividualServicePsDvo {

    /**/
    String cntrNoDtl;
    String cstNm;
    String cstGdNm;
    String locaraTno;
    @DBDecField
    String exnoEncr;
    String idvTno;
    String cralLocaraTno;
    @DBDecField
    String mexnoEncr;
    String cralIdvTno;
    String adrDtl;
    String basePdNm;
    String pdNm;
    String frisuAsPtrmN;
    String frisuBfsvcPtrmN;
    String pdUswy;
    String sellTp;
    String mngtTp;
    String cntrCnfmDtm;
    String ogCd;
    String brmgrPrtnrNm;
    String brmgrPrtnrNo;
    String chnDt;
    String asnDt;
    String sellChnl;
    String prtnrNo;
    String prtnrKnm;
    String bfCntrNo;
    String istDt;
    String chngDt;
    String canDt;
    String svStpDt;
    String asExprDt;
    String bsExprDt;
    String cpsDt;
    String mshJDt;
    String mshWdwalDt;
    String bcNo;
    String pblBcNo;
    String qrsRdmNo;
    String ssPdctBcNo;
    String sppIvcNo;

    /*가구화정보 조회*/
    String svHshdNo;
    String cntrDtl;
    String cstKnm;
    String adrZip;

    /*특이사항 저장*/
    String cntrSn;
    String ogTpCd;
    String wkPrtnrNo;
    String cstUnuitmCn;

    /*처리내역조회*/
    String svTp;
    String rcpDt;
    String svBizDclsf;
    String reqDt;
    String vstFshDt;
    String wkPrgsStat;
    String asCaus;
    String rtngdProcsTp;
    String fstVstFshDt;
    String zipNo;
    String ogTp;
    String ogNm;
    String prtnrNm;
    String bldNm;
    String imgYn;
    String cstSvAsnNo;
    String procStus;
}
