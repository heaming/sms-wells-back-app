package com.kyowon.sms.wells.web.withdrawal.pchssl.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.withdrawal.pchssl.converter.WwdcSalesConfirmConvert;
import com.kyowon.sms.wells.web.withdrawal.pchssl.dto.WwdcSalesConfirmDto.SaveSalesConfirmReq;
import com.kyowon.sms.wells.web.withdrawal.pchssl.dto.WwdcSalesConfirmDto.SearchSalesConfirmReq;
import com.kyowon.sms.wells.web.withdrawal.pchssl.dto.WwdcSalesConfirmDto.SearchSalesConfirmRes;
import com.kyowon.sms.wells.web.withdrawal.pchssl.dvo.WwdcSalesConfirmDvo;
import com.kyowon.sms.wells.web.withdrawal.pchssl.mapper.WwdcSalesConfirmMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * <pre>
 * 매출확정관리 서비스
 * </pre>
 *
 * @author limkimoon
 * @since 2023-05-31
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class WwdcSalesConfirmService {

    private final WwdcSalesConfirmMapper mapper;
    private final WwdcSalesConfirmConvert convert;

    /**
     * 매출확정관리 조회 / 페이징
     * @param req
     * @param pageInfo 페이징
     * @return PagingResult<SearchSalesConfirmRes>
     */
    public PagingResult<SearchSalesConfirmRes> getSalesConfirm(
        SearchSalesConfirmReq req, PageInfo pageInfo
    ) {
        return mapper.selectSalesConfirm(req, pageInfo);
    }

    /**
     * 매출확정관리 엑셀다운로드
     * @param req
     * @return List<SearchSalesConfirmRes>
     */
    public List<SearchSalesConfirmRes> getSalesConfirmForExcelDownload(
        SearchSalesConfirmReq req
    ) {
        return mapper.selectSalesConfirm(req);
    }

    /**
     * 매출확정관리 인식상태변경
     * @param req
     * @return int processCount
     */
    public int getSalesConfirmChangeState(
        List<SaveSalesConfirmReq> req
    ) throws Exception {
        int processCount = 0;
        for (SaveSalesConfirmReq dto : req) {
            WwdcSalesConfirmDvo dvo = convert.mapSaveWwdcSalesConfirmDvo(dto);
            processCount += mapper.updateSalesConfirm(dvo);
        }
        return processCount;
    }

}
