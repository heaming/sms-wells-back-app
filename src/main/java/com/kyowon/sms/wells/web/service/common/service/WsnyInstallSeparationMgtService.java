package com.kyowon.sms.wells.web.service.common.service;

import com.kyowon.sms.wells.web.service.common.converter.WsnyInstallSeparationMgtConverter;
import com.kyowon.sms.wells.web.service.common.dto.WsnyInstallSeparationMgtDto.*;
import com.kyowon.sms.wells.web.service.common.dvo.WsnyInstallSeparationMgtDvo;
import com.kyowon.sms.wells.web.service.common.mapper.WsnyInstallSeparationMgtMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
/**
 * <pre>
 * W-SV-U-0158M01 설치/분리비용 관리
 * </pre>
 *
 * @author kyunglyn.lee
 * @since 2023-04-07
 */
@Service
@RequiredArgsConstructor
public class WsnyInstallSeparationMgtService {
    private final WsnyInstallSeparationMgtMapper mapper;
    private final WsnyInstallSeparationMgtConverter converter;

    public List<FindProductsRes> getPdCd(String pdGr){
        return mapper.selectPdCd(pdGr);
    }
    public PagingResult<SearchRes> getInstallSeparationCosts(
        SearchReq dto, PageInfo pageInfo
    ){
        return mapper.selectInstallSeparationCosts(dto,pageInfo);
    }

    public List<SearchRes> getInstallSeparationCostsExcelDownload(SearchReq dto)throws Exception{
        return mapper.selectInstallSeparationCosts(dto);
    }

    @Transactional
    public int saveInstallSeparationCosts(List<SaveReq> dtos) throws Exception{
        int processCnt = 0;
        for(SaveReq dto : dtos){
            WsnyInstallSeparationMgtDvo dvo = converter.mapSaveReqToInstallSeparationMgtDvo(dto);

            processCnt += mapper.insertInstallSeparationCosts(dvo);
        }
        return processCnt;
    }

    @Transactional
    public int removeInstallSeparationCosts(List<RemoveReq> dtos) throws Exception{
        int processCnt = 0;
        for(RemoveReq dto: dtos){
            WsnyInstallSeparationMgtDvo dvo = converter.mapDeleteReqToInstallSeparationMgtDvo(dto);
            processCnt += mapper.deleteInstallSeparationCosts(dvo);
        }
        return processCnt;
    }

}
