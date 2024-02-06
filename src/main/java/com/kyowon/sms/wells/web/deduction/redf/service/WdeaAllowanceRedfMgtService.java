package com.kyowon.sms.wells.web.deduction.redf.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.deduction.redf.converter.WdeaAllowanceRedfMgtConverter;
import com.kyowon.sms.wells.web.deduction.redf.dto.WdeaAllowanceRedfMgtDto.*;
import com.kyowon.sms.wells.web.deduction.redf.mapper.WdeaAllowanceRedfMgtMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WdeaAllowanceRedfMgtService {

    private final WdeaAllowanceRedfMgtMapper mapper;
    private final WdeaAllowanceRedfMgtConverter converter;

    /**
     * 수당(실적) 되물림 관리 - M, P, 직원판매, 홈마스터 목록조회
     * @param dto
     * @return PagingResult<SearchAwRedfRes>
     */
    public PagingResult<SearchAwRedfRes> getAwRedfMgtPages(SearchReq dto, PageInfo pageInfo) {
        return mapper.selectAwRedfMgts(dto, pageInfo);
    }

    /**
     * 수당(실적) 되물림 관리 - M, P, 직원판매, 홈마스터 목록 엑셀다운로드
     * @param dto
     * @return SXSSFWorkbook
     */
    public List<SearchAwRedfRes> getAwRedfMgtForExcelDownload(SearchReq dto) throws Exception {
        return mapper.selectAwRedfMgts(dto);
    }

    /**
     * 수당(실적) 되물림 관리 - B2B/총판 목록 조회
     * @param dto
     * @return PagingResult<SearchRedfRes>
     */
    public PagingResult<SearchRedfRes> getRedfMgtPages(SearchReq dto, PageInfo pageInfo) {
        return mapper.selectRedfMgts(dto, pageInfo);
    }

    /**
     * 수당(실적) 되물림 관리 - B2B/총판 목록 엑셀다운로드
     * @param dto
     * @return SXSSFWorkbook
     */
    public List<SearchRedfRes> getRedfMgtForExcelDownload(SearchReq dto) throws Exception {
        return mapper.selectRedfMgts(dto);
    }

    /**
     * 수당(실적) 되물림 관리 - 영업부 되물림 생성 목록 조회
     * @param dto
     * @return PagingResult<SearchRedfBizdRes>
     */
    public PagingResult<SearchRedfBizdRes> getRedfBizdMgt(SearchRedfBizdReq dto, PageInfo pageInfo) {
        return mapper.selectRedfBizdMgts(dto, pageInfo);
    }

    /**
     * 수당(실적) 되물림 관리 - 영업부 되물림 생성 목록 조회
     * @param dto
     * @return PagingResult<SearchRedfBizdRes>
     */
    //    public PagingResult<SearchRedfBizdRes> getRedfBizdMgtTest(SearchRedfBizdReq dto, PageInfo pageInfo) {
    //
    //        PagingResult<SearchRedfBizdRes> returnList = null;
    //        PagingResult<WdeaAllowanceRedfMgtDvo> dvo = new PagingResult<>();
    //
    //        if ("W02".equals(dto.ogTpCd())) {
    //            /* A~: 지구장 이하, B~: 지점장 이상
    //            *  ~1: 2019년 03월 이전
    //            *  ~2: 2019년 04월 ~ 2020년 12월
    //            *  ~3: 2021년 01월 ~ 2021년 06월
    //            *  ~4: 2021년 07월 ~ 2023년 03월
    //            *  ~5: 2023년 04월 이후
    //            *  ALL: 전 기간(위 해당사항에 걸리지 않는 조건)
    //            * */
    //
    //            /* 지구장 이하 */
    //            if ("A1".equals(dto.ymDvCd())) {
    //                dvo = mapper.selectRedfBizd201903Mgts(dto, pageInfo); // M조직 2019년 03월 이전, 직책구분 - 지구장 이하
    //            }
    //            if ("A2".equals(dto.ymDvCd())) {
    //                dvo = mapper.selectRedfBizd202012Mgts(dto, pageInfo); // M조직 2019년 04월 ~ 2020년 12월, 직책구분 - 지구장 이하
    //            }
    //            if ("A3".equals(dto.ymDvCd())) {
    //                dvo = mapper.selectRedfBizd202106Mgts(dto, pageInfo); // M조직 2021년 01월 ~ 2021년 06월, 직책구분 - 지구장 이하
    //            }
    //            if ("A4".equals(dto.ymDvCd())) {
    //                dvo = mapper.selectRedfBizd202303Mgts(dto, pageInfo); // M조직 2021년 07월 ~ 2023년 03월, 직책구분 - 지구장 이하
    //            }
    //            if ("A5".equals(dto.ymDvCd())) {
    //                dvo = mapper.selectRedfBizd202304Mgts(dto, pageInfo); // M조직 2023년 04월 이후, 직책구분 - 지구장 이하
    //            }
    //            if ("ALL".equals(dto.ymDvCd()) && "01".equals(dto.rsbDvCd())) {
    //                dvo = mapper.selectRedfBizdAllMgts(dto, pageInfo); // M조직 전 기간(위 해당사항에 걸리지 않는 조건), 직책구분 - 지구장 이하
    //            }
    //
    //            /* 지점장 이상 */
    //            if ("B1".equals(dto.ymDvCd())) {
    //                dvo = mapper.selectRedfBizdBrmgr201903Mgts(dto, pageInfo); // M조직 2019년 03월 이전, 직책구분 - 지점장 이상
    //            }
    //            if ("B2".equals(dto.ymDvCd())) {
    //                dvo = mapper.selectRedfBizdBrmgr202012Mgts(dto, pageInfo); // M조직 2019년 04월 ~ 2020년 12월, 직책구분 - 지점장 이상
    //            }
    //            if ("B3".equals(dto.ymDvCd())) {
    //                dvo = mapper.selectRedfBizdBrmgr202106Mgts(dto, pageInfo); // M조직 2021년 01월 ~ 2021년 06월, 직책구분 - 지점장 이상
    //            }
    //            if ("B4".equals(dto.ymDvCd())) {
    //                dvo = mapper.selectRedfBizdBrmgr202303Mgts(dto, pageInfo); // M조직 2021년 07월 ~ 2023년 03월, 직책구분 - 지점장 이상
    //            }
    //            if ("B5".equals(dto.ymDvCd())) {
    //                dvo = mapper.selectRedfBizdBrmgr202304Mgts(dto, pageInfo); // M조직 2023년 04월 이후, 직책구분 - 지점장 이상
    //            }
    //            if ("ALL".equals(dto.ymDvCd()) && "02".equals(dto.rsbDvCd())) {
    //                dvo = mapper.selectRedfBizdBrmgrAllMgts(dto, pageInfo); // M조직 전 기간(위 해당사항에 걸리지 않는 조건), 직책구분 - 지점장 이상
    //            }
    //            returnList = converter.mapRedfBizdW02CancelRes(dvo);
    //            returnList.setPageInfo(pageInfo);
    //
    //        } else {
    //            dvo = mapper.selectRedfBizdMgtsTest(dto, pageInfo); // P조직 쿼리 return
    //            returnList = converter.mapRedfBizdW01Res(dvo);
    //            returnList.setPageInfo(pageInfo);
    //        }
    //
    //        return returnList;
    //    }

    /**
     * 수당(실적) 되물림 관리 - 영업부 되물림 생성 목록 엑셀다운로드
     * @param dto
     * @return SXSSFWorkbook
     */
    public List<SearchRedfBizdRes> getRedfBizdMgtForExcelDownload(SearchRedfBizdReq dto) throws Exception {
        return mapper.selectRedfBizdMgts(dto);
    }

    /**
     * 수당(실적) 되물림 관리 - 영업부 되물림 생성 목록 엑셀다운로드
     * @param dto
     * @return SXSSFWorkbook
     */
    //    public List<SearchRedfBizdRes> getRedfBizdMgtForExcelDownloadTest(SearchRedfBizdReq dto) throws Exception {
    //        List<SearchRedfBizdRes> returnList = null;
    //        List<WdeaAllowanceRedfMgtDvo> dvo = new PagingResult<>();
    //
    //        if ("W02".equals(dto.ogTpCd())) {
    //            /* A~: 지구장 이하, B~: 지점장 이상
    //            *  ~1: 2019년 03월 이전
    //            *  ~2: 2019년 04월 ~ 2020년 12월
    //            *  ~3: 2021년 01월 ~ 2021년 06월
    //            *  ~4: 2021년 07월 ~ 2023년 03월
    //            *  ~5: 2023년 04월 이후
    //            *  ALL: 전 기간(위 해당사항에 걸리지 않는 조건)
    //            * */
    //
    //            /* 지구장 이하 */
    //            if ("A1".equals(dto.ymDvCd())) {
    //                dvo = mapper.selectRedfBizd201903Mgts(dto); // M조직 2019년 03월 이전, 직책구분 - 지구장 이하
    //            }
    //            if ("A2".equals(dto.ymDvCd())) {
    //                dvo = mapper.selectRedfBizd202012Mgts(dto); // M조직 2019년 04월 ~ 2020년 12월, 직책구분 - 지구장 이하
    //            }
    //            if ("A3".equals(dto.ymDvCd())) {
    //                dvo = mapper.selectRedfBizd202106Mgts(dto); // M조직 2021년 01월 ~ 2021년 06월, 직책구분 - 지구장 이하
    //            }
    //            if ("A4".equals(dto.ymDvCd())) {
    //                dvo = mapper.selectRedfBizd202303Mgts(dto); // M조직 2021년 07월 ~ 2023년 03월, 직책구분 - 지구장 이하
    //            }
    //            if ("A5".equals(dto.ymDvCd())) {
    //                dvo = mapper.selectRedfBizd202304Mgts(dto); // M조직 2023년 04월 이후, 직책구분 - 지구장 이하
    //            }
    //            if ("ALL".equals(dto.ymDvCd())) {
    //                dvo = mapper.selectRedfBizdAllMgts(dto); // M조직 전 기간(위 해당사항에 걸리지 않는 조건), 직책구분 - 지구장 이하
    //            }
    //
    //            /* 지점장 이상 */
    //            if ("B1".equals(dto.ymDvCd())) {
    //                dvo = mapper.selectRedfBizdBrmgr201903Mgts(dto); // M조직 2019년 03월 이전, 직책구분 - 지점장 이상
    //            }
    //            if ("B2".equals(dto.ymDvCd())) {
    //                dvo = mapper.selectRedfBizdBrmgr202012Mgts(dto); // M조직 2019년 04월 ~ 2020년 12월, 직책구분 - 지점장 이상
    //            }
    //            if ("B3".equals(dto.ymDvCd())) {
    //                dvo = mapper.selectRedfBizdBrmgr202106Mgts(dto); // M조직 2021년 01월 ~ 2021년 06월, 직책구분 - 지점장 이상
    //            }
    //            if ("B4".equals(dto.ymDvCd())) {
    //                dvo = mapper.selectRedfBizdBrmgr202303Mgts(dto); // M조직 2021년 07월 ~ 2023년 03월, 직책구분 - 지점장 이상
    //            }
    //            if ("B5".equals(dto.ymDvCd())) {
    //                dvo = mapper.selectRedfBizdBrmgr202304Mgts(dto); // M조직 2023년 04월 이후, 직책구분 - 지점장 이상
    //            }
    //            if ("ALL".equals(dto.ymDvCd())) {
    //                dvo = mapper.selectRedfBizdBrmgrAllMgts(dto); // M조직 전 기간(위 해당사항에 걸리지 않는 조건), 직책구분 - 지점장 이상
    //            }
    //
    //            returnList = converter.mapRedfBizdW02CancelRes(dvo);
    //
    //        } else {
    //            dvo = mapper.selectRedfBizdMgtsTest(dto); // P조직 쿼리 return
    //            returnList = converter.mapRedfBizdW01Res(dvo);
    //        }
    //
    //        return returnList;
    //    }
}
