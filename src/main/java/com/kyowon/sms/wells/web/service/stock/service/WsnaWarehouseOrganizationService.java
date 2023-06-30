package com.kyowon.sms.wells.web.service.stock.service;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaWarehouseOrganizationDto.*;
import static com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst.WareDtlDvCd.BUSINESS_CENTER_ORGANIZATION;
import static com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst.WareDtlDvCd.SERVICE_CENTER_ORGANIZATION;

import java.util.List;

import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.service.stock.converter.WsnaWarehouseOrganizationConverter;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaWarehouseOrganizationDvo;
import com.kyowon.sms.wells.web.service.stock.mapper.WsnaWarehouseOrganizationMapper;
import com.sds.sflex.common.utils.StringUtil;
import com.sds.sflex.system.config.validation.BizAssert;
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

    public PagingResult<SearchRes> getWarehouseOgs(SearchReq dto, PageInfo pageInfo) {
        return this.mapper.selectWarehouseOgs(dto, pageInfo);
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

    public List<SearchWarehouseRes> getHighRankWarehouses(SearchWarehouseReq dto) {
        List<SearchWarehouseRes> dtos;

        if (isOrgWarehouse(dto.wareDtlDvCd())) {
            dtos = this.mapper.selectLogisticsCenters(); // 물류센터 조회
        } else {
            dtos = this.mapper.selectIndvIndpWarehouses(dto); // 동일 조직의 개인/독립창고 조회

            if (dtos.size() == 0) {
                dtos = this.mapper.selectOrganizationWarehouses(dto); // 동일 조직의 조직창고 조회
            }
        }

        return dtos;
    }

    private boolean isOrgWarehouse(String wareDtlDvCd) {
        return SERVICE_CENTER_ORGANIZATION.getCode().equals(wareDtlDvCd)
            || BUSINESS_CENTER_ORGANIZATION.getCode().equals(wareDtlDvCd);
    }

    public PagingResult<SearchBuildingRes> getBuildings(SearchBuildingReq dto, PageInfo pageInfo) {
        return this.mapper.selectBuildings(dto, pageInfo);
    }
}
