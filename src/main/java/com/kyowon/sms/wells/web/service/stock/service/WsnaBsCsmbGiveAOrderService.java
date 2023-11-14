package com.kyowon.sms.wells.web.service.stock.service;

import java.text.ParseException;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import com.kyowon.sms.wells.web.service.stock.converter.WsnaBsCsmbGiveAOrderConverter;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaBsCsmbGiveAOrderDto.CreatReq;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaBsCsmbGiveAOrderDto.ProdutCodeRes;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaBsCsmbGiveAOrderDto.SearchReq;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaBsCsmbGiveAOrderDto.SearchRes;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaBsCsmbGiveAOrderDvo;
import com.kyowon.sms.wells.web.service.stock.ivo.EAI_CBDO1007.response.RealTimeGradeStockResIvo;
import com.kyowon.sms.wells.web.service.stock.mapper.WsnaBsCsmbGiveAOrderMapper;
import com.sds.sflex.common.utils.DateUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WsnaBsCsmbGiveAOrderService {
    private final WsnaBsCsmbGiveAOrderMapper mapper;
    private final WsnaBsCsmbGiveAOrderConverter converter;
    private final WsnaItemStockItemizationService stockService;
    // 교원프라퍼티파주물류
    private static final String SAP_PLNT_CD = "2108";
    // 파주창고
    private static final String PAJU_SAP_SAVE_LCT_CD = "21082082";
    // 성수창고
    private static final String SGSU_SAP_SAVE_LCT_CD = "21082090";

    public List<SearchRes> getBsCsmbGiveAOrderQty(SearchReq dto) {
        if (Integer.parseInt(DateUtil.getNowDayString().substring(0, 4)) <= Integer.parseInt(dto.mngtYm())) {
            List<WsnaBsCsmbGiveAOrderDvo> dvos = this.getBeforeBsCsmbGiveAOrderQty(dto);

            return converter.listDvoToBsCsmbGiveAOrderDto(dvos);
        } else {
            List<WsnaBsCsmbGiveAOrderDvo> dvos = mapper.selectBsCsmbGiveAOrderQty(dto);

            return converter.listDvoToBsCsmbGiveAOrderDto(dvos);
        }
    }

    private List<WsnaBsCsmbGiveAOrderDvo> getBeforeBsCsmbGiveAOrderQty(SearchReq dto) {
        List<WsnaBsCsmbGiveAOrderDvo> dvos = mapper.selectBeforeBsCsmbGiveAOrderQty(dto);

        // 파주재고 set
        List<String> itmPds = dvos.stream().map(WsnaBsCsmbGiveAOrderDvo::getCsmbPdCd).toList();

        if (!ObjectUtils.isEmpty(itmPds)) {
            List<RealTimeGradeStockResIvo> pajuStocks = stockService
                .getRealTimeGradeStocks(SAP_PLNT_CD, PAJU_SAP_SAVE_LCT_CD, itmPds);

            dvos.forEach(dvo -> {
                pajuStocks.forEach(stock -> {
                    if (dvo.getCsmbPdCd().equals(stock.getItmPdCd()) && "N".equals(dvo.getRgstYn())) {
                        int pajuLgstCnrStocQty = stock.getLgstAGdQty().intValue() + stock.getLgstBGdQty().intValue()
                            + stock.getLgstCGdQty().intValue() + stock.getLgstEGdQty().intValue()
                            + stock.getLgstRGdQty().intValue();

                        dvo.setPajuLgstCnrStocQty(pajuLgstCnrStocQty);
                    }
                });
            });

            if (!"1".equals(dto.sgsuExcludeYn())) { // 성수재고 제외가 아니면
                // 성수재고 set
                List<RealTimeGradeStockResIvo> sgsuStocks = stockService
                    .getRealTimeGradeStocks(SAP_PLNT_CD, SGSU_SAP_SAVE_LCT_CD, itmPds);

                dvos.forEach(dvo -> {
                    sgsuStocks.forEach(stock -> {
                        if (dvo.getCsmbPdCd().equals(stock.getItmPdCd()) && "N".equals(dvo.getRgstYn())) {
                            int sgsuLgstCnrStocQty = stock.getLgstAGdQty().intValue() + stock.getLgstBGdQty().intValue()
                                + stock.getLgstCGdQty().intValue() + stock.getLgstEGdQty().intValue()
                                + stock.getLgstRGdQty().intValue();

                            dvo.setSgsuLgstCnrStocQty(sgsuLgstCnrStocQty);
                        }
                    });
                });
            }
        }

        // 최종재고 set
        dvos.forEach(dvo -> {
            if ("N".equals(dvo.getRgstYn())) {
                dvo.setWoStocQty(dvo.getPajuLgstCnrStocQty() + dvo.getSgsuLgstCnrStocQty());
            }
        });

        // 재고지속월 set
        dvos.forEach(dvo -> {
            if ("N".equals(dvo.getRgstYn())) {
                if (dvo.getWoStocQty() == 0 || dvo.getMmAvDdlvQty() == 0) {
                    dvo.setStocPersMmN(0);
                } else {
                    dvo.setStocPersMmN(
                        (float)Math.round(((float)dvo.getWoStocQty() / (float)dvo.getMmAvDdlvQty()) * 10) / 10
                    );
                }
            }
        });

        // 예상소진일자 set
        //        String baseDay = "";
        String nowMonth = DateUtil.getNowDayString().substring(0, 6);

        if (nowMonth.equals(dto.mngtYm())) {
            String baseDay = DateUtil.getNowDayString();

            dvos.forEach(dvo -> {
                if ("N".equals(dvo.getRgstYn())) {
                    try {
                        dvo.setEtExsDt(DateUtil.addMonths(baseDay, (int)dvo.getStocPersMmN()));
                    } catch (ParseException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
        } else {
            String baseDay = dto.mngtYm() + "01";

            dvos.forEach(dvo -> {
                if ("N".equals(dvo.getRgstYn())) {
                    try {
                        dvo.setEtExsDt(DateUtil.addMonths(baseDay, (int)dvo.getStocPersMmN()));
                    } catch (ParseException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
        }

        //        dvos.forEach(dvo -> {
        //            try {
        //                dvo.setEtExsDt(DateUtil.addMonths(baseDay, dvo.getStocPersMmN()));
        //            } catch (ParseException e) {
        //                throw new RuntimeException(e);
        //            }
        //        });

        // 필요량 set
        dvos.forEach(dvo -> {
            if ("N".equals(dvo.getRgstYn())) {
                if (dvo.getMmAvDdlvQty() == 0 || dvo.getWoStocQty() == 0) {
                    dvo.setNcstQty(0);
                } else {
                    dvo.setNcstQty((dvo.getMmAvDdlvQty() * 3) / dvo.getWoStocQty());
                }
            }
        });

        // 발주수량 set
        dvos.forEach(dvo -> {
            if ("N".equals(dvo.getRgstYn())) {
                if (dvo.getMinOrdQty() == 0 || dvo.getNcstQty() == 0) {
                    dvo.setGoQty(0);
                } else {
                    if (dvo.getMinOrdQty() > dvo.getNcstQty()) {
                        dvo.setGoQty(dvo.getMinOrdQty());
                    } else {
                        int i = 1;

                        while (dvo.getMinOrdQty() > 0) {
                            int goQty = dvo.getMinOrdQty() * i;

                            if (dvo.getNcstQty() >= goQty) {
                                dvo.setGoQty(goQty);
                                break;
                            }

                            i++;
                        }
                    }
                }
            }
        });

        // 발주금액 set
        dvos.forEach(dvo -> {
            if ("N".equals(dvo.getRgstYn())) {
                dvo.setGoAmt(dvo.getGoQty() * dvo.getGoUprc());
            }
        });

        return dvos;
    }

    @Transactional
    public int createBsCsmbGiveAOrderQty(List<CreatReq> dtos) {
        int processCount = 0;

        List<String> csmbPdCds = dtos.stream().map(CreatReq::csmbPdCd).toList();

        // 해당 관리년월 데이터 존재여부 확인
        int exstBsCsmbGiveAOrderQtyYn = mapper.selectExistBsCsmbGiveAOrderQtyYn(dtos.get(0).mngtYm(), csmbPdCds);

        if (exstBsCsmbGiveAOrderQtyYn > 0) { // 해당월 데이터 존재할경우 품목코드별로 삭제 후 insert
            for (String csmbPdCd : csmbPdCds) {
                mapper.deleteBsCsmbGiveAOrderQty(dtos.get(0).mngtYm(), csmbPdCd);
            }
        }

        List<WsnaBsCsmbGiveAOrderDvo> dvos = converter.listCreatReqToBsCsmbGiveAOrderDvos(dtos);

        for (WsnaBsCsmbGiveAOrderDvo dvo : dvos) {
            mapper.insertBsCsmbGiveAOrderQty(dvo);
            processCount++;
        }

        return processCount;
    }

    public List<ProdutCodeRes> getProductCodesByItmKndCd(String itmKndCd) {
        return mapper.selectProductCodesByItmKndCd(itmKndCd);
    }
}
