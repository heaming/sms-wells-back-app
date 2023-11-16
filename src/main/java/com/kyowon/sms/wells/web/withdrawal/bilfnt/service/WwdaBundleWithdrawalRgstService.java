package com.kyowon.sms.wells.web.withdrawal.bilfnt.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sds.sflex.common.utils.StringUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sflex.common.common.dvo.BatchCallReqDvo;
import com.kyowon.sflex.common.common.service.BatchCallService;
import com.kyowon.sms.wells.web.withdrawal.bilfnt.converter.WwdaBundleWithdrawalRgstConverter;
import com.kyowon.sms.wells.web.withdrawal.bilfnt.dto.WwdaBundleWithdrawalRgstDto.SaveReq;
import com.kyowon.sms.wells.web.withdrawal.bilfnt.dto.WwdaBundleWithdrawalRgstDto.SearchReq;
import com.kyowon.sms.wells.web.withdrawal.bilfnt.dto.WwdaBundleWithdrawalRgstDto.SearchRgstHistRes;
import com.kyowon.sms.wells.web.withdrawal.bilfnt.dto.WwdaBundleWithdrawalRgstDto.SearchUnrgPsRes;
import com.kyowon.sms.wells.web.withdrawal.bilfnt.dvo.WwdaBundleWithdrawalRgstDvo;
import com.kyowon.sms.wells.web.withdrawal.bilfnt.mapper.WwdaBundleWithdrawalRgstMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * <pre>
 * 묶음 출금 미등록 관리 서비스
 * </pre>
 *
 * @author donghyun.yoo
 * @since 2023-02-01z
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class WwdaBundleWithdrawalRgstService {

    private final WwdaBundleWithdrawalRgstMapper mapper;
    private final WwdaBundleWithdrawalRgstConverter converter;

    private final BatchCallService batchCallService;

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

    @Transactional
    public int saveBundleRegistration(
        List<SaveReq> req
    ) throws Exception {
        for (SaveReq saveReq : req) {
            WwdaBundleWithdrawalRgstDvo dvo = converter.mapSaveReqToWwdaBundleWithdrawalRgstDvo(saveReq);
            BatchCallReqDvo batchDvo = new BatchCallReqDvo();
            Map<String, String> params = new HashMap<>();

            batchDvo.setJobKey("WSM_WD_OA0001");
            params.put("itgBilBatExcnYn", StringUtil.nvl2(dvo.getUnrgRsCd(), "")); // 변경대상 여부
            params.put("cntrNo", dvo.getCntrNo());
            params.put("cntrSn", dvo.getCntrSn());
            params.put("rcpStrtdt", dvo.getCntrPdStrtdt());
            params.put("rcpEnddt", dvo.getCntrPdEnddt());

            batchDvo.setParams(params);
            String runId = batchCallService.runJob(batchDvo);

            while (true) {
                Thread.sleep(2000);
                String jobStatus = batchCallService.getLastestJobStatus(runId);
                if ("Ended OK".equals(jobStatus) || "Ended Not OK".equals(jobStatus)) {
                    //                success = "S";
                    break;
                }
            }

            log.debug("Batch Run Id ::: " + runId);
        }

        return 1;
    }

}
