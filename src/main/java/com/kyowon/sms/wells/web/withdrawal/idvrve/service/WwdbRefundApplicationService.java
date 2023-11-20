package com.kyowon.sms.wells.web.withdrawal.idvrve.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sds.sflex.system.config.response.SaveResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.kyowon.sms.common.web.withdrawal.bilfnt.dvo.ZwdaAutoTransferRealTimeAccountCheckDvo;
import com.kyowon.sms.common.web.withdrawal.bilfnt.service.ZwdaAutoTransferRealTimeAccountService;
import com.kyowon.sms.common.web.withdrawal.idvrve.dvo.ZwdbRefundApplicationReqDvo;
import com.kyowon.sms.common.web.withdrawal.idvrve.service.ZwdbRefundApplicationService;
import com.kyowon.sms.wells.web.withdrawal.idvrve.converter.WwdbRefundApplicationConverter;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbRefundApplicationDto.*;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dvo.*;
import com.kyowon.sms.wells.web.withdrawal.idvrve.mapper.WwdbRefundApplicationMapper;
import com.kyowon.sms.wells.web.withdrawal.interfaces.dto.WwdaAutoTransferInterfaceDto;
import com.sds.sflex.common.docs.service.AttachFileService;
import com.sds.sflex.common.utils.StringUtil;
import com.sds.sflex.system.config.constant.CommConst;
import com.sds.sflex.system.config.context.SFLEXContextHolder;
import com.sds.sflex.system.config.core.dvo.UserSessionDvo;
import com.sds.sflex.system.config.core.util.IDGenUtil;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

