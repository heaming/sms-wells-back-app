package com.kyowon.sms.wells.web.competence.evaluate.mapper;

import com.kyowon.sms.wells.web.competence.evaluate.dvo.WpsdWelsMngerSvCmpstIctrDvo;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WpsdWelsMngerSvCmpstIctrMapper {
    PagingResult<WpsdWelsMngerSvCmpstIctrDvo> selectWelsMngerSvCmpstIctrPages(
        WpsdWelsMngerSvCmpstIctrDvo dvo,
        PageInfo pageInfo
    );

    List<WpsdWelsMngerSvCmpstIctrDvo> selectWelsMngerSvCmpstIctrPages(
        WpsdWelsMngerSvCmpstIctrDvo dvo
    );

    PagingResult<WpsdWelsMngerSvCmpstIctrDvo> selectWelsMngerSvCmpstIctrTotalPages(
        WpsdWelsMngerSvCmpstIctrDvo dvo,
        PageInfo pageInfo
    );

    List<WpsdWelsMngerSvCmpstIctrDvo> selectWelsMngerSvCmpstIctrTotalPages(
        WpsdWelsMngerSvCmpstIctrDvo dvo
    );
}
