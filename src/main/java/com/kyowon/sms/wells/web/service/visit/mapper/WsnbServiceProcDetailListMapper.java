package com.kyowon.sms.wells.web.service.visit.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.visit.dto.WsnbServiceProcDetailListDto.*;
import com.kyowon.sms.wells.web.service.visit.dvo.WsnbServiceProcDetailBilItemDvo;
import com.kyowon.sms.wells.web.service.visit.dvo.WsnbServiceProcDetailListDvo;
import com.kyowon.sms.wells.web.service.visit.dvo.WsnbServiceProcDetailPuPartDvo;
import com.kyowon.sms.wells.web.service.visit.dvo.WsnbServiceProcDetailStlmIzDvo;

@Mapper
public interface WsnbServiceProcDetailListMapper {

    WsnbServiceProcDetailListDvo selectServiceProcDetailList(SearchReq dto);

    List<WsnbServiceProcDetailStlmIzDvo> selectServiceProcDetailStlmIz(SearchReq dto);

    List<WsnbServiceProcDetailBilItemDvo> selectServiceProcDetailBilItem(SearchReq dto);

    List<WsnbServiceProcDetailPuPartDvo> selectServiceProcDetailPuPart(SearchReq dto);
    CttIzReq selectServiceProcDetailCttDatas(SearchReq dto);
    // CTT_OJ_ID 신규 채번
    String selectCttBasKey();
    // 컨택기본 등록
    int insertCttNwRgst(WsnbServiceProcDetailBilItemDvo dvo);
    // 컨택기본 수정
    int updateCttNwRgst(WsnbServiceProcDetailBilItemDvo dvo);
    // 컨택변경이력 등록
    int insertCttChHistRgst(WsnbServiceProcDetailBilItemDvo dvo);
    // 컨택상세 등록
    int insertCttNwDtlRgst(WsnbServiceProcDetailBilItemDvo dvo);
    // 컨택변경상세이력 등록
    int insertCttDchHistRgst(WsnbServiceProcDetailBilItemDvo dvo);
    // 컨택정보 변경시 기존 정보 조회
    HashMap<String, String> selectCttBeforeDatas(SaveCttNwRgstReq dto);

    // 작업취소 내역 등록
    int updateWkCanIz(SaveWkCanRgstReq dto);
    int insertWkCanHist(SaveWkCanRgstReq dto);
}
