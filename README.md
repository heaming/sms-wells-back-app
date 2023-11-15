# K-STATION

교원그룹 영업관리시스템 차세대 프로젝트

## Contents

- [ABOUT](#ABOUT)
- [SAMPLE CODE](#SAMPLE-CODE)

## ABOUT

모든 고객이 머물면서 모든 것을 해결하는 '장소' **K-station**
**K-station**은 교원 그룹사(빨간펜, 구몬, 웰스, 라이프) 사업 부문의 모든 고객이 머물면서 모든 업무를 해결하는 '장소'로, 보다 더 빠른 서비스를 지원하기 위한 영업관리 플랫폼입니다.
분화되어 있던 시스템 및 DB를 하나로 통합하고, Biz/IT프로세스 개선과 설계로 변화와 고객 대응에 유연한 서비스를 제공합니다.

* 서비스/모바일 영역 개발
* 팀 내 맡은 역할 :
    - 백엔드 / 프론트엔드 개발 :
      학생 성적 등록 / 조회 / 수정 / 삭제
    - Sk렌터카 API - 웰스 영업차량 관리 인터페이스 개발 :
      채팅 등록 / 조회 / 수정 / 삭제
    - DB설계 :
      웰스팜 모종 관련 TABLE 설계 (상품 정보, 배송 스케쥴, 재고 등)
    - Common :
      상품 검색, 웰스 매니저 & 엔지니어 조직 검색 컴포넌트 개발

## 프로젝트 일정

![img.png](img.png)

## 기술적 고려사항

    * 프로그래밍 언어 :
      Java, Servlet, JSP
    * 적용 Architecture :
      2Layer, MVC, Interface base, Controller, RestController
    * 적용 Framework :
      Spring Boot, Mybatis, Bootstrap
    * 웹 표준 기술 :
      CSS3, HTML5, Javascript, JQuery, Ajax, Node.js, Lombok, Sock.js
    * DB관리 기술:
      Oracle DB, Mongo DB
    * 기술적 특이사항 <적용 Open API> :
      FullCalendar API, Naver Search API, NAVER CLOVA chatbot API, NAVER sens API, I'mport API, Chart.js, SweetAlert,
      DataTable.js, Matrial.js, Scripts.js

* 기술적 고려사항
    * 프로그래밍 언어 :
      Java, Servlet, JSP
    * 적용 Architecture :
      2Layer, MVC, Interface base, Controller, RestController
    * 적용 Framework :
      Spring Boot, Mybatis, Bootstrap
    * 웹 표준 기술 :
      CSS3, HTML5, Javascript, JQuery, Ajax, Node.js, Lombok, Sock.js
    * DB관리 기술:
      Oracle DB, Mongo DB
    * 기술적 특이사항 <적용 Open API> :
      FullCalendar API, Naver Search API, NAVER CLOVA chatbot API, NAVER sens API, I'mport API, Chart.js, SweetAlert,
      DataTable.js, Matrial.js, Scripts.js

* 구현 영상 : https://drive.google.com/file/d/1gptKkNvCIh_jwRwpRKe7ffj2_0yuMhbI/view?usp=sharing
* 요약 PPT :
  https://drive.google.com/file/d/1Nqb813pXgP_7iNaknPhfCRpmO69CbUge/view?usp=sharing
* 프로젝트 분석설계서 :
  https://drive.google.com/file/d/1d1HDFC-xsA2rDLwvch73dOdBQEMMp5zh/view?usp=sharing

***

## SAMPLE CODE

> 1. 모종출하대차MAP
> 2. 일자별 설치재고 현황
> 3. 공통 컴포넌트 - 조직(엔지니어/매니저) 조회

### 1. 모종출하대차 MAP

**1-1. dvo**
```java


/**
 * <pre>
 * K-W-SV-U-0302M01 모종 출하대차 MAP dvo
 * </pre>
 *
 * @author heymi.cho
 * @since 2023-09-07
 */

@Getter
@Setter
public class WsnaSeedingTruckArrangementMapDvo {
    String dgLctCd;
    String dgLctNm;
    Integer truckNo;
    //    List<String> cart1F;
    //    List<String> cart1B;
    //    List<String> cart2F;
    //    List<String> cart2B;
    //    List<String> cart3F;
    //    List<String> cart3B;
    //    List<String> cart4F;
    //    List<String> cart4B;
    //    List<String> cart5F;
    //    List<String> cart5B;
    //    List<String> cart6F;
    //    List<String> cart6B;
    //    List<String> cart7F;
    //    List<String> cart7B;
    //    List<String> cart8F;
    //    List<String> cart8B;
    //    List<String> cart9F;
    //    List<String> cart9B;
    String cart1F;
    String cart1B;
    String cart2F;
    String cart2B;
    String cart3F;
    String cart3B;
    String cart4F;
    String cart4B;
    String cart5F;
    String cart5B;
    String cart6F;
    String cart6B;
    String cart7F;
    String cart7B;
    String cart8F;
    String cart8B;
    String cart9F;
    String cart9B;
}

/**
 * <pre>
 * K-W-SV-U-0302M01 모종 출하대차 MAP dvo
 * </pre>
 *
 * @author heymi.cho
 * @since 2023-09-07
 */

@Getter
//@Setter
@RequiredArgsConstructor
@EqualsAndHashCode(cacheStrategy = EqualsAndHashCode.CacheStrategy.LAZY)
public class WsnaSeedingTruckArrangementMapGgLctDvo {
    private final String ggLctCd;
    private final String ggLctNm;
    //    private List<WsnaSeedingTruckArrangementMapSeedDvo> seeds;
    //    private int hashCode;

    //    public WsnaSeedingTruckArrangementMapGgLctDvo(String ggLctCd, String ggLctNm) {
    //        this.ggLctCd = ggLctCd;
    //        this.ggLctNm = ggLctNm;
    //        this.hashCode = Objects.hash(ggLctCd, ggLctNm);
    //    }

    //    @Override
    //    public boolean equals(Object o) {
    //        if (this == o)
    //            return true;
    //        if (o == null || getClass() != o.getClass())
    //            return false;
    //        WsnaSeedingTruckArrangementMapGgLctDvo that = (WsnaSeedingTruckArrangementMapGgLctDvo)o;
    //        return ggLctCd == that.ggLctCd && ggLctNm == that.ggLctNm;
    //    }
    //
    //    @Override
    //    public int hashCode() {
    //        return this.hashCode;
    //    }
}

/**
 * <pre>
 * K-W-SV-U-0302M01 모종 출하대차 MAP dvo
 * </pre>
 *
 * @author heymi.cho
 * @since 2023-09-07
 */

@Getter
@Setter
@AllArgsConstructor
public class WsnaSeedingTruckArrangementMapSeedDvo {

    private String sdingPkgGrpCd;
    private String sdingPkgGrpCdNm;
    private Integer sdingPkgGrpqty;

}

```

**1-2. dto**
![main_attendace](https://user-images.githubusercontent.com/85826542/177026047-e739b864-4c98-4d28-a9a2-3be7c92c85f8.gif)
```java
/* dto */
/**
 * @title 모종 출하대차 MAP dto
 * @author heymi.cho
 * @since 2023-09-07
 */

public class WsnaSeedingTruckArrangementMapDto {

    @Builder
    @ApiModel("WsnaSeedingTruckArrangementMapDto-SearchReq")
    public record SearchReq(
        @NotNull
        String baseDt
    ) {}

    @Builder
    @ApiModel("WsnaSeedingTruckArrangementMapDto-SearchTodayGgLct")
    public record SearchTodayGgLct(
        String dgGgLctCd,
        String dgGgLctNm
    ) {}

    @Builder
    @ApiModel("WsnaSeedingTruckArrangementMapDto-SearchSeedAgg")
    public record SearchSeedAgg(
        String dgGgLctCd,
        String dgGgLctNm,
        String sdingPkgGrpCd,

        String sdingPkgGrpCdNm,

        Integer sdingQty
    ) {}

    @Builder
    @ApiModel("WsnaSeedingTruckArrangementMapDto-SearchSeedTotalRes")
    public record SearchSeedTotalRes(
        Integer pak01,
        Integer pak02,
        Integer pak03,
        Integer pak04,
        Integer pak05,
        Integer pak13,
        Integer pak23,
        Integer pak50,
        Integer pak08,
        Integer pak09,
        Integer pak12,
        Integer pak51,
        Integer pak52,
        Integer pak53,
        Integer pak54,
        Integer pak55,
        Integer pak56,
        Integer pak57,
        Integer pak58,
        Integer pak59,
        Integer pak60,
        Integer pak24,
        Integer pak28,
        Integer pak29,
        Integer pak30,
        Integer pak31
    ) {}

    @Builder
    @ApiModel("WsnaSeedingTruckArrangementMapDto-SearchRes")
    public record SearchRes(
        List<SearchSeedAgrgRes> seedAgrgRes,
        SearchSeedTotalRes seedTotalRes
    ) {}

    @Builder
    @ApiModel("WsnaSeedingTruckArrangementMapDto-SearchLabelRes")
    public record SearchLabelRes(
        String dgLctNm,
        Integer cartNo,
        Integer truckNo,
        String basedt,
        String cartF,
        String cartB
    ) {}

    @Builder
    @ApiModel("WsnaSeedingTruckArrangementMapDto-SearchSeedAgrgRes")
    public record SearchSeedAgrgRes(
        String dgLctCd,
        String dgLctNm,
        Integer truckNo,
        String cart1F,
        String cart1B,
        String cart2F,
        String cart2B,
        String cart3F,
        String cart3B,
        String cart4F,
        String cart4B,
        String cart5F,
        String cart5B,
        String cart6F,
        String cart6B,
        String cart7F,
        String cart7B,
        String cart8F,
        String cart8B,
        String cart9F,
        String cart9B

    ) {}

}

```

**1-3. service**
```java

/**
 * <pre>
 * K-W-SV-U-0302M01 모종 출하대차 MAP
 * </pre>
 *
 * @author heymi.cho
 * @since 2023-09-07
 */

@Service
@RequiredArgsConstructor
public class WsnaSeedingTruckArrangementMapService {

    private final WsnaSeedingTruckArrangementMapMapper mapper;
    //    private final ArrayList<WsnaSeedingTruckArrangementMapSeedDvo> seedTotal;

    /**
     * 모종 출고 센터 조회
     * @param dto
     * @return
     */
    public List<SearchTodayGgLct> getTodayGgLct(
        SearchReq dto
    ) {
        return mapper.selectTodayGgLct(dto);
    }

    /**
     * 모종 출하대차 MAP 조회
     * @param dto
     * @return
     */
    public WsnaSeedingTruckArrangementMapDto.SearchRes getSeedingTruckArragementMap(
        SearchReq dto
    ) {
        List<SearchSeedAgrgRes> result = new ArrayList<>();
        List<SearchSeedAgg> seedAggs = mapper.selectSeedAggregation(dto);

        Map<WsnaSeedingTruckArrangementMapGgLctDvo, Queue<WsnaSeedingTruckArrangementMapSeedDvo>> aggMap = new HashMap<>();
        Map<String, Integer> seedTotalMap = new HashMap<>();

        // 센터별 모종수량 리스트
        for (int i = 0; i < seedAggs.size(); i++) {
            SearchSeedAgg seedAgg = seedAggs.get(i);
            WsnaSeedingTruckArrangementMapGgLctDvo ggLct = new WsnaSeedingTruckArrangementMapGgLctDvo(
                seedAgg.dgGgLctCd(), seedAgg.dgGgLctNm()
            );

            Queue<WsnaSeedingTruckArrangementMapSeedDvo> seedQtyQue = aggMap.getOrDefault(ggLct, new LinkedList<>());
            seedQtyQue.offer(
                new WsnaSeedingTruckArrangementMapSeedDvo(
                    seedAgg.sdingPkgGrpCd(), seedAgg.sdingPkgGrpCdNm(), seedAgg.sdingQty()
                )
            );

            aggMap.put(ggLct, seedQtyQue);

            seedTotalMap
                .put(
                    seedAgg.sdingPkgGrpCd(), seedTotalMap.getOrDefault(seedAgg.sdingPkgGrpCd(), 0) + seedAgg.sdingQty()
                );
        }

        // 모종별 합계
        //        for (String sdingPkgGrpCd : seedTotalMap.keySet()) {
        //            int qty = seedTotalMap.get(sdingPkgGrpCd);
        //            seedTotal.add(new WsnaSeedingTruckArrangementMapSeedDvo(sdingPkgGrpCd, sdingPkgGrpCd, qty));
        //        }

        // 모종별 합산
        SearchSeedTotalRes seedTotalRes = new SearchSeedTotalRes(
            seedTotalMap.getOrDefault("PAK01", 0),
            seedTotalMap.getOrDefault("PAK02", 0),
            seedTotalMap.getOrDefault("PAK03", 0),
            seedTotalMap.getOrDefault("PAK04", 0),
            seedTotalMap.getOrDefault("PAK05", 0),
            seedTotalMap.getOrDefault("PAK13", 0),
            seedTotalMap.getOrDefault("PAK23", 0),
            seedTotalMap.getOrDefault("PAK50", 0),
            seedTotalMap.getOrDefault("PAK08", 0),
            seedTotalMap.getOrDefault("PAK09", 0),
            seedTotalMap.getOrDefault("PAK12", 0),
            seedTotalMap.getOrDefault("PAK51", 0),
            seedTotalMap.getOrDefault("PAK52", 0),
            seedTotalMap.getOrDefault("PAK53", 0),
            seedTotalMap.getOrDefault("PAK54", 0),
            seedTotalMap.getOrDefault("PAK55", 0),
            seedTotalMap.getOrDefault("PAK56", 0),
            seedTotalMap.getOrDefault("PAK57", 0),
            seedTotalMap.getOrDefault("PAK58", 0),
            seedTotalMap.getOrDefault("PAK59", 0),
            seedTotalMap.getOrDefault("PAK60", 0),
            seedTotalMap.getOrDefault("PAK24", 0),
            seedTotalMap.getOrDefault("PAK28", 0),
            seedTotalMap.getOrDefault("PAK29", 0),
            seedTotalMap.getOrDefault("PAK30", 0),
            seedTotalMap.getOrDefault("PAK31", 0)
        );

        // 모종수량리스트 배차
        for (WsnaSeedingTruckArrangementMapGgLctDvo ggLct : aggMap.keySet()) {
            List<String> cart1F = new ArrayList<>();
            List<String> cart1B = new ArrayList<>();
            List<String> cart2F = new ArrayList<>();
            List<String> cart2B = new ArrayList<>();
            List<String> cart3F = new ArrayList<>();
            List<String> cart3B = new ArrayList<>();
            List<String> cart4F = new ArrayList<>();
            List<String> cart4B = new ArrayList<>();
            List<String> cart5F = new ArrayList<>();
            List<String> cart5B = new ArrayList<>();
            List<String> cart6F = new ArrayList<>();
            List<String> cart6B = new ArrayList<>();
            List<String> cart7F = new ArrayList<>();
            List<String> cart7B = new ArrayList<>();
            List<String> cart8F = new ArrayList<>();
            List<String> cart8B = new ArrayList<>();
            List<String> cart9F = new ArrayList<>();
            List<String> cart9B = new ArrayList<>();

            Queue<WsnaSeedingTruckArrangementMapSeedDvo> seedQtyQue = aggMap.get(ggLct);
            List<String> cart = new ArrayList<>();
            int truckNo = 1;
            String fb = "F";
            int sdQtyInTruck = 0;

            while (!seedQtyQue.isEmpty()) {
                //                if (fb.equals("F")) {
                //                    truckNo++;
                //                }
                WsnaSeedingTruckArrangementMapSeedDvo seed = seedQtyQue.peek();

                if (sdQtyInTruck + seed.getSdingPkgGrpqty() <= 16) {
                    seed = seedQtyQue.poll();
                    cart.add(seed.getSdingPkgGrpCdNm() + "(" + seed.getSdingPkgGrpqty() + ")");
                    sdQtyInTruck += seed.getSdingPkgGrpqty();
                } else if (sdQtyInTruck + seed.getSdingPkgGrpqty() > 16) {
                    int left = 16 - sdQtyInTruck;
                    cart.add(seed.getSdingPkgGrpCdNm() + "(" + left + ")");
                    seed.setSdingPkgGrpqty(seed.getSdingPkgGrpqty() - left);
                    sdQtyInTruck += left;
                }

                if (sdQtyInTruck >= 16 || seedQtyQue.isEmpty()) {
                    switch (truckNo) {
                        case 1:
                            if (fb.equals("F")) {
                                cart1F.addAll(cart);
                            } else {
                                cart1B.addAll(cart);
                            }
                            break;
                        case 2:
                            if (fb.equals("F")) {
                                cart2F.addAll(cart);
                            } else {
                                cart2B.addAll(cart);
                            }
                            break;
                        case 3:
                            if (fb.equals("F")) {
                                cart3F.addAll(cart);
                            } else {
                                cart3B.addAll(cart);
                            }
                            break;
                        case 4:
                            if (fb.equals("F")) {
                                cart4F.addAll(cart);
                            } else {
                                cart4B.addAll(cart);
                            }
                            break;
                        case 5:
                            if (fb.equals("F")) {
                                cart5F.addAll(cart);
                            } else {
                                cart5B.addAll(cart);
                            }
                            break;
                        case 6:
                            if (fb.equals("F")) {
                                cart6F.addAll(cart);
                            } else {
                                cart6B.addAll(cart);
                            }
                            break;
                        case 7:
                            if (fb.equals("F")) {
                                cart7F.addAll(cart);
                            } else {
                                cart7B.addAll(cart);
                            }
                            break;
                        case 8:
                            if (fb.equals("F")) {
                                cart8F.addAll(cart);
                            } else {
                                cart8B.addAll(cart);
                            }
                            break;
                        case 9:
                            if (fb.equals("F")) {
                                cart9F.addAll(cart);
                            } else {
                                cart9B.addAll(cart);
                            }
                            break;
                        default:
                            break;
                    }

                    sdQtyInTruck = 0;
                    if (fb.equals("B") && !seedQtyQue.isEmpty()) {
                        truckNo++;
                    }
                    fb = fb.equals("F") ? "B" : "F";
                    cart.clear();
                }
            }

            SearchSeedAgrgRes searchSeedAgrgRes = new SearchSeedAgrgRes(
                ggLct.getGgLctCd(),
                ggLct.getGgLctNm(),
                truckNo,
                String.join("\n", cart1F),
                String.join("\n", cart1B),
                String.join("\n", cart2F),
                String.join("\n", cart2B),
                String.join("\n", cart3F),
                String.join("\n", cart3B),
                String.join("\n", cart4F),
                String.join("\n", cart4B),
                String.join("\n", cart5F),
                String.join("\n", cart5B),
                String.join("\n", cart6F),
                String.join("\n", cart6B),
                String.join("\n", cart7F),
                String.join("\n", cart7B),
                String.join("\n", cart8F),
                String.join("\n", cart8B),
                String.join("\n", cart9F),
                String.join("\n", cart9B)
                //                cart1F,
                //                cart1B,
                //                cart2F,
                //                cart2B,
                //                cart3F,
                //                cart3B,
                //                cart4F,
                //                cart4B,
                //                cart5F,
                //                cart5B,
                //                cart6F,
                //                cart6B,
                //                cart7F,
                //                cart7B,
                //                cart8F,
                //                cart8B,
                //                cart9F,
                //                cart9B
            );
            result.add(searchSeedAgrgRes);

        }
        Collections.sort(result, Comparator.comparing(SearchSeedAgrgRes::dgLctCd));

        return new WsnaSeedingTruckArrangementMapDto.SearchRes(result, seedTotalRes);
    }

    /**
     * 모종 출하대차 MAP 조회
     * @param dto
     * @return
     */
    public List<SearchLabelRes> getSeedingTruckArrangementLabel(
        SearchReq dto
    ) {
        List<SearchLabelRes> result = new ArrayList<>();

        List<SearchSeedAgg> seedAggs = mapper.selectSeedAggregation(dto);
        Map<WsnaSeedingTruckArrangementMapGgLctDvo, Queue<WsnaSeedingTruckArrangementMapSeedDvo>> aggMap = new HashMap<>();

        // 센터별 모종수량 리스트
        for (int i = 0; i < seedAggs.size(); i++) {
            SearchSeedAgg seedAgg = seedAggs.get(i);
            WsnaSeedingTruckArrangementMapGgLctDvo ggLct = new WsnaSeedingTruckArrangementMapGgLctDvo(
                seedAgg.dgGgLctCd(), seedAgg.dgGgLctNm()
            );

            Queue<WsnaSeedingTruckArrangementMapSeedDvo> seedQtyQue = aggMap.getOrDefault(ggLct, new LinkedList<>());
            seedQtyQue.offer(
                new WsnaSeedingTruckArrangementMapSeedDvo(
                    seedAgg.sdingPkgGrpCd(), seedAgg.sdingPkgGrpCdNm(), seedAgg.sdingQty()
                )
            );

            aggMap.put(ggLct, seedQtyQue);

        }

        // 배차
        for (WsnaSeedingTruckArrangementMapGgLctDvo ggLct : aggMap.keySet()) {

            Queue<WsnaSeedingTruckArrangementMapSeedDvo> seedQtyQue = aggMap.get(ggLct); // [샐러드 16, 야채 15]

            List<String> cartF = new ArrayList<>();
            List<String> cartB = new ArrayList<>();
            List<String> cart = new ArrayList<>(); // temp
            int truckNo = 1;
            String fb = "F";
            int sdQtyInTruck = 0; // 16개 되는지 확인

            // 차 앞||뒤 16개 채우기
            while (!seedQtyQue.isEmpty()) {
                WsnaSeedingTruckArrangementMapSeedDvo seed = seedQtyQue.peek();

                if (sdQtyInTruck + seed.getSdingPkgGrpqty() <= 16) {
                    seed = seedQtyQue.poll();
                    cart.add(seed.getSdingPkgGrpCdNm() + "\t(" + seed.getSdingPkgGrpqty() + ")");
                    sdQtyInTruck += seed.getSdingPkgGrpqty();
                } else if (sdQtyInTruck + seed.getSdingPkgGrpqty() > 16) {
                    int left = 16 - sdQtyInTruck;

                    if (left > 0) {
                        cart.add(seed.getSdingPkgGrpCdNm() + "\t(" + left + ")");
                        seed.setSdingPkgGrpqty(seed.getSdingPkgGrpqty() - left);
                        sdQtyInTruck += left;
                    }

                }

                if (sdQtyInTruck >= 16 || seedQtyQue.isEmpty()) {
                    if (fb.equals("F")) {
                        cartF.addAll(cart);

                        if (seedQtyQue.isEmpty()) {
                            SearchLabelRes labelRes = new SearchLabelRes(
                                ggLct.getGgLctNm(),
                                truckNo,
                                0,
                                dto.baseDt(),
                                String.join("\n", cartF),
                                ""
                            );
                            result.add(labelRes);
                            cartF.clear();
                            cartB.clear();
                        }
                    } else {
                        cartB.addAll(cart);

                        SearchLabelRes labelRes = new SearchLabelRes(
                            ggLct.getGgLctNm(),
                            truckNo,
                            0,
                            dto.baseDt(),
                            String.join("\n", cartF),
                            String.join("\n", cartB)
                        );
                        result.add(labelRes);
                        cartF.clear();
                        cartB.clear();
                    }

                    sdQtyInTruck = 0;

                    if (fb.equals("B") && !seedQtyQue.isEmpty()) {
                        truckNo++;
                    }
                    fb = fb.equals("F") ? "B" : "F";
                    cart.clear();
                }
            }
            int finalTruckNo = truckNo;
            result = result.stream().map(
                item -> {
                    if (item.dgLctNm().equals(ggLct.getGgLctNm())) {
                        item = new SearchLabelRes(
                            item.dgLctNm(),
                            item.cartNo(),
                            finalTruckNo,
                            item.basedt(),
                            item.cartF(),
                            item.cartB()
                        );
                    }
                    return item;
                }
            ).collect(Collectors.toList());
        }

        return result;
    }
}

```
**1-4. controller**

**1-5.excel**
