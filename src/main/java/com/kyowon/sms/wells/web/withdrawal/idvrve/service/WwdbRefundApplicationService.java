package com.kyowon.sms.wells.web.withdrawal.idvrve.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.withdrawal.idvrve.converter.WwdbRefundApplicationConverter;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbRefundApplicationDto.App;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbRefundApplicationDto.Ctract;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbRefundApplicationDto.EditRefundReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbRefundApplicationDto.RefundBasic;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbRefundApplicationDto.RefundDetail;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbRefundApplicationDto.SaveRefundReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbRefundApplicationDto.SearchBankRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbRefundApplicationDto.SearchCardRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbRefundApplicationDto.SearchRefundApplicationDetailDepositReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbRefundApplicationDto.SearchRefundApplicationDetailDepositRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbRefundApplicationDto.SearchRefundApplicationDetailPartnerRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbRefundApplicationDto.SearchRefundApplicationDetailPerformanceReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbRefundApplicationDto.SearchRefundApplicationDetailPerformanceRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbRefundApplicationDto.SearchRefundApplicationDetailPossibleReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbRefundApplicationDto.SearchRefundApplicationDetailPossibleRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbRefundApplicationDto.SearchRefundApplicationDetailReceiptRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbRefundApplicationDto.SearchRefundApplicationDetailReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbRefundApplicationDto.SearchRefundApplicationDetailRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbRefundApplicationDto.SearchRefundApplicationInfoRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbRefundApplicationDto.SearchRefundApplicationReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbRefundApplicationDto.SearchRefundApplicationRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbRefundApplicationDto.SearchRefundContractDetailReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbRefundApplicationDto.SearchRefundContractDetailRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbRefundApplicationDto.SearchRefundPossibleAmountReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbRefundApplicationDto.SearchRefundPossibleAmountRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dvo.WwdbRefundApplicationDetailDvo;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dvo.WwdbRefundApplicationDvo;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dvo.WwdbRefundApplicationInfoDvo;
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
    public int createRefundApplication(SaveRefundReq req) throws Exception {

        log.info("Service SaveReq: " + req);
        int processCount = 0;

        WwdbRefundApplicationDvo vo = converter.mapSaveWwdbRefundApplicationDvo(req);

        // 환불접수번호 seq 때문에 상세 입력 후 상세히스토리, 기본 입력 후 기본히스토리 입력함.
        // 환불접수기본은 1번만, 환불접수상세는 vo.getDetails의 길이 만큼 반복한다.

        for (WwdbRefundApplicationDetailDvo details : vo.getDetails()) {
            log.info("===========================WwdbRefundApplicationDvo.details=================================");
            log.info("WwdbRefundApplicationDvo.details", details);
            log.info("===========================WwdbRefundApplicationDvo.details=================================");
            processCount += mapper.insertRefundApplicationDetail(details);
            processCount += mapper.insertRefundApplicationDetailHistory(details);
        }

        processCount += mapper.insertRefundApplication(vo);
        processCount += mapper.insertRefundApplicationHistory(vo);

        // 수납상세에 수납구분코드? 환불로 insert 쿼리 작성해야함 

        return processCount;
    }

    /**
     * 환불 신청 팝업 환불신청, 예외환불사유, 환불접수총액, 처리정보 조회
     * @param String rfndRcpNo
     * @return List<SearchRefundApplicationInfoRes>
     */
    public SearchRefundApplicationInfoRes getRefundApplicationInfo(
        String rfndRcpNo
    ) {
        //    RefundDetail 에 조회된 내용을 저장
        List<RefundDetail> RefundDetail = mapper.selectRefundApplicationDetailInfo(rfndRcpNo);
        RefundBasic basic = mapper.selectRefundApplicationInfo(rfndRcpNo);
        log.info("====================================================20230515basic=====");
        log.info("RefundDetail", RefundDetail);
        log.info("basic", basic);
        log.info("====================================================20230515basic=====");
        return new SearchRefundApplicationInfoRes(basic, RefundDetail);
    }

    /**
     * 환불 신청 팝업 환불 수정
     * @param SaveReq req
     * @return processCount
     */
    @Transactional
    public int editRefundApplication(EditRefundReq req) throws Exception {

        int processCount = 0;

        WwdbRefundApplicationInfoDvo vo = converter.mapEditWwdbRefundApplicationDvo(req);

        for (WwdbRefundApplicationDetailDvo details : vo.getDetails()) {

            processCount += mapper.updateRefundApplicationDetail(details);
            processCount += mapper.insertRefundApplicationDetailHistory(details);

        }

        processCount += mapper.updateRefundApplication(vo.getBasic());
        processCount += mapper.insertRefundApplicationHistory(vo);

        return processCount;
    }

    /**
     * 환불 신청 팝업 환불 삭제
     * @param SaveReq req
     * @return processCount
     */
    @Transactional
    public int removeRefundApplication(String rfndRcpNo) throws Exception {

        int processCount = 0;

        processCount += mapper.insertRefundApplicationHistory(rfndRcpNo);
        processCount += mapper.deleteRefundApplication(rfndRcpNo);

        return processCount;
    }

    /**
     * 환불 신청 상세 내역 신청정보 조회 
     * @param pageInfo 
     * @param String rfndRcpNo,
     * @return PagingResult<SearchRefundApplicationDetailPartnerRes>
     */
    public List<SearchRefundApplicationDetailPartnerRes> getRefundApplicationDetailPartner(
        String rfndRcpNo
    ) {
        return mapper.selectRefundApplicationDetailPartner(rfndRcpNo);
    }

    /**
     * 환불 신청 상세 내역 환불가능금액 조회 
     * @param pageInfo 
     * @param SearchRefundApplicationDetailPossibleReq req,
     * @return PagingResult<SearchRefundApplicationDetailPartnerRes>
     */
    public List<SearchRefundApplicationDetailPossibleRes> getRefundApplicationDetailPossible(
        SearchRefundApplicationDetailPossibleReq req
    ) {
        return mapper.selectRefundApplicationDetailPossible(req);
    }

    /**
     * 환불 신청 상세 내역 상세입금 조회 
     * @param pageInfo 
     * @param SearchRefundApplicationDetailPossibleReq req,
     * @return PagingResult<SearchRefundApplicationDetailPartnerRes>
     */
    public PagingResult<SearchRefundApplicationDetailDepositRes> getRefundApplicationDetailDepositPages(
        SearchRefundApplicationDetailDepositReq req,
        PageInfo pageInfo
    ) {
        return mapper.selectRefundApplicationDetailDeposit(req, pageInfo);
    }

    public List<SearchRefundApplicationDetailDepositRes> getRefundApplicationDetailDepositExcels(
        SearchRefundApplicationDetailDepositReq req
    ) {
        return mapper.selectRefundApplicationDetailDeposit(req);
    }

    /**
     * 환불 신청 상세 내역 매출실적조회 
     * @param pageInfo 
     * @param SearchRefundApplicationDetailPossibleReq req,
     * @return PagingResult<SearchRefundApplicationDetailPartnerRes>
     */
    public PagingResult<SearchRefundApplicationDetailPerformanceRes> getRefundApplicationDetailPerformancePages(
        SearchRefundApplicationDetailPerformanceReq req,
        PageInfo pageInfo
    ) {
        return mapper.selectRefundApplicationDetailPerformance(req, pageInfo);
    }

    public List<SearchRefundApplicationDetailPerformanceRes> getRefundApplicationDetailPerformanceExcels(
        SearchRefundApplicationDetailPerformanceReq req
    ) {
        return mapper.selectRefundApplicationDetailPerformance(req);
    }

    /**
     * 환불 신청 팝업 환불신청, 예외환불사유, 환불접수총액, 처리정보 조회
     * @param String rfndRcpNo
     * @return List<SearchRefundApplicationInfoRes>
     */
    public SearchRefundApplicationDetailRes getRefundApplicationDetail(
        SearchRefundApplicationDetailReq req
    ) {
        // 계약정보
        Ctract ctract = mapper.selectRefundApplicationDetailContract(req);

        // 환불신청 테이블
        App app = mapper.selectRefundApplicationDetailApplication(req);

        // 환불신청 // 추가 버튼 누르면 추가로 생성되는 부분
        List<RefundDetail> refundDetail = mapper.selectRefundApplicationDetailInfo2(req);

        // 예외사유, 환불접수총액, 처리정보 출력
        RefundBasic basic = mapper.selectRefundApplicationInfo(req);

        return new SearchRefundApplicationDetailRes(
            ctract, // 계약정보
            app, // 환불신청
            basic, // 예외사유, 환불접수총액, 처리정보 출력
            refundDetail // 환불신청 // 추가 버튼 누르면 추가로 생성되는 부분
        );
    }

    public List<SearchRefundApplicationDetailReceiptRes> getRefundApplicationDetailReceipt(
        String rfndRcpNo
    ) {
        return mapper.selectRefundApplicationDetailReceipt(rfndRcpNo);
    }

}
