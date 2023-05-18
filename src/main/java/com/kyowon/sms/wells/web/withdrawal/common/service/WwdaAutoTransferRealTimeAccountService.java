package com.kyowon.sms.wells.web.withdrawal.common.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.jetty.util.StringUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.withdrawal.common.dvo.WwdaAccountOwnerRnmConfDataDvo;
import com.kyowon.sms.wells.web.withdrawal.common.dvo.WwdaAccountOwnerRnmConfHeaderDvo;
import com.kyowon.sms.wells.web.withdrawal.common.dvo.WwdaAnAccountEffectivenessResDvo;
import com.kyowon.sms.wells.web.withdrawal.common.dvo.WwdaAutoTransferRealTimeAccountCheckDvo;
import com.kyowon.sms.wells.web.withdrawal.common.dvo.WwdaRealTimeAnAccountChangeResultDvo;
import com.kyowon.sms.wells.web.withdrawal.common.mapper.WwdaAutoTransferRealTimeAccountMapper;

import lombok.RequiredArgsConstructor;

/**
 * <pre>
 * 자동이체 실시간 계좌 등록 서비스
 * </pre>
 *
 * @author donghyun.yoo
 * @since 2023-05-18
 */
@Service
@RequiredArgsConstructor
public class WwdaAutoTransferRealTimeAccountService {

    private final WwdaAutoTransferRealTimeAccountMapper mapper;

    // Z-WD-S-0027(은행계좌유효성체크_SB)
    // 입력값/파라미터
    // 고객번호 - cstNo
    // 은행코드 - bnkCd
    // 계좌번호 - acNo
    // 법인격구분코드 - copnDvCd
    // 법인격구분식별값 - copnDvDrmVal
    // 예금주명 -  achldrNm 
    // 시스템구분코드 - sysDvCd 1 : EDU, 2: WELLS
    // 담장자ID - picId
    // 부서ID - deptId 

