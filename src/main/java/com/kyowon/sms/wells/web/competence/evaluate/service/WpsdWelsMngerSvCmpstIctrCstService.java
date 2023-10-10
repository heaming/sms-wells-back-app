package com.kyowon.sms.wells.web.competence.evaluate.service;

import com.kyowon.sms.wells.web.competence.evaluate.converter.WpsdWelsMngerSvCmpstIctrCstConverter;
import com.kyowon.sms.wells.web.competence.evaluate.dto.WpsdWelsMngerSvCmpstIctrCstDto.SearchReq;
import com.kyowon.sms.wells.web.competence.evaluate.dto.WpsdWelsMngerSvCmpstIctrCstDto.SearchRes;
import com.kyowon.sms.wells.web.competence.evaluate.dvo.WpsdWelsMngerSvCmpstIctrCstDvo;
import com.kyowon.sms.wells.web.competence.evaluate.mapper.WpsdWelsMngerSvCmpstIctrCstMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WpsdWelsMngerSvCmpstIctrCstService {

    private final WpsdWelsMngerSvCmpstIctrCstMapper mapper;
    private final WpsdWelsMngerSvCmpstIctrCstConverter converter;

    public PagingResult<SearchRes> getWelsMngerSvCmpstIctrCstPages(SearchReq dto, PageInfo pageInfo) {
        PagingResult<SearchRes> res = new PagingResult<>();
        WpsdWelsMngerSvCmpstIctrCstDvo dvo = converter.mapToDvo(dto);
        List<WpsdWelsMngerSvCmpstIctrCstDvo> list =
            mapper.selectWelsMngerSvCmpstIctrCstPages(dvo, pageInfo);

        res.setList(converter.dvoToSearchRes(list));
        res.setPageInfo(pageInfo);

        return res;
    }

    public List<SearchRes> getWelsMngerSvCmpstIctrCstsForExcelDownload(SearchReq dto) {
        WpsdWelsMngerSvCmpstIctrCstDvo dvo = converter.mapToDvo(dto);
        List<WpsdWelsMngerSvCmpstIctrCstDvo> list =
            mapper.selectWelsMngerSvCmpstIctrCstPages(dvo);
        return converter.dvoToSearchRes(list);
    }
}
