package com.kyowon.sms.wells.web.service.stock.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.service.stock.converter.WsnaIndependenceWareOstrConverter;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaIndependenceWareOstrDto.CreateReq;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaIndependenceWareOstrDto.SearchReq;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaIndependenceWareOstrDto.SearchRes;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaIndependenceWareOstrDvo;
import com.kyowon.sms.wells.web.service.stock.mapper.WsnaIndependenceWareOstrMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WsnaIndependenceWareOstrService {

    private final WsnaIndependenceWareOstrMapper mapper;
    private final WsnaIndependenceWareOstrConverter converter;

    public PagingResult<SearchRes> selectIndependenceWareOstrs(
        @Valid
        SearchReq dto,
        PageInfo pageInfo
    ) {
        return this.mapper.selectIndependenceWareOstrs(dto, pageInfo);
    }

    @Transactional
    public int createIndependenceWareOstr(List<CreateReq> list) {
        int cnt = 0;
        String newOstrAkNo = mapper.selectNewOstrAkNoByQomOstr("360");

        List<WsnaIndependenceWareOstrDvo> voList = converter.mapAllCreateReqToWsnaIndependenceWareOstrDvo(list);
        int len = voList.size();
        for(int i = 0; i < len; i++){
            voList.get(i).setOstrAkNo(newOstrAkNo);
        }

        cnt += mapper.insertTbSvstItmOstrAkIz(voList);
//        cnt += mapper.insertTbIfinItmOstrAkSendEtxt(voList);
        return cnt;
    };
}
