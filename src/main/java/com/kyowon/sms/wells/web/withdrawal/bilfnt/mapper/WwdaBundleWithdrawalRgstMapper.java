package com.kyowon.sms.wells.web.withdrawal.bilfnt.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.withdrawal.bilfnt.dto.WwdaBundleWithdrawalRgstDto.SearchReq;
import com.kyowon.sms.wells.web.withdrawal.bilfnt.dto.WwdaBundleWithdrawalRgstDto.SearchRgstHistRes;
import com.kyowon.sms.wells.web.withdrawal.bilfnt.dto.WwdaBundleWithdrawalRgstDto.SearchUnrgPsRes;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WwdaBundleWithdrawalRgstMapper {

    /** 묶음출금 미등록 현황 조회
     * @param pageInfo 
     *  
     * @param PagingResult<SearchUnrgPsRes>
     * @return SearchReq
     */
    PagingResult<SearchUnrgPsRes> selectUnregistrationPsInqrPages(SearchReq req, PageInfo pageInfo);

    /** 묶음출금 미등록 현황 엑셀다운로드
     * 
     * @param req
     * @return
     */
    List<SearchUnrgPsRes> selectUnregistrationPsInqrPages(
        SearchReq req
    );

    /** 묶음 출금 등록 이력 조회
     * @param pageInfo 
     * 
     * @param PagingResult<SearchRgstHistRes>
     * @return SearchReq
     */
    PagingResult<SearchRgstHistRes> selectBundleRgstRsInqrPages(SearchReq req, PageInfo pageInfo);

    /** 묶음 출금 등록 이력 엑셀다운로드
     * 
     * @param req
     * @return
     */
    List<SearchRgstHistRes> selectBundleRgstRsInqrPages(SearchReq req);

}
