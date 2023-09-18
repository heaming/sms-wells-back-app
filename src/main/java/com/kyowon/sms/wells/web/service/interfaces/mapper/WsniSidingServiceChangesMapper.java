package com.kyowon.sms.wells.web.service.interfaces.mapper;

import com.kyowon.sms.wells.web.service.interfaces.dvo.WsniSidingServiceChangesDvo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WsniSidingServiceChangesMapper {

    int insertSdingAsAkHist(
        String cntrNo,
        String cntrSn,
        String akSn,
        String histChDtm,
        String asAkDvCd,
        String akChdt,
        String bfchPdCd,
        String afchPdCd,
        String mtrProcsStatCd,
        String matNm,
        String csmrUprcAmt
    );

    int deleteSdingAskAk(
        String cntrNo,
        String cntrSn,
        String akSn,
        String asAkDvCd,
        String akChdt
    );

    WsniSidingServiceChangesDvo selectCustomer(String cntrNo, String cntrSn);

    Integer selectSidingAkCount(
        String cntrNo,
        String cntrSn,
        String akSn,
        String asAkDvCd,
        String akChdt
    );

    int updateSidingAk(
        String akChdt,
        String bfchPdCd,
        String afchPdCd,
        String mtrProcsStatCd,
        String cntrNo,
        String cntrSn,
        String akSn,
        String asAkDvCd
    );

    int insertSidingAk(
        String cntrNo,
        String cntrSn,
        String asAkDvCd,
        String bfchPdCd,
        String afchPdCd,
        String mtrProcsStatCd
    );

    Integer selectBsTarget(
        String cntrNo,
        String cntrSn,
        String asnOjYm
    );

    Integer updateStopNextSiding(String cntrNo, String cntrSn, String akChdt);
}
