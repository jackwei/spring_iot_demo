//package com.ykkj.utils.excel;
//
//
//import lombok.Data;
//
//@Data
//public class JiheHandledRate {
//    private static final long serialVersionUID = 1L;
//    /**
//     * 运管中心名称
//     */
//    @RoleExcel(name = "运管中心名称", allowAll = true)
//    private String ygcentername;
//
//    /**
//     * 总数量
//     */
//    @RoleExcel(name = "线索总数量", allowAll = true)
//    private Long totalCount;
//package com.sdhs.jihe.domain;
//
//import com.sdhs.common.annotation.RoleExcel;
//import com.sdhs.common.utils.CalculationUtils;
//import lombok.Data;
//
//import static com.sdhs.common.utils.CalculationUtils.divide;
//import static com.sdhs.common.utils.CalculationUtils.multiplyAndRound;
//
//    @Data
//    public class JiheHandledRate {
//        private static final long serialVersionUID = 1L;
//        /**
//         * 运管中心名称
//         */
//        @RoleExcel(name = "运管中心名称", allowAll = true)
//        private String ygcentername;
//
//        /**
//         * 总数量
//         */
//        @RoleExcel(name = "线索总数量", allowAll = true)
//        private Long totalCount;
//
//        /**
//         * 已处理数量
//         */
//        @RoleExcel(name = "已确认线索数量", allowAll = true)
//        private Long handledCount;
//
//        /**
//         * 待处理数量
//         */
//        @RoleExcel(name = "待确认线索数量", allowAll = true)
//        private Long unhandledCount;
//
//        /**
//         * 处理数量占比 描述
//         */
//        @RoleExcel(name = "线索确认占比", allowAll = true)
//        private String handledPercentDesc;
//
//        /**
//         * 工单发起数量
//         */
//        @RoleExcel(name = "工单发起数量", roles = {"internalAuditRole", "admin"})
//        private Long ticketCount;
//
//        /**
//         * 工单发起数量占比 描述
//         */
//        @RoleExcel(name = "工单发起数量占比", roles = {"internalAuditRole", "admin"})
//        private String ticketPercentDesc;
//
//        /**
//         * 应追缴金额
//         */
//        @RoleExcel(name = "工单合计漏征金额（元）", roles = {"internalAuditRole", "admin"})
//        private double owefeeYuan;
//
//        // 其他字段...
//    }
