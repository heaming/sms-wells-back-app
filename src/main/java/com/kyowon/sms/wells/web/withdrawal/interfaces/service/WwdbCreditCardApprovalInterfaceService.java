package com.kyowon.sms.wells.web.withdrawal.interfaces.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.kyowon.sms.common.web.withdrawal.idvrve.service.ZwdbCreditCardApprovalService;
import com.kyowon.sms.common.web.withdrawal.zcommon.dto.ZwdzWithdrawalDto;
import com.kyowon.sms.common.web.withdrawal.zcommon.dvo.ZwdzWithdrawalContractDvo;
import com.kyowon.sms.common.web.withdrawal.zcommon.dvo.ZwdzWithdrawalReceiveAskDvo;
import com.kyowon.sms.common.web.withdrawal.zcommon.mapper.ZwdzWithdrawalMapper;
import com.kyowon.sms.common.web.withdrawal.zcommon.service.ZwdzWithdrawalService;
import com.kyowon.sms.wells.web.withdrawal.interfaces.converter.WwdbCreditCardApprovalInterfaceConverter;
import com.kyowon.sms.wells.web.withdrawal.interfaces.dto.WwdaCreditCardApprovalInterfaceDto;
import com.kyowon.sms.wells.web.withdrawal.interfaces.dvo.WwdbCreditCardApprovalInterfaceDvo;
import com.sds.sflex.common.utils.DbEncUtil;
import com.sds.sflex.common.utils.StringUtil;
import com.sds.sflex.system.config.core.service.MessageResourceService;
import com.ubintis.common.util.DateUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WwdbCreditCardApprovalInterfaceService {

    private final ZwdbCreditCardApprovalService cardApprovalService;
    private final ZwdzWithdrawalService withdrawalService;
    private final MessageResourceService messageResourceService;

    private final ZwdzWithdrawalMapper withdrawalMapper;
    private final WwdbCreditCardApprovalInterfaceConverter converter;

    public WwdaCreditCardApprovalInterfaceDto.SaveRes createCreditCardApproval(
        WwdaCreditCardApprovalInterfaceDto.SaveReq dto
    ) {
        /**
         * 1. 수납요청 데이터 생성
         * 2. 수납요청상세 데이터 생성
         * 3. 카드 결제 요청
         */
        String returnRsCd = "";
        String returnRsNm = "";
        ZwdzWithdrawalContractDvo contractDvo = new ZwdzWithdrawalContractDvo();
        contractDvo.setCntrNo(dto.cntrNo());
        contractDvo.setCntrSn(dto.cntrSn());
        contractDvo.setFnitCd(dto.fnitCd());
        List<ZwdzWithdrawalContractDvo> contractDvos = withdrawalMapper.selectContracts(contractDvo);

        if (!ObjectUtils.isEmpty(contractDvos)) {

            ZwdzWithdrawalReceiveAskDvo receiveAskDvo = new ZwdzWithdrawalReceiveAskDvo();
            receiveAskDvo.setKyowonGroupCompanyCd("2000");
            receiveAskDvo.setReceiveCompanyCode("2000");
            receiveAskDvo.setCustomNumber(contractDvos.get(0).getCstNo());
            receiveAskDvo.setRveAkMthdCd("02"); // 비대면
            receiveAskDvo.setRveAkPhCd("10"); // 소비자 알림센터
            receiveAskDvo.setRvePrtnrOgTpCd(contractDvos.get(0).getSellOgTpCd());
            receiveAskDvo.setRvePrtnrNo(contractDvos.get(0).getSellPrtnrNo());
            receiveAskDvo.setReceiveAskDate(DateUtil.getShortDateString());
            receiveAskDvo.setReceiveAskAmount(dto.stlmAmt());
            String rveAkNo = withdrawalService.createReceiveAskBase(receiveAskDvo);
            receiveAskDvo.setReceiveAskNumber(rveAkNo);
            // 입금구분코드(1-입금)
            receiveAskDvo.setDepositDivideCode("1");
            // 입금수단코드(02-신용카드)
            receiveAskDvo.setDepositMeansCode("02");
            // 입금유형코드(0201-개별수납(신용카드))
            receiveAskDvo.setDepositTypeCode("0201");
            // 수납구분코드
            receiveAskDvo.setReceiveDivideCode("");
            // 계약번호
            receiveAskDvo.setContractNumber(dto.cntrNo());
            // 계약번호
            receiveAskDvo.setContractSerialNumber(dto.cntrSn());
            // 수납코드
            receiveAskDvo.setProductCode(contractDvos.get(0).getPdCd());
            // 수납요청대상식별번호1
            receiveAskDvo.setReceiveAskObjectDrmNumber1(dto.cntrNo());
            // 수납요청대상식별번호2
            receiveAskDvo.setReceiveAskObjectDrmNumber2(dto.cntrSn());
            // 수납상태코드
            receiveAskDvo.setReceiveStatusCode("01");
            // 금융기관코드
            receiveAskDvo.setFinancialInstitutionCd(dto.fnitCd());
            // 신용카드생년월일
            receiveAskDvo.setCrdcdBryyMmdd("J".equals(dto.indvEntrpDvCd()) ? dto.bryyMmddBzopNo() : "");
            // 신용카드사업자등록번호
            receiveAskDvo.setCrdcdBzrno("S".equals(dto.indvEntrpDvCd()) ? dto.bryyMmddBzopNo() : "");
            // 신용카드법인격구분코드
            receiveAskDvo.setCrdcdCopnDvCd("J".equals(dto.indvEntrpDvCd()) ? "1" : "2");
            // 신용카드암호화
            receiveAskDvo.setCreditCardNumberEncr(DbEncUtil.enc(dto.cardNo()));
            // 신용카드유효기간년월
            receiveAskDvo.setCreditCardExpireDate(dto.crdcdExpdtYm());
            // 신용카드할부개월수
            receiveAskDvo.setCreditCardIstmMcn("0");
            // 수납회사코드
            receiveAskDvo.setReceiveCompanyCd("2000");
            // 소득공제여부
            receiveAskDvo.setIncmdcYn("N");

            withdrawalService.createReceiveAskDetail(receiveAskDvo);
            withdrawalService.createReceiveAskDetailHistory(receiveAskDvo);
            WwdbCreditCardApprovalInterfaceDvo aprDvo = new WwdbCreditCardApprovalInterfaceDvo();
            aprDvo.setKwGrpCoCd(receiveAskDvo.getKyowonGroupCompanyCd()); // 교원그룹코드
            aprDvo.setRveAkNo(receiveAskDvo.getReceiveAskNumber()); // 수납요청번호
            aprDvo.setCstNo(receiveAskDvo.getCustomNumber()); // 고객번호
            aprDvo.setFinanceCd(dto.fnitCd()); // 카드사
            aprDvo.setCrdcdNo1(dto.cardNo().substring(0, 4)); // 카드번호1
            aprDvo.setCrdcdNo2(dto.cardNo().substring(4, 8)); // 카드번호1
            aprDvo.setCrdcdNo3(dto.cardNo().substring(8, 12)); // 카드번호1
            aprDvo.setCrdcdNo4(dto.cardNo().substring(12)); // 카드번호1
            aprDvo.setCrdcdExpdtYm(dto.crdcdExpdtYm()); // 카드유효기간
            aprDvo.setCrcdonrNm(contractDvos.get(0).getCstNm()); // 카드주명
            aprDvo.setBryyMmdd(receiveAskDvo.getCrdcdBryyMmdd()); // 생년월일
            aprDvo.setBzrno(receiveAskDvo.getCrdcdBzrno()); // 사업자등록번호
            aprDvo.setIncmdcYn("N"); // 소득공제여부
            aprDvo.setIstmMcn("0"); // 할부개월
            aprDvo.setAprAmt(dto.stlmAmt()); // 승인금액
            aprDvo.setRveAkMthdCd(receiveAskDvo.getRveAkMthdCd()); // 수납요청방식코드
            aprDvo.setRveAkPhCd(receiveAskDvo.getRveAkPhCd()); // 수납요청경로코드
            aprDvo.setRvePrtnrOgTpCd(receiveAskDvo.getRvePrtnrOgTpCd()); // 수납요청파트너조직유형코드
            aprDvo.setRvePrtnrNo(receiveAskDvo.getRvePrtnrNo()); // 수납요청파트너번호
            aprDvo.setCrdcdDv(receiveAskDvo.getCrdcdCopnDvCd()); // 신용카드법인격구분코드
            HashMap<String, Object> paramVal = new HashMap<String, Object>();
            // 신용카드 승인
            paramVal.put("CRDCD_APR_DV", "APR");
            HashMap<String, Object> returnMap = cardApprovalService.createCreditCardApprovalCancelStep1(
                converter.mapCreditCardApprovalInterfaceDvoToCreditCardApprovalDtoSaveReq(aprDvo), paramVal
            );

            returnRsCd = StringUtil.isEmpty(returnMap.get("APR_NO").toString()) ? "F" : "S";
            returnRsNm = StringUtil.isEmpty(returnMap.get("APR_NO").toString())
                ? messageResourceService.getMessage("MSG_ALT_PROC_FAIL") : "";
        } else {
            returnRsCd = "F";
            // 계약정보를 찾을수 없습니다.
            returnRsNm = messageResourceService.getMessage("MSG_ALT_NO_CONTRACT_FOUND");
        }

        return WwdaCreditCardApprovalInterfaceDto.SaveRes.builder().rsCd(returnRsCd).rsCdNm(returnRsNm).build();
    }

    public WwdaCreditCardApprovalInterfaceDto.SaveRes createCreditCardApprovalNotification(
        WwdaCreditCardApprovalInterfaceDto.SaveNotificationReq dto
    ) throws Exception {

        /**
         * 1. 수납요청 데이터 생성
         * 2. 수납요청상세 데이터 생성
         */
        String returnRsCd = "";
        String returnRsNm = "";
        ZwdzWithdrawalContractDvo contractDvo = new ZwdzWithdrawalContractDvo();
        contractDvo.setCntrNo(dto.cntrNo());
        contractDvo.setCntrSn(dto.cntrSn());
        List<ZwdzWithdrawalContractDvo> contractDvos = withdrawalMapper.selectContracts(contractDvo);

        if (!ObjectUtils.isEmpty(contractDvos)) {

            ZwdzWithdrawalReceiveAskDvo receiveAskDvo = new ZwdzWithdrawalReceiveAskDvo();
            receiveAskDvo.setKyowonGroupCompanyCd("2000");
            receiveAskDvo.setReceiveCompanyCode("2000");
            receiveAskDvo.setCustomNumber(contractDvos.get(0).getCstNo());
            receiveAskDvo.setRveAkMthdCd("08"); // 소비자 알림센터
            receiveAskDvo.setRvePrtnrOgTpCd(contractDvos.get(0).getSellOgTpCd());
            receiveAskDvo.setRvePrtnrNo(contractDvos.get(0).getSellPrtnrNo());
            receiveAskDvo.setReceiveAskDate(DateUtil.getShortDateString());
            receiveAskDvo.setReceiveAskAmount(dto.stlmAmt());
            String rveAkNo = withdrawalService.createReceiveAskBase(receiveAskDvo);
            receiveAskDvo.setReceiveAskNumber(rveAkNo);
            // 입금구분코드(1-입금)
            receiveAskDvo.setDepositDivideCode("1");
            // 입금수단코드(02-신용카드)
            receiveAskDvo.setDepositMeansCode("02");
            // 입금유형코드(0201-개별수납(신용카드))
            receiveAskDvo.setDepositTypeCode("0201");
            // 수납구분코드
            receiveAskDvo.setReceiveDivideCode("");
            // 계약번호
            receiveAskDvo.setContractNumber(dto.cntrNo());
            // 계약번호
            receiveAskDvo.setContractSerialNumber(dto.cntrSn());
            // 수납코드
            receiveAskDvo.setProductCode(contractDvos.get(0).getPdCd());
            // 수납요청대상식별번호1
            receiveAskDvo.setReceiveAskObjectDrmNumber1(dto.cntrNo());
            // 수납요청대상식별번호2
            receiveAskDvo.setReceiveAskObjectDrmNumber2(dto.cntrSn());
            // 수납상태코드
            receiveAskDvo.setReceiveStatusCode("01");
            // 신용카드할부개월수
            receiveAskDvo.setCreditCardIstmMcn("0");
            // 수납회사코드
            receiveAskDvo.setReceiveCompanyCd("2000");
            // 소득공제여부
            receiveAskDvo.setIncmdcYn("N");

            withdrawalService.createReceiveAskDetail(receiveAskDvo);
            withdrawalService.createReceiveAskDetailHistory(receiveAskDvo);

            List<String> kwGrpCoCds = new ArrayList<>();
            kwGrpCoCds.add("2000");
            List<String> rveAkNos = new ArrayList<>();
            rveAkNos.add(rveAkNo);

            String redirectUrl = "/#/ns/zmwdb-credit-card-approval-certification?kwGrpCoCds=2000&rveAkNos=" + rveAkNo
                + "&incmdcYns=N";
            int result = cardApprovalService.createCreditCardNotification(
                ZwdzWithdrawalDto.SaveNotificationReq.builder()
                    .kwGrpCoCds(kwGrpCoCds)
                    .rveAkNos(rveAkNos)
                    .forwardingDiv("NFTF")
                    .dispatchTemplateId("Z_WDB00002")
                    .forwardingGb("kakao")
                    .receiverdDestInfo(contractDvos.get(0).getCstNm() + "^" + dto.mpNo())
                    .connectUrl("/anonymous/login?deviceCheck=Y&redirectUrl=")
                    .redirectUrl(redirectUrl)
                    .build()
            );

            returnRsCd = result > 0 ? "S" : "F";
            returnRsNm = result > 0
                ? "" : messageResourceService.getMessage("MSG_ALT_PROC_FAIL");

        } else {
            returnRsCd = "F";
            // 계약정보를 찾을수 없습니다.
            returnRsNm = messageResourceService.getMessage("MSG_ALT_NO_CONTRACT_FOUND");
        }
        return WwdaCreditCardApprovalInterfaceDto.SaveRes.builder().rsCd(returnRsCd).rsCdNm(returnRsNm).build();
    }
}
