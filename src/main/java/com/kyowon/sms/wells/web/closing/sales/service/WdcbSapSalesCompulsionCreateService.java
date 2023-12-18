package com.kyowon.sms.wells.web.closing.sales.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.closing.sales.converter.WdcbSapSalesCompulsionCreateConverter;
import com.kyowon.sms.wells.web.closing.sales.dto.WdcbSapSalesCompulsionCreateDto.RemoveReq;
import com.kyowon.sms.wells.web.closing.sales.dto.WdcbSapSalesCompulsionCreateDto.SaveReq;
import com.kyowon.sms.wells.web.closing.sales.dto.WdcbSapSalesCompulsionCreateDto.SearchRes;
import com.kyowon.sms.wells.web.closing.sales.dto.WdcbSapSalesCompulsionCreateDto.SearchSapMapNmRes;
import com.kyowon.sms.wells.web.closing.sales.dvo.WdcbSapSalesCompulsionCreateDvo;
import com.kyowon.sms.wells.web.closing.sales.mapper.WdcbSapSalesCompulsionCreateMapper;
import com.sds.sflex.system.config.constant.CommConst;
import com.sds.sflex.system.config.exception.BizException;
import com.sds.sflex.system.config.validation.BizAssert;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * <pre>
 * SAP매출강제생성 서비스
 * </pre>
 *
 * @author WOO SEUNGMIN
 * @since 2023-10-25
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class WdcbSapSalesCompulsionCreateService {
    private final WdcbSapSalesCompulsionCreateMapper mapper;
    private final WdcbSapSalesCompulsionCreateConverter converter;

    /**
     * 자재명 조회
     * @param zsmtrl SAP자재코드
     * @return 자재명
     */
    public SearchSapMapNmRes getSapMapNm(String zsmtrl) {
        return mapper.selectSapMapNm(zsmtrl);
    }

    /**
     * SAP매출 조회
     * @param baseYm 기준년월
     * @return 내역
     */
    public List<SearchRes> getSapSales(String baseYm) {
        return mapper.selectSapSales(baseYm);
    }

    /**
     * SAP매출강제생성 수정(저장)
     */
    @Transactional
    public int saveSapSalesCompulsionCreates(List<SaveReq> dtos) {
        int result = 0;
        WdcbSapSalesCompulsionCreateDvo baseInfoDvo = converter
            .mapSaveReqToWdcbSapSalesCompulsionCreateDvo(dtos.get(0));
        log.info("baseInfoDvo:" + baseInfoDvo);
        String pkResult = mapper.selectByPK(baseInfoDvo);

        int cnt = 0;
        for (SaveReq dto : dtos) {
            WdcbSapSalesCompulsionCreateDvo dvo = converter.mapSaveReqToWdcbSapSalesCompulsionCreateDvo(dto);
            cnt += 1;
            switch (dto.rowState()) {
                case CommConst.ROW_STATE_CREATED -> {

                    dvo.setZsslrq(pkResult);
                    result = mapper.insertIfinSlInfAtcIz(dvo);
                    BizAssert.isTrue(result == 1, "MSG_ALT_SVE_ERR");

                    log.info("dtos.size():" + dtos.size());
                    log.info("cnt:" + cnt);
                    if (cnt == dtos.size()) {
                        result = mapper.insertIfinSlInfHdrBas(dvo);
                        BizAssert.isTrue(result == 1, "MSG_ALT_SVE_ERR");
                    }
                }
                case CommConst.ROW_STATE_UPDATED -> {
                    result = mapper.updateIfinSlInfAtcIz(dvo);
                    BizAssert.isTrue(result == 1, "MSG_ALT_SVE_ERR");

                    log.info("dtos.size():" + dtos.size());
                    log.info("cnt:" + cnt);

                    if (cnt == dtos.size()) {
                        result = mapper.updateIfinSlInfHdrBas(dvo);
                        BizAssert.isTrue(result == 1, "MSG_ALT_SVE_ERR");
                    }
                }
                default -> throw new BizException("MSG_ALT_UNHANDLE_ROWSTATE");
            }
        }

        return result;
    }

    /**
     * SAP매출강제생성 삭제
     */
    @Transactional
    public int removeSapSalesCompulsionCreates(List<RemoveReq> dtos) {
        int processCount = 0;
        int result = 0;
        int cnt = 0;
        for (RemoveReq dto : dtos) {
            cnt += 1;
            WdcbSapSalesCompulsionCreateDvo dvo = converter.mapRemoveReqToWdcbSapSalesCompulsionCreateDvo(dto);

            result = mapper.deleteIfinSlInfAtcIz(dvo);
            BizAssert.isTrue(result == 1, "MSG_ALT_SVE_ERR");
            processCount += result;

            int ifinSlInfAtcIzCount = mapper.selectIfinSlInfAtcIzCount(dvo);
            if (ifinSlInfAtcIzCount == 0) {
                result = mapper.deleteIfinSlInfHdrBas(dvo);
                BizAssert.isTrue(result == 1, "MSG_ALT_SVE_ERR");
            }
        }
        return processCount;
    }
}
