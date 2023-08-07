package com.kyowon.sms.wells.web.service.stock.service;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaWarehouseOrganizationDto.*;
import static com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst.WareDtlDvCd.BUSINESS_CENTER_ORGANIZATION;
import static com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst.WareDtlDvCd.SERVICE_CENTER_ORGANIZATION;
import static com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst.WareDvCd.BUSINESS;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.service.stock.converter.WsnaWarehouseOrganizationConverter;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaTransferMaterialsDataDvo;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaTransferMaterialsHgrDvo;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaTransferMaterialsIostDvo;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaWarehouseOrganizationDvo;
import com.kyowon.sms.wells.web.service.stock.mapper.WsnaWarehouseOrganizationMapper;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;
import com.sds.sflex.common.utils.StringUtil;
import com.sds.sflex.system.config.core.service.MessageResourceService;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
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

    private final MessageResourceService messageService; // 메시지 서비스

    private final WsnaTransferMaterialsService transferMaterialsService; // 물량이동 수불데이터 생성 서비스

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

    @Transactional
    public int saveWarehouseOg(SaveReq dto) {
        int processCount = 0;

        WsnaWarehouseOrganizationDvo dvo = this.converter.mapSaveReqToWsnaWarehouseOgDvo(dto);

        int isDuplicate = this.mapper.selectWarehouseOgPartner(dvo);

        BizAssert.isTrue(isDuplicate == 0, "MSG_ALT_MNGR_DUP");

        if (StringUtil.isBlank(dvo.getWareNo())) {
            processCount += this.mapper.insertWarehouseOg(dvo); // 월별창고내역 INSERT
        } else {
            processCount += this.mapper.updateWarehouseOg(dvo); // 월별창고내역 UPDATE

            // 영업센터의 상위창고 변경 시 [W-SV-S-0092] 물량이동 수불데이터 생성 서비스 호출
            if (BUSINESS.getCode().equals(dvo.getWareDvCd()) && !dvo.getOrglhgrWareNo().equals(dvo.getHgrWareNo())) {
                // 고객서비스재고내역(TB_SVST_CST_SV_ITM_STOC_IZ)에서 현재 창고(WARE_NO)에 이동재고 A, B, E, R 등급수량 > 0 인 건수 조회
                int mmtStockCnt = this.mapper.selectMmtStockCnt(dvo.getWareNo());
                BizAssert.isTrue(mmtStockCnt == 0, "MSG_ALT_MMT_STOC_EXST_PROCS_IMPSB"); // "이동재고내역이 있어 처리가 불가능합니다."

                // 고객서비스재고내역(TB_SVST_CST_SV_ITM_STOC_IZ)에서 현재 창고(WARE_NO)에 시점재고 A, B, E, R 등급수량 > 0 인 재고 조회
                List<WsnaTransferMaterialsDataDvo> dataDvos = this.mapper.selectPitmStockExists(dvo);

                if (CollectionUtils.isNotEmpty(dataDvos)) {
                    String newItmOstrNo = this.mapper.selectNewItmOstrNo(SnServiceConst.RTNGD_OSTR_INSI); // 품목출고번호 채번 (반품출고)
                    String newItmStrNo = this.mapper.selectNewItmStrNo(SnServiceConst.RTNGD_STR); // 품목입고번호 채번 (반품입고)
                    String rmkCn = this.messageService.getMessage("MSG_TXT_STOC_MMT_RTNGD_INSI"); // 비고, 하위창고간 재고이동-반품내부

                    // 조회한 재고 품목코드에 대해서 현재 상위창고로 내부반품 처리
                    for (WsnaTransferMaterialsDataDvo dataDvo : dataDvos) {
                        WsnaTransferMaterialsIostDvo returnDvo = this.converter.mapDataDvoToIostDvo(dataDvo);
                        returnDvo.setItmOstrNo(newItmOstrNo);
                        returnDvo.setItmStrNo(newItmStrNo);
                        returnDvo.setOstrTpCd(SnServiceConst.RTNGD_OSTR_INSI); // 반품출고(내부) "261"
                        returnDvo.setStrTpCd(SnServiceConst.RTNGD_STR); // 반품입고(내부) "161"
                        returnDvo.setRmkCn(rmkCn);
                        returnDvo.setOstrRsonCd("10");

                        // 내부 반품 처리
                        transferMaterialsService.saveItmIostMaterials(returnDvo);
                    }

                    WsnaTransferMaterialsDataDvo dataDvo = dataDvos.get(0);

                    WsnaTransferMaterialsHgrDvo hgrDvo = this.converter.mapDataDvoToHgrDvo(dataDvo);
                    hgrDvo.setItmStrNo(newItmStrNo);
                    hgrDvo.setStrTpCd(SnServiceConst.RTNGD_STR); // 반품입고(내부) "161"

                    // 물량이동 처리 (입출고 상위 창고가 달라 상위 창고간 물량이동)
                    transferMaterialsService.saveTransferMaterialsForHgr(hgrDvo);

                    // 정상 입출고 처리
                    transferMaterialsService.saveOutOfMaterials(hgrDvo, dataDvo);
                }
            }
        }

        return processCount;
    }

    public List<SearchWarehouseRes> getHighRankWarehouses(SearchWarehouseReq dto) {
        List<SearchWarehouseRes> dtos;

        if (StringUtil.isNotBlank(dto.ogId())) { // ogId가 있는 경우 [창고조직 신규 등록] 시 상위 창고 조회
            if (isOrgWarehouse(dto.wareDtlDvCd())) {
                dtos = this.mapper.selectLogisticsCenters(); // 물류센터 조회
            } else {
                dtos = this.mapper.selectIndvIndpWarehouses(dto); // 동일 조직의 개인/독립창고 조회

                if (dtos.size() == 0) {
                    dtos = this.mapper.selectOrganizationWarehouses(dto); // 동일 조직의 조직창고 조회
                }
            }
        } else { // ogId가 없는 경우 [창고조직 수정] 시 상위 창고 목록 조회
            if (isOrgWarehouse(dto.wareDtlDvCd())) {
                dtos = this.mapper.selectLogisticsCenters(); // 물류센터 조회
            } else {
                dtos = this.mapper.selectOrganizationWarehouses(dto); // 창고구분코드에 해당하는 조직창고 조회
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
