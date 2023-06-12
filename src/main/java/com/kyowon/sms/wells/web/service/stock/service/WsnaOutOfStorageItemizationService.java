package com.kyowon.sms.wells.web.service.stock.service;

import com.kyowon.sms.wells.web.service.stock.mapper.WsnaOutOfStorageItemizationMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaOutOfStorageItemizationDto.*;

/**
 * <pre>
 * W-SV-U-0141M01 출고관리
 * </pre>
 *
 * @author songTaeSung
 * @since 2023.01.30
 */
@Service
@RequiredArgsConstructor
public class WsnaOutOfStorageItemizationService {
    private final WsnaOutOfStorageItemizationMapper mapper;

    /**
     * 출고관리 - 조회
     *
     * @param dto : { stOStrDt : 시작출고일자
                    , edOstrDt : 종료출고일자
                    , ostrTpCd : 출고유형코드
                    , wareDvCd : 창고구분코드
                    , strOjWareNo : 입고창고번호
                    , ostrWareDvCd : 출고창고구분코드
    }
     * @param
     * @return
     */
    public PagingResult<SearchRes> getOutOfStorageItemizations(SearchReq dto, PageInfo pageInfo) {

        return this.mapper.selectOutOfStorageItemizations(dto, pageInfo);
    }

    /**
     * 출고관리 - 엑셀다운로드
     *
     * @param dto : { stOStrDt : 시작출고일자
                    , edOstrDt : 종료출고일자
                    , ostrTpCd : 출고유형코드
                    , wareDvCd : 창고구분코드
                    , strOjWareNo : 입고창고번호
                    , ostrWareDvCd : 출고창고구분코드
    }
     * @param
     * @return
     */
    public List<SearchRes> getOutOfStorageItemizationExcelDownload(SearchReq dto) {
        return this.mapper.selectOutOfStorageItemizations(dto);
    }
}
