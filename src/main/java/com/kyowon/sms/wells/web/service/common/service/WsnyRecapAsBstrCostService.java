package com.kyowon.sms.wells.web.service.common.service;

import com.kyowon.sms.wells.web.service.common.converter.WsnyRecapAsBstrCostConverter;
import com.kyowon.sms.wells.web.service.common.dto.WsnyRecapAsBstrCostDto;
import com.kyowon.sms.wells.web.service.common.dvo.WsnyRecapAsBstrCostDvo;
import com.kyowon.sms.wells.web.service.common.mapper.WsnyRecapAsBstrCostMapper;
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
public class WsnyRecapAsBstrCostService {
    private final WsnyRecapAsBstrCostMapper mapper;
    private final WsnyRecapAsBstrCostConverter converter;

    /**
     * 유상 AS 출장비 관리 조회(페이징)
     *
     * @param searchReq : { pdCd:상품코드 }
     * @param pageInfo : 페이징정보
     * @return 조회결과
     */
    public PagingResult<WsnyRecapAsBstrCostDto.SearchRes> selectRecapAsBstrCostPages(
        WsnyRecapAsBstrCostDto.SearchReq searchReq, PageInfo pageInfo
    ) {
        return new PagingResult<>(
            converter.mapAllRecapAsBstrCostDvoToSearchRes(mapper.selectRecapAsBstrCostPages(searchReq, pageInfo)), pageInfo
        );
    }

    /**
     * 유상 AS 출장비 관리 저장
     *
     * @param rowData : [ {pdCd:상품코드, izSn, bstrCsAmt:출장비용금액, vlStrtDtm:유효시작일시, vlEndDtm:유효종료일시 } ...]
     * @return 처리수
     */
    public int saveRecapAsBstrCost(List<WsnyRecapAsBstrCostDto.SaveReq> rowData) throws ParseException {
        AtomicInteger updateCount = new AtomicInteger();
        for (WsnyRecapAsBstrCostDto.SaveReq row : rowData) {
            if (CommConst.ROW_STATE_DELETED.equals(row.rowState())) {
                updateCount.addAndGet(mapper.deleteRecapAsBstrCost(row));
            }
        }
        WsnyRecapAsBstrCostDvo target = null;
        for (WsnyRecapAsBstrCostDto.SaveReq row : rowData) {
            switch (row.rowState()) {
                case CommConst.ROW_STATE_CREATED -> {
                    WsnyRecapAsBstrCostDvo maxIzSn = mapper.selectMaxIzSn(converter.mapAllRecapAsBstrCostSaveReqToDvo(row));
                    WsnyRecapAsBstrCostDvo newRow = converter.mapAllRecapAsBstrCostSaveReqToDvo(row);
                    newRow.setIzSn(maxIzSn.getIzSn());
                    updateCount.addAndGet(mapper.insertRecapAsBstrCost(newRow));
                    target = mapper.selectTarget(converter.mapAllDvoToSaveReq(newRow));
                    String pdCd = row.pdCd();
                    String prevIzSn = target.getPrevIzSn();
                    String startDtm = row.vlStrtDtm();
                    if (!ObjectUtils.isEmpty(prevIzSn))
                        mapper.updatePrevIsZnEndDtm(
                            new WsnyRecapAsBstrCostDvo(pdCd, prevIzSn, null, DateUtil.addDays(startDtm, -1))
                        );
                }
                case CommConst.ROW_STATE_UPDATED -> {
                    target = mapper.selectTarget(row);
                    String pdCd = row.pdCd();
                    String prevIzSn = target.getPrevIzSn();
                    String nextIzSn = target.getNextIzSn();
                    String startDtm = row.vlStrtDtm();
                    String endDtm = row.vlEndDtm();
                    updateCount.addAndGet(mapper.updateRecapAsBstrCost(converter.mapAllRecapAsBstrCostSaveReqToDvo(row)));
                    if (!ObjectUtils.isEmpty(prevIzSn))
                        mapper.updatePrevIsZnEndDtm(
                            new WsnyRecapAsBstrCostDvo(pdCd, prevIzSn, null, DateUtil.addDays(startDtm, -1))
                        );
                    if (!ObjectUtils.isEmpty(nextIzSn))
                        mapper.updateNextIsZnStrtDtm(
                            new WsnyRecapAsBstrCostDvo(pdCd, nextIzSn, DateUtil.addDays(endDtm, 1), null)
                        );
                }
                default -> {}
            }
        }
        return updateCount.get();
    }

}
