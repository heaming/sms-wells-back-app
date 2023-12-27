package com.kyowon.sms.wells.web.service.common.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.service.common.converter.WsnyPaidAsCostMgtConverter;
import com.kyowon.sms.wells.web.service.common.dto.WsnyPaidAsCostMgtDto.SaveReq;
import com.kyowon.sms.wells.web.service.common.dto.WsnyPaidAsCostMgtDto.SearchReq;
import com.kyowon.sms.wells.web.service.common.dto.WsnyPaidAsCostMgtDto.SearchRes;
import com.kyowon.sms.wells.web.service.common.dvo.WsnyPaidAsCostMgtDvo;
import com.kyowon.sms.wells.web.service.common.mapper.WsnyPaidAsCostMgtMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import lombok.RequiredArgsConstructor;

/**
 * <pre>
 * W-SV-U-0159M01 유상A/S 서비스비용 관리
 * </pre>
 *
 * @author kyunglyn.lee
 * @since 2023-03-08
 */
@Service
@RequiredArgsConstructor
public class WsnyPaidAsCostMgtService {
    private final WsnyPaidAsCostMgtMapper mapper;
    private final WsnyPaidAsCostMgtConverter converter;

    /**
     * 유상 A/S 서비스 비용 조회
     */
    public PagingResult<SearchRes> getPaidAsCostMgts(
        SearchReq dto, PageInfo pageInfo
    ){
        return mapper.selectPaidAsCostMgts(dto, pageInfo);
    }


    /**
     * 유상 A/S 서비스 비용 엑셀 다운로드
     */
    public List<SearchRes> getPaidAsCostMgtsExcelDownload(SearchReq dto)throws Exception{
        return mapper.selectPaidAsCostMgts(dto);
    }

    /**
     * 유상 A/S 서비스 비용 수정
     */
    public int savePaidAsCostMgts(List<SaveReq> dtos) throws Exception{
        int proccessCount = 0;
        for(SaveReq dto : dtos){
            WsnyPaidAsCostMgtDvo dvo = converter.mapSaveReqToWsnyPaidAsCostMgtDvo(dto);
            proccessCount += mapper.updatePaidAsCostMgts(dvo);
            proccessCount += mapper.insertPaidAsCostMgts(dvo);
        }
        return proccessCount;
    }

}
