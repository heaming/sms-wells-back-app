package com.kyowon.sms.wells.web.service.allocate.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.allocate.dto.WsncBSPeriodChartDto;
import com.kyowon.sms.wells.web.service.allocate.dvo.WsncBSPeriodChartDvo;

@Mapper
public interface WsncBSPeriodChartMapper {
    WsncBSPeriodChartDto.SearchBaseInfoRes selectPeriodChartBaseInfo(WsncBSPeriodChartDto.SearchReq dto);

    String selectBSPeriodChartBs03_02(WsncBSPeriodChartDvo dvo);

    String selectBSPeriodChartBs03_03(WsncBSPeriodChartDvo dvo);

    int selectBSPeriodChartBs03_04(WsncBSPeriodChartDvo dvo);

    String selectBSPeriodChartBs03_05(WsncBSPeriodChartDvo dvo);

    List<WsncBSPeriodChartDto.Search06Res> selectBSPeriodChartBs03_06(WsncBSPeriodChartDto.SearchBaseInfoRes dto);

    List<WsncBSPeriodChartDto.Search07Res> selectBSPeriodChartBs03_07(WsncBSPeriodChartDvo dvo);

    int selectBSPeriodChartBs03_08(WsncBSPeriodChartDvo dvo);

    int selectBSPeriodChartBs03_09(WsncBSPeriodChartDvo dvo);

    String selectBSPeriodChartBs03_10(String chekVstDt);

    String selectBSPeriodChartBs03_11(WsncBSPeriodChartDvo dvo);

    int selectBSPeriodChartBs03_12(WsncBSPeriodChartDvo dvo);

    int selectBSPeriodChartBs03_13(WsncBSPeriodChartDvo dvo);

    int selectBSPeriodChartBs03_14(WsncBSPeriodChartDvo dvo);

    int selectBSPeriodChartBs03_15(WsncBSPeriodChartDvo dvo);

    int selectBSPeriodChartBs03_16(WsncBSPeriodChartDvo dvo);

    int selectBSPeriodChartBs03_17(WsncBSPeriodChartDvo dvo);

    int selectBSPeriodChartBs03_18(WsncBSPeriodChartDvo dvo);

    int selectBSPeriodChartBs03_19(WsncBSPeriodChartDvo dvo);

    String selectBSPeriodChartBs03_20(WsncBSPeriodChartDvo dvo);

    String selectBSPeriodChartBs03_21(WsncBSPeriodChartDvo dvo);

    int insertBSPeriodChart(WsncBSPeriodChartDvo dvo);

    int insertBSPeriodChartError(WsncBSPeriodChartDvo dvo);

    String selectPriorityVstDt(WsncBSPeriodChartDto.SearchReq dto);

    int selectPriorityVstDiff(String priorityVstDt, int chekCyclMths);
}
