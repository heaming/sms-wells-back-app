package com.kyowon.sms.wells.web.withdrawal.idvrve.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwwdbBillingDocumentMgtDto.SearchDtlsReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwwdbBillingDocumentMgtDto.SearchDtlsRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwwdbBillingDocumentMgtDto.SearchFwReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwwdbBillingDocumentMgtDto.SearchFwRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwwdbBillingDocumentMgtDto.SearchReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwwdbBillingDocumentMgtDto.SearchRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dvo.WwwdbBillingDocumentDetailDvo;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dvo.WwwdbBillingDocumentDvo;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dvo.WwwdbBillingDocumentForwardingDvo;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dvo.WwwdbBillingDocumentMgtDvo;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WwwdbBillingDocumentMgtMapper {

    /* 청구서 관리 목록 조회 */
    PagingResult<SearchRes> selectBillingDocuments(SearchReq dto, PageInfo pageInfo);

    /* 청구서 관리 엑셀 다운로드 */
    List<SearchRes> selectBillingDocuments(SearchReq dto);

    /* 청구서 관리 목록 삭제 */
    int deleteBillingDocuments(WwwdbBillingDocumentMgtDvo dto) throws Exception;

    /* 청구서 관리 상세 목록 삭제 */
    int deleteBillingDocumentDtails(WwwdbBillingDocumentMgtDvo dto) throws Exception;

    /* 청구서 관리 저장 */
    int insertBillingDocument(WwwdbBillingDocumentDvo dvo) throws Exception;

    /* 청구서 관리 상세 등록 */
    int insertBillingDocumentDtails(WwwdbBillingDocumentDetailDvo dvo) throws Exception;

    /* 청구서 관리 상세 수정 */
    int updateBillingDocumentDtails(WwwdbBillingDocumentDetailDvo dvo) throws Exception;

    /* 청구서 관리 채번 */
    String selectBillingDocumentPk();

    /* 청구서 관리 상세 조회 */
    List<SearchDtlsRes> selectBillingDocumentDetails(SearchDtlsReq dto);

    //    /* 청구서 발송 목록 조회 */
    //    public List<WwwdbBillingDocumentForwardingDvo> selectBillingDocuments(SaveFwReq dto);

    /* 청구서 발송 내력 조회 */
    List<SearchFwRes> selectBillingDocumentForwardings(SearchFwReq dto);

    int insertBillingDocumentForwarding(WwwdbBillingDocumentForwardingDvo dvo);

    /* 청구서 발송 카톡 채번 - 이건 나중에 수정해야함 */
    String selectMmtSeq();

}
