package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.SignUpBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.AdminDAO;
import lk.ijse.dto.AdminDto;
import lk.ijse.entity.Admin;

import java.sql.SQLException;

public class SignUpBOImpl implements SignUpBO {

    AdminDAO adminDAO = (AdminDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ADMIN);

    @Override
    public String generateNextUserId() throws SQLException {
        return adminDAO.generateId();
    }

    @Override
    public boolean addAdmin(AdminDto dto) throws SQLException {
        return adminDAO.save(new Admin(dto.getAdminId(), dto.getName(), dto.getEmail(), dto.getUserName(), dto.getPassword()));
    }
}
