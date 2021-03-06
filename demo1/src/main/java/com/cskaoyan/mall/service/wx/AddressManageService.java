package com.cskaoyan.mall.service.wx;

import com.cskaoyan.mall.bean.Address;
import com.cskaoyan.mall.bean.Region;
import com.cskaoyan.mall.vo.DeleteAddVo;

import java.util.List;

/**
 * 地址管理
 * @yanhua
 */
public interface AddressManageService {
     //显示所有地址
     List<Address>  queryAllAddress();
     //查询省 市  区
     List<Region>  queryByPid(Integer pid);
     //新建地址（插入地址）
     int  insertAddress(Address address,int userId);

     //编辑时显示地址信息
     Address queryById(Integer id);
     //删除地址
     void deleteAddressById(DeleteAddVo deleteAddVo);

     //修改
     int updateByPrimaryKey(Address address);
}
