package com.kyowon.sms.wells.web.withdrawal.idvrve.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbCorporationDepositSspMgtDto.SearchReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbCorporationDepositSspMgtDto.SearchRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dvo.WwdbCorporationDepositSspMgtDvo;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WwdbCorporationDepositSspMgtMapper {
    /* 법인입금 SSP제휴 조회 */
    public PagingResult<SearchRes> selectCorporationDepositSspMgt(SearchReq dto, PageInfo pageInfo);

    /* 법인입금 SSP제휴 엑셀 다운로드 */
    public List<SearchRes> selectCorporationDepositSspMgt(SearchReq dto);

    /* 법인입금 SSP제휴 저장 */
    int insertCorporationDepositSspMgt(WwdbCorporationDepositSspMgtDvo dvo);
}
