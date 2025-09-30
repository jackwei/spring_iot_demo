//package com.ykkj.utils.excel;
//
//import javax.servlet.http.HttpServletResponse;
//import java.io.OutputStream;
//import java.lang.reflect.Field;
//import java.net.URLEncoder;
//import java.util.*;
//import java.util.stream.Collectors;
//
///*
// * @description:支持角色权限的Excel工具类
// * @author: 龙谷情
// * @date: 2025-07-08 16:39:28
// **/
//public class RoleExcelUtil<T> {
//
//    private final Class<T> clazz;
//    private List<ExcelColumn> excelColumns;
//
//    public RoleExcelUtil(Class<T> clazz) {
//        this.clazz = clazz;
//        this.excelColumns = getFilteredColumns();
//    }
//
//    /**
//     * 获取过滤后的列（根据角色权限）
//     */
//    private List<ExcelColumn> getFilteredColumns() {
//        List<ExcelColumn> allColumns = new ArrayList<>();
//        Class<?> currentClass = clazz;
//
//        while (currentClass != Object.class) {
//            Field[] fields = currentClass.getDeclaredFields();
//            for (Field field : fields) {
//                RoleExcel roleExcel = field.getAnnotation(RoleExcel.class);
//                if (roleExcel == null) {
//                    continue;
//                }
//                if (hasExportPermission(roleExcel)) {
//                    allColumns.add(buildExcelColumn(field, roleExcel));
//                }
//            }
//            currentClass = currentClass.getSuperclass();
//        }
//        return allColumns;
//    }
//
//    /**
//     * 检查当前用户是否有权限导出该字段
//     */
//    private boolean hasExportPermission(RoleExcel roleExcel) {
//        if (roleExcel.allowAll()) {
//            return true;
//        }
//
//        LoginUser loginUser = null;//SecurityUtils.getLoginUser();
//        if (loginUser == null) {
//            return false;
//        }
//
//        // 检查角色权限
////        String[] allowRoles = roleExcel.roles();
////        if (allowRoles.length > 0) {
////            List<String> userRoleKeys = loginUser.getUser().getRoles().stream()
////                    .map(SysRole::getRoleKey)
////                    .collect(Collectors.toList());
////            for (String role : allowRoles) {
////                if (userRoleKeys.contains(role) || loginUser.getUser().isAdmin()) {
////                    return true;
////                }
////            }
////        }
//
//        return false;
//    }
//
//    /**
//     * 构建Excel列信息
//     */
//    private ExcelColumn buildExcelColumn(Field field, RoleExcel roleExcel) {
//        ExcelColumn column = new ExcelColumn();
//        column.setField(field);
//        column.setHeader(roleExcel.name());
//        column.setWidth(roleExcel.width());
//        column.setDateFormat(roleExcel.dateFormat());
//        return column;
//    }
//
//    /**
//     * 导出Excel文件
//     */
//    public void exportExcel(HttpServletResponse response, List<T> list, String title) {
//        try (Workbook workbook = new SXSSFWorkbook(500)) {
//            Sheet sheet = workbook.createSheet(title);
//            setSheetStyle(sheet);
//
//            // 创建表头
//            Row headerRow = sheet.createRow(0);
//            createHeader(headerRow);
//
//            // 填充数据
//            for (int i = 0; i < list.size(); i++) {
//                Row dataRow = sheet.createRow(i + 1);
//                fillDataRow(dataRow, list.get(i));
//            }
//
//            // 输出到客户端
//            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
//            response.setCharacterEncoding("utf-8");
//            String fileName = URLEncoder.encode(title + ".xlsx", "UTF-8").replaceAll("\\+", "%20");
//            response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName);
//
//            try (OutputStream os = response.getOutputStream()) {
//                workbook.write(os);
//                os.flush();
//            }
//        } catch (Exception e) {
//            throw new RuntimeException("导出Excel失败", e);
//        }
//    }
//
//    /**
//     * 设置工作表样式
//     */
//    private void setSheetStyle(Sheet sheet) {
//        for (int i = 0; i < excelColumns.size(); i++) {
//            sheet.setColumnWidth(i, excelColumns.get(i).getWidth() * 256);
//        }
//    }
//
//    /**
//     * 创建表头
//     */
//    private void createHeader(Row headerRow) {
//        CellStyle headerStyle = headerRow.getSheet().getWorkbook().createCellStyle();
//        Font headerFont = headerRow.getSheet().getWorkbook().createFont();
//        headerFont.setBold(true);
//        headerStyle.setFont(headerFont);
//        headerStyle.setAlignment(HorizontalAlignment.CENTER);
//
//        for (int i = 0; i < excelColumns.size(); i++) {
//            Cell cell = headerRow.createCell(i);
//            cell.setCellValue(excelColumns.get(i).getHeader());
//            cell.setCellStyle(headerStyle);
//        }
//    }
//
//    /**
//     * 填充数据行
//     */
//    private void fillDataRow(Row dataRow, T obj) throws Exception {
//        for (int i = 0; i < excelColumns.size(); i++) {
//            ExcelColumn column = excelColumns.get(i);
//            Field field = column.getField();
//            field.setAccessible(true);
//
//            Cell cell = dataRow.createCell(i);
//            Object value = field.get(obj);
//
//            if (value == null) {
//                cell.setCellValue("");
//                continue;
//            }
//
//            // 处理日期类型
//            if (value instanceof Date && !column.getDateFormat().isEmpty()) {
//                CellStyle style = dataRow.getSheet().getWorkbook().createCellStyle();
//                DataFormat format = dataRow.getSheet().getWorkbook().createDataFormat();
//                style.setDataFormat(format.getFormat(column.getDateFormat()));
//                cell.setCellStyle(style);
//                cell.setCellValue((Date) value);
//                continue;
//            }
//
//            // 处理基本类型
//            cell.setCellValue(value.toString());
//        }
//    }
//
//    // ExcelColumn类定义
//    public static class ExcelColumn {
//        private Field field;
//        private String header;
//        private int width;
//        private String dateFormat;
//
//        // getters/setters
//        public Field getField() {
//            return field;
//        }
//
//        public void setField(Field field) {
//            this.field = field;
//        }
//
//        public String getHeader() {
//            return header;
//        }
//
//        public void setHeader(String header) {
//            this.header = header;
//        }
//
//        public int getWidth() {
//            return width;
//        }
//
//        public void setWidth(int width) {
//            this.width = width;
//        }
//
//        public String getDateFormat() {
//            return dateFormat;
//        }
//
//        public void setDateFormat(String dateFormat) {
//            this.dateFormat = dateFormat;
//        }
//    }
//}
