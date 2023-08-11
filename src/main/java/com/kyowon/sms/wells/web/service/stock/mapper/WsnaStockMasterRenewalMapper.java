package com.kyowon.sms.wells.web.service.stock.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.stock.dvo.WsnaStockMasterRenewalDvo;

/**
 * <pre>
 * W-SV-U-0279M01 재고마스터갱신
 * </pre>
 *
 * @author hyewon.kim 김혜원
 * @since 2023.03.13
 */
@Mapper
public interface WsnaStockMasterRenewalMapper {

    int updateBaseTermDateStocks(WsnaStockMasterRenewalDvo dvo);

    int updateMonthlyStocks(WsnaStockMasterRenewalDvo dvo);

    int updatePointInTimeStocks(WsnaStockMasterRenewalDvo dvo);

    int updateMovementStocks(WsnaStockMasterRenewalDvo dvo);

}
