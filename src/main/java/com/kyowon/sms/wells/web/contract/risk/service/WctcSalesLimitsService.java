package com.kyowon.sms.wells.web.contract.risk.service;

import static com.kyowon.sms.wells.web.contract.risk.dto.WctcSalesLimitsDto.SearchBlacklistReq;
import static com.kyowon.sms.wells.web.contract.risk.dto.WctcSalesLimitsDto.SearchBlacklistRes;

import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.api.client.util.Lists;
import com.kyowon.sms.wells.web.contract.common.service.WctzAddressService;
import com.kyowon.sms.wells.web.contract.common.service.WctzTelephoneNumberService;
import com.kyowon.sms.wells.web.contract.risk.converter.WctcSalesLimitsConverter;
import com.kyowon.sms.wells.web.contract.risk.dto.WctcSalesLimitsDto.SaveBlacklistReq;
import com.kyowon.sms.wells.web.contract.risk.dto.WctcSalesLimitsDto.SearchBlacklistInfoRes;
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
    private final WctzAddressService adrService;
    private final WctzTelephoneNumberService tnoService;

    public PagingResult<SearchBlacklistRes> getBlacklistPages(SearchBlacklistReq dto, PageInfo pageInfo) {
        List<SearchBlacklistInfoRes> blacklistBeforeFilter = mapper.selectBlacklistPages(dto);
        List<SearchBlacklistRes> results = Lists.newArrayList();
        String cntrNo;
        int cntrSn;
        // java에서 검증할 조회조건 선언
        String adr = dto.adr();
        String tno = dto.tno();
        String selrInf = dto.selrInf();
        int blackCount = 1;
        for (SearchBlacklistInfoRes black : blacklistBeforeFilter) {
            cntrNo = black.sellLmCntrNo();
            cntrSn = black.sellLmCntrSn();
            SearchBlacklistRes newBlack;
            if (black.isIndv()) {
                newBlack = converter.mapBlacklistInfosToSearchBlacklistRes(
                    black,
                    tnoService.getContractorMpnoByCntr(cntrNo),
                    tnoService.getContractorTnoByCntr(cntrNo),
                    adrService.getContractorAddressByCntr(cntrNo),
                    tnoService.getInstallerMpnoByCntr(cntrNo, cntrSn),
                    tnoService.getInstallerTnoByCntr(cntrNo, cntrSn),
                    adrService.getInstallerAddressByCntr(cntrNo, cntrSn),
                    tnoService.getPartnerMpnoByCntr(cntrNo)
                );
            } else {
                newBlack = converter.mapBlacklistInfosToSearchBlacklistRes(
                    black,
                    tnoService.getContractorTnoByCntr(cntrNo),
                    adrService.getContractorAddressByCntr(cntrNo),
                    tnoService.getInstallerTnoByCntr(cntrNo, cntrSn),
                    adrService.getInstallerAddressByCntr(cntrNo, cntrSn)
                );
            }
            // 조회된 데이터에서 전화번호, 주소, 판매자정보(이름, 사번, 연락처) 조건 filtering
            // 해당하면 리스트에 추가((페이지인덱스-1)*페이지사이즈 < blackCount <= 페이지인덱스*페이지사이즈 인 경우)
            if (newBlack.cntrAdr().contains(adr) || newBlack.cntrZip().contains(adr)
                || newBlack.cntrMpno().contains(tno) || newBlack.cntrTno().contains(tno)
                || newBlack.prtnrKnm().contains(selrInf) || newBlack.prtnrNo().contains(selrInf)
                || newBlack.prtnrMpno().contains(selrInf)) {
                if (((pageInfo.getPageIndex() - 1) * pageInfo.getPageSize() < blackCount)
                    && (pageInfo.getPageIndex() * pageInfo.getPageSize() >= blackCount)) {
                    results.add(newBlack);
                }
                blackCount++;
            }
        }
        // pageInfo에 totalcount 세팅
        pageInfo.setTotalCount(Long.valueOf(blackCount));
        return new PagingResult(results, pageInfo);
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
