package com.kyowon.sms.wells.web.service.stock.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.service.stock.converter.WsnaStockMasterRenewalConverter;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaStockMasterRenewalDto.EditReq;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaStockMasterRenewalDvo;
import com.kyowon.sms.wells.web.service.stock.mapper.WsnaStockMasterRenewalMapper;
import com.sds.sflex.system.config.exception.BizException;

import lombok.RequiredArgsConstructor;

/**
 * <pre>
 * W-SV-U-0279M01 재고마스터갱신
 * </pre>
 *
 * @author hyewon.kim 김혜원
 * @since 2023.03.13
 */
@Service
@RequiredArgsConstructor
public class WsnaStockMasterRenewalService {

    private final WsnaStockMasterRenewalMapper mapper;

    private final WsnaStockMasterRenewalConverter converter;

    static final String BASE_TERM_DATE_STOCK = "1";
    static final String MONTHLY_STOCK = "2";
    static final String POINT_IN_TIME_STOCK = "3";
    static final String MOVEMENT_STOCK = "4";

    @Transactional(timeout = 300)
    public int editStockMaster(EditReq dto) {
        int processCount = 0;

        WsnaStockMasterRenewalDvo dvo = this.converter.mapEditReqToWsnaStockMasterRenewalDvo(dto);

        switch (dto.rnwOjAcd()) {
            case BASE_TERM_DATE_STOCK -> {
                // 기초재고갱신
                processCount += this.mapper.updateBaseTermDateStocks(dvo);
            }
            case MONTHLY_STOCK -> {
                // 월별재고 갱신
                processCount += this.mapper.updateMonthlyStocks(dvo);
            }
            case POINT_IN_TIME_STOCK -> {
                // 시점재고 갱신
                processCount += this.mapper.updatePointInTimeStocks(dvo);
            }
            case MOVEMENT_STOCK -> {
                // 이동재고 갱신
                processCount += this.mapper.updateMovementStocks(dvo);
            }
            default -> throw new BizException("MSG_ALT_RENW_CODE_NOT_PROC");
        }

        return processCount;
    }
}
