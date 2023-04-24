package com.kyowon.sms.wells.web.contract.ordermgmt.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaSpectxBlkPrntDto;
import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaSpectxBlkPrntDto.SearchCntrRes;
import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaSpectxBlkPrntDto.SearchReq;
import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaSpectxBlkPrntDto.SearchRes;
import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.WctaSpectxBlkPrntDvo;

@Mapper
public interface WctaSpectxBlkPrntMapper {
    List<SearchRes> selectSpectxBlkPrnts(SearchReq dto);

    SearchCntrRes selectTradeSpcshCst(String cntrNo, String cntrSn);

    int insertSsctSpectxIsBas(@Param("item")
    WctaSpectxBlkPrntDvo dvo);

    int updateSsctSpectxIsBas(WctaSpectxBlkPrntDvo dvo);

    int updateSsctSpectxIsBasFirst(WctaSpectxBlkPrntDvo dvo);

    int insertSsctSpectxIsDtl(WctaSpectxBlkPrntDvo dvo);

    int updateSsctSpectxIsDtl(WctaSpectxBlkPrntDvo dvo);

    int insertSsctSpectxIsChHist(String spectxGrpNo);

    int insertSsctSpectxPblHist(String spectxGrpNo, String cntrNo, String cntrSn);

    int deleteSsctSpectxIsBas(String spectxGrpNo);

    int deleteSsctSpectxIsDtl(String spectxGrpNo, String cntrNo, String cntrSn);

    List<WctaSpectxBlkPrntDto.SpectxFwRes> selectTradeSpcshFwInqrs(WctaSpectxBlkPrntDto.SpectxFwReq dto);

    int insertSsctSpectxPblHistSend(String spectxGrpNo, String cntrNo, String cntrSn);

    int updateSsctSpectxIsDtlChSn(String spectxGrpNo, String cntrNo, String cntrSn);

}
