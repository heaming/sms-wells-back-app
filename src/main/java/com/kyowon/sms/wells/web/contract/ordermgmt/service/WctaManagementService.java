package com.kyowon.sms.wells.web.contract.ordermgmt.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.contract.ordermgmt.converter.WctaManagementConverter;
import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaManagementDto.*;
import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.WctaDfntAckdReqDvo;
import com.kyowon.sms.wells.web.contract.ordermgmt.mapper.WctaManagementMapper;
import com.sds.sflex.common.utils.StringUtil;
import com.sds.sflex.system.config.exception.BizException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class WctaManagementService {
    private final WctaManagementMapper mapper;
    private final WctaManagementConverter converter;
    WctaDfntAckdReqDvo paramMap = new WctaDfntAckdReqDvo();

    public SearchRes getContractManagements(SearchKssOrdrListReq dto) {
        if (Arrays.asList(new String[] {"A", "N", "U"}).contains(dto.cntrDv())) {
            List<SearchKssOrdrListRes> searchKssOrdrListResList = mapper.selectKssOrdrList(dto);
            return new SearchRes(searchKssOrdrListResList, null, null);
        } else if (dto.cntrDv().equals("R")) {
            List<SearchRePromConcListRes> searchRePromConcListResList = mapper.selectRePromConcList(dto);
            return new SearchRes(null, searchRePromConcListResList, null);
        } else if (dto.cntrDv().equals("S")) {
            List<SearchEmployeePurchaseListRes> searchEmployeePurchaseListResList = mapper
                .selectEmployeePurchaseList(dto);
            return new SearchRes(null, null, searchEmployeePurchaseListResList);
        }
        return null;
    }

    public SearchCntrDtlRes getContractMngtDtls(SearchLspyOrdrDtptListReq dto) {
        if (dto.cntrwTpCd().equals("1")) { // 일시불(환경가전)
            List<SearchLspyOrdrDtptListRes> searchLspyOrdrDtptListResList = converter
                .mapWctaLspyOrdrDtptListDvoToSearchLspyOrdrDtptListRes(mapper.selectLspyOrdrDtptList(dto));
            return new SearchCntrDtlRes(searchLspyOrdrDtptListResList, null, null, null, null, null, null);
        } else if (dto.cntrwTpCd().equals("2")) { // 일시불(BH)
            List<SearchBhOrdrDtptListRes> searchBhOrdrDtptListResList = converter
                .mapWctaBhOrdrDtptListDvoToSearchBhOrdrDtptListRes(mapper.selectBhOrdrDtptList(dto));
            return new SearchCntrDtlRes(null, searchBhOrdrDtptListResList, null, null, null, null, null);
        } else if (dto.cntrwTpCd().equals("3") || dto.cntrwTpCd().equals("8")) { // 렌탈/장기할부
            List<SearchRentOrdrDtptListRes> searchRentOrdrDtptListResList = null;
            if (dto.cntrwTpCd().equals("3")) { // 렌탈
                searchRentOrdrDtptListResList = converter
                    .mapWctaRentOrdrDtptListDvoToSearchRentOrdrDtptListRes(mapper.selectRentOrdrDtptList(dto));
            } else if (dto.cntrwTpCd().equals("8")) { // 장기할부
                searchRentOrdrDtptListResList = converter
                    .mapWctaRentOrdrDtptListDvoToSearchRentOrdrDtptListRes(mapper.selectLtmIstmOrdrDtptList(dto));
            }
            return new SearchCntrDtlRes(null, null, searchRentOrdrDtptListResList, null, null, null, null);
        } else if (dto.cntrwTpCd().equals("4")) { // 멤버십
            List<SearchMbOrdrDtptListRes> searchMbOrdrDtptListResList = converter
                .mapWctaMbOrdrDtptListDvoToSearchMbOrdrDtptListRes(mapper.selectMbOrdrDtptList(dto));
            return new SearchCntrDtlRes(null, null, null, searchMbOrdrDtptListResList, null, null, null);
        } else if (dto.cntrwTpCd().equals("5")) { // 홈케어서비스
            List<SearchHcsOrdrDtptListRes> searchHcsOrdrDtptListResList = converter
                .mapWctaHcsOrdrDtptListDvoToSearchHcsOrdrDtptListRes(mapper.selectHcsOrdrDtptList(dto));
            return new SearchCntrDtlRes(null, null, null, null, searchHcsOrdrDtptListResList, null, null);
        } else if (dto.cntrwTpCd().equals("6")) { // 모종 일시불
            List<SearchPlantsOrdrDtptListRes> searchPlantsOrdrDtptListResList = converter
                .mapWctaPlantsOrdrDtptListDvoToSearchPlantsOrdrDtptListRes(mapper.selectPlantsOrdrDtptList(dto));
            return new SearchCntrDtlRes(null, null, null, null, null, searchPlantsOrdrDtptListResList, null);
        } else if (dto.cntrwTpCd().equals("7")) { // 정기배송
            List<SearchRglrDlvrOrdrDtptListRes> searchRglrDlvrOrdrDtptListResList = converter
                .mapWctaRglrDlvrOrdrDtptListDvoToSearchRglrDlvrOrdrDtptListRes(mapper.selectRglrDlvrOrdrDtptList(dto));
            return new SearchCntrDtlRes(null, null, null, null, null, null, searchRglrDlvrOrdrDtptListResList);
        }
        return null;
    }

    @Transactional
    public int saveConfirmApprovals(List<SaveConfirmApprovalsReq> dtos) {
        int processCount = 0;
        boolean isPymnSkip = false;

        String cntrNo = dtos.get(0).cntrNo();
        String cntrSn = dtos.get(0).cntrSn();
        List<SearchOrdrInfo4ReqDfntRes> ordrInfo4ReqDfntList = mapper.selectOrdrInfo4ReqDfntList(cntrNo, cntrSn);
        //비대면(30) && 계약서구분(3:렌탈,8:장기할부) && 조직유형코드:W05(Z95)
        if (StringUtil.isNotEmpty(ordrInfo4ReqDfntList.get(0).cstStlmInMthCd())) {
            if (ordrInfo4ReqDfntList.get(0).cstStlmInMthCd().equals("30")
                && Arrays.asList(new String[] {"3", "8"}).contains(ordrInfo4ReqDfntList.get(0).cntrwTpCd())
                && ordrInfo4ReqDfntList.get(0).sellOgTpCd().equals("W05")) {
                isPymnSkip = true;
            }

            //비대면(30) && 판매유입채널상세코드 :직원구매(9020) && 판매유입채널상세코드:아웃바운드(SalesTM)
            if (ordrInfo4ReqDfntList.get(0).cstStlmInMthCd().equals("30")
                && Arrays.asList(new String[] {"9020", "3010"})
                    .contains(ordrInfo4ReqDfntList.get(0).sellInflwChnlDtlCd())) {
                isPymnSkip = true;
            }
        }

        if (ordrInfo4ReqDfntList.size() == 0) {
            throw new BizException("MSG_ALT_ORD_INF_NOT_CONF" + "\n" + "MSG_ALT_CNFM_APR_CANT"); // 주문정보를 확인할 수 없습니다.\n확정승인을 할 수 없습니다.
        }

        //log.debug("cntrPrgsStatCd : {}", ordrInfo4ReqDfntList.get(0).cntrPrgsStatCd());
        //계약진행상태코드 결제완료 인지 여부 체크
        if (!ordrInfo4ReqDfntList.get(0).cntrPrgsStatCd().equals("50") && !isPymnSkip) {
            throw new BizException("MSG_ALT_STLM_FSH_STAT_CAN_ONLY_APR"); // 결제 완료 상태에서만 승인 할 수 있습니다.
        }

        paramMap.setCntrNo(dtos.get(0).cntrNo());
        paramMap.setIsCurReq("Y");
        paramMap.setCancYn("N");
        paramMap.setAprvYn("N");
        paramMap.setRecvId("");

        List<SearchDfntAckdReqListRes> dfntAckdReqList = mapper.selectDfntAckdReqList(paramMap);
        if (dfntAckdReqList.size() == 0) {
            throw new BizException("MSG_ALT_NOT_CNFM_APR_ICHR_ORD_AND_AK_IZ_CONF"); // 확정 승인 담당 주문이 아닙니다. 요청 내역을 확인해주세요.
        }
        for (int i = 0; i < dfntAckdReqList.size(); i++) {
            String cntrAprId = dfntAckdReqList.get(i).cntrAprId();
            processCount += mapper.updateAprvDfntAckdReq(cntrAprId);
            if (processCount != 1) {
                throw new BizException("MSG_ALT_CNFM_AK_APR_PROCS_ERR"); // 확정 요청 승인 처리중 오류(건수<>1)
            }
        }

        //전체 체크 확인(미승인 확정 요청건 조회)
        List<SearchDfntAckdReqListRes> dfntAckdRsltList = mapper.selectDfntAckdReqList(paramMap);
        if (dfntAckdRsltList.size() == 0) {
            processCount = 0;
        } else {
            processCount = dfntAckdRsltList.size();
        }
        return processCount;
    }

    public String saveAckdCnptMsg(List<SaveConfirmApprovalsReq> dtos) {
        String ackdCnptMsg = "== 확정 승인 전 확인 메시지 ==\n"; // 승인 확인 메시지
        boolean gotoNextIndex = false;
        boolean isPymnSkip = false;

        String cntrNo = dtos.get(0).cntrNo();
        String cntrSn = dtos.get(0).cntrSn();
        List<SearchOrdrInfo4ReqDfntRes> ordrInfo4ReqDfntList = mapper.selectOrdrInfo4ReqDfntList(cntrNo, cntrSn);
        //비대면(30) && 계약서구분(3:렌탈,8:장기할부) && 조직유형코드:W05(Z95)
        if (StringUtil.isNotEmpty(ordrInfo4ReqDfntList.get(0).cstStlmInMthCd())) {
            if (ordrInfo4ReqDfntList.get(0).cstStlmInMthCd().equals("30")
                && Arrays.asList(new String[] {"3", "8"}).contains(ordrInfo4ReqDfntList.get(0).cntrwTpCd())
                && ordrInfo4ReqDfntList.get(0).sellOgTpCd().equals("W05")) {
                isPymnSkip = true;
            }

            //비대면(30) && 판매유입채널상세코드 :직원구매(9020) && 판매유입채널상세코드:아웃바운드(SalesTM)
            if (ordrInfo4ReqDfntList.get(0).cstStlmInMthCd().equals("30")
                && Arrays.asList(new String[] {"9020", "3010"})
                    .contains(ordrInfo4ReqDfntList.get(0).sellInflwChnlDtlCd())) {
                isPymnSkip = true;
            }
        }

        if (ordrInfo4ReqDfntList.size() == 0) {
            throw new BizException("MSG_ALT_ORD_INF_NOT_CONF" + "\n" + "MSG_ALT_CNFM_APR_CANT"); // 주문정보를 확인할 수 없습니다.\n확정승인을 할 수 없습니다.
        }
        log.debug("cntrPrgsStatCd : {}", ordrInfo4ReqDfntList.get(0).cntrPrgsStatCd());
        //계약진행상태코드 결제완료 인지 여부 체크
        if (!ordrInfo4ReqDfntList.get(0).cntrPrgsStatCd().equals("50") && !isPymnSkip) {
            throw new BizException("MSG_ALT_STLM_FSH_STAT_CAN_ONLY_APR"); // 결제 완료 상태에서만 승인 할 수 있습니다.
        }

        paramMap.setCntrNo(dtos.get(0).cntrNo());
        paramMap.setIsCurReq("Y");
        paramMap.setCancYn("N");
        paramMap.setAprvYn("N");
        paramMap.setRecvId("");

        List<SearchDfntAckdReqListRes> dfntAckdReqList = mapper.selectDfntAckdReqList(paramMap);
        if (dfntAckdReqList.size() == 0) {
            throw new BizException("MSG_ALT_NOT_CNFM_APR_ICHR_ORD_AND_AK_IZ_CONF"); // 확정 승인 담당 주문이 아닙니다. 요청 내역을 확인해주세요.
        }
        for (int i = 0; i < dfntAckdReqList.size(); i++) {
            gotoNextIndex = false;
            log.info("[{}] {}", i + 1, dfntAckdReqList.get(i).ackdCnftMsg());
            if (dfntAckdReqList.get(i).aprvYn().equals("Y")) {
                gotoNextIndex = true; // 이미승인된 자료는 스킵
            } else {
                if (!StringUtil.isEmpty(ackdCnptMsg))
                    ackdCnptMsg += "\n";
                ackdCnptMsg += String.format(
                    castString(dfntAckdReqList.get(i).ackdCnftMsg()),
                    new String[] {castString(dfntAckdReqList.get(i).cntrAprAkDvCdNm())}
                );
                gotoNextIndex = true;
            }
        }
        return ackdCnptMsg;
    }

    /**
     *
     * Obejct Type 을 String Type으로 Casting하여 Return한다.
     *
     * @param value
     * @return java.lang.String
     */
    public static String castString(Object value) {
        String out = null;
        if (value == null || "".equals(value)) {
            out = "";
        } else {
            out = value.toString();
        }
        return out;
    }
}
