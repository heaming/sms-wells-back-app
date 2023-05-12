package com.kyowon.sms.wells.web.withdrawal.idvrve.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.withdrawal.idvrve.converter.WwdbRefundApplicationConverter;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbRefundApplicationDto.SaveReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbRefundApplicationDto.SearchBankRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbRefundApplicationDto.SearchCardRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbRefundApplicationDto.SearchRefundApplicationReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbRefundApplicationDto.SearchRefundApplicationRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbRefundApplicationDto.SearchRefundContractDetailReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbRefundApplicationDto.SearchRefundContractDetailRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbRefundApplicationDto.SearchRefundPossibleAmountReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbRefundApplicationDto.SearchRefundPossibleAmountRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dvo.WwdbRefundApplicationDvo;
import com.kyowon.sms.wells.web.withdrawal.idvrve.mapper.WwdbRefundApplicationMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class WwdbRefundApplicationService {

    private final WwdbRefundApplicationMapper mapper;
    private final WwdbRefundApplicationConverter converter;

    /**
     * 환불 신청 현황 목록
     * @param pageInfo 
     * @param SearchRefundApplicationReq
     * @return PagingResult<SearchRefundApplicationRes>
     */
    public PagingResult<SearchRefundApplicationRes> getRefundApplicationPages(
        SearchRefundApplicationReq req,
        PageInfo pageInfo
    ) {
        return mapper.selectRefundApplication(req, pageInfo);
    }

    /**
     * 환불 신청 현황 목록 엑셀 다운로드
     * @param List 
     * @param SearchRefundApplicationReq
     * @return List<SearchRefundApplicationRes>
     */
    public List<SearchRefundApplicationRes> getRefundApplicationExcels(
        SearchRefundApplicationReq req
    ) {
        return mapper.selectRefundApplication(req);
    }

    /**
     * 환불 신청 현황 상세 계약 조회
     * @param List 
     * @param SearchRefundContractDetailReq
     * @return PagingResult<SearchRefundContractDetailRes> 
     */
    public PagingResult<SearchRefundContractDetailRes> getRefundContractDetailPages(

        SearchRefundContractDetailReq req, PageInfo pageInfo
    ) {
        return mapper.selectRefundContractDetail(req, pageInfo);
    }

    /**
     * 환불 신청 현황 상세 계약 목록 엑셀 다운로드
     * @param List 
     * @param SearchRefundContractDetailReq
     * @return PagingResult<SearchRefundContractDetailRes> 
     */
    public List<SearchRefundContractDetailRes> getRefundContractDetailExcels(
        SearchRefundContractDetailReq req
    ) {
        return mapper.selectRefundContractDetail(req);
    }

    /**
     * 환불 신청 팝업 환불가능금액 조회
     * @param SearchRefundPossibleAmountReq
     * @return SearchRefundPossibleAmountRes 
     */
    public SearchRefundPossibleAmountRes getRefundPossibleAmount(
        SearchRefundPossibleAmountReq req
    ) {
        return mapper.selectRefundPossibleAmount(req);
    }

    /**
     * 카드사 이름과 코드 조회
     * @return List<SearchCardBankRes>
     */
    public List<SearchCardRes> getRefundApplicationCard() {
        return mapper.selectRefundApplicationCard();
    }

    /**
     * 은행사 이름과 코드 조회
     * @param rfndDsbDvCd 지급구분
     * @return List<SearchCardBankRes>
     */
    public List<SearchBankRes> getRefundApplicationBank() {
        return mapper.selectRefundApplicationBank();
    }

    /**
     * 환불 신청 팝업 환불 등록
     * @param SaveReq req
     * @return processCount
     */
    @Transactional
    public int createRefundApplication(SaveReq req) throws Exception {

        int processCount = 0;

        WwdbRefundApplicationDvo vo = converter.mapSaveWwdbRefundApplicationDvo(req);

        // 환불접수번호 seq 때문에 상세 입력 후 상세히스토리, 기본 입력 후 기본히스토리 입력함.
        // 환불접수기본은 1번만, 환불접수상세는 req.saveList의 길이 만큼 반복한다.
        log.info("===========================vo.getSaveList()=================================");
        log.info("vo.getSaveList()", vo.getSaveList());

        log.info("===========================vo.getSaveList()=================================");

        for (WwdbRefundApplicationDvo.saveList saveList : vo.getSaveList()) {
            log.info("===========================WwdbRefundApplicationDvo.saveList=================================");
            log.info("WwdbRefundApplicationDvo.saveList", saveList);
            log.info("===========================WwdbRefundApplicationDvo.saveList=================================");
            processCount += mapper.insertRefundApplicationDetail(saveList);
            processCount += mapper.insertRefundApplicationDetailHistory(saveList);
        }
        processCount += mapper.insertRefundApplication(vo);
        processCount += mapper.insertRefundApplicationHistory(vo);

        // 수납상세에 수납구분코드? 환불로 insert 쿼리 작성해야함 

        return processCount;
    }

}
