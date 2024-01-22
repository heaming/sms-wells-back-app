package com.kyowon.sms.wells.web.service.stock.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.contract.ordermgmt.service.WctaInstallationReqdDtInService;
import com.kyowon.sms.wells.web.service.stock.converter.WsnaPcsvOutOfStorageSaveConverter;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaPcsvOutOfStorageMgtDto.SaveReq;
import com.kyowon.sms.wells.web.service.stock.dvo.*;
import com.kyowon.sms.wells.web.service.stock.mapper.WsnaPcsvOutOfStorageSaveMapper;
import com.kyowon.sms.wells.web.service.stock.mapper.WsnaPcsvSendDtlMapper;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbIndividualVisitPrdDto;
import com.kyowon.sms.wells.web.service.visit.service.WsnbIndividualVisitPrdService;
import com.sds.sflex.common.utils.DateUtil;
import com.sds.sflex.system.config.core.service.MessageResourceService;
import com.sds.sflex.system.config.validation.BizAssert;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class WsnaPcsvOutOfStorageSaveService {

    private final WsnaPcsvOutOfStorageSaveMapper mapper;

    private final WsnaPcsvSendDtlMapper sendDtlMapper;

    private final WsnaPcsvOutOfStorageSaveConverter converter;

    private final MessageResourceService messageResourceService;

    private final WsnaItemStockItemizationService itemStockService;

    private final WsnaLogisticsOutStorageAskService logisticsOutStorageAskService;

    private final WctaInstallationReqdDtInService installationReqdDtInService;

    private final WsnbIndividualVisitPrdService visitPrdService;

    @Transactional
    public int savePcsvOutOfStorages(List<SaveReq> dtos) throws Exception {
        int processCount = 0;

        List<WsnaLogisticsOutStorageAskReqDvo> logisticDvos = new ArrayList<>();

        List<WsnaPcsvOutOfStorageSaveDvo> dvos = converter.mapSaveReqToPcsvOutOfStorageDvo(dtos);
        String lgstOstrAkNo = mapper.selectNewLgstOstrAkNo(); // 물류요청번호 생성

        // 택배 출고 저장 - 정상출고[1112] / 재배송출고[1113]
        for (WsnaPcsvOutOfStorageSaveDvo dvo : dvos) {
            if ("1112".equals(dvo.getSvBizDclsfCd())) {
                // --- 정상출고----

                // 0.동일 키값으로 결과가 저장되었는지 체크한다.
                int existCnt = mapper.selectExistSvpdCstSvWkRsIz(dvo);
                BizAssert.isTrue(existCnt == 0, "MSG_ALT_EXIST_FSH_WK_LIST_RTRY_CONF");

                // 1.배정테이블 업데이트
                mapper.updateSvpdCstSvasIstAsnIz(dvo);
                dvo.setSvProcsCn(messageResourceService.getMessage("MSG_ALT_INST_PCSV_OSTR_FSH")); //설치택배상품 출고완료

                // 2.작업결과 IU
                mapper.insertSvpdCstSvWkRsIz(dvo);

                // 3.작업엔지니어 정보를 구한다.
                WsnaPcsvOutOfStorageSaveDvo engineerDvo = mapper.selectEngineerOgbsMmPrtnrIz(dvo);
                if (engineerDvo != null) {
                    dvo.setMngrDvCd(engineerDvo.getMngrDvCd());
                    dvo.setDgr1LevlOgId(engineerDvo.getDgr1LevlOgId());
                    dvo.setDgr3LevlOgId(engineerDvo.getDgr3LevlOgId());
                    dvo.setBrchOgId(engineerDvo.getBrchOgId());
                }

                dvo.setLgstOstrAkNo(lgstOstrAkNo); // 물류요청번호

                String idvTno = dvo.getIdvTno(); // 전화번호
                String cralIdvTno = dvo.getCralIdvTno(); //휴대폰 번호

                // 4.상품 내역 등록 및 수불 처리 (물류)
                List<WsnaPcsvSendDtlDvo> pcsvSendDtlDvos = this.setWsnaPcsvSendDtlDvo(dvo);
                for (WsnaPcsvSendDtlDvo pcsvSendDtlDvo : pcsvSendDtlDvos) {
                    // 5.택배 발송정보 저장 (TB_SVPD_OSTR_AK_PCSV_SEND_DTL)
                    sendDtlMapper.insertPcsvSendDtl(pcsvSendDtlDvo);

                    dvo.setPdCd(pcsvSendDtlDvo.getItmPdCd());
                    dvo.setUseQty(pcsvSendDtlDvo.getOstrAkQty());

                    // 6.작업출고내역 등록 (TB_SVST_SV_WK_OSTR_IZ)
                    mapper.insertSvstSvWkOstrIz(dvo);

                    // 7.재고변경 (TB_SVST_CST_SV_ITM_STOC_IZ)
                    itemStockService.createStock(setWsnaItemStockItemizationReqDvo(pcsvSendDtlDvo));

                    // 8.택배정보 물류 연동을위한 매핑 저장 (물류 연동시 전화번호,휴대폰 번호 복호화 전송)
                    pcsvSendDtlDvo.setAdrsTnoVal(idvTno);
                    pcsvSendDtlDvo.setAdrsCphonNoVal(cralIdvTno);
                    logisticDvos.add(converter.mapPcsvOutOfStorageDvoToLogisticDvo(pcsvSendDtlDvo));
                }

                // 9.출고 확정시 일자(설치일자,배송예정일자) 현재날짜 지정 (판매시스템 연계)
                String sppDueDt = DateUtil.getNowDayString(); // 배송예정일자
                dvo.setIstDt(DateUtil.getNowDayString()); // 설치일자

                String cntrNo = dvo.getCntrNo();
                String cntrSn = dvo.getCntrSn();
                String istDt = dvo.getIstDt();

                int result = installationReqdDtInService.saveInstallReqdDt(cntrNo, cntrSn, istDt, "", sppDueDt);
                if (result > 0) {
                    mapper.updateSvpdCstSvExcnIz(dvo);
                }

                // 10.BS주기표 생성
                this.visitPrdService.processVisitPeriodRegen(this.setWsnbVisitPrdProcessReq(cntrNo, cntrSn, istDt));

                // 11.물류 인터페이스 연동
                if (ObjectUtils.isNotEmpty(logisticDvos)) {
                    logisticsOutStorageAskService.createSelfFilterOutOfStorageAsks(logisticDvos);
                }

            } else if ("1113".equals(dvo.getSvBizDclsfCd())) {
                // --- 재배송 출고----

                // 0.동일 키값으로 결과가 저장되었는지 체크한다.
                int existCnt = mapper.selectExistSvpdCstSvWkRsIz(dvo);
                BizAssert.isTrue(existCnt == 0, "MSG_ALT_EXIST_FSH_WK_LIST_RTRY_CONF");

                // 1.배정테이블 업데이트
                mapper.updateSvpdCstSvasIstAsnIz(dvo);

                // 2.작업결과 IU - [설치택배상품 재배송 출고완료]
                dvo.setSvProcsCn(messageResourceService.getMessage("MSG_ALT_INST_PCSV_OSTR_RSHP_FSH"));
                mapper.insertSvpdCstSvWkRsIz(dvo);

                // 3.반품요청오더가 미처리된게 있다면 수불 없이 완료 처리를 한다
                WsnaPcsvOutOfStorageSaveDvo returningDvo = mapper.selectReturningSvpdCstSvasIstOjIz(dvo);
                if (returningDvo != null) {
                    //CST_SV_ASN_NO, SV_BIZ_HCLSF_CD, WK_CAN_MO_CN 정의
                    dvo.setCstSvAsnNo(returningDvo.getCstSvAsnNo());
                    dvo.setSvBizHclsfCd(returningDvo.getSvBizHclsfCd());
                    dvo.setWkCanMoCn(returningDvo.getWkCanMoCn());

                    // 위치현상원인수당조회
                    WsnaPcsvOutOfStorageSaveDvo asCodeDvo = mapper.selectAsCodeSvpdCstSvasIstOjIz(dvo);
                    if (asCodeDvo != null) {
                        //AS_LCT_CD, AS_PHN_CD, AS_CAUS_CD, SITE_AW_ATC_CD 정의
                        dvo.setAsLctCd(asCodeDvo.getAsLctCd());
                        dvo.setAsPhnCd(asCodeDvo.getAsPhnCd());
                        dvo.setAsCausCd(asCodeDvo.getAsCausCd());
                        dvo.setSiteAwAtcCd(asCodeDvo.getSiteAwAtcCd());
                    }
                    // 4. 반품 배정번호를 기준으로 배정테이블 업데이트
                    mapper.updateSvpdCstSvasIstAsnIz(dvo); // 배정테이블 업데이트

                    // 5. 반품 배정번호를 기준으로 작업결과IU - [설치택배상품 재배송으로 인한 반품완료 처리(재고X)]
                    dvo.setSvProcsCn(messageResourceService.getMessage("MSG_ALT_INST_PCSV_OSTR_RSHP_RTNGD_FSH"));
                    mapper.insertSvpdCstSvWkRsIz(dvo);
                }
            } else if ("1410".equals(dvo.getSvBizDclsfCd())) {
                // --- 사은품상품 출고----

                // 0.동일 키값으로 결과가 저장되었는지 체크한다. [TB_SVPD_CST_SV_WK_RS_IZ]
                int existCnt = mapper.selectExistSvpdCstSvWkRsIz(dvo);
                BizAssert.isTrue(existCnt == 0, "MSG_ALT_EXIST_FSH_WK_LIST_RTRY_CONF");

                // 1.배정테이블 업데이트 [TB_SVPD_CST_SVAS_IST_ASN_IZ]
                mapper.updateSvpdCstSvasIstAsnIz(dvo);
                dvo.setSvProcsCn(messageResourceService.getMessage("MSG_ALT_INST_PCSV_OSTR_FSH")); //설치택배상품 출고완료

                // 2.작업결과 IU [TB_SVPD_CST_SV_WK_RS_IZ]
                mapper.insertSvpdCstSvWkRsIz(dvo);

                // 3.작업엔지니어 정보를 구한다. [TB_OGBS_MM_PRTNR_IZ]
                WsnaPcsvOutOfStorageSaveDvo engineerDvo = mapper.selectEngineerOgbsMmPrtnrIz(dvo);
                if (engineerDvo != null) {
                    dvo.setMngrDvCd(engineerDvo.getMngrDvCd());
                    dvo.setDgr1LevlOgId(engineerDvo.getDgr1LevlOgId());
                    dvo.setDgr3LevlOgId(engineerDvo.getDgr3LevlOgId());
                    dvo.setBrchOgId(engineerDvo.getBrchOgId());
                }

                dvo.setLgstOstrAkNo(lgstOstrAkNo); // 물류요청번호

                String idvTno = dvo.getIdvTno(); // 전화번호
                String cralIdvTno = dvo.getCralIdvTno(); //휴대폰 번호

                // 4.상품 내역 등록 및 수불 처리 (물류)
                List<WsnaPcsvSendDtlDvo> pcsvSendDtlDvos = this.setWsnaPcsvSendDtlDvo(dvo);
                for (WsnaPcsvSendDtlDvo pcsvSendDtlDvo : pcsvSendDtlDvos) {

                    /********************************************************************/
                    log.debug("pcsvSendDtlDvo = " + pcsvSendDtlDvo);

                    // 5.택배 발송정보 저장 (TB_SVPD_OSTR_AK_PCSV_SEND_DTL)
                    sendDtlMapper.insertPcsvSendDtl(pcsvSendDtlDvo);

                    dvo.setPdCd(pcsvSendDtlDvo.getItmPdCd());
                    dvo.setUseQty(pcsvSendDtlDvo.getOstrAkQty());

                    // 6.작업출고내역 등록 (TB_SVST_SV_WK_OSTR_IZ)
                    mapper.insertSvstSvWkOstrIz(dvo);

                    // 7.재고변경 (TB_SVST_CST_SV_ITM_STOC_IZ)
                    itemStockService.createStock(setWsnaItemStockItemizationReqDvo(pcsvSendDtlDvo));

                    // 8.택배정보 물류 연동을위한 매핑 저장 (물류 연동시 전화번호,휴대폰 번호 복호화 전송)
                    pcsvSendDtlDvo.setAdrsTnoVal(idvTno);
                    pcsvSendDtlDvo.setAdrsCphonNoVal(cralIdvTno);
                    logisticDvos.add(converter.mapPcsvOutOfStorageDvoToLogisticDvo(pcsvSendDtlDvo));
                }

                // 9.출고 확정시 일자(설치일자,배송예정일자) 현재날짜 지정 (판매시스템 연계) 생략

                // 10.BS주기표 생성 생략

                // 11.물류 인터페이스 연동
                if (ObjectUtils.isNotEmpty(logisticDvos)) {
                    logisticsOutStorageAskService.createSelfFilterOutOfStorageAsks(logisticDvos);
                }
            }
            processCount += 1;
        }

        return processCount;
    }

    @Transactional
    public int savePcsvOutOfStorageTest(List<SaveReq> dtos) {
        int processCount = 0;

        // 물류인터페이스 호출용 dvo
        List<WsnaLogisticsOutStorageAskReqDvo> logisticDvos = new ArrayList<>();

        List<WsnaPcsvOutOfStorageSaveDvo> dvos = converter.mapSaveReqToPcsvOutOfStorageDvo(dtos);
        // 물류요청번호 생성
        String lgstOstrAkNo = mapper.selectNewLgstOstrAkNo();
        for (WsnaPcsvOutOfStorageSaveDvo dvo : dvos) {
            if ("1112".equals(dvo.getSvBizDclsfCd())) { // 1112 : 제품배송
                dvo.setLgstOstrAkNo(lgstOstrAkNo); // 물류요청번호
                String idvTno = dvo.getIdvTno();
                String cralIdvTno = dvo.getCralIdvTno();

                List<WsnaPcsvSendDtlDvo> pcsvSendDtlDvos = this.setWsnaPcsvSendDtlDvo(dvo);

                for (WsnaPcsvSendDtlDvo pcsvSendDtlDvo : pcsvSendDtlDvos) {
                    // 1.택배 발송정보 저장 (TB_SVPD_OSTR_AK_PCSV_SEND_DTL)
                    sendDtlMapper.insertPcsvSendDtl(pcsvSendDtlDvo);

                    dvo.setPdCd(pcsvSendDtlDvo.getItmPdCd());
                    dvo.setUseQty(pcsvSendDtlDvo.getOstrAkQty());
                    // 2.작업출고내역 등록 (TB_SVST_SV_WK_OSTR_IZ)
                    mapper.insertSvstSvWkOstrIz(dvo);

                    // 3.재고변경 (TB_SVST_CST_SV_ITM_STOC_IZ)
                    itemStockService.createStock(setWsnaItemStockItemizationReqDvo(pcsvSendDtlDvo));

                    // 4. 택배정보 물류 연동을위한 매핑 저장 (TB_SVPD_OSTR_AK_PCSV_SEND_DTL)  (물류 연동시 전화번호,휴대폰 번호 복호화 전송)
                    pcsvSendDtlDvo.setAdrsTnoVal(idvTno);
                    pcsvSendDtlDvo.setAdrsCphonNoVal(cralIdvTno);
                    logisticDvos.add(converter.mapPcsvOutOfStorageDvoToLogisticDvo(pcsvSendDtlDvo));
                }
            }
        }

        // 7.물류 인터페이스 연동
        if (ObjectUtils.isNotEmpty(logisticDvos)) {
            // 물류인터페이스 호출
            logisticsOutStorageAskService.createSelfFilterOutOfStorageAsks(logisticDvos);
        }
        return processCount;
    }

    private List<WsnaPcsvSendDtlDvo> setWsnaPcsvSendDtlDvo(
        WsnaPcsvOutOfStorageSaveDvo vo
    ) {
        List<WsnaPcsvSendDtlDvo> sendDtlDvos = new ArrayList<>();
        // 출고요청 번호 생성
        WsnaPcsvSendDtlDvo sendDtlDvo = new WsnaPcsvSendDtlDvo();
        String now = DateUtil.getNowString();
        sendDtlDvo.setOstrAkNo(sendDtlMapper.selectOstAkNo());

        // 고정 셋팅
        sendDtlDvo.setOstrAkTpCd("400");
        sendDtlDvo.setSppDvCd("1");
        sendDtlDvo.setOstrAkRgstDt(now.substring(0, 8));
        sendDtlDvo.setOstrHopDt(now.substring(0, 8));
        sendDtlDvo.setAsnOjYm(now.substring(0, 6));
        sendDtlDvo.setIostAkDvCd("WE");
        sendDtlDvo.setLgstSppMthdCd("2");
        sendDtlDvo.setItmGdCd("A");

        // 창고정보  세팅
        sendDtlDvo.setOstrOjWareNo("100002");
        sendDtlDvo.setWareMngtPrtnrNo(vo.getWareMngtPrtnrNo());
        sendDtlDvo.setWareMngtPrtnrOgTpCd(vo.getWareMngtPrtnrOgTpCd());

        // 고객정보 파라미터 세팅
        sendDtlDvo.setCstSvAsnNo(vo.getCstSvAsnNo());
        sendDtlDvo.setCstNo(vo.getCntrCstNo());
        sendDtlDvo.setCstNm(vo.getRcgvpKnm());
        sendDtlDvo.setCntrNo(vo.getCntrNo());
        sendDtlDvo.setCntrSn(vo.getCntrSn());
        sendDtlDvo.setAdrsTnoVal(vo.getIdvTno());
        sendDtlDvo.setAdrsCphonNoVal(vo.getCralIdvTno());
        sendDtlDvo.setBasAdr(vo.getRnadr());
        sendDtlDvo.setDtlAdr(vo.getRdadr());
        sendDtlDvo.setZip(vo.getNewAdrZip());

        // 파라미터(물류작업방식코드,합포장일련번호,물류요청번호)
        sendDtlDvo.setLgstWkMthdCd(vo.getLgstWkMthdCd());
        sendDtlDvo.setMpacSn(vo.getMpacSn());
        sendDtlDvo.setLgstOstrAkNo(vo.getLgstOstrAkNo());

        // null대신 X값 세팅. (물류인터페이스요청)
        sendDtlDvo.setSvCnrCd("X");
        sendDtlDvo.setSvCnrNm("X");
        sendDtlDvo.setSvCnrIchrPrtnrNm("X");
        sendDtlDvo.setSvCnrLkTnoEncr("X");
        sendDtlDvo.setSvCnrAdr("X");
        sendDtlDvo.setOvivTpCd("X");

        //상품 정보 세팅
        List<WsnaPcsvOutOfStorageSaveProductDvo> products = vo.getProducts();
        if (CollectionUtils.isNotEmpty(products)) {
            int cnt = 1;
            for (WsnaPcsvOutOfStorageSaveProductDvo pdDvo : products) {
                WsnaPcsvSendDtlDvo sendDtlProductDvo = converter.mapPcsvSendDtlToPcsvSendDtl(sendDtlDvo);
                //상품 기준으로 출고요청일련번호 생성
                sendDtlProductDvo.setOstrAkSn(cnt);
                sendDtlProductDvo.setItmPdCd(pdDvo.getPdCd());
                sendDtlProductDvo.setOstrAkQty(pdDvo.getUseQty());
                sendDtlProductDvo.setPdCn(pdDvo.getPdNm() + "(" + pdDvo.getPdCd() + ")" + ": " + pdDvo.getUseQty());
                sendDtlDvos.add(sendDtlProductDvo);
                cnt++;
            }
        }
        return sendDtlDvos;
    }

    /* 재고변경 */
    private WsnaItemStockItemizationReqDvo setWsnaItemStockItemizationReqDvo(WsnaPcsvSendDtlDvo vo) {
        String nowDay = DateUtil.getNowDayString();

        WsnaItemStockItemizationReqDvo reqDvo = new WsnaItemStockItemizationReqDvo();
        reqDvo.setProcsYm(nowDay.substring(0, 6));
        reqDvo.setProcsDt(nowDay);
        reqDvo.setWareDv("1"); /*창고구분*/
        reqDvo.setWareNo(vo.getOstrOjWareNo());
        reqDvo.setWareMngtPrtnrNo(vo.getWareMngtPrtnrNo()); //파트너번호
        reqDvo.setItmPdCd(vo.getItmPdCd());
        reqDvo.setQty(String.valueOf(vo.getOstrAkQty()));
        reqDvo.setIostTp("213");
        reqDvo.setWorkDiv("A"); /*작업구분 workDiv*/
        reqDvo.setMngtUnit("10");
        reqDvo.setItemGd("A");

        return reqDvo;

    }

    /* BS 주기표  */
    private WsnbIndividualVisitPrdDto.SearchProcessReq setWsnbVisitPrdProcessReq(
        String cntrNo, String cntrSn, String istDt
    ) {
        WsnbIndividualVisitPrdDto.SearchProcessReq visitDto = new WsnbIndividualVisitPrdDto.SearchProcessReq(
            cntrNo,
            cntrSn,
            "",
            "",
            DateUtil.getNowDayString(),
            istDt,
            "",
            ""
        );
        return visitDto;
    }

}
