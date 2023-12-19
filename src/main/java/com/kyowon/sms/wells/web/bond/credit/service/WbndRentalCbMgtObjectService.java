package com.kyowon.sms.wells.web.bond.credit.service;

import com.kyowon.sms.wells.web.bond.credit.converter.WbndRentalCbMgtObjectConverter;
import com.kyowon.sms.wells.web.bond.credit.dto.WbndRentalCbMgtObjectDto.SaveReq;
import com.kyowon.sms.wells.web.bond.credit.dto.WbndRentalCbMgtObjectDto.SearchPaymentRes;
import com.kyowon.sms.wells.web.bond.credit.dto.WbndRentalCbMgtObjectDto.SearchReq;
import com.kyowon.sms.wells.web.bond.credit.dto.WbndRentalCbMgtObjectDto.SearchRes;
import com.kyowon.sms.wells.web.bond.credit.dvo.WbndRentalCbDelinquentIzDvo;
import com.kyowon.sms.wells.web.bond.credit.mapper.WbndRentalCbMgtObjectMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import com.sds.sflex.system.config.validation.BizAssert;
import com.sds.sflex.system.config.validation.ValidAssert;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
@Slf4j
@Service
@RequiredArgsConstructor
public class WbndRentalCbMgtObjectService {

    private final WbndRentalCbMgtObjectMapper mapper;
    private final WbndRentalCbMgtObjectConverter converter;

    /**
     * 렌탈CB 연체대상 관리 조회
     *
     * @param dto
     * @return List<SearchRes>
     */
    public List<SearchRes> getRentalCbMgtObjects(SearchReq dto) {
        WbndRentalCbDelinquentIzDvo reqDvo = this.converter.mapSearchReqToRentalCbDlqIzDvo(dto);
        List<WbndRentalCbDelinquentIzDvo> resList = new ArrayList<>();
        switch (dto.selGbn()) {
            case "1" -> {
                resList = this.mapper.selectRentalCbMessageObjectsByCustomer(reqDvo);
            }
            case "2" -> {
                resList = this.mapper.selectRentalCbMessageObjectsByContract(reqDvo);
            }
        }
        return this.converter.listRentalCbDlqIzDvoToSearchRes(resList);
    }

    /**
     * 렌탈CB 연체대상 관리 저장
     *
     * @param dtos
     * @return int
     */
    @Transactional
    public int saveRentalCbMgtObjects(List<SaveReq> dtos) {
        int processCount = 0;

        for (SaveReq dto : dtos) {
            WbndRentalCbDelinquentIzDvo dvo = this.converter.mapSaveReqToRentalCbDlqIzDvo(dto);
            this.mapper.updateMessageObjectYn(dvo);
            // BizAssert.isTrue(resultYn > 0, "MSG_ALT_SVE_ERR");
            // processCount += resultYn;

            int resultHist = this.mapper.insertMessageObjectHist(dvo);
            BizAssert.isTrue(resultHist > 0, "MSG_ALT_SVE_ERR");
            processCount += resultHist;
        }

        return processCount;
    }

    /**
     * 렌탈CB납입정보(팝업) 페이징 조회
     *
     * @param cstNo    고객번호
     * @param baseYm   기준년월
     * @param pageInfo 페이지정보
     * @return PagingResult<SearchPaymentRes>
     */
    public PagingResult<SearchPaymentRes> getRentalCbMgtPaymentInfos(String cstNo, String baseYm, PageInfo pageInfo) {
        ValidAssert.hasText(cstNo);
        PagingResult<SearchPaymentRes> pagingResult = new PagingResult<>();

        PagingResult<WbndRentalCbDelinquentIzDvo> res = this.mapper.selectRentalCbMgtPaymentInfos(cstNo, baseYm, pageInfo);

        List<SearchPaymentRes> data = this.converter.listRentalCbDlqIzDvoToSearchPaymentRes(res.getList());
        pagingResult.setPageInfo(res.getPageInfo());
        pagingResult.setList(data);

        return pagingResult;
    }

    /**
     * 렌탈CB납입정보(팝업) 엑셀다운로드
     *
     * @param cstNo  고객번호
     * @param baseYm 기준년월
     * @return List<SearchPaymentRes>
     */
    public List<SearchPaymentRes> getRentalCbMgtPaymentInfoForExcelDownload(String cstNo, String baseYm) {
        return this.converter.listRentalCbDlqIzDvoToSearchPaymentRes(this.mapper.selectRentalCbMgtPaymentInfos(cstNo, baseYm));
    }
}
