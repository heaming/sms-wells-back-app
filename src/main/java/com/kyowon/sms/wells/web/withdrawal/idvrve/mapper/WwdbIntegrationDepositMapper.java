package com.kyowon.sms.wells.web.withdrawal.idvrve.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbIntegrationDepositDto.SearchReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbIntegrationDepositDto.SearchRes;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WwdbIntegrationDepositMapper {
    /* 통합입금목록 조회 */
    public PagingResult<SearchRes> selectIntegrationDeposit(SearchReq dto, PageInfo pageInfo);

    /* 통합입금목록 엑셀 다운로드 */
    public List<SearchRes> selectIntegrationDeposit(SearchReq dto);
}
