package com.kyowon.sms.wells.web.service.stock.service;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaStoreDetailItemizationDto.SearchReq;
import com.kyowon.sms.wells.web.service.stock.mapper.WsnaStoreDetailItemizationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaStoreDetailItemizationDto.*;

/**
 * <pre>
 * W-SV-U-0133M01 입고상세내역조회
 * </pre>
 *
 * @author songTaeSung
 * @since 2023.01.25
 */
@Service
@RequiredArgsConstructor
public class WsnaStoreDetailItemizationService {

    private final WsnaStoreDetailItemizationMapper mapper;

    /**
     * 입고상세내역 - 조회
     *
     * @param dto : { stStrDt : 시작입고일자
                    , edStrDt : 종료입고일자
                    , strTpCd : 입고유형코드
                    , wareDvCd : 입고창고코드
                    , ostrWareDvCd : 출고창고코드
                    , pgGdCd : 등급코드
                    , itmKndCd : 품목코드
                    , useYn : 사용여부
    }
     * @param
     * @return
     */
    public List<SearchRes> getStoreDetailItemizations(SearchReq dto) {
        return this.mapper.selectStoreDetailItemizations(dto);
    }

    /**
     * 입고상세내역 - 엑셀다운로드
     *
     * @param dto : { stStrDt : 시작입고일자
                    , edStrDt : 종료입고일자
                    , strTpCd : 입고유형코드
                    , wareDvCd : 입고창고코드
                    , ostrWareDvCd : 출고창고코드
                    , pgGdCd : 등급코드
                    , itmKndCd : 품목코드
                    , useYn : 사용여부
    }
     * @param
     * @return
     */
    public List<SearchRes> getStoreDetailItemizationExcelDownload(SearchReq dto) {
        return this.mapper.selectStoreDetailItemizations(dto);
    }
}
