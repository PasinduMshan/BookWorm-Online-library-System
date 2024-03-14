package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.BranchBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.AdminDAO;
import lk.ijse.dao.custom.BranchDAO;
import lk.ijse.dto.BranchDto;
import lk.ijse.entity.Branch;
import lk.ijse.lib.AdminConnection;

import java.sql.SQLException;
import java.util.ArrayList;

public class BranchBOImpl implements BranchBO {

    BranchDAO branchDAO = (BranchDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.BRANCH);
    AdminDAO adminDAO = (AdminDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ADMIN);

    @Override
    public String generateBranchId() throws SQLException {
        return branchDAO.generateId();
    }

    @Override
    public boolean saveBranch(BranchDto dto) throws SQLException {
       String userName = AdminConnection.getInstance().getUserName();
       String password = AdminConnection.getInstance().getPassword();

       return branchDAO.save(new Branch(
               dto.getBranchId(),
               dto.getName(),
               adminDAO.getID(userName,password)
       ));
    }

    @Override
    public boolean deleteBranch(String id) throws SQLException {
        return branchDAO.delete(id);
    }

    @Override
    public BranchDto searchBranch(String id) throws SQLException {
        Branch branch = branchDAO.search(id);

        return new BranchDto(branch.getBranchId(), branch.getName());

    }

    @Override
    public boolean updateBranch(BranchDto dto) throws SQLException {
        String userName = AdminConnection.getInstance().getUserName();
        String password = AdminConnection.getInstance().getPassword();

        return branchDAO.update(new Branch(
                dto.getBranchId(),
                dto.getName(),
                adminDAO.getID(userName,password)
        ));
    }

    @Override
    public ArrayList<BranchDto> getAllBranches() throws SQLException {
        ArrayList<BranchDto> branchDtos = new ArrayList<>();

        ArrayList<Branch> branches = branchDAO.getAll();

        for (Branch branch : branches) {
            branchDtos.add(new BranchDto(
               branch.getBranchId(),
               branch.getName()
            ));
        }
        return branchDtos;
    }
}
