package com.kyowon.sms.wells.web.service.allocate.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.allocate.dto.WsncBsPeriodChartDto;
import com.kyowon.sms.wells.web.service.allocate.dvo.WsncBsPeriodChartDvo;

@Mapper
public interface WsncBsPeriodChartMapper {
    WsncBsPeriodChartDto.SearchBaseInfoRes selectPeriodChartBaseInfo(WsncBsPeriodChartDto.SearchReq dto);

    String selectBsPeriodChartBs03_02(WsncBsPeriodChartDvo dvo);

    String selectBsPeriodChartBs03_03(WsncBsPeriodChartDvo dvo);

    int selectBsPeriodChartBs03_04(WsncBsPeriodChartDvo dvo);

    String selectBsPeriodChartBs03_05(WsncBsPeriodChartDvo dvo);

    List<WsncBsPeriodChartDto.Search06Res> selectBsPeriodChartBs03_06(WsncBsPeriodChartDto.SearchBaseInfoRes dto);

    List<WsncBsPeriodChartDto.Search07Res> selectBsPeriodChartBs03_07(WsncBsPeriodChartDvo dvo);

    int selectBsPeriodChartBs03_08(WsncBsPeriodChartDvo dvo);

    int selectBsPeriodChartBs03_09(WsncBsPeriodChartDvo dvo);

    String selectBsPeriodChartBs03_10(String chekVstDt);

    String selectBsPeriodChartBs03_11(WsncBsPeriodChartDvo dvo);

    int selectBsPeriodChartBs03_12(WsncBsPeriodChartDvo dvo);

    int selectBsPeriodChartBs03_13(WsncBsPeriodChartDvo dvo);

    int selectBsPeriodChartBs03_14(WsncBsPeriodChartDvo dvo);

    int selectBsPeriodChartBs03_15(WsncBsPeriodChartDvo dvo);

    int selectBsPeriodChartBs03_16(WsncBsPeriodChartDvo dvo);

    int selectBsPeriodChartBs03_17(WsncBsPeriodChartDvo dvo);

    int selectBsPeriodChartBs03_18(WsncBsPeriodChartDvo dvo);

    int selectBsPeriodChartBs03_19(WsncBsPeriodChartDvo dvo);

    String selectBsPeriodChartBs03_20(WsncBsPeriodChartDvo dvo);

    String selectBsPeriodChartBs03_21(WsncBsPeriodChartDvo dvo);

    int insertBsPeriodChart(WsncBsPeriodChartDvo dvo);

    int insertBsPeriodChartError(WsncBsPeriodChartDvo dvo);

    String selectPriorityVstDt(WsncBsPeriodChartDto.SearchReq dto);

    int selectPriorityVstDiff(String priorityVstDt, int chekCyclMths);
}
