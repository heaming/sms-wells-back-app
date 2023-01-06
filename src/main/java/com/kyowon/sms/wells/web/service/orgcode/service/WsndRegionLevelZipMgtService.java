package com.kyowon.sms.wells.web.service.orgcode.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.service.orgcode.converter.WsndRegionLevelZipMgtConverter;
import com.kyowon.sms.wells.web.service.orgcode.dto.WsndRegionLevelZipMgtDto.EditReq;
import com.kyowon.sms.wells.web.service.orgcode.dto.WsndRegionLevelZipMgtDto.SearchExcelRes;
import com.kyowon.sms.wells.web.service.orgcode.dto.WsndRegionLevelZipMgtDto.SearchReq;
import com.kyowon.sms.wells.web.service.orgcode.dto.WsndRegionLevelZipMgtDto.SearchRes;
import com.kyowon.sms.wells.web.service.orgcode.dvo.WsndRegionLevelZipNoDvo;
import com.kyowon.sms.wells.web.service.orgcode.mapper.WsndRegionLevelZipMgtMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import lombok.RequiredArgsConstructor;

/**
 * <pre>
 * W-SV-U-0145M01 급지 우편번호 관리
 * </pre>
 *
 * @author gs.piit130 김혜원
 * @since 2022.12.06
 */
@Service
@RequiredArgsConstructor
public class WsndRegionLevelZipMgtService {

    private final WsndRegionLevelZipMgtMapper mapper;

    private final WsndRegionLevelZipMgtConverter converter;

    /**
     * 급지 우편번호 관리 - 조회 (페이징)
     *
     * @param dto : { zipFrom: 우편번호From, zipTo: 우편번호To, ctpvNm: 시도명, ctctyNm: 시군구명, wkGrpCd: 작업그룹코드, ogId:서비스센터 }
     * @param pageInfo
     * @return
     */
    public PagingResult<SearchRes> getZipNoPages(SearchReq dto, PageInfo pageInfo) {
        return new PagingResult<>(
            this.converter.mapAllZipNoDvoToSearchRes(this.mapper.selectZipNos(dto, pageInfo)), pageInfo
        );
    }

    /**
     * 급지 우편번호 관리 - 엑셀 다운로드
     *
     * @param dto : { zipFrom: 우편번호From, zipTo: 우편번호To, ctpvNm: 시도명, ctctyNm: 시군구명, wkGrpCd: 작업그룹코드, ogId:서비스센터 }
     * @return
     */
    public List<SearchExcelRes> getZipNosForExcelDownload(SearchReq dto) {
        return this.mapper.selectZipNosForExcelDownload(dto);
    }

    /**
     * 급지 우편번호 관리 - 저장
     *
     * @param dtos : [{ zip: 우편번호, pdlvNo: 출고지번호 }]
     * @return
     */
    @Transactional
    public int saveZipNo(List<EditReq> dtos) throws Exception {
        int processCount = 0;

        for (EditReq dto : dtos) {
            WsndRegionLevelZipNoDvo regionLevelZip = this.converter.mapEditReqToWsndRegionLevelZipNoDvo(dto);
            processCount += this.mapper.updateZipNo(regionLevelZip);
        }

        return processCount;
    }

}
