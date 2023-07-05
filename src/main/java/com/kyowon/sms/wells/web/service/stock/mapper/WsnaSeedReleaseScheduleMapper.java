package com.kyowon.sms.wells.web.service.stock.mapper;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaSeedReleaseScheduleDto.SearchReq;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.stock.dvo.WsnaSeedReleaseScheduleAsTpDvo;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaSeedReleaseScheduleCnfmDvo;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaSeedReleaseScheduleDvo;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaSeedReleaseScheduleSearchDvo;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WsnaSeedReleaseScheduleMapper {

    PagingResult<WsnaSeedReleaseScheduleSearchDvo> selectSeedReleaseSchedulesPaging(SearchReq dto, PageInfo pageInfo);

    List<WsnaSeedReleaseScheduleSearchDvo> selectSeedReleaseSchedulesPaging(SearchReq dto);

    int updateSdingSppPlanIzDpDt(WsnaSeedReleaseScheduleDvo dvo);

    int insertSdingSppCnfmIz(WsnaSeedReleaseScheduleCnfmDvo dvo);

    int updateCstSvasIstAsnIzForCnfm(String cstSvAsnNo);

    int updateCstSvasIstAsnIzForInstl(String sdingMcnrCntrNo);

    int updateSdingSppPlanIzForCnfm(WsnaSeedReleaseScheduleCnfmDvo dvo);

    int insertSvWkOstrIzs(WsnaSeedReleaseScheduleCnfmDvo dvo);

    Integer selectCstSvWkRsIzCount(String cstSvAsnNo);

    WsnaSeedReleaseScheduleAsTpDvo selectAsTpCdInfo(WsnaSeedReleaseScheduleCnfmDvo dvo);

    int updateCstSvasIstAsnIz(String cstSvAsnNo, String svBizHclsfCd, String siteAwAtcCd);

    int insertCstSvWkRsIz(WsnaSeedReleaseScheduleCnfmDvo dvo, WsnaSeedReleaseScheduleAsTpDvo asTpDvo);

    int updateSdingSppPlanIzForPcsv(WsnaSeedReleaseScheduleCnfmDvo dvo);

}