/**
 * <pre>
 * 환불 신청 현황 서비스
 * </pre>
 *
 * @author kimoon.lim
 * @since 2023-10-31
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class WwdbRefundApplicationService {

    private final WwdbRefundApplicationMapper mapper;
    private final WwdbRefundApplicationConverter converter;
    private final AttachFileService attachFileService;

    private final ZwdaAutoTransferRealTimeAccountService acService;
    private final ZwdbRefundApplicationService zwdbRefundApplicationService;

    /**
     * 환불 신청 현황 목록 ( 메인 )
     *
     * @param req
     * @return PagingResult<SearchRefundApplicationRes>
     */
    public PagingResult<SearchRefundApplicationRes> getRefundApplicationPages(
        SearchRefundApplicationReq req,
        PageInfo pageInfo
    ) {
        return mapper.selectRefundApplication(req, pageInfo);
    }

    /**
     * 환불 신청 현황 목록 엑셀 다운로드 ( 메인 )
     *
     * @param req
     * @return List<SearchRefundApplicationRes>
     */
    public List<SearchRefundApplicationRes> getRefundApplicationExcels(
        SearchRefundApplicationReq req
    ) {
        return mapper.selectRefundApplication(req);
    }

    /*******************************************************************/
    /**
     * 환불 신청 현황 P01. 신청 조회 ( 팝업조회 - 신규 )
     *
     * @param req
     * @return PagingResult<SearchRefundContractDetailRes>
     */
    public List<SearchRefundContractDetailRes> getRefundContractDetailPages(
        SearchRefundContractDetailReq req
    ) {
        return mapper.selectRefundContractDetail(req);
    }

    /**
     * 환불 신청 현황 P01. 신청 조회 엑셀다운로드 ( 팝업조회 - 신규 )
     *
     * @param req
     * @return PagingResult<SearchRefundContractDetailRes>
     */
    public List<SearchRefundContractDetailRes> getRefundContractDetailExcels(
        SearchRefundContractDetailReq req
    ) {
        return mapper.selectRefundContractDetail(req);
    }

    /**
     * 환불 신청 현황 P01. 환불정보 검색 ( 팝업- 조회 )
     *
     * @param req
     * @return res
     */
    public SearchRefundRes getRefundApplication(
        SearchRefundReq req
    ) {
        return mapper.selectRefund(req);
    }

    /** 메인그리드에서 팝업조회시 **/
    /**
     * 환불 신청 현황 P01. 신청조회 - 계약상세 ( 팝업조회 - 신규 )
     *
     * @param req
     * @param pageInfo
     * @return
     */
    public PagingResult<SearchRefundBaseRes> getRefundBasePages(
        SearchRefundBaseReq req, PageInfo pageInfo
    ) {
        return mapper.selectRefundBasePages(req, pageInfo);
    }

    /* 환불상세 조회 */

    /**
     * 환불 신청 현황 P01. 신청조회 - 환불상세 ( 팝업조회 - 신규/ 등록조회 )
     *
     * @param req
     * @return PagingResult
     */
    public List<SearchRefundDetailRes> getRefundDetailPages(
        SearchRefundDetailReq req
    ) {
        List<SearchRefundDetailRes> data;

        if (StringUtil.isEmpty(req.rfndAkNo())) {
            data = mapper.selectRefundDetail(req);
        } else {
            data = mapper.selectRefundDetailPage(req);
        }
        return data;
    }

    /**
     * 환불 신청 현황 P01. 신청조회 - 전금상세
     *
     * @param req
     * @return
     */
    public List<SearchRefundBalanceTransferRes> getRefundBalanceTransferPages(
        SearchRefundBalanceTransferReq req
    ) {
        return mapper.selectRefundBalanceTransfer(req);
    }

    /* 저장용 서비스 */
    /**
     * 환불 신청 팝업 임시저장
     * @param req
     * @return
     * @throws Exception
     */
    @Transactional
    public int getRefundTempSave(
        SaveReq req
    ) throws Exception {
        int processCount = 0;

        SaveBaseReq saveBaseReq = req.saveBaseReq();
        List<SaveCntrReq> saveCntrReqs = req.saveCntrReqs();
        List<SaveDtlReq> saveDtlReqs = req.saveDtlReqs();
        List<SaveBltfReq> saveBltfReqs = req.saveBltfReqs();

        UserSessionDvo session = SFLEXContextHolder.getContext().getUserSession(); //세션정보

        String rfndId = mapper.selectRefundPk();// 채번용 데이터(환불요청번호)

        //환불요청기본
        WwdbRefundBaseDvo dvo = converter.mapTempSaveWwdbRefundBaseDvo(saveBaseReq);

        try {
            dvo.setRfndAkPrtnrNo(session.getEmployeeIDNumber());
            dvo.setRfndAkPrtnrOgTpCd(session.getOgTpCd());
        } catch (Exception e) {
            e.printStackTrace();
        }
        dvo.setAftRfndAkNo(rfndId);
        processCount += mapper.insertRefundTempSave(dvo); // 그리드4- 환불기본

        for (SaveCntrReq list : saveCntrReqs) {
            WwdbRefundCntrDvo cntrDvo = converter.mapTempSaveWwdbRefundCntrDvo(list);
            cntrDvo.setAftRfndAkNo(rfndId);
            processCount += mapper.insertRefundTempSaveReqDetail(cntrDvo); // 환불요청계약상세 - 환불요청상세 상위테이블
        }

        //환불요청상세
        for (SaveDtlReq list : saveDtlReqs) {
            WwdbRefundDtlDvo dtlDvo = converter.mapTempSaveWwdbRefundDtlDvo(list);
            dtlDvo.setAftRfndAkNo(rfndId);
            processCount += mapper.insertRefundTempSaveDetail(dtlDvo); // 그리드2-환불요청상세
        }

        //환불전금상세
        for (SaveBltfReq list : saveBltfReqs) {
            WwdbRefundBltfDvo bltfDvo = converter.mapTempSaveWwdbRefundBltfDvo(list);
            if (bltfDvo.getRfndAkNo() == null || StringUtil.isNull(bltfDvo.getRfndAkNo())) {
                bltfDvo.setRfndAkNo(rfndId);
            }

            switch (list.rowState()) {
                case CommConst.ROW_STATE_CREATED:
                case CommConst.ROW_STATE_UPDATED:

                    if (!CollectionUtils.isEmpty(list.attachFiles())) {
                        if (StringUtil.isEmpty(bltfDvo.getRfndEvidMtrFileId())) {
                            String apnFileId = IDGenUtil.getUUID("WDB");
                            bltfDvo.setRfndEvidMtrFileId(apnFileId);
                        }
                        attachFileService
                            .saveAttachFiles("ATG_WDB_RFND_FILE", bltfDvo.getRfndEvidMtrFileId(), list.attachFiles());
                    }
                    processCount += mapper.insertBalanceTempSaveDetail(bltfDvo); // 그리드3-환불전금요청상세
                    break;
                case CommConst.ROW_STATE_DELETED:
                    processCount += mapper.deleteBalanceTempSaveDetail(bltfDvo);
                    break;
            }
        }
        return processCount;
    }

    /**
     * 은행계좌유효성체크
     *
     * @param dto
     * @return
     */
    public WwdaAutoTransferInterfaceDto.SearchBankEffectivenessCheckRes getBankEffectivenessCheck(
        WwdaAutoTransferInterfaceDto.SearchBankEffectivenessCheckReq dto
    ) {

        String systemDvCd = "2"; /*시스템구분코드 1 : EDU, 2: WELLS*/
        Map<String, Object> reqParam = new HashMap<String, Object>();
        reqParam.put("cntrNo", dto.cntrNo()); /* 계약번호 */
        reqParam.put("cntrSn", dto.cntrSn()); /* 계약일련번호 */
        reqParam.put("bnkCd", dto.bnkCd()); /* 은행코드 */
        reqParam.put("acNo", dto.acno()); /* 계좌번호 */
        reqParam.put("copnDvCd", dto.copnDvCd()); /* 법인격구분코드 01: 개인, 02: 법인*/
        reqParam.put("copnDvDrmVal", dto.copnDvDrmVal()); /* 법인격구분식별값 */
        reqParam.put("achldrNm", dto.achldrNm()); /* 예금주명 */
        reqParam.put("systemDvCd", systemDvCd); /*  */
        reqParam.put("sysDvCd", dto.sysDvCd()); /* ??시스템구분코드  */
        reqParam.put("psicId", dto.psicId()); /* 담당자ID */
        reqParam.put("deptId", dto.deptId()); /* 부서ID */

        // 2. 은행계좌 유효성검사 서비스 호출(Z-WD-S-0027)
        ZwdaAutoTransferRealTimeAccountCheckDvo resultDvo = acService.saveAftnAcEftnChecks(reqParam);

        // 3. 수신결과 및 리턴 설정
        // 3.1.1 리턴 받은 값이 없거나 Null 인 경우 "0000" 셋팅
        String acFntRsCd = resultDvo.getAcFntRsCd();
        String acFntRsNm = "";
        // 3.2 리턴받은 계좌이체불능코드에 해당하는 계좌이체결과코드 조회
        if (!StringUtil.isEmpty(resultDvo.getBilCrtStatCd())) {
            // 실패인 경우
            if ("2".equals(resultDvo.getBilCrtStatCd())) {
                acFntRsNm = resultDvo.getErrCn();
            }
        }
        return WwdaAutoTransferInterfaceDto.SearchBankEffectivenessCheckRes.builder()
            .achldrNm(resultDvo.getAchldrNm())
            .acFntImpsCd(acFntRsCd)
            .acFntImpsCdNm(acFntRsNm)
            .errCn(resultDvo.getErrCn())
            .bilCrtStatCd(resultDvo.getBilCrtStatCd())
            .build();
    }
    /* 저장 END */

    /**
     * 환불 삭제
     * @param req
     * @return
     * @throws Exception
     */
    public int getRefundDelete(
        removeReq req
    ) throws Exception {
        int processCount = 0;

        WwdbRefundRemoveDvo dvo = converter.mapRemoveWwdbRefundDvo(req);

        processCount += mapper.deleteRefundBase(dvo);
        processCount += mapper.deleteRefundCntrDetail(dvo);
        processCount += mapper.deleteRefundDetail(dvo);
        processCount += mapper.deleteRefundBalanceDetail(dvo);

        return processCount;
    }

    /* 메인그리드에서 조회 종료*/

    /* ===== 컨텍 이력사항 ===== */

    /**
     * 환불 신청 컨텍 이력 사항
     *
     * @param cntrNo
     * @param pageInfo
     * @return
     */
    public PagingResult<SearchRefundApplicationConnectHistoryRes> getRefundApplicationConnectHistoryPages(
        String cntrNo, PageInfo pageInfo
    ) {
        return mapper.selectRefundApplicationConnectHistory(cntrNo, pageInfo);
    }

    /**
     * 환불 신청 컨텍 이력 사항
     *
     * @param cntrNo
     * @return PagingResult<SearchRefundApplicationConnectHistoryRes>
     */
    public List<SearchRefundApplicationConnectHistoryRes> getRefundApplicationConnectHistorysForExcelDownload(
        String cntrNo
    ) {
        return mapper.selectRefundApplicationConnectHistory(cntrNo);
    }

    /* 승인 서비스 */
    /**
     * 환불 신청 팝업 임시 저장
     * @param req
     * @return
     * @throws Exception
     */
    @Transactional
    public int getRefundApprovalSave(
        SaveApprovalReq req
    ) throws Exception {
        int processCount = 0;

        SaveBaseReq saveBaseReq = req.saveBaseReq();
        List<SaveDtlReq> saveDtlReqs = req.saveDtlReqs();

        UserSessionDvo session = SFLEXContextHolder.getContext().getUserSession(); //세션정보

        //환불요청기본
        WwdbRefundBaseDvo dvo = converter.mapTempSaveWwdbRefundBaseDvo(saveBaseReq);

        if ("03".equals(dvo.getProcsDv())) { //승인시
            for (SaveDtlReq list : saveDtlReqs) {

                WwdbRefundDtlDvo dtlDvo = converter.mapTempSaveWwdbRefundDtlDvo(list);

                ZwdbRefundApplicationReqDvo reqDvo = new ZwdbRefundApplicationReqDvo();
                reqDvo.setRfndDv("1");
                reqDvo.setKwGrpCoCd(session.getCompanyCode());
                reqDvo.setRfndCshAkAmt(dtlDvo.getRfndCshAkAmt()); //현금
                reqDvo.setRfndCardAkAmt(dtlDvo.getRfndCardAkAmt()); //카드
                reqDvo.setRfndBltfAkAmt(dtlDvo.getRfndBltfAkAmt()); //전금
                reqDvo.setRfndRveDt(dvo.getRveDt()); /*환불수납일자*/
                reqDvo.setRfndPerfDt(dvo.getPerfDt()); /*환불실적일자*/
                reqDvo.setRfndDsbDt(dvo.getDsbDt()); /*환불지급일자*/
                reqDvo.setRfndProcsCn(dvo.getRfndProcsCn()); /*환불처리내용*/
                reqDvo.setRfndDsbDt(dvo.getDsbDt()); /*환불예정일자*/
                reqDvo.setRfndAkNo(dvo.getRfndAkNo());
                reqDvo.setRfndRcpPhCd(dtlDvo.getRfndRcpPhCd()); // 환불접수경로코드
                reqDvo.setRfndDsbDuedt(dvo.getDsbDt()); // 환불완료일자
                reqDvo.setRfndRveDt(dvo.getRveDt()); // 환불접수일자
                reqDvo.setRfndPerfDt(dvo.getPerfDt()); // 환불실적일자
                reqDvo.setRfndDsbDt(dvo.getDsbDt()); // 환불지급일자
                reqDvo.setRfndProcsCn(dvo.getRfndProcsCn()); // 환불처리내용
                reqDvo.setCntrNo(dtlDvo.getCntrNo()); // 계약번호
                reqDvo.setCntrSn(dtlDvo.getCntrSn()); // 계약일련번호
                reqDvo.setRveNo(dtlDvo.getRveNo()); // 수납번호
                reqDvo.setRveSn(dtlDvo.getRveSn()); // 수납일련번호

                zwdbRefundApplicationService.createRefundApplication(reqDvo);

            }
        }

        //환불 상태값 변경
        processCount += mapper.updateRefundStatus(dvo);

        return processCount;
    }

}
