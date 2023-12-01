package com.kyowon.sms.wells.web.withdrawal.idvrve.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.common.web.withdrawal.idvrve.dvo.ZwdbIntegrationDepositDvo;
import com.kyowon.sms.common.web.withdrawal.idvrve.mapper.ZwdbIntegrationDepositMapper;
import com.kyowon.sms.common.web.withdrawal.idvrve.mapper.ZwdbRefundApplicationMapper;
import com.kyowon.sms.common.web.withdrawal.zcommon.dvo.ZwdzWithdrawalReceiveDvo;
import com.kyowon.sms.common.web.withdrawal.zcommon.dvo.ZwdzWithdrawalRefundAskDvo;
import com.kyowon.sms.common.web.withdrawal.zcommon.mapper.ZwdzWithdrawalMapper;
import com.kyowon.sms.common.web.withdrawal.zcommon.service.ZwdzWithdrawalService;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbServiceRefundDto.SaveReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dvo.WwdbServiceRefundDvo;
import com.kyowon.sms.wells.web.withdrawal.idvrve.mapper.WwdbServiceRefundMapper;
import com.sds.sflex.system.config.context.SFLEXContextHolder;
import com.sds.sflex.system.config.core.dvo.UserSessionDvo;

import lombok.RequiredArgsConstructor;

/**
 * <pre>
 * 서비스 환불 등록 서비스
 * </pre>
 *
 * @author sonkiseok
 * @since 2023-04-13
 */
@Service
@RequiredArgsConstructor
public class WwdbServiceRefundService {

    private final WwdbServiceRefundMapper serviceRefundMapper;

    private final ZwdbIntegrationDepositMapper integrationDepositMapper;
    private final ZwdzWithdrawalMapper zwdzwithdrawalMapper;

    private final ZwdbRefundApplicationMapper refundApplicationMapper;
    private final ZwdzWithdrawalService withdrawalService;

