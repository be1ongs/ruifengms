package com.ruoyi.order.service.impl;

import java.io.File;
import java.io.FileInputStream;

import java.io.FileOutputStream;
import java.io.IOException;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.ShiroUtils;
import com.ruoyi.order.constants.BaseConstants;
import com.ruoyi.order.constants.StatusConstants;
import com.ruoyi.order.domain.*;
import com.ruoyi.order.service.IRfOrderService;
import com.ruoyi.order.service.IRfProduceNoticeDetailService;
import org.apache.commons.compress.utils.Lists;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.ruoyi.order.mapper.RfProduceNoticeMapper;
import com.ruoyi.order.service.IRfProduceNoticeService;
import com.ruoyi.common.core.text.Convert;
import org.springframework.transaction.annotation.Transactional;

/**
 * 生产通知单Service业务层处理
 *
 * @author pg
 * @date 2024-02-19
 */
@Service
public class RfProduceNoticeServiceImpl implements IRfProduceNoticeService {
    @Autowired
    private RfProduceNoticeMapper rfProduceNoticeMapper;

    @Autowired
    private IRfProduceNoticeDetailService iRfProduceNoticeDetailService;

    @Autowired
    private IRfProduceNoticeService iRfProduceNoticeService;

    @Autowired
    private IRfOrderService iRfOrderService;
    // 注入配置文件中的文件存储路径属性
    @Value("${file.storage.path}")
    private String fileStoragePath;
    // 注入配置文件中的文件存储路径属性
    @Value("${file.download.path}")
    private String fileDownloadPath;
    // 模板文件名称
    private static final String TEMPLATE_FILE_NAME = "tmp.xlsx";
    // 固定行号，从0开始计数
    private static final int FIXED_ROW_NUM = 3;
    // 固定行号，从0开始计数
    private static final int FIXED_ROW_NUM_TWO = 2;


    /**
     * 查询生产通知单
     *
     * @param id 生产通知单主键
     * @return 生产通知单
     */
    @Override
    public RfProduceNotice selectRfProduceNoticeById(Long id) {
        return rfProduceNoticeMapper.selectRfProduceNoticeById(id);
    }

    /**
     * 查询生产通知单列表
     *
     * @param rfProduceNotice 生产通知单
     * @return 生产通知单
     */
    @Override
    public List<RfProduceNotice> selectRfProduceNoticeList(RfProduceNotice rfProduceNotice) {
        rfProduceNotice.setDelFlag(BaseConstants.DEL_FLAG_NORMAL);
        List<RfProduceNotice> list = rfProduceNoticeMapper.selectRfProduceNoticeList(rfProduceNotice);
        list.forEach(e -> {
            //查询关联订单数量
            RfProduceNoticeDetail rfProduceNoticeDetail = new RfProduceNoticeDetail();
            rfProduceNoticeDetail.setProduceNoticeId(e.getId());
            rfProduceNoticeDetail.setDelFlag(BaseConstants.DEL_FLAG_NORMAL);
            List<RfProduceNoticeDetail> rfProduceNoticeDetailList = iRfProduceNoticeDetailService.selectRfProduceNoticeDetailList(rfProduceNoticeDetail);
            e.setProduceNum(rfProduceNoticeDetailList.size());
        });


        return list;
    }

    /**
     * 新增生产通知单
     *
     * @param rfProduceNotice 生产通知单
     * @return 结果
     */
    @Override
    public int insertRfProduceNotice(RfProduceNotice rfProduceNotice) {
        rfProduceNotice.setCreateTime(DateUtils.getNowDate());
        return rfProduceNoticeMapper.insertRfProduceNotice(rfProduceNotice);
    }

    /**
     * 修改生产通知单
     *
     * @param rfProduceNotice 生产通知单
     * @return 结果
     */
    @Override
    public int updateRfProduceNotice(RfProduceNotice rfProduceNotice) {
        rfProduceNotice.setUpdateTime(DateUtils.getNowDate());
        return rfProduceNoticeMapper.updateRfProduceNotice(rfProduceNotice);
    }

