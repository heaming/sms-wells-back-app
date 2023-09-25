package com.kyowon.sms.wells.web.service.stock.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.common.web.service.stock.ivo.EAI_WSVO1009.request.WellsCounselReqIvo;
import com.kyowon.sms.common.web.service.stock.ivo.EAI_WSVO1009.response.WellsCounselResIvo;
import com.kyowon.sms.common.web.service.stock.service.ZsnaWellsCounselSevice;
import com.kyowon.sms.wells.web.contract.ordermgmt.service.WctaInstallationReqdDtInService;
import com.kyowon.sms.wells.web.service.stock.converter.WsnaPcsvReturningGoodsMgtConverter;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaPcsvReturningGoodsMgtDto;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaPcsvReturningGoodsMgtDto.SaveReq;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaItemStockItemizationReqDvo;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaLogisticsInStorageAskReqDvo;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaPcsvReturningGoodsSaveDvo;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaPcsvReturningGoodsSaveProductDvo;
import com.kyowon.sms.wells.web.service.stock.mapper.WsnaPcsvReturningGoodsMgtMapper;
import com.kyowon.sms.wells.web.service.stock.mapper.WsnaPcsvReturningGoodsSaveMapper;
import com.sds.sflex.common.utils.DateUtil;
import com.sds.sflex.system.config.context.SFLEXContextHolder;
import com.sds.sflex.system.config.core.dvo.UserSessionDvo;
import com.sds.sflex.system.config.validation.BizAssert;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class WsnaPcsvReturningGoodsSaveService {

    private static final String RETURN_INSIDE = "261"; // 택배반품출고유형코드
    private final WsnaPcsvReturningGoodsSaveMapper saveMapper;
    private final WsnaPcsvReturningGoodsMgtMapper mapper;
    private final WsnaPcsvReturningGoodsMgtConverter converter;
    private final ZsnaWellsCounselSevice counselService;
    private final WsnaItemStockItemizationService itemStockService;
    private final WctaInstallationReqdDtInService reqdDtService;
    private final WsnaLogisticsInStorageAskService logisticsService;

    @Transactional
    public int savePcsvReturningGoods(List<SaveReq> dtos) {

        WellsCounselResIvo counselRes;
        int processCount = 0;
        int saveCount = 0;

        List<WsnaPcsvReturningGoodsSaveDvo> dvos = converter.mapSaveReqToPcsvReturningGoodsDvo(dtos);

        // 물류수불처리 DVO
        List<WsnaPcsvReturningGoodsSaveDvo> logisticsDvos = new ArrayList<WsnaPcsvReturningGoodsSaveDvo>();
        for (WsnaPcsvReturningGoodsSaveDvo dvo : dvos) {
            /*
              취소완료(wkPrgsStatCd-10) , 취소일자(NOT NULL)
            */
            if ("10".equals(dvo.getWkPrgsStatCd()) && !StringUtils.isEmpty(dvo.getRsgFshDt())) {
                // 1. 동일 키값으로 작업결과 조회
                selectExistSvpdCstSvWkRsIz(dvo);
                // 2. 고객서비스설치배정내역 업데이트
                saveMapper.updateSvpdCstSvasIstAsnIz(dvo);
                // 3. 작업결과 저장
                saveMapper.insertSvpdCstSvWkRsIz(dvo);
                // 4. 철거일자 업데이트(고객서비스수행내역)
                saveMapper.updateSvpdCstSvExcnIz(dvo);
                // 5. 미완료 웰컴BS 취소 처리 업데이트 TB_SVPD_CST_SV_BFSVC_ASN_IZ
                saveMapper.updateSvpdCstSvBfsvcAsnIz(dvo);
                // 6. 판매시스템 철거일자 업데이트
                String reqdDt = DateUtil.getNowDayString();
                // 반품인 경우 설치일자 NULL, 철거일자 NOT NULL
                reqdDtService
                    .saveInstallReqdDt(dvo.getCntrNo(), dvo.getCntrSn(), "", reqdDt.substring(0, 8), "");
                log.info("[판매시스템 철거일자 업데이트] => {}", dvo.getRsgFshDt());

                logisticsDvos.add(dvo);
            } else {
                /*
                  반품요청(wkPrgsStatCd-00), 반품등록(wkPrgsStatCd-10) 처리
                */
                // 1. 고객서비스AS설치배정내역 업데이트
                counselRes = new WellsCounselResIvo();
                counselRes = counselService.saveWellsCounsel(setPcsvReturnGoodsWellsCounselReqIvoSaveReq(dvo));
                log.info("[고객센터 상담정보 연계 처리결과 조회] => {}", counselRes);
                // 성공 시, 다음 단계 진행
                if ("S".equals(counselRes.getResultCode())) {
                    // 2. 고객서비스AS설치배정내역 업데이트
                    saveMapper.updateSvpdCstSvasAsIstAsnIz(dvo);
                    log.info("[고객서비스AS설치배정내역 업데이트] => {}", dvo);
                } else {
                    log.info("[고객센터 상담정보 연계 실패] => {}", counselRes);
                }
            }
            // 8. 서비스작업출고내역 저장 및 물류 수불처리
            saveCount = savePcsvReturningGoodsOstrs(logisticsDvos);
            log.info("[물류 수불처리 건수] => {}", saveCount);
            processCount += 1;
        }
        return processCount;
    }

    @Transactional
    // 물류 수불처리 테스트
    public int savePcsvReturningGoodsTest(List<SaveReq> dtos) {

        List<WsnaPcsvReturningGoodsSaveDvo> dvos = converter.mapSaveReqToPcsvReturningGoodsDvo(dtos);
        List<WsnaPcsvReturningGoodsSaveProductDvo> pdvos = new ArrayList<>();

        for (WsnaPcsvReturningGoodsSaveDvo dvo : dvos) {
            pdvos = dvo.getProducts();
            int productSize = pdvos.size();
            log.info(" pdvos.size() : " + productSize);
        }

        int saveCount = savePcsvReturningGoodsOstrs(dvos);

        return saveCount;
    }

    @Transactional
    public int savePcsvReturningGoodsOstrs(List<WsnaPcsvReturningGoodsSaveDvo> dvos) {

        int processCount = 1;

        for (WsnaPcsvReturningGoodsSaveDvo dvo : dvos) {
            String ostrDt = DateUtil.getNowDayString();
            String itmOstrNo = this.mapper.selectNextItmOstrNo(
                // 택배반품 임시사용 261 사용
                new WsnaPcsvReturningGoodsMgtDto.FindItmOstrNoReq(RETURN_INSIDE, ostrDt)
            );
            log.info("[택배반품 품목출고일련번호] => {}", itmOstrNo);

            int ostrSn = 1; // 출고일련번호

            // 상품정보세팅 필요
            List<WsnaPcsvReturningGoodsSaveProductDvo> products = dvo.getProducts();
            log.info("[상품 세부 종류] => {}", products.size());
            for (WsnaPcsvReturningGoodsSaveProductDvo productsVo : products) {
                dvo.setOstrTpCd(RETURN_INSIDE);
                dvo.setOstrDt(ostrDt);
                dvo.setItmOstrNo(itmOstrNo);
                dvo.setOstrSn(String.valueOf(ostrSn));
                dvo.setLogisticsPdCd(productsVo.getPdCd());
                dvo.setLogisticsPdNm(productsVo.getPdNm());
                dvo.setLogisticsPdQty(productsVo.getUseQty());
                // 9. 서비스작업출고내역 저장
                saveMapper.insertSvstSvWkOstrIz(dvo);
                // 반품요청 연계(물류서비스) DVO 세팅
                List<WsnaPcsvReturningGoodsSaveDvo> logisticsVo = new ArrayList<WsnaPcsvReturningGoodsSaveDvo>();
                logisticsVo.add(dvo);
                // 10. 반품요청 연계(물류서비스) DVO 변환 및 호출
                logisticsService.createInStorageAsks(setLogisticsInStorageAskReqDvo(logisticsVo));
                // 11. 품목 재고 변경 내용 저장
                itemStockService.createStock(setPcsvReturnGoodsWsnaItemStockItemizationDtoSaveReq(dvo));

                ostrSn += 1;
            }
            processCount += 1;
        }
        return processCount;
    }

    private List<WsnaLogisticsInStorageAskReqDvo> setLogisticsInStorageAskReqDvo(
        List<WsnaPcsvReturningGoodsSaveDvo> logisticsVo
    ) {
        List<WsnaLogisticsInStorageAskReqDvo> AskReqList = new ArrayList<WsnaLogisticsInStorageAskReqDvo>();
        for (WsnaPcsvReturningGoodsSaveDvo dvo : logisticsVo) {
            WsnaLogisticsInStorageAskReqDvo AskReqDvo = new WsnaLogisticsInStorageAskReqDvo();

            AskReqDvo.setOstrAkNo(dvo.getItmOstrNo());
            AskReqDvo.setOstrAkSn(Integer.parseInt(dvo.getOstrSn()));
            AskReqDvo.setOstrAkTpCd(RETURN_INSIDE);
            AskReqDvo.setOstrAkRgstDt(dvo.getOstrDt());
            AskReqDvo.setStrHopDt(dvo.getOstrDt());
            AskReqDvo.setLgstStrTpCd("RT");
            AskReqDvo.setIostAkDvCd("WE");
            AskReqDvo.setWareMngtPrtnrNo(dvo.getWareMngtPrtnrNo());
            AskReqDvo.setWareMngtPrtnrOgTpCd(dvo.getWareMngtPrtnrOgTpCd());
            AskReqDvo.setLgstSppMthdCd("6"); // 확인필요
            AskReqDvo.setItmPdCd(dvo.getLogisticsPdCd());
            AskReqDvo.setOstrAkQty(Integer.parseInt(dvo.getLogisticsPdQty()));
            AskReqDvo.setItmGdCd(dvo.getCmptGd()); //산출등급
            AskReqDvo.setOstrOjWareNo(dvo.getWkWareNo());
            AskReqDvo.setSvCnrCd(dvo.getWkWareNo());
            AskReqDvo.setSvCnrNm(dvo.getWareNm());
            AskReqDvo.setRmkCn(dvo.getRmkCn());

            AskReqList.add(AskReqDvo);
        }

        return AskReqList;
    }

    private WsnaItemStockItemizationReqDvo setPcsvReturnGoodsWsnaItemStockItemizationDtoSaveReq(
        WsnaPcsvReturningGoodsSaveDvo dvo
    ) {

        WsnaItemStockItemizationReqDvo reqDvo = new WsnaItemStockItemizationReqDvo();
        String nowDay = DateUtil.getNowDayString();
        reqDvo.setProcsYm(dvo.getOstrDt().substring(0, 6));
        reqDvo.setProcsDt(dvo.getOstrDt());
        reqDvo.setWareDv(dvo.getWkWareNo().substring(0, 1)); /*창고구분*/
        reqDvo.setWareNo(dvo.getWkWareNo());
        reqDvo.setWareMngtPrtnrNo(dvo.getWareMngtPrtnrNo()); //파트너번호
        reqDvo.setIostTp(RETURN_INSIDE); //입출고유형(택배반품 261)
        reqDvo.setWorkDiv("A"); /*작업구분 workDiv*/
        reqDvo.setItmPdCd(dvo.getLogisticsPdCd());
        reqDvo.setMngtUnit("1");
        reqDvo.setItemGd(dvo.getCmptGd()); //상품등급
        reqDvo.setQty(dvo.getLogisticsPdQty());

        return reqDvo;
    }

    private void selectExistSvpdCstSvWkRsIz(
        WsnaPcsvReturningGoodsSaveDvo dvo
    ) {
        int existCnt = saveMapper.selectExistSvpdCstSvWkRsIz(dvo);
        BizAssert.isTrue(existCnt == 0, "이미 완료 처리 되었습니다. 작업목록을 다시 확인 해주세요.");
    }

    private WellsCounselReqIvo setPcsvReturnGoodsWellsCounselReqIvoSaveReq(WsnaPcsvReturningGoodsSaveDvo dvo) {

        WellsCounselReqIvo reqIvo = new WellsCounselReqIvo();
        StringBuffer cnslCn = new StringBuffer();

        // 1. dvo와 ivo 매핑 처리
        reqIvo.setCST_NO(dvo.getCntrCstNo()); //계약고객번호
        reqIvo.setSELL_TP_CD(dvo.getSellTpCd()); //판매유형코드
        reqIvo.setCNTR_NO(dvo.getCntrNo()); //계약번호
        reqIvo.setCNTR_SN(dvo.getCntrSn()); //계약일련번호
        reqIvo.setCST_NM(dvo.getRcgvpKnm()); //고객명
        //상담내용
        cnslCn.append("@ 상담내용 ||");
        cnslCn.append("1. 매출일자 : ");
        cnslCn.append(dvo.getCntrPdStrtdt() + "||");
        cnslCn.append("2. 제품수령일자 : ");
        cnslCn.append(dvo.getPcsvRcgvDt() + "||");
        cnslCn.append("3. 반품상담접수일자 : ");
        cnslCn.append(dvo.getRcpdt() + "||");
        cnslCn.append("4. 현물입고일자 : ");
        cnslCn.append(dvo.getArvDt() + "||");
        cnslCn.append("5. 경과일수 : ");
        cnslCn.append(dvo.getPdUseDc() + "||");
        cnslCn.append("6. 산정등급 : ");
        cnslCn.append(dvo.getCmptGd() + "||");
        cnslCn.append("7. 개봉여부 : ");
        if ("91".equals(dvo.getDtmChRsonCd())) {
            cnslCn.append("개봉" + "||");
        } else if ("92".equals(dvo.getDtmChRsonCd())) {
            cnslCn.append("미개봉" + "||");
        } else {
            cnslCn.append("||");
        }
        cnslCn.append("8. 반품운송장 번호 : ");
        cnslCn.append(dvo.getFwSppIvcNo() + "||");
        cnslCn.append("9. 비고(택배사/반품자) : ");
        cnslCn.append(dvo.getDtmChRsonDtlCn());

        reqIvo.setCNSL_CN(cnslCn.toString()); //상담내용
        UserSessionDvo session = SFLEXContextHolder.getContext().getUserSession();
        reqIvo.setREG_USER_ID(session.getUserId()); //입력사용자 ID

        return reqIvo;
    }

}
