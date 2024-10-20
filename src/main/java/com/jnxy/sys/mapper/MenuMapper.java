package com.jnxy.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jnxy.sys.entity.Menu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 系统菜单表 Mapper 接口
 * </p>
 *
 * @author liyaolong
 * @since 2023-03-28
 */
public interface MenuMapper extends BaseMapper<Menu> {
    public List<Menu> getMenuListByUserId(@Param("userId") Integer userId,
                                          @Param("pid") Integer pid);

}
