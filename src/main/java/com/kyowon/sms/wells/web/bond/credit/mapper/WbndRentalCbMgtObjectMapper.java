package com.kyowon.sms.wells.web.bond.credit.mapper;

import com.kyowon.sms.wells.web.bond.credit.dvo.WbndRentalCbDelinquentIzDvo;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <pre>
 * W-BN-U-0069M01	렌탈CB 연체대상 관리
 * W-BN-U-0071P01	렌탈CB납입정보(팝업)
 * </pre>
 *
 * @author gs.piit128 gilyong.han
 * @since 2023-05-12
 */
@Mapper
public interface WbndRentalCbMgtObjectMapper {

    /**
     * 렌탈CB 연체대상 관리 조회 - 고객별
     *
     * @param dvo
     * @return List<WbndRentalCbDelinquentIzDvo>
     */
    List<WbndRentalCbDelinquentIzDvo> selectRentalCbMessageObjectsByCustomer(
        WbndRentalCbDelinquentIzDvo dvo
    );

    /**
     * 렌탈CB 연체대상 관리 조회 - 계약별
     *
     * @param dvo
     * @return List<WbndRentalCbDelinquentIzDvo>
     */
    List<WbndRentalCbDelinquentIzDvo> selectRentalCbMessageObjectsByContract(
        WbndRentalCbDelinquentIzDvo dvo
    );

    /**
     * 렌탈CB 연체대상 관리 update
     *
     * @return int
     */
    int updateMessageObjectYn(String baseYm);

    /**
     * 렌탈CB 연체대상 관리 이력 insert
     *
     * @return int
     */
    int insertMessageObjectHist(String baseYm);

    /**
     * 렌탈CB납입정보(팝업) 페이징 조회
     *
     * @param cstNo, pageInfo
     * @return PagingResult<WbndRentalCbDelinquentIzDvo>
     */
    PagingResult<WbndRentalCbDelinquentIzDvo> selectRentalCbMgtPaymentInfos(@Param("cstNo")
                                                                            String cstNo, @Param("baseYm") String baseYm, PageInfo pageInfo);

    /**
     * 렌탈CB납입정보(팝업) 엑셀다운로드
     *
     * @param cstNo
     * @return List<WbndRentalCbDelinquentIzDvo>
     */
    List<WbndRentalCbDelinquentIzDvo> selectRentalCbMgtPaymentInfos(@Param("cstNo")
                                                                    String cstNo, @Param("baseYm") String baseYm);

}
