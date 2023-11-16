package com.kyowon.sms.wells.web.bond.consultation.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.bond.consultation.dto.WbncUnpaidGuideUrgentDto.*;
import com.kyowon.sms.wells.web.bond.consultation.dvo.WbncUncollectedAdviceNoteOjIzDvo;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

/**
 * <pre>
 * W-BN-U-0031M01	미납요금 안내/촉구 대상
 * </pre>
 *
 * @author gs.piit128 gilyong.han
 * @since 2023-07-06
 */
@Mapper
public interface WbncUnpaidGuideUrgentMapper {

    /**
     * 미납요금 안내/촉구 대상 페이징 조회
     * @param dto, pageInfo
     * @return PagingResult<SearchRes>
     */
    PagingResult<SearchRes> selectUnpaidGuideUrgentPages(
        SearchReq dto,
        PageInfo pageInfo
    );

    /**
     * 미납요금 안내/촉구 대상 엑셀 다운로드
     * @param dto
     * @return List<SearchRes>
     */
    List<SearchRes> selectUnpaidGuideUrgentPages(
        SearchReq dto
    );

    /**
     * 미납요금 안내/촉구 대상 대상별 확정여부 체크
     * @param dto
     * @return CheckRes
     */
    List<WbncUncollectedAdviceNoteOjIzDvo> selectCheckUnpaidGuideUrgentObjects(CheckReq dto);

    /**
     * 미납요금 안내/촉구 대상 고객별 확정여부 체크
     * @param dto
     * @return CheckRes
     */
    int selectCheckUnpaidGuideUrgentCustomers(CheckReq dto);

    /**
     * 미납요금 안내/촉구 대상 확정
     * @param dto
     * @return int
     */
    int updateUnpaidGuideUrgentObjects(SaveObjectReq dto);

    /**
     * 미납요금 안내/촉구 대상 대상별 자료생성전 삭제
     * @param dto
     * @return int
     */
    int deleteAllUnpaidGuideUrgentObjects(CreateObjectReq dto);

    /**
     * 미납요금 안내/촉구 대상 대상별 자료생성
     * @param dto
     * @return int
     */
    int insertUnpaidGuideUrgentObjects(CreateObjectReq dto);

    /**
     * 미납요금 안내/촉구 대상 고객별 자료생성 전 삭제
     * @param dto
     * @return int
     */
    int deleteAllUnpaidGuideUrgentCustomers(CreateCustomerReq dto);

    /**
     * 미납요금 안내/촉구 대상 고객별 자료생성
     * @param dto
     * @return int
     */
    int insertUnpaidGuideUrgentCustomers(CreateCustomerReq dto);
}
