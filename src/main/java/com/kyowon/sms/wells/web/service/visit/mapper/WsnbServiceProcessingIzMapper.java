package com.kyowon.sms.wells.web.service.visit.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.visit.dto.WsnbServiceProcessingIzDto.Product;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbServiceProcessingIzDto.SearchReq;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbServiceProcessingIzDto.SearchRes;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

/**
 * <pre>
 * W-SV-U-0054M01 서비스처리 내역
 * </pre>
 *
 * @author hyewon.kim
 * @since 2023.03.20
 */
@Mapper
public interface WsnbServiceProcessingIzMapper {

    List<Product> selectProducts(String pdGrpCd);

    PagingResult<SearchRes> selectServiceProcessingItemizations(SearchReq dto, PageInfo pageInfo);

}
