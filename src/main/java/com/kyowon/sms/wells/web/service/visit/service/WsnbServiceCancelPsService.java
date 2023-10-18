package com.kyowon.sms.wells.web.service.visit.service;

import com.kyowon.sms.wells.web.service.visit.converter.WsnbServiceCancelPsConverter;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbServiceCancelPsDto.*;
import com.kyowon.sms.wells.web.service.visit.dvo.WsnbServiceCancelPsDvo;
import com.kyowon.sms.wells.web.service.visit.mapper.WsnbServiceCancelPsMapper;
import com.sds.sflex.common.docs.dvo.AttachFileDvo;
import com.sds.sflex.common.docs.service.AttachFileService;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.eclipse.jetty.util.StringUtil;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class WsnbServiceCancelPsService {
    private final WsnbServiceCancelPsMapper mapper;
    private final WsnbServiceCancelPsConverter converter;
    private final AttachFileService attachFileService;

    public PagingResult<SearchRes> getServiceCancelPsPages(SearchReq dto, PageInfo pageInfo){
        PagingResult<WsnbServiceCancelPsDvo> dvos = mapper.selectServiceCancelPsPages(dto, pageInfo);

        for(WsnbServiceCancelPsDvo dvo : dvos){
            List<AttachFileDvo> attachFileDvos;

            // 설치불가환경 1
            if(StringUtil.isNotBlank(dvo.getIstImpEnvr1stImgFileUid())){
                attachFileDvos = attachFileService.getAttachFiles(dvo.getIstImpEnvr1stImgFileUid());
                if(!CollectionUtils.sizeIsEmpty(attachFileDvos)){
                    dvo.setIstImpEnvr1stImgFileUid(attachFileDvos.get(0).getFileUid());
                }
            }
            // 설치불가환경 2
            if(StringUtil.isNotBlank(dvo.getIstImpEnvr2ndImgFileUid())){
                attachFileDvos = attachFileService.getAttachFiles(dvo.getIstImpEnvr2ndImgFileUid());
                if(!CollectionUtils.sizeIsEmpty(attachFileDvos)){
                    dvo.setIstImpEnvr2ndImgFileUid(attachFileDvos.get(0).getFileUid());
                }
            }
            // 설치불가환경 3
            if(StringUtil.isNotBlank(dvo.getIstImpEnvr3rdImgFileUid())){
                attachFileDvos = attachFileService.getAttachFiles(dvo.getIstImpEnvr3rdImgFileUid());
                if(!CollectionUtils.sizeIsEmpty(attachFileDvos)){
                    dvo.setIstImpEnvr3rdImgFileUid(attachFileDvos.get(0).getFileUid());
                }
            }
        }
        PagingResult<SearchRes> pagingResult = converter.mapAllDvoToSearchRes(dvos);
        pagingResult.setPageInfo(pageInfo);
        return pagingResult;
    }

    public List<SearchRes> getServiceCancelPsExcelDownload(SearchReq dto){
        return converter.mapAllDvoToSearchRes(mapper.selectServiceCancelPsPages(dto));
    }
}
