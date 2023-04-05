package core.utils;

import core.exception.CampoInvalidoExceptions;

import java.util.Objects;

public class Utils {
    
    public static void validateGenericInput(Object object) throws CampoInvalidoExceptions {
        if (Objects.isNull(object) || object.toString().isEmpty()){
            throw new CampoInvalidoExceptions("O campo nome não pode estar vazio ou nulo");
        }
    }
}
