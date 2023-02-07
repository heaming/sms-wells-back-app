package com.kyowon.sms.wells.web.withdrawal.bilfnt.service;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.withdrawal.bilfnt.converter.WwdaAftnCheckListConverter;
import com.kyowon.sms.wells.web.withdrawal.bilfnt.dto.WwdaAftnCheckListDto.SearchAftnBilNrcvListReq;
import com.kyowon.sms.wells.web.withdrawal.bilfnt.dto.WwdaAftnCheckListDto.SearchAftnBilNrcvListRes;
import com.kyowon.sms.wells.web.withdrawal.bilfnt.mapper.WwdaAftnCheckListMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import lombok.RequiredArgsConstructor;

/**
 * <pre>
 * 자동이체 미수신 체크 목록 관리 서비스
 * </pre>
 *
 * @author donghyun.yoo
 * @since 2023-02-07
 */
@Service
@RequiredArgsConstructor
public class WwdaAftnCheckListService {

    private final WwdaAftnCheckListMapper mapper;
    private final WwdaAftnCheckListConverter converter;

    /** 자동이체 미수신 체크 목록
     * 
     * @param req
     * @param pageInfo
     * @return PagingResult<SearchAftnBilNrcvListRes>
     */
    public PagingResult<SearchAftnBilNrcvListRes> getAftnBilNrcvListPages(
        SearchAftnBilNrcvListReq req, PageInfo pageInfo
    ) {
        return mapper.selectAftnBilNrcvListPages(req, pageInfo);

    }

}
