package com.kyowon.sms.wells.web.withdrawal.idvrve.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbAutomaticPrepaymentDiscountExcludeMgtDto.SearchContractInformationReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbAutomaticPrepaymentDiscountExcludeMgtDto.SearchContractInformationRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbAutomaticPrepaymentDiscountExcludeMgtDto.SearchReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbAutomaticPrepaymentDiscountExcludeMgtDto.SearchRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dvo.WwdbAutomaticPrepaymentDiscountExcludeMgtDvo;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WwdbAutomaticPrepaymentDiscountExcludeMgtMapper {

    /* 자동 선납할인제외 목록 조회 */
    PagingResult<SearchRes> selectAutomaticPrepaymentDiscountExcludes(SearchReq dto, PageInfo pageInfo);

    /* 자동 선납할인제외 목록 엑셀 다운로드 */
    List<SearchRes> selectAutomaticPrepaymentDiscountExcludes(SearchReq dto);

    /* 자동 선납할인제외 목록 계약 정보 조회 */
    public SearchContractInformationRes selectAutomaticPrepaymentDiscountExcludeContractInformation(
        SearchContractInformationReq dto
    );

    /* 자동 선납할인제외 목록 등록 */
    public int insertAutomaticPrepaymentDiscountExcludes(WwdbAutomaticPrepaymentDiscountExcludeMgtDvo dvo);

    /* 자동 선납할인제외 목록 이력 저장 */
    public int insertAutomaticPrepaymentDiscountExcludeHistory(WwdbAutomaticPrepaymentDiscountExcludeMgtDvo dvo);

    /* 자동 선납할인제외 목록 수정 */
    public int updateAutomaticPrepaymentDiscountExcludes(WwdbAutomaticPrepaymentDiscountExcludeMgtDvo dvo);

    /* 자동 선납할인제외 목록 삭제 */
    public int deleteAutomaticPrepaymentDiscountExcludes(WwdbAutomaticPrepaymentDiscountExcludeMgtDvo dvo);

    /* 자동 선납할인제외 중복 체크 */
    public int selectAutomaticPrepaymentDiscountExcludesDuplicateCount(
        WwdbAutomaticPrepaymentDiscountExcludeMgtDvo dvo
    );
}
