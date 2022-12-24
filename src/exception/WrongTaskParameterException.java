package exception;

public class WrongTaskParameterException extends Exception{
   private final String parameter;

    public WrongTaskParameterException(String parameter) {
        this.parameter = parameter;
    }

    @Override
    public String getMessage() {
        return "Параметр \'"+parameter+"\' задан неверно!";
    }
}
