package com.awesome.wow.treesearch;

import com.awesome.wow.dto.SubStatusDTO;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum OrderStatusEnum {
    UNPAID(0, "待支付"),
    ORDER_NOT_TAKEN(10, "待接单"),
    SAMPLE_NOT_TAKEN(20, "待收样"),
    SAMPLE_NOT_SENT(30, "待送样"),
    SAMPLE_SENT(40, "已送样"),
    NO_REPORT(50, "报告未出"),
    PARTIAL_REPORT(51, "部分报告已出"),
    ALL_REPORT(52, "报告已出"),
    ORDER_FINISHED(60, "已完成"),
    ORDER_CANCELED(70, "已取消"),
    REFUNDING(80, "退款中"),
    REFUND_FINISHED(81, "已退款"),

    UNKNOWN(-1, "代码未及时和数据库的订单推单状态保持同步"),
    ;

    @Getter
    private static int[][][] states;

    static {
        states = new int[][][]{
                //订单状态,支付状态,物流状态,推送状态,报告状态
                /*待支付*/{{UNPAID.code}, {0}, {0}, {0}, {-999}},
                /*待接单*/{{ORDER_NOT_TAKEN.code}, {100}, {105}, {0}, {-999}},
                /*待收样*/{{SAMPLE_NOT_TAKEN.code}, {100}, {110}, {0}, {-999}},
                /*待送样*/{{SAMPLE_NOT_SENT.code}, {100}, {200}, {0}, {-999}},
                /*已送样*/{{SAMPLE_SENT.code}, {100}, {210}, {220, 301}, {-999}},
                /*报告未出*/{{NO_REPORT.code}, {100}, {210}, {300}, {0}},
                /*部分报告已出*/{{PARTIAL_REPORT.code}, {100}, {210}, {300}, {1}},
                /*报告已出*/{{ALL_REPORT.code}, {100}, {210}, {300}, {2}},
                /*已完成*/{{ORDER_FINISHED.code}, {400}, {210}, {300}, {2}},
                /*已取消*/{{ORDER_CANCELED.code}, {500, 501}, {0}, {0}, {-999}},
                /*退款中*/{{REFUNDING.code}, {600}, {0, 105, 110, 200, 210}, {0, 220, 300, 301}, {0, 1, 2}},
                /*已退款*/{{REFUND_FINISHED.code}, {601}, {0, 105, 110, 200, 210}, {0, 220, 300, 301}, {0, 1, 2}}
        };

    }

    /**
     * 遍历指定列，取出包含传入值的行索引
     *
     * @param subState    指定匹配值
     * @param columnIndex 列索引
     */
    private static List<Integer> getRowsOfSubStatus(Integer subState, int columnIndex) {
        List<Integer> rowIndexList = new ArrayList<>();
        if (subState == null) {
            return rowIndexList;
        }
        for (int i = 0; i < states.length; i++) {
            //遍历第colIdx列
            int[] payS = states[i][columnIndex];
            for (int pay : payS) {
                if (pay == subState || pay == -999) {
                    rowIndexList.add(i);
                }
            }
        }
        return rowIndexList;
    }

    @Getter
    private final Integer code;

    @Getter
    private final String desc;

    OrderStatusEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static OrderStatusEnum valueOf(Integer status) {
        for (OrderStatusEnum e : OrderStatusEnum.values()) {
            if (e.getCode().equals(status)) {
                return e;
            }
        }
        return UNKNOWN;
    }

    /**
     * 传入4个状态返回新的订单状态枚举
     * todo:消除魔法值+优化
     *
     * @param payState       ：支付状态
     * @param logisticsState ：物流状态
     * @param pushState      ：推送状态
     * @param reportState    ：报告状态
     * @return ：新订单状态
     */
    public static OrderStatusEnum getOrderNewStateEnumBy4State(
            Integer payState
            , Integer logisticsState
            , Integer pushState
            , Integer reportState
    ) {
        List<Integer> allRowIdx = new ArrayList<>();
        for (int i = 0; i < states.length; i++) {
            allRowIdx.add(i);
        }

        List<Integer> payStateRows = payState != null ? getRowsOfSubStatus(payState, 1) : allRowIdx;
        List<Integer> logisticsStateRows = logisticsState != null ? getRowsOfSubStatus(logisticsState, 2) : allRowIdx;
        List<Integer> pushStateRows = pushState != null ? getRowsOfSubStatus(pushState, 3) : allRowIdx;
        List<Integer> reportStateRows = reportState != null ? getRowsOfSubStatus(reportState, 4) : allRowIdx;

        //取交集
        List<Integer> rows = Stream.of(payStateRows, logisticsStateRows, pushStateRows, reportStateRows)
                .reduce((a, b) -> {
                    a.retainAll(b);
                    return a;
                })
                .orElse(new ArrayList<>());

        if (rows.size() != 1) {
            return UNKNOWN;
        }

        int collectState = states[rows.get(0)][0][0];

        return valueOf(collectState);
    }

    /**
     * 根据汇总状态，转换成子状态
     *
     * @param collectState
     * @return
     */
    public static SubStatusDTO explainCollectState(Integer collectState) {
        if (collectState == null) {
            return null;
        }
        Integer fixRowIdx = null;
        for (int i = 0; i < states.length; i++) {
            if (states[i][0][0] == collectState) {
                fixRowIdx = i;
                break;
            }
        }
        if (fixRowIdx == null) {
            return null;
        }
        int[][] row = states[fixRowIdx];

        return SubStatusDTO.builder()
                .payStates(Arrays.stream(row[1]).filter(value -> value != -999).boxed().collect(Collectors.toList()))
                .logisticsStates(Arrays.stream(row[2]).filter(value -> value != -999).boxed().collect(Collectors.toList()))
                .pushStates(Arrays.stream(row[3]).filter(value -> value != -999).boxed().collect(Collectors.toList()))
                .reportStates(Arrays.stream(row[4]).filter(value -> value != -999).boxed().collect(Collectors.toList()))
                .build();
    }
}
