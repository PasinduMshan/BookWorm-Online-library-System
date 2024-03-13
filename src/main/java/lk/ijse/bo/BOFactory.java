package lk.ijse.bo;

import lk.ijse.bo.custom.impl.LoginBOImpl;
import lk.ijse.bo.custom.impl.SignUpBOImpl;

public class BOFactory {

    private static BOFactory boFactory;

    private BOFactory() {}

    public static BOFactory getBoFactory() {
        return boFactory == null ? boFactory = new BOFactory() : boFactory;
    }

    public enum BOTypes {
        SIGNUP, LOGIN
    }

    public SuperBO getBO(BOTypes boTypes) {
        switch (boTypes) {
            case SIGNUP:
                return new SignUpBOImpl();
            case LOGIN:
                return new LoginBOImpl();
            default:
                return null;
        }
    }
}
