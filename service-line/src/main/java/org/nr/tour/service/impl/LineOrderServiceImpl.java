package org.nr.tour.service.impl;

import org.nr.tour.BaseOrderRepository;
import org.nr.tour.BaseOrderServiceImpl;
import org.nr.tour.domain.Insurance;
import org.nr.tour.domain.LineOrder;
import org.nr.tour.domain.LineOrderInsurance;
import org.nr.tour.domain.LineOrderVisitor;
import org.nr.tour.domain.util.EntityUtils;
import org.nr.tour.repository.LineOrderInsuranceRepository;
import org.nr.tour.repository.LineOrderRepository;
import org.nr.tour.repository.LineOrderVisitorRepository;
import org.nr.tour.service.LineOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author chenhaiyang <690732060@qq.com>
 */
@Service
@Transactional
public class LineOrderServiceImpl extends BaseOrderServiceImpl<LineOrder> implements LineOrderService {

    @Autowired
    LineOrderVisitorRepository lineOrderVisitorRepository;

    @Autowired
    LineOrderInsuranceRepository lineOrderInsuranceRepository;

    @Autowired
    LineOrderRepository lineOrderRepository;

    @Override
    public void updateVisitors(String orderId, List<LineOrderVisitor> visitors) {
        lineOrderVisitorRepository.delete(lineOrderVisitorRepository.findByOrderId(orderId));

        for (LineOrderVisitor visitor : visitors) {
            visitor.setOrderId(orderId);
            EntityUtils.preSave(visitor);
            lineOrderVisitorRepository.save(visitor);
        }
    }

    @Override
    public void updateInsurances(String orderId, List<Insurance> insurances) {
        final List<LineOrderInsurance> alreadyExist = lineOrderInsuranceRepository.findByLineOrderId(orderId);
        if (!CollectionUtils.isEmpty(alreadyExist)) {
            lineOrderInsuranceRepository.delete(alreadyExist);
        }

        for (Insurance insurance : insurances) {
            LineOrderInsurance lineOrderInsurance = new LineOrderInsurance();
            lineOrderInsurance.setLineOrderId(orderId);
            lineOrderInsurance.setInsuranceId(insurance.getId());
            lineOrderInsurance.setPrice(insurance.getPrice());

            EntityUtils.preSave(lineOrderInsurance);
            lineOrderInsuranceRepository.save(lineOrderInsurance);
        }
    }

    @Override
    protected BaseOrderRepository<LineOrder> getRepository() {
        return lineOrderRepository;
    }
}