    /**
     * 환불정보수정 수정
     * @param req
     * @return saveRes
     */
    @Transactional
    public WwdbServiceRefundDvo saveServiceRefund(SaveReq req) {
        /*
        * 4. 환불접수기본 생성
        * 5. 환불접수상세 생성
        * 6. 수납요청기본 생성
        * 7. 수납요청상세 생성
        * 8. 통합입금기본 생성
        * 9. 입금대사기본 생성
        * 10. 수납기본 생성
        * 11. 수납상세 생성
        * */

        UserSessionDvo session = SFLEXContextHolder.getContext().getUserSession(); //세션정보

        // 환불접수번호
        String refundReceipNo = refundApplicationMapper.selectRefundReceiptPk(session.getCompanyCode());

        // 수납요청번호
        String rveAkNo = withdrawalService.createReceiveAskNumber(session.getCompanyCode());
        /*1. 환불접수기본 데이터 생성*/
        WwdbServiceRefundDvo serviceRefundDvo = new WwdbServiceRefundDvo();

        serviceRefundDvo.setRfndRcpNo(refundReceipNo);/*환불접수번호*/
        serviceRefundDvo.setRveAkNo(rveAkNo); /*수납요청번호*/
        serviceRefundDvo.setKwGrpCoCd(session.getCompanyCode());/*교원그룹회사코드*/
        serviceRefundDvo.setRveCoCd(session.getCompanyCode());/*수납회사코드*/
        serviceRefundDvo.setRveCd(session.getRveCd()); /*수납코드*/
        serviceRefundDvo.setCntrNo(req.serviceInfo().cntrNo());/*계약번호*/
        serviceRefundDvo.setCntrSn(req.serviceInfo().cntrSn());/*계약일련번호*/
        serviceRefundDvo.setRfndPrtnrNo(session.getEmployeeIDNumber()); /*환불요청파트너조직유형코드*/
        serviceRefundDvo.setRfndPrtnrOgTpCd(session.getOgTpCd());/*환불요청파트너번호*/
        serviceRefundDvo.setRfndRcpPhCd("02");/*환불접수경로코드-서비스*/
        serviceRefundDvo.setRfndAkDt(req.serviceRefundInfo().rfndRqdt());/*환불요청일자*/
        serviceRefundDvo.setRfndDsbDuedt(req.serviceRefundInfo().rfndDsbDt());/*환불완료일자*/
        serviceRefundDvo.setCstSvAsnNo(req.serviceInfo().cstSvAsnNo());/*고객서비스배정번호*/
        serviceRefundDvo.setCsBilNo(req.serviceInfo().csBilNo());/*고객청구번호*/
        serviceRefundDvo.setDpSn(req.serviceInfo().dpSn());/*입금일련번호*/
        serviceRefundDvo.setRfndCshAkAmt(req.serviceRefundInfo().rfndCshAkSumAmt());/*현금환불금액*/
        serviceRefundDvo.setRfndCardAkAmt(req.serviceRefundInfo().rfndCardAkSumAmt());/*카드환불금액*/
        serviceRefundDvo.setRfndBltfAkAmt(req.serviceRefundInfo().rfndBltfAkSumAmt());/*전금환불금액*/
        serviceRefundDvo.setRfndDdtnAmt(req.serviceRefundInfo().rfndDdtnAmt());/*공제금액*/
        serviceRefundDvo.setCrdcdFeeAmt(req.serviceRefundInfo().crdcdFeeSumAmt());/*수수료금액*/
        serviceRefundDvo.setCardRfndFer(req.serviceRefundInfo().crdcdFeeFer());/*카드환불수수료율*/
        serviceRefundDvo.setBilAmt(req.serviceRefundInfo().bilAmt());/*청구금액-환불가능금액*/
        serviceRefundDvo.setRfndRveDt(req.serviceRefundInfo().rfndRqdt());/*수납일자*/
        serviceRefundDvo.setRfndPerfDt(req.serviceRefundInfo().rfndRqdt());/*실적일자*/
        serviceRefundDvo.setRfndDsbDt(req.serviceRefundInfo().rfndDsbDt());/*지급일자*/
        serviceRefundDvo.setItgDpNo(req.serviceInfo().itgDpNo());
        serviceRefundDvo.setRfndAkAmt(req.serviceRefundInfo().rfndAkAmt()); /*실지급액*/

        serviceRefundMapper.insertServiceRefundReceiptBas(serviceRefundDvo);

        serviceRefundDvo.setRfndRsonCd(req.serviceRefundInfo().rfndRsonCd());/*환불사유코드*/
        serviceRefundDvo.setRfndRsonCn(req.serviceRefundInfo().rfndRsonCn());/*환불사유내용*/
        serviceRefundDvo.setRfrndTpCd("03");/*환불유형코드-서비스상품종료*/

        // 현급 환불 금액이 존재하는 경우
        if (Integer.parseInt(req.serviceRefundInfo().rfndCshAkSumAmt()) > 0) {

            /*카드환불정보 삭제*/
            serviceRefundDvo.setRfndCardAkAmt("");/*카드환불금액*/
            serviceRefundDvo.setRfndBltfAkAmt("");/*전금환불금액*/
            serviceRefundDvo.setCardRfndFnitCd("");/*카드환불금융기관코드*/
            serviceRefundDvo.setCardRfndCrdcdAprStrtDt("");/*카드환불신용카드승인시작일자*/
            serviceRefundDvo.setCardRfndCrdcdAprEndDt("");/*카드환불신용카드승인종료일자*/
            serviceRefundDvo.setCardRfndCrcdnoEncr("");/*카드환불신용카드번호암호화*/
            serviceRefundDvo.setCardRfndCrdcdIstmMcn("");/*카드환불신용카드할부개월수*/
            serviceRefundDvo.setCardRfndCrcdonrNm("");/*카드환불신용카드주명*/
            serviceRefundDvo.setCardRfndCrdcdAprno("");/*카드환불신용카드승인번호*/
            serviceRefundDvo.setCardRfndCrdcdAprAmt("");/*카드환불신용카드승인금액*/

            /*현금 환불 정보 추가*/
            serviceRefundDvo.setRfndCshAkAmt(req.serviceRefundInfo().rfndCshAkSumAmt());/*현금환불금액*/
            serviceRefundDvo.setRfrndDbsDvCd("01");/*환불지급구분코드-현금환불*/
            serviceRefundDvo.setCshRfndDvCd(req.serviceRefundInfo().cshRfndDvCd());/*현금환불구분코드*/
            serviceRefundDvo.setRfrndAcDvCd("02");/*환불계좌구분코드-신규계좌*/
            serviceRefundDvo.setCshRfndFnitCd(req.serviceRefundInfo().cshRfndFnitCd());/*현금환불금융기관코드*/
            serviceRefundDvo.setCshRfndAcnoEncr(req.serviceRefundInfo().cshRfndAcnoEncr());/*현금환불계좌번호암호화*/
            serviceRefundDvo.setCshRfndAcownNm(req.serviceRefundInfo().cstNm());/*현금환불계좌주명*/
            serviceRefundDvo.setCrdcdFeeAmt(req.serviceRefundInfo().crdcdFeeSumAmt());/*카드환불수수료*/
            serviceRefundDvo.setRfndFeeYn(Integer.parseInt(req.serviceRefundInfo().crdcdFeeSumAmt()) > 0 ? "Y" : "N");
            serviceRefundMapper.insertServiceRefundReceiptDtl(serviceRefundDvo);
        }

        // 카드 환불 금액이 존재하는 경우
        if (Integer.parseInt(req.serviceRefundInfo().rfndCardAkSumAmt()) > 0) {

            /*현금환불정보 삭제*/
            serviceRefundDvo.setRfndCshAkAmt("");/*현금환불금액*/
            serviceRefundDvo.setRfndBltfAkAmt("");/*전금환불금액*/
            serviceRefundDvo.setCshRfndDvCd("");/*현금환불구분코드*/
            serviceRefundDvo.setRfrndAcDvCd("");/*환불계좌구분코드-신규계좌*/
            serviceRefundDvo.setCshRfndFnitCd("");/*현금환불금융기관코드*/
            serviceRefundDvo.setCshRfndAcnoEncr("");/*현금환불계좌번호암호화*/
            serviceRefundDvo.setCshRfndAcownNm("");/*현금환불계좌주명*/
            serviceRefundDvo.setCrdcdFeeAmt("");/*카드환불수수료*/
            serviceRefundDvo.setCardRfndFer("");/*카드환불수수료율*/

            /*카드 환불 정보 추가*/
            serviceRefundDvo.setRfrndDbsDvCd("02");/*환불지급구분코드-현금환불*/
            serviceRefundDvo.setRfndCardAkAmt(req.serviceRefundInfo().rfndCardAkSumAmt());/*카드환불금액*/
            serviceRefundDvo.setCardRfndCrdcdAprAmt(req.serviceRefundInfo().rfndCardAkSumAmt());/*카드환불신용카드승인금액*/
            serviceRefundDvo.setCardRfndFnitCd(req.serviceRefundInfo().cardRfndFnitCd());/*카드환불금융기관코드*/
            serviceRefundDvo.setCardRfndCrdcdAprStrtDt(req.serviceInfo().dpDtm());/*카드환불신용카드승인시작일자*/
            serviceRefundDvo.setCardRfndCrdcdAprEndDt(req.serviceInfo().dpDtm());/*카드환불신용카드승인종료일자*/
            serviceRefundDvo.setCardRfndCrcdnoEncr(req.serviceRefundInfo().cardRfndCrcdnoEncr());/*카드환불신용카드번호암호화*/
            serviceRefundDvo.setCardRfndCrdcdIstmMcn(req.serviceRefundInfo().istmMcn());/*카드환불신용카드할부개월수*/
            serviceRefundDvo.setCardRfndCrcdonrNm(req.serviceRefundInfo().crcdonrNm());/*카드환불신용카드주명*/
            serviceRefundDvo.setCardRfndCrdcdAprno(req.serviceInfo().cardAprno());/*카드환불신용카드승인번호*/
            serviceRefundDvo.setCardRfndCrdcdAprAmt(req.serviceRefundInfo().rfndAkAmt());/*카드환불신용카드승인금액*/
            serviceRefundMapper.insertServiceRefundReceiptDtl(serviceRefundDvo);
        }

        // 수납요청기본 생성
        serviceRefundMapper.insertServiceRefundRveAkBas(serviceRefundDvo);
        // 수납요청상세 생성
        serviceRefundMapper.insertServiceRefundRveAkDtl(serviceRefundDvo);
        // 통합입금기본 데이터 수정 및 생성
        ZwdbIntegrationDepositDvo itgDpDvo = new ZwdbIntegrationDepositDvo();
        itgDpDvo.setKwGrpCoCd(session.getCompanyCode());
        serviceRefundDvo.setItgDpNo(integrationDepositMapper.selectIntegrationDepositNumber(itgDpDvo));
        serviceRefundMapper.insertServiceRefundItgDpBas(serviceRefundDvo);
        serviceRefundMapper.insertServiceRefundDpCprcnfBas(serviceRefundDvo);

        ZwdzWithdrawalReceiveDvo dvo = new ZwdzWithdrawalReceiveDvo();
        dvo.setKwGrpCoCd(session.getCompanyCode());
        String rveNo = zwdzwithdrawalMapper.selectReceiveNumber(dvo);
        serviceRefundDvo.setRveNo(rveNo);
        serviceRefundMapper.insertServiceRefundRveBas(serviceRefundDvo);
        serviceRefundMapper.insertServiceRefundRveDtl(serviceRefundDvo);
        ZwdzWithdrawalRefundAskDvo rendInfo = new ZwdzWithdrawalRefundAskDvo();
        rendInfo.setKwGrpCoCd(serviceRefundDvo.getKwGrpCoCd());
        rendInfo.setItgDpNo(serviceRefundDvo.getItgDpNo());
        rendInfo.setRveAkNo(rveAkNo);
        withdrawalService
            .NAAfterDepositComparisonComfirmation(rendInfo);

        return serviceRefundDvo;
    }
}
