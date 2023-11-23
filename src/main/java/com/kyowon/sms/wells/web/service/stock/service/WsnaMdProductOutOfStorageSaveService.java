package com.kyowon.sms.wells.web.service.stock.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.contract.ordermgmt.service.WctaInstallationReqdDtInService;
import com.kyowon.sms.wells.web.service.stock.converter.WsnaMdProductOutOfStorageSaveConverter;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaMdProductOutOfStorageMgtDto.RemoveReq;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaMdProductOutOfStorageMgtDto.SaveReq;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaItemStockItemizationReqDvo;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaMdProdcutOutOfStorageSaveDvo;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaMdProductOutOfStorageSaveProductDvo;
import com.kyowon.sms.wells.web.service.stock.mapper.WsnaMdProductOutOfStorageSaveMapper;
import com.sds.sflex.common.utils.DateUtil;
import com.sds.sflex.system.config.core.service.MessageResourceService;
import com.sds.sflex.system.config.validation.BizAssert;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class WsnaMdProductOutOfStorageSaveService {

    private final WsnaMdProductOutOfStorageSaveMapper mapper;

    private final WsnaMdProductOutOfStorageSaveConverter converter;

    private final MessageResourceService messageResourceService;

    private final WctaInstallationReqdDtInService installationReqdDtInService;

    private final WsnaItemStockItemizationService itemStockService;

    @Transactional
    public int saveMdProductOutOfStorages(List<SaveReq> dtos) {
        int processCount = 0;

        List<WsnaMdProdcutOutOfStorageSaveDvo> dvos = converter.mapSaveReqToMdProductOutOfStorageSaveDvo(dtos);
        for (WsnaMdProdcutOutOfStorageSaveDvo dvo : dvos) {

            // 0.동일 키값으로 결과가 저장되었는지 체크한다.
            int existCnt = mapper.selectExistSvpdCstSvWkRsIz(dvo);
            BizAssert.isTrue(existCnt == 0, "MSG_ALT_EXIST_FSH_WK_LIST_RTRY_CONF");

            // 1.배정테이블 업데이트
            // TODO 해당 소스 프로세스 정의필요
            // EX) 서비스대분류코드 : BS 인경우에만 BS배정  그외에는 AS 설치배정
            if ("2".equals(dvo.getSvBizHclsfCd())) {
                mapper.updateSvpdCstSvBfsvcAsnIz(dvo); // 고객서비스BS배정내역
            } else {
                mapper.updateSvpdCstSvasIstAsnIz(dvo); // 고객서비스설치배정내역
            }

            // 2.작업결과 IU
            dvo.setSvProcsCn(messageResourceService.getMessage("MSG_ALT_INST_MD_PRODUCT_OSTR_FSH"));
            mapper.insertSvpdCstSvWkRsIz(dvo);

            List<WsnaMdProductOutOfStorageSaveProductDvo> products = dvo.getProducts();
            for (WsnaMdProductOutOfStorageSaveProductDvo product : products) {
                dvo.setPdCd(product.getPdCd());
                dvo.setUseQty(product.getUseQty());

                // 3.작업출고내역 등록 (TB_SVST_SV_WK_OSTR_IZ)
                mapper.insertSvstSvWkOstrIz(dvo);

                // 4.재고변경 (TB_SVST_CST_SV_ITM_STOC_IZ)
                itemStockService.createStock(setWsnaItemStockItemizationReqDvo(dvo));
            }

            // 5.출고 확정시 일자(설치일자,배송예정일자) 현재날짜 지정 (판매시스템 연계)
            String sppDueDt = DateUtil.getNowDayString(); // 배송예정일자
            dvo.setIstDt(DateUtil.getNowDayString()); // 설치일자

            String cntrNo = dvo.getCntrNo();
            String cntrSn = dvo.getCntrSn();
            String istDt = dvo.getIstDt();

            int result = installationReqdDtInService.saveInstallReqdDt(cntrNo, cntrSn, istDt, "", sppDueDt);
            if (result > 0) {
                // 6.수행내역 설치일자 업데이트 (TB_SVPD_CST_SV_EXCN_IZ)
                mapper.updateSvpdCstSvExcnIz(dvo);
            }

            processCount += 1;
        }

        return processCount;
    }

    /* 재고변경 */
    private WsnaItemStockItemizationReqDvo setWsnaItemStockItemizationReqDvo(WsnaMdProdcutOutOfStorageSaveDvo vo) {
        String nowDay = DateUtil.getNowDayString();

        WsnaItemStockItemizationReqDvo reqDvo = new WsnaItemStockItemizationReqDvo();
        reqDvo.setProcsYm(nowDay.substring(0, 6));
        reqDvo.setProcsDt(nowDay);
        reqDvo.setWareDv("2"); /*창고구분*/
        reqDvo.setWareNo(vo.getWkWareNo());
        reqDvo.setWareMngtPrtnrNo(vo.getPrtnrNo()); //파트너번호
        reqDvo.setItmPdCd(vo.getPdCd());
        reqDvo.setQty(String.valueOf(vo.getUseQty()));
        reqDvo.setIostTp("213");
        reqDvo.setWorkDiv("A"); /*작업구분 workDiv*/
        reqDvo.setMngtUnit("10");
        reqDvo.setItemGd("A");

        return reqDvo;

    }

    @Transactional
    public int saveMdProductOutOfStorageCancels(List<RemoveReq> dtos) {
        int processCount = 0;

        // 1.작업출고내역(TB_SVST_SV_WK_OSTR_IZ) 삭제 여부 업데이트
        List<WsnaMdProdcutOutOfStorageSaveDvo> dvos = converter.mapRemoveReqToMdProductOutOfStorageSaveDvo(dtos);
        for (WsnaMdProdcutOutOfStorageSaveDvo dvo : dvos) {
            mapper.updateSvstSvWkOstrIzCancel(dvo);

            // 2.배정테이블(TB_SVPD_CST_SVAS_IST_ASN_IZ, TB_SVPD_CST_SV_BFSVC_ASN_IZ)업데이트 (작업상태 초기화)
            if ("2".equals(dvo.getSvBizHclsfCd())) {
                mapper.updateSvpdCstSvBfsvcAsnIzCancel(dvo);
            } else {
                mapper.updateSvpdCstSvasIstAsnIzCancel(dvo);
            }
            // 3.수행테이블 (TB_SVPD_CST_SV_EXCN_IZ)  설치일자 초기화
            mapper.updateSvpdCstSvExcnIzCancel(dvo);

            // 4.고객서비스작업결과내역(TB_SVPD_CST_SV_WK_RS_IZ) 삭제
            mapper.deleteSvpdCstSvWkRsIzCancel(dvo);

            processCount += 1;
        }
        return processCount;
    }

}
