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
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaItemStockItemizationReqDvo;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaLogisticsInStorageAskReqDvo;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaPcsvReturningGoodsSaveDvo;
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

    private static final String RETURN_INSIDE = "162"; // 택배반품-임시
    private final WsnaPcsvReturningGoodsSaveMapper saveMapper;
    private final WsnaPcsvReturningGoodsMgtMapper mapper;
    private final WsnaPcsvReturningGoodsMgtConverter converter;
    private final ZsnaWellsCounselSevice counselService;
    private final WsnaItemStockItemizationService itemStockService;
    private final WctaInstallationReqdDtInService reqdDtService;
    private final WsnaLogisticsInStorageAskService logisticsService;

    @Transactional
    public int savePcsvReturningGoods(List<WsnaPcsvReturningGoodsMgtDto.SaveReq> dtos) {

        WellsCounselResIvo counselRes;
        int processCount = 0;
        int saveCount = 0;

        List<WsnaPcsvReturningGoodsSaveDvo> dvos = converter.mapSaveReqToPcsvReturningGoodsDvo(dtos);
        // 물류수불처리 DVO
        List<WsnaPcsvReturningGoodsSaveDvo> logisticsDvos = new ArrayList<WsnaPcsvReturningGoodsSaveDvo>();
        for (WsnaPcsvReturningGoodsSaveDvo dvo : dvos) {
            if ("11".equals(dvo.getFindGb()) && "10".equals(dvo.getWkPrgsStatCd())
                && !StringUtils.isEmpty(dvo.getReqdDt())) {
                /*
                  취소완료 처리 StringUtils.isEmpty(dvo.getReqdDt())
                  조건 - 처리구분(11), 서비스상태코드(10), 철거일(Not Null)
                */

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
                // 6. 철거일자 업데이트(서비스모바일 > 설치완료 > 철거일자 판매시스템 연계)
                String reqdDt = DateUtil.getNowDayString();
                // 7. 판매시스템 철거일자 업데이트
                reqdDtService.saveInstallReqdDt(dvo.getCntrNo(), dvo.getCntrSn(), "", reqdDt.substring(0, 8), "");
                log.debug("[판매시스템 철거일자 업데이트] => {}", reqdDt);

                logisticsDvos.add(dvo);
            } else {
                /*
                  반품요청, 반품등록 처리
                */
                // 1. 고객서비스AS설치배정내역 업데이트
                counselRes = new WellsCounselResIvo();
                counselRes = counselService.saveWellsCounsel(setPcsvReturnGoodsWellsCounselReqIvoSaveReq(dvo));
                log.debug("[고객센터 상담정보 연계 처리결과 조회] => {}", counselRes);
                // 성공 시, 다음 단계 진행
                if ("S".equals(counselRes.getResultCode())) {
                    // 2. 고객서비스AS설치배정내역 업데이트
                    saveMapper.updateSvpdCstSvasAsIstAsnIz(dvo);
                    log.debug("[고객서비스AS설치배정내역 업데이트] => {}", dvo);
                } else {
                    log.debug("[고객센터 상담정보 연계 실패] => {}", counselRes);
                }
            }
            // 8. 서비스작업출고내역 저장 및 물류 수불처리
            saveCount = savePcsvReturningGoodsOstrs(logisticsDvos);
            processCount += 1;
        }
        return processCount + saveCount;
    }

    @Transactional
    public int savePcsvReturningGoodsOstrs(List<WsnaPcsvReturningGoodsSaveDvo> dvos) {

        int processCount = 0;
        int serialNumber = 1;

        for (WsnaPcsvReturningGoodsSaveDvo dvo : dvos) {
            String ostrDt = DateUtil.getNowDayString();
            String itmOstrNo = this.mapper.selectNextItmOstrNo(
                // 택배반품 임시사용 262 사용
                new WsnaPcsvReturningGoodsMgtDto.FindItmOstrNoReq(RETURN_INSIDE, ostrDt)
            );
            /*
            String itmStrNo = this.mapper.selectNextItmStrNo(
                new WsnaPcsvReturningGoodsMgtDto.FindItmStrNoReq(RETURN_INSIDE, ostrDt, dvo.getWareNo())
            );
            */
            dvo.setItmOstrNo(itmOstrNo);
            dvo.setOstrSn(String.valueOf(serialNumber));
            /*
            dvo.setItmStrNo(itmStrNo);
            dvo.setStrSn(String.valueOf(serialNumber));
            */
            dvo.setOstrTpCd(RETURN_INSIDE);
            dvo.setOstrDt(ostrDt);
            log.info("[택배반품 출고일련번호] => {}", itmOstrNo);

            // 9. 서비스작업출고내역 저장
            saveMapper.insertSvstSvWkOstrIz(dvo);
            // 10. 반품요청 연계(물류서비스) DVO 변환 및 호출
            logisticsService.createInStorageAsks(setLogisticsInStorageAskReqDvo(dvo));
            // 11. 품목 재고 변경 내용 저장
            itemStockService.createStock(setPcsvReturnGoodsWsnaItemStockItemizationDtoSaveReq(dvo));

            processCount += 1;
        }
        return processCount;
    }

    private List<WsnaLogisticsInStorageAskReqDvo> setLogisticsInStorageAskReqDvo(WsnaPcsvReturningGoodsSaveDvo dvo) {

        List<WsnaLogisticsInStorageAskReqDvo> AskReqList = new ArrayList<WsnaLogisticsInStorageAskReqDvo>();
        WsnaLogisticsInStorageAskReqDvo AskReqDvo = new WsnaLogisticsInStorageAskReqDvo();

        AskReqDvo.setOstrAkNo(dvo.getItmOstrNo());
        AskReqDvo.setOstrAkSn(Integer.parseInt(dvo.getOstrSn()));
        AskReqDvo.setOstrAkTpCd(RETURN_INSIDE);
        AskReqDvo.setOstrAkRgstDt(dvo.getOstrDt());
        AskReqDvo.setStrHopDt(dvo.getOstrDt());
        AskReqDvo.setLgstStrTpCd("RT");
        AskReqDvo.setIostAkDvCd("WE");
        AskReqDvo.setWareMngtPrtnrNo(dvo.getPrtnrNo());
        AskReqDvo.setWareMngtPrtnrOgTpCd(dvo.getOgTpCd());
        AskReqDvo.setLgstSppMthdCd("6"); // 확인필요
        AskReqDvo.setItmPdCd(dvo.getPdCd());
        AskReqDvo.setOstrAkQty(Integer.parseInt(dvo.getUseQty()));
        AskReqDvo.setItmGdCd(dvo.getPdGdCd());
        AskReqDvo.setOstrOjWareNo(dvo.getWareNo()); // 확인필요 - 출고창고번호
        AskReqDvo.setSvCnrCd(dvo.getWareNo());
        AskReqDvo.setSvCnrNm(dvo.getWareNm());
        AskReqDvo.setRmkCn(dvo.getRmkCn());

        AskReqList.add(AskReqDvo);

        return AskReqList;
    }

    private WsnaItemStockItemizationReqDvo setPcsvReturnGoodsWsnaItemStockItemizationDtoSaveReq(
        WsnaPcsvReturningGoodsSaveDvo dvo
    ) {

        WsnaItemStockItemizationReqDvo reqDvo = new WsnaItemStockItemizationReqDvo();
        String nowDay = DateUtil.getNowDayString();
        reqDvo.setProcsYm(nowDay.substring(0, 6));
        reqDvo.setProcsDt(nowDay);
        reqDvo.setWareDv(dvo.getWareNo().substring(0, 1)); /*창고구분*/
        reqDvo.setWareNo(dvo.getWareNo());
        reqDvo.setWareMngtPrtnrNo(dvo.getPrtnrNo()); //파트너번호
        reqDvo.setIostTp("162"); //입출고유형(택배반품)
        reqDvo.setWorkDiv("A"); /*작업구분 workDiv*/
        reqDvo.setItmPdCd(dvo.getPdCd());
        reqDvo.setMngtUnit("1");
        reqDvo.setItemGd(dvo.getPdGdCd()); //상품등급
        reqDvo.setQty(dvo.getUseQty());

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
        cnslCn.append(dvo.getPdArvDt() + "||");
        cnslCn.append("3. 반품상담접수일자 : ");
        cnslCn.append(dvo.getVstRqdt() + "||");
        cnslCn.append("4. 현물입고일자 : ");
        cnslCn.append(dvo.getArvDt() + "||");
        cnslCn.append("5. 경과일수 : ");
        cnslCn.append(dvo.getPdUseDc() + "||");
        cnslCn.append("6. 산정등급 : ");
        cnslCn.append(dvo.getRtngdGd() + "||");
        cnslCn.append("7. 개봉여부 : ");
        if ("91".equals(dvo.getDtmChRsonCd())) {
            cnslCn.append("개봉" + "||");
        } else if ("92".equals(dvo.getDtmChRsonCd())) {
            cnslCn.append("미개봉" + "||");
        } else {
            cnslCn.append("||");
        }
        cnslCn.append("8. 반품운송장 번호 : ");
        cnslCn.append(dvo.getSppIvcNo() + "||");
        cnslCn.append("9. 택배사/반품자 : ");
        cnslCn.append(dvo.getSppProcsBzsNm() + "/");
        cnslCn.append(dvo.getRtngdNm());

        reqIvo.setCNSL_CN(cnslCn.toString()); //상담내용
        UserSessionDvo session = SFLEXContextHolder.getContext().getUserSession();
        reqIvo.setREG_USER_ID(session.getUserId()); //입력사용자 ID

        return reqIvo;
    }

}
