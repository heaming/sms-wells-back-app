package com.kyowon.sms.wells.web.service.visit.service;

import java.util.HashMap;
import java.util.List;

import com.sds.sflex.system.config.validation.ValidAssert;
import org.apache.commons.codec.binary.StringUtils;
import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.service.visit.dto.WsnbServiceProcDetailListDto.*;
import com.kyowon.sms.wells.web.service.visit.dvo.WsnbServiceProcDetailBilItemDvo;
import com.kyowon.sms.wells.web.service.visit.dvo.WsnbServiceProcDetailListDvo;
import com.kyowon.sms.wells.web.service.visit.dvo.WsnbServiceProcDetailPuPartDvo;
import com.kyowon.sms.wells.web.service.visit.dvo.WsnbServiceProcDetailStlmIzDvo;
import com.kyowon.sms.wells.web.service.visit.mapper.WsnbServiceProcDetailListMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class WsnbServiceProcDetailListService {
    private final WsnbServiceProcDetailListMapper mapper;

    public WsnbServiceProcDetailListDvo getServiceProcDetailList(SearchReq dto) {

        return mapper.selectServiceProcDetailList(dto);
    }

    public List<WsnbServiceProcDetailStlmIzDvo> getServiceProcDetailStlmIzs(SearchReq dto) {

        return mapper.selectServiceProcDetailStlmIz(dto);
    }

    public List<WsnbServiceProcDetailBilItemDvo> getServiceProcDetailBilItems(SearchReq dto) {

        return mapper.selectServiceProcDetailBilItem(dto);
    }

    public List<WsnbServiceProcDetailPuPartDvo> getServiceProcDetailPuParts(SearchReq dto) {

        return mapper.selectServiceProcDetailPuPart(dto);
    }

    public CttIzReq getServiceProcDetailCttDatas(SearchReq dto){
        return mapper.selectServiceProcDetailCttDatas(dto);
    }

    @Transactional
    public int saveCttNwRgst(SaveCttNwRgstReq dto) throws Exception{
        ValidAssert.notNull(dto);

        int processCount = 0;

        WsnbServiceProcDetailBilItemDvo dvo = new WsnbServiceProcDetailBilItemDvo();
        dvo.setCttOjId(StringUtils.equals(dto.wkKnd(), "insert") ? mapper.selectCttBasKey() : dto.cttOjId());
        dvo.setWkKnd(dto.wkKnd());
        dvo.setCttRcpDtm(dto.cttRcpDtm());
        dvo.setCttDuedt(dto.cttDuedt());
        dvo.setCttMoCn(dto.cttMoCn());
        dvo.setCntrNo(dto.cntrNo());
        dvo.setCntrSn(dto.cntrSn());
        dvo.setCttPrgsStatCd("10"); // 컨택진행상태코드

        if(StringUtils.equals(dto.wkKnd(), "insert")){
            // 컨택기본 등록
            processCount += mapper.insertCttNwRgst(dvo);
        }else{
            // 기존 등록된 내용
            HashMap<String, String> beforeData = mapper.selectCttBeforeDatas(dto);
            dvo.setBeforeRcpDtm(beforeData.get("CTT_RCP_DTM"));
            dvo.setBeforeDuedt(beforeData.get("CTT_DUEDT"));
            dvo.setBeforeMoCn(beforeData.get("CTT_MO_CN"));

            // 컨택기본 수정
            processCount += mapper.updateCttNwRgst(dvo);
        }

        // 컨택변경이력 등록
        processCount += mapper.insertCttChHistRgst(dvo);

        // 컨택상세 등록
        processCount += mapper.insertCttNwDtlRgst(dvo);

        // 컨택변경상세이력 등록
        processCount += mapper.insertCttDchHistRgst(dvo);

        return processCount;
    }

    @Transactional
    public int saveWkCanRgst(SaveWkCanRgstReq dto) throws Exception{
        ValidAssert.notNull(dto);

        int processCount = 0;

        // 고객서비스AS설치배정내역 저장
        processCount += mapper.updateWkCanIz(dto);

        // 고객서비스AS설치배정이력 저장
        processCount += mapper.insertWkCanHist(dto);

        return processCount;
    }
}
