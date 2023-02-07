package com.kyowon.sms.wells.web.withdrawal.bilfnt.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.withdrawal.bilfnt.dto.WwdaAftnCheckListDto.SearchAftnBilNrcvListReq;
import com.kyowon.sms.wells.web.withdrawal.bilfnt.dto.WwdaAftnCheckListDto.SearchAftnBilNrcvListRes;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WwdaAftnCheckListMapper {

    /** 자동이체 미수신 체크 목록
     * @param pageInfo 
     * 
     * @param SearchAftnBilNrcvListReq
     * @return PagingResult<SearchAftnBilNrcvListRes>
     */
    PagingResult<SearchAftnBilNrcvListRes> selectAftnBilNrcvListPages(SearchAftnBilNrcvListReq req, PageInfo pageInfo);

}
