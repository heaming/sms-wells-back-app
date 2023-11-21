package com.kyowon.sms.wells.web.service.visit.service;

import java.util.Base64;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.service.visit.converter.WsnbServiceProcessingConverter;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbServiceProcessingDto.FindProductRes;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbServiceProcessingDto.SearchReq;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbServiceProcessingDto.SearchRes;
import com.kyowon.sms.wells.web.service.visit.dvo.WsnbServiceProcessingDvo;
import com.kyowon.sms.wells.web.service.visit.mapper.WsnbServiceProcessingMapper;
import com.sds.sflex.common.docs.dvo.AttachFileDvo;
import com.sds.sflex.common.docs.service.AttachFileService;
import com.sds.sflex.common.utils.StringUtil;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * <pre>
 * W-SV-U-0054M01 서비스처리 내역
 * </pre>
 *
 * @author hyewon.kim
 * @since 2023.03.20
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class WsnbServiceProcessingService {

    private final WsnbServiceProcessingMapper mapper;

    private final WsnbServiceProcessingConverter converter;

    private final AttachFileService attachFileService;

    /**
     * 상품 그룹별 상품 목록 조회
     * @param pdGrpCd 상품그룹코드
     */
    public List<FindProductRes> getProducts(String pdGrpCd) {
        return this.mapper.selectProducts(pdGrpCd);
    }

    /**
     * 서비스 처리내역 조회
     */
    public PagingResult<SearchRes> getServiceProcessings(SearchReq dto, PageInfo pageInfo) {
        PagingResult<WsnbServiceProcessingDvo> dvos = mapper.selectServiceProcessings(dto, pageInfo);

        for (WsnbServiceProcessingDvo dvo : dvos) {
            if (ObjectUtils.isNotEmpty(dvo.getCstSignCnByte())) {
                dvo.setCstSignCn(Base64.getEncoder().encodeToString(dvo.getCstSignCnByte()));
            }

            /* "ATG_SNB_WK_IMG" */
            List<AttachFileDvo> attachFileDvos;

            // 설치환경사진
            if (StringUtil.isNotBlank(dvo.getIstEnvrPhoPhDocId())) {
                attachFileDvos = attachFileService.getAttachFiles(dvo.getIstEnvrPhoPhDocId());

                if (!CollectionUtils.sizeIsEmpty(attachFileDvos)) {
                    dvo.setIstEnvrPhoPhFileUid(attachFileDvos.get(0).getFileUid());
                }
            }

            // 설치키트사진
            if (StringUtil.isNotBlank(dvo.getIstKitPhoPhDocId())) {
                attachFileDvos = attachFileService.getAttachFiles(dvo.getIstKitPhoPhDocId());

                if (!CollectionUtils.sizeIsEmpty(attachFileDvos)) {
                    dvo.setIstKitPhoPhFileUid(attachFileDvos.get(0).getFileUid());
                }
            }

            // 설치천장사진
            if (StringUtil.isNotBlank(dvo.getIstCelngPhoPhDocId())) {
                attachFileDvos = attachFileService.getAttachFiles(dvo.getIstCelngPhoPhDocId());

                if (!CollectionUtils.sizeIsEmpty(attachFileDvos)) {
                    dvo.setIstCelngPhoPhFileUid(attachFileDvos.get(0).getFileUid());
                }
            }

            if (StringUtil.isNotBlank(dvo.getIstEnvrPhoPhFileUid())
                || StringUtil.isNotBlank(dvo.getIstEnvrPhoPhFileUid())
                || StringUtil.isNotBlank(dvo.getIstEnvrPhoPhFileUid())) {
                dvo.setIstImg("Y");
            }
        }

        return new PagingResult<>(converter.mapAllDvoToSearchRes(dvos), pageInfo);
    }

    /**
     * 서비스 처리내역 엑셀 다운로드
     */
    public List<SearchRes> getServiceProcessingsForExcel(SearchReq dto) {
        return converter.mapAllDvoToSearchRes(mapper.selectServiceProcessings(dto));
    }

}
