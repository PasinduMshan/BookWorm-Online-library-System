package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.DashBoardBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.QueryDAO;
import lk.ijse.dto.QueryDto;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DashBoardBOImpl implements DashBoardBO {
    QueryDAO queryDAO = (QueryDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.QUERY);

    @Override
    public ArrayList<QueryDto> loadAllLateReturn() throws SQLException {
        ArrayList<QueryDto> queryDtos = new ArrayList<>();

        List<Object[]> allLateReturns = queryDAO.getAllLateReturns();

        for (Object[] objects : allLateReturns) {

            LocalDate borrowDate = (LocalDate) objects[3];
            LocalDate returnDate = (LocalDate) objects[4];

            queryDtos.add(new QueryDto(
                    (String) objects[0],
                    (String) objects[1],
                    (String) objects[2],
                    String.valueOf(borrowDate),
                    String.valueOf(returnDate)
            ));
        }
        return queryDtos;
    }
}
