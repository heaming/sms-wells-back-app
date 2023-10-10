package com.kyowon.sms.wells.web.competence.evaluate.mapper;

import com.kyowon.sms.wells.web.competence.evaluate.dvo.WpsdWelsMngerSvCmpstIctrIndvDvo;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WpsdWelsMngerSvCmpstIctrIndvMapper {
    PagingResult<WpsdWelsMngerSvCmpstIctrIndvDvo> selectWelsMngerSvCmpstIctrIndvPages(
        WpsdWelsMngerSvCmpstIctrIndvDvo dvo,
        PageInfo pageInfo
    );

    List<WpsdWelsMngerSvCmpstIctrIndvDvo> selectWelsMngerSvCmpstIctrIndvPages(
        WpsdWelsMngerSvCmpstIctrIndvDvo dvo
    );
}
