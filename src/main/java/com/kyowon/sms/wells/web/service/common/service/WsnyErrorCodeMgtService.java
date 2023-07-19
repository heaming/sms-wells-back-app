package com.kyowon.sms.wells.web.service.common.service;

import static com.kyowon.sms.wells.web.service.common.dto.WsnyErrorCodeMgtDto.DeleteReq;
import static com.kyowon.sms.wells.web.service.common.dto.WsnyErrorCodeMgtDto.SaveReq;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.service.common.converter.WsnyErrorCodeMgtConverter;
import com.kyowon.sms.wells.web.service.common.dto.WsnyErrorCodeMgtDto.SearchReq;
import com.kyowon.sms.wells.web.service.common.dto.WsnyErrorCodeMgtDto.SearchRes;
import com.kyowon.sms.wells.web.service.common.dvo.WsnyErrorCodeMgtDvo;
import com.kyowon.sms.wells.web.service.common.mapper.WsnyErrorCodeMgtMapper;
import com.sds.sflex.common.docs.service.AttachFileService;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * <pre>
 * K-W-SV-U-0304M01 상품별 에러코드 관리
 * </pre>
 *
 * @author 37758 이재훈
 * @since 2023.07.03
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class WsnyErrorCodeMgtService {
    private final WsnyErrorCodeMgtMapper mapper;
    private final WsnyErrorCodeMgtConverter converter;
    private final AttachFileService attachFileService;
    private final String groupId = "ATG_SNY_ERR_IMG";

    /**
     * 상품별 에러코드 관리 조회(페이징)
     * @param searchReq 조회조건
     * @param pageInfo : 페이징정보
     * @return 조회결과
     */
    public PagingResult<SearchRes> getErrorCodePages(
        SearchReq searchReq, PageInfo pageInfo
    ) {
        return new PagingResult<>(
            converter.mapAllErrorCodeDvoToSearchRes(mapper.selectErrorCodePages(searchReq, pageInfo)), pageInfo
        );
    }

    /**
     * 상품별 에러코드 관리 조회(엑셀)
     * @param searchReq 조회조건
     * @return 조회결과
     */
    public List<SearchRes> getErrorCodeForExcelDownload(SearchReq searchReq) {
        return converter.mapAllErrorCodeDvoToSearchRes(mapper.selectErrorCodePages(searchReq));
    }

    /**
     * 상품별 에러코드 관리 삭제
     */
    @Transactional
    public int deleteErrorCode(List<DeleteReq> dtos) throws Exception {
        int processCnt = 0;

        for (DeleteReq dto : dtos) {
            WsnyErrorCodeMgtDvo dvo = this.converter.mapDeleteReqToWsnyErrorCodeMgtDvo(dto);
            processCnt += this.mapper.deleteErrorCode(dvo);
        }
        return processCnt;
    }

    /**
     * 상품별 에러코드 관리 등록 및 수정
     */
    @Transactional
    public int saveErrorCode(SaveReq req) throws Exception {
        int count = 0;

        WsnyErrorCodeMgtDvo dvo = this.converter.mapSaveReqToWsnyErrorCodeMgtDvo(req);
        String fileId = groupId + "_BAS" + dvo.getPdCd() + dvo.getErrCn();
        dvo.setErrImageDocId(fileId);
        if (dvo.getFlag().equals("I")) {
            mapper.insertErrorCode(dvo);
        } else {
            mapper.updateErrorCode(dvo);
        }

        attachFileService.saveAttachFiles(groupId, fileId, dvo.getAttachFiles());
        return count;
    }
}
