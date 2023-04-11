package com.kyowon.sms.wells.web.product.manage.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.common.web.product.manage.dto.ZpdcMaterialMgtDto.ValidationReq;
import com.kyowon.sms.wells.web.product.manage.dto.WpdcAsPartMgtDto;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

/* wpdc-as-part-mgt.xml */
@Mapper
public interface WpdcAsPartMgtMapper {

    /**
     * AS부품 목록 페이징 조회
     * @param dto
     * @param pageInfo
     * @return
     */
    PagingResult<WpdcAsPartMgtDto.SearchRes> selectAsPartPages(
        WpdcAsPartMgtDto.SearchReq dto, PageInfo pageInfo
    );

    /**
     * AS부품 목록 엑셀다운로드
     * @param dto
     * @return
     */
    List<WpdcAsPartMgtDto.SearchRes> selectAsPartPages(WpdcAsPartMgtDto.SearchReq dto);

    /**
     * 유효성 체크 조회
     * @param dto
     * @return
     */
    String selectValidation(ValidationReq dto);

}
