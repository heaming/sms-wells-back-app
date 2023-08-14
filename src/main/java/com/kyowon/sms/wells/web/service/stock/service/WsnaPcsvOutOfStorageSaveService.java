package com.kyowon.sms.wells.web.service.stock.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.contract.ordermgmt.service.WctaInstallationReqdDtInService;
import com.kyowon.sms.wells.web.service.stock.converter.WsnaPcsvOutOfStorageSaveConverter;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaPcsvOutOfStorageMgtDto.SaveReq;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaLogisticsOutStorageAskReqDvo;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaPcsvOutOfStorageSaveDvo;
import com.kyowon.sms.wells.web.service.stock.mapper.WsnaPcsvOutOfStorageSaveMapper;
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

    private final WsnaPcsvOutOfStorageSaveConverter converter;

    private final MessageResourceService messageResourceService;

    private final WctaInstallationReqdDtInService installationReqdDtInService;

    private final WsnaItemStockItemizationService itemStockservice;

    @Transactional
    public int savePcsvOutOfStorage(List<SaveReq> dtos) {
        int processCount = 0;

        // 물류인터페이스 호출용 dvo
        List<WsnaLogisticsOutStorageAskReqDvo> logisticDvos = new ArrayList<>();

        // 1. 출고 정보 저장 (정상출고/재배송출고)
        List<WsnaPcsvOutOfStorageSaveDvo> dvos = converter.mapSaveReqToPcsvOutOfStorageDvo(dtos);
        for (WsnaPcsvOutOfStorageSaveDvo dvo : dvos) {
            if ("1112".equals(dvo.getSvBizDclsfCd())) {
                /* 정상출고 */
                //동일 키값으로 결과가 저장되었는지 체크한다.
                int existCnt = mapper.selectExistSvpdCstSvWkRsIz(dvo);
                BizAssert.isTrue(existCnt == 0, "MSG_ALT_EXIST_FSH_WK_LIST_RTRY_CONF");

                // 배정테이블 업데이트
                mapper.updateSvpdCstSvasIstAsnIz(dvo);
                dvo.setSvProcsCn(messageResourceService.getMessage("MSG_ALT_INST_PCSV_OSTR_FSH")); //설치택배상품 출고완료

                // 작업결과 IU
                mapper.insertSvpdCstSvWkRsIz(dvo);

                //TODO TB_SVPD_CST_SVAS_IST_ASN_HIST 로그 저장

                //TODO 웰컴BS 생성
                //23.03.14 이경호 파트장님요청, 렌탈건은 웰컴 BS 생성
                //23.06.03 백현아 파트장님요청, 일시불 판매코드 4572건은 무조건 웰컴 BS 생성
                //mapper.deleteSvpdCstSvRgbsprIz(dvo);
                //mapper.insertSvpdCstSvRgbsprIz(dvo);

                //사용내역 IU
                mapper.insertSvstSvWkOstrIz(dvo);

                //출고 확정시 일자(설치일자,배송예정일자) 현재날짜 지정 (판매시스템 연계)
                String istDt = DateUtil.getNowDayString();
                String sppDueDt = DateUtil.getNowDayString();
                int result = installationReqdDtInService
                    .saveInstallReqdDt(dvo.getCntrNo(), dvo.getCntrSn(), istDt, "", sppDueDt);
                if (result > 0) {
                    mapper.updateSvpdCstSvExcnIz(dvo);
                }
                //TODO 수불처리
                //
                //                /* 수불처리 */
                //                //작업엔지니어 정보를 구한다.
                //                WsnaPcsvOutOfStorageSaveDvo engineerDvo = mapper.selectEngineerOgbsMmPrtnrIz(dvo);
                //                if (engineerDvo != null) {
                //                    dvo.setMngrDvCd(engineerDvo.getMngrDvCd());
                //                    dvo.setDgr1LevlOgId(engineerDvo.getDgr1LevlOgId());
                //                    dvo.setDgr3LevlOgId(engineerDvo.getDgr3LevlOgId());
                //                    dvo.setBrchOgId(engineerDvo.getBrchOgId());
                //                }
                //
                //                // 수불 오류 방지를 위해서 재고 수량 체크
                //                int itmStocIzQty = 0;
                //                WsnaPcsvOutOfStorageSaveDvo qtyDvo = mapper.selectQtySvstCstSvItmStocIz(dvo);
                //                if (qtyDvo != null) {
                //                    if ("A".equals(dvo.getPdGdCd())) {
                //                        itmStocIzQty = qtyDvo.getAQty();
                //                    } else if ("B".equals(dvo.getPdGdCd())) {
                //                        itmStocIzQty = qtyDvo.getBQty();
                //                    } else if ("E".equals(dvo.getPdGdCd())) {
                //                        itmStocIzQty = qtyDvo.getEQty();
                //                    } else if ("R".equals(dvo.getPdGdCd())) {
                //                        itmStocIzQty = qtyDvo.getRQty();
                //                    }
                //                }
                //                BizAssert.isFalse(itmStocIzQty < 0, "MSG_ALT_MAT_QTY_ERR", new String[] {""}); //자재 수량이 부족합니다.보유 자재를 확인해주세요!
                //            WsnaItemStockItemizationReqDvo itemDvo = setPcsvOstrWsnaItemStockItemizationDtoSaveReq(dvo);
                //            log.info("itemDvo qty---> {} ", itemDvo.getQty());
                //            log.info("itemDvo itemPdCd---> {} ", itemDvo.getItmPdCd());
                //            log.info("itemDvo WareNo ---> {} ", itemDvo.getWareNo());
                //            log.info("itemDvo WareMngtPrtnrNo---> {} ", itemDvo.getWareMngtPrtnrNo());
                //            try {
                //                itemStockservice.createStock(itemDvo);
                //            } catch (ParseException e) {
                //                throw new RuntimeException(e);
                //            }

                // throw new BizException("정상출고 에러!");

            } else if ("1113".equals(dvo.getSvBizDclsfCd())) {
                /* 재배송출고 */

                //동일 키값으로 결과가 저장되었는지 체크한다.
                int existCnt = mapper.selectExistSvpdCstSvWkRsIz(dvo);
                BizAssert.isTrue(existCnt == 0, "MSG_ALT_EXIST_FSH_WK_LIST_RTRY_CONF");
                // 배정테이블 업데이트
                mapper.updateSvpdCstSvasIstAsnIz(dvo);

                //작업결과 IU
                dvo.setSvProcsCn(messageResourceService.getMessage("MSG_ALT_INST_PCSV_OSTR_RSHP_FSH")); //설치택배상품 재배송 출고완료
                mapper.insertSvpdCstSvWkRsIz(dvo);

                //TODO TB_SVPD_CST_SVAS_IST_ASN_HIST 로그 저장

                //반품요청오더가 미처리된게 있다면 수불 없이 완료 처리를 한다
                //반품 요청 오더 (미처리) 정보가 존재하는경우
                WsnaPcsvOutOfStorageSaveDvo returningDvo = mapper.selectReturningSvpdCstSvasIstOjIz(dvo);
                if (returningDvo != null) {
                    //CST_SV_ASN_NO, SV_BIZ_HCLSF_CD, WK_CAN_MO_CN 정의
                    dvo.setCstSvAsnNo(returningDvo.getCstSvAsnNo());
                    dvo.setSvBizHclsfCd(returningDvo.getSvBizHclsfCd());
                    dvo.setWkCanMoCn(returningDvo.getWkCanMoCn());

                    //위치현상원인수당조회
                    WsnaPcsvOutOfStorageSaveDvo asCodeDvo = mapper.selectAsCodeSvpdCstSvasIstOjIz(dvo);
                    if (asCodeDvo != null) {
                        //AS_LCT_CD, AS_PHN_CD, AS_CAUS_CD, SITE_AW_ATC_CD 정의
                        dvo.setAsLctCd(asCodeDvo.getAsLctCd());
                        dvo.setAsPhnCd(asCodeDvo.getAsPhnCd());
                        dvo.setAsCausCd(asCodeDvo.getAsCausCd());
                        dvo.setSiteAwAtcCd(asCodeDvo.getSiteAwAtcCd());
                    }
                    mapper.updateSvpdCstSvasIstAsnIz(dvo); // 배정테이블 업데이트

                    //반품 배정번호를 기준으로 작업결과IU
                    dvo.setSvProcsCn(messageResourceService.getMessage("MSG_ALT_INST_PCSV_OSTR_RSHP_RTNGD_FSH")); //설치택배상품 재배송으로 인한 반품완료 처리(재고X)
                    mapper.insertSvpdCstSvWkRsIz(dvo);

                    //TODO TB_SVPD_CST_SVAS_IST_ASN_HIST 로그 저장(반품 오더기준)
                }

                //throw new BizException("재배송출고 에러!");

            }

            logisticDvos.add(converter.mapPcsvOutOfStorageDvoToLogisticDvo(dvo));
            processCount += 1;
        }

        return processCount;

    }

    private WsnaLogisticsOutStorageAskReqDvo setWsnaPcsvOutOfStorageSaveDvo(
        WsnaPcsvOutOfStorageSaveDvo vo
    ) {
        WsnaLogisticsOutStorageAskReqDvo logisticsAskReqDvo = new WsnaLogisticsOutStorageAskReqDvo();
        String now = DateUtil.getNowString();

        // 물류인터페이스호출용 값 세팅
        logisticsAskReqDvo.setOstrAkTpCd("400");
        logisticsAskReqDvo.setOstrAkRgstDt(now.substring(0, 8));
        logisticsAskReqDvo.setIostAkDvCd("WE");
        logisticsAskReqDvo.setMpacSn(0);
        logisticsAskReqDvo.setLgstSppMthdCd("2");
        logisticsAskReqDvo.setLgstWkMthdCd("WE01");
        logisticsAskReqDvo.setWareMngtPrtnrNo("71321");
        logisticsAskReqDvo.setWareMngtPrtnrOgTpCd("@7132");
        logisticsAskReqDvo.setOstrOjWareNo("100002");
        logisticsAskReqDvo.setItmGdCd("A");
        // 고객정보 파라미터 세팅
        logisticsAskReqDvo.setCstNm(vo.getRcgvpKnm());
        logisticsAskReqDvo.setAdrsTnoVal(vo.getIdvTno());
        logisticsAskReqDvo.setAdrsCphonNoVal(vo.getCralIdvTno());

        // 입고요청창고정보. 일단 파주물류센터로 세팅. TODO : 확인필요.
        logisticsAskReqDvo.setStrOjWareNo("100002"); //입고대상창고번호
        logisticsAskReqDvo.setWareDtlDvCd("10");
        logisticsAskReqDvo.setWareNm("교원파주물류센터");
        // null대신 X값 세팅. (물류인터페이스요청)
        logisticsAskReqDvo.setSvCnrCd("X");
        logisticsAskReqDvo.setSvCnrNm("X");
        logisticsAskReqDvo.setSvCnrIchrPrtnrNm("X");
        logisticsAskReqDvo.setSvCnrLkTnoEncr("X");
        logisticsAskReqDvo.setSvCnrAdr("X");
        logisticsAskReqDvo.setOvivTpCd("X");

        return logisticsAskReqDvo;
    }
    //    private WsnaItemStockItemizationReqDvo setPcsvOstrWsnaItemStockItemizationDtoSaveReq(
    //        WsnaPcsvOutOfStorageSaveDvo vo
    //    ) {
    //        String nowDay = DateUtil.getNowDayString();
    //
    //        WsnaItemStockItemizationReqDvo reqDvo = new WsnaItemStockItemizationReqDvo();
    //        reqDvo.setProcsYm(nowDay.substring(0, 6));
    //        reqDvo.setProcsDt(nowDay);
    //        reqDvo.setWareDv(vo.getWkWareNo().substring(0, 1)); /*창고구분*/
    //        reqDvo.setWareNo(vo.getWkWareNo());
    //        reqDvo.setWareMngtPrtnrNo(vo.getWareMngtPrtnrNo()); //파트너번호
    //        reqDvo.setItmPdCd(vo.getPdCd());
    //        reqDvo.setQty(vo.getUseQty());
    //        reqDvo.setIostTp("213");
    //        reqDvo.setWorkDiv("A"); /*작업구분 workDiv*/
    //        reqDvo.setMngtUnit("1");
    //        reqDvo.setItemGd("A");
    //        return reqDvo;
    //    }

}
