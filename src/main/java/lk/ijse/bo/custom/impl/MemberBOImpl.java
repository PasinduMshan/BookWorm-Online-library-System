package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.MemberBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.MemberDAO;
import lk.ijse.dto.MemberDto;
import lk.ijse.entity.Member;

import java.sql.SQLException;
import java.util.ArrayList;

public class MemberBOImpl implements MemberBO {
    MemberDAO memberDAO = (MemberDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.MEMBER);

    @Override
    public String generateMemberId() throws SQLException {
        return memberDAO.generateId();
    }

    @Override
    public boolean addMember(MemberDto dto) throws SQLException {
        return memberDAO.save(new Member(
                dto.getMemberId(),
                dto.getName(),
                dto.getAddress(),
                dto.getEmail(),
                dto.getContact(),
                dto.getD_O_B()
        ));
    }

    @Override
    public boolean deleteMember(String id) throws SQLException {
        return memberDAO.delete(id);
    }

    @Override
    public boolean updateMember(MemberDto dto) throws SQLException {
        return memberDAO.update(new Member(
                dto.getMemberId(),
                dto.getName(),
                dto.getAddress(),
                dto.getEmail(),
                dto.getContact(),
                dto.getD_O_B()
        ));
    }

    @Override
    public MemberDto searchMember(String contact) throws SQLException {
        Member member = memberDAO.searchMember(contact);

        return new MemberDto(
                member.getMemberId(),
                member.getName(),
                member.getAddress(),
                member.getEmail(),
                member.getContact(),
                member.getD_O_B()
        );
    }

    @Override
    public ArrayList<MemberDto> getAllMember() throws SQLException {
        ArrayList<MemberDto> memberDtos = new ArrayList<>();

        ArrayList<Member> members = memberDAO.getAll();

        for (Member member : members) {
            memberDtos.add(new MemberDto(
                    member.getMemberId(),
                    member.getName(),
                    member.getAddress(),
                    member.getEmail(),
                    member.getContact(),
                    member.getD_O_B()
            ));
        }
        return memberDtos;
    }

    @Override
    public MemberDto searchMemberById(String memberId) throws SQLException {
        Member member = memberDAO.search(memberId);

        return new MemberDto(
                member.getMemberId(),
                member.getName(),
                member.getAddress(),
                member.getEmail(),
                member.getContact(),
                member.getD_O_B()
        );
    }


}
