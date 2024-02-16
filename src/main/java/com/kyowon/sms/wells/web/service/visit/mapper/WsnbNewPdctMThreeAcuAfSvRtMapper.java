package com.kyowon.sms.wells.web.service.visit.mapper;

import com.kyowon.sms.wells.web.service.visit.dto.WsnbNewPdctMThreeAcuAfSvRtDto.*;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WsnbNewPdctMThreeAcuAfSvRtMapper {
    List<SearchRes> selectNewPdctMThreeAcuAfSvRts(SearchReq searchReq);
    PagingResult<PdListRes> selectPdResults(PdListReq dto, PageInfo pageInfo);
    int deleteNewPdctMThreeAcuAfSvRtInfo(String pdCd);
    int insertNewPdctMThreeAcuAfSvRtInfo(SaveReq saveReq);
    int updateNewPdctMThreeAcuAfSvRtInfo(SaveReq saveReq);
    List<PdDtlListRes> selectPdDtls();
    int selectDuplicationPdCdCnt(SaveReq saveReq);
}
