package Login.LoginLines;

public enum LoginLinesCreate implements LoginLinesInterface{
    CREATE_USERNAME {
        @Override
        public String printUI() {
            return "Enter Username. MUST BE UNIQUE AND HAVE 5-12 CHARACTERS";
        }
    },
    CREATE_PASSWORD {
        @Override
        public String printUI() {
            return "Enter Password. MUST BE 8-12 CHARACTERS";
        }
    },
    CREATE_ADMIN {
        @Override
        public String printUI() {
            return "ADMIN or NO";
        }
    },

    WRONG_INFO {
        @Override
        public String printUI() {
            return "Invalid Username or Password";
        }
    };

    static
    public final LoginLinesCreate[] values = values();
    public LoginLinesCreate next() {
        return values[(ordinal() + 1) % values.length];
    }

}
