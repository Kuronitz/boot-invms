package com.jnxy.base.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jnxy.base.entity.Supplier;

/**
 * <p>
 * 供应商信息表 服务类
 * </p>
 *
 * @author liyaolong
 * @since 2023-03-31
 */
public interface ISupplierService extends IService<Supplier> {
    void addSupplier(Supplier supplier);

    void updateSupplier(Supplier supplier);

    Supplier getSupplierById(Long supplierId);

    void deleteSupplierById(Long supplierId);

}
