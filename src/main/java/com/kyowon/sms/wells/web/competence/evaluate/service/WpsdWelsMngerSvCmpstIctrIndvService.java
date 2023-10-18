package com.kyowon.sms.wells.web.competence.evaluate.service;

import com.kyowon.sms.wells.web.competence.evaluate.converter.WpsdWelsMngerSvCmpstIctrIndvConverter;
import com.kyowon.sms.wells.web.competence.evaluate.dto.WpsdWelsMngerSvCmpstIctrIndvDto.SearchReq;
import com.kyowon.sms.wells.web.competence.evaluate.dto.WpsdWelsMngerSvCmpstIctrIndvDto.SearchRes;
import com.kyowon.sms.wells.web.competence.evaluate.dvo.WpsdWelsMngerSvCmpstIctrIndvDvo;
import com.kyowon.sms.wells.web.competence.evaluate.mapper.WpsdWelsMngerSvCmpstIctrIndvMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WpsdWelsMngerSvCmpstIctrIndvService {

    private final WpsdWelsMngerSvCmpstIctrIndvMapper mapper;
    private final WpsdWelsMngerSvCmpstIctrIndvConverter converter;

    public PagingResult<SearchRes> getWelsMngerSvCmpstIctrIndvPages(SearchReq dto, PageInfo pageInfo) {
        PagingResult<SearchRes> res = new PagingResult<>();
        WpsdWelsMngerSvCmpstIctrIndvDvo dvo = converter.mapToDvo(dto);
        List<WpsdWelsMngerSvCmpstIctrIndvDvo> list =
            mapper.selectWelsMngerSvCmpstIctrIndvPages(dvo, pageInfo);

        res.setList(converter.dvoToSearchRes(list));
        res.setPageInfo(pageInfo);

        return res;
    }

    public List<SearchRes> getWelsMngerSvCmpstIctrIndvsForExcelDownload(SearchReq dto) {
        WpsdWelsMngerSvCmpstIctrIndvDvo dvo = converter.mapToDvo(dto);
        List<WpsdWelsMngerSvCmpstIctrIndvDvo> list =
            mapper.selectWelsMngerSvCmpstIctrIndvPages(dvo);
        return converter.dvoToSearchRes(list);
    }
}
