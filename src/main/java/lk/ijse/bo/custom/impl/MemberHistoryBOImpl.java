package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.MemberHistoryBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.MemberDAO;
import lk.ijse.dao.custom.QueryDAO;
import lk.ijse.dto.MemberDto;
import lk.ijse.dto.QueryDto;
import lk.ijse.entity.Member;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MemberHistoryBOImpl implements MemberHistoryBO {
    QueryDAO queryDAO = (QueryDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.QUERY);
    MemberDAO memberDAO = (MemberDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.MEMBER);

    @Override
    public List<QueryDto> getAllTransaction(String id) throws SQLException {
        ArrayList<QueryDto> queryDtos = new ArrayList<>();

        List<Object[]> allMemberHistory = queryDAO.getAllMemberHistory(id);

        for (Object[] objects : allMemberHistory) {

            LocalDate borrowDate = (LocalDate) objects[3];
            LocalDate returnDate = (LocalDate) objects[4];

            queryDtos.add(new QueryDto(
                    (String) objects[0],
                    (String) objects[1],
                    (String) objects[2],
                    String.valueOf(borrowDate),
                    String.valueOf(returnDate),
                    (String) objects[5]
            ));
        }
        return queryDtos;
    }

    @Override
    public MemberDto getMemberName(String id) throws SQLException {
        Member member = memberDAO.search(id);

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
