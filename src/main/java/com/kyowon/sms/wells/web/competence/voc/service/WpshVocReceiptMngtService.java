package com.kyowon.sms.wells.web.competence.voc.service;

import com.kyowon.sms.wells.web.competence.voc.converter.WpshVocReceiptMngtConverter;
import com.kyowon.sms.wells.web.competence.voc.dto.WpshVocReceiptMngtDto.*;
import com.kyowon.sms.wells.web.competence.voc.dvo.WpshVocReceiptMngtDvo;
import com.kyowon.sms.wells.web.competence.voc.mapper.WpshVocReceiptMngtMapper;
import com.sds.sflex.common.docs.service.AttachFileService;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class WpshVocReceiptMngtService {

    private final WpshVocReceiptMngtMapper mapper;
    private final WpshVocReceiptMngtConverter converter;
    private final AttachFileService attachFileService;

    private static final String groupIdRcp = "ATG_PSH_VOC_RCP";

    private static final String groupIdRly = "ATG_PSH_VOC_RLY";

    public PagingResult<SearchRes> getVocReceiptMngtPages(SearchReq dto, PageInfo pageInfo) {
        return mapper.selectVocReceiptMngtPages(dto, pageInfo);
    }

    public List<SearchRes> getVocReceiptMngtsForExcelDownload(SearchReq dto) {
        return mapper.selectVocReceiptMngtPages(dto);
    }

    public SearchDtlRes getVocReceiptDtl(SearchDtlReq dto) {
        WpshVocReceiptMngtDvo dvo = converter.mapSearchDtlReq(dto);
        return mapper.selectVocReceiptDtl(dvo);
    }

    @Transactional
    public int saveVocReceipt(SaveReq dto) throws Exception {
        int processCount = 0;
        log.info("########## {}", dto);
        WpshVocReceiptMngtDvo dvo = converter.mapSaveReq(dto);
        processCount = mapper.saveVocReceipt(dvo);
        if (processCount > 0) {
            dvo.setVocRcpId(mapper.selectVocRcpId());
        }
        log.info("############# {}", dvo);
        attachFileService.saveAttachFiles(groupIdRcp, dvo.getVocRcpId(), dto.attachFiles());
        return processCount;
    }

    @Transactional
    public int saveVocReceiptEvl(SaveEvlReq dto) {
        log.info("########## {}", dto);
        WpshVocReceiptMngtDvo dvo = converter.mapSaveEvlReq(dto);
        log.info("############# {}", dvo);
        return mapper.saveVocReceiptEvl(dvo);
    }

    @Transactional
    public int saveVocReceiptProcs(SaveProcsReq dto) {
        log.info("########## {}", dto);
        WpshVocReceiptMngtDvo dvo = converter.mapSaveProcsReq(dto);
        log.info("############# {}", dvo);
        return mapper.saveVocReceiptProcs(dvo);
    }

    @Transactional
    public int saveVocReceiptRcp(SaveRcpReq dto) {
        log.info("########## {}", dto);
        WpshVocReceiptMngtDvo dvo = converter.mapSaveRcpReq(dto);
        log.info("############# {}", dvo);
        return mapper.saveVocReceiptRcp(dvo);
    }

    @Transactional
    public int saveVocReceiptRly(SaveRlyReq dto) throws Exception {
        int processCount = 0;
        log.info("########## {}", dto);
        WpshVocReceiptMngtDvo dvo = converter.mapSaveRlyReq(dto);
        processCount = mapper.saveVocReceiptRly(dvo);
        if (StringUtils.isEmpty(dvo.getVocRlyId())) {
            dvo.setVocRlyId(mapper.selectVocRlyId());
        }
        log.info("############# {}", dvo);
        attachFileService.saveAttachFiles(groupIdRly, dvo.getVocRlyId(), dto.attachFilesRly());
        return processCount;
    }
}
