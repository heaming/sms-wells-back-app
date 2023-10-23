package com.kyowon.sms.wells.web.competence.evaluate.service;

import com.kyowon.sms.wells.web.competence.evaluate.converter.WpsdWelsMngerSvCmpstIctrConverter;
import com.kyowon.sms.wells.web.competence.evaluate.dto.WpsdWelsMngerSvCmpstIctrDto.SearchReq;
import com.kyowon.sms.wells.web.competence.evaluate.dto.WpsdWelsMngerSvCmpstIctrDto.SearchRes;
import com.kyowon.sms.wells.web.competence.evaluate.dvo.WpsdWelsMngerSvCmpstIctrDvo;
import com.kyowon.sms.wells.web.competence.evaluate.mapper.WpsdWelsMngerSvCmpstIctrMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WpsdWelsMngerSvCmpstIctrService {

    private final WpsdWelsMngerSvCmpstIctrMapper mapper;
    private final WpsdWelsMngerSvCmpstIctrConverter converter;

    public PagingResult<SearchRes> getWelsMngerSvCmpstIctrPages(SearchReq dto, PageInfo pageInfo) {
        PagingResult<SearchRes> res = new PagingResult<>();
        WpsdWelsMngerSvCmpstIctrDvo dvo = converter.mapToDvo(dto);
        List<WpsdWelsMngerSvCmpstIctrDvo> list;
        if (dvo.getAwdDv().equals("01")) {
            list = mapper.selectWelsMngerSvCmpstIctrTotalPages(dvo, pageInfo);
        } else {
            list = mapper.selectWelsMngerSvCmpstIctrPages(dvo, pageInfo);
        }
        res.setList(converter.dvoToSearchRes(list));
        res.setPageInfo(pageInfo);

        return res;
    }

    public List<SearchRes> getWelsMngerSvCmpstIctrsForExcelDownload(SearchReq dto) {
        WpsdWelsMngerSvCmpstIctrDvo dvo = converter.mapToDvo(dto);
        List<WpsdWelsMngerSvCmpstIctrDvo> list;
        if (dvo.getAwdDv().equals("01")) {
            list = mapper.selectWelsMngerSvCmpstIctrTotalPages(dvo);
        } else {
            list = mapper.selectWelsMngerSvCmpstIctrPages(dvo);
        }
        return converter.dvoToSearchRes(list);
    }
}
