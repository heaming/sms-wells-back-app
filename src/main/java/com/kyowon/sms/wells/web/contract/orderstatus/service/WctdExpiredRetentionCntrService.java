package com.kyowon.sms.wells.web.contract.orderstatus.service;

import static com.kyowon.sms.wells.web.contract.orderstatus.dto.WctdExpiredRetentionCntrDto.SearchReq;
import static com.kyowon.sms.wells.web.contract.orderstatus.dto.WctdExpiredRetentionCntrDto.SearchRes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import com.google.api.client.util.Lists;
import com.kyowon.sms.wells.web.contract.orderstatus.converter.WctdExpiredRetentionCntrConverter;
import com.kyowon.sms.wells.web.contract.orderstatus.dvo.WctdExpiredRetentionCntrDvo;
import com.kyowon.sms.wells.web.contract.orderstatus.mapper.WctdExpiredRetentionCntrMapper;
import com.kyowon.sms.wells.web.contract.zcommon.constants.CtContractConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WctdExpiredRetentionCntrService {
    private final WctdExpiredRetentionCntrMapper mapper;
    private final WctdExpiredRetentionCntrConverter converter;

    @Transactional
    public ArrayList<SearchRes> setMshCntrInfo(List<WctdExpiredRetentionCntrDvo> dvos) {
        ArrayList<SearchRes> cntrs = Lists.newArrayList();
        for (WctdExpiredRetentionCntrDvo cntr : dvos) {
            WctdExpiredRetentionCntrDvo mshCntrInfo = mapper.getMembershipCntrInfo(
                cntr.getCntrNo(), cntr.getCntrSn(), Arrays.asList(CtContractConst.CntrStatCd.CANCELLATION.getDtlCds())
            );
            if (!ObjectUtils.isEmpty(mshCntrInfo)) {
                cntr.setMshCntrNo(mshCntrInfo.getMshCntrNo());
                cntr.setMshCntrSn(mshCntrInfo.getMshCntrSn());
                cntr.setMshCntrDt(mshCntrInfo.getMshCntrDt());
                cntr.setMshCanDt(mshCntrInfo.getMshCanDt());
                cntr.setMshWdwalDt(mshCntrInfo.getMshWdwalDt());
                cntr.setCntrtCralLocaraTno(mshCntrInfo.getCntrtCralLocaraTno());
                cntr.setCntrtMexnoEncr(mshCntrInfo.getCntrtMexnoEncr());
                cntr.setCntrtCralIdvTno(mshCntrInfo.getCntrtCralIdvTno());
                cntr.setIstllCralLocaraTno(mshCntrInfo.getIstllCralLocaraTno());
                cntr.setIstllMexnoEncr(mshCntrInfo.getIstllMexnoEncr());
                cntr.setIstllCralIdvTno(mshCntrInfo.getIstllCralIdvTno());
            }
            cntrs.add(converter.mapWctdExpiredRetentionCntrDvoToSearchRes(cntr));
        }
        return cntrs;
    }

    @Transactional
    public PagingResult<SearchRes> getExpiredRetentionCntrPages(SearchReq dto, PageInfo pageInfo) {
        PagingResult<WctdExpiredRetentionCntrDvo> result = mapper.selectExpiredRetentionCntrPages(dto, pageInfo);
        return new PagingResult(setMshCntrInfo(result.getList()), result.getPageInfo());
    }

    @Transactional
    public List<SearchRes> getExpiredRetentionCntrsForExcelDownload(SearchReq dto) {
        List<WctdExpiredRetentionCntrDvo> result = mapper.selectExpiredRetentionCntrPages(dto);
        return setMshCntrInfo(result);
    }
}
