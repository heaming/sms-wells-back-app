package com.kyowon.sms.wells.web.service.stock.mapper;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaSeedReleaseScheduleDto.SearchReq;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.stock.dvo.*;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WsnaSeedReleaseScheduleMapper {

    PagingResult<WsnaSeedReleaseScheduleSearchDvo> selectSeedReleaseSchedulesPaging(SearchReq dto, PageInfo pageInfo);

    List<WsnaSeedReleaseScheduleSearchDvo> selectSeedReleaseSchedulesPaging(SearchReq dto);

    List<WsnaSeedReleaseScheduleSearchDvo> selectSeedReleaseSchedulesForFlorin(SearchReq dto);

    int updateSdingSppPlanIzDpDt(WsnaSeedReleaseScheduleDvo dvo);

    Integer selectSdingSppCnfmIzCount(WsnaSeedReleaseScheduleCnfmDvo dvo);

    int insertSdingSppCnfmIz(WsnaSeedReleaseScheduleCnfmDvo dvo);

    int updateCstSvasIstAsnIzForCnfm(String cstSvAsnNo);

    int updateCstSvExcnIzForInstl(String cntrNo, int cntrSn, String istDt);

    int insertWelcomeBS(String cntrNo, int cntrSn, String istDt);

    int updateSdingSppPlanIzForCnfm(WsnaSeedReleaseScheduleCnfmDvo dvo);

    int insertSvWkOstrIzsForAS(WsnaSeedReleaseScheduleCnfmDvo dvo);

    int insertSvWkOstrIzsForBS(WsnaSeedReleaseScheduleCnfmDvo dvo);

    Integer selectCstSvWkRsIzCount(String cstSvAsnNo);

    int updateCstSvRgbsprIz(String cntrNo, int cntrSn, String vstDuedt);

    WsnaSeedReleaseScheduleAsTpDvo selectAsTpCdInfo(WsnaSeedReleaseScheduleCnfmDvo dvo);

    int updateCstSvasIstAsnIz(String cstSvAsnNo, String svBizHclsfCd, String siteAwAtcCd);

    WsnaSeedReleaseScheduleAsTpDvo selectPdGrpAtcCdInfo(String pdCd, String svBizDclsfCd);

    int updateCstSvBfSvcAsnIz(String cstSvAsnNo, String svBizHclsfCd, String siteAwAtcCd);

    int insertCstSvWkRsIz(WsnaSeedReleaseScheduleWkRsDvo dvo);

    WsnaSeedReleaseScheduleSproutDvo selectSdingSproutInfo(WsnaSeedReleaseScheduleCnfmDvo dvo);

    int insertSvWkOstrIzsForSprout(WsnaSeedReleaseScheduleSproutDvo dvo);

    int updateSdingSppPlanIzForPcsv(WsnaSeedReleaseScheduleCnfmDvo dvo);

    List<WsnaSeedReleaseScheduleAggDvo> selectSeedReleaseAggregations(SearchReq dto);

}
