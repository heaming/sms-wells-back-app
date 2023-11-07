package com.kyowon.sms.wells.web.bond.consultation.mapper;

import static com.kyowon.sms.wells.web.bond.consultation.dto.WbncUnpaidGuideUrgentExcludeDto.SearchReq;
import static com.kyowon.sms.wells.web.bond.consultation.dto.WbncUnpaidGuideUrgentExcludeDto.SearchRes;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.bond.consultation.dvo.WbncBondContactExcludeObjectIzDvo;

/**
 * <pre>
 * W-BN-U-0032M01	미납요금 안내/촉구 대상 제외관리(탭)
 * </pre>
 *
 * @author gs.piit128 gilyong.han
 * @since 2023-07-10
 */
@Mapper
public interface WbncUnpaidGuideUrgentExcludeMapper {

    /**
     * 미납요금 안내/촉구 대상 제외관리(탭) 조회
     * @param dto
     * @return List<SearchRes>
     */
    List<SearchRes> selectUnpaidGuideUrgentExcludes(SearchReq dto);

    /**
     * 미납요금 안내/촉구 대상 제외관리(탭) 저장
     * @param dvo
     * @return int
     */
    int updateUnpaidGuideUrgentExclude(WbncBondContactExcludeObjectIzDvo dvo);

    /**
     * 미납요금 안내/촉구 대상 제외관리(탭) 엑셀 업로드 merge
     * @param dvo
     * @return int
     */
    int updateUnpaidGuideUrgentExcludeExcelUpload(WbncBondContactExcludeObjectIzDvo dvo);
}
