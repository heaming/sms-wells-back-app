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

/**
 * <pre>
 * W-BN-U-0031M01	미납요금 안내/촉구 대상
 * </pre>
 *
 * @author gs.piit128 gilyong.han
 * @since 2023-07-06
 */
@Service
@RequiredArgsConstructor
public class WbncUnpaidGuideUrgentService {
    private final WbncUnpaidGuideUrgentMapper mapper;

    /**
     * 미납요금 안내/촉구 대상 페이징 조회
     * @param dto, pageInfo
     * @return PagingResult<SearchRes>
     */
    public PagingResult<SearchRes> getUnpaidGuideUrgentPages(SearchReq dto, PageInfo pageInfo) {
        return mapper.selectUnpaidGuideUrgentPages(dto, pageInfo);
    }

    /**
     * 미납요금 안내/촉구 대상 엑셀 다운로드
     * @param dto
     * @return List<SearchRes>
     */
    public List<SearchRes> getUnpaidGuideUrgentsForExcelDownload(SearchReq dto) {
        return mapper.selectUnpaidGuideUrgentPages(dto);
    }

    /**
     * 미납요금 안내/촉구 대상 대상별 확정여부 체크
     * @param dto
     * @return CheckRes
     */
    public CheckRes checkUnpaidGuideUrgentObjects(CheckReq dto) {
        String cnfmYn = "N";
        int ojTotalCount;

        List<WbncUncollectedAdviceNoteOjIzDvo> dvos = this.mapper.selectCheckUnpaidGuideUrgentObjects(dto);
        ojTotalCount = dvos.size();

        for (WbncUncollectedAdviceNoteOjIzDvo dvo : dvos) {
            if (dvo.getCnfmYn().equals("Y")) {
                cnfmYn = "Y";
                break;
            }
        }

        return CheckRes.builder().ojTotalCount(ojTotalCount).cnfmYn(cnfmYn).build();
    }

    /**
     * 미납요금 안내/촉구 대상 고객별 확정여부 체크
     * @param dto
     * @return CheckRes
     */
    public CheckRes checkUnpaidGuideUrgentCustomers(CheckReq dto) {
        String cnfmYn = "N";
        int totalCount = 0;
        int ojTotalCount = 0;
        CheckRes objectRes = this.checkUnpaidGuideUrgentObjects(dto);
        if (objectRes.cnfmYn().equals("N")) {
            return objectRes;
        }
        if (objectRes.cnfmYn().equals("Y")) {
            cnfmYn = "Y";
            totalCount = this.mapper.selectCheckUnpaidGuideUrgentCustomers(dto);
            ojTotalCount = objectRes.ojTotalCount();
        }

        return CheckRes.builder().ojTotalCount(ojTotalCount).totalCount(totalCount).cnfmYn(cnfmYn).build();
    }

    /**
     * 미납요금 안내/촉구 대상 확정
     * @param dto
     * @return int
     */
    @Transactional
    public int saveUnpaidGuideUrgentObjects(SaveObjectReq dto) {
        CheckRes objectRes = this.checkUnpaidGuideUrgentObjects(
            CheckReq.builder().ucAmtFwTpCd(dto.ucAmtFwTpCd()).ojWkDt(dto.ojWkDt()).build()
        );
        BizAssert.isFalse(objectRes.ojTotalCount() == 0, "MSG_ALT_NO_DATA_PIZ_CREATE_DATE"); // 검색된 데이터가 없습니다. 자료생성 후 저장 버튼을 클릭해 주시기 바랍니다.
        BizAssert.isFalse(objectRes.cnfmYn().equals("Y"), "MSG_ALT_DATA_ALREADY_CNFM");

        int processCount = this.mapper.updateUnpaidGuideUrgentObjects(dto);

        BizAssert.isFalse(processCount == 0, "MSG_ALT_SAV_NO_DATA");
        BizAssert.isFalse(processCount < 0, "MSG_ALT_SVE_ERR");
        return processCount;
    }

    /**
     * 미납요금 안내/촉구 대상 대상별 자료생성
     * @param dto
     * @return int
     */
    @Transactional
    public int createUnpaidGuideUrgentObjects(CreateObjectReq dto) {
        this.mapper.deleteAllUnpaidGuideUrgentObjects(dto);
        int processCount = this.mapper.insertUnpaidGuideUrgentObjects(dto);

        BizAssert.isFalse(processCount == 0, "MSG_ALT_SAV_NO_DATA");
        BizAssert.isFalse(processCount < 0, "MSG_ALT_SVE_ERR");
        return processCount;
    }

    /**
     * 미납요금 안내/촉구 대상 고객별 자료생성
     * @param dto
     * @return int
     */
    @Transactional
    public int createUnpaidGuideUrgentCustomers(CreateCustomerReq dto) {
        this.mapper.deleteAllUnpaidGuideUrgentCustomers(dto);
        int processCount = this.mapper.insertUnpaidGuideUrgentCustomers(dto);

        BizAssert.isFalse(processCount == 0, "MSG_ALT_SAV_NO_DATA");
        BizAssert.isFalse(processCount < 0, "MSG_ALT_SVE_ERR");
        return processCount;
    }
}
