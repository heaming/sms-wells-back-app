package com.kyowon.sms.wells.web.closing.standard.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.closing.standard.dvo.WdcyBusinessCloseHhCheckDvo;
import com.kyowon.sms.wells.web.closing.standard.dvo.WdcyBusinessCloseHourConfirmDvo;

/**
 * <pre>
 * 일마감통제확인 서비스 맵퍼
 * </pre>
 *
 * @author WOO SEUNGMIN
 * @since 2023-02-06
 */
@Mapper
public interface WdcyBusinessCloseHhCheckMapper {
    /**
     * 검색 조건에 맞는 영업마감 시간 정보를 가지고 온다
     * @return WdcyBusinessCloseHhCheckDvo 검색 결과
     */
    WdcyBusinessCloseHhCheckDvo selectBusinessCloseHh(String clDt, String clPsicNo, String clBizTpCd);

    WdcyBusinessCloseHourConfirmDvo selectCloseHourConfirm(WdcyBusinessCloseHhCheckDvo wdcyBusinessCloseHhCheckDvo);
}
