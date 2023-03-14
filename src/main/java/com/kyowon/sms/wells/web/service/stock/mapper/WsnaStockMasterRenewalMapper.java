package com.kyowon.sms.wells.web.service.stock.mapper;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaStockMasterRenewalDto.EditReq;

import java.util.List;

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

    List<WsnaStockMasterRenewalDvo> selectBaseTermDateDiff(EditReq dto);

    int updateBaseTermDateStocks(WsnaStockMasterRenewalDvo dvo);

    List<WsnaStockMasterRenewalDvo> selectMonthlyDiff(EditReq dto);

    int updateMonthlyStocks(WsnaStockMasterRenewalDvo dvo);

    List<WsnaStockMasterRenewalDvo> selectPointInTimeDiff(EditReq dto);

    int updatePointInTimeStocks(WsnaStockMasterRenewalDvo dvo);

    List<WsnaStockMasterRenewalDvo> selectMovementDiff();

    int updateMovementStocks(WsnaStockMasterRenewalDvo dvo);

}
