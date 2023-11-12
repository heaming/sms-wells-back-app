package com.kyowon.sms.wells.web.withdrawal.idvrve.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.withdrawal.idvrve.converter.WwdbServiceRefundConverter;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbServiceRefundDto.SaveReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dvo.WwdbRefundBaseDvo;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dvo.WwdbRefundCntrDvo;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dvo.WwdbRefundDtlDvo;
import com.kyowon.sms.wells.web.withdrawal.idvrve.mapper.WwdbRefundApplicationMapper;
import com.sds.sflex.system.config.context.SFLEXContextHolder;
import com.sds.sflex.system.config.core.dvo.UserSessionDvo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WwdbServiceRefundService {

    private final WwdbRefundApplicationMapper mapper;
    private final WwdbServiceRefundConverter converter;

    /**
     * 환불정보수정 수정
     * @param req
     * @return saveRes
     */
    @Transactional
    public WwdbRefundBaseDvo saveServiceRefund(SaveReq req) {
        /*
        * 1. 환불요청기본 생성
        * 2. 환불요청상세 생성
        * 3. 환불요청계약상세 생성
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

        String rfndId = mapper.selectRefundPk();// 채번용 데이터(환불요청번호)

        /*1. 환불요청기본 생성*/
        WwdbRefundBaseDvo refundAskBaseDvo = new WwdbRefundBaseDvo();
        refundAskBaseDvo.setAftRfndAkNo(rfndId); /*환불요청번호*/
        refundAskBaseDvo.setArfndYn("N"); /*선환불여부*/
        refundAskBaseDvo.setRfndAkPrtnrNo(session.getEmployeeIDNumber()); /*환불요청파트너조직유형코드*/
        refundAskBaseDvo.setRfndAkPrtnrOgTpCd(session.getOgTpCd());/*환불요청파트너번호*/
        refundAskBaseDvo.setCshRfndFnitCd(req.serviceRefundInfo().cshRfndFnitCd());/*현금환불금융기관코드*/
        refundAskBaseDvo.setCshRfndAcnoEncr(req.serviceRefundInfo().cshRfndAcnoEncr());/*현금환불계좌번호암호화*/
        refundAskBaseDvo.setCshRfndAcownNm(req.serviceRefundInfo().cstNm()); /*현금환불계좌주명*/
        refundAskBaseDvo.setRfndCshAkSumAmt(req.serviceRefundInfo().rfndCshAkSumAmt());/*환불현금요청합계금액*/
        refundAskBaseDvo.setRfndCardAkSumAmt(req.serviceRefundInfo().rfndCardAkSumAmt());/*환불카드요청합계금액*/
        refundAskBaseDvo.setRfndBltfAkSumAmt(req.serviceRefundInfo().rfndBltfAkSumAmt());/*환불전금요청합계금액*/
        refundAskBaseDvo.setCrdcdFeeSumAmt(req.serviceRefundInfo().crdcdFeeSumAmt());/*신용카드수수료합계금액*/
        refundAskBaseDvo.setRfndAkStatCd("03");/*환불요청상태코드*/
        refundAskBaseDvo.setRveDt(req.serviceRefundInfo().rfndRqdt());/*환불수납일자*/
        refundAskBaseDvo.setPerfDt(req.serviceRefundInfo().rfndRqdt());/*환불실적일자*/
        refundAskBaseDvo.setDsbDt(req.serviceRefundInfo().rfndRqdt());/*환불지급일자*/
        refundAskBaseDvo.setRfndProcsDvCd("01");/*환불처리구분코드*/
        mapper.insertRefundTempSave(refundAskBaseDvo);

        /*2. 환불요청상세 생성*/
        WwdbRefundDtlDvo refundAskDtlDvo = new WwdbRefundDtlDvo();
        mapper.insertRefundTempSaveDetail(refundAskDtlDvo);

        /*3. 환불요청계약상세 생성*/
        WwdbRefundCntrDvo refundAskCntrDvo = new WwdbRefundCntrDvo();
        refundAskCntrDvo.setAftRfndAkNo(rfndId); /*환불요청번호*/
        refundAskCntrDvo.setCntrNo(req.serviceInfo().cntrNo()); /*계약번호*/
        refundAskCntrDvo.setCntrSn(req.serviceInfo().cntrSn()); /*계약일련번호*/
        refundAskCntrDvo.setCstNo(req.serviceInfo().cstNo()); /*고객번호*/

        mapper.insertRefundTempSaveReqDetail(refundAskCntrDvo);
        return refundAskBaseDvo;
    }
}
