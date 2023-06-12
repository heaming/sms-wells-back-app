package com.kyowon.sms.wells.web.fee.aggregate.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.fee.aggregate.dto.WfeaWelsMngerSettlementAwDto.*;
import com.kyowon.sms.wells.web.fee.aggregate.dvo.WfeaWelsMngerSettlementAwDvo;

/**
 * <pre>
 * 웰스매니저 정착수당
 * </pre>
 *
 * @author gs.piit150
 * @since 2023.06.07
 */
@Mapper
public interface WfeaWelsMngerSettlementAwMapper {

    List<SearchRes> selectWelsMngers(
        SearchReq dto
    );

    SearchEtcRes selectCheckWelsMngerOpng(
        SearchReq dto
    );

    int insertWelsMngerOpng(WfeaWelsMngerSettlementAwDvo dvo);

    int updateWelsMnger(WfeaWelsMngerSettlementAwDvo dvo);

    int updateWelsMngerConfirm(WfeaWelsMngerSettlementAwDvo dvo);

}
