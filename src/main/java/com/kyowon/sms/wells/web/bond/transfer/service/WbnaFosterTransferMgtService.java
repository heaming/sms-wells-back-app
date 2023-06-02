package com.kyowon.sms.wells.web.bond.transfer.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.kyowon.sflex.common.common.dvo.BatchCallReqDvo;
import com.kyowon.sflex.common.common.service.BatchCallService;
import com.kyowon.sms.wells.web.bond.transfer.converter.WbnaFosterTransferMgtConverter;
import com.kyowon.sms.wells.web.bond.transfer.dto.WbnaFosterTransferMgtDto.SearchDetailRes;
import com.kyowon.sms.wells.web.bond.transfer.dto.WbnaFosterTransferMgtDto.SearchDetailSummaryRes;
import com.kyowon.sms.wells.web.bond.transfer.dto.WbnaFosterTransferMgtDto.SearchReq;
import com.kyowon.sms.wells.web.bond.transfer.dto.WbnaFosterTransferMgtDto.SearchRes;
import com.kyowon.sms.wells.web.bond.transfer.mapper.WbnaFosterTransferMgtMapper;
import com.sds.sflex.common.utils.StringUtil;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import com.sds.sflex.system.config.validation.BizAssert;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WbnaFosterTransferMgtService {
    private final WbnaFosterTransferMgtMapper mapper;
    private final WbnaFosterTransferMgtConverter converter;
    private final BatchCallService batchCallService;

    public List<SearchRes> getFosterTransfers(SearchReq dto) {
        return mapper.selectFosterTransfers(this.converter.mapSearchReqToBondContractBaseDvo(dto));
    }

    public PagingResult<SearchDetailRes> getFosterTransferDetails(SearchReq dto, PageInfo pageInfo) {
        return mapper.selectFosterTransferDetails(dto, pageInfo);
    }

    public SearchDetailSummaryRes getPartTransferDetailsSummary(
        SearchReq dto
    ) {
        return mapper.selectPartTransferDetailsSummary(dto);
    }

    // TODO: 배치 호출 예정
    public String sendFosterDataTransfer(SearchReq dto) throws Exception {
        //배치 dvo 생성
        BatchCallReqDvo batchDvo = new BatchCallReqDvo();

        Map<String, String> params = new HashMap<String, String>();

        batchDvo.setJobKey("WSM_BN_OA0001");
        params.put("baseYm", dto.baseYm()); //기준년월
        params.put("bzHdqDvCd", dto.bzHdqDvCd()); //사업부 구분
        params.put("clcoCd", dto.clcoCd()); //추심사 코드
        params.put("bndNwDvCd", dto.bndNwDvCd()); //사업부 구분
        params.put("cstNm", dto.cstNm()); // 고객명
        params.put("cstNo", dto.cstNo()); // 고객번호
        params.put("cralLocaraTno", dto.cralLocaraTno());/* 휴대지역전화번호 */
        params.put("mexnoEncr", dto.mexnoEncr());/* 휴대전화국번호암호화 */
        params.put("cralIdvTno", dto.cralIdvTno());/* 휴대개별전화번호 */
        batchDvo.setParams(params); // Job 실행시 필요한 파라미터

        String runId = batchCallService.runJob(batchDvo);
        BizAssert.isTrue(StringUtils.isNotEmpty(runId), "MSG_ALT_SVE_ERR");
        return StringUtil.isBlank(runId) ? "S" : "E";
    }

    public List<SearchDetailRes> getFosterTransferDetailsExcelDownload(SearchReq dto) {
        return mapper.selectFosterTransferDetails(dto);
    }
}
