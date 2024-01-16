package com.kyowon.sms.wells.web.withdrawal.interfaces.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.kyowon.sflex.common.common.service.BatchCallService;
import com.kyowon.sms.common.web.withdrawal.common.dto.ZwwdbFinanceCodesDto;
import com.kyowon.sms.common.web.withdrawal.common.mapper.ZwwdbFinanceCodesMapper;
import com.kyowon.sms.common.web.withdrawal.idvrve.service.ZwdbCreditCardApprovalService;
import com.kyowon.sms.common.web.withdrawal.zcommon.dto.ZwdzWithdrawalDto;
import com.kyowon.sms.common.web.withdrawal.zcommon.dvo.ZwdzWithdrawalContractDvo;
import com.kyowon.sms.common.web.withdrawal.zcommon.dvo.ZwdzWithdrawalReceiveAskDvo;
import com.kyowon.sms.common.web.withdrawal.zcommon.mapper.ZwdzWithdrawalMapper;
import com.kyowon.sms.common.web.withdrawal.zcommon.service.ZwdzWithdrawalService;
import com.kyowon.sms.wells.web.withdrawal.interfaces.converter.WwdbCreditCardApprovalInterfaceConverter;
import com.kyowon.sms.wells.web.withdrawal.interfaces.dto.WwdaCreditCardApprovalInterfaceDto;
import com.kyowon.sms.wells.web.withdrawal.interfaces.dvo.WwdbCreditCardApprovalInterfaceDvo;
import com.kyowon.sms.wells.web.withdrawal.interfaces.mapper.WwdbCreditCardApprovalInterfaceMapper;
import com.sds.sflex.common.utils.DbEncUtil;
import com.sds.sflex.common.utils.StringUtil;
import com.ubintis.common.util.DateUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WwdbCreditCardApprovalInterfaceService {

    private final ZwdbCreditCardApprovalService cardApprovalService;
    private final ZwdzWithdrawalService withdrawalService;
    private final BatchCallService batchCallService;

    private final ZwdzWithdrawalMapper withdrawalMapper;
    private final WwdbCreditCardApprovalInterfaceConverter converter;

    private final WwdbCreditCardApprovalInterfaceMapper creditCardAprovalInterfaceMapper;
    private final ZwwdbFinanceCodesMapper financeCodesMapper;

    public WwdaCreditCardApprovalInterfaceDto.SaveRes createCreditCardApproval(
        WwdaCreditCardApprovalInterfaceDto.SaveReq dto
    ) throws Exception {
        /**
         * 1. 수납요청 데이터 생성
         * 2. 수납요청상세 데이터 생성
         * 3. 카드 결제 요청
         */

        String returnRsCd = "F";
        String returnRsNm = "실패";

        /* 데이터 존재 여부 확인 */
        if (StringUtil.isEmpty(dto.cntrNo())) {
            returnRsCd = "F";
            returnRsNm = "계약번호는 필수 입니다.";
            return WwdaCreditCardApprovalInterfaceDto.SaveRes.builder().rsCd(returnRsCd).rsCdNm(returnRsNm).build();
        }
        if (StringUtil.isEmpty(dto.cntrSn())) {
            returnRsCd = "F";
            returnRsNm = "계약일련번호는 필수 입니다.";
            return WwdaCreditCardApprovalInterfaceDto.SaveRes.builder().rsCd(returnRsCd).rsCdNm(returnRsNm).build();
        }
        if (StringUtil.isEmpty(dto.fnitCd())) {
            returnRsCd = "F";
            returnRsNm = "금융기관코드는 필수 입니다.";
            return WwdaCreditCardApprovalInterfaceDto.SaveRes.builder().rsCd(returnRsCd).rsCdNm(returnRsNm).build();
        } else {
            if ("Z98".equals(dto.fnitCd())) {
                returnRsCd = "F";
                returnRsNm = "유효하지 않은 금융기관코드 입니다.";
                return WwdaCreditCardApprovalInterfaceDto.SaveRes.builder().rsCd(returnRsCd).rsCdNm(returnRsNm).build();
            }

            ZwwdbFinanceCodesDto.SearchReq searchReq = new ZwwdbFinanceCodesDto.SearchReq("2", "003");
            List<ZwwdbFinanceCodesDto.SearchRes> fintInfos = financeCodesMapper.selectFinanceBureauCodes(searchReq);
            if (fintInfos.stream().filter(item -> dto.fnitCd().equals(item.codeId())).count() < 1) {
                returnRsCd = "F";
                returnRsNm = "유효하지 않은 금융기관코드 입니다.";
                return WwdaCreditCardApprovalInterfaceDto.SaveRes.builder().rsCd(returnRsCd).rsCdNm(returnRsNm).build();
            }
        }
        if (StringUtil.isEmpty(dto.rveDvCd())) {
            returnRsCd = "F";
            returnRsNm = "수납구분코드는 필수 입니다.";
            return WwdaCreditCardApprovalInterfaceDto.SaveRes.builder().rsCd(returnRsCd).rsCdNm(returnRsNm).build();
        }
        if (StringUtil.isEmpty(dto.indvEntrpDvCd())) {
            returnRsCd = "F";
            returnRsNm = "개인사업자구분은 필수 입니다.";
            return WwdaCreditCardApprovalInterfaceDto.SaveRes.builder().rsCd(returnRsCd).rsCdNm(returnRsNm).build();
        }
        if ("J".equals(dto.indvEntrpDvCd()) && StringUtil.isEmpty(dto.bryyMmddBzopNo())) {
            returnRsCd = "F";
            returnRsNm = "개인의 경우 생년월일은 필수 입니다.";
            return WwdaCreditCardApprovalInterfaceDto.SaveRes.builder().rsCd(returnRsCd).rsCdNm(returnRsNm).build();
        }
        if ("S".equals(dto.indvEntrpDvCd()) && StringUtil.isEmpty(dto.bryyMmddBzopNo())) {
            returnRsCd = "F";
            returnRsNm = "사업자의 경우 사업자번호는 필수 입니다.";
            return WwdaCreditCardApprovalInterfaceDto.SaveRes.builder().rsCd(returnRsCd).rsCdNm(returnRsNm).build();
        }
        if (StringUtil.isEmpty(dto.cardNo())) {
            returnRsCd = "F";
            returnRsNm = "카드번호는 필수 입니다.";
            return WwdaCreditCardApprovalInterfaceDto.SaveRes.builder().rsCd(returnRsCd).rsCdNm(returnRsNm).build();
        }
        if (StringUtil.isEmpty(dto.crdcdExpdtYm())) {
            returnRsCd = "F";
            returnRsNm = "카드 유효기간은 필수 입니다.";
            return WwdaCreditCardApprovalInterfaceDto.SaveRes.builder().rsCd(returnRsCd).rsCdNm(returnRsNm).build();
        }
        if (StringUtil.isEmpty(dto.rveCd())) {
            returnRsCd = "F";
            returnRsNm = "수납코드는 필수 입니다.";
            return WwdaCreditCardApprovalInterfaceDto.SaveRes.builder().rsCd(returnRsCd).rsCdNm(returnRsNm).build();
        }
        if (StringUtil.isEmpty(dto.ogTpCd())) {
            returnRsCd = "F";
            returnRsNm = "파트너의 조직유형코드는 필수 입니다.";
            return WwdaCreditCardApprovalInterfaceDto.SaveRes.builder().rsCd(returnRsCd).rsCdNm(returnRsNm).build();
        }
        if (StringUtil.isEmpty(dto.prtnrNo())) {
            returnRsCd = "F";
            returnRsNm = "파트너번호는 필수 입니다.";
            return WwdaCreditCardApprovalInterfaceDto.SaveRes.builder().rsCd(returnRsCd).rsCdNm(returnRsNm).build();
        }
        if (StringUtil.isEmpty(dto.aprPsicId())) {
            returnRsCd = "F";
            returnRsNm = "승인담당자 필수 입니다.";
            return WwdaCreditCardApprovalInterfaceDto.SaveRes.builder().rsCd(returnRsCd).rsCdNm(returnRsNm).build();
        }

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
            receiveAskDvo.setReceiveDivideCode(dto.rveDvCd());
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
            // 수납코드
            receiveAskDvo.setReceiveCode(dto.rveCd());

            withdrawalService.createReceiveAskDetail(receiveAskDvo);
            withdrawalService.createReceiveAskDetailHistory(receiveAskDvo);

            // 조직유형코드
            receiveAskDvo.setRvePrtnrOgTpCd(dto.ogTpCd());
            // 파트너번호
            receiveAskDvo.setRvePrtnrNo(dto.prtnrNo());
            // 승인담당자 -> 최초등록자ID
            receiveAskDvo.setAprPsicId(dto.aprPsicId());
            creditCardAprovalInterfaceMapper.updateReceiveAskDetail(receiveAskDvo);

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
            aprDvo.setRvePrtnrOgTpCd(dto.ogTpCd()); // 수납요청파트너조직유형코드
            aprDvo.setRvePrtnrNo(dto.prtnrNo()); // 수납요청파트너번호
            aprDvo.setCrdcdDv(receiveAskDvo.getCrdcdCopnDvCd()); // 신용카드법인격구분코드
            aprDvo.setRveCd(dto.rveCd()); //  수납코드
            HashMap<String, Object> paramVal = new HashMap<String, Object>();
            // 신용카드 승인
            paramVal.put("CRDCD_APR_DV", "APR");
            paramVal.put("RVE_AK_NO", receiveAskDvo.getReceiveAskNumber());
            HashMap<String, Object> returnMap = cardApprovalService.createCreditCardApprovalCancelStep1(
                converter.mapCreditCardApprovalInterfaceDvoToCreditCardApprovalDtoSaveReq(aprDvo), paramVal
            );
            while (true) {
                Thread.sleep(2000);
                String batchId = returnMap.get("BATCH_ID").toString();
                String jobStatus = batchCallService.getLastestJobStatus(batchId);
                if ("Ended OK".equals(jobStatus) || "Ended Not OK".equals(jobStatus)) {
                    returnRsCd = "S";
                    returnRsNm = "성공";
                    break;
                }
            }
            if ("S".equals(returnRsCd)) {
                // 통합입금번호
                receiveAskDvo.setItgDpNo(returnMap.get("ITG_DP_NO").toString());
                creditCardAprovalInterfaceMapper.updateReceiveDetail(receiveAskDvo);
            }
        } else {
            returnRsCd = "F";
            // 계약정보를 찾을수 없습니다.
            returnRsNm = "계약정보를 찾을수 없습니다.";
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
            receiveAskDvo.setRveAkMthdCd("02"); // 비대면
            receiveAskDvo.setRveAkMthdCd("10"); // 소비자 알림센터
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
            receiveAskDvo.setReceiveDivideCode(dto.rveDvCd());
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
            // 수납코드
            receiveAskDvo.setReceiveCode(dto.rveCd());

            withdrawalService.createReceiveAskDetail(receiveAskDvo);
            withdrawalService.createReceiveAskDetailHistory(receiveAskDvo);

            // 조직유형코드
            receiveAskDvo.setRvePrtnrOgTpCd(dto.ogTpCd());
            // 파트너번호
            receiveAskDvo.setRvePrtnrNo(dto.prtnrNo());
            // 승인담당자 -> 최초등록자ID
            receiveAskDvo.setAprPsicId(dto.aprPsicId());
            creditCardAprovalInterfaceMapper.updateReceiveAskDetail(receiveAskDvo);

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
            returnRsNm = result > 0 ? "성공" : "실패";

        } else {
            returnRsCd = "F";
            // 계약정보를 찾을수 없습니다.
            returnRsNm = "계약정보를 찾을수 없습니다.";
        }
        return WwdaCreditCardApprovalInterfaceDto.SaveRes.builder().rsCd(returnRsCd).rsCdNm(returnRsNm).build();
    }
}
