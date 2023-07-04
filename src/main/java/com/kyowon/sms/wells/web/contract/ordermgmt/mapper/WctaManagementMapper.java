package com.kyowon.sms.wells.web.contract.ordermgmt.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaManagementDto.*;
import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.*;

@Mapper
public interface WctaManagementMapper {
    List<SearchKssOrdrListRes> selectKssOrdrList(SearchKssOrdrListReq dto);

    List<SearchRePromConcListRes> selectRePromConcList(SearchKssOrdrListReq dto);

    List<SearchEmployeePurchaseListRes> selectEmployeePurchaseList(SearchKssOrdrListReq dto);

    List<WctaLspyOrdrDtptListDvo> selectLspyOrdrDtptList(SearchLspyOrdrDtptListReq dto);

    List<WctaBhOrdrDtptListDvo> selectBhOrdrDtptList(SearchLspyOrdrDtptListReq dto);

    List<WctaRentOrdrDtptListDvo> selectRentOrdrDtptList(SearchLspyOrdrDtptListReq dto);

    List<WctaMbOrdrDtptListDvo> selectMbOrdrDtptList(SearchLspyOrdrDtptListReq dto);

    List<WctaHcsOrdrDtptListDvo> selectHcsOrdrDtptList(SearchLspyOrdrDtptListReq dto);

    List<WctaPlantsOrdrDtptListDvo> selectPlantsOrdrDtptList(SearchLspyOrdrDtptListReq dto);

    List<WctaRglrDlvrOrdrDtptListDvo> selectRglrDlvrOrdrDtptList(SearchLspyOrdrDtptListReq dto);

    List<WctaRentOrdrDtptListDvo> selectLtmIstmOrdrDtptList(SearchLspyOrdrDtptListReq dto);

    List<SearchOrdrInfo4ReqDfntRes> selectOrdrInfo4ReqDfntList(String cntrNo, String cntrSn);

    List<SearchDfntAckdReqListRes> selectDfntAckdReqList(WctaDfntAckdReqDvo paramMap);

    int updateAprvDfntAckdReq(String cntrAprId);
}
