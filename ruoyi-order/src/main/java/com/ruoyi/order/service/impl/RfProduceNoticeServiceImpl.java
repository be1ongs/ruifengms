package com.ruoyi.order.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.ShiroUtils;
import com.ruoyi.order.constants.StatusConstants;
import com.ruoyi.order.domain.ProNoticeRequest;
import com.ruoyi.order.domain.RfOrderForProNotice;
import com.ruoyi.order.domain.RfProduceNoticeDetail;
import com.ruoyi.order.service.IRfProduceNoticeDetailService;
import org.apache.commons.compress.utils.Lists;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.order.mapper.RfProduceNoticeMapper;
import com.ruoyi.order.domain.RfProduceNotice;
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
        return rfProduceNoticeMapper.selectRfProduceNoticeList(rfProduceNotice);
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
            rfProduceNoticeDetail.setProduceNoticeId(id);
            rfProduceNoticeDetail.setStatus(StatusConstants.NOT_START);
            rfProduceNotice.setCreateTime(DateUtils.getNowDate());
            rfProduceNotice.setCreateBy(ShiroUtils.getLoginName());
            iRfProduceNoticeDetailService.insertRfProduceNoticeDetail(rfProduceNoticeDetail);
        }




        //生成excel


        return 0;
    }
}
