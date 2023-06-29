package com.kyowon.sms.wells.web.service.stock.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.common.web.service.stock.ivo.EAI_WSVO1003.request.WellsCounselReqIvo;
import com.kyowon.sms.common.web.service.stock.ivo.EAI_WSVO1003.response.WellsCounselResIvo;
import com.kyowon.sms.common.web.service.stock.service.ZsnaWellsCounselSevice;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaItemStockItemizationReqDvo;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaPcsvReturningGoodsDvo;
import com.kyowon.sms.wells.web.service.stock.mapper.WsnaPcsvReturningGoodsSaveMapper;
import com.sds.sflex.common.utils.DateUtil;
import com.sds.sflex.system.config.context.SFLEXContextHolder;
import com.sds.sflex.system.config.core.dvo.UserSessionDvo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class WsnaPcsvReturningGoodsSaveService {

    private final WsnaPcsvReturningGoodsSaveMapper mapper;
    private final ZsnaWellsCounselSevice counselService;
    private final WsnaItemStockItemizationService itemStockservice;

    @Transactional
    public void savePcsvReturningGoods(WsnaPcsvReturningGoodsDvo dvo) {

        WellsCounselResIvo counselRes;

        // 취소완료 처리 - 처리구분(11), 서비스상태코드(10), 철거일(Not Null)
        if ("11".equals(dvo.getFindGb()) && "10".equals(dvo.getWkPrgsStatCd()) && !(dvo.getReqdDt().isEmpty())) {
            // 작업상태 변경(savePcsvReturningGoodsCancel)
            // 고객서비스AS 설치배정내역 업데이트, 로그 저장, 작업결과테이블 저장, 철거일자 업데이트 , 고객서비스BS배정내역 업데이트
            mapper.savePcsvReturningGoodsCancel(dvo);
            log.debug("############# 취소요청 처리 완료[savePcsvReturningGoodsCancel] #################");

            // 철거제품 내역등록 (savePcsvReturningGoodsReceivingAndPaying)
            // 작업엔지니어 조회, 작업순번조회, 작업결과테이블 저장, 로그저장 , 수불처리
            mapper.savePcsvReturningGoodsReceivingAndPaying(dvo);
            log.debug("############# 철거제품 내역등록(savePcsvReturningGoodsReceivingAndPaying] #################");

            // 수불처리 연계 인터페이스
            itemStockservice.createStock(setPcsvReturnGoodsWsnaItemStockItemizationDtoSaveReq(dvo));
            //            try {
            //            } catch (ParseException e) {
            //                throw new RuntimeException(e);
            //            }

            log.debug("############# 수불처리 등록(savePcsvReturningGoodsReceivingAndPaying] #################");

        } else {
            // 반품요청, 반품등록 처리
            // 고객서비스AS 설치배정내역 업데이트 및 로그 저장
            mapper.savePcsvReturningGoods(dvo);
            log.debug("############# 반품요청/등록 처리 완료[savePcsvReturningGoods] #################");

            // KWCC(고객센터) 상담정보 EAI 인터페이스 호출
            counselRes = counselService.saveWellsCounsel(setPcsvReturnGoodsWellsCounselReqIvoSaveReq(dvo));
            log.debug("[고객센터 상담정보 연계 처리결과 조회] => {}", counselRes);
            log.debug("############# 고객센터 등록 완료[saveWellsCounsel] #################");
        }

    }

    private WellsCounselReqIvo setPcsvReturnGoodsWellsCounselReqIvoSaveReq(WsnaPcsvReturningGoodsDvo dvo) {

        WellsCounselReqIvo reqIvo = new WellsCounselReqIvo();
        StringBuffer cnslCn = new StringBuffer();

        // 1. dvo와 ivo 매핑 처리
        reqIvo.setCstNo(dvo.getCntrCstNo()); //계약고객번호
        reqIvo.setSellTpCd(dvo.getSellTpCd()); //판매유형코드
        reqIvo.setCntrNo(dvo.getCntrNo()); //계약번호
        reqIvo.setCntrSn(dvo.getCntrSn()); //계약일련번호
        reqIvo.setCstNm(dvo.getRcgvpKnm()); //고객명
        //상담내용
        cnslCn.append("@ 상담내용 ||");
        cnslCn.append("1. 매출일자 : ");
        cnslCn.append(dvo.getCntrPdStrtdt() + "||");
        cnslCn.append("2. 제품수령일자 : ");
        cnslCn.append(dvo.getPdCdArvDt() + "||");
        cnslCn.append("3. 반품상담접수일자 : ");
        cnslCn.append(dvo.getVstRqdt() + "||");
        cnslCn.append("4. 현물입고일자 : ");
        cnslCn.append(dvo.getArvDt() + "||");
        cnslCn.append("5. 경과일수 : ");
        cnslCn.append(dvo.getPdUseDc() + "||");
        cnslCn.append("6. 산정등급 : ");
        cnslCn.append(dvo.getRtngdGd() + "||");
        cnslCn.append("7. 개봉여부 : ");
        if ("91".equals(dvo.getGdsOpenYn())) {
            cnslCn.append("개봉" + "||");
        } else {
            cnslCn.append("미개봉" + "||");
        }
        cnslCn.append("8. 반품운송장 번호 : ");
        cnslCn.append(dvo.getSppIvcNo() + "||");
        cnslCn.append("9. 택배사/반품자 : ");
        cnslCn.append(dvo.getSppProcsBzsNm() + "/");
        cnslCn.append(dvo.getRtngdNm() + "||");

        reqIvo.setCnslCn(cnslCn.toString()); //상담내용
        UserSessionDvo session = SFLEXContextHolder.getContext().getUserSession();
        reqIvo.setRegUserId(session.getUserId()); //입력사용자 ID

        return reqIvo;
    }

    private WsnaItemStockItemizationReqDvo setPcsvReturnGoodsWsnaItemStockItemizationDtoSaveReq(
        WsnaPcsvReturningGoodsDvo dvo
    ) {

        WsnaItemStockItemizationReqDvo reqDvo = new WsnaItemStockItemizationReqDvo();
        String nowDay = DateUtil.getNowDayString();
        reqDvo.setProcsYm(nowDay.substring(0, 6));
        reqDvo.setProcsDt(nowDay);
        reqDvo.setWareDv(dvo.getWareNo().substring(0, 1)); /*창고구분*/
        reqDvo.setWareNo(dvo.getWareNo());
        reqDvo.setWareMngtPrtnrNo(dvo.getPrtnrNo()); //파트너번호
        reqDvo.setIostTp("162"); //입출고유형
        reqDvo.setWorkDiv("A"); /*작업구분 workDiv*/
        reqDvo.setItmPdCd(dvo.getPdCd());
        reqDvo.setMngtUnit("1");
        reqDvo.setItemGd(dvo.getFnlRtngdGd()); //최종상품등급
        reqDvo.setQty(dvo.getUseQty());

        return reqDvo;
    }

}
