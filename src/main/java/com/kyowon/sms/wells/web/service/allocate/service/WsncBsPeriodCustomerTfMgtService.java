package com.kyowon.sms.wells.web.service.allocate.service;

import com.kyowon.sms.wells.web.service.allocate.converter.WsncBsPeriodCustomerTfConverter;
import com.kyowon.sms.wells.web.service.allocate.dto.WsncBsPeriodCustomerTfDto.SearchRes;
import com.kyowon.sms.wells.web.service.allocate.dto.WsncBsPeriodCustomerTfDto.SearchReq;
import com.kyowon.sms.wells.web.service.allocate.dto.WsncBsPeriodCustomerTfDto.CreateTfReq;
import com.kyowon.sms.wells.web.service.allocate.dto.WsncBsPeriodCustomerTfDto.BranchsAndServiceCentersRes;
import com.kyowon.sms.wells.web.service.allocate.dto.WsncBsPeriodCustomerTfDto.ManagersAndEngineersRes;
import com.kyowon.sms.wells.web.service.allocate.dvo.WsncBsPeriodCustomerTfCreateDvo;
import com.kyowon.sms.wells.web.service.allocate.mapper.WsncBsPeriodCustomerTfMgtMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import com.sds.sflex.system.config.validation.BizAssert;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class WsncBsPeriodCustomerTfMgtService {

    private final WsncBsPeriodCustomerTfMgtMapper mapper;

    private final WsncBsPeriodCustomerTfConverter converter;

    public PagingResult<SearchRes> getBsPeriodCustomers(
        SearchReq dto,
        PageInfo pageInfo
    ) {
        return mapper.selectBsPeriodCustomers(dto, pageInfo);
    }

    public List<SearchRes> getBsPeriodCustomersForExcelDownload(
        SearchReq dto
    ) {
        return mapper.selectBsPeriodCustomers(dto);
    }

    public List<BranchsAndServiceCentersRes> getBranchsAndServiceCenters(
        String ogTpCd, String ogId, String dgr1LevlOgId, String dgr2LevlOgId
    ) {
        return mapper.selectBranchsAndServiceCenters(ogTpCd, ogId, dgr1LevlOgId, dgr2LevlOgId);
    }

    public List<ManagersAndEngineersRes> getManagersAndEngineers(String ogId) {
        return mapper.selectManagersAndEngineers(ogId);
    }

    @Transactional
    public int createTransfer(List<CreateTfReq> dtos) throws Exception {
        int cnt = 0;
        for (CreateTfReq dto : dtos) {
            WsncBsPeriodCustomerTfCreateDvo dvo = converter.mapCreateTfReqToBsPeriodCustomerTfCreateDvo(dto);

            String baseYm = dto.baseYm();
            String bfchIchrBrchOgId = dto.bfchIchrBrchOgId();
            String afchIchrBrchOgId = dto.afchIchrBrchOgId();

            String asnTfDvCd = mapper.selectAsnTfDvCd(baseYm, bfchIchrBrchOgId, afchIchrBrchOgId);
            BizAssert.notNull(asnTfDvCd, "배정이관구분코드를 조회할 수 없습니다.");

            dvo.setAsnTfDvCd(asnTfDvCd);
            cnt += mapper.insertTransfer(dvo);
        }
        return cnt;
    }

    @Transactional
    public int saveTransferConfirm(List<CreateTfReq> dtos) throws Exception {
        int cnt = 0;
        for (CreateTfReq dto : dtos) {
            WsncBsPeriodCustomerTfCreateDvo dvo = converter.mapCreateTfReqToBsPeriodCustomerTfCreateDvo(dto);

            String baseYm = dto.baseYm();
            String bfchIchrBrchOgId = dto.bfchIchrBrchOgId();
            String afchIchrBrchOgId = dto.afchIchrBrchOgId();

            String asnTfDvCd = mapper.selectAsnTfDvCd(baseYm, bfchIchrBrchOgId, afchIchrBrchOgId);
            BizAssert.notNull(asnTfDvCd, "배정이관구분코드를 조회할 수 없습니다.");

            dvo.setAsnTfDvCd(asnTfDvCd);
            cnt += mapper.mergeTransferConfirm(dvo);
        }
        return cnt;
    }
}
