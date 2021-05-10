package com.luoyingmm.dao.role;

import com.luoyingmm.dao.BaseDao;
import com.luoyingmm.pojo.Role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoleDaoImpl implements RoleDao{
    @Override
    public List<Role> getRoleList(Connection connection) throws SQLException {
        PreparedStatement pstm = null;
        ResultSet rs = null;
        ArrayList<Role> roles = new ArrayList<>();
        if (connection != null){
            String sql = "select * from smbms_role";
            Object[] param = {};
            rs = BaseDao.execute(connection, pstm, rs, sql, param);
            while (rs.next()){
                Role role = new Role();
                role.setId(rs.getInt("id"));
                role.setRoleCode(rs.getString("RoleCode"));
                role.setRoleName(rs.getString("RoleName"));
                roles.add(role);
            }
            BaseDao.closeResource(null,pstm,rs);
        }
        return roles;
    }
}
