package com.kyowon.sms.wells.web.service.orgcode.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.service.orgcode.converter.WsndRegionLevelPdlvMgtConverter;
import com.kyowon.sms.wells.web.service.orgcode.dto.WsndRegionLevelPdlvMgtDto.SaveReq;
import com.kyowon.sms.wells.web.service.orgcode.dto.WsndRegionLevelPdlvMgtDto.SearchReq;
import com.kyowon.sms.wells.web.service.orgcode.dto.WsndRegionLevelPdlvMgtDto.SearchRes;
import com.kyowon.sms.wells.web.service.orgcode.dvo.WsndPlaceOfDeliveryDvo;
import com.kyowon.sms.wells.web.service.orgcode.mapper.WsndRegionLevelPdlvMgtMapper;
import com.sds.sflex.system.config.constant.CommConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import com.sds.sflex.system.config.validation.BizAssert;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * <pre>
 * W-SV-U-0218M01 - 급지 출고지 관리
 * </pre>
 *
 * @author yeonghwa.cheon
 * @since 2022.12.14
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class WsndRegionLevelPdlvMgtService {

    private final WsndRegionLevelPdlvMgtMapper mapper;
    private final WsndRegionLevelPdlvMgtConverter converter;

    /**
     * 급지 출고지 관리 - 조회(페이징)
     *
     * @param dto      : { pdlvDvCd : 출고지구분코드 , pdlvNm : 출고지명, ogId : 서비스센터ID, applyDate : 적용일자 }
     * @param pageInfo : 페이징정보
     * @return 조회결과
     */
    public PagingResult<SearchRes> getPlaceOfDeliveryPages(
        SearchReq dto, PageInfo pageInfo
    ) {
        return mapper.selectPlaceOfDeliveryPages(dto, pageInfo);

    }

    /**
     * 급지 출고지 관리 - 조회(엑셀다운로드)
     *
     * @param dto : { pdlvDvCd : 출고지구분코드 , pdlvNm : 출고지명, ogId : 서비스센터ID, applyDate : 적용일자 }
     * @return 조회결과
     */
    public List<SearchRes> getPlaceOfDeliveryPagesExcelDownload(SearchReq dto) {
        return mapper.selectPlaceOfDeliveryPages(dto);
    }

    /**
     * 급지 출고지 관리 - 삭제
     *
     * @param pdlvNos : List<String>
     * @param pdlvDvCds : List<Stirng>
     */
    @Transactional
    public int removePlaceOfDeliverys(List<String> pdlvNos, List<String> pdlvDvCds) {
        int processCount = 0;
        for (int i = 0; i < pdlvNos.size(); i++) {

            int result = mapper.deletePlaceOfDelivery(pdlvNos.get(i), pdlvDvCds.get(i));

            BizAssert.isTrue(result == 1, "MSG_ALT_SVE_ERR");

            processCount += result;
        }
        return processCount;
    }

    /**
     * 급지 출고지 관리 - 저장
     *
     * @param dtos : [{ pdlvDvCd : 출고지구분코드 , pdlvNo : 출고지번호 ,  apyStrtdtOrigin : 수정전 적용시작일
     *                , apyStrtdt : 적용시작일 , pdlvNm : 출고지명 , zip : 우편번호 ,
     *                , pdlvAdr : 출고지주소  , pdlvDtlAdr : 출고지상세주소, cnrOgId : 센터ID }]
     */
    @Transactional
    public int savePlaceOfDeliverys(List<SaveReq> dtos) {
        int processCount = 0;
        for (SaveReq dto : dtos) {
            WsndPlaceOfDeliveryDvo dvo = converter.mapSaveReqToWsndPlaceOfDeliveryDvo(dto);
            int result = 0;

            switch (dto.rowState()) {
                case CommConst.ROW_STATE_CREATED -> {
                    WsndPlaceOfDeliveryDvo res = mapper.selectPlaceOfDeliveryByPk(dto.pdlvNo());
                    if (res == null) {
                        result += mapper.insertPlaceOfDelivery(dvo);
                        mapper.insertPlaceOfDeliveryHistory(dvo);
                    } else if (res.getDataDlYn().equals("N")) {
                        BizAssert.isTrue(res.getDataDlYn().equals("N"), "MSG_ALT_DUP_PDLV_DV");
                        processCount = -2;
                        return processCount;
                    } else if (res.getDataDlYn().equals("Y")) {
                        String apyStrtdt = mapper.selectStrtdtByPk(dto.pdlvNo());
                        if (Integer.parseInt(apyStrtdt) > Integer.parseInt(dvo.getApyStrtdt())) {
                            processCount = -1;
                            return processCount;
                        } else {
                            result += mapper.updatePlaceOfDeliveryHistory(dvo);
                            mapper.updatePlaceOfDelivery(dvo);
                            mapper.insertPlaceOfDeliveryHistory(dvo);
                        }
                    }
                }
                case CommConst.ROW_STATE_UPDATED -> {
                    result += mapper.updatePlaceOfDeliveryHistory(dvo);
                    mapper.updatePlaceOfDelivery(dvo);
                    mapper.insertPlaceOfDeliveryHistory(dvo);
                }
            }

            processCount += result;
        }
        return processCount;
    }

}
