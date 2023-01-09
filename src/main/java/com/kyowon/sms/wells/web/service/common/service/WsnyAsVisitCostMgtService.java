package com.kyowon.sms.wells.web.service.common.service;

import com.kyowon.sms.wells.web.service.common.converter.WsnyAsVisitCostMgtConverter;
import com.kyowon.sms.wells.web.service.common.dto.WsnyAsVisitCostMgtDto;
import com.kyowon.sms.wells.web.service.common.dvo.WsnyAsVisitCostMgtDvo;
import com.kyowon.sms.wells.web.service.common.mapper.WsnyAsVisitCostMgtMapper;
import com.sds.sflex.common.utils.DateUtil;
import com.sds.sflex.system.config.constant.CommConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.text.ParseException;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * <pre>
 * W-SV-U-0101M01 유상 AS 출장비 관리
 * </pre>
 *
 * @author gs.piit122 김동엽
 * @since 2022.11.18
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class WsnyAsVisitCostMgtService {
    private final WsnyAsVisitCostMgtMapper mapper;
    private final WsnyAsVisitCostMgtConverter converter;

    /**
     * 유상 AS 출장비 관리 조회(페이징)
     *
     * @param searchReq : { pdCd:상품코드 }
     * @param pageInfo : 페이징정보
     * @return 조회결과
     */
    public PagingResult<WsnyAsVisitCostMgtDto.SearchRes> getAsVisitCostPages(
        WsnyAsVisitCostMgtDto.SearchReq searchReq, PageInfo pageInfo
    ) {
        return new PagingResult<>(
            converter.mapAllRecapAsBstrCostDvoToSearchRes(mapper.selectAsVisitCostPages(searchReq, pageInfo)), pageInfo
        );
    }

    /**
     * 유상 AS 출장비 관리 저장
     *
     * @param rowData : [ {pdCd:상품코드, izSn, bstrCsAmt:출장비용금액, apyStrtdt:유효시작일시, apyEnddt:유효종료일시 } ...]
     * @return 처리수
     */
    public int saveAsVisitCosts(List<WsnyAsVisitCostMgtDto.SaveReq> rowData) throws ParseException {
        AtomicInteger updateCount = new AtomicInteger();
        for (WsnyAsVisitCostMgtDto.SaveReq row : rowData) {
            if (CommConst.ROW_STATE_DELETED.equals(row.rowState())) {
                updateCount.addAndGet(mapper.deleteRecapAsBstrCost(row));
            }
        }
        WsnyAsVisitCostMgtDvo target = null;
        for (WsnyAsVisitCostMgtDto.SaveReq row : rowData) {
            switch (row.rowState()) {
                case CommConst.ROW_STATE_CREATED -> {
                    WsnyAsVisitCostMgtDvo maxIzSn = mapper.selectMaxIzSn(converter.mapAllRecapAsBstrCostSaveReqToDvo(row));
                    WsnyAsVisitCostMgtDvo newRow = converter.mapAllRecapAsBstrCostSaveReqToDvo(row);
                    newRow.setIzSn(maxIzSn.getIzSn());
                    updateCount.addAndGet(mapper.insertRecapAsBstrCost(newRow));
                    target = mapper.selectTarget(converter.mapAllDvoToSaveReq(newRow));
                    String pdCd = row.pdCd();
                    String prevIzSn = target.getPrevIzSn();
                    String startDtm = row.apyStrtdt();
                    if (!ObjectUtils.isEmpty(prevIzSn))
                        mapper.updatePrevIsZnEndDtm(
                            new WsnyAsVisitCostMgtDvo(pdCd, prevIzSn, null, DateUtil.addDays(startDtm, -1))
                        );
                }
                case CommConst.ROW_STATE_UPDATED -> {
                    target = mapper.selectTarget(row);
                    String pdCd = row.pdCd();
                    String prevIzSn = target.getPrevIzSn();
                    String nextIzSn = target.getNextIzSn();
                    String startDtm = row.apyStrtdt();
                    String endDtm = row.apyEnddt();
                    updateCount.addAndGet(mapper.updateRecapAsBstrCost(converter.mapAllRecapAsBstrCostSaveReqToDvo(row)));
                    if (!ObjectUtils.isEmpty(prevIzSn))
                        mapper.updatePrevIsZnEndDtm(
                            new WsnyAsVisitCostMgtDvo(pdCd, prevIzSn, null, DateUtil.addDays(startDtm, -1))
                        );
                    if (!ObjectUtils.isEmpty(nextIzSn))
                        mapper.updateNextIsZnStrtDtm(
                            new WsnyAsVisitCostMgtDvo(pdCd, nextIzSn, DateUtil.addDays(endDtm, 1), null)
                        );
                }
                default -> {}
            }
        }
        return updateCount.get();
    }

}
