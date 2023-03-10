package com.kyowon.sms.wells.web.withdrawal.bilfnt.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.withdrawal.bilfnt.dto.WwdaBundleWithdrawalRgstDto.SearchReq;
import com.kyowon.sms.wells.web.withdrawal.bilfnt.dto.WwdaBundleWithdrawalRgstDto.SearchRgstHistRes;
import com.kyowon.sms.wells.web.withdrawal.bilfnt.dto.WwdaBundleWithdrawalRgstDto.SearchUnrgPsRes;
import com.kyowon.sms.wells.web.withdrawal.bilfnt.mapper.WwdaBundleWithdrawalRgstMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import lombok.RequiredArgsConstructor;

/**
 * <pre>
 * 묶음 출금 미등록 관리 서비스
 * </pre>
 *
 * @author donghyun.yoo
 * @since 2023-02-01z
 */
@Service
@RequiredArgsConstructor
public class WwdaBundleWithdrawalRgstService {

    private final WwdaBundleWithdrawalRgstMapper mapper;

    /** 묶음출금 미등록 현황 조회
     * @param pageInfo 
     * 
     * @param SearchRes
     * @return PagingResult<SearchUnrgPsRes>
     */
    public PagingResult<SearchUnrgPsRes> getUnregistrationPsInqrPages(
        SearchReq req,
        PageInfo pageInfo
    ) {

        return mapper.selectUnregistrationPsInqrPages(req, pageInfo);
    }

    /** 묶음출금 미등록 현황 엑셀다운로드
     * 
     * @param req
     * @return
     */
    public List<SearchUnrgPsRes> getUnregistrationPsInqrExcels(
        SearchReq req
    ) {
        return mapper.selectUnregistrationPsInqrPages(req);
    }

    /** 묶음 출금 등록 이력 조회
     * @param pageInfo 
     * 
     * @param SearchReq
     * @return PagingResult<SearchRgstHistRes>
     */
    public PagingResult<SearchRgstHistRes> getBundleRgstRsInqrPages(
        SearchReq req,
        PageInfo pageInfo
    ) {
        return mapper.selectBundleRgstRsInqrPages(req, pageInfo);
    }

    /** 묶음 출금 등록 이력 엑셀다운로드
     * 
     * @param req
     * @return
     */
    public List<SearchRgstHistRes> getBundleRgstRsInqrPages(
        SearchReq req
    ) {
        return mapper.selectBundleRgstRsInqrPages(req);
    }

}
