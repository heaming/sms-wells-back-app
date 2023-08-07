package com.kyowon.sms.wells.web.service.allocate.mapper;

import static com.kyowon.sms.wells.web.service.allocate.dto.WsncBsAccountByProductDto.SearchReq;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.allocate.dto.WsncBsAccountByProductDto;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WsncBsAccountByProductMapper {

    /**
     * 조회
     *
     * @param dto : { baseDt, pdGrpCd, pdCd, mgtDept, rnglGrp, branch }
     * @return 조회결과
     */
    PagingResult<WsncBsAccountByProductDto.SearchRes> selectBsAccountByProduct(SearchReq dto, PageInfo pageInfo);

    /**
     * 엑셀다운로드
     *
     * @param dto : { baseDt, pdGrpCd, pdCd, mgtDept, rnglGrp, branch }
     * @return 조회결과
     */
    List<WsncBsAccountByProductDto.SearchRes> selectBsAccountByProduct(SearchReq dto);
}
