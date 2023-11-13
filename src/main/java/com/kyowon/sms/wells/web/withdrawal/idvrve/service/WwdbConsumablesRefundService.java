package com.kyowon.sms.wells.web.withdrawal.idvrve.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.withdrawal.idvrve.converter.WwdbConsumablesRefundConverter;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbConsumablesRefundDto.RemoveReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbConsumablesRefundDto.SaveReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbConsumablesRefundDto.SearchBankRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbConsumablesRefundDto.SearchCardRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbConsumablesRefundDto.SearchConsumablesRefundReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbConsumablesRefundDto.SearchConsumablesRefundRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbConsumablesRefundDto.SearchContractInfoRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dvo.WwdbConsumablesRefundDvo;
import com.kyowon.sms.wells.web.withdrawal.idvrve.mapper.WwdbConsumablesRefundMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * <pre>
 * 소모품환불관리 서비스
 * </pre>
 *
 * @author sonkiseok
 * @since 2023-04-19
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class WwdbConsumablesRefundService {

    private final WwdbConsumablesRefundMapper mapper;
    private final WwdbConsumablesRefundConverter converter;

    /**
     * 소모품환불관리 목록 / 페이징
     * @param req
     * @param pageInfo
     * @return PagingResult<SearchConsumablesRefundRes>
     */
    public PagingResult<SearchConsumablesRefundRes> getConsumablesRefundPages(
        SearchConsumablesRefundReq req,
        PageInfo pageInfo
    ) {
        return mapper.selectConsumablesRefund(req, pageInfo);
    }

    /**
     * 소모품환불관리 엑셀 다운로드
     * @param req List<SearchConsumablesRefundRes>
     * @return
     */
    public List<SearchConsumablesRefundRes> getConsumablesRefundExcels(
        SearchConsumablesRefundReq req
    ) {
        return mapper.selectConsumablesRefund(req);
    }

    /**
     * 소모품환불관리 삭제
     * @param req
     * @return int processCount
     * @throws Exception
     */
    @Transactional
    public int removeConsumablesRefunds(List<RemoveReq> req) throws Exception {

        int processCount = 0;

        log.info("=================result start ===============");
        for (RemoveReq dto : req) {
            WwdbConsumablesRefundDvo dvo = converter.mapRemoveConsumablesRefundDvo(dto);

            int result = 0;
            result += mapper.insertConsumablesRefundHistory(dvo);
            result += mapper.deleteConsumablesRefund(dvo);
            result += mapper.insertConsumablesRefundDetailHistory(dvo);
            result += mapper.deleteConsumablesRefundDetail(dvo);
            log.info("result:" + result);
            processCount += result;
        }

        log.info("=================result end===============");

        return processCount;
    }

    /**
     * 소모품환불관리 등록
     * @param req
     * @return int processCount
     * @throws Exception
     */
    @Transactional
    public int saveConsumablesRefund(SaveReq req) throws Exception {
        int processCount = 0;

        WwdbConsumablesRefundDvo dvo = converter.mapSaveConsumablesRefundDvo(req);

        processCount += mapper.insertConsumablesRefundDetail(dvo);
        processCount += mapper.insertConsumablesRefundDetailHistory(dvo);

        processCount += mapper.insertConsumablesRefund(dvo);
        processCount += mapper.insertConsumablesRefundHistory(dvo);

        return processCount;
    }

    /**
     * 고객 정보 조회
     * @param cntrNo
     * @return SearchContractInfoRes
     */
    public SearchContractInfoRes getContractInfo(
        String cntrNo
    ) {
        return mapper.selectContractInfo(cntrNo);
    }

    /**
     * 카드사 은행사 이름과 코드 조회
     * @return List<SearchCardRes>
     */
    public List<SearchCardRes> getConsumablesRefundCard() {
        return mapper.selectConsumablesRefundCard();
    }

    /**
     * 카드사 은행사 이름과 코드 조회
     * @return List<SearchBankRes>
     */
    public List<SearchBankRes> getConsumablesRefundBank() {
        return mapper.selectConsumablesRefundBank();
    }

}
