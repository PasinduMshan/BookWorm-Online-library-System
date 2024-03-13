package lk.ijse.dao.custom;

import lk.ijse.dao.CrudDAO;
import lk.ijse.entity.Member;

import java.sql.SQLException;

public interface MemberDAO extends CrudDAO<Member> {

    Member searchMember(String contact) throws SQLException;
}
