package com.kyowon.sms.wells.web.service.stock.service;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.contract.ordermgmt.service.WctaInstallationReqdDtInService;
import com.kyowon.sms.wells.web.service.stock.converter.WsnaPcsvOutOfStorageSaveConverter;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaMdProductOutOfStorageMgtDto.SaveReq;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaItemStockItemizationReqDvo;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaPcsvOutOfStorageSaveDvo;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaPcsvOutOfStorageSaveProductDvo;
import com.kyowon.sms.wells.web.service.stock.mapper.WsnaPcsvOutOfStorageSaveMapper;
import com.kyowon.sms.wells.web.service.visit.service.WsnbIndividualVisitPrdService;
import com.sds.sflex.common.utils.DateUtil;
import com.sds.sflex.system.config.core.service.MessageResourceService;
import com.sds.sflex.system.config.validation.BizAssert;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class WsnaMdProductOutOfStorageSaveService {

    private final WsnaPcsvOutOfStorageSaveMapper mapper;

    private final WsnaPcsvOutOfStorageSaveConverter converter;

    private final MessageResourceService messageResourceService;

    private final WsnaItemStockItemizationService itemStockService;

    private final WctaInstallationReqdDtInService installationReqdDtInService;

    private final WsnbIndividualVisitPrdService visitPrdService;

    @Transactional
    public int saveMdProductOutOfStorages(List<SaveReq> dtos) {
        int processCount = 0;

        // List<WsnaPcsvOutOfStorageSaveDvo> dvos = converter.mapSaveReqToPcsvOutOfStorageDvo(dtos);
        List<WsnaPcsvOutOfStorageSaveDvo> dvos = null;
        for (WsnaPcsvOutOfStorageSaveDvo dvo : dvos) {

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

            // 상품 목록
            List<WsnaPcsvOutOfStorageSaveProductDvo> products = dvo.getProducts();
            if (CollectionUtils.isNotEmpty(products)) {
                for (WsnaPcsvOutOfStorageSaveProductDvo pdDvo : products) {
                    // 4.작업출고내역 등록 (TB_SVST_SV_WK_OSTR_IZ)
                    mapper.insertSvstSvWkOstrIz(dvo);

                    // 5.재고변경 (TB_SVST_CST_SV_ITM_STOC_IZ)
                    itemStockService.createStock(setWsnaItemStockItemizationReqDvo(dvo));
                }
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

            processCount += 1;
        }

        return processCount;
    }

    /* 재고변경 */
    private WsnaItemStockItemizationReqDvo setWsnaItemStockItemizationReqDvo(WsnaPcsvOutOfStorageSaveDvo vo) {
        String nowDay = DateUtil.getNowDayString();

        WsnaItemStockItemizationReqDvo reqDvo = new WsnaItemStockItemizationReqDvo();
        reqDvo.setProcsYm(nowDay.substring(0, 6));
        reqDvo.setProcsDt(nowDay);
        reqDvo.setWareDv("1"); /*창고구분*/
        reqDvo.setWareNo("100002");
        reqDvo.setWareMngtPrtnrNo(vo.getWareMngtPrtnrNo()); //파트너번호
        reqDvo.setItmPdCd(vo.getPdCd());
        reqDvo.setQty(String.valueOf(vo.getUseQty()));
        reqDvo.setIostTp("213");
        reqDvo.setWorkDiv("A"); /*작업구분 workDiv*/
        reqDvo.setMngtUnit("10");
        reqDvo.setItemGd("A");

        return reqDvo;

    }

}
