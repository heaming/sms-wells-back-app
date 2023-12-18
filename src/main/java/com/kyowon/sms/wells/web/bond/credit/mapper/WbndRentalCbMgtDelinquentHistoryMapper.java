package com.kyowon.sms.wells.web.bond.credit.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.kyowon.sms.wells.web.bond.credit.dvo.WbndRentalCbDelinquentIzDvo;

/**
 * <pre>
 * W-BN-U-0070M01	렌탈CB연체이력조회
 * </pre>
 *
 * @author gs.piit128 gilyong.han
 * @since 2023-05-19
 */
@Mapper
public interface WbndRentalCbMgtDelinquentHistoryMapper {

    /**
     * 렌탈CB연체이력조회 조회
     * @param dvo
     * @return List<WbndRentalCbDelinquentIzDvo>
     */
    List<WbndRentalCbDelinquentIzDvo> selectRentalCbMgtDelinquentHistories(
        WbndRentalCbDelinquentIzDvo dvo
    );

    /**
     * 렌탈CB연체이력조회 당월 알림톡 발송 COUNT
     * @param dvo
     * @return int
     */
    int selectRentalCbDlqIzSendThisMonthCount(
        @Param("baseYm")
        String baseYm
    );

    /**
     * 렌탈CB연체이력조회 update Iz
     * @param dvo
     * @return int
     */
    int updateRentalCbDlqIz(
        WbndRentalCbDelinquentIzDvo dvo
    );

    /**
     * 렌탈CB연체이력조회 insert History
     * @param dvo
     * @return int
     */
    int insertRentalCbDlqHistory(
        WbndRentalCbDelinquentIzDvo dvo
    );

    /**
     * 렌탈CB연체이력조회 update message date
     * @param cstNo, baseYm
     * @return int
     */
    int updateMessageSendDate(
        @Param("cstNo")
        String cstNo,
        @Param("baseYm")
        String baseYm
    );

}
