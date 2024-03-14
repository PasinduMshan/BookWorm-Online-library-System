package lk.ijse.bo;

import lk.ijse.bo.custom.impl.*;

public class BOFactory {

    private static BOFactory boFactory;

    private BOFactory() {}

    public static BOFactory getBoFactory() {
        return boFactory == null ? boFactory = new BOFactory() : boFactory;
    }

    public enum BOTypes {
        SIGNUP, LOGIN, MEMBER, BRANCH, BOOK
    }

    public SuperBO getBO(BOTypes boTypes) {
        switch (boTypes) {
            case SIGNUP:
                return new SignUpBOImpl();
            case LOGIN:
                return new LoginBOImpl();
            case MEMBER:
                return new MemberBOImpl();
            case BRANCH:
                return new BranchBOImpl();
            case BOOK:
                return new BookBOImpl();
            default:
                return null;
        }
    }
}
