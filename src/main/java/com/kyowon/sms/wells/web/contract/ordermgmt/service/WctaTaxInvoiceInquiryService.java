package com.kyowon.sms.wells.web.contract.ordermgmt.service;

import java.util.Objects;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.contract.common.dvo.WctzCntrDetailChangeHistDvo;
import com.kyowon.sms.wells.web.contract.common.dvo.WctzTxinvRcpBaseChangeHistDvo;
import com.kyowon.sms.wells.web.contract.common.service.WctzHistoryService;
import com.kyowon.sms.wells.web.contract.ordermgmt.converter.WctaTaxInvoiceInquiryConverter;
import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaTaxInvoiceInquiryDto.SaveReq;
import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.WctaTaxInvoiceInquiryDvo;
import com.kyowon.sms.wells.web.contract.ordermgmt.mapper.WctaTaxInvoiceInquiryMapper;
import com.sds.sflex.common.utils.DateUtil;
import com.sds.sflex.system.config.context.SFLEXContextHolder;
import com.sds.sflex.system.config.core.dvo.UserSessionDvo;
import com.sds.sflex.system.config.core.service.MessageResourceService;
import com.sds.sflex.system.config.validation.BizAssert;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WctaTaxInvoiceInquiryService {

    private final WctaTaxInvoiceInquiryMapper mapper;
    private final WctaTaxInvoiceInquiryConverter converter;
    private final WctzHistoryService historyService;

    private final MessageResourceService messageResourceService;

    public WctaTaxInvoiceInquiryDvo getTaxInvoiceInquiry(String cntrNo, int cntrSn) {
        return mapper.selectTaxInvoiceInquiry(cntrNo, cntrSn);
    }

    @Transactional
    public String saveTaxInvoice(SaveReq dto) {
        WctaTaxInvoiceInquiryDvo dvo = converter.mapSaveReqToWctaTaxInvoiceInquiryDvo(dto);

        int result = 0;

        // 파라미터
        String cntrNo = dvo.getCntrNo();
        int cntrSn = dvo.getCntrSn();
        String bzrno = dvo.getBzrno(); // 사업자번호
        String txinvPblOjYn = dvo.getTxinvPblOjYn(); // 발행여부
        String emadr = dvo.getEmadr(); // 이메일
        String cralIdvTno = dvo.getCralIdvTno(); // 휴대지역전화번호
        String mexno = dvo.getMexno(); // 휴대전화국번호암호화
        String cralLocaraTno = dvo.getCralLocaraTno(); // 휴대개별전화번호
        String now = DateUtil.todayNnow();
        String nowDate = DateUtil.getNowDayString(); // 현재일자
        String nowYm = nowDate.substring(0, 6); // 현재년월
        int nowDay = Integer.parseInt(nowDate.substring(4, 6));// 현재일
        int txinvPblD = Integer.parseInt(dvo.getTxinvPblD()); // 발행일자
        String rtnMsg = ""; // 반환 메세지
        UserSessionDvo session = SFLEXContextHolder.getContext().getUserSession();

        boolean txinvPblOjInfoCheck = true; // 발행정보 변경 여부
        boolean txinvPblOjCheck = true; // 발행여부 변경 여부

        BizAssert.isFalse(StringUtils.isEmpty(bzrno) || (bzrno.length() < 10), "MSG_ALT_INVALID_BZRNO"); // 잘못된 사업자등록번호 입니다.

        WctaTaxInvoiceInquiryDvo compInvoice = mapper.selectTaxInvoiceInquiryCheck(cntrNo, cntrSn); // 원본 데이터
        BizAssert.isFalse(Objects.isNull(compInvoice), "MSG_ALT_NOT_FOUND_TXINV"); // 등록된 세금계산서를 찾을 수 없습니다.

        // 비교용
        String txinvPblOjYnComp = compInvoice.getTxinvPblOjYn();
        String emadrComp = compInvoice.getEmadr();
        String cralIdvTnoComp = compInvoice.getCralIdvTno();
        String mexnoComp = compInvoice.getMexno();
        String cralLocaraTnoComp = compInvoice.getCralLocaraTno();
        String cntrCnfmDtm = compInvoice.getCntrCnfmDtm();
        String cntrCnfmYm = StringUtils.isEmpty(cntrCnfmDtm) ? " " : cntrCnfmDtm.substring(0, 6);
        String dpTpCd = compInvoice.getDpTpCd();
        int txinvPblDComp = Integer.parseInt(compInvoice.getTxinvPblD());

        // 파라미터와 일치한 지 확인하는 구간
        if (txinvPblOjYn.equals(txinvPblOjYnComp)
            && emadrComp.equals(emadr)
            && cralIdvTnoComp.equals(cralIdvTno)
            && mexnoComp.equals(mexno)
            && cralLocaraTnoComp.equals(cralLocaraTno)) {
            txinvPblOjInfoCheck = false;
        }
        if (txinvPblOjYn.equals(txinvPblOjYnComp)) {
            txinvPblOjCheck = false;
        }

        BizAssert.isTrue(txinvPblOjCheck && txinvPblOjInfoCheck, "MSG_ALT_NO_CHG_CNTN"); // 변경된 내용이 없습니다.

        if (txinvPblOjInfoCheck && "Y".equals(txinvPblOjYn)) {
            if (nowYm.equals(cntrCnfmYm)) {
                rtnMsg = "0차월 발행은 말일 날짜로 발행되며, 익월 초 수신 가능합니다.\n※선택한 발행일자는 익월부터 반영됩니다.";
            } else if (nowDay >= txinvPblD || nowDay >= txinvPblDComp) {
                rtnMsg = "변경사항은 익월부터 반영됩니다.\\n※당월부터 반영되길 희망하는 건은 담당자에게 문의 하세요.";
            }
        }

        BizAssert.isFalse(
            dpTpCd.startsWith("02") && "Y".equals(txinvPblOjYn), "MSG_ALT_CARD_FNT_CST_NOT_PBL",
            new String[] {messageResourceService.getMessage("MSG_TXT_TXINV")}
        ); // 카드이체 고객은 세금계산서 발행이 불가합니다.

        if (txinvPblOjCheck) {

            // 계약상세 수정
            result = mapper.updateCntrDetailTxinvPblOj(txinvPblOjYn, cntrNo, cntrSn);
            BizAssert.isTrue(result > 0, "MSG_ALT_SVE_ERR"); // 저장에 실패하였습니다.
            // 계약상세변경이력 생성
            historyService.createContractDetailChangeHistory(
                WctzCntrDetailChangeHistDvo
                    .builder()
                    .cntrNo(cntrNo)
                    .cntrSn(cntrSn)
                    .histEndDtm(now)
                    .build()
            );

            // 세금계산서접수기준내역 추가

            dvo.setRcpdt(nowDate);
            dvo.setTxinvPblYn(dvo.getTxinvPblOjYn());
            dvo.setAplcPsicId(session.getEmployeeIDNumber());
            dvo.setDtaDlYn("N");
            dvo.setTxinvPblYn("Y");
            dvo.setMexnoEncr(dvo.getMexno());

            result = mapper.updateTaxInvoiceInquiry(dvo);
            BizAssert.isTrue(result > 0, "MSG_ALT_SVE_ERR"); // 저장에 실패하였습니다.
            // 세금계산서접수기준변경이력
            // mapper.insertTaxInvoiceReceiptBaseHist(dvo);
            historyService.createTaxInvoiceReceiptBaseChangeHistory(
                WctzTxinvRcpBaseChangeHistDvo
                    .builder()
                    .cntrNo(cntrNo)
                    .cntrSn(cntrSn)
                    .build()
            );

        }
        return rtnMsg;
    }
}
