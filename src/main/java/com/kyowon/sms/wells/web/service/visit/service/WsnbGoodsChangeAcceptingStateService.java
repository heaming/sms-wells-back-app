package com.kyowon.sms.wells.web.service.visit.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.service.common.mapper.WsnzHistoryMapper;
import com.kyowon.sms.wells.web.service.visit.converter.WsnbGoodsChangeAcceptingStateConverter;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbGoodsChangeAcceptingStateDto.SaveReq;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbGoodsChangeAcceptingStateDto.SearchReq;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbGoodsChangeAcceptingStateDto.SearchRes;
import com.kyowon.sms.wells.web.service.visit.dvo.WsnbGoodsChangeAcceptingStateDvo;
import com.kyowon.sms.wells.web.service.visit.dvo.WsnbWorkOrderDvo;
import com.kyowon.sms.wells.web.service.visit.mapper.WsnbGoodsChangeAcceptingStateMapper;
import com.sds.sflex.system.config.context.SFLEXContextHolder;
import com.sds.sflex.system.config.core.dvo.UserSessionDvo;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WsnbGoodsChangeAcceptingStateService {

    private final WsnbGoodsChangeAcceptingStateMapper mapper;
    private final WsnzHistoryMapper historyMapper;
    private final WsnbGoodsChangeAcceptingStateConverter converter;
    private final WsnbWorkOrderService workOrderService;

    public PagingResult<SearchRes> getGoodsChangeAcceptingStatePages(SearchReq req, PageInfo pageInfo) {
        return mapper.selectGoodsChangeAcceptingState(req, pageInfo);
    }

    public List<SearchRes> getGoodsChangeAcceptingStateExcelDownload(SearchReq req) {
        return mapper.selectGoodsChangeAcceptingState(req);
    }

    public int saveGoodsChangeAcceptingStateReject(List<SaveReq> dtos) {
        int processCount = 0;

        for (SaveReq dto : dtos) {
            WsnbGoodsChangeAcceptingStateDvo dvo = converter.mapSaveReqToGoodsChangeAcceptingStateDvo(dto);
            dvo.setAprAkStatCd("04"); // 반려
            // 상품교체승인요청내역 반려 업데이트
            int result = mapper.updateSvpdPdChngAprAkIz(dvo);

            processCount += result;
        }

        return processCount;
    }

    @Transactional
    public int saveGoodsChangeAcceptingStateApprove(List<SaveReq> dtos) throws Exception {
        int processCount = 0;
        String asIstOjNo = "";

        for (SaveReq dto : dtos) {
            WsnbGoodsChangeAcceptingStateDvo dvo = converter.mapSaveReqToGoodsChangeAcceptingStateDvo(dto);
            dvo.setAprAkStatCd("03"); // 승인

            // 서비스업무세분류코드(BFCH_SV_BIZ_DCLSF_CD : 변경전, AFCH_SV_BIZ_DCLSF_CD : 변경후) NULL 처리 => 배정서비스 호출
            if (StringUtils.isEmpty(dvo.getOldSvBizDclsfCd()) || !"3110".equals(dvo.getOldSvBizDclsfCd())) {
                // 변경전 코드 없거나 3110(제품A/S)이 아닌 경우 세팅
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
                String strHist = sdf.format(new Date());
                WsnbWorkOrderDvo wkDvo = new WsnbWorkOrderDvo();
                wkDvo.setCntrNo(dvo.getCntrNo());
                wkDvo.setCntrSn(dvo.getCntrSn());
                wkDvo.setApyDtm(strHist);
                wkDvo.setInChnlDvCd("2");
                wkDvo.setSvBizHclsfCd("3");
                wkDvo.setMtrStatCd("1");
                wkDvo.setSvBizDclsfCd("3210");
                wkDvo.setVstRqdt(dvo.getChangeRqstDt());
                wkDvo.setVstAkHh("091000");
                wkDvo.setRcpdt(strHist.substring(0, 8));
                wkDvo.setUrgtDvCd("1");
                wkDvo.setAsIstOjNo("");
                wkDvo.setSmsFwYn("N");
                UserSessionDvo session = SFLEXContextHolder.getContext().getUserSession();
                String userId = session.getEmployeeIDNumber();
                wkDvo.setUserId(userId);
                wkDvo.setRcpOgTpCd("W06");

                dvo.setOldSvBizDclsfCd("3110");

                // 배정서비스 호출
                asIstOjNo = workOrderService.saveWorkOrders(wkDvo);
                // 설치배정번호 세팅
                dvo.setAsIstOjNo(asIstOjNo);
            }

            // 변경후 코드 없을 때 3210(제품원인) 세팅
            if (StringUtils.isEmpty(dvo.getNewSvBizDclsfCd())) {
                dvo.setNewSvBizDclsfCd("3210");
            }

            // 고객서비스AS설치대상내역 변경
            mapper.updateCstSvasIstOjIz(dvo);
            // 고객서비스AS설치대상이력 생성
            mapper.insertCstSvasIstOjHist(dvo);
            // 고객서비스AS설치배정내역 변경
            mapper.updateCstSvAsIstAsnIz(dvo);
            // 고객서비스AS설치배정이력 생성
            historyMapper.insertCstSvasIstAsnHistByPk(dvo.getCstSvAsnNo());
            // 서비스작업상세변경내역 변경
            mapper.insertSvWkDchIz(dvo);
            // 상품교체승인요청내역 승인 업데이트
            int result = mapper.updateSvpdPdChngAprAkIz(dvo);
            processCount += result;
        }

        return processCount;
    }

}