    /**
     * 撤销生产通知单及明细
     *
     * @param rfProduceNotice 生产通知单
     * @return 结果
     */
    @Override
    @Transactional
    public int revokeRfProduceNoticeAndDetail(RfProduceNotice rfProduceNotice) {
        rfProduceNotice.setDelFlag(BaseConstants.DEL_FLAG_DELETED);
        //先撤销生产通知单主表数据
        rfProduceNoticeMapper.updateRfProduceNotice(rfProduceNotice);
        //再撤销生产通知单子表数据
        //先查关联上的子表数据有多少
        RfProduceNoticeDetail detail = new RfProduceNoticeDetail();
        detail.setDelFlag(BaseConstants.DEL_FLAG_NORMAL);
        detail.setProduceNoticeId(rfProduceNotice.getId());
        List<RfProduceNoticeDetail> rfProduceNoticeDetailList = iRfProduceNoticeDetailService.selectRfProduceNoticeDetailList(detail);
        rfProduceNoticeDetailList.stream().forEach(e -> {
            Long id = e.getId();
            e = new RfProduceNoticeDetail();
            e.setId(id);
            e.setDelFlag(BaseConstants.DEL_FLAG_DELETED);
            iRfProduceNoticeDetailService.updateRfProduceNoticeDetail(e);
        });
        return rfProduceNoticeDetailList.size();
    }

    @Override
    public int generateRfProduceNoticeAndDetailExcel(RfProduceNotice rfProduceNotice) throws Exception {
        rfProduceNotice = iRfProduceNoticeService.selectRfProduceNoticeById(rfProduceNotice.getId());
        // 获取生产通知单详情
        List<RfProduceNoticeDetail> rfProduceNoticeDetailList = getRfProduceNoticeDetailList(rfProduceNotice);
        try (FileInputStream fileInputStream = new FileInputStream(new File(fileStoragePath + TEMPLATE_FILE_NAME))) {
            // 使用 WorkbookFactory 创建 Workbook 对象，支持 .xls 和 .xlsx 格式
            Workbook workbook = WorkbookFactory.create(fileInputStream);
            // 获取第一个工作表（Sheet）
            Sheet sheet = workbook.getSheetAt(0);
            for (int i = 0; i < sheet.getNumMergedRegions(); i++) {
                CellRangeAddress mergedRegion = sheet.getMergedRegion(i);
                int firstRow = mergedRegion.getFirstRow();
                int firstColumn = mergedRegion.getFirstColumn();
                Row row = sheet.getRow(firstRow);
                if (row != null) {
                    Cell cell = row.getCell(firstColumn);
                    if (cell != null) {
                        // 获取合并单元格的值
                        String cellValue = cell.getStringCellValue();
                        System.out.println("合并单元格的值: " + cellValue);
                    }
                }
            }
            // 处理日期单元格
            processDateCell(sheet);
            //处理编号单元格
            processNoCell(sheet,workbook,rfProduceNotice);
            // 从固定行数开始填充数据
            fillData(sheet, rfProduceNoticeDetailList);
            // 生成当前时间戳作为文件名的一部分
            String timestamp = String.valueOf(System.currentTimeMillis());
            String fileName = saveExcelAndRfProduceNotice(rfProduceNotice, rfProduceNoticeDetailList, workbook);
            System.out.println("成功生成Excel文件：" + fileName);
        } catch (IOException e) {
            System.err.println("读取 Excel 模板文件时出错：" + e.getMessage());
            e.printStackTrace();
            throw new Exception("读取 Excel 模板文件时出错");

        }
        return rfProduceNoticeDetailList.size();
    }

