package com.kyowon.sms.wells.web.service.stock.service;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaWarehouseOrganizationDto.CreateReq;
import static com.kyowon.sms.wells.web.service.stock.dto.WsnaWarehouseOrganizationDto.FindRes;

import java.util.List;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaWarehouseOrganizationDto;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaWarehouseOrganizationDto.SaveReq;
import com.sds.sflex.common.utils.StringUtil;
import com.sds.sflex.system.config.validation.BizAssert;
import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.service.stock.converter.WsnaWarehouseOrganizationConverter;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaWarehouseOrganizationDto.CountReq;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaWarehouseOrganizationDto.SearchReq;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaWarehouseOrganizationDto.SearchRes;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaWarehouseOrganizationDvo;
import com.kyowon.sms.wells.web.service.stock.mapper.WsnaWarehouseOrganizationMapper;
import com.sds.sflex.system.config.validation.ValidAssert;

import lombok.RequiredArgsConstructor;

/**
 * <pre>
 * W-SV-U-0138M01 창고조직 관리
 * W-SV-U-0175P01 창고조직 등록
 * </pre>
 *
 * @author songTaeSung
 * @since 2022.12.08
 */
@Service
@RequiredArgsConstructor
public class WsnaWarehouseOrganizationService {

    private final WsnaWarehouseOrganizationMapper mapper;
    private final WsnaWarehouseOrganizationConverter converter;

    /**
     * 창고조직관리 - 조회
     *
     * @param dto : { baseYm : 기준년월, wareDv : 창고구분, codeUseYn : 사용여부, wareLocaraCd : 창고지역코드}
     * @return 조회결과
     */

    public List<SearchRes> getWarehouseOgs(SearchReq dto) {
        return this.mapper.selectWarehouseOgs(dto);
    }

    /**
     * 창고조직이월 - 조회
     *
     * @param dto : { baseYm : 기준년월}
     * @return 조회결과
     */
    public int getWareCarriedCounter(CountReq dto) {
        return this.mapper.selectWareCarriedCounter(dto);
    }

    public int createWareCarried(CreateReq dto) {

        int processCount = 0;

        WsnaWarehouseOrganizationDvo warehouse = this.converter.mapCreateReqToWsnaWarehouseOgDvo(dto);

        processCount += this.mapper.insertWareCarried(warehouse);
        return processCount;
    }

    /**
     * 창고조직관리 - 엑셀 다운로드
     *
     * @param dto : { baseYm : 기준년월, wareDv : 창고구분, codeUseYn : 사용여부, wareLocaraCd : 창고지역코드}
     * @return 조회결과
     */
    public List<SearchRes> getWarehouseOgsExcelDownload(SearchReq dto) {
        return this.mapper.selectWarehouseOgs(dto);
    }

    /**
     * 창고조직 등록 - 조회
     *
     * @param apyYmWareNo 기준년월 + 창고번호
     * @return 조회결과
     */
    public FindRes getWarehouseOg(String apyYmWareNo) {
        WsnaWarehouseOrganizationDvo warehouse = getWarehouseByPk(apyYmWareNo);
        return this.converter.mapWsnaWarehouseOgDvoToFindRes(warehouse);
    }

    private WsnaWarehouseOrganizationDvo getWarehouseByPk(String apyYmWareNo) {
        ValidAssert.hasText(apyYmWareNo);
        return this.mapper.selectWarehouseByPk(apyYmWareNo).orElseThrow();
    }

    public int saveWarehouseOg(SaveReq dto) {
        int processCount = 0;

        WsnaWarehouseOrganizationDvo dvo = this.converter.mapSaveReqToWsnaWarehouseOgDvo(dto);

        int isDuplicate = this.mapper.selectWarehouseOgPartner(dvo);

        BizAssert.isTrue(isDuplicate == 0, "MSG_ALT_MNGR_DUP");

        if (StringUtil.isBlank(dvo.getWareNo())) {
            processCount += this.mapper.insertWarehouseOg(dvo);
        } else {
            processCount += this.mapper.updateWarehouseOg(dvo);
        }

        return processCount;
    }

}
