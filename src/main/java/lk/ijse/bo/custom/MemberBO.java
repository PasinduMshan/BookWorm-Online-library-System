package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.dto.MemberDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface MemberBO extends SuperBO {
    String generateMemberId() throws SQLException;

    boolean addMember(MemberDto dto) throws SQLException;

    boolean deleteMember(String id) throws  SQLException;

    boolean updateMember(MemberDto dto) throws SQLException;

    MemberDto searchMember(String contact) throws SQLException;

    ArrayList<MemberDto> getAllMember() throws SQLException;

    MemberDto searchMemberById(String memberId) throws SQLException;
}
