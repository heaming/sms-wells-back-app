package com.kyowon.sms.wells.web.service.stock.service;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaOutOfStorageAskMngtDto.*;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import com.kyowon.sms.wells.web.service.stock.converter.WsnaOutofStorageAskMngtConverter;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaOutOfStorageAskMngtDto;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaReturningGoodsOstrDto;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaOutOfStorageAskMngtDvo;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaReturningGoodsDvo;
import com.sds.sflex.system.config.constant.CommConst;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.service.stock.mapper.WsnaOutOfStorageAskMngtMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import lombok.RequiredArgsConstructor;

/**
 *
 * <pre>
 * W-SV-U-0117M01 출고요청 관리
 * </pre>
 *
 * @author songtaesung
 * @since 2022.12.26
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class WsnaOutOfStorageAskMngtService {

    private final WsnaOutOfStorageAskMngtMapper mapper;

    private final WsnaOutofStorageAskMngtConverter converter;

    /**
     * 출고요청 관리 - 조회
     * @param dto : { strOjWareNo: 출고요청창고, ostrAkTpCd: 출고요청유형코드, startStrHopDt: 입고희망일자 시작일, endStrHopDt: 입고희망일자 종료일, wareDvCd: 출고요청 접수창고, wareLocaraCd: 창고지역코드 }
     * @return 조회결과
     */
    public PagingResult<SearchRes> getOutOfStorageAsks(SearchReq dto, PageInfo pageInfo) {
        return this.mapper.selectOutOfStorageAsks(dto, pageInfo);
    }

    /**
     * 출고요청 관리 - 상단영역 조회
     * @param dto : { session.userid : 사용자id , apyYm : 기준년월 }
     * @return 조회결과
     */
    public FindRes getOutOfStorageAskItems(FindReq dto) {
        return this.mapper.selectOutOfStorageAskItms(dto);
    }

    /**
     * 출고요청 관리 - 하단영역(그리드) 조회
     * @param dto : { session.userid : 사용자id , apyYm : 기준년월 }
     * @return 조회결과
     */
    public List<OutOfRes> getOutOfStorageItemPages(
        SearchReq dto
    ) {
        // TODO: 물류창고 조회 로직 추가 필요.
        return this.mapper.selectOutOfStorageItms(dto);
    }

    /**
     * 출고요청 관리 - 조건에 맞는 출고대상창고 목록 조회
     * @param dto : { session.userid : 사용자id , apyYm : 기준년월 }
     * @return 조회결과
     */
    public List<SearchOstrObjectWarehouseRes> getOstrObjectWarehouses(
        SearchOstrObjectWarehouseReq dto
    ) {
        return this.mapper.selectOstrObjectWarehouses(dto);
    }

    public int removeOutOfStorageAskItems(List<RemoveReq> dtos) {
        int processCount = 0;
        // TODO: 삭제시 검증에 필요한 인터페이스(W-SV-I-0027) 개발 후 로직추가.

        for (RemoveReq dto : dtos) {
            processCount += this.mapper.deleteOutOfStorageAskItems(dto);
        }

        return processCount;
    }

    public int saveOutOfStorageAskItems(List<SaveReq> dtos) {
        int processCount = 0;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        Calendar calendar = Calendar.getInstance();
        String strToday = dateFormat.format(calendar.getTime());
        String strOstrAkNo = null;

        SaveReq saveReq = dtos.get(0);

        log.info("saveReq --->>" + saveReq);
        log.info("saveReq.ostrAkRgstDt ---->" + saveReq.ostrAkRgstDt());

        if (StringUtils.isEmpty(saveReq.ostrAkNo())) {
            strOstrAkNo = this.mapper.selectNewOstrAkNo(
                new FindOstrAkNoReq(saveReq.ostrAkRgstDt(), saveReq.ostrAkTpCd(), saveReq.strOjWareNo())
            );

        }

        for (SaveReq dto : dtos) {
            WsnaOutOfStorageAskMngtDvo dvo = this.converter.mapSaveReqToOutOfStorageAskMngtDvo(dto);

            switch (dto.rowState()) {
                case CommConst.ROW_STATE_CREATED -> {
                    if (!StringUtils.isEmpty(strOstrAkNo)) {
                        dvo.setOstrAkNo(strOstrAkNo);
                    }
                    processCount += mapper.insertOutOfStorageAskItems(dvo);

                }
                case CommConst.ROW_STATE_UPDATED -> {
                    processCount += mapper.updateOutOfStorageAskItmes(dvo);

                }
            }

        }

        //TODO: 시쿼스 정의 및 인터페이스(W-SV-I-0027) 개발 후 로직추가.
        return processCount;
    }

    public List<OutOfRes> getOutOfStorageItemExcelDownload(SearchReq dto) {
        return mapper.selectOutOfStorageItms(dto);
    }
}
