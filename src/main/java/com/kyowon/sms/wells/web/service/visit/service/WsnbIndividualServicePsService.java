package com.kyowon.sms.wells.web.service.visit.service;

import java.util.List;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.service.visit.converter.WsnbIndividualServicePsConverter;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbIndividualServicePsDto.*;
import com.kyowon.sms.wells.web.service.visit.dvo.WsnbIndividualServicePsDvo;
import com.kyowon.sms.wells.web.service.visit.mapper.WsnbIndividualServicePsMapper;
import com.sds.sflex.common.docs.dvo.AttachFileDvo;
import com.sds.sflex.common.docs.service.AttachFileService;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WsnbIndividualServicePsService {

    private final WsnbIndividualServicePsMapper mapper;
    private final WsnbIndividualServicePsConverter converter;
    private final AttachFileService attachFileService;

    public SearchRes getIndividualServicePs(SearchReq dto){
        return mapper.selectIndividualServicePs(dto);
    } // 개인별서비스현황 조회
    public List<SearchHouseholdRes> getIndividualServiceHousehold(SearchReq dto){
        return converter.mapSearchHouseholdToDvo(mapper.selectIndividualServiceHousehold(dto)) ;
    } // 가구화 조회
    public List<SearchContactRes> getIndividualServiceContact(SearchReq dto){
        return mapper.selectIndividualServiceContact(dto);
    } // 컨택현황 조회
    public List<SearchFarmRes> getIndividualServiceFarm(SearchReq dto){
        return mapper.selectIndividualFarm(dto);
    } // 연계코드 조회
    public List<SearchDelinquentRes> getIndividualDelinquent(SearchReq dto){
        return mapper.selectIndividualDelinquent(dto);
    } // 연체정보 조회
    public PagingResult<SearchStateRes> getIndividualProcessState(SearchReq dto, PageInfo pageInfo){
        PagingResult<WsnbIndividualServicePsDvo> dvos = mapper.selectIndividualProcessState(dto, pageInfo);

        for (WsnbIndividualServicePsDvo dvo : dvos.getList()) {
            if (ObjectUtils.isNotEmpty(dvo.getIstEnvrPhoPhDocId())){
                List<AttachFileDvo> attachFileDvos = attachFileService.getAttachFiles(dvo.getIstEnvrPhoPhDocId());
                if(ObjectUtils.isNotEmpty(attachFileDvos) && attachFileDvos.size() > 0)
                    dvo.setIstEnvrFileUid(attachFileDvos.get(0).getFileUid());
            }
            if (ObjectUtils.isNotEmpty(dvo.getIstKitPhoPhDocId())){
                List<AttachFileDvo> attachFileDvos = attachFileService.getAttachFiles(dvo.getIstKitPhoPhDocId());
                if(ObjectUtils.isNotEmpty(attachFileDvos) && attachFileDvos.size() > 0)
                    dvo.setIstKitFileUid(attachFileDvos.get(0).getFileUid());
            }
            if (ObjectUtils.isNotEmpty(dvo.getIstCelngPhoPhDocId())){
                List<AttachFileDvo> attachFileDvos = attachFileService.getAttachFiles(dvo.getIstCelngPhoPhDocId());
                if(ObjectUtils.isNotEmpty(attachFileDvos) && attachFileDvos.size() > 0)
                    dvo.setIstCelngFileUid(attachFileDvos.get(0).getFileUid());
            }
        }

        return converter.mapAllSearchStateToDvo(dvos);
//        return mapper.selectIndividualProcessState(dto, pageInfo);
    } // 처리내역 조회

    public List<SearchStateRes> getIndividualProcessStateExcelDownload(SearchReq dto)throws Exception{
        List<WsnbIndividualServicePsDvo> dvos = mapper.selectIndividualProcessState(dto);
        return converter.mapAllSearchStateResToDvo(dvos);
    }
    public PagingResult<SearchCounselRes> getIndividualCounsel(SearchReq dto, PageInfo pageInfo){
        return mapper.selectIndividualCounsel(dto, pageInfo);
    } // 상담내역 조회

    @Transactional
    public int saveUnusualItem(SaveReq dto){
        WsnbIndividualServicePsDvo dvo = converter.mapWsnbIndividualServicePsDvoToSaveReq(dto);
        mapper.insertUnusualItem(dvo);
        return 1;
    } // 고객특이사항 저장
}
