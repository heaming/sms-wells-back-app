package com.kyowon.sms.wells.web.product.manage.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.kyowon.sms.common.web.product.manage.dto.ZpdcMaterialMgtDto;
import com.kyowon.sms.common.web.product.manage.dto.ZpdcMaterialMgtDto.SearchSapReq;
import com.kyowon.sms.common.web.product.manage.dto.ZpdcMaterialMgtDto.SearchSapRes;
import com.kyowon.sms.common.web.product.manage.dvo.ZpdcEachTbPdbsPdRelDvo;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

/* wpdc-product-material-mgt.xml */
@Mapper
public interface WpdcMaterialMgtMapper {

    PagingResult<SearchSapRes> selectMaterialSapPages(@Param("dto")
    SearchSapReq dto, PageInfo pageInfo);

    int deleteTbPdbsPdRel(@Param("pdCd")
    String pdCd,
        @Param("info")
        List<ZpdcMaterialMgtDto.TbPdbsPdRel> tbPdbsPdRels,
        @Param("delMode")
        String delMode
    );

    // 연결상품
    int mergeEachTbPdbsPdRel(@Param("info")
    ZpdcEachTbPdbsPdRelDvo info);

    int deleteEachPdbsPdRels(String pdCd);

}