    // 출력값/처리결과
    // 예금주명 - achldrNm
    // 계좌이체불능코드 - acFntImpsCd
    // 계좌이체불능코드명 - acFntImpsCdNm
    // 오류내용 - errCn
    // 청구생성상태코드 - bilCrtStatCd (1 : 정상처리, 2 : 오류, 3 : 자료없음)
    @Transactional
    public WwdaAutoTransferRealTimeAccountCheckDvo saveAftnAcEftnChecks(Map<String, Object> jobParam) {
        WwdaAutoTransferRealTimeAccountCheckDvo returnDvo = new WwdaAutoTransferRealTimeAccountCheckDvo();

        String acNo = ((String)jobParam.get("acNo")).trim();
        String achldrNm = ((String)jobParam.get("achldrNm")).trim();
        String copnDvDrmVal = ((String)jobParam.get("copnDvDrmVal")).trim();
        String copnDvCd = ((String)jobParam.get("copnDvCd"));

        // 1.1 고객번호 체크
        if (StringUtil.isEmpty((String)jobParam.get("cstNo"))) {
            returnDvo.setErrCn("고객／판매자코드　오류！");
            returnDvo.setBilCrtStatCd("2");
            return returnDvo;

            // 1.2 담장자ID 체크
        } else if (StringUtil.isEmpty((String)jobParam.get("picId"))) {
            returnDvo.setErrCn("담당자코드　오류！");
            returnDvo.setBilCrtStatCd("2");
            return returnDvo;
        }

        // 1.3 은행코드 확인
        // 1.3.1 체크대상테이블 : TB_RVDW_FNIT_CD(금융기관코드)
        String checkBnkCd = mapper.selectBankCode((String)jobParam.get("bnkCd"));
        if (StringUtil.isEmpty(checkBnkCd)) {
            returnDvo.setErrCn("은행코드를　확인하십시오！");
            returnDvo.setBilCrtStatCd("2");
            return returnDvo;
        }

        if (StringUtil.isEmpty(acNo)) {
            returnDvo.setErrCn("계좌번호를　입력하십시오！");
            returnDvo.setBilCrtStatCd("2");
            return returnDvo;

            // 1.4.3 계좌번호.length > 16 OR ""
        } else if (acNo.length() > 16) {
            returnDvo.setErrCn("계좌번호를　확인하십시오！");
            returnDvo.setBilCrtStatCd("2");
            return returnDvo;
            // 1.5.1 예금주명 = NULL OR 공백제거후 = ""
        } else if (StringUtil.isEmpty(achldrNm)) {
            returnDvo.setErrCn("예금주명을　확인하십시오！");
            returnDvo.setBilCrtStatCd("2");
            return returnDvo;
            // 1.6.1 법인격구분식별값 = NULL OR ""(공백제거)
        } else if (StringUtil.isEmpty(copnDvDrmVal)) {
            returnDvo.setErrCn("생년월일／사업자번호를　입력하십시오！");
            returnDvo.setBilCrtStatCd("2");
            return returnDvo;
            // 1.6.2 법인격구분코드 != "1", "2"
        } else if (!List.of("1", "2").contains(copnDvCd)) {
            returnDvo.setErrCn("개인/법인 구분값을　입력하십시오！");
            returnDvo.setBilCrtStatCd("2");
            return returnDvo;
        }

        // 1.7 사업자번호 확인
        String bzrno = mapper.selectEntrepreneurNo((String)jobParam.get("copnDvDrmVal"));
        if (StringUtil.isEmpty(bzrno)) {
            returnDvo.setErrCn("사업자번호를　수정하십시오！");
            returnDvo.setBilCrtStatCd("2");
            return returnDvo;
        }

        // 임시 
        // 추후 insert값 확인필요
        // 2. 전문자료를 저장한다.
        // 2.1 저장대상 테이블 : TB_RVCL_AC_FNT_RTM_AK_APR_IZ(계좌이체실시간요청승인내역), TB_RVCL_AC_FNT_RTM_AK_APR_HIST(계좌이체실시간요청승인이력)
        WwdaAutoTransferRealTimeAccountCheckDvo saveDvo = new WwdaAutoTransferRealTimeAccountCheckDvo();

        String akSn = mapper.selectAcFntRtmAkAprAskSerialMax();
        saveDvo.setAkSn(Integer.parseInt(akSn));
        saveDvo.setCopnDvCd(copnDvCd);
        saveDvo.setCopnDvDrmVal(copnDvDrmVal);
        saveDvo.setBnkCd((String)jobParam.get("bnkCd"));

        saveDvo.setAcnoEncr(acNo);

        // 2.3.1 저장 에러 발생시 Exception 처리
        // 상태코드 = "2"
        try {
            mapper.insertExtxMaterialIz(saveDvo);
            mapper.insertExtxMaterialHis(saveDvo);
        } catch (Exception e) {
            returnDvo.setErrCn("Insert Error!" + e);
            returnDvo.setBilCrtStatCd("2");
        }

        Date nowDate = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMDD");
        String rqdt = simpleDateFormat.format(nowDate);

        Map<String, Object> parameterValues = new HashMap<>();

        parameterValues.put("rqdt", rqdt);
        parameterValues.put("akSn", akSn);
        parameterValues.put("sysDvCd", (String)jobParam.get("sysDvCd"));

        //3.1 전문 송신용 서비스 호출
        //3.1.1 호출 프로그램 ID : Z-WD-S-0059
        String result = saveAccountOwnerRnmConf(parameterValues);

        if ("1".equals(result)) {

            //4. 수신 결과 확인
            //4.1 결과 확인 테이블 : TB_RVCL_AC_FNT_RTM_AK_APR_IZ(계좌이체실시간요청승인내역)
            WwdaAutoTransferRealTimeAccountCheckDvo checkDvo = new WwdaAutoTransferRealTimeAccountCheckDvo();

            checkDvo = mapper.selectReceiveResultCheck(rqdt, akSn);

            //4.2 결과 확인 처리
            //4.2.1 AC_FNT_RS_CD(계좌이체결과코드) = NULL OR "" 
            if (StringUtil.isEmpty(checkDvo.getAcFntRsCd())) {
                try {
                    mapper.insertResultConfirmationProcessingHis(rqdt, akSn);
                    mapper.updateResultConfirmationProcessingIz(rqdt, akSn);
                } catch (Exception e) {
                    returnDvo.setErrCn("Insert Error!" + e);
                    returnDvo.setBilCrtStatCd("2");
                }

                returnDvo.setAcFntImpsCd("Z902");
            } else {
                returnDvo.setAcFntImpsCd(checkDvo.getAcFntRsCd());
            }
            returnDvo.setAchldrNm(checkDvo.getAchldrNm());
            returnDvo.setBilCrtStatCd("1");

            // 4.2.3 AC_FNT_IMPS_CD_NM(계좌이체불능코드명)
            returnDvo.setAcFntImpsCdNm(mapper.selectAcFntImpsCdNmCheck(checkDvo.getAcFntImpsCd()));
        }
        return returnDvo;
    }

