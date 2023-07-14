package com.kyowon.sms.wells.web.service.stock.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.common.web.service.stock.ivo.EAI_WSVO1003.request.WellsCounselReqIvo;
import com.kyowon.sms.common.web.service.stock.ivo.EAI_WSVO1003.response.WellsCounselResIvo;
import com.kyowon.sms.common.web.service.stock.service.ZsnaWellsCounselSevice;
import com.kyowon.sms.wells.web.contract.ordermgmt.service.WctaInstallationReqdDtInService;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaItemStockItemizationReqDvo;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaPcsvReturningGoodsSaveDvo;
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

    private final WsnaPcsvReturningGoodsSaveMapper mapper;
    private final ZsnaWellsCounselSevice counselService;
    private final WsnaItemStockItemizationService itemStockservice;
    private final WctaInstallationReqdDtInService reqdDtService;

    @Transactional
    public void savePcsvReturningGoods(WsnaPcsvReturningGoodsSaveDvo dvo) {

        WellsCounselResIvo counselRes = null;

        if ("11".equals(dvo.getFindGb()) && "10".equals(dvo.getWkPrgsStatCd())
            && !StringUtils.isEmpty(dvo.getReqdDt())) {
            /*
              취소완료 처리 StringUtils.isEmpty(dvo.getReqdDt())
              조건 - 처리구분(11), 서비스상태코드(10), 철거일(Not Null)
            */

            // 1. 동일 키값으로 작업결과 조회
            selectExistSvpdCstSvWkRsIz(dvo);
            // 2. 고객서비스설치배정내역 업데이트
            mapper.updateSvpdCstSvasIstAsnIz(dvo);
            // 3. 작업결과 저장
            mapper.insertSvpdCstSvWkRsIz(dvo);
            // 4. 철거일자 업데이트
            mapper.updateSvpdCstSvExcnIz(dvo);
            // 5. 미완료 웰컴BS 취소 처리 업데이트 TB_SVPD_CST_SV_BFSVC_ASN_IZ
            mapper.updateSvpdCstSvBfsvcAsnIz(dvo);
            // 6. 철거일자 업데이트(서비스모바일 > 설치완료 > 철거일자 판매시스템 연계)
            String reqdDt = DateUtil.getNowDayString();
            // 7. 판매시스템 철거일자 업데이트 TODO : 판매시스템 변경 중, 연계  추후 작업
            // reqdDtService.saveInstallReqdDt(dvo.getCntrNo(), dvo.getCntrSn(), "", reqdDt.substring(0, 8));
            log.debug("[판매시스템 철거일자 업데이트] => {}", reqdDt);
            // 7. 작업엔지니어 조회 추가 작업 필요
            WsnaPcsvReturningGoodsSaveDvo engineerDvo = mapper.selectEngineerOgbsMmPrtnrIz(dvo); //작업엔지니어 정보를 구한다.
            // 8. 서비스작업출고내역 저장
            mapper.insertSvstSvWkOstrIz(dvo);
            // 9. 수불처리 저장 // TODO : 판매시스템 변경 중, 연계  추후 작업
            //itemStockservice.createStock(setPcsvReturnGoodsWsnaItemStockItemizationDtoSaveReq(dvo));
            log.debug("[수불처리 저장] => {}", setPcsvReturnGoodsWsnaItemStockItemizationDtoSaveReq(dvo));

        } else {
            /*
              반품요청, 반품등록 처리
            */
            // 1. 고객서비스AS설치배정내역 업데이트
            mapper.updateSvpdCstSvasAsIstAsnIz(dvo);
            log.debug("[고객서비스AS설치배정내역 업데이트] => {}", dvo);

            // 2. KWCC(고객센터) 상담정보 EAI 인터페이스 호출 TODO : 고객센터 작업 중, 연계  추후 작업
            //counselRes = counselService.saveWellsCounsel(setPcsvReturnGoodsWellsCounselReqIvoSaveReq(dvo));
            log.debug("[고객센터 상담정보 연계 처리결과 조회] => {}", counselRes);
        }

    }

    private void selectExistSvpdCstSvWkRsIz(
        WsnaPcsvReturningGoodsSaveDvo dvo
    ) {
        int existCnt = mapper.selectExistSvpdCstSvWkRsIz(dvo);
        BizAssert.isTrue(existCnt == 0, "이미 완료 처리 되었습니다. 작업목록을 다시 확인 해주세요.");
    }

    private WellsCounselReqIvo setPcsvReturnGoodsWellsCounselReqIvoSaveReq(WsnaPcsvReturningGoodsSaveDvo dvo) {

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
        cnslCn.append(dvo.getRtngdNm() + "||");

        reqIvo.setCnslCn(cnslCn.toString()); //상담내용
        UserSessionDvo session = SFLEXContextHolder.getContext().getUserSession();
        reqIvo.setRegUserId(session.getUserId()); //입력사용자 ID

        return reqIvo;
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
        reqDvo.setIostTp("162"); //입출고유형
        reqDvo.setWorkDiv("A"); /*작업구분 workDiv*/
        reqDvo.setItmPdCd(dvo.getPdCd());
        reqDvo.setMngtUnit("1");
        reqDvo.setItemGd(dvo.getFnlRtngdGd()); //최종상품등급
        reqDvo.setQty(dvo.getUseQty());

        return reqDvo;
    }

}
