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
        String choCapslCn,
        String csmrUprcAmt
    );

    int deleteSdingAskAk(
        String cntrNo,
        String cntrSn,
        String akSn,
        String asAkDvCd,
        String akChdt
    );

    WsniSidingServiceChangesDvo selectCustomer(
        String cntrNo,
        String cntrSn
    );

    int selectSidingAkCount(
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

    String selectAkSnMax(String cntrNo, String cntrSn);

    int insertSidingAk(
        String cntrNo,
        String cntrSn,
        String akSn,
        String asAkDvCd,
        String bfchPdCd,
        String afchPdCd,
        String mtrProcsStatCd
    );

    WsniSidingServiceChangesDvo selectBsTarget(
        String cntrNo,
        String cntrSn,
        String asnOjYm
    );

    int updateStopNextSiding(String cntrNo, String cntrSn, String akChdt);

    String selectPdctPdCds(
        String cntrNo,
        String cntrSn,
        String akSn
    );
}
