package com.kyowon.sms.wells.web.service.stock.service;

import com.kyowon.sms.wells.web.service.stock.converter.WsnaIndividualWareOstrConverter;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaIndividualWareOstrDvo;
import com.kyowon.sms.wells.web.service.stock.mapper.WsnaIndividualWareOstrMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaIndividualWareOstrDto.*;

@Service
@RequiredArgsConstructor
public class WsnaIndividualWareOstrService {

    private final WsnaIndividualWareOstrMapper mapper;
    private final WsnaIndividualWareOstrConverter converter;

    public List<SearchRes> getIndividualWareOstrs(SearchReq dto) {
        return this.mapper.selectIndividualWareOstrs(dto);
    }

    public List<LogisticRes> getLogistic(LogisticReq dto) {
        return this.mapper.selectLogistic(dto);
    }

    public List<ItmRes> getItemKndCode(ItmReq dto) {
        return this.mapper.selectItemKndCode(dto);
    }

    @Transactional
    public int createIndividualWareOstr(List<CreateReq> list) {
        int cnt = 0;
        String newOstrAkNo = mapper.selectNewOstrAkNoByQomOstr("360");

        List<WsnaIndividualWareOstrDvo> voList = converter.mapAllCreateReqToIndividualDvo(list);
        int len = voList.size();
        for(int i = 0; i < len; i++){
            voList.get(i).setOstrAkNo(newOstrAkNo);
        }

        cnt += mapper.insertTbSvstItmOstrAkIz(voList);
        return cnt;
    }
}
