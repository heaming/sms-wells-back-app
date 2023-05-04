package com.kyowon.sms.wells.web.bond.transfer.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.bond.transfer.converter.WbnaFosterTransferMgtConverter;
import com.kyowon.sms.wells.web.bond.transfer.dto.WbnaFosterTransferMgtDto.SearchDetailRes;
import com.kyowon.sms.wells.web.bond.transfer.dto.WbnaFosterTransferMgtDto.SearchDetailSummaryRes;
import com.kyowon.sms.wells.web.bond.transfer.dto.WbnaFosterTransferMgtDto.SearchReq;
import com.kyowon.sms.wells.web.bond.transfer.dto.WbnaFosterTransferMgtDto.SearchRes;
import com.kyowon.sms.wells.web.bond.transfer.mapper.WbnaFosterTransferMgtMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WbnaFosterTransferMgtService {
    private final WbnaFosterDataTransferService WbnaFosterDataTransferService;
    private final WbnaFosterTransferMgtMapper mapper;
    private final WbnaFosterTransferMgtConverter converter;

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
    public int sendFosterDataTransfer(SearchReq dto) {
        return 0;
    }

    public List<SearchDetailRes> getFosterTransferDetailsExcelDownload(SearchReq dto) {
        return mapper.selectFosterTransferDetails(dto);
    }
}
