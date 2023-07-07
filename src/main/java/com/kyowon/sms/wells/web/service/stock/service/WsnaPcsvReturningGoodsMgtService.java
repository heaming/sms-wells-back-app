package com.kyowon.sms.wells.web.service.stock.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.service.stock.converter.WsnaPcsvReturningGoodsMgtConverter;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaPcsvReturningGoodsMgtDto;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaPcsvReturningGoodsMgtDto.SaveReq;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaPcsvReturningGoodsMgtDto.SearchReq;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaPcsvReturningGoodsMgtDto.SearchRes;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaPcsvReturningGoodsDvo;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaPcsvReturningGoodsSaveDvo;
import com.kyowon.sms.wells.web.service.stock.mapper.WsnaPcsvReturningGoodsMgtMapper;
import com.sds.sflex.common.utils.DateUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class WsnaPcsvReturningGoodsMgtService {
    private final WsnaPcsvReturningGoodsMgtMapper mapper;
    private final WsnaPcsvReturningGoodsMgtConverter converter;
    private final WsnaPcsvReturningGoodsSaveService service;

    public List<SearchRes> getPcsvReturningGoods(
        SearchReq req
    ) {
        List<SearchRes> dtos = mapper.selectPcsvReturningGoods(req);
        List<WsnaPcsvReturningGoodsDvo> addDtos = new ArrayList<>();

        for (SearchRes res : dtos) {
            WsnaPcsvReturningGoodsDvo dvo = converter.mapSearchResToWsnaPcsvReturningGoodsDvo(res);

            String pdArvDt = "";
            // 물류 KWLIB.DL1500P EAI연계
            //  1. 송장번호(sppIvcNo)로 상품도착일자(pdArvDt) 조회
            // 물류(HQ) 연계인터페이스 호출
            log.debug("[송장번호] => {}", dvo.getSppIvcNo());
            if (!StringUtils.isEmpty(dvo.getSppIvcNo())) {
                // TODO : 물류 연계인터페이스 추후 작업
                //                pdArvDt = "20230328";
                log.debug("[물류(DB2) 작업일자 조회] => {}", pdArvDt);
                if (!StringUtils.isEmpty(pdArvDt)) {
                    dvo = setPcsvReturnGoodsPdArvDtSaveReq(pdArvDt, dvo);
                }
            }
            addDtos.add(dvo);
        }
        return converter.mapDvoToSearchRes(addDtos);
    }

    public List<SearchRes> getPcsvReturningGoodsExcelDownload(SearchReq dto) {
        List<SearchRes> dtos = mapper.selectPcsvReturningGoods(dto);
        List<WsnaPcsvReturningGoodsDvo> addDtos = new ArrayList<>();

        for (SearchRes res : dtos) {
            WsnaPcsvReturningGoodsDvo dvo = converter.mapSearchResToWsnaPcsvReturningGoodsDvo(res);

            String pdArvDt = "";
            // 물류 KWLIB.DL1500P EAI연계
            //  1. 송장번호(sppIvcNo)로 상품도착일자(pdArvDt) 조회
            // 물류(HQ) 연계인터페이스 호출
            log.debug("[송장번호] => {}", dvo.getSppIvcNo());
            if (!StringUtils.isEmpty(dvo.getSppIvcNo())) {
                // TODO : 물류 연계인터페이스 추후 작업
                //                pdArvDt = "20230328";
                log.debug("[물류(DB2) 작업일자 조회] => {}", pdArvDt);
                if (!StringUtils.isEmpty(pdArvDt)) {
                    dvo = setPcsvReturnGoodsPdArvDtSaveReq(pdArvDt, dvo);
                }
            }
            addDtos.add(dvo);
        }
        return converter.mapDvoToSearchRes(addDtos);
    }

    public List<WsnaPcsvReturningGoodsMgtDto.FindLogisticsCentersRes> getPcsvLogisticsCenters() {
        return mapper.selectPcsvLogisticsCenters();
    }

    public List<WsnaPcsvReturningGoodsMgtDto.FindProductsRes> getPcsvProducts(
        WsnaPcsvReturningGoodsMgtDto.FindProductsReq dto
    ) {
        return mapper.selectPcsvProducts(dto);
    }

    @Transactional
    public int savePcsvReturningGoods(List<SaveReq> dtos) {
        int processCount = 0;

        for (SaveReq dto : dtos) {
            WsnaPcsvReturningGoodsSaveDvo dvo = converter.mapSaveReqToPcsvReturningGoodsDvo(dto);

            service.savePcsvReturningGoods(dvo);

            processCount += 1;
        }

        return processCount;
    }

    private WsnaPcsvReturningGoodsDvo setPcsvReturnGoodsPdArvDtSaveReq(
        String pdArvDt, WsnaPcsvReturningGoodsDvo dvo
    ) {
        /*
        물류 KWLIB.DL1500P EAI연계로 dvo 속성 값 추가
        1. pdArvDt 상품도착일자 추가
        2. pdUseDc 상품사용일수 추가
        3. rtngdGd 반품상품등급 추가
        4. fnlRtngdGd 최종반품상품등급 추가
        */

        String nowDay = DateUtil.getNowDayString();
        // 상품도착(작업)일자 추가
        dvo.setPdArvDt(pdArvDt);
        // 상품사용(경과)일수 추가
        int pdUseDc = DateUtil.getDays(dvo.getVstRqdt(), pdArvDt);
        log.debug("[반품상담접수일자] => {}", dvo.getVstRqdt());
        log.debug("[작업일자] => {}", pdArvDt);
        log.debug("[사용일수] => {}", pdUseDc);
        dvo.setPdUseDc(String.valueOf(pdUseDc));
        // 반품상품등급 추가
        if (pdUseDc > 14) {
            dvo.setRtngdGd("R");
        } else {
            dvo.setRtngdGd("E");
        }
        // 최종반품상품등급 추가
        int fnlPdUseDc;
        if (StringUtils.isEmpty(dvo.getWellsReqdDt())) {
            if (pdUseDc > 14) {
                dvo.setFnlRtngdGd("R");
            } else {
                dvo.setFnlRtngdGd("E");
            }
        } else {
            fnlPdUseDc = DateUtil.getDays(dvo.getWellsReqdDt(), pdArvDt);
            if (fnlPdUseDc > 14) {
                dvo.setFnlRtngdGd("R");
            } else {
                dvo.setFnlRtngdGd("E");
            }
        }
        return dvo;
    }

}
