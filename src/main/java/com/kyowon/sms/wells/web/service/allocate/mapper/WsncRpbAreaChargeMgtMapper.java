package com.kyowon.sms.wells.web.service.allocate.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.allocate.dto.WsncRpbAreaChargeMgtDto.SearchReq;
import com.kyowon.sms.wells.web.service.allocate.dto.WsncRpbAreaChargeMgtDto.SearchRes;
import com.kyowon.sms.wells.web.service.allocate.dvo.WsncRpbAreaChargeDvo;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

/**
 * <pre>
 * W-SV-U-0017M01 책임지역 담당자 관리
 * </pre>
 *
 * @author hyewon.kim
 * @since 2022.12.22
 */
@Mapper
public interface WsncRpbAreaChargeMgtMapper {

    /**
     * 책임지역 담당자 관리 - 조회 (페이징)
     *
     * @param dto      : { zipFrom: 우편번호 From, zipTo: 우편번호 To, ctpvNm: 시도명, ctctyNm: 시군구명, ogId: 서비스센터(조직ID), wkGrpCd: 작업그룹코드, applyDate: 적용일자, rpbLocaraCdFrom: 지역코드 From, rpbLocaraCdTo: 지역코드 To }
     * @param pageInfo
     * @return
     */
    PagingResult<SearchRes> selectPersonInCharges(SearchReq dto, PageInfo pageInfo);

    /**
     * 책임지역 담당자 관리 - 조회 (엑셀 다운로드)
     * @param dto : { zipFrom: 우편번호 From, zipTo: 우편번호 To, ctpvNm: 시도명, ctctyNm: 시군구명, ogId: 서비스센터(조직ID), wkGrpCd: 작업그룹코드, applyDate: 적용일자, rpbLocaraCdFrom: 지역코드 From, rpbLocaraCdTo: 지역코드 To }
     * @return
     */
    List<SearchRes> selectPersonInCharges(SearchReq dto);

    /**
     * 책임지역 담당자 관리 - 저장
     * @param dvo
     * @return
     */
    int insertPersonInCharge(WsncRpbAreaChargeDvo dvo);

}