    // 获取生产通知单详情
    private List<RfProduceNoticeDetail> getRfProduceNoticeDetailList(RfProduceNotice rfProduceNotice) {
        RfProduceNoticeDetail detail = new RfProduceNoticeDetail();
        detail.setDelFlag(BaseConstants.DEL_FLAG_NORMAL);
        detail.setProduceNoticeId(rfProduceNotice.getId());
        return iRfProduceNoticeDetailService.selectRfProduceNoticeDetailList(detail);
    }
    // 处理编号单元格
    private void processNoCell(Sheet sheet,Workbook workbook,RfProduceNotice rfProduceNotice) {
        CellRangeAddress mergedRegion = sheet.getMergedRegion(FIXED_ROW_NUM_TWO);
        int noColumn = mergedRegion.getFirstColumn();
        Row row = sheet.getRow(mergedRegion.getFirstRow());
        if (row != null) {
            Cell cell = row.getCell(noColumn);
            if (cell != null) {
                // 创建样式并设置水平对齐方式为右对齐
                CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
                cellStyle.setAlignment(HorizontalAlignment.RIGHT);
                cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
                Font font = workbook.createFont();
                font.setFontHeightInPoints((short) 16); // 设置字体大小
                font.setBold(true); // 设置加粗
                cellStyle.setFont(font);

                // 创建日期单元格并设置样式
                cell.setCellStyle(cellStyle);
                cell.setCellValue(rfProduceNotice.getProduceNoticeCode());
            }
        }
    }
    // 处理日期单元格
    private void processDateCell(Sheet sheet) {
        CellRangeAddress mergedRegion = sheet.getMergedRegion(FIXED_ROW_NUM);
        int dateColumn = mergedRegion.getFirstColumn();
        Row row = sheet.getRow(mergedRegion.getFirstRow());
        if (row != null) {
            Cell cell = row.getCell(dateColumn);
            if (cell != null) {
                // 获取当前日期
                LocalDate currentDate = LocalDate.now();

                // 使用 DateTimeFormatter 格式化日期
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy年 MM月 dd日");

                // 格式化日期并输出
                String formattedDate = currentDate.format(formatter);

                // 创建样式并设置水平对齐方式为右对齐
                CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
                cellStyle.setAlignment(HorizontalAlignment.RIGHT);
                cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);

                // 创建日期单元格并设置样式
                cell.setCellStyle(cellStyle);
                cell.setCellValue(formattedDate);

                System.out.println("格式化后的日期字符串：" + formattedDate);
            }
        }
    }

    // 从固定行数开始填充数据
    private void fillData(Sheet sheet, List<RfProduceNoticeDetail> rfProduceNoticeDetailList) {
        int currentRowNum = FIXED_ROW_NUM + 1; // 从固定行数的下一行开始

        // 创建单元格样式
        CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
        cellStyle.setAlignment(HorizontalAlignment.CENTER); // 设置水平居中对齐
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER); // 设置垂直居中对齐
        cellStyle.setBorderTop(BorderStyle.THIN); // 设置上边框样式为细线
        cellStyle.setBorderBottom(BorderStyle.THIN); // 设置下边框样式为细线
        cellStyle.setBorderLeft(BorderStyle.THIN); // 设置左边框样式为细线
        cellStyle.setBorderRight(BorderStyle.THIN); // 设置右边框样式为细线

        // 循环填充数据
        for (RfProduceNoticeDetail rfProduceNoticeDetail : rfProduceNoticeDetailList) {
            Row newRow = sheet.createRow(currentRowNum++); // 创建新行
            newRow.setHeightInPoints(23); // 设置行高，单位为像素
            int currentCellNum = 0; // 追踪当前列数，从第一列开始

            // 填充数据到每一列
            fillCell(newRow.createCell(currentCellNum++), rfProduceNoticeDetail.getProductNameCn(), cellStyle);
            fillCell(newRow.createCell(currentCellNum++), rfProduceNoticeDetail.getProductNameEn(), cellStyle);
            fillCell(newRow.createCell(currentCellNum++), rfProduceNoticeDetail.getMaterial(), cellStyle);
            fillCell(newRow.createCell(currentCellNum++), rfProduceNoticeDetail.getNoticeAmount(), cellStyle);
            fillCell(newRow.createCell(currentCellNum++), rfProduceNoticeDetail.getMaterialStandards(), cellStyle);
            fillCell(newRow.createCell(currentCellNum++), "", cellStyle); // 占位列
            fillCell(newRow.createCell(currentCellNum++), "", cellStyle); // 占位列
            fillCell(newRow.createCell(currentCellNum++), rfProduceNoticeDetail.getNeedTime(), cellStyle);
            fillCell(newRow.createCell(currentCellNum++), rfProduceNoticeDetail.getOrderNum(), cellStyle);
            fillCell(newRow.createCell(currentCellNum++), rfProduceNoticeDetail.getRemark(), cellStyle);
        }
    }

    // 设置单元格内容及样式
    private void fillCell(Cell cell, Object value, CellStyle cellStyle) {
        cell.setCellStyle(cellStyle);
        if (value instanceof String) {
            cell.setCellValue((String) value);
        } else if (value instanceof Date) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            cell.setCellValue(dateFormat.format((Date) value));
        } else {
            cell.setCellValue(value == null ? "" : value.toString());
        }
    }

    // 保存填充好数据的Excel文件，带文件名参数
    private void saveExcel(Workbook workbook, String fileName) {
        try (FileOutputStream fileOut = new FileOutputStream(fileDownloadPath + fileName)) {
            workbook.write(fileOut);
            System.out.println("成功生成Excel文件：" + fileName);
        } catch (IOException e) {
            System.err.println("保存Excel文件时出错：" + e.getMessage());
            e.printStackTrace();
        }
    }


    @Transactional(rollbackFor = Exception.class)
    public String saveExcelAndRfProduceNotice(RfProduceNotice rfProduceNotice, List<RfProduceNoticeDetail> rfProduceNoticeDetailList, Workbook workbook) throws IOException {
        // 生成当前时间戳作为文件名的一部分
        String timestamp = String.valueOf(System.currentTimeMillis());

        // 文件名格式为：output_<timestamp>.xlsx
        String fileName = "output_" + timestamp + ".xlsx";

        // 保存填充好数据的Excel文件
        saveExcel(workbook, fileName);
        Long id = rfProduceNotice.getId();
        rfProduceNotice = new RfProduceNotice();
        rfProduceNotice.setId(id);
        // 回填文件地址到 rfProduceNotice 对象中
        rfProduceNotice.setXlsAddress(fileName);
        //设置rfProduceNotice 开始时间 和状态
        rfProduceNotice.setStartTime(DateUtils.getNowDate());
        rfProduceNotice.setStatus(StatusConstants.PRODUCING);

        //设置明细表的生产数量和生产中数量
        int totalNoticeAmount = 0;
        int finishAmount = 0;
        int produceAmount = 0;
        for (RfProduceNoticeDetail noticeDetail : rfProduceNoticeDetailList) {
            finishAmount += noticeDetail.getFinishAmount();
            produceAmount += noticeDetail.getProduceAmount();
            Long noticeDetailId = noticeDetail.getId();
            noticeDetail = new RfProduceNoticeDetail();
            noticeDetail.setId(noticeDetailId);
            noticeDetail.setStartTime(DateUtils.getNowDate());
            noticeDetail.setStatus(StatusConstants.PRODUCING);
            noticeDetail.setFinishAmount(finishAmount);
            noticeDetail.setProduceAmount(produceAmount);
            iRfProduceNoticeDetailService.updateRfProduceNoticeDetail(noticeDetail);
        }


        // 更新 rfProduceNotice 对象
        iRfProduceNoticeService.updateRfProduceNotice(rfProduceNotice);


        // 事务提交后，更新数据库操作会生效
        return fileName;
    }



    public int checkProNoticeConfirm(String ids) {
        String[] idList = ids.split(",");
        for (String id : idList) {
            int orderId = Integer.parseInt(id);
            RfOrder rfOrder = iRfOrderService.selectRfOrderById(orderId);
            if (rfOrder != null) {
                int orderAmount = rfOrder.getAmount();
                int totalNoticeAmount = calculateTotalNoticeAmount(orderId); // 计算订单对应的生产通知明细的数量总和
                if ((orderAmount - totalNoticeAmount) <= 0) {
                    return 0; // 如果生产通知明细的数量总和超过订单数量，则返回 0
                }
            }
        }
        return 1; // 所有订单的生产通知明细总数量均不超过订单数量
    }

    // 计算订单对应的生产通知明细的数量总和
    private int calculateTotalNoticeAmount(int orderId) {
        int totalNoticeAmount = 0;
        RfProduceNoticeDetail detail = new RfProduceNoticeDetail();
        detail.setDelFlag(BaseConstants.DEL_FLAG_NORMAL);
        detail.setOrderId((long) orderId);
        List<RfProduceNoticeDetail> rfProduceNoticeDetailList = iRfProduceNoticeDetailService.selectRfProduceNoticeDetailList(detail);
        for (RfProduceNoticeDetail noticeDetail : rfProduceNoticeDetailList) {
            totalNoticeAmount += noticeDetail.getNoticeAmount();
        }
        return totalNoticeAmount;
    }


    /**
     * 批量删除生产通知单
     *
     * @param ids 需要删除的生产通知单主键
     * @return 结果
     */
    @Override
    public int deleteRfProduceNoticeByIds(String ids) {
        return rfProduceNoticeMapper.deleteRfProduceNoticeByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除生产通知单信息
     *
     * @param id 生产通知单主键
     * @return 结果
     */
    @Override
    public int deleteRfProduceNoticeById(Long id) {
        return rfProduceNoticeMapper.deleteRfProduceNoticeById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertRfProduceNoticeAndDeatail(ProNoticeRequest request) {
        //生产通知单主表
        RfProduceNotice rfProduceNotice = new RfProduceNotice();
        String name = request.getName();
        List<RfOrderForProNotice> rfOrderList = request.getData();
        rfProduceNotice.setStatus(StatusConstants.NOT_START);
        rfProduceNotice.setCreateTime(DateUtils.getNowDate());
        rfProduceNotice.setCreateBy(ShiroUtils.getLoginName());
        rfProduceNotice.setDelFlag(BaseConstants.DEL_FLAG_NORMAL);
        rfProduceNotice.setProduceNoticeName(name);
        rfProduceNotice.setProduceNum(rfOrderList.size());
        this.insertRfProduceNotice(rfProduceNotice);
        Long id = rfProduceNotice.getId();
        //生产通知单明细表
        List<RfProduceNoticeDetail> noticeDetailList = Lists.newArrayList();
        for (int i = 0; i < rfOrderList.size(); i++) {
            RfOrderForProNotice e = rfOrderList.get(i);
            RfProduceNoticeDetail rfProduceNoticeDetail = new RfProduceNoticeDetail();
            BeanUtils.copyProperties(e, rfProduceNoticeDetail);
            rfProduceNoticeDetail.setOrderId(e.getId().longValue());
            rfProduceNoticeDetail.setNoticeAmount(e.getUnpaidNum());
            rfProduceNoticeDetail.setDelFlag(BaseConstants.DEL_FLAG_NORMAL);
            rfProduceNoticeDetail.setProduceNoticeId(id);
            rfProduceNoticeDetail.setStatus(StatusConstants.NOT_START);
            rfProduceNoticeDetail.setProduceAmount(0);
            rfProduceNoticeDetail.setFinishAmount(0);
            rfProduceNoticeDetail.setCreateTime(DateUtils.getNowDate());
            rfProduceNoticeDetail.setCreateBy(ShiroUtils.getLoginName());
            iRfProduceNoticeDetailService.insertRfProduceNoticeDetail(rfProduceNoticeDetail);
        }
        RfProduceNotice rfProduceNoticeForUpdateCode = new RfProduceNotice();
        String formattedNumber = String.format("No.%07d", id);
        System.out.println("Formatted number: " + formattedNumber);
        rfProduceNoticeForUpdateCode.setId(id);
        rfProduceNoticeForUpdateCode.setProduceNoticeCode(formattedNumber);

        return this.updateRfProduceNotice(rfProduceNoticeForUpdateCode);
    }
}
