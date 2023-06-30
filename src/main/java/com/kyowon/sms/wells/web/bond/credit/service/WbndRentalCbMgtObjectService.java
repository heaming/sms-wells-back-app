package com.kyowon.sms.wells.web.bond.credit.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.bond.credit.converter.WbndRentalCbMgtObjectConverter;
import com.kyowon.sms.wells.web.bond.credit.dto.WbndRentalCbMgtObjectDto.SearchPaymentRes;
import com.kyowon.sms.wells.web.bond.credit.dto.WbndRentalCbMgtObjectDto.SearchReq;
import com.kyowon.sms.wells.web.bond.credit.dto.WbndRentalCbMgtObjectDto.SearchRes;
import com.kyowon.sms.wells.web.bond.credit.dvo.WbndRentalCbDelinquentIzDvo;
import com.kyowon.sms.wells.web.bond.credit.mapper.WbndRentalCbMgtObjectMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import com.sds.sflex.system.config.validation.BizAssert;
import com.sds.sflex.system.config.validation.ValidAssert;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class WbndRentalCbMgtObjectService {

    private final WbndRentalCbMgtObjectMapper mapper;
    private final WbndRentalCbMgtObjectConverter converter;

    public List<SearchRes> getRentalCbMgtObjects(SearchReq dto) {
        WbndRentalCbDelinquentIzDvo reqDvo = this.converter.mapSearchReqToRentalCbDlqIzDvo(dto);
        List<WbndRentalCbDelinquentIzDvo> resList = new ArrayList<>();
        switch (dto.selGbn()) {
            case "1" -> {
                resList = this.mapper.selectRentalCbMessageObjectsByCustomer(reqDvo);
            }
            case "2" -> {
                resList = this.mapper.selectRentalCbMessageObjectsByContract(reqDvo);
            }
        }
        return this.converter.listRentalCbDlqIzDvoToSearchRes(resList);
    }

    @Transactional
    public int saveRentalCbMgtObjects(SearchReq dto) {
        int processCount = 0;

        int resultYn = this.mapper.updateMessageObjectYn(dto);
        BizAssert.isTrue(resultYn > 0, "MSG_ALT_SVE_ERR");
        processCount += resultYn;

        int resultHist = this.mapper.insertMessageObjectHist(dto);
        BizAssert.isTrue(resultHist > 0, "MSG_ALT_SVE_ERR");
        processCount += resultHist;

        return processCount;
    }

    public PagingResult<SearchPaymentRes> getRentalCbMgtPaymentInfos(String cstNo, PageInfo pageInfo) {
        ValidAssert.hasText(cstNo);
        PagingResult<SearchPaymentRes> pagingResult = new PagingResult<>();

        PagingResult<WbndRentalCbDelinquentIzDvo> res = this.mapper.selectRentalCbMgtPaymentInfos(cstNo, pageInfo);

        List<SearchPaymentRes> data = this.converter.listRentalCbDlqIzDvoToSearchPaymentRes(res.getList());
        pagingResult.setPageInfo(res.getPageInfo());
        pagingResult.setList(data);

        return pagingResult;
    }

    public List<SearchPaymentRes> getRentalCbMgtPaymentInfoForExcelDownload(String cstNo) {
        return this.converter.listRentalCbDlqIzDvoToSearchPaymentRes(this.mapper.selectRentalCbMgtPaymentInfos(cstNo));
    }
}
