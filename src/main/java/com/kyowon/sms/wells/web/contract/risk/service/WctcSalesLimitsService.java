package com.kyowon.sms.wells.web.contract.risk.service;

import static com.kyowon.sms.wells.web.contract.risk.dto.WctcSalesLimitsDto.SearchBlacklistReq;
import static com.kyowon.sms.wells.web.contract.risk.dto.WctcSalesLimitsDto.SearchBlacklistRes;

import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.contract.risk.converter.WctcSalesLimitsConverter;
import com.kyowon.sms.wells.web.contract.risk.dto.WctcSalesLimitsDto.FindBlacklistRes;
import com.kyowon.sms.wells.web.contract.risk.dto.WctcSalesLimitsDto.SaveBlacklistReq;
import com.kyowon.sms.wells.web.contract.risk.dvo.WctcSellLimitOjIzDvo;
import com.kyowon.sms.wells.web.contract.risk.mapper.WctcSalesLimitsMapper;
import com.sds.sflex.system.config.constant.CommConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import com.sds.sflex.system.config.exception.BizException;
import com.sds.sflex.system.config.validation.BizAssert;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WctcSalesLimitsService {

    private final WctcSalesLimitsMapper mapper;
    private final WctcSalesLimitsConverter converter;

    public FindBlacklistRes getBlacklistInfos(String cntrNo, int cntrSn) {
        return mapper.selectBlacklistInfos(cntrNo, cntrSn);
    }

    public PagingResult<SearchBlacklistRes> getBlacklistPages(SearchBlacklistReq dto, PageInfo pageInfo) {
        return mapper.selectBlacklistPages(dto, pageInfo);
    }

    public List<SearchBlacklistRes> getBlacklistsForExcelDownload(SearchBlacklistReq dto) {
        return mapper.selectBlacklistPages(dto);
    }

    @Transactional
    public int saveBlacklists(List<SaveBlacklistReq> dtos) {
        int processCount = 0;
        Iterator<SaveBlacklistReq> iterator = dtos.iterator();
        while (iterator.hasNext()) {
            SaveBlacklistReq dto = iterator.next();
            WctcSellLimitOjIzDvo dvo = converter.mapSaveBlacklistReqToWctcSellLimitOjIzDvo(dto);
            processCount += switch (dto.rowState()) {
                case CommConst.ROW_STATE_CREATED -> mapper.insertBlacklist(dvo);
                case CommConst.ROW_STATE_UPDATED -> {
                    int result = mapper.updateBlacklist(dvo);
                    BizAssert.isTrue(result == 1, "MSG_ALT_SVE_ERR");
                    yield result;
                }
                default -> throw new BizException("MSG_ALT_UNHANDLE_ROWSTATE");
            };
        }
        return processCount;
    }

    @Transactional
    public int removeBlacklists(List<String> sellLmIds) {
        int processCount = 0;
        int result;
        for (Iterator<String> iterator = sellLmIds.iterator(); iterator.hasNext(); processCount += result) {
            result = mapper.deleteBlacklist(iterator.next());
            BizAssert.isTrue(result == 1, "MSG_ALT_SVE_ERR");
        }
        return processCount;
    }
}
