package com.jnxy.base.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jnxy.base.entity.Supplier;
import com.jnxy.base.mapper.SupplierMapper;
import com.jnxy.base.service.ISupplierService;
import com.jnxy.common.vo.UserContext;
import com.jnxy.sys.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * <p>
 * 供应商信息表 服务实现类
 * </p>
 *
 * @author liyaolong
 * @since 2023-03-31
 */
@Service
public class SupplierServiceImpl extends ServiceImpl<SupplierMapper, Supplier> implements ISupplierService {
    @Override
    @Transactional
    public void addSupplier(Supplier supplier) {
        User user = UserContext.getCurrentUserObj();
        supplier.setCreatedUser(user.getUsername());
        Date createdDate = new Date();
        supplier.setCreatedDate(createdDate);
        this.baseMapper.insert(supplier);
    }

    @Override
    public Supplier getSupplierById(Long supplierId) {
        return this.baseMapper.selectById(supplierId);
    }

    @Override
    @Transactional
    public void updateSupplier(Supplier supplier) {
        this.baseMapper.updateById(supplier);
    }

    @Override
    public void deleteSupplierById(Long supplierId) {
        this.baseMapper.deleteById(supplierId);
    }
}
