package com.kyowon.sms.wells.web.service.stock.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    static final String BASE_TERM_DATE_STOCK = "1";
    static final String MONTHLY_STOCK = "2";
    static final String POINT_IN_TIME_STOCK = "3";
    static final String MOVEMENT_STOCK = "4";

    public int editStockMaster(EditReq dto) {
        int processCount = 0;

        switch (dto.rnwOjAcd()) {
            case BASE_TERM_DATE_STOCK -> {
                processCount += this.editBaseTermDateStocks(dto);
            }
            case MONTHLY_STOCK -> {
                processCount += this.editMonthlyStocks(dto);
            }
            case POINT_IN_TIME_STOCK -> {
                processCount += this.editPointInTimeStocks(dto);
            }
            case MOVEMENT_STOCK -> {
                processCount += this.editMovementStocks();
            }
            default -> throw new BizException("MSG_ALT_RENW_CODE_NOT_PROC");
        }

        return processCount;
    }

    /**
     * 기초재고 갱신
     * @param dto { mngtYm: 관리년월, wareDvCd: 창고구분코드, rnwOjAcd: 갱신대상 = "1" }
     * @return
     */
    @Transactional
    private int editBaseTermDateStocks(EditReq dto) {
        int processCount = 0;

        List<WsnaStockMasterRenewalDvo> dvos = this.mapper.selectBaseTermDateDiff(dto);

        for (WsnaStockMasterRenewalDvo dvo : dvos) {
            processCount += this.mapper.updateBaseTermDateStocks(dvo);
        }

        return processCount;
    }

    /**
     * 월별재고 갱신
     * @param dto { mngtYm: 관리년월, wareDvCd: 창고구분코드, rnwOjAcd: 갱신대상 = "2" }
     * @return
     */
    @Transactional
    private int editMonthlyStocks(EditReq dto) {
        int processCount = 0;

        List<WsnaStockMasterRenewalDvo> dvos = this.mapper.selectMonthlyDiff(dto);

        for (WsnaStockMasterRenewalDvo dvo : dvos) {
            processCount += this.mapper.updateMonthlyStocks(dvo);
        }

        return processCount;
    }

    /**
     * 시점재고 갱신
     * @param dto { mngtYm: 관리년월, wareDvCd: 창고구분코드, rnwOjAcd: 갱신대상 = "3" }
     * @return
     */
    @Transactional
    private int editPointInTimeStocks(EditReq dto) {
        int processCount = 0;

        List<WsnaStockMasterRenewalDvo> dvos = this.mapper.selectPointInTimeDiff(dto);

        for (WsnaStockMasterRenewalDvo dvo : dvos) {
            processCount += this.mapper.updatePointInTimeStocks(dvo);
        }

        return processCount;
    }

    /**
     * 이동재고 갱신
     * @return
     */
    @Transactional
    private int editMovementStocks() {
        int processCount = 0;

        List<WsnaStockMasterRenewalDvo> dvos = this.mapper.selectMovementDiff();

        for (WsnaStockMasterRenewalDvo dvo : dvos) {
            processCount += this.mapper.updateMovementStocks(dvo);
        }

        return processCount;
    }

}
