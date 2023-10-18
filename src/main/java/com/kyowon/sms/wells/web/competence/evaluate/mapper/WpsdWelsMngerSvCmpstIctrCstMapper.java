package com.kyowon.sms.wells.web.competence.evaluate.mapper;

import com.kyowon.sms.wells.web.competence.evaluate.dvo.WpsdWelsMngerSvCmpstIctrCstDvo;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WpsdWelsMngerSvCmpstIctrCstMapper {
    PagingResult<WpsdWelsMngerSvCmpstIctrCstDvo> selectWelsMngerSvCmpstIctrCstPages(
        WpsdWelsMngerSvCmpstIctrCstDvo dvo,
        PageInfo pageInfo
    );

    List<WpsdWelsMngerSvCmpstIctrCstDvo> selectWelsMngerSvCmpstIctrCstPages(
        WpsdWelsMngerSvCmpstIctrCstDvo dvo
    );
}
