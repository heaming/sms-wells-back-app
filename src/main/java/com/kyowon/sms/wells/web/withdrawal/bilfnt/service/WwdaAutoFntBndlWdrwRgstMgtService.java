package com.kyowon.sms.wells.web.withdrawal.bilfnt.service;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.withdrawal.bilfnt.converter.WwdaAutoFntBndlWdrwRgstMgtConverter;
import com.kyowon.sms.wells.web.withdrawal.bilfnt.dto.WwdaAutoFntBndlWdrwRgstMgtDto.SearchReq;
import com.kyowon.sms.wells.web.withdrawal.bilfnt.dto.WwdaAutoFntBndlWdrwRgstMgtDto.SearchRgstHistRes;
import com.kyowon.sms.wells.web.withdrawal.bilfnt.dto.WwdaAutoFntBndlWdrwRgstMgtDto.SearchUnrgPsRes;
import com.kyowon.sms.wells.web.withdrawal.bilfnt.mapper.WwdaAutoFntBndlWdrwRgstMgtMapper;
import com.sds.sflex.system.config.datasource.PagingResult;

import lombok.RequiredArgsConstructor;

/**
 * <pre>
 * 묶음 출금 미등록 관리 서비스
 * </pre>
 *
 * @author donghyun.yoo
 * @since 2023-02-01
 */
@Service
@RequiredArgsConstructor
public class WwdaAutoFntBndlWdrwRgstMgtService {

    private final WwdaAutoFntBndlWdrwRgstMgtMapper mapper;
    private final WwdaAutoFntBndlWdrwRgstMgtConverter converter;

    /** 묶음출금 미등록 현황 조회
     * 
     * @param SearchRes
     * @return PagingResult<SearchUnrgPsRes>
     */
    public PagingResult<SearchUnrgPsRes> getUnregistrationPsInqrPages(
        SearchReq req
    ) {

        return mapper.selectUnregistrationPsInqrPages(req);
    }

    /** 묶음 출금 등록 이력 조회
     * 
     * @param SearchReq
     * @return PagingResult<SearchRgstHistRes>
     */
    public PagingResult<SearchRgstHistRes> getBundleRgstRsInqrPages(
        SearchReq req
    ) {
        return mapper.selectBundleRgstRsInqrPages(req);
    }
}
