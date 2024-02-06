package com.kyowon.sms.wells.web.service.visit.service;

import com.kyowon.sms.wells.web.contract.ordermgmt.service.WctaInstallationReqdDtInService;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbServiceProcDetailCmnCdInqrDto;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbServiceProcDetailListDto.*;
import com.kyowon.sms.wells.web.service.visit.dvo.WsnbServiceProcDetailBilItemDvo;
import com.kyowon.sms.wells.web.service.visit.dvo.WsnbServiceProcDetailListDvo;
import com.kyowon.sms.wells.web.service.visit.dvo.WsnbServiceProcDetailPuPartDvo;
import com.kyowon.sms.wells.web.service.visit.dvo.WsnbServiceProcDetailStlmIzDvo;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbServiceProcDetailCmnCdInqrDto.CmnCdInqrRes;
import com.kyowon.sms.wells.web.service.visit.mapper.WsnbServiceProcDetailListMapper;
import com.sds.sflex.system.config.exception.BizException;
import com.sds.sflex.system.config.validation.ValidAssert;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class WsnbServiceProcDetailListService {
    private final WsnbServiceProcDetailListMapper mapper;

    private final WctaInstallationReqdDtInService contractIstService; // 계약설치요청일자변경 서비스

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

    public WkCanReq getServiceProcDetailWkCanDatas(WkCanRes dto){
        return mapper.selectServiceProcDetailWkCanDatas(dto);
    }

    public List<CmnCdInqrRes> getServiceProcDetailCmnCdInqr1(WsnbServiceProcDetailCmnCdInqrDto dto){
        return mapper.selectCmnCdInqr1(dto);
    }

    public List<CmnCdInqrRes> getServiceProcDetailCmnCdInqr2(WsnbServiceProcDetailCmnCdInqrDto dto){
        return mapper.selectCmnCdInqr2(dto);
    }

    @Transactional
    public int saveCttNwRgst(SaveCttNwRgstReq dto) throws Exception{

        ValidAssert.notNull(dto);

        int processCount = 0;

        WsnbServiceProcDetailBilItemDvo dvo = new WsnbServiceProcDetailBilItemDvo();
        dvo.setCttOjId(StringUtils.equals(dto.wkKnd(), "insert") ? mapper.selectCttBasKey() : dto.cttOjId());
        dvo.setCstSvAsnNo(dto.cstSvAsnNo());
        dvo.setCttRcpDtm(dto.cttRcpDtm());
        dvo.setCttDuedt(dto.cttDuedt());
        dvo.setCttMoCn(dto.cttMoCn());
        dvo.setCntrNo(dto.cntrNo());
        dvo.setCntrSn(dto.cntrSn());
        dvo.setSvBizDclsfCd(dto.svBizDclsfCd());
        dvo.setCttPrgsStatCd("10"); // 컨택진행상태코드

        // 1단계 Oracle  LC_SERVICEVISIT_201_LST_I01 컨택등록 VS101(TB_SVPD_CST_SV_CNTC_IZ)
        // 컨택기본 등록
        processCount += mapper.insertCttNwRgst(dvo);  //컨택등록 VS101(TB_SVPD_CST_SV_CNTC_IZ)


        // 2단계
        // TB_SVPD_OTSC_PDCT_AS_RS_IZ   /*아웃소싱제품AS결과내역		    KWAS.LC_ALLOCATE_AC222TB
        int otscSeq = mapper.selectOutSourcingSaveSnInf(dvo);
        dvo.setOtscSeq(otscSeq);
        mapper.insertOutSourcingInfRgst(dvo);

        // 3단계
        // 약속변경 ATEMP_LYJ_CHANGE_VST_DT(TB_SVPD_WK_DTM_CH_IZ)
        processCount += mapper.insertCttNwDtlRgst(dvo);   // 약속변경 ATEMP_LYJ_CHANGE_VST_DT(TB_SVPD_WK_DTM_CH_IZ)


        // 4단계
        // DB2 예정일자 업데이트
        if (dvo.getSvBizDclsfCd().startsWith("11")
                || dvo.getSvBizDclsfCd().startsWith("41")) {

            String lcCttRsCd = "01"; // 91 (컨택완료)
            String sppDuedt = dvo.getCttDuedt();

            // 방문예정일, 컨택코드 업데이트
            contractIstService.saveInstallOrderReqDt(dto.cntrNo(), dto.cntrSn(), sppDuedt, lcCttRsCd);
        }

        return processCount;
    }


    @Transactional
    public int saveWkCanRgst(SaveWkCanRgstReq dto) throws Exception{
        ValidAssert.notNull(dto);

        HashMap<String, String> wkStatData = mapper.selectWkStatData(dto);
        if(StringUtils.equals("2", wkStatData.get("CST_SV_ASN_NO").substring(0, 1))){
            throw new BizException("해당 작업은 B/S 입니다.");
        }
        if(!StringUtils.equals("00", wkStatData.get("WK_PRGS_STAT_CD"))){
            throw new BizException("작업대기 상태가 아닙니다.");
        }

        int processCount = 0;

        WsnbServiceProcDetailBilItemDvo dvo = new WsnbServiceProcDetailBilItemDvo();
        dvo.setWkCanRsonCd(dto.canRson());
        dvo.setWkCanMoCn(dto.canRsonDtlIz());
        dvo.setCstSvAsnNo(dto.cstSvAsnNo());
        dvo.setCntrNo(dto.cntrNo());
        dvo.setCntrSn(dto.cntrSn());
        dvo.setCttRcpDtm(dto.cttRcpDtm());
        dvo.setSvBizDclsfCd(dto.svBizDclsfCd());

        // 1단계 Oracle  LC_SERVICEVISIT_201_LST_I01 컨택등록 VS101(TB_SVPD_CST_SV_CNTC_IZ)

        // 컨택기본 등록
        processCount += mapper.insertCttNwRgst(dvo);  //컨택등록 VS101(TB_SVPD_CST_SV_CNTC_IZ)

        if (dvo.getSvBizDclsfCd().startsWith("11")
                || dvo.getSvBizDclsfCd().startsWith("41")) {

            String lcCttRsCd = "91"; // 91 (컨택완료)
            String sppDuedt = "";

            // 방문예정일, 컨택코드 업데이트
            contractIstService.saveInstallOrderReqDt(dto.cntrNo(), dto.cntrSn(), sppDuedt, lcCttRsCd);
        }

        return processCount;
    }
}
