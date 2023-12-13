package com.kyowon.sms.wells.web.service.allocate.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.service.allocate.converter.WsncFixationVisitConverter;
import com.kyowon.sms.wells.web.service.allocate.dto.WsncFixationVisitDto;
import com.kyowon.sms.wells.web.service.allocate.dvo.WsncFixationVisitDvo;
import com.kyowon.sms.wells.web.service.allocate.mapper.WsncFixationVisitMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import lombok.RequiredArgsConstructor;

/**
 * <pre>
 * [WSNC] 고정방문 관리
 * </pre>
 *
 * @author  juno.cha
 * @since 2022-12-26
 */
@Service
@RequiredArgsConstructor
@Transactional
public class WsncFixationVisitService {
    private final WsncFixationVisitMapper wwsncFixationVisitMgntMapper;

    private final WsncFixationVisitConverter wsncFixationVisitConverter;

    public PagingResult<WsncFixationVisitDto.SearchRes> getFixationVisits(
        WsncFixationVisitDto.SearchReq dto, PageInfo pageInfo
    ) {
//        if("test1".equals(dto.fxnPrtnrNo())){
//            wwsncFixationVisitMgntMapper.selectFixationVisits2(dto, pageInfo);
//        } else if("test2".equals(dto.fxnPrtnrNo())){
//            wwsncFixationVisitMgntMapper.selectFixationVisits2(dto);
//        } else if("test3".equals(dto.fxnPrtnrNo())){
//            wwsncFixationVisitMgntMapper.selectFixationVisits3(dto, pageInfo);
//        } else if("test4".equals(dto.fxnPrtnrNo())){
//            wwsncFixationVisitMgntMapper.selectFixationVisits3(dto);
//        } else if("test5".equals(dto.fxnPrtnrNo())){
//            wwsncFixationVisitMgntMapper.selectFixationVisits4(dto, pageInfo);
//        } else if("test6".equals(dto.fxnPrtnrNo())){
//            wwsncFixationVisitMgntMapper.selectFixationVisits4(dto);
//        } else if("test7".equals(dto.fxnPrtnrNo())){
//            wwsncFixationVisitMgntMapper.selectFixationVisits5(dto, pageInfo);
//        } else if(StringUtils.isNotEmpty(dto.fxnPrtnrNo()) && dto.fxnPrtnrNo().startsWith("test-")){
//            wwsncFixationVisitMgntMapper.selectFixationVisits6(dto);
//        }
        return wwsncFixationVisitMgntMapper.selectFixationVisits(dto, pageInfo);
    }

    public List<WsncFixationVisitDto.SearchRes> getFixationVisitsExcelDownload(
        WsncFixationVisitDto.SearchReq dto
    ) {
        return wwsncFixationVisitMgntMapper.selectFixationVisits(dto);
    }

    public WsncFixationVisitDto.SearchRegRes getFixationVisit(
        WsncFixationVisitDto.SearchRegReq dto
    ) {
        return wwsncFixationVisitMgntMapper.selectFixationVisit(dto);
    }

    public int saveFixationVisit(WsncFixationVisitDto.SaveRegReq dto) throws Exception {
        WsncFixationVisitDvo dvo = wsncFixationVisitConverter.mapFixationVisitReqToFixationVisitDvo(dto);

        //update (USE_YN = 'N' setting)
        if (StringUtils.isNotEmpty(dvo.getChSn())) {
            wwsncFixationVisitMgntMapper.updateFixationVisit(dvo);
        }

        //insert (CH_SN 채번 후 insert)
        return wwsncFixationVisitMgntMapper.insertFixationVisit(dvo);
    }
}