    // SB32 교원 계좌주 실명 조회
    // AS-IS 참조 프로그램 : DQMNG : SB32StreamBuilder
    // 입력값/파라미터 : 요청일자, 요청일련번호, SYS_DV_CD(시스템구분코드)
    // 출력값/처리결과 : 상태코드
    // Z-WD-S-0059 계좌주 실명확인 전문 송수신
    @Transactional
    public String saveAccountOwnerRnmConf(Map<String, Object> reqParam) {
        // 1. 실시간 계좌변경 송신전문을 조회한다.
        WwdaRealTimeAnAccountChangeResultDvo resultDvo = mapper.selectRealTimeAnAccountChange(reqParam);

        Date nowDate = new Date();

        String sysDvCd = (String)reqParam.get("sysDvCd");
        // 전문생성
        // 전문 ID : OSBN1_CDEO1001 (계좌유효성검사)
        // 헤더부 전문 DTO 생성
        WwdaAccountOwnerRnmConfHeaderDvo headerDvo = new WwdaAccountOwnerRnmConfHeaderDvo();
        headerDvo.setWhlLngt("0200"); // (LENGTH) = "0200"
        headerDvo.setInttCd("W".equals(sysDvCd) ? "41000065" : "41000365"); // (기관코드) = 입력 시스템구분코드 = 'W' ? "41000065" : "41000365"
        headerDvo.setDealKindCd("2001"); // (거래종류코드) = "2001"
        headerDvo.setSnrcCd("3"); // (송수신코드) = "3"
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        headerDvo.setDealDt(sdf.format(nowDate)); // (거래일자) = 현재일자(YYYYMMDD)
        sdf = new SimpleDateFormat("HHmmss");
        headerDvo.setDealHr(sdf.format(nowDate)); // (거래시간) = 현재시간(HH24MISS)
        headerDvo.setOpenBankCd(resultDvo.getFnitCd().substring(1)); // (개설은행코드)  = substring(TB_RVDW_FNIT_CD.금융기관대표코드, 2, 2) // 뒤 2자리
        headerDvo.setDealNo(""); // (거래번호) = ""
        headerDvo.setRplyCd(""); // (응답코드) = ""
        headerDvo.setNwOpenBankCd(""); // (신개설은행코드) = ""
        headerDvo.setResrScp1(""); // (예약 영역) = ""
        headerDvo.setResrScp2(""); // (예약 영역) = ""
        headerDvo.setUserDealNo(resultDvo.getAkSn()); // (사용자거래번호) = TB_RVCL_AC_FNT_RTM_AK_APR_IZ.AK_SN(요청일련번호)
        headerDvo.setOccrdiv("O"); // (발생구분) = "O"
        headerDvo.setSpczTrnmWayDiv("Y"); // (전문전송방식 구분) = "Y"
        headerDvo.setResrScp3(""); // (예약 영역) = ""
        headerDvo.setAtmtCancYn(""); // (자동취소여부) = ""

        // 데이터부 전문 DTO 생성
        WwdaAccountOwnerRnmConfDataDvo dataDvo = new WwdaAccountOwnerRnmConfDataDvo();
        dataDvo.setAcno(resultDvo.getAcnoEncr()); // (계좌번호) = 복호화(ACNO_ENCR(계좌번호암호화))
        dataDvo.setDepsPrsnNm(""); // (예금주명) = ""
        dataDvo.setHndlBank(resultDvo.getFnitCd().substring(1)); // (취급은행) = substring(TB_RVDW_FNIT_CD.금융기관대표코드, 2, 2) // 뒤 2자리
        dataDvo.setHndlBankBrnc("0000"); // (취급은행지점) = "0000" 
        dataDvo.setDealAmt("100"); // (거래금액) = "100"
        dataDvo.setDpstRqstPrsnNm("교원"); // (입금의뢰인명) = "교원"
        dataDvo.setChckamt(""); // (수표금액) = ""
        dataDvo.setCmsCd(""); // (CMS_CD) = ""
        dataDvo.setBlan1(""); // (Filler) = ""
        dataDvo.setNwHndlBank(resultDvo.getBnkCd()); // (신 취급은행) = TB_RVCL_AC_FNT_RTM_AK_APR_IZ.BNK_CD(은행코드)
        dataDvo.setBlan2(""); // (Filler) = ""

        /* 3.1 FEP "OSBN1_CDEO1001 (계좌유효성검사)" 전문 호출 
         * 4.1 수신전문 DTO 결과 확인(송신전문과 동일구조)
            - 요청일자 : 요청일자
            - 요청일련번호 : 수신DTO.userDealNo (사용자거래번호)
            - 응답코드 : 수신DTO.rplyCd   (응답코드)
            - 예금주명 : 수신DTO.depsPrsnNm   (예금주명)
        */

        WwdaAnAccountEffectivenessResDvo receiveDvo = new WwdaAnAccountEffectivenessResDvo();

        // ========== FEP 호출 후 수신 데이터가 와야함. 삭제대상 ===========
        receiveDvo.setRqdt("20221021");
        receiveDvo.setUserDealNo("거래번호123");
        receiveDvo.setRplyCd("0000");
        receiveDvo.setDepsPrsnNm("예금주");
        // =============================

        try {
            mapper.updateAcEftnAcFntRtmAkAprIz(receiveDvo);
            mapper.insertAcEftnAcFntRtmAkAprHist(receiveDvo);
        } catch (Exception e) {
            return "2";
        }
        return "1";
    }
}
