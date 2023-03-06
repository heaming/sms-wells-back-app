package com.kyowon.sms.wells.web.withdrawal.idvrve.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbAutoPrepaymentDiscountExcludeMgtDto.SearchContractReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbAutoPrepaymentDiscountExcludeMgtDto.SearchContractRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbAutoPrepaymentDiscountExcludeMgtDto.SearchReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbAutoPrepaymentDiscountExcludeMgtDto.SearchRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dvo.WwdbAutoPrepaymentDiscountExcludeMgtDvo;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WwdbAutoPrepaymentDiscountExcludeMgtMapper {

    /* 자동 선납할인제외 목록 조회 */
    PagingResult<SearchRes> selectAutoPrepaymentDiscountExcludes(SearchReq dto, PageInfo pageInfo);

    /* 자동 선납할인제외 목록 엑셀 다운로드 */
    List<SearchRes> selectAutoPrepaymentDiscountExcludes(SearchReq dto);

    /* 자동 선납할인제외 목록 계약 정보 조회 */
    public SearchContractRes selectAutoPrepaymentDiscountExcludeContractInformation(
        SearchContractReq dto
    );

    /* 자동 선납할인제외 목록 등록 */
    public int insertAutoPrepaymentDiscountExcludes(WwdbAutoPrepaymentDiscountExcludeMgtDvo dvo);

    /* 자동 선납할인제외 목록 이력 저장 */
    public int insertAutoPrepaymentDiscountExcludeHistory(WwdbAutoPrepaymentDiscountExcludeMgtDvo dvo);

    /* 자동 선납할인제외 목록 수정 */
    public int updateAutoPrepaymentDiscountExcludes(WwdbAutoPrepaymentDiscountExcludeMgtDvo dvo);

    /* 자동 선납할인제외 목록 삭제 */
    public int deleteAutoPrepaymentDiscountExcludes(WwdbAutoPrepaymentDiscountExcludeMgtDvo dvo);

    /* 자동 선납할인제외 중복 체크 */
    public int selectAutoPrepaymentDiscountExcludesDuplicateCount(
        WwdbAutoPrepaymentDiscountExcludeMgtDvo dvo
    );
}
