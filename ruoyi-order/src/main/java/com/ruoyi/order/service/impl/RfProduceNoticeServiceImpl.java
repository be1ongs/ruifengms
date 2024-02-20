package com.ruoyi.order.service.impl;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.ShiroUtils;
import com.ruoyi.order.constants.BaseConstants;
import com.ruoyi.order.constants.StatusConstants;
import com.ruoyi.order.domain.*;
import com.ruoyi.order.service.IRfOrderService;
import com.ruoyi.order.service.IRfProduceNoticeDetailService;
import org.apache.commons.compress.utils.Lists;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.order.mapper.RfProduceNoticeMapper;
import com.ruoyi.order.service.IRfProduceNoticeService;
import com.ruoyi.common.core.text.Convert;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

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
    private IRfOrderService iRfOrderService;


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
        list.forEach(e->{
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
        rfProduceNoticeDetailList.stream().forEach(e->{
            Long id =e.getId();
            e = new RfProduceNoticeDetail();
            e.setId(id);
            e.setDelFlag(BaseConstants.DEL_FLAG_DELETED);
            iRfProduceNoticeDetailService.updateRfProduceNoticeDetail(e);
        });
        return rfProduceNoticeDetailList.size();
    }

    public int checkProNoticeConfirm(String ids) {
        String[] idList = ids.split(",");
        for (String id : idList) {
            int orderId = Integer.parseInt(id);
            RfOrder rfOrder = iRfOrderService.selectRfOrderById(orderId);
            if (rfOrder != null) {
                int orderAmount = rfOrder.getAmount();
                int totalNoticeAmount = calculateTotalNoticeAmount(orderId); // 计算订单对应的生产通知明细的数量总和
                if (( orderAmount - totalNoticeAmount) <= 0) {
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
    public int insertRfProduceNoticeAndDeatail(ProNoticeRequest request){
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
        for (int i =0; i <rfOrderList.size(); i++){
            RfOrderForProNotice e = rfOrderList.get(i);
            RfProduceNoticeDetail rfProduceNoticeDetail = new RfProduceNoticeDetail();
            BeanUtils.copyProperties(e, rfProduceNoticeDetail);
            rfProduceNoticeDetail.setOrderId(e.getId().longValue());
            rfProduceNoticeDetail.setNoticeAmount(e.getUnpaidNum());
            rfProduceNoticeDetail.setDelFlag(BaseConstants.DEL_FLAG_NORMAL);
            rfProduceNoticeDetail.setProduceNoticeId(id);
            rfProduceNoticeDetail.setStatus(StatusConstants.NOT_START);
            rfProduceNoticeDetail.setCreateTime(DateUtils.getNowDate());
            rfProduceNoticeDetail.setCreateBy(ShiroUtils.getLoginName());
            iRfProduceNoticeDetailService.insertRfProduceNoticeDetail(rfProduceNoticeDetail);
        }
        RfProduceNotice rfProduceNoticeForUpdateCode  = new RfProduceNotice();
        String formattedNumber = String.format("No.%07d", id);
        System.out.println("Formatted number: " + formattedNumber);
        rfProduceNoticeForUpdateCode.setId(id);
        rfProduceNoticeForUpdateCode.setProduceNoticeCode(formattedNumber);

        return this.updateRfProduceNotice(rfProduceNoticeForUpdateCode);
    }
}
