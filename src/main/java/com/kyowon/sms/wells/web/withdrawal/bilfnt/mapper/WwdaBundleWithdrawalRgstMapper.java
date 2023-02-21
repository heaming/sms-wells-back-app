package com.kyowon.sms.wells.web.withdrawal.bilfnt.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.withdrawal.bilfnt.dto.WwdaBundleWithdrawalRgstDto.SearchReq;
import com.kyowon.sms.wells.web.withdrawal.bilfnt.dto.WwdaBundleWithdrawalRgstDto.SearchRgstHistRes;
import com.kyowon.sms.wells.web.withdrawal.bilfnt.dto.WwdaBundleWithdrawalRgstDto.SearchUnrgPsRes;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WwdaBundleWithdrawalRgstMapper {

    /** 묶음출금 미등록 현황 조회
     *  
     * @param PagingResult<SearchUnrgPsRes>
     * @return SearchReq
     */
    PagingResult<SearchUnrgPsRes> selectUnregistrationPsInqrPages(SearchReq req);

    /** 묶음 출금 등록 이력 조회
     * 
     * @param PagingResult<SearchRgstHistRes>
     * @return SearchReq
     */
    PagingResult<SearchRgstHistRes> selectBundleRgstRsInqrPages(SearchReq req);

}
