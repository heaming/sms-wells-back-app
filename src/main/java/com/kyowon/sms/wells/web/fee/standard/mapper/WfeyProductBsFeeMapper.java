package com.kyowon.sms.wells.web.fee.standard.mapper;

import com.kyowon.sms.wells.web.fee.standard.dto.WfeyProductBsFeeDto.SearchProductBsFeeReq;
import com.kyowon.sms.wells.web.fee.standard.dto.WfeyProductBsFeeDto.SearchProductBsFeeRes;
import com.kyowon.sms.wells.web.fee.standard.dvo.WfeyProductBsFeeDvo;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WfeyProductBsFeeMapper {
    /* 상품별 BS 수수료 기준정보 조회 */
    List<SearchProductBsFeeRes> selectProductBsFeeList(SearchProductBsFeeReq req);
    /* 상품별 BS 수수료 기준정보 페이징 (미사용) */
    PagingResult<SearchProductBsFeeRes> selectProductBsFeeList(SearchProductBsFeeReq req, PageInfo pageInfo);
    /* 상품별 BS 수수료 기준정보 정합성 검증 */
    int selectValidProductBsFee(WfeyProductBsFeeDvo req);
    /* 상품별 BS 수수료 기준정보 인서트 */
    int insertProductBsFee(WfeyProductBsFeeDvo req);
    /* 상품별 BS 수수료 기준정보 업데이트 */
    int updateProductBsFee(WfeyProductBsFeeDvo req);
    /* 상품별 BS 수수료 기준정보 삭제 */
    int deleteProductBsFee(WfeyProductBsFeeDvo req);

}
