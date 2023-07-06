package com.kyowon.sms.wells.web.contract.changeorder.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import com.kyowon.sflex.common.message.dvo.KakaoSendReqDvo;
import com.kyowon.sflex.common.message.service.KakaoMessageService;
import com.kyowon.sms.wells.web.contract.changeorder.converter.WctbContractChangeMgtConverter;
import com.kyowon.sms.wells.web.contract.changeorder.dto.WctbContractChangeMngtDto;
import com.kyowon.sms.wells.web.contract.changeorder.dto.WctbContractChangeMngtDto.*;
import com.kyowon.sms.wells.web.contract.changeorder.dvo.WctbContractChangeDvo;
import com.kyowon.sms.wells.web.contract.changeorder.mapper.WctbContractChangeMgtMapper;
import com.kyowon.sms.wells.web.contract.common.dvo.WctzCntrChRcchStatChangeHistDvo;
import com.kyowon.sms.wells.web.contract.common.dvo.WctzContractChRcchStatChangeDtlHistDvo;
import com.kyowon.sms.wells.web.contract.common.service.WctzHistoryService;
import com.sds.sflex.common.utils.DateUtil;
import com.sds.sflex.common.utils.StringUtil;
import com.sds.sflex.system.config.context.SFLEXContextHolder;
import com.sds.sflex.system.config.core.dvo.UserSessionDvo;
import com.sds.sflex.system.config.core.service.MessageResourceService;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import com.sds.sflex.system.config.exception.BizException;
import com.sds.sflex.system.config.validation.BizAssert;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class WctbContractChangeMgtService {
    private final WctbContractChangeMgtMapper mapper;
    private final WctbContractChangeMgtConverter converter;
    private final MessageResourceService messageResourceService;
    private final KakaoMessageService kakaoMessageService;
    private final WctzHistoryService historyService;

    /*
    * 계약변경관리-조회
    * */
    public PagingResult<SearchContractChangeRes> getContractChangePages(
        SearchContractChangeReq dto, PageInfo pageInfo
    ) {
        return mapper.selectContractChanges(dto, pageInfo);

    }

    /*
    *
    * 계약변경관리-변경전 체크
    * */

    public FindBeforeChangeCheckRes getBeforeChangeCheck(FindBeforeChangeCheckReq dto) {
        WctbContractChangeDvo dvo = converter.findCheckReqToWctbContractChangeDvo(dto);

        String alncmpCd = dvo.getAlncmpCd(); // 제휴사코드
        String sellTpCd = dvo.getSellTpCd(); // 판매유형코드
        String sellTpDltCd = dvo.getSellTpDtlCd(); // 판매유형상세코드
        String mclsfRefPdClsfVal = dvo.getMclsfRefPdClsfVal();

        String cntrNo = dvo.getCntrNo(); // 계약번호
        int cntrSn = dvo.getCntrSn(); // 계약일련번호
        String inDv = dvo.getInDv(); // 처리구분
        String istDt = dvo.getIstDt(); // 설치일자
        String aprvDv = dvo.getAprvDv(); // 승인구분

        WctbContractChangeDvo checkDvo = mapper.selectCheckOgTpCd();

        String ogTpCd = checkDvo.getOgTpCd();
        String resYn = checkDvo.getResYn();

        BizAssert.isFalse(!"HR1".equals(ogTpCd) && "N".equals(resYn), "MSG_ALT_NO_AUTH"); // 권한이 없습니다.

        List<WctbContractChangeDvo> checkOrderList = mapper.selectCheckOrderChPrgs(cntrNo, cntrSn);

        WctbContractChangeDvo cntrOrderDvo = mapper.selectCntrOrderInfo(cntrNo, cntrSn);

        String pkgYn = cntrOrderDvo.getPkgYn(); // 패키지 주문
        String prmPtrmYn = cntrOrderDvo.getPrmPtrmYn(); // 선납
        String dpYn = cntrOrderDvo.getDpYn(); // 입금처리
        String ftfYn = cntrOrderDvo.getFtfYn(); // 비대면
        String cttRsCd = cntrOrderDvo.getCttRsCd(); // 컨택결과코드
        String canDt = cntrOrderDvo.getCanDt(); // 취소일자
        String onePlusOneYn = cntrOrderDvo.getOnePlusOneYn(); // 1+1여부
        String canPrgsStatCd = cntrOrderDvo.getCanPrgsStatCd(); // 취소진행상태코드
        String istBzsCd = cntrOrderDvo.getIstBzsCd(); //설치업체코드
        String rglrSppCntr = cntrOrderDvo.getRglrSppCntr(); // 정기배송 계약리스트
        String istPcsvTpCd = cntrOrderDvo.getIstPcsvTpCd(); // 설치택배구분

        String msg = ""; // 경고메세지
        String fstRgstUsrId = CollectionUtils.isEmpty(checkOrderList) ? "" : checkOrderList.get(0).getFstRgstUsrId();

        if (!"40".equals(inDv)) {
            //렌탈일때만 체크
            if ("2".equals(sellTpCd)) {

                BizAssert.isFalse(
                    CollectionUtils.isNotEmpty(checkOrderList), "MSG_ALT_CH_ORDER_IN_PROGRESS",
                    new String[] {fstRgstUsrId}
                ); // 주문변경이 진행중입니다.
                                                                                                                                            //계약상세번호기준으로 체크정보 조회

                if ("20".equals(inDv)) {
                    BizAssert.isFalse("Y".equals(pkgYn), "MSG_ALT_PKG_ORD_CANNOT_CHANGE_CNTR_TP"); // 패키지 주문은 계약유형변경 불가합니다.
                    BizAssert.isFalse("Y".equals(prmPtrmYn), "MSG_ALT_PRM_ORD_CANNOT_CHANGE_CNTR_TP"); // 선납 주문은 계약유형변경 불가합니다.
                    BizAssert.isFalse("Y".equals(dpYn), "MSG_ALT_DP_ORD_CANNOT_CHANGE"); // 입금 처리된 주문은 변경 불가합니다.
                    if ("Z95".equals(ogTpCd)) {
                        BizAssert.isFalse("Y".equals(ftfYn), "MSG_ALT_SODBT_NFTF_ORD_CANNT_CHANGE"); // 총판 비대면 접수 주문은 계약변경 불가합니다. 관리자에게 문의 하세요.
                    }
                }
                /* TODO: 변경수정 권한여부 확인(TOBE에서는 아직 변경권한 프로세스없음(2023.06.27) */
                if ("10".equals(inDv)) {

                }
            }
        } else {
            /* 취소요청 체크 처리 로직 */
            cntrOrderDvo.setCntrCnfmDt(dvo.getCntrCnfmDt());
            cntrOrderDvo.setIstDt(istDt);
            cntrOrderDvo.setAprvDv(aprvDv);
            cntrOrderDvo.setSellTpDtlCd(sellTpDltCd);
            cntrOrderDvo.setMclsfRefPdClsfVal(mclsfRefPdClsfVal);
            checkCancelable(cntrOrderDvo, checkOrderList);
        }
        FindBeforeChangeCheckRes res = FindBeforeChangeCheckRes.builder()
            .checkYn("Y")
            .warnMsg(msg)
            .build();
        return res;
    }

    /*
    * 취소요청 체크 처리 로직
    * */
    public String checkCancelable(
        WctbContractChangeDvo cntrOrderDvo, List<WctbContractChangeDvo> checkOrderList
    ) {
        String msg = ""; // 경고메세지

        String istDt = cntrOrderDvo.getIstDt(); // 설치일자
        String sellTpDltCd = cntrOrderDvo.getSellTpDtlCd(); // 판매유형상세코드
        String aprvDv = cntrOrderDvo.getAprvDv(); // 승인구분
        String mclsfRefPdClsfVal = cntrOrderDvo.getMclsfRefPdClsfVal();

        String pkgYn = cntrOrderDvo.getPkgYn(); // 패키지 주문
        String prmPtrmYn = cntrOrderDvo.getPrmPtrmYn(); // 선납
        String dpYn = cntrOrderDvo.getDpYn(); // 입금처리
        String ftfYn = cntrOrderDvo.getFtfYn(); // 비대면
        String cttRsCd = cntrOrderDvo.getCttRsCd(); // 컨택결과코드
        String canDt = cntrOrderDvo.getCanDt(); // 취소일자
        String onePlusOneYn = cntrOrderDvo.getOnePlusOneYn(); // 1+1여부
        String canPrgsStatCd = cntrOrderDvo.getCanPrgsStatCd(); // 취소진행상태코드
        String istBzsCd = cntrOrderDvo.getIstBzsCd(); //설치업체코드
        String rglrSppCntr = cntrOrderDvo.getRglrSppCntr(); // 정기배송 계약리스트
        String istPcsvTpCd = cntrOrderDvo.getIstPcsvTpCd(); // 설치택배구분

        String fstRgstUsrId = CollectionUtils.isEmpty(checkOrderList) ? "" : checkOrderList.get(0).getFstRgstUsrId();

        BizAssert.isFalse(
            !CollectionUtils.isEmpty(checkOrderList), "MSG_ALT_CH_ORDER_IN_PROGRESS",
            new String[] {fstRgstUsrId}
        ); // 주문변경이 진행중입니다.
        BizAssert.isFalse(ObjectUtils.isEmpty(cntrOrderDvo), "MSG_ALT_ORD_INF_NOT_FOUND"); // 주문정보를 찾을 수 없습니다.

        String cntrCnfmDt = cntrOrderDvo.getCntrCnfmDt();
        String cntrCnfmYm = cntrCnfmDt.substring(0, 6);

        String now = DateUtil.getNowDayString();
        BizAssert.isFalse(now.equals(cntrCnfmDt), "MSG_ALT_TOD_ORD_CAN_DEL_NO_APR"); // 당일접수 주문은 계약현황목록에서 승인 절차 없이 삭제 가능합니다.
        BizAssert.isFalse(now.substring(0, 6).equals(cntrCnfmYm), "MSG_ALT_ONLY_CAN_DEL_THM_ORD"); // 당월접수 주문만 삭제 가능합니다.

        BizAssert.isFalse(!StringUtil.isEmpty(istDt), "MSG_ALT_INST_ORD_CANNOT_DEL"); // 설치 완료된 주문은 삭제 불가합니다.
        BizAssert.isFalse("Y".equals(prmPtrmYn), "MSG_ALT_PRM_ORD_CANNOT_CHANGE_CNTR_TP"); // 선납 주문은 계약유형변경 불가합니다.
        BizAssert.isFalse("Y".equals(dpYn), "MSG_ALT_DP_ORD_CANNOT_CHANGE"); // 입금 처리된 주문은 변경 불가합니다.

        //TODO. 키위연동은 현재 미정
        String kiwi = "";

        if (List.of("24", "26").contains(sellTpDltCd) && StringUtil.isNotEmpty(istBzsCd)
            && StringUtil.isNotEmpty(cttRsCd)) {
            BizAssert.isFalse(Integer.parseInt(cttRsCd) < 90, "MSG_ALT_DEL_AFTER_CANC_BY_OTSC"); // 아웃소싱 업체 측에서 취소 후 삭제 가능 합니다.
        }
        BizAssert.isFalse(
            StringUtil.isNotEmpty(istBzsCd) && "2".equals(istPcsvTpCd) && "01".equals(cttRsCd),
            "MSG_ALT_OTSD_INST_PROD_PCSV_PROD_ORD_CANNOT_DEL", new String[] {cttRsCd}
        ); // 외부업체설치제품,택배상품에 대한 주문은 삭제 불가합니다.(컨택코드:01)
        BizAssert.isFalse(!StringUtil.isEmpty(canDt), "MSG_ALT_ALRDY_CANC_ORD"); // 취소 처리된 주문은 삭제가 불가 합니다.

        if ("Y".equals(onePlusOneYn)) {
            msg = messageResourceService.getMessage("MSG_ALT_1PLUS1_DSC_ORD_RESTORE_NTMF_RTLFE"); // 1+1할인으로 매칭된 주문입니다. 삭제하시면 익월 초 렌탈료 원복됩니다.
        }
        if ("Y".equals(pkgYn)) {
            msg = messageResourceService.getMessage("MSG_ALT_PKG_DSC_ORD_DEL_RESTORE_NTMF_RTLFE"); // 패키지 할인으로 매칭된 주문입니다. 삭제하시면 익월 초 렌탈료 원복됩니다.
        }

        if ("20".equals(aprvDv)) {
            BizAssert.isFalse("20".equals(canPrgsStatCd), "MSG_ALT_ARDY_DEL_REQ_STAT"); // 이미 삭제 요청된 상태입니다.
        }
        if ("60".equals(aprvDv)) {
            BizAssert.isFalse(StringUtil.isEmpty(canPrgsStatCd), "MSG_ALT_NO_DEL_REQ_INFO_FOUND"); // 삭제 요청 정보를 찾을 수 없습니다.
            BizAssert.isFalse("30".equals(canPrgsStatCd), "MSG_ALT_ARDY_RJ_STAT"); // 이미 반려 처리된 상태입니다.
        }
        if (List.of("24", "26").contains(sellTpDltCd)
            && "01003".equals(mclsfRefPdClsfVal)
            && "1".equals(istBzsCd)) {
            msg = messageResourceService.getMessage("MSG_ALT_RGLR_PKG_SPRTL_CANC_RCP_CCS", rglrSppCntr); // 정기배송 패키지 rglrSppCntr 은 고객센터로 별도 취소접수 하시길 바랍니다. (배송제품으로 동시 삭제 불가)
        }
        return msg;
    }

    /*
    *
    * 계약변경관리-취소요청
    * */

    @Transactional
    public int saveCancelAsks(SaveCancelReq dto) throws Exception {
        WctbContractChangeDvo dvo = converter.saveCancelReqToWctbContractChangeDvo(dto);

        String cntrNo = dvo.getCntrNo();
        int cntrSn = dvo.getCntrSn();

        String templateCode = "wells17950";
        int processCount = 0;

        /* 취소요청 체크 로직 한번더 실행*/
        WctbContractChangeDvo cntrOrderDvo = mapper.selectCntrOrderInfo(cntrNo, cntrSn);
        List<WctbContractChangeDvo> checkOrderList = mapper.selectCheckOrderChPrgs(cntrNo, cntrSn);

        /* 지점장 휴대전화번호*/
        String cralLocaraTno = StringUtils.defaultIfEmpty(cntrOrderDvo.getBrmgrCralLocaraTno(), "");
        String mexnoEncr = StringUtils.defaultIfEmpty(cntrOrderDvo.getBrmgrMexnoEncr(), "");
        String cralIdvTno = StringUtils.defaultIfEmpty(cntrOrderDvo.getBrmgrCralIdvTno(), "");
        String phoneNo = cralLocaraTno + mexnoEncr + cralIdvTno;

        String brmgrNm = cntrOrderDvo.getBrmgrNm();

        cntrOrderDvo.setCntrCnfmDt(dvo.getCntrCnfmDt());
        cntrOrderDvo.setIstDt(dvo.getIstDt());
        cntrOrderDvo.setAprvDv(dvo.getAprvDv());
        cntrOrderDvo.setSellTpDtlCd(dvo.getSellTpDtlCd());
        cntrOrderDvo.setMclsfRefPdClsfVal(dvo.getMclsfRefPdClsfVal());

        String msg = checkCancelable(cntrOrderDvo, checkOrderList);

        UserSessionDvo session = SFLEXContextHolder.getContext().getUserSession();
        String employeeUserId = session.getEmployeeIDNumber();

        String now = DateUtil.getNowString();
        String cntrChAkCn = "계약변경유형코드: 계약취소신청";

        dvo.setCntrChRcpDtm(now); // 계약변경접수일시
        dvo.setCntrChTpCd("402"); // 계약변경유형코드
        dvo.setChRqrDvCd("2"); // 변경요청자구분코드
        dvo.setChRqrNm(dvo.getCstKnm()); // 변경요청자명
        dvo.setCntrChAkCn(cntrChAkCn); // 계약변경요청내용
        dvo.setCntrChPrgsStatCd("20"); // 계약변경진행상태코드
        dvo.setChRcstDvCd(""); // 변경접수자구분코드
        dvo.setChRcpUsrId(employeeUserId); // 변경접수사용자id
        dvo.setCntrChFshDtm(now); // 계약변경완료일시
        dvo.setDtaDlYn("N"); // 데이터삭제여부

        int basRes = mapper.insertContractChRcpBase(dvo); // 계약변경접수기본

        BizAssert.isTrue(basRes > 0, "MSG_ALT_SVE_ERR");

        historyService.createContractChangeRcchStatChangeHistory(
            WctzCntrChRcchStatChangeHistDvo
                .builder()
                .cntrChRcpId(dvo.getCntrChRcpId())
                .cntrChPrgsStatCd(dvo.getCntrChPrgsStatCd())
                .build()
        );

        dvo.setCntrUnitTpCd("020"); // 계약단위유형코드
        dvo.setDtlCntrNo(cntrNo); // 상세계약번호
        dvo.setDtlCntrSn(cntrSn); // 상세계약일련번호
        dvo.setCntrChRsonDvCd(""); // 계약변경사유구분코드
        dvo.setCntrChRsonCd(""); // 계약변경사유코드
        dvo.setCntrChAtcDvCd(""); // 계약변경항목구분코드

        int dtlsRes = mapper.insertContractChRcpDetail(dvo); // 계약변경접수상세
        BizAssert.isTrue(dtlsRes > 0, "MSG_ALT_SVE_ERR");

        historyService.createContractChRcchChangeDtlHistory(
            WctzContractChRcchStatChangeDtlHistDvo
                .builder()
                .cntrChRcpId(dvo.getCntrChRcpId())
                .cntrChSn(Integer.parseInt(dvo.getCntrChSn()))
                .build()
        );

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("brmgrNm", brmgrNm); // 지점장명
        paramMap.put("prtnrKnm", dvo.getPrtnrKnm()); // 판매자명
        paramMap.put("cstKnm", dvo.getCstKnm()); // 고객명
        paramMap.put("rcpdt", dvo.getCntrCnfmDt()); // 접수일자
        paramMap.put("cntrDtlNo", dvo.getCntrDtlNo()); // 계약상세번호
        paramMap.put("cralIdvTno", phoneNo); // 전화번호
        paramMap.put("linkUrl", ""); // TODO: 링크 생성 시 추가예정

        if (StringUtil.isEmpty(phoneNo) || phoneNo.length() < 10) {
            throw new BizException("MSG_ALT_WRONG_DEL_REQ_APRV_PHON_NO"); // 삭제 요청 결재 담당자 휴대폰 전화번호가 올바르지 않습니다.
        }

        String destInfo = brmgrNm + "^" + phoneNo;

        KakaoSendReqDvo kakaoSendReqDvo = KakaoSendReqDvo.withTemplateCode()
            .templateCode(templateCode) // 카카오톡템플릿 ID
            .templateParamMap(paramMap)
            .destInfo(destInfo)
            .callback("15884113")
            .build();
        processCount += kakaoMessageService.sendMessage(kakaoSendReqDvo);

        return processCount;
    }

    public WctbContractChangeMngtDto.FindPartnerRes getPartnerByCntrNo(String cntrNo, String cntrSn) {
        // 계약변경관리-파트너 변경(조회)
        return mapper.selectPartnerByCntrNo(cntrNo, cntrSn);
    }

    @Transactional
    public int editPartner(WctbContractChangeMngtDto.EditPartnerReq dto) {
        // 계약변경관리-파트너 변경(저장)
        // 저장할 데이터 변환 DTO -> DVO
        WctbContractChangeDvo inputDvo = converter.mapEditPartnerReqToWctbContractChangeMngtDvo(dto);

        // 데이터의 INSERT/UPDATE/유효시작일시/유효종료일시를 일관되게 맞추기 위해, 미리 조회해온다.
        WctbContractChangeDvo dateTimeDvo = mapper.selectDateTime();
        inputDvo.setFstRgstDtm(dateTimeDvo.getFstRgstDtm());
        inputDvo.setFstRgstUsrId(dateTimeDvo.getFstRgstUsrId());
        inputDvo.setFstRgstPrgId(dateTimeDvo.getFstRgstPrgId());
        inputDvo.setFstRgstDeptId(dateTimeDvo.getFstRgstDeptId());
        inputDvo.setFnlMdfcDtm(dateTimeDvo.getFnlMdfcDtm());
        inputDvo.setFnlMdfcUsrId(dateTimeDvo.getFnlMdfcUsrId());
        inputDvo.setFnlMdfcPrgId(dateTimeDvo.getFnlMdfcPrgId());
        inputDvo.setFnlMdfcDeptId(dateTimeDvo.getFnlMdfcDeptId());

        int result;
        // 접수기본/상세, 계약기본에 승인, 변경 완료 상태로 처리한다.
        // 1. INSERT 계약변경접수기본
        result = mapper.insertSellPartnerToCntrChRcpBas(inputDvo);
        BizAssert.isTrue(result == 1, "MSG_ALT_SVE_ERR");

        // 2. INSERT 계약변경접수변경이력
        result = mapper.insertSellPartnerToCntrChRcchHist(inputDvo);
        BizAssert.isTrue(result == 1, "MSG_ALT_SVE_ERR");

        // 3. INSERT 계약변경접수상세
        result = mapper.insertSellPartnerToCntrChRcpDtl(inputDvo);
        BizAssert.isTrue(result > 0, "MSG_ALT_SVE_ERR");

        // 4. INSERT 계약변경접수상세이력
        result = mapper.insertSellPartnerToCntrChRcpDchHist(inputDvo);
        BizAssert.isTrue(result > 0, "MSG_ALT_SVE_ERR");

        // 5. UPDATE 계약기본
        result = mapper.updateSellPartnerToCntrBas(inputDvo);
        BizAssert.isTrue(result == 1, "MSG_ALT_SVE_ERR");

        // 6-1. UPDATE 계약변경이력
        result = mapper.updateExSellPartnerToCntrChHist(inputDvo);
        BizAssert.isTrue(result == 1, "MSG_ALT_SVE_ERR");

        // 6-2. INSERT 계약변경이력
        result = mapper.insertSellPartnerToCntrChHist(inputDvo);
        BizAssert.isTrue(result == 1, "MSG_ALT_SVE_ERR");

        // 7-1. UPDATE 계약파트너관계 유효종료일시
        result = mapper.updateSellPartnerToCntrPrtnrRel(inputDvo);
        BizAssert.isTrue(result > 0, "MSG_ALT_SVE_ERR");

        // 7-2. INSERT 계약파트너관계
        result = mapper.insertSellPartnerToCntrPrtnrRel(inputDvo);
        BizAssert.isTrue(result > 0, "MSG_ALT_SVE_ERR");

        return 1;
    }
}
