package com.kyowon.sms.wells.web.bond.consultation.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.bond.consultation.dto.WbncUnpaidGuideUrgentDto.*;
import com.kyowon.sms.wells.web.bond.consultation.dvo.WbncUncollectedAdviceNoteOjIzDvo;
import com.kyowon.sms.wells.web.bond.consultation.mapper.WbncUnpaidGuideUrgentMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import com.sds.sflex.system.config.validation.BizAssert;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WbncUnpaidGuideUrgentService {

    private final WbncUnpaidGuideUrgentMapper mapper;
    //    private final WbncUnpaidGuideUrgentConverter converter;

    public PagingResult<SearchRes> getUnpaidGuideUrgentPages(SearchReq dto, PageInfo pageInfo) {
        return mapper.selectUnpaidGuideUrgentPages(dto, pageInfo);
    }

    public List<SearchRes> getUnpaidGuideUrgentsForExcelDownload(SearchReq dto) {
        return mapper.selectUnpaidGuideUrgentPages(dto);
    }

    public CheckRes checkUnpaidGuideUrgentObjects(CheckReq dto) {
        String cnfmYn = "N";
        int totalCount = 0;

        List<WbncUncollectedAdviceNoteOjIzDvo> dvos = this.mapper.selectCheckUnpaidGuideUrgentObjects(dto);
        totalCount = dvos.size();

        for (WbncUncollectedAdviceNoteOjIzDvo dvo : dvos) {
            if (dvo.getCnfmYn().equals("Y")) {
                cnfmYn = "Y";
                break;
            }
        }

        return CheckRes.builder().totalCount(totalCount).cnfmYn(cnfmYn).build();
    }

    public CheckRes checkUnpaidGuideUrgentCustomers(CheckReq dto) {
        String cnfmYn = "N";
        int totalCount = 0;

        CheckRes objectRes = this.checkUnpaidGuideUrgentObjects(dto);
        if (objectRes.cnfmYn().equals("N")) {
            return objectRes;
        }
        if (objectRes.cnfmYn().equals("Y")) {
            cnfmYn = "Y";
            totalCount = this.mapper.selectCheckUnpaidGuideUrgentCustomers(dto);
        }

        return CheckRes.builder().totalCount(totalCount).cnfmYn(cnfmYn).build();
    }

    @Transactional
    public int saveUnpaidGuideUrgentObjects(SaveObjectReq dto) {
        CheckRes objectRes = this.checkUnpaidGuideUrgentObjects(
            CheckReq.builder().ucAmtFwTpCd(dto.ucAmtFwTpCd()).ojWkDt(dto.ojWkDt()).build()
        );
        BizAssert.isFalse(objectRes.totalCount() == 0, "MSG_ALT_NO_DATA_PIZ_CREATE_DATE"); // 검색된 데이터가 없습니다. 자료생성 후 저장 버튼을 클릭해 주시기 바랍니다.
        BizAssert.isFalse(objectRes.cnfmYn().equals("Y"), "MSG_ALT_DATA_ALREADY_CNFM");

        int processCount = this.mapper.updateUnpaidGuideUrgentObjects(dto);

        BizAssert.isFalse(processCount == 0, "MSG_ALT_SAV_NO_DATA");
        BizAssert.isFalse(processCount < 0, "MSG_ALT_SVE_ERR");
        return processCount;
    }

    @Transactional
    public int createUnpaidGuideUrgentObjects(CreateObjectReq dto) {
        this.mapper.deleteAllUnpaidGuideUrgentObjects(dto);
        int processCount = this.mapper.insertUnpaidGuideUrgentObjects(dto);

        BizAssert.isFalse(processCount == 0, "MSG_ALT_SAV_NO_DATA");
        BizAssert.isFalse(processCount < 0, "MSG_ALT_SVE_ERR");
        return processCount;
    }

    @Transactional
    public int createUnpaidGuideUrgentCustomers(CreateCustomerReq dto) {
        this.mapper.deleteAllUnpaidGuideUrgentCustomers(dto);
        int processCount = this.mapper.insertUnpaidGuideUrgentCustomers(dto);

        BizAssert.isFalse(processCount == 0, "MSG_ALT_SAV_NO_DATA");
        BizAssert.isFalse(processCount < 0, "MSG_ALT_SVE_ERR");
        return processCount;
    }
}
