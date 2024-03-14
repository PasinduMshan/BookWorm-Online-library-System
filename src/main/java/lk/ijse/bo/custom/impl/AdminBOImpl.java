package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.AdminBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.AdminDAO;
import lk.ijse.dto.AdminDto;
import lk.ijse.entity.Admin;

import java.sql.SQLException;
import java.util.ArrayList;

public class AdminBOImpl implements AdminBO {
    AdminDAO adminDAO = (AdminDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ADMIN);

    @Override
    public String generateId() throws SQLException {
        return adminDAO.generateId();
    }

    @Override
    public boolean saveAdmin(AdminDto dto) throws SQLException {
        return adminDAO.save(new Admin(
                dto.getAdminId(),
                dto.getName(),
                dto.getEmail(),
                dto.getUserName(),
                dto.getPassword()
        ));
    }

    @Override
    public boolean deleteAdmin(String id) throws SQLException {
        return adminDAO.delete(id);
    }

    @Override
    public boolean updateAdmin(AdminDto dto) throws SQLException {
        return adminDAO.update(new Admin(
                dto.getAdminId(),
                dto.getName(),
                dto.getEmail(),
                dto.getUserName(),
                dto.getPassword()
        ));
    }

    @Override
    public AdminDto searchAdmin(String id) throws SQLException {
        Admin admin = adminDAO.search(id);

        return new AdminDto(
                admin.getAdminId(),
                admin.getName(),
                admin.getEmail(),
                admin.getUserName(),
                admin.getPassword()
        );
    }

    @Override
    public ArrayList<AdminDto> getAllAdmin() throws SQLException {
        ArrayList<AdminDto> adminDtos = new ArrayList<>();

        ArrayList<Admin> admins = adminDAO.getAll();

        for (Admin admin : admins) {
            adminDtos.add(new AdminDto(
                    admin.getAdminId(),
                    admin.getName(),
                    admin.getEmail(),
                    admin.getUserName(),
                    admin.getPassword()
            ));
        }
        return adminDtos;
    }
}
