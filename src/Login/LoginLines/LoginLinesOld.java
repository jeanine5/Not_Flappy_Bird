package Login.LoginLines;

public enum LoginLinesOld implements LoginLinesInterface{
    USERNAME {
        @Override
        public String printUI() {
            return "Enter Username";
        }
    },
    PASSWORD {
        @Override
        public String printUI() {
            return "Enter Password";
        }
    },

    INVALID_LOGIN{
        @Override
        public String printUI() {
            return "Invalid Username or Password";
        }
    };

    static
    public final LoginLinesOld[] values = values();
    public LoginLinesOld next() {
        return values[(ordinal() + 1) % values.length];
    }
}
