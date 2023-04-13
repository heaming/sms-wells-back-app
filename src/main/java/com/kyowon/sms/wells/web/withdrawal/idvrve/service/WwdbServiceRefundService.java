package com.kyowon.sms.wells.web.withdrawal.idvrve.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.withdrawal.idvrve.converter.WwdbServiceRefundConverter;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbServiceRefundDto.SaveReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbServiceRefundDto.SearchBankRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbServiceRefundDto.SearchCardRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbServiceRefundDto.SearchServiceRefundReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbServiceRefundDto.SearchServiceRefundRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dvo.WwdbServiceRefundDvo;
import com.kyowon.sms.wells.web.withdrawal.idvrve.mapper.WwdbServiceRefundMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WwdbServiceRefundService {

    private final WwdbServiceRefundMapper mapper;
    private final WwdbServiceRefundConverter converter;

    /**
     * 고객 정보 조회
     * @param SearchServiceRefundReq
     * @return SearchServiceRefundRes
     */
    public SearchServiceRefundRes getServiceRefund(
        SearchServiceRefundReq req
    ) {
        return mapper.selectServiceRefundCustomer(req);
    }

    /**
     * 카드사 은행사 이름과 코드 조회
     * @return List<SearchCardBankRes>
     */
    public List<SearchCardRes> getServiceRefundCard() {
        return mapper.selectServiceRefundCard();
    }

    /**
     * 카드사 은행사 이름과 코드 조회
     * @param rfndDsbDvCd 지급구분
     * @return List<SearchCardBankRes>
     */
    public List<SearchBankRes> getServiceRefundBank() {
        return mapper.selectServiceRefundBank();
    }

    /**
     * 환불정보수정 수정
     * @param SearchServiceRefundReq
     * @return saveRes
     */
    @Transactional
    public int saveServiceRefund(SaveReq req) throws Exception {
        WwdbServiceRefundDvo dvo = converter.mapSaveWwdbServiceRefundDvo(req);
        return mapper.updateServiceRefundCustomer(dvo);
    }
}
